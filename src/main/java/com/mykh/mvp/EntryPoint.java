package com.mykh.mvp;

import com.mykh.mvp.service.mvp.MvpProcessor;
import com.mykh.mvp.service.mvp.MvpProcessorImpl;
import lombok.SneakyThrows;

import java.util.List;


/**
 * @author Dmytro Mykh on 11/03/2024
 */

public class EntryPoint {

    @SneakyThrows
    public static void main(String[] args) {
        MvpProcessor processor = new MvpProcessorImpl<>();
        String mvpPlayer = processor.getMvp(List.of("src/main/resources/csv/basketball.csv", "src/main/resources/csv/handball.csv"));
        System.out.println(mvpPlayer);
    }

}