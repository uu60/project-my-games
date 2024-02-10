/*
 Navicat Premium Data Transfer

 Source Server         : tencent mysql
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : my_game

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 07/11/2022 18:20:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_room_record
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_room_record`;
CREATE TABLE `t_chat_room_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_chat_room_record
-- ----------------------------
BEGIN;
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (1, 'qwe', 'dududu', '2022-06-12 15:29:33');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (2, 'aaa', 'dududu', '2022-06-12 15:32:01');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (3, '123', 'dududu', '2022-06-12 15:33:38');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (4, '123', 'dududu', '2022-06-12 15:34:00');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (5, '123', 'dududu', '2022-06-12 15:35:25');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (6, '123', 'dududu', '2022-06-12 15:38:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (7, '321', 'dududu', '2022-06-12 15:38:40');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (8, '321', 'dududu', '2022-06-12 15:40:18');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (9, '123', 'dududu', '2022-06-12 15:40:57');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (10, 'aaaa', 'dududu', '2022-06-12 15:42:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (11, 'a', 'dududu', '2022-06-12 15:42:15');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (12, 'a', 'dududu', '2022-06-12 15:42:16');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (13, 'a', 'dududu', '2022-06-12 15:42:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (14, 'a', 'dududu', '2022-06-12 15:42:18');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (15, 'a', 'dududu', '2022-06-12 15:42:19');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (16, 'a', 'dududu', '2022-06-12 15:42:20');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (17, 'a', 'dududu', '2022-06-12 15:43:24');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (18, '123', 'dududu', '2022-06-12 15:43:28');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (19, '123', 'dududu', '2022-06-12 15:43:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (20, '123', 'dududu', '2022-06-12 15:43:31');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (21, '123', 'dududu', '2022-06-12 15:43:32');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (22, 'sad', 'dududu', '2022-06-12 15:43:33');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (23, 'asd', 'dududu', '2022-06-12 15:43:34');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (24, 'cx', 'dududu', '2022-06-12 15:43:35');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (25, '1', 'dududu', '2022-06-12 15:43:36');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (26, '2', 'dududu', '2022-06-12 15:43:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (27, '2', 'dududu', '2022-06-12 15:43:39');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (28, 'd', 'dududu', '2022-06-12 15:43:40');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (29, '21', 'dududu', '2022-06-12 15:43:41');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (30, '1', 'dududu', '2022-06-12 15:43:42');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (31, 'da', 'dududu', '2022-06-12 15:43:43');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (32, 'c', 'dududu', '2022-06-12 15:43:44');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (33, 'q', 'dududu', '2022-06-12 15:43:46');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (34, '1', 'dududu', '2022-06-12 15:43:46');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (35, 'd', 'dududu', '2022-06-12 15:43:47');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (36, '12', 'dududu', '2022-06-12 15:43:48');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (37, 'ads', 'dududu', '2022-06-12 15:43:50');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (38, '1', 'dududu', '2022-06-12 15:44:15');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (39, '1', 'dududu', '2022-06-12 15:44:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (40, 'a', 'dududu', '2022-06-12 15:44:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (41, '你好啊', 'dududu', '2022-06-12 15:44:41');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (42, '你好', 'w', '2022-06-12 16:01:47');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (43, '你也好', 'dududu', '2022-06-12 16:02:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (44, '真的可以诶hhhh', 'w', '2022-06-12 16:02:22');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (45, '牛哇', 'w', '2022-06-12 16:02:29');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (46, '不错', 'dududu', '2022-06-12 16:02:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (47, '😄', 'dududu', '2022-06-12 16:02:34');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (48, '居然还可以emoji', 'dududu', '2022-06-12 16:02:41');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (49, '你哪能显示吗', 'dududu', '2022-06-12 16:02:46');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (50, '👋', 'w', '2022-06-12 16:02:50');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (51, '能\n', 'w', '2022-06-12 16:02:56');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (52, '太高级了', 'w', '2022-06-12 16:03:03');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (53, '但是好像发完消息他不会自己往下走', 'w', '2022-06-12 16:03:16');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (54, '左上角那个 怎么都不居中', 'dududu', '2022-06-12 16:03:21');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (55, '得自己往下巴拉', 'w', '2022-06-12 16:03:24');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (56, '是哦 我想一下', 'dududu', '2022-06-12 16:03:36');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (57, '回来就看不到了', 'w', '2022-06-12 16:04:07');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (58, '退出重进', 'w', '2022-06-12 16:04:18');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (59, 'a', 'dududu', '2022-06-12 16:06:13');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (60, 'a', 'dududu', '2022-06-12 16:06:32');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (61, 'a', 'dududu', '2022-06-12 16:06:34');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (62, 'a', 'dududu', '2022-06-12 16:06:35');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (63, '1', 'dududu', '2022-06-12 16:06:52');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (64, '123', 'dududu', '2022-06-12 16:07:36');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (65, 'asdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjsasdasdnjaksdjs', 'dududu', '2022-06-12 16:19:33');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (66, 'as', 'dududu', '2022-06-12 16:21:36');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (67, '1', 'dududu', '2022-06-12 16:22:57');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (68, '12', 'dududu', '2022-06-12 16:23:01');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (69, '1', 'dududu', '2022-06-12 16:23:03');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (70, '123', 'dududu', '2022-06-12 16:25:54');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (71, '213', 'dududu', '2022-06-12 16:33:55');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (72, '321', 'dududu', '2022-06-12 16:33:59');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (73, '123', 'dududu', '2022-06-12 16:41:27');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (74, '123', 'dududu', '2022-06-12 16:41:28');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (75, '123', 'dududu', '2022-06-12 16:41:29');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (76, '123', 'dududu', '2022-06-12 16:41:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (77, '123', 'dududu', '2022-06-12 16:41:31');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (78, '123', 'dududu', '2022-06-12 16:41:32');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (79, '123', 'dududu', '2022-06-12 18:32:28');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (80, '123', 'dududu', '2022-06-12 18:33:26');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (81, '231', 'dududu', '2022-06-12 18:33:28');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (82, '1', 'dududu', '2022-06-12 18:33:42');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (83, 'asd', 'dududu', '2022-06-12 18:52:23');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (84, '123', 'dududu', '2022-06-12 18:53:08');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (85, '诶', 'w', '2022-06-12 19:08:54');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (86, '?', 'dududu', '2022-06-12 19:09:09');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (87, 'asd', 'dududu', '2022-06-12 19:25:57');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (88, 'happy', 'dududu', '2022-06-12 19:26:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (89, 'heihei\n', 'dududu', '2022-06-12 19:33:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (90, '好了吗', 'w', '2022-06-12 19:34:59');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (91, '还没有', 'dududu', '2022-06-12 19:35:22');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (92, '？\n\n', 'dududu', '2022-06-12 19:36:41');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (93, '//', 'dududu', '2022-06-12 19:36:47');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (94, '123\n', 'dududu', '2022-06-12 19:43:14');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (95, 'asd\n', 'dududu', '2022-06-12 19:43:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (96, 'asd\n', 'dududu', '2022-06-12 19:43:19');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (97, 'asd\nsad\nasd\nasd\n', 'dududu', '2022-06-12 19:43:27');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (98, '阿斯顿', 'dududu', '2022-06-12 19:45:03');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (99, '123', 'dududu', '2022-06-12 19:52:40');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (100, 'abc\n', 'dududu', '2022-06-12 19:54:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (101, 'aaa\n', 'dududu', '2022-06-12 19:54:20');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (102, 'asc\n', 'dududu', '2022-06-12 19:54:23');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (103, '阿电视剧看不\n见\n', 'dududu', '2022-06-12 19:57:40');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (104, '阿上了吃苦耐\n\n\n\n\n\n\n劳', 'dududu', '2022-06-12 19:57:50');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (105, 'ascasckb', 'dududu', '2022-06-12 20:00:01');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (106, 'asnm\n', 'dududu', '2022-06-12 20:00:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (107, '你好呀', 'dududu', '2022-06-12 20:01:32');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (108, '终于解决了bug', 'dududu', '2022-06-12 20:04:08');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (109, 'dazi', 'dududu', '2022-06-12 20:04:12');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (110, '打字也会出问题啊', 'dududu', '2022-06-12 20:04:19');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (111, '看看有问题吗', 'dujianzhang', '2022-06-12 20:19:06');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (112, '你好', 'dujianzhang', '2022-06-12 20:19:25');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (113, '啊实打实的', 'dujianzhang', '2022-06-12 20:20:22');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (114, '在那点小事', '清歆', '2022-06-12 20:20:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (115, '阿斯顿拿到手了', 'dujianzhang', '2022-06-12 20:20:35');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (116, '看看', 'w', '2022-06-12 20:22:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (117, '可以', 'w', '2022-06-12 20:22:39');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (118, '平板也行', 'w', '2022-06-12 20:22:45');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (119, '回车可以吧', 'dujianzhang', '2022-06-12 20:23:05');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (120, '可以', 'w', '2022-06-12 20:23:24');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (121, 'l', 'dududu', '2022-06-17 15:14:12');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (122, '?', 'djz', '2022-10-28 23:50:05');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (123, '?', 'dujianzhang', '2022-10-28 23:50:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (124, '捏麻 d', 'djz', '2022-10-28 23:50:13');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (125, '你干嘛', 'dujianzhang', '2022-10-28 23:50:15');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (126, '分身是吧', 'djz', '2022-10-28 23:50:18');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (127, '玩五子棋', 'dujianzhang', '2022-10-28 23:50:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (128, '大家好', 'dujianzhang', '2022-10-29 00:02:54');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (129, '我是djz', 'dujianzhang', '2022-10-29 00:02:57');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (130, '😅', 'dujianzhang', '2022-10-29 00:03:19');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (131, '？', 'dujianzhang', '2022-10-29 00:04:41');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (132, '？', 'djz', '2022-10-29 00:04:44');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (133, '？', 'dujianzhang', '2022-10-29 00:04:46');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (134, 'djz', 'dujianzhang', '2022-10-29 00:04:47');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (135, '只因', '帅', '2022-10-29 00:04:48');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (136, '不是我', 'dujianzhang', '2022-10-29 00:04:48');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (137, '我是djz', 'djz', '2022-10-29 00:04:49');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (138, '我是', 'dujianzhang', '2022-10-29 00:04:53');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (139, '尼斯个几把', 'djz', '2022-10-29 00:04:54');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (140, '那个是假的', 'dujianzhang', '2022-10-29 00:04:55');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (141, '我是真的', 'djz', '2022-10-29 00:04:59');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (142, '。。。', 'dujianzhang', '2022-10-29 00:05:01');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (143, '那那个是谁', '帅', '2022-10-29 00:05:07');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (144, '🐴院士', 'dujianzhang', '2022-10-29 00:05:18');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (145, '👀', '帅', '2022-10-29 00:05:32');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (146, '不是', 'djz', '2022-10-29 00:05:34');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (147, '我是djz', 'djz', '2022-10-29 00:05:38');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (148, '你好', 'dujianzhang', '2022-10-29 00:05:54');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (149, '你好', '帅', '2022-10-29 00:06:00');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (150, '五子棋', 'dujianzhang', '2022-10-29 00:06:02');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (151, '你开一个', 'dujianzhang', '2022-10-29 00:06:04');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (152, '你跟🐴院士玩吧', '帅', '2022-10-29 00:06:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (153, '他要走了', 'dujianzhang', '2022-10-29 00:06:26');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (154, '你要睡了吗', 'dujianzhang', '2022-10-29 00:06:33');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (155, '不啊', '帅', '2022-10-29 00:06:37');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (156, '我撤了', 'djz', '2022-10-29 00:06:38');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (157, '你俩玩', 'djz', '2022-10-29 00:06:42');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (158, '啥时候写个观战公能', 'djz', '2022-10-29 00:06:49');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (159, '赶紧的', 'djz', '2022-10-29 00:06:52');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (160, '干啥啊', 'dujianzhang', '2022-10-29 00:06:56');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (161, '麻烦死了', 'dujianzhang', '2022-10-29 00:06:59');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (162, '锻炼一下', 'djz', '2022-10-29 00:07:12');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (163, '？', 'Tooth', '2022-10-29 13:54:49');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (164, '害永久保存聊天记录的奥', 'Tooth', '2022-10-29 13:55:02');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (165, '牛逼啊', 'Tooth', '2022-10-29 13:55:04');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (166, '有屏蔽词没', 'Tooth', '2022-10-29 13:55:11');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (167, 'djzsb', 'Tooth', '2022-10-29 13:55:15');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (168, '没有', 'Tooth', '2022-10-29 13:55:17');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (169, '挺好', 'Tooth', '2022-10-29 13:55:19');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (170, '能发表情不', 'Tooth', '2022-10-29 13:55:30');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (171, '👴', 'Tooth', '2022-10-29 13:55:36');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (172, '牛逼', 'Tooth', '2022-10-29 13:55:40');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (173, '？', 'dujianzhang', '2022-10-29 13:57:33');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (174, 'o', 'dujianzhang', '2022-10-29 13:57:57');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (175, '？', 'dujianzhang', '2022-10-29 13:58:00');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (176, '刚才变成离线状态了', 'Tooth', '2022-10-29 13:58:02');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (177, '🃏', 'dujianzhang', '2022-10-29 13:58:20');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (178, '🤡', 'dujianzhang', '2022-10-29 13:58:25');
INSERT INTO `t_chat_room_record` (`id`, `content`, `username`, `create_time`) VALUES (179, '🤡', 'Tooth', '2022-10-29 13:58:35');
COMMIT;

-- ----------------------------
-- Table structure for t_login_token
-- ----------------------------
DROP TABLE IF EXISTS `t_login_token`;
CREATE TABLE `t_login_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_login_token
-- ----------------------------
BEGIN;
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (44, '329ece16-f396-45c2-949b-2003871d2d9a', 'admin', '2022-05-08 17:04:02', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (45, '8c9f76bc-9764-473f-97dc-a0e60f98b4a5', 'admin', '2022-05-09 23:14:10', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (46, 'a85009a4-0373-47cd-8fbb-0302aac97805', 'admin', '2022-05-09 23:15:24', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (47, '10e692ee-4166-4a92-9e10-e9c0be668bc1', 'admin', '2022-05-09 23:15:38', '2032-05-06 23:09:34');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (48, '82724f0b-cc96-46d9-aec9-0143b724d60c', 'dujianzhang', '2022-05-11 13:40:26', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (49, 'd7e8f38f-344c-404a-9d4e-c08d190c13d1', 'dujianzhang', '2022-05-14 13:35:57', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (50, 'f37bd7e8-6b30-4419-a0cb-1f1256f0bd78', 'dujianzhang', '2022-05-14 13:54:00', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (51, 'b84efb14-b485-4806-ba55-f78ea98c3a14', 'dujianzhang', '2022-05-14 13:56:13', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (52, 'f5f8bd3f-7e72-41f8-aa6c-95555a3d7282', 'dujianzhang', '2022-05-14 15:57:12', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (53, '5272bef6-8a51-47a1-866b-f01a853a7972', 'dujianzhang', '2022-05-14 16:05:05', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (54, '1c1311eb-dd5a-4b2d-8a2e-e2b6421e0cf5', 'dujianzhang', '2022-05-21 18:56:14', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (55, 'f447801c-0357-4e10-b0d4-0230603f114f', 'dujianzhang', '2022-05-21 19:36:33', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (56, '9efcaf25-8352-4522-bead-7dc5e6d76752', 'dujianzhang', '2022-05-30 21:06:22', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (57, 'c3b443b6-1955-43da-83fb-44a811f59226', 'dujianzhang', '2022-05-30 21:06:39', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (58, '27a24552-5baf-49f4-8986-e343013fe99b', 'dujianzhang', '2022-05-30 21:09:31', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (59, '5409cea0-5596-4c08-af5e-fcc22a2d13d4', 'dujianzhang', '2022-05-30 21:18:27', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (60, '8571feba-9632-4495-8230-b93ef00ed060', 'dujianzhang', '2022-05-30 21:21:07', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (61, '81a8971c-84fe-4276-939f-0c93c947a8b9', 'dujianzhang', '2022-05-30 21:26:57', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (62, 'd3b2e675-989e-4681-9619-c85441e16674', 'dujianzhang', '2022-06-01 00:08:04', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (63, '67c31577-11f3-4d91-9dbc-6765578a35a0', 'dujianzhang', '2022-06-01 00:18:52', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (64, 'becee556-6c98-4d08-927f-9487a2d48c74', 'dujianzhang', '2022-06-01 00:24:51', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (65, '364088c7-e7ed-45ac-bd83-3336faf423f5', 'dududu', '2022-06-12 14:33:59', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (66, '3dd6b4ce-b0dc-4ee3-adad-67271e60ac44', 'dududu', '2022-06-12 14:38:20', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (67, 'bb017542-5811-4832-8e4c-f2fa0032dd38', 'dududu', '2022-06-12 14:41:19', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (68, '4154cf6a-9c31-46f6-913c-2e3aa9b89fb7', 'w', '2022-06-12 16:01:23', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (69, 'a8ff9a6e-7c39-419f-9f89-371ed0c63201', 'dududu', '2022-06-12 16:26:55', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (70, '0b47119f-9ecf-416c-82c7-2409b53c5c70', 'w', '2022-06-12 16:30:04', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (71, 'bf3e2ac1-3515-4094-9699-c29d2bf7d144', 'wz', '2022-06-12 16:31:13', '2032-06-09 16:17:35');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (72, '0b24c335-dc04-4ffd-955b-cbfbcbc186e2', 'dududu', '2022-06-12 18:30:59', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (73, '6706be3d-3134-4275-afa8-381487f30aaa', 'dududu', '2022-06-12 18:31:47', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (74, '8646cc4f-d47d-4a02-acd6-68547aadf26e', 'dududu', '2022-06-12 18:38:38', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (75, 'f13e1199-6689-4cf2-9152-2f645b8f12a4', 'dududu', '2022-06-12 18:39:17', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (76, 'a4e473c1-4cbe-49ac-9b40-bf884e26f10d', 'dujianzhang', '2022-06-12 18:39:51', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (77, 'd2888109-c816-4c8d-abdf-555ca9022ceb', 'dududu', '2022-06-12 18:42:01', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (78, '1b0f15fc-a454-49f2-8222-b58948ddde9f', 'dududu', '2022-06-12 18:47:42', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (79, 'f60cc78c-a71e-4737-957f-6ccb490810ec', 'dududu', '2022-06-12 18:48:20', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (80, '29b1a013-6c1a-41d5-8fa2-2e972e68cfac', 'dududu', '2022-06-12 18:50:28', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (81, 'd2060da9-a190-42e5-8843-bf1f34dc4ca8', 'dududu', '2022-06-12 18:51:00', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (82, 'd4d32b99-cfe0-4c99-9f1a-7103254ca21b', 'dududu', '2022-06-12 18:52:12', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (83, 'f7336725-0b7b-4ba8-a207-dc8366c753b4', 'w', '2022-06-12 19:08:39', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (84, '3e4447c7-3524-4796-9cc7-add7bf68669c', 'dujianzhang', '2022-06-12 20:08:32', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (85, '9cedf58c-941f-4646-993d-638e19f7ee14', '清歆', '2022-06-12 20:19:07', '2032-06-09 19:41:55');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (86, 'a6a7c2b6-abf5-4c97-932c-c4c56a14dc2d', 'w', '2022-06-12 20:22:20', '2032-06-09 19:41:55');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (87, '87bb5ece-4d3a-45da-9fd9-62300fe29626', 'dududu', '2022-06-17 15:03:40', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (88, '1ca3513c-1330-486b-bdb8-fdd5921747f1', 'dududu', '2022-06-17 15:03:46', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (89, '52d8041d-a03d-4022-b2eb-09f348bfe7ec', 'dududu', '2022-06-17 15:04:00', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (90, 'b56950d7-acd2-4ac0-8a48-4f4098722db1', 'dududu', '2022-06-17 15:05:23', '2032-06-14 14:33:05');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (91, 'ea566852-b3f0-40ce-a16c-06f3c79dcd39', 'dujianzhang', '2022-06-17 15:08:44', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (92, 'cd3a7412-374d-4f4c-878f-2ebb2878ac60', 'dujianzhang', '2022-06-17 15:17:49', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (93, 'c324da9d-8ca7-43f5-8142-668d46e4f0b9', 'dujianzhang', '2022-06-17 15:44:40', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (94, 'd05cd6f1-fdd2-4928-a797-5f4559334404', 'dujianzhang', '2022-10-28 23:47:11', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (95, '6a09753f-e0aa-4b8f-957d-fbf3b57d8d96', 'djz', '2022-10-28 23:49:19', '2032-10-25 23:46:07');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (96, 'ed2cc37b-697b-44d3-9be8-342c14381ea1', '帅', '2022-10-29 00:04:26', '2032-10-25 23:46:07');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (97, 'ea140b6f-9a84-492d-b06a-a79750919c4b', 'dujianzhang', '2022-10-29 10:47:28', NULL);
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (98, 'ddcf6c3d-d580-42c2-bf3d-b72f9ca460f2', 'Tooth', '2022-10-29 13:54:27', '2032-10-26 13:47:59');
INSERT INTO `t_login_token` (`id`, `value`, `username`, `create_time`, `expire_time`) VALUES (99, '0915a489-5858-4696-9b45-0aacd77cca2c', 'dujianzhang', '2022-10-29 13:57:07', '2032-10-26 13:47:59');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (1, 'admin', '21232F297A57A5A743894A0E4A801FC3', '2022-05-01 20:58:25');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (2, 'dujianzhang', '81273dee1356fc9a35a1b6ac463992d0', '2022-05-11 13:40:18');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (3, 'dududu', '81273dee1356fc9a35a1b6ac463992d0', '2022-06-12 14:22:56');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (4, 'w', 'e10adc3949ba59abbe56e057f20f883e', '2022-06-12 16:01:16');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (5, 'wz', 'e10adc3949ba59abbe56e057f20f883e', '2022-06-12 16:31:05');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (6, '清歆', 'd78a01c0be448b221636941eba84d9e2', '2022-06-12 20:18:31');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (7, 'djz', '417b5995c03cbcd24e822ec68c3ca287', '2022-10-28 23:49:12');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (8, '🦷帅', 'f3764d7a187540da38c9a2485e1efa27', '2022-10-29 00:03:05');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (9, '帅', 'e10adc3949ba59abbe56e057f20f883e', '2022-10-29 00:04:19');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (10, '123123', '4297f44b13955235245b2497399d7a93', '2022-10-29 13:48:11');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (11, '2213123', '2bbfa55dc58149cbdc795d293e8a5f7a', '2022-10-29 13:48:55');
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`) VALUES (12, 'Tooth', '7d2e017376f775ecaff27ebe835fed4e', '2022-10-29 13:54:25');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
