package com.icyrelic.api.scoreboard;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public class AxiomTeam {

    private AxiomBoard board;
    private String name;
    private String prefix;
    private List<Player> players;

    public AxiomTeam(AxiomBoard board, String name, String prefix){
        this.board = board;
        this.name = name;
        this.prefix = prefix;
        this.players = new ArrayList<>();
    }

    public AxiomBoard getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }
}
