package com.mykh.mvp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Dmytro Mykh on 11/03/2024
 */

@Setter
@EqualsAndHashCode(callSuper = true ,exclude = {"ratingPoints", "isWinner"})
public abstract class TeamPlayer extends Player {

    public abstract BigDecimal getRatingPoints();

    @Getter
    protected boolean isWinner;
    @Getter
    protected final String number;
    @Getter
    protected final String teamName;
    protected BigDecimal ratingPoints;

    public TeamPlayer(String name, String nickname, String number, String teamName) {
        super(name, nickname);
        this.number = number;
        this.teamName = teamName;
    }

}
