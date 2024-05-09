package com.octopus.mygamesbackend.business.game;

import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.utils.websocket.Resp;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

public class OddEvenGame extends Game {
    public final static String GAME_NAME = "odd_even";
    private final static int QUESTIONER = 0;
    private final static int GUESSER = 1;
    // 筹码
    private final static int INIT_CHIP = 10;
    // 保存出题线程
    private volatile Thread waitingQuestionerThread;
    // 本回合答案
    private volatile int tempAnswer = -1;

    public OddEvenGame(List<String> players) {
        // 随机决定起手角色
        int player0 = new Random().nextInt(2);
        for (int i = 0; i < players.size(); i++) {
            // 这个用户是出题人
            if (player0 == i) {
                playerDataMap.put(players.get(i), new OddOrEvenPlayerData(INIT_CHIP, QUESTIONER));
            } else {
                playerDataMap.put(players.get(i), new OddOrEvenPlayerData(INIT_CHIP, GUESSER));
            }
        }
    }

    public void handle(String username, int usedChip, int oddOrEven) throws IOException, InterruptedException {
        // 表示先提交的用户是猜题的
        OddOrEvenPlayerData currentPlayer = (OddOrEvenPlayerData) playerDataMap.get(username);
        if (currentPlayer.getRoleFlag() == GUESSER && tempAnswer == -1) {
            // 挂起当前线程
            waitingQuestionerThread = Thread.currentThread();
            LockSupport.park();
        }
        if (currentPlayer.getRoleFlag() == GUESSER) { // 出过题了
            // 奇数
            int isOdd = tempAnswer % 2;
            // 寻找对手
            OddOrEvenPlayerData opponent = null;
            String opponentName = null;
            for (String player : playerDataMap.keySet()) {
                if (!player.equals(username)) {
                    opponentName = player;
                    opponent = (OddOrEvenPlayerData) playerDataMap.get(player);
                }
            }
            if (isOdd == oddOrEven) {
                // 当前用户猜对了，对手给筹码
                currentPlayer.setChip(currentPlayer.chip + usedChip);
                opponent.setChip(opponent.chip - usedChip);
            } else {
                // 猜错了
                currentPlayer.setChip(currentPlayer.chip - usedChip);
                opponent.setChip(opponent.chip + usedChip);
            }

            // 游戏结束情况
            if (currentPlayer.chip <= 0 || opponent.chip <= 0) {
                currentPlayer.setGameOver(true);
                opponent.setGameOver(true);
            } else {
                currentPlayer.switchRole();
                opponent.switchRole();
            }
            currentPlayer.setPrevOpponentUsedChip(tempAnswer);
            opponent.setPrevOpponentUsedChip(usedChip);

            WebSocketSessionPoolUtils.sendMessage(username, Resp.ok(1).put("data", currentPlayer));
            WebSocketSessionPoolUtils.sendMessage(opponentName, Resp.ok(1).put("data", opponent));
            this.tempAnswer = -1;
            // 游戏结束
            if (currentPlayer.chip <= 0 || opponent.chip <= 0) {
                Thread.sleep(5000);
                WebSocketSessionPoolUtils.close(username);
                WebSocketSessionPoolUtils.close(opponentName);
            }
        } else { // 当前用户是出题人，看一下是否有挂起猜题线程
            this.tempAnswer = usedChip;
            if (waitingQuestionerThread != null) {
                LockSupport.unpark(waitingQuestionerThread);
            }
        }
    }

    @Override
    public List<String> getAllPlayers() {
        return new ArrayList<>(playerDataMap.keySet());
    }

    @Data
    public static class OddOrEvenPlayerData extends PlayerData {
        // 剩余筹码
        private int chip;
        // 身份标志
        // 0-出题人 1-猜题人
        private int roleFlag;
        private boolean gameOver;
        private int prevOpponentUsedChip;

        public OddOrEvenPlayerData(int chip, int roleFlag) {
            this.chip = chip;
            this.roleFlag = roleFlag;
        }

        public void switchRole() {
            this.roleFlag = this.roleFlag == 0 ? 1 : 0;
        }
    }
}
