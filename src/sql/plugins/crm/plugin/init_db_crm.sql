--
-- Init table crm_status_crm
--
INSERT INTO crm_status_crm (id_status_crm, status_label) VALUES (0, 'crm.statusCRM.draft');
INSERT INTO crm_status_crm (id_status_crm, status_label) VALUES (1, 'crm.statusCRM.validated');

--
-- Init table crm_parameter
--
INSERT INTO crm_parameter (parameter_key, parameter_value) VALUES ('displayDraft', 'true' );
INSERT INTO crm_parameter (parameter_key, parameter_value) VALUES ('useIdCrmUser', 'true' );