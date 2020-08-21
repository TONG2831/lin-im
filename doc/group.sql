CREATE TABLE `t_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) NOT NULL DEFAULT '' COMMENT '群名称',
  `group_avatar` varchar(256) NOT NULL DEFAULT '' COMMENT '群头像',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='群表';

CREATE TABLE `t_group_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `avatar` varchar(256) NOT NULL DEFAULT '' COMMENT '用户头像',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '群id',
  `identity` tinyint(4) NOT NULL DEFAULT '0' COMMENT '成员身份 0:普通成员 1:群主',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_group_user` (`group_id`,`uid`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='群成员表';

CREATE TABLE `t_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `from` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `avatar` varchar(256) NOT NULL DEFAULT '' COMMENT '用户头像',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型 0:单对单 1:多人',
  `to` bigint(20) NOT NULL DEFAULT '0' COMMENT '单对单:用户id 多人:群id',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '消息内容',
  `create_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_msg_to_from` (`msg_type`,`to`,`from`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='消息表';