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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * DemandDAO
 *
 */
public class DemandDAO implements IDemandDAO
{
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_demand ) FROM crm_demand ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_demand (id_demand, id_demand_type, user_guid, status_text, id_status_crm, config_data, date_modification ) VALUES (?,?,?,?,?,?,?) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_demand, id_demand_type, user_guid, status_text, id_status_crm, config_data, date_modification FROM crm_demand WHERE id_demand = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE crm_demand SET id_demand_type = ?, user_guid = ?, status_text = ?, id_status_crm = ?, config_data = ?, date_modification = ? WHERE id_demand = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_demand WHERE id_demand = ? ";
    private static final String SQL_QUERY_SELECT_ALL = " SELECT id_demand, id_demand_type, user_guid, status_text, id_status_crm, config_data, date_modification FROM crm_demand ORDER BY id_demand ASC ";
    private static final String SQL_QUERY_DELETE_FROM_ID_DEMAND_TYPE = " DELETE FROM crm_demand WHERE id_demand_type = ? ";
    private static final String SQL_QUERY_SELECT_BY_USER_GUID = " SELECT id_demand, id_demand_type, user_guid, status_text, id_status_crm, config_data, date_modification FROM crm_demand WHERE user_guid = ? ORDER BY id_status_crm DESC, date_modification DESC ";

    /**
     * {@inheritDoc}
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey = 1;

        if ( daoUtil.next(  ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc}
     */
    public void insert( Demand demand, Plugin plugin )
    {
        if ( demand != null )
        {
            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

            int nIndex = 1;

            daoUtil.setInt( nIndex++, demand.getIdDemand(  ) );
            daoUtil.setInt( nIndex++, demand.getIdDemandType(  ) );
            daoUtil.setString( nIndex++, demand.getUserGuid(  ) );
            daoUtil.setString( nIndex++, demand.getStatusText(  ) );
            daoUtil.setInt( nIndex++, demand.getIdStatusCRM(  ) );
            daoUtil.setString( nIndex++, demand.getConfigData(  ) );
            daoUtil.setTimestamp( nIndex++, demand.getDateModification(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );
        }
    }

    /**
     * {@inheritDoc}
     */
    public Demand load( int nIdDemand, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nIdDemand );
        daoUtil.executeQuery(  );

        Demand demand = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            demand = new Demand(  );
            demand.setIdDemand( daoUtil.getInt( nIndex++ ) );
            demand.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demand.setUserGuid( daoUtil.getString( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setConfigData( daoUtil.getString( nIndex++ ) );
            demand.setDateModification( daoUtil.getTimestamp( nIndex++ ) );
        }

        daoUtil.free(  );

        return demand;
    }

    /**
     * {@inheritDoc}
     */
    public void store( Demand demand, Plugin plugin )
    {
        if ( demand != null )
        {
            int nIndex = 1;

            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

            daoUtil.setInt( nIndex++, demand.getIdDemandType(  ) );
            daoUtil.setString( nIndex++, demand.getUserGuid(  ) );
            daoUtil.setString( nIndex++, demand.getStatusText(  ) );
            daoUtil.setInt( nIndex++, demand.getIdStatusCRM(  ) );
            daoUtil.setString( nIndex++, demand.getConfigData(  ) );
            daoUtil.setTimestamp( nIndex++, demand.getDateModification(  ) );

            daoUtil.setInt( nIndex++, demand.getIdDemand(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete( int nIdDemand, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nIdDemand );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public List<Demand> selectAll( Plugin plugin )
    {
        List<Demand> listDemands = new ArrayList<Demand>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            Demand demand = new Demand(  );
            demand.setIdDemand( daoUtil.getInt( nIndex++ ) );
            demand.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demand.setUserGuid( daoUtil.getString( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setConfigData( daoUtil.getString( nIndex++ ) );
            demand.setDateModification( daoUtil.getTimestamp( nIndex++ ) );

            listDemands.add( demand );
        }

        daoUtil.free(  );

        return listDemands;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteByIdDemandType( int nIdDemandType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_FROM_ID_DEMAND_TYPE, plugin );
        daoUtil.setInt( 1, nIdDemandType );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public List<Demand> selectByUserGuid( String strUserGuid, Plugin plugin )
    {
        List<Demand> listDemands = new ArrayList<Demand>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_USER_GUID, plugin );
        daoUtil.setString( 1, strUserGuid );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            Demand demand = new Demand(  );
            demand.setIdDemand( daoUtil.getInt( nIndex++ ) );
            demand.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demand.setUserGuid( daoUtil.getString( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setConfigData( daoUtil.getString( nIndex++ ) );
            demand.setDateModification( daoUtil.getTimestamp( nIndex++ ) );

            listDemands.add( demand );
        }

        daoUtil.free(  );

        return listDemands;
    }
}
