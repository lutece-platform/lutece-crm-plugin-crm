/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.crm.util.constants;


/**
 *
 * CRMConstants
 *
 */
public final class CRMConstants
{
    // CONSTANTS
    public static final int INVALID_ID_INT = -1;
    public static final String SLASH = "/";
    public static final String COMMA = ",";
    public static final String CONSTANT_ON = "on";
    public static final String CONSTANT_TRUE = "true";
    public static final String CONSTANT_FALSE = "false";
    public static final String CONSTANT_PLUGIN_NAME = "plugin_name";
    public static final String CONSTANT_CRM = "crm";
    public static final String CONSTANT_DISPLAYDRAFT = "displayDraft";
    public static final String CONSTANT_USE_ID_CRM_USER = "useIdCrmUser";
    public static final String NO_CATEGORY = "0";
    public static final String ALL_CATEGORY = "-1";

    // PROPERTIES
    public static final String PROPERTY_PAGE_PATH = "crm.crm.pagePathLabel";
    public static final String PROPERTY_PAGE_TITLE = "crm.crm.pageTitle";
    public static final String PROPERTY_MANAGE_NOTIFICATIONS_PAGE_TITLE = "crm.manage_notifications.pageTitle";
    public static final String PROPERTY_VIEW_NOTIFICATION_PAGE_TITLE = "crm.view_notification.pageTitle";
    public static final String PROPERTY_MANAGE_DEMAND_TYPES_PAGE_TITLE = "crm.manage_demand_types.pageTitle";
    public static final String PROPERTY_CREATE_DEMAND_TYPE_PAGE_TITLE = "crm.create_demand_type.pageTitle";
    public static final String PROPERTY_MODIFY_DEMAND_TYPE_PAGE_TITLE = "crm.modify_demand_type.pageTitle";
    public static final String PROPERTY_MODIFY_CRM_USER_PAGE_TITLE = "crm.modify_crm_user.pageTitle";
    public static final String PROPERTY_DEFAULT_LIST_DEMAND_TYPES_PER_PAGE = "crm.listDemandTypes.itemsPerPage";
    public static final String PROPERTY_PAGE_TITLE_MANAGE_CATEGORIES = "crm.manage_categories.pageTitle";
    public static final String PROPERTY_PAGE_TITLE_MODIFY_CATEGORY = "crm.modify_category.pageTitle";
    public static final String PROPERTY_PAGE_TITLE_CREATE_CATEGORY = "crm.create_category.pageTitle";
    public static final String PROPERTY_DEFAULT_LIST_CATEGORY_PER_PAGE = "crm.listCategories.itemsPerPage";
    public static final String PROPERTY_DAEMON_NB_EXPIRATION_DAYS = "daemon.crmDemandCleaner.nbExpirationDays";
    public static final String PROPERTY_WEBMASTER_EMAIL = "email.webmaster";
    public static final String PROPERTY_CRM_USER_MAX_SIZE = "crm.crmUser.maxSize";
    public static final String PROPERTY_CRM_USER_ATTRIBUTE_KEYS = "crm.userAttributes.keys";
    public static final String PROPERTY_DEMANDS_PER_PAGE = "crm.paginator.demand.itemsPerPage";
    
    public static final String PROPERTY_LABEL_DEMAND_TYPES_LIST = "crm.crm.labelDemandTypesList"; 
    public static final String PROPERTY_LABEL_CRM_INFO = "crm.crm.labelInfo"; 
    public static final String PROPERTY_LABEL_CRM_CONTACT = "crm.crm.labelContact";
    public static final String PROPERTY_LABEL_CRM_DATE_BEGIN = "crm.crm.labelDateBegin";
    public static final String PROPERTY_LABEL_CRM_DATE_END = "crm.crm.labelDateEnd";
    public static final String PROPERTY_NO_CATEGORY = "crm.crm.labelNoCategory";
    public static final String PROPERTY_ALL_CATEGORY = "crm.crm.labelAllCategory";
    // MARKS 
    public static final String MARK_MAP_DEMAND_TYPES_LIST = "map_demand_types_list";
    public static final String MARK_MAP_DEMANDS_LIST = "map_demands_list";
    public static final String MARK_MYLUTECE_USER = "mylutece_user";
    public static final String MARK_CATEGORIES_LIST = "categories_list";
    public static final String MARK_DEMAND_TYPES_LIST = "demand_types_list";
    public static final String MARK_NOTIFICATIONS_LIST = "notifications_list";
    public static final String MARK_NOTIFICATION = "notification";
    public static final String MARK_DEMAND = "demand";
    public static final String MARK_DEMAND_TYPE = "demand_type";
    public static final String MARK_STATUS_CRM_LIST = "status_crm_list";
    public static final String MARK_PAGINATOR = "paginator";
    public static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";
    public static final String MARK_MAP_NB_ITEMS_PER_PAGE = "map_nb_items_per_page";
    public static final String MARK_MAX_ORDER = "max_order";
    public static final String MARK_DEMAND_TYPE_FILTER = "demand_type_filter";
    public static final String MARK_USER_WORKGROUP_REF_LIST = "user_workgroup_list";
    public static final String MARK_ROLE_REF_LIST = "role_list";
    public static final String MARK_IS_WELL_ORDERED = "is_well_ordered";
    public static final String MARK_OPERATORS_LIST = "operators_list";
    public static final String MARK_CATEGORY = "category";
    public static final String MARK_CRM_USER = "crm_user";
    public static final String MARK_TARGETS_LIST = "targets_list";
    public static final String MARK_DISPLAYDRAFT = "bDisplayDraft";
    public static final String MARK_USE_IDCRMUSER = "bUseIdCrmUser";
    public static final String MARK_LOCALE = "locale";
    public static final String MARK_DEMAND_TYPES_REFLIST = "demandTypesRefList";
    public static final String MARK_FILTER = "filter";
    public static final String MARK_MODIFICATIONDATE = "modificationDate";
    public static final String MARK_MAP_PAGINATOR = "map_paginator";
    public static final String MARK_MAP_DO_LOGIN = "url_dologin";
    public static final String MARK_CATEGORY_ID_CATEGORY = "category_id_category";
    public static final String MARK_BASE_URL = "base_url";
  
    // MARKS XSL
    public static final String MARK_XSL_PARAM_I18N_LABEL_DEMAND_TYPES_LIST = "i18n-label-demand-types-list"; 
    public static final String MARK_XSL_PARAM_I18N_LABEL_CRM_INFO = "i18n-label-crm-info"; 
    public static final String MARK_XSL_PARAM_I18N_LABEL_CRM_CONTACT = "i18n-label-crm-contact";
    public static final String MARK_XSL_PARAM_I18N_LABEL_CRM_DATE_BEGIN = "i18n-label-crm-date-begin";
    public static final String MARK_XSL_PARAM_I18N_LABEL_CRM_DATE_END = "i18n-label-crm-date-end";

    // PARAMETERS
    public static final String PARAMETER_ACTION = "action";
    public static final String PARAMETER_ID_DEMAND = "id_demand";
    public static final String PARAMETER_ID_NOTIFICATION = "id_notification";
    public static final String PARAMETER_PAGE = "page";
    public static final String PARAMETER_PAGE_INDEX = "page_index";
    public static final String PARAMETER_DEMAND_TYPE_LABEL = "demand_type_label";
    public static final String PARAMETER_URL_RESOURCE = "url_resource";
    public static final String PARAMETER_URL_INFO = "url_info";
    public static final String PARAMETER_URL_CONTACT = "url_contact";
    public static final String PARAMETER_ORDER = "order";
    public static final String PARAMETER_DATE_BEGIN = "date_begin";
    public static final String PARAMETER_DATE_END = "date_end";
    public static final String PARAMETER_CATEGORY_ID_CATEGORY = "category_id_category";
    public static final String PARAMETER_ID_DEMAND_TYPE = "id_demand_type";
    public static final String PARAMETER_SESSION = "session";
    public static final String PARAMETER_LABEL = "label";
    public static final String PARAMETER_WORKGROUP_KEY = "workgroup_key";
    public static final String PARAMETER_ROLE_KEY = "role_key";
    public static final String PARAMETER_SEARCH = "search";
    public static final String PARAMETER_OPERATOR_DATE_BEGIN = "operator_date_begin";
    public static final String PARAMETER_OPERATOR_DATE_END = "operator_date_end";
    public static final String PARAMETER_CATEGORY_NAME = "category_name";
    public static final String PARAMETER_CATEGORY_DESCRIPTION = "category_description";
    public static final String PARAMETER_URL_RETURN = "url_return";
    public static final String PARAMETER_DEMAND_DATA = "demand_data";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_ID_CRM_USER = "id_crm_user";
    public static final String PARAMETER_SIGNATURE = "signature";
    public static final String PARAMETER_CRM_WEBB_APP_CODE = "crm_web_app_code";
    public static final String PARAMETER_TIMESTAMP = "timestamp";
    public static final String PARAMETER_TARGET = "target";
    public static final String PARAMETER_URL_DELETE = "url_delete";
    public static final String PARAMETER_CHECKBOX_DRAFT_DISPLAY = "bDisplayDraft";
    public static final String PARAMETER_CHECKBOX_USE_IDCRMUSER = "bUseIdCrmUser";
    public static final String PARAMETER_MODIFICATIONDATE = "modificationDate";
    public static final String PARAMETER_DEMANDTYPE = "demandType";
    public static final String PARAMETER_NOTIFICATION = "notification";
    public static final String PARAMETER_ID_STATUS = "id_status";
    public static final String PARAMETER_SORT_ATTRIBUTE = "sorted_attribute_name";
    public static final String PARAMETER_SORT_ORDER = "asc_sort";
    public static final String PARAMETER_INCLUDE_ID_CRM_USER = "include_id_crm_user";
    public static final String PARAMETER_NEED_AUTHENTICATION = "need_authentication";
    public static final String PARAMETER_NEED_VALIDATION = "need_validation";
    
    

    // ACTIONS
    public static final String ACTION_MANAGE_NOTIFICATIONS = "manage_notifications";
    public static final String ACTION_VIEW_NOTIFICATION = "view_notification";
    public static final String ACTION_REMOVE_DEMAND = "remove_demand";
    public static final String ACTION_DO_REMOVE_DRAFT = "do_remove_draft";
    public static final String ACTION_REMOVE_DRAFT = "remove_draft";
    public static final String ACTION_MODIFY_CRM_USER = "modify_crm_user";
    public static final String ACTION_DO_MODIFY_CRM_USER = "do_modify_crm_user";

    // MESSAGES
    public static final String MESSAGE_CONFIRM_REMOVE_DEMAND = "crm.message.confirmRemoveDemand";
    public static final String MESSAGE_ERROR_DATEEND_BEFORE_DATEBEGIN = "crm.message.dateEndBeforeDateBegin";
    public static final String MESSAGE_INVALID_DATEEND = "crm.message.invalidDateEnd";
    public static final String MESSAGE_INVALID_DATEBEGIN = "crm.message.invalidDateBegin";
    public static final String MESSAGE_INVALID_DATE_BEFORE_70 = "crm.message.invalidDate.before1970";
    public static final String MESSAGE_CONFIRM_REMOVE_DEMAND_TYPE = "crm.message.confirmRemoveDemandType";
    public static final String MESSAGE_CONFIRM_PURGE_DEMAND_TYPE = "crm.message.confirmPurgeDemandType";
    public static final String MESSAGE_ERROR = "crm.message.error";
    public static final String MESSAGE_CONFIRM_REMOVE_CATEGORY = "crm.message.confirmRemoveCategory";
    public static final String MESSAGE_CANNOT_REMOVE_CATEGORY = "crm.message.cannotRemoveCategory";
    public static final String MESSAGE_SIZE_TOO_BIG = "crm.message.sizeTooBig";
    public static final String MESSAGE_INVALID_EMAIL = "crm.message.invalidEmail";
    public static final String MESSAGE_EMAIL_ALREADY_IN_USE = "crm.message.emailAlreadyInUse";
    public static final String MESSAGE_INVALID_FORMAT_DATE_MODIFICATION = "crm.message.invalidFormatDateModification";

    // SORTS
    public static final String SORT_DATE_MODIFICATION = "dateModification";
    public static final String SORT_NB_UNREAD_NOTIFICATION = "unreadNotification";

    // EVENTS
    public static final String EVENT_CRM_USER_MODIFIED = "EventCRMUserModified";
    
    /////////////////////////////////////////////////////////////////////////////////
    // Xml Tags
    public static final String TAG_DEMANDE_TYPE_PORTLET = "crm-demand-type-portlet";
    public static final String TAG_DEMANDE_TYPE_PORTLET_CONTENT = "crm-demand-type-portlet-content";
    public static final String TAG_CATEGORY_LIST = "crm-demand-type-category-list";
    
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_CATEGORY_ID = "category-id";
    public static final String TAG_CATEGORY_NAME = "category-name";
    public static final String TAG_CATEGORY_DESCRIPTION = "category-description";
	public static final String TAG_DEMAND_TYPE_LIST = "demand-type-list";
 
	public static final String TAG_DEMAND_TYPE = "demand-type";
	public static final String TAG_DEMAND_TYPE_ID = "demand-type-id";
	public static final String TAG_DEMAND_TYPE_LABEL = "demand-type-label";
    public static final String TAG_DEMAND_TYPE_URL_RESOURCE = "demand-type-url-resource";
    public static final String TAG_DEMAND_TYPE_URL_INFO = "demand-type-url-info";
    public static final String TAG_DEMAND_TYPE_URL_CONTACT = "demand-type-url-contact";
    public static final String TAG_DEMAND_TYPE_ORDER = "demand-type-order";
    public static final String TAG_DEMAND_TYPE_CATEGORY = "demand-type-category";
    public static final String TAG_DEMAND_TYPE_TARGET = "demand-type-target";
    public static final String TAG_DEMAND_TYPE_URL_DELETE = "demand-type-delete";
    public static final String TAG_DEMAND_TYPE_DATE_BEGIN = "demand-type-date-begin";
    public static final String TAG_DEMAND_TYPE_DATE_END = "demand-type-date-end";
    public static final String TAG_DEMAND_TYPE_NEED_AUTHENTICATION = "demand-type-need-authentication";
    public static final String TAG_DEMAND_TYPE_NEED_VALIDATION = "demand-type-need-validation";
    
  

    /**
     * Private constructor
     */
    private CRMConstants(  )
    {
    }
}
