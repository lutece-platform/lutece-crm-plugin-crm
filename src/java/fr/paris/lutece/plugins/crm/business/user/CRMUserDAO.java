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
package fr.paris.lutece.plugins.crm.business.user;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 *
 * CRMUserDAO
 *
 */
public class CRMUserDAO implements ICRMUserDAO
{
    // SQL QUERIES
    private static final String SQL_QUERY_NEW_PK = " SELECT max( id_crm_user ) FROM crm_user ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_user (id_crm_user, user_guid, first_name, last_name, email, phone_number) VALUES (?,?,?,?,?,?) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_crm_user, user_guid, first_name, last_name, email, phone_number FROM crm_user WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_SELECT_BY_USER_GUID = " SELECT id_crm_user, user_guid, first_name, last_name, email, phone_number FROM crm_user WHERE user_guid = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE crm_user SET user_guid = ?, first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_user WHERE id_crm_user = ? ";

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
            daoUtil.setString( nIndex++, user.getFirstName(  ) );
            daoUtil.setString( nIndex++, user.getLastName(  ) );
            daoUtil.setString( nIndex++, user.getEmail(  ) );
            daoUtil.setString( nIndex++, user.getPhoneNumber(  ) );

            daoUtil.executeUpdate(  );
            daoUtil.free(  );

            nKey = user.getIdCRMUser(  );
        }

        return nKey;
    }

    /**
     * {@inheritDoc}
     */
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
            user.setFirstName( daoUtil.getString( nIndex++ ) );
            user.setLastName( daoUtil.getString( nIndex++ ) );
            user.setEmail( daoUtil.getString( nIndex++ ) );
            user.setPhoneNumber( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free(  );

        return user;
    }

    /**
     * {@inheritDoc}
     */
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
            user.setFirstName( daoUtil.getString( nIndex++ ) );
            user.setLastName( daoUtil.getString( nIndex++ ) );
            user.setEmail( daoUtil.getString( nIndex++ ) );
            user.setPhoneNumber( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free(  );

        return user;
    }

    /**
     * {@inheritDoc}
     */
    public void store( CRMUser user, Plugin plugin )
    {
        if ( user != null )
        {
            int nIndex = 1;

            DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

            daoUtil.setString( nIndex++, user.getUserGuid(  ) );
            daoUtil.setString( nIndex++, user.getFirstName(  ) );
            daoUtil.setString( nIndex++, user.getLastName(  ) );
            daoUtil.setString( nIndex++, user.getEmail(  ) );
            daoUtil.setString( nIndex++, user.getPhoneNumber(  ) );

            daoUtil.setInt( nIndex++, user.getIdCRMUser(  ) );
            daoUtil.executeUpdate(  );
            daoUtil.free(  );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete( int nIdCRMUser, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nIdCRMUser );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
