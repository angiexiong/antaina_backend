/*
SQLyog Professional v12.08 (32 bit)
MySQL - 8.0.12 : Database - storage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`storage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `storage`;

/*Table structure for table `admin_permission` */

DROP TABLE IF EXISTS `admin_permission`;

CREATE TABLE `admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接口url',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接口权限名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理后台权限关联表';

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL,
  `useraccount` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（0:删除,1:正常,2冻结登陆...）',
  `access_permission` varchar(3999) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户访问权限',
  `access_menu` varchar(3999) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户访问菜单',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`useraccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='管理员';

/*Table structure for table `customer_info` */

DROP TABLE IF EXISTS `customer_info`;

CREATE TABLE `customer_info` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户名称',
  `priority` int(3) NOT NULL COMMENT '优先级(数字越大，优先级越高)',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='客户表(记录公司名)';

/*Table structure for table `order_delivery_detail` */

DROP TABLE IF EXISTS `order_delivery_detail`;

CREATE TABLE `order_delivery_detail` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL COMMENT '订单号',
  `amount` decimal(10,2) NOT NULL COMMENT '出货量',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单出货记录表';

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL COMMENT '客户编号',
  `customer_product_code` varchar(64) DEFAULT NULL COMMENT '客户物料编号',
  `product_code` varchar(64) NOT NULL COMMENT '物料编号',
  `amount` decimal(10,2) NOT NULL COMMENT '订单量',
  `delivery_amount` decimal(10,2) NOT NULL COMMENT '已出货量',
  `remaining_amount` decimal(10,2) NOT NULL COMMENT '未出货量',
  `status` tinyint(1) NOT NULL COMMENT '状态(0:未全部交货, 1:已完成)',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户订单表';

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL,
  `product_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物料编号',
  `product_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物料名称',
  `model` varchar(256) NOT NULL COMMENT '型号',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '物料类型(0:原料; 1:半成品; 2:成品)',
  `product_unit` tinyint(1) NOT NULL DEFAULT '0' COMMENT '计量单位(0:片; 1:个)',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料信息表';

/*Table structure for table `storage_detail` */

DROP TABLE IF EXISTS `storage_detail`;

CREATE TABLE `storage_detail` (
  `id` bigint(20) NOT NULL,
  `storage_id` bigint(20) NOT NULL COMMENT '主表库存项id',
  `month` int(2) NOT NULL COMMENT '月份',
  `monthly_input` decimal(10,2) NOT NULL COMMENT '月度入库',
  `monthly_output` decimal(10,2) NOT NULL COMMENT '月度出库',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存明细表(以月份为基准)';

/*Table structure for table `storage_info` */

DROP TABLE IF EXISTS `storage_info`;

CREATE TABLE `storage_info` (
  `id` bigint(20) NOT NULL,
  `product_code` bigint(20) NOT NULL COMMENT '物料编号',
  `total_amount` decimal(10,2) NOT NULL COMMENT '历史总库存',
  `year_amount` decimal(10,2) NOT NULL COMMENT '年度总库存',
  `year` int(2) NOT NULL COMMENT '年份',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
