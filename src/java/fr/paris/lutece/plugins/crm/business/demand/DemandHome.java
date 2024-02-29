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

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;

/**
 *
 * DemandHome
 *
 */
public final class DemandHome
{
    private static final String BEAN_CRM_DEMANDDAO = "crm.demandDAO";
    private static Plugin _plugin = PluginService.getPlugin( CRMPlugin.PLUGIN_NAME );
    private static IDemandDAO _dao = SpringContextService.getBean( BEAN_CRM_DEMANDDAO );

    /**
     * Private constructor - this class need not be instantiated
     */
    private DemandHome( )
    {
    }

    /**
     * Insert a new record in the table.
     * 
     * @param demand
     *            instance of the Demand object to insert
     * @return the newly created demand id
     */
    public static int create( Demand demand )
    {
        return _dao.insert( demand, _plugin );
    }

    /**
     * Update the record in the table
     * 
     * @param demand
     *            the reference of the Demand
     */
    public static void update( Demand demand )
    {
        _dao.store( demand, _plugin );
    }

    /**
     * Delete a record from the table
     * 
     * @param nIdDemand
     *            int identifier of the demand to delete
     */
    public static void remove( int nIdDemand )
    {
        _dao.delete( nIdDemand, _plugin );
    }

    /**
     * Load the data from the table
     * 
     * @param nIdDemand
     *            The identifier of the demand
     * @return The instance of the Demand
     */
    public static Demand findByPrimaryKey( int nIdDemand )
    {
        return _dao.load( nIdDemand, _plugin );
    }

    /**
     * Load the data from the table
     * 
     * @param nIdDemand
     *            The identifier of the demand
     * @return The instance of the Demand
     */
    public static Demand findByRemoteKey( String strRemoteId, int nIdDemandType )
    {

        return _dao.loadByRemoteKey( strRemoteId, nIdDemandType, _plugin );
    }

    /**
     * Find all demands
     * 
     * @return a list of {@link Demand}
     */
    public static List<Demand> findAll( )
    {
        return _dao.selectAll( _plugin );
    }

    /**
     * Find by filter
     * 
     * @param dFilter
     *            the filter
     * @return a list of {@link Demand}
     */
    public static List<Demand> findByFilter( DemandFilter dFilter )
    {
        return _dao.selectByFilter( dFilter, null, _plugin );
    }

    /**
     * Find by filter with pagination properties
     * 
     * @param dFilter
     *            the filter
     * @param paginationProperties
     *            the pagination properties
     * @return a list of {@link Demand}
     */
    public static List<Demand> findByFilter( DemandFilter dFilter, IPaginationProperties paginationProperties )
    {
        return _dao.selectByFilter( dFilter, paginationProperties, _plugin );
    }

    /**
     * Count results by filter
     * 
     * @param dFilter
     *            the filter
     * @return the number of results
     */
    public static int countByFilter( DemandFilter dFilter )
    {
        return _dao.countByFilter( dFilter, _plugin );
    }
    
    /**
     * Find by category code
     * 
     * @param categoryCode
     *            the category Code
     * @return a list of {@link Demand}
     */
    public static List<Demand> findByCategoryCode( String categoryCode )
    {
        return _dao.selectByCategoryCode( categoryCode, _plugin );
    }
}
