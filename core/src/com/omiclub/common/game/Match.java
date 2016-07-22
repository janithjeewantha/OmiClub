package com.omiclub.common.game;

import java.util.Collections;

/**
 * Created by janith on 7/22/16.
 */
public class Match {
    private Game game;
    private int trumps;

    public Match(Game game) {
        this.game = game;
        Collections.shuffle(game.getDummyCards());

    }
}
