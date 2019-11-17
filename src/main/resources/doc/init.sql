-- 创建数据库
CREATE DATABASE commerce;

-- 使用数据库
use commerce;

-- v1.1 创建表
CREATE TABLE `commerce`.`product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` char(30) NOT NULL COMMENT '商品名称',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存',
  `total_sold` int(11) NOT NULL DEFAULT '0' COMMENT '商品已售',
  `price` bigint(20) unsigned DEFAULT '0' COMMENT '商品售价',
  `classification_id` int(11) NOT NULL DEFAULT 0 COMMENT '分类id',
  `member_id` int(11) NOT NULL COMMENT '用户id',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_name` (`product_name`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE `commerce`.`product_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) unsigned NOT NULL COMMENT '商品id',
  `total` int(11) unsigned NOT NULL COMMENT '购买数量',
  `price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品金额',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `status` tinyint(2) unsigned DEFAULT '0' COMMENT '状态：0待支付 1已支付 2已过期 3已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `product_name` char(30) NOT NULL DEFAULT '' COMMENT '商品名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_member_id_status` (`member_id`,`status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单';

CREATE TABLE `commerce`.`product_bill` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` int(11) NOT NULL COMMENT '用户id',
  `assets_money` bigint(20) DEFAULT '0' COMMENT '资产金额',
  `assets_remark` varchar(100) DEFAULT '' COMMENT '资产简介',
  `rights_money` bigint(20) DEFAULT '0' COMMENT '权益金额',
  `rights_remark` varchar(100) DEFAULT '' COMMENT '权益简介',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `classification_id` int(11) NOT NULL DEFAULT '0' COMMENT '分类id',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复式记账表';

CREATE TABLE `commerce`.`classification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classification` char(50) NOT NULL DEFAULT '' COMMENT '分类',
  `classification_name` char(50) NOT NULL DEFAULT '' COMMENT '分类名称',
  `is_delete` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 商品增加过期时间和状态
ALTER TABLE `commerce`.`product` ADD column `expired_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  ADD COLUMN `product_status` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '状态：0售卖，1下架，2过期';

