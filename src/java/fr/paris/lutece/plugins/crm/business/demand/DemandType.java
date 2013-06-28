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
package fr.paris.lutece.plugins.crm.business.demand;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.crm.util.CrmUtils;
import fr.paris.lutece.plugins.crm.util.TargetEnum;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.workgroup.AdminWorkgroupResource;
import fr.paris.lutece.util.date.DateUtil;
import fr.paris.lutece.util.xml.XmlUtil;


/**
 *
 * DemandType
 *
 */
public class DemandType implements AdminWorkgroupResource
{
    public static final String ROLE_NONE = "none";
    
    
    private int _nIdDemandType;
    private String _strLabel;
    private String _strUrlResource;
    private String _strUrlInfo;
    private String _strUrlContact;
    private int _nOrder;
    private int _nIdCategory;
    private Date _dateBegin;
    private Date _dateEnd;
    private String _strWorkgroupKey;
    private String _strRoleKey;
    private TargetEnum _target;
    private String _strUrlDelete;

    /**
     * Constructor
     */
    public DemandType(  )
    {
        _nIdDemandType = 0;
        _strLabel = StringUtils.EMPTY;
        _strUrlResource = StringUtils.EMPTY;
        _strUrlInfo = StringUtils.EMPTY;
        _strUrlContact = StringUtils.EMPTY;
        _nOrder = 1;
        _nIdCategory = -1;
        _strWorkgroupKey = StringUtils.EMPTY;
        _strRoleKey = StringUtils.EMPTY;
        _target = TargetEnum.SELF;
        _strUrlDelete = StringUtils.EMPTY;
    }

    /**
     * Get the id demand type
     * @return the id demand type
     */
    public int getIdDemandType(  )
    {
        return _nIdDemandType;
    }

    /**
     * Set the id demand type
     * @param nIdDemandType the id deman type
     */
    public void setIdDemandType( int nIdDemandType )
    {
        _nIdDemandType = nIdDemandType;
    }

    /**
     * Get the label
     * @return the label
     */
    public String getLabel(  )
    {
        return _strLabel;
    }

    /**
     * Set the label
     * @param strLabel the label
     */
    public void setLabel( String strLabel )
    {
        _strLabel = strLabel;
    }

    /**
     * Get the url of the resource
     * @return the url of the resource
     */
    public String getUrlResource(  )
    {
        return _strUrlResource;
    }

    /**
     * Set the url of the form
     * @param strUrlResource the url of the form
     */
    public void setUrlResource( String strUrlResource )
    {
        _strUrlResource = strUrlResource;
    }

    /**
     * Get the url of the information
     * @return the url of the information
     */
    public String getUrlInfo(  )
    {
        return _strUrlInfo;
    }

    /**
     * Set the url of the information
     * @param strUrlInfo the url of the information
     */
    public void setUrlInfo( String strUrlInfo )
    {
        _strUrlInfo = strUrlInfo;
    }

    /**
     * Get the url of the contact
     * @return the url of the contact
     */
    public String getUrlContact(  )
    {
        return _strUrlContact;
    }

    /**
     * Set the url of the contact
     * @param strUrlContact the url of the contact
     */
    public void setUrlContact( String strUrlContact )
    {
        _strUrlContact = strUrlContact;
    }

    /**
     * Get the order
     * @return the order
     */
    public int getOrder(  )
    {
        return _nOrder;
    }

    /**
     * Set the order
     * @param nOrder the order
     */
    public void setOrder( int nOrder )
    {
        _nOrder = nOrder;
    }

    /**
     * Get the id category
     * @return the ID category
     */
    public int getIdCategory(  )
    {
        return _nIdCategory;
    }

    /**
     * Set the ID category
     * @param nIdCategory the id category
     */
    public void setIdCategory( int nIdCategory )
    {
        _nIdCategory = nIdCategory;
    }

    /**
     * Get the beginning date
     * @return the beginning date
     */
    public Date getDateBegin(  )
    {
        return _dateBegin;
    }

    /**
     * Set the beginning date
     * @param dateBegin the beginning date
     */
    public void setDateBegin( Date dateBegin )
    {
        _dateBegin = dateBegin;
    }

    /**
     * Get the closing date
     * @return the closing date
     */
    public Date getDateEnd(  )
    {
        return _dateEnd;
    }

    /**
     * Set the closing date
     * @param dateEnd the closing date
     */
    public void setDateEnd( Date dateEnd )
    {
        _dateEnd = dateEnd;
    }

    /**
     * Set the workgroup
     * @param strWorkgroupKey the workgroup key
     */
    public void setWorkgroup( String strWorkgroupKey )
    {
        _strWorkgroupKey = strWorkgroupKey;
    }

    /**
     * Get the workgroup key
     * @return the workgroup key
     */
    public String getWorkgroup(  )
    {
        return _strWorkgroupKey;
    }

    /**
     * Set the role
     * @param strRoleKey the role key
     */
    public void setRole( String strRoleKey )
    {
        _strRoleKey = strRoleKey;
    }

    /**
     * Get the role
     * @return the role key
     */
    public String getRole(  )
    {
        return _strRoleKey;
    }

    /**
     * Check if the demand type is open or not
     * @return true if it is open, false otherwise
     */
    public boolean isOpen(  )
    {
        boolean bIsDateBeginCorrect = false;
        boolean bIsDateEndCorrect = false;
        Date dateToday = new Date(  );

        if ( _dateBegin != null )
        {
            if ( _dateBegin.before( dateToday ) )
            {
                bIsDateBeginCorrect = true;
            }
        }
        else
        {
            bIsDateBeginCorrect = true;
        }

        if ( _dateEnd != null )
        {
            if ( _dateEnd.after( dateToday ) )
            {
                bIsDateEndCorrect = true;
            }
        }
        else
        {
            bIsDateEndCorrect = true;
        }

        return bIsDateBeginCorrect && bIsDateEndCorrect;
    }

    /**
     * Check if the demand type is closed
     * @return true if it is closed, false otherwise
     */
    public boolean isClosed(  )
    {
        return !isOpen(  );
    }

    /**
     * Set target
     * @param target the target
     */
    public void setTarget( TargetEnum target )
    {
        _target = target;
    }

    /**
     * Get target
     * @return the target
     */
    public TargetEnum getTarget(  )
    {
        return _target;
    }

    /**
     * Get the url to delete the demand
     * @return the url to delete the demand
     */
    public String getUrlDelete(  )
    {
        return _strUrlDelete;
    }

    /**
     * Set the url to delete the demand
     * @param strUrlDelete the url to delete the demand
     */
    public void setUrlDelete( String strUrlDelete )
    {
        _strUrlDelete = strUrlDelete;
    }
    
    
    /**
     * Returns the xml of this Demande Type
     *
     * @param request The HTTP Servlet request
     * @param locale the Locale
     * @return the xml of this Demande Type
     */
    public String getXml( HttpServletRequest request, Locale locale )
    {
        StringBuffer strXml = new StringBuffer(  );
        XmlUtil.beginElement( strXml, CRMConstants.TAG_DEMAND_TYPE );
        XmlUtil.addElement( strXml, CRMConstants.TAG_DEMAND_TYPE_ID,_nIdDemandType );
        XmlUtil.addElement( strXml, CRMConstants.TAG_DEMAND_TYPE_CATEGORY,_nIdCategory );
        XmlUtil.addElement( strXml, CRMConstants.TAG_DEMAND_TYPE_ORDER, _nOrder );
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_LABEL,_strLabel  );
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_URL_RESOURCE,_strUrlResource);
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_URL_INFO,_strUrlInfo);
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_URL_CONTACT,_strUrlContact);
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_TARGET,_target.toString());
        CrmUtils.addElementHtml( strXml, CRMConstants.TAG_DEMAND_TYPE_URL_DELETE,_strUrlDelete);
        CrmUtils.addElementHtml(strXml,CRMConstants.TAG_DEMAND_TYPE_DATE_BEGIN, _dateBegin !=null ?DateUtil.getDateString(_dateBegin,locale):null);
        CrmUtils.addElementHtml(strXml,CRMConstants.TAG_DEMAND_TYPE_DATE_END, _dateEnd !=null ?DateUtil.getDateString(_dateEnd,locale):null);
        XmlUtil.endElement( strXml, CRMConstants.TAG_DEMAND_TYPE );
        return strXml.toString(  );
    }
    
    
    
    
    
   
    
    
    
}
