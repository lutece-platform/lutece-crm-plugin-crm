
--
-- Dumping data for table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES 
('CRM_DEMAND_TYPES_MANAGEMENT','crm.adminFeature.demand_type_management.name',3,'jsp/admin/plugins/crm/ManageDemandTypes.jsp','crm.adminFeature.demand_type_management.description',0,'crm','APPLICATIONS','images/admin/skin/plugins/crm/crm.png', NULL);

--
-- Dumping data for table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('CRM_DEMAND_TYPES_MANAGEMENT',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('CRM_DEMAND_TYPES_MANAGEMENT',2);
