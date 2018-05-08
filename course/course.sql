/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-08 16:39:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ano` varchar(100) NOT NULL,
  `ausername` varchar(100) DEFAULT NULL,
  `apassword` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cno` varchar(100) NOT NULL,
  `cname` varchar(100) DEFAULT NULL,
  `credit` int(100) DEFAULT NULL,
  `dno` varchar(100) DEFAULT NULL,
  `currentnum` int(100) DEFAULT NULL,
  `maxnum` int(100) DEFAULT NULL,
  `cintroduce` varchar(1000) DEFAULT NULL,
  `tno` varchar(100) DEFAULT NULL,
  `cterm` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cno`),
  KEY `dno` (`dno`),
  KEY `tno` (`tno`),
  CONSTRAINT `dno` FOREIGN KEY (`dno`) REFERENCES `department` (`dno`),
  CONSTRAINT `tno` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '语文', '2', null, null, '100', '啊啊啊啊啊啊啊啊啊啊', '112233', null);
INSERT INTO `course` VALUES ('2', '数学', '2', '2244', '0', '10', null, '100000', '201803');
INSERT INTO `course` VALUES ('3', '英语', '1', null, '3', '10', null, '100001', '201803');
INSERT INTO `course` VALUES ('4', '物理', '1', '1234', '1', '20', '湿答答', '100000', '201803');
INSERT INTO `course` VALUES ('5', '生物', '1', '1234', '3', '20', null, '100000', '201709');
INSERT INTO `course` VALUES ('6', 'zhengzhi', '2', null, '1', '10', null, null, '201803');
INSERT INTO `course` VALUES ('7', '地理', '2', null, null, '20', '啊啊啊啊啊啊啊啊啊啊啊啊啊', '100001', null);
INSERT INTO `course` VALUES ('8', 'xxx', '1', null, '0', '10', null, '100001', '201803');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dno` varchar(100) NOT NULL,
  `dname` varchar(100) DEFAULT NULL,
  `dintroduce` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1234', '软件开发与测试', '我是介绍。。。。。。。。');
INSERT INTO `department` VALUES ('2244', '网络工程', 'vvvvvvvvvvvvv');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleno` varchar(100) NOT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `roleintroduce` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'teacher', '教师');
INSERT INTO `role` VALUES ('3', 'student', '学生');

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `choice` smallint(1) DEFAULT NULL,
  `entry` smallint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', '1');

-- ----------------------------
-- Table structure for stucourse
-- ----------------------------
DROP TABLE IF EXISTS `stucourse`;
CREATE TABLE `stucourse` (
  `sno` varchar(100) DEFAULT NULL,
  `cno` varchar(100) DEFAULT NULL,
  `score` int(100) DEFAULT NULL,
  KEY `sno` (`sno`),
  KEY `cno` (`cno`),
  CONSTRAINT `cno` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`),
  CONSTRAINT `sno` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stucourse
-- ----------------------------
INSERT INTO `stucourse` VALUES ('1234', '5', '22');
INSERT INTO `stucourse` VALUES ('1234', '1', '22');
INSERT INTO `stucourse` VALUES ('1234', '3', null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` varchar(100) NOT NULL,
  `sname` varchar(100) DEFAULT NULL,
  `spassword` varchar(100) DEFAULT NULL,
  `sterm` varchar(100) DEFAULT NULL,
  `dno` varchar(100) DEFAULT NULL,
  `ssex` varchar(100) DEFAULT NULL,
  `idcard` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sno`),
  KEY `ddno` (`dno`),
  CONSTRAINT `ddno` FOREIGN KEY (`dno`) REFERENCES `department` (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1234', '姜智超', '123456', '201803', '1234', '男', '413026199505074210');
INSERT INTO `student` VALUES ('2345', '奥特曼', '123456', '201803', '2244', '女', '123456123456123456');

-- ----------------------------
-- Table structure for subjectrole
-- ----------------------------
DROP TABLE IF EXISTS `subjectrole`;
CREATE TABLE `subjectrole` (
  `no` varchar(100) NOT NULL,
  `roleno` varchar(100) NOT NULL,
  PRIMARY KEY (`no`,`roleno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subjectrole
-- ----------------------------
INSERT INTO `subjectrole` VALUES ('100000', '2');
INSERT INTO `subjectrole` VALUES ('100001', '2');
INSERT INTO `subjectrole` VALUES ('112233', '2');
INSERT INTO `subjectrole` VALUES ('1234', '3');
INSERT INTO `subjectrole` VALUES ('2345', '3');
INSERT INTO `subjectrole` VALUES ('admin', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tno` varchar(100) NOT NULL,
  `tname` varchar(100) DEFAULT NULL,
  `tpassword` varchar(100) DEFAULT NULL,
  `ttitle` varchar(100) DEFAULT NULL,
  `tsex` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('100000', '额额', '123456', '助教', '男');
INSERT INTO `teacher` VALUES ('100001', '啊啊', '123456', '助教', '男');
INSERT INTO `teacher` VALUES ('112233', 'aaaaa', '11223344', '教授', '女');
