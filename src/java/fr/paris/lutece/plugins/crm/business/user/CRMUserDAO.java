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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * CRMUserDAO
 *
 */
public class CRMUserDAO implements ICRMUserDAO
{
    // SQL QUERIES
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_crm_user ) FROM crm_user ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_user (id_crm_user, user_guid, status) VALUES (?,?,?) ";
    private static final String SQL_QUERY_SELECT_ALL = " SELECT id_crm_user, user_guid, status FROM crm_user ";
    private static final String SQL_QUERY_SELECT = SQL_QUERY_SELECT_ALL + " WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_SELECT_BY_USER_GUID = SQL_QUERY_SELECT_ALL + " WHERE user_guid = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE crm_user SET user_guid = ?, status = ? WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_user WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_SELECT_ID_CRM_USER = " SELECT cu.id_crm_user FROM crm_user AS cu ";
    private static final String SQL_QUERY_FILTER_BY_LIST_IDS = " WHERE id_crm_user IN ( ";

    // CONSTANTS
    private static final String INTERROGATION_MARK = "?";
    private static final String COMMA = ",";
    private static final String CLOSED_BRACKET = ")";

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public int insert( CRMUser user, Plugin plugin )
    {
        int nKey = -1;

        if ( user != null )
        {
            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

            int nIndex = 1;
            user.setIdCRMUser( newPrimaryKey( plugin ) );

            daoUtil.setInt( nIndex++, user.getIdCRMUser(  ) );
            daoUtil.setString( nIndex++, user.getUserGuid(  ) );
            daoUtil.setInt( nIndex, user.getStatus(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );

            nKey = user.getIdCRMUser(  );
        }

        return nKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CRMUser load( int nIdCRMUser, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nIdCRMUser );
        daoUtil.executeQuery(  );

        CRMUser user = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            user = new CRMUser(  );
            user.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            user.setUserGuid( daoUtil.getString( nIndex++ ) );
            user.setStatus( daoUtil.getInt( nIndex ) );
        }

        daoUtil.free(  );

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CRMUser loadByUserGuid( String strUserGuid, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_USER_GUID, plugin );
        daoUtil.setString( 1, strUserGuid );
        daoUtil.executeQuery(  );

        CRMUser user = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            user = new CRMUser(  );
            user.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            user.setUserGuid( daoUtil.getString( nIndex++ ) );
            user.setStatus( daoUtil.getInt( nIndex ) );
        }

        daoUtil.free(  );

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( CRMUser user, Plugin plugin )
    {
        if ( user != null )
        {
            int nIndex = 1;

            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

            daoUtil.setString( nIndex++, user.getUserGuid(  ) );
            daoUtil.setInt( nIndex++, user.getStatus(  ) );

            daoUtil.setInt( nIndex, user.getIdCRMUser(  ) );
            daoUtil.executeUpdate(  );
            daoUtil.free(  );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdCRMUser, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nIdCRMUser );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CRMUser> selectAll( Plugin plugin )
    {
        List<CRMUser> listUsers = new ArrayList<CRMUser>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            CRMUser user = new CRMUser(  );
            user.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            user.setUserGuid( daoUtil.getString( nIndex++ ) );
            user.setStatus( daoUtil.getInt( nIndex ) );
            listUsers.add( user );
        }

        daoUtil.free(  );

        return listUsers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> selectListIdsCRMUserByFilter( CRMUserFilter filter, Plugin plugin )
    {
        List<Integer> listIds = new ArrayList<Integer>(  );
        String strSQL = filter.buildSQLQuery( SQL_QUERY_SELECT_ID_CRM_USER );
        DAOUtil daoUtil = new DAOUtil( strSQL, plugin );
        filter.setFilterValues( daoUtil );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            listIds.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return listIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CRMUser> selectByListIds( List<Integer> listIdsCRMUser, Plugin plugin )
    {
        List<CRMUser> listUsers = new ArrayList<CRMUser>(  );

        // Build the SQL query
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_ALL );

        if ( !listIdsCRMUser.isEmpty(  ) )
        {
            sbSQL.append( SQL_QUERY_FILTER_BY_LIST_IDS );

            for ( int nIndex = 0; nIndex < listIdsCRMUser.size(  ); nIndex++ )
            {
                sbSQL.append( INTERROGATION_MARK );

                if ( nIndex < ( listIdsCRMUser.size(  ) - 1 ) )
                {
                    sbSQL.append( COMMA );
                }
            }

            sbSQL.append( CLOSED_BRACKET );
        }

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );

        // Set the values
        int nIndex = 1;

        for ( int nIdCRMUser : listIdsCRMUser )
        {
            daoUtil.setInt( nIndex, nIdCRMUser );
            nIndex++;
        }

        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            nIndex = 1;

            CRMUser user = new CRMUser(  );
            user.setIdCRMUser( daoUtil.getInt( nIndex++ ) );
            user.setUserGuid( daoUtil.getString( nIndex++ ) );
            user.setStatus( daoUtil.getInt( nIndex ) );
            listUsers.add( user );
        }

        daoUtil.free(  );

        return listUsers;
    }
}
