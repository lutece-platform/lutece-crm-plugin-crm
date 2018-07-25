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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * CRMUserAttributeDAO
 *
 */
public class CRMUserAttributeDAO implements ICRMUserAttributeDAO
{
    private static final String SQL_QUERY_INSERT = " INSERT INTO crm_user_attribute (id_crm_user, user_attribute_key, user_attribute_value) VALUES (?,?,?) ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM crm_user_attribute WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_SELECT = " SELECT user_attribute_key, user_attribute_value FROM crm_user_attribute WHERE id_crm_user = ? ";
    private static final String SQL_QUERY_COUNT_ATTRIBUTE_OCCURENCES = " SELECT COUNT(id_crm_user) FROM crm_user_attribute WHERE user_attribute_key = ? AND user_attribute_value = ? ";

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insert( int nIdCRMUser, String strUserAttributeKey, String strUserAttributeValue, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        int nIndex = 1;

        daoUtil.setInt( nIndex++, nIdCRMUser );
        daoUtil.setString( nIndex++, strUserAttributeKey );
        daoUtil.setString( nIndex++, strUserAttributeValue );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdCRMUser, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nIdCRMUser );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> load( int nIdCRMUser, Plugin plugin )
    {
        Map<String, String> userAttributes = new HashMap<String, String>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nIdCRMUser );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            int nIndex = 1;
            userAttributes.put( daoUtil.getString( nIndex++ ), daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );

        return userAttributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int countAttributeValueInstances( String strUserAttributeValue, String strUserAttributeKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_COUNT_ATTRIBUTE_OCCURENCES, plugin );
        daoUtil.setString( 1, strUserAttributeKey );
        daoUtil.setString( 2, strUserAttributeValue );
        daoUtil.executeQuery( );

        int nNbInstances = 0;
        if ( daoUtil.next( ) )
        {
            nNbInstances = daoUtil.getInt( 1 );
        }

        daoUtil.free( );

        return nNbInstances;
    }
}
