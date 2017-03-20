package com.icyrelic.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public class AxiomBoard {

    private ScoreboardManager sbManager;
    private Scoreboard board;
    private Objective objective;
    private List<AxiomTeam> teams;
    public AxiomBoard(String name, DisplaySlot slot){
        this.sbManager = Bukkit.getScoreboardManager();
        this.board = sbManager.getNewScoreboard();
        this.teams = new ArrayList<>();
        objective = board.registerNewObjective(name, "dummy");
        objective.setDisplaySlot(slot);
    }

    public Scoreboard getScoreboard(){
        return this.board;
    }

    public void registerTeam(AxiomTeam axiomTeam){
        Team team = board.registerNewTeam(axiomTeam.getName());
        team.setPrefix(axiomTeam.getPrefix());
        team.setDisplayName(axiomTeam.getName());
        axiomTeam.getPlayers().forEach(team::addPlayer);
        teams.add(axiomTeam);
    }

    public Team getTeam(String name){
        return board.getTeam(name);
    }

    public Objective getObjective(){
       return this.objective;
    }


}
