package com.octopus.mygamesbackend.utils.manager;

import com.octopus.mygamesbackend.pojo.game.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    // 游戏 : (原来的房间 : 游戏对象)
    private final static Map<String, Map<String, Game>> games = new ConcurrentHashMap<>();
    private static final Map<String, Map<String, GameManager>> managers = new ConcurrentHashMap<>();

    static {
        for (String game : Game.allGames) {
            games.put(game, new ConcurrentHashMap<>());
            managers.put(game, new ConcurrentHashMap<>());
        }
    }

    private final String game;
    private final String room;

    private GameManager(String game, String room) {
        this.game = game;
        this.room = room;
    }
    public static GameManager manager(String game, String room) {
        if (getManagersByGame(game).get(room) == null) {
            synchronized (managers) {
                if (getManagersByGame(game).get(room) == null) {
                    getManagersByGame(game).put(room, new GameManager(game, room));
                }
            }
        }
        return getManagersByGame(game).get(room);
    }

    private static Map<String, GameManager> getManagersByGame(String game) {
        return managers.get(game);
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
        getManagersByGame(game).remove(room);
    }

    private static Map<String, Game> getGamesByName(String game) {
        return games.get(game);
    }
}
