package com.mykh.mvp.util;

import com.mykh.mvp.model.TeamPlayer;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author Dmytro Mykh on 12/03/2024
 */
@NoArgsConstructor(access = PRIVATE)
public final class TeamGameUtils {

    public static <T extends TeamPlayer> void verifyUniqueNicknamePlayer(List<T> team) {
        Set<String> uniqueNicknames = new HashSet<>();

        for (T player : team) {
            String nickname = player.getNickname();

            if (!uniqueNicknames.add(nickname)) {
                throw new IllegalArgumentException(String.format("Duplicated player found. Result is not valid: %s", nickname));
            }
        }
    }

    public static <T extends TeamPlayer> List<T> updateWinnerPlayers(List<T> team) {
        Map<String, Integer> teamPoints = calculateTeamPoints(team);
        final String winningTeam = findWinningTeam(teamPoints);

        return team.stream()
                .peek(player -> {
                    if (player.getTeamName().equals(winningTeam)) {
                        player.setWinner(true);
                    }
                })
                .toList();
    }


    public static String findWinningTeam(Map<String, Integer> teamPoints) {
        return teamPoints.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static <T extends TeamPlayer> Map<String, Integer> calculateTeamPoints(List<T> listPlayers) {
        Map<String, Integer> teamPoints = new HashMap<>();

        for (T player : listPlayers) {
            teamPoints.merge(player.getTeamName(), player.getRatingPoints().intValue(), Integer::sum);
        }

        return teamPoints;
    }
}
