CREATE TABLE `t_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(128) NOT NULL DEFAULT '' COMMENT '账号',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码 加密',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_account` (`account`) USING BTREE,
  UNIQUE KEY `uniq_mail` (`mail`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='账号表';

CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(256) NOT NULL DEFAULT '' COMMENT '头像',
  `sex` char(1) NOT NULL DEFAULT '' COMMENT '性别 M/W',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_user` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='用户表';