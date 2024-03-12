package com.mykh.mvp.converter.team;

import com.mykh.mvp.model.TeamPlayer;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public abstract class BaseTeamConverter<T extends TeamPlayer> implements TeamPlayerConverter {

    protected static final String CSV_DELIMITER = ";";

    protected static final String PLAYER_NAME = "player name";

    protected static final String NICKNAME = "nickname";

    protected static final String NUMBER = "number";

    protected static final String TEAM_NAME = "team name";

    protected abstract void fillPlayers(Map<String, Integer> csvHeaderIndexMap, String[] metadata, List<T> players);

    protected List<String> rowsFromCsv(String csv) throws IOException, CsvException {
        List<String> rows;

        try (FileReader filereader = new FileReader(csv);
             CSVReader csvReader = prepareCsvReader(filereader)) {

            rows = csvReader.readAll()
                    .stream()
                    .flatMap(Arrays::stream)
                    .toList();

        }
        return rows;
    }

    protected Map<String, Integer> prepareCsvHeaderIndexMap(String header) {
        String[] values = header.split(CSV_DELIMITER);
        return Arrays.stream(values)
                .collect(HashMap::new, (map, key) -> map.put(key, map.size()), HashMap::putAll);
    }

    private CSVReader prepareCsvReader(FileReader fileReader) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(new CSVParserBuilder()
                        .withEscapeChar('\0')
                        .build())
                .build();

        // Skip the first line (sport type)
        csvReader.readNext();

        return csvReader;
    }

}
