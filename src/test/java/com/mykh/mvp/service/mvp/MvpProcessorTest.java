package com.mykh.mvp.service.mvp;

import com.mykh.mvp.exception.UnknownCsvType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Dmytro Mykh on 12/03/2024
 */


class MvpProcessorTest {

    private static final String CSV_PATH = "src/test/resources/csv/";

    private static final String HANDBALL_CSV = CSV_PATH + "handball.csv";
    private static final String BASKETBALL_CSV = CSV_PATH + "basketball.csv";
    private static final String UNKNOWN_CSV = CSV_PATH + "unknown.csv";

    private final MvpProcessor mvpProcessor = new MvpProcessorImpl<>();

    @Test
    void mvpProcessorSuccessTestSingleGame() {
        String mvp = mvpProcessor.getMvpNickname(List.of(HANDBALL_CSV));
        assertEquals("nick2", mvp);
    }

    @Test
    void mvpProcessorSuccessTestMultipleGames() {
        String mvp = mvpProcessor.getMvpNickname(List.of(BASKETBALL_CSV, HANDBALL_CSV));
        assertEquals("nick3", mvp);
    }

    @Test
    void mvpProcessorSuccessTestFailedUnknownSportHeader() {
        UnknownCsvType unknownCsvType = assertThrows(UnknownCsvType.class,
                () -> mvpProcessor.getMvpNickname(List.of(HANDBALL_CSV, UNKNOWN_CSV)));

        assertEquals("Unknown CSV type received with header: [UNKNOWN]", unknownCsvType.getMessage());
    }

    @Test
    void mvpProcessorSuccessTestFailedFileNotExists() {
        UnknownCsvType unknownCsvType = assertThrows(UnknownCsvType.class,
                () -> mvpProcessor.getMvpNickname(List.of("my_file.csv")));

        assertEquals("Cannot identify sport type of the CSV file: [my_file.csv]", unknownCsvType.getMessage());
    }

}
