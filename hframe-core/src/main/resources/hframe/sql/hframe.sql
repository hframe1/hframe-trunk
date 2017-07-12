create table hfcfg_component_template(
   hfcfg_component_template_id bigint(12) primary key auto_increment not null comment '组件模板id',
   hfcfg_component_template_code varchar(64) not null comment '组件模板编码',
   hfcfg_component_template_name varchar(128) not null comment '组件模板名称',
   hfcfg_component_template_type tinyint(2) comment '组件模板类型',
   hfcfg_component_template_desc varchar(128) comment '组件模板描述',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '组件模板';


create table hfcfg_db_connect(
   hfcfg_db_connect_id bigint(12) primary key auto_increment not null comment '数据库连接信息id',
   hfcfg_db_connect_code varchar(64) not null comment '数据库连接信息编码',
   hfcfg_db_connect_name varchar(128) not null comment '数据库连接信息名称',
   url varchar(256) not null comment 'URL',
   user varchar(64) not null comment '用户',
   password varchar(64) not null comment '密码',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间') comment '数据库连接信息';


create table hfcfg_login_page(
   hfcfg_login_page_id bigint(20) primary key auto_increment comment '登陆页面ID',
   hfcfg_login_page_name varchar(64) comment '登陆页面名称',
   hfcfg_login_page_code varchar(64) comment '登陆页面编码',
   snapshot_url varchar(128) comment '页面快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '登陆页面';


create table hfcfg_page_template(
   hfcfg_page_template_id bigint(20) primary key auto_increment comment '页面模板ID',
   hfcfg_page_template_type int(2) comment '页面模板类型',
   hfcfg_page_template_name varchar(64) comment '页面模板名称',
   hfcfg_page_template_code varchar(128) comment '页面模板编码',
   hfcfg_page_template_desc varchar(128) comment '页面模板描述',
   snapshot_url varchar(128) comment '快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面模板';


create table hfcfg_page_template_elements(
   hfcfg_page_template_elements_id bigint(12) primary key auto_increment not null comment '页面模板元素id',
   hfcfg_page_template_id bigint(20) comment '页面模板ID',
   hfcfg_page_template_elements_type tinyint(4) comment '页面模板元素类型',
   event_extend tinyint(4) comment '事件继承',
   hfcfg_component_template_id bigint(12) not null comment '组件模板id',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '页面模板元素';


create table hfcfg_program_skin(
   hfcfg_program_skin_id bigint(20) primary key auto_increment comment '项目皮肤ID',
   program_skin_name varchar(64) comment '项目皮肤名称',
   program_skin_code varchar(64) comment '项目皮肤编码',
   snapshot_url varchar(128) comment '快照URL',
   program_template_id bigint(20) comment '项目模板ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目皮肤';


create table hfcfg_program_template(
   hfcfg_program_template_id bigint(20) primary key auto_increment comment '项目模板ID',
   program_template_name varchar(64) comment '项目模板名称',
   program_template_code varchar(64) comment '项目模板编码',
   program_template_desc varchar(128) comment '项目模板描述',
   snapshot_url varchar(128) comment '快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目模板';


create table hfmd_entity(
   hfmd_entity_id bigint(20) primary key auto_increment comment '实体ID',
   hfmd_entity_name varchar(64) not null comment '实体名称',
   hfmd_entity_code varchar(64) not null comment '实体编码',
   hfmd_entity_type int(2) comment '实体类型            ',
   hfmd_entity_desc varchar(124) comment '实体描述',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体';


create table hfmd_entity_attr(
   hfmd_entity_attr_id bigint(20) primary key auto_increment comment '实体属性ID',
   hfmd_entity_attr_name varchar(64) not null comment '实体属性名称',
   hfmd_entity_attr_code varchar(64) not null comment '实体属性编码',
   hfmd_entity_attr_desc varchar(128) comment '实体属性描述',
   attr_type int(2) not null comment '属性类型',
   size varchar(6) comment '大小',
   ispk int(2) comment '是否主键',
   nullable int(2) not null comment '是否为空',
   is_busi_attr int(2) comment '是否业务属性',
   is_redundant_attr int(2) comment '是否冗余属性',
   rel_hfmd_entity_attr_id bigint(20) comment '关联属性ID',
   hfmd_enum_class_id bigint(20) comment '枚举类ID',
   pri numeric(2,2) comment '优先级',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体属性';


create table hfmd_entity_join_rule(
   hfmd_entity_join_rule_id bigint(20) primary key auto_increment not null comment '实体连带id',
   source_hfmd_entity_id bigint(20) comment '源实体id',
   source_hfmd_entity_attr_id bigint(20) comment '源实体属性id',
   source_hfmd_entity_attr_value varchar(64) comment '源实体属性值',
   join_type tinyint(2) comment '连带类型',
   target_hfmd_entity_id bigint(20) comment '目标实体id',
   target_hfmd_entity_attr_id bigint(20) comment '目标实体属性id',
   target_hfmd_entity_attr_value varchar(64) comment '目标实体属性值',
   editable tinyint(2) comment '是否可编辑') comment '实体连带关系';


create table hfmd_entity_rel(
   hfmd_entity_rel_id bigint(20) primary key auto_increment comment '实体关系ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_rel_type int(2) comment '实体关联类型          ',
   hfmd_entity_rel_level int(2) comment '实体关联级别          ',
   hfmd_entity_rel_desc varchar(128) comment '实体关联描述',
   rel_hfmd_entity_id bigint(20) comment '关联实体ID',
   rel_entity_attr_id bigint(20),
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体关系';


create table hfmd_enum(
   hfmd_enum_id bigint(20) primary key auto_increment not null comment '枚举ID',
   hfmd_enum_value varchar(32) comment '枚举值',
   hfmd_enum_text varchar(32) comment '枚举文本',
   hfmd_enum_desc varchar(128) comment '枚举描述',
   is_default int(2) comment '是否默认',
   pri numeric(2,2) comment '优先级',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   hfmd_enum_class_id bigint(20) comment '枚举类目ID',
   hfmd_enum_class_code varchar(32),
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) not null comment '删除标识') comment '枚举';


create table hfmd_enum_class(
   hfmd_enum_class_id bigint(20) primary key auto_increment comment '枚举类目ID',
   hfmd_enum_class_name varchar(32) comment '枚举类目名称',
   hfmd_enum_class_code varchar(64) comment '枚举类目编码',
   hfmd_enum_class_desc varchar(128) comment '枚举类目描述',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '枚举类目';


create table hfpm_data_field(
   hfpm_data_field_id bigint(20) primary key auto_increment comment '数据列ID',
   hfpm_data_field_code varchar(64) comment '数据列编码',
   hfpm_field_show_type_id varchar(32) comment '列展示类型ID',
   field_show_code varchar(6) comment '列展示码',
   create_edit_auth tinyint(4) comment '新增编辑权限',
   update_edit_auth tinyint(4) comment '修改编辑权限',
   list_show_auth tinyint(4) comment '列表展示权限',
   detail_show_auth tinyint(4) comment '明细展示权限',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   data_get_method int(2) comment '数据获取方式',
   hfpm_data_field_name varchar(64) comment '数据列名称',
   workfow_model_id varchar(64) comment '流程ID',
   hfpm_data_set_id bigint(20) comment '数据集ID',
   pri numeric(6,2) comment '优先级',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '数据列';


create table hfpm_data_set(
   hfpm_data_set_id bigint(20) primary key auto_increment comment '数据集ID',
   hfpm_data_set_name varchar(64) comment '数据集名称',
   hfpm_data_set_code varchar(64) comment '数据集编码',
   main_hfmd_entity_id bigint(20) comment '主实体ID',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '数据集';


create table hfpm_entity_bind_rule(
   hfpm_entity_bind_rule_id bigint(20) primary key auto_increment comment '实体捆绑规则ID',
   bind_type int(2) comment '捆绑类型',
   src_hfmd_entity_id bigint(20) comment '源实体ID',
   src_hfmd_entity_attr_id bigint(20) comment '源实体属性ID',
   dest_hfmd_entity_id bigint(20) comment '目标实体ID',
   dest_hfmd_entity_attr_id bigint(20) comment '目标实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体捆绑规则';


create table hfpm_entity_permission(
   hfpm_entity_permission_id bigint(20) primary key auto_increment comment '页面事件属性ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   value_type int(2) comment '值类型',
   value varchar(128) comment '值',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体权限';


create table hfpm_field_show_type(
   hfpm_field_show_type_id bigint(20) primary key auto_increment comment '展示类型ID',
   hfpm_field_show_type_code varchar(32) comment '展示类型编码',
   hfpm_field_show_type_name varchar(32) comment '展示类型名称',
   pre_str varchar(256) comment '前缀',
   after_str varchar(256) comment '后缀',
   col_span int(2) comment '列数',
   row_span int(2) comment '行数',
   width int(11) comment '宽度',
   height int(11) comment '高度',
   param1 varchar(128) comment '参数1',
   param2 varchar(128) comment '参数2',
   param3 varchar(128) comment '参数3',
   param4 varchar(128) comment '参数4',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '展示类型';


create table hfpm_module(
   hfpm_module_id bigint(20) primary key auto_increment comment '模块ID',
   hfpm_module_name varchar(64) comment '模块名称',
   hfpm_module_code varchar(64) comment '模块编码',
   hfpm_module_desc varchar(128) comment '模块描述',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '模块';


create table hfpm_page(
   hfpm_page_id bigint(20) primary key auto_increment not null comment '页面ID',
   hfpm_page_code varchar(64) comment '页面编码',
   hfpm_page_name varchar(128) comment '页面名称',
   hfpm_page_type int(2) comment '页面类型',
   create_time datetime comment '修改时间',
   hfpm_page_desc varchar(128) comment '页面描述',
   pri numeric(6,2) comment '优先级',
   modify_time datetime comment '修改时间',
   modify_op_id bigint(20) comment '修改人',
   parent_hfpm_page_id bigint(20) comment '父页面ID',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   op_id bigint(20) comment '创建人',
   del_flag int(2) comment '删除标识',
   hfcfg_page_template_id bigint(20) comment '页面模板ID') comment '页面';


create table hfpm_page_cfg(
   hfpm_page_cfg_id bigint(20) primary key auto_increment comment '页面设置ID',
   hfcfg_page_template_id bigint(20) comment '页面模板ID',
   hfpm_program_cfg_id bigint(20) comment '项目配置ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目页面设置';


create table hfpm_page_component(
   hfpm_page_component_id bigint(20) primary key auto_increment comment '页面组件ID',
   hfpm_page_component_name varchar(64) comment '页面组件名称',
   hfpm_page_component_type int(2) comment '页面组件类型',
   hfpm_page_id bigint(20) not null comment '页面ID',
   hfpm_data_set_id bigint(20) comment '数据集ID',
   hfcfg_component_template_id bigint(12) not null comment '组件模板id',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面组件';


create table hfpm_page_entity_rel(
   hfpm_page_entity_rel_id bigint(20) primary key auto_increment comment '页面关联实体ID',
   hfpm_page_id bigint(20) comment '页面ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   is_main_entity int(2) comment '是否为主实体',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面关联实体';


create table hfpm_page_event(
   hfpm_page_event_id bigint(12) primary key auto_increment not null comment '页面事件id',
   hfpm_page_event_name varchar(128) not null comment '页面事件名称',
   hfpm_page_event_code varchar(64) not null comment '页面事件编码',
   hfpm_page_id bigint(20) not null comment '页面ID',
   hfpm_page_component_id bigint(20) comment '页面组件ID',
   event_source tinyint(4) comment '事件来源',
   effect_field bigint(20) not null comment '作用域',
   from_condition varchar(200) comment '条件',
   to_result varchar(200) comment '结果',
   hfpm_page_event_type tinyint(4) comment '页面事件类型',
   target_hfpm_page_id bigint(20) comment '目标页面',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '页面事件';


create table hfpm_page_event_attr(
   hfpm_page_event_attr_id bigint(20) primary key auto_increment comment '页面事件属性ID',
   hfpm_page_event_id bigint(20) comment '页面事件ID',
   hfpm_page_event_attr_type int(2) comment '页面事件属性类型        ',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   value_type int(2) comment '值类型',
   value varchar(128) comment '值',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面事件属性';


create table hfpm_program(
   hfpm_program_id bigint(20) primary key auto_increment comment '项目ID',
   hfpm_program_name varchar(64) comment '项目名称',
   hfpm_program_code varchar(64) comment '项目编码',
   hfpm_program_desc varchar(512) comment '项目描述',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目';


create table hfpm_program_cfg(
   hfpm_program_cfg_id bigint(12) primary key auto_increment not null comment '项目设置id',
   show_name varchar(128) comment '项目标题',
   hfcfg_program_template_id bigint(20) comment '项目模板ID',
   hfcfg_program_skin_id bigint(20) comment '项目皮肤ID',
   hfcfg_login_page_id bigint(20) comment '登陆页面ID',
   bg_img_url varchar(128) comment '背景图片URL',
   user_entity_name varchar(128) comment '用户登录实体',
   data_entity_name varchar(128) comment '数据权限实体',
   func_entity_name varchar(128) comment '功能权限实体',
   user_auth_path varchar(128) comment '用户授权路径',
   func_auth_path varchar(128) comment '功能授权路径',
   dictionarys varchar(128) comment '系统字典实体',
   hfpm_program_id bigint(20) comment '项目ID',
   hfcfg_db_connect_id bigint(12) not null comment '数据库连接信息id',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识',
   super_auth_filter_entity bigint(20) comment '超级管理员规则【实体】',
   super_auth_filter_field bigint(20) comment '超级管理员规则【字段】',
   super_auth_filter_field_value varchar(64) comment '超级管理员规则【字段值】',
   user_login_data_set varchar(128) comment '用户登录数据集') comment '项目设置';


create table hfsec_menu(
   hfsec_menu_id bigint(20) primary key auto_increment comment '菜单ID',
   hfsec_menu_code varchar(64) comment '菜单编码',
   hfsec_menu_name varchar(128) comment '菜单名称',
   hfsec_menu_desc varchar(128) comment '菜单描述',
   menu_level int(2) comment '菜单级别',
   icon varchar(64) comment '图标',
   url varchar(128) comment '地址',
   parent_hfsec_menu_id bigint(20) comment '父级菜单ID',
   pri numeric(4,2) comment '优先级',
   creator_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '菜单';


create table hfsec_organize(
   hfsec_organize_id bigint(12) primary key auto_increment not null comment '组织id',
   hfsec_organize_code varchar(64) not null comment '组织编码',
   hfsec_organize_name varchar(128) not null comment '组织名称',
   hfsec_organize_type tinyint(4) comment '组织类型',
   hfsec_organize_level tinyint(4) comment '组织级别',
   parent_hfsec_organize_id bigint(12) comment '上级组织id',
   status tinyint(4) comment '状态',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '组织';


create table hfsec_role(
   hfsec_role_id bigint(12) primary key auto_increment not null comment '角色id',
   hfsec_role_code varchar(64) not null comment '角色编码',
   hfsec_role_name varchar(128) not null comment '角色名称',
   hfsec_role_type tinyint(4) comment '角色类型',
   status tinyint(4) comment '状态',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '角色';


create table hfsec_role_authorize(
   hfsec_role_authorize_id bigint(12) primary key auto_increment not null comment '角色授权id',
   hfsec_role_authorize_type tinyint(4) comment '角色授权类型',
   hfsec_role_id bigint(12) not null comment '角色id',
   hfsec_menu_id bigint(20) comment '菜单ID',
   operate_event_list varchar(128) comment '操作列表',
   status tinyint(4) comment '状态',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '角色授权';


create table hfsec_user(
   hfsec_user_id bigint(20) primary key auto_increment comment '用户ID',
   hfsec_user_name varchar(64) comment '用户名称',
   account varchar(64) comment '用户账号',
   password varchar(128) comment '用户密码',
   gender int(2) comment '性别',
   mobile varchar(6) comment '手机号',
   email int(2) comment '邮箱',
   addr int(2) comment '地址',
   last_login_time datetime comment '上次登录时间',
   status int(2) comment '状态',
   hfsec_organize_id bigint(12) not null comment '组织ID',
   creator_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '用户';


create table hfsec_user_authorize(
   hfsec_user_authorize_id bigint(12) primary key auto_increment not null comment '用户授权id',
   hfsec_user_id bigint(20) comment '用户ID',
   hfsec_organize_id bigint(12) not null comment '组织id',
   hfsec_role_id bigint(12) not null comment '角色id',
   status tinyint(4) comment '状态',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '用户授权';


create table hfus_entity_attr(
   hfus_entity_attr_id bigint(20) primary key auto_increment comment '常用实体属性ID',
   hfus_entity_attr_name varchar(64) comment '实体属性名称',
   hfus_entity_attr_code varchar(64) comment '实体属性编码',
   hfus_entity_attr_desc varchar(128) comment '实体描述',
   attr_type int(2) comment '属性类型',
   size varchar(6) comment '大小',
   ispk int(2) comment '是否主键',
   nullable int(2) comment '是否可为空',
   is_busi_attr int(2) comment '是否业务属性',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '常用实体属性';


create table hfus_entity_store(
   hfus_entity_store_id bigint(12) primary key auto_increment not null comment '常用实体库id',
   hfus_entity_store_name varchar(128) not null comment '常用实体库名称',
   hfus_entity_store_code varchar(64) not null comment '常用实体库编码',
   sql_content text not null comment 'SQL建表语句',
   entity_group tinyint(4) comment '分组',
   hfus_entity_store_type tinyint(4) comment '常用实体库类型',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '常用实体库';


create table hfus_entity_type_relat_entity_attr(
   hfus_entity_type_relat_entity_attr_id bigint(20) primary key auto_increment comment '关系ID',
   entity_type int(2) comment '实体类型',
   hfus_entity_attr_id bigint(20) comment '常用实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体类型关联实体属性';


create table hfus_page_event(
   hfus_page_event_id bigint(20) primary key auto_increment comment '常用页面事件ID',
   hfpm_event_name varchar(32) comment '事件名称',
   hfpm_event_type int(2) comment '事件类型',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '常用页面事件';


create table hfus_program_entity_attr(
   hfus_program_entity_attr_id bigint(20) primary key auto_increment comment '项目常用实体ID',
   hfpm_program_id bigint(20) comment '项目ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目常用实体属性';


create table hfus_word_store(
   hfus_word_store_id bigint(20) primary key auto_increment comment '单词库ID',
   chinese_chars varchar(64) comment '汉字名称',
   english_name varchar(64) comment '英语名称',
   english_short_name varchar(64) comment '英文简称',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '单词库';


alter table hfcfg_page_template_elements add constraint FK_hfcfg_page_template_elements_4_hfcfg_component_template_id foreign key ( hfcfg_component_template_id) references hfcfg_component_template(hfcfg_component_template_id) on delete restrict on update restrict;


alter table hfcfg_page_template_elements add constraint FK_hfcfg_page_template_elements_4_hfcfg_page_template_id foreign key ( hfcfg_page_template_id) references hfcfg_page_template(hfcfg_page_template_id) on delete restrict on update restrict;


alter table hfcfg_program_skin add constraint FK_hfcfg_program_skin_4_program_template_id foreign key ( program_template_id) references hfcfg_program_template(hfcfg_program_template_id) on delete restrict on update restrict;


alter table hfmd_entity add constraint FK_hfmd_entity_4_hfpm_module_id foreign key ( hfpm_module_id) references hfpm_module(hfpm_module_id) on delete restrict on update restrict;


alter table hfmd_entity add constraint FK_hfmd_entity_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfmd_entity_attr add constraint FK_hfmd_entity_attr_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfmd_entity_attr add constraint FK_hfmd_entity_attr_4_hfmd_enum_class_id foreign key ( hfmd_enum_class_id) references hfmd_enum_class(hfmd_enum_class_id) on delete restrict on update restrict;


alter table hfmd_entity_attr add constraint FK_hfmd_entity_attr_4_hfpm_module_id foreign key ( hfpm_module_id) references hfpm_module(hfpm_module_id) on delete restrict on update restrict;


alter table hfmd_entity_attr add constraint FK_hfmd_entity_attr_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfmd_entity_attr add constraint FK_hfmd_entity_attr_4_rel_hfmd_entity_attr_id foreign key ( rel_hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfmd_entity_rel add constraint FK_hfmd_entity_rel_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfmd_entity_rel add constraint FK_hfmd_entity_rel_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfmd_entity_rel add constraint FK_hfmd_entity_rel_4_rel_hfmd_entity_id foreign key ( rel_hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfmd_enum add constraint FK_hfmd_enum_4_hfmd_enum_class_id foreign key ( hfmd_enum_class_id) references hfmd_enum_class(hfmd_enum_class_id) on delete restrict on update restrict;


alter table hfmd_enum add constraint FK_hfmd_enum_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfmd_enum_class add constraint FK_hfmd_enum_class_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfpm_data_field add constraint FK_hfpm_data_field_4_hfmd_entity_attr_id foreign key ( hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_data_field add constraint FK_hfpm_data_field_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_data_field add constraint FK_hfpm_data_field_4_hfpm_data_set_id foreign key ( hfpm_data_set_id) references hfpm_data_set(hfpm_data_set_id) on delete restrict on update restrict;


alter table hfpm_data_field add constraint FK_hfpm_data_field_4_hfpm_field_show_type_id foreign key ( hfpm_field_show_type_id) references hfpm_field_show_type(hfpm_field_show_type_id) on delete restrict on update restrict;


alter table hfpm_data_set add constraint FK_hfpm_data_set_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfpm_data_set add constraint FK_hfpm_data_set_4_main_hfmd_entity_id foreign key ( main_hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_entity_bind_rule add constraint FK_hfpm_entity_bind_rule_4_dest_hfmd_entity_attr_id foreign key ( dest_hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_entity_bind_rule add constraint FK_hfpm_entity_bind_rule_4_dest_hfmd_entity_id foreign key ( dest_hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_entity_bind_rule add constraint FK_hfpm_entity_bind_rule_4_src_hfmd_entity_attr_id foreign key ( src_hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_entity_bind_rule add constraint FK_hfpm_entity_bind_rule_4_src_hfmd_entity_id foreign key ( src_hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_entity_permission add constraint FK_hfpm_entity_permission_4_hfmd_entity_attr_id foreign key ( hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_entity_permission add constraint FK_hfpm_entity_permission_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_module add constraint FK_hfpm_module_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfpm_page add constraint FK_hfpm_page_4_hfcfg_page_template_id foreign key ( hfcfg_page_template_id) references hfcfg_page_template(hfcfg_page_template_id) on delete restrict on update restrict;


alter table hfpm_page add constraint FK_hfpm_page_4_hfpm_module_id foreign key ( hfpm_module_id) references hfpm_module(hfpm_module_id) on delete restrict on update restrict;


alter table hfpm_page add constraint FK_hfpm_page_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfpm_page add constraint FK_hfpm_page_4_parent_hfpm_page_id foreign key ( parent_hfpm_page_id) references hfpm_page(hfpm_page_id) on delete restrict on update restrict;


alter table hfpm_page_cfg add constraint FK_hfpm_page_cfg_4_hfcfg_page_template_id foreign key ( hfcfg_page_template_id) references hfcfg_page_template(hfcfg_page_template_id) on delete restrict on update restrict;


alter table hfpm_page_cfg add constraint FK_hfpm_page_cfg_4_hfpm_program_cfg_id foreign key ( hfpm_program_cfg_id) references hfpm_program_cfg(hfpm_program_cfg_id) on delete restrict on update restrict;


alter table hfpm_page_component add constraint FK_hfpm_page_component_4_hfcfg_component_template_id foreign key ( hfcfg_component_template_id) references hfcfg_component_template(hfcfg_component_template_id) on delete restrict on update restrict;


alter table hfpm_page_component add constraint FK_hfpm_page_component_4_hfpm_data_set_id foreign key ( hfpm_data_set_id) references hfpm_data_set(hfpm_data_set_id) on delete restrict on update restrict;


alter table hfpm_page_component add constraint FK_hfpm_page_component_4_hfpm_page_id foreign key ( hfpm_page_id) references hfpm_page(hfpm_page_id) on delete restrict on update restrict;


alter table hfpm_page_entity_rel add constraint FK_hfpm_page_entity_rel_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_page_entity_rel add constraint FK_hfpm_page_entity_rel_4_hfpm_page_id foreign key ( hfpm_page_id) references hfpm_page(hfpm_page_id) on delete restrict on update restrict;


alter table hfpm_page_event add constraint FK_hfpm_page_event_4_effect_field foreign key ( effect_field) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_page_event add constraint FK_hfpm_page_event_4_hfpm_page_component_id foreign key ( hfpm_page_component_id) references hfpm_page_component(hfpm_page_component_id) on delete restrict on update restrict;


alter table hfpm_page_event add constraint FK_hfpm_page_event_4_hfpm_page_id foreign key ( hfpm_page_id) references hfpm_page(hfpm_page_id) on delete restrict on update restrict;


alter table hfpm_page_event add constraint FK_hfpm_page_event_4_target_hfpm_page_id foreign key ( target_hfpm_page_id) references hfpm_page(hfpm_page_id) on delete restrict on update restrict;


alter table hfpm_page_event_attr add constraint FK_hfpm_page_event_attr_4_hfmd_entity_attr_id foreign key ( hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_page_event_attr add constraint FK_hfpm_page_event_attr_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_page_event_attr add constraint FK_hfpm_page_event_attr_4_hfpm_page_event_id foreign key ( hfpm_page_event_id) references hfpm_page_event(hfpm_page_event_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_data_entity_name foreign key ( data_entity_name) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_dictionarys foreign key ( dictionarys) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_func_entity_name foreign key ( func_entity_name) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_hfcfg_db_connect_id foreign key ( hfcfg_db_connect_id) references hfcfg_db_connect(hfcfg_db_connect_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_hfcfg_login_page_id foreign key ( hfcfg_login_page_id) references hfcfg_login_page(hfcfg_login_page_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_hfcfg_program_skin_id foreign key ( hfcfg_program_skin_id) references hfcfg_program_skin(hfcfg_program_skin_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_hfcfg_program_template_id foreign key ( hfcfg_program_template_id) references hfcfg_program_template(hfcfg_program_template_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_super_auth_filter_entity foreign key ( super_auth_filter_entity) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_super_auth_filter_field foreign key ( super_auth_filter_field) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_user_entity_name foreign key ( user_entity_name) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfpm_program_cfg add constraint FK_hfpm_program_cfg_4_user_login_data_set foreign key ( user_login_data_set) references hfpm_data_set(hfpm_data_set_id) on delete restrict on update restrict;


alter table hfsec_menu add constraint FK_hfsec_menu_4_parent_hfsec_menu_id foreign key ( parent_hfsec_menu_id) references hfsec_menu(hfsec_menu_id) on delete restrict on update restrict;


alter table hfsec_organize add constraint FK_hfsec_organize_4_parent_hfsec_organize_id foreign key ( parent_hfsec_organize_id) references hfsec_organize(hfsec_organize_id) on delete restrict on update restrict;


alter table hfsec_role_authorize add constraint FK_hfsec_role_authorize_4_hfsec_menu_id foreign key ( hfsec_menu_id) references hfsec_menu(hfsec_menu_id) on delete restrict on update restrict;


alter table hfsec_role_authorize add constraint FK_hfsec_role_authorize_4_hfsec_role_id foreign key ( hfsec_role_id) references hfsec_role(hfsec_role_id) on delete restrict on update restrict;


alter table hfsec_user add constraint FK_hfsec_user_4_hfsec_organize_id foreign key ( hfsec_organize_id) references hfsec_organize(hfsec_organize_id) on delete restrict on update restrict;


alter table hfsec_user_authorize add constraint FK_hfsec_user_authorize_4_hfsec_organize_id foreign key ( hfsec_organize_id) references hfsec_organize(hfsec_organize_id) on delete restrict on update restrict;


alter table hfsec_user_authorize add constraint FK_hfsec_user_authorize_4_hfsec_role_id foreign key ( hfsec_role_id) references hfsec_role(hfsec_role_id) on delete restrict on update restrict;


alter table hfsec_user_authorize add constraint FK_hfsec_user_authorize_4_hfsec_user_id foreign key ( hfsec_user_id) references hfsec_user(hfsec_user_id) on delete restrict on update restrict;


alter table hfus_entity_type_relat_entity_attr add constraint FK_hfus_entity_type_relat_entity_attr_4_hfus_entity_attr_id foreign key ( hfus_entity_attr_id) references hfus_entity_attr(hfus_entity_attr_id) on delete restrict on update restrict;


alter table hfus_program_entity_attr add constraint FK_hfus_program_entity_attr_4_hfmd_entity_attr_id foreign key ( hfmd_entity_attr_id) references hfmd_entity_attr(hfmd_entity_attr_id) on delete restrict on update restrict;


alter table hfus_program_entity_attr add constraint FK_hfus_program_entity_attr_4_hfmd_entity_id foreign key ( hfmd_entity_id) references hfmd_entity(hfmd_entity_id) on delete restrict on update restrict;


alter table hfus_program_entity_attr add constraint FK_hfus_program_entity_attr_4_hfpm_program_id foreign key ( hfpm_program_id) references hfpm_program(hfpm_program_id) on delete restrict on update restrict;
