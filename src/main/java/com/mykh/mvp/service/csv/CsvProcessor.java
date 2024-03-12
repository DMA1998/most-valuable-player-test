package com.mykh.mvp.service.csv;

import com.mykh.mvp.model.TeamPlayer;

import java.util.List;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public interface CsvProcessor {

    <T extends TeamPlayer> List<List<T>> getTeamPlayersFromCsvFiles(List<String> csvFiles);
}
