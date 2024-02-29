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
package fr.paris.lutece.plugins.crm.business.demand.category;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * This class provides Data Access methods for Category objects
 *
 */
public final class CategoryDAO implements ICategoryDAO
{
    // SQL QUERIES
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_category ) FROM crm_category";
    private static final String SQL_QUERY_SELECT = "SELECT id_category, name, description, code FROM crm_category WHERE id_category = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO crm_category ( id_category, name, description, code ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM crm_category WHERE id_category = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE crm_category SET id_category = ?, name = ?, description = ?, code = ? WHERE id_category = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_category, name, description, code FROM crm_category ORDER BY name ASC";
    private static final String SQL_QUERY_SELECT_BY_CODE = "SELECT id_category, name, description, code FROM crm_category WHERE code = ?";
    private static final String SQL_QUERY_SELECT_FIRST_CATEGORY = "SELECT id_category, name, description, code FROM crm_category ORDER BY name ASC LIMIT 1";

    /**
     * {@inheritDoc}
     */
    public int newPrimaryKey( Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin ) )
        {
            daoUtil.executeQuery( );

            int nKey;

            if ( !daoUtil.next( ) )
            {
                // if the table is empty
                nKey = 1;
            }

            nKey = daoUtil.getInt( 1 ) + 1;
            
            return nKey;
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized int insert( Category category, Plugin plugin )
    {
        int nIndex = 1;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin ) )
        {
            category.setIdCategory( newPrimaryKey( plugin ) );

            daoUtil.setInt( nIndex++, category.getIdCategory( ) );
            daoUtil.setString( nIndex++, category.getName( ) );
            daoUtil.setString( nIndex++, category.getDescription( ) );
            daoUtil.setString( nIndex++, category.getCode( ) );

            daoUtil.executeUpdate( );
            
        }
        
        return category.getIdCategory( );
    }

    /**
     * {@inheritDoc}
     */
    public Category load( int nId, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );

            Category category = null;

            if ( daoUtil.next( ) )
            {
                int nIndex = 1;
                category = new Category( );

                category.setIdCategory( daoUtil.getInt( nIndex++ ) );
                category.setName( daoUtil.getString( nIndex++ ) );
                category.setDescription( daoUtil.getString( nIndex++ ) );
                category.setCode( daoUtil.getString( nIndex++ ) );
            }
            
            return category;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete( int nCategoryId, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin ) )
        {
            daoUtil.setInt( 1, nCategoryId );
            daoUtil.executeUpdate( );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void store( Category category, Plugin plugin )
    {
        int nIndex = 1;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
            daoUtil.setInt( nIndex++, category.getIdCategory( ) );
            daoUtil.setString( nIndex++, category.getName( ) );
            daoUtil.setString( nIndex++, category.getDescription( ) );
            daoUtil.setString( nIndex++, category.getCode( ) );
            daoUtil.setInt( nIndex++, category.getIdCategory( ) );

            daoUtil.executeUpdate( );
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Category> selectCategoriesList( Plugin plugin )
    {
        Collection<Category> categoryList = new ArrayList<Category>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin ) )
        {
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                int nIndex = 1;
                Category category = new Category( );

                category.setIdCategory( daoUtil.getInt( nIndex++ ) );
                category.setName( daoUtil.getString( nIndex++ ) );
                category.setDescription( daoUtil.getString( nIndex++ ) );
                category.setCode( daoUtil.getString( nIndex++ ) );

                categoryList.add( category );
            }
        }
        return categoryList;
    }

    /**
     * {@inheritDoc}
     */
    public Category selectFirstCategory( Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_FIRST_CATEGORY, plugin ) )
        {
            daoUtil.executeQuery( );

            Category category = null;

            if ( daoUtil.next( ) )
            {
                int nIndex = 1;
                category = new Category( );

                category.setIdCategory( daoUtil.getInt( nIndex++ ) );
                category.setName( daoUtil.getString( nIndex++ ) );
                category.setDescription( daoUtil.getString( nIndex++ ) );
                category.setCode( daoUtil.getString( nIndex++ ) );
            }
            
            return category;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Category selectByCode( String strCode, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CODE, plugin ) )
        {
            daoUtil.setString( 1 , strCode );
            daoUtil.executeQuery( );
            Category category = null;

            if ( daoUtil.next( ) )
            {
                category = new Category();
                int nIndex = 1;
                
                category.setIdCategory( daoUtil.getInt( nIndex++ ) );
                category.setName( daoUtil.getString( nIndex++ ) );
                category.setDescription( daoUtil.getString( nIndex++ ) );
                category.setCode( daoUtil.getString( nIndex++ ) );
            }
            
            return category;
        }
    }
}
