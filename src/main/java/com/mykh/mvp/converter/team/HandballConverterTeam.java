package com.mykh.mvp.converter.team;

import com.mykh.mvp.model.HandballPlayer;
import com.mykh.mvp.util.GameUtils;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public class HandballConverterTeam extends BaseTeamConverter<HandballPlayer> implements TeamPlayerConverter {

    private static final String CSV_HEADER = "player name;nickname;number;team name;goals made;goals received";
    private static final String GOALS_MADE = "goals made";
    private static final String GOALS_RECEIVED = "goals received";

    @Override
    @SneakyThrows
    public List<HandballPlayer> convertFromCsv(String csv) {
        List<HandballPlayer> players = new ArrayList<>();
        List<String> rows = super.rowsFromCsv(csv);

        Map<String, Integer> csvIndexMap = prepareCsvHeaderIndexMap(CSV_HEADER);

        rows.forEach(row -> {
            String[] metadata = row.split(CSV_DELIMITER);
            fillPlayers(csvIndexMap, metadata, players);
        });

        return GameUtils.updateWinnerPlayers(players);
    }


    @Override
    protected void fillPlayers(Map<String, Integer> csvHeaderIndexMap, String[] metadata, List<HandballPlayer> players) {
        final String playerName = metadata[csvHeaderIndexMap.get(PLAYER_NAME)];
        final String nickname = metadata[csvHeaderIndexMap.get(NICKNAME)];
        final String number = metadata[csvHeaderIndexMap.get(NUMBER)];
        final String teamName = metadata[csvHeaderIndexMap.get(TEAM_NAME)];
        final String goalsMade = metadata[csvHeaderIndexMap.get(GOALS_MADE)];
        final String goalsReceived = metadata[csvHeaderIndexMap.get(GOALS_RECEIVED)];

        players.add(new HandballPlayer(playerName, nickname, number, teamName, Integer.parseInt(goalsMade), Integer.parseInt(goalsReceived)));
    }
}
