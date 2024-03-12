package com.mykh.mvp.service.csv;

import com.mykh.mvp.model.TeamPlayer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author Dmytro Mykh on 11/03/2024
 */
public interface CsvProcessor {

    <T extends TeamPlayer> List<List<T>> playersFromCsvRows(List<String> csvFiles);
}