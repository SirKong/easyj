-- ----------------------------
-- 租户模型
-- ----------------------------
CREATE TABLE `tenant` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR (50) NOT NULL COMMENT '租户名称',
  `country` VARCHAR(20) COMMENT '国家',
  `province` VARCHAR(20) COMMENT '省份',
  `city` VARCHAR(20) COMMENT '城市',
  `contact` VARCHAR(20) COMMENT '联系人',
  `phone` VARCHAR(15) COMMENT '电话',
  `mobile` VARCHAR(15) COMMENT '手机',
  `address` VARCHAR(200) COMMENT '地址',
  `status` INT(1) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 查询模型
-- ----------------------------
CREATE TABLE `query` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `code` VARCHAR(50) NOT NULL COMMENT '代码',
  `title` VARCHAR(100) COMMENT '标题',
  `description` VARCHAR(200) COMMENT '描述',
  `query` VARCHAR(500) NOT NULL COMMENT '查询',
  `version` VARCHAR(20) NOT NULL COMMENT '版本',
  `page_size` INT(4) DEFAULT 0 COMMENT '分页大小',
  `enabled` INT(1) COMMENT '启用',
  `view_template` VARCHAR(200) NOT NULL COMMENT '视图模版',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 查询输入模型
-- ----------------------------
CREATE TABLE `query_in` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `query_id` INT(11) NOT NULL COMMENT '查询',
  `name` VARCHAR(20) NOT NULL COMMENT '字段名称',
  `label` VARCHAR(50) COMMENT '字段显示名',
  `type` VARCHAR(10) COMMENT '字段类型',
  `order` INT(2) COMMENT '字段顺序',
  `format` VARCHAR(50) COMMENT '字段格式',
  `default_value` VARCHAR(100) COMMENT '字段默认值',
  `value_provider` VARCHAR(50) COMMENT '字段值提供者',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 查询输出模型
-- ----------------------------
CREATE TABLE `query_out` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `query_id` INT(11) NOT NULL COMMENT '查询',
  `name` VARCHAR(20) NOT NULL COMMENT '字段名称',
  `label` VARCHAR(50) COMMENT '字段显示名',
  `type` VARCHAR(10) COMMENT '字段类型',
  `order` INT(2) COMMENT '字段顺序',
  `format` VARCHAR(50) COMMENT '字段格式',
  `mode` INT(1) COMMENT '字段模式',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 更新模型
-- ----------------------------
CREATE TABLE `update` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `code` VARCHAR(50) NOT NULL COMMENT '代码',
  `title` VARCHAR(100) NOT NULL COMMENT '标题',
  `description` VARCHAR(200) COMMENT '描述',
  `update1` VARCHAR(500) COMMENT '新增',
  `update2` VARCHAR(500) COMMENT '修改',
  `update3` VARCHAR(500) COMMENT '删除',
  `version` VARCHAR(20) COMMENT '版本',
  `enabled` INT(1) NOT NULL COMMENT '启用',
  `view_template` VARCHAR(200) NOT NULL COMMENT '视图模版',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 更新输入模型
-- ----------------------------
CREATE TABLE `update_in` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `update_id` INT(11) NOT NULL COMMENT '更新',
  `name` VARCHAR(20) NOT NULL COMMENT '字段名称',
  `label` VARCHAR(50) COMMENT '字段显示名',
  `type` VARCHAR(10) COMMENT '字段类型',
  `order` INT(2) COMMENT '字段顺序',
  `format` VARCHAR(50) COMMENT '字段格式',
  `default_value` VARCHAR(100) COMMENT '字段默认值',
  `value_provider` VARCHAR(50) COMMENT '字段值提供者',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 更新输出模型
-- ----------------------------
CREATE TABLE `update_out` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `update_id` INT(11) NOT NULL COMMENT '查询',
  `name` VARCHAR(20) NOT NULL COMMENT '字段名称',
  `label` VARCHAR(50) COMMENT '字段显示名',
  `type` VARCHAR(10) COMMENT '字段类型',
  `order` INT(2) COMMENT '字段顺序',
  `format` VARCHAR(50) COMMENT '字段格式',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 页面模型
-- ----------------------------
CREATE TABLE `page` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `parent_id` INT(11) COMMENT '继承',
  `layout` VARCHAR(50) NOT NULL COMMENT '布局',
  `title` VARCHAR(50) NOT NULL COMMENT '标题',
  `path` VARCHAR(200) NOT NULL COMMENT '路径',
  `type` INT(1) DEFAULT 0 COMMENT '类型',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 页面位置模型
-- ----------------------------
CREATE TABLE `page_position` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `page_id` INT(11) NOT NULL COMMENT '继承',
  `name` VARCHAR (50) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 组件模型
-- ----------------------------
CREATE TABLE `widget` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `position_id` INT(11) NOT NULL COMMENT '位置',
  `path` VARCHAR(200) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 事件模型
-- ----------------------------
CREATE TABLE `widget_event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `widget_id` INT(11) NOT NULL COMMENT '组件',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  `expression` VARCHAR(200) NOT NULL COMMENT '表达式',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 用户模型
-- ----------------------------
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `username` VARCHAR(20) NOT NULL COMMENT '账号',
  `password` VARCHAR(20) NOT NULL COMMENT '密码',
  `email` VARCHAR(50) COMMENT '邮箱',
  `mobile` VARCHAR(15) COMMENT '手机',
  `phone` VARCHAR(15) COMMENT '电话',
  `address` VARCHAR(200) COMMENT '地址',
  `status` INT(1) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 角色模型
-- ----------------------------
CREATE TABLE `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `code` VARCHAR(200) NOT NULL COMMENT '代码',
  `name` VARCHAR(200) NOT NULL COMMENT '名称',
  `description` VARCHAR(200) COMMENT '描述',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 资源模型
-- ----------------------------
CREATE TABLE `resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `code` VARCHAR(200) NOT NULL COMMENT '代码',
  `name` VARCHAR(200) NOT NULL COMMENT '名称',
  `description` VARCHAR(200) COMMENT '描述',
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- 用户角色关系模型
-- ----------------------------
CREATE TABLE `user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `user_id` INT(11) NOT NULL COMMENT '用户',
  `role_id` INT(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 角色资源关系模型
-- ----------------------------
CREATE TABLE `role_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `role_id` INT(11) NOT NULL COMMENT '角色',
  `resource_id` INT(11) NOT NULL COMMENT '资源',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 菜单模型
-- ----------------------------
CREATE TABLE `menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `parent_id` INT(11) COMMENT '父菜单',
  `code` VARCHAR(20) COMMENT '代码',
  `name` VARCHAR(50) COMMENT '名称',
  `url` VARCHAR(200) COMMENT 'URL',
  `order` INT(2) DEFAULT 0 COMMENT '顺序',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 用户菜单模型
-- ----------------------------
CREATE TABLE `user_menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `user_id` INT(11) NOT NULL COMMENT '角色',
  `menu_id` INT(11) NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- 查询模型约束
-- ----------------------------
ALTER TABLE `query` ADD CONSTRAINT fk_query_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);

-- ----------------------------
-- 查询输入模型约束
-- ----------------------------
ALTER TABLE `query_in` ADD CONSTRAINT fk_query_in_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `query_in` ADD CONSTRAINT fk_query_in_query_id FOREIGN KEY (`query_id`) REFERENCES `query` (`id`);

-- ----------------------------
-- 查询输出模型约束
-- ----------------------------
ALTER TABLE `query_out` ADD CONSTRAINT fk_query_out_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `query_out` ADD CONSTRAINT fk_query_out_query_id FOREIGN KEY (`query_id`) REFERENCES `query` (`id`);

-- ----------------------------
-- 更新模型约束
-- ----------------------------
ALTER TABLE `update` ADD CONSTRAINT fk_update_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);

-- ----------------------------
-- 更新输入模型约束
-- ----------------------------
ALTER TABLE `update_in` ADD CONSTRAINT fk_update_in_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `update_in` ADD CONSTRAINT fk_update_in_update_id FOREIGN KEY (`update_id`) REFERENCES `update` (`id`);

-- ----------------------------
-- 更新输出模型约束
-- ----------------------------
ALTER TABLE `update_out` ADD CONSTRAINT fk_update_out_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `update_out` ADD CONSTRAINT fk_update_out_update_id FOREIGN KEY (`update_id`) REFERENCES `update` (`id`);

-- ----------------------------
-- 页面模型约束
-- ----------------------------
ALTER TABLE `page` ADD CONSTRAINT fk_page_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `page` ADD CONSTRAINT fk_page_parent_id FOREIGN KEY (`parent_id`) REFERENCES `page` (`id`);

-- ----------------------------
-- 页面位置模型约束
-- ----------------------------
ALTER TABLE `page_position` ADD CONSTRAINT fk_page_position_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `page_position` ADD CONSTRAINT fk_page_position_page_id FOREIGN KEY (`page_id`) REFERENCES `page` (`id`);

-- ----------------------------
-- 组件模型约束
-- ----------------------------
ALTER TABLE `widget` ADD CONSTRAINT fk_widget_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `widget` ADD CONSTRAINT fk_widget_position_id FOREIGN KEY (`position_id`) REFERENCES `page_position` (`id`);

-- ----------------------------
-- 事件模型约束
-- ----------------------------
ALTER TABLE `widget_event` ADD CONSTRAINT fk_widget_event_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `widget_event` ADD CONSTRAINT fk_widget_event_widget_id FOREIGN KEY (`widget_id`) REFERENCES `widget` (`id`);

-- ----------------------------
-- 用户模型约束
-- ----------------------------
ALTER TABLE `user` ADD CONSTRAINT fk_user_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);

-- ----------------------------
-- 角色模型约束
-- ----------------------------
ALTER TABLE `role` ADD CONSTRAINT fk_role_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);

-- ----------------------------
-- 资源模型约束
-- ----------------------------
ALTER TABLE `resource` ADD CONSTRAINT fk_resource_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);


-- ----------------------------
-- 用户角色关系模型约束
-- ----------------------------
ALTER TABLE `user_role` ADD CONSTRAINT fk_user_role_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `user_role` ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `user_role` ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

-- ----------------------------
-- 角色资源关系模型约束
-- ----------------------------
ALTER TABLE `role_resource` ADD CONSTRAINT fk_role_resource_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `role_resource` ADD CONSTRAINT fk_role_resource_role_id FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
ALTER TABLE `role_resource` ADD CONSTRAINT fk_role_resource_resource_id FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`);

-- ----------------------------
-- 菜单模型
-- ----------------------------
ALTER TABLE `menu` ADD CONSTRAINT fk_menu_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `menu` ADD CONSTRAINT fk_menu_parent_id FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`);

-- ----------------------------
-- 用户菜单模型
-- ----------------------------
ALTER TABLE `user_menu` ADD CONSTRAINT fk_user_menu_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);
ALTER TABLE `user_menu` ADD CONSTRAINT fk_user_menu_user_id FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `user_menu` ADD CONSTRAINT fk_user_menu_menu_id FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);