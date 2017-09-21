/*
Navicat MySQL Data Transfer

Source Server         : cc
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : pz

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2017-09-08 16:59:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_progressforms`
-- ----------------------------
DROP TABLE IF EXISTS `tb_progressforms`;
CREATE TABLE `tb_progressforms` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `create_man` varchar(20) DEFAULT NULL,
  `center` varchar(30) DEFAULT NULL COMMENT '中心',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `hope_money` int(20) DEFAULT NULL COMMENT '目标额',
  `finish_money` int(20) DEFAULT NULL COMMENT '已完成金额',
  `unfinish_money` int(20) DEFAULT NULL COMMENT '未完成金额',
  `marks` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_valid` int(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_progressforms
-- ----------------------------
INSERT INTO `tb_progressforms` VALUES ('1', 'shsxt', '浦东', '17777777777', '100000', '50000', '50000', '测试', '1', null, null);
INSERT INTO `tb_progressforms` VALUES ('2', 'shsxt', '浦东', '1771771888', '1000000', '100000', '90000', '', '1', null, null);
INSERT INTO `tb_progressforms` VALUES ('3', 'shsxt', '杨行', '1883715563', '856564', '8976', '89', '', '1', null, null);
INSERT INTO `tb_progressforms` VALUES ('4', 'shsxt', '田林', '18838188888', '500000', '30000', '5999', '天下漫友是一家', '1', null, '2017-09-08 09:18:19');
INSERT INTO `tb_progressforms` VALUES ('5', 'shsxt', '古美中心', '1731724567', '40000', '58000', '25000', 'abc', '1', null, '2017-09-08 09:18:01');
INSERT INTO `tb_progressforms` VALUES ('6', 'Tony', 'center', '121234', '10000', '5000', '5000', 'test', '1', '2017-09-07 15:31:52', '2017-09-08 09:49:00');
INSERT INTO `tb_progressforms` VALUES ('7', 'Tony', '测试', '12333', '1322424', '1000', '2222', 'test', '1', '2017-09-07 15:36:33', '2017-09-07 15:36:33');
INSERT INTO `tb_progressforms` VALUES ('8', 'Tony', '测试', '132222222', '500000', '100000', '3232323', 'test', '1', '2017-09-07 15:41:05', '2017-09-07 15:41:05');
INSERT INTO `tb_progressforms` VALUES ('9', 'Tony', '中心', '111111', '11111', '1111', '1111111', 'test', '1', '2017-09-07 17:09:41', '2017-09-07 17:09:41');
INSERT INTO `tb_progressforms` VALUES ('10', 'Tony', '测试', '122222', '22222', '33333', '44444', '测试', '1', '2017-09-07 17:14:34', '2017-09-08 09:49:05');
INSERT INTO `tb_progressforms` VALUES ('11', 'Tony', '测试', '111111', '11111', '1111', '1111111', '1111', '1', '2017-09-07 17:22:55', '2017-09-08 09:49:05');
INSERT INTO `tb_progressforms` VALUES ('12', 'Tony', '浦东', '111111', '20000', '10000', '1000', 'test', '1', '2017-09-07 17:24:06', '2017-09-07 17:33:17');
INSERT INTO `tb_progressforms` VALUES ('13', 'Tony', 'saasdasd', '111111', '11111', '1111', '1111111', 'sadsadsad', '1', '2017-09-07 17:43:51', '2017-09-07 17:43:51');
INSERT INTO `tb_progressforms` VALUES ('14', 'Tony', '1111', '111111111', '11111', '1111', '1111111', 'test', '1', '2017-09-07 17:44:28', '2017-09-07 17:44:28');
INSERT INTO `tb_progressforms` VALUES ('15', 'Tony', '测试', '1322222', '500000', '400000', '100000', '培正', '1', '2017-09-08 09:31:23', '2017-09-08 09:31:38');
INSERT INTO `tb_progressforms` VALUES ('16', 'chenchen', 'zzz', '111111', '11111', '1111', '1111111', 'www', '1', '2017-09-08 13:40:18', '2017-09-08 13:40:18');
INSERT INTO `tb_progressforms` VALUES ('17', 'chenchen', 'center', '111111', '11111', '1111111', '1111111', 'center', '1', '2017-09-08 13:41:35', '2017-09-08 13:41:35');
INSERT INTO `tb_progressforms` VALUES ('18', 'chenchen', '11111', '232323', '11111', '1111111111', '121212', 'test', '1', '2017-09-08 13:41:59', '2017-09-08 13:41:59');

-- ----------------------------
-- Table structure for `tb_reportforms`
-- ----------------------------
DROP TABLE IF EXISTS `tb_reportforms`;
CREATE TABLE `tb_reportforms` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `center` varchar(255) DEFAULT NULL COMMENT '中心',
  `subscribe_people` int(255) DEFAULT NULL COMMENT '预约人数',
  `arrive_people` int(255) DEFAULT NULL COMMENT '实到人数',
  `order_people` int(255) DEFAULT NULL COMMENT '下单人数',
  `new_order` int(255) DEFAULT NULL COMMENT '新订单',
  `old_order` int(255) DEFAULT NULL COMMENT '续费订单',
  `one_day_money` int(255) DEFAULT NULL COMMENT '日成交额',
  `hope_money` int(255) DEFAULT NULL COMMENT '目标额',
  `difference` int(255) DEFAULT NULL COMMENT '差额',
  `marks` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_valid` int(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reportforms
-- ----------------------------
INSERT INTO `tb_reportforms` VALUES ('1', '培正', 'sfd@outlook.com', '18838123465', '杨行', '20', '18', '15', '10', '5', '18520', '100000', null, '报表', '1', null, null);
INSERT INTO `tb_reportforms` VALUES ('2', 'zhanglb', '123dafa@163.com', '158546789854', '宝山', '45', '40', '38', '30', '8', '5464', '4564987', null, 'fdu', '1', null, null);
INSERT INTO `tb_reportforms` VALUES ('3', '逗点', '1451568989@qq.com', '1731728801', '张扬', '40', '30', '28', '20', '5', '10000', '0', null, '培正逗点', '1', null, null);
INSERT INTO `tb_reportforms` VALUES ('4', 'zhanglb', 'dream913@outlook.com', '17317289013', '古美中心', '30', '25', '22', '18', '4', '100000', '100000000', null, 'hellokitty', '1', null, null);
INSERT INTO `tb_reportforms` VALUES ('5', 'Tony', null, '', '杨浦', '20', '15', '20', '13', '7', '5000', '10000', null, 'test', '1', '2017-09-06 16:44:51', '2017-09-08 15:21:08');
INSERT INTO `tb_reportforms` VALUES ('6', 'Tony', null, '', '总部', '20', '15', '30', '17', '13', '4321', '3211', null, '天下漫友是一家 2222222', '1', '2017-09-06 16:52:54', '2017-09-07 11:47:50');
INSERT INTO `tb_reportforms` VALUES ('7', 'Tony', null, '', '总部', '30', '25', '30', '15', '15', '22222', '333333', null, 'test', '1', '2017-09-06 16:53:49', '2017-09-07 11:47:33');
INSERT INTO `tb_reportforms` VALUES ('8', 'Tony', null, '133333333', '杨浦', '20', '30', '50', '20', '30', '1111111', '2222222', null, 'test', '1', '2017-09-07 10:07:06', '2017-09-07 11:47:33');
INSERT INTO `tb_reportforms` VALUES ('9', 'Tony', null, '133333333', '杨浦', '20', '30', '50', '20', '30', '1111111', '2222222', null, 'add', '1', '2017-09-07 10:07:22', '2017-09-07 11:47:33');
INSERT INTO `tb_reportforms` VALUES ('10', 'Tony', null, '133333333', '杨浦', '20', '30', '50', '20', '30', '1111111', '2222222', null, 'delete', '1', '2017-09-07 10:07:32', '2017-09-07 11:47:22');
INSERT INTO `tb_reportforms` VALUES ('11', 'Tony', null, '1213212121', 'acfun', '30', '20', '50', '25', '25', '80000', '70000', null, 'test', '1', '2017-09-07 10:09:04', '2017-09-07 11:47:33');
INSERT INTO `tb_reportforms` VALUES ('12', 'admin', null, '123313', '中部', '20', '15', '30', '15', '15', '2132', '31231', null, 'aaaaaa', '0', '2017-09-08 16:55:24', '2017-09-08 16:55:43');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `is_valid` int(4) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10', 'shsxt', '96e79218965eb72c92a549dd5a330112', 'Tony', 'java@123.com', '123', '1', '2016-12-01 12:05:49', '2016-08-23 15:26:22');
INSERT INTO `t_user` VALUES ('18', 'test', '96e79218965eb72c92a549dd5a330112', 'test', '123@126.com', '1232323', '1', '2017-02-22 18:50:35', '2017-09-08 14:42:04');
INSERT INTO `t_user` VALUES ('20', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '144@122.com', '111', '1', '2017-02-28 19:47:53', null);
INSERT INTO `t_user` VALUES ('21', 'cc', '202cb962ac59075b964b07152d234b70', 'chenchen', 'cc@163.com', '121234', '1', '2017-09-08 13:31:05', '2017-09-08 13:31:05');
