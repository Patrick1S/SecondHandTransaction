/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/06/2022 17:48:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goodscomment
-- ----------------------------
DROP TABLE IF EXISTS `t_goodscomment`;
CREATE TABLE `t_goodscomment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `user_id` int DEFAULT NULL COMMENT '评论人id',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论时间',
  `pid` int DEFAULT NULL COMMENT '父id',
  `origin_id` int DEFAULT NULL COMMENT '最上级评论id',
  `goods_id` int DEFAULT NULL COMMENT '关联商品的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_goodscomment
-- ----------------------------
BEGIN;
INSERT INTO `t_goodscomment` VALUES (2, '测试2', 3, '2022-4-28', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (263, 'ddd', 41, '2022-06-10 23:58:36', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (264, 'fvff', 41, '2022-06-10 23:58:41', 263, 263, 1);
INSERT INTO `t_goodscomment` VALUES (265, 'ffd', 41, '2022-06-10 23:58:46', 264, 263, 1);
INSERT INTO `t_goodscomment` VALUES (266, 'sss', 41, '2022-06-11 00:03:23', 2, 2, 1);
INSERT INTO `t_goodscomment` VALUES (268, 'rrr', 3, '2022-06-11 00:04:07', 267, 2, 1);
INSERT INTO `t_goodscomment` VALUES (269, 'fff', 1, '2022-06-11 00:08:42', 2, 2, 1);
INSERT INTO `t_goodscomment` VALUES (274, 'DVD', 1, '2022-06-11 00:29:01', 271, 271, 1);
INSERT INTO `t_goodscomment` VALUES (276, 'ff', 3, '2022-06-13 15:43:03', 265, 263, 1);
INSERT INTO `t_goodscomment` VALUES (277, 'nbcnzvvz', 1, '2022-06-13 21:50:09', 263, 263, 1);
INSERT INTO `t_goodscomment` VALUES (279, 'bbb', 3, '2022-06-14 17:06:23', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (280, 'zbxz', 3, '2022-06-14 17:06:42', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (281, 'xxc', 3, '2022-06-14 17:06:53', 263, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (282, 'dvsv', 3, '2022-06-14 17:06:59', 280, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (283, 'sdvf', 3, '2022-06-14 17:07:04', 279, 279, 1);
INSERT INTO `t_goodscomment` VALUES (284, 'dfdf', 3, '2022-06-14 17:07:08', 278, 278, 1);
INSERT INTO `t_goodscomment` VALUES (285, 'dcsdv', 3, '2022-06-14 17:07:12', 277, 263, 1);
INSERT INTO `t_goodscomment` VALUES (286, 'zxcv', 3, '2022-06-14 17:07:16', 264, 263, 1);
INSERT INTO `t_goodscomment` VALUES (287, 'dfsf', 3, '2022-06-14 17:07:25', 268, 2, 1);
INSERT INTO `t_goodscomment` VALUES (288, 'bnjikl', 1, '2022-06-15 19:39:14', 287, 2, 1);
INSERT INTO `t_goodscomment` VALUES (289, 'jcakdvsd', 1, '2022-06-18 14:35:04', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (290, 'vsdjvs', 1, '2022-06-18 14:35:09', 283, 279, 1);
INSERT INTO `t_goodscomment` VALUES (291, ',xvns,b', 1, '2022-06-18 14:35:13', 281, 281, 1);
INSERT INTO `t_goodscomment` VALUES (292, 'ssdsv', 61, '2022-06-18 17:13:40', 291, 281, 1);
INSERT INTO `t_goodscomment` VALUES (293, 'sdsfdv', 61, '2022-06-18 17:13:44', NULL, NULL, 1);
INSERT INTO `t_goodscomment` VALUES (294, '这血海深仇', 66, '2022-06-30 17:27:33', NULL, NULL, 55);
INSERT INTO `t_goodscomment` VALUES (295, '短发比较好的反馈阶段', 66, '2022-06-30 17:27:39', 294, 294, 55);
INSERT INTO `t_goodscomment` VALUES (296, '睡不好觉啊是v是', 69, '2022-06-30 17:27:47', NULL, NULL, 55);
INSERT INTO `t_goodscomment` VALUES (297, 'v的女生可改善肌肤', 69, '2022-06-30 17:27:53', 295, 294, 55);
INSERT INTO `t_goodscomment` VALUES (298, 'vxmvns', 69, '2022-06-30 17:27:57', 294, 294, 55);
INSERT INTO `t_goodscomment` VALUES (299, ' vs宝马女短款积分，并', 69, '2022-06-30 17:28:00', 296, 296, 55);
INSERT INTO `t_goodscomment` VALUES (300, '城市的女生', 66, '2022-06-30 17:28:08', 296, 296, 55);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
