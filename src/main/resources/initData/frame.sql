/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : frame

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-01 16:57:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `hidden` bit(1) NOT NULL,
  `requestURI` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `alowMenus_id` bigint(20) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_9ogfu2m8yudrxxnkpt17icdiv` (`parent_id`),
  KEY `FK_adh21281v2hwk1bqtkn3nmsdh` (`alowMenus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '2017-01-08 20:54:24', '1', '2017-01-08 20:54:24', '0', '主页面', '\0', '/main', null, null, null);
INSERT INTO `sys_menu` VALUES ('2', '2017-01-08 20:54:24', '2', '2017-01-08 20:54:24', '1', '系统管理', '\0', '', '1', null, null);
INSERT INTO `sys_menu` VALUES ('3', '2017-01-08 20:54:24', '3', '2017-01-08 20:54:24', '0', '菜单管理', '\0', '/menu/', '2', null, null);
INSERT INTO `sys_menu` VALUES ('5', '2017-01-08 20:54:24', '5', '2017-01-08 20:54:24', '2', '菜单管理-修改', '', '/menu/edit', '3', null, null);
INSERT INTO `sys_menu` VALUES ('6', '2017-01-08 20:54:24', '6', '2017-01-08 20:54:24', '1', '菜单管理-新增', '', '/menu/add', '3', null, null);
INSERT INTO `sys_menu` VALUES ('7', '2017-02-10 11:34:41', '7', '2017-02-10 11:34:41', '2', '用户管理', '\0', '/users/', '2', null, null);
INSERT INTO `sys_menu` VALUES ('9', '2017-02-10 14:32:35', null, '2017-02-10 14:32:35', '0', '用户管理-删除', '', '/users/delete', '7', null, null);
INSERT INTO `sys_menu` VALUES ('10', '2017-02-10 16:15:46', null, '2017-02-10 16:15:46', '1', '菜单管理-删除', '', '/menu/delete', '3', null, null);
INSERT INTO `sys_menu` VALUES ('11', '2017-02-10 16:24:25', null, '2017-02-10 16:24:25', '0', '用户管理-新增', '', '/users/add', '7', null, null);
INSERT INTO `sys_menu` VALUES ('12', '2017-02-10 16:25:06', null, '2017-02-10 16:25:06', '2', '用户管理-修改', '', '/users/edit', '7', null, null);
INSERT INTO `sys_menu` VALUES ('13', '2017-02-24 17:26:02', null, '2017-02-24 17:26:02', '0', '角色管理', '\0', '/role/', '2', null, null);
INSERT INTO `sys_menu` VALUES ('14', '2017-02-24 17:48:52', null, '2017-02-24 17:48:52', '0', '角色管理-修改', '', '/role/edit', '13', null, null);
INSERT INTO `sys_menu` VALUES ('15', '2017-02-24 17:49:15', null, '2017-02-24 17:49:15', '0', '角色管理-新增', '', '/role/add', '13', null, null);
INSERT INTO `sys_menu` VALUES ('17', '2017-03-03 18:54:51', null, '2017-03-03 18:54:51', '0', '角色管理-删除', '', '/role/delete', '13', null, null);

-- ----------------------------
-- Table structure for sys_menu_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_copy`;
CREATE TABLE `sys_menu_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `hidden` bit(1) NOT NULL,
  `requestURI` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `alowMenus_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9ogfu2m8yudrxxnkpt17icdiv` (`parent_id`),
  KEY `FK_adh21281v2hwk1bqtkn3nmsdh` (`alowMenus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_copy
-- ----------------------------
INSERT INTO `sys_menu_copy` VALUES ('1', '2017-01-08 20:54:24', '1', '2017-01-08 20:54:24', '0', '主页面', '\0', '/main', null, null);
INSERT INTO `sys_menu_copy` VALUES ('2', '2017-01-08 20:54:24', '2', '2017-01-08 20:54:24', '1', '系统管理', '\0', '', '1', null);
INSERT INTO `sys_menu_copy` VALUES ('3', '2017-01-08 20:54:24', '3', '2017-01-08 20:54:24', '0', '菜单管理', '\0', '/menu/', '2', null);
INSERT INTO `sys_menu_copy` VALUES ('5', '2017-01-08 20:54:24', '5', '2017-01-08 20:54:24', '1', '菜单管理-修改', '', '/menu/edit', '3', null);
INSERT INTO `sys_menu_copy` VALUES ('6', '2017-01-08 20:54:24', '6', '2017-01-08 20:54:24', '1', '菜单管理-新增', '', '/menu/add', '3', null);
INSERT INTO `sys_menu_copy` VALUES ('7', '2017-02-10 11:34:41', '7', '2017-02-10 11:34:41', '2', '用户管理', '\0', '/users/', '2', null);
INSERT INTO `sys_menu_copy` VALUES ('9', '2017-02-10 14:32:35', null, '2017-02-10 14:32:35', '0', '用户管理-删除', '', '/users/delete', '7', null);
INSERT INTO `sys_menu_copy` VALUES ('10', '2017-02-10 16:15:46', null, '2017-02-10 16:15:46', '1', '菜单管理-删除', '', '/menu/delete', '3', null);
INSERT INTO `sys_menu_copy` VALUES ('11', '2017-02-10 16:24:25', null, '2017-02-10 16:24:25', '0', '用户管理-新增', '', '/users/add', '7', null);
INSERT INTO `sys_menu_copy` VALUES ('12', '2017-02-10 16:25:06', null, '2017-02-10 16:25:06', '1', '用户管理-修改', '', '/users/edit', '7', null);
INSERT INTO `sys_menu_copy` VALUES ('13', '2017-02-24 17:26:02', null, '2017-02-24 17:26:02', '0', '角色管理', '\0', '/role/', '2', null);
INSERT INTO `sys_menu_copy` VALUES ('14', '2017-02-24 17:48:52', null, '2017-02-24 17:48:52', '0', '角色管理-修改', '', '/role/edit', '13', null);
INSERT INTO `sys_menu_copy` VALUES ('15', '2017-02-24 17:49:15', null, '2017-02-24 17:49:15', '0', '角色管理-新增', '', '/role/add', '13', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(63) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `roles_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3fkc7lk206rg3jg92oyic6s9w` (`roles_id`),
  CONSTRAINT `FK_3fkc7lk206rg3jg92oyic6s9w` FOREIGN KEY (`roles_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2017-02-24 18:11:46', null, '2017-02-24 18:11:46', '13', 'ADMIN', '管理员', null, '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '2017-02-27 11:59:23', null, '2017-02-27 11:59:23', '9', 'USER', '用户', null, '用户');

-- ----------------------------
-- Table structure for sys_role_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_copy`;
CREATE TABLE `sys_role_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `code` varchar(63) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `roles_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3fkc7lk206rg3jg92oyic6s9w` (`roles_id`),
  CONSTRAINT `sys_role_copy_ibfk_1` FOREIGN KEY (`roles_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_copy
-- ----------------------------
INSERT INTO `sys_role_copy` VALUES ('1', '2017-02-24 18:11:46', null, '2017-02-24 18:11:46', '11', 'ADMIN', '管理员', null, '超级管理员');
INSERT INTO `sys_role_copy` VALUES ('2', '2017-02-27 11:59:23', null, '2017-02-27 11:59:23', '7', 'USER', '用户', null, '用户');

-- ----------------------------
-- Table structure for sys_role_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_sys_menu`;
CREATE TABLE `sys_role_sys_menu` (
  `sys_role_id` bigint(20) NOT NULL,
  `alowMenus_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sys_role_id`,`alowMenus_id`),
  KEY `FK_259e6yxvldnodowvg55t399fb` (`alowMenus_id`),
  CONSTRAINT `FK_4vygwc7h57d18no68d0d0m6b8` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_sys_menu
-- ----------------------------
INSERT INTO `sys_role_sys_menu` VALUES ('1', '1');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '1');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '2');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '2');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '3');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '3');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '5');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '5');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '6');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '6');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '7');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '7');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '9');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '9');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '10');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '10');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '11');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '11');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '12');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '12');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '13');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '13');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '14');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '14');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '15');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '15');
INSERT INTO `sys_role_sys_menu` VALUES ('1', '17');
INSERT INTO `sys_role_sys_menu` VALUES ('2', '17');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `nickName` varchar(63) DEFAULT NULL,
  `userLoginVerification` varchar(63) DEFAULT NULL,
  `userPassword` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7dfwp7ixqkpmykr5wl8hh93rn` (`userLoginVerification`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-01-08 20:54:24', '1', '2017-01-08 20:54:24', '7', 'DC', 'DC', 'B0996B5F7B2C2F3EA67701C590DE7A199B777EDF');
INSERT INTO `sys_user` VALUES ('6', '2017-03-07 23:08:26', null, '2017-03-07 23:08:26', '0', 'w', 'wson', '4843E6A50070E2AA90964F2EDDC3734FD5BBC2F0');

-- ----------------------------
-- Table structure for sys_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_copy`;
CREATE TABLE `sys_user_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `init_id` bigint(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `nickName` varchar(63) DEFAULT NULL,
  `userLoginVerification` varchar(63) DEFAULT NULL,
  `userPassword` varchar(63) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7dfwp7ixqkpmykr5wl8hh93rn` (`userLoginVerification`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_copy
-- ----------------------------
INSERT INTO `sys_user_copy` VALUES ('1', '2017-01-08 20:54:24', '1', '2017-01-08 20:54:24', '6', 'DC', 'DC', 'B0996B5F7B2C2F3EA67701C590DE7A199B777EDF');
INSERT INTO `sys_user_copy` VALUES ('4', '2017-02-10 17:46:28', null, '2017-02-10 17:46:28', '6', '1123', 'woson', '2C367CFB6A396D5A4AA327B18BC384EA4BC1F407');

-- ----------------------------
-- Table structure for sys_user_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_sys_role`;
CREATE TABLE `sys_user_sys_role` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sys_user_id`,`roles_id`),
  KEY `FK_lf19cb5m3ddvknunumbl69ydr` (`roles_id`),
  CONSTRAINT `FK_dyo61fvlg3kt1j3u5sj99gm5r` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_sys_role
-- ----------------------------
INSERT INTO `sys_user_sys_role` VALUES ('1', '1');
INSERT INTO `sys_user_sys_role` VALUES ('6', '2');
