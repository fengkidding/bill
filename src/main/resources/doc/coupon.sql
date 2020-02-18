
-- 券码表
create table `coupon`(
  `coupon_id` int (11) unsigned not null AUTO_INCREMENT comment '主键',
  `coupon_code` char (30) not null comment '券码',
  `coupon_status` tinyint(2) unsigned not null default 0 comment '券码状态：0未使用，1以适应，2已过期，3已退款',
  `order_id` int (11) unsigned not null default 0 comment '订单id',
  `product_id` int (11) unsigned not null default 0 comment '产品id',
  `create_time` datetime default current_timestamp comment '创建时间',
  `update_time` datetime default current_timestamp comment '更新时间',
  primary key (`coupon_id`),
  key `idx_coupon_code` (`coupon_code`(10)),
  key `idx_order_id` (`order_id`),
  key `idx_product_id` (`product_id`),
  key `idx_create_time` (`create_time`),
  key `idx_update_time` (`update_time`)
) ENGINE = InnoDB default charset = utf8mb4 comment = '券码表';