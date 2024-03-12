package com.mykh.mvp.util;

import com.mykh.mvp.model.BasketballPlayer;
import com.mykh.mvp.model.HandballPlayer;
import com.mykh.mvp.model.TeamPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dmytro Mykh on 12/03/2024
 */


@ExtendWith(MockitoExtension.class)
class TeamGameUtilsTest<T> {

    @Test
    public void updateBasketballTeamIncreasePointsToWinnerTeam() {
        List<BasketballPlayer> basketballPlayers = prepareBasketballPlayers();

        //Check winners
        int basketballPlayersWinnersScoredPoints = countPoints("Team B", basketballPlayers);
        int basketballPlayersLosersScoredPoints = countPoints("Team A", basketballPlayers);
        assertEquals(33, basketballPlayersWinnersScoredPoints);
        assertEquals(14, basketballPlayersLosersScoredPoints);

        List<BasketballPlayer> updatedBasketballPlayers = TeamGameUtils.updateWinnerPlayers(basketballPlayers);

        int updatedBasketballPlayersWinnersScoredPoints = countPoints("Team B", updatedBasketballPlayers);
        int updatedBasketballPlayersLosersScoredPoints = countPoints("Team A", updatedBasketballPlayers);

        assertEquals(53, updatedBasketballPlayersWinnersScoredPoints);
        assertEquals(14, updatedBasketballPlayersLosersScoredPoints);
    }


    @Test
    public void updateHandballTeamIncreasePointsToWinnerTeam() {
        List<HandballPlayer> basketballPlayers = prepareHandballPlayers();

        //Check winners
        int basketballPlayersWinnersScoredPoints = countPoints("Team C", basketballPlayers);
        int basketballPlayersLosersScoredPoints = countPoints("Team A", basketballPlayers);
        assertEquals(24, basketballPlayersWinnersScoredPoints);
        assertEquals(24, basketballPlayersLosersScoredPoints);

        List<HandballPlayer> updatedBasketballPlayers = TeamGameUtils.updateWinnerPlayers(basketballPlayers);

        int updatedBasketballPlayersWinnersScoredPoints = countPoints("Team C", updatedBasketballPlayers);
        int updatedBasketballPlayersLosersScoredPoints = countPoints("Team A", updatedBasketballPlayers);

        assertEquals(44, updatedBasketballPlayersWinnersScoredPoints);
        assertEquals(24, updatedBasketballPlayersLosersScoredPoints);
    }

    private <T extends TeamPlayer> int countPoints(String teamName, List<T> team) {
        return team.stream()
                .filter(p -> p.getTeamName().equals(teamName))
                .map(TeamPlayer::calculateRatingPoints)
                .map(BigDecimal::intValue)
                .reduce(0, Integer::sum);
    }

    //Team B is a winner
    private List<BasketballPlayer> prepareBasketballPlayers() {
        return List.of(
                new BasketballPlayer("p1", "n1", "3", "Team A", 1, 2, 3),
                new BasketballPlayer("p2", "n2", "4", "Team A", 1, 2, 3),
                new BasketballPlayer("p3", "n3", "5", "Team B", 10, 2, 4),
                new BasketballPlayer("p4", "n4", "7", "Team B", 1, 2, 3));
    }


    //TEAM C is a winner
    private List<HandballPlayer> prepareHandballPlayers() {
        return List.of(
                new HandballPlayer("p1H", "n1H", "1", "Team C", 7, 2)
                , new HandballPlayer("p2H", "n2H", "2", "Team C", 7, 2),
                new HandballPlayer("p3H", "n3H", "3", "Team A", 7, 2),
                new HandballPlayer("p4H", "n4H", "4", "Team A", 7, 2));
    }
}
