CREATE TABLE `access` (
  `access_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `student_id` bigint(12) DEFAULT NULL COMMENT '学生id',
  `schedule_id` bigint(12) DEFAULT NULL COMMENT '排期表id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`access_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `classs` (
  `classs_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `classs_code` varchar(64) DEFAULT NULL COMMENT '班级编码',
  `classs_name` varchar(128) DEFAULT NULL COMMENT '班级名称',
  `college_id` bigint(12) DEFAULT NULL COMMENT '学院id',
  `specialty_id` bigint(12) DEFAULT NULL COMMENT '专业id',
  `grade_id` bigint(12) DEFAULT NULL COMMENT '年级id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`classs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `college` (
  `college_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `college_type` tinyint(4) DEFAULT NULL COMMENT '学院类型',
  `college_code` varchar(64) DEFAULT NULL COMMENT '学院编码',
  `college_name` varchar(128) DEFAULT NULL COMMENT '学院名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `course` (
  `course_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_code` varchar(64) DEFAULT NULL COMMENT '课程编码',
  `course_name` varchar(128) DEFAULT NULL COMMENT '课程名称',
  `course_type` tinyint(4) DEFAULT NULL COMMENT '课程类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `grade` (
  `grade_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `grade_code` varchar(64) DEFAULT NULL COMMENT '年级编码',
  `grade_name` varchar(128) DEFAULT NULL COMMENT '年级名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


CREATE TABLE `hfsec_organize` (
  `hfsec_organize_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `hfsec_organize_code` varchar(64) DEFAULT NULL COMMENT '组织编码',
  `hfsec_organize_name` varchar(128) DEFAULT NULL COMMENT '组织名称',
  `hfsec_organize_type` tinyint(4) DEFAULT NULL COMMENT '组织类型',
  `hfsec_organize_level` tinyint(4) DEFAULT NULL COMMENT '组织级别',
  `parent_hfsec_organize_level` bigint(12) DEFAULT NULL COMMENT '上级组织id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfsec_organize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
  PRIMARY KEY (`hfsec_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `schedule` (
  `schedule_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '排期表id',
  `teaching_relation_id` bigint(12) DEFAULT NULL COMMENT '授课关系id',
  `schedule_date` date DEFAULT NULL COMMENT '日期',
  `time_interval` tinyint(4) DEFAULT NULL COMMENT '时段',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `score` (
  `score_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `student_id` bigint(12) DEFAULT NULL COMMENT '学生id',
  `course_id` bigint(12) DEFAULT NULL COMMENT '课程id',
  `grade_id` bigint(12) DEFAULT NULL COMMENT '年级id',
  `score_score` int(11) DEFAULT NULL COMMENT '分数',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `specialty` (
  `specialty_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `specialty_code` varchar(64) DEFAULT NULL COMMENT '专业编码',
  `specialty_name` varchar(128) DEFAULT NULL COMMENT '专业名称',
  `college_id` bigint(12) DEFAULT NULL COMMENT '学院id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`specialty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `student` (
  `student_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `student_code` varchar(64) DEFAULT NULL COMMENT '学生编码',
  `student_name` varchar(128) DEFAULT NULL COMMENT '学生名称',
  `student_desc` varchar(128) DEFAULT NULL COMMENT '学生描述',
  `classs_id` bigint(12) DEFAULT NULL COMMENT '班级id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `teacher` (
  `teacher_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `teacher_code` varchar(64) DEFAULT NULL COMMENT '教师编码',
  `teacher_name` varchar(128) DEFAULT NULL COMMENT '教师名称',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `teaching_relation` (
  `teaching_relation_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '授课关系id',
  `teacher_id` bigint(12) DEFAULT NULL COMMENT '教师id',
  `grade_id` bigint(12) DEFAULT NULL COMMENT '年级id',
  `classs_id` bigint(12) DEFAULT NULL COMMENT '班级id',
  `course_id` bigint(12) DEFAULT NULL COMMENT '课程id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`teaching_relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
