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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;


/**
 *
 * DemandTypeHome
 *
 */
public final class DemandTypeHome
{
    private static final String BEAN_CRM_DEMANDTYPEDAO = "crm.demandTypeDAO";
    private static Plugin _plugin = PluginService.getPlugin( CRMPlugin.PLUGIN_NAME );
    private static IDemandTypeDAO _dao = (IDemandTypeDAO) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME,
            BEAN_CRM_DEMANDTYPEDAO );

    /**
     * Private constructor - this class need not be instantiated
     */
    private DemandTypeHome(  )
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
     * @param demandType instance of the DemandType object to insert
     * @return the key of the newly created demandType
     */
    public static int create( DemandType demandType )
    {
        return _dao.insert( demandType, _plugin );
    }

    /**
     * Update the record in the table
     * @param demandType the reference of the demandType
     */
    public static void update( DemandType demandType )
    {
        _dao.store( demandType, _plugin );
    }

    /**
     * Delete a record from the table
     * @param nIdDemandType int identifier of the demandType to delete
     */
    public static void remove( int nIdDemandType )
    {
        _dao.delete( nIdDemandType, _plugin );
    }

    /**
     * Load the data from the table
     * @param nIdDemandType The identifier of the demandType
     * @return The instance of the demandType
     */
    public static DemandType findByPrimaryKey( int nIdDemandType )
    {
        return _dao.load( nIdDemandType, _plugin );
    }

    /**
     * Find the demandType by its order
     * @param nOrder the order
     * @return a {@link DemandType}
     */
    public static DemandType findByOrder( int nOrder )
    {
        return _dao.selectByOrder( nOrder, _plugin );
    }

    /**
     * Find all demandTypes
     * @return a list of {@link DemandType}
     */
    public static List<DemandType> findAll(  )
    {
        return _dao.selectAll( _plugin );
    }

    /**
     * Find the list of demandTypes by id category and date
     * @param nIdCategory the ID category
     * @param dateToday the date of today
     * @return a list of {@link DemandType}
     */
    public static List<DemandType> findByIdCategoryAndDate( int nIdCategory, java.util.Date dateToday )
    {
        return _dao.selectByIdCategoryAndDate( nIdCategory, dateToday, _plugin );
    }

    /**
     * Find all demandTypes as a {@link ReferenceList}
     * @return a {@link ReferenceList}
     */
    public static ReferenceList findDemandTypes(  )
    {
        ReferenceList list = new ReferenceList(  );

        for ( DemandType demandType : _dao.selectAll( _plugin ) )
        {
            list.addItem( demandType.getIdDemandType(  ), demandType.getLabel(  ) );
        }

        return list;
    }

    /**
     * Find the max order
     * @return the max order
     */
    public static int findMaxOrder(  )
    {
        return _dao.selectMaxOrder( _plugin );
    }

    /**
     * Find by filter
     * @param dtFilter the filter
     * @return a list of {@link DemandType}
     */
    public static List<DemandType> findByFilter( DemandTypeFilter dtFilter )
    {
        return _dao.selectDemandTypesByFilter( dtFilter, _plugin );
    }

    /**
     * Find demand types that have not a date end
     * @return a list of {@link DemandType}
     */
    public static List<DemandType> findNoDateEndDemandTypes(  )
    {
        return _dao.selectNoDateEndDemandTypes( _plugin );
    }
}
