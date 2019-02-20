/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : topui

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-02-20 09:54:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for act_evt_log
-- ----------------------------
DROP TABLE IF EXISTS `act_evt_log`;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_evt_log
-- ----------------------------

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_bytearray`;
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

-- ----------------------------
-- Records of act_ge_bytearray
-- ----------------------------
INSERT INTO `act_ge_bytearray` VALUES ('2', '1', 'source', null, 0x7B227374656E63696C736574223A7B226E616D657370616365223A22687474703A2F2F62336D6E2E6F72672F7374656E63696C7365742F62706D6E322E3023227D7D, null);

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_ge_property
-- ----------------------------
INSERT INTO `act_ge_property` VALUES ('next.dbid', '2501', '2');
INSERT INTO `act_ge_property` VALUES ('schema.history', 'create(5.22.0.0)', '1');
INSERT INTO `act_ge_property` VALUES ('schema.version', '5.22.0.0', '1');

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_actinst`;
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
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_hi_actinst
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_attachment`;
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
  `TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_hi_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_hi_comment
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_detail`;
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
  `TIME_` datetime NOT NULL,
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

-- ----------------------------
-- Records of act_hi_detail
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_identitylink`;
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

-- ----------------------------
-- Records of act_hi_identitylink
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
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

-- ----------------------------
-- Records of act_hi_procinst
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_taskinst`;
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
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime DEFAULT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_hi_taskinst
-- ----------------------------

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_varinst`;
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
  `CREATE_TIME_` datetime DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_hi_varinst
-- ----------------------------

-- ----------------------------
-- Table structure for act_id_group
-- ----------------------------
DROP TABLE IF EXISTS `act_id_group`;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_id_group
-- ----------------------------

-- ----------------------------
-- Table structure for act_id_info
-- ----------------------------
DROP TABLE IF EXISTS `act_id_info`;
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

-- ----------------------------
-- Records of act_id_info
-- ----------------------------

-- ----------------------------
-- Table structure for act_id_membership
-- ----------------------------
DROP TABLE IF EXISTS `act_id_membership`;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_id_membership
-- ----------------------------

-- ----------------------------
-- Table structure for act_id_user
-- ----------------------------
DROP TABLE IF EXISTS `act_id_user`;
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

-- ----------------------------
-- Records of act_id_user
-- ----------------------------

-- ----------------------------
-- Table structure for act_procdef_info
-- ----------------------------
DROP TABLE IF EXISTS `act_procdef_info`;
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

-- ----------------------------
-- Records of act_procdef_info
-- ----------------------------

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_re_deployment
-- ----------------------------

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp NULL DEFAULT NULL,
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

-- ----------------------------
-- Records of act_re_model
-- ----------------------------
INSERT INTO `act_re_model` VALUES ('1', '2', null, null, null, '2019-01-10 18:03:35', '2019-01-10 18:03:36', '1', '{\"name\":\"请假\",\"revision\":null,\"description\":\"\"}', null, '2', null, '');

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS `act_re_procdef`;
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

-- ----------------------------
-- Records of act_re_procdef
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_ru_event_subscr
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_execution`;
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
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
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

-- ----------------------------
-- Records of act_ru_execution
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_identitylink`;
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

-- ----------------------------
-- Records of act_ru_identitylink
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_ru_job
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_task`;
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
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
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

-- ----------------------------
-- Records of act_ru_task
-- ----------------------------

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_variable`;
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

-- ----------------------------
-- Records of act_ru_variable
-- ----------------------------

-- ----------------------------
-- Table structure for extend_act_business
-- ----------------------------
DROP TABLE IF EXISTS `extend_act_business`;
CREATE TABLE `extend_act_business` (
  `id` varchar(32) NOT NULL,
  `text` varchar(100) DEFAULT NULL COMMENT '业务流程名字',
  `actKey` varchar(50) DEFAULT NULL COMMENT '流程key ',
  `iconCls` varchar(255) DEFAULT NULL,
  `classurl` varchar(255) DEFAULT NULL COMMENT '类路径',
  `type` varchar(1) DEFAULT NULL COMMENT '0=根节点 1=分组 2=业务类 3=回调',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父节点id',
  `levelId` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createId` varchar(32) DEFAULT NULL,
  `updateId` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of extend_act_business
-- ----------------------------
INSERT INTO `extend_act_business` VALUES ('27e46d6e9e904ea9b0479a895858caa4', '请假', 'leave', '', 'com.qh.water_management.modules.flowwork.entity.LeaveEntity', '3', 'f28276525d0b44c085c0568f43384fa6', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-09 17:01:17', '2019-01-09 17:51:21', null);
INSERT INTO `extend_act_business` VALUES ('3087920fd2894b208384c824840b08db', '业务树根目录', 'root', '', null, '1', 'e584c9af8ce9441fa2c77a1f8e1fb9e4', '1', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 15:16:58', null, null);
INSERT INTO `extend_act_business` VALUES ('56c9c9b3abbd43d395e5b65cdcbbada5', '请假回调', 'leave_call', '', 'com.qh.water_management.modules.flowwork.callBack.ActCallBack', '4', '27e46d6e9e904ea9b0479a895858caa4', '4', '1', 'open', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-09 17:02:05', '2019-01-09 17:49:44', null);
INSERT INTO `extend_act_business` VALUES ('e584c9af8ce9441fa2c77a1f8e1fb9e4', '流程业务树', 'actTree', null, null, null, '1', '0', '1', 'closed', '1', null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 16:26:57', null, null);
INSERT INTO `extend_act_business` VALUES ('f28276525d0b44c085c0568f43384fa6', '人事部门', 'renshi', '', null, '2', '3087920fd2894b208384c824840b08db', '2', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 16:57:05', null, null);

-- ----------------------------
-- Table structure for extend_act_model
-- ----------------------------
DROP TABLE IF EXISTS `extend_act_model`;
CREATE TABLE `extend_act_model` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `actVersion` int(11) DEFAULT NULL COMMENT '版本号',
  `extendActBusinessId` varchar(32) DEFAULT NULL COMMENT '关联的 业务表 ID',
  `deploymentId` varchar(32) DEFAULT NULL COMMENT 'activiti中的部署表id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `modelId` varchar(32) DEFAULT NULL COMMENT 'activiti中的模型表id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `status` varchar(1) DEFAULT NULL COMMENT '发布状态 1:已发布 2：未发布',
  `businessName` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '业务流程单据编号',
  `instanceId` varchar(32) DEFAULT NULL COMMENT '流程实例id',
  `defId` varchar(255) DEFAULT NULL COMMENT '流程定义id',
  `startTime` datetime DEFAULT NULL COMMENT '流程发起时间',
  `startUserId` varchar(32) DEFAULT NULL,
  `createId` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateId` varchar(32) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of extend_act_model
-- ----------------------------
INSERT INTO `extend_act_model` VALUES ('e330ce5069474ac48f8525c16cb670fe', '1', '27e46d6e9e904ea9b0479a895858caa4', null, '', '1', '请假', '1', null, null, null, null, null, null, '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-10 18:03:35', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-15 11:22:37', null);

-- ----------------------------
-- Table structure for sys_auth_access
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_access`;
CREATE TABLE `sys_auth_access` (
  `id` varchar(50) NOT NULL COMMENT '权限id（主键）',
  `roleId` varchar(50) DEFAULT NULL,
  `menuId` varchar(50) DEFAULT NULL COMMENT '菜单id',
  `createId` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_access
-- ----------------------------

-- ----------------------------
-- Table structure for sys_auth_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_data`;
CREATE TABLE `sys_auth_data` (
  `id` varchar(50) NOT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `orgId` longtext,
  `sort` int(11) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `createId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateId` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限';

-- ----------------------------
-- Records of sys_auth_data
-- ----------------------------
INSERT INTO `sys_auth_data` VALUES ('1F253D05C11D0FFAE055000000000001', '1', '数据权限分组', null, '0', '0', '0', '1', 'closed', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-14 11:05:36', null, null, null, null);
INSERT INTO `sys_auth_data` VALUES ('22c3f513aa6f47bcb97d7d8980b2eb17', '96842621b3c54e088dff593e13820b85', '一级管理数据权限', '6cfa2d6067ee4e9cb179035552c9471a', '2', '2', null, '1', 'open', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-14 17:56:36', null, null, '', null);
INSERT INTO `sys_auth_data` VALUES ('2faf2b837385489aa63dc05c0ed63f69', '96842621b3c54e088dff593e13820b85', '超级管理数据权限', '74b269f649b24af688abaaaf50ce26e1,af1499ffb1ef4053ad142f31cc17abb4,48ad4add452f47c5bd75b38c7d2dd5c4,6cfa2d6067ee4e9cb179035552c9471a,2c07fd8e3d2448d59a47f5280874961b', '1', '2', null, '1', 'open', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-14 17:54:41', null, null, '', null);
INSERT INTO `sys_auth_data` VALUES ('96842621b3c54e088dff593e13820b85', '1F253D05C11D0FFAE055000000000001', '系统管理平台', '48ad4add452f47c5bd75b38c7d2dd5c4', '1', '1', null, '1', 'closed', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-14 17:52:48', null, null, '', null);
INSERT INTO `sys_auth_data` VALUES ('a9258d12a92d4bbfac40def7d21a7dcc', '1F253D05C11D0FFAE055000000000001', '协同办公系统', '6cfa2d6067ee4e9cb179035552c9471a', '2', '1', null, '1', 'closed', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-14 17:53:42', null, null, '', null);

-- ----------------------------
-- Table structure for sys_auth_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_group`;
CREATE TABLE `sys_auth_group` (
  `id` varchar(50) NOT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  `menuId` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `codeSetId` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createId` varchar(255) DEFAULT NULL,
  `updateId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_group
-- ----------------------------
INSERT INTO `sys_auth_group` VALUES ('036b6465c3334a3dbb413de6fd0c0471', '3ab985337a3d4caa85625fe61621e915', '协同超级管理员', '2', '69a443a55ada46d7b47c8c775d42709d', null, null, '1', 'open', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:38:41', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('1F253D05C11D0FFAE055000000000001', '1', '角色权限分组', '0', null, null, '1', '0', 'closed', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 10:37:50', null, null, null);
INSERT INTO `sys_auth_group` VALUES ('2ec603b5363345629bade9c7fc560e05', '1F253D05C11D0FFAE055000000000001', '系统管理平台', '1', '', null, null, '1', 'closed', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:33:25', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('3ab985337a3d4caa85625fe61621e915', '1F253D05C11D0FFAE055000000000001', '协同办公系统', '1', '', null, null, '2', 'closed', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:34:01', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('5a267e8ac16f4edca060e6a3f18265c0', '3ab985337a3d4caa85625fe61621e915', '测试', '2', '69a443a55ada46d7b47c8c775d42709d', null, null, '3', 'open', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:39:25', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('5ef925ea00d64ffdb7ad06c669fb9893', '2ec603b5363345629bade9c7fc560e05', '平台超级管理员', '2', '', null, null, '2', 'open', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:37:56', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('b727eabf965e4f78b495906db75197c7', '3ab985337a3d4caa85625fe61621e915', '协同普通用户', '2', '69a443a55ada46d7b47c8c775d42709d', null, null, '2', 'open', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:39:03', null, '', null);
INSERT INTO `sys_auth_group` VALUES ('ee297663ec454c6d8e6d0adbec788b84', '2ec603b5363345629bade9c7fc560e05', '系统超级管理员', '2', 'bb577a31c688421fb5840b346cd36c08', null, null, '1', 'open', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-17 10:37:23', null, '', null);

-- ----------------------------
-- Table structure for sys_auth_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_rule`;
CREATE TABLE `sys_auth_rule` (
  `id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_rule
-- ----------------------------

-- ----------------------------
-- Table structure for sys_basic_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_basic_config`;
CREATE TABLE `sys_basic_config` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `createId` varchar(255) DEFAULT NULL,
  `updateId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  `modifyFlag` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_basic_config
-- ----------------------------
INSERT INTO `sys_basic_config` VALUES ('8b0fb0c6001b422da87b8882fca9e2e0', '系统版本', 'systemVersion', 'SpringBoot2.0', '系统版本', null, '3', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-03 16:47:41', null, null, null, null);
INSERT INTO `sys_basic_config` VALUES ('976028c41b8945bbaaad137735559684', '系统名称', 'systemName', '信息化系统', '系统名称', null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-03 14:45:53', null, null, null, null);
INSERT INTO `sys_basic_config` VALUES ('bd91400dd62a4258aae966aea3b85fd2', '公司名称', 'companyName', '苏州盛景信息科技股份有限公司', '公司名称', '0', '2', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-03 15:18:38', '2018-12-03 16:44:45', null, '0', null);

-- ----------------------------
-- Table structure for sys_code_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_item`;
CREATE TABLE `sys_code_item` (
  `id` varchar(50) NOT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `codeSetId` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `children` varchar(255) DEFAULT NULL,
  `createId` varchar(255) DEFAULT NULL,
  `updateId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  `checked` varchar(255) DEFAULT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_code_item
-- ----------------------------
INSERT INTO `sys_code_item` VALUES ('029dfcae872c43b789078bdc211fb5ca', '839a98526e40455091b37f07d737444c', '甘肃省（甘）', null, '', 'AAAC', '19', null, '5', '19', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:16:52', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('06a85bf54e1f4b45b5095b282b278d4c', '8cc8338f39d94bdc879745d69a7ff515', '延庆县', null, '', 'AGC', '', null, '6', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:57:11', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('087f19a95e8743fdab266e06722c434f', 'fef11155262040d9a730e3aa0214bd69', '海淀区', null, '', 'AGC', '', null, '6', '8', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:29:05', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('0e491dfd7f3f45bfbea39fb601753b18', '839a98526e40455091b37f07d737444c', '山西省（晋）', null, '', 'AAAC', '20', null, '5', '20', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:17:17', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('0f82f61d2aeb4ef8aa4973eb04ce6279', '2b64ce760804472493032dabda3020cb', '本科', null, '', 'AABA', 'BK', null, '5', '70', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:23:49', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('20bcb19f97bb4507b02ac89b892a24da', '2b64ce760804472493032dabda3020cb', '高中', null, '', 'AABA', 'GZ', null, '5', '30', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:22:27', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('224ffbeafbcf44818628b6e1f76c0421', '6e0dcfa886be4a14a019b9d914a08c96', '招聘求职字典', null, '', 'C', '', null, '1', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:38:27', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('22efb7778eaf4614afc45e739cbbdb51', 'fef11155262040d9a730e3aa0214bd69', '石景山区', null, '', 'AGC', '', null, '6', '7', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:28:45', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('230425c2f51c40b59b00831417fcecb3', '6e0dcfa886be4a14a019b9d914a08c96', '基础信息字典', null, '', 'A', '', null, '1', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:35:40', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('2b64ce760804472493032dabda3020cb', '7c5af1cf15254bceb4fc6202bd14c291', '学历', null, '', 'AABA', '', null, '4', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:20:24', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('2ba5479c754045ceb1c3262e2f1736d1', '230425c2f51c40b59b00831417fcecb3', '地区指标', null, '', 'AG', '', null, '2', '7', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-12 16:46:58', '2018-12-28 12:57:37', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('2c6d609f1936416c801768ffcb9771d5', 'c6a126293ef04874a0cf9da8471a27e0', '基本指标', null, '', 'AAA', '', null, '3', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:58:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('2e90a425cd7f4166abcd75dce4af8056', 'fef11155262040d9a730e3aa0214bd69', '门头沟区', null, '', 'AGC', '', null, '6', '9', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:29:32', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('2f06904a4fd1490fb803dd8d43ba2b65', '2c6d609f1936416c801768ffcb9771d5', '行政职务', null, '', 'AAAF', '', null, '4', '6', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:02:36', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('3408e688535b41feb433c5f9aec667d5', 'fef11155262040d9a730e3aa0214bd69', '房山区', null, '', 'AGC', '', null, '6', '10', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:29:54', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('3714544ac6ea4852b0c9dffde243313f', '839a98526e40455091b37f07d737444c', '宁夏回族（宁）', null, '', 'AAAC', '30', null, '5', '30', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:23:14', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('37e2e47be5d347e0905cd7e083221793', 'e4755663fafe430cb9ad1b6619093590', '汉族', null, '', 'AAAB', '01', null, '5', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:05:03', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('3d0ac72bfa2644c681f245d75570a759', '2b64ce760804472493032dabda3020cb', '小学', null, '', 'AABA', 'XX', null, '5', '10', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-02 16:21:21', '2019-01-02 16:21:42', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('3e5d513b8da34d5d90d22bdbcd6da0d2', 'f02386e8f4ab49a287325e1ff886ea5f', '女', null, '', 'AAAA', '', null, '5', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:59:26', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('3e78fd8bde484f20a14ea8e42d74cbdb', 'f02386e8f4ab49a287325e1ff886ea5f', '男女不限', null, '', 'AAAA', '', null, '5', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:59:50', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('3f423cd53c264cf99b34b4216ef048eb', '839a98526e40455091b37f07d737444c', '香港特别行政区', null, '', 'AAAC', '33', null, '5', '33', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:24:10', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('41f64c6af7a8445c8ac6b53235ecb79a', '839a98526e40455091b37f07d737444c', '辽宁省（辽）', null, '', 'AAAC', '08', null, '5', '8', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:12:36', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('45d2f1a1ed244b1ea4114f99a725c575', '839a98526e40455091b37f07d737444c', '河北省（冀）', null, '', 'AAAC', '05', null, '5', '5', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:10:53', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('47309fc58cf74c1f885982a790dc505b', '839a98526e40455091b37f07d737444c', '贵州省（贵）', null, '', 'AAAC', '25', null, '5', '25', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:19:29', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('4bbb749a0f484a4db702506ba326a943', '839a98526e40455091b37f07d737444c', '浙江省（浙）', null, '', 'AAAC', '15', null, '5', '15', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:15:15', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('4e0d47f94e0a49f898de4529488d39e6', '839a98526e40455091b37f07d737444c', '湖北省（鄂） ', null, '', 'AAAC', '17', null, '5', '17', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:16:11', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('4ff386d529eb41c3a88f9ce6cf0a8792', 'fef11155262040d9a730e3aa0214bd69', '昌平区', null, '', 'AGC', '', null, '6', '13', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:31:02', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('52ca24213806442e8f14a300631842b9', '2b64ce760804472493032dabda3020cb', '初中', null, '', 'AABA', 'CZ', null, '5', '20', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:22:06', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('56f1e612a59d43868409556a52e504be', 'e4755663fafe430cb9ad1b6619093590', '京族', null, '', 'AAAB', '05', null, '5', '5', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:07:40', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('5a0e3520fdab4d889d1448fa75ef7bf0', 'fef11155262040d9a730e3aa0214bd69', '平谷区', null, '', 'AGC', '', null, '6', '16', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:32:04', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('5cc660f5b26f41158d89b030e984969a', '2b64ce760804472493032dabda3020cb', '硕士', null, '', 'AABA', 'SS', null, '5', '80', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:24:07', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('5dc195ed71674276bb36fed7fc3b6bbb', '839a98526e40455091b37f07d737444c', '青海省（青）', null, '', 'AAAC', '27', null, '5', '27', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:21:39', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('5e7147a13ff44a8591f4643909507036', '839a98526e40455091b37f07d737444c', '安徽省（皖） ', null, '', 'AAAC', '11', null, '5', '11', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:13:50', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('60893cf4272f40b493aa5f8e51592f66', '998a164a769442318b2bb26b13c4fc0c', '指标层级', null, '', 'ACD', '', null, '3', '4', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:04:48', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('64157c4b24d04ec69a7e5db2e9fd43f0', 'f02386e8f4ab49a287325e1ff886ea5f', '男', null, '', 'AAAA', '', null, '5', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:59:08', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('648f31f1968b4e869c3629a6aad8bf80', '7c5af1cf15254bceb4fc6202bd14c291', '学位', null, '', 'AABB', '', null, '4', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-02 16:20:45', '2019-01-02 16:26:01', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('6b04facc9a7243fe9a977413efabc45c', '839a98526e40455091b37f07d737444c', '四川省（川）', null, '', 'AAAC', '29', null, '5', '29', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:22:54', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('6d00f320db4d4d54bfd2ea0a1254f646', '2ba5479c754045ceb1c3262e2f1736d1', '中国', null, '', 'AGA', '', null, '3', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:48:06', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('6e0dcfa886be4a14a019b9d914a08c96', '23DB879B877C3C44E055000000000001', '数据字典管理', null, '', '0', '', null, '0', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 15:52:54', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('6f17c635ceda46a48bcc552b4d28f094', '839a98526e40455091b37f07d737444c', '河南省（豫） ', null, '', 'AAAC', '06', null, '5', '6', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:11:43', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('7391057c57a84849bb8a5902689f6d2b', '2b64ce760804472493032dabda3020cb', '大专', null, '', 'AABA', 'DZ', null, '5', '50', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:23:03', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('768842138e764a0190963ee94d340c3f', 'e4755663fafe430cb9ad1b6619093590', '瑶族', null, '', 'AAAB', '02', null, '5', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:05:55', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('768b246a6b5841ceba615694387a1ba7', '839a98526e40455091b37f07d737444c', '台湾省（台）', null, '', 'AAAC', '32', null, '5', '32', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:23:53', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('7c5af1cf15254bceb4fc6202bd14c291', 'c6a126293ef04874a0cf9da8471a27e0', '教育指标', null, '', 'AAB', '', null, '3', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:03:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('7e6e1ccc6325450a80cefe06961ae95a', '839a98526e40455091b37f07d737444c', '陕西省（陕）', null, '', 'AAAC', '22', null, '5', '22', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:17:59', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('7f8d118327ae428088c7ce5cdd28f6a7', '60893cf4272f40b493aa5f8e51592f66', '三级', null, '', 'ACDA', '3', null, '4', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:06:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('80505a5bc7124efc8a8faafd54e1288e', 'fef11155262040d9a730e3aa0214bd69', '丰台区', null, '', 'AGC', '', null, '6', '6', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:28:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('8195e8498a724451bb04b25c8df57a05', '839a98526e40455091b37f07d737444c', '天津市（津） ', null, '', 'AAAC', '02', null, '5', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:09:08', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('82ce3174b6f54520b2eb9f781f67c739', '648f31f1968b4e869c3629a6aad8bf80', '学士', null, '', 'AABB', '', null, '5', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-02 16:24:29', '2019-01-02 16:26:08', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('82dd259626804402b0223f1826194f30', '2b64ce760804472493032dabda3020cb', '技校', null, '', 'AABA', 'JX', null, '5', '60', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:23:22', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('839a98526e40455091b37f07d737444c', '2c6d609f1936416c801768ffcb9771d5', '籍贯', null, '', 'AAAC', '', null, '4', '3', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:01:12', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('83deb6d587e249ceaee0ab3e14567c91', '839a98526e40455091b37f07d737444c', '北京市（京） ', null, '', 'AAAC', '01', null, '5', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:08:41', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('85b5af2d8ab04275bd16aaf73b783ebc', '2c6d609f1936416c801768ffcb9771d5', '岗位性质', null, '', 'AAAE', '', null, '4', '5', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:02:08', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('85be6819e6184af080f84fc1ba9ecae8', '839a98526e40455091b37f07d737444c', '西藏（藏）', null, '', 'AAAC', '28', null, '5', '28', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:22:26', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('8cc8338f39d94bdc879745d69a7ff515', 'eb51cea3c19e40949cbd14fb4d67b4f4', '县', null, '', 'AGB', '', null, '5', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:35:04', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('923fcec8a2d74f0da89ef6c6a4ca87ec', '60893cf4272f40b493aa5f8e51592f66', '五级', null, '', 'ACDA', '5', null, '4', '5', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:06:57', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('93b771fe8bf24167a14e39bd834c566c', '648f31f1968b4e869c3629a6aad8bf80', '硕士', null, '', 'AABB', '', null, '5', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-02 16:24:42', '2019-01-02 16:26:13', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('9406309b1eb941528338e5e85ba44b47', '60893cf4272f40b493aa5f8e51592f66', '一级', null, '', 'ACDA', '1', null, '4', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:05:20', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('97e299e49d3e46b68cfa4153aff631bd', '8cc8338f39d94bdc879745d69a7ff515', '密云县', null, '', 'AGC', '', null, '6', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:36:27', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('998a164a769442318b2bb26b13c4fc0c', '230425c2f51c40b59b00831417fcecb3', '其他指标', null, '', 'AC', '', null, '2', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:03:09', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('9a80fc80bc734f4aa12785d1bfb9c941', '2c6d609f1936416c801768ffcb9771d5', '婚姻状况', null, '', 'AAAD', '', null, '4', '4', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:01:43', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a16d2586779e426ca34e753ba631239b', '60893cf4272f40b493aa5f8e51592f66', '六级', null, '', 'ACDA', '6', null, '4', '6', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:07:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a175661dc98e40fd9245bc88f90b4e76', 'fef11155262040d9a730e3aa0214bd69', '大兴区', null, '', 'AGC', '', null, '6', '14', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:31:24', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a243327eac5441aebb7c732855e3b26c', '839a98526e40455091b37f07d737444c', '吉林省（吉）', null, '', 'AAAC', '23', null, '5', '23', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:18:43', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a70ececc855c4c0e8c9d438aed0cac49', '60893cf4272f40b493aa5f8e51592f66', '二级', null, '', 'ACDA', '2', null, '4', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:05:50', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a9c4c7f50aba4c9abc423813990115b9', 'c6a126293ef04874a0cf9da8471a27e0', '党籍信息', null, '', 'AAC', '', null, '3', '3', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:03:37', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('a9cec9aaf7d04422adbccf9e08a265b3', '839a98526e40455091b37f07d737444c', '海南省（琼）', null, '', 'AAAC', '31', null, '5', '31', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:23:33', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('af0a3f26a37b488db8651393ba72d7f6', '839a98526e40455091b37f07d737444c', '广东省（粤）', null, '', 'AAAC', '26', null, '5', '26', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:20:03', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('babbf5ee018f4d5e936e9e98afa055b0', '839a98526e40455091b37f07d737444c', '新疆维吾尔（新） ', null, '', 'AAAC', '13', null, '5', '13', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:14:27', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('bc2dd114c6cd46f99a448a7fe4eb4c6b', '839a98526e40455091b37f07d737444c', '湖南省（湘）', null, '', 'AAAC', '10', null, '5', '10', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:13:19', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('be0d39dc841148359481b75c7670f2b5', '839a98526e40455091b37f07d737444c', '云南省（云） ', null, '', 'AAAC', '07', null, '5', '7', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:12:03', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('be464e6c30a049dabcdc673d8ebee324', '60893cf4272f40b493aa5f8e51592f66', '七级', null, '', 'ACDA', '7', null, '4', '7', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:07:32', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('bebc38e2902b4c62aaf15b78fe5e4a2c', 'fef11155262040d9a730e3aa0214bd69', '怀柔区', null, '', 'AGC', '', null, '6', '15', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:31:41', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('c077aa9b4c79463196d706742bd7d66a', 'fef11155262040d9a730e3aa0214bd69', '通州区', null, '', 'AGC', '', null, '6', '11', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:30:14', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('c2cbfb123e584d11853f64a9b52b45ba', '839a98526e40455091b37f07d737444c', '广西壮族（桂）', null, '', 'AAAC', '18', null, '5', '18', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:16:32', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('c2e86a5b89f04a24b6c0de6561bfa6f7', '839a98526e40455091b37f07d737444c', '内蒙古（蒙）', null, '', 'AAAC', '21', null, '5', '21', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:17:39', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('c6a126293ef04874a0cf9da8471a27e0', '230425c2f51c40b59b00831417fcecb3', '人员指标', null, '', 'AA', '', null, '2', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:56:57', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('c9cdb6a3bc6342cea78b0f6e13d534da', '648f31f1968b4e869c3629a6aad8bf80', '博士', null, '', 'AABB', '', null, '5', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-02 16:24:55', '2019-01-02 16:26:21', '', '', null, null);
INSERT INTO `sys_code_item` VALUES ('ca66480c824d4ac0b816a83d9056f8d0', '839a98526e40455091b37f07d737444c', '福建省（闽）', null, '', 'AAAC', '24', null, '5', '24', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:19:08', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('cf85cacb3813499abb9bd7ac4bc33535', 'fef11155262040d9a730e3aa0214bd69', '东城区', null, '', 'AGC', '', null, '6', '1', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:49:51', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('d0ddb1e185894fc195afa6de614ad33d', 'e4755663fafe430cb9ad1b6619093590', '珞巴族', null, '', 'AAAB', '06', null, '5', '6', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:08:04', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('d8dab8060cc040a5ab99eaff102e3b6d', 'e4755663fafe430cb9ad1b6619093590', '撒拉族', null, '', 'AAAB', '04', null, '5', '4', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:07:17', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('dbbad1749bf4407bad3dface368e8317', '60893cf4272f40b493aa5f8e51592f66', '八级', null, '', 'ACDA', '8', null, '4', '8', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:07:54', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('df08182690a54f3dae407e4d35d0b12d', 'e4755663fafe430cb9ad1b6619093590', '拉祜族', null, '', 'AAAB', '03', null, '5', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:06:26', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e05e9d8ef5e64766a66fd0370eb35161', 'fef11155262040d9a730e3aa0214bd69', '崇文区', null, '', 'AGC', '', null, '6', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:51:38', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e4755663fafe430cb9ad1b6619093590', '2c6d609f1936416c801768ffcb9771d5', '民族', null, '', 'AAAB', '', null, '4', '2', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:00:42', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e4a978cc11cb4f1b9e7072792086c9aa', '2b64ce760804472493032dabda3020cb', '中专', null, '', 'AABA', 'ZZ', null, '5', '40', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-02 16:22:46', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e5690c7ac23e4d5e8aaa8dffec704c5d', '60893cf4272f40b493aa5f8e51592f66', '四级', null, '', 'ACDA', '4', null, '4', '4', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-13 14:06:35', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e5716c84a0d54f3989b1d526a6b7c339', '839a98526e40455091b37f07d737444c', '重庆市（渝） ', null, '', 'AAAC', '04', null, '5', '4', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:10:29', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('e5d0968ff8eb4049bd345605f283e155', 'fef11155262040d9a730e3aa0214bd69', '顺义区', null, '', 'AGC', '', null, '6', '12', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:30:34', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('eb12196bd14b42a8a4e945d7849ef46f', 'fef11155262040d9a730e3aa0214bd69', '宣武区', null, '', 'AGC', '', null, '6', '4', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:27:13', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('eb51cea3c19e40949cbd14fb4d67b4f4', '6d00f320db4d4d54bfd2ea0a1254f646', '北京市', null, '', 'AGA', '', null, '4', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:48:44', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('ebcc7c88f53f4b6ab66763c518f5a7dd', '839a98526e40455091b37f07d737444c', '澳门特别行政区', null, '', 'AAAC', '34', null, '5', '34', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:24:29', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f02386e8f4ab49a287325e1ff886ea5f', '2c6d609f1936416c801768ffcb9771d5', '性别', null, '', 'AAAA', '', null, '4', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 12:58:45', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f090c58c5ce042bf9d2af6b7c8deafeb', 'fef11155262040d9a730e3aa0214bd69', '西城区', null, '', 'AGC', '', null, '6', '2', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:51:01', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f22553715c5040e3aac61ba952546660', '839a98526e40455091b37f07d737444c', '山东省（鲁） ', null, '', 'AAAC', '12', null, '5', '12', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:14:10', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f39a4c1a432d4b8795502a1151a015bd', '839a98526e40455091b37f07d737444c', '上海市（沪） ', null, '', 'AAAC', '03', null, '5', '3', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:09:30', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f4919ddc6ab04458b11d217647d40879', 'fef11155262040d9a730e3aa0214bd69', '朝阳区', null, '', 'AGC', '', null, '6', '5', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-21 14:27:45', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('f85ad347479e4d6886fd3f96e99e224f', '839a98526e40455091b37f07d737444c', '江苏省（苏）', null, '', 'AAAC', '14', null, '5', '14', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:14:48', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('fd440cc716704cbca2b2f00258c906b9', '839a98526e40455091b37f07d737444c', '江西省（赣）', null, '', 'AAAC', '16', null, '5', '16', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:15:49', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('fea15a016cd944f8ba742ce2c58cea90', '839a98526e40455091b37f07d737444c', '黑龙江省（黑） ', null, '', 'AAAC', '09', null, '5', '9', 'open', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-28 13:12:59', null, '', null, null, null);
INSERT INTO `sys_code_item` VALUES ('fef11155262040d9a730e3aa0214bd69', 'eb51cea3c19e40949cbd14fb4d67b4f4', '市辖区', null, '', 'AGB', '', null, '5', '1', 'closed', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 16:49:25', null, '', null, null, null);

-- ----------------------------
-- Table structure for sys_data_dict_cate
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dict_cate`;
CREATE TABLE `sys_data_dict_cate` (
  `id` varchar(50) NOT NULL COMMENT 'uuid',
  `text` varchar(255) DEFAULT NULL COMMENT '字典集名称',
  `code` varchar(255) DEFAULT NULL COMMENT '字典集代码',
  `value` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createId` varchar(50) DEFAULT NULL,
  `updateId` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dict_cate
-- ----------------------------
INSERT INTO `sys_data_dict_cate` VALUES ('03b0eaf9019e4902970860d86648ee11', '字段类型Oracle', 'fieldTypeOracle', null, '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-05 16:27:13', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('05dc70c683de44348cbb1061f843dd34', '业务类型', 'actBusType', null, '8', '业务类型', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 14:37:26', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('0b6167600348442e8b433028cd7e2d72', '是否', 'yesOrNo', null, '3', '是否', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:28:22', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('122d5b2c2c2144f9a932af9e1b0ca02c', '启用禁用', 'en-disable', null, '5', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:49:55', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('12cf180909a041cb9d11a1ce8dcea515', '状态', 'reperporyStatus', '', '1', '状态', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2018-12-05 14:20:31', '2018-12-05 14:34:50', null);
INSERT INTO `sys_data_dict_cate` VALUES ('256fbc60c6824a429d99c5a909c2730c', '职务', 'post', null, '7', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:26:07', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('8be7dd1f33424ac3a8bc88a540435010', '节点状态', 'nodeState', null, '4', '节点状态', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:31:31', null, null);
INSERT INTO `sys_data_dict_cate` VALUES ('8fb738a8009347f9918a7211cecd8fa8', '机构类型', 'type', null, '6', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:51:38', null, null);

-- ----------------------------
-- Table structure for sys_data_dict_dtl
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dict_dtl`;
CREATE TABLE `sys_data_dict_dtl` (
  `id` varchar(50) NOT NULL COMMENT 'uuid',
  `puuid` varchar(50) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL COMMENT '字典项名称',
  `value` varchar(255) DEFAULT NULL COMMENT '字典项值',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createId` varchar(50) DEFAULT NULL,
  `updateId` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(11) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `selected` tinyint(4) DEFAULT NULL COMMENT '默认选中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dict_dtl
-- ----------------------------
INSERT INTO `sys_data_dict_dtl` VALUES ('05d0ad7134f34c32a4050fd017eec434', '8fb738a8009347f9918a7211cecd8fa8', '员工职位', '3', '3', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:52:23', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('0d508d31f30941059e933e5595f46fca', '256fbc60c6824a429d99c5a909c2730c', ' 项目组长', '9', '9', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:44:45', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('20ceb692e66941848bc5e93e0c25d67f', '256fbc60c6824a429d99c5a909c2730c', '助理工程师', '11', '11', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:45:08', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('2613b75622924abab58bc6c18dc21717', '256fbc60c6824a429d99c5a909c2730c', '产品总监', '5', '5', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:43:02', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('277502c4aa2f49ee906a370d05b1176e', '256fbc60c6824a429d99c5a909c2730c', '总经理', '2', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:26:41', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('2ca7328ae02640f1af5370a9054290d9', '256fbc60c6824a429d99c5a909c2730c', '技术总监', '4', '4', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:42:49', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('36c1567413524339b7268bf2cd93a6dc', '256fbc60c6824a429d99c5a909c2730c', '行政工程师', '12', '12', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:45:22', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('3887a0f6de75489eace5e434a1588e7b', '0b6167600348442e8b433028cd7e2d72', '否', '0', '2', '0', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:29:34', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('4581ce8d28074d228401a6271dde65e4', '256fbc60c6824a429d99c5a909c2730c', '部门经理', '7', '7', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:43:26', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('51a782f0843343fd8a8aa11e314666e9', '122d5b2c2c2144f9a932af9e1b0ca02c', '禁用', '0', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:50:30', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('61d667f5a8414b75a57d52d529c28a6c', '05dc70c683de44348cbb1061f843dd34', '根节点', '1', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 14:38:19', null, null, null, '0');
INSERT INTO `sys_data_dict_dtl` VALUES ('6397db68376d4b598a5f0e9824a430b9', '256fbc60c6824a429d99c5a909c2730c', '研发工程师', '10', '10', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:44:57', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('6e4283f1269d48989de73256fbefb22c', '122d5b2c2c2144f9a932af9e1b0ca02c', '启用', '1', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:50:23', null, null, null, '1');
INSERT INTO `sys_data_dict_dtl` VALUES ('72bae3fc88ab4baaa1cc3a96741167eb', '12cf180909a041cb9d11a1ce8dcea515', '未关闭', '1', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-05 17:40:16', null, null, null, '1');
INSERT INTO `sys_data_dict_dtl` VALUES ('75a6fd7c4f574024a57972585052cd67', '05dc70c683de44348cbb1061f843dd34', '分组', '2', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 14:38:42', null, null, null, '1');
INSERT INTO `sys_data_dict_dtl` VALUES ('7e4a92ab9e5c4810847d4b20e1e28d92', '256fbc60c6824a429d99c5a909c2730c', '董事长', '1', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:26:28', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('7f87d1173043446789e4a50c7b5dcf6e', '05dc70c683de44348cbb1061f843dd34', '业务类', '3', '3', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-09 14:39:00', '2019-01-09 14:39:15', null, '', '0');
INSERT INTO `sys_data_dict_dtl` VALUES ('8a094b256efb4d7da4a6e1331d8bc0b3', '256fbc60c6824a429d99c5a909c2730c', '项目经理', '8', '8', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:43:39', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('a283ec05f40c48b78123c69931fbe083', '0b6167600348442e8b433028cd7e2d72', '是', '1', '1', '0', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:29:05', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('a63803e63591462eb0f16abcf005a84c', '256fbc60c6824a429d99c5a909c2730c', '副总经理', '3', '3', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:26:57', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('b1b8808a8ce549b9945f5a123678ddfc', '8be7dd1f33424ac3a8bc88a540435010', '无子节点', 'open', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:32:43', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('bf7725b3e4604ab9ae45c2aab63f4986', '05dc70c683de44348cbb1061f843dd34', '回调', '4', '4', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-09 14:39:30', null, null, null, '0');
INSERT INTO `sys_data_dict_dtl` VALUES ('cb82f9ded5bb4d6ebdf1bbe0aaa4ab11', '8fb738a8009347f9918a7211cecd8fa8', '公司企业', '1', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:52:00', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('d02a0dbc0e2d429cb86d49c82f4bb03e', '12cf180909a041cb9d11a1ce8dcea515', '关闭', '2', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-05 17:51:33', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('e0f97bf076c44651931c26dc160c0344', '8fb738a8009347f9918a7211cecd8fa8', '一级部门', '2', '2', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 09:52:13', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('f03e0ae0bdff4c84901b5bc1a70ff7b1', '8be7dd1f33424ac3a8bc88a540435010', '有子节点', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 13:32:22', null, null, null, null);
INSERT INTO `sys_data_dict_dtl` VALUES ('f5c29e6ef25d492e85f1b064db587d16', '256fbc60c6824a429d99c5a909c2730c', '运营总监', '6', '6', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 11:43:13', null, null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuId` varchar(50) NOT NULL COMMENT '菜单Id(uuid)',
  `parentId` varchar(50) DEFAULT NULL COMMENT '上级Id',
  `menuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `menuIcon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menuType` int(11) DEFAULT NULL COMMENT '菜单类型',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `menuOrder` varchar(255) DEFAULT NULL COMMENT '菜单排序',
  `menuStatus` int(255) DEFAULT NULL COMMENT '菜单状态',
  `expanded` int(11) DEFAULT NULL COMMENT '是否展开（0-折叠，1-展开）',
  `createId` varchar(255) DEFAULT NULL,
  `updateId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('bb577a31c688421fb5840b346cd36c08', '0', '系统设置', null, 'fa fa-cog', null, null, '100', '1', '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-11-20 10:37:45', null, null);

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation` (
  `id` varchar(255) NOT NULL COMMENT '操作Id，主键',
  `desc` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` varchar(50) NOT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `typeText` varchar(11) DEFAULT NULL,
  `typeValue` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `leaderId` varchar(255) DEFAULT NULL,
  `children` varchar(255) DEFAULT NULL,
  `del` int(11) DEFAULT '1',
  `createId` varchar(50) DEFAULT NULL,
  `updateId` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  `checked` varchar(255) DEFAULT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('2c07fd8e3d2448d59a47f5280874961b', '74b269f649b24af688abaaaf50ce26e1', '综合办公室', '2', '一级部门', 'zhbgs', '2', '', '2', '1', 'closed', null, '老鲁', null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 17:43:40', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('48ad4add452f47c5bd75b38c7d2dd5c4', 'af1499ffb1ef4053ad142f31cc17abb4', '总经理', '3', '员工职位', 'zjl', '3', '', '1', '1', 'open', null, '老鲁', null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 09:36:46', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('6cfa2d6067ee4e9cb179035552c9471a', 'af1499ffb1ef4053ad142f31cc17abb4', '副总经理', '3', '员工职位', 'fzjl', '3', '', '2', '1', 'open', null, '老鲁', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-14 17:40:11', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('70b332fa1acd4e008a256fe1c55a0248', '1', '组织机构树', null, null, 'orgTree', '0', null, null, '', 'closed', null, null, null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 10:01:26', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('74b269f649b24af688abaaaf50ce26e1', '70b332fa1acd4e008a256fe1c55a0248', '苏州盛景科技股份有限公司', '1', '公司企业', 'mapscene', '1', '', '1', '1', 'closed', null, '老鲁', null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 13:36:07', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('af1499ffb1ef4053ad142f31cc17abb4', '74b269f649b24af688abaaaf50ce26e1', '总经理部', '2', '一级部门', 'zjlb', '2', '', '1', '1', 'closed', null, '老鲁', null, '1', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-11 17:38:41', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('df4d9024a0794c1098b9daa3bc425bf7', '2c07fd8e3d2448d59a47f5280874961b', '部门副经理', '3', '员工职位', 'zhbgsbmfjl', '3', '', '2', '1', 'open', null, 'xq', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 13:16:04', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('dfa473d23a8d4f13b7c0fc8103773fee', '2c07fd8e3d2448d59a47f5280874961b', '部门经理', '3', '员工职位', 'zhbgsbmjl', '3', '', '1', '1', 'open', null, 'lw', null, null, '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 13:15:23', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(255) NOT NULL COMMENT '权限Id',
  `pdesc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `menuId` varchar(255) DEFAULT NULL COMMENT '菜单Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_operation`;
CREATE TABLE `sys_permission_operation` (
  `permissionId` varchar(255) NOT NULL,
  `operationId` varchar(255) NOT NULL,
  PRIMARY KEY (`permissionId`,`operationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` varchar(50) NOT NULL COMMENT 'uuid',
  `parentId` varchar(50) DEFAULT NULL COMMENT '父级编号',
  `text` varchar(255) DEFAULT NULL COMMENT '名称',
  `codeSetId` varchar(255) DEFAULT NULL,
  `resourceType` varchar(255) DEFAULT NULL COMMENT '资源类别',
  `url` varchar(255) DEFAULT NULL COMMENT '资源地址或标识',
  `iconCls` varchar(255) DEFAULT '' COMMENT '资源图标',
  `levelId` int(255) DEFAULT NULL COMMENT '层级',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `state` varchar(255) DEFAULT NULL COMMENT '是否有子节点',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL,
  `createId` varchar(50) DEFAULT NULL,
  `updateId` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('03d522a77b1849c3a5a99285e955702a', 'f315449cabce4779835c4998c41c8218', '已启动流程', 'menu', 'menu', '/office/ewfRuExecution/start', 'fa fa-deaf', '3', '3', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:05:38', null, null, null);
INSERT INTO `sys_resources` VALUES ('0b72b2d2538144e29ae168f6f7e00e84', '69a443a55ada46d7b47c8c775d42709d', '文档中心', 'menu', 'menu', '', 'fa fa-book', '2', '5', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:23:04', null, null, null);
INSERT INTO `sys_resources` VALUES ('0b76b3f10a584f3380cb5b64088fa8af', '8aa08e904fb13570014fb157bb9f0001', '企业管理', 'menu', 'menu', null, 'fa fa-dashboard', '1', '2', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('139e5cb000e04a74b137f490dae63aa8', 'a1aeaf2e9549456b9435cb6babe222db', '接口管理', 'menu', 'menu', '/swagger-ui.html', '', '3', '3', 'open', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:08:04', null, null, null);
INSERT INTO `sys_resources` VALUES ('1F0AC3604622C63FE055000000000001', 'be37b40107764018bfe35a82465aa9e3', '基础配置', 'menu', 'menu', '/system/config/index', 'fa fa-cube', '3', '3', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('276eae19a4f7447a965dd9cd4780d7e7', '380a81889f5944c083f57225da9f677a', '数据权限', 'menu', 'menu', '/system/sysAuthData/index', 'fa fa-support', '2', '4', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 16:09:50', null, null, null);
INSERT INTO `sys_resources` VALUES ('28c13a61d2694124bbde111f336e328c', '69a443a55ada46d7b47c8c775d42709d', '任务管理', 'menu', 'menu', null, 'fa fa-book', '2', '3', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('31cc871b6a9b464f84164fc70cc3726f', '8aa08e904fb13570014fb157bb9f0001', '流程中心', 'menu', 'menu', null, 'fa fa-xing-square', '1', '4', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('35d23d7a8a1f467583611a84a632b323', 'be37b40107764018bfe35a82465aa9e3', '树形字典', 'menu', 'menu', '/system/codeItem/index?codeSetId=0&amp;levelId=1', 'fa fa-bookmark', '2', '4', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-12 14:21:45', null, null, null);
INSERT INTO `sys_resources` VALUES ('380a81889f5944c083f57225da9f677a', 'bb577a31c688421fb5840b346cd36c08', '用户管理', 'menu', 'menu', null, 'fa fa-user', '2', '2', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('3a8fb265bc8b4ca888f2a6f95794b123', '28c13a61d2694124bbde111f336e328c', '未完成任务', 'menu', 'menu', '/office/oaTaskBase/indexNotCompleted', '', '3', '4', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:19:17', null, null, null);
INSERT INTO `sys_resources` VALUES ('422d8b35b90d4ad08484ac7e60e4167a', '31cc871b6a9b464f84164fc70cc3726f', '流程设计', 'menu', 'menu', '', 'fa fa-book', '2', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:11:57', null, null, null);
INSERT INTO `sys_resources` VALUES ('4dfabf1b5f624cf7b10c96b9cd078ec2', 'a1aeaf2e9549456b9435cb6babe222db', '代码生成', 'menu', 'menu', '/system/generator/index', 'fa fa-code', '3', '2', 'open', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-03 14:07:29', '2019-01-03 14:08:48', null, null);
INSERT INTO `sys_resources` VALUES ('511f08c15d214ce5bb0c75069fbe92d7', '0b72b2d2538144e29ae168f6f7e00e84', '本地文档', 'menu', 'menu', '/office/archive/index?type=local', '', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:23:39', null, null, null);
INSERT INTO `sys_resources` VALUES ('538489afbed540afa6b964940c17e8ef', 'bb577a31c688421fb5840b346cd36c08', '监控日志', 'menu', 'menu', '', 'fa fa-heartbeat', '2', '91', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 13:55:14', null, null, null);
INSERT INTO `sys_resources` VALUES ('5580a7799e3c486c96adcd57a00d3ae1', '8aa08e904fb13570014fb157bb9f0001', '表单中心', 'menu', 'menu', null, 'fa fa-wpforms', '1', '5', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('5602156efc934d7f894ab9f74583f67a', '31cc871b6a9b464f84164fc70cc3726f', '业务流程', 'menu', 'menu', '', 'fa fa-book', '2', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:11:32', null, null, null);
INSERT INTO `sys_resources` VALUES ('58c080fbb9c64c4d82811c790981b17b', 'b5bcb3fa5eca4524a3a74e74f17e2a76', '通用流程', 'menu', 'menu', '', 'fa fa-book', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:24:14', null, null, null);
INSERT INTO `sys_resources` VALUES ('5e28b350884f4af5bb9d3557f1988941', '380a81889f5944c083f57225da9f677a', '组织机构', 'menu', 'menu', '/mdata/organization/index', 'fa fa-sitemap', '2', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 15:56:51', null, null, null);
INSERT INTO `sys_resources` VALUES ('6395c2e62f994b899fbbf7742e12b4f3', 'f315449cabce4779835c4998c41c8218', '流程设计器', 'menu', 'menu', '/act/model/list', 'fa fa-sitemap', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-03 14:04:15', '2019-01-10 10:31:53', null, null);
INSERT INTO `sys_resources` VALUES ('69a443a55ada46d7b47c8c775d42709d', '8aa08e904fb13570014fb157bb9f0001', '协同办公', 'menu', 'menu', null, 'fa fa-book', '1', '1', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('71409b4de09d41d49bb3557d60cb328f', '8aa08e904fb13570014fb157bb9f0001', '门户网站', 'menu', 'menu', null, 'fa fa-edge', '1', '3', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('779ebe4af1ca4955890bb081a99aaa43', 'f315449cabce4779835c4998c41c8218', '流程业务树', 'menu', 'menu', '/act/bus/busTree', 'fa fa-vine', '3', '0', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-09 13:53:56', '2019-01-09 13:55:51', null, null);
INSERT INTO `sys_resources` VALUES ('78a48ddf39424ae5873140071a2fa83e', 'be37b40107764018bfe35a82465aa9e3', '数据字典', 'menu', 'menu', '/system/dicSet/index', '', '3', '3', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('796203e2043346da8633047bce6b47b9', '28c13a61d2694124bbde111f336e328c', '所有任务', 'menu', 'menu', '/office/oaTaskBase/index', '', '3', '3', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:18:42', null, null, null);
INSERT INTO `sys_resources` VALUES ('862ecd9062bc4d3ebfe894813da8406f', '8aa08e904fb13570014fb157bb9f0001', '报表中心', 'menu', 'menu', null, 'fa fa-bar-chart', '1', '6', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('87c1b82e220d4825b4f66170067c3bcd', 'cabbe8de5b194c6ea5fbddf12bf60ff1', '我的日程', 'menu', 'menu', '/office/scheduler/index_my', '', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:25:34', null, null, null);
INSERT INTO `sys_resources` VALUES ('899b8adf798f40a88aeb70e47510c044', 'f315449cabce4779835c4998c41c8218', '已完成流程', 'menu', 'menu', '/office/ewfRuExecution/allList', 'fa fa-shekel', '3', '4', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:06:11', null, null, null);
INSERT INTO `sys_resources` VALUES ('8aa08e904fb13570014fb157bb9f0001', '1', '系统资源树', 'menu', 'menu', null, '', '0', '1', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('8b702d5d80df4f28ab1fcd275ed37d06', '28c13a61d2694124bbde111f336e328c', '我的任务', 'menu', 'menu', '/office/oaTaskBase/indexMy', '', '3', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:18:10', null, null, null);
INSERT INTO `sys_resources` VALUES ('982dc7484b554963a51b3cb99e85d31a', '862ecd9062bc4d3ebfe894813da8406f', '报表管理', 'menu', 'menu', '', 'fa fa-book', '2', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:14:42', null, null, null);
INSERT INTO `sys_resources` VALUES ('98f19984fef74f98a081a83cf5ed1598', '982dc7484b554963a51b3cb99e85d31a', '报表管理', 'menu', 'menu', '/report/templet/index', '', '3', '2', 'open', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:16:07', null, null, null);
INSERT INTO `sys_resources` VALUES ('9bb65681ffad4e1cb00761602a69fe09', '69a443a55ada46d7b47c8c775d42709d', '传阅管理', 'menu', 'menu', '', 'fa fa-book', '2', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:17:13', null, null, null);
INSERT INTO `sys_resources` VALUES ('a1098237d5e34d84b735c2a233ff1618', '31cc871b6a9b464f84164fc70cc3726f', '机构用户', 'menu', 'menu', '', 'fa fa-book', '2', '4', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:12:53', null, null, null);
INSERT INTO `sys_resources` VALUES ('a1aeaf2e9549456b9435cb6babe222db', 'bb577a31c688421fb5840b346cd36c08', '开发工具', 'menu', 'menu', '', 'fa fa-code', '2', '31', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 13:54:15', null, null, null);
INSERT INTO `sys_resources` VALUES ('a2d0c5a2503643edbb6e7cf382322bfc', '31cc871b6a9b464f84164fc70cc3726f', '流程管理', 'menu', 'menu', '', 'fa fa-deviantart', '2', '5', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:13:29', null, null, null);
INSERT INTO `sys_resources` VALUES ('ac34b1c32a0a42088f2de22cd3e3b2dc', 'be37b40107764018bfe35a82465aa9e3', '资源管理', 'menu', 'menu', '/system/menu/index?levelId=0', 'fa fa-life-ring', '3', '2', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('af073842a861443086855e69af509d16', 'a1aeaf2e9549456b9435cb6babe222db', '代码生成（Oracle）', 'menu', 'menu', '/orclgenerator/index/index', 'fa fa-code', '3', '1', 'open', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '2019-01-03 14:06:52', '2019-01-03 14:08:16', null, null);
INSERT INTO `sys_resources` VALUES ('afc5b111871c44149f1bc2c9f63c283a', '380a81889f5944c083f57225da9f677a', '用户信息', 'menu', 'menu', '/mdata/user/index', 'fa fa-user', '2', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 16:08:06', null, null, null);
INSERT INTO `sys_resources` VALUES ('b5bcb3fa5eca4524a3a74e74f17e2a76', '69a443a55ada46d7b47c8c775d42709d', '流程中心', 'menu', 'menu', '', 'fa fa-xing-square', '2', '4', 'closed', '0', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:20:31', null, null, null);
INSERT INTO `sys_resources` VALUES ('b868a48e0cef460d88510286b70141b6', 'f315449cabce4779835c4998c41c8218', '已部署流程', 'menu', 'menu', '/office/ewfReDeployment/index', 'fa fa-random', '3', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:04:52', null, null, null);
INSERT INTO `sys_resources` VALUES ('b9ee1ff4421341cea2926df21e74201f', '8aa08e904fb13570014fb157bb9f0001', '消息中心', 'menu', 'menu', null, 'fa fa-wechat', '1', '7', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('bb577a31c688421fb5840b346cd36c08', '8aa08e904fb13570014fb157bb9f0001', '平台管理', 'menu', 'menu', null, 'fa fa-cog', '1', '100', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('be37b40107764018bfe35a82465aa9e3', 'bb577a31c688421fb5840b346cd36c08', '系统配置', 'menu', 'menu', '', 'fa fa-cog', '2', '1', 'closed', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_resources` VALUES ('c2df12cd67cb48e3805c56abd496af06', '31cc871b6a9b464f84164fc70cc3726f', '流程监控', 'menu', 'menu', '', 'fa fa-book', '2', '3', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:12:23', null, null, null);
INSERT INTO `sys_resources` VALUES ('c777a71539374cb093e8aac86e5a4489', '380a81889f5944c083f57225da9f677a', '角色权限', 'menu', 'menu', '/system/authGroup/index', 'fa fa-user-circle', '2', '3', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2018-12-10 16:09:03', null, null, null);
INSERT INTO `sys_resources` VALUES ('cabbe8de5b194c6ea5fbddf12bf60ff1', '69a443a55ada46d7b47c8c775d42709d', '日程管理', 'menu', 'menu', '', 'fa fa-book', '2', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:17:39', null, null, null);
INSERT INTO `sys_resources` VALUES ('ce238fa792764d7f8f4a36cc9eb8a1c8', '538489afbed540afa6b964940c17e8ef', '异常日志', 'menu', 'menu', '/system/log/index?type=exception', 'fa fa-bug', '3', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:10:06', null, null, null);
INSERT INTO `sys_resources` VALUES ('d8b20462a3f34fe791f4272e1b48cf9f', '28c13a61d2694124bbde111f336e328c', '待办任务', 'menu', 'menu', '/office/oaTaskBase/indexToDoForm', '', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:17:40', null, null, null);
INSERT INTO `sys_resources` VALUES ('db2a234fe8504322a6d85883ceed210d', '538489afbed540afa6b964940c17e8ef', '操作日志', 'menu', 'menu', '/system/log/index?type=operate', 'fa fa-list-alt', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:09:28', null, null, null);
INSERT INTO `sys_resources` VALUES ('e56edb0d1a41479f98ea6cbcff5a5552', 'b5bcb3fa5eca4524a3a74e74f17e2a76', '业务流程', 'menu', 'menu', '', 'fa fa-book', '3', '2', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-07 09:24:41', null, null, null);
INSERT INTO `sys_resources` VALUES ('e6e94c77f175446d9f777ed9bdb1bf3c', '982dc7484b554963a51b3cb99e85d31a', '报表设计', 'menu', 'menu', '/report/ureport/designer', 'fa fa-yelp', '3', '1', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 14:15:27', null, null, null);
INSERT INTO `sys_resources` VALUES ('f315449cabce4779835c4998c41c8218', 'bb577a31c688421fb5840b346cd36c08', '流程配置', 'menu', 'menu', '', 'fa fa-xing-square', '2', '21', 'closed', '1', '', '8b691c39ebba11e8bbe3c86000d051dc', null, '2019-01-03 13:53:43', null, null, null);

-- ----------------------------
-- Table structure for sys_tree_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_tree_dict`;
CREATE TABLE `sys_tree_dict` (
  `id` varchar(50) NOT NULL COMMENT 'uuid',
  `parentId` varchar(50) DEFAULT NULL COMMENT '父级编号',
  `codeSetId` varchar(50) DEFAULT NULL COMMENT '体系编号',
  `text` varchar(255) DEFAULT NULL COMMENT '指标名称',
  `code` varchar(255) DEFAULT NULL COMMENT '指标代码',
  `state` bit(1) DEFAULT NULL COMMENT '是否可展开',
  `iconcls` varchar(255) DEFAULT NULL COMMENT '显示图标',
  `sort` int(11) DEFAULT NULL COMMENT '指标排序',
  `levelId` varchar(255) DEFAULT NULL COMMENT '层级',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_tree_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT 'uuid',
  `loginName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `userName` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nickName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `orgId` varchar(50) DEFAULT NULL COMMENT '所属机构',
  `post` varchar(255) DEFAULT NULL COMMENT '职务',
  `telephone` varchar(255) DEFAULT NULL COMMENT '固定电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `cellphone` varchar(255) DEFAULT NULL COMMENT '手机',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `zone` varchar(255) DEFAULT NULL COMMENT '所属区域',
  `idCard` varchar(255) DEFAULT NULL COMMENT '身份证',
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `nativePlace` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `userGroup` varchar(255) DEFAULT NULL,
  `dataAuth` varchar(255) DEFAULT NULL,
  `createId` varchar(50) DEFAULT NULL,
  `updateId` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `del` int(11) DEFAULT '0',
  `updateTime` datetime DEFAULT NULL,
  `creatorOrgId` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3106bf2abba5491c8d1f8a13b249313b', 'lw', '老王', null, '4973db717bb30e06c50a32db6ae2c959e15d8eb1b0a3f992cd8307278a55fe3f', '4lnpye7REo1FBhUTBwol', null, '1', null, 'dfa473d23a8d4f13b7c0fc8103773fee', '8a094b256efb4d7da4a6e1331d8bc0b3', '0755-33942020', 'zhuyongjing@ewsd.cn', '18588220078', '1', '97e299e49d3e46b68cfa4153aff631bd', '', '密云县', '县', '中国', '北京市', '江苏省（苏）', '汉族', 'ee297663ec454c6d8e6d0adbec788b84', '22c3f513aa6f47bcb97d7d8980b2eb17', '8b691c39ebba11e8bbe3c86000d051dc', '8b691c39ebba11e8bbe3c86000d051dc', '', '2019-01-02 17:21:44', null, '2019-01-03 13:18:12', null, '工科院校', '本科', '学士');
INSERT INTO `sys_user` VALUES ('8b691c39ebba11e8bbe3c86000d051dc', 'admin', 'admin', '超级管理员', '20f5120f0e1e1dad012e379b0ca3700468494cdd9b6918d5a3669c520cc1edc5', 'OD1wvH3DUgJQqaytDDW7', 'https://thirdqq.qlogo.cn/qqapp/101458592/7DCF00F9AF919A0BC2E5174A591765EE/100', '1', '20', '74b269f649b24af688abaaaf50ce26e1', '7e4a92ab9e5c4810847d4b20e1e28d92', '0755-33942020', 'xiaoshiming@ewsd.cn', '18588220078', '1', null, '', null, null, null, null, null, null, 'ee297663ec454c6d8e6d0adbec788b84', '22c3f513aa6f47bcb97d7d8980b2eb17', '', null, null, '2018-12-12 10:58:41', '0', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('907f679d8b1447f39e1215f46703e3e8', 'xq', '小乔', '小乔', 'b3c1f12654faa27562ce003b2bfd3e1c79ef84afeae23feea1350c3fd757b68a', 'Www8dO5axwYVuJKnFaNz', null, '1', '20', '74b269f649b24af688abaaaf50ce26e1', '6397db68376d4b598a5f0e9824a430b9', '0755-33942020', 'fengkai@ewsd.cn', '18588220078', '1', 'f4919ddc6ab04458b11d217647d40879', '123', '朝阳区', '市辖区', '中国', '北京市', '北京市（京） ', '汉族', 'ee297663ec454c6d8e6d0adbec788b84', '22c3f513aa6f47bcb97d7d8980b2eb17', '8b691c39ebba11e8bbe3c86000d051dc', null, '', '2019-01-02 17:13:27', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` varchar(50) NOT NULL COMMENT '用户组id',
  `text` varchar(255) DEFAULT NULL COMMENT '用户组名称',
  `parentId` varchar(50) DEFAULT NULL COMMENT '父用户组id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `levelId` int(11) DEFAULT NULL COMMENT '层级',
  `status` int(11) DEFAULT NULL COMMENT '状态（启用、禁用）',
  `createId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------

-- ----------------------------
-- Procedure structure for grant
-- ----------------------------
DROP PROCEDURE IF EXISTS `grant`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `grant`(IN currentId varchar(50),roleId varchar(50), ids text, out result varchar(2000))
BEGIN
	declare accessAuth varchar(2000) default '';
	 -- 开始事务
	START TRANSACTION;

	#分割字符串，用逗号隔开
  	set @i=0;
	#如果不存在，择创建一个用于保存分割字符串后数据的临时表str_spilt_result
    CREATE TEMPORARY TABLE if not exists str_spilt_result(pro_id varchar(50) NOT NULL);   
    #清空临时表
    truncate table str_spilt_result;  
    SET @cnt = 1+(LENGTH(str) - LENGTH(REPLACE(str,param,'')));  
    WHILE @i < @cnt DO  
        SET @i = @i + 1;  
        SET @result = REVERSE(SUBSTRING_INDEX(REVERSE(SUBSTRING_INDEX(str,param,@i)),param,1));
        #把数据插入临时表1
        INSERT INTO str_spilt_result(pro_id) VALUES (@result);  
    END WHILE;  
    SELECT pro_id from str_spilt_result; 
		BEGIN
/*局部变量的定义 declare*/
	DECLARE proId varchar(50);
	DECLARE v_count int;
	-- 定义游标遍历时，作为判断是否遍历完全部记录的标记
	DECLARE num INT DEFAULT 0; 
	-- 定义游标，并将sql结果集赋值到游标中
	DECLARE sys_uuid_list CURSOR FOR (SELECT pro_id from str_spilt_result);
	-- 声明当游标遍历完全部记录后将标志变量置成某个值
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET num=1;
	-- 获取id的数量
	SELECT count(pro_id) into v_count from str_spilt_result;
	OPEN sys_uuid_list;
	-- 将游标中的值赋值给变量
	FETCH sys_uuid_list INTO proId;
			-- while循环
			while num <> 1 DO
			IF (proId is not NULL) THEN
				-- 嵌套使用游标
				BEGIN
					declare resId varchar(50);
					DECLARE sz INT DEFAULT 0;
					declare flag int DEFAULT 0;
					declare sys_res_list CURSOR FOR(select id from sys_resources where id = proId || parentId = proId);
					DECLARE CONTINUE HANDLER FOR NOT FOUND SET sz = 1;

					open sys_res_list;
						FETCH sys_res_list into resId;
						while sz <> 1 DO
							set flag = (select count(id) from sys_auth_access where roleId = roleId and menuId = proId);
							IF (flag = 0) THEN
										insert into sys_auth_access(id, roleId, menuId, createId, createTime)
										VALUES(REPLACE(UUID(),'-',''), roleId, proId, currentId, now());
							else 
								update sys_auth_access 
								set roleId = roleId, 
										menuId = proId, 
										createId = currentId, 
										createTime = now()
								where id = resId;
							end if;
							IF EXISTS( select 1 from sys_auth_access where roleId = roleId and menuId = proId limit 1) THEN
									SET accessAuth = accessAuth + '{"accessAuth":"1"}' + ",";
							ELSE
									SET accessAuth = accessAuth + '{"accessAuth":"0"}' + ",";
							END IF;
						
							FETCH sys_res_list into resId;
						end while;
					close sys_res_list;
				END;
			end if;
			FETCH sys_uuid_list into proId;
		end while;
	CLOSE sys_uuid_list; 
 
END;
 COMMIT; -- 事务提交
select '[' + accessAuth.substring(accessAuth,0,length(accessAuth)-1) + ']';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for str_spilt
-- ----------------------------
DROP PROCEDURE IF EXISTS `str_spilt`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `str_spilt`(IN str text,param varchar(50))
BEGIN
	#分割字符串，用逗号隔开
  	set @i=0;
	#如果不存在，择创建一个用于保存分割字符串后数据的临时表str_spilt_result
    CREATE TEMPORARY TABLE if not exists str_spilt_result(pro_id varchar(50) NOT NULL);   
    #清空临时表
    truncate table str_spilt_result;  
    SET @cnt = 1+(LENGTH(str) - LENGTH(REPLACE(str,param,'')));  
    WHILE @i < @cnt DO  
        SET @i = @i + 1;  
        SET @result = REVERSE(SUBSTRING_INDEX(REVERSE(SUBSTRING_INDEX(str,param,@i)),param,1));
        #把数据插入临时表1
        INSERT INTO str_spilt_result(pro_id) VALUES (@result);  
    END WHILE;  
    SELECT pro_id from str_spilt_result; 
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getActBusParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getActBusParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getActBusParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId; 
DECLARE lid INT;  
  
WHILE rootId is not null  do   
    SET fid =(SELECT parentId FROM extend_act_business WHERE id = rootId);  
    set lid = (SELECT levelId FROM extend_act_business WHERE id = (SELECT parentId FROM extend_act_business WHERE id = rootId)); 
    IF fid is not null and lid != 0 THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getCodeItemParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getCodeItemParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getCodeItemParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId; 
DECLARE lid INT;  
  
WHILE rootId is not null  do   
    SET fid =(SELECT parentid FROM sys_code_item WHERE id = rootId);  
    set lid = (SELECT levelId FROM sys_code_item WHERE id = (SELECT parentId FROM sys_code_item WHERE id = rootId)); 
    IF fid is not null and lid != 0 THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getOrganizationParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getOrganizationParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getOrganizationParentList`(rootId varchar(50)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId;   
DECLARE lid INT;
  
WHILE rootId is not null  do   
    SET fid =(SELECT parentId FROM sys_organization WHERE id = rootId); 
		set lid = (SELECT levelId FROM sys_organization WHERE id = (SELECT parentId FROM sys_organization WHERE id = rootId));
    IF fid is not null and lid != 0 THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getParentList
-- ----------------------------
DROP FUNCTION IF EXISTS `getParentList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId;   
DECLARE lid INT;
  
WHILE rootId is not null  do   
    SET fid =(SELECT parentId FROM sys_resources WHERE id = rootId); 
		set lid = (SELECT levelId FROM sys_resources WHERE id = (SELECT parentId FROM sys_resources WHERE id = rootId));
    IF fid is not null and lid != 0 THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getZoneFillbackData
-- ----------------------------
DROP FUNCTION IF EXISTS `getZoneFillbackData`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getZoneFillbackData`(rootId varchar(100)) RETURNS text CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId; 
DECLARE s varchar(100) DEFAULT '';
DECLARE lid INT;  
  
set str = (select text from sys_code_item where id = rootId);
WHILE rootId is not null  do   
    SET fid =(SELECT parentId FROM sys_code_item WHERE id = rootId);  
    set lid = (SELECT levelId FROM sys_code_item WHERE id = (SELECT parentId FROM sys_code_item WHERE id = rootId)); 
    IF fid is not null and lid >= 3 THEN   
				set s = (select text from sys_code_item WHERE id = fid);
        SET str = concat(str, ',', s);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END
;;
DELIMITER ;
