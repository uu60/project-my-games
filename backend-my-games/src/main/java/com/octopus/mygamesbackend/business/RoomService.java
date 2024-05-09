package com.octopus.mygamesbackend.business;

import com.octopus.mygamesbackend.business.game.GobangGame;
import com.octopus.mygamesbackend.business.game.OddEvenGame;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.business.holder.DuoRoomHolder;
import com.octopus.mygamesbackend.business.holder.GameHolder;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class RoomService {
    @Autowired
    private ThreadPoolExecutor pool;

    public void waitPlayer(String game, String room, String username, DeferredResult<Resp> result, long timeOut) {
        pool.execute(() -> {
            DuoRoomHolder duoRoomHolder = DuoRoomHolder.get(game, room);
            duoRoomHolder.lock();
            try {
                // 房间现在可加入
                duoRoomHolder.open();
                duoRoomHolder.setWaitingThread(Thread.currentThread());
                boolean overTimeOrInterrupted = !duoRoomHolder.await(timeOut * 1000 * 1000);
                duoRoomHolder.setWaitingThread(null);
                // 超时或被打断
                if (overTimeOrInterrupted) {
                    duoRoomHolder.remove();
                    result.setResult(Resp.error(MyConstants.HTTP_USER_EXIT));
                    return;
                }
                // 人数够了被唤醒
                List<String> players = duoRoomHolder.getPlayerList();
                GameHolder gameHolder = GameHolder.get(game, room);
                switch (game) {
                    case OddEvenGame.GAME_NAME:
                        gameHolder.createGame(new OddEvenGame(players));
                        break;
                    case GobangGame.GAME_NAME:
                        gameHolder.createGame(new GobangGame(players));
                        break;
                }
                duoRoomHolder.remove();
                duoRoomHolder.signalAll();
                result.setResult(Resp.ok());
            } finally {
                duoRoomHolder.unlock();
            }
        });
    }

    public List<String> getPlayersByRoom(String game, String room, String username) {
        DuoRoomHolder duoRoomHolder = DuoRoomHolder.get(game, room);
        GameHolder gameHolder = GameHolder.get(game, room);
        if (gameHolder.getGame() == null) {
            duoRoomHolder.lock();
            try {
                while (gameHolder.getGame() == null) {
                    duoRoomHolder.await(0);
                }
                return new ArrayList<>(gameHolder.getGame().getPlayerDataMap().keySet());
            } finally {
                duoRoomHolder.unlock();
            }
        } else {
            return new ArrayList<>(gameHolder.getGame().getPlayerDataMap().keySet());
        }
    }

    public List<String> getOpponentsByRoom(String game, String room, String username) {
        List<String> players = getPlayersByRoom(game, room, username);
        List<String> opponents = new ArrayList<>();
        for (String player : players) {
            if (!player.equals(username)) {
                opponents.add(player);
            }
        }
        return opponents;
    }
}
