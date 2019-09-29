/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.13 : Database - janshcf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`janshcf` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `janshcf`;

/*Table structure for table `cf_accessclient` */

DROP TABLE IF EXISTS `cf_accessclient`;

CREATE TABLE `cf_accessclient` (
  `id` varchar(32) NOT NULL COMMENT '接入者id',
  `acname` varchar(60) NOT NULL COMMENT '接入者名称',
  `cid` varchar(32) NOT NULL,
  `mname` varchar(60) DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(60) DEFAULT NULL COMMENT '联系人邮箱',
  `ackey` varchar(40) DEFAULT NULL COMMENT '秘钥',
  `begintime` varchar(30) NOT NULL COMMENT '开始时间',
  `endtime` varchar(30) NOT NULL COMMENT '结束时间',
  `budget` varchar(16) NOT NULL COMMENT '预算',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `cumulative` varchar(16) NOT NULL,
  `callbackurl` varchar(2000) DEFAULT NULL COMMENT '回调地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入者表';

/*Data for the table `cf_accessclient` */

insert  into `cf_accessclient`(`id`,`acname`,`cid`,`mname`,`phone`,`email`,`ackey`,`begintime`,`endtime`,`budget`,`status`,`cumulative`,`callbackurl`) values ('PpHsaw7Uwpmu','民生-招财猫','PpHsBuaOyYDz','锅子','13231231211','1111111@vv.com','2zOX9p24','2016-06-27 00:00:00','2016-10-22 23:59:59','1200000000.000','kt','144.50','http://www.baidu.com');
insert  into `cf_accessclient`(`id`,`acname`,`cid`,`mname`,`phone`,`email`,`ackey`,`begintime`,`endtime`,`budget`,`status`,`cumulative`,`callbackurl`) values ('PpPXrDUUXZSr','民生-耍猴','PpHsBuaOyYDz','果子','11111111111','11@11.com','jZbJg0XW','2016-06-28 00:00:00','2016-11-27 23:59:59','88888888.000','kt','385.93','http://baidu.com');
insert  into `cf_accessclient`(`id`,`acname`,`cid`,`mname`,`phone`,`email`,`ackey`,`begintime`,`endtime`,`budget`,`status`,`cumulative`,`callbackurl`) values ('PqFt2SuaeuUv','11111111','PpHsBuaOyYDz','','','','Oe9owm2e','2016-07-07 00:00:00','2016-07-31 23:59:59','1111.000','kt','0.000','http://www.baidu.com/cf-manage/accessclient/addinit');

/*Table structure for table `cf_accessprice` */

DROP TABLE IF EXISTS `cf_accessprice`;

CREATE TABLE `cf_accessprice` (
  `apid` varchar(60) NOT NULL COMMENT '接入者报价编号',
  `acid` varchar(32) NOT NULL COMMENT '接入者id',
  `price` varchar(16) NOT NULL COMMENT '报价',
  `ispno` varchar(32) NOT NULL COMMENT '运营商编号',
  `ipstype` varchar(2) NOT NULL COMMENT '套餐类型',
  `province` varchar(4) NOT NULL COMMENT '省份',
  `facevalue` varchar(16) NOT NULL COMMENT '面额',
  `status` varchar(2) NOT NULL COMMENT '状态',
  PRIMARY KEY (`apid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入者报价';

/*Data for the table `cf_accessprice` */

insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2fUEXx4Pt','PpHsaw7Uwpmu','12.000','yd','ll','qg','50M','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2fUEXx4Pu','PpHsaw7Uwpmu','12.110','yd','ll','11','50M','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2fUEXx4Pv','PpHsaw7Uwpmu','9.900','yd','ll','11','20M','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2fUEXx4Pw','PpHsaw7Uwpmu','10.000','yd','ll','11','30M','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2fUEXx4rt','PpHsaw7Uwpmu','20.000','yd','ll','qg','100M','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2ZiLL0pr9','PpHsaw7Uwpmu','29.990','yd','hf','11','30','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2ZiLL0ps9','PpHsaw7Uwpmu','49.990','yd','hf','11','50','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpI2ZiLL0psw','PpHsaw7Uwpmu','49.990','yd','hf','qg','20','kt');
insert  into `cf_accessprice`(`apid`,`acid`,`price`,`ispno`,`ipstype`,`province`,`facevalue`,`status`) values ('PpPY5aEwoztk','PpPXrDUUXZSr','23.000','yd','ll','qg','50M','kt');

/*Table structure for table `cf_additionalquery` */

DROP TABLE IF EXISTS `cf_additionalquery`;

CREATE TABLE `cf_additionalquery` (
  `newbizid` varchar(20) NOT NULL COMMENT '流水id',
  `originalbizid` varchar(20) NOT NULL COMMENT '原流水号',
  `updatetime` varchar(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`newbizid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='补充查询';

/*Data for the table `cf_additionalquery` */

insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PpVaCyAvVoLB','PpVWQvIUudN9','20160629181600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PpVaiAjzQqdC','PpVWQvIUudN9','20160629181800');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PpVaSYttx36W','PpVWQvIUudN9','20160629181700');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PpVWQvIUudN9','PpVWQvIUudN9','20160629180100');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6XlLNcabj','PqG6XlLNcabj','20160707171258');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6Xw8G6pL8','PqG6Xw8G6pL8','20160707171258');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6Y0RyoY74','PqG6Y0RyoY74','20160707171258');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6Y4u0uHf7','PqG6Y4u0uHf7','20160707171259');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6Y9vcHtYl','PqG6Y9vcHtYl','20160707171259');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG6YFB0H196','PqG6YFB0H196','20160707171259');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8ommPBU2T','PqG8ommPBU2T','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8ooZyhFb4','PqG8ooZyhFb4','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8opZuBjVu','PqG8opZuBjVu','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8orBqR7jW','PqG8orBqR7jW','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8os76DVOW','PqG8os76DVOW','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqG8ot9WOUjL','PqG8ot9WOUjL','20160707172200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOGxtuq4nL','PqiOGxtuq4nL','20160712131900');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOH0o5a5BX','PqiOH0o5a5BX','20160712131900');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOH3okSzwa','PqiOH3okSzwa','20160712131901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOH75QMErS','PqiOH75QMErS','20160712131901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHA6rEd3r','PqiOHA6rEd3r','20160712131901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHDGTcRdo','PqiOHDGTcRdo','20160712131901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHGRXq8HV','PqiOHGRXq8HV','20160712131901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHJQfSxjS','PqiOHJQfSxjS','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHMYj5uJp','PqiOHMYj5uJp','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHPXlwf4w','PqiOHPXlwf4w','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHQTOUBiM','PqiOHQTOUBiM','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHRN9S70k','PqiOHRN9S70k','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHSINHVU5','PqiOHSINHVU5','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHT745opp','PqiOHT745opp','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHU4qqJw3','PqiOHU4qqJw3','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiOHV2KcKhs','PqiOHV2KcKhs','20160712131902');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1dG0mvW8','PqiP1dG0mvW8','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1f4wA53a','PqiP1f4wA53a','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1gCU8IHT','PqiP1gCU8IHT','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1hIcr4EM','PqiP1hIcr4EM','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1iJXdaT6','PqiP1iJXdaT6','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqiP1jHpRfl2','PqiP1jHpRfl2','20160712132200');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK2kmIgEQJ0','PqG8ooZyhFb4','20160708092300');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3VbPNo2w1','PqK3VbPNo2w1','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3VdFrS0Uc','PqK3VdFrS0Uc','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3Veqf8W87','PqK3Veqf8W87','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3Vfw76yUN','PqK3Vfw76yUN','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3VhPPPEm4','PqK3VhPPPEm4','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK3ViSU9h2I','PqK3ViSU9h2I','20160708092600');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WC5nj8ID','PqK4WC5nj8ID','20160708093000');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WGE4L6KK','PqK4WGE4L6KK','20160708093000');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WKrBAF8c','PqK4WKrBAF8c','20160708093001');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WOSPgQ9X','PqK4WOSPgQ9X','20160708093001');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WS66AcrK','PqK4WS66AcrK','20160708093001');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqK4WW01T8sz','PqK4WW01T8sz','20160708093001');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqLsXvLqgMmP','PqLsXvLqgMmP','20160708165500');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqLsY4TXYApQ','PqLsY4TXYApQ','20160708165500');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM3ciUrOdYs','PqM3ciUrOdYs','20160708173900');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM3d0MyMgEv','PqM3d0MyMgEv','20160708173901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM3N7EEGQl6','PqM3N7EEGQl6','20160708173800');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM3NnHeg0Qu','PqM3NnHeg0Qu','20160708173802');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM3NURxefyb','PqM3NURxefyb','20160708173801');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM5daBLWr62','PqM5daBLWr62','20160708174700');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM68nmlQV9p','PqM68nmlQV9p','20160708174900');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM69DreiGTD','PqM69DreiGTD','20160708174901');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM6kr2d0wJ5','PqM6kr2d0wJ5','20160708175409');
insert  into `cf_additionalquery`(`newbizid`,`originalbizid`,`updatetime`) values ('PqM7W3ikuEAt','PqM7W3ikuEAt','20160708175501');

/*Table structure for table `cf_audit` */

DROP TABLE IF EXISTS `cf_audit`;

CREATE TABLE `cf_audit` (
  `id` varchar(32) NOT NULL COMMENT '审批id',
  `auname` varchar(32) NOT NULL COMMENT '审批名称',
  `autype` varchar(10) NOT NULL COMMENT '审批类型',
  `detailkey` varchar(16) DEFAULT NULL COMMENT '明细主键',
  `createtime` varchar(20) NOT NULL COMMENT '手机号',
  `updatetime` varchar(20) DEFAULT NULL COMMENT '审批时间',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `auditer` varchar(32) DEFAULT NULL COMMENT '审批人',
  `draftman` varchar(32) DEFAULT NULL COMMENT '起草人',
  `opinion` varchar(1000) DEFAULT NULL COMMENT '意见',
  `servicename` varchar(50) DEFAULT NULL COMMENT '接口实现名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批表';

/*Data for the table `cf_audit` */

insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpHyOa8TRl3q','供应商价格管理','create','PpHyOYyevogo','2016-06-27 10:24:15','2016-06-27 10:39:57','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpHyzCLnB5Bt','供应商价格管理','create','PpHyzByyDbTd','2016-06-27 10:26:36','2016-06-27 10:39:53','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpI2fUGklFbi','接入者价格管理','create','PpI2fUEXx4Pt','2016-06-27 10:41:14','2016-06-27 10:42:26','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpI2ZiNNlo8J','接入者价格管理','create','PpI2ZiLL0pr8','2016-06-27 10:40:52','2016-06-27 10:42:31','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpI6fA0PkDXR','批量充值管理','create','PpI6d1m6lDfe','2016-06-27 10:57:06','2016-06-28 17:30:24','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpPY08oACraX','接入者价格管理','create','PpPY08e23yDj','2016-06-28 17:29:36','2016-06-28 17:30:38','66','PpI1onbw6DPO','PpHpRxbS73Gg','tongyi','auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpPY5aGqyp5z','接入者价格管理','create','PpPY5aEwoztk','2016-06-28 17:29:57','2016-06-28 17:30:29','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpPYoB1Etu9W','批量充值管理','create','PpPYm6pv23s2','2016-06-28 17:32:48','2016-06-28 17:33:00','66','PpI1onbw6DPO','PpHpRxbS73Gg',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpUEHwihrZoY','批量充值管理','create','PpUEGzrSu66Z','2016-06-29 12:42:39','','1',NULL,'admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpUKIvZjJPj5','批量充值管理','create','PpUKIIguRFVa','2016-06-29 13:06:33','2016-06-29 13:06:52','66','PpHpRxbS73Gg','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PpVWKFKBAWHm','批量充值管理','create','PpVWJc3u1XEI','2016-06-29 18:00:34','2016-06-29 18:00:54','66','PpHpRxbS73Gg','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcjWZ5szW4v','批量充值管理','create','PqcjUke8UEaM','2016-07-11 14:05:47','','1',NULL,'admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('Pqcpu88QGmSS','供应商价格管理','delete','PpHyOYyevogo','2016-07-11 14:31:08','2016-07-11 14:35:42','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcqezXpQJnl','接入者价格管理','delete','PpI2ZiLL0pr8','2016-07-11 14:34:08','2016-07-11 14:35:30','66','PpI1onbw6DPO','admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcqJQXa5qJQ','接入者价格管理','delete','PqLrNcjEentG','2016-07-11 14:32:45','2016-07-11 14:35:36','66','PpI1onbw6DPO','admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcqmCezZb7b','接入者价格管理','delete','PqLq50wWUPGv','2016-07-11 14:34:35','2016-07-11 14:35:25','66','PpI1onbw6DPO','admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcrdyR8gzvR','供应商价格管理','delete','PqcrMPvr2kMk','2016-07-11 14:38:02','2016-07-11 14:38:15','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcrMQQhKwUM','供应商价格管理','create','PqcrMPvr2kMk','2016-07-11 14:36:55','2016-07-11 14:37:11','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqcrpYfnKNoM','供应商价格管理','create','PqcrpYFjztcZ','2016-07-11 14:38:47','2016-07-11 14:38:59','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqdMKiyi2iQG','供应商价格管理','create','PqdMKi4AHOra','2016-07-11 16:39:56','2016-07-11 16:40:08','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqdYXJ6cTgBw','批量充值管理','create','PqdYW3RqMVXV','2016-07-11 17:28:25','2016-07-11 17:28:54','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqG2XSBt1oaW','批量充值管理','create','PqG2U8brW0BQ','2016-07-07 16:57:03','2016-07-07 17:05:14','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqG8VgCAxDkp','批量充值管理','create','PqG8UaU55Y4l','2016-07-07 17:20:46','2016-07-07 17:21:01','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqhxnkFLbqko','批量充值管理','create','PqhxmzZhmxoI','2016-07-12 11:33:51','2016-07-12 11:34:03','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqiaVBz1kjpw','供应商价格管理','delete','PqiXvMnIsWpS','2016-07-12 14:07:35','2016-07-12 14:07:49','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqifhtTTqoqZ','供应商价格管理','create','Pqifhsa2IL7Z','2016-07-12 14:28:15','2016-07-12 14:28:30','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqihWuHeR3t6','供应商价格管理','delete','Pqifhsa2IL7Z','2016-07-12 14:35:30','2016-07-12 14:35:40','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqiOl07InRFZ','批量充值管理','create','PqiOkFmm0Og6','2016-07-12 13:20:56','2016-07-12 13:21:09','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqiXvNmNWFdw','供应商价格管理','create','PqiXvMnIsWpS','2016-07-12 13:57:21','2016-07-12 13:57:34','66','PpI1onbw6DPO','admin',NULL,'auditSupplierpriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqK3DDrR13sF','批量充值管理','create','PqK3BhBXcnUS','2016-07-08 09:24:49','2016-07-08 09:25:07','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqK4H6ebK9M2','批量充值管理','create','PqK4GGYPpkwH','2016-07-08 09:29:02','2016-07-08 09:29:18','66','PpI1onbw6DPO','admin',NULL,'auditBatchrechargeServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqLpzee3ndgW','接入者价格管理','create','PqLpzeR5QZtf','2016-07-08 16:44:51','','1',NULL,'admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqLq50yPZvaR','接入者价格管理','create','PqLq50wWUPGv','2016-07-08 16:45:12','2016-07-08 16:51:47','66','PpI1onbw6DPO','admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqLrItHxrJR1','接入者价格管理','create','PqLrIt76IgY1','2016-07-08 16:50:03','','1',NULL,'admin',NULL,'auditAccesspriceServiceImpl');
insert  into `cf_audit`(`id`,`auname`,`autype`,`detailkey`,`createtime`,`updatetime`,`status`,`auditer`,`draftman`,`opinion`,`servicename`) values ('PqLrNclUdIRi','接入者价格管理','create','PqLrNcjEentG','2016-07-08 16:50:22','2016-07-08 16:51:39','66','PpI1onbw6DPO','admin',NULL,'auditAccesspriceServiceImpl');

/*Table structure for table `cf_auditdetail` */

DROP TABLE IF EXISTS `cf_auditdetail`;

CREATE TABLE `cf_auditdetail` (
  `id` varchar(32) NOT NULL COMMENT '审批id',
  `detail` text NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批明细表';

/*Data for the table `cf_auditdetail` */

insert  into `cf_auditdetail`(`id`,`detail`) values ('PpHyOa8TRl3q','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PpHyOYyevogo\",\"pname\":\"移动-北京-话费-20元\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"20\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"19.800\",\"begintime\":\"2016-06-27 00:00:00\",\"endtime\":\"2016-08-20 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"900011\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpHyzCLnB5Bt','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PpHyzByyDbTd\",\"pname\":\"移动-流量-全国-50M\",\"ispno\":\"yd\",\"ipstype\":\"ll\",\"pno\":\"qg\",\"size\":\"50M\",\"sid\":\"PpHy74Uyljov\",\"price\":\"9.880\",\"begintime\":\"2016-06-27 00:00:00\",\"endtime\":\"2016-09-23 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"900022\",\"company\":\"M\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpI2fUGklFbi','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PpI2fUEXx4Pt\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"12.000\",\"ispno\":\"yd\",\"ipstype\":\"ll\",\"province\":\"qg\",\"facevalue\":\"50M\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpI2ZiNNlo8J','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PpI2ZiLL0pr8\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"21.000\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpI6fA0PkDXR','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PpI6d1m6lDfe\",\"batchname\":\"批次001\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpPY08oACraX','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PpPY08e23yDj\",\"acid\":\"PpPXrDUUXZSr\",\"price\":\"22.000\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpPY5aGqyp5z','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PpPY5aEwoztk\",\"acid\":\"PpPXrDUUXZSr\",\"price\":\"23.000\",\"ispno\":\"yd\",\"ipstype\":\"ll\",\"province\":\"qg\",\"facevalue\":\"50M\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpPYoB1Etu9W','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PpPYm6pv23s2\",\"batchname\":\"20160628\",\"apno\":\"PpPXrDUUXZSr\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpUEHwihrZoY','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PpUEGzrSu66Z\",\"batchname\":\"201606291242\",\"apno\":\"PpPXrDUUXZSr\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpUKIvZjJPj5','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PpUKIIguRFVa\",\"batchname\":\"201606291306\",\"apno\":\"PpPXrDUUXZSr\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PpVWKFKBAWHm','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PpVWJc3u1XEI\",\"batchname\":\"20160629\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcjWZ5szW4v','{\"data\":{\"cfBatchinfoList\":null,\"errorinfo\":null,\"CfBatchrechargeList\":null,\"cfCustomerList\":null,\"cfAccessclientList\":null,\"cid\":null,\"batchid\":\"PqcjUke8UEaM\",\"qbatchid\":null,\"batchname\":\"07111405\",\"apno\":\"PpHsaw7Uwpmu\",\"acname\":null,\"userid\":null,\"cname\":null,\"status\":\"20\",\"qstatus\":null,\"start\":null,\"length\":null,\"count\":null,\"createtime\":null,\"updatetime\":null,\"begintime\":null,\"endtime\":null,\"cfBatchrechargeList\":null},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('Pqcpu88QGmSS','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PpHyOYyevogo\",\"pname\":\"移动-北京-话费-20元\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"20\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"19.800\",\"begintime\":\"2016-06-27 00:00:00\",\"endtime\":\"2016-08-20 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"900011\",\"company\":null,\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcqezXpQJnl','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PpI2ZiLL0pr8\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"29.000\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"30\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcqJQXa5qJQ','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLrNcjEentG\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"19.900\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcqmCezZb7b','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLq50wWUPGv\",\"acid\":\"PpPXrDUUXZSr\",\"price\":\"19.990\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcrdyR8gzvR','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqcrMPvr2kMk\",\"pname\":\"0711\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"20\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"19.990\",\"begintime\":\"2016-07-11 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"0711\",\"company\":null,\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcrMQQhKwUM','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqcrMPvr2kMk\",\"pname\":\"0711\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"20\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"19.990\",\"begintime\":\"2016-07-11 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"0711\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqcrpYfnKNoM','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqcrpYFjztcZ\",\"pname\":\"0711\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"11\",\"size\":\"20\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"18.880\",\"begintime\":\"2016-07-11 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"0711\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqdMKiyi2iQG','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqdMKi4AHOra\",\"pname\":\"移动-话费-山东\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"37\",\"size\":\"30\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"29.990\",\"begintime\":\"2016-07-11 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"07111639\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqdYXJ6cTgBw','{\"data\":{\"cfBatchinfoList\":null,\"errorinfo\":null,\"CfBatchrechargeList\":null,\"cfCustomerList\":null,\"cfAccessclientList\":null,\"cid\":null,\"batchid\":\"PqdYW3RqMVXV\",\"qbatchid\":null,\"batchname\":\"07111728\",\"apno\":\"PpHsaw7Uwpmu\",\"acname\":null,\"userid\":null,\"cname\":null,\"status\":\"20\",\"qstatus\":null,\"start\":null,\"length\":null,\"count\":null,\"createtime\":null,\"updatetime\":null,\"begintime\":null,\"endtime\":null,\"cfBatchrechargeList\":null},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqG2XSBt1oaW','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PqG2U8brW0BQ\",\"batchname\":\"07071656\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqG8VgCAxDkp','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PqG8UaU55Y4l\",\"batchname\":\"0000\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqhxnkFLbqko','{\"data\":{\"cfBatchinfoList\":null,\"errorinfo\":null,\"CfBatchrechargeList\":null,\"cfCustomerList\":null,\"cfAccessclientList\":null,\"cid\":null,\"batchid\":\"PqhxmzZhmxoI\",\"qbatchid\":null,\"batchname\":\"07121133\",\"apno\":\"PpHsaw7Uwpmu\",\"acname\":null,\"userid\":null,\"cname\":null,\"status\":\"20\",\"qstatus\":null,\"start\":null,\"length\":null,\"count\":null,\"createtime\":null,\"updatetime\":null,\"begintime\":null,\"endtime\":null,\"cfBatchrechargeList\":null},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqiaVBz1kjpw','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqiXvMnIsWpS\",\"pname\":\"全国-话费-移动-30元\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"30\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"29.990\",\"begintime\":\"2016-07-12 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"07121357\",\"company\":null,\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqifhtTTqoqZ','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"Pqifhsa2IL7Z\",\"pname\":\"移动-话费-全国-30\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"30\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"29.980\",\"begintime\":\"2016-07-12 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"07121427\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqihWuHeR3t6','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"Pqifhsa2IL7Z\",\"pname\":\"移动-话费-全国-30\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"30\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"29.980\",\"begintime\":\"2016-07-12 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"07121427\",\"company\":null,\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqiOl07InRFZ','{\"data\":{\"cfBatchinfoList\":null,\"errorinfo\":null,\"CfBatchrechargeList\":null,\"cfCustomerList\":null,\"cfAccessclientList\":null,\"cid\":null,\"batchid\":\"PqiOkFmm0Og6\",\"qbatchid\":null,\"batchname\":\"07121320\",\"apno\":\"PpHsaw7Uwpmu\",\"acname\":null,\"userid\":null,\"cname\":null,\"status\":\"20\",\"qstatus\":null,\"start\":null,\"length\":null,\"count\":null,\"createtime\":null,\"updatetime\":null,\"begintime\":null,\"endtime\":null,\"cfBatchrechargeList\":null},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqiXvNmNWFdw','{\"data\":{\"CfSupplierpriceList\":null,\"supplierList\":null,\"provinceList\":null,\"id\":\"PqiXvMnIsWpS\",\"pname\":\"全国-话费-移动-30元\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"pno\":\"qg\",\"size\":\"30\",\"sid\":\"PpHsvfrpHNbR\",\"price\":\"29.990\",\"begintime\":\"2016-07-12 00:00:00\",\"endtime\":\"2016-07-31 23:59:59\",\"status\":\"kt\",\"cpordernos\":\"07121357\",\"company\":\"\",\"time\":null,\"cfSupplierpriceList\":null},\"class\":\"cn.com.jansh.model.SupplierpriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqK3DDrR13sF','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PqK3BhBXcnUS\",\"batchname\":\"0708\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqK4H6ebK9M2','{\"data\":{\"cfBatchinfoList\":null,\"CfBatchrechargeList\":null,\"errorinfo\":null,\"cfCustomerList\":null,\"batchid\":\"PqK4GGYPpkwH\",\"batchname\":\"07080928\",\"apno\":\"PpHsaw7Uwpmu\",\"status\":\"20\"},\"class\":\"cn.com.jansh.model.BatchinfoModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqLpzee3ndgW','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLpzeR5QZtf\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"19.900\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"gb\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqLq50yPZvaR','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLq50wWUPGv\",\"acid\":\"PpPXrDUUXZSr\",\"price\":\"19.990\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqLrItHxrJR1','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLrIt76IgY1\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"19.990\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');
insert  into `cf_auditdetail`(`id`,`detail`) values ('PqLrNclUdIRi','{\"data\":{\"priceList\":null,\"cfCustomerList\":null,\"provinceList\":null,\"apid\":\"PqLrNcjEentG\",\"acid\":\"PpHsaw7Uwpmu\",\"price\":\"19.900\",\"ispno\":\"yd\",\"ipstype\":\"hf\",\"province\":\"qg\",\"facevalue\":\"20\",\"status\":\"kt\",\"pname\":null,\"acname\":null,\"audit\":null,\"ipsnoList\":null},\"class\":\"cn.com.jansh.model.AccesspriceModel\"}');

/*Table structure for table `cf_batchinfo` */

DROP TABLE IF EXISTS `cf_batchinfo`;

CREATE TABLE `cf_batchinfo` (
  `batchid` varchar(32) NOT NULL COMMENT '批次编号',
  `batchname` varchar(60) NOT NULL COMMENT '批次名称',
  `apno` varchar(32) NOT NULL COMMENT '接入者编号',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `userid` varchar(32) DEFAULT NULL,
  `updatetime` varchar(14) DEFAULT NULL,
  `createtime` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`batchid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='批量充值表';

/*Data for the table `cf_batchinfo` */

insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PpI6d1m6lDfe','批次001','PpHsaw7Uwpmu','33','PpHpRxbS73Gg',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PpPYm6pv23s2','20160628','PpPXrDUUXZSr','33','PpHpRxbS73Gg',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PpUEGzrSu66Z','201606291242','PpPXrDUUXZSr','20','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PpUKIIguRFVa','201606291306','PpPXrDUUXZSr','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PpVWJc3u1XEI','20160629','PpHsaw7Uwpmu','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqcjUke8UEaM','07111405','PpHsaw7Uwpmu','20','admin','20160711140541','20160711140541');
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqdYMheXeATI','07111727','PpHsaw7Uwpmu','20','admin','20160711172745','20160711172745');
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqdYW3RqMVXV','07111728','PpHsaw7Uwpmu','33','admin','20160711172820','20160711172820');
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqG1btggdWUL','0707','PpHsaw7Uwpmu','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqG2U8brW0BQ','07071656','PpHsaw7Uwpmu','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqG8UaU55Y4l','0000','PpHsaw7Uwpmu','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqhxmzZhmxoI','07121133','PpHsaw7Uwpmu','33','admin','20160712113349','20160712113349');
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqiOkFmm0Og6','07121320','PpHsaw7Uwpmu','33','admin','20160712132054','20160712132054');
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqK3BhBXcnUS','0708','PpHsaw7Uwpmu','33','admin',NULL,NULL);
insert  into `cf_batchinfo`(`batchid`,`batchname`,`apno`,`status`,`userid`,`updatetime`,`createtime`) values ('PqK4GGYPpkwH','07080928','PpHsaw7Uwpmu','33','admin',NULL,NULL);

/*Table structure for table `cf_batchrecharge` */

DROP TABLE IF EXISTS `cf_batchrecharge`;

CREATE TABLE `cf_batchrecharge` (
  `id` varchar(32) NOT NULL COMMENT '充值id',
  `ispno` varchar(32) DEFAULT NULL,
  `isptype` varchar(2) NOT NULL COMMENT '套餐类型',
  `facevalue` varchar(16) NOT NULL COMMENT '面值',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `batchid` varchar(32) NOT NULL COMMENT '批量充值编号',
  `bizid` varchar(32) DEFAULT NULL COMMENT '流水号',
  `status` varchar(2) NOT NULL COMMENT '发送状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='批量充值明细表';

/*Data for the table `cf_batchrecharge` */

insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEcBrkNN','yd','hf','10','13025166548','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEd0YXMC','yd','hf','10','13325166548','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEdPDoJ0','yd','hf','10','13021166548','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEe0LWSd','yd','hf','10','13025166448','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEektGoh','yd','hf','10','13025136548','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpI6dEeNesZf','yd','hf','10','13025566548','PpI6d1m6lDfe',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHqWVfEb','yd','hf','99','13025166548','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHrJhnLI','yd','hf','99','13021166548','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHrpvRji','yd','hf','99','13025166448','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHrXSWKl','yd','hf','99','13325166548','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHryhNyq','yd','hf','99','13025136548','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpPYmHstPHAH','yd','hf','99','13025566548','PpPYm6pv23s2',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCHoVB8Z','yd','hf','10','13025166548','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCHrd852','yd','hf','10','13325166548','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCHTLXzr','yd','hf','10','13021166548','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCI58ejP','yd','ll','10M','13025566548','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCIj4qhd','yd','hf','10','13025136548','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUEHCILhrFv','yd','hf','10','13025166448','PpUEGzrSu66Z',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISV3BqBg','yd','hf','10','13025166548','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISW5L2YL','yd','hf','10','13021166548','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISWmFqeV','yd','hf','10','13325166548','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISWnKpGp','yd','hf','10','13025166448','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISWS8nN3','yd','hf','10','13025136548','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpUKISX0Hwun','yd','ll','10M','13025566548','PpUKIIguRFVa',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgdWzPdD','yd','hf','10','13021166548','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgdZAyOj','yd','hf','10','13025166548','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgetUkzy','yd','hf','10','13025166448','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgewmEOg','yd','hf','10','13325166548','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgflY3eP','yd','ll','10M','13025566548','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PpVWJgfyYCxu','yd','hf','10','13025136548','PpVWJc3u1XEI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUzmFtcNW',NULL,'hf','10','13025166548','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUzmH0tG8',NULL,'hf','10','13255551234','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUzmnYLIx',NULL,'hf','10','18812341234','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUzn26E0K',NULL,'hf','10','13912341234','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUznEL2XP',NULL,'ll','10M','15213241234','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqcjUznGA55a',NULL,'hf','10','17712341234','PqcjUke8UEaM',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMulOCOF6',NULL,'hf','10','13025166548','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMulvtw1P',NULL,'hf','10','13255551234','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMumqcALX',NULL,'hf','10','17712341234','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMumwmIwn',NULL,'hf','10','13912341234','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMumzzw4l',NULL,'hf','10','18812341234','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYMunQk7r5',NULL,'ll','10M','15213241234','PqdYMheXeATI',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4CWhjQi',NULL,'hf','50','13812345670','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4D7DR5Q',NULL,'hf','50','13812345671','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4DtaTji',NULL,'hf','50','13812345674','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4DXnO5k',NULL,'hf','50','13812345673','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4DYCdyL',NULL,'hf','50','13812345672','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4E43OsP',NULL,'hf','50','13812345678','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4EG5mbY',NULL,'hf','50','13812345676','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4ELMapK',NULL,'hf','50','13812345675','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4EMM72E',NULL,'hf','50','13812345677','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqdYW4FkD1xW',NULL,'hf','50','13812345679','PqdYW3RqMVXV',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4samk1C','yd','hf','10','13025166548','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4sRcHLX',NULL,'hf','10','13021166548','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4t7D3w6',NULL,'hf','10','13025166448','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4t7S016',NULL,'hf','10','13325166548','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4uCShCh',NULL,'hf','10','13025136548','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG1c4urHMfv',NULL,'ll','10M','13025566548','PqG1btggdWUL',NULL,'21');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9A9v3DL',NULL,'hf','10','13025166548','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9Bbuu09',NULL,'hf','10','13912341234','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9BpV9Jr',NULL,'hf','10','17712341234','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9BTSRx0',NULL,'hf','10','18812341234','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9BZbj0s',NULL,'hf','10','13255551234','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG2U9CI3Q3V',NULL,'ll','10M','15213241234','PqG2U8brW0BQ',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk4LCTq8',NULL,'hf','10','18812341234','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk4NQl7b',NULL,'hf','10','13255551234','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk4yGa7C',NULL,'hf','10','13025166548','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk57heJq',NULL,'hf','10','13912341234','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk5FN5f5',NULL,'hf','10','17712341234','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqG8Uk5XaJXB',NULL,'ll','10M','15213241234','PqG8UaU55Y4l',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnEo2o7us',NULL,'hf','10','13025166548','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnEpd253D',NULL,'hf','10','18812341234','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnEpZNZyg',NULL,'hf','10','13255551234','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnEqBQ4fV',NULL,'hf','10','13912341234','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnEqu7QLI',NULL,'hf','10','17712341234','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqhxnErRHIQZ',NULL,'ll','10M','15213241234','PqhxmzZhmxoI',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS4hTLsV',NULL,'hf','10','13025166548','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS4KlzC2',NULL,'hf','10','13255551234','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS5FtYSk',NULL,'hf','10','18812341234','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS5udmve',NULL,'hf','10','17712341234','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS6252m7',NULL,'hf','10','13912341234','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqiOkS6FV5PS',NULL,'ll','10M','15213241234','PqiOkFmm0Og6',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BraEBwdo',NULL,'hf','10','18812341234','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BrajeUy4',NULL,'hf','10','13912341234','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BraNeB24',NULL,'hf','10','17712341234','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BrazVDCb',NULL,'hf','10','13255551234','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BrbVci5C',NULL,'ll','10M','15213241234','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK3BrZwrMOL',NULL,'hf','10','13025166548','PqK3BhBXcnUS',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHJdp0qj',NULL,'hf','10','13255551234','PqK4GGYPpkwH',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHJlx9ta',NULL,'hf','10','13025166548','PqK4GGYPpkwH',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHKLNunv',NULL,'hf','10','18812341234','PqK4GGYPpkwH',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHKVz7Ie',NULL,'hf','10','17712341234','PqK4GGYPpkwH',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHLvhM3F',NULL,'hf','10','13912341234','PqK4GGYPpkwH',NULL,'33');
insert  into `cf_batchrecharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`batchid`,`bizid`,`status`) values ('PqK4GHLyY3nW',NULL,'ll','10M','15213241234','PqK4GGYPpkwH',NULL,'33');

/*Table structure for table `cf_currbusilog` */

DROP TABLE IF EXISTS `cf_currbusilog`;

CREATE TABLE `cf_currbusilog` (
  `bizid` varchar(20) NOT NULL COMMENT '流水id',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `ispno` varchar(32) DEFAULT NULL,
  `ipstype` varchar(2) NOT NULL COMMENT '套餐类型',
  `province` varchar(4) DEFAULT NULL COMMENT '省份',
  `facevalue` varchar(16) NOT NULL COMMENT '面值',
  `orderid` varchar(32) DEFAULT NULL COMMENT '供应商订单编号',
  `cporderno` varchar(50) NOT NULL COMMENT 'cp订购编号',
  `systransno` varchar(20) NOT NULL COMMENT '接入者订单编号',
  `responsecode` varchar(10) DEFAULT NULL COMMENT '操作结果编码',
  `spprice` varchar(16) NOT NULL COMMENT '供应商报价',
  `spno` varchar(32) NOT NULL COMMENT '供应商报价编号',
  `apprice` varchar(16) NOT NULL COMMENT '接入者报价',
  `apno` varchar(32) NOT NULL COMMENT '接入者报价编号·',
  `createtime` varchar(20) NOT NULL COMMENT '创建时间',
  `updatetime` varchar(20) NOT NULL COMMENT '更新时间',
  `status` varchar(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`bizid`),
  KEY `index2` (`updatetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值流水表';

/*Data for the table `cf_currbusilog` */

insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpIesL168PhI','13337459638','yd','hf','qg','20','','900011','PpI66LInUMKS','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160627131300','20160627163000','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpIesXNcQSbC','13337459638','yd','ll','qg','50M','','900022','PpI6Ab0hR6eY','WS10100','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160627131300','20160629150000','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpP59McCKyqK','13337459638','yd','hf','qg','20','','900011','PpP50rODEtMo','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160628153500','20160628153500','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPAgktO3LSN','13337459638','yd','ll','qg','50M','','900022','PpPAfynRb8FZ','CF000000','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160628155700','20160628155700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPAwMRb2OHo','13337459638','yd','ll','qg','50M','','900022','PpPAk2j9xHFz','CF000000','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160628155800','20160628155800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPBwnORbI7O','13337459638','yd','hf','qg','20','','900011','PpPBl4SAQW2L','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160628160200','20160628160200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPBww2ufxQR','13337459638','yd','ll','qg','50M','','900022','PpPBqe35DjUq','CF000000','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160628160200','20160628160200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPFDhnPv4jy','13337459638','yd','hf','qg','20','','900011','PpPF6VL6yuLl','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160628161500','20160628161500','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPIUdcwZcDg','13337459638','yd','hf','qg','20','','900011','PpPIPJU8j4QH','1001','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160628162800','20160628162800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPJV4Bvk8mH','13337459638','yd','hf','qg','20','','900011','PpPJFwPA5HR8','1001','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160628163200','20160628163200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPNHBsMDbVg','13337459638','yd','ll','qg','50M','PpPNFheNySbc','900022','PpPN5Xv4esNF','WS10100','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160628164700','20160628164706','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYba8DI23Z','11111111111','yd','hf','qg','20','PpPYa2wRtXXD','900011','PpPYXQd0Xii5','1','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160628173200','20160628173200','4');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYM5UQdaSb','13025166548','yd','hf','44','10','','','PpI6dEcBrkNN','CF270005','','','','PpHsaw7Uwpmu','20160628173100','20160628173100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYM9T7pIbn','13325166548','yd','hf','37','10','','','PpI6dEd0YXMC','CF270005','','','','PpHsaw7Uwpmu','20160628173100','20160628173100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYMCd8Fflj','13021166548','yd','hf','11','10','','','PpI6dEdPDoJ0','CF270005','','','','PpHsaw7Uwpmu','20160628173100','20160628173100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYMGTYCRiV','13025166448','yd','hf','44','10','','','PpI6dEe0LWSd','CF270005','','','','PpHsaw7Uwpmu','20160628173101','20160628173101','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYMJlbu89q','13025136548','yd','hf','44','10','','','PpI6dEektGoh','CF270005','','','','PpHsaw7Uwpmu','20160628173101','20160628173101','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYMMwN5C58','13025566548','yd','hf','44','10','','','PpI6dEeNesZf','CF270005','','','','PpHsaw7Uwpmu','20160628173101','20160628173101','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPYrBVo5FPS','11111111111','yd','ll','qg','50M','PpPYpdbzaimC','900022','PpPYdKYsj7HQ','WS10100','9.880','PpHy74Uyljov','23.000','PpPXrDUUXZSr','20160628173300','20160628173306','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6nurWl83','13025166548','yd','hf','44','99','','','PpPYmHqWVfEb','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6oxOh3tc','13021166548','yd','hf','11','99','','','PpPYmHrJhnLI','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6q8uqi38','13025166448','yd','hf','44','99','','','PpPYmHrpvRji','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6r6EhWP4','13325166548','yd','hf','37','99','','','PpPYmHrXSWKl','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6rsIDpTd','13025136548','yd','hf','44','99','','','PpPYmHryhNyq','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpPZ6snZkIU8','13025566548','yd','hf','44','99','','','PpPYmHstPHAH','CF270005','','','','PpPXrDUUXZSr','20160628173400','20160628173400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTkfQImTE2n','13025166548','yd','hf','44','99','','','PpTkY35Vc9RP','CF270005','','','','PpPXrDUUXZSr','20160629104500','20160629104500','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTpxClL7OEw','13021166548','yd','hf','11','99','','','PpTpoFM76gFb','CF270005','','','','PpPXrDUUXZSr','20160629110600','20160629110600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTqCo97p5Oi','18811796666','yd','hf','qg','20','','900011','PpTq8re4wtf9','CF000000','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629110700','20160629110700','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTsyVOyn17F','13021166548','yd','hf','11','99','','','PpTst2Y3tTzW','CF270005','','','','PpPXrDUUXZSr','20160629111800','20160629111800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTtjKKuYOPf','13021166548','yd','hf','11','99','','','PpTtWEoZ3WLy','CF270005','','','','PpPXrDUUXZSr','20160629112100','20160629112100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTujlTrLPWz','15555555555','yd','hf','qg','20','','900011','PpTueMgOtyen','CF000000','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629112500','20160629112500','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTvkCPFzhhS','13021166548','yd','hf','11','99','','','PpTvi0VLStFL','CF270005','','','','PpPXrDUUXZSr','20160629112900','20160629112900','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTvUaOFT4Cc','13021166548','yd','hf','11','99','','','PpTvM0DQ97AQ','CF270005','','','','PpPXrDUUXZSr','20160629112800','20160629112800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTwFPghDMtC','13021166548','yd','hf','11','99','','','PpTw6wL9GkfK','CF270005','','','','PpPXrDUUXZSr','20160629113100','20160629113100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpTx0FJ6aJKc','13021166548','yd','hf','11','99','','','PpTws0wD1lr3','CF270005','','','','PpPXrDUUXZSr','20160629113400','20160629113400','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpU32qumUjqd','13021166548','yd','hf','11','99','','','PpU2ybE5GP1F','CF270005','','','','PpPXrDUUXZSr','20160629115800','20160629115800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpU3ISAKoYGp','16666666666','yd','hf','qg','20','','900011','PpU3BeXImhAn','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160629115900','20160629115900','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUDs1QUglji','18811791111','yd','hf','qg','20','','900011','PpUDkaUp6Iiz','CF000000','19.800','PpHsvfrpHNbR','21.000','PpHsaw7Uwpmu','20160629124100','20160629124100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUDsN7sbClu','18811111111','yd','ll','qg','50M','','900022','PpUDp2PF5FLM','CF000000','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160629124101','20160629124101','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUE7clzFfn1','15555555555','yd','hf','qg','20','','900011','PpUDvDN3uWmk','CF000000','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629124200','20160629124200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUE7uo8p227','15555555555','yd','ll','qg','50M','','900022','PpUE2Taeduj0','CF000000','9.880','PpHy74Uyljov','23.000','PpPXrDUUXZSr','20160629124201','20160629124201','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUEcr7zNfAd','13333333333','yd','hf','qg','20','','900011','PpUEO5xSQvtM','CF000000','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629124400','20160629124400','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUH8wkY3wBZ','15555555555','yd','ll','qg','50M','','900022','PpUH7sRGuj7l','WS10100','9.880','PpHy74Uyljov','23.000','PpPXrDUUXZSr','20160629125400','20160629150000','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUHOXtWIudI','15555555555','yd','ll','qg','50M','','900022','PpUHC5VD9ASz','WS10100','9.880','PpHy74Uyljov','23.000','PpPXrDUUXZSr','20160629125500','20160629150000','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUI9NJdalkR','15555555555','yd','hf','qg','20','','900011','PpUHxSEimQMk','CF000000','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629125800','20160629125800','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUJ9oWkKzgo','18811111111','yd','ll','qg','50M','','900022','PpUJ5SzXImKI','WS10100','9.880','PpHy74Uyljov','12.000','PpHsaw7Uwpmu','20160629130200','20160629150000','1');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKfSIvIHSf','13025566548','yd','ll','44','10M','','','PpUKTKy8lvBT','CF270005','','','','PpPXrDUUXZSr','20160629130800','20160629130800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKfTnkFnoK','13025136548','yd','hf','44','10','','','PpUKUocvKdHX','CF270005','','','','PpPXrDUUXZSr','20160629130800','20160629130800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKfUz5847q','13025166448','yd','hf','44','10','','','PpUKVnZY4hvz','CF270005','','','','PpPXrDUUXZSr','20160629130800','20160629130800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKfWU5zpby','13325166548','yd','hf','37','10','','','PpUKWonN8gT4','CF270005','','','','PpPXrDUUXZSr','20160629130800','20160629130800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKfXQ6Zliu','13021166548','yd','hf','11','10','','','PpUKY4af9F77','CF270005','','','','PpPXrDUUXZSr','20160629130800','20160629130800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKPrYgJAdn','13025166548','yd','hf','44','10','','','PpUKISV3BqBg','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKPtVxsNeU','13021166548','yd','hf','11','10','','','PpUKISW5L2YL','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKPwUNjxuG','13325166548','yd','hf','37','10','','','PpUKISWmFqeV','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKPxcwDiYs','13025166448','yd','hf','44','10','','','PpUKISWnKpGp','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKPzMrhgFK','13025136548','yd','hf','44','10','','','PpUKISWS8nN3','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUKQ28qFDhd','13025566548','yd','ll','44','10M','','','PpUKISX0Hwun','CF270005','','','','PpPXrDUUXZSr','20160629130700','20160629130700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpULft6kT0ZR','13021166548','yd','hf','11','10','','','PpULcsha73vr','CF270005','','','','PpPXrDUUXZSr','20160629131200','20160629131200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpULfv7PNvcH','13025166448','yd','hf','44','10','','','PpULQpz53yGV','CF270005','','','','PpPXrDUUXZSr','20160629131200','20160629131200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpULQHlxASub','13025166548','yd','hf','44','10','','','PpULN83wTdWD','CF270005','','','','PpPXrDUUXZSr','20160629131100','20160629131100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpULQJlTpAtw','13025566548','yd','ll','44','10M','','','PpULOUJfEAVx','CF270005','','','','PpPXrDUUXZSr','20160629131100','20160629131100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpULQLCLfuW1','13025136548','yd','hf','44','10','','','PpULPnCZblxn','CF270005','','','','PpPXrDUUXZSr','20160629131100','20160629131100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUvAjRcFNPb','13025166448','yd','hf','44','10','','','PpUv1Al57Mgs','CF270005','','','','PpPXrDUUXZSr','20160629153300','20160629153300','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpUwQlgmize7','13025166448','yd','hf','44','10','','','PpUwJ4cl2Xim','CF270005','','','','PpPXrDUUXZSr','20160629153800','20160629153800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVaCyAvVoLB','13025566548','yd','ll','44','10M','','','PpVa8eCTBi6A','CF270005','','','','PpHsaw7Uwpmu','20160629181600','20160629181600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVaiAjzQqdC','13025566548','yd','ll','44','10M','','','PpVaWgDoz8W7','CF270005','','','','PpHsaw7Uwpmu','20160629181800','20160629181800','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVaSYttx36W','13025566548','yd','ll','44','10M','','','PpVaNVxp78xo','CF270005','','','','PpHsaw7Uwpmu','20160629181700','20160629181700','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVSegpXqyAH','13025166448','yd','hf','44','10','','','PpVSPgE5RI1X','CF270005','','','','PpPXrDUUXZSr','20160629174600','20160629174600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVTf7p1VNPe','13025166448','yd','hf','44','10','','','PpVTZMeiH9cF','CF270005','','','','PpPXrDUUXZSr','20160629175000','20160629175000','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWgQf94gdp','13025566548','yd','ll','44','10M','','','PpVWate33x8a','CF270005','','','','PpHsaw7Uwpmu','20160629180200','20160629180200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQpNpvYyD','13021166548','yd','hf','11','10','','','PpVWJgdWzPdD','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQqzWTkPH','13025166548','yd','hf','44','10','','','PpVWJgdZAyOj','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQsZ8YP5k','13025166448','yd','hf','44','10','','','PpVWJgetUkzy','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQuE7HMYD','13325166548','yd','hf','37','10','','','PpVWJgewmEOg','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQvIUudN9','13025566548','yd','ll','44','10M','','','PpVWJgflY3eP','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQwC09VVq','13025136548','yd','hf','44','10','','','PpVWJgfyYCxu','CF270005','','','','PpHsaw7Uwpmu','20160629180100','20160629180100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWQxKYON4n','18888888888','yd','hf','qg','20','PpVWPcRoqe5M','900011','PpVWFgUeKgSo','OF1','19.800','PpHsvfrpHNbR','22.000','PpPXrDUUXZSr','20160629180100','20160629180100','4');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWw2fx0FS1','13025566548','yd','ll','44','10M','','','PpVWgbPl9KkK','CF270005','','','','PpHsaw7Uwpmu','20160629180300','20160629180300','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVWw5CpIc37','13025566548','yd','ll','44','10M','','','PpVWoPmGw8uD','CF270005','','','','PpHsaw7Uwpmu','20160629180300','20160629180300','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVYhImo77Cr','13025566548','yd','ll','44','10M','','','PpVYgdxFo1DA','CF270005','','','','PpHsaw7Uwpmu','20160629181000','20160629181000','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVYwuC4uqoY','13025566548','yd','ll','44','10M','','','PpVYhmdJHni7','CF270005','','','','PpHsaw7Uwpmu','20160629181100','20160629181100','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVZCWNxBwFZ','13025566548','yd','ll','44','10M','','','PpVZ7AtUY2V2','CF270005','','','','PpHsaw7Uwpmu','20160629181200','20160629181200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PpVZS7fNJ09R','13025566548','yd','ll','44','10M','','','PpVZJSh0SmCT','CF270005','','','','PpHsaw7Uwpmu','20160629181300','20160629181300','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6XlLNcabj','13025166548',NULL,'hf','44','10','','','PqG2U9A9v3DL','CF270005','','','','PpHsaw7Uwpmu','20160707171258','20160707171258','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6Xw8G6pL8','13912341234',NULL,'hf','32','10','','','PqG2U9Bbuu09','CF270005','','','','PpHsaw7Uwpmu','20160707171258','20160707171258','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6Y0RyoY74','17712341234',NULL,'hf','32','10','','','PqG2U9BpV9Jr','CF270005','','','','PpHsaw7Uwpmu','20160707171258','20160707171258','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6Y4u0uHf7','18812341234',NULL,'hf','53','10','','','PqG2U9BTSRx0','CF270005','','','','PpHsaw7Uwpmu','20160707171259','20160707171259','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6Y9vcHtYl','13255551234',NULL,'hf','37','10','','','PqG2U9BZbj0s','CF270005','','','','PpHsaw7Uwpmu','20160707171259','20160707171259','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG6YFB0H196','15213241234',NULL,'ll','50','10M','','','PqG2U9CI3Q3V','CF270005','','','','PpHsaw7Uwpmu','20160707171259','20160707171259','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8ommPBU2T','18812341234','yd','hf','53','10','','','PqG8Uk4LCTq8','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8ooZyhFb4','13255551234','lt','hf','37','10','','','PqG8Uk4NQl7b','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8opZuBjVu','13025166548','lt','hf','44','10','','','PqG8Uk4yGa7C','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8orBqR7jW','13912341234','yd','hf','32','10','','','PqG8Uk57heJq','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8os76DVOW','17712341234','dx','hf','32','10','','','PqG8Uk5FN5f5','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqG8ot9WOUjL','15213241234','yd','ll','50','10M','','','PqG8Uk5XaJXB','CF270005','','','','PpHsaw7Uwpmu','20160707172200','20160707172200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOGxtuq4nL','13812345670',NULL,'hf','32','50','','','PqdYW4CWhjQi','CF270005','','','','PpHsaw7Uwpmu','20160712131900','20160712131900','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOH0o5a5BX','13812345671',NULL,'hf','32','50','','','PqdYW4D7DR5Q','CF270005','','','','PpHsaw7Uwpmu','20160712131900','20160712131900','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOH3okSzwa','13812345674',NULL,'hf','32','50','','','PqdYW4DtaTji','CF270005','','','','PpHsaw7Uwpmu','20160712131901','20160712131901','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOH75QMErS','13812345673',NULL,'hf','32','50','','','PqdYW4DXnO5k','CF270005','','','','PpHsaw7Uwpmu','20160712131901','20160712131901','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHA6rEd3r','13812345672',NULL,'hf','32','50','','','PqdYW4DYCdyL','CF270005','','','','PpHsaw7Uwpmu','20160712131901','20160712131901','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHDGTcRdo','13812345678',NULL,'hf','32','50','','','PqdYW4E43OsP','CF270005','','','','PpHsaw7Uwpmu','20160712131901','20160712131901','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHGRXq8HV','13812345676',NULL,'hf','32','50','','','PqdYW4EG5mbY','CF270005','','','','PpHsaw7Uwpmu','20160712131901','20160712131901','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHJQfSxjS','13812345675',NULL,'hf','32','50','','','PqdYW4ELMapK','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHMYj5uJp','13812345677',NULL,'hf','32','50','','','PqdYW4EMM72E','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHPXlwf4w','13812345679',NULL,'hf','32','50','','','PqdYW4FkD1xW','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHQTOUBiM','13025166548',NULL,'hf','44','10','','','PqhxnEo2o7us','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHRN9S70k','18812341234',NULL,'hf','53','10','','','PqhxnEpd253D','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHSINHVU5','13255551234',NULL,'hf','37','10','','','PqhxnEpZNZyg','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHT745opp','13912341234',NULL,'hf','32','10','','','PqhxnEqBQ4fV','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHU4qqJw3','17712341234',NULL,'hf','32','10','','','PqhxnEqu7QLI','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiOHV2KcKhs','15213241234',NULL,'ll','50','10M','','','PqhxnErRHIQZ','CF270005','','','','PpHsaw7Uwpmu','20160712131902','20160712131902','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1dG0mvW8','13025166548','lt','hf','44','10','','','PqiOkS4hTLsV','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1f4wA53a','13255551234','lt','hf','37','10','','','PqiOkS4KlzC2','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1gCU8IHT','18812341234','yd','hf','53','10','','','PqiOkS5FtYSk','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1hIcr4EM','17712341234','dx','hf','32','10','','','PqiOkS5udmve','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1iJXdaT6','13912341234','yd','hf','32','10','','','PqiOkS6252m7','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqiP1jHpRfl2','15213241234','yd','ll','50','10M','','','PqiOkS6FV5PS','CF270005','','','','PpHsaw7Uwpmu','20160712132200','20160712132200','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK2kmIgEQJ0','13255551234','lt','hf','37','10','','','PqK2bpBSRMg3','CF270005','','','','PpHsaw7Uwpmu','20160708092300','20160708092300','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3VbPNo2w1','18812341234','yd','hf','53','10','','','PqK3BraEBwdo','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3VdFrS0Uc','13912341234','yd','hf','32','10','','','PqK3BrajeUy4','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3Veqf8W87','17712341234','dx','hf','32','10','','','PqK3BraNeB24','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3Vfw76yUN','13255551234','lt','hf','37','10','','','PqK3BrazVDCb','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3VhPPPEm4','15213241234','yd','ll','50','10M','','','PqK3BrbVci5C','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK3ViSU9h2I','13025166548','lt','hf','44','10','','','PqK3BrZwrMOL','CF270005','','','','PpHsaw7Uwpmu','20160708092600','20160708092600','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WC5nj8ID','13255551234','lt','hf','37','10','','','PqK4GHJdp0qj','CF270005','','','','PpHsaw7Uwpmu','20160708093000','20160708093000','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WGE4L6KK','13025166548','lt','hf','44','10','','','PqK4GHJlx9ta','CF270005','','','','PpHsaw7Uwpmu','20160708093000','20160708093000','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WKrBAF8c','18812341234','yd','hf','53','10','','','PqK4GHKLNunv','CF270005','','','','PpHsaw7Uwpmu','20160708093001','20160708093001','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WOSPgQ9X','17712341234','dx','hf','32','10','','','PqK4GHKVz7Ie','CF270005','','','','PpHsaw7Uwpmu','20160708093001','20160708093001','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WS66AcrK','13912341234','yd','hf','32','10','','','PqK4GHLvhM3F','CF270005','','','','PpHsaw7Uwpmu','20160708093001','20160708093001','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqK4WW01T8sz','15213241234','yd','ll','50','10M','','','PqK4GHLyY3nW','CF270005','','','','PpHsaw7Uwpmu','20160708093001','20160708093001','2');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqLsXvLqgMmP','18811791223','yd','hf','qg','20','','900011','PqLsOmyZFGzI','CF000000','19.800','PpHsvfrpHNbR','19.900','PpHsaw7Uwpmu','20160708165500','20160708165500','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqLsY4TXYApQ','18811791223','yd','hf','qg','20','','900011','PqLsTUCJo1px','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708165500','20160708165500','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM3ciUrOdYs','18811796987','yd','hf','qg','20','','900011','PqM3asmPmczi','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708173900','20160708173900','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM3d0MyMgEv','18811797896','yd','hf','qg','20','','900011','PqM3XEH85DDy','CF000000','19.800','PpHsvfrpHNbR','19.900','PpHsaw7Uwpmu','20160708173901','20160708173901','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM3N7EEGQl6','18811791555','yd','hf','qg','20','','900011','PqLy403t0eIN','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708173800','20160708173800','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM3NnHeg0Qu','18811798888','yd','hf','qg','20','','900011','PqM2oigWsFGf','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708173802','20160708173802','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM3NURxefyb','18811799999','yd','hf','qg','20','','900011','PqM2lUbiQ5zH','CF000000','19.800','PpHsvfrpHNbR','19.900','PpHsaw7Uwpmu','20160708173801','20160708173801','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM5daBLWr62','18811794567','yd','hf','11','20','','900011','PqM4Ew4G40YR','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708174700','20160708174700','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM68nmlQV9p','13916789123','yd','hf','31','20','','900011','PqM5u0QzxQCA','CF000000','19.800','PpHsvfrpHNbR','19.900','PpHsaw7Uwpmu','20160708174900','20160708174900','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM69DreiGTD','13917681234','yd','hf','31','20','','900011','PqM5zBKdNBVz','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708174901','20160708174901','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM6kr2d0wJ5','17719614567','yd','hf','61','20','','900011','PqM6d3iKwtjq','CF000000','19.800','PpHsvfrpHNbR','19.990','PpPXrDUUXZSr','20160708175409','20160708175409','3');
insert  into `cf_currbusilog`(`bizid`,`phone`,`ispno`,`ipstype`,`province`,`facevalue`,`orderid`,`cporderno`,`systransno`,`responsecode`,`spprice`,`spno`,`apprice`,`apno`,`createtime`,`updatetime`,`status`) values ('PqM7W3ikuEAt','17719611234','yd','hf','61','20','','900011','PqM6ZOLD1dxf','CF000000','19.800','PpHsvfrpHNbR','19.900','PpHsaw7Uwpmu','20160708175501','20160708175501','3');

/*Table structure for table `cf_currbusilog_his` */

DROP TABLE IF EXISTS `cf_currbusilog_his`;

CREATE TABLE `cf_currbusilog_his` (
  `bizid` varchar(20) NOT NULL COMMENT '流水id',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `ispno` varchar(10) NOT NULL COMMENT '运营商编号',
  `ipstype` varchar(2) NOT NULL COMMENT '套餐类型',
  `province` varchar(4) DEFAULT NULL COMMENT '省份',
  `facevalue` varchar(16) NOT NULL COMMENT '面值',
  `orderid` varchar(32) DEFAULT NULL COMMENT '供应商订单编号',
  `cporderno` varchar(50) NOT NULL COMMENT 'cp订购编号',
  `systransno` varchar(20) NOT NULL COMMENT '接入者订单编号',
  `responsecode` varchar(10) DEFAULT NULL COMMENT '操作结果编码',
  `spprice` varchar(16) NOT NULL COMMENT '供应商报价',
  `spno` varchar(32) NOT NULL COMMENT '供应商报价编号',
  `apprice` varchar(16) NOT NULL COMMENT '接入者报价',
  `apno` varchar(32) NOT NULL COMMENT '接入者报价编号·',
  `createtime` varchar(20) NOT NULL COMMENT '创建时间',
  `updatetime` varchar(20) NOT NULL COMMENT '更新时间',
  `status` varchar(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`bizid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值流水历史表';

/*Data for the table `cf_currbusilog_his` */

/*Table structure for table `cf_customer` */

DROP TABLE IF EXISTS `cf_customer`;

CREATE TABLE `cf_customer` (
  `id` varchar(32) NOT NULL COMMENT '客户id',
  `cname` varchar(120) NOT NULL COMMENT '客户名称',
  `mname` varchar(40) DEFAULT NULL COMMENT '联系人名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(40) DEFAULT NULL COMMENT '联系人邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/*Data for the table `cf_customer` */

insert  into `cf_customer`(`id`,`cname`,`mname`,`phone`,`email`) values ('PpHsBuaOyYDz','中国民生银行','锅子','13231231211','1111111@vv.com');
insert  into `cf_customer`(`id`,`cname`,`mname`,`phone`,`email`) values ('PqLMORzxiXaI','客户001','孙子兵法','','11@11.com');
insert  into `cf_customer`(`id`,`cname`,`mname`,`phone`,`email`) values ('PqLMYhsYVNmX','客户002','','','11@1111.com');

/*Table structure for table `cf_ofbill` */

DROP TABLE IF EXISTS `cf_ofbill`;

CREATE TABLE `cf_ofbill` (
  `cpno` varchar(32) NOT NULL COMMENT 'CP流水号',
  `spno` varchar(32) NOT NULL COMMENT 'SP订单号',
  `num` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `size` varchar(16) DEFAULT NULL COMMENT '商品数量',
  `phone` varchar(30) DEFAULT NULL COMMENT '充值账号',
  `oprice` varchar(16) DEFAULT NULL COMMENT '订单金额',
  `otime` varchar(20) DEFAULT NULL COMMENT '订单时间',
  `status` varchar(6) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`cpno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='欧飞对账表';

/*Data for the table `cf_ofbill` */

/*Table structure for table `cf_phonenoinfo` */

DROP TABLE IF EXISTS `cf_phonenoinfo`;

CREATE TABLE `cf_phonenoinfo` (
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `supplier` varchar(32) NOT NULL COMMENT '运营商',
  `province` varchar(32) NOT NULL COMMENT '归属地',
  `city` varchar(32) NOT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='215手机归属地';

/*Data for the table `cf_phonenoinfo` */

insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('12312312312','联通','-','-');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13021012885','联通','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13025166548','联通','广东','广州');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13255551234','联通','山东','青岛');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13312312312','电信','贵州','遵义');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13333333333','电信','河北','秦皇岛');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345670','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345671','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345672','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345673','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345674','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345675','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345676','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345677','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345678','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13812345679','移动','江苏','连云港');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13912341234','移动','江苏','常州');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13916789123','移动','上海','上海');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('13917681234','移动','上海','上海');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15210123445','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15210555553','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15210555555','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15210961234','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15213241234','移动','重庆','重庆');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15215555553','移动','安徽','马鞍山');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15234567890','移动','山西','长治');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15255555553','移动','安徽','合肥');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('15255555555','移动','安徽','合肥');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('17712341234','电信','江苏','常州');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('17719611234','电信','陕西','宝鸡');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('17719614567','电信','陕西','宝鸡');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18310390100','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811111111','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811231234','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811561234','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791111','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791112','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791113','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791114','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791221','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791222','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791223','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811791234','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18811794567','移动','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18812341234','移动','云南','曲靖');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18845671234','移动','黑龙江','黑河');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18911791111','电信','北京','北京');
insert  into `cf_phonenoinfo`(`phone`,`supplier`,`province`,`city`) values ('18911791223','电信','北京','北京');

/*Table structure for table `cf_province` */

DROP TABLE IF EXISTS `cf_province`;

CREATE TABLE `cf_province` (
  `pno` varchar(4) NOT NULL,
  `pname` varchar(60) NOT NULL,
  `porder` int(4) NOT NULL,
  PRIMARY KEY (`pno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省份表';

/*Data for the table `cf_province` */

insert  into `cf_province`(`pno`,`pname`,`porder`) values ('11','北京',11);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('12','天津',12);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('13','河北',13);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('14','山西',14);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('15','内蒙古自治区',15);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('21','辽宁',21);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('22','吉林',22);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('23','黑龙江',23);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('31','上海',31);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('32','江苏',32);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('33','浙江',33);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('34','安徽',34);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('35','福建',35);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('36','江西',36);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('37','山东',37);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('41','河南',41);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('42','湖北',42);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('43','湖南',43);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('44','广东',44);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('45','广西壮族自治区',45);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('46','海南',46);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('50','重庆',50);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('51','四川',51);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('52','贵州',52);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('53','云南',53);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('54','西藏自治区',54);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('61','陕西',61);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('62','甘肃',62);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('63','青海',63);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('64','宁夏回族自治区',64);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('65','新疆维吾尔自治区',65);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('71','台湾',71);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('81','香港特别行政区',81);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('82','澳门特别行政区',82);
insert  into `cf_province`(`pno`,`pname`,`porder`) values ('qg','全国',1);

/*Table structure for table `cf_recharge` */

DROP TABLE IF EXISTS `cf_recharge`;

CREATE TABLE `cf_recharge` (
  `id` varchar(32) NOT NULL COMMENT '充值id',
  `ispno` varchar(32) NOT NULL COMMENT '运营商',
  `isptype` varchar(2) NOT NULL COMMENT '套餐类型',
  `facevalue` varchar(16) NOT NULL COMMENT '面值',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `bizid` varchar(32) DEFAULT NULL COMMENT '流水号',
  `status` varchar(2) NOT NULL COMMENT '发送状态',
  `province` varchar(4) DEFAULT NULL COMMENT '省份',
  `cid` varchar(32) NOT NULL COMMENT '客户',
  `acid` varchar(32) NOT NULL COMMENT '接入者',
  `source` varchar(5) DEFAULT NULL COMMENT '充值来源',
  `cbstatus` varchar(5) DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  `orderid` varchar(32) NOT NULL COMMENT '接入者订单id',
  `originalbizid` varchar(32) DEFAULT NULL COMMENT '原流水号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`acid`,`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='213单笔充值表';

/*Data for the table `cf_recharge` */

insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpI66LIWpMqf','yd','hf','20','13337459638','PpIesL168PhI','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpI66LInUMKS',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpI6Ab0EgESF','yd','ll','50M','13337459638','PpIesXNcQSbC','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpI6Ab0hR6eY',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpP50rO4ktRU','yd','hf','20','13337459638','PpP59McCKyqK','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpP50rODEtMo','PpIesL168PhI');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPAfyn6CC8A','yd','ll','50M','13337459638','PpPAgktO3LSN','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPAfynRb8FZ','PpIesXNcQSbC');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPAk2jy7MkC','yd','ll','50M','13337459638','PpPAwMRb2OHo','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPAk2j9xHFz','PpIesXNcQSbC');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPBl4S9BUx4','yd','hf','20','13337459638','PpPBwnORbI7O','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPBl4SAQW2L','PpIesL168PhI');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPBqe3xEYqV','yd','ll','50M','13337459638','PpPBww2ufxQR','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPBqe35DjUq','PpIesXNcQSbC');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPF6VLZnhPu','yd','hf','20','13337459638','PpPFDhnPv4jy','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPF6VL6yuLl','PpIesL168PhI');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPIPJUGpnof','yd','hf','20','13337459638','PpPIUdcwZcDg','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPIPJU8j4QH','PpIesL168PhI');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPJFwPDFnz8','yd','hf','20','13337459638','PpPJV4Bvk8mH','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPJFwPA5HR8','PpIesL168PhI');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPN5XvYUPdF','yd','ll','50M','13337459638','PpPNHBsMDbVg','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','PpHpRxbS73Gg','PpPN5Xv4esNF','PpIesXNcQSbC');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPYdKYx5HSn','yd','ll','50M','11111111111','PpPYrBVo5FPS','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','PpHpRxbS73Gg','PpPYdKYsj7HQ',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpPYXQd0LRTT','yd','hf','20','11111111111','PpPYba8DI23Z','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','PpHpRxbS73Gg','PpPYXQd0Xii5',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTkY350IyZk','yd','hf','99','13025166548','PpTkfQImTE2n','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTkY35Vc9RP','PpPZ6nurWl83');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTpoFMLUYJe','yd','hf','99','13021166548','PpTpxClL7OEw','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTpoFM76gFb','PpPZ6oxOh3tc');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTq8reMJggl','yd','hf','20','18811796666','PpTqCo97p5Oi','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTq8re4wtf9',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTst2YOhmIo','yd','hf','99','13021166548','PpTsyVOyn17F','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTst2Y3tTzW','PpTpxClL7OEw');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTtWEoET9Jz','yd','hf','99','13021166548','PpTtjKKuYOPf','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTtWEoZ3WLy','PpTpxClL7OEw');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTueMgM5SCC','yd','hf','20','15555555555','PpTujlTrLPWz','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTueMgOtyen',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTvi0VXcfCb','yd','hf','99','13021166548','PpTvkCPFzhhS','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTvi0VLStFL','PpTtjKKuYOPf');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTvM0DuwBS2','yd','hf','99','13021166548','PpTvUaOFT4Cc','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTvM0DQ97AQ','PpTtjKKuYOPf');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTw6wLP7nqM','yd','hf','99','13021166548','PpTwFPghDMtC','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTw6wL9GkfK','PpTvUaOFT4Cc');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpTws0w21GsB','yd','hf','99','13021166548','PpTx0FJ6aJKc','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpTws0wD1lr3','PpTwFPghDMtC');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpU2ybEWiqv3','yd','hf','99','13021166548','PpU32qumUjqd','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpU2ybE5GP1F','PpTx0FJ6aJKc');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpU3BeX0eEum','yd','hf','20','16666666666','PpU3ISAKoYGp','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpU3BeXImhAn',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUDkaUesejv','yd','hf','20','18811791111','PpUDs1QUglji','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpUDkaUp6Iiz',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUDp2P2pUdB','yd','ll','50M','18811111111','PpUDsN7sbClu','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpUDp2PF5FLM',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUDvDNF1mIo','yd','hf','20','15555555555','PpUE7clzFfn1','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUDvDN3uWmk',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUE2TagCiOh','yd','ll','50M','15555555555','PpUE7uo8p227','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUE2Taeduj0',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUEO5x1G7E0','yd','hf','20','13333333333','PpUEcr7zNfAd','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUEO5xSQvtM',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUH7sR4eT9b','yd','ll','50M','15555555555','PpUH8wkY3wBZ','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUH7sRGuj7l','PpUE7uo8p227');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUHC5V9UDoT','yd','ll','50M','15555555555','PpUHOXtWIudI','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUHC5VD9ASz','PpUE7uo8p227');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUHxSEGX3ZD','yd','hf','20','15555555555','PpUI9NJdalkR','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUHxSEimQMk','PpUE7clzFfn1');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUJ5SzGZEoz','yd','ll','50M','18811111111','PpUJ9oWkKzgo','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpUJ5SzXImKI','PpUDsN7sbClu');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUKTKyjoFyw','yd','ll','10M','13025566548','PpUKfSIvIHSf','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUKTKy8lvBT','PpUKQ28qFDhd');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUKUocBmhHM','yd','hf','10','13025136548','PpUKfTnkFnoK','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUKUocvKdHX','PpUKPzMrhgFK');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUKVnZm8Wn5','yd','hf','10','13025166448','PpUKfUz5847q','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUKVnZY4hvz','PpUKPxcwDiYs');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUKWonq38YJ','yd','hf','10','13325166548','PpUKfWU5zpby','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUKWonN8gT4','PpUKPwUNjxuG');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUKY4aCWjYf','yd','hf','10','13021166548','PpUKfXQ6Zliu','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUKY4af9F77','PpUKPtVxsNeU');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpULcshzNsnX','yd','hf','10','13021166548','PpULft6kT0ZR','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpULcsha73vr','PpUKPtVxsNeU');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpULN83vpbth','yd','hf','10','13025166548','PpULQHlxASub','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpULN83wTdWD','PpUKPrYgJAdn');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpULOUJFQxpH','yd','ll','10M','13025566548','PpULQJlTpAtw','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpULOUJfEAVx','PpUKQ28qFDhd');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpULPnCMhWpo','yd','hf','10','13025136548','PpULQLCLfuW1','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpULPnCZblxn','PpUKPzMrhgFK');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpULQpz9guf9','yd','hf','10','13025166448','PpULfv7PNvcH','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpULQpz53yGV','PpUKPxcwDiYs');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUv1AlseLBj','yd','hf','10','13025166448','PpUvAjRcFNPb','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUv1Al57Mgs','PpULfv7PNvcH');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpUwJ4cSaIrm','yd','hf','10','13025166448','PpUwQlgmize7','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpUwJ4cl2Xim','PpUvAjRcFNPb');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVa8eCzHrfx','yd','ll','10M','13025566548','PpVaCyAvVoLB','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVa8eCTBi6A','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVaNVxuy4B2','yd','ll','10M','13025566548','PpVaSYttx36W','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVaNVxp78xo','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVaWgDAfnRh','yd','ll','10M','13025566548','PpVaiAjzQqdC','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVaWgDoz8W7','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVSPgEiJ7IM','yd','hf','10','13025166448','PpVSegpXqyAH','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpVSPgE5RI1X','PpUvAjRcFNPb');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVTZMeJbhqb','yd','hf','10','13025166448','PpVTf7p1VNPe','33',NULL,'PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpVTZMeiH9cF','PpVSegpXqyAH');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVWatem4PGc','yd','ll','10M','13025566548','PpVWgQf94gdp','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVWate33x8a','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVWFgUx5jTk','yd','hf','20','18888888888','PpVWQxKYON4n','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PpVWFgUeKgSo',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVWgbPtkgof','yd','ll','10M','13025566548','PpVWw2fx0FS1','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVWgbPl9KkK','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVWoPm7MoKS','yd','ll','10M','13025566548','PpVWw5CpIc37','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVWoPmGw8uD','PpVWgQf94gdp');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVYgdxIL5ud','yd','ll','10M','13025566548','PpVYhImo77Cr','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVYgdxFo1DA','PpVWw2fx0FS1');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVYhmdGprpF','yd','ll','10M','13025566548','PpVYwuC4uqoY','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVYhmdJHni7','PpVWw2fx0FS1');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVZ7At59HuN','yd','ll','10M','13025566548','PpVZCWNxBwFZ','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVZ7AtUY2V2','PpVWQvIUudN9');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PpVZJShHpyTE','yd','ll','10M','13025566548','PpVZS7fNJ09R','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PpVZJSh0SmCT','PpVZCWNxBwFZ');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqK2bpBDFsGs','lt','hf','10','13255551234','PqK2kmIgEQJ0','33',NULL,'PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqK2bpBSRMg3','PqG8ooZyhFb4');
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqLsOmyaaZI0','yd','hf','20','18811791223','PqLsXvLqgMmP','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqLsOmyZFGzI',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqLsTUC4IdT9','yd','hf','20','18811791223','PqLsY4TXYApQ','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqLsTUCJo1px',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqLxxOuBquX1','yd','hf','20','18811795555','PqM0r1bI87UM','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqLxxOu3KUvx',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqLy403D3RMI','yd','hf','20','18811791555','PqM3N7EEGQl6','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqLy403t0eIN',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM2lUbuFjAP','yd','hf','20','18811799999','PqM3NURxefyb','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqM2lUbiQ5zH',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM2oigY0KnW','yd','hf','20','18811798888','PqM3NnHeg0Qu','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqM2oigWsFGf',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM3asmyM9DL','yd','hf','20','18811796987','PqM3ciUrOdYs','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqM3asmPmczi',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM3XEHeJb3D','yd','hf','20','18811797896','PqM3d0MyMgEv','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqM3XEH85DDy',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM49pFgaSNj','yd','hf','20','18811794321','PqM4Oppdzp84','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqM49pFLSCB6',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM4Ew3A67S2','yd','hf','20','18811794567','PqM5daBLWr62','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqM4Ew4G40YR',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM5u0QKkBMn','yd','hf','20','13916789123','PqM68nmlQV9p','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqM5u0QzxQCA',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM5zBKRoDTb','yd','hf','20','13917681234','PqM69DreiGTD','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqM5zBKdNBVz',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM6d3iEmCkk','yd','hf','20','17719614567','PqM6kr2d0wJ5','33','qg','PpHsBuaOyYDz','PpPXrDUUXZSr','pf','0','admin','PqM6d3iKwtjq',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqM6ZOLIZoSN','yd','hf','20','17719611234','PqM7W3ikuEAt','33','qg','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','0','admin','PqM6ZOLD1dxf',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqob1gEYqrxT','yd','hf','50','18811791223','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqob1gGyjuAz',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqofsaKBtpIu','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqofsaLljYqO',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqome9WdHvs4','yd','ll','20M','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqome9YbxUc5',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqopiz47xxej','yd','hf','20','18811791221','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqopiz4EHVZs',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqopZeytlDbn','yd','hf','20','18811791223','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqopZey2Fkq2',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoqT2i8cifn','yd','hf','30','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoqT2iotjWz',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoquaHo2IxR','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoquaHnoedK',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqos1EHzq3ii','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqos1EHHrYei',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqosleQIQnnq','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqosleQ3un9e',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqosLwNP4oyf','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqosLwNJuZW9',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqotEH7XzeX8','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqotEH7qnKu0',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqoti1mfxeNX','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqoti1me2kqB',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqotLfTzYd4p','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqotLfTl5gC9',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqotRvzz08LC','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqotRvzGJlRj',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqotYTIxVajf','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqotYTI2Gc3C',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqou7gjLnYcH','yd','hf','30','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqou7gkGWBZ5',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqouKgAfKZQ3','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqouKgA9gfJU',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqov8XodulI0','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqov8Xo1TujZ',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqovx5fbMbMw','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqovx5fPyjCc',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqowh7ZKwULL','yd','ll','20M','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqowh7ZFIhFS',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqox97a51tN7','yd','hf','20','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqox97ad5GgR',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoXfgtqNBEK','yd','ll','30M','18811791113','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoXfgtXrMIX',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoXySDBIPgv','yd','ll','100M','18811791234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoXySEdB059',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoYcfAWnDGE','yd','ll','100M','18811791234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoYcfCRB1XZ',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoYoAqFvs2V','yd','hf','30','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoYoAqCxCec',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoyqD1ok6rA','yd','hf','20','15210555555','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoyqD1wIsj4',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoyqVKoikmx','yd','hf','20','18811791221','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoyqVKBRyH0',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoYTFVVhT2s','yd','ll','100M','18811791234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoYTFXxebuh',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqoz5Tjl0rCt','yd','hf','20','15210555555','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqoz5TjdHNUX',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoZNwnv2JIp','yd','hf','30','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoZNwnwpTTm',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('PqoZoMcJswAA','yd','hf','30','18811791111','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'PqoZoMcwUO7Y',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp0ACivdWmd','yd','hf','20','15210555555','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp0ACk8lX9Q',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp3JXCFD45Y','yd','hf','20','15210555555','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp3JXC14Muz',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp422IIFR9R','yd','hf','20','18811791234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp422JhJwb2',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp4dByapcQU','yd','hf','20','18811794567','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp4dBzzCBqp',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp5ARMfcm6H','yd','hf','20','18811231234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp5ARNbWahD',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp5i2kOhZaj','yd','hf','20','15210123445','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp5i2kmCrw7',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp5INHcKMot','yd','hf','20','18811231234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp5INHX8nWh',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp7bSnSSU8w','yd','hf','20','18811791234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp7bSooFKXS',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp81hJCavap','yd','hf','20','18811231234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp81hKiM7Ty',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp8gkxSQy4k','yd','hf','20','18845671234','','4','23','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp8gkx7D0Pr',NULL);
insert  into `cf_recharge`(`id`,`ispno`,`isptype`,`facevalue`,`phone`,`bizid`,`status`,`province`,`cid`,`acid`,`source`,`cbstatus`,`userid`,`orderid`,`originalbizid`) values ('Pqp8QrYhtMoE','yd','hf','20','18811561234','','4','11','PpHsBuaOyYDz','PpHsaw7Uwpmu','pf','1',NULL,'Pqp8QrY2woc8',NULL);

/*Table structure for table `cf_report` */

DROP TABLE IF EXISTS `cf_report`;

CREATE TABLE `cf_report` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `CID` varchar(32) NOT NULL COMMENT '客户',
  `ACID` varchar(32) NOT NULL COMMENT '接入者id',
  `ISPNO` varchar(32) NOT NULL COMMENT '运营商',
  `TOTALSPPRICE` varchar(20) NOT NULL COMMENT '成本额',
  `TOTALAPPRICE` varchar(20) NOT NULL COMMENT '销售额',
  `PROFIT` varchar(20) NOT NULL COMMENT '利润',
  `QUERYTIME` varchar(20) NOT NULL COMMENT '查询时间',
  `CREATETIME` varchar(20) NOT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表统计';

/*Data for the table `cf_report` */

/*Table structure for table `cf_singleorder` */

DROP TABLE IF EXISTS `cf_singleorder`;

CREATE TABLE `cf_singleorder` (
  `id` varchar(32) NOT NULL COMMENT '充值id',
  `ispno` varchar(32) NOT NULL COMMENT '运营商',
  `isptype` varchar(2) NOT NULL COMMENT '套餐类型',
  `facevalue` varchar(16) NOT NULL,
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `status` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值订单表';

/*Data for the table `cf_singleorder` */

/*Table structure for table `cf_supplier` */

DROP TABLE IF EXISTS `cf_supplier`;

CREATE TABLE `cf_supplier` (
  `id` varchar(32) NOT NULL COMMENT '供应商id',
  `sname` varchar(120) NOT NULL COMMENT '供应商名称',
  `mname` varchar(40) NOT NULL COMMENT '联系人名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(40) DEFAULT NULL COMMENT '联系人邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';

/*Data for the table `cf_supplier` */

insert  into `cf_supplier`(`id`,`sname`,`mname`,`phone`,`email`) values ('PpHsvfrpHNbR','欧飞','锅子','13231231211','1111111@vv.com');
insert  into `cf_supplier`(`id`,`sname`,`mname`,`phone`,`email`) values ('PpHy74Uyljov','网宿','锅子','13231231211','1111111@vv.com');

/*Table structure for table `cf_supplierprice` */

DROP TABLE IF EXISTS `cf_supplierprice`;

CREATE TABLE `cf_supplierprice` (
  `id` varchar(32) NOT NULL COMMENT '报价id',
  `pname` varchar(60) NOT NULL COMMENT '套餐名称',
  `ispno` varchar(32) NOT NULL COMMENT '运营商编号',
  `ipstype` varchar(2) NOT NULL COMMENT '套餐类型',
  `pno` varchar(4) NOT NULL COMMENT '省份',
  `size` varchar(16) NOT NULL COMMENT '面额',
  `sid` varchar(32) NOT NULL COMMENT '供应商编号',
  `price` varchar(16) NOT NULL COMMENT '供应商报价',
  `begintime` varchar(20) NOT NULL COMMENT '开始时间',
  `endtime` varchar(20) NOT NULL COMMENT '结束时间',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `cpordernos` varchar(50) NOT NULL COMMENT 'cp订购编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商报价表';

/*Data for the table `cf_supplierprice` */

insert  into `cf_supplierprice`(`id`,`pname`,`ispno`,`ipstype`,`pno`,`size`,`sid`,`price`,`begintime`,`endtime`,`status`,`cpordernos`) values ('PpHyzByyDbTd','移动-流量-全国-50M','yd','ll','qg','50M','PpHy74Uyljov','9.880','2016-06-27 00:00:00','2016-09-23 23:59:59','kt','900022');
insert  into `cf_supplierprice`(`id`,`pname`,`ispno`,`ipstype`,`pno`,`size`,`sid`,`price`,`begintime`,`endtime`,`status`,`cpordernos`) values ('PqcrpYFjztcZ','0711','yd','hf','11','20','PpHsvfrpHNbR','18.880','2016-07-11 00:00:00','2016-07-31 23:59:59','kt','0711');
insert  into `cf_supplierprice`(`id`,`pname`,`ispno`,`ipstype`,`pno`,`size`,`sid`,`price`,`begintime`,`endtime`,`status`,`cpordernos`) values ('PqdMKi4AHOra','移动-话费-山东','yd','hf','37','30','PpHsvfrpHNbR','29.990','2016-07-11 00:00:00','2016-07-31 23:59:59','kt','07111639');

/*Table structure for table `cf_wsbill` */

DROP TABLE IF EXISTS `cf_wsbill`;

CREATE TABLE `cf_wsbill` (
  `spno` varchar(32) NOT NULL COMMENT 'SP订单号',
  `cpno` varchar(32) DEFAULT NULL COMMENT 'CP流水号',
  `num` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `size` varchar(16) DEFAULT NULL COMMENT '商品数量',
  `phone` varchar(30) DEFAULT NULL COMMENT '充值账号',
  `oprice` varchar(16) DEFAULT NULL COMMENT '订单金额',
  `otime` varchar(20) DEFAULT NULL COMMENT '订单时间',
  `status` varchar(6) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`spno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网宿对账表';

/*Data for the table `cf_wsbill` */

/*Table structure for table `immenu` */

DROP TABLE IF EXISTS `immenu`;

CREATE TABLE `immenu` (
  `MENUID` varchar(32) NOT NULL,
  `CNNAME` varchar(60) NOT NULL COMMENT '菜单名称',
  `ENNAME` varchar(120) NOT NULL COMMENT '菜单英文名称',
  `MENUURL` varchar(256) NOT NULL COMMENT '菜单URL',
  `PARENTID` varchar(32) NOT NULL COMMENT '上级菜单ID',
  `MENUORDER` varchar(2) NOT NULL COMMENT '顺序',
  `MENULEVEL` varchar(3) NOT NULL COMMENT '级别',
  `STATUS` varchar(2) NOT NULL COMMENT '状态,0-无效,1-有效',
  `ICON` varchar(20) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `immenu` */

insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0100','首页','shouye','/main/show','0','1','1','1','icon-home');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0200','系统管理','sysmanage','#','0','2','1','1','icon-settings');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0201','用户管理','usermanage','/imuser/init','0200','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0202','角色管理','rolemanage','/role/init','0200','2','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0203','系统参数','syspara','/base/init','0200','3','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0300','充值管理','recharge','#','0','3','1','1','icon-basket');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0301','流水查询','currbusilog','/currbusilog/init','0300','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0305','单笔充值','recharge','/recharge/init','0300','5','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0306','批量充值','batchrecharge','/batchrecharge/init','0300','6','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0307','批量充值查询','batchcurrbusilog','/batchcurrbusilog/init','0300','7','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0308','补充查询','addcurrbusilog','/addcurrbusilog/init','0300','8','2','1','');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0400','报价管理','price','#','0','4','1','1','icon-tag');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0401','供应商管理','supplier','/supplier/init','0400','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0402','客户管理','customer','/customer/init','0400','2','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0403','接入者管理','accessclient','/accessclient/init','0400','3','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0404','供应商报价','supplierprice','/supplierprice/init','0400','4','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0405','接入者报价','accprice','/accprice/init','0400','5','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0500','审批管理','audit','#','0','5','1','1','icon-pencil');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0501','审批管理','audit','/audit/init','0500','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0502','审批查询','audit','/auditresult/userinit','0500','2','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0600','统计管理','count','#','0','6','1','1','icon-docs');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0601','报表管理','reportmanage','/reportmanage/init','0600','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0602','对账管理','bill','/bill/init','0600','2','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0700','个人中心','personcenter','#','0','7','1','1','icon-user');
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0701','个人信息','personalcenter','/personalcenter/init','0700','1','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0702','修改密码','pwdmanage','/password/init','0700','2','2','1',NULL);
insert  into `immenu`(`MENUID`,`CNNAME`,`ENNAME`,`MENUURL`,`PARENTID`,`MENUORDER`,`MENULEVEL`,`STATUS`,`ICON`) values ('0703','日志查询','loggermanage','/log/init','0700','3','2','1',NULL);

/*Table structure for table `imresource` */

DROP TABLE IF EXISTS `imresource`;

CREATE TABLE `imresource` (
  `RESOURCEID` varchar(32) NOT NULL COMMENT '资源ID',
  `CNNAME` varchar(30) NOT NULL COMMENT '资源中文名称',
  `ENNAME` varchar(30) NOT NULL COMMENT '资源英文名称',
  `RESOURCEKEY` varchar(120) NOT NULL COMMENT '资源KEY',
  `PARENTID` varchar(32) NOT NULL,
  `REMARK` varchar(50) DEFAULT NULL COMMENT '资源描述',
  `STATUS` varchar(2) NOT NULL COMMENT '状态;0:无效,1:有效',
  PRIMARY KEY (`RESOURCEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源定义表';

/*Data for the table `imresource` */

insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0200','系统管理','sysmanage','#','0','系统管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0300','充值管理','cfmanage','#','0','充值管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0400','报价管理','suppliermanage','#','0','报价管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0500','审批管理','audit','#','0','审批管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0600','统计管理','count','#','0','统计管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('0700','个人中心','personcenter','#','0','个人中心','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('accprice','接入者报价','accprice','/accprice/**','0400','接入者报价','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('addcurrbusilog','补充查询','addcurrbusilog','/addcurrbusilog/**','0300','充值管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('anonymous_login','登录','login','/login','0','匿名用户访问','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('audit','审批管理','audit','/audit/**','0500','审批管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('auditresult','审批查询','auditresult','/auditresult/**','0500','审批查询','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('batchcurrbusilog','批量充值查询','batchcurrbusilog','/batchcurrbusilog/**','0300','批量充值查询','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('bill','对账管理','bill','/bill/**','0600','对账管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('cf_accessclient','接入者管理','accessclient','/accessclient/**','0400','充值管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('cf_batchrecharge','批量充值','batchrecharge','/batchrecharge/**','0300','批量充值','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('cf_customer','客户管理','customer','/customer/**','0400','充值管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('getrandom','获取秘钥','getrandom','/getrandom/getrandom','cf_accessclient','获取秘钥','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('loggermanage','日志查询','loggernamage','/log/**','0700','系统管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('mainpage','首页','mainpage','/main/**','0','主页面','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('of_currbusilog','流水查询','currbusilog','/currbusilog/**','0300','充值管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('personalcenter','个人信息','personalcenter','/personalcenter/**','0700','系统管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('pwdedit','修改密码','pwdedit','/password/**','0700','修改密码','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('recharge','单笔充值','recharge','/recharge/**','0300','充值（单笔）','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('reportmanage','报表管理','reportmanage','/reportmanage/**','0600','报表管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('rolemanage','角色管理','rolemanage','/role/**','0200','角色管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('supplier','供应商管理','supplier','/supplier/**','0400','供应商管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('supplierprice','供应商报价','supplierprice','/supplierprice/**','0400','供应商报价','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('syspara','参数管理','syspara','/base/**','0200','系统参数管理','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('sys_init','入口','init','/','0','登录回调','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('sys_login','登录','login','/login','0','登录','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('sys_logout','退出','logout','/logout','0','退出登录','1');
insert  into `imresource`(`RESOURCEID`,`CNNAME`,`ENNAME`,`RESOURCEKEY`,`PARENTID`,`REMARK`,`STATUS`) values ('usermanage','用户管理','usermanage','/imuser/**','0200','系统管理','1');

/*Table structure for table `imrole` */

DROP TABLE IF EXISTS `imrole`;

CREATE TABLE `imrole` (
  `ROLEID` varchar(32) NOT NULL COMMENT '角色ID',
  `ROLENAME` varchar(120) NOT NULL COMMENT '角色名称',
  `STATUS` varchar(2) NOT NULL COMMENT '状态 ;  0-无效,1-有效',
  `UPDATETIME` varchar(20) NOT NULL COMMENT '更新日期',
  `CREATETIME` varchar(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色定义表';

/*Data for the table `imrole` */

insert  into `imrole`(`ROLEID`,`ROLENAME`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','角色01','1','2016-06-27 15:50:08','2016-06-27 09:42:42');
insert  into `imrole`(`ROLEID`,`ROLENAME`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','审批','1','2016-06-27 10:39:00','2016-06-27 10:37:03');
insert  into `imrole`(`ROLEID`,`ROLENAME`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','超级管理员','1','20160418090101','20160418090101');

/*Table structure for table `imroleresource` */

DROP TABLE IF EXISTS `imroleresource`;

CREATE TABLE `imroleresource` (
  `ROLEID` varchar(32) NOT NULL,
  `RESOURCEID` varchar(32) NOT NULL COMMENT '交易代码',
  `STATUS` varchar(2) NOT NULL COMMENT '状态,0-无效,1-有效',
  `UPDATETIME` varchar(20) NOT NULL,
  `CREATETIME` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLEID`,`RESOURCEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

/*Data for the table `imroleresource` */

insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0200','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0300','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0400','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0500','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0600','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','0700','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','accprice','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','addcurrbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','audit','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','auditresult','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','batchcurrbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','bill','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','cf_accessclient','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','cf_batchrecharge','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','cf_customer','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','getrandom','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','loggermanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','mainpage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','of_currbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','personalcenter','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','pwdedit','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','recharge','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','reportmanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','rolemanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','supplier','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','supplierprice','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','syspara','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHnw2GMpShy','usermanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','0400','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','0500','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','0700','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','accprice','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','audit','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','auditresult','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','cf_accessclient','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','cf_customer','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','getrandom','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','loggermanage','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','mainpage','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','personalcenter','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','pwdedit','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','supplier','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1cBXV79Ae','supplierprice','1','2016-06-27 10:39:01','2016-06-27 10:39:01');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0200','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0300','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0400','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0500','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0600','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','0700','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','accprice','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','addcurrbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','audit','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','auditresult','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','batchcurrbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','bill','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','cf_accessclient','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','cf_batchrecharge','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','cf_customer','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','getrandom','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','loggermanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','mainpage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','of_currbusilog','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','personalcenter','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','pwdedit','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','recharge','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','reportmanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','rolemanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','supplier','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','supplierprice','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','syspara','1','2016-06-27 15:50:08','2016-06-27 15:50:08');
insert  into `imroleresource`(`ROLEID`,`RESOURCEID`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('R0001','usermanage','1','2016-06-27 15:50:08','2016-06-27 15:50:08');

/*Table structure for table `imsyspara` */

DROP TABLE IF EXISTS `imsyspara`;

CREATE TABLE `imsyspara` (
  `id` varchar(32) NOT NULL,
  `syskey` varchar(32) NOT NULL,
  `sysvalue` varchar(100) NOT NULL,
  `sysremark` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数表';

/*Data for the table `imsyspara` */

insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('1','PWDERRCOUNT','4','最大密码错误次数');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('10','OF_cardid','140101','(欧飞)商品编码，话费');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('11','OF_version','6.0','(欧飞)接口版本');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('12','OF_ret_url','http://192.168.23.153:8080/cf-manage/offdn/ordercb','(欧飞)订单充值结果回调URL地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('13','OF_queryUrl','http://192.168.23.123:8080/cfre/ofqueryOrder','(欧飞)查询充值状态接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('14','OF_queryDetailsUrl','http://AXXX.api2.ofpay.com/queryOrderInfo.do','(欧飞)查询订单详情接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('15','OF_billUrl','http://AXXXX.api2.ofpay.com/querybill.do','(欧飞)自动对账接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('2','WS_apiKey','78448f3883d04a8594793fb771d3a256e155893ee79d481f960a562a75af7f94','(网宿)接口apiKey');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('3','WS_cpUserName','jscx','(网宿)客户CDN账号');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('4','WS_orderUrl','http://192.168.23.123:8080/cfre/user/order','(网宿)订购接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('5','WS_queryOrderUrl','http://192.168.23.123:8080/cfre/user/queryOrder','(网宿)订购查询接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('6','OF_KeyStr','OFCARD','(欧飞)KeyStr，默认为 OFCARD');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('7','OF_orderUrl','http://192.168.23.123:8080/cfre/onlineorder','(欧飞)手机充值接口地址');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('8','OF_userid','A1276142','(欧飞)SP编码');
insert  into `imsyspara`(`id`,`syskey`,`sysvalue`,`sysremark`) values ('9','OF_userpws','*********************','(欧飞)SP接入密码(账户密码的MD5值，小写）');

/*Table structure for table `imuser` */

DROP TABLE IF EXISTS `imuser`;

CREATE TABLE `imuser` (
  `USERID` varchar(32) NOT NULL COMMENT '用户id',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWD` varchar(100) NOT NULL COMMENT '密码',
  `CNAME` varchar(60) NOT NULL COMMENT '用户姓名',
  `PWDERRNUM` varchar(6) NOT NULL COMMENT '密码错误次数',
  `PHONENO` varchar(15) NOT NULL COMMENT '手机号',
  `EMAIL` varchar(40) NOT NULL COMMENT '邮箱',
  `STATUS` varchar(2) NOT NULL COMMENT '状态;0-失效,1-正常,2-冻结',
  `UPDATETIME` varchar(20) NOT NULL COMMENT '更新时间YYYYMMDDHH24MISS',
  `CREATETIME` varchar(20) NOT NULL COMMENT '创建时间YYYYMMDDHH24MISS',
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作员信息表';

/*Data for the table `imuser` */

insert  into `imuser`(`USERID`,`USERNAME`,`PASSWD`,`CNAME`,`PWDERRNUM`,`PHONENO`,`EMAIL`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('admin','admin','04332c939735a467e8e2af1e77147033585c2fd0720d9adc5b4f9238ee17ef38e275749824d63279','超级管理员','0','0','0','1','2016-07-12 15:36:13','20160418090101');
insert  into `imuser`(`USERID`,`USERNAME`,`PASSWD`,`CNAME`,`PWDERRNUM`,`PHONENO`,`EMAIL`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpHpRxbS73Gg','test001','8e8312533744950e4dce5a0b9c04e42b78215de7b60e40a0c7a6c9f7b5151ada290a4411fea852e9','郭采洁','0','12331111112','1111111@vv.com','1','2016-06-30 15:34:32','2016-06-27 09:48:43');
insert  into `imuser`(`USERID`,`USERNAME`,`PASSWD`,`CNAME`,`PWDERRNUM`,`PHONENO`,`EMAIL`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpI1onbw6DPO','qwe','4e02038059b3c31ccea5b863ceb85628a936025ba7cfd5235424c8deb503b3e2cddc0375b368c3b3','guozi','0','12331111112','1111111@vv.com','1','2016-07-12 14:35:35','2016-06-27 10:37:51');
insert  into `imuser`(`USERID`,`USERNAME`,`PASSWD`,`CNAME`,`PWDERRNUM`,`PHONENO`,`EMAIL`,`STATUS`,`UPDATETIME`,`CREATETIME`) values ('PpIf5SO2zriw','cyl','25248336f2439a2d6203b9e7837e9a523d015c92e403a223457a1d4b7d3688c8a4f45fa44971636e','cyl','2','18888888888','98@163.com','1','2016-06-27 13:14:24','2016-06-27 13:13:50');

/*Table structure for table `imuserlog` */

DROP TABLE IF EXISTS `imuserlog`;

CREATE TABLE `imuserlog` (
  `LOGID` varchar(32) NOT NULL COMMENT '日志ID',
  `USERID` varchar(32) NOT NULL COMMENT '用户id',
  `TRANSNAME` varchar(60) NOT NULL COMMENT '交易名称',
  `TRANSTIME` varchar(20) NOT NULL COMMENT '交易时间YYYYMMDDHH24MISS',
  `REMOTEIP` varchar(20) NOT NULL COMMENT '请求ip地址',
  `STATUS` varchar(2) NOT NULL COMMENT '交易状态,0-失败，1-成功',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作员日志表';

/*Data for the table `imuserlog` */

insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpacmIVh8ZpI','admin','登录','2016-06-30 14:57:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Ppam5G090TYU','PpHpRxbS73Gg','登录','2016-06-30 15:34:32','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHmujMg2a1o','admin','登录','2016-06-27 09:38:39','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHnw61i28lk','admin','角色管理-新增','2016-06-27 09:42:43','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHpkXUT0C0K','PpHpRxbS73Gg','登录','2016-06-27 09:49:55','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHppXs5JphH','PpHpRxbS73Gg','修改密码','2016-06-27 09:50:14','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHpSpigej5t','admin','用户管理-新增','2016-06-27 09:48:47','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHptSuRfF0b','PpHpRxbS73Gg','个人信息-编辑','2016-06-27 09:50:29','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHpyJOXs6m5','PpHpRxbS73Gg','个人信息-编辑','2016-06-27 09:50:48','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHq0vFaUmGm','PpHpRxbS73Gg','登录','2016-06-27 09:50:58','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHqI3nSmPYx','PpHpRxbS73Gg','登录','2016-06-27 09:52:04','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHsayIARHbj','PpHpRxbS73Gg','接入者管理-新增','2016-06-27 10:01:13','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHsBxNTDJJg','PpHpRxbS73Gg','客户管理-新增','2016-06-27 09:59:37','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHsvgy6G87w','PpHpRxbS73Gg','供应商管理-新增','2016-06-27 10:02:33','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHy76rqy1Ab','PpHpRxbS73Gg','供应商管理-新增','2016-06-27 10:23:08','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHyOcTaCBgD','PpHpRxbS73Gg','供应商报价-新增','2016-06-27 10:24:16','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHyzEtsTqwH','PpHpRxbS73Gg','供应商报价-新增','2016-06-27 10:26:36','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpHzKiYEEOpw','admin','登录','2016-06-27 10:27:59','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI1cFstWEfB','admin','角色管理-新增','2016-06-27 10:37:03','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI1p9ulp1eo','admin','用户管理-新增','2016-06-27 10:37:53','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI1xjr0ItzH','PpI1onbw6DPO','登录','2016-06-27 10:38:26','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2246n7aG9','admin','登录','2016-06-27 10:38:42','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI26tI0gl1m','admin','角色管理-编辑','2016-06-27 10:39:01','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI28wuhMMFN','PpI1onbw6DPO','登录','2016-06-27 10:39:09','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2CAJbmw5F','PpI1onbw6DPO','修改密码','2016-06-27 10:39:21','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2fVmHmkaO','PpHpRxbS73Gg','接入者报价-新增','2016-06-27 10:41:14','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2FwDeDbMl','PpI1onbw6DPO','个人信息-编辑','2016-06-27 10:39:36','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2hTB9KSml','PpI1onbw6DPO','登录','2016-06-27 10:41:21','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2IUX8YKAp','PpI1onbw6DPO','登录','2016-06-27 10:39:45','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2jPeAuBhi','PpHpRxbS73Gg','登录','2016-06-27 10:41:29','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2Kf17jefW','PpI1onbw6DPO','审批管理','2016-06-27 10:39:54','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2LnAIkoFt','PpI1onbw6DPO','审批管理','2016-06-27 10:39:58','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2PrrQyeIf','PpHpRxbS73Gg','登录','2016-06-27 10:40:14','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2v87CKtp8','PpI1onbw6DPO','登录','2016-06-27 10:42:14','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2yWMgh41S','PpI1onbw6DPO','审批管理','2016-06-27 10:42:27','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2ZkLz2XXQ','PpHpRxbS73Gg','接入者报价-新增','2016-06-27 10:40:52','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI2zkqjD9dJ','PpI1onbw6DPO','审批管理','2016-06-27 10:42:32','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI30wkmjriw','PpHpRxbS73Gg','登录','2016-06-27 10:42:36','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI66Mcvrsyz','PpHpRxbS73Gg','单笔充值','2016-06-27 10:54:52','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpI6AcWEsVor','PpHpRxbS73Gg','单笔充值','2016-06-27 10:55:08','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIe0HcpcltU','PpI1onbw6DPO','登录','2016-06-27 13:09:32','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIe242HUUMw','PpHpRxbS73Gg','登录','2016-06-27 13:09:39','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIekoG0Yqv7','admin','登录','2016-06-27 13:12:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIepKDZqH0l','PpI1onbw6DPO','登录','2016-06-27 13:12:48','192.168.23.153','0');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIeqdPnrDVy','admin','登录','2016-06-27 13:12:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIf6OMhon10','admin','用户管理-新增','2016-06-27 13:13:54','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIfA8zF5sr2','admin','角色管理-编辑','2016-06-27 13:14:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIfC7nYcVyO','PpIf5SO2zriw','登录','2016-06-27 13:14:16','192.168.23.153','0');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIfELgJ6dzC','PpIf5SO2zriw','登录','2016-06-27 13:14:24','192.168.23.153','0');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIfFJ817Gj0','admin','登录','2016-06-27 13:14:28','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIhFWA6WaYn','PpHpRxbS73Gg','登录','2016-06-27 13:22:25','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIiMDyVbQZ8','PpHpRxbS73Gg','登录','2016-06-27 13:26:49','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIJFSGYb0Pk','PpI1onbw6DPO','登录','2016-06-27 11:47:05','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpIJHUsMQRwB','PpHpRxbS73Gg','登录','2016-06-27 11:47:13','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJFuDUlbcZL','admin','登录','2016-06-27 15:40:05','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJFyJSzU5vb','PpHpRxbS73Gg','登录','2016-06-27 15:40:21','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJH9Q9ySJEG','PpHpRxbS73Gg','登录','2016-06-27 15:45:02','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJIJq5TJyNq','PpHpRxbS73Gg','登录','2016-06-27 15:49:40','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJIPGG1zjax','PpHpRxbS73Gg','角色管理-编辑','2016-06-27 15:50:01','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJIR4Cu8J6b','PpHpRxbS73Gg','角色管理-编辑','2016-06-27 15:50:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJITYx47GnJ','PpHpRxbS73Gg','登录','2016-06-27 15:50:17','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJR9kzqDjJo','PpHpRxbS73Gg','登录','2016-06-27 16:24:46','192.168.23.132','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpJRyvIZDgph','PpHpRxbS73Gg','登录','2016-06-27 16:28:03','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpNbQOCzSmXF','PpHpRxbS73Gg','登录','2016-06-28 09:30:39','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpNnS8DMWO1a','PpHpRxbS73Gg','登录','2016-06-28 10:18:25','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOjOIoYL6Gq','admin','登录','2016-06-28 14:08:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOjUMydj7qD','PpHpRxbS73Gg','登录','2016-06-28 14:08:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOw7kdlewwc','PpHpRxbS73Gg','登录','2016-06-28 14:59:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOZ0HRxUUUN','admin','登录','2016-06-28 13:27:18','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOz0tHhuncd','PpHpRxbS73Gg','登录','2016-06-28 15:10:37','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOZ4Ig5tWRK','PpHpRxbS73Gg','登录','2016-06-28 13:27:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpOzzISFUbgR','PpHpRxbS73Gg','登录','2016-06-28 15:14:29','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpP4MRQrdGUZ','PpHpRxbS73Gg','登录','2016-06-28 15:31:52','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpP4rKeLJkwJ','PpHpRxbS73Gg','登录','2016-06-28 15:33:50','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpP50uC6gND4','PpHpRxbS73Gg','补充查询','2016-06-28 15:34:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpP7deeRuAJt','PpHpRxbS73Gg','登录','2016-06-28 15:44:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpP9hMNbvteb','PpHpRxbS73Gg','登录','2016-06-28 15:53:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPAg2P4hNpQ','PpHpRxbS73Gg','补充查询','2016-06-28 15:56:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPaIvIrAfFK','admin','登录','2016-06-28 17:38:44','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPAJgS6fNwt','PpHpRxbS73Gg','登录','2016-06-28 15:55:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPAk59Iqdj9','PpHpRxbS73Gg','补充查询','2016-06-28 15:57:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPaVnHXH2dO','admin','登录','2016-06-28 17:39:34','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPaZeNH4IEt','PpHpRxbS73Gg','登录','2016-06-28 17:39:49','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPBl7Ev0JZX','PpHpRxbS73Gg','补充查询','2016-06-28 16:01:15','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPBqgkpt0FD','PpHpRxbS73Gg','补充查询','2016-06-28 16:01:36','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPF6XGTaY2l','PpHpRxbS73Gg','补充查询','2016-06-28 16:14:32','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPh3I3Q9KF2','PpHpRxbS73Gg','登录','2016-06-28 18:05:33','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPIM9KRJz79','PpHpRxbS73Gg','登录','2016-06-28 16:27:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPIPMGQEBCD','PpHpRxbS73Gg','补充查询','2016-06-28 16:27:39','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPJFz4s2Aea','PpHpRxbS73Gg','补充查询','2016-06-28 16:31:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPJMPbApnEE','PpHpRxbS73Gg','登录','2016-06-28 16:31:26','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPN5abnqLyH','PpHpRxbS73Gg','补充查询','2016-06-28 16:46:15','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPTwtD4Gnwj','PpHpRxbS73Gg','登录','2016-06-28 17:13:30','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPV0613BcPJ','PpHpRxbS73Gg','登录','2016-06-28 17:17:40','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPVHM2Tyeve','PpHpRxbS73Gg','登录','2016-06-28 17:18:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPVqlaulTOd','PpHpRxbS73Gg','登录','2016-06-28 17:21:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPWgNWWk0Xc','PpHpRxbS73Gg','登录','2016-06-28 17:24:21','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPXrHFqnCS6','PpHpRxbS73Gg','接入者管理-新增','2016-06-28 17:29:02','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPY0BgAbKEI','PpHpRxbS73Gg','接入者报价-新增','2016-06-28 17:29:36','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPY5c7C4qEZ','PpHpRxbS73Gg','接入者报价-新增','2016-06-28 17:29:57','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPY8CZUcBsW','PpI1onbw6DPO','登录','2016-06-28 17:30:07','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYDI8LgbDj','PpI1onbw6DPO','审批管理','2016-06-28 17:30:26','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYdLZeQM5Y','PpHpRxbS73Gg','单笔充值','2016-06-28 17:32:06','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYELfhMkzd','PpI1onbw6DPO','审批管理','2016-06-28 17:30:30','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYGfl5y5VY','PpI1onbw6DPO','审批管理','2016-06-28 17:30:39','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYKBrd3ddq','PpHpRxbS73Gg','登录','2016-06-28 17:30:53','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYq1734sb6','PpI1onbw6DPO','登录','2016-06-28 17:32:55','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYrmEpJat5','PpI1onbw6DPO','审批管理','2016-06-28 17:33:02','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYwWgiUpY8','PpHpRxbS73Gg','登录','2016-06-28 17:33:20','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpPYXTBboluM','PpHpRxbS73Gg','单笔充值','2016-06-28 17:31:44','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTc8r15kWi8','admin','登录','2016-06-29 10:11:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTHiTc2zVTL','admin','登录','2016-06-29 08:50:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTHjXmy5LIF','admin','登录','2016-06-29 08:50:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTkTpXYOCbD','admin','登录','2016-06-29 10:44:15','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTkY6QTbNAK','admin','补充查询','2016-06-29 10:44:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTpkAxTuvy3','admin','登录','2016-06-29 11:05:10','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTpoI3ltNff','admin','补充查询','2016-06-29 11:05:25','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTq8ti1wjp8','admin','单笔充值','2016-06-29 11:06:45','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTqQ3nrzY7k','PpHpRxbS73Gg','登录','2016-06-29 11:07:51','192.168.23.12','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTqt3Cjym8O','admin','登录','2016-06-29 11:09:42','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTsDPYM42Ej','admin','登录','2016-06-29 11:14:59','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTst5ZWjmSp','admin','补充查询','2016-06-29 11:17:39','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTsZ4y6UNQJ','admin','登录','2016-06-29 11:16:22','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTT3kf2UjiX','admin','登录','2016-06-29 09:35:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTTa9l40agK','admin','登录','2016-06-29 09:37:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTtWH8vXYbh','admin','补充查询','2016-06-29 11:20:09','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTuEfrJWGfJ','admin','登录','2016-06-29 11:23:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTueOp9p5JE','admin','单笔充值','2016-06-29 11:24:39','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTvi3B4k8gh','admin','补充查询','2016-06-29 11:28:51','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTvM2T5qOOq','admin','补充查询','2016-06-29 11:27:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTVVm1am69s','admin','登录','2016-06-29 09:44:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTw6yXgGWt1','admin','补充查询','2016-06-29 11:30:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTwnYbuk3Jj','admin','登录','2016-06-29 11:33:11','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTws3aqcfL9','admin','补充查询','2016-06-29 11:33:28','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTXJa2KIR0V','admin','登录','2016-06-29 09:51:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTZ3bpodZX7','admin','登录','2016-06-29 09:58:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTZeAY9SzdZ','admin','登录','2016-06-29 10:01:13','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTZKFgukmi8','admin','登录','2016-06-29 09:59:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpTZqwIqGWaj','admin','登录','2016-06-29 10:02:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpU2w4nQUeTD','admin','登录','2016-06-29 11:57:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpU2yf0Gck60','admin','补充查询','2016-06-29 11:57:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpU3BhBmbfYZ','admin','单笔充值','2016-06-29 11:58:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUDkcgt9tWv','admin','单笔充值','2016-06-29 12:40:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUDp4rw7I0N','admin','单笔充值','2016-06-29 12:40:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUDvFQJaYFP','admin','单笔充值','2016-06-29 12:41:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUDXt8eyxUn','admin','登录','2016-06-29 12:39:42','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUE2Vyas9oK','admin','单笔充值','2016-06-29 12:41:40','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUEO7wQJfI2','admin','单笔充值','2016-06-29 12:43:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUGaHuiMxiu','admin','登录','2016-06-29 12:51:46','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUGBYQDgP1C','admin','登录','2016-06-29 12:50:11','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUH7v8eHnp9','admin','补充查询','2016-06-29 12:53:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUHC7ZOUYxN','admin','补充查询','2016-06-29 12:54:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUHxUeIfIrK','admin','补充查询','2016-06-29 12:57:14','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUJ5VEtVRn1','admin','补充查询','2016-06-29 13:01:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKEaZZ4aWv','admin','登录','2016-06-29 13:06:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKLYkTnYyF','PpHpRxbS73Gg','登录','2016-06-29 13:06:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKOJ7qlizg','PpHpRxbS73Gg','审批管理','2016-06-29 13:06:54','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKPR8Slw0r','admin','登录','2016-06-29 13:06:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKTNH7cqhM','admin','补充查询','2016-06-29 13:07:13','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKUqKxN7Ci','admin','补充查询','2016-06-29 13:07:19','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKVpg1exr8','admin','补充查询','2016-06-29 13:07:23','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKWqaJIXUa','admin','补充查询','2016-06-29 13:07:26','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUKY7VyHpTs','admin','补充查询','2016-06-29 13:07:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpULcv1OWakY','admin','补充查询','2016-06-29 13:11:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpULNATys2KB','admin','补充查询','2016-06-29 13:10:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpULOWcSZYMs','admin','补充查询','2016-06-29 13:10:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpULPpKr9cod','admin','补充查询','2016-06-29 13:10:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpULQsChJy7U','admin','补充查询','2016-06-29 13:11:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUuhFCOC3YZ','admin','登录','2016-06-29 15:31:06','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUUJHSKuP9W','admin','登录','2016-06-29 13:46:18','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUv1E0TgwI8','admin','补充查询','2016-06-29 15:32:23','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpUwJ7JnFHrX','admin','补充查询','2016-06-29 15:37:30','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVa5QR9SnpM','admin','登录','2016-06-29 18:15:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVa8ggoJVl8','admin','补充查询','2016-06-29 18:15:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVaNXdgR9Nz','admin','补充查询','2016-06-29 18:16:40','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVaWiwqQRwG','admin','补充查询','2016-06-29 18:17:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpViOsTSJuJz','admin','登录','2016-06-29 18:48:32','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVj7IyJ0JUp','admin','登录','2016-06-29 18:51:23','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVkHaw3NmmM','admin','登录','2016-06-29 18:56:01','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVQvPMRdpDY','admin','登录','2016-06-29 17:39:07','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVRsvMx3zEh','admin','登录','2016-06-29 17:42:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVSPj7obQFH','admin','补充查询','2016-06-29 17:45:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVTZOu3tv4K','admin','补充查询','2016-06-29 17:49:38','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWavvGXxS4','admin','补充查询','2016-06-29 18:01:38','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWFj7vNbSx','admin','单笔充值','2016-06-29 18:00:17','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWgdLZjDsN','admin','补充查询','2016-06-29 18:02:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWNlEVPgvI','PpHpRxbS73Gg','登录','2016-06-29 18:00:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWoSPK962j','admin','补充查询','2016-06-29 18:02:30','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWPdqEG5bl','PpHpRxbS73Gg','审批管理','2016-06-29 18:00:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVWQVuuJyRa','admin','登录','2016-06-29 18:00:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVYgg4kG3j7','admin','补充查询','2016-06-29 18:09:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVYhpcyNNNj','admin','补充查询','2016-06-29 18:10:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVZ7D0vZU2W','admin','补充查询','2016-06-29 18:11:39','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PpVZJUj6RCYJ','admin','补充查询','2016-06-29 18:12:26','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcanyp78cgf','admin','登录','2016-07-11 13:31:09','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcc5lwdYa3r','admin','登录','2016-07-11 13:36:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcjPUisFJ88','admin','登录','2016-07-11 14:05:20','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcjYHqVv5j5','admin','登录','2016-07-11 14:05:54','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcpuAfDFbyx','admin','供应商报价-删除','2016-07-11 14:31:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcpyeiIWsYR','PpI1onbw6DPO','登录','2016-07-11 14:31:25','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcq3MJgV7HS','admin','登录','2016-07-11 14:31:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcqbGQzPzOy','admin','登录','2016-07-11 14:33:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcqf1IA1jWW','admin','接入者报价-删除','2016-07-11 14:34:08','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcqJTADathR','admin','接入者报价-删除','2016-07-11 14:32:45','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcqmEWE8Zoc','admin','接入者报价-删除','2016-07-11 14:34:36','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcqvjQZVBq7','PpI1onbw6DPO','登录','2016-07-11 14:35:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcqzNfQP57v','PpI1onbw6DPO','审批管理','2016-07-11 14:35:26','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcr0Yn7ipM4','PpI1onbw6DPO','审批管理','2016-07-11 14:35:31','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcr2DbtRSip','PpI1onbw6DPO','审批管理','2016-07-11 14:35:37','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcr3gGL8WdL','PpI1onbw6DPO','审批管理','2016-07-11 14:35:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcr5S1YtKbn','admin','登录','2016-07-11 14:35:49','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcre0DJzehD','admin','供应商报价-删除','2016-07-11 14:38:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcrfd73ndSr','PpI1onbw6DPO','登录','2016-07-11 14:38:09','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcrhc5B41bS','PpI1onbw6DPO','审批管理','2016-07-11 14:38:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrilmD83Pw','admin','登录','2016-07-11 14:38:21','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrMSU92gmg','admin','供应商报价-新增','2016-07-11 14:36:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrObpXrq1G','PpI1onbw6DPO','登录','2016-07-11 14:37:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrpZvdwumN','admin','供应商报价-新增','2016-07-11 14:38:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrQx3rbeXn','PpI1onbw6DPO','审批管理','2016-07-11 14:37:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqcrr3ZhGn5a','PpI1onbw6DPO','登录','2016-07-11 14:38:52','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrS0fzyFu5','admin','登录','2016-07-11 14:37:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrswQWiFMB','PpI1onbw6DPO','审批管理','2016-07-11 14:39:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqcrtuLLwPnX','admin','登录','2016-07-11 14:39:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdM4p3a8537','admin','登录','2016-07-11 16:38:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdMKlK7eQXI','admin','供应商报价-新增','2016-07-11 16:39:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdMMMqfjDTB','PpI1onbw6DPO','登录','2016-07-11 16:40:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdMOTPJR7Xo','PpI1onbw6DPO','审批管理','2016-07-11 16:40:11','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdMPONA8TAh','admin','登录','2016-07-11 16:40:14','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdOJN6RiX1P','admin','登录','2016-07-11 16:47:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdP6S63AIpQ','admin','登录','2016-07-11 16:50:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdYd4TEsjuV','PpI1onbw6DPO','登录','2016-07-11 17:28:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdYfKSf1f12','PpI1onbw6DPO','审批管理','2016-07-11 17:28:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdYgMmXdEYM','admin','登录','2016-07-11 17:28:59','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqdYIDXOfsMp','admin','登录','2016-07-11 17:27:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFoW68o7Vw0','admin','登录','2016-07-07 16:01:21','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFqzOAVwuSb','admin','登录','2016-07-07 16:11:10','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFsoHx2hRmN','admin','登录','2016-07-07 16:18:24','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFt2UumpAVl','admin','接入者管理-新增','2016-07-07 16:19:19','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFvUaDLf9dW','admin','登录','2016-07-07 16:29:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFw80cgkKeL','admin','登录','2016-07-07 16:31:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFwzxuMJXwf','admin','登录','2016-07-07 16:35:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFxSnFjq1Bp','admin','登录','2016-07-07 16:36:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFyVz80cIUH','admin','登录','2016-07-07 16:41:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFz6I2i1v8k','admin','登录','2016-07-07 16:43:24','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqFzcKPMHcAV','admin','登录','2016-07-07 16:45:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG1CpS2Rsuq','admin','登录','2016-07-07 16:51:45','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG1Yxevv6wr','admin','登录','2016-07-07 16:53:10','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG2b4CLLbXL','PpI1onbw6DPO','登录','2016-07-07 16:57:17','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG4cFDnNpHc','PpI1onbw6DPO','审批管理','2016-07-07 17:05:18','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG4dXkRYPde','admin','登录','2016-07-07 17:05:23','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG5GDFZDimS','admin','登录','2016-07-07 17:07:52','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG6r8KhrtF2','admin','登录','2016-07-07 17:14:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG7zEMm7UeE','admin','登录','2016-07-07 17:18:41','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG8aOUJcNF6','PpI1onbw6DPO','审批管理','2016-07-07 17:21:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG8b8g6Rs67','admin','登录','2016-07-07 17:21:07','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqG8XVIn8Vp2','PpI1onbw6DPO','登录','2016-07-07 17:20:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqGXzm05c2dV','admin','登录','2016-07-07 19:02:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqhOpjGOpjy6','admin','登录','2016-07-12 09:14:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqhQf7aNwsRM','admin','登录','2016-07-12 09:22:13','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqhsZH4ZukpJ','admin','登录','2016-07-12 11:13:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqhxj1cya1FR','admin','登录','2016-07-12 11:33:33','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqhxpPW8sHf9','PpI1onbw6DPO','登录','2016-07-12 11:33:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqhxr1AYtopC','PpI1onbw6DPO','审批管理','2016-07-12 11:34:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqhxribqR74U','admin','登录','2016-07-12 11:34:07','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiaaSRLTWhT','admin','登录','2016-07-12 14:07:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiaVED5yNRP','admin','供应商报价-删除','2016-07-12 14:07:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiaXG3Vnd1L','PpI1onbw6DPO','登录','2016-07-12 14:07:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiaZkUB8Zg3','PpI1onbw6DPO','审批管理','2016-07-12 14:07:52','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqifhwybPN5T','admin','供应商报价-新增','2016-07-12 14:28:16','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqifkLi4JRpP','PpI1onbw6DPO','登录','2016-07-12 14:28:25','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqifmB7h4bIz','PpI1onbw6DPO','审批管理','2016-07-12 14:28:32','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('Pqifmw3tsEdS','admin','登录','2016-07-12 14:28:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqifOMTuV1cF','admin','登录','2016-07-12 14:27:00','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqihaYTkhdqF','PpI1onbw6DPO','审批管理','2016-07-12 14:35:44','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqihbHxJUWlQ','admin','登录','2016-07-12 14:35:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqihWvrik9RC','admin','供应商报价-删除','2016-07-12 14:35:30','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqihYAlCl6L0','PpI1onbw6DPO','登录','2016-07-12 14:35:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiObaSqO1Oe','admin','登录','2016-07-12 13:20:20','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiOmoEqKLof','PpI1onbw6DPO','登录','2016-07-12 13:21:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiOotYTkHPS','PpI1onbw6DPO','审批管理','2016-07-12 13:21:11','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiOq4UhUYAj','admin','登录','2016-07-12 13:21:15','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqirOjFPnPAw','admin','登录','2016-07-12 15:14:42','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqitjajYolRB','admin','登录','2016-07-12 15:23:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqitoySvYjAZ','admin','登录','2016-07-12 15:24:19','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqitQFIR8Fx7','admin','登录','2016-07-12 15:22:44','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiwoY9BBJHG','admin','登录','2016-07-12 15:36:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiXk8auAxeE','admin','登录','2016-07-12 13:56:37','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiXvQEGZuWO','admin','供应商报价-新增','2016-07-12 13:57:21','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiXx4ty5i4V','PpI1onbw6DPO','登录','2016-07-12 13:57:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiXz5rpRwvc','PpI1onbw6DPO','审批管理','2016-07-12 13:57:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiXzoDL8ZfP','admin','登录','2016-07-12 13:57:38','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqiYm8iXcyo3','admin','登录','2016-07-12 14:00:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK2brwut6Ja','admin','补充查询','2016-07-08 09:22:25','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK2UK8NmE57','admin','登录','2016-07-08 09:21:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK3FcYWJxTa','PpI1onbw6DPO','登录','2016-07-08 09:24:58','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK3INXsnF8M','PpI1onbw6DPO','审批管理','2016-07-08 09:25:09','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK3JHlb9euQ','admin','登录','2016-07-08 09:25:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK4JLSlu4p9','PpI1onbw6DPO','登录','2016-07-08 09:29:11','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK4LNLHQWrG','PpI1onbw6DPO','审批管理','2016-07-08 09:29:19','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK4MOYDUokc','admin','登录','2016-07-08 09:29:22','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK6O54qM0IO','admin','登录','2016-07-08 09:37:26','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqK7n0gAn8bV','admin','登录','2016-07-08 09:43:00','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKBIxDGT5s8','admin','登录','2016-07-08 09:56:58','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKBoXNzKIM5','admin','登录','2016-07-08 09:58:59','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKEBvFlCvzk','admin','登录','2016-07-08 10:08:26','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKemrOZjK14','admin','登录','2016-07-08 11:54:04','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKFZ1c2RZBG','admin','登录','2016-07-08 10:13:53','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKJRsoCGfhl','admin','登录','2016-07-08 10:29:19','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKSViB65P2O','admin','登录','2016-07-08 11:05:18','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKTJ0mhnZM8','admin','登录','2016-07-08 11:08:28','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKxi6Bb4Cad','admin','登录','2016-07-08 13:09:14','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKxlrsV7kAP','admin','登录','2016-07-08 13:09:28','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqKZF0Iwx4tJ','admin','登录','2016-07-08 11:32:02','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL385oovexM','admin','登录','2016-07-08 13:30:46','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL4ETRjytxg','admin','登录','2016-07-08 13:35:08','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL4iu5ELAu8','admin','登录','2016-07-08 13:37:05','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL6J56gZ72A','admin','登录','2016-07-08 13:43:23','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL7hcMJ183i','admin','登录','2016-07-08 13:48:55','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqL9xLxHcMAP','admin','登录','2016-07-08 13:57:53','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLB1xU2ASYG','admin','登录','2016-07-08 14:02:09','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLBs6ai9P2E','admin','登录','2016-07-08 14:05:29','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLFDwjz0rm7','admin','登录','2016-07-08 14:18:48','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLGIlxJeZHF','admin','登录','2016-07-08 14:23:05','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLLB39b5Jsb','admin','登录','2016-07-08 14:42:27','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLLsayZM21u','admin','客户管理-新增','2016-07-08 14:45:14','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLLvLrfm6Lf','admin','客户管理-删除','2016-07-08 14:45:25','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLlwigFIpNp','admin','登录','2016-07-08 16:28:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLMfLGptfjx','admin','客户管理-编辑','2016-07-08 14:48:22','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLMOUEHR8Qr','admin','客户管理-新增','2016-07-08 14:47:17','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLMTneLcEWe','admin','客户管理-编辑','2016-07-08 14:47:37','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLMYjZWrvs4','admin','客户管理-新增','2016-07-08 14:47:56','192.168.23.160','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLnT3suPYPt','admin','登录','2016-07-08 16:34:49','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLouAgXGvtN','admin','登录','2016-07-08 16:40:32','127.0.0.1','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLpzhN1azNY','admin','接入者报价-新增','2016-07-08 16:44:51','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLq533PPsal','admin','接入者报价-新增','2016-07-08 16:45:12','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLrDesiYKHr','admin','登录','2016-07-08 16:49:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLreJOy2Mlf','PpI1onbw6DPO','登录','2016-07-08 16:51:26','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLriMTvsvvh','PpI1onbw6DPO','审批管理','2016-07-08 16:51:41','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLrIudRW0Vx','admin','接入者报价-新增','2016-07-08 16:50:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLrk2jrjbpC','PpI1onbw6DPO','审批管理','2016-07-08 16:51:48','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLrNegnlGNI','admin','接入者报价-新增','2016-07-08 16:50:22','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLroCWRoKjH','admin','登录','2016-07-08 16:52:04','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLsOo6J5BPE','admin','单笔充值','2016-07-08 16:54:24','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLsTW3wcxVL','admin','单笔充值','2016-07-08 16:54:43','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLtmWRAs4KA','admin','登录','2016-07-08 16:59:54','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLWchJkpbWN','admin','登录','2016-07-08 15:27:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLxlgqujYlX','admin','登录','2016-07-08 17:15:44','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLxxR4SJvqe','admin','单笔充值','2016-07-08 17:16:29','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLy41yv0VWS','admin','单笔充值','2016-07-08 17:16:55','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqLzUIWjUNb6','admin','登录','2016-07-08 17:22:34','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM0ph3sZ7J3','admin','登录','2016-07-08 17:27:54','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM2gupW9Wta','admin','登录','2016-07-08 17:35:17','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM2lWR8PHmx','admin','单笔充值','2016-07-08 17:35:35','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM2okRgNoeu','admin','单笔充值','2016-07-08 17:35:47','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM3auV6RZIq','admin','单笔充值','2016-07-08 17:38:53','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM3NlcNa4FB','admin','登录','2016-07-08 17:38:02','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM3XGZImn3V','admin','单笔充值','2016-07-08 17:38:39','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM42vayVH59','admin','登录','2016-07-08 17:40:40','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM49rKLYiAw','admin','单笔充值','2016-07-08 17:41:07','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM4Exq86qMd','admin','单笔充值','2016-07-08 17:41:27','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM5cx09dBYh','admin','登录','2016-07-08 17:46:57','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM5nM1wayBK','admin','登录','2016-07-08 17:47:37','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM5u2N5fO8c','admin','单笔充值','2016-07-08 17:48:03','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM5zDAmrSrb','admin','单笔充值','2016-07-08 17:48:23','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM6d5WpXf63','admin','单笔充值','2016-07-08 17:50:56','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM6UFUeMcQH','admin','登录','2016-07-08 17:50:22','192.168.23.153','1');
insert  into `imuserlog`(`LOGID`,`USERID`,`TRANSNAME`,`TRANSTIME`,`REMOTEIP`,`STATUS`) values ('PqM6ZQTowMeI','admin','单笔充值','2016-07-08 17:50:42','192.168.23.153','1');

/*Table structure for table `imuserrole` */

DROP TABLE IF EXISTS `imuserrole`;

CREATE TABLE `imuserrole` (
  `USERID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLEID` varchar(32) NOT NULL COMMENT '角色编号',
  `UPDATETIME` varchar(20) NOT NULL COMMENT '更新时间',
  `CREATETIME` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ROLEID`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `imuserrole` */

insert  into `imuserrole`(`USERID`,`ROLEID`,`UPDATETIME`,`CREATETIME`) values ('PpHpRxbS73Gg','PpHnw2GMpShy','2016-06-27 09:48:43','2016-06-27 09:48:43');
insert  into `imuserrole`(`USERID`,`ROLEID`,`UPDATETIME`,`CREATETIME`) values ('PpIf5SO2zriw','PpHnw2GMpShy','2016-06-27 13:13:50','2016-06-27 13:13:50');
insert  into `imuserrole`(`USERID`,`ROLEID`,`UPDATETIME`,`CREATETIME`) values ('PpI1onbw6DPO','PpI1cBXV79Ae','2016-06-27 10:37:51','2016-06-27 10:37:51');
insert  into `imuserrole`(`USERID`,`ROLEID`,`UPDATETIME`,`CREATETIME`) values ('PpIf5SO2zriw','PpI1cBXV79Ae','2016-06-27 13:13:50','2016-06-27 13:13:50');
insert  into `imuserrole`(`USERID`,`ROLEID`,`UPDATETIME`,`CREATETIME`) values ('admin','R0001','20160418090101','20160418090101');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
