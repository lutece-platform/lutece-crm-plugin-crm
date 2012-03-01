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

import fr.paris.lutece.plugins.crm.util.TargetEnum;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * DemandTypeDAO
 *
 */
public class DemandTypeDAO implements IDemandTypeDAO
{
    // SQL QUERIES
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_demand_type ) FROM crm_demand_type ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_demand_type (id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target FROM crm_demand_type WHERE id_demand_type = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE crm_demand_type SET label = ?, url_resource = ?, url_info = ?, url_contact = ?, demand_type_order = ?, id_category = ?, date_begin = ?, date_end = ?, workgroup_key = ?, role_key = ?, target = ? WHERE id_demand_type = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_demand_type WHERE id_demand_type = ? ";
    private static final String SQL_QUERY_SELECT_ALL = " SELECT id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target FROM crm_demand_type ";
    private static final String SQL_QUERY_SELECT_MAX_ORDER = " SELECT max( demand_type_order ) FROM crm_demand_type ";
    private static final String SQL_QUERY_SELECT_BY_DEMAND_TYPE_ORDER = " SELECT id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target FROM crm_demand_type WHERE demand_type_order = ? ";
    private static final String SQL_QUERY_SELECT_BY_ID_CATEGORY_AND_DATE = " SELECT id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target " +
        " FROM crm_demand_type WHERE id_category = ? AND ( date_begin IS NULL OR date_begin <= ? ) AND ( date_end IS NULL OR date_end > ? ) ";
    private static final String SQL_QUERY_SELECT_NO_DATE_END_DEMAND_TYPES = " SELECT id_demand_type, label, url_resource, url_info, url_contact, demand_type_order, id_category, date_begin, date_end, workgroup_key, role_key, target FROM crm_demand_type WHERE date_end IS NULL ";

    // FILTERS
    private static final String SQL_ORDER_BY = " ORDER BY ";
    private static final String SQL_ASC = " ASC ";
    private static final String SQL_OR = " OR ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_FILTER_DEMAND_TYPE_ORDER = " demand_type_order ";
    private static final String SQL_FILTER_LABEL = " label LIKE ? ";
    private static final String SQL_FILTER_URL_RESOURCE = " url_resource LIKE ? ";
    private static final String SQL_FILTER_ID_CATEGORY = " id_category = ? ";
    private static final String SQL_FILTER_DATE_BEGIN = " date_begin ";
    private static final String SQL_FILTER_DATE_END = " date_end ";
    private static final String SQL_FILTER_WORKGROUP_KEY = " workgroup_key = ? ";
    private static final String SQL_FILTER_ROLE_KEY = " role_key = ? ";
    private static final String PERCENT = "%";
    private static final String QUESTION_MARK = " ? ";

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
    public synchronized int insert( DemandType demandType, Plugin plugin )
    {
        int nKey = -1;

        if ( demandType != null )
        {
            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

            int nIndex = 1;
            demandType.setIdDemandType( newPrimaryKey( plugin ) );

            daoUtil.setInt( nIndex++, demandType.getIdDemandType(  ) );
            daoUtil.setString( nIndex++, demandType.getLabel(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlResource(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlInfo(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlContact(  ) );
            daoUtil.setInt( nIndex++, demandType.getOrder(  ) );
            daoUtil.setInt( nIndex++, demandType.getIdCategory(  ) );

            Date dateBegin = null;

            if ( demandType.getDateBegin(  ) != null )
            {
                dateBegin = new Date( demandType.getDateBegin(  ).getTime(  ) );
            }

            Date dateEnd = null;

            if ( demandType.getDateEnd(  ) != null )
            {
                dateEnd = new Date( demandType.getDateEnd(  ).getTime(  ) );
            }

            daoUtil.setDate( nIndex++, dateBegin );
            daoUtil.setDate( nIndex++, dateEnd );
            daoUtil.setString( nIndex++, demandType.getWorkgroup(  ) );
            daoUtil.setString( nIndex++, demandType.getRole(  ) );
            daoUtil.setInt( nIndex++, demandType.getTarget(  ).getId(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );

            nKey = demandType.getIdDemandType(  );
        }

        return nKey;
    }

    /**
     * {@inheritDoc}
     */
    public DemandType load( int nIdDemandType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nIdDemandType );
        daoUtil.executeQuery(  );

        DemandType demandType = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );
        }

        daoUtil.free(  );

        return demandType;
    }

    /**
     * {@inheritDoc}
     */
    public void store( DemandType demandType, Plugin plugin )
    {
        if ( demandType != null )
        {
            int nIndex = 1;

            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

            daoUtil.setString( nIndex++, demandType.getLabel(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlResource(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlInfo(  ) );
            daoUtil.setString( nIndex++, demandType.getUrlContact(  ) );
            daoUtil.setInt( nIndex++, demandType.getOrder(  ) );
            daoUtil.setInt( nIndex++, demandType.getIdCategory(  ) );

            Date dateBegin = null;

            if ( demandType.getDateBegin(  ) != null )
            {
                dateBegin = new Date( demandType.getDateBegin(  ).getTime(  ) );
            }

            Date dateEnd = null;

            if ( demandType.getDateEnd(  ) != null )
            {
                dateEnd = new Date( demandType.getDateEnd(  ).getTime(  ) );
            }

            daoUtil.setDate( nIndex++, dateBegin );
            daoUtil.setDate( nIndex++, dateEnd );
            daoUtil.setString( nIndex++, demandType.getWorkgroup(  ) );
            daoUtil.setString( nIndex++, demandType.getRole(  ) );
            daoUtil.setInt( nIndex++, demandType.getTarget(  ).getId(  ) );

            daoUtil.setInt( nIndex++, demandType.getIdDemandType(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete( int nIdDemandType, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nIdDemandType );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public List<DemandType> selectAll( Plugin plugin )
    {
        List<DemandType> listDemandTypes = new ArrayList<DemandType>(  );
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_ALL );
        sbSQL.append( SQL_ORDER_BY );
        sbSQL.append( SQL_FILTER_DEMAND_TYPE_ORDER );
        sbSQL.append( SQL_ASC );

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            DemandType demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );

            listDemandTypes.add( demandType );
        }

        daoUtil.free(  );

        return listDemandTypes;
    }

    /**
     * {@inheritDoc}
     */
    public List<DemandType> selectByIdCategoryAndDate( int nIdCategory, java.util.Date dateToday, Plugin plugin )
    {
        List<DemandType> listDemandTypes = new ArrayList<DemandType>(  );
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_BY_ID_CATEGORY_AND_DATE );
        sbSQL.append( SQL_ORDER_BY );
        sbSQL.append( SQL_FILTER_DEMAND_TYPE_ORDER );
        sbSQL.append( SQL_ASC );

        int nIndex = 1;
        Date date = new Date( dateToday.getTime(  ) );
        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        daoUtil.setInt( nIndex++, nIdCategory );
        daoUtil.setDate( nIndex++, date );
        daoUtil.setDate( nIndex++, date );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            nIndex = 1;

            DemandType demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );

            listDemandTypes.add( demandType );
        }

        daoUtil.free(  );

        return listDemandTypes;
    }

    /**
     * {@inheritDoc}
     */
    public DemandType selectByOrder( int nOrder, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_DEMAND_TYPE_ORDER, plugin );
        daoUtil.setInt( 1, nOrder );
        daoUtil.executeQuery(  );

        DemandType demandType = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );
        }

        daoUtil.free(  );

        return demandType;
    }

    /**
     * {@inheritDoc}
     */
    public int selectMaxOrder( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_MAX_ORDER, plugin );

        int nMaxOrder = 1;

        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            nMaxOrder = daoUtil.getInt( 1 );
        }

        daoUtil.free(  );

        return nMaxOrder;
    }

    /**
     * {@inheritDoc}
     */
    public List<DemandType> selectDemandTypesByFilter( DemandTypeFilter dtFilter, Plugin plugin )
    {
        List<DemandType> listDemandTypes = new ArrayList<DemandType>(  );
        StringBuilder sbSQL = new StringBuilder( buildSQLQuery( dtFilter ) );
        sbSQL.append( SQL_ORDER_BY );
        sbSQL.append( SQL_FILTER_DEMAND_TYPE_ORDER );
        sbSQL.append( SQL_ASC );

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        setFilterValues( dtFilter, daoUtil );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            DemandType demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );

            listDemandTypes.add( demandType );
        }

        daoUtil.free(  );

        return listDemandTypes;
    }

    /**
     * {@inheritDoc}
     */
    public List<DemandType> selectNoDateEndDemandTypes( Plugin plugin )
    {
        List<DemandType> listDemandTypes = new ArrayList<DemandType>(  );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_NO_DATE_END_DEMAND_TYPES, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            DemandType demandType = new DemandType(  );
            demandType.setIdDemandType( daoUtil.getInt( nIndex++ ) );
            demandType.setLabel( daoUtil.getString( nIndex++ ) );
            demandType.setUrlResource( daoUtil.getString( nIndex++ ) );
            demandType.setUrlInfo( daoUtil.getString( nIndex++ ) );
            demandType.setUrlContact( daoUtil.getString( nIndex++ ) );
            demandType.setOrder( daoUtil.getInt( nIndex++ ) );
            demandType.setIdCategory( daoUtil.getInt( nIndex++ ) );
            demandType.setDateBegin( daoUtil.getDate( nIndex++ ) );
            demandType.setDateEnd( daoUtil.getDate( nIndex++ ) );
            demandType.setWorkgroup( daoUtil.getString( nIndex++ ) );
            demandType.setRole( daoUtil.getString( nIndex++ ) );
            demandType.setTarget( TargetEnum.getTarget( daoUtil.getInt( nIndex++ ) ) );

            listDemandTypes.add( demandType );
        }

        daoUtil.free(  );

        return listDemandTypes;
    }

    /**
     * Build the SQL query with filter
     * @param dtFilter the filter
     * @return a SQL query
     */
    private String buildSQLQuery( DemandTypeFilter dtFilter )
    {
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_ALL );
        int nIndex = 1;

        if ( dtFilter.containsLabel(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_LABEL );
        }

        if ( dtFilter.containsUrlResource(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_URL_RESOURCE );
        }

        if ( dtFilter.containsIdCategory(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_ID_CATEGORY );
        }

        if ( dtFilter.containsDateBegin(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_DATE_BEGIN );
            sbSQL.append( dtFilter.getOperatorDateBegin(  ) );
            sbSQL.append( QUESTION_MARK );
        }

        if ( dtFilter.containsDateEnd(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_DATE_END );
            sbSQL.append( dtFilter.getOperatorDateEnd(  ) );
            sbSQL.append( QUESTION_MARK );
        }

        if ( dtFilter.containsWorkgroup(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_WORKGROUP_KEY );
        }

        if ( dtFilter.containsRole(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_ROLE_KEY );
        }

        if ( dtFilter.containsOrder(  ) )
        {
            nIndex = addSQLWhereOr( dtFilter.getIsWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_DEMAND_TYPE_ORDER );
            sbSQL.append( dtFilter.getOperatorOrder(  ) );
            sbSQL.append( QUESTION_MARK );
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
     * @param dtFilter the filter
     * @param daoUtil the DAOUtil
     */
    private void setFilterValues( DemandTypeFilter dtFilter, DAOUtil daoUtil )
    {
        int nIndex = 1;

        if ( dtFilter.containsLabel(  ) )
        {
            daoUtil.setString( nIndex++, PERCENT + dtFilter.getLabel(  ) + PERCENT );
        }

        if ( dtFilter.containsUrlResource(  ) )
        {
            daoUtil.setString( nIndex++, PERCENT + dtFilter.getUrlResource(  ) + PERCENT );
        }

        if ( dtFilter.containsIdCategory(  ) )
        {
            daoUtil.setInt( nIndex++, dtFilter.getIdCategory(  ) );
        }

        if ( dtFilter.containsDateBegin(  ) )
        {
            daoUtil.setDate( nIndex++, new Date( dtFilter.getDateBegin(  ).getTime(  ) ) );
        }

        if ( dtFilter.containsDateEnd(  ) )
        {
            daoUtil.setDate( nIndex++, new Date( dtFilter.getDateEnd(  ).getTime(  ) ) );
        }

        if ( dtFilter.containsWorkgroup(  ) )
        {
            // No '%' because the workgroup key is unique
            daoUtil.setString( nIndex++, dtFilter.getWorkgroup(  ) );
        }

        if ( dtFilter.containsRole(  ) )
        {
            // No '%' because the role key is unique
            daoUtil.setString( nIndex++, dtFilter.getRole(  ) );
        }

        if ( dtFilter.containsOrder(  ) )
        {
            daoUtil.setInt( nIndex++, dtFilter.getOrder(  ) );
        }
    }
}
