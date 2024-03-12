package com.mykh.mvp;

import com.mykh.mvp.service.mvp.MvpProcessor;
import com.mykh.mvp.service.mvp.MvpProcessorImpl;

import java.util.List;


/**
 * @author Dmytro Mykh on 11/03/2024
 */

public class EntryPoint {

    public static void main(String[] args) {
        MvpProcessor processor = new MvpProcessorImpl<>();
        String mvpPlayer = processor.getMvpNickname(List.of("src/main/resources/basketball.csv", "src/main/resources/handball.csv"));
        System.out.println(mvpPlayer);
    }

}