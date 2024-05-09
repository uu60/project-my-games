package com.octopus.mygamesbackend.business.game;

import com.octopus.mygamesbackend.utils.websocket.WebSocketSessionPoolUtils;
import com.octopus.mygamesbackend.utils.websocket.Resp;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GobangGame extends Game {
    public final static String GAME_NAME = "gobang";
    private final static int BLACK = 0;
    private final static int WHITE = 1;
    private final int[][] gomoku = new int[15][15];

    {
        for (int i = 0; i < gomoku.length; i++) {
            for (int j = 0; j < gomoku[0].length; j++) {
                gomoku[i][j] = -1;
            }
        }
    }

    public GobangGame(List<String> players) {
        // 随机决定起手角色
        int player0 = new Random().nextInt(2);
        for (int i = 0; i < players.size(); i++) {
            // 这个用户是黑色
            if (player0 == i) {
                playerDataMap.put(players.get(i), new GobangPlayerData(BLACK));
            } else {
                playerDataMap.put(players.get(i), new GobangPlayerData(WHITE));
            }
        }
    }

    @Override
    public List<String> getAllPlayers() {
        return new ArrayList<>(playerDataMap.keySet());
    }

    private int switchTurn() {
        int curTurn = 0;
        for (String player : playerDataMap.keySet()) {
            curTurn = ((GobangPlayerData) playerDataMap.get(player)).turn == 0 ? 1 : 0;
            break;
        }
        for (String player : playerDataMap.keySet()) {
            ((GobangPlayerData) playerDataMap.get(player)).turn = curTurn;
        }
        // 返回旧值
        return curTurn == 0 ? 1 : 0;
    }

    private void setPiece(int x, int y) {
        for (String player : playerDataMap.keySet()) {
            ((GobangPlayerData) playerDataMap.get(player)).newPiece = x + "," + y;
        }
    }

    public void handle(int x, int y, String username) {
        String opponentName = null;
        for (String player : playerDataMap.keySet()) {
            if (!player.equals(username)) {
                opponentName = player;
            }
        }
        int caller = switchTurn();
        setPiece(x, y);
        gomoku[x][y] = caller;
        boolean over = checkGameOver(x, y, caller);
        int winner = over ? caller : -1;
        if (winner != -1) {
            // 游戏已经结束了
            for (String player : playerDataMap.keySet()) {
                ((GobangPlayerData) playerDataMap.get(player)).winner = winner;
            }
            WebSocketSessionPoolUtils.sendMessage(username, Resp.ok(1).put("data", playerDataMap.get(username)));
            WebSocketSessionPoolUtils.sendMessage(opponentName, Resp.ok(1).put("data", playerDataMap.get(opponentName)));
            try {
                Thread.sleep(5000);
                WebSocketSessionPoolUtils.close(username);
                WebSocketSessionPoolUtils.close(opponentName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            WebSocketSessionPoolUtils.sendMessage(username, Resp.ok(1).put("data", playerDataMap.get(username)));
            WebSocketSessionPoolUtils.sendMessage(opponentName, Resp.ok(1).put("data", playerDataMap.get(opponentName)));
        }

    }

    private boolean checkGameOver(int x, int y, int roleFlag) {
        // 横着经过的所有格
        List<Integer> row = new ArrayList<>();
        List<Integer> line = new ArrayList<>();
        List<Integer> mainDiagonal = new ArrayList<>();
        List<Integer> minorDiagonal = new ArrayList<>();
        for (int i = -4; i <= 4; i++) {
            if (x + i >= 0 && x + i < 15) {
                row.add(gomoku[x + i][y]);
                if (y - i >= 0 && y - i < 15) {
                    mainDiagonal.add(gomoku[x + i][y - i]);
                }
                if (y + i >= 0 && y + i < 15) {
                    minorDiagonal.add(gomoku[x + i][y + i]);
                }
            }
            if (y + i >= 0 && y + i < 15) {
                line.add(gomoku[x][y + i]);
            }
        }
        boolean done = hasFiveConnected(row, roleFlag);
        if (done) {
            return true;
        }
        done = hasFiveConnected(line, roleFlag);
        if (done) {
            return true;
        }
        done = hasFiveConnected(mainDiagonal, roleFlag);
        if (done) {
            return true;
        }
        done = hasFiveConnected(minorDiagonal, roleFlag);
        return done;
    }

    private static boolean hasFiveConnected(List<Integer> list, int roleFlag) {
        int cur = 0;
        int i = 0;
        for (Integer piece : list) {
            if (piece == roleFlag) {
                cur++;
                if (cur == 5) {
                    return true;
                }
            } else {
                cur = 0;
                if (list.size() - i <= 5) {
                    break;
                }
            }
            i++;
        }
        return false;
    }

    @Data
    private static class GobangPlayerData extends PlayerData {
        private String newPiece;
        private int winner = -1;
        // 该谁了
        private int turn;
        // 颜色
        private final int roleFlag;

        public GobangPlayerData(int roleFlag) {
            this.roleFlag = roleFlag;
        }
    }
}
