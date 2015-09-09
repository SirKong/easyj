-- ----------------------------
-- 删除模型约束
-- ----------------------------
ALTER TABLE `query` DROP FOREIGN KEY fk_query_tenant_id;

ALTER TABLE `query_in` DROP FOREIGN KEY fk_query_in_tenant_id;
ALTER TABLE `query_in` DROP FOREIGN KEY fk_query_in_query_id;

ALTER TABLE `query_out` DROP FOREIGN KEY fk_query_out_tenant_id;
ALTER TABLE `query_out` DROP FOREIGN KEY fk_query_out_query_id;

ALTER TABLE `update` DROP FOREIGN KEY fk_update_tenant_id;

ALTER TABLE `update_in` DROP FOREIGN KEY fk_update_in_tenant_id;
ALTER TABLE `update_in` DROP FOREIGN KEY fk_update_in_update_id;

ALTER TABLE `update_out` DROP FOREIGN KEY fk_update_out_tenant_id;
ALTER TABLE `update_out` DROP FOREIGN KEY fk_update_out_update_id;

ALTER TABLE `page` DROP FOREIGN KEY fk_page_tenant_id;
ALTER TABLE `page` DROP FOREIGN KEY fk_page_parent_id;

ALTER TABLE `page_position` DROP FOREIGN KEY fk_page_position_tenant_id;
ALTER TABLE `page_position` DROP FOREIGN KEY fk_page_position_page_id;

ALTER TABLE `widget` DROP FOREIGN KEY fk_widget_tenant_id;
ALTER TABLE `widget` DROP FOREIGN KEY fk_widget_position_id;

ALTER TABLE `widget_event` DROP FOREIGN KEY fk_widget_event_tenant_id;
ALTER TABLE `widget_event` DROP FOREIGN KEY fk_widget_event_widget_id;


ALTER TABLE `user` DROP FOREIGN KEY fk_user_tenant_id;

ALTER TABLE `role` DROP FOREIGN KEY fk_role_tenant_id;

ALTER TABLE `resource` DROP FOREIGN KEY fk_resource_tenant_id;

ALTER TABLE `user_role` DROP FOREIGN KEY fk_user_role_tenant_id;
ALTER TABLE `user_role` DROP FOREIGN KEY fk_user_role_user_id;
ALTER TABLE `user_role` DROP FOREIGN KEY fk_user_role_role_id;

ALTER TABLE `role_resource` DROP FOREIGN KEY fk_role_resource_tenant_id ;
ALTER TABLE `role_resource` DROP FOREIGN KEY fk_role_resource_role_id;
ALTER TABLE `role_resource` DROP FOREIGN KEY fk_role_resource_resource_id;

ALTER TABLE `menu` DROP FOREIGN KEY fk_menu_tenant_id;
ALTER TABLE `menu` DROP FOREIGN KEY fk_menu_parent_id;

ALTER TABLE `user_menu` DROP FOREIGN KEY fk_user_menu_tenant_id;
ALTER TABLE `user_menu` DROP FOREIGN KEY fk_user_menu_user_id;
ALTER TABLE `user_menu` DROP FOREIGN KEY fk_user_menu_menu_id;

-- ----------------------------
-- 删除模型
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;

DROP TABLE IF EXISTS `query`;
DROP TABLE IF EXISTS `query_in`;
DROP TABLE IF EXISTS `query_out`;
DROP TABLE IF EXISTS `update`;
DROP TABLE IF EXISTS `update_in`;
DROP TABLE IF EXISTS `update_out`;

DROP TABLE IF EXISTS `page`;
DROP TABLE IF EXISTS `page_position`;
DROP TABLE IF EXISTS `widget`;
DROP TABLE IF EXISTS `widget_event`;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `resource`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `role_resource`;

DROP TABLE IF EXISTS `menu`;
DROP TABLE IF EXISTS `user_menu`;

