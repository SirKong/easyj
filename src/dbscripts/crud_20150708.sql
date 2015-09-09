-- ----------------------------
-- 通用查询模型
-- ----------------------------
DROP TABLE IF EXISTS `sys_query_definition`;
CREATE TABLE `sys_query_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '英文名',
  `label` varchar(255) DEFAULT NULL COMMENT '中文名',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` varchar(255) NOT NULL COMMENT '版本',
  `query` varchar(1000) NOT NULL COMMENT 'sql模板',
  `enabled` tinyint(1) NOT NULL COMMENT '是否可用 默认 false',
  `page_size` varchar(255) NOT NULL COMMENT '分页标示 默认 0 不分页',
  `create_date` date NOT NULL COMMENT '创建日期',
  `view_template` varchar(255) DEFAULT NULL COMMENT '视图模板',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 通用查询输入模型
-- ----------------------------
DROP TABLE IF EXISTS `sys_query_in_definition`;
CREATE TABLE `sys_query_in_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `query_id` int(11) NOT NULL COMMENT '外键关联 sys_query',
  `name` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `query_operation` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 通用查询输出模型
-- ----------------------------
DROP TABLE IF EXISTS `sys_query_out_definition`;
CREATE TABLE `sys_query_out_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `mode` varchar(255) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `level` int(11) NOT NULL,
  `width` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 通用更新模型
-- ----------------------------
DROP TABLE IF EXISTS `sys_update_definition`;
CREATE TABLE `sys_update_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `version` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `update1` varchar(255) DEFAULT NULL COMMENT '插入模版',
  `update2` varchar(255) DEFAULT NULL COMMENT '更新模版',
  `update3` varchar(255) DEFAULT NULL COMMENT ' 删除模板',
  `view_template` varchar(255) DEFAULT NULL COMMENT '视图模板',
  `update1_enabled` tinyint(1) DEFAULT NULL COMMENT '0:不支持，1：支持',
  `update2_enabled` tinyint(1) DEFAULT NULL COMMENT '0:不支持，1：支持',
  `update3_enabled` tinyint(1) DEFAULT NULL COMMENT '0:不支持，1：支持',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 通用更新输入模型
-- ----------------------------
DROP TABLE IF EXISTS `sys_update_in_definition`;
CREATE TABLE `sys_update_in_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);

-- 添加关联约束
ALTER TABLE `sys_query_in_definition` ADD CONSTRAINT fk_sys_query_in_definition_query_id FOREIGN KEY (`query_id`) REFERENCES `sys_query_definition` (`id`);
ALTER TABLE `sys_query_out_definition` ADD CONSTRAINT fk_sys_query_out_definition_query_id FOREIGN KEY (`query_id`) REFERENCES `sys_query_definition` (`id`);
ALTER TABLE `sys_update_in_definition` ADD CONSTRAINT fk_sys_update_in_definition_update_id FOREIGN KEY (`update_id`) REFERENCES `sys_update_definition` (`id`);
