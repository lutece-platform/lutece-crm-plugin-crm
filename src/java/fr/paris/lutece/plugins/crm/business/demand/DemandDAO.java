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

import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 *
 * DemandDAO
 *
 */
public class DemandDAO implements IDemandDAO
{
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_demand ) FROM crm_demand ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_demand (id_demand, id_demand_type, id_crm_user, status_text, id_status_crm, data, date_modification ) VALUES (?,?,?,?,?,?,?) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_demand, id_demand_type, id_crm_user, status_text, id_status_crm, data, date_modification FROM crm_demand WHERE id_demand = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE crm_demand SET id_demand_type = ?, id_crm_user = ?, status_text = ?, id_status_crm = ?, data = ?, date_modification = ? WHERE id_demand = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_demand WHERE id_demand = ? ";
    private static final String SQL_QUERY_SELECT_ALL = " SELECT id_demand, id_demand_type, id_crm_user, status_text, id_status_crm, data, date_modification, (SELECT count(*) FROM crm_notification WHERE is_read = 0 AND id_demand = demand.id_demand) AS nb_unread_notif FROM crm_demand demand ";
    private static final String SQL_QUERY_SELECT_ALL_WITH_NOTIFICATION = " SELECT demand.id_demand, id_demand_type, id_crm_user, status_text, id_status_crm, data, date_modification, (SELECT count(*) FROM crm_notification WHERE is_read = 0 AND id_demand = demand.id_demand) AS nb_unread_notif FROM crm_demand AS demand ";
    private static final String SQL_QUERY_COUNT = " SELECT count(*) FROM ";

    // FILTERS
    private static final String SQL_ORDER_BY = " ORDER BY ";
    private static final String SQL_DESC = " DESC ";
    private static final String SQL_ASC = " ASC ";
    private static final String SQL_OR = " OR ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_DATE_MODIFICATION = " date_modification ";
    private static final String SQL_FILTER_ID_CRM_USER = " id_crm_user = ? ";
    private static final String SQL_FILTER_ID_DEMAND_TYPE = " id_demand_type = ? ";
    private static final String SQL_FILTER_DATE_MODIFICATION = " date_modification LIKE '";
    private static final String SQL_FILTER_ID_STATUS_CRM = " id_status_crm = ? ";
    private static final String SQL_NB_UNREAD_NOTIFICATION = " nb_unread_notif ";

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
    public synchronized int insert( Demand demand, Plugin plugin )
    {
        int nIdDemand = -1;

        if ( demand != null )
        {
            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

            int nIndex = 1;
            demand.setIdDemand( newPrimaryKey( plugin ) );
            daoUtil.setInt( nIndex++, demand.getIdDemand(  ) );
            daoUtil.setInt( nIndex++, demand.getIdDemandType(  ) );
            daoUtil.setInt( nIndex++, demand.getIdCRMUser(  ) );
            daoUtil.setString( nIndex++, demand.getStatusText(  ) );
            daoUtil.setInt( nIndex++, demand.getIdStatusCRM(  ) );
            daoUtil.setString( nIndex++, demand.getData(  ) );
            daoUtil.setTimestamp( nIndex++, demand.getDateModification(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );

            nIdDemand = demand.getIdDemand(  );
        }

        return nIdDemand;
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
            demand.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setData( daoUtil.getString( nIndex++ ) );
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
            daoUtil.setInt( nIndex++, demand.getIdCRMUser(  ) );
            daoUtil.setString( nIndex++, demand.getStatusText(  ) );
            daoUtil.setInt( nIndex++, demand.getIdStatusCRM(  ) );
            daoUtil.setString( nIndex++, demand.getData(  ) );
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
            demand.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setData( daoUtil.getString( nIndex++ ) );
            demand.setDateModification( daoUtil.getTimestamp( nIndex++ ) );

            listDemands.add( demand );
        }

        daoUtil.free(  );

        return listDemands;
    }

    /**
     * {@inheritDoc}
     */
    public List<Demand> selectByFilter( DemandFilter dFilter, IPaginationProperties paginationProperties, Plugin plugin )
    {
        List<Demand> listDemands = new ArrayList<Demand>(  );
        StringBuilder sbSQL = new StringBuilder( buildSQLQuery( dFilter ) );

        if ( paginationProperties != null )
        {
            sbSQL.append( " LIMIT " + paginationProperties.getItemsPerPage(  ) );
            sbSQL.append( " OFFSET " +
                ( ( paginationProperties.getPageIndex(  ) - 1 ) * paginationProperties.getItemsPerPage(  ) ) );
        }

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        setFilterValues( dFilter, daoUtil );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            Demand demand = new Demand(  );
            demand.setIdDemand( daoUtil.getInt( nIndex++ ) );
            demand.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demand.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            demand.setStatusText( daoUtil.getString( nIndex++ ) );
            demand.setIdStatusCRM( daoUtil.getInt( nIndex++ ) );
            demand.setData( daoUtil.getString( nIndex++ ) );
            demand.setDateModification( daoUtil.getTimestamp( nIndex++ ) );

            listDemands.add( demand );
        }

        daoUtil.free(  );

        return listDemands;
    }

    /**
     * Build the SQL query with filter
     * @param dFilter the filter
     * @return a SQL query
     */
    private String buildSQLQuery( DemandFilter dFilter )
    {
        StringBuilder sbSQL = new StringBuilder(  );

        if ( StringUtils.isNotBlank( dFilter.getNotification(  ) ) )
        {
            sbSQL.append( SQL_QUERY_SELECT_ALL_WITH_NOTIFICATION );
        }
        else
        {
            sbSQL.append( SQL_QUERY_SELECT_ALL );
        }

        int nIndex = 1;

        if ( dFilter.containsIdCRMUser(  ) )
        {
            nIndex = addSQLWhereOr( dFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_ID_CRM_USER );
        }

        if ( dFilter.containsIdDemandType(  ) )
        {
            nIndex = addSQLWhereOr( dFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_ID_DEMAND_TYPE );
        }

        if ( dFilter.containsDateModification(  ) )
        {
            nIndex = addSQLWhereOr( dFilter.getIsWideSearch(  ), sbSQL, nIndex );

            SimpleDateFormat sdfSQL = new SimpleDateFormat( "yyyy-MM-dd" );
            String strDateModification = sdfSQL.format( dFilter.getDateModification(  ) );

            sbSQL.append( SQL_FILTER_DATE_MODIFICATION + strDateModification + "%'" );
        }

        if ( dFilter.containsIdStatusCRM(  ) )
        {
            nIndex = addSQLWhereOr( dFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_ID_STATUS_CRM );
        }

        if ( StringUtils.isNotBlank( dFilter.getNotification(  ) ) )
        {
            nIndex = addSQLWhereOr( dFilter.getIsWideSearch(  ), sbSQL, nIndex );

            StringBuilder strFilterNotification = new StringBuilder(  );
            strFilterNotification
                    .append( " EXISTS (SELECT id_notification FROM crm_notification notif WHERE (notif.object LIKE '%" );
            strFilterNotification.append( dFilter.getNotification(  ) );
            strFilterNotification.append( "%' OR notif.message LIKE '%" );
            strFilterNotification.append( dFilter.getNotification(  ) );
            strFilterNotification.append( "%') AND notif.id_demand = demand.id_demand )" );

            sbSQL.append( strFilterNotification.toString(  ) );
        }

        // order by
        sbSQL.append( SQL_ORDER_BY );

        List<DemandSort> listDemandSort = dFilter.getListDemandSort(  );

        if ( ( listDemandSort == null ) || listDemandSort.isEmpty(  ) )
        {
            // default order
            sbSQL.append( SQL_DATE_MODIFICATION );
            sbSQL.append( SQL_DESC );
        }
        else
        {
            int nSize = listDemandSort.size(  );

            for ( int i = 0; i < nSize; i++ )
            {
                if ( i != 0 )
                {
                    sbSQL.append( " , " );
                }

                DemandSort demandSort = listDemandSort.get( i );

                if ( CRMConstants.SORT_DATE_MODIFICATION.equals( demandSort.getField(  ) ) )
                {
                    sbSQL.append( SQL_DATE_MODIFICATION );
                }
                else if ( CRMConstants.SORT_NB_UNREAD_NOTIFICATION.equals( demandSort.getField(  ) ) )
                {
                    sbSQL.append( SQL_NB_UNREAD_NOTIFICATION );
                }

                if ( demandSort.isAsc(  ) )
                {
                    sbSQL.append( SQL_ASC );
                }
                else
                {
                    sbSQL.append( SQL_DESC );
                }
            }
        }

        return sbSQL.toString(  );
    }

    /**
     * Add a <b>WHERE</b> or a <b>OR</b> depending of the index.
     * <br/>
     * <ul>
     * <li>if <code>nIndex</code> == 1, then we add a <b>WHERE</b></li>
     * <li>if <code>nIndex</code> != 1, then we add a <b>OR</b> or a <b>AND</b> depending of the wide search characteristic</li>
     * </ul>
     * @param bIsWideSearch true if it is a wide search, false otherwise
     * @param sbSQL the SQL query
     * @param nIndex the index
     * @return the new index
     */
    private int addSQLWhereOr( boolean bIsWideSearch, StringBuilder sbSQL, int nIndex )
    {
        if ( nIndex == 1 )
        {
            sbSQL.append( SQL_WHERE );
        }
        else
        {
            sbSQL.append( bIsWideSearch ? SQL_OR : SQL_AND );
        }

        return nIndex + 1;
    }

    /**
     * Set the filter values on the DAOUtil
     * @param dFilter the filter
     * @param daoUtil the DAOUtil
     */
    private void setFilterValues( DemandFilter dFilter, DAOUtil daoUtil )
    {
        int nIndex = 1;

        if ( dFilter.containsIdCRMUser(  ) )
        {
            daoUtil.setInt( nIndex++, dFilter.getIdCRMUser(  ) );
        }

        if ( dFilter.containsIdDemandType(  ) )
        {
            daoUtil.setInt( nIndex++, dFilter.getIdDemandType(  ) );
        }

        //        if ( dFilter.containsDateModification(  ) )
        //        {
        //            String strDateModification = dFilter.getDateModification( ).toString( );
        //
        //
        //            daoUtil.setString( nIndex++, strDateModification.toString( ) );
        //        }
        if ( dFilter.containsIdStatusCRM(  ) )
        {
            daoUtil.setInt( nIndex++, dFilter.getIdStatusCRM(  ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    public int countByFilter( DemandFilter dFilter, Plugin plugin )
    {
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_COUNT );
        sbSQL.append( " ( " );
        sbSQL.append( buildSQLQuery( dFilter ) );
        sbSQL.append( " ) AS results" );

        int nTotalResult = 0;

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        setFilterValues( dFilter, daoUtil );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            nTotalResult = daoUtil.getInt( 1 );
        }

        daoUtil.free(  );

        return nTotalResult;
    }
}
