/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.crm.web.demand;

import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeFilter;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.service.demand.DemandService;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.plugins.crm.util.OperatorEnum;
import fr.paris.lutece.plugins.crm.util.TargetEnum;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppException;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.service.workgroup.AdminWorkgroupService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.portal.web.constants.Parameters;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.date.DateUtil;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.sort.AttributeComparator;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * DemandTypeJspBean
 *
 */
public class DemandTypeJspBean extends PluginAdminPageJspBean
{
    public static final String RIGHT_MANAGE_CRM_DEMAND_TYPES = "CRM_DEMAND_TYPES_MANAGEMENT";

    // TEMPLATES
    private static final String TEMPLATE_MANAGE_DEMAND_TYPES = "/admin/plugins/crm/demand/manage_demand_types.html";
    private static final String TEMPLATE_CREATE_DEMAND_TYPE = "/admin/plugins/crm/demand/create_demand_type.html";
    private static final String TEMPLATE_MODIFY_DEMAND_TYPE = "/admin/plugins/crm/demand/modify_demand_type.html";

    // JSP
    private static final String JSP_MANAGE_DEMAND_TYPES = "jsp/admin/plugins/crm/ManageDemandTypes.jsp";
    private static final String JSP_DO_REMOVE_DEMAND_TYPE = "jsp/admin/plugins/crm/DoRemoveDemandType.jsp";
    private static final String JSP_DO_PURGE_DEMAND_TYPE = "jsp/admin/plugins/crm/DoPurgeDemandType.jsp";

    // VARIABLES
    private DemandTypeService _demandTypeService = DemandTypeService.getService(  );
    private DemandService _demandService = DemandService.getService(  );
    private CategoryService _categoryService = CategoryService.getService(  );
    private DemandTypeFilter _dtFilter;
    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;

    /**
     * Get interface of managing the list of demand types
     * @param request {@link HttpServletRequest}
     * @return the html code
     */
    public String getManageDemandTypes( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_MANAGE_DEMAND_TYPES_PAGE_TITLE );

        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( CRMConstants.PROPERTY_DEFAULT_LIST_DEMAND_TYPES_PER_PAGE,
                50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = getUrlManageDemandTypes( request );
        List<DemandType> listDemandTypes = getDemandTypesList( request );

        LocalizedPaginator<DemandType> paginator = new LocalizedPaginator<DemandType>( listDemandTypes, _nItemsPerPage,
                url.getUrl(  ), CRMConstants.PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( CRMConstants.MARK_NB_ITEMS_PER_PAGE, Integer.toString( _nItemsPerPage ) );
        model.put( CRMConstants.MARK_PAGINATOR, paginator );
        model.put( CRMConstants.MARK_DEMAND_TYPES_LIST, paginator.getPageItems(  ) );
        model.put( CRMConstants.MARK_DEMAND_TYPE_FILTER, _dtFilter );
        model.put( CRMConstants.MARK_CATEGORIES_LIST, _categoryService.getCategories( getLocale(  ), true, false ) );
        model.put( CRMConstants.MARK_IS_WELL_ORDERED, _demandTypeService.isWellOrdered(  ) );
        model.put( CRMConstants.MARK_OPERATORS_LIST, _demandTypeService.getOperatorsList(  ) );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_DEMAND_TYPES, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
    * Returns the form to create a demand type
    * @param request The Http request
    * @return the html code of the demand type form
    */
    public String getCreateDemandType( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_CREATE_DEMAND_TYPE_PAGE_TITLE );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( CRMConstants.MARK_CATEGORIES_LIST, _categoryService.getCategories( getLocale(  ), false, false ) );
        model.put( CRMConstants.MARK_USER_WORKGROUP_REF_LIST,
            AdminWorkgroupService.getUserWorkgroups( getUser(  ), getLocale(  ) ) );
        model.put( CRMConstants.MARK_MAX_ORDER, _demandTypeService.findMaxOrder(  ) );
        model.put( CRMConstants.MARK_TARGETS_LIST, _demandTypeService.getTargetsList(  ) );

        if ( SecurityService.isAuthenticationEnable(  ) )
        {
            model.put( CRMConstants.MARK_ROLE_REF_LIST, _demandTypeService.getRolesList(  ) );
        }

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_DEMAND_TYPE, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Process the data capture form of a new demand type
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    public String doCreateDemandType( HttpServletRequest request )
    {
        DemandType demandType = new DemandType(  );
        String strUrl = getDemandTypeData( request, demandType );

        if ( StringUtils.isBlank( strUrl ) )
        {
            // The newly created demand type is placed at the head of the list
            _demandTypeService.create( demandType );
            strUrl = getUrlManageDemandTypes( request ).getUrl(  );
        }

        return strUrl;
    }

    /**
     * Manages the removal form of a demand type whose identifier is in the http request
     * @param request The Http request
     * @return the html code to confirm
     */
    public String getConfirmRemoveDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            UrlItem url = new UrlItem( JSP_DO_REMOVE_DEMAND_TYPE );
            url.addParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE, nIdDemandType );

            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_CONFIRM_REMOVE_DEMAND_TYPE,
                    url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Handles the removal form of a demand type
     * @param request The Http request
     * @return the jsp URL to display the form to manage demand types
     */
    public String doRemoveDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            _demandTypeService.remove( nIdDemandType );
            strUrl = getUrlManageDemandTypes( request ).getUrl(  );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Returns the form to update info about a demand type
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyDemandType( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_MODIFY_DEMAND_TYPE_PAGE_TITLE );

        String strHtml = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            DemandType demandType = _demandTypeService.findByPrimaryKey( nIdDemandType );

            if ( demandType != null )
            {
                Map<String, Object> model = new HashMap<String, Object>(  );
                model.put( CRMConstants.MARK_DEMAND_TYPE, demandType );
                model.put( CRMConstants.MARK_CATEGORIES_LIST,
                    _categoryService.getCategories( getLocale(  ), false, false ) );
                model.put( CRMConstants.MARK_MAX_ORDER, _demandTypeService.findMaxOrder(  ) );
                model.put( CRMConstants.MARK_USER_WORKGROUP_REF_LIST,
                    AdminWorkgroupService.getUserWorkgroups( getUser(  ), getLocale(  ) ) );
                model.put( CRMConstants.MARK_TARGETS_LIST, _demandTypeService.getTargetsList(  ) );

                if ( SecurityService.isAuthenticationEnable(  ) )
                {
                    model.put( CRMConstants.MARK_ROLE_REF_LIST, _demandTypeService.getRolesList(  ) );
                }

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_DEMAND_TYPE, getLocale(  ),
                        model );

                strHtml = getAdminPage( template.getHtml(  ) );
            }
            else
            {
                throw new AppException( I18nService.getLocalizedString( CRMConstants.MESSAGE_ERROR,
                        request.getLocale(  ) ) );
            }
        }
        else
        {
            throw new AppException( I18nService.getLocalizedString( CRMConstants.MESSAGE_ERROR, request.getLocale(  ) ) );
        }

        return strHtml;
    }

    /**
     * Process the change form of a demand type
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    public String doModifyDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            DemandType demandType = new DemandType(  );
            demandType.setIdDemandType( nIdDemandType );
            strUrl = getDemandTypeData( request, demandType );

            if ( StringUtils.isBlank( strUrl ) )
            {
                _demandTypeService.update( demandType );
                strUrl = getUrlManageDemandTypes( request ).getUrl(  );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Do reorder the demand types
     * @param request {@link HttpServletRequest}
     * @return the jsp URL to display the form to manage demand types
     */
    public String doReorderDemandTypes( HttpServletRequest request )
    {
        _demandTypeService.doReorderDemandTypes(  );

        return getUrlManageDemandTypes( request ).getUrl(  );
    }

    /**
     * Manages the purge form of a demand type whose identifier is in the http request
     * @param request The Http request
     * @return the html code to confirm
     */
    public String getConfirmPurgeDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            UrlItem url = new UrlItem( JSP_DO_PURGE_DEMAND_TYPE );
            url.addParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE, nIdDemandType );

            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_CONFIRM_PURGE_DEMAND_TYPE,
                    url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Handles the purge form of a demand type
     * @param request The Http request
     * @return the jsp URL to display the form to manage demand types
     */
    public String doPurgeDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            _demandService.removeByIdDemandType( nIdDemandType );
            strUrl = getUrlManageDemandTypes( request ).getUrl(  );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Enable a demand type
     * @param request The Http request
     * @return the jsp URL to display the form to manage demand types
     */
    public String doEnableDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            DemandType demandType = _demandTypeService.findByPrimaryKey( nIdDemandType );

            if ( demandType != null )
            {
                demandType.setDateEnd( null );
                _demandTypeService.update( demandType );
            }

            strUrl = getUrlManageDemandTypes( request ).getUrl(  );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Disable a demand type
     * @param request The Http request
     * @return the jsp URL to display the form to manage demand types
     */
    public String doDisableDemandType( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strIdDemandType = request.getParameter( CRMConstants.PARAMETER_ID_DEMAND_TYPE );

        if ( StringUtils.isNotBlank( strIdDemandType ) && StringUtils.isNumeric( strIdDemandType ) )
        {
            int nIdDemandType = Integer.parseInt( strIdDemandType );
            DemandType demandType = _demandTypeService.findByPrimaryKey( nIdDemandType );

            if ( demandType != null )
            {
                demandType.setDateEnd( new Date(  ) );
                _demandTypeService.update( demandType );
            }

            strUrl = getUrlManageDemandTypes( request ).getUrl(  );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    // PRIVATE METHODS

    /**
     * Get the data of the demand type
     * @param request {@link HttpServletRequest}
     * @param demandType the {@link DemandType}
     * @return an empty string if there are no errors, messages otherwise
     */
    private String getDemandTypeData( HttpServletRequest request, DemandType demandType )
    {
        String strUrl = StringUtils.EMPTY;
        String strLabel = request.getParameter( CRMConstants.PARAMETER_DEMAND_TYPE_LABEL );
        String strUrlResource = request.getParameter( CRMConstants.PARAMETER_URL_RESOURCE );

        if ( StringUtils.isNotBlank( strLabel ) && StringUtils.isNotBlank( strUrlResource ) )
        {
            String strUrlInfo = request.getParameter( CRMConstants.PARAMETER_URL_INFO );
            String strUrlContact = request.getParameter( CRMConstants.PARAMETER_URL_CONTACT );
            String strDateBegin = request.getParameter( CRMConstants.PARAMETER_DATE_BEGIN );
            String strDateEnd = request.getParameter( CRMConstants.PARAMETER_DATE_END );
            String strOrder = request.getParameter( CRMConstants.PARAMETER_ORDER );
            String strIdCategory = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );
            String strWorkgroupKey = request.getParameter( CRMConstants.PARAMETER_WORKGROUP_KEY );
            String strRoleKey = request.getParameter( CRMConstants.PARAMETER_ROLE_KEY );
            String strTarget = request.getParameter( CRMConstants.PARAMETER_TARGET );

            int nOrder = 0;

            if ( StringUtils.isNotBlank( strOrder ) && StringUtils.isNumeric( strOrder ) )
            {
                nOrder = Integer.parseInt( strOrder );
            }

            int nIdCategory = -1;

            if ( StringUtils.isNotBlank( strIdCategory ) && StringUtils.isNumeric( strIdCategory ) )
            {
                nIdCategory = Integer.parseInt( strIdCategory );
            }

            int nTarget = 0;

            if ( StringUtils.isNotBlank( strTarget ) && StringUtils.isNumeric( strTarget ) )
            {
                nTarget = Integer.parseInt( strTarget );
            }

            Date dateBegin = null;
            Date dateEnd = null;

            if ( StringUtils.isNotBlank( strDateBegin ) )
            {
                dateBegin = DateUtil.formatDateLongYear( strDateBegin, getLocale(  ) );

                if ( dateBegin != null )
                {
                    if ( dateBegin.before( new Date( 0 ) ) )
                    {
                        strUrl = AdminMessageService.getMessageUrl( request,
                                CRMConstants.MESSAGE_INVALID_DATE_BEFORE_70, AdminMessage.TYPE_STOP );
                    }
                }
                else
                {
                    strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_INVALID_DATEBEGIN,
                            AdminMessage.TYPE_STOP );
                }
            }

            if ( StringUtils.isBlank( strUrl ) && StringUtils.isNotBlank( strDateEnd ) )
            {
                dateEnd = DateUtil.formatDateLongYear( strDateEnd, getLocale(  ) );

                if ( dateEnd != null )
                {
                    if ( dateEnd.before( new Date( 0 ) ) )
                    {
                        strUrl = AdminMessageService.getMessageUrl( request,
                                CRMConstants.MESSAGE_INVALID_DATE_BEFORE_70, AdminMessage.TYPE_STOP );
                    }
                }
                else
                {
                    strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_INVALID_DATEEND,
                            AdminMessage.TYPE_STOP );
                }
            }

            // validate period (dateEnd > dateBegin )
            if ( StringUtils.isBlank( strUrl ) && ( dateBegin != null ) && ( dateEnd != null ) )
            {
                if ( dateEnd.before( dateBegin ) )
                {
                    strUrl = AdminMessageService.getMessageUrl( request,
                            CRMConstants.MESSAGE_ERROR_DATEEND_BEFORE_DATEBEGIN, AdminMessage.TYPE_STOP );
                }
            }

            if ( StringUtils.isBlank( strUrl ) )
            {
                demandType.setLabel( strLabel );
                demandType.setUrlResource( StringUtils.isNotBlank( strUrlResource ) ? strUrlResource : StringUtils.EMPTY );
                demandType.setUrlInfo( StringUtils.isNotBlank( strUrlInfo ) ? strUrlInfo : StringUtils.EMPTY );
                demandType.setUrlContact( StringUtils.isNotBlank( strUrlContact ) ? strUrlContact : StringUtils.EMPTY );
                demandType.setOrder( nOrder );
                demandType.setIdCategory( nIdCategory );
                demandType.setDateBegin( dateBegin );
                demandType.setDateEnd( dateEnd );
                demandType.setWorkgroup( StringUtils.isNotBlank( strWorkgroupKey ) ? strWorkgroupKey : StringUtils.EMPTY );
                demandType.setRole( StringUtils.isNotBlank( strRoleKey ) ? strRoleKey : StringUtils.EMPTY );
                demandType.setTarget( TargetEnum.getTarget( nTarget ) );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Get the list of demand types
     * @param request {@link HttpServletRequest}
     * @return a list of {@link DemandType}
     */
    private List<DemandType> getDemandTypesList( HttpServletRequest request )
    {
        List<DemandType> listDemandTypes;
        String strSession = request.getParameter( CRMConstants.PARAMETER_SESSION );

        // Check if it s a search or not
        if ( StringUtils.isNotBlank( strSession ) )
        {
            // find demand types from search
            listDemandTypes = doSearchDemandTypesList( request );
        }
        else
        {
            // Find all demand types
            // Reinit the filter stored in the session
            _dtFilter = null;
            listDemandTypes = _demandTypeService.findAll(  );
        }

        // Get only the demand types the user is authorized to ses
        listDemandTypes = (List<DemandType>) AdminWorkgroupService.getAuthorizedCollection( listDemandTypes, getUser(  ) );

        // Sort the list
        String strSortedAttributeName = request.getParameter( Parameters.SORTED_ATTRIBUTE_NAME );

        if ( StringUtils.isNotBlank( strSortedAttributeName ) )
        {
            String strAscSort = request.getParameter( Parameters.SORTED_ASC );
            boolean bIsAscSort = true;

            if ( StringUtils.isNotBlank( strAscSort ) )
            {
                bIsAscSort = Boolean.parseBoolean( strAscSort );
            }

            Collections.sort( listDemandTypes, new AttributeComparator( strSortedAttributeName, bIsAscSort ) );
        }

        return listDemandTypes;
    }

    /**
     * Search the list of demand type
     * @param request {@link HttpServletRequest}
     * @return a list of {@link DemandType}
     */
    private List<DemandType> doSearchDemandTypesList( HttpServletRequest request )
    {
        String strSearch = request.getParameter( CRMConstants.PARAMETER_SEARCH );

        if ( StringUtils.isNotBlank( strSearch ) || ( _dtFilter == null ) )
        {
            _dtFilter = new DemandTypeFilter(  );

            String strLabel = request.getParameter( CRMConstants.PARAMETER_LABEL );
            String strUrlForm = request.getParameter( CRMConstants.PARAMETER_URL_RESOURCE );
            String strIdCategory = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );
            String strDateBegin = request.getParameter( CRMConstants.PARAMETER_DATE_BEGIN );
            String strDateEnd = request.getParameter( CRMConstants.PARAMETER_DATE_END );
            String strOperatorDateBegin = request.getParameter( CRMConstants.PARAMETER_OPERATOR_DATE_BEGIN );
            String strOperatorDateEnd = request.getParameter( CRMConstants.PARAMETER_OPERATOR_DATE_END );

            if ( StringUtils.isNotBlank( strLabel ) )
            {
                _dtFilter.setLabel( strLabel );
            }

            if ( StringUtils.isNotBlank( strUrlForm ) )
            {
                _dtFilter.setUrlResourece( strUrlForm );
            }

            if ( StringUtils.isNotBlank( strIdCategory ) && StringUtils.isNumeric( strIdCategory ) )
            {
                int nIdCategory = Integer.parseInt( strIdCategory );
                _dtFilter.setIdCategory( nIdCategory );
            }

            if ( StringUtils.isNotBlank( strDateBegin ) )
            {
                Date dateBegin = DateUtil.formatDateLongYear( strDateBegin, getLocale(  ) );

                if ( dateBegin != null )
                {
                    if ( dateBegin.after( new Date( 0 ) ) )
                    {
                        _dtFilter.setDateBegin( dateBegin );
                    }
                }
            }

            if ( StringUtils.isNotBlank( strDateEnd ) )
            {
                Date dateEnd = DateUtil.formatDateLongYear( strDateEnd, getLocale(  ) );

                if ( dateEnd != null )
                {
                    if ( dateEnd.after( new Date( 0 ) ) )
                    {
                        _dtFilter.setDateEnd( dateEnd );
                    }
                }
            }

            if ( StringUtils.isNotBlank( strOperatorDateBegin ) && StringUtils.isNumeric( strOperatorDateBegin ) )
            {
                int nIdOperatorDateBegin = Integer.parseInt( strOperatorDateBegin );

                if ( nIdOperatorDateBegin < OperatorEnum.values(  ).length )
                {
                    _dtFilter.setOperatorDateBegin( OperatorEnum.values(  )[nIdOperatorDateBegin] );
                }
            }

            if ( StringUtils.isNotBlank( strOperatorDateEnd ) && StringUtils.isNumeric( strOperatorDateBegin ) )
            {
                int nIdOperatorDateEnd = Integer.parseInt( strOperatorDateEnd );

                if ( nIdOperatorDateEnd < OperatorEnum.values(  ).length )
                {
                    _dtFilter.setOperatorDateEnd( OperatorEnum.values(  )[nIdOperatorDateEnd] );
                }
            }
        }

        return _demandTypeService.findByFilter( _dtFilter );
    }

    /**
     * Get the url of the interface that manages the demand types
     * @param request {@link HttpServletRequest}
     * @return a {@link UrlItem}
     */
    private UrlItem getUrlManageDemandTypes( HttpServletRequest request )
    {
        UrlItem url = new UrlItem( AppPathService.getBaseUrl( request ) + JSP_MANAGE_DEMAND_TYPES );
        url.addParameter( CRMConstants.PARAMETER_SESSION, CRMConstants.PARAMETER_SESSION );

        String strSortedAttributeName = request.getParameter( Parameters.SORTED_ATTRIBUTE_NAME );

        if ( StringUtils.isNotBlank( strSortedAttributeName ) )
        {
            String strAscSort = request.getParameter( Parameters.SORTED_ASC );

            if ( StringUtils.isNotBlank( strAscSort ) )
            {
                url.addParameter( Parameters.SORTED_ASC, strAscSort );
            }

            url.addParameter( Parameters.SORTED_ATTRIBUTE_NAME, strSortedAttributeName );
        }

        return url;
    }
}
