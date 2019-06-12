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

/*Data for the table `admin_permission` */

insert  into `admin_permission`(`id`,`url`,`permission`,`create_time`) values (1,'/api/admin/permission/list','权限列表查询','2019-04-23 14:12:48'),(2,'/api/admin/user/add','添加后台管理账号','2019-04-23 14:13:34'),(3,'/api/admin/user/list','管理账号列表查询','2019-04-23 14:14:48'),(4,'/api/admin/user/export','管理员账号列表导出','2019-04-23 14:15:27'),(5,'/api/admin/user/getById','查询管理员详情信息','2019-04-23 14:16:08'),(6,'/api/admin/user/update','管理员修改某个账号','2019-04-23 14:16:25'),(7,'/api/admin/user/getByToken','根据token查询用户信息','2019-04-23 14:16:54'),(8,'/api/admin/user/adminLogout','管理员登出','2019-04-23 14:17:15'),(9,'/api/autoUpgrade/getListWithPage','分页查询自动升级配置列表','2019-04-23 14:18:08'),(10,'/api/autoUpgrade/export','导出自动升级配置信息','2019-04-23 14:18:36');

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`useraccount`,`password`,`username`,`phone`,`status`,`access_permission`,`access_menu`,`create_time`,`update_time`) values (535492994632151099,'admin','admin','管理员','18681444223',1,'/api/admin/permission/list;/api/admin/user/add;/api/admin/user/list;/api/admin/user/export;/api/admin/user/getById;/api/admin/user/update;/api/admin/user/getByToken;/api/admin/user/adminLogout;/api/autoUpgrade/getListWithPage;/api/autoUpgrade/export;/api/autoUpgrade/add;/api/autoUpgrade/delete;/api/upload/inSensitiveFileUpload;/api/article/articleCategory/add;/api/article/articleCategory/update;/api/article/articleCategory/getByTypeCode;/api/article/articleCategory/delete;/api/article/articleCategory/lists;/api/article/articleCategory/export;/api/article/articleCategory/getSupportCategory;/api/article/articleCategory/exportSupportCategory;/api/article/articleCategory/getMarketCategory;/api/article/articleCategory/exportMarketCategory;/api/article/articleCategory/getUserContractCategory;/api/article/add;/api/article/delete;/api/article/update;/api/article/listAdmin;/api/article/getById;/api/article/updateTop;/api/article/supportListAdmin;/api/article/exportSupportList;/api/article/marketListAdmin;/api/article/exportMarketList;/api/article/userContractListAdmin;/api/article/exportUserContractLis;/api/msg/add;/api/msg/delete;/api/msg/getMessageById;/api/msg/update;/api/msg/listAdmin;/api/msg/export;/api/slideshow/add;/api/slideshow/delete;/api/slideshow/update;/api/slideshow/adminList;/api/slideshow/export;/api/slideshow/getById;/api/ticket/getById;/api/ticket/update;/api/ticket/getListWithPage;/api/ticket/export;/api/qrCode/list;/api/qrCode/export;/api/qrCode/getById;/api/qrCode/updateStatus;/api/statisticsPlatform/getListWithPage;/api/statisticsPlatform/export;/api/statisticsPlatform/getDayStatistics;/api/sysConfig/getListByTypeCode;/api/sysConfig/getByTypeCodeAndKey;/api/sysConfig/getSysConfigWithPage;/api/sysConfig/export;/api/sysConfig/insert;/api/sysConfig/update;/api/sysConfig/delete;/api/sysUrlConfig/getListWithPage;/api/sysUrlConfig/export;/api/sysUrlConfig/insert;/api/sysUrlConfig/delete;/api/transaction/getListWithPage;/api/transaction/export;/api/transferRecord/getListWithPage;/api/transferRecord/export;/api/user/getListWithPage;/api/user/getByUsername;/api/user/export;/api/versionHistory/add;/api/versionHistory/delete;/api/versionHistory/update;/api/versionHistory/getListByParamsWithPage;/api/versionHistory/export;/api/versionHistory/getById','admin;admin_add;admin_query;admin_update;customer;customer_info;customer_add;customer_update;order;order_info;product;product_query;product_add;product_update;account;account_asset;report;report_user;report_transaction;system;platform_add;platform_query;platform_update;schedule','2019-01-17 16:17:58.000','2019-06-12 02:16:29.328');

/*Data for the table `customer_info` */

insert  into `customer_info`(`id`,`name`,`priority`,`create_time`,`update_time`) values (585411749436592128,'安泰',99,'2019-06-04 02:17:17.491','2019-06-04 02:18:07.452'),(585412113078554624,'安泰纳',98,'2019-06-04 02:18:44.189','2019-06-04 02:18:44.189'),(585412182536228864,'广美达',97,'2019-06-04 02:19:00.749','2019-06-04 02:19:00.749'),(585413670566236160,'佰奥',96,'2019-06-04 02:24:55.523','2019-06-04 02:24:55.523'),(588057192117178368,'ccc',0,'2019-06-11 09:29:20.175','2019-06-11 11:41:42.889'),(588057211822018560,'bbb',0,'2019-06-11 09:29:24.873','2019-06-11 09:29:24.873');

/*Data for the table `order_delivery_detail` */

/*Data for the table `order_info` */

insert  into `order_info`(`id`,`customer_id`,`customer_product_code`,`product_code`,`amount`,`delivery_amount`,`remaining_amount`,`status`,`create_time`,`update_time`) values (1,585411749436592128,'140.00604A','C13047100','1000.00','0.00','0.00',0,'2019-06-04 15:47:15.000','2019-06-04 15:47:17.000');

/*Data for the table `product_info` */

insert  into `product_info`(`id`,`product_code`,`product_name`,`model`,`type`,`product_unit`,`create_time`,`update_time`) values (585413003961307136,'C13047100','主机外壳','PS-E109主机外壳  丝印在孔下方',0,0,'2019-06-04 02:22:16.592','2019-06-04 02:22:16.592'),(585413459588550656,'YTZ013272','7.2铜针','挤注无电镀铜针 7.25*2.5mm',0,0,'2019-06-04 02:24:05.222','2019-06-04 02:24:05.222'),(585413918894198784,'P15018100','电源盒','G3电源盒 底壳 PC 白色 佰奥',0,0,'2019-06-04 02:25:54.729','2019-06-04 02:25:54.729'),(585414069155139584,'P15018110','电源盒','G3电源盒 面壳 191*52.5*36.75 PC 白色 佰奥',1,0,'2019-06-04 02:26:30.554','2019-06-12 03:30:06.290');

/*Data for the table `storage_detail` */

/*Data for the table `storage_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
