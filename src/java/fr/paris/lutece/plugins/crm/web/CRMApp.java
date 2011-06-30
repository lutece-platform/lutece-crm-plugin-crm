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
package fr.paris.lutece.plugins.crm.web;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.business.notification.NotificationFilter;
import fr.paris.lutece.plugins.crm.business.notification.NotificationStatusEnum;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.service.demand.DemandService;
import fr.paris.lutece.plugins.crm.service.demand.DemandStatusCRMService;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.SiteMessage;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.message.SiteMessageService;
import fr.paris.lutece.portal.service.page.PageNotFoundException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.web.xpages.XPageApplication;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * CRMApp
 *
 */
public class CRMApp implements XPageApplication
{
    // PARAMETERS
    private static final String PARAMETER_ACTION = "action";
    private static final String PARAMETER_ID_DEMAND = "id_demand";
    private static final String PARAMETER_ID_NOTIFICATION = "id_notification";
    private static final String PARAMETER_PAGE = "page";

    // MARKS
    private static final String MARK_MAP_DEMAND_TYPES_LIST = "map_demand_types_list";
    private static final String MARK_MAP_DEMANDS_LIST = "map_demands_list";
    private static final String MARK_MYLUTECE_USER = "mylutece_user";
    private static final String MARK_CATEGORIES_LIST = "categories_list";
    private static final String MARK_DEMAND_TYPES_LIST = "demand_types_list";
    private static final String MARK_NOTIFICATIONS_LIST = "notifications_list";
    private static final String MARK_NOTIFICATION = "notification";
    private static final String MARK_DEMAND = "demand";
    private static final String MARK_DEMAND_TYPE = "demand_type";
    private static final String MARK_STATUS_CRM_LIST = "status_crm_list";

    // PROPERTIES
    private static final String PROPERTY_PAGE_PATH = "crm.crm.pagePathLabel";
    private static final String PROPERTY_PAGE_TITLE = "crm.crm.pageTitle";
    private static final String PROPERTY_MANAGE_NOTIFICATIONS_PAGE_TITLE = "crm.manage_notifications.pageTitle";
    private static final String PROPERTY_VIEW_NOTIFICATION_PAGE_TITLE = "crm.view_notification.pageTitle";

    // ACTIONS
    private static final String ACTION_MANAGE_NOTIFICATIONS = "manage_notifications";
    private static final String ACTION_VIEW_NOTIFICATION = "view_notification";
    private static final String ACTION_REMOVE_DEMAND = "remove_demand";
    private static final String ACTION_DO_REMOVE_DEMAND = "do_remove_demand";

    // MESSAGES
    private static final String MESSAGE_CONFIRM_REMOVE_DEMAND = "crm.message.confirmRemoveDemand";

    // JSP
    private static final String JSP_PORTAL = "jsp/site/Portal.jsp";

    // TEMPLATES
    private static final String TEMPLATE_CRM_HOME_PAGE = "skin/plugins/crm/crm.html";
    private static final String TEMPLATE_MANAGE_NOTIFICATIONS = "skin/plugins/crm/manage_notifications.html";
    private static final String TEMPLATE_VIEW_NOTIFICATION = "skin/plugins/crm/view_notification.html";
    private DemandTypeService _demandTypeService = DemandTypeService.getService(  );
    private DemandService _demandService = DemandService.getService(  );
    private CategoryService _categoryService = CategoryService.getService(  );
    private NotificationService _notificationService = NotificationService.getService(  );
    private DemandStatusCRMService _statusCRMService = DemandStatusCRMService.getService(  );

    /**
     * Get the XPage of the plugin CRM
     * @param request {@link HttpServletRequest}
     * @param nMode the mode
     * @param plugin {@link Plugin}
     * @return a {@link XPage}
     * @throws UserNotSignedException exception when user is not connected
     * @throws SiteMessageException site message when displaying messages
     */
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin )
        throws UserNotSignedException, SiteMessageException
    {
        XPage page = null;
        LuteceUser user = getUser( request );
        String strAction = request.getParameter( PARAMETER_ACTION );

        if ( StringUtils.isNotBlank( strAction ) )
        {
            if ( ACTION_MANAGE_NOTIFICATIONS.equals( strAction ) )
            {
                page = getManageNotificationsPage( request, user );
            }
            else if ( ACTION_VIEW_NOTIFICATION.equals( strAction ) )
            {
                page = getViewNotificationPage( request, user );
            }
            else if ( ACTION_REMOVE_DEMAND.equals( strAction ) )
            {
                getMessageConfirmation( request );
            }
            else if ( ACTION_DO_REMOVE_DEMAND.equals( strAction ) )
            {
                doRemoveDemand( request );
            }
        }

        if ( page == null )
        {
            page = getCRMHomePage( request, user );
        }

        return page;
    }

    /**
     * Get the home page
     * @param request {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @return a {@link XPage}
     */
    private XPage getCRMHomePage( HttpServletRequest request, LuteceUser user )
    {
        XPage page = new XPage(  );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_MAP_DEMAND_TYPES_LIST, _demandTypeService.findForLuteceUser( request ) );
        model.put( MARK_CATEGORIES_LIST, _categoryService.getCategories( request.getLocale(  ), false, true ) );
        model.put( MARK_MAP_DEMANDS_LIST, _demandService.findByUserGuid( user.getName(  ), request.getLocale(  ) ) );
        model.put( MARK_MYLUTECE_USER, user );
        model.put( MARK_DEMAND_TYPES_LIST, _demandTypeService.findAll(  ) );
        model.put( MARK_STATUS_CRM_LIST, _statusCRMService.getAllStatusCRM( request.getLocale(  ) ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CRM_HOME_PAGE, request.getLocale(  ), model );

        page.setTitle( I18nService.getLocalizedString( PROPERTY_PAGE_TITLE, request.getLocale(  ) ) );
        page.setPathLabel( I18nService.getLocalizedString( PROPERTY_PAGE_PATH, request.getLocale(  ) ) );
        page.setContent( template.getHtml(  ) );

        return page;
    }

    /**
     * Get the page to manage the notifications of a resource
     * @param request {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @return a {@link XPage}
     */
    private XPage getManageNotificationsPage( HttpServletRequest request, LuteceUser user )
    {
        XPage page = null;
        String strIdDemand = request.getParameter( PARAMETER_ID_DEMAND );

        if ( StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
        {
            int nIdDemand = Integer.parseInt( strIdDemand );
            Demand demand = _demandService.findByPrimaryKey( nIdDemand );

            if ( ( demand != null ) && user.getName(  ).equals( demand.getUserGuid(  ) ) )
            {
                // Check the existence of the demand and the owner of the demand is indeed the current user
                page = new XPage(  );

                NotificationFilter nFilter = new NotificationFilter(  );
                nFilter.setIdDemand( nIdDemand );

                DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                Map<String, Object> model = new HashMap<String, Object>(  );
                model.put( MARK_NOTIFICATIONS_LIST, _notificationService.findByFilter( nFilter ) );
                model.put( MARK_DEMAND_TYPE, demandType );
                model.put( MARK_MYLUTECE_USER, user );

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_NOTIFICATIONS,
                        request.getLocale(  ), model );

                page.setTitle( I18nService.getLocalizedString( PROPERTY_MANAGE_NOTIFICATIONS_PAGE_TITLE,
                        request.getLocale(  ) ) );
                page.setPathLabel( I18nService.getLocalizedString( PROPERTY_PAGE_PATH, request.getLocale(  ) ) );
                page.setContent( template.getHtml(  ) );
            }
        }

        return page;
    }

    /**
     * Get the page to view the notification
     * @param request {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @return a {@link XPage}
     */
    private XPage getViewNotificationPage( HttpServletRequest request, LuteceUser user )
    {
        XPage page = null;
        String strIdNotification = request.getParameter( PARAMETER_ID_NOTIFICATION );

        if ( StringUtils.isNotBlank( strIdNotification ) && StringUtils.isNumeric( strIdNotification ) )
        {
            int nIdNotification = Integer.parseInt( strIdNotification );
            Notification notification = _notificationService.findByPrimaryKey( nIdNotification );

            if ( notification != null )
            {
                Demand demand = _demandService.findByPrimaryKey( notification.getIdDemand(  ) );

                if ( ( demand != null ) && user.getName(  ).equals( demand.getUserGuid(  ) ) )
                {
                    if ( notification.getStatus(  ) == NotificationStatusEnum.NEW.getId(  ) )
                    {
                        // Set the status of the notification to READ
                        notification.setStatus( NotificationStatusEnum.READ.getId(  ) );
                        _notificationService.update( notification );
                    }

                    DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                    // Check the existence of the demand and the owner of the demand is indeed the current user
                    page = new XPage(  );

                    Map<String, Object> model = new HashMap<String, Object>(  );
                    model.put( MARK_NOTIFICATION, notification );
                    model.put( MARK_DEMAND, demand );
                    model.put( MARK_MYLUTECE_USER, user );
                    model.put( MARK_DEMAND_TYPE, demandType );

                    HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_VIEW_NOTIFICATION,
                            request.getLocale(  ), model );

                    page.setTitle( I18nService.getLocalizedString( PROPERTY_VIEW_NOTIFICATION_PAGE_TITLE,
                            request.getLocale(  ) ) );
                    page.setPathLabel( I18nService.getLocalizedString( PROPERTY_PAGE_PATH, request.getLocale(  ) ) );
                    page.setContent( template.getHtml(  ) );
                }
            }
        }

        return page;
    }

    /**
     * Get the confirmation message for removing the demand
     * @param request {@link HttpServletRequest}
     * @throws SiteMessageException the confirmation message
     */
    private void getMessageConfirmation( HttpServletRequest request )
        throws SiteMessageException
    {
        String strIdDemand = request.getParameter( PARAMETER_ID_DEMAND );

        if ( StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
        {
            int nIdDemand = Integer.parseInt( strIdDemand );

            // Not safe because the webmaster can change the portal url set in the property file
            // UrlItem url = new UrlItem( AppPathService.getPortalUrl(  ) );
            UrlItem url = new UrlItem( JSP_PORTAL );
            url.addParameter( PARAMETER_PAGE, CRMPlugin.PLUGIN_NAME );
            url.addParameter( PARAMETER_ACTION, ACTION_DO_REMOVE_DEMAND );
            url.addParameter( PARAMETER_ID_DEMAND, nIdDemand );
            SiteMessageService.setMessage( request, MESSAGE_CONFIRM_REMOVE_DEMAND, SiteMessage.TYPE_CONFIRMATION,
                url.getUrl(  ) );
        }
    }

    /**
     * Do remove a demand
     * @param request {@link HttpServletRequest}
     */
    private void doRemoveDemand( HttpServletRequest request )
    {
        String strIdDemand = request.getParameter( PARAMETER_ID_DEMAND );

        if ( StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
        {
            int nIdDemand = Integer.parseInt( strIdDemand );
            Demand demand = _demandService.findByPrimaryKey( nIdDemand );

            if ( ( demand != null ) && ( demand.getIdStatusCRM(  ) == 0 ) )
            {
                // Check if the demand is in draft state
                _demandService.remove( nIdDemand );
            }
        }
    }

    /**
    * Gets the user from the request
    * @param request The HTTP user
    * @return The Lutece User
    * @throws UserNotSignedException exception if user not connected
    */
    public LuteceUser getUser( HttpServletRequest request )
        throws UserNotSignedException
    {
        if ( SecurityService.isAuthenticationEnable(  ) )
        {
            LuteceUser user = SecurityService.getInstance(  ).getRemoteUser( request );

            if ( user == null )
            {
                throw new UserNotSignedException(  );
            }

            return user;
        }
        else
        {
            throw new PageNotFoundException(  );
        }
    }
}
