package com.mykh.mvp.converter;

import com.google.common.collect.ImmutableMap;
import com.mykh.mvp.converter.team.BasketballConverterTeam;
import com.mykh.mvp.converter.team.HandballConverterTeam;
import com.mykh.mvp.converter.team.TeamPlayerConverter;
import com.mykh.mvp.enumeration.SportType;
import com.mykh.mvp.exception.UnknownCsvType;
import com.mykh.mvp.model.TeamPlayer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Slf4j
public class CsvConvertionStrategy {

    private static final ImmutableMap<SportType, TeamPlayerConverter> CSV_CONVERSION_MAP = ImmutableMap.<SportType, TeamPlayerConverter>builder()
            .put(SportType.BASKETBALL, new BasketballConverterTeam())
            .put(SportType.HANDBALL, new HandballConverterTeam())
            //extend if needed
            .build();


    public static <T extends TeamPlayer> List<T> convertTeamPlayers(String header, String row) {
        SportType type = parseHeaderType(header);

        TeamPlayerConverter teamPlayerConverter = Optional.ofNullable(CSV_CONVERSION_MAP.get(type))
                .orElseThrow(() -> {
            log.debug("Please, add missed converter to the map");
            return new UnknownCsvType("Unknown CSV type");
        });

        return teamPlayerConverter.convertFromCsv(row);
    }

    private static SportType parseHeaderType(String header) {
        SportType type;

        try {
            type = SportType.valueOf(header);
        } catch (Exception ex) {
            log.error("Unknown CSV header received {}.", header);
            throw new UnknownCsvType(String.format("Unknown CSV type received with header: [%]", header));
        }

        return type;
    }
}
