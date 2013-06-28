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
package fr.paris.lutece.plugins.crm.business.portlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.demand.category.Category;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.plugins.crm.util.CrmUtils;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.business.portlet.Portlet;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.util.xml.XmlUtil;


/**
 * This class represents business objects DemandTypePortlet
 */
public class DemandTypePortlet extends Portlet
{

    
    


    // Constants
    private int _nIdCategory;
    
    /**
     * Sets the identifier of the portlet type to the value specified in the ArticlesListPortletHome class
     */
    public DemandTypePortlet(  )
    {
    }

    /**
     * Returns the Xml code of the DemandeTypePortlet portlet without XML heading
     *
     * @param request The HTTP Servlet request
     * @return the Xml code of the form portlet content
     */
    public String getXml( HttpServletRequest request )
    {
       
        Locale locale;

        if ( request != null )
        {
            locale = request.getLocale(  );
        }
        else
        {
            locale = I18nService.getDefaultLocale();
        }
        
        StringBuffer strXml = new StringBuffer(  );
        XmlUtil.beginElement( strXml, CRMConstants.TAG_DEMANDE_TYPE_PORTLET );
        XmlUtil.beginElement( strXml, CRMConstants.TAG_DEMANDE_TYPE_PORTLET_CONTENT );
        XmlUtil.beginElement( strXml, CRMConstants.TAG_CATEGORY_LIST );
        if(_nIdCategory!=CrmUtils.CONSTANT_ID_NULL)
        {
        	Category category=null;
        	if(_nIdCategory != CrmUtils.convertStringToInt(CRMConstants.NO_CATEGORY))
        		
        	{
        		category=CategoryService.getService().findByPrimaryKey(_nIdCategory);
        	}
        	else
        	{
        		category=new Category();
        		category.setIdCategory(_nIdCategory);
        		category.setName(" ");
        	}
        	
        	
        	List<DemandType> listDemandType=DemandTypeService.getService().findForLuteceUser(request, _nIdCategory);
        	if(category!=null)
        	{
        		strXml.append(category.getXml(request, locale, listDemandType))	;
        	}
        }
        else
        {
        	Collection<Category> listCategory=CategoryService.getService().getCategoriesList();
        	Map<String, List<DemandType>> mapCategoryDemandType= DemandTypeService.getService().findForLuteceUser(request);
        	for(Category category:listCategory )
        	{
        		strXml.append(category.getXml(request, locale, mapCategoryDemandType.get(Integer.toString(category.getIdCategory()))))	;
        	}
        	
        }
        XmlUtil.endElement( strXml, CRMConstants.TAG_CATEGORY_LIST );
        XmlUtil.endElement( strXml, CRMConstants.TAG_DEMANDE_TYPE_PORTLET_CONTENT);
        XmlUtil.endElement( strXml, CRMConstants.TAG_DEMANDE_TYPE_PORTLET );

        String str = addPortletTags( strXml );

        return str;
    }

    
    /**
     * the Category id associated to the portlet
     * @return the Category id associated to the portlet
     */
    public int getIdCategory() {
		return _nIdCategory;
	}

    /**
     * set the Category id associated to the portlet
     * @param _nIdCategory set the Category id associated to the portlet
     */
	public void setIdCategory(int _nIdCategory) {
		this._nIdCategory = _nIdCategory;
	}

	/**
     * Returns the Xml code of the form portlet with XML heading
     *
     * @param request The HTTP Servlet Request
     * @return the Xml code of the Articles List portlet
     */
    public String getXmlDocument( HttpServletRequest request )
    {
        return XmlUtil.getXmlHeader(  ) + getXml( request );
    }

    /**
     * Updates the current instance of the form portlet object
     */
    public void update(  )
    {
        DemandTypePortletHome.getInstance(  ).update( this );
    }

    /**
     * Removes the current instance of the  the form portlet  object
     */
    public void remove(  )
    {
        DemandTypePortletHome.getInstance(  ).remove( this );
    }
    
    
    @Override
    public Map<String, String> getXslParams() {
    	Locale defaultLocale=I18nService.getDefaultLocale();
    	HashMap<String, String> mapParams=new HashMap<String, String>();
    	mapParams.put(CRMConstants.MARK_XSL_PARAM_I18N_LABEL_DEMAND_TYPES_LIST, I18nService.getLocalizedString(CRMConstants.PROPERTY_LABEL_DEMAND_TYPES_LIST, defaultLocale));
    	mapParams.put(CRMConstants.MARK_XSL_PARAM_I18N_LABEL_CRM_INFO, I18nService.getLocalizedString(CRMConstants.PROPERTY_LABEL_CRM_INFO, defaultLocale));
    	mapParams.put(CRMConstants.MARK_XSL_PARAM_I18N_LABEL_CRM_CONTACT, I18nService.getLocalizedString(CRMConstants.PROPERTY_LABEL_CRM_CONTACT, defaultLocale));
    	mapParams.put(CRMConstants.MARK_XSL_PARAM_I18N_LABEL_CRM_DATE_BEGIN, I18nService.getLocalizedString(CRMConstants.PROPERTY_LABEL_CRM_DATE_BEGIN, defaultLocale));
    	mapParams.put(CRMConstants.MARK_XSL_PARAM_I18N_LABEL_CRM_DATE_END, I18nService.getLocalizedString(CRMConstants.PROPERTY_LABEL_CRM_DATE_END, defaultLocale));
    	return mapParams;
    }

   
}
