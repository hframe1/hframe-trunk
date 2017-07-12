CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `core_action` (
  `id` varchar(64) NOT NULL,
  `action_name` varchar(128) DEFAULT NULL,
  `action_method` varchar(64) DEFAULT NULL,
  `table_id` varchar(64) DEFAULT NULL,
  `columns_id` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_column_enum_rel` (
  `core_column_enum_rel_id` varchar(32) NOT NULL,
  `core_column_id` varchar(32) NOT NULL,
  `core_enum_group_id` varchar(32) DEFAULT NULL,
  `column_enum_dyn_id` varchar(32) DEFAULT NULL,
  `show_type` int(2) DEFAULT NULL,
  `ext1` varchar(32) DEFAULT NULL,
  `ext2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`core_column_enum_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_component` (
  `id` varchar(64) NOT NULL,
  `component_name` varchar(128) DEFAULT NULL,
  `content` text,
  `sort` varchar(64) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_control` (
  `id` varchar(64) NOT NULL,
  `control_name` varchar(64) DEFAULT NULL,
  `control_value` varchar(256) DEFAULT NULL,
  `control_quote` varchar(256) DEFAULT NULL,
  `attr` varchar(64) DEFAULT NULL,
  `descption` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_db` (
  `id` varchar(64) NOT NULL,
  `db_name` varchar(64) DEFAULT NULL,
  `show_name` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `descprition` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_element` (
  `core_element_id` varchar(32) NOT NULL,
  `core_element_type` int(3) DEFAULT NULL,
  `content` varchar(512) NOT NULL,
  `condition` varchar(128) DEFAULT NULL,
  `objects` varchar(128) DEFAULT NULL,
  `desc` varchar(128) DEFAULT NULL,
  `ext1` varchar(128) DEFAULT NULL,
  `ext2` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`core_element_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_enum` (
  `core_enum_id` varchar(32) NOT NULL,
  `core_enum_group_id` varchar(32) NOT NULL,
  `core_enum_group_show_type` int(2) DEFAULT NULL,
  `core_enum_value` varchar(32) NOT NULL,
  `core_enum_display_value` varchar(32) DEFAULT NULL,
  `core_enum_default` int(2) DEFAULT NULL,
  `core_enum_pri` int(11) DEFAULT NULL,
  `core_enum_desc` varchar(128) DEFAULT NULL,
  `ext1` varchar(32) DEFAULT NULL,
  `ext2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`core_enum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_enum_bak` (
  `core_enum_id` varchar(32) NOT NULL,
  `core_enum_group_id` varchar(32) NOT NULL,
  `core_enum_group_show_type` int(2) DEFAULT NULL,
  `core_enum_value` varchar(32) NOT NULL,
  `core_enum_display_value` varchar(32) DEFAULT NULL,
  `core_enum_default` int(2) DEFAULT NULL,
  `core_enum_pri` int(11) DEFAULT NULL,
  `core_enum_desc` varchar(128) DEFAULT NULL,
  `ext1` varchar(32) DEFAULT NULL,
  `ext2` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_enum_dyn` (
  `core_enum_id` varchar(32) NOT NULL,
  `core_enum_group_id` varchar(32) DEFAULT NULL,
  `show_type` int(2) DEFAULT NULL,
  `sql` varchar(256) NOT NULL,
  `condition` varchar(128) DEFAULT NULL,
  `order_condition` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`core_enum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_enum_group` (
  `core_enum_group_id` varchar(32) NOT NULL,
  `core_enmu_group_show_type` varchar(30) DEFAULT NULL,
  `core_enum_group_name` varchar(32) DEFAULT NULL,
  `core_enum_desc` varchar(128) DEFAULT NULL,
  `ext1` varchar(128) DEFAULT NULL,
  `ext2` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`core_enum_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_enum_group_bak` (
  `core_enum_group_id` varchar(32) NOT NULL,
  `core_enmu_group_show_type` varchar(30) DEFAULT NULL,
  `core_enum_group_name` varchar(32) DEFAULT NULL,
  `core_enum_desc` varchar(128) DEFAULT NULL,
  `ext1` varchar(128) DEFAULT NULL,
  `ext2` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_field` (
  `id` varchar(32) NOT NULL,
  `core_set_id` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `show_exp` varchar(256) DEFAULT NULL,
  `hidden_exp` varchar(256) DEFAULT NULL,
  `ext1` varchar(32) DEFAULT NULL,
  `ext2` varchar(128) DEFAULT NULL,
  `priv` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_page` (
  `id` varchar(64) NOT NULL,
  `page_name` varchar(64) NOT NULL,
  `method_id` varchar(64) DEFAULT NULL,
  `page_path` varchar(256) DEFAULT NULL,
  `page_content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_set` (
  `core_set_id` varchar(32) NOT NULL,
  `core_set_name` varchar(128) NOT NULL,
  `core_set_type` varchar(32) DEFAULT NULL,
  `core_set_tables` varchar(256) DEFAULT NULL,
  `core_set_quote` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`core_set_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_set_column_rel` (
  `core_set_column_rel_id` varchar(32) NOT NULL,
  `core_set_id` varchar(32) NOT NULL,
  `core_column_id` varchar(32) NOT NULL,
  `ext1` varchar(128) DEFAULT NULL,
  `ext2` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`core_set_column_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_show_type` (
  `id` varchar(32) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `col_span` int(2) DEFAULT NULL,
  `row_span` int(2) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `pre_str` varchar(256) DEFAULT NULL,
  `after_str` varchar(256) DEFAULT NULL,
  `core_enum_group_id` varchar(32) DEFAULT NULL,
  `core_element_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_show_type_attr` (
  `id` varchar(64) NOT NULL,
  `view` varchar(128) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `condition` varchar(256) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `src` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_table` (
  `table_id` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `table_desc` varchar(124) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `db_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `core_table_column` (
  `id` varchar(64) NOT NULL,
  `column_name` varchar(64) NOT NULL,
  `column_type` varchar(64) NOT NULL,
  `show_type_id` varchar(32) DEFAULT NULL,
  `column_size` int(11) DEFAULT NULL,
  `ispk` int(11) DEFAULT NULL,
  `nullable` int(11) DEFAULT NULL,
  `showable` int(11) DEFAULT NULL,
  `show_name` varchar(64) DEFAULT NULL,
  `table_id` varchar(64) NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  `priv` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


core_table_db;


CREATE TABLE `core_table_fk` (
  `id` varchar(64) NOT NULL,
  `column_id_from` varchar(64) NOT NULL,
  `column_id_to` varchar(64) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `diary` (
  `diary_id` varchar(64) NOT NULL,
  `title` longtext,
  `create_date` datetime DEFAULT NULL,
  `content` longtext,
  `diary_group_id` varchar(64) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `diary_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`diary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `diary_group` (
  `diary_group_id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator_id` varchar(64) DEFAULT NULL,
  `diary_group_name` longtext,
  PRIMARY KEY (`diary_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `full_form_test` (
  `id` varchar(64) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `valid_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `hfcfg_component_template` (
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `hfcfg_component_template_code` varchar(64) DEFAULT NULL COMMENT '组件模板编码',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `hfcfg_component_template_type` tinyint(2) DEFAULT NULL COMMENT '组件模板类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `hfcfg_component_template_desc` varchar(128) DEFAULT NULL COMMENT '组件模板描述',
  `hfcfg_component_template_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '组件模板id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `hfcfg_component_template_name` varchar(128) DEFAULT NULL COMMENT '组件模板名称',
  PRIMARY KEY (`hfcfg_component_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='组件模板';


CREATE TABLE `hfcfg_db_connect` (
  `hfcfg_db_connect_name` varchar(128) DEFAULT NULL COMMENT '数据库连接信息名称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `hfcfg_db_connect_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '数据库连接信息id',
  `url` varchar(256) DEFAULT NULL COMMENT 'URL',
  `user` varchar(64) DEFAULT NULL COMMENT '用户',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `hfcfg_db_connect_code` varchar(64) DEFAULT NULL COMMENT '数据库连接信息编码',
  PRIMARY KEY (`hfcfg_db_connect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='数据库连接信息';


CREATE TABLE `hfcfg_login_page` (
  `hfcfg_login_page_id` bigint(20) unsigned zerofill NOT NULL COMMENT '登陆页面ID',
  `hfcfg_login_page_name` varchar(64) DEFAULT NULL COMMENT '登陆页面名称',
  `hfcfg_login_page_code` varchar(64) DEFAULT NULL COMMENT '登陆页面编码',
  `snapshot_url` varchar(128) DEFAULT NULL COMMENT '页面快照URL',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfcfg_login_page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆页面';


CREATE TABLE `hfcfg_page_template` (
  `hfcfg_page_template_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面模板ID',
  `hfcfg_page_template_type` int(2) NOT NULL COMMENT '页面模板类型',
  `hfcfg_page_template_name` varchar(64) DEFAULT NULL COMMENT '页面模板名称',
  `hfcfg_page_template_code` varchar(128) NOT NULL COMMENT '页面模板编码',
  `hfcfg_page_template_desc` varchar(128) DEFAULT NULL COMMENT '页面模板描述',
  `template_url` varchar(128) DEFAULT NULL COMMENT '模板地址',
  `snapshot_url` varchar(128) DEFAULT NULL COMMENT '快照URL',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfcfg_page_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='页面模板';


CREATE TABLE `hfcfg_page_template_elements` (
  `hfcfg_page_template_id` bigint(20) DEFAULT NULL COMMENT '页面模板ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `hfcfg_page_template_elements_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '页面模板元素id',
  `hfcfg_page_template_elements_type` tinyint(4) DEFAULT NULL COMMENT '页面模板元素类型',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `event_extend` tinyint(4) DEFAULT NULL COMMENT '事件继承',
  `hfcfg_component_template_id` bigint(12) DEFAULT NULL COMMENT '组件模板id',
  PRIMARY KEY (`hfcfg_page_template_elements_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='页面模板元素';


CREATE TABLE `hfcfg_program_skin` (
  `hfcfg_program_skin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目皮肤ID',
  `program_skin_name` varchar(64) DEFAULT NULL COMMENT '项目皮肤名称',
  `program_skin_code` varchar(64) DEFAULT NULL COMMENT '项目皮肤编码',
  `snapshot_url` varchar(128) DEFAULT NULL COMMENT '快照URL',
  `program_template_id` bigint(20) DEFAULT NULL COMMENT '项目模板ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfcfg_program_skin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目皮肤';


CREATE TABLE `hfcfg_program_template` (
  `hfcfg_program_template_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目模板ID',
  `program_template_name` varchar(64) DEFAULT NULL COMMENT '项目模板名称',
  `program_template_code` varchar(64) DEFAULT NULL COMMENT '项目模板编码',
  `program_template_desc` varchar(128) DEFAULT NULL COMMENT '项目模板描述',
  `template_url` varchar(128) DEFAULT NULL COMMENT '模板地址',
  `snapshot_url` varchar(128) DEFAULT NULL COMMENT '快照URL',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfcfg_program_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目模板';


CREATE TABLE `hfmd_entity` (
  `hfmd_entity_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实体ID',
  `hfmd_entity_name` varchar(64) NOT NULL COMMENT '实体名称',
  `hfmd_entity_code` varchar(64) NOT NULL COMMENT '实体编码',
  `hfmd_entity_type` int(2) NOT NULL COMMENT '实体类型',
  `hfmd_entity_desc` varchar(124) DEFAULT NULL COMMENT '实体描述',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `hfpm_module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_entity_id`),
  KEY `FKDB525C4DF6C2F764` (`hfpm_module_id`),
  KEY `FKDB525C4DBBCC2B50` (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031866346 DEFAULT CHARSET=utf8 COMMENT='实体';


CREATE TABLE `hfmd_entity_attr` (
  `hfmd_entity_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实体属性ID',
  `hfmd_entity_attr_name` varchar(64) NOT NULL COMMENT '实体属性名称',
  `hfmd_entity_attr_code` varchar(64) NOT NULL COMMENT '实体属性编码',
  `hfmd_entity_attr_desc` varchar(128) DEFAULT NULL COMMENT '实体属性描述',
  `attr_type` int(2) NOT NULL COMMENT '属性类型',
  `size` varchar(6) DEFAULT NULL COMMENT '大小',
  `ispk` int(2) DEFAULT NULL COMMENT '是否主键',
  `nullable` int(2) DEFAULT NULL COMMENT '是否为空',
  `is_busi_attr` int(2) DEFAULT NULL COMMENT '是否业务属性',
  `is_redundant_attr` int(2) DEFAULT NULL COMMENT '是否冗余属性',
  `rel_hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '关联属性ID',
  `hfmd_enum_class_id` bigint(20) DEFAULT NULL COMMENT '枚举类ID',
  `pri` decimal(6,2) DEFAULT NULL COMMENT '优先级',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `hfpm_module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_entity_attr_id`),
  KEY `FKEE94DEA39747FE05` (`hfmd_enum_class_id`),
  KEY `FKEE94DEA3F6C2F764` (`hfpm_module_id`),
  KEY `FKEE94DEA3B2991B38` (`hfmd_entity_id`),
  KEY `FKEE94DEA3BBCC2B50` (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031892081 DEFAULT CHARSET=utf8 COMMENT='实体属性';


CREATE TABLE `hfmd_entity_join_rule` (
  `hfmd_entity_join_rule_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '实体连带id',
  `source_hfmd_entity_id` bigint(20) NOT NULL COMMENT '源实体id',
  `source_hfmd_entity_attr_id` bigint(20) NOT NULL COMMENT '源实体属性id',
  `source_hfmd_entity_attr_value` varchar(64) DEFAULT NULL COMMENT '源实体属性值',
  `join_type` tinyint(2) NOT NULL COMMENT '连带类型 1-值对应 2-值关联',
  `target_hfmd_entity_id` bigint(20) NOT NULL COMMENT '目标实体id',
  `target_hfmd_entity_attr_id` bigint(20) NOT NULL COMMENT '目标实体属性id',
  `target_hfmd_entity_attr_value` varchar(64) DEFAULT NULL COMMENT '目标实体属性值',
  `editable` tinyint(2) DEFAULT NULL COMMENT '是否可编辑 0-否 1-是',
  PRIMARY KEY (`hfmd_entity_join_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体连带关系';


CREATE TABLE `hfmd_entity_rel` (
  `hfmd_entity_rel_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实体关系ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `hfmd_entity_rel_type` int(2) DEFAULT NULL COMMENT '实体关联类型\n            0：一对一\n            1：一对多\n            2：多对一\n            3：多对多',
  `hfmd_entity_rel_level` int(2) DEFAULT NULL COMMENT '实体关联级别\n            0 ：弱关联（引用）\n            1 ：强关联（归属）',
  `hfmd_entity_rel_desc` varchar(128) DEFAULT NULL COMMENT '实体关联描述',
  `rel_hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '关联实体ID',
  `rel_entity_attr_id` bigint(20) DEFAULT NULL,
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_entity_rel_id`),
  KEY `FKF72E55A7B2991B38` (`hfmd_entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体关系';


CREATE TABLE `hfmd_enum` (
  `hfmd_enum_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '枚举ID',
  `hfmd_enum_value` varchar(32) NOT NULL COMMENT '枚举值',
  `hfmd_enum_text` varchar(32) NOT NULL COMMENT '枚举文本',
  `hfmd_enum_desc` varchar(128) DEFAULT NULL COMMENT '枚举描述',
  `is_default` int(2) DEFAULT NULL COMMENT '是否默认',
  `pri` decimal(4,2) DEFAULT NULL COMMENT '优先级',
  `ext1` varchar(128) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) DEFAULT NULL COMMENT '扩展字段2',
  `hfmd_enum_class_id` bigint(20) NOT NULL COMMENT '枚举类目ID',
  `hfmd_enum_class_code` varchar(32) NOT NULL,
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_enum_id`),
  KEY `FKD59B1FEBBBCC2B50` (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='枚举';


CREATE TABLE `hfmd_enum_class` (
  `hfmd_enum_class_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '枚举类目ID',
  `hfmd_enum_class_name` varchar(32) NOT NULL COMMENT '枚举类目名称',
  `hfmd_enum_class_code` varchar(64) DEFAULT NULL COMMENT '枚举类目编码',
  `hfmd_enum_class_desc` varchar(128) DEFAULT NULL COMMENT '枚举类目描述',
  `ext1` varchar(128) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(128) DEFAULT NULL COMMENT '扩展字段2',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfmd_enum_class_id`),
  KEY `FKDA25024BBCC2B50` (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='枚举类目';


CREATE TABLE `hfpm_data_field` (
  `hfpm_data_field_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据列ID',
  `hfpm_data_field_code` varchar(64) DEFAULT NULL COMMENT '数据列编码',
  `hfpm_field_show_type_id` varchar(32) DEFAULT NULL COMMENT '列展示类型ID',
  `field_show_code` varchar(6) DEFAULT NULL COMMENT '列展示码',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '实体属性ID',
  `data_get_method` int(2) DEFAULT NULL COMMENT '数据获取方式',
  `hfpm_data_field_name` varchar(64) DEFAULT NULL COMMENT '数据列名称',
  `hfpm_data_set_id` bigint(20) DEFAULT NULL COMMENT '数据集ID',
  `pri` decimal(6,2) DEFAULT NULL COMMENT '优先级',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_data_field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031897806 DEFAULT CHARSET=utf8 COMMENT='数据列';


CREATE TABLE `hfpm_data_field_bak` (
  `hfpm_data_field_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据列ID',
  `hfpm_data_field_code` varchar(64) DEFAULT NULL COMMENT '数据列编码',
  `hfpm_field_show_type_id` varchar(32) DEFAULT NULL COMMENT '列展示类型ID',
  `field_show_code` varchar(6) DEFAULT NULL COMMENT '列展示码',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '实体属性ID',
  `data_get_method` int(2) DEFAULT NULL COMMENT '数据获取方式',
  `hfpm_data_field_name` varchar(64) DEFAULT NULL COMMENT '数据列名称',
  `hfpm_data_set_id` bigint(20) DEFAULT NULL COMMENT '数据集ID',
  `pri` decimal(6,2) DEFAULT NULL COMMENT '优先级',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `hfpm_data_set` (
  `hfpm_data_set_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据集ID',
  `hfpm_data_set_name` varchar(64) DEFAULT NULL COMMENT '数据集名称',
  `hfpm_data_set_code` varchar(64) DEFAULT NULL COMMENT '数据集编码',
  `main_hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '主实体ID',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_data_set_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031888613 DEFAULT CHARSET=utf8 COMMENT='数据集';


CREATE TABLE `hfpm_entity_bind_rule` (
  `hfpm_entity_bind_rule_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实体捆绑规则ID',
  `bind_type` int(2) DEFAULT NULL COMMENT '捆绑类型',
  `src_hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '源实体ID',
  `src_hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '源实体属性ID',
  `dest_hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '目标实体ID',
  `dest_hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '目标实体属性ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_entity_bind_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体捆绑规则';


CREATE TABLE `hfpm_entity_permission` (
  `hfpm_entity_permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面事件属性ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '实体属性ID',
  `value_type` int(2) DEFAULT NULL COMMENT '值类型',
  `value` varchar(128) DEFAULT NULL COMMENT '值',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_entity_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体权限';


CREATE TABLE `hfpm_field_show_type` (
  `hfpm_field_show_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '展示类型ID',
  `hfpm_field_show_type_code` varchar(32) DEFAULT NULL COMMENT '展示类型编码',
  `hfpm_field_show_type_name` varchar(32) DEFAULT NULL COMMENT '展示类型名称',
  `pre_str` varchar(256) DEFAULT NULL COMMENT '前缀',
  `after_str` varchar(256) DEFAULT NULL COMMENT '后缀',
  `col_span` int(2) DEFAULT NULL COMMENT '列数',
  `row_span` int(2) DEFAULT NULL COMMENT '行数',
  `width` int(11) DEFAULT NULL COMMENT '宽度',
  `height` int(11) DEFAULT NULL COMMENT '高度',
  `param1` varchar(128) DEFAULT NULL COMMENT '参数1',
  `param2` varchar(128) DEFAULT NULL COMMENT '参数2',
  `param3` varchar(128) DEFAULT NULL COMMENT '参数3',
  `param4` varchar(128) DEFAULT NULL COMMENT '参数4',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_field_show_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='展示类型';


CREATE TABLE `hfpm_module` (
  `hfpm_module_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模块ID',
  `hfpm_module_name` varchar(64) NOT NULL COMMENT '模块名称',
  `hfpm_module_code` varchar(64) NOT NULL COMMENT '模块编码',
  `hfpm_module_desc` varchar(128) DEFAULT NULL COMMENT '模块描述',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_module_id`),
  KEY `FK4CB7FFB0BBCC2B50` (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031110975 DEFAULT CHARSET=utf8 COMMENT='模块';


CREATE TABLE `hfpm_page` (
  `hfpm_page_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面ID',
  `hfpm_page_code` varchar(64) DEFAULT NULL COMMENT '页面编码',
  `hfpm_page_name` varchar(128) DEFAULT NULL COMMENT '页面名称',
  `hfpm_page_type` int(2) DEFAULT NULL COMMENT '页面类型',
  `hfpm_page_desc` varchar(128) DEFAULT NULL COMMENT '页面描述',
  `parent_hfpm_page_id` bigint(20) DEFAULT NULL COMMENT '父页面ID',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `hfpm_module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `pri` decimal(6,2) DEFAULT NULL COMMENT '优先级',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `hfpm_data_set_id` bigint(20) DEFAULT NULL COMMENT '数据集ID',
  `hfcfg_page_template_id` bigint(20) DEFAULT NULL COMMENT '页面模板ID',
  PRIMARY KEY (`hfpm_page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='页面';


CREATE TABLE `hfpm_page_cfg` (
  `hfpm_page_cfg_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面设置ID',
  `hfcfg_page_template_id` bigint(20) DEFAULT NULL COMMENT '页面模板ID',
  `hfpm_program_cfg_id` bigint(20) DEFAULT NULL COMMENT '项目配置ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_page_cfg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目页面设置';


CREATE TABLE `hfpm_page_component` (
  `hfpm_page_component_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面组件ID',
  `hfpm_page_component_name` varchar(64) DEFAULT NULL COMMENT '页面组件名称',
  `hfpm_page_component_type` int(2) DEFAULT NULL COMMENT '页面组件类型',
  `hfpm_page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
  `hfpm_data_set_id` bigint(20) DEFAULT NULL COMMENT '数据集ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `hfcfg_component_template_id` bigint(12) DEFAULT NULL COMMENT '组件模板id',
  PRIMARY KEY (`hfpm_page_component_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='页面组件';


CREATE TABLE `hfpm_page_entity_rel` (
  `hfpm_page_entity_rel_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面关联实体ID',
  `hfpm_page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `is_main_entity` int(2) DEFAULT NULL COMMENT '是否为主实体',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_page_entity_rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面关联实体';


CREATE TABLE `hfpm_page_event` (
  `hfpm_page_event_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '页面事件ID',
  `hfpm_page_id` bigint(20) DEFAULT NULL COMMENT '页面ID',
  `hfpm_event_name` varchar(32) DEFAULT NULL COMMENT '事件名称',
  `hfpm_event_monitor_object` varchar(64) DEFAULT NULL COMMENT '事件监听对象',
  `hfpm_event_monitor_object_type` varchar(64) DEFAULT NULL COMMENT '事件监听对象类型',
  `hfpm_event_type` int(2) DEFAULT NULL COMMENT '事件类型',
  `hfpm_event_effect_object` varchar(64) DEFAULT NULL COMMENT '事件作用对象',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `hfpm_page_component_id` bigint(20) DEFAULT NULL COMMENT '页面组件ID',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `effect_field` bigint(20) DEFAULT NULL COMMENT '作用域',
  `event_source` tinyint(4) DEFAULT NULL COMMENT '事件来源',
  `hfpm_page_event_code` varchar(64) DEFAULT NULL COMMENT '页面事件编码',
  `hfpm_page_event_name` varchar(128) DEFAULT NULL COMMENT '页面事件名称',
  `hfpm_page_event_type` tinyint(4) DEFAULT NULL COMMENT '页面事件类型',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `target_hfpm_page_id` bigint(20) DEFAULT NULL COMMENT '目标页面',
  `from_condition` varchar(200) DEFAULT NULL COMMENT '条件',
  `to_result` varchar(200) DEFAULT NULL COMMENT '结果',
  PRIMARY KEY (`hfpm_page_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面事件';


CREATE TABLE `hfpm_page_event_attr` (
  `hfpm_page_event_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面事件属性ID',
  `hfpm_page_event_id` bigint(20) DEFAULT NULL COMMENT '页面事件ID',
  `hfpm_page_event_attr_type` int(2) DEFAULT NULL COMMENT '页面事件属性类型\n            0:条件',
  `hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '实体属性ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `value_type` int(2) DEFAULT NULL COMMENT '值类型',
  `value` varchar(128) DEFAULT NULL COMMENT '值',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_page_event_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面事件属性';


CREATE TABLE `hfpm_program` (
  `hfpm_program_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `hfpm_program_name` varchar(64) NOT NULL COMMENT '项目名称',
  `hfpm_program_code` varchar(64) NOT NULL COMMENT '项目编码',
  `hfpm_program_desc` varchar(512) DEFAULT NULL COMMENT '项目描述',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfpm_program_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151031375439 DEFAULT CHARSET=utf8 COMMENT='项目';


CREATE TABLE `hfpm_program_cfg` (
  `hfpm_program_cfg_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目配置ID',
  `show_name` varchar(128) DEFAULT NULL COMMENT '项目标题',
  `hfcfg_program_template_id` bigint(20) DEFAULT NULL COMMENT '项目模板ID',
  `hfcfg_program_skin_id` bigint(20) DEFAULT NULL COMMENT '项目皮肤ID',
  `hfcfg_login_page_id` bigint(20) DEFAULT NULL COMMENT '登陆页面ID',
  `bg_img_url` varchar(128) DEFAULT NULL COMMENT '背景图片URL',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `hfcfg_db_connect_id` bigint(12) DEFAULT NULL COMMENT '数据库连接信息id',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `data_entity_name` varchar(128) DEFAULT NULL COMMENT '数据权限实体',
  `dictionarys` varchar(128) DEFAULT NULL COMMENT '系统字典实体',
  `func_auth_path` varchar(512) DEFAULT NULL COMMENT '功能授权路径',
  `func_entity_name` varchar(128) DEFAULT NULL COMMENT '功能权限实体',
  `user_auth_path` varchar(128) DEFAULT NULL COMMENT '用户授权路径',
  `user_entity_name` varchar(128) DEFAULT NULL COMMENT '用户登录实体',
  `super_auth_filter_entity` bigint(20) DEFAULT NULL COMMENT '超级管理员规则【实体】',
  `super_auth_filter_field` bigint(20) DEFAULT NULL COMMENT '超级管理员规则【字段】',
  `super_auth_filter_field_value` varchar(64) DEFAULT NULL COMMENT '超级管理员规则【字段值】',
  PRIMARY KEY (`hfpm_program_cfg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='项目设置';


CREATE TABLE `hfpm_test` (
  `hfpm_test_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'hfpm_testid',
  `hfpm_test_code` varchar(64) DEFAULT NULL COMMENT 'hfpm_test编码',
  `hfpm_test_name` varchar(128) DEFAULT NULL COMMENT 'hfpm_test名称',
  `hfpm_program_cfg_id` bigint(20) DEFAULT NULL COMMENT '项目配置ID',
  `hfcfg_program_template_id` bigint(20) DEFAULT NULL COMMENT '项目模板ID',
  PRIMARY KEY (`hfpm_test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试对象';


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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='菜单';


CREATE TABLE `hfsec_organize` (
  `hfsec_organize_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `hfsec_organize_code` varchar(64) DEFAULT NULL COMMENT '组织编码',
  `hfsec_organize_name` varchar(128) DEFAULT NULL COMMENT '组织名称',
  `hfsec_organize_type` tinyint(4) DEFAULT NULL COMMENT '组织类型',
  `hfsec_organize_level` tinyint(4) DEFAULT NULL COMMENT '组织级别',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `parent_hfsec_organize_id` bigint(12) DEFAULT NULL COMMENT '上级组织id',
  PRIMARY KEY (`hfsec_organize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='组织';


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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='角色授权';


CREATE TABLE `hfsec_user` (
  `hfsec_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `hfsec_user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `account` varchar(64) NOT NULL COMMENT '用户账号',
  `PASSWORD` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `gender` int(2) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(6) DEFAULT NULL COMMENT '手机号',
  `email` int(2) DEFAULT NULL COMMENT '邮箱',
  `addr` int(2) DEFAULT NULL COMMENT '地址',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `hfsec_organize_id` bigint(12) DEFAULT NULL COMMENT '组织ID',
  `STATUS` int(2) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户授权';


CREATE TABLE `hfus_entity_attr` (
  `hfus_entity_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '常用实体属性ID',
  `hfus_entity_attr_name` varchar(64) NOT NULL COMMENT '实体属性名称',
  `hfus_entity_attr_code` varchar(64) NOT NULL COMMENT '实体属性编码',
  `hfus_entity_attr_desc` varchar(128) DEFAULT NULL COMMENT '实体描述',
  `attr_type` int(2) DEFAULT NULL COMMENT '属性类型',
  `size` varchar(6) DEFAULT NULL COMMENT '大小',
  `ispk` int(2) DEFAULT NULL COMMENT '是否主键',
  `nullable` int(2) DEFAULT NULL COMMENT '是否可为空',
  `is_busi_attr` int(2) DEFAULT NULL COMMENT '是否业务属性',
  `is_redundant_attr` int(2) DEFAULT NULL COMMENT '是否冗余属性',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfus_entity_attr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='常用实体属性';


CREATE TABLE `hfus_entity_store` (
  `hfus_entity_store_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '常用实体库id',
  `hfus_entity_store_name` varchar(128) DEFAULT NULL COMMENT '常用实体库名称',
  `hfus_entity_store_code` varchar(64) DEFAULT NULL COMMENT '常用实体库编码',
  `sql_content` text COMMENT 'SQL建表语句',
  `entity_group` tinyint(4) DEFAULT NULL COMMENT '分组',
  `hfus_entity_store_type` tinyint(4) DEFAULT NULL COMMENT '常用实体库类型',
  `creator_id` bigint(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(12) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`hfus_entity_store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='常用实体库';


CREATE TABLE `hfus_entity_type_relat_entity_attr` (
  `hfus_entity_type_relat_entity_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `entity_type` int(2) DEFAULT NULL COMMENT '实体类型',
  `hfus_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '常用实体属性ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfus_entity_type_relat_entity_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体类型关联实体属性';


CREATE TABLE `hfus_page_event` (
  `hfus_page_event_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '常用页面事件ID',
  `hfpm_event_name` varchar(32) DEFAULT NULL COMMENT '事件名称',
  `hfpm_event_type` int(2) DEFAULT NULL COMMENT '事件类型',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfus_page_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常用页面事件';


CREATE TABLE `hfus_program_entity_attr` (
  `hfus_program_entity_attr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目常用实体ID',
  `hfpm_program_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `hfmd_entity_id` bigint(20) DEFAULT NULL COMMENT '实体ID',
  `hfmd_entity_attr_id` bigint(20) DEFAULT NULL COMMENT '实体属性ID',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfus_program_entity_attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目常用实体属性';


CREATE TABLE `hfus_word_store` (
  `hfus_word_store_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '单词库ID',
  `chinese_chars` varchar(64) DEFAULT NULL COMMENT '汉字名称',
  `english_name` varchar(64) DEFAULT NULL COMMENT '英语名称',
  `english_short_name` varchar(64) DEFAULT NULL COMMENT '英文简称',
  `op_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_op_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`hfus_word_store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='单词库';


CREATE TABLE `sec_menu` (
  `menu_id` varchar(64) NOT NULL,
  `menu_name` varchar(128) DEFAULT NULL,
  `url` longtext,
  `par_id` varchar(64) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_menu` (
  `menu_id` varchar(64) NOT NULL,
  `url` longtext,
  `pre_menu_id` varchar(64) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_org` (
  `sys_org_id` varchar(64) NOT NULL,
  `sys_org_name` varchar(128) DEFAULT NULL,
  `par_org_id` varchar(64) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `pri` int(11) DEFAULT NULL,
  PRIMARY KEY (`sys_org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_user` (
  `user_id` varchar(64) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `addr` longtext,
  `sign` longtext,
  `user_name` varchar(128) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `up_offer` (
  `offer_id` varchar(64) NOT NULL,
  `offer_type` varchar(64) DEFAULT NULL,
  `offer_desc` longtext,
  PRIMARY KEY (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


v_core_table_db;


v_test;
