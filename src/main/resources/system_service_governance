#############服务治理---绑定关系表

CREATE TABLE `t_binding` (
                             `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `name` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
                             `info` varchar(1000) NOT NULL DEFAULT '' COMMENT '信息描述',
                             `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                             `group` varchar(50) NOT NULL DEFAULT '' COMMENT '应用分组',
                             `cluster` varchar(50) NOT NULL DEFAULT '' COMMENT '数据中心',
                             `version` varchar(50) NOT NULL DEFAULT '' COMMENT '版本',
                             `instance_ids` varchar(2000) NOT NULL DEFAULT '' COMMENT '实例ID',
                             `tag` varchar(100) NOT NULL DEFAULT '' COMMENT '标签',
                             `priority` int(10) NOT NULL DEFAULT '0' COMMENT '优先级',
                             `creator` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
                             `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB AUTO_INCREMENT = 122 DEFAULT CHARSET = utf8mb4 COMMENT = '绑定关系表';

#############服务治理---路由规则表

CREATE TABLE `t_route_rule` (
                                `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `binding_id` int(11) unsigned NOT NULL COMMENT '绑定关系的id',
                                `appcode` varchar(50) NOT NULL DEFAULT '' COMMENT '应用code',
                                `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,已下发:1，未下发:0',
                                `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
                                `group` varchar(50) NOT NULL DEFAULT '' COMMENT '应用分组',
                                `cluster` varchar(50) NOT NULL DEFAULT '' COMMENT '数据中心',
                                `version` varchar(50) NOT NULL DEFAULT '' COMMENT '版本',
                                `instance_ids` varchar(2000) NOT NULL DEFAULT '' COMMENT '实例ID',
                                `priority` int(10) NOT NULL DEFAULT '0' COMMENT '优先级',
                                `weight` double NOT NULL DEFAULT '0' COMMENT '权重',
                                `desc` varchar(100) NOT NULL DEFAULT '' COMMENT '描述',
                                `condition` varchar(1000) NOT NULL DEFAULT '' COMMENT '过滤条件',
                                `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                KEY `idx_appcode` (`appcode`)
) ENGINE = InnoDB AUTO_INCREMENT = 103 DEFAULT CHARSET = utf8mb4 COMMENT = '路由规则表';
