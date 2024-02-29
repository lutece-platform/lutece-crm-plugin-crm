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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;

/**
 *
 * IDemandDAO
 *
 */
public interface IDemandDAO
{
    /**
     * Generate a new primary key
     * 
     * @param plugin
     *            the {@link Plugin}
     * @return a new primary key
     */
    int newPrimaryKey( Plugin plugin );

    /**
     * Insert a new record in the table.
     * 
     * @param demand
     *            instance
     * @param plugin
     *            the Plugin
     * @return the newly created demand id
     */
    int insert( Demand demand, Plugin plugin );

    /**
     * Update the record in the table
     * 
     * @param demand
     *            the reference of the object
     * @param plugin
     *            the Plugin
     */
    void store( Demand demand, Plugin plugin );

    /**
     * Delete a record from the table
     * 
     * @param nIdDemand
     *            int identifier of the demand to delete
     * @param plugin
     *            the Plugin
     */
    void delete( int nIdDemand, Plugin plugin );

    /**
     * Load the data from the table
     * 
     * @param nIdDemand
     *            The identifier of the demand
     * @param plugin
     *            the Plugin
     * @return The instance of the object
     */
    Demand load( int nIdDemand, Plugin plugin );

    /**
     * Find all demands
     * 
     * @param plugin
     *            {@link Plugin}
     * @return a list of {@link Demand}
     */
    List<Demand> selectAll( Plugin plugin );

    /**
     * Find by filter
     * 
     * @param dFilter
     *            the filter
     * @param paginationProperties
     *            the pagination properties (can be null)
     * @param plugin
     *            {@link Plugin}
     * @return a list of {@link Demand}
     */
    List<Demand> selectByFilter( DemandFilter dFilter, IPaginationProperties paginationProperties, Plugin plugin );

    /**
     * Count results by filter
     * 
     * @param dFilter
     *            the filter
     * @param plugin
     *            {@link Plugin}
     * @return the number of result
     */
    int countByFilter( DemandFilter dFilter, Plugin plugin );

    /**
     * Load demand by Remote Id and id demand
     * 
     * @param strRemoteId
     *            the remote Id
     * @param nIdDemandType
     *            the Id demand type
     * @param plugin
     *            the plugin
     * @return The instance of the object
     */
    Demand loadByRemoteKey( String strRemoteId, int nIdDemandType, Plugin plugin );

    /**
     * {@inheritDoc}
     */
    List<Demand> selectByCategoryCode( String strCategoryCode, Plugin plugin );

}
