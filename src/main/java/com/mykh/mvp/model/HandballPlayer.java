package com.mykh.mvp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class HandballPlayer extends TeamPlayer {

    private final int goalsMade;
    private final int goalsReceived;

    public HandballPlayer(String name, String nickname, String number, String teamName, int goalsMade, int goalsReceived) {
        super(name, nickname, number, teamName);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
        setRatingPoints(getRatingPoints());
    }

    @Override
    public BigDecimal getRatingPoints() {
        int winnerCounts = super.isWinner ? 10 : 0;
        int goalsMadePoints = this.goalsMade * 2;

        return new BigDecimal(goalsMadePoints - this.goalsReceived + winnerCounts);
    }

}
