package com.mykh.mvp.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Getter
@Setter
public class BasketballPlayer extends TeamPlayer {

    private final int scoredPoint;
    private final int rebounds;
    private final int assists;

    public BasketballPlayer(String name, String nickname, String number,
                            String teamName, int scoredPoint, int rebounds, int assists) {
        super(name, nickname, number, teamName);
        this.scoredPoint = scoredPoint;
        this.rebounds = rebounds;
        this.assists = assists;
        setRatingPoints(calculateRatingPoints());
    }

    @Override
    public BigDecimal calculateRatingPoints() {
        int winnerCounts = super.isWinner ? 10 : 0;
        int scoredPoints = this.scoredPoint * 2;

        return new BigDecimal(scoredPoints + this.rebounds + this.assists + winnerCounts);
    }

}
