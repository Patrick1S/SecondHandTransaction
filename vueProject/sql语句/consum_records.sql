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

 Date: 30/06/2022 17:47:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consum_records
-- ----------------------------
DROP TABLE IF EXISTS `consum_records`;
CREATE TABLE `consum_records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_id` int DEFAULT NULL COMMENT '登陆id',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易类型',
  `seller_id` int DEFAULT NULL COMMENT '消费商家id',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '时间',
  `buyer_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消费者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of consum_records
-- ----------------------------
BEGIN;
INSERT INTO `consum_records` VALUES (282, 67, 5000.00, '充值', NULL, '2022-06-19 16:41:11', NULL);
INSERT INTO `consum_records` VALUES (283, 67, 99.00, '消费', 68, '2022-06-19 16:41:26', '67');
INSERT INTO `consum_records` VALUES (284, 67, 99.00, '消费', 68, '2022-06-19 16:42:34', '67');
INSERT INTO `consum_records` VALUES (285, 67, 99.00, '消费', 68, '2022-06-19 16:42:47', '67');
INSERT INTO `consum_records` VALUES (286, 68, 99.00, '收款', 68, '2022-06-19 16:43:44', '67');
INSERT INTO `consum_records` VALUES (287, 68, 99.00, '收款', 68, '2022-06-19 16:43:46', '67');
INSERT INTO `consum_records` VALUES (288, 68, 99.00, '收款', 68, '2022-06-19 16:43:48', '67');
INSERT INTO `consum_records` VALUES (289, 67, 99.00, '买家收到退款', 68, '2022-06-19 16:44:46', '67');
INSERT INTO `consum_records` VALUES (290, 68, 99.00, '卖家退款', 68, '2022-06-19 16:44:46', '67');
INSERT INTO `consum_records` VALUES (291, 69, 500000.00, '充值', NULL, '2022-06-30 16:59:38', NULL);
INSERT INTO `consum_records` VALUES (292, 69, 198.00, '消费', 66, '2022-06-30 17:03:21', '69');
INSERT INTO `consum_records` VALUES (293, 69, 99.00, '消费', 66, '2022-06-30 17:03:21', '69');
INSERT INTO `consum_records` VALUES (294, 69, 99.00, '消费', 66, '2022-06-30 17:03:21', '69');
INSERT INTO `consum_records` VALUES (295, 66, 99.00, '收款', 66, '2022-06-30 17:09:39', '69');
INSERT INTO `consum_records` VALUES (296, 66, 198.00, '收款', 66, '2022-06-30 17:09:41', '69');
INSERT INTO `consum_records` VALUES (297, 66, 99.00, '收款', 66, '2022-06-30 17:09:42', '69');
INSERT INTO `consum_records` VALUES (298, 69, 198.00, '买家收到退款', 66, '2022-06-30 17:19:45', '69');
INSERT INTO `consum_records` VALUES (299, 66, 198.00, '卖家退款', 66, '2022-06-30 17:19:45', '69');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
