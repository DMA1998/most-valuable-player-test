package com.mykh.mvp.converter.team;

import com.mykh.mvp.model.BasketballPlayer;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public class BasketballConverterTeam extends BaseTeamConverter<BasketballPlayer> implements TeamPlayerConverter {

    private static final String CSV_HEADER = "player name;nickname;number;team name;scored points;rebounds;assists";
    private static final String SCORED_POINTS = "scored points";
    private static final String REBOUNDS = "rebounds";
    private static final String ASSISTS = "assists";

    @Override
    protected String getCsvHeader() {
        return CSV_HEADER;
    }

    @Override
    public List<BasketballPlayer> convertFromCsv(String csv) {
        return super.convertFromCsv(csv);
    }

    @Override
    protected void fillPlayers(Map<String, Integer> csvHeaderIndexMap, String[] metadata, List<BasketballPlayer> players) {
        final String playerName = metadata[csvHeaderIndexMap.get(PLAYER_NAME)];
        final String nickname = metadata[csvHeaderIndexMap.get(NICKNAME)];
        final String number = metadata[csvHeaderIndexMap.get(NUMBER)];
        final String teamName = metadata[csvHeaderIndexMap.get(TEAM_NAME)];
        final String scoredPoints = metadata[csvHeaderIndexMap.get(SCORED_POINTS)];
        final String rebounds = metadata[csvHeaderIndexMap.get(REBOUNDS)];
        final String assists = metadata[csvHeaderIndexMap.get(ASSISTS)];


        players.add(new BasketballPlayer(playerName, nickname, number, teamName,
                Integer.parseInt(scoredPoints), Integer.parseInt(rebounds), Integer.parseInt(assists)));
    }
}
