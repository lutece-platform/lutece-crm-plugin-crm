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
package fr.paris.lutece.plugins.crm.web.category;

import fr.paris.lutece.plugins.crm.business.demand.category.Category;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppException;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * CategoryJspBean
 *
 */
public class CategoryJspBean extends PluginAdminPageJspBean
{
    // TEMPLATES
    private static final String TEMPLATE_MANAGE_CATEGORIES = "/admin/plugins/crm/category/manage_categories.html";
    private static final String TEMPLATE_CREATE_CATEGORY = "/admin/plugins/crm/category/create_category.html";
    private static final String TEMPLATE_MODIFY_CATEGORY = "/admin/plugins/crm/category/modify_category.html";

    // JSP
    private static final String JSP_DO_REMOVE_CATEGORY = "jsp/admin/plugins/crm/DoRemoveCategory.jsp";
    private static final String JSP_MANAGE_CATEGORIES = "jsp/admin/plugins/crm/ManageCategories.jsp";
    private static final String JSP_REDIRECT_TO_MANAGE_CATEGORIES = "ManageCategories.jsp";

    // VARIABLES
    private CategoryService _categoryService = CategoryService.getService(  );
    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;

    /**
     * Returns the list of categories
     * @param request The Http request
     * @return the categorys list
     */
    public String getManageCategories( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_PAGE_TITLE_MANAGE_CATEGORIES );

        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( CRMConstants.PROPERTY_DEFAULT_LIST_CATEGORY_PER_PAGE,
                50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( JSP_MANAGE_CATEGORIES );
        String strUrl = url.getUrl(  );
        Collection<Category> listCategories = _categoryService.getCategoriesList(  );
        LocalizedPaginator<Category> paginator = new LocalizedPaginator<Category>( (List<Category>) listCategories,
                _nItemsPerPage, strUrl, CRMConstants.PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale(  ) );

        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( CRMConstants.MARK_NB_ITEMS_PER_PAGE, Integer.toString( _nItemsPerPage ) );
        model.put( CRMConstants.MARK_PAGINATOR, paginator );
        model.put( CRMConstants.MARK_CATEGORIES_LIST, paginator.getPageItems(  ) );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_CATEGORIES, getLocale(  ), model );

        return getAdminPage( templateList.getHtml(  ) );
    }

    /**
     * Returns the form to create a category
     * @param request The Http request
     * @return the html code of the category form
     */
    public String getCreateCategory( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_PAGE_TITLE_CREATE_CATEGORY );

        Map<String, Object> model = new HashMap<String, Object>(  );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_CATEGORY, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Process the data capture form of a new category
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    public String doCreateCategory( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strName = request.getParameter( CRMConstants.PARAMETER_CATEGORY_NAME );
        String strDescription = request.getParameter( CRMConstants.PARAMETER_CATEGORY_DESCRIPTION );

        if ( StringUtils.isNotBlank( strName ) && StringUtils.isNotBlank( strDescription ) )
        {
            Category category = new Category(  );
            category.setName( strName );
            category.setDescription( strDescription );

            _categoryService.createCategory( category );

            strUrl = JSP_REDIRECT_TO_MANAGE_CATEGORIES;
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Manages the removal form of a category whose identifier is in the http request
     * @param request The Http request
     * @return the html code to confirm
     */
    public String getConfirmRemoveCategory( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strCategoryId = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );

        if ( StringUtils.isNotBlank( strCategoryId ) && StringUtils.isNumeric( strCategoryId ) )
        {
            int nId = Integer.parseInt( strCategoryId );
            UrlItem url = new UrlItem( JSP_DO_REMOVE_CATEGORY );
            url.addParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY, nId );

            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_CONFIRM_REMOVE_CATEGORY,
                    url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Handles the removal form of a category
     * @param request The Http request
     * @return the jsp URL to display the form to manage categorys
     */
    public String doRemoveCategory( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strCategoryId = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );

        if ( StringUtils.isNotBlank( strCategoryId ) && StringUtils.isNumeric( strCategoryId ) )
        {
            int nId = Integer.parseInt( strCategoryId );

            String strError = _categoryService.removeCategory( nId, getLocale(  ) );

            if ( StringUtils.isBlank( strError ) )
            {
                strUrl = JSP_REDIRECT_TO_MANAGE_CATEGORIES;
            }
            else
            {
                Object[] args = { strError };
                strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_CANNOT_REMOVE_CATEGORY, args,
                        AdminMessage.TYPE_STOP );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }

    /**
     * Returns the form to update info about a category
     * @param request The Http request
     * @return The HTML form to update info
     */
    public String getModifyCategory( HttpServletRequest request )
    {
        setPageTitleProperty( CRMConstants.PROPERTY_PAGE_TITLE_MODIFY_CATEGORY );

        String strUrl = StringUtils.EMPTY;
        String strCategoryId = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );

        if ( StringUtils.isNotBlank( strCategoryId ) && StringUtils.isNumeric( strCategoryId ) )
        {
            int nId = Integer.parseInt( strCategoryId );
            Category category = _categoryService.findByPrimaryKey( nId );

            Map<String, Object> model = new HashMap<String, Object>(  );
            model.put( CRMConstants.MARK_CATEGORY, category );

            HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_CATEGORY, getLocale(  ), model );

            strUrl = getAdminPage( template.getHtml(  ) );
        }
        else
        {
            throw new AppException( I18nService.getLocalizedString( CRMConstants.MESSAGE_ERROR, request.getLocale(  ) ) );
        }

        return strUrl;
    }

    /**
     * Process the change form of a category
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    public String doModifyCategory( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strCategoryId = request.getParameter( CRMConstants.PARAMETER_CATEGORY_ID_CATEGORY );

        if ( StringUtils.isNotBlank( strCategoryId ) && StringUtils.isNumeric( strCategoryId ) )
        {
            String strName = request.getParameter( CRMConstants.PARAMETER_CATEGORY_NAME );
            String strDescription = request.getParameter( CRMConstants.PARAMETER_CATEGORY_DESCRIPTION );

            if ( StringUtils.isNotBlank( strName ) && StringUtils.isNotBlank( strDescription ) )
            {
                int nId = Integer.parseInt( strCategoryId );
                Category category = _categoryService.findByPrimaryKey( nId );

                if ( category != null )
                {
                    category.setName( strName );
                    category.setDescription( strDescription );
                    _categoryService.updateCategory( category );

                    strUrl = JSP_REDIRECT_TO_MANAGE_CATEGORIES;
                }
                else
                {
                    strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR,
                            AdminMessage.TYPE_STOP );
                }
            }
            else
            {
                strUrl = AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, CRMConstants.MESSAGE_ERROR, AdminMessage.TYPE_STOP );
        }

        return strUrl;
    }
}
