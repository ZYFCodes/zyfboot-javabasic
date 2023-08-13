CREATE TABLE `t_alert_channel` (
                                   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                                   `alert_id` int(11) NOT NULL DEFAULT '0' COMMENT '告警id',
                                   `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '告警通知渠道类型,1:weChat企业微信,2:email邮箱,3:message短信,4:voice语音',
                                   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
                                   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`),
                                   KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB AUTO_INCREMENT = 643 DEFAULT CHARSET = utf8mb4 COMMENT = '告警通道表';

CREATE TABLE `t_alert_config` (
                                  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `mi_id` int(11) NOT NULL DEFAULT '0' COMMENT '指标的主键id',
                                  `mi_name` varchar(100) NOT NULL DEFAULT '' COMMENT '指标名称',
                                  `alert_name` varchar(100) NOT NULL DEFAULT '' COMMENT '报警名称',
                                  `appcode` varchar(200) NOT NULL DEFAULT '' COMMENT 'app编码',
                                  `prome_expre` varchar(1000) NOT NULL DEFAULT '' COMMENT '表达式',
                                  `conditions` varchar(1000) NOT NULL DEFAULT '' COMMENT '过滤条件',
                                  `threshold` double NOT NULL DEFAULT '0' COMMENT '阈值',
                                  `time_duration` varchar(45) NOT NULL DEFAULT '' COMMENT '持续时间',
                                  `summary` varchar(1000) NOT NULL DEFAULT '' COMMENT '告警内容',
                                  `status` int(4) NOT NULL DEFAULT '0' COMMENT '告警规则开通状态',
                                  `identifier` varchar(25) NOT NULL DEFAULT '' COMMENT '比较标识符',
                                  `create_user` varchar(1000) NOT NULL DEFAULT 'default_user' COMMENT '创建人',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 524 DEFAULT CHARSET = utf8mb4 COMMENT = '告警配置表';

CREATE TABLE `t_alert_record` (
                                  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `mi_id` int(11) NOT NULL DEFAULT '0' COMMENT '指标的主键id',
                                  `mi_name` varchar(1000) NOT NULL DEFAULT '' COMMENT '指标名称',
                                  `alert_id` int(11) NOT NULL DEFAULT '0' COMMENT '告警id',
                                  `alert_name` varchar(200) NOT NULL DEFAULT '' COMMENT '告警名称',
                                  `appcode` varchar(200) NOT NULL DEFAULT '' COMMENT 'app编码',
                                  `receivers` varchar(1000) NOT NULL DEFAULT '' COMMENT '告警接收人',
                                  `threshold_value` double NOT NULL DEFAULT '0' COMMENT '阈值',
                                  `current_value` double NOT NULL DEFAULT '0' COMMENT '当前报警值',
                                  `summary` varchar(1000) NOT NULL DEFAULT '' COMMENT '告警详情描述',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
                                  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 42037 DEFAULT CHARSET = utf8mb4 COMMENT = '告警记录表';

CREATE TABLE `t_channel_record` (
                                    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `direct_alert_record_id` int(11) NOT NULL DEFAULT '0' COMMENT '记录表的id',
                                    `channel` varchar(36) NOT NULL DEFAULT '' COMMENT '发送通道',
                                    `receivers` varchar(1000) NOT NULL DEFAULT '' COMMENT '接收人',
                                    `send_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '发送状态',
                                    `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
                                    `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                                    `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 123 DEFAULT CHARSET = utf8mb4 COMMENT = '发送通道记录表';

CREATE TABLE `t_direct_alert_record` (
                                         `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                                         `uuid` varchar(50) NOT NULL DEFAULT '' COMMENT '请求的唯一标识,用于做数据幂等',
                                         `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                                         `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                                         PRIMARY KEY (`id`),
                                         KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB AUTO_INCREMENT = 89 DEFAULT CHARSET = utf8mb4 COMMENT = '直接发送告警记录表';

CREATE TABLE `t_dynamic_server_list` (
                                         `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `monitor_init_id` int(11) NOT NULL DEFAULT '0' COMMENT '监控初始化表id',
                                         `env_types` varchar(36) NOT NULL DEFAULT '' COMMENT '用户选择的环境',
                                         `tags` varchar(200) NOT NULL DEFAULT '' COMMENT '标签',
                                         `port` int(10) NOT NULL DEFAULT '0' COMMENT '端口信息',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                                         `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                                         PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 632 DEFAULT CHARSET = utf8mb4 COMMENT = '动态绑定实例列表';

CREATE TABLE `t_monitor_indicator` (
                                       `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                       `name` varchar(100) NOT NULL DEFAULT '' COMMENT '指标名称',
                                       `appcode` varchar(200) NOT NULL DEFAULT '' COMMENT 'ispublic=false的时候指定所属的appcode',
                                       `prom_expre` varchar(1000) NOT NULL DEFAULT '' COMMENT 'prometheus表达式',
                                       `condition` varchar(1000) NOT NULL DEFAULT '' COMMENT '过滤条件',
                                       `desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '监控指标描述',
                                       `ispublic` int(11) NOT NULL DEFAULT '1' COMMENT '是否是公有,公有的话可以被大家看到,私有只能被自己所属的appcode看到',
                                       `common_receiver` varchar(1000) NOT NULL DEFAULT '' COMMENT '通用接收人',
                                       `create_user` varchar(1000) NOT NULL DEFAULT 'default_user' COMMENT '创建人',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 DEFAULT CHARSET = utf8mb4 COMMENT = '监控指标表';

CREATE TABLE `t_monitor_indicator_condition` (
                                                 `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                                 `mi_id` int(11) NOT NULL DEFAULT '0' COMMENT '指标的主键id',
                                                 `label_key` varchar(100) NOT NULL DEFAULT '' COMMENT '过滤条件关键字',
                                                 `label_operation` varchar(100) NOT NULL DEFAULT '' COMMENT '运算符',
                                                 `label_value` varchar(100) NOT NULL DEFAULT '' COMMENT '过滤条件值',
                                                 `label_type` varchar(100) NOT NULL DEFAULT '' COMMENT '过滤条件类型',
                                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                 `update_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '更新时间',
                                                 PRIMARY KEY (`id`),
                                                 UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 123 DEFAULT CHARSET = utf8mb4 COMMENT = '监控过滤条件表';

CREATE TABLE `t_monitor_init` (
                                  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `appcode` varchar(200) NOT NULL DEFAULT '' COMMENT 'appcode',
                                  `discovery_type` varchar(20) NOT NULL DEFAULT 'dynamic' COMMENT '发现方式',
                                  `end_point` varchar(1000) NOT NULL DEFAULT '' COMMENT '暴露端点',
                                  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '激活状态',
                                  `isvaild` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
                                  `http_type` varchar(100) NOT NULL DEFAULT '' COMMENT 'http协议类型',
                                  `static_ip_list` varchar(1000) NOT NULL DEFAULT '' COMMENT '静态ip列表',
                                  `scrape_interval` varchar(50) NOT NULL DEFAULT '' COMMENT '抓取间隔',
                                  `scrape_out` varchar(50) NOT NULL DEFAULT '' COMMENT '抓取timeout',
                                  `create_user` varchar(150) NOT NULL DEFAULT 'default_user' COMMENT '创建人',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 349 DEFAULT CHARSET = utf8mb4 COMMENT = '监控初始化表';

CREATE TABLE `t_params` (
                            `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `monitor_init_id` int(10) NOT NULL DEFAULT '0' COMMENT '监控初始化表id',
                            `params_key` varchar(25) NOT NULL DEFAULT '' COMMENT 'params的key',
                            `params_values` varchar(500) NOT NULL DEFAULT '' COMMENT 'params的values,多个用逗号隔开',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
                            `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
                            PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 66 DEFAULT CHARSET = utf8mb4 COMMENT = 't_params配置表';
