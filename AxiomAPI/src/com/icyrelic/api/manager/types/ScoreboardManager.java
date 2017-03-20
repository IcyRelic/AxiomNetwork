package com.icyrelic.api.manager.types;

import com.icyrelic.api.manager.Manager;
import com.icyrelic.api.scoreboard.AxiomBoard;

import java.util.HashMap;

/**
 * @author IcyRelic
 */
public class ScoreboardManager extends Manager {

    private HashMap<String, AxiomBoard> scoreboardList = new HashMap<>();

    public void setup() {

    }

    public AxiomBoard getBoard(String name){
        return scoreboardList.get(name);
    }
}
