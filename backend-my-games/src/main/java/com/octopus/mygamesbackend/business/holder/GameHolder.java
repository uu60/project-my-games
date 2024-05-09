package com.octopus.mygamesbackend.business.holder;

import com.octopus.mygamesbackend.business.game.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameHolder {
    // 游戏 : (原来的房间 : 游戏对象)
    private final static Map<String, Map<String, Game>> games = new ConcurrentHashMap<>();
    private static final Map<String, Map<String, GameHolder>> holders = new ConcurrentHashMap<>();

    static {
        for (String game : Game.allGames) {
            games.put(game, new ConcurrentHashMap<>());
            holders.put(game, new ConcurrentHashMap<>());
        }
    }

    private final String game;
    private final String room;

    private GameHolder(String game, String room) {
        this.game = game;
        this.room = room;
    }
    public static GameHolder get(String game, String room) {
        if (getHoldersByGame(game).get(room) == null) {
            synchronized (holders) {
                if (getHoldersByGame(game).get(room) == null) {
                    getHoldersByGame(game).put(room, new GameHolder(game, room));
                }
            }
        }
        return getHoldersByGame(game).get(room);
    }

    private static Map<String, GameHolder> getHoldersByGame(String game) {
        return holders.get(game);
    }

    public void createGame(Game gameObj) {
        getGamesByName(game).put(room, gameObj);
    }

    public boolean existsGame() {
        return getGame() != null;
    }

    public Game getGame() {
        return getGamesByName(game).get(room);
    }

    public void remove() {
        getGamesByName(game).remove(room);
        getHoldersByGame(game).remove(room);
    }

    private static Map<String, Game> getGamesByName(String game) {
        return games.get(game);
    }
}
