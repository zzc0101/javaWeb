/*
Navicat MySQL Data Transfer

Source Server         : zzc
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : excise

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-10-17 20:30:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `p_id` int NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `t_city_t_province_p_id_fk` (`p_id`),
  CONSTRAINT `t_city_t_province_p_id_fk` FOREIGN KEY (`p_id`) REFERENCES `t_province` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES ('1', '武汉市', '1');
INSERT INTO `t_city` VALUES ('2', '仙桃市', '1');
INSERT INTO `t_city` VALUES ('3', '荆州市', '1');
INSERT INTO `t_city` VALUES ('4', '天门市', '1');
INSERT INTO `t_city` VALUES ('5', '宜昌市', '1');
INSERT INTO `t_city` VALUES ('6', '襄阳市', '1');
INSERT INTO `t_city` VALUES ('7', '杭州市', '2');
INSERT INTO `t_city` VALUES ('8', '温州市', '2');
INSERT INTO `t_city` VALUES ('9', '宁波市', '2');
INSERT INTO `t_city` VALUES ('10', '绍兴市', '2');
INSERT INTO `t_city` VALUES ('11', '湖州市', '2');
INSERT INTO `t_city` VALUES ('12', '嘉兴市', '2');
INSERT INTO `t_city` VALUES ('13', '广州市', '3');
INSERT INTO `t_city` VALUES ('14', '深圳市', '3');
INSERT INTO `t_city` VALUES ('15', '珠海市', '3');
INSERT INTO `t_city` VALUES ('16', '佛山市', '3');
INSERT INTO `t_city` VALUES ('17', '惠州市', '3');
INSERT INTO `t_city` VALUES ('18', '汕头市', '3');
INSERT INTO `t_city` VALUES ('19', '韶关市', '3');
INSERT INTO `t_city` VALUES ('20', '长沙市', '4');
INSERT INTO `t_city` VALUES ('21', '株洲市', '4');
INSERT INTO `t_city` VALUES ('22', '岳阳市', '4');
INSERT INTO `t_city` VALUES ('23', '张家界市', '4');
INSERT INTO `t_city` VALUES ('24', '邵阳市', '4');
INSERT INTO `t_city` VALUES ('25', '湘潭市', '4');
INSERT INTO `t_city` VALUES ('26', '南京市', '5');
INSERT INTO `t_city` VALUES ('27', '无锡市', '5');
INSERT INTO `t_city` VALUES ('28', '苏州市', '5');
INSERT INTO `t_city` VALUES ('29', '南通市', '5');
INSERT INTO `t_city` VALUES ('30', '常州市', '5');
INSERT INTO `t_city` VALUES ('31', '徐州市', '5');

-- ----------------------------
-- Table structure for t_downloadlist
-- ----------------------------
DROP TABLE IF EXISTS `t_downloadlist`;
CREATE TABLE `t_downloadlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `size` longblob NOT NULL,
  `star` int NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_downloadlist
-- ----------------------------
INSERT INTO `t_downloadlist` VALUES ('1', 'Android.pdf电子书', 'Android.pdf', '安卓是一种基于Linux内核（不包含GNU组件）的自由及开放源代码的操作系统。主要使用于移动设备，如智能手机和平板电脑，由美国Google公司和开放手机联盟领导及开发。Android操作系统最初由Andy Rubin开发，主要支持手机。2005年8月由Google收购注资。2007年11月，Google与84家硬件制造商、软件开发商及电信营运商组建开放手机联盟共同研发改良Android系统。', 0x313631363630, '3', 'e_books.png');
INSERT INTO `t_downloadlist` VALUES ('2', 'Web.pdf电子书', 'Web.pdf', 'JSP（全称JavaServer Pages）是由Sun Microsystems公司主导创建的一种动态网页技术标准。JSP部署于网络服务器上，可以响应客户端发送的请求，并根据请求内容动态地生成HTML、XML或其他格式文档的Web网页，然后返回给请求者。JSP技术以Java语言作为脚本语言，为用户的HTTP请求提供服务，并能与服务器上的其它Java程序共同处理复杂的业务需求。', 0x39353334313835, '4', 'books.png');
INSERT INTO `t_downloadlist` VALUES ('3', '无线传感器网络.pdf电子书', '无线传感器网络.pdf', '无线传感器网络(Wireless Sensor Networks, WSN)是一种分布式传感网络，它的末梢是可以感知和检查外部世界的传感器。WSN中的传感器通过无线方式通信，因此网络设置灵活，设备位置可以随时更改，还可以跟互联网进行有线或无线方式的连接。通过无线通信方式形成的一个多跳自组织网络。', 0x3138313332313432, '2', 'e_books.png');

-- ----------------------------
-- Table structure for t_province
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `province` varchar(255) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_province
-- ----------------------------
INSERT INTO `t_province` VALUES ('1', '湖北省');
INSERT INTO `t_province` VALUES ('2', '浙江省');
INSERT INTO `t_province` VALUES ('3', '广东省');
INSERT INTO `t_province` VALUES ('4', '湖南省');
INSERT INTO `t_province` VALUES ('5', '江苏省');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `resourceId` int NOT NULL AUTO_INCREMENT,
  `resourceName` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '资源下载', '/GetDownloadList.do');
INSERT INTO `t_resource` VALUES ('2', '资源下载页面', '/main/download.jsp');
INSERT INTO `t_resource` VALUES ('3', '用户管理', '/userManager.do');
INSERT INTO `t_resource` VALUES ('4', '用户管理界面', '/main/userManager.jsp');
INSERT INTO `t_resource` VALUES ('5', '资源管理', '/resourceManager.do');
INSERT INTO `t_resource` VALUES ('6', '资源管理界面', '/main/resourceManager.jsp');
INSERT INTO `t_resource` VALUES ('7', '个人中心', '/personalCenter.do');
INSERT INTO `t_resource` VALUES ('8', '个人中心界面', '/main/personalCenter');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员');
INSERT INTO `t_role` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resourceId` int NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roleid_fk_rr` (`roleId`),
  KEY `resourceid_fk` (`resourceId`),
  CONSTRAINT `resourceid_fk` FOREIGN KEY (`resourceId`) REFERENCES `t_resource` (`resourceId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roleid_fk_rr` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES ('1', '1', '1');
INSERT INTO `t_role_resource` VALUES ('2', '2', '1');
INSERT INTO `t_role_resource` VALUES ('3', '3', '1');
INSERT INTO `t_role_resource` VALUES ('4', '4', '1');
INSERT INTO `t_role_resource` VALUES ('5', '5', '1');
INSERT INTO `t_role_resource` VALUES ('6', '6', '1');
INSERT INTO `t_role_resource` VALUES ('7', '7', '1');
INSERT INTO `t_role_resource` VALUES ('8', '8', '1');
INSERT INTO `t_role_resource` VALUES ('9', '1', '2');
INSERT INTO `t_role_resource` VALUES ('10', '2', '2');
INSERT INTO `t_role_resource` VALUES ('11', '7', '2');
INSERT INTO `t_role_resource` VALUES ('12', '8', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `chrName` varchar(255) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', '202cb962ac59075b964b07152d234b70', '管理员', 'admin@qq.com', '湖北省', '武汉市');
INSERT INTO `t_user` VALUES ('ls2020', '827ccb0eea8a706c4c34a16891f84e7b', '李四', '1233@qq.com', '湖北省', '仙桃市');
INSERT INTO `t_user` VALUES ('ww2020', '827ccb0eea8a706c4c34a16891f84e7b', '王五', '11123@qq.com', '浙江省', '绍兴市');
INSERT INTO `t_user` VALUES ('zj2020', '827ccb0eea8a706c4c34a16891f84e7b', '甄姬', '12333@qq.com', '浙江省', '嘉兴市');
INSERT INTO `t_user` VALUES ('zs2020', '81dc9bdb52d04dc20036dbd8313ed055', '张三', '123@qq.com', '江苏省', '南京市');
INSERT INTO `t_user` VALUES ('zzc01', '827ccb0eea8a706c4c34a16891f84e7b', '超级英雄', 'zzc0101@qq.com', '湖北省', '武汉市');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleId` int NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username_fk` (`userName`),
  KEY `roleid_fk` (`roleId`),
  CONSTRAINT `roleid_fk` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username_fk` FOREIGN KEY (`userName`) REFERENCES `t_user` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', 'admin');
INSERT INTO `t_user_role` VALUES ('2', '2', 'zzc01');
INSERT INTO `t_user_role` VALUES ('3', '2', 'zs2020');
INSERT INTO `t_user_role` VALUES ('19', '2', 'ls2020');
INSERT INTO `t_user_role` VALUES ('20', '2', 'ww2020');
INSERT INTO `t_user_role` VALUES ('21', '2', 'zj2020');
