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
package fr.paris.lutece.plugins.crm.service.user;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.business.user.MokeCRMUser;

import java.util.Map.Entry;

/**
 *
 * MokeCRMUserService
 *
 */
public class MokeCRMUserService extends CRMUserService
{
    private static final int ID_CRM_USER1 = 1;

    /**
     * Find by primary key
     * 
     * @param nIdCRMUser
     *            the id crm user
     * @return a {@link CRMUser}
     */
    public CRMUser findByPrimaryKey( int nIdCRMUser )
    {
        CRMUser crmUser = new MokeCRMUser( nIdCRMUser );
        System.out.println( trace( crmUser ) );

        return crmUser;
    }

    /**
     * Find from a given user guid
     * 
     * @param strUserGuid
     *            the user guid
     * @return a {@link CRMUser}
     */
    public CRMUser findByUserGuid( String strUserGuid )
    {
        CRMUser crmUser = new MokeCRMUser( ID_CRM_USER1 );
        System.out.println( trace( crmUser ) );

        return crmUser;
    }

    /**
     * Create a new {@link CRMUser}
     * 
     * @param user
     *            the {@link CRMUser}
     * @return the new primary key
     */
    public int create( CRMUser user )
    {
        System.out.println( trace( user ) );

        return ID_CRM_USER1;
    }

    /**
     * Update a {@link CRMUser}
     * 
     * @param user
     *            the {@link CRMUser}
     */
    public void update( CRMUser user )
    {
        System.out.println( trace( user ) );
    }

    /**
     * Remove a CRMUser
     * 
     * @param nIdCRMUser
     *            the id crm user
     */
    public void remove( int nIdCRMUser )
    {
        System.out.println( trace( ) );
    }

    /**
     * Trace
     * 
     * @return trace
     */
    private String trace( )
    {
        return trace( null );
    }

    /**
     * Trace
     * 
     * @param crmUser
     *            the crm user
     * @return trace
     */
    private String trace( CRMUser crmUser )
    {
        StringBuilder sbTrace = new StringBuilder( );
        sbTrace.append( "\n ---------------------- CRM User Service -------------------" );
        sbTrace.append( "\nMethod name : " + Thread.currentThread( ).getStackTrace( ) [2].getMethodName( ) );

        if ( crmUser != null )
        {
            sbTrace.append( "\nid_crm_user : " + crmUser.getIdCRMUser( ) );
            sbTrace.append( "\nuser_guid : " + crmUser.getUserGuid( ) );

            for ( Entry<String, String> userAttribute : crmUser.getUserAttributes( ).entrySet( ) )
            {
                sbTrace.append( "\n" + userAttribute.getKey( ) + " : " + userAttribute.getValue( ) );
            }
        }

        sbTrace.append( "\n --------------------------------------------------------------------" );

        return sbTrace.toString( );
    }
}
