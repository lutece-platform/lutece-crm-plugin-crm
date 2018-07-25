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
package fr.paris.lutece.plugins.crm.business.demand.category;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.util.CrmUtils;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.util.xml.XmlUtil;

/**
 *
 * This is the business class for the object Category
 *
 */
public class Category
{

    private int _nIdCategory;
    private String _strName;
    private String _strDescription;

    /**
     * Returns the IdCategory
     * 
     * @return The IdCategory
     */
    public int getIdCategory( )
    {
        return _nIdCategory;
    }

    /**
     * Sets the IdCategory
     * 
     * @param nIdCategory
     *            The IdCategory
     */
    public void setIdCategory( int nIdCategory )
    {
        _nIdCategory = nIdCategory;
    }

    /**
     * Returns the Name
     * 
     * @return The Name
     */
    public String getName( )
    {
        return _strName;
    }

    /**
     * Sets the Name
     * 
     * @param strName
     *            The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Description
     * 
     * @return The Description
     */
    public String getDescription( )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * 
     * @param strDescription
     *            The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     * Returns the xml of this Category with demand Type associated
     *
     * @param request
     *            The HTTP Servlet request
     * @param locale
     *            the Locale
     * @param listDemandType
     *            the list of demande type
     * @return the xml of this Category
     */
    public String getXml( HttpServletRequest request, Locale locale, List<DemandType> listDemandType )
    {
        StringBuffer strXml = new StringBuffer( );
        XmlUtil.beginElement( strXml, CRMConstants.TAG_CATEGORY );
        XmlUtil.addElement( strXml, CRMConstants.TAG_CATEGORY_ID, _nIdCategory );
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_CATEGORY_NAME, _strName );
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_CATEGORY_DESCRIPTION, _strDescription );
        if ( listDemandType != null )
        {
            XmlUtil.beginElement( strXml, CRMConstants.TAG_DEMAND_TYPE_LIST );
            for ( DemandType demandType : listDemandType )
            {
                strXml.append( demandType.getXml( request, locale ) );

            }
            XmlUtil.endElement( strXml, CRMConstants.TAG_DEMAND_TYPE_LIST );
        }

        XmlUtil.endElement( strXml, CRMConstants.TAG_CATEGORY );
        return strXml.toString( );
    }

    /**
     * Returns the xml of this Category
     *
     * @param request
     *            The HTTP Servlet request
     * @param locale
     *            the Locale
     * @return the xml of this Category
     */
    public String getXml( HttpServletRequest request, Locale locale )
    {
        return getXml( request, locale, null );
    }
}
