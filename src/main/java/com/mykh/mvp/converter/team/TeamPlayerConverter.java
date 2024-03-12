package com.mykh.mvp.converter.team;

import com.mykh.mvp.model.TeamPlayer;

import java.util.List;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@FunctionalInterface
public interface TeamPlayerConverter {

     <T extends TeamPlayer> List<T> convertFromCsv(String csv);
}
