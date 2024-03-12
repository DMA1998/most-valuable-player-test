package com.mykh.mvp.service.csv;

import com.mykh.mvp.converter.CsvConvertStrategy;
import com.mykh.mvp.exception.UnknownCsvType;
import com.mykh.mvp.model.TeamPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public class CsvProcessorImpl implements CsvProcessor {

    @Override
    public <T extends TeamPlayer> List<List<T>> getTeamPlayersFromCsvFiles(List<String> csvFiles) {
        List<List<T>> players = new ArrayList<>();

        for (String csvFile : csvFiles) {
            String sportName = extractCsvSportType(csvFile);
            List<T> teamPlayers = CsvConvertStrategy.getTeamPlayers(sportName, csvFile);
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
            throw new UnknownCsvType(String.format("Cannot identify sport type of the CSV file: [%s]", csv));

        }

        return name;
    }

}
