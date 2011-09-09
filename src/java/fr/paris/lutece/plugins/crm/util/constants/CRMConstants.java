/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
    public static final String MARK_MAX_ORDER = "max_order";
    public static final String MARK_DEMAND_TYPE_FILTER = "demand_type_filter";
    public static final String MARK_USER_WORKGROUP_REF_LIST = "user_workgroup_list";
    public static final String MARK_ROLE_REF_LIST = "role_list";
    public static final String MARK_IS_WELL_ORDERED = "is_well_ordered";
    public static final String MARK_OPERATORS_LIST = "operators_list";
    public static final String MARK_CATEGORY = "category";
    public static final String MARK_CRM_USER = "crm_user";

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
    public static final String PARAMETER_TIMESTAMP = "timestamp";

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

    /**
     * Private constructor
     */
    private CRMConstants(  )
    {
    }
}
