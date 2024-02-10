package com.octopus.mygamesbackend.service;

import com.octopus.mygamesbackend.utils.http.R;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

public interface RoomService {
    void waitPlayer(String game, String room, String username, DeferredResult<R> result, long timeOut);

    List<String> getPlayersByRoom(String game, String room, String username);

    List<String> getOpponentsByRoom(String game, String room, String username);

}
