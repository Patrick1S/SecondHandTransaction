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

 Date: 30/06/2022 17:47:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_id` int DEFAULT NULL COMMENT '登陆id',
  `user_id` int DEFAULT NULL COMMENT '商家id',
  `goods_id` int DEFAULT NULL COMMENT '商品',
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论',
  `goods_points` int DEFAULT NULL COMMENT '商品得分',
  `seller_points` int DEFAULT NULL COMMENT '商家得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
BEGIN;
INSERT INTO `evaluate` VALUES (14, 67, 68, 54, '卖家很好', 5, 5);
INSERT INTO `evaluate` VALUES (15, 69, 66, 55, '商品很好', 5, 5);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
