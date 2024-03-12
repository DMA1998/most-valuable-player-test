package com.mykh.mvp.converter;

import com.google.common.collect.ImmutableMap;
import com.mykh.mvp.converter.team.BasketballConverterTeam;
import com.mykh.mvp.converter.team.HandballConverterTeam;
import com.mykh.mvp.converter.team.TeamPlayerConverter;
import com.mykh.mvp.enumeration.SportType;
import com.mykh.mvp.exception.UnknownCsvType;
import com.mykh.mvp.model.TeamPlayer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public final class CsvConvertStrategy {

    private static final ImmutableMap<SportType, TeamPlayerConverter> CSV_CONVERSION_MAP = ImmutableMap.<SportType, TeamPlayerConverter>builder()
            .put(SportType.BASKETBALL, new BasketballConverterTeam())
            .put(SportType.HANDBALL, new HandballConverterTeam())
            //extend if needed
            .build();

    public static <T extends TeamPlayer> List<T> getTeamPlayers(String sportName, String row) {
        SportType type = parseHeaderType(sportName);

        TeamPlayerConverter teamPlayerConverter = Optional.ofNullable(CSV_CONVERSION_MAP.get(type))
                .orElseThrow(() -> {
            log.debug("Please, add missed converter to the map");
            return new UnknownCsvType("Unknown CSV type");
        });

        return teamPlayerConverter.convertFromCsv(row);
    }

    private static SportType parseHeaderType(String sportName) {
        SportType type;

        try {
            type = SportType.valueOf(sportName);
        } catch (Exception ex) {
            log.error("Unknown CSV header received {}.", sportName);
            throw new UnknownCsvType(String.format("Unknown CSV type received with header: [%s]", sportName));
        }

        return type;
    }
}
