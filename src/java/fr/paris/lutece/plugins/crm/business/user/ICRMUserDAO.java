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

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
 *
 * ICRMUserDAO
 *
 */
public interface ICRMUserDAO
{
    /**
    * Generates a new primary key
    * @param plugin The Plugin
    * @return The new primary key
    */
    int newPrimaryKey( Plugin plugin );

    /**
     * Insert a new record in the table.
     * @param user instance of the CRMUser object to insert
     * @param plugin the Plugin
     * @return the key of the newly created notification
     */
    int insert( CRMUser user, Plugin plugin );

    /**
     * Update the record in the table
     * @param user the reference of the CRMUser
     * @param plugin the Plugin
     */
    void store( CRMUser user, Plugin plugin );

    /**
     * Delete a record from the table
     * @param nIdCRMUser int identifier of the CRMUser to delete
     * @param plugin the Plugin
     */
    void delete( int nIdCRMUser, Plugin plugin );

    /**
     * Load the data from the table
     * @param nIdCRMUser The identifier of the CRMUser
     * @param plugin the Plugin
     * @return The instance of the CRMUser
     */
    CRMUser load( int nIdCRMUser, Plugin plugin );

    /**
     * Load the data from a given user guid
     * @param strUserGuid the user guid
     * @param plugin the Plugin
     * @return The instance of the CRMUser
     */
    CRMUser loadByUserGuid( String strUserGuid, Plugin plugin );

    /**
     * Find all.
     *
     * @param plugin the plugin
     * @return the list
     */
    List<CRMUser> selectAll( Plugin plugin );

    /**
     * Find list ids crm user by filter.
     *
     * @param filter the filter
     * @param plugin the plugin
     * @return the list
     */
    List<Integer> selectListIdsCRMUserByFilter( CRMUserFilter filter, Plugin plugin );

    /**
     * Select by list ids.
     *
     * @param listIdsCRMUser the list ids crm user
     * @param plugin the plugin
     * @return the list
     */
    List<CRMUser> selectByListIds( List<Integer> listIdsCRMUser, Plugin plugin );
}
