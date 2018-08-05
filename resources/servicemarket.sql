/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : servicemarket

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-02-02 19:48:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `price` int(11) NOT NULL COMMENT '商品价格，单位：厘',
  `number` int(11) NOT NULL COMMENT '剩余数量',
  `shop_id` int(11) NOT NULL COMMENT '店铺id，外键',
  `picture` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '小财华', '23', '89', '1', 'ww.jieni');
INSERT INTO `goods` VALUES ('2', 'Leninism', '34', '32', '1', 'ee.w');
INSERT INTO `goods` VALUES ('3', '瘦肉', '15', '50', '2', 'sds');
INSERT INTO `goods` VALUES ('4', '糖醋里脊', '20', '50', '2', 'www.google.com');
INSERT INTO `goods` VALUES ('5', '苹果', '2', '100', '1', 'www.163.com');
INSERT INTO `goods` VALUES ('8', '羊肉', '25', '50', '3', 'string');
INSERT INTO `goods` VALUES ('9', '牛肉丸', '50', '45', '3', 'string');
INSERT INTO `goods` VALUES ('10', '大碗汤圆', '5', '20', '3', 'string');
INSERT INTO `goods` VALUES ('11', '香米饭', '2', '100', '4', 'localhost::8080');
INSERT INTO `goods` VALUES ('12', '例汤', '15', '50', '2', 'www.sohu.com');
INSERT INTO `goods` VALUES ('14', '卤蛋', '10', '100', '6', 'http://192.168.111.129/shop6/goods/1517141697907922.jpg');
INSERT INTO `goods` VALUES ('15', '洋葱', '3', '100', '6', 'string');
INSERT INTO `goods` VALUES ('16', '面', '2', '100', '6', 'string');
INSERT INTO `goods` VALUES ('17', '鸡肉', '18', '4', '7', 'http://192.168.111.129/shop3/head/1517153668757490.jpg');
INSERT INTO `goods` VALUES ('18', '猪肚', '32', '15', '7', 'http://192.168.111.129/shop3/head/1517153668757490.jpg');
INSERT INTO `goods` VALUES ('19', '鱼', '16', '22', '7', 'http://192.168.111.129/shop3/head/1517153668757490.jpg');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '商家id 外键',
  `number` int(11) NOT NULL COMMENT '满足团购需要的人数',
  `start_time` datetime NOT NULL COMMENT '团购开始时间',
  `end_time` datetime NOT NULL COMMENT '团购截止时间',
  `original_price` int(11) NOT NULL COMMENT '原价',
  `group_price` int(11) NOT NULL COMMENT '团购价',
  `limit_num` int(11) NOT NULL COMMENT '每人限买几份',
  `surplus` int(11) NOT NULL COMMENT '剩余量',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '7', '50', '2017-10-02 16:42:06', '2017-12-22 08:00:00', '13400', '11100', '2', '50');
INSERT INTO `groups` VALUES ('3', '2', '50', '2017-11-11 17:11:11', '2018-02-15 00:00:00', '13411', '11500', '1', '50');
INSERT INTO `groups` VALUES ('4', '2', '50', '2018-02-03 00:00:00', '2018-02-08 00:00:00', '15555', '11000', '1', '51');
INSERT INTO `groups` VALUES ('5', '6', '100', '2018-02-01 00:00:00', '2018-02-10 10:00:00', '200', '155', '1', '100');
INSERT INTO `groups` VALUES ('6', '1', '85', '2017-12-31 00:00:00', '2018-03-15 10:00:00', '150', '110', '2', '79');
INSERT INTO `groups` VALUES ('7', '3', '20', '2018-01-30 17:07:53', '2018-02-10 17:07:53', '210', '180', '2', '20');

-- ----------------------------
-- Table structure for group_details
-- ----------------------------
DROP TABLE IF EXISTS `group_details`;
CREATE TABLE `group_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '这是团购详情表',
  `group_id` int(11) NOT NULL COMMENT '团购表id 外键',
  `goods_id` int(11) NOT NULL COMMENT '商品id 外键',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `num` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `shop_id` (`goods_id`),
  CONSTRAINT `group_details_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_details_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of group_details
-- ----------------------------
INSERT INTO `group_details` VALUES ('1', '1', '4', '瘦肉', '2');
INSERT INTO `group_details` VALUES ('2', '1', '3', '茄子', '4');
INSERT INTO `group_details` VALUES ('5', '7', '8', '牛肉', '10');
INSERT INTO `group_details` VALUES ('6', '7', '9', '牛肉丸', '20');

-- ----------------------------
-- Table structure for helper_accept
-- ----------------------------
DROP TABLE IF EXISTS `helper_accept`;
CREATE TABLE `helper_accept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代购接受表',
  `helper_id` int(11) NOT NULL COMMENT '代购者id',
  `helper_table_id` int(11) NOT NULL COMMENT '代购表id',
  `dead_time` datetime NOT NULL COMMENT '截止时间',
  `accept_time` datetime NOT NULL COMMENT '接受时间',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `state` int(11) NOT NULL COMMENT '请求者确认状态,1待确认，2用户同意，3用户拒绝',
  PRIMARY KEY (`id`),
  KEY `helper_id` (`helper_id`),
  KEY `helper_table_id` (`helper_table_id`),
  CONSTRAINT `helper_accept_ibfk_1` FOREIGN KEY (`helper_id`) REFERENCES `user` (`id`),
  CONSTRAINT `helper_accept_ibfk_2` FOREIGN KEY (`helper_table_id`) REFERENCES `helper_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of helper_accept
-- ----------------------------
INSERT INTO `helper_accept` VALUES ('5', '4', '3', '2018-02-05 19:35:14', '2018-02-02 19:41:28', '183xxxxxx', '1');

-- ----------------------------
-- Table structure for helper_table
-- ----------------------------
DROP TABLE IF EXISTS `helper_table`;
CREATE TABLE `helper_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `listing` varchar(255) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  `arrive_time` datetime NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin NOT NULL,
  `price` int(11) NOT NULL COMMENT '价格',
  `time` datetime NOT NULL COMMENT '发起时间',
  `state` int(11) NOT NULL COMMENT '是否被代购者接单的状态，1待接收，2已被接收，3表示用户再次确认，4表示完成',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `helper_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of helper_table
-- ----------------------------
INSERT INTO `helper_table` VALUES ('1', '2', '2条排骨，4个鸡蛋，一小包红枣，一个淮山', '广东海洋大学', '2018-02-03 16:00:00', '13420118137', '40', '2018-02-02 15:54:33', '1');
INSERT INTO `helper_table` VALUES ('3', '6', '两斤牛肉丸，2条排骨，4个鸡蛋，一小包红枣，一个淮山', '东莞凤岗', '2018-02-03 16:00:00', '13420118137', '90', '2018-02-02 15:57:07', '2');

-- ----------------------------
-- Table structure for market
-- ----------------------------
DROP TABLE IF EXISTS `market`;
CREATE TABLE `market` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '集市名字',
  `lon` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '集市经度',
  `lat` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '集市纬度s',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of market
-- ----------------------------
INSERT INTO `market` VALUES ('1', '海大超市', '23.33', '34.34');
INSERT INTO `market` VALUES ('2', '超市', '25.66', '21.30');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id 外键',
  `shop_id` int(11) NOT NULL COMMENT '商店id 外键',
  `price` int(11) NOT NULL COMMENT '价格',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `is_deliver` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '是否送货',
  `address` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '地址',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '备注',
  `state` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '1提交，2确认，3拒绝，4取消',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '1', '1', '148', '13420118011', '是', '钟海楼', '好吃的', '2', '2017-12-30 00:45:09');
INSERT INTO `orders` VALUES ('2', '2', '1', '251', '12345678911', '是', '科技楼', '烧烤的', '4', '2017-11-30 11:31:14');
INSERT INTO `orders` VALUES ('3', '6', '2', '350', '13420118137', '是', '海湛', '要新鲜的', '3', '2018-01-29 20:44:28');
INSERT INTO `orders` VALUES ('4', '6', '3', '800', '18316866391', '否', '湖光岩', '多拿几个餐具', '1', '2018-01-29 23:09:45');
INSERT INTO `orders` VALUES ('5', '6', '7', '212', '13420118137', '是', '广东海洋大学', '切块', '1', '2018-01-31 16:34:45');
INSERT INTO `orders` VALUES ('6', '3', '7', '80', '13420118137', '是', '海大', '下单啦', '1', '2018-01-31 17:08:56');

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订购表id 外键',
  `goods_id` int(11) NOT NULL COMMENT '商品id 外键',
  `goods_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `goods_num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES ('1', '1', '1', '小财华', '2', '23');
INSERT INTO `order_details` VALUES ('2', '1', '2', 'Leninism', '3', '34');
INSERT INTO `order_details` VALUES ('3', '2', '1', '小财华', '5', '23');
INSERT INTO `order_details` VALUES ('4', '2', '2', 'Leninism', '4', '34');
INSERT INTO `order_details` VALUES ('5', '3', '3', '瘦肉', '10', '15');
INSERT INTO `order_details` VALUES ('6', '3', '4', '糖醋里脊', '10', '20');
INSERT INTO `order_details` VALUES ('7', '4', '8', '羊肉', '10', '25');
INSERT INTO `order_details` VALUES ('8', '4', '9', '牛肉丸', '10', '50');
INSERT INTO `order_details` VALUES ('9', '4', '10', '大碗汤圆', '10', '5');
INSERT INTO `order_details` VALUES ('10', '5', '17', '鸡肉', '2', '18');
INSERT INTO `order_details` VALUES ('11', '5', '18', '猪肚', '4', '32');
INSERT INTO `order_details` VALUES ('12', '5', '19', '鱼', '3', '16');
INSERT INTO `order_details` VALUES ('13', '6', '19', '鱼', '5', '16');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_shop` varchar(1) COLLATE utf8_bin NOT NULL,
  `is_helper` varchar(1) COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permissions_ibfk_1` (`user_id`),
  CONSTRAINT `permissions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', 'N', 'Y', '1');
INSERT INTO `permissions` VALUES ('2', 'Y', 'Y', '4');
INSERT INTO `permissions` VALUES ('3', 'Y', 'N', '5');
INSERT INTO `permissions` VALUES ('4', 'Y', 'Y', '6');

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约表id',
  `user_id` int(11) NOT NULL COMMENT '用户id，外键',
  `shop_id` int(11) NOT NULL COMMENT '店铺id',
  `des` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '预约描述',
  `ispay` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否支付定金',
  `pay` int(255) DEFAULT NULL COMMENT '定金，单位厘',
  `state` int(11) NOT NULL COMMENT '状态',
  `take_time` datetime DEFAULT NULL COMMENT '取货时间',
  `price` int(11) NOT NULL COMMENT '预约总价，单位厘',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `reserve_time` datetime NOT NULL COMMENT '预约时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `reserve_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES ('1', '1', '1', null, 'N', '0', '4', '2012-07-22 21:00:00', '12', '123', '2012-07-22 21:00:00');
INSERT INTO `reserve` VALUES ('2', '1', '1', 'hijie', 'N', '0', '1', '2012-07-22 21:00:00', '12', '123', '2012-07-22 21:00:00');
INSERT INTO `reserve` VALUES ('4', '1', '1', '这是一个很好的描述', 'N', '0', '5', '2017-11-26 12:11:27', '57', '12345678923', '2017-11-26 12:11:27');
INSERT INTO `reserve` VALUES ('6', '1', '1', '这是一个很好的描述', 'N', '0', '1', '2017-11-26 12:21:55', '5586', '12345678923', '2017-11-26 12:21:55');
INSERT INTO `reserve` VALUES ('7', '2', '1', '这是一个很好的描述', 'N', '0', '3', null, '5586', '12345678923', '2017-11-26 14:06:41');
INSERT INTO `reserve` VALUES ('8', '6', '7', '我要预约', 'Y', '10', '5', '2018-02-15 16:30:19', '54', '13420111137', '2018-01-31 16:44:04');
INSERT INTO `reserve` VALUES ('9', '6', '7', '我要预约', 'N', '0', '1', '2018-02-15 16:30:19', '54', '13420111137', '2018-01-31 16:45:09');
INSERT INTO `reserve` VALUES ('10', '6', '7', '修改后的预约单', 'N', '0', '1', '2018-02-10 16:46:52', '18', '13420118137', '2018-01-31 16:49:23');
INSERT INTO `reserve` VALUES ('11', '1', '7', '我还要预约', 'N', '0', '1', '2018-01-31 17:07:24', '64', '11111111', '2018-01-31 17:17:20');

-- ----------------------------
-- Table structure for reserve_details
-- ----------------------------
DROP TABLE IF EXISTS `reserve_details`;
CREATE TABLE `reserve_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reserve_id` int(11) NOT NULL COMMENT '预定表id 外键',
  `goods_id` int(11) NOT NULL COMMENT '商品id 外键',
  `goods_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `goods_num` int(11) NOT NULL COMMENT '商品数量',
  `goods_price` int(10) NOT NULL COMMENT '商品价格',
  PRIMARY KEY (`id`),
  KEY `reserve_id` (`reserve_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `reserve_details_ibfk_1` FOREIGN KEY (`reserve_id`) REFERENCES `reserve` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reserve_details_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of reserve_details
-- ----------------------------
INSERT INTO `reserve_details` VALUES ('1', '4', '1', '小财华', '98', '23');
INSERT INTO `reserve_details` VALUES ('2', '4', '2', 'Leninism', '98', '34');
INSERT INTO `reserve_details` VALUES ('5', '6', '1', '小财华', '98', '23');
INSERT INTO `reserve_details` VALUES ('6', '6', '2', 'Leninism', '98', '34');
INSERT INTO `reserve_details` VALUES ('7', '7', '1', '小财华', '98', '23');
INSERT INTO `reserve_details` VALUES ('8', '7', '2', 'Leninism', '98', '34');
INSERT INTO `reserve_details` VALUES ('9', '8', '17', '鸡肉', '3', '18');
INSERT INTO `reserve_details` VALUES ('10', '9', '17', '鸡肉', '3', '18');
INSERT INTO `reserve_details` VALUES ('11', '10', '17', '鸡肉', '1', '18');
INSERT INTO `reserve_details` VALUES ('12', '11', '18', '猪肚', '2', '32');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '店铺名称',
  `logo` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '店铺头像',
  `phone` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '电话',
  `type` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `address` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '地址',
  `notice` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '公告',
  `market_id` int(11) NOT NULL COMMENT '所在集市，外键',
  `user_id` int(11) NOT NULL COMMENT '店铺拥有者，外键',
  `des` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺地址',
  `satisfaction` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '用户满意度',
  PRIMARY KEY (`id`),
  KEY `market_id` (`market_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`),
  CONSTRAINT `shop_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '大腕饭', 'loki', '123', '饭', '我家门口', '好吃便宜', '1', '1', 'kdjife', '5');
INSERT INTO `shop` VALUES ('2', '小碗饭', 'logo', '1342011', '肉', '宿舍楼', '还可以', '1', '2', '撒大声地', '4');
INSERT INTO `shop` VALUES ('3', '超市', 'http://192.168.111.128/3/head/1517063219468526.jpg', '13420118137', 'string', 'A412', '全场打折', '1', '4', '新开张', '5');
INSERT INTO `shop` VALUES ('4', '铭香', 'www.baidu.com', '82293718', '烧烤', '飞鸿附近', '欢迎光临', '2', '5', '服务周到', '5');
INSERT INTO `shop` VALUES ('6', '小胖', 'http://192.168.111.129/shop6/head/1517141324308307.jpg', '135225488', '快餐', '湛江', '没有', '2', '3', '偏僻', '5');
INSERT INTO `shop` VALUES ('7', '湖光肉店', '', '110', '肉', '湖光镇', '下午3点半营业', '2', '5', '经济', '5');

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态表id',
  `helper_table_id` int(11) NOT NULL COMMENT '代购表id',
  `helper_id` int(11) NOT NULL COMMENT '代购者id',
  `user_accept_state` int(11) NOT NULL COMMENT '用户确认状态',
  `arrive_state` int(11) NOT NULL COMMENT '代购送达状态,',
  `create_time` datetime NOT NULL COMMENT '状态生成时间',
  `arrive_time` datetime DEFAULT NULL COMMENT '送达时间',
  `appraise` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户评价',
  PRIMARY KEY (`id`),
  KEY `group_id` (`helper_table_id`),
  KEY `helper_id` (`helper_id`),
  CONSTRAINT `state_ibfk_1` FOREIGN KEY (`helper_id`) REFERENCES `user` (`id`),
  CONSTRAINT `state_ibfk_2` FOREIGN KEY (`helper_table_id`) REFERENCES `helper_table` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of state
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'token的值',
  `experTime` datetime NOT NULL COMMENT '过期的时间',
  `isShoper` tinyint(1) NOT NULL COMMENT '是否拥有店主的权限',
  `isHelper` tinyint(1) NOT NULL COMMENT '是否拥有代购者的权限',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('123', '2017-02-04 00:00:00', '1', '1', '1');
INSERT INTO `token` VALUES ('609b4874-9d46-4f22-bde5-7baecd6d93ac', '2017-11-28 20:24:26', '0', '1', '1');
INSERT INTO `token` VALUES ('fda823fe-9ff6-4f57-b12f-2cb67a416b70', '2018-01-27 23:30:24', '0', '0', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 NOT NULL COMMENT '手机',
  `realname` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `qq` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `wechat` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '微信号',
  `address` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '地址',
  `postcode` varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '邮编',
  `other_link` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '其他联系方式',
  `picture` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zlh', 'zlh', '13420120607', '曾亮辉', '23', '74088002', 'efji@126.com', 'hellili', '阿娇低分', '512660', '传真：342343', 'http://192.168.111.129/1/head/2018/01/28/1517145997628046.jpg');
INSERT INTO `user` VALUES ('2', 'zwp', 'zwp', '13201181111', 'string', '0', 'string', 'string', 'string', 'string', 'string', 'string', 'http://192.168.111.128/2/head/2018/01/26/1516957795114818.jpg');
INSERT INTO `user` VALUES ('3', 'nihao', '655555', '58755212', 'string', '0', 'string', 'string', 'string', 'string', 'string', 'string', 'http://192.168.111.129/3/head/2018/01/28/1517142084321807.jpg');
INSERT INTO `user` VALUES ('4', 'string', 'string', 'string', null, null, null, null, null, 'string', null, null, '');
INSERT INTO `user` VALUES ('5', 'string', 'string', 'string', null, null, null, null, null, 'string', null, null, '');
INSERT INTO `user` VALUES ('6', 'string', '123456', '18316866398', '张维鹏', '18', '745233700', '745233700@qq.com', 'a110', '地址', '邮编', '海大', 'http://192.168.111.128/6/head/2018/01/27/1517062385225361.jpg');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id 外键',
  `group_id` int(11) NOT NULL COMMENT '团购表id 外键',
  `is_deliver` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '是否送货',
  `num` int(11) NOT NULL COMMENT '用户购买的份数',
  `address` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '收货地址',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_group_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('5', '2', '3', 'Y', '2', '海大路', '1342011');
INSERT INTO `user_group` VALUES ('7', '1', '1', 'Y', '2', '钟海楼', '159275');
INSERT INTO `user_group` VALUES ('8', '1', '6', 'Y', '6', '学校', '18316866995');
