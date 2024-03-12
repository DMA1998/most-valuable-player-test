package com.mykh.mvp.converter.team;

import com.mykh.mvp.model.BasketballPlayer;
import com.mykh.mvp.model.HandballPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dmytro Mykh on 12/03/2024
 */
 class TeamPlayerConverterTest {

    private static final String CSV_PATH = "src/test/resources/csv/";
    private static final String HANDBALL_CSV = CSV_PATH + "handball.csv";
    private static final String BASKETBALL_CSV = CSV_PATH + "basketball.csv";
    private static final String BASKETBALL_CSV_NOT_UNIQUE_PLAYER = CSV_PATH + "basketball_not_unique_nickname.csv";

    private final BaseTeamConverter<BasketballPlayer> basketballConverterTeam = new BasketballConverterTeam();

    private final BaseTeamConverter<HandballPlayer> handballConverterTeam = new HandballConverterTeam();

    @Test
    void testSuccessfulBasketballCsvConvert() {
        List<BasketballPlayer> basketballPlayers = basketballConverterTeam.convertFromCsv(BASKETBALL_CSV);
        assertEquals(expectedBasketballPlayers(), basketballPlayers);
    }

    @Test
    void testSuccessfulHandballCsvConvert() {
        List<HandballPlayer> handballPlayers = handballConverterTeam.convertFromCsv(HANDBALL_CSV);
        assertEquals(expectedHandballPlayers(), handballPlayers);
    }

    @Test
    void testFailedIndexExceeded() {
        ArrayIndexOutOfBoundsException ex = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> basketballConverterTeam.convertFromCsv(HANDBALL_CSV));

        assertEquals("Index 6 out of bounds for length 6", ex.getMessage());
    }

    @Test
    void testFailedPlayerWithoutUniqueNicknameFound() {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> basketballConverterTeam.convertFromCsv(BASKETBALL_CSV_NOT_UNIQUE_PLAYER));

        assertEquals("Duplicated player found. Result is not valid: nick1", ex.getMessage());
    }

    private List<HandballPlayer> expectedHandballPlayers() {
        return List.of(
                new HandballPlayer("player 1", "nick1", "4", "Team A", 0, 20),
                new HandballPlayer("player 2", "nick2", "8", "Team A", 15, 20),
                new HandballPlayer("player 3", "nick3", "15", "Team A", 10, 20),
                new HandballPlayer("player 4", "nick4", "16", "Team B", 1, 25),
                new HandballPlayer("player 5", "nick5", "23", "Team B", 12, 25),
                new HandballPlayer("player 6", "nick6", "42", "Team B", 8, 25)
        );
    }

    private List<BasketballPlayer> expectedBasketballPlayers() {
        return List.of(
                new BasketballPlayer("player 1", "nick1", "4", "Team A", 10, 2, 7),
                new BasketballPlayer("player 2", "nick2", "8", "Team A", 0, 10, 0),
                new BasketballPlayer("player 3", "nick3", "15", "Team A", 15, 10, 4),
                new BasketballPlayer("player 4", "nick4", "16", "Team B", 20, 0, 0),
                new BasketballPlayer("player 5", "nick5", "23", "Team B", 4, 7, 7),
                new BasketballPlayer("player 6", "nick6", "42", "Team B", 8, 10, 0)
        );
    }

}
