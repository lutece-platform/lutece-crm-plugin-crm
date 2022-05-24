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
package fr.paris.lutece.plugins.crm.business.user;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

import java.util.Map;

/**
 *
 * CRMUser
 *
 */
public class CRMUser
{
    public static final Timestamp DEFAULT_DATE_LAST_LOGIN = Timestamp.valueOf( "1980-01-01 00:00:00" );

    /**
     * Status of not activated users.
     */
    public static final int STATUS_NOT_ACTIVATED = 0;

    /**
     * Status of activated users.
     */
    public static final int STATUS_ACTIVATED = 1;

    /**
     * Status of expired users. Expired users will be anonymized.
     */
    public static final int STATUS_EXPIRED = 5;

    /**
     * Status of anonymized users.
     */
    public static final int STATUS_ANONYMIZED = 10;
    private int _nIdCRMUser;
    private String _strUserGuid;
    private Map<String, String> _userInfos;
    private int _nStatus;
    private Timestamp _dateLastLogin;
    private boolean _bMustBeUpdated;

    /**
     * Set the id crm user
     * 
     * @param nIdCRMUser
     *            the id crm user
     */
    public void setIdCRMUser( int nIdCRMUser )
    {
        _nIdCRMUser = nIdCRMUser;
    }

    /**
     * Get the id crm user
     * 
     * @return the id crm user
     */
    public int getIdCRMUser( )
    {
        return _nIdCRMUser;
    }

    /**
     * Set the user guid
     * 
     * @param strUserGuid
     *            the user guid
     */
    public void setUserGuid( String strUserGuid )
    {
        _strUserGuid = strUserGuid;
    }

    /**
     * Get the user guid
     * 
     * @return the user guid
     */
    public String getUserGuid( )
    {
        return _strUserGuid;
    }

    /**
     * Set the user attributes
     * 
     * @param userInfos
     *            the user attributes
     */
    public void setUserAttributes( Map<String, String> userInfos )
    {
        _userInfos = userInfos;
    }

    /**
     * Get the user attributes
     * 
     * @return the user attributes
     */
    public Map<String, String> getUserAttributes( )
    {
        return _userInfos;
    }

    /**
     * Get the user attribute value
     * 
     * @param strUserAttributeKey
     *            the key
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

    /**
     * Get the status of the user
     * 
     * @return The status of the user
     */
    public int getStatus( )
    {
        return _nStatus;
    }

    /**
     * Set the status of the user
     * 
     * @param nStatus
     *            The status of the user
     */
    public void setStatus( int nStatus )
    {
        _nStatus = nStatus;
    }

    /**
     * Check if the user is active
     * 
     * @return true if it is active, false otherwise
     */
    public boolean isActive( )
    {
        return ( ( _nStatus >= STATUS_ACTIVATED ) && ( _nStatus < STATUS_EXPIRED ) );
    }

    /**
     * Checks if is anonymized.
     *
     * @return true, if is anonymized
     */
    public boolean isAnonymized( )
    {
        return _nStatus == STATUS_ANONYMIZED;
    }

    /**
     * Get the last login date of the user
     * 
     * @return The last login date of the user. The last login date is null if the user never logged in before.
     */
    public Timestamp getDateLastLogin( )
    {
        return _dateLastLogin;
    }

    /**
     * Set the last login date of the user
     * 
     * @param dateLastLogin
     *            The last login date of the user, or null if the user never logged in.
     */
    public void setDateLastLogin( Timestamp dateLastLogin )
    {
        this._dateLastLogin = dateLastLogin;
    }

    /**
     * 
     * @return true if the user information must be updated
     */
    public boolean isMustBeUpdated( )
    {
        return _bMustBeUpdated;
    }

    /**
     * 
     * @param _bMustBeUpdated
     *            true if the user information must be updated
     */
    public void setMustBeUpdated( boolean _bMustBeUpdated )
    {
        this._bMustBeUpdated = _bMustBeUpdated;
    }
}
