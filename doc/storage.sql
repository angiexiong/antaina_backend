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

/*Data for the table `admin_permission` */

insert  into `admin_permission`(`id`,`url`,`permission`,`create_time`) values (1,'/api/admin/permission/list','权限列表查询','2019-04-23 14:12:48'),(2,'/api/admin/user/add','添加后台管理账号','2019-04-23 14:13:34'),(3,'/api/admin/user/list','管理账号列表查询','2019-04-23 14:14:48'),(4,'/api/admin/user/export','管理员账号列表导出','2019-04-23 14:15:27'),(5,'/api/admin/user/getById','查询管理员详情信息','2019-04-23 14:16:08'),(6,'/api/admin/user/update','管理员修改某个账号','2019-04-23 14:16:25'),(7,'/api/admin/user/getByToken','根据token查询用户信息','2019-04-23 14:16:54'),(8,'/api/admin/user/adminLogout','管理员登出','2019-04-23 14:17:15'),(9,'/api/autoUpgrade/getListWithPage','分页查询自动升级配置列表','2019-04-23 14:18:08'),(10,'/api/autoUpgrade/export','导出自动升级配置信息','2019-04-23 14:18:36');

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

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`useraccount`,`password`,`username`,`phone`,`status`,`access_permission`,`access_menu`,`create_time`,`update_time`) values (535492994632151099,'admin','MTIzNDU2Nzg5YQ==','管理员','18681444223',1,'/api/admin/permission/list;/api/admin/user/add;/api/admin/user/list;/api/admin/user/export;/api/admin/user/getById;/api/admin/user/update;/api/admin/user/getByToken;/api/admin/user/adminLogout;/api/autoUpgrade/getListWithPage;/api/autoUpgrade/export;/api/autoUpgrade/add;/api/autoUpgrade/delete;/api/upload/inSensitiveFileUpload;/api/article/articleCategory/add;/api/article/articleCategory/update;/api/article/articleCategory/getByTypeCode;/api/article/articleCategory/delete;/api/article/articleCategory/lists;/api/article/articleCategory/export;/api/article/articleCategory/getSupportCategory;/api/article/articleCategory/exportSupportCategory;/api/article/articleCategory/getMarketCategory;/api/article/articleCategory/exportMarketCategory;/api/article/articleCategory/getUserContractCategory;/api/article/add;/api/article/delete;/api/article/update;/api/article/listAdmin;/api/article/getById;/api/article/updateTop;/api/article/supportListAdmin;/api/article/exportSupportList;/api/article/marketListAdmin;/api/article/exportMarketList;/api/article/userContractListAdmin;/api/article/exportUserContractLis;/api/msg/add;/api/msg/delete;/api/msg/getMessageById;/api/msg/update;/api/msg/listAdmin;/api/msg/export;/api/slideshow/add;/api/slideshow/delete;/api/slideshow/update;/api/slideshow/adminList;/api/slideshow/export;/api/slideshow/getById;/api/ticket/getById;/api/ticket/update;/api/ticket/getListWithPage;/api/ticket/export;/api/qrCode/list;/api/qrCode/export;/api/qrCode/getById;/api/qrCode/updateStatus;/api/statisticsPlatform/getListWithPage;/api/statisticsPlatform/export;/api/statisticsPlatform/getDayStatistics;/api/sysConfig/getListByTypeCode;/api/sysConfig/getByTypeCodeAndKey;/api/sysConfig/getSysConfigWithPage;/api/sysConfig/export;/api/sysConfig/insert;/api/sysConfig/update;/api/sysConfig/delete;/api/sysUrlConfig/getListWithPage;/api/sysUrlConfig/export;/api/sysUrlConfig/insert;/api/sysUrlConfig/delete;/api/transaction/getListWithPage;/api/transaction/export;/api/transferRecord/getListWithPage;/api/transferRecord/export;/api/user/getListWithPage;/api/user/getByUsername;/api/user/export;/api/versionHistory/add;/api/versionHistory/delete;/api/versionHistory/update;/api/versionHistory/getListByParamsWithPage;/api/versionHistory/export;/api/versionHistory/getById','user_management;user_query_page;user_trade_query_page;market_management;market_data;market_information_add;market_information_query_page;market_information_update;advertising_management;advertising_add;advertising_update;advertising_query_page;message_center_management;message_center_add;message_center_update;message_center_query_page;help_center_management;help_center_add;help_center_update;help_center_query_page;feedback_query_page;version_center_management;version_add;version_query_page;version_log_add;version_log_update;version_log_query_page;merchant_qrcode_management;merchant_qrcode_query_page;merchant_qrcode_update;protocol_management;protocol_add;protocol_query_page;protocol_update;block_chain_management;block_chain_statistics_query_page;block_chain_aic_upgrade_query_page;system_center_management;userAccount_config_add;userAccount_config_update;access_config_operation;article_type_operation','2019-01-17 16:17:58.000','2019-01-17 16:17:58.000');

/*Table structure for table `customer_info` */

DROP TABLE IF EXISTS `customer_info`;

CREATE TABLE `customer_info` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户名称',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='客户表(记录公司名)';

/*Data for the table `customer_info` */

/*Table structure for table `material_info` */

DROP TABLE IF EXISTS `material_info`;

CREATE TABLE `material_info` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL COMMENT '客户编号',
  `material_code` varchar(64) NOT NULL COMMENT '物料编号',
  `customer_material_code` varchar(64) DEFAULT NULL COMMENT '客户物料编号',
  `material_name` varchar(128) NOT NULL COMMENT '物料名称',
  `model` varchar(256) NOT NULL COMMENT '型号',
  `material_unit` tinyint(1) NOT NULL COMMENT '单位(0:片; 1:个)',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料信息表';

/*Data for the table `material_info` */

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL COMMENT '客户编号',
  `amount` decimal(10,2) NOT NULL COMMENT '订单量',
  `material_id` bigint(20) NOT NULL COMMENT '物料id',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户订单表';

/*Data for the table `order_info` */

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

/*Data for the table `storage_detail` */

/*Table structure for table `storage_info` */

DROP TABLE IF EXISTS `storage_info`;

CREATE TABLE `storage_info` (
  `id` bigint(20) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL COMMENT '历史总库存',
  `year_amount` decimal(10,2) NOT NULL COMMENT '年度总库存',
  `year` int(2) NOT NULL COMMENT '年份',
  `create_time` datetime(3) NOT NULL,
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `storage_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
