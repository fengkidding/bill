-- 金额备忘表
CREATE TABLE `commerce`.`money_memo` (
  `money_memo_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` INT(11) NOT NULL COMMENT '用户id',
  `amount` DECIMAL(11,2) DEFAULT 0 COMMENT '金额',
  `money_type` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '类型：0资产，1负债',
  `description` char(50) NOT NULL DEFAULT '' COMMENT '描述',
  `is_delete` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`money_memo_id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='金额备忘表';
