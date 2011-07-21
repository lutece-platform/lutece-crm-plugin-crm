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
package fr.paris.lutece.plugins.crm.service.demand;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.demand.DemandFilter;
import fr.paris.lutece.plugins.crm.business.demand.DemandHome;
import fr.paris.lutece.plugins.crm.business.demand.DemandStatusCRM;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.sql.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 *
 * DemandService
 *
 */
public final class DemandService
{
    private static final String BEAN_CRM_DEMANDSERVICE = "crm.demandService";

    /**
     * Private constructor
     */
    private DemandService(  )
    {
    }

    /**
     * Get an instance of {@link DemandService}
     * @return an instance of {@link DemandService}
     */
    public static DemandService getService(  )
    {
        return (DemandService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME, BEAN_CRM_DEMANDSERVICE );
    }

    /**
     * Find a demand by its primary key
     * @param nIdDemand the id demand
     * @return a {@link Demand}
     */
    public Demand findByPrimaryKey( int nIdDemand )
    {
        return DemandHome.findByPrimaryKey( nIdDemand );
    }

    /**
     * Create a new demand
     * @param demand the demand
     * @return the newly created demand id
     */
    public int create( Demand demand )
    {
        int nIdDemand = -1;

        if ( demand != null )
        {
            demand.setDateModification( new Timestamp( new Date(  ).getTime(  ) ) );
            nIdDemand = DemandHome.create( demand );
        }

        return nIdDemand;
    }

    /**
     * Update a demand
     * @param demand the demand
     */
    public void update( Demand demand )
    {
        if ( demand != null )
        {
            demand.setDateModification( new Timestamp( new Date(  ).getTime(  ) ) );
            DemandHome.update( demand );
        }
    }

    /**
     * Remove a demand
     * @param nIdDemand the id demand
     */
    public void remove( int nIdDemand )
    {
        // Remove all notifications associated to the demand
        NotificationService.getService(  ).removeByIdDemand( nIdDemand );
        DemandHome.remove( nIdDemand );
    }

    /**
     * Remove the demands given an id demand type
     * @param nIdDemandType the id demand type
     */
    public void removeByIdDemandType( int nIdDemandType )
    {
        DemandFilter dFilter = new DemandFilter(  );
        dFilter.setIdDemandType( nIdDemandType );

        for ( Demand demand : findByFilter( dFilter ) )
        {
            remove( demand.getIdDemand(  ) );
        }
    }

    /**
     * Find all demands
     * @return a list of {@link Demand}
     */
    public List<Demand> findAll(  )
    {
        return DemandHome.findAll(  );
    }

    /**
     * Find by filter
     * @param dFilter the filter
     * @return a list of {@link Demand}
     */
    public List<Demand> findByFilter( DemandFilter dFilter )
    {
        return DemandHome.findByFilter( dFilter );
    }

    /**
     * Find the demands given an user crm id
     * @param nIdCRMUser the user crm id
     * @param locale {@link Locale}
     * @return a map of (id_status_crm, List&lt;Demand&gt;)
     */
    public Map<String, List<Demand>> findByIdCRMUser( int nIdCRMUser, Locale locale )
    {
        Map<String, List<Demand>> map = new HashMap<String, List<Demand>>(  );

        for ( DemandStatusCRM statusCRM : DemandStatusCRMService.getService(  ).getAllStatusCRM( locale ) )
        {
            DemandFilter dFilter = new DemandFilter(  );
            dFilter.setIdCRMUser( nIdCRMUser );
            dFilter.setIdStatusCRM( statusCRM.getIdStatusCRM(  ) );

            List<Demand> listDemands = findByFilter( dFilter );
            map.put( Integer.toString( statusCRM.getIdStatusCRM(  ) ), listDemands );
        }

        return map;
    }
}
