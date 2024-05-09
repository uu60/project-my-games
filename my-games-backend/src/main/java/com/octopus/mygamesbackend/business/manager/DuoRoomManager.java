package com.octopus.mygamesbackend.business.manager;

import com.octopus.mygamesbackend.business.pojo.game.Game;
import com.octopus.mygamesbackend.business.pojo.game.Room;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DuoRoomManager {
    // 游戏 : (房间 : 玩家集合)
    private static final Map<String, Map<String, Room>> rooms = new ConcurrentHashMap<>();
    private static final Map<String, Map<String, DuoRoomManager>> managers = new ConcurrentHashMap<>();

    static {
        // 初始化房间容器
        for (String game : Game.allGames) {
            rooms.put(game, new ConcurrentHashMap<>());
            managers.put(game, new ConcurrentHashMap<>());
        }
    }

    private final String game;
    private final String room;
    // 等待玩家加入的线程
    private volatile Thread waitingThread;
    // 锁
    private final Lock lock = new ReentrantLock();
    // 等待条件
    private final Condition condition = lock.newCondition();

    private DuoRoomManager(String game, String room) {
        this.game = game;
        this.room = room;
    }

    public void setWaitingThread(Thread thread) {
        this.waitingThread = thread;
    }

    public Thread getWaitingThread() {
        return this.waitingThread;
    }

    public static DuoRoomManager manager(String game, String room) {
        if (getManagersByGame(game).get(room) == null) {
            synchronized (managers) {
                if (getManagersByGame(game).get(room) == null) {
                    getManagersByGame(game).put(room, new DuoRoomManager(game, room));
                }
            }
        }
        return getManagersByGame(game).get(room);
    }

    private static Map<String, DuoRoomManager> getManagersByGame(String game) {
        return managers.get(game);
    }

    public boolean existsRoom() {
        return getRoomsByGame(game).containsKey(room) || GameManager.manager(game, room).existsGame();
    }

    public void createRoom(String player) {
        lock();
        try {
            Room roomObj = new Room(room, player);
            getRoomsByGame(game).put(room, roomObj);
        } finally {
            unlock();
        }
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public boolean await(long millis) {
        try {
            if (millis <= 0) {
                condition.await();
                return true;
            } else {
                return condition.await(millis, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            log.info("waiting on [game: {} room: {}] interrupted.", game, room);
            return false;
        }
    }

    public void signalAll() {
        condition.signalAll();
    }

    public int getPlayerNum() {
        return getPlayerList().size();
    }

    public List<String> getPlayerList() {
        return getRoom().getPlayers();
    }

    private Room getRoom() {
        return getRoomsByGame(game).get(room);
    }

    public boolean addPlayer(String player, int targetPlayerNum) {
        lock();
        try {
            if (!existsRoom() || getPlayerNum() == targetPlayerNum || getRoom().getPlayers().contains(player)) {
                return false;
            }
            List<String> players = getPlayerList();
            players.add(player);
            if (getPlayerNum() == targetPlayerNum) {
                // 唤醒等待玩家加入的线程
                signalAll();
            }
        } finally {
            unlock();
        }
        return true;
    }

    public void remove() {
        getRoomsByGame(game).remove(room);
        getManagersByGame(game).remove(room);
    }

    private Map<String, Room> getRoomsByGame(String game) {
        return rooms.get(game);
    }

    public boolean isRoomAvailable() {
        return getRoom().isAvailable();
    }

    public void open() {
        getRoom().setAvailable(true);
    }

    public void close() {
        getRoom().setAvailable(false);
    }
}
