/*
SQLyog v10.2 
MySQL - 5.1.32-community : Database - tourism_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tourism_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tourism_system`;

/*Table structure for table `administration` */

DROP TABLE IF EXISTS `administration`;

CREATE TABLE `administration` (
  `tcaID` int(11) NOT NULL AUTO_INCREMENT COMMENT '旅游局账号',
  `tcaName` varchar(20) DEFAULT NULL COMMENT '旅游局名称',
  `tcaAddress` varchar(20) DEFAULT NULL COMMENT '旅游局地点',
  `tcaScope` varchar(20) DEFAULT NULL COMMENT '旅游局管理范围',
  `tcaPassword` varchar(10) DEFAULT NULL COMMENT '旅游局密码',
  `identify` int(11) DEFAULT NULL COMMENT '管理员身份识别,分角色管理',
  PRIMARY KEY (`tcaID`)
) ENGINE=InnoDB AUTO_INCREMENT=1235 DEFAULT CHARSET=utf8;

/*Data for the table `administration` */

insert  into `administration`(`tcaID`,`tcaName`,`tcaAddress`,`tcaScope`,`tcaPassword`,`identify`) values (1234,'张林','','','111111',NULL);

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `tcID` int(11) NOT NULL AUTO_INCREMENT COMMENT '旅游公司的账号',
  `tcName` varchar(20) DEFAULT NULL COMMENT '旅游公司名称',
  `tcAddress` varchar(20) DEFAULT NULL COMMENT '公司地点',
  `tcScope` varchar(20) DEFAULT NULL COMMENT '旅游公司经营范围',
  `tcPassword` varchar(10) DEFAULT NULL COMMENT '公司密码',
  `identify` int(11) DEFAULT NULL COMMENT '管理员身份识别,分角色登录',
  PRIMARY KEY (`tcID`)
) ENGINE=InnoDB AUTO_INCREMENT=1223 DEFAULT CHARSET=utf8;

/*Data for the table `company` */

insert  into `company`(`tcID`,`tcName`,`tcAddress`,`tcScope`,`tcPassword`,`identify`) values (1222,NULL,NULL,NULL,'111111',NULL);

/*Table structure for table `line` */

DROP TABLE IF EXISTS `line`;

CREATE TABLE `line` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `name` varchar(255) DEFAULT NULL COMMENT '景点名称',
  `catering` varchar(255) DEFAULT NULL COMMENT '餐饮',
  `accommodation` varchar(255) DEFAULT NULL COMMENT '住宿',
  `traffic` varchar(255) DEFAULT NULL COMMENT '交通',
  `price` double DEFAULT NULL COMMENT '价钱',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `contact` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `tourism_time` datetime DEFAULT NULL COMMENT '游览时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `line` */

insert  into `line`(`ID`,`name`,`catering`,`accommodation`,`traffic`,`price`,`picture`,`contact`,`tourism_time`) values (1,'鼓浪屿+厦门5日自由行','2次非自理，5次自理','1晚5星,1晚4星','行车时长9.7小时',3000,'/source/images/20160318/20160318.png',NULL,'2016-03-24 20:45:11'),(2,'江西婺源+三清山3日2晚跟团游','2次非自理，5次自理','1晚5星,1晚4星','行车时长9.7小时',3000,'/source/images/20160318/20160318.png',NULL,'2016-03-24 20:45:13');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID，外键',
  `people_count` int(11) DEFAULT NULL COMMENT '订票人数',
  `total_price` double DEFAULT NULL COMMENT '总价钱',
  `order_time` datetime DEFAULT NULL COMMENT '订票时间',
  `line_id` int(11) DEFAULT NULL COMMENT '旅游路线ID，外键',
  `travel_time` datetime DEFAULT NULL COMMENT '出游时间',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态,0表示订单提交,1表示商家接单',
  PRIMARY KEY (`ID`),
  KEY `os_fk_1` (`user_id`),
  KEY `os_fk_2` (`line_id`),
  CONSTRAINT `os_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`),
  CONSTRAINT `os_fk_2` FOREIGN KEY (`line_id`) REFERENCES `line` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`ID`,`user_id`,`people_count`,`total_price`,`order_time`,`line_id`,`travel_time`,`order_status`) values (1,1222,2,6000,'2016-03-18 21:41:11',2,'2016-03-18 21:41:18',NULL),(2,1222,2,6000,'2016-03-18 21:41:29',1,'2016-03-18 21:41:35',NULL);

/*Table structure for table `scenic_spot` */

DROP TABLE IF EXISTS `scenic_spot`;

CREATE TABLE `scenic_spot` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `position` varchar(255) DEFAULT NULL COMMENT '位置',
  `tour_project` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `introduction` varchar(255) DEFAULT NULL COMMENT '景点介绍',
  `price` double DEFAULT NULL COMMENT '价钱',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `time` datetime DEFAULT NULL COMMENT '开发时间',
  `spot_sort` int(11) DEFAULT NULL COMMENT '景点类别',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `scenic_spot` */

insert  into `scenic_spot`(`ID`,`position`,`tour_project`,`introduction`,`price`,`picture`,`time`,`spot_sort`) values (1,'杭州','杭州西湖风景区','“西湖十景”是指浙江省杭州市著名旅游景点西湖上的十处特色风景，分别是苏堤春晓、曲苑风荷、平湖秋月、断桥残雪、柳浪闻莺、花港观鱼、雷峰夕照、双峰插云、南屏晚钟、三潭印月。西湖十景形成于南宋时期，基本围绕西湖分布，有的就位于湖上。',3000,'/source/images/20160319/201603191618.png','2016-03-18 21:36:28',NULL),(2,'湖南','张家界风景区','张家界景色很美，门票有点贵，火车站建得太小气了，不过森林公园太赞了，它美妙得展现了大自然的美丽，雄伟的山峰直插云霄。森林公园里环境优美，空气很清新，是真正的大自然风光。',NULL,'/source/images/20160319/201603191920.png','2016-03-20 16:24:32',NULL);

/*Table structure for table `tourist` */

DROP TABLE IF EXISTS `tourist`;

CREATE TABLE `tourist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `name` varchar(30) DEFAULT NULL COMMENT '出行人姓名',
  `identify_number` varchar(18) DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tourist` */

/*Table structure for table `travel_time` */

DROP TABLE IF EXISTS `travel_time`;

CREATE TABLE `travel_time` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `line_id` int(11) DEFAULT NULL COMMENT '旅游路线ID',
  `time` datetime DEFAULT NULL COMMENT '出游时间',
  PRIMARY KEY (`ID`),
  KEY `tt_fk_1` (`line_id`),
  CONSTRAINT `tt_fk_1` FOREIGN KEY (`line_id`) REFERENCES `line` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `travel_time` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，主键',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `identify_number` varchar(18) DEFAULT NULL COMMENT '身份证',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1223 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`name`,`password`,`identify_number`,`phone_number`) values (1222,'Nicky','111111','332302221010321111','15511231222');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
