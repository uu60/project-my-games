package com.octopus.mygamesbackend.business.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Game {
    public final static String[] allGames = {"odd_even", "gobang"};
    public final static Map<String, Integer> targetPlayerNumMap = new HashMap<>();
    static {
        targetPlayerNumMap.put("odd_even", 2);
        targetPlayerNumMap.put("gobang", 2);
    }
    protected final Map<String, PlayerData> playerDataMap = new ConcurrentHashMap<>();

    public abstract List<String> getAllPlayers();

    public Map<String, PlayerData> getPlayerDataMap() {
        return playerDataMap;
    }

}
