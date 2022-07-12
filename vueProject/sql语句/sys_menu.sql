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

 Date: 30/06/2022 17:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pid` int DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '主页', '/home', 'el-icon-house', '', NULL, 'Home');
INSERT INTO `sys_menu` VALUES (2, '系统管理', '', 'el-icon-s-grid', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '用户管理', '/user', 'el-icon-user', NULL, 2, 'User');
INSERT INTO `sys_menu` VALUES (4, '角色管理', '/role', 'el-icon-s-custom', NULL, 2, 'Role');
INSERT INTO `sys_menu` VALUES (5, '菜单管理', '/menu', 'el-icon-menu', NULL, 2, 'Menu');
INSERT INTO `sys_menu` VALUES (6, '文件管理', '/file', 'el-icon-document', NULL, 2, 'File');
INSERT INTO `sys_menu` VALUES (120, '商品管理', '/goods', NULL, NULL, NULL, 'Goods');
INSERT INTO `sys_menu` VALUES (121, '二手商品管理', '/secgoods', NULL, NULL, NULL, 'Secgoods');
INSERT INTO `sys_menu` VALUES (122, '购物车管理', '/cart', NULL, NULL, NULL, 'Cart');
INSERT INTO `sys_menu` VALUES (123, '订单管理', '/orders', NULL, NULL, NULL, 'Orders');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
