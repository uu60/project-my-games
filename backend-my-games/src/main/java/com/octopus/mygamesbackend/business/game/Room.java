package com.octopus.mygamesbackend.business.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Room {
    // 房间名
    private String name;
    // 房间人数
    private final List<String> players = new ArrayList<>();
    // 是否可以进入
    private volatile boolean available;

    public Room(String name, String... players) {
        this.name = name;
        this.players.addAll(Arrays.asList(players));
    }
}
