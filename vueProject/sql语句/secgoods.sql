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

 Date: 30/06/2022 17:47:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for secgoods
-- ----------------------------
DROP TABLE IF EXISTS `secgoods`;
CREATE TABLE `secgoods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传用户',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上架时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of secgoods
-- ----------------------------
BEGIN;
INSERT INTO `secgoods` VALUES (51, '手机', 888, '骁龙888', '张三', 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '2022-06-09 19:02:16');
INSERT INTO `secgoods` VALUES (52, 'aa', NULL, NULL, NULL, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '2022-06-09 19:02:16');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
