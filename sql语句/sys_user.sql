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

 Date: 30/06/2022 17:48:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '银行账户',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `account` decimal(10,2) DEFAULT '0.00' COMMENT '账户余额',
  `points` decimal(10,0) DEFAULT '0' COMMENT '用户积分',
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '用户状态',
  `grade` int DEFAULT '5' COMMENT '商家等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'zzzzzz', '管理员', '男', 'admin张吉鑫张吉鑫@gamil.co', '153657586872', '北京市海淀区', NULL, '2022-04-04 23:22:24', 'http://localhost:9090/file/48d38c39210a4afab08b322359c646ac.jpeg', 'ROLE_ADMIN', 50000.00, 0, '审核通过', 5);
INSERT INTO `sys_user` VALUES (65, '买家111', 'zzzzzz', '买家111', '男', '12345678@qq.com', '12345678', '', NULL, '2022-06-19 11:12:46', '', 'ROLE_USER', 703.00, 0, '审核通过', 5);
INSERT INTO `sys_user` VALUES (66, '卖家222', 'zzzzzz', '卖家222', '女', '87654321', '87654321', '', NULL, '2022-06-19 11:14:54', '', 'ROLE_SELLER', 198.00, 0, '审核通过', 5);
INSERT INTO `sys_user` VALUES (67, '买家1111', 'zzzzzz', '买家1111', '男', '123456@qq.com', '123456', '上海市 上海市-黄浦区-中山东二路600号', '125365768797906', '2022-06-19 16:37:35', 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'ROLE_USER', 4802.00, 99, '审核通过', 5);
INSERT INTO `sys_user` VALUES (68, '卖家2222', 'zzzzzz', '卖家2222', '女', '654321@qq.com', '654321', '', NULL, '2022-06-19 16:39:00', '', 'ROLE_SELLER', 198.00, 0, '审核通过', 5);
INSERT INTO `sys_user` VALUES (69, '买家11111', 'zzzzzz', '买家11111', '男', '12345678@qq.com', '12345678901', '北京朝阳', '1253564747585', '2022-06-30 16:33:20', 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'ROLE_USER', 499802.00, 99, '审核通过', 5);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
