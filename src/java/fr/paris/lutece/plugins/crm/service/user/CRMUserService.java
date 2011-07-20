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
import fr.paris.lutece.plugins.crm.business.user.CRMUserHome;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;


/**
 *
 * CRMUserService
 *
 */
public final class CRMUserService
{
    private static final String BEAN_CRM_CRMUSERSERVICE = "crm.crmUserService";

    /**
     * Private constructor
     */
    private CRMUserService(  )
    {
    }

    /**
     * Get the instance of CRMUserService
     * @return the instance of CRMUserService
     */
    public static CRMUserService getService(  )
    {
        return (CRMUserService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME, BEAN_CRM_CRMUSERSERVICE );
    }

    /**
     * Find by primary key
     * @param nIdCRMUser the id crm user
     * @return a {@link CRMUser}
     */
    public CRMUser findByPrimaryKey( int nIdCRMUser )
    {
        return CRMUserHome.findByPrimaryKey( nIdCRMUser );
    }

    /**
     * Find from a given user guid
     * @param strUserGuid the user guid
     * @return a {@link CRMUser}
     */
    public CRMUser findByUserGuid( String strUserGuid )
    {
        return CRMUserHome.findByUserGuid( strUserGuid );
    }

    /**
     * Create a new {@link CRMUser}
     * @param user the {@link CRMUser}
     * @return the new primary key
     */
    public int create( CRMUser user )
    {
        int nIdCRMUser = -1;

        if ( user != null )
        {
            nIdCRMUser = CRMUserHome.create( user );
        }

        return nIdCRMUser;
    }

    /**
     * Update a {@link CRMUser}
     * @param user the {@link CRMUser}
     */
    public void update( CRMUser user )
    {
        if ( user != null )
        {
            CRMUserHome.update( user );
        }
    }

    /**
     * Remove a CRMUser
     * @param nIdCRMUser the id crm user
     */
    public void remove( int nIdCRMUser )
    {
        CRMUserHome.remove( nIdCRMUser );
    }
}
