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
package fr.paris.lutece.plugins.crm.service.user;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.business.user.CRMUserAttributeHome;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.security.UserAttributesService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 *
 * CRMUserAttributesService
 *
 */
public final class CRMUserAttributesService implements UserAttributesService
{
    private static final String BEAN_CRM_CRMUSERATTRIBUTESSERVICE = "crm.crmUserAttributesService";
    private CRMUserService _crmUserService;

    /**
     * Private constructor
     */
    private CRMUserAttributesService(  )
    {
    }

    /**
     * Get the instance of CRMUserAttributesService
     * @return the instance of {@link CRMUserAttributesService}
     */
    public static CRMUserAttributesService getService(  )
    {
        return SpringContextService.getBean( BEAN_CRM_CRMUSERATTRIBUTESSERVICE );
    }

    /**
     * Set the CRMUserService
     * @param crmUserService the CRMUserService
     */
    public void setCRMUserService( CRMUserService crmUserService )
    {
        _crmUserService = crmUserService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAttribute( String strUserId, String strAttribute )
    {
        String strAttributeValue = StringUtils.EMPTY;
        CRMUser user = _crmUserService.findByUserGuid( strUserId );

        if ( ( user != null ) && ( user.getUserAttributes(  ) != null ) )
        {
            strAttributeValue = user.getUserAttributes(  ).get( strAttribute );

            if ( StringUtils.isBlank( strAttributeValue ) )
            {
                strAttributeValue = StringUtils.EMPTY;
            }
        }

        return strAttributeValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getAttributes( String strUserId )
    {
        Map<String, String> listAttributes = new HashMap<String, String>(  );
        CRMUser user = _crmUserService.findByUserGuid( strUserId );

        if ( user != null )
        {
            listAttributes = user.getUserAttributes(  );
        }

        return listAttributes;
    }

    /**
     * Get the user attributes from a given id crm user
     * @param nIdCRMUser the id crm user
     * @return a map of user attribute key, user attribute value
     */
    public Map<String, String> getAttributes( int nIdCRMUser )
    {
        return CRMUserAttributeHome.findByPrimaryKey( nIdCRMUser );
    }

    /**
     * Remove the user attributes of the crm user
     * @param nIdCRMUser the id crm user
     */
    public void remove( int nIdCRMUser )
    {
        CRMUserAttributeHome.remove( nIdCRMUser );
    }

    /**
     * Create a new user attribute
     * @param nIdCRMUser the id crm user
     * @param strUserAttributeKey the user attribute key
     * @param strUserAttributeValue the user attribute value
     */
    public void create( int nIdCRMUser, String strUserAttributeKey, String strUserAttributeValue )
    {
        CRMUserAttributeHome.create( nIdCRMUser, strUserAttributeKey, strUserAttributeValue );
    }

    /**
     * Get the list of user attribute keys defined in <b>crm.properties</b>
     * @return a list of user attribute keys
     */
    public List<String> getUserAttributeKeys(  )
    {
        List<String> listUserAttributeKeys = new ArrayList<String>(  );
        String strUserAttributeKeys = AppPropertiesService.getProperty( CRMConstants.PROPERTY_CRM_USER_ATTRIBUTE_KEYS );

        if ( StringUtils.isNotBlank( strUserAttributeKeys ) )
        {
            String[] userAttributeKeys = strUserAttributeKeys.split( CRMConstants.COMMA );

            if ( ( userAttributeKeys != null ) && ( userAttributeKeys.length > 0 ) )
            {
                for ( String strUserAttributeKey : userAttributeKeys )
                {
                    listUserAttributeKeys.add( strUserAttributeKey );
                }
            }
        }

        return listUserAttributeKeys;
    }

    /**
     * Gets the user attribute key labels.
     *
     * @param locale the locale
     * @return the user attribute key labels
     */
    public List<String> getUserAttributeKeyLabels( Locale locale )
    {
        List<String> listLabels = new ArrayList<String>(  );

        for ( String strAttributeKey : getUserAttributeKeys(  ) )
        {
            listLabels.add( I18nService.getLocalizedString( strAttributeKey, locale ) );
        }

        return listLabels;
    }
}
