-- ----------------------------
-- 租户模型
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
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
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- 主题模型
-- ----------------------------
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` INT(11) NOT NULL COMMENT '租户',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
);
ALTER TABLE `theme` ADD CONSTRAINT fk_theme_tenant_id FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`);

-- ----------------------------
-- 布局模型
-- ----------------------------
DROP TABLE IF EXISTS `layout`;
CREATE TABLE `layout` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `theme_id` INT(11) NOT NULL COMMENT '主题',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
);
ALTER TABLE `layout` ADD CONSTRAINT fk_layout_theme_id FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`);

-- ----------------------------
-- 位置模型
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `layout_id` INT(11) NOT NULL COMMENT '布局',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
);
ALTER TABLE `position` ADD CONSTRAINT fk_position_layout_id FOREIGN KEY (`layout_id`) REFERENCES `layout` (`id`);