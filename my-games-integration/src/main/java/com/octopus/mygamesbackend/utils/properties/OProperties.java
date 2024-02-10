package com.octopus.mygamesbackend.utils.properties;

public class OProperties {
    // 1 HTTP相关
    // 1.1 通用
    public final static int HTTP_SUCCESS = 200;
    public final static int HTTP_DEFAULT_ERROR = 400;
    public final static int HTTP_INTERNAL_SERVER_ERROR = 500;
    public final static int HTTP_ILLEGAL_ACCESS = 403;

    // 2 房间
    public final static int HTTP_ROOM_ERROR = 420;
    public final static int HTTP_ROOM_WAIT_TIMEOUT = 421;
    public final static int HTTP_USER_EXIT = 320;

    // 3 帐户相关
    public final static int HTTP_NO_SUCH_USER = 410;
    public final static int HTTP_WRONG_PWD = 411;
    public final static int HTTP_LOGIN_EXPIRED = 412;
    public final static int HTTP_ILLEGAL_PARAMS = 413;
    public final static int HTTP_REPEAT_USERNAME = 414;
    public final static int HTTP_WRONG_CODE = 415;

    // 4 SESSION相关
    public final static String SESSION_USERNAME_KEY = "username";
    public final static String SESSION_ROOM_KEY = "room";
    public final static String SESSION_GAME_KEY = "game";

}
