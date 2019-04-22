/*
Navicat MySQL Data Transfer

Source Server         : aidr.vip
Source Server Version : 50719
Source Host           : 116.62.148.24:3306
Source Database       : vote

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-04-11 10:40:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for _empty_copy
-- ----------------------------
DROP TABLE IF EXISTS `_empty_copy`;
CREATE TABLE `_empty_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统id，对外不可见，int索引',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of _empty_copy
-- ----------------------------

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `id` int(11) NOT NULL COMMENT '候选人id',
  `name` varchar(32) DEFAULT NULL COMMENT '候选人姓名',
  `des` varchar(255) DEFAULT NULL COMMENT '候选人标语',
  `createBy` varchar(24) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(24) DEFAULT NULL COMMENT '修改人',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(3) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candidate
-- ----------------------------
INSERT INTO `candidate` VALUES ('0', 'string', 'string', '小熊', '2019-04-10 20:09:15', '小熊', '2019-04-10 19:51:14', '0');
INSERT INTO `candidate` VALUES ('1', 'string', 'string', '小熊', '2019-04-10 20:09:17', '小熊', '2019-04-10 20:07:35', '0');

-- ----------------------------
-- Table structure for candidate_voter
-- ----------------------------
DROP TABLE IF EXISTS `candidate_voter`;
CREATE TABLE `candidate_voter` (
  `candidateId` int(11) NOT NULL COMMENT '候选人id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`candidateId`,`createBy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candidate_voter
-- ----------------------------
INSERT INTO `candidate_voter` VALUES ('0', '2019-04-11 10:34:32', '测试', '2019-04-11 10:34:32', '测试', '0');
INSERT INTO `candidate_voter` VALUES ('1', '2019-04-10 20:07:35', '小熊', '2019-04-10 20:07:35', '小熊', '0');
INSERT INTO `candidate_voter` VALUES ('1', '2019-04-11 10:34:32', '测试', '2019-04-11 10:34:32', '测试', '0');
INSERT INTO `candidate_voter` VALUES ('2', '2019-04-11 10:34:32', '测试', '2019-04-11 10:34:32', '测试', '0');
INSERT INTO `candidate_voter` VALUES ('3', '2019-04-11 10:34:32', '测试', '2019-04-11 10:34:32', '测试', '0');
INSERT INTO `candidate_voter` VALUES ('4', '2019-04-11 10:34:32', '测试', '2019-04-11 10:34:32', '测试', '0');

-- ----------------------------
-- Table structure for sys_permit
-- ----------------------------
DROP TABLE IF EXISTS `sys_permit`;
CREATE TABLE `sys_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sysId` varchar(64) NOT NULL COMMENT '系统识别键',
  `name` varchar(256) NOT NULL COMMENT '权限名称',
  `group` varchar(256) NOT NULL COMMENT '权限群组',
  `note` varchar(256) NOT NULL COMMENT '权限备注',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permit
-- ----------------------------
INSERT INTO `sys_permit` VALUES ('1', 'student_info', '学生信息管理', '学生管理', '学生信息管理', '2018-12-09 15:42:51', '', '2018-12-09 16:36:17', '', '0');
INSERT INTO `sys_permit` VALUES ('2', 'student_admission', '学生入校管理', '学生管理', '学生入校管理', '2018-12-09 15:43:05', '', '2018-12-09 16:36:20', '', '0');
INSERT INTO `sys_permit` VALUES ('3', 'student_sch_status', '在校状态管理', '学生管理', '在校状态管理', '2018-12-09 15:43:24', '', '2018-12-09 16:36:22', '', '0');
INSERT INTO `sys_permit` VALUES ('4', 'theory_english', '英语水平信息', '理论培训', '英语水平信息', '2018-12-09 15:43:36', '', '2018-12-09 16:34:12', '', '0');
INSERT INTO `sys_permit` VALUES ('5', 'theory_trader', '私商仪理论信息', '理论培训', '私商仪理论信息', '2018-12-09 15:44:19', '', '2018-12-09 16:37:39', '', '0');
INSERT INTO `sys_permit` VALUES ('6', 'theory_navigation', '航线理论信息', '理论培训', '航线理论信息', '2018-12-09 15:44:35', '', '2018-12-09 16:36:56', '', '0');
INSERT INTO `sys_permit` VALUES ('7', 'theory_straining', '学生送培管理', '理论培训', '学生送培管理', '2018-12-09 15:44:54', '', '2018-12-09 16:38:00', '', '0');
INSERT INTO `sys_permit` VALUES ('8', 'theory_view', '委培公司面试管理', '理论培训', '委培公司面试管理', '2018-12-09 15:45:08', '', '2018-12-09 16:38:08', '', '0');
INSERT INTO `sys_permit` VALUES ('9', 'finance_contract', '合同管理', '财务管理', '合同管理', '2018-12-09 15:45:20', '', '2018-12-09 16:39:04', '', '0');
INSERT INTO `sys_permit` VALUES ('10', 'finance_notification', '付费通知管理', '财务管理', '付费通知管理', '2018-12-09 15:45:38', '', '2018-12-09 16:39:30', '', '0');
INSERT INTO `sys_permit` VALUES ('11', 'finance_todo', '待办事项', '财务管理', '待办事项', '2018-12-09 15:45:48', '', '2018-12-09 16:39:19', '', '0');
INSERT INTO `sys_permit` VALUES ('12', 'finance_charge', '收费统计', '财务管理', '收费统计', '2018-12-09 15:45:59', '', '2018-12-09 16:39:13', '', '0');
INSERT INTO `sys_permit` VALUES ('13', 'sys_account', '账号管理', '系统管理', '账号管理', '2018-12-09 15:46:12', '', '2018-12-09 16:36:01', '', '0');
INSERT INTO `sys_permit` VALUES ('14', 'sys_role', '角色管理', '系统管理', '角色管理', '2018-12-09 15:46:21', '', '2018-12-09 16:35:52', '', '0');
INSERT INTO `sys_permit` VALUES ('15', 'sys_dict', '数据字典', '系统管理', '数据字典', '2018-12-09 15:46:29', '', '2018-12-09 16:35:40', '', '0');
INSERT INTO `sys_permit` VALUES ('16', 'sys_admin', '管理员管理', '系统管理', '超级管理', '2018-12-09 22:24:02', '', '2018-12-09 22:24:35', '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '角色名',
  `note` varchar(256) NOT NULL COMMENT '备注',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nameIndex` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '最高权限', '2018-12-09 22:18:47', '', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role` VALUES ('2', '管理员', '管理员', '2018-12-27 00:33:32', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role` VALUES ('11', '开发', '开发人员', '2018-12-26 18:08:41', '', '2018-12-27 01:18:11', '', '0');
INSERT INTO `sys_role` VALUES ('22', '测试', '测试人员', '2018-12-27 21:02:13', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role` VALUES ('23', '【影', '设哦中u', '2018-12-28 12:02:30', '', '2018-12-28 12:20:07', '', '1');
INSERT INTO `sys_role` VALUES ('24', 'test', 'test', '2018-12-28 14:58:31', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role` VALUES ('25', '基地航医', '医疗人员', '2018-12-28 15:16:33', '', '2018-12-28 15:52:26', '', '0');
INSERT INTO `sys_role` VALUES ('26', '总部航医', '', '2018-12-28 15:52:38', '', '2018-12-28 15:52:38', '', '0');
INSERT INTO `sys_role` VALUES ('27', '测试创建角色', '123123', '2019-01-02 20:05:07', '', '2019-01-02 20:23:16', '', '1');
INSERT INTO `sys_role` VALUES ('28', '戳朱', '132', '2019-01-02 20:23:21', '', '2019-01-02 20:23:30', '', '1');
INSERT INTO `sys_role` VALUES ('29', '只能查看系统管理', '', '2019-01-16 18:28:37', '', '2019-01-16 18:28:37', '', '0');
INSERT INTO `sys_role` VALUES ('30', '只能查看学生信息', '只能查看学生信息', '2019-01-18 10:21:24', '测试', '2019-01-18 13:21:04', '小纯2', '0');
INSERT INTO `sys_role` VALUES ('33', 'string', 'string', '2019-01-21 01:38:04', 'eamon0', '2019-01-21 01:38:04', 'eamon0', '0');
INSERT INTO `sys_role` VALUES ('35', 'string1', 'string1', '2019-01-21 01:42:05', 'eamon0', '2019-01-21 14:35:55', '测试', '0');
INSERT INTO `sys_role` VALUES ('37', 'stringw', 'stringw', '2019-01-21 02:38:52', 'eamon0', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role` VALUES ('41', '招生组', '招生组', '2019-01-21 14:35:38', '测试', '2019-01-21 20:10:56', '小纯2', '0');
INSERT INTO `sys_role` VALUES ('42', '123', '123', '2019-02-01 14:49:50', 'eamon0', '2019-02-01 14:49:50', 'eamon0', '0');
INSERT INTO `sys_role` VALUES ('43', 'guest', '', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role` VALUES ('44', '学生（豆）', '学生录入学生自己本人的信息', '2019-03-14 16:01:21', '豆', '2019-03-14 16:01:21', '豆', '0');

-- ----------------------------
-- Table structure for sys_role_permit
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permit`;
CREATE TABLE `sys_role_permit` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `permitId` int(11) NOT NULL COMMENT '权限id',
  `operation` varchar(256) NOT NULL DEFAULT 'create,retrieve,update,delete' COMMENT '操作字符',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`roleId`,`permitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permit
-- ----------------------------
INSERT INTO `sys_role_permit` VALUES ('1', '1', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '2', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '3', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '4', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '5', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '6', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '7', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '8', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '9', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '10', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '11', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '12', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '13', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '14', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '15', 'create,delete,update,retrieve', '2019-03-14 19:27:36', '小纯2', '2019-03-14 19:27:36', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('1', '16', 'create,retrieve,update,delete', '2018-12-09 22:27:18', '', '2019-01-02 20:28:18', '', '1');
INSERT INTO `sys_role_permit` VALUES ('2', '1', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '2', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '3', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '4', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '5', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '6', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '7', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '8', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '9', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '10', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '11', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '12', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '13', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '14', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('2', '15', 'create,retrieve,update,delete', '2018-12-27 00:48:22', '', '2018-12-27 00:48:22', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '1', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '1');
INSERT INTO `sys_role_permit` VALUES ('11', '2', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '3', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '4', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '5', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '6', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '7', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('11', '8', 'create,retrieve,update,delete', '2018-12-27 19:53:26', '', '2018-12-27 19:53:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('22', '1', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '2', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '3', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '4', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '5', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '6', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '7', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '8', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '9', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '10', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '11', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '12', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '13', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '14', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('22', '15', 'create,retrieve,update,delete', '2018-12-27 21:52:46', '', '2018-12-27 21:52:46', '', '1');
INSERT INTO `sys_role_permit` VALUES ('23', '1', 'create,retrieve,update,delete', '2018-12-28 12:02:30', '', '2018-12-28 12:20:07', '', '1');
INSERT INTO `sys_role_permit` VALUES ('23', '5', 'create,retrieve,update,delete', '2018-12-28 12:02:30', '', '2018-12-28 12:20:07', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '1', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '2', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '3', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '5', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '6', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '7', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '8', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '10', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '11', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('24', '14', 'create,retrieve,update,delete', '2018-12-28 14:58:40', '', '2018-12-28 14:58:45', '', '1');
INSERT INTO `sys_role_permit` VALUES ('25', '1', 'create,retrieve,update,delete', '2018-12-28 15:52:26', '', '2018-12-28 15:52:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('25', '5', 'create,retrieve,update,delete', '2018-12-28 15:52:26', '', '2018-12-28 15:52:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('25', '6', 'create,retrieve,update,delete', '2018-12-28 15:52:26', '', '2018-12-28 15:52:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('25', '9', 'create,retrieve,update,delete', '2018-12-28 15:52:26', '', '2018-12-28 15:52:26', '', '0');
INSERT INTO `sys_role_permit` VALUES ('27', '1', 'create,retrieve,update,delete', '2019-01-02 20:22:57', '', '2019-01-02 20:23:16', '', '1');
INSERT INTO `sys_role_permit` VALUES ('27', '5', 'create,retrieve,update,delete', '2019-01-02 20:22:57', '', '2019-01-02 20:23:16', '', '1');
INSERT INTO `sys_role_permit` VALUES ('27', '11', 'create,retrieve,update,delete', '2019-01-02 20:22:57', '', '2019-01-02 20:23:16', '', '1');
INSERT INTO `sys_role_permit` VALUES ('27', '15', 'create,retrieve,update,delete', '2019-01-02 20:22:57', '', '2019-01-02 20:23:16', '', '1');
INSERT INTO `sys_role_permit` VALUES ('28', '1', 'create,retrieve,update,delete', '2019-01-02 20:23:21', '', '2019-01-02 20:23:30', '', '1');
INSERT INTO `sys_role_permit` VALUES ('28', '2', 'create,retrieve,update,delete', '2019-01-02 20:23:21', '', '2019-01-02 20:23:30', '', '1');
INSERT INTO `sys_role_permit` VALUES ('28', '3', 'create,retrieve,update,delete', '2019-01-02 20:23:21', '', '2019-01-02 20:23:30', '', '1');
INSERT INTO `sys_role_permit` VALUES ('29', '13', 'create,retrieve,update,delete', '2019-01-16 18:28:37', '', '2019-01-16 18:28:37', '', '0');
INSERT INTO `sys_role_permit` VALUES ('29', '14', 'create,retrieve,update,delete', '2019-01-16 18:28:37', '', '2019-01-16 18:28:37', '', '0');
INSERT INTO `sys_role_permit` VALUES ('29', '15', 'create,retrieve,update,delete', '2019-01-16 18:28:37', '', '2019-01-16 18:28:37', '', '0');
INSERT INTO `sys_role_permit` VALUES ('30', '1', 'create,delete,update,retrieve', '2019-01-21 20:15:28', '小纯2', '2019-01-21 20:15:28', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('30', '2', 'create,delete,update,retrieve', '2019-01-21 20:15:28', '小纯2', '2019-01-21 20:15:28', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('30', '3', 'create,delete,update,retrieve', '2019-01-21 20:15:28', '小纯2', '2019-01-21 20:15:28', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '1', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '2', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '3', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '4', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '5', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '6', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('33', '7', 'create,retrieve,update,delete', '2019-01-21 01:38:09', 'eamon0', '2019-01-21 01:38:09', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('35', '1', 'create,delete,update,retrieve', '2019-01-21 14:35:55', '测试', '2019-01-21 14:35:55', '测试', '0');
INSERT INTO `sys_role_permit` VALUES ('35', '2', 'create,delete,update,retrieve', '2019-01-21 14:35:55', '测试', '2019-01-21 14:35:55', '测试', '0');
INSERT INTO `sys_role_permit` VALUES ('35', '3', 'create,delete,update,retrieve', '2019-01-21 14:35:55', '测试', '2019-01-21 14:35:55', '测试', '0');
INSERT INTO `sys_role_permit` VALUES ('35', '4', 'create,retrieve,update,delete', '2019-01-21 01:42:14', 'eamon0', '2019-01-21 14:35:55', '测试', '1');
INSERT INTO `sys_role_permit` VALUES ('35', '5', 'create,retrieve,update,delete', '2019-01-21 01:42:14', 'eamon0', '2019-01-21 14:35:55', '测试', '1');
INSERT INTO `sys_role_permit` VALUES ('35', '6', 'create,retrieve,update,delete', '2019-01-21 01:42:15', 'eamon0', '2019-01-21 14:35:55', '测试', '1');
INSERT INTO `sys_role_permit` VALUES ('35', '7', 'create,retrieve,update,delete', '2019-01-21 01:42:15', 'eamon0', '2019-01-21 14:35:55', '测试', '1');
INSERT INTO `sys_role_permit` VALUES ('37', '1', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '2', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '3', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '4', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '5', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '6', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '7', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '8', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '9', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '10', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '11', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '12', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '13', 'create,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '14', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('37', '15', 'create,delete,update,retrieve', '2019-01-21 20:16:05', '小纯2', '2019-01-21 20:16:05', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('41', '1', 'create,delete,update,retrieve', '2019-01-21 20:15:17', '小纯2', '2019-01-21 20:15:17', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('41', '2', 'create,delete,update,retrieve', '2019-01-21 20:15:17', '小纯2', '2019-01-21 20:15:17', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('41', '3', 'create,delete,update,retrieve', '2019-01-21 20:15:17', '小纯2', '2019-01-21 20:15:17', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('41', '4', 'create', '2019-01-21 20:10:56', '小纯2', '2019-01-21 20:15:17', '小纯2', '1');
INSERT INTO `sys_role_permit` VALUES ('42', '1', 'create,delete,update,retrieve', '2019-02-01 14:49:50', 'eamon0', '2019-02-01 14:49:50', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('42', '2', 'create,delete,update,retrieve', '2019-02-01 14:49:50', 'eamon0', '2019-02-01 14:49:50', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('42', '3', 'create,delete,update,retrieve', '2019-02-01 14:49:50', 'eamon0', '2019-02-01 14:49:50', 'eamon0', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '1', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '2', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '3', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '4', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '5', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '6', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '7', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '8', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '9', 'create,delete,update,retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '10', 'create,delete,update,retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '11', 'create,delete,update,retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '12', 'create,delete,update,retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '13', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('43', '14', 'retrieve', '2019-02-25 17:41:53', '小纯2', '2019-02-25 17:41:53', '小纯2', '0');
INSERT INTO `sys_role_permit` VALUES ('44', '1', 'create,update', '2019-03-14 16:01:21', '豆', '2019-03-14 16:01:21', '豆', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `account` varchar(64) NOT NULL COMMENT '账号名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(16) NOT NULL COMMENT '盐',
  `token` varchar(32) DEFAULT NULL COMMENT '令牌',
  `name` varchar(64) NOT NULL COMMENT '用户姓名',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `position` varchar(64) NOT NULL COMMENT '职位',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountIndex` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10038 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10000', 'eamon', 'string', 'f81855a', '0181cf3f75ed4582a921c1814762084d', '小熊', '17771842186', '开发', '2018-12-09 19:45:24', '', '2019-04-11 10:33:06', '', '0');
INSERT INTO `sys_user` VALUES ('10001', 'eamon0', 'string', '566cda3', '91d2baf68bd74a9ba7231fa944d95c7f', 'eamon0', '17771842186', '开发', '2018-12-09 21:47:39', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10005', 'IwYvI1', 'string', '9ceac81', null, '小纯', '15972102336', 'string', '2018-12-10 03:09:00', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10006', 'IwYvI', 'string', 'f81855a', '24fe355160ba4966aa166f014fef2256', '小纯2', '15972102336', 'string', '2018-12-10 03:54:29', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10008', 'suewang', 'string', '2db5d0b', '9388ba9754a4440b8b474024cb3c97c3', '小朔', '15972102336', 'string', '2018-12-12 15:09:12', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10013', 'testadmin', 'string', '6c80e45', 'bf4f73a72f2244d78f324b43adaa3623', '测试', '15972102336', '测试人员', '2018-12-27 12:33:17', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10015', 'test2', 'string', '5d2c68f', '18acfdf3bad24a51830cebe30d38ba7f', '测试2', '13277987795', '测试岗位', '2018-12-27 14:23:36', '', '2019-04-11 10:33:14', '测试', '1');
INSERT INTO `sys_user` VALUES ('10016', 'oeuaeou', 'string', '977358e', null, 'py', '13277987795', '123', '2018-12-27 18:06:55', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10017', '测试创建账号', 'string', '17b0d35', null, '你好', '13000002321', 'ceoi ', '2018-12-27 20:54:36', '', '2019-04-11 10:33:14', '', '1');
INSERT INTO `sys_user` VALUES ('10018', 'test3', 'string', 'a6f87a8', '', '高级权限测试3', '15972102336', '最高权限测试3', '2018-12-28 10:27:24', '', '2019-04-11 10:33:14', '', '0');
INSERT INTO `sys_user` VALUES ('10019', '123', 'string', 'd910992', null, '123', '123', '123', '2018-12-28 12:20:19', '', '2019-04-11 10:33:14', '', '1');
INSERT INTO `sys_user` VALUES ('10020', '2222222', 'string', '96ad120', null, '111111', '1111111111111', '1111', '2018-12-28 14:57:26', '', '2019-04-11 10:33:15', '', '1');
INSERT INTO `sys_user` VALUES ('10022', 'testtest', 'string', 'd3dd281', 'd97c7dccac124d4e98fedc6678bf8bfd', '测试账号123', '123', '123', '2019-01-16 11:54:06', '', '2019-04-11 10:33:15', '', '0');
INSERT INTO `sys_user` VALUES ('10023', 'testtest2', 'string', 'e78d7cb', '', '123123', '123123', '123123', '2019-01-16 11:54:22', '', '2019-04-11 10:33:15', '小纯2', '0');
INSERT INTO `sys_user` VALUES ('10024', 'testtest0', 'string', '8ab1026', 'e9529740e81347c7b306ccfc42e8ba84', '基地航医', '123', '123', '2019-01-16 20:18:54', '', '2019-04-11 10:33:15', '', '0');
INSERT INTO `sys_user` VALUES ('10028', 'eamon2', 'string', '3c6a133', 'ff3f591916f94e0dba06ec75e286c16b', 'eamon2', 'eamon2', 'eamon2', '2019-01-18 03:45:48', 'eamon0', '2019-04-11 10:33:15', 'eamon2', '1');
INSERT INTO `sys_user` VALUES ('10029', 'stu', 'string', '578e63b', '', '只能查看学生信息', '15972102336', '只能查看学生信息', '2019-01-18 10:22:26', '测试', '2019-04-11 10:33:15', '测试', '0');
INSERT INTO `sys_user` VALUES ('10030', 'xuhao', 'string', '4f1d587', 'd89b62a79f8a4823b91446b6c85d240f', '徐浩', '12222111110', '项目经理', '2019-01-18 17:51:14', '高级权限测试3', '2019-04-11 10:33:15', '高级权限测试3', '0');
INSERT INTO `sys_user` VALUES ('10031', 'string1', 'string', '4e1ce6d', null, 'string1', 'string1', 'string1', '2019-01-20 23:04:45', 'eamon0', '2019-04-11 10:33:15', 'eamon0', '0');
INSERT INTO `sys_user` VALUES ('10033', 'eamon1', 'string', '2aa8492', '7d2ce1ede2514369acab28e4ed58c3e4', '小熊', '17771842186', '技术总监', '2019-02-23 19:22:57', '小熊', '2019-04-11 10:33:15', '小熊', '0');
INSERT INTO `sys_user` VALUES ('10034', 'guest', 'string', 'b5d3541', '', 'guest', '', '', '2019-02-25 17:42:12', '小纯2', '2019-04-11 10:33:15', '小纯2', '0');
INSERT INTO `sys_user` VALUES ('10035', 'dou', 'string', 'bcfe16b', '7440ccd288a6488a8311bc5b2753ce0b', '豆', '13163305213', '产品经理', '2019-03-14 15:39:44', '小熊', '2019-04-11 10:33:15', '小熊', '0');
INSERT INTO `sys_user` VALUES ('10036', 'admin', 'string', 'b1c0a50', '03947b7c80754b96979aa6228050fab6', '超级管理员', '15972102336', '超级管理员', '2019-03-25 13:20:00', '测试', '2019-04-11 10:33:15', '测试', '0');
INSERT INTO `sys_user` VALUES ('10037', 'admin1', 'string', 'a684518', null, '超级管理员', '13277987795', '超级管理员', '2019-03-25 13:20:32', '测试', '2019-04-11 10:33:15', '测试', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createBy` varchar(24) NOT NULL COMMENT '创建操作人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(24) NOT NULL COMMENT '更新操作人',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`userId`,`roleId`),
  KEY `userIdIndex` (`userId`) USING BTREE,
  KEY `roleIdIndex` (`roleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('10000', '1', '2019-01-08 14:38:14', '', '2019-01-08 14:38:14', '', '0');
INSERT INTO `sys_user_role` VALUES ('10001', '1', '2019-01-02 20:28:18', '', '2019-01-02 20:28:18', '', '0');
INSERT INTO `sys_user_role` VALUES ('10005', '1', '2019-01-02 20:28:18', '', '2019-01-02 20:28:18', '', '0');
INSERT INTO `sys_user_role` VALUES ('10006', '1', '2018-12-10 03:54:29', '', '2019-01-21 11:04:22', '', '0');
INSERT INTO `sys_user_role` VALUES ('10008', '1', '2019-01-02 20:28:18', '', '2019-01-02 20:28:18', '', '0');
INSERT INTO `sys_user_role` VALUES ('10013', '1', '2019-01-02 20:28:18', '', '2019-01-02 20:28:18', '', '0');
INSERT INTO `sys_user_role` VALUES ('10015', '2', '2018-12-27 14:23:36', '', '2019-01-21 09:35:52', '测试', '1');
INSERT INTO `sys_user_role` VALUES ('10016', '11', '2018-12-27 19:53:26', '', '2019-01-21 02:43:17', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10016', '37', '2019-01-21 02:47:31', 'eamon0', '2019-01-21 02:47:31', 'eamon0', '0');
INSERT INTO `sys_user_role` VALUES ('10017', '1', '2018-12-27 21:01:24', '', '2018-12-27 21:24:45', '', '1');
INSERT INTO `sys_user_role` VALUES ('10018', '1', '2019-01-02 20:28:18', '', '2019-01-02 20:28:18', '', '0');
INSERT INTO `sys_user_role` VALUES ('10019', '1', '2018-12-28 12:20:19', '', '2018-12-28 12:20:22', '', '1');
INSERT INTO `sys_user_role` VALUES ('10020', '1', '2018-12-28 14:58:05', '', '2018-12-28 14:58:09', '', '1');
INSERT INTO `sys_user_role` VALUES ('10022', '1', '2019-01-16 11:54:06', '', '2019-01-16 11:54:06', '', '0');
INSERT INTO `sys_user_role` VALUES ('10023', '2', '2019-01-16 11:54:22', '', '2019-01-16 18:28:53', '', '1');
INSERT INTO `sys_user_role` VALUES ('10023', '29', '2019-01-21 11:06:20', '小纯2', '2019-01-21 11:06:20', '小纯2', '0');
INSERT INTO `sys_user_role` VALUES ('10024', '25', '2019-01-16 20:18:54', '', '2019-01-16 20:18:54', '', '0');
INSERT INTO `sys_user_role` VALUES ('10028', '1', '2019-01-18 03:45:48', 'eamon0', '2019-01-18 03:45:48', 'eamon0', '0');
INSERT INTO `sys_user_role` VALUES ('10029', '30', '2019-01-18 10:22:26', '测试', '2019-01-18 10:22:26', '测试', '0');
INSERT INTO `sys_user_role` VALUES ('10030', '1', '2019-01-18 17:51:14', '高级权限测试3', '2019-01-18 17:51:14', '高级权限测试3', '0');
INSERT INTO `sys_user_role` VALUES ('10031', '1', '2019-01-20 23:42:50', 'eamon0', '2019-01-20 23:44:02', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10031', '2', '2019-01-20 23:41:58', 'eamon0', '2019-01-20 23:42:50', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10031', '11', '2019-01-20 23:44:02', 'eamon0', '2019-01-21 01:42:08', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10031', '33', '2019-01-21 01:38:07', 'eamon0', '2019-01-21 01:42:08', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10031', '35', '2019-01-21 01:42:08', 'eamon0', '2019-01-21 02:38:52', 'eamon0', '1');
INSERT INTO `sys_user_role` VALUES ('10031', '37', '2019-01-21 02:38:52', 'eamon0', '2019-01-21 02:55:28', 'eamon0', '0');
INSERT INTO `sys_user_role` VALUES ('10033', '1', '2019-03-14 22:12:10', '小熊', '2019-03-14 22:12:10', '小熊', '0');
INSERT INTO `sys_user_role` VALUES ('10034', '43', '2019-02-25 17:42:12', '小纯2', '2019-02-25 17:42:12', '小纯2', '0');
INSERT INTO `sys_user_role` VALUES ('10035', '1', '2019-03-14 15:39:44', '小熊', '2019-03-14 15:39:44', '小熊', '0');
INSERT INTO `sys_user_role` VALUES ('10036', '1', '2019-03-25 13:20:00', '测试', '2019-03-25 13:20:00', '测试', '0');
INSERT INTO `sys_user_role` VALUES ('10037', '1', '2019-03-25 13:20:32', '测试', '2019-03-25 13:20:32', '测试', '0');
