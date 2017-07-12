/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/10/27 21:39:16                          */
/*==============================================================*/


drop table if exists hfcfg_login_page;

drop table if exists hfcfg_page_template;

drop table if exists hfcfg_program_skin;

drop table if exists hfcfg_program_template;

drop table if exists hfmd_entity;

drop table if exists hfmd_entity_attr;

drop table if exists hfmd_entity_rel;

drop table if exists hfmd_enum;

drop table if exists hfmd_enum_class;

drop table if exists hfpm_data_field;

drop table if exists hfpm_data_set;

drop table if exists hfpm_entity_bind_rule;

drop table if exists hfpm_entity_permission;

drop table if exists hfpm_field_show_type;

drop table if exists hfpm_module;

drop table if exists hfpm_page;

drop table if exists hfpm_page_cfg;

drop table if exists hfpm_page_component;

drop table if exists hfpm_page_entity_rel;

drop table if exists hfpm_page_event;

drop table if exists hfpm_page_event_attr;

drop table if exists hfpm_program;

drop table if exists hfpm_program_cfg;

drop table if exists hfus_entity_attr;

drop table if exists hfus_entity_type_relat_entity_attr;

drop table if exists hfus_page_event;

drop table if exists hfus_program_entity_attr;

drop table if exists hfus_word_store;

/*==============================================================*/
/* Table: hfcfg_login_page                                      */
/*==============================================================*/
create table hfcfg_login_page
(
   hfcfg_login_page_id  bigint not null comment '登陆页面ID',
   hfcfg_login_page_name varchar(64) comment '登陆页面名称',
   hfcfg_login_page_code varchar(64) comment '登陆页面编码',
   snapshot_url         varchar(128) comment '页面快照URL',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfcfg_login_page_id)
)
type = InnoDB;

alter table hfcfg_login_page comment '登陆页面';

/*==============================================================*/
/* Table: hfcfg_page_template                                   */
/*==============================================================*/
create table hfcfg_page_template
(
   hfcfg_page_template_id bigint not null comment '页面模板ID',
   hfcfg_page_template_type int(2) not null comment '页面模板类型',
   hfcfg_page_template_name varchar(64) comment '页面模板名称',
   hfcfg_page_template_code varchar(128) not null comment '页面模板编码',
   hfcfg_page_template_desc varchar(128) comment '页面模板描述',
   snapshot_url         varchar(128) comment '快照URL',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfcfg_page_template_id)
)
type = InnoDB;

alter table hfcfg_page_template comment '页面模板';

/*==============================================================*/
/* Table: hfcfg_program_skin                                    */
/*==============================================================*/
create table hfcfg_program_skin
(
   hfcfg_program_skin_id bigint not null comment '项目皮肤ID',
   program_skin_name    varchar(64) comment '项目皮肤名称',
   program_skin_code    varchar(64) comment '项目皮肤编码',
   snapshot_url         varchar(128) comment '快照URL',
   program_template_id  bigint comment '项目模板ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfcfg_program_skin_id)
)
type = InnoDB;

alter table hfcfg_program_skin comment '项目皮肤';

/*==============================================================*/
/* Table: hfcfg_program_template                                */
/*==============================================================*/
create table hfcfg_program_template
(
   hfcfg_program_template_id bigint not null comment '项目模板ID',
   program_template_name varchar(64) comment '项目模板名称',
   program_template_code varchar(64) comment '项目模板编码',
   program_template_desc varchar(128) comment '项目模板描述',
   snapshot_url         varchar(128) comment '快照URL',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfcfg_program_template_id)
)
type = InnoDB;

alter table hfcfg_program_template comment '项目模板';

/*==============================================================*/
/* Table: hfmd_entity                                           */
/*==============================================================*/
create table hfmd_entity
(
   hfmd_entity_id       bigint not null comment '实体ID',
   hfmd_entity_name     varchar(64) not null comment '实体名称',
   hfmd_entity_code     varchar(64) not null comment '实体编码',
   hfmd_entity_type     int(2) not null default NULL comment '实体类型
            0：业务类
            1：配置类
            2：关系类
            3：扩展类',
   hfmd_entity_desc     varchar(124) default NULL comment '实体描述',
   hfpm_program_id      bigint default NULL comment '项目ID',
   hfpm_module_id       bigint comment '模块ID',
   op_id                bigint comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfmd_entity_id)
);

alter table hfmd_entity comment '实体';

/*==============================================================*/
/* Table: hfmd_entity_attr                                      */
/*==============================================================*/
create table hfmd_entity_attr
(
   hfmd_entity_attr_id  bigint not null comment '实体属性ID',
   hfmd_entity_attr_name varchar(64) not null comment '实体属性名称',
   hfmd_entity_attr_code varchar(64) not null comment '实体属性编码',
   hfmd_entity_attr_desc varchar(128) default NULL comment '实体属性描述',
   attr_type            int(2) not null default NULL comment '属性类型',
   size                 varchar(6) comment '大小',
   ispk                 int(2) default NULL comment '是否主键',
   nullable             int(2) default NULL comment '是否为空',
   is_busi_attr         int(2) default NULL comment '是否业务属性',
   is_redundant_attr    int(2) comment '是否冗余属性',
   rel_hfmd_entity_attr_id bigint default NULL comment '关联属性ID',
   hfmd_enum_class_id   bigint comment '枚举类ID',
   pri                  numeric(2,2) default NULL comment '优先级',
   hfpm_program_id      bigint comment '项目ID',
   hfpm_module_id       bigint comment '模块ID',
   hfmd_entity_id       bigint comment '实体ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfmd_entity_attr_id)
);

alter table hfmd_entity_attr comment '实体属性';


create table hfmd_entity_join_rule(
 hfmd_entity_join_rule_id bigint(20) primary key auto_increment comment '实体连带id',
 source_hfmd_entity_id bigint(20) not null comment '源实体id',
 source_hfmd_entity_attr_id bigint(20) not null comment '源实体属性id',
 source_hfmd_entity_attr_value varchar(64) comment '源实体属性值',
 join_type tinyint(2) not null comment '连带类型',
 target_hfmd_entity_id bigint(20) not null comment '目标实体id',
 target_hfmd_entity_attr_id bigint(20) not null comment '目标实体属性id',
 target_hfmd_entity_attr_value varchar(64) comment '目标实体属性值',
 editable tinyint(2) comment '是否可编辑'
);

alter table hfmd_entity_join_rule comment '实体连带关系';


/*==============================================================*/
/* Table: hfmd_entity_rel                                       */
/*==============================================================*/
create table hfmd_entity_rel
(
   hfmd_entity_rel_id   bigint not null comment '实体关系ID',
   hfmd_entity_id       bigint comment '实体ID',
   hfmd_entity_rel_type int(2) comment '实体关联类型
            0：一对一
            1：一对多
            2：多对一
            3：多对多',
   hfmd_entity_rel_level int(2) comment '实体关联级别
            0 ：弱关联（引用）
            1 ：强关联（归属）',
   hfmd_entity_rel_desc varchar(128) comment '实体关联描述',
   rel_hfmd_entity_id   bigint comment '关联实体ID',
   rel_entity_attr_id   bigint,
   hfpm_program_id      bigint comment '项目ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfmd_entity_rel_id)
);

alter table hfmd_entity_rel comment '实体关系';

/*==============================================================*/
/* Table: hfmd_enum                                             */
/*==============================================================*/
create table hfmd_enum
(
   hfmd_enum_id         bigint not null comment '枚举ID',
   hfmd_enum_value      varchar(32) not null comment '枚举值',
   hfmd_enum_text       varchar(32) not null default NULL comment '枚举文本',
   hfmd_enum_desc       varchar(128) default NULL comment '枚举描述',
   is_default           int(2) default NULL comment '是否默认',
   pri                  numeric(2,2) default NULL comment '优先级',
   ext1                 varchar(128) default NULL comment '扩展字段1',
   ext2                 varchar(128) default NULL comment '扩展字段2',
   hfmd_enum_class_id   varchar(32) not null comment '枚举类目ID',
   hfmd_enum_class_code varchar(32) not null,
   hfpm_program_id      bigint comment '项目ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfmd_enum_id)
);

alter table hfmd_enum comment '枚举';

/*==============================================================*/
/* Table: hfmd_enum_class                                       */
/*==============================================================*/
create table hfmd_enum_class
(
   hfmd_enum_class_id   bigint not null comment '枚举类目ID',
   hfmd_enum_class_name varchar(32) not null default NULL comment '枚举类目名称',
   hfmd_enum_class_code varchar(32) not null default NULL comment '枚举类目编码',
   hfmd_enum_class_desc varchar(128) default NULL comment '枚举类目描述',
   ext1                 varchar(128) default NULL comment '扩展字段1',
   ext2                 varchar(128) default NULL comment '扩展字段2',
   hfpm_program_id      bigint comment '项目ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfmd_enum_class_id)
);

alter table hfmd_enum_class comment '枚举类目';

/*==============================================================*/
/* Table: hfpm_data_field                                       */
/*==============================================================*/
create table hfpm_data_field
(
   hfpm_data_field_id   bigint not null comment '数据列ID',
   hfpm_data_field_code varchar(64) comment '数据列编码',
   hfpm_field_show_type_id varchar(32) comment '列展示类型ID',
   field_show_code      varchar(6) comment '列展示码
            由三位数字组成，分别表示新建、修改、展示
            0: 不可见 1: 查看权限 2: 可编辑',
   hfmd_entity_id       bigint comment '实体ID',
   hfmd_entity_attr_id  bigint comment '实体属性ID',
   data_get_method      int(2) comment '数据获取方式
            0：程序关联
            1：数据关联
            2：数据冗余
            ',
   hfpm_data_field_name varchar(64) comment '数据列名称',
   hfpm_data_set_id     bigint comment '数据集ID',
   pri                  numeric(6,2) comment '优先级',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_data_field_id)
)
type = InnoDB;

alter table hfpm_data_field comment '数据列';

/*==============================================================*/
/* Table: hfpm_data_set                                         */
/*==============================================================*/
create table hfpm_data_set
(
   hfpm_data_set_id     bigint not null comment '数据集ID',
   hfpm_data_set_name   varchar(64) comment '数据集名称',
   hfpm_data_set_code   varchar(64) comment '数据集编码',
   main_hfmd_entity_id  bigint comment '主实体ID',
   hfpm_program_id      bigint comment '项目ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_data_set_id)
)
type = InnoDB;

alter table hfpm_data_set comment '数据集';

/*==============================================================*/
/* Table: hfpm_entity_bind_rule                                 */
/*==============================================================*/
create table hfpm_entity_bind_rule
(
   hfpm_entity_bind_rule_id bigint not null comment '实体捆绑规则ID',
   bind_type            int(2) comment '捆绑类型',
   src_hfmd_entity_id   bigint comment '源实体ID',
   src_hfmd_entity_attr_id bigint comment '源实体属性ID',
   dest_hfmd_entity_id  bigint comment '目标实体ID',
   dest_hfmd_entity_attr_id bigint comment '目标实体属性ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_entity_bind_rule_id)
)
type = InnoDB;

alter table hfpm_entity_bind_rule comment '实体捆绑规则';

/*==============================================================*/
/* Table: hfpm_entity_permission                                */
/*==============================================================*/
create table hfpm_entity_permission
(
   hfpm_entity_permission_id bigint not null comment '页面事件属性ID',
   hfmd_entity_id       bigint comment '实体ID',
   hfmd_entity_attr_id  bigint comment '实体属性ID',
   value_type           int(2) comment '值类型',
   value                varchar(128) comment '值',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_entity_permission_id)
)
type = InnoDB;

alter table hfpm_entity_permission comment '实体权限';

/*==============================================================*/
/* Table: hfpm_field_show_type                                  */
/*==============================================================*/
create table hfpm_field_show_type
(
   hfpm_field_show_type_id bigint not null comment '展示类型ID',
   hfpm_field_show_type_code varchar(32) comment '展示类型编码',
   hfpm_field_show_type_name varchar(32) default NULL comment '展示类型名称',
   pre_str              varchar(256) comment '前缀',
   after_str            varchar(256) comment '后缀',
   col_span             int(2) default NULL comment '列数',
   row_span             int(2) default NULL comment '行数',
   width                int(11) default NULL comment '宽度',
   height               int(11) default NULL comment '高度',
   param1               varchar(128) comment '参数1',
   param2               varchar(128) comment '参数2',
   param3               varchar(128) comment '参数3',
   param4               varchar(128) comment '参数4',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_field_show_type_id)
);

alter table hfpm_field_show_type comment '展示类型';

/*==============================================================*/
/* Table: hfpm_module                                           */
/*==============================================================*/
create table hfpm_module
(
   hfpm_module_id       bigint not null comment '模块ID',
   hfpm_module_name     varchar(64) not null comment '模块名称',
   hfpm_module_code     varchar(64) not null comment '模块编码',
   hfpm_module_desc     varchar(128) comment '模块描述',
   hfpm_program_id      bigint comment '项目ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_module_id)
)
type = InnoDB;

alter table hfpm_module comment '模块';

/*==============================================================*/
/* Table: hfpm_page                                             */
/*==============================================================*/
create table hfpm_page
(
   hfpm_page_id         bigint not null comment '页面ID',
   hfpm_page_code       varchar(64) comment '页面编码',
   hfpm_page_name       varchar(128) comment '页面名称',
   hfpm_page_type       int(2) comment '页面类型',
   hfpm_page_desc       varchar(128) comment '页面描述',
   parent_hfpm_page_id  bigint comment '父页面ID',
   hfpm_program_id      bigint comment '项目ID',
   hfpm_module_id       bigint comment '模块ID',
   pri                  numeric(6,2) comment '优先级',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_page_id)
)
type = InnoDB;

alter table hfpm_page comment '页面';

/*==============================================================*/
/* Table: hfpm_page_cfg                                         */
/*==============================================================*/
create table hfpm_page_cfg
(
   hfpm_page_cfg_id     bigint not null comment '页面设置ID',
   hfcfg_page_template_id bigint comment '页面模板ID',
   hfpm_program_cfg_id  bigint comment '项目配置ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_page_cfg_id)
)
type = InnoDB;

alter table hfpm_page_cfg comment '项目页面设置';

/*==============================================================*/
/* Table: hfpm_page_component                                   */
/*==============================================================*/
create table hfpm_page_component
(
   hfpm_page_component_id bigint not null comment '页面组件ID',
   hfpm_page_component_name varchar(64) comment '页面组件名称',
   hfpm_page_component_type int(2) comment '页面组件类型',
   hfpm_page_id         bigint comment '页面ID',
   hfpm_data_set_id     bigint comment '数据集ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_page_component_id)
)
type = InnoDB;

alter table hfpm_page_component comment '页面组件';

/*==============================================================*/
/* Table: hfpm_page_entity_rel                                  */
/*==============================================================*/
create table hfpm_page_entity_rel
(
   hfpm_page_entity_rel_id bigint not null comment '页面关联实体ID',
   hfpm_page_id         bigint comment '页面ID',
   hfmd_entity_id       bigint comment '实体ID',
   is_main_entity       int(2) comment '是否为主实体',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_page_entity_rel_id)
)
type = InnoDB;

alter table hfpm_page_entity_rel comment '页面关联实体';

/*==============================================================*/
/* Table: hfpm_page_event                                       */
/*==============================================================*/
create table hfpm_page_event
(
   hfpm_page_event_id   bigint not null comment '页面事件ID',
   hfpm_page_id         bigint comment '页面ID',
   hfpm_event_name      varchar(32) comment '事件名称',
   hfpm_event_monitor_object varchar(64) comment '事件监听对象',
   hfpm_event_monitor_object_type varchar(64) comment '事件监听对象类型',
   hfpm_event_type      int(2) comment '事件类型',
   hfpm_event_effect_object varchar(64) comment '事件作用对象',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   hfpm_page_component_id bigint comment '页面组件ID',
   primary key (hfpm_page_event_id)
)
type = InnoDB;

alter table hfpm_page_event comment '页面事件';

/*==============================================================*/
/* Table: hfpm_page_event_attr                                  */
/*==============================================================*/
create table hfpm_page_event_attr
(
   hfpm_page_event_attr_id bigint not null comment '页面事件属性ID',
   hfpm_page_event_id   bigint comment '页面事件ID',
   hfpm_page_event_attr_type int(2) comment '页面事件属性类型
            0:条件',
   hfmd_entity_attr_id  bigint comment '实体属性ID',
   hfmd_entity_id       bigint comment '实体ID',
   value_type           int(2) comment '值类型',
   value                varchar(128) comment '值',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_page_event_attr_id)
)
type = InnoDB;

alter table hfpm_page_event_attr comment '页面事件属性';

/*==============================================================*/
/* Table: hfpm_program                                          */
/*==============================================================*/
create table hfpm_program
(
   hfpm_program_id      bigint not null comment '项目ID',
   hfpm_program_name    varchar(64) not null default NULL comment '项目名称',
   hfpm_program_code    varchar(64) not null default NULL comment '项目编码',
   hfpm_program_desc    varchar(512) default NULL comment '项目描述',
   op_id                bigint comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_program_id)
);

alter table hfpm_program comment '项目';

/*==============================================================*/
/* Table: hfpm_program_cfg                                      */
/*==============================================================*/
create table hfpm_program_cfg
(
   hfpm_program_cfg_id  bigint not null comment '项目配置ID',
   show_name            varchar(128) comment '项目标题',
   hfcfg_program_template_id bigint comment '项目模板ID',
   hfcfg_program_skin_id bigint comment '项目皮肤ID',
   hfcfg_login_page_id  bigint comment '项目登陆页ID',
   bg_img_url           varchar(128) comment '背景图片URL',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfpm_program_cfg_id)
)
type = InnoDB;

alter table hfpm_program_cfg comment '项目设置';

/*==============================================================*/
/* Table: hfus_entity_attr                                      */
/*==============================================================*/
create table hfus_entity_attr
(
   hfus_entity_attr_id  bigint not null comment '常用实体属性ID',
   hfus_entity_attr_name varchar(64) not null comment '实体属性名称',
   hfus_entity_attr_code varchar(64) not null comment '实体属性编码',
   hfus_entity_attr_desc varchar(128) default NULL comment '实体描述',
   attr_type            int(2) default NULL comment '属性类型',
   size                 varchar(6) comment '大小',
   ispk                int(2) comment '是否主键',
   nullable             int(2) default NULL comment '是否可为空',
   is_busi_attr         int(2) default NULL comment '是否业务属性',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfus_entity_attr_id)
);
alter table hfus_entity_attr comment '常用实体属性';


create table hfsec_user
(
   hfsec_user_id  bigint not null comment '用户ID',
   hfsec_user_name varchar(64) not null comment '用户名称',
   account varchar(64) not null comment '用户账号',
   password varchar(128) default NULL comment '用户密码',
   gender            int(2) default NULL comment '性别',
   mobile             varchar(6) comment '手机号',
   email             int(2) default NULL comment '邮箱',
   addr             int(2) default NULL comment '地址',
   last_login_time datetime comment '上次登录时间',
   status     int(2)  comment '状态' ,
   hfuc_org_id bigint comment '归属组织ID',
   creator_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modifier_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfsec_user_id)
);
alter table hfsec_user comment '用户';


create table hfsec_menu (
  hfsec_menu_id bigint(20) not null  comment '菜单ID',
  hfsec_menu_code varchar(64) not null comment '菜单编码',
  hfsec_menu_name varchar(128) not null comment '菜单名称',
  hfsec_menu_desc varchar(128)  comment '菜单描述',
  menu_level int(2)  comment '菜单级别',
  icon varchar(64)  comment '图标',
  url varchar(128)  comment '地址',
  parent_hfsec_menu_id bigint(20) comment '父级菜单ID',
  hfpm_program_id bigint(20) comment '项目ID',
  hfpm_module_id bigint(20) comment '模块ID',
  pri decimal(6,2) comment '优先级',
  creator_id bigint(20) comment '创建人',
  create_time datetime comment '创建时间',
  modifier_id bigint(20) comment '修改人',
  modify_time datetime comment '修改时间',
  del_flag int(2) comment '删除标识',
  primary key (hfsec_menu_id)
);
alter table hfsec_menu comment '菜单';


/*==============================================================*/
/* Table: hfus_entity_type_relat_entity_attr                    */
/*==============================================================*/
create table hfus_entity_type_relat_entity_attr
(
   hfus_entity_type_relat_entity_attr_id bigint not null comment '关系ID',
   entity_type          int(2) comment '实体类型',
   hfus_entity_attr_id  bigint comment '常用实体属性ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfus_entity_type_relat_entity_attr_id)
)
type = InnoDB;

alter table hfus_entity_type_relat_entity_attr comment '实体类型关联实体属性';

/*==============================================================*/
/* Table: hfus_page_event                                       */
/*==============================================================*/
create table hfus_page_event
(
   hfus_page_event_id   bigint not null comment '常用页面事件ID',
   hfpm_event_name      varchar(32) comment '事件名称',
   hfpm_event_type      int(2) comment '事件类型',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfus_page_event_id)
)
type = InnoDB;

alter table hfus_page_event comment '常用页面事件';

/*==============================================================*/
/* Table: hfus_program_entity_attr                              */
/*==============================================================*/
create table hfus_program_entity_attr
(
   hfus_program_entity_attr_id bigint not null comment '项目常用实体ID',
   hfpm_program_id      bigint comment '项目ID',
   hfmd_entity_id       bigint comment '实体ID',
   hfmd_entity_attr_id  bigint comment '实体属性ID',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfus_program_entity_attr_id)
);

alter table hfus_program_entity_attr comment '项目常用实体属性';

/*==============================================================*/
/* Table: hfus_word_store                                       */
/*==============================================================*/
create table hfus_word_store
(
   hfus_word_store_id   bigint not null comment '单词库ID',
   chinese_chars        varchar(64) comment '汉字名称',
   english_name         varchar(64) comment '英语名称',
   english_short_name   varchar(64) comment '英文简称',
   op_id                bigint comment '创建人',
   create_time          datetime comment '创建时间',
   modify_op_id         bigint comment '修改人',
   modify_time          datetime comment '修改时间',
   del_flag             int(2) comment '删除标识',
   primary key (hfus_word_store_id)
)
type = InnoDB;

alter table hfus_word_store comment '单词库';

alter table hfcfg_program_skin add constraint FK_Reference_10 foreign key (program_template_id)
      references hfcfg_program_template (hfcfg_program_template_id) on delete restrict on update restrict;

alter table hfmd_entity add constraint FK_Reference_1 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfmd_entity add constraint FK_Reference_14 foreign key (hfpm_module_id)
      references hfpm_module (hfpm_module_id) on delete restrict on update restrict;

alter table hfmd_entity_attr add constraint FK_Reference_15 foreign key (hfmd_entity_id)
      references hfmd_entity (hfmd_entity_id) on delete restrict on update restrict;

alter table hfmd_entity_attr add constraint FK_Reference_16 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfmd_entity_attr add constraint FK_Reference_17 foreign key (hfmd_enum_class_id)
      references hfmd_enum_class (hfmd_enum_class_id) on delete restrict on update restrict;

alter table hfmd_entity_attr add constraint FK_Reference_32 foreign key (hfpm_module_id)
      references hfpm_module (hfpm_module_id) on delete restrict on update restrict;

alter table hfmd_entity_rel add constraint FK_Reference_35 foreign key (hfmd_entity_id)
      references hfmd_entity (hfmd_entity_id) on delete restrict on update restrict;

alter table hfmd_enum add constraint FK_Reference_3 foreign key (hfmd_enum_class_id)
      references hfmd_enum_class (hfmd_enum_class_id) on delete restrict on update restrict;

alter table hfmd_enum add constraint FK_Reference_34 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfmd_enum_class add constraint FK_Reference_33 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfpm_data_field add constraint FK_Reference_24 foreign key (hfpm_data_set_id)
      references hfpm_data_set (hfpm_data_set_id) on delete restrict on update restrict;

alter table hfpm_data_field add constraint FK_Reference_27 foreign key (hfpm_field_show_type_id)
      references hfpm_field_show_type (hfpm_field_show_type_id) on delete restrict on update restrict;

alter table hfpm_data_set add constraint FK_Reference_25 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfpm_module add constraint FK_Reference_13 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfpm_page add constraint FK_Reference_22 foreign key (parent_hfpm_page_id)
      references hfpm_page (hfpm_page_id) on delete restrict on update restrict;

alter table hfpm_page add constraint FK_Reference_26 foreign key (hfpm_program_id)
      references hfpm_program (hfpm_program_id) on delete restrict on update restrict;

alter table hfpm_page_cfg add constraint FK_Reference_21 foreign key (hfcfg_page_template_id)
      references hfcfg_page_template (hfcfg_page_template_id) on delete restrict on update restrict;

alter table hfpm_page_cfg add constraint FK_Reference_36 foreign key (hfpm_program_cfg_id)
      references hfpm_program_cfg (hfpm_program_cfg_id) on delete restrict on update restrict;

alter table hfpm_page_component add constraint FK_Reference_28 foreign key (hfpm_data_set_id)
      references hfpm_data_set (hfpm_data_set_id) on delete restrict on update restrict;

alter table hfpm_page_component add constraint FK_Reference_29 foreign key (hfpm_page_id)
      references hfpm_page (hfpm_page_id) on delete restrict on update restrict;

alter table hfpm_page_entity_rel add constraint FK_Reference_23 foreign key (hfpm_page_id)
      references hfpm_page (hfpm_page_id) on delete restrict on update restrict;

alter table hfpm_page_event add constraint FK_Reference_30 foreign key (hfpm_page_id)
      references hfpm_page (hfpm_page_id) on delete restrict on update restrict;

alter table hfpm_page_event_attr add constraint FK_Reference_31 foreign key (hfpm_page_event_id)
      references hfpm_page_event (hfpm_page_event_id) on delete restrict on update restrict;

alter table hfpm_program_cfg add constraint FK_Reference_11 foreign key (hfcfg_program_template_id)
      references hfcfg_program_template (hfcfg_program_template_id) on delete restrict on update restrict;

alter table hfpm_program_cfg add constraint FK_Reference_12 foreign key (hfcfg_program_skin_id)
      references hfcfg_program_skin (hfcfg_program_skin_id) on delete restrict on update restrict;

alter table hfpm_program_cfg add constraint FK_Reference_18 foreign key (hfcfg_login_page_id)
      references hfcfg_login_page (hfcfg_login_page_id) on delete restrict on update restrict;

alter table hfus_entity_type_relat_entity_attr add constraint FK_Reference_9 foreign key (hfus_entity_attr_id)
      references hfus_entity_attr (hfus_entity_attr_id) on delete restrict on update restrict;

