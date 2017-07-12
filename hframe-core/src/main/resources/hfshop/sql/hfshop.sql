CREATE TABLE `pc_category` (
  `pc_category_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '产品分类id',
  `pc_category_code` varchar(64) DEFAULT NULL COMMENT '产品分类编码',
  `pc_category_name` varchar(128) DEFAULT NULL COMMENT '产品分类名称',
  `pc_category_desc` varchar(128) DEFAULT NULL COMMENT '产品分类描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pc_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `pc_product` (
  `pc_product_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `pc_product_code` varchar(64) DEFAULT NULL COMMENT '产品编码',
  `pc_product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `pc_product_desc` varchar(128) DEFAULT NULL COMMENT '产品描述',
  `pc_product_type` tinyint(4) DEFAULT NULL COMMENT '产品类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `pc_category_id` bigint(12) DEFAULT NULL COMMENT '产品分类id',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pc_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
