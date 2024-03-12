package com.mykh.mvp;

import com.mykh.mvp.model.BasketballPlayer;
import com.mykh.mvp.model.HandballPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Dmytro Mykh on 12/03/2024
 */
class RatingPointsCalculatorTest {

    @Test
    void testCalculationFormulaForBasketballLoser() {
        //scored points 3 * 2 + rebounds 5 * 1 + assists 2 + 1;
        BasketballPlayer basketballPlayer = new BasketballPlayer(null, null,null,null,3,5,2);
        Assertions.assertEquals(13, basketballPlayer.getRatingPoints().intValue());
    }

    @Test
    void testCalculationFormulaForBasketballWinner() {
        //scored points 3 * 2 + rebounds 5 * 1 + assists 2 + 1 (+ 10 as a player from winner team) ;
        BasketballPlayer basketballPlayer = new BasketballPlayer(null, null,null,null,3,5,2);
        basketballPlayer.setWinner(true);
        Assertions.assertEquals(23,  basketballPlayer.getRatingPoints().intValue());
    }

    @Test
    void testCalculationFormulaForHandballLoser() {
        //scored points 7 * 2 - goals received 2
        HandballPlayer handballPlayer =  new HandballPlayer("p1H", "n1H", "1", "Team C", 9, 3);
        Assertions.assertEquals(15, handballPlayer.getRatingPoints().intValue());
    }

    @Test
    void testCalculationFormulaForHandballWinner() {
        //scored points 7 * 2 - goals received 2 (+ 10 as a player from winner team) ;
        HandballPlayer handballPlayer =  new HandballPlayer("p1H", "n1H", "1", "Team C", 9, 3);
        handballPlayer.setWinner(true);
        Assertions.assertEquals(25,  handballPlayer.getRatingPoints().intValue());
    }


}
