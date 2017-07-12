CREATE TABLE `hfsec_menu` (
  `hfsec_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `hfsec_menu_code` varchar(64) DEFAULT NULL COMMENT '菜单编码',
  `hfsec_menu_name` varchar(128) DEFAULT NULL COMMENT '菜单名称',
  `hfsec_menu_desc` varchar(128) DEFAULT NULL COMMENT '菜单描述',
  `menu_level` int(2) DEFAULT NULL COMMENT '菜单级别',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `url` varchar(128) DEFAULT NULL COMMENT '地址',
  `parent_hfsec_menu_id` bigint(20) DEFAULT NULL COMMENT '父级菜单ID',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `hfpm_module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `pri` decimal(6,2) DEFAULT NULL COMMENT '优先级',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfsec_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;


CREATE TABLE `rpt_user_query` (
  `rpt_user_query_name` varchar(128) DEFAULT NULL COMMENT '用户查询名称',
  `rpt_user_query_desc` varchar(128) DEFAULT NULL COMMENT '用户查询描述',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `rpt_user_query_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '用户查询id',
  `rpt_user_query_code` varchar(64) DEFAULT NULL COMMENT '用户查询编码',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`rpt_user_query_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sec_org` (
  `sec_org_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `sec_org_type_id` bigint(20) DEFAULT NULL COMMENT '组织类型ID',
  `sec_org_name` int(11) DEFAULT NULL COMMENT '组织名称',
  PRIMARY KEY (`sec_org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sec_org_type` (
  `sec_org_type_name` int(11) DEFAULT NULL COMMENT '组织类型名称',
  `sec_org_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织类型ID',
  PRIMARY KEY (`sec_org_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `test` (
  `test` int(12) NOT NULL AUTO_INCREMENT COMMENT 'test',
  PRIMARY KEY (`test`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
