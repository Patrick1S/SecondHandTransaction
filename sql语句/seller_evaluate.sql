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

 Date: 30/06/2022 17:48:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for seller_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `seller_evaluate`;
CREATE TABLE `seller_evaluate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seller_id` int DEFAULT NULL COMMENT '商家id',
  `buyer_id` int DEFAULT NULL COMMENT '买家id',
  `goods_id` int DEFAULT NULL COMMENT '商品',
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '评论',
  `buyer_points` int DEFAULT NULL COMMENT '买家得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of seller_evaluate
-- ----------------------------
BEGIN;
INSERT INTO `seller_evaluate` VALUES (10, 68, 67, 54, '买家很好', 4);
INSERT INTO `seller_evaluate` VALUES (11, 66, 69, 55, '买家很好', 5);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
