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

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 *
 * CRMUserHome
 *
 */
public final class CRMUserHome
{
    private static final String BEAN_CRM_CRMUSERDAO = "crm.crmUserDAO";
    private static Plugin _plugin = PluginService.getPlugin( CRMPlugin.PLUGIN_NAME );
    private static ICRMUserDAO _dao = SpringContextService.getBean( BEAN_CRM_CRMUSERDAO );

    /**
     * Private constructor - this class need not be instantiated
     */
    private CRMUserHome(  )
    {
    }

    /**
     * Generates a new primary key
     * @return The new primary key
     */
    public static int newPrimaryKey(  )
    {
        return _dao.newPrimaryKey( _plugin );
    }

    /**
     * Insert a new record in the table.
     * @param user instance of the CRMUser object to insert
     * @return the key of the newly created notification
     */
    public static int create( CRMUser user )
    {
        return _dao.insert( user, _plugin );
    }

    /**
     * Update the record in the table
     * @param user the reference of the CRMUser
     */
    public static void update( CRMUser user )
    {
        _dao.store( user, _plugin );
    }

    /**
     * Delete a record from the table
     * @param nIdCRMUser int identifier of the CRMUser to delete
     */
    public static void remove( int nIdCRMUser )
    {
        _dao.delete( nIdCRMUser, _plugin );
    }

    /**
     * Load the data from the table
     * @param nIdCRMUser The identifier of the CRMUser
     * @return The instance of the CRMUser
     */
    public static CRMUser findByPrimaryKey( int nIdCRMUser )
    {
        return _dao.load( nIdCRMUser, _plugin );
    }

    /**
     * Load the data from a given user guid
     * @param strUserGuid The user guid
     * @return The instance of the CRMUser
     */
    public static CRMUser findByUserGuid( String strUserGuid )
    {
        return _dao.loadByUserGuid( strUserGuid, _plugin );
    }

    /**
     * Load the data from the table.
     *
     * @return The instance of the CRMUser
     */
    public static List<CRMUser> findAll(  )
    {
        return _dao.selectAll( _plugin );
    }

    /**
     * Find list ids crm user by filter.
     *
     * @param filter the filter
     * @return the list
     */
    public static List<Integer> findListIdsCRMUserByFilter( CRMUserFilter filter )
    {
        return _dao.selectListIdsCRMUserByFilter( filter, _plugin );
    }

    /**
     * Find by list ids.
     *
     * @param listIdsCRMUser the list ids crm user
     * @return the list
     */
    public static List<CRMUser> findByListIds( List<Integer> listIdsCRMUser )
    {
        return _dao.selectByListIds( listIdsCRMUser, _plugin );
    }
}
