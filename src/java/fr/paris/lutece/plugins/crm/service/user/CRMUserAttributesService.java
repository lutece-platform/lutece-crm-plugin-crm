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
package fr.paris.lutece.plugins.crm.service.user;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.UserAttributesService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
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
        return (CRMUserAttributesService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME,
            BEAN_CRM_CRMUSERATTRIBUTESSERVICE );
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
    public String getAttribute( String strUserId, String strAttribute )
    {
        String strAttributeValue = StringUtils.EMPTY;
        CRMUser user = _crmUserService.findByUserGuid( strUserId );

        if ( user != null )
        {
            if ( LuteceUser.NAME_GIVEN.equals( strAttribute ) )
            {
                strAttributeValue = user.getFirstName(  );
            }
            else if ( LuteceUser.NAME_FAMILY.equals( strAttribute ) )
            {
                strAttributeValue = user.getLastName(  );
            }
            else if ( LuteceUser.BUSINESS_INFO_ONLINE_EMAIL.equals( strAttribute ) )
            {
                strAttributeValue = user.getEmail(  );
            }
            else if ( LuteceUser.BUSINESS_INFO_TELECOM_TELEPHONE_NUMBER.equals( strAttribute ) )
            {
                strAttributeValue = user.getPhoneNumber(  );
            }
        }

        return strAttributeValue;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, String> getAttributes( String strUserId )
    {
        Map<String, String> listAttributes = new HashMap<String, String>(  );
        CRMUser user = _crmUserService.findByUserGuid( strUserId );

        if ( user != null )
        {
            listAttributes.put( LuteceUser.NAME_GIVEN, user.getFirstName(  ) );
            listAttributes.put( LuteceUser.NAME_FAMILY, user.getLastName(  ) );
            listAttributes.put( LuteceUser.BUSINESS_INFO_ONLINE_EMAIL, user.getEmail(  ) );
            listAttributes.put( LuteceUser.BUSINESS_INFO_TELECOM_TELEPHONE_NUMBER, user.getPhoneNumber(  ) );
        }

        return listAttributes;
    }
}
