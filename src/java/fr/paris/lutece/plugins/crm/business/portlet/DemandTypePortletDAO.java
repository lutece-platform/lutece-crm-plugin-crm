/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.crm.business.portlet;

import fr.paris.lutece.portal.business.portlet.Portlet;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 * This class provides Data Access methods for DemandTypePortlet objects
 */
public final class DemandTypePortletDAO implements IDemandTypePortletDAO
{
    private static final String SQL_QUERY_INSERT = "INSERT INTO crm_demand_type_portlet ( id_portlet , id_category ) VALUES ( ? , ? )";
    private static final String SQL_QUERY_SELECT = "SELECT id_portlet , id_category FROM crm_demand_type_portlet WHERE id_portlet = ? ";
    private static final String SQL_QUERY_SELECT_COUNT_PORTLET_BY_ID_CATEGORY = "SELECT COUNT(id_portlet )FROM crm_demand_type_portlet WHERE id_category = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE crm_demand_type_portlet SET id_portlet = ?, id_category = ? WHERE id_portlet = ? ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM crm_demand_type_portlet WHERE id_portlet= ? ";

    ///////////////////////////////////////////////////////////////////////////////////////
    //Access methods to data

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert( Portlet portlet )
    {
        DemandTypePortlet p = (DemandTypePortlet) portlet;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT );
        daoUtil.setInt( 1, p.getId(  ) );
        daoUtil.setInt( 2, p.getIdCategory() );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nPortletId )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE );
        daoUtil.setInt( 1, nPortletId );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Portlet load( int nPortletId )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT );
        daoUtil.setInt( 1, nPortletId );
        daoUtil.executeQuery(  );

        DemandTypePortlet portlet = new DemandTypePortlet(  );

        if ( daoUtil.next(  ) )
        {
            portlet.setId( daoUtil.getInt( 1 ) );
            portlet.setIdCategory( daoUtil.getInt( 2 ) );
        }

        daoUtil.free(  );

        return portlet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int selectCountPortletByIdCategory( int nIdCategory )
    {
        int nCountPortlet = 0;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_COUNT_PORTLET_BY_ID_CATEGORY );
        daoUtil.setInt( 1, nIdCategory );
        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            nCountPortlet = daoUtil.getInt( 1 );
        }

        daoUtil.free(  );

        return nCountPortlet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( Portlet portlet )
    {
        DemandTypePortlet p = (DemandTypePortlet) portlet;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE );
        daoUtil.setInt( 1, p.getId(  ) );
        daoUtil.setInt( 2, p.getIdCategory(  ) );
        daoUtil.setInt( 3, p.getId(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
