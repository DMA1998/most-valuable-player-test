package com.mykh.mvp.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Dmytro Mykh on 11/03/2024
 */


@Getter
@Setter
public abstract class TeamPlayer extends Player {

    public abstract BigDecimal calculateRatingPoints();

    protected boolean isWinner;
    protected final String number;
    protected final String teamName;
    protected BigDecimal ratingPoints;

    public TeamPlayer(String name, String nickname, String number, String teamName) {
        super(name, nickname);
        this.number = number;
        this.teamName = teamName;
    }

}
