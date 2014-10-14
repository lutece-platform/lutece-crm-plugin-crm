/*
 * Copyright (c) 2002-2014, Mairie de Paris
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.demand.DemandFilter;
import fr.paris.lutece.plugins.crm.business.demand.DemandStatusCRM;
import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.demand.PaginationFilterSortManager;
import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.business.notification.NotificationFilter;
import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.service.demand.DemandService;
import fr.paris.lutece.plugins.crm.service.demand.DemandStatusCRMService;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.plugins.crm.service.listener.CRMUserModificationListenerService;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.plugins.crm.service.parameters.AdvancedParametersService;
import fr.paris.lutece.plugins.crm.service.signrequest.CRMRequestAuthenticatorService;
import fr.paris.lutece.plugins.crm.service.user.CRMUserAttributesService;
import fr.paris.lutece.plugins.crm.service.user.CRMUserService;
import fr.paris.lutece.plugins.crm.util.ListUtils;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
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
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.PortalJspBean;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.web.xpages.XPageApplication;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.IPaginator;
import fr.paris.lutece.util.string.StringUtil;
import fr.paris.lutece.util.url.UrlItem;


/**
 *
 * CRMApp
 *
 */
public class CRMApp implements XPageApplication
{
    // JSP
    private static final String JSP_PORTAL = "Portal.jsp";
    private static final String JSP_SITE = "jsp/site/";

    // TEMPLATES
    private static final String TEMPLATE_CRM_HOME_PAGE = "skin/plugins/crm/crm.html";
    private static final String TEMPLATE_MANAGE_NOTIFICATIONS = "skin/plugins/crm/manage_notifications.html";
    private static final String TEMPLATE_VIEW_NOTIFICATION = "skin/plugins/crm/view_notification.html";
    private static final String TEMPLATE_MODIFY_CRM_USER = "skin/plugins/crm/modify_crm_user.html";
    
    
    // VARIABLES
    private DemandTypeService _demandTypeService = DemandTypeService.getService(  );
    private DemandService _demandService = DemandService.getService(  );
    private CategoryService _categoryService = CategoryService.getService(  );
    private NotificationService _notificationService = NotificationService.getService(  );
    private DemandStatusCRMService _statusCRMService = DemandStatusCRMService.getService(  );
    private CRMUserService _crmUserService = CRMUserService.getService(  );
    private CRMUserAttributesService _crmUserAttributesService = CRMUserAttributesService.getService(  );
    private AdvancedParametersService _advancedParametersService = AdvancedParametersService.getService(  );
    //PROPERTIE
    private static final String CRM_WEBB_APP_CODE_PROPERTY ="crm.webapp.code";
    
    
    /**
     * Get the XPage of the plugin CRM
     * @param request {@link HttpServletRequest}
     * @param nMode the mode
     * @param plugin {@link Plugin}
     * @return a {@link XPage}
     * @throws UserNotSignedException exception when user is not connected
     * @throws SiteMessageException site message when displaying messages
     */
    @Override
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin )
        throws UserNotSignedException, SiteMessageException
    {
        XPage page = null;
        LuteceUser user = null;
        
        try {
        	user=getUser( request );
		} catch (UserNotSignedException e) {
			
		}
        
       createOrUpdateCRMAccount( user );
        
        String strAction = request.getParameter( CRMConstants.PARAMETER_ACTION );

        if ( StringUtils.isNotBlank( strAction ) )
        {
        	if(user == null)
            {
        		throw new UserNotSignedException();
            }
        	
            if ( CRMConstants.ACTION_MANAGE_NOTIFICATIONS.equals( strAction ) )
            {
                page = getManageNotificationsPage( request, user );
            }
            else if ( CRMConstants.ACTION_VIEW_NOTIFICATION.equals( strAction ) )
            {
                page = getViewNotificationPage( request, user );
            }
            else if ( CRMConstants.ACTION_REMOVE_DEMAND.equals( strAction ) )
            {
                getDemandRemovingConfirmationMessage( request, user );
            }
            else if ( CRMConstants.ACTION_MODIFY_CRM_USER.equals( strAction ) )
            {
            	page = getModifyCRMUserPage( request, user );
            }
            else if ( CRMConstants.ACTION_DO_MODIFY_CRM_USER.equals( strAction ) )
            {
            	doModifyCRMUser( request, user );
                page = getModifyCRMUserPage( request, user );
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
     * @throws SiteMessageException
     */
    private XPage getCRMHomePage( HttpServletRequest request, LuteceUser user )
        throws SiteMessageException
    {
        XPage page = null;
        CRMUser crmUser = user!=null?_crmUserService.findByUserGuid( user.getName(  ) ):null;
        page = new XPage(  );
        Map<String, Object> model = new HashMap<String, Object>(  );
        if ( crmUser != null )
        {
	           //research by filter
	            DemandFilter dFilter = new DemandFilter(  );
	            dFilter.setIdCRMUser( crmUser.getIdCRMUser(  ) );
	
	            PaginationFilterSortManager paginationFilterSortManager = new PaginationFilterSortManager( request );
	
	            String strSession = (String) ( request.getParameter( CRMConstants.PARAMETER_SESSION ) );
	
	            if ( StringUtils.isBlank( strSession ) )
	            {
	                paginationFilterSortManager.cleanSession(  );
	            }
	
	            String strIdStatusToSort = (String) ( request.getParameter( CRMConstants.PARAMETER_ID_STATUS ) );
	            String strSortField = (String) ( request.getParameter( CRMConstants.PARAMETER_SORT_ATTRIBUTE ) );
	            String strSortOrder = (String) ( request.getParameter( CRMConstants.PARAMETER_SORT_ORDER ) );
	
	            int nIdStatusToSort = -1;
	
	            if ( StringUtils.isNotEmpty( strIdStatusToSort ) )
	            {
	                nIdStatusToSort = Integer.parseInt( strIdStatusToSort );
	            }
	
	            if ( StringUtils.isNotEmpty( strSortField ) && StringUtils.isNotEmpty( strSortOrder ) )
	            {
	                paginationFilterSortManager.storeSort( nIdStatusToSort, strSortField,
	                    Boolean.parseBoolean( strSortOrder ) );
	            }
	
	            String strModificationDate = request.getParameter( CRMConstants.PARAMETER_MODIFICATIONDATE );
	            String strDemandType = request.getParameter( CRMConstants.PARAMETER_DEMANDTYPE );
	            String strNotification = request.getParameter( CRMConstants.PARAMETER_NOTIFICATION );
	
	            if ( StringUtils.isNotBlank( strModificationDate ) || StringUtils.isNotBlank( strDemandType ) ||
	                    StringUtils.isNotBlank( strNotification ) )
	            {
	                paginationFilterSortManager.cleanSessionFilter(  );
	            }
	
	            if ( StringUtils.isNotBlank( strModificationDate ) )
	            {
	                Date modificationDate = checkFormatModificationDateFilter( strModificationDate, request );
	                paginationFilterSortManager.storeFilterModificationDate( modificationDate );
	                paginationFilterSortManager.storeFilterStringModificationDate( strModificationDate );
	            }
	
	            if ( StringUtils.isNotBlank( strDemandType ) )
	            {
	                int nIdDemandType = Integer.parseInt( strDemandType );
	                paginationFilterSortManager.storeFilterDemandType( nIdDemandType );
	            }
	
	            if ( StringUtils.isNotBlank( strNotification ) )
	            {
	                paginationFilterSortManager.storeFilterNotification( strNotification );
	            }
	
	            Date dateModificationSession = paginationFilterSortManager.retrieveFilterModificationDate(  );
	
	            if ( dateModificationSession != null )
	            {
	                dFilter.setDateModification( dateModificationSession );
	                model.put( CRMConstants.MARK_MODIFICATIONDATE,
	                    paginationFilterSortManager.retrieveFilterStringModificationDate(  ) );
	            }
	
	            Integer nIdDemandTypeSession = paginationFilterSortManager.retrieveFilterDemandType(  );
	
	            if ( ( nIdDemandTypeSession != null ) && ( nIdDemandTypeSession >= 0 ) )
	            {
	                dFilter.setIdDemandType( nIdDemandTypeSession );
	            }
	
	            String strNotificationSession = paginationFilterSortManager.retrieveFilterNotification(  );
	
	            if ( StringUtils.isNotBlank( strNotificationSession ) )
	            {
	                dFilter.setNotification( strNotificationSession );
	            }
	            
	            model.put( CRMConstants.MARK_MAP_DEMANDS_LIST,
	                    _demandService.findByFilterMap( dFilter, request.getLocale(  ), paginationFilterSortManager ) );
	            model.put( CRMConstants.MARK_FILTER, dFilter );
	            
	            Map<String, IPaginator<Demand>> mapPaginator = new HashMap<String, IPaginator<Demand>>(  );
	            Map<String, String> mapNbItemsPerPage = new HashMap<String, String>(  );
	            int nIdStatus;
	
	            for ( DemandStatusCRM statusCRM : DemandStatusCRMService.getService(  )
	                                                                    .getAllStatusCRM( request.getLocale(  ) ) )
	            {
	                nIdStatus = statusCRM.getIdStatusCRM(  );
	
	                IPaginator<Demand> paginator = paginationFilterSortManager.retrievePaginator( nIdStatus );
	                int nItemsPerPage = paginationFilterSortManager.retrieveItemsPerPage( nIdStatus );
	
	                mapNbItemsPerPage.put( Integer.toString( nIdStatus ), Integer.toString( nItemsPerPage ) );
	                mapPaginator.put( Integer.toString( nIdStatus ), paginator );
	            }
	            model.put( CRMConstants.MARK_STATUS_CRM_LIST, _statusCRMService.getAllStatusCRM( request.getLocale(  ) ) );
	
	            model.put( CRMConstants.MARK_MAP_PAGINATOR, mapPaginator );
	            model.put( CRMConstants.MARK_MAP_NB_ITEMS_PER_PAGE, mapNbItemsPerPage );
	            model.put( CRMConstants.MARK_DISPLAYDRAFT,
	                    _advancedParametersService.isParameterValueByKey( CRMConstants.CONSTANT_DISPLAYDRAFT ) );
	             
        	}
        	model.put( CRMConstants.MARK_LOCALE, request.getLocale(  ) );
            model.put( CRMConstants.MARK_MAP_DEMAND_TYPES_LIST, _demandTypeService.findForLuteceUser( request ) );
            model.put( CRMConstants.MARK_CATEGORIES_LIST,
                _categoryService.getCategories( request.getLocale(  ), false, true ) );
             model.put( CRMConstants.MARK_DEMAND_TYPES_LIST, _demandTypeService.findAll(  ) );
          
            model.put( CRMConstants.MARK_CRM_USER, crmUser );
            List<DemandType> listAllOpenedDemandType = initListAllOpenedDemandType(  );

            model.put( CRMConstants.MARK_DEMAND_TYPES_REFLIST,
                ListUtils.toReferenceList( listAllOpenedDemandType, "idDemandType", "label", "" ) );
            
            model.put(CRMConstants.MARK_MAP_DO_LOGIN,SecurityService.getInstance(  ).getLoginPageUrl() );
            model.put(CRMConstants.MARK_BASE_URL, AppPathService.getBaseUrl(request));
            HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CRM_HOME_PAGE, request.getLocale(  ), model );

            page.setTitle( I18nService.getLocalizedString( CRMConstants.PROPERTY_PAGE_TITLE, request.getLocale(  ) ) );
            page.setPathLabel( I18nService.getLocalizedString( CRMConstants.PROPERTY_PAGE_PATH, request.getLocale(  ) ) );
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
        CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );
        String strIdDemand = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND );

        if ( ( crmUser != null ) && StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
        {
            int nIdDemand = Integer.parseInt( strIdDemand );
            Demand demand = _demandService.findByPrimaryKey( nIdDemand );

            if ( ( demand != null ) && ( crmUser.getIdCRMUser(  ) == demand.getIdCRMUser(  ) ) )
            {
                // Check the existence of the demand and the owner of the demand is indeed the current user
                page = new XPage(  );

                NotificationFilter nFilter = new NotificationFilter(  );
                nFilter.setIdDemand( nIdDemand );

                DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                Map<String, Object> model = new HashMap<String, Object>(  );
                model.put( CRMConstants.MARK_NOTIFICATIONS_LIST, _notificationService.findByFilter( nFilter ) );
                model.put( CRMConstants.MARK_DEMAND_TYPE, demandType );

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_NOTIFICATIONS,
                        request.getLocale(  ), model );

                page.setTitle( I18nService.getLocalizedString( CRMConstants.PROPERTY_MANAGE_NOTIFICATIONS_PAGE_TITLE,
                        request.getLocale(  ) ) );
                page.setPathLabel( I18nService.getLocalizedString( CRMConstants.PROPERTY_PAGE_PATH,
                        request.getLocale(  ) ) );
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
        CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );
        String strIdNotification = request.getParameter( CRMConstants.PARAMETER_ID_NOTIFICATION );

        if ( ( crmUser != null ) && StringUtils.isNotBlank( strIdNotification ) &&
                StringUtils.isNumeric( strIdNotification ) )
        {
            int nIdNotification = Integer.parseInt( strIdNotification );
            Notification notification = _notificationService.findByPrimaryKey( nIdNotification );

            if ( notification != null )
            {
                Demand demand = _demandService.findByPrimaryKey( notification.getIdDemand(  ) );

                if ( ( demand != null ) && ( crmUser.getIdCRMUser(  ) == demand.getIdCRMUser(  ) ) )
                {
                    // Check the existence of the demand and the owner of the demand is indeed the current user
                    if ( !notification.isRead(  ) )
                    {
                        // Set the status of the notification to READ
                        notification.setIsRead( true );
                        _notificationService.update( notification );
                    }

                    DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                    page = new XPage(  );

                    Map<String, Object> model = new HashMap<String, Object>(  );
                    model.put( CRMConstants.MARK_NOTIFICATION, notification );
                    model.put( CRMConstants.MARK_DEMAND, demand );
                    model.put( CRMConstants.MARK_DEMAND_TYPE, demandType );

                    HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_VIEW_NOTIFICATION,
                            request.getLocale(  ), model );

                    page.setTitle( I18nService.getLocalizedString( CRMConstants.PROPERTY_VIEW_NOTIFICATION_PAGE_TITLE,
                            request.getLocale(  ) ) );
                    page.setPathLabel( I18nService.getLocalizedString( CRMConstants.PROPERTY_PAGE_PATH,
                            request.getLocale(  ) ) );
                    page.setContent( template.getHtml(  ) );
                }
            }
        }

        return page;
    }

    /**
     * Get the modify crm account page
     * @param request {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @return a {@link XPage}
     */
    private XPage getModifyCRMUserPage( HttpServletRequest request, LuteceUser user )
    {
        XPage page = null;
        CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );

        if ( crmUser != null )
        {
            page = new XPage(  );

            Map<String, Object> model = new HashMap<String, Object>(  );
            model.put( CRMConstants.MARK_CRM_USER, crmUser );

            HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_CRM_USER, request.getLocale(  ),
                    model );

            page.setTitle( I18nService.getLocalizedString( CRMConstants.PROPERTY_VIEW_NOTIFICATION_PAGE_TITLE,
                    request.getLocale(  ) ) );
            page.setPathLabel( I18nService.getLocalizedString( CRMConstants.PROPERTY_PAGE_PATH, request.getLocale(  ) ) );
            page.setContent( template.getHtml(  ) );
        }

        return page;
    }

    /**
     * Do modify a crm user
     * @param request the {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @throws SiteMessageException message if some info are not well filled
     */
    private void doModifyCRMUser( HttpServletRequest request, LuteceUser user )
        throws SiteMessageException
    {
        CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );

        if ( crmUser != null )
        {
            UrlItem url = new UrlItem( JSP_SITE + JSP_PORTAL );
            url.addParameter( CRMConstants.PARAMETER_PAGE, CRMPlugin.PLUGIN_NAME );
            url.addParameter( CRMConstants.PARAMETER_ACTION, CRMConstants.ACTION_MODIFY_CRM_USER );

            int nMaxSize = AppPropertiesService.getPropertyInt( CRMConstants.PROPERTY_CRM_USER_MAX_SIZE, 255 );

            Map<String, String> userAttributes = new HashMap<String, String>(  );

            for ( String strUserAttributeKey : _crmUserAttributesService.getUserAttributeKeys(  ) )
            {
                String strUserAttributeValue = request.getParameter( strUserAttributeKey );

                if ( StringUtils.isNotBlank( strUserAttributeValue ) && ( strUserAttributeValue.length(  ) > nMaxSize ) )
                {
                    Object[] params = { nMaxSize };
                    SiteMessageService.setMessage( request, CRMConstants.MESSAGE_SIZE_TOO_BIG, params,
                        SiteMessage.TYPE_STOP );
                }

                if ( StringUtils.isNotBlank( strUserAttributeKey )
                        && strUserAttributeKey.endsWith( CRMConstants.PARAMETER_EMAIL ) )
                {
                    if ( !StringUtil.checkEmail( strUserAttributeValue ) )
                    {
                        SiteMessageService.setMessage( request, CRMConstants.MESSAGE_INVALID_EMAIL,
                                SiteMessage.TYPE_STOP, url.getUrl( ) );
                    }
                    // If the user changed one of his emails
                    if ( !StringUtils.equals( crmUser.getUserAttributeValue( strUserAttributeKey ),
                            strUserAttributeValue ) )
                    {
                        if ( _crmUserAttributesService.isAttributeValueInUse( strUserAttributeValue,
                                strUserAttributeKey ) )
                        {
                            SiteMessageService.setMessage( request, CRMConstants.MESSAGE_EMAIL_ALREADY_IN_USE,
                                    SiteMessage.TYPE_STOP, url.getUrl( ) );
                        }
                    }
                }


                userAttributes.put( strUserAttributeKey,
                    StringUtils.isNotBlank( strUserAttributeValue ) ? strUserAttributeValue : StringUtils.EMPTY );
            }

            crmUser.setUserAttributes( userAttributes );

            _crmUserService.update( crmUser );
            CRMUserModificationListenerService.getService(  )
                                              .notifyListeners( crmUser, CRMConstants.EVENT_CRM_USER_MODIFIED );
        }
    }

    /**
     * Get the confirmation message for removing the demand
     * @param request {@link HttpServletRequest}
     * @param user the {@link LuteceUser}
     * @throws SiteMessageException the confirmation message
     */
    private void getDemandRemovingConfirmationMessage( HttpServletRequest request, LuteceUser user )
        throws SiteMessageException
    {
        String strIdDemand = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND );
        String strWebAppCode=AppPropertiesService.getProperty(CRM_WEBB_APP_CODE_PROPERTY);
        CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );

        if ( ( crmUser != null ) && StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
        {
            int nIdDemand = Integer.parseInt( strIdDemand );
            Demand demand = _demandService.findByPrimaryKey( nIdDemand );

            if ( ( demand != null ) && ( demand.getIdStatusCRM(  ) == 0 ) &&
                    ( crmUser.getIdCRMUser(  ) == demand.getIdCRMUser(  ) ) )
            {
                // Check the existence of the demand and the owner of the demand is indeed the current user and the demand is not validated
                DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                if ( demandType != null )
                {
                    String strData = demand.getData(  ).replace( "\"", "'" );

                    List<String> listElements = new ArrayList<String>(  );
                    listElements.add( Integer.toString( nIdDemand ) );
                    if(StringUtils.isNotBlank(strWebAppCode ))
                    {
                    	listElements.add( strWebAppCode );
                    }

                    String strTimestamp = Long.toString( new Date(  ).getTime(  ) );
                    String strSignature = CRMRequestAuthenticatorService.getRequestAuthenticatorForUrl(  )
                                                                        .buildSignature( listElements, strTimestamp );

                    StringBuilder sbUrlReturn = new StringBuilder( AppPathService.getBaseUrl( request ) );

                    if ( !sbUrlReturn.toString(  ).endsWith( CRMConstants.SLASH ) )
                    {
                        sbUrlReturn.append( CRMConstants.SLASH );
                    }

                    sbUrlReturn.append( JSP_SITE + JSP_PORTAL );

                    UrlItem urlReturn = new UrlItem( sbUrlReturn.toString(  ) );
                    urlReturn.addParameter( CRMConstants.PARAMETER_PAGE, CRMPlugin.PLUGIN_NAME );

                    UrlItem url = new UrlItem( demandType.getUrlResource(  ) );
                    url.addParameter( CRMConstants.PARAMETER_ACTION, CRMConstants.ACTION_REMOVE_DRAFT );
                    url.addParameter( CRMConstants.PARAMETER_ID_DEMAND, nIdDemand );
                    url.addParameter( CRMConstants.PARAMETER_TIMESTAMP, strTimestamp );
                    url.addParameter( CRMConstants.PARAMETER_SIGNATURE, strSignature );
                    url.addParameter( CRMConstants.PARAMETER_DEMAND_DATA, strData );
                    url.addParameter( CRMConstants.PARAMETER_URL_RETURN, urlReturn.getUrl(  ) );
                    if(StringUtils.isNotBlank(strWebAppCode ))
                    {
                    	 url.addParameter( CRMConstants.PARAMETER_CRM_WEBB_APP_CODE, strWebAppCode );
                    }
                    SiteMessageService.setMessage( request, CRMConstants.MESSAGE_CONFIRM_REMOVE_DEMAND,
                        SiteMessage.TYPE_CONFIRMATION, url.getUrl(  ) );
                }
            }
        }
    }

    /**
     * Create a CRM account if the current user does not have one
     * @param user the LuteceUser
     */
    private void createOrUpdateCRMAccount( LuteceUser user )
    {
    	  if(	user!=null	)
          {
         
	    	CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );
	
	        if ( crmUser == null )
	        {
	            crmUser = new CRMUser(  );
	            crmUser.setUserGuid( user.getName(  ) );
	            crmUser.setStatus( CRMUser.STATUS_ACTIVATED );
	
	            Map<String, String> userAttributes = new HashMap<String, String>(  );
	
	            for ( String strUserAttributeKey : _crmUserAttributesService.getUserAttributeKeys(  ) )
	            {
	                userAttributes.put( strUserAttributeKey, user.getUserInfo( strUserAttributeKey ) );
	            }
	
	            crmUser.setUserAttributes( userAttributes );
	            _crmUserService.create( crmUser );
	        }
	        else if ( crmUser.isMustBeUpdated())
	        {
	        	 crmUser.setMustBeUpdated(false);
		         crmUser.setStatus( CRMUser.STATUS_ACTIVATED );
		         Map<String, String> userAttributes = new HashMap<String, String>(  );
		         for ( String strUserAttributeKey : _crmUserAttributesService.getUserAttributeKeys(  ) )
	             {
	                userAttributes.put( strUserAttributeKey, user.getUserInfo( strUserAttributeKey ) );
	             }
	
		         crmUser.setUserAttributes( userAttributes );
		         _crmUserService.update( crmUser );
	        	
	        }
          }
    }

    /**
     * Do open a demand type
     * @param request the HTTP request
     * @return the resource url of the demand type
     */
    public String doOpenDemandType( HttpServletRequest request )
    {
        String strUrl = AppPathService.getBaseUrl( request );
        LuteceUser user;
        String strWebAppCode=AppPropertiesService.getProperty(CRM_WEBB_APP_CODE_PROPERTY);
        try
        {
            
        	String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );
    		CRMUser crmUser=null;
        	 
           if ( StringUtils.isNotBlank( strIdDemandType ) &&
                    StringUtils.isNumeric( strIdDemandType )  )
            {
        	   
        	    int nIdDemandType = Integer.parseInt( strIdDemandType );
                DemandType demandType = _demandTypeService.findByPrimaryKey( nIdDemandType );
                
                if ( ( demandType != null ) && demandType.isOpen(  ) )
                {
                 
                	if( demandType.isIncludeIdCrmUser())
                	{
                		user = getUser( request );
                		crmUser = _crmUserService.findByUserGuid( user.getName(  ) );
                		//if crm user does not exist create crm user
                		if(user!= null &&  crmUser == null )
                		{
                			crmUser=new CRMUser();
                			crmUser.setUserGuid(user.getName());
                			crmUser.setMustBeUpdated(true);
                			crmUser.setIdCRMUser(_crmUserService.create(crmUser));
                		}
                		
                		
                	}
                	
                	
                	List<String> listElements = new ArrayList<String>(  );
                     
                    listElements.add( Integer.toString( demandType.getIdDemandType(  ) ) );
                    if(demandType.isIncludeIdCrmUser())
                    {
                    	listElements.add( Integer.toString( crmUser.getIdCRMUser(  ) ) );
                    	
                    }
                    
                     if(StringUtils.isNotBlank(strWebAppCode ))
                    {
                    	listElements.add( strWebAppCode );
                    }
                    
                  
                    
                    String strTimestamp = Long.toString( new Date(  ).getTime(  ) );
                    String strSignature = CRMRequestAuthenticatorService.getRequestAuthenticatorForUrl(  )
                                                                        .buildSignature( listElements, strTimestamp );

                    UrlItem url = new UrlItem( demandType.getUrlResource(  ) );
                    url.addParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE, demandType.getIdDemandType(  ) );
                    if (demandType.isIncludeIdCrmUser())
                    {
                    	url.addParameter( CRMConstants.PARAMETER_ID_CRM_USER, crmUser.getIdCRMUser(  ) );
                    }
                    if(StringUtils.isNotBlank(strWebAppCode ))
                    {
                    	 url.addParameter( CRMConstants.PARAMETER_CRM_WEBB_APP_CODE, strWebAppCode );
                    }
                    
                    url.addParameter( CRMConstants.PARAMETER_TIMESTAMP, strTimestamp );
                    url.addParameter( CRMConstants.PARAMETER_SIGNATURE, strSignature );
                    
                    
                    strUrl = url.getUrl(  );
                }
            }
        }
        catch ( UserNotSignedException e )
        {
            strUrl = PortalJspBean.redirectLogin( request );
        }

        return strUrl;
    }

    /**
     * Do edit a demand
     * @param request the HTTP request
     * @return the url resource to edit the resource
     */
    public String doEditDemand( HttpServletRequest request )
    {
        String strUrl = AppPathService.getBaseUrl( request );
        LuteceUser user;
        String strWebAppCode=AppPropertiesService.getProperty(CRM_WEBB_APP_CODE_PROPERTY);
        try
        {
            user = getUser( request );

            CRMUser crmUser = _crmUserService.findByUserGuid( user.getName(  ) );
            String strIdDemand = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND );

            if ( ( crmUser != null ) && StringUtils.isNotBlank( strIdDemand ) && StringUtils.isNumeric( strIdDemand ) )
            {
                int nIdDemand = Integer.parseInt( strIdDemand );
                Demand demand = _demandService.findByPrimaryKey( nIdDemand );

                if ( ( demand != null ) && ( crmUser.getIdCRMUser(  ) == demand.getIdCRMUser(  ) ) &&
                        ( demand.getIdStatusCRM(  ) == 0 ) )
                {
                    DemandType demandType = _demandTypeService.findByPrimaryKey( demand.getIdDemandType(  ) );

                    if ( ( demandType != null ) && demandType.isOpen(  ) )
                    {
                        List<String> listElements = new ArrayList<String>(  );
                        listElements.add( Integer.toString( demand.getIdDemand(  ) ) );
                        if(StringUtils.isNotBlank(strWebAppCode ))
                        {
                        	listElements.add( strWebAppCode );
                        }
                        String strTimestamp = Long.toString( new Date(  ).getTime(  ) );
                        String strSignature = CRMRequestAuthenticatorService.getRequestAuthenticatorForUrl(  )
                                                                            .buildSignature( listElements, strTimestamp );

                        UrlItem url = new UrlItem( demandType.getUrlResource(  ) );
                        url.addParameter( CRMConstants.PARAMETER_ID_DEMAND, demand.getIdDemand(  ) );
                        if(StringUtils.isNotBlank(strWebAppCode ))
                        {
                        	 url.addParameter( CRMConstants.PARAMETER_CRM_WEBB_APP_CODE, strWebAppCode );
                        }
                        url.addParameter( CRMConstants.PARAMETER_DEMAND_DATA, demand.getData(  ) );
                        url.addParameter( CRMConstants.PARAMETER_TIMESTAMP, strTimestamp );
                        url.addParameter( CRMConstants.PARAMETER_SIGNATURE, strSignature );

                        strUrl = url.getUrl(  );
                    }
                }
            }
        }
        catch ( UserNotSignedException e )
        {
            strUrl = PortalJspBean.redirectLogin( request );
        }

        return strUrl;
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

    /**
     * Get the list of all the opened demand types only
     * @return the list of opened demand types
     */
    private List<DemandType> initListAllOpenedDemandType(  )
    {
        List<DemandType> listAllDemandType = _demandTypeService.findAll(  );
        List<DemandType> listAllOpenedDemandType = new ArrayList<DemandType>(  );

        for ( DemandType demandType : listAllDemandType )
        {
            if ( demandType.isOpen(  ) )
            {
                listAllOpenedDemandType.add( demandType );
            }
        }

        return listAllOpenedDemandType;
    }

    /**
     * Check the format of the filter modification date
     * @throws SiteMessageException
     */
    private Date checkFormatModificationDateFilter( String strModificationDate, HttpServletRequest request )
        throws SiteMessageException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        sdf.setLenient( true );

        Date d = new Date(  );

        try
        {
            d = sdf.parse( strModificationDate );
        }
        catch ( Exception e )
        {
            SiteMessageService.setMessage( request, CRMConstants.MESSAGE_INVALID_FORMAT_DATE_MODIFICATION );
        }

        String t = sdf.format( d );

        if ( t.compareTo( strModificationDate ) != 0 )
        {
            SiteMessageService.setMessage( request, CRMConstants.MESSAGE_INVALID_FORMAT_DATE_MODIFICATION );
        }

        return d;
    }
}
