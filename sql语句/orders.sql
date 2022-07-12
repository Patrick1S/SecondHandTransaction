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

 Date: 30/06/2022 17:47:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单编号',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '订单状态',
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下单时间',
  `pay_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '付款时间',
  `delivery_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发货时间',
  `deal_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '成交时间',
  `login_id` int DEFAULT NULL COMMENT '登陆id',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发货地址',
  `cons_points` decimal(10,0) DEFAULT '0' COMMENT '消耗积分',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (219, '202206191655628036739', 99.00, '退款失败，交易完成', '2022-06-19 16:40:36', NULL, '2022-06-19 16:43:04', '2022-06-19 16:43:44', 67, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 68);
INSERT INTO `orders` VALUES (220, '202206191655628150573', 99.00, '退款成功', '2022-06-19 16:42:30', NULL, '2022-06-19 16:43:04', '2022-06-19 16:43:46', 67, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 68);
INSERT INTO `orders` VALUES (221, '202206191655628162207', 99.00, '交易完成', '2022-06-19 16:42:42', NULL, '2022-06-19 16:43:04', '2022-06-19 16:43:48', 67, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 68);
INSERT INTO `orders` VALUES (222, '202206301656579202182', 99.00, '', '2022-06-30 16:53:22', NULL, NULL, NULL, 66, NULL, 0, 66);
INSERT INTO `orders` VALUES (223, '202206301656579356978', 198.00, '', '2022-06-30 16:55:56', NULL, NULL, NULL, 66, NULL, 0, 66);
INSERT INTO `orders` VALUES (224, '202206301656579364395', 99.00, '', '2022-06-30 16:56:04', NULL, NULL, NULL, 66, NULL, 0, 66);
INSERT INTO `orders` VALUES (225, '202206301656579500744', 99.00, '交易完成', '2022-06-30 16:58:20', NULL, '2022-06-30 17:04:50', '2022-06-30 17:09:39', 69, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 66);
INSERT INTO `orders` VALUES (226, '202206301656579509824', 198.00, '退款成功', '2022-06-30 16:58:29', NULL, '2022-06-30 17:04:50', '2022-06-30 17:09:41', 69, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 66);
INSERT INTO `orders` VALUES (227, '202206301656579516511', 99.00, '退款失败，交易完成', '2022-06-30 16:58:36', NULL, '2022-06-30 17:04:50', '2022-06-30 17:09:42', 69, '北京 北京市 朝阳区 亚运村街道 北四环中路35号 北京信息科技大学健翔桥校区健翔桥学四公寓', 0, 66);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
