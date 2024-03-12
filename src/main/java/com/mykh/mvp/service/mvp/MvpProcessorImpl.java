package com.mykh.mvp.service.mvp;

import com.mykh.mvp.model.TeamPlayer;
import com.mykh.mvp.service.csv.CsvProcessor;
import com.mykh.mvp.service.csv.CsvProcessorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro Mykh on 12/03/2024
 */

public class MvpProcessorImpl<T extends TeamPlayer> implements MvpProcessor {

    private final CsvProcessor csvProcessor;

    public MvpProcessorImpl() {
        this.csvProcessor = new CsvProcessorImpl();
    }

    @Override
    public String getMvpNickname(List<String> csvFiles) {
        List<List<T>> listPlayers = csvProcessor.playersFromCsvRows(csvFiles);

        Map<String, Integer> playerPoints = calculatePlayerPoints(listPlayers);

        return playerPoints.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private Map<String, Integer> calculatePlayerPoints(List<List<T>> listPlayers) {
        Map<String, Integer> playerPoints = new HashMap<>();
        for (List<T> players : listPlayers) {
            for (T player : players) {
                playerPoints.merge(player.getNickname(), player.calculateRatingPoints().intValue(), Integer::sum);
            }
        }

        return playerPoints;
    }

}
