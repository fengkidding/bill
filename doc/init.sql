-- 创建数据库
CREATE DATABASE f;

-- 使用数据库
use f;

-- v1.1 创建表

CREATE TABLE IF NOT EXISTS product(
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `product_name` CHAR(30) NOT NULL COMMENT '商品名称' ,
  `product_type` CHAR(30) NOT NULL COMMENT '商品类别' ,
  `total` INT(11) NOT NULL DEFAULT 0 COMMENT '商品库存' ,
  `total_sold` INT(11) NOT NULL DEFAULT 0 COMMENT '商品已售' ,
  `price` BIGINT(20) UNSIGNED DEFAULT 0 COMMENT '商品售价' ,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY(`id`) ,
  KEY `idx_product_name` (`product_name`) ,
  KEY `idx_create_time` (`create_time`) ,
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE IF NOT EXISTS product_order(
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `order_user` CHAR(10) NOT NULL COMMENT '订单用户' ,
  `product_id` INT(11) UNSIGNED NOT NULL COMMENT '商品id' ,
  `product_type` CHAR(30) NOT NULL COMMENT '商品类别' ,
  `total` INT(11) UNSIGNED NOT NULL COMMENT '购买数量' ,
  `price` BIGINT(20) UNSIGNED DEFAULT 0 NOT NULL COMMENT '商品金额' ,
  `status` TINYINT(2) UNSIGNED DEFAULT 0 COMMENT '状态：0待支付 1已支付 2已过期 3已删除' ,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY(`id`) ,
  KEY `idx_product_id` (`product_id`) ,
  KEY `idx_order_user_status` (`order_user`,`status`) ,
  KEY `idx_create_time` (`create_time`) ,
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品订单';

CREATE TABLE IF NOT EXISTS product_bill(
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `bill_user` CHAR(10) NOT NULL COMMENT '归属用户' ,
  `product_order_id` INT(11) UNSIGNED NOT NULL COMMENT '订单id' ,
  `debit_money` BIGINT(20) DEFAULT 0 COMMENT '借金额' ,
  `debit_remark` VARCHAR(100) DEFAULT '' COMMENT '借简介' ,
  `credit_money` BIGINT(20) DEFAULT 0 COMMENT '贷金额' ,
  `credit_remark` VARCHAR(100) DEFAULT '' COMMENT '贷简介' ,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY(`id`) ,
  KEY `idx_bill_user` (`bill_user`) ,
  KEY `idx_product_order_id` (`product_order_id`) ,
  KEY `idx_create_time` (`create_time`) ,
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复式记账表';

-- 订单表增加商品名称快照
ALTER TABLE product_order ADD COLUMN `product_name` CHAR(30) NOT NULL DEFAULT '' COMMENT '商品名称';

-- 订单增加备注
alter table `f`.`product_order` add column `remark` varchar(255) not null default '' comment '备注';

-- 账单增加商品类别
alter table `f`.`product_bill` add column `product_type` char(30) NOT NULL COMMENT '商品类别';

-- 订单增加类型索引
alter table `f`.`product_order` add index idx_product_type(`product_type`(10));

-- 修改账单字段
alter TABLE `f`.`product_bill` CHANGE `debit_money` `assets_money` BIGINT(20) DEFAULT 0 COMMENT '资产金额',
  CHANGE `debit_remark` `assets_remark` VARCHAR(100) DEFAULT '' COMMENT '资产简介' ,
  CHANGE `credit_money` `rights_money` BIGINT(20) DEFAULT 0 COMMENT '权益金额' ,
CHANGE `credit_remark` `rights_remark` VARCHAR(100) DEFAULT '' COMMENT '权益简介' ;

-- 分类表
CREATE TABLE `f`.`classification`(
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classification` CHAR(50) NOT NULL DEFAULT '' COMMENT '分类',
  `classification_name` CHAR(50) NOT NULL DEFAULT '' COMMENT '分类名称',
  `create_time` DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`) ,
  KEY `idx_update_time` (`update_time`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT '分类表';

-- 账单表减少订单新增分类
ALTER TABLE `f`.`product_bill` DROP COLUMN `product_order_id`,
  DROP COLUMN `product_type`;
ALTER TABLE `f`.`product_bill` ADD COLUMN `classification_id` INT(11) NOT NULL DEFAULT 0 COMMENT '分类id';

-- 删除订单和产品上的分类，加上分类id
ALTER TABLE `f`.`product_order` DROP COLUMN `product_type`;
ALTER TABLE `f`.`product` DROP COLUMN `product_type`;
ALTER TABLE `f`.`product` ADD COLUMN `classification_id` INT(11) NOT NULL DEFAULT 0 COMMENT '分类id';






