#############服务市场---服务功能表
CREATE TABLE `t_srv_func` (
                              `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
                              `desc` varchar(100) NOT NULL DEFAULT '' COMMENT '描述',
                              `type` varchar(50) NOT NULL DEFAULT '' COMMENT '类型',
                              `tags` varchar(200) NOT NULL DEFAULT '' COMMENT '标签，多个用逗号隔开',
                              `opening_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开通的时间',
                              `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `ds_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '服务发现的类型',
                              `provider` varchar(50) NOT NULL DEFAULT '' COMMENT '提供服务功能的appCode',
                              `backend` varchar(200) NOT NULL DEFAULT '' COMMENT '后端地址',
                              `subjects` varchar(1000) NOT NULL DEFAULT '' COMMENT '订阅的事件类型',
                              PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 DEFAULT CHARSET = utf8mb4 COMMENT = '服务功能表';

#############服务市场---服务关联表
CREATE TABLE `t_srv_func_ref` (
                                  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                                  `srv_func_id` int(11) NOT NULL DEFAULT '0' COMMENT '服务功能id',
                                  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开通,0:未开通,1:开通',
                                  `opening_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开通的时间',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB AUTO_INCREMENT = 204 DEFAULT CHARSET = utf8mb4 COMMENT = '服务关联表';

#############服务市场---服务关联表
CREATE TABLE `t_srv_rel_del_` (
                                  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                                  `srv_fun_id` int(11) NOT NULL DEFAULT '0' COMMENT '服务功能id',
                                  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开通,0:未开通,1:开通',
                                  `opening_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开通的时间',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '服务关联表';
