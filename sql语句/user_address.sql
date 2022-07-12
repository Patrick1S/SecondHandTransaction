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

 Date: 30/06/2022 17:48:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_address
-- ----------------------------
BEGIN;
INSERT INTO `user_address` VALUES (1, 3, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓');
INSERT INTO `user_address` VALUES (2, 3, '上海 上海市 浦东新区 浦东机场');
INSERT INTO `user_address` VALUES (71, 67, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓');
INSERT INTO `user_address` VALUES (72, 66, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓');
INSERT INTO `user_address` VALUES (73, 69, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓');
INSERT INTO `user_address` VALUES (74, 69, '上海 上海市 浦东新区 浦东机场');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
