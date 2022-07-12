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

 Date: 30/06/2022 17:48:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
INSERT INTO `sys_file` VALUES (218, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (219, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (220, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (221, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (222, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (223, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (224, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (225, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (226, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (227, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (228, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (229, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (230, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (231, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (232, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (233, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (234, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (235, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (236, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (237, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (238, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (239, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (240, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (241, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (242, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (243, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (244, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (245, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (246, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (247, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (248, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (249, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (250, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (251, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (252, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (253, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (254, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (255, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (256, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (257, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (258, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (259, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (260, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (261, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (262, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (263, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (264, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (265, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (266, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (267, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 1, 1);
INSERT INTO `sys_file` VALUES (268, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 1, 1);
INSERT INTO `sys_file` VALUES (269, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (270, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (271, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (272, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (273, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (274, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (275, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (276, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (277, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (278, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (279, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (280, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (281, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (282, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (283, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (284, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (285, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (286, 'a6400.jpeg', 'jpeg', 29, 'http://localhost:9090/file/d3b42787f0c74f53a4af2fc0039104e2.jpeg', '608f0f2a1ce0d6d943c02cd239b8f5df', 0, 1);
INSERT INTO `sys_file` VALUES (287, 'HuaWeiP50.jpeg', 'jpeg', 47, 'http://localhost:9090/file/0a8d4cf5cba94c55b3a6375c46c8110e.jpeg', '9bf42121a902de98942c4cce77f52af8', 0, 1);
INSERT INTO `sys_file` VALUES (288, 'C43BB34D-40FD-4FC6-A900-7DA9905B5F32_1_102_o.jpeg', 'jpeg', 315, 'http://localhost:9090/file/2265edba883e4654a1596a6c2e4cb776.jpeg', '8cb4dc4c662848f13047bfe89eb7b6d3', 0, 1);
INSERT INTO `sys_file` VALUES (289, 'C036BBEF-A396-4A56-BF9B-6C61807CEC3D_1_102_o.jpeg', 'jpeg', 561, 'http://localhost:9090/file/562bb3a267a14b63ae6cd290e3e4de6d.jpeg', '8407770072d1082a3aa6fadb3d421721', 0, 1);
INSERT INTO `sys_file` VALUES (290, 'C15EA50E-70C5-4CCD-8CB0-FA855BDF5098_1_105_c.jpeg', 'jpeg', 124, 'http://localhost:9090/file/88e1f507286e423f848a8aefd0b444f7.jpeg', 'e8e811dbbaf7fd92813ad5c55d55dddd', 0, 1);
INSERT INTO `sys_file` VALUES (291, 'C15EA50E-70C5-4CCD-8CB0-FA855BDF5098_1_102_o.jpeg', 'jpeg', 417, 'http://localhost:9090/file/aaf82de1fc484023a7849f2f404e26b2.jpeg', '978c7ebfaf73abb1dc71faa855badb31', 0, 1);
INSERT INTO `sys_file` VALUES (292, 'C2EC1876-89B1-4C70-BE74-2DFE16A9FB7E_1_105_c.jpeg', 'jpeg', 230, 'http://localhost:9090/file/92fb6b829eb5428f9c7bf68717d3f209.jpeg', 'b71e856e8bec5cd994fe38a60fcba2ee', 0, 1);
INSERT INTO `sys_file` VALUES (293, 'C2EC1876-89B1-4C70-BE74-2DFE16A9FB7E_1_105_c.jpeg', 'jpeg', 230, 'http://localhost:9090/file/92fb6b829eb5428f9c7bf68717d3f209.jpeg', 'b71e856e8bec5cd994fe38a60fcba2ee', 0, 1);
INSERT INTO `sys_file` VALUES (294, 'C2C0161C-5D17-4255-AA54-45E36937DD01_1_105_c.jpeg', 'jpeg', 126, 'http://localhost:9090/file/86072dba334149feac64ff958f9920a9.jpeg', '02b0e9055773de8d58966972409e8668', 0, 1);
INSERT INTO `sys_file` VALUES (295, 'C5DBCB3C-5D49-4B59-88F3-E4ECBAB85670_1_105_c.jpeg', 'jpeg', 122, 'http://localhost:9090/file/7ba33947dca0492bb29c00b543e5d959.jpeg', '8bb9297f9d13b869679aee5911227a46', 0, 1);
INSERT INTO `sys_file` VALUES (296, 'C5DBCB3C-5D49-4B59-88F3-E4ECBAB85670_1_105_c.jpeg', 'jpeg', 122, 'http://localhost:9090/file/7ba33947dca0492bb29c00b543e5d959.jpeg', '8bb9297f9d13b869679aee5911227a46', 0, 1);
INSERT INTO `sys_file` VALUES (297, 'C2EC1876-89B1-4C70-BE74-2DFE16A9FB7E_1_105_c.jpeg', 'jpeg', 230, 'http://localhost:9090/file/92fb6b829eb5428f9c7bf68717d3f209.jpeg', 'b71e856e8bec5cd994fe38a60fcba2ee', 0, 1);
INSERT INTO `sys_file` VALUES (298, 'C2C0161C-5D17-4255-AA54-45E36937DD01_1_105_c.jpeg', 'jpeg', 126, 'http://localhost:9090/file/86072dba334149feac64ff958f9920a9.jpeg', '02b0e9055773de8d58966972409e8668', 0, 1);
INSERT INTO `sys_file` VALUES (299, 'C37B199A-8F4E-40C4-BB22-0C3CD36925CB_1_105_c.jpeg', 'jpeg', 138, 'http://localhost:9090/file/3e10da3c9b9743ac9fc20d1fadda1ac9.jpeg', '1538aa91becda85ed9411632b8930fec', 0, 1);
INSERT INTO `sys_file` VALUES (300, 'C51EA0F3-A011-4BA9-965B-3E9857E053CA_1_105_c.jpeg', 'jpeg', 410, 'http://localhost:9090/file/177d08921b6f4b02acb38c9f82030171.jpeg', 'e7bd454dc5b0753e81d12748a2991891', 0, 1);
INSERT INTO `sys_file` VALUES (301, 'C2EC1876-89B1-4C70-BE74-2DFE16A9FB7E_1_105_c.jpeg', 'jpeg', 230, 'http://localhost:9090/file/92fb6b829eb5428f9c7bf68717d3f209.jpeg', 'b71e856e8bec5cd994fe38a60fcba2ee', 0, 1);
INSERT INTO `sys_file` VALUES (302, 'C2EC1876-89B1-4C70-BE74-2DFE16A9FB7E_1_105_c.jpeg', 'jpeg', 230, 'http://localhost:9090/file/92fb6b829eb5428f9c7bf68717d3f209.jpeg', 'b71e856e8bec5cd994fe38a60fcba2ee', 0, 1);
INSERT INTO `sys_file` VALUES (303, 'C5DBCB3C-5D49-4B59-88F3-E4ECBAB85670_1_105_c.jpeg', 'jpeg', 122, 'http://localhost:9090/file/7ba33947dca0492bb29c00b543e5d959.jpeg', '8bb9297f9d13b869679aee5911227a46', 0, 1);
INSERT INTO `sys_file` VALUES (304, 'C5002403-5FEC-4FBF-84D5-060BC000C1C0_1_102_o.jpeg', 'jpeg', 765, 'http://localhost:9090/file/4d06dd64643f45fa91416810f8b4be79.jpeg', 'ebd0f3075147221574d65fa728d7a291', 0, 1);
INSERT INTO `sys_file` VALUES (305, 'f4aef2634cfab990.jpeg', 'jpeg', 46, 'http://localhost:9090/file/023454d11c1e4d9c8c508c23dde34a8c.jpeg', '6f044462c916e3b0c210848088c79491', 0, 1);
INSERT INTO `sys_file` VALUES (306, 'b26ba629ff0e6fea.jpg', 'jpg', 24, 'http://localhost:9090/file/4d9f54d481ee45559cddbb38baf1ea5a.jpg', 'dae74ab41dd2c2ea5be3cd1f801ce794', 0, 1);
INSERT INTO `sys_file` VALUES (307, '631960b39fac2e47.jpg', 'jpg', 13, 'http://localhost:9090/file/ded7ec58c96d46f8ac068def302218f3.jpg', 'ec2834ccb48d6819760d201f11c4396d', 0, 1);
INSERT INTO `sys_file` VALUES (308, '678f9e38cee21573.jpg', 'jpg', 0, 'http://localhost:9090/file/bd44961de9a44e96a0461a14696bb53a.jpg', 'fc22fee2c56228adc261546ae20097f6', 0, 1);
INSERT INTO `sys_file` VALUES (309, 'f19221676a065df2.jpeg', 'jpeg', 126, 'http://localhost:9090/file/48d38c39210a4afab08b322359c646ac.jpeg', '78c73de04cf9cea9fe35a7e8c06e1c65', 0, 1);
INSERT INTO `sys_file` VALUES (310, '06bcc8c58d82f60a.jpg', 'jpg', 18, 'http://localhost:9090/file/834b0b35942849b4bfcdeed2bebaf327.jpg', '1d48cd49667b6c6abd00a0e1693d4d15', 0, 1);
INSERT INTO `sys_file` VALUES (311, '06bcc8c58d82f60a.jpg', 'jpg', 18, 'http://localhost:9090/file/834b0b35942849b4bfcdeed2bebaf327.jpg', '1d48cd49667b6c6abd00a0e1693d4d15', 0, 1);
INSERT INTO `sys_file` VALUES (312, '406dd9a7d2d14d6b.jpg', 'jpg', 29, 'http://localhost:9090/file/1af6530f74094e34804b4b73858492d8.jpg', 'd2cc7112efcdbd052450f47e5991a4d8', 0, 1);
INSERT INTO `sys_file` VALUES (313, 'f19221676a065df2.jpeg', 'jpeg', 126, 'http://localhost:9090/file/48d38c39210a4afab08b322359c646ac.jpeg', '78c73de04cf9cea9fe35a7e8c06e1c65', 0, 1);
INSERT INTO `sys_file` VALUES (314, '724ed9fd3afaf62b.jpg', 'jpg', 31, 'http://localhost:9090/file/57d124d4f4604d6a9daa214570e74387.jpg', '82067c9b74d28e881cd56e76d8f0d4f8', 0, 1);
INSERT INTO `sys_file` VALUES (315, '77b3727d3b52490d988d64758236beef.JPG', 'JPG', 116, 'http://localhost:9090/file/607aaba5a0ee44d492c07ce187376674.JPG', '65f5137b086d9d337d32ee796a5aa730', 0, 1);
INSERT INTO `sys_file` VALUES (316, '00677ddd79f24717974ae5671898f7e6.JPG', 'JPG', 22, 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'c05362157e3f4ae5153ba1cb42779265', 0, 1);
INSERT INTO `sys_file` VALUES (317, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (318, '00677ddd79f24717974ae5671898f7e6.JPG', 'JPG', 22, 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'c05362157e3f4ae5153ba1cb42779265', 0, 1);
INSERT INTO `sys_file` VALUES (319, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (320, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (321, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (322, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (323, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (324, '00677ddd79f24717974ae5671898f7e6.JPG', 'JPG', 22, 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'c05362157e3f4ae5153ba1cb42779265', 0, 1);
INSERT INTO `sys_file` VALUES (325, '84bab965ce8e5a84.jpg!q70.jpg', 'jpg', 49, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '9cf5f0bf2266dfdcd350373732d7e786', 0, 1);
INSERT INTO `sys_file` VALUES (326, '00677ddd79f24717974ae5671898f7e6.JPG', 'JPG', 22, 'http://localhost:9090/file/760b68b7e8f141b9bb4b410e53655dbd.JPG', 'c05362157e3f4ae5153ba1cb42779265', 0, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
