package com.mykh.mvp.service.csv;

import com.mykh.mvp.converter.CsvConvertionStrategy;
import com.mykh.mvp.model.TeamPlayer;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public class CsvProcessorImpl<T extends TeamPlayer> implements CsvProcessor {

    @Override
    public <T extends TeamPlayer> List<List<T>> playersFromCsvRows(List<String> csvFiles) {
        List<List<T>> players = new ArrayList<>();

        for (String csvFile : csvFiles) {
            String sportName = extractCsvSportType(csvFile);
            List<T> teamPlayers = CsvConvertionStrategy.convertTeamPlayers(sportName, csvFile);
            players.add(teamPlayers);
        }

        return players;
    }

    private String extractCsvSportType(String csv) {
        String name;

        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            name = br.lines()
                    .limit(1)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("CSV file is empty"));// Limit to only the first line

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return name;
    }

}
