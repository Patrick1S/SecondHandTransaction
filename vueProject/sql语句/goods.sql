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

 Date: 30/06/2022 17:47:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `unit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单位',
  `store` int DEFAULT NULL COMMENT '库存',
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上架时间',
  `user_id` int DEFAULT NULL COMMENT '商品发布者',
  `transport` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易方式',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '商品状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (1, '索尼（SONY）Alpha 6400 APS-C画幅微单数码相机', 7499.00, ' 标准套装 黑色（SELP1650镜头 ILCE-6400L/A6400L/α6400）', '台', 15, 'http://localhost:9090/file/5c1b26b2dc7646a983aa64253ce705a8.jpeg', '2022-06-08 19:21:50', 1, '普通快递', '审核通过');
INSERT INTO `goods` VALUES (7, '华为* HUAWEI P50 Pro', 5558.00, '全网通手机 高通骁龙888芯片 可可茶金 8GB+256GB', '台', 79, 'http://localhost:9090/file/d29522db3e564ae580f136b84b934641.jpeg', '2022-06-08 19:21:50', 1, '普通快递', '审核通过');
INSERT INTO `goods` VALUES (40, ' 雀巢（Nestle）速溶咖啡 1+2 原味', 99.00, '微研磨 冲调饮品 90条1350g（新老包装交替发货）', '条', 99, 'http://localhost:9090/file/023454d11c1e4d9c8c508c23dde34a8c.jpeg', '2022-06-18 14:17:50', 1, '普通快递', '审核通过');
INSERT INTO `goods` VALUES (41, '联想ThinkBook 14p ', 4599.00, 'AMD锐龙标压 14英寸高性能轻薄笔记本电脑 R7-5800H 16G 512G 16:10 2.2K 高色域', '台', 299, 'http://localhost:9090/file/4d9f54d481ee45559cddbb38baf1ea5a.jpg', '2022-06-18 14:17:50', 1, NULL, '审核通过');
INSERT INTO `goods` VALUES (42, '美的（Midea）侧吸油烟机', 2169.00, '侧吸油烟机京东小家 21立方大吸力挥手抽油烟机灶具 免拆洗烟灶套装J39（天然气）', '架', 300, 'http://localhost:9090/file/ded7ec58c96d46f8ac068def302218f3.jpg', '2022-06-18 14:17:50', 1, NULL, '审核通过');
INSERT INTO `goods` VALUES (43, '太平鸟男装', 659.00, '2020年冬季新款明线毛呢夹克男B2BCA4258 黑色 XL 微阔', '件', 498, 'http://localhost:9090/file/48d38c39210a4afab08b322359c646ac.jpeg', '2022-06-18 14:17:50', 1, NULL, '审核通过');
INSERT INTO `goods` VALUES (44, 'Dickies男鞋夏季新款帆布鞋', 269.00, '高帮潮流学生百搭情侣加绒休闲鞋板鞋 黑色【男款】 39', '双', 500, 'http://localhost:9090/file/834b0b35942849b4bfcdeed2bebaf327.jpg', '2022-06-18 14:17:50', 1, NULL, '审核通过');
INSERT INTO `goods` VALUES (55, '雀巢咖啡', 99.00, '雀巢咖啡，618大促，买一赠一，快来抢购', '袋', 492, 'http://localhost:9090/file/4a2cab200cb64f9f960680765b39bf65.jpg', '2022-06-30 10:47:18', 66, NULL, '审核通过');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
