package com.octopus.mygamesbackend.business;

import com.octopus.mygamesbackend.business.pojo.game.GobangGame;
import com.octopus.mygamesbackend.business.pojo.game.OddEvenGame;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.business.manager.DuoRoomManager;
import com.octopus.mygamesbackend.business.manager.GameManager;
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
            DuoRoomManager duoRoomManager = DuoRoomManager.manager(game, room);
            duoRoomManager.lock();
            try {
                // 房间现在可加入
                duoRoomManager.open();
                duoRoomManager.setWaitingThread(Thread.currentThread());
                boolean overTimeOrInterrupted = !duoRoomManager.await(timeOut * 1000 * 1000);
                duoRoomManager.setWaitingThread(null);
                // 超时或被打断
                if (overTimeOrInterrupted) {
                    duoRoomManager.remove();
                    result.setResult(Resp.error(MyConstants.HTTP_USER_EXIT));
                    return;
                }
                // 人数够了被唤醒
                List<String> players = duoRoomManager.getPlayerList();
                GameManager gameManager = GameManager.manager(game, room);
                switch (game) {
                    case OddEvenGame.GAME_NAME:
                        gameManager.createGame(new OddEvenGame(players));
                        break;
                    case GobangGame.GAME_NAME:
                        gameManager.createGame(new GobangGame(players));
                        break;
                }
                duoRoomManager.remove();
                duoRoomManager.signalAll();
                result.setResult(Resp.ok());
            } finally {
                duoRoomManager.unlock();
            }
        });
    }

    public List<String> getPlayersByRoom(String game, String room, String username) {
        DuoRoomManager duoRoomManager = DuoRoomManager.manager(game, room);
        GameManager gameManager = GameManager.manager(game, room);
        if (gameManager.getGame() == null) {
            duoRoomManager.lock();
            try {
                while (gameManager.getGame() == null) {
                    duoRoomManager.await(0);
                }
                return new ArrayList<>(gameManager.getGame().getPlayerDataMap().keySet());
            } finally {
                duoRoomManager.unlock();
            }
        } else {
            return new ArrayList<>(gameManager.getGame().getPlayerDataMap().keySet());
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
