CREATE TABLE `cfg_broker` (
  `cfg_broker_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '消息队列id',
  `cfg_broker_name` varchar(128) DEFAULT NULL COMMENT '消息队列名称',
  `cfg_broker_code` varchar(64) DEFAULT NULL COMMENT '消息队列编码',
  `cfg_broker_type` tinyint(4) DEFAULT NULL COMMENT '消息队列类型',
  `addr_list` varchar(64) DEFAULT NULL COMMENT '地址列表',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `zk_addr_list` varchar(64) DEFAULT NULL COMMENT 'zk地址列表',
  PRIMARY KEY (`cfg_broker_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='消息队列';


CREATE TABLE `cfg_datasource` (
  `cfg_datasource_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '数据源id',
  `cfg_datasource_type` tinyint(4) DEFAULT NULL COMMENT '数据库类型',
  `url` varchar(64) DEFAULT NULL COMMENT 'URL',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `db` varchar(64) DEFAULT NULL COMMENT '数据库名称',
  PRIMARY KEY (`cfg_datasource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='数据源';


CREATE TABLE `cfg_node` (
  `cfg_node_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `cfg_node_code` varchar(64) DEFAULT NULL COMMENT '节点编码',
  `cfg_node_name` varchar(128) DEFAULT NULL COMMENT '节点名称',
  `ip` varchar(64) DEFAULT NULL COMMENT '节点IP',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='节点';


CREATE TABLE `cfg_node_task_relat` (
  `cfg_node_task_relat_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '任务节点关系id',
  `cfg_node_id` bigint(12) DEFAULT NULL COMMENT '节点id',
  `cfg_task_inst_id` bigint(12) DEFAULT NULL COMMENT '任务实例id',
  `is_main_node` tinyint(4) DEFAULT NULL COMMENT '是否主机',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_node_task_relat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='任务节点部署';


CREATE TABLE `cfg_statistics` (
  `cfg_statistics_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '流量统计id',
  `cfg_statistics_name` varchar(128) DEFAULT NULL COMMENT '流量统计名称',
  `cfg_datasource_id` bigint(12) DEFAULT NULL COMMENT '数据源id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `log_begin_file` varchar(64) DEFAULT NULL COMMENT '日志起始文件',
  `log_begin_position` bigint(20) DEFAULT NULL COMMENT '日志起始位置',
  `log_begin_timestamp` datetime DEFAULT NULL COMMENT '日志起始时间',
  `log_end_file` varchar(64) DEFAULT NULL COMMENT '日志终止文件',
  `log_end_position` bigint(20) DEFAULT NULL COMMENT '日志终止位置',
  `log_end_timestamp` datetime DEFAULT NULL COMMENT '日志终止时间',
  PRIMARY KEY (`cfg_statistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='流量统计';


CREATE TABLE `cfg_statistics_detail` (
  `cfg_statistics_detail_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '流量统计明细id',
  `cfg_statistics_id` bigint(12) DEFAULT NULL COMMENT '流量统计id',
  `db_object_name` varchar(64) DEFAULT NULL COMMENT '数据对象名称',
  `db_object_operate_type` tinyint(4) DEFAULT NULL COMMENT '数据操作类型',
  `data_filter_expression` varchar(512) DEFAULT NULL COMMENT '数据过滤表达式',
  `cfg_statistics_topic_id` bigint(12) DEFAULT NULL COMMENT '统计主题id',
  `statistics_view` varchar(64) DEFAULT NULL COMMENT '统计维度',
  `statistics_value` varchar(512) DEFAULT NULL COMMENT '统计值',
  `count` tinyint(4) DEFAULT NULL COMMENT '是否计数',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `statistics_script` text COMMENT '统计脚本',
  PRIMARY KEY (`cfg_statistics_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='流量统计明细';


CREATE TABLE `cfg_statistics_topic` (
  `cfg_statistics_topic_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '统计主题id',
  `cfg_statistics_topic_code` varchar(64) DEFAULT NULL COMMENT '统计主题编码',
  `cfg_statistics_topic_name` varchar(128) DEFAULT NULL COMMENT '统计主题名称',
  `cfg_statistics_topic_desc` varchar(128) DEFAULT NULL COMMENT '统计主题描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_statistics_topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='统计主题';


CREATE TABLE `cfg_subscribe` (
  `cfg_subscribe_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '订阅id',
  `type` smallint(6) DEFAULT NULL COMMENT '订阅类型',
  `cfg_datasource_id` bigint(12) DEFAULT NULL COMMENT '数据源id',
  `db_object_name` varchar(64) DEFAULT NULL COMMENT '数据对象名称',
  `data_filter_expression` varchar(1000) DEFAULT NULL COMMENT '数据过滤表达式',
  `cfg_topic_id` bigint(12) DEFAULT NULL COMMENT '主题id',
  `partition_strategy` tinyint(4) DEFAULT NULL COMMENT '分区策略',
  `partition_key` varchar(64) DEFAULT NULL COMMENT '分区属性',
  `log_begin_position` bigint(20) DEFAULT NULL COMMENT '日志起始位置',
  `log_begin_timestamp` datetime DEFAULT NULL COMMENT '日志起始时间',
  `log_end_position` bigint(20) DEFAULT NULL COMMENT '日志终止位置',
  `log_end_timestamp` datetime DEFAULT NULL COMMENT '日志终止时间',
  `log_current_position` bigint(20) DEFAULT NULL COMMENT '当前日志执行位置',
  `cfg_subscribe_name` varchar(128) DEFAULT NULL COMMENT '订阅名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `log_begin_file` varchar(64) DEFAULT NULL COMMENT '日志起始文件',
  `log_end_file` varchar(64) DEFAULT NULL COMMENT '日志终止文件',
  `cfg_broker_id` bigint(12) DEFAULT NULL COMMENT '消息队列id',
  `db_object_operate_type` tinyint(4) DEFAULT NULL COMMENT '数据操作类型',
  PRIMARY KEY (`cfg_subscribe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='订阅';


CREATE TABLE `cfg_subscribe_data` (
  `cfg_subscribe_data_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '订阅数据id',
  `cfg_subscribe_data_type` tinyint(4) DEFAULT NULL COMMENT '订阅数据类型',
  `cfg_subscribe_data_code` varchar(64) DEFAULT NULL COMMENT '订阅数据编码',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `contain_change_before_value` tinyint(4) DEFAULT NULL COMMENT '订阅变更前数据',
  `cfg_subscribe_id` bigint(12) DEFAULT NULL COMMENT '订阅id',
  PRIMARY KEY (`cfg_subscribe_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8 COMMENT='订阅数据';


CREATE TABLE `cfg_subscribe_detail` (
  `cfg_subscribe_detail_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '数据订阅明细id',
  `cfg_subscribe_id` bigint(12) DEFAULT NULL COMMENT '订阅id',
  `db_object_name` varchar(64) DEFAULT NULL COMMENT '数据对象名称',
  `cfg_topic_id` bigint(12) DEFAULT NULL COMMENT '主题id',
  `db_object_datas` varchar(1024) DEFAULT NULL COMMENT '数据对象数据',
  `partition_strategy` tinyint(4) DEFAULT NULL COMMENT '分区策略',
  `partition_key` varchar(64) DEFAULT NULL COMMENT '分区属性',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `data_filter_expression` varchar(512) DEFAULT NULL COMMENT '数据过滤表达式',
  `db_object_operate_type` tinyint(4) DEFAULT NULL COMMENT '数据操作类型',
  PRIMARY KEY (`cfg_subscribe_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='数据订阅明细';


CREATE TABLE `cfg_task_def` (
  `cfg_task_def_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '任务定义id',
  `cfg_task_def_code` varchar(64) DEFAULT NULL COMMENT '任务定义编码',
  `cfg_task_def_name` varchar(128) DEFAULT NULL COMMENT '任务定义名称',
  `cfg_task_def_type` tinyint(4) DEFAULT NULL COMMENT '任务定义类型',
  `cfg_task_instance_type` tinyint(4) DEFAULT NULL COMMENT '任务实例类型',
  `param_name_1` varchar(64) DEFAULT NULL COMMENT '任务参数名称',
  `param_code_1` varchar(64) DEFAULT NULL COMMENT '任务参数编码',
  `param_name_2` varchar(64) DEFAULT NULL COMMENT '任务参数名称2',
  `param_code_2` varchar(64) DEFAULT NULL COMMENT '任务参数编码2',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_task_def_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='任务定义';


CREATE TABLE `cfg_task_inst` (
  `cfg_task_inst_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '任务实例id',
  `cfg_task_inst_desc` varchar(128) DEFAULT NULL COMMENT '任务实例描述',
  `cfg_task_def_id` bigint(12) DEFAULT NULL COMMENT '任务定义id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `param_value_1` varchar(128) DEFAULT NULL COMMENT '任务参数值',
  `param_value_remark_1` varchar(128) DEFAULT NULL COMMENT '任务参数值描述',
  `param_value_2` varchar(128) DEFAULT NULL COMMENT '任务参数值2',
  `param_value_remark_2` varchar(128) DEFAULT NULL COMMENT '任务参2数值描述2',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_task_inst_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='任务实例';


CREATE TABLE `cfg_task_node_def` (
  `cfg_task_node_def_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '任务节点定义id',
  `cfg_task_node_def_name` varchar(128) DEFAULT NULL COMMENT '任务节点定义名称',
  `cfg_task_node_def_code` varchar(64) DEFAULT NULL COMMENT '任务节点定义编码',
  `java_class` varchar(128) DEFAULT NULL COMMENT '任务节点实现类',
  `cfg_task_def_id` bigint(12) DEFAULT NULL COMMENT '任务定义id',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cfg_task_node_def_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='任务子节点定义';


CREATE TABLE `cfg_topic` (
  `cfg_topic_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主题id',
  `cfg_topic_name` varchar(128) DEFAULT NULL COMMENT '主题名称',
  `cfg_topic_desc` varchar(128) DEFAULT NULL COMMENT '主题描述',
  `cfg_topic_type` tinyint(4) DEFAULT NULL COMMENT '主题类型',
  `cfg_topic_code` varchar(64) DEFAULT NULL COMMENT '主题编码',
  `PARTITIONS` tinyint(4) DEFAULT NULL COMMENT '分区数',
  `replicas` tinyint(4) DEFAULT NULL COMMENT '副本数',
  `serial_no` smallint(4) DEFAULT NULL COMMENT '消息序列生成规则',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `cfg_broker_id` bigint(12) DEFAULT NULL COMMENT '消息队列id',
  PRIMARY KEY (`cfg_topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='主题';


CREATE TABLE `hfmd_enum` (
  `hfmd_enum_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典项ID',
  `hfmd_enum_value` varchar(32) DEFAULT NULL COMMENT '字典项值',
  `hfmd_enum_text` varchar(32) DEFAULT NULL COMMENT '字典项文本',
  `hfmd_enum_desc` varchar(128) DEFAULT NULL COMMENT '字典项描述',
  `is_default` int(2) DEFAULT NULL COMMENT '是否默认',
  `pri` decimal(4,2) DEFAULT NULL COMMENT '优先级',
  `ext1` varchar(128) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) DEFAULT NULL COMMENT '扩展字段2',
  `hfmd_enum_class_id` bigint(20) DEFAULT NULL COMMENT '字典ID',
  `hfmd_enum_class_code` varchar(32) DEFAULT NULL,
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_enum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='字典项';


CREATE TABLE `hfmd_enum_class` (
  `hfmd_enum_class_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `hfmd_enum_class_name` varchar(32) DEFAULT NULL COMMENT '字典名称',
  `hfmd_enum_class_code` varchar(64) DEFAULT NULL COMMENT '字典编码',
  `hfmd_enum_class_desc` varchar(128) DEFAULT NULL COMMENT '字典描述',
  `ext1` varchar(128) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) DEFAULT NULL COMMENT '扩展字段2',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_enum_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='字典';


CREATE TABLE `hfsec_menu` (
  `hfsec_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `hfsec_menu_code` varchar(64) DEFAULT NULL COMMENT '菜单编码',
  `hfsec_menu_name` varchar(128) DEFAULT NULL COMMENT '菜单名称',
  `hfsec_menu_desc` varchar(128) DEFAULT NULL COMMENT '菜单描述',
  `menu_level` int(2) DEFAULT NULL COMMENT '菜单级别',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `url` varchar(128) DEFAULT NULL COMMENT '地址',
  `parent_hfsec_menu_id` bigint(20) DEFAULT NULL COMMENT '父级菜单ID',
  `pri` decimal(4,2) DEFAULT NULL COMMENT '优先级',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfsec_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='菜单';


CREATE TABLE `hfsec_organize` (
  `hfsec_organize_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `hfsec_organize_code` varchar(64) DEFAULT NULL COMMENT '组织编码',
  `hfsec_organize_name` varchar(128) DEFAULT NULL COMMENT '组织名称',
  `hfsec_organize_type` tinyint(4) DEFAULT NULL COMMENT '组织类型',
  `hfsec_organize_level` tinyint(4) DEFAULT NULL COMMENT '组织级别',
  `parent_hfsec_organize_id` bigint(12) DEFAULT NULL COMMENT '上级组织id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfsec_organize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='组织';


CREATE TABLE `hfsec_role` (
  `hfsec_role_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `hfsec_role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `hfsec_role_name` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `hfsec_role_type` tinyint(4) DEFAULT NULL COMMENT '角色类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfsec_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';


CREATE TABLE `hfsec_role_authorize` (
  `hfsec_role_authorize_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '角色授权id',
  `hfsec_role_authorize_type` tinyint(4) DEFAULT NULL COMMENT '角色授权类型',
  `hfsec_role_id` bigint(12) DEFAULT NULL COMMENT '角色id',
  `hfsec_menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfsec_role_authorize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色授权';


CREATE TABLE `hfsec_user` (
  `hfsec_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `hfsec_user_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `account` varchar(64) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `gender` int(2) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(6) DEFAULT NULL COMMENT '手机号',
  `email` int(2) DEFAULT NULL COMMENT '邮箱',
  `addr` int(2) DEFAULT NULL COMMENT '地址',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `hfuc_org_id` bigint(20) DEFAULT NULL COMMENT '归属组织ID',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `hfsec_organize_id` bigint(20) DEFAULT NULL COMMENT '组织ID',
  PRIMARY KEY (`hfsec_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户';


CREATE TABLE `hfsec_user_authorize` (
  `hfsec_user_authorize_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '用户授权id',
  `hfsec_user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `hfsec_organize_id` bigint(12) DEFAULT NULL COMMENT '组织id',
  `hfsec_role_id` bigint(12) DEFAULT NULL COMMENT '角色id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfsec_user_authorize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户授权';
