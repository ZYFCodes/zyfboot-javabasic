CREATE TABLE `common_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(256) NOT NULL DEFAULT '' COMMENT '字典code',
  `dict_code_name` varchar(256) NOT NULL DEFAULT '' COMMENT '字典code中文名',
  `dict_value` varchar(256) NOT NULL DEFAULT '' COMMENT '字典value',
  `dict_value_name` varchar(256) NOT NULL DEFAULT '' COMMENT '字典value中文名',
  `dict_value_desc` varchar(256) NOT NULL DEFAULT '' COMMENT '字典value描述',
  `ctime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `utime` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `creator` varchar(256) NOT NULL DEFAULT '' COMMENT '创建人',
  `last_operator` varchar(256) NOT NULL DEFAULT '' COMMENT '上次操作人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dict_code_dict_value` (`dict_code`,`dict_value`)
) ENGINE=InnoDB AUTO_INCREMENT=649 DEFAULT CHARSET=utf8mb4 COMMENT='通用字典表'