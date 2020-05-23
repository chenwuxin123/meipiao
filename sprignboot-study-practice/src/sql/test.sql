/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : ctripinter

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 23/05/2020 09:22:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '李白', '喝酒写诗', '1587975762226');
INSERT INTO `test` VALUES (2, '华罗庚', '数学家', '1587975785443');
INSERT INTO `test` VALUES (3, '乔布斯', '手机发明家', '1587975774956');
INSERT INTO `test` VALUES (4, 'success', '哈哈', '1587975780484');
INSERT INTO `test` VALUES (5, '宝马', '垃圾啊', '1587975790454');

SET FOREIGN_KEY_CHECKS = 1;
