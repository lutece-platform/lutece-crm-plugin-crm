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
package fr.paris.lutece.plugins.crm.business.user;


/**
 *
 * CRMUser
 *
 */
public class CRMUser
{
    private int _nIdCRMUser;
    private String _strUserGuid;
    private String _strFirstName;
    private String _strLastName;
    private String _strEmail;
    private String _strPhoneNumber;

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
     * Set the first name
     * @param strFirstName the first name
     */
    public void setFirstName( String strFirstName )
    {
        _strFirstName = strFirstName;
    }

    /**
     * Get the first name
     * @return the first name
     */
    public String getFirstName(  )
    {
        return _strFirstName;
    }

    /**
     * Set the last name
     * @param strLastName the last name
     */
    public void setLastName( String strLastName )
    {
        _strLastName = strLastName;
    }

    /**
     * Get the last name
     * @return the last name
     */
    public String getLastName(  )
    {
        return _strLastName;
    }

    /**
     * Set the email
     * @param strEmail the email
     */
    public void setEmail( String strEmail )
    {
        _strEmail = strEmail;
    }

    /**
     * Get the email
     * @return the email
     */
    public String getEmail(  )
    {
        return _strEmail;
    }

    /**
     * Se the phone number
     * @param strPhoneNumber the phone number
     */
    public void setPhoneNumber( String strPhoneNumber )
    {
        _strPhoneNumber = strPhoneNumber;
    }

    /**
     * Get the phone number
     * @return the phone number
     */
    public String getPhoneNumber(  )
    {
        return _strPhoneNumber;
    }
}
