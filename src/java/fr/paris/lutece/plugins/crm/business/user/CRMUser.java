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
package fr.paris.lutece.plugins.crm.business.user;

import org.apache.commons.lang.StringUtils;

import java.util.Map;


/**
 *
 * CRMUser
 *
 */
public class CRMUser
{
    private int _nIdCRMUser;
    private String _strUserGuid;
    private Map<String, String> _userInfos;

    /**
     * Set the id crm user
     * @param nIdCRMUser the id crm user
     */
    public void setIdCRMUser( int nIdCRMUser )
    {
        _nIdCRMUser = nIdCRMUser;
    }

    /**
     * Get the id crm user
     * @return the id crm user
     */
    public int getIdCRMUser(  )
    {
        return _nIdCRMUser;
    }

    /**
     * Set the user guid
     * @param strUserGuid the user guid
     */
    public void setUserGuid( String strUserGuid )
    {
        _strUserGuid = strUserGuid;
    }

    /**
     * Get the user guid
     * @return the user guid
     */
    public String getUserGuid(  )
    {
        return _strUserGuid;
    }

    /**
     * Set the user attributes
     * @param userInfos the user attributes
     */
    public void setUserAttributes( Map<String, String> userInfos )
    {
        _userInfos = userInfos;
    }

    /**
     * Get the user attributes
     * @return the user attributes
     */
    public Map<String, String> getUserAttributes(  )
    {
        return _userInfos;
    }

    /**
     * Get the user attribute value
     * @param strUserAttributeKey the key
     * @return the user attribute value
     */
    public String getUserAttributeValue( String strUserAttributeKey )
    {
        String strUserInfoValue = StringUtils.EMPTY;

        if ( _userInfos != null )
        {
            strUserInfoValue = _userInfos.get( strUserAttributeKey );
        }

        return StringUtils.isNotBlank( strUserInfoValue ) ? strUserInfoValue : StringUtils.EMPTY;
    }
}
