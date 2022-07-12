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

 Date: 30/06/2022 17:47:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders_goods
-- ----------------------------
DROP TABLE IF EXISTS `orders_goods`;
CREATE TABLE `orders_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL COMMENT '订单id',
  `goods_id` int DEFAULT NULL COMMENT '商品uid',
  `num` int DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2109825026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of orders_goods
-- ----------------------------
BEGIN;
INSERT INTO `orders_goods` VALUES (-1690243070, 224, 55, 1);
INSERT INTO `orders_goods` VALUES (-1602162686, 226, 55, 2);
INSERT INTO `orders_goods` VALUES (-1319624702, 219, 54, 1);
INSERT INTO `orders_goods` VALUES (-1035931647, 225, 55, 1);
INSERT INTO `orders_goods` VALUES (-610787327, 220, 54, 1);
INSERT INTO `orders_goods` VALUES (675344386, 227, 55, 1);
INSERT INTO `orders_goods` VALUES (868282369, 222, 55, 1);
INSERT INTO `orders_goods` VALUES (936910849, 221, 54, 1);
INSERT INTO `orders_goods` VALUES (1560342529, 223, 55, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
