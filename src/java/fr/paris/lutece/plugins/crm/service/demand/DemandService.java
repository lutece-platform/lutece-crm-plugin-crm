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
package fr.paris.lutece.plugins.crm.service.demand;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.demand.DemandFilter;
import fr.paris.lutece.plugins.crm.business.demand.DemandHome;
import fr.paris.lutece.plugins.crm.business.demand.DemandSort;
import fr.paris.lutece.plugins.crm.business.demand.DemandStatusCRM;
import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.demand.IPaginationProperties;
import fr.paris.lutece.plugins.crm.business.demand.PaginationFilterSortManager;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.html.IPaginator;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

import java.util.ArrayList;
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
public class DemandService
{
    private static final String BEAN_CRM_DEMANDSERVICE = "crm.demandService";

    /**
     * Constructor
     */
    protected DemandService(  )
    {
    }

    /**
     * Get an instance of {@link DemandService}
     * @return an instance of {@link DemandService}
     */
    public static DemandService getService(  )
    {
        return SpringContextService.getBean( BEAN_CRM_DEMANDSERVICE );
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
     * Remove a demand and its resource
     * @param nIdDemand the id demand
     */
    public void removeWithItsResource( int nIdDemand, boolean bByDaemon )
    {
        Demand demand = findByPrimaryKey( nIdDemand );

        if ( demand != null )
        {
            DemandType demandType = DemandTypeService.getService(  ).findByPrimaryKey( demand.getIdDemandType(  ) );

            if ( demandType != null )
            {
                if ( StringUtils.isNotBlank( demandType.getUrlDelete(  ) ) && bByDaemon )
                {
                    try
                    {
                        DemandWebService.getService(  )
                                        .sendRemoveDraft( demandType.getUrlDelete(  ), nIdDemand,
                            demand.getData(  ).replace( "\"", "'" ) );
                        remove( nIdDemand );
                    }
                    catch ( HttpAccessException e )
                    {
                        String strError = "CRM Demand - Error connecting to '" + demandType.getUrlDelete(  ) + "' : ";
                        AppLogService.error( strError + e.getMessage(  ), e );
                    }
                }
                else
                {
                    try
                    {
                        DemandWebService.getService(  )
                                        .sendRemoveDraft( demandType.getUrlResource(  ), nIdDemand,
                            demand.getData(  ).replace( "\"", "'" ) );
                        remove( nIdDemand );
                    }
                    catch ( HttpAccessException e )
                    {
                        String strError = "CRM Demand - Error connecting to '" + demandType.getUrlResource(  ) +
                            "' : ";
                        AppLogService.error( strError + e.getMessage(  ), e );
                    }
                }
            }
        }
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
            removeWithItsResource( demand.getIdDemand(  ), false );
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
     * Find the demands given an user crm id, results can be sorted
     * @param nIdCRMUser the user crm id
     * @param locale {@link Locale}
     * @param nIdStatusToSort the id status of demands that will be sorted
     * @param listDemandSort the sorts to apply
     * @return a map of (id_status_crm, List&lt;Demand&gt;)
     */
    public Map<String, List<Demand>> findByIdCRMUser( int nIdCRMUser, Locale locale, int nIdStatusToSort,
        List<DemandSort> listDemandSort )
    {
        Map<String, List<Demand>> map = new HashMap<String, List<Demand>>(  );

        for ( DemandStatusCRM statusCRM : DemandStatusCRMService.getService(  ).getAllStatusCRM( locale ) )
        {
            DemandFilter dFilter = new DemandFilter(  );
            dFilter.setIdCRMUser( nIdCRMUser );
            dFilter.setIdStatusCRM( statusCRM.getIdStatusCRM(  ) );

            // sort
            if ( nIdStatusToSort == statusCRM.getIdStatusCRM(  ) )
            {
                dFilter.setListDemandSort( listDemandSort );
            }

            List<Demand> listDemands = findByFilter( dFilter );
            map.put( Integer.toString( statusCRM.getIdStatusCRM(  ) ), listDemands );
        }

        return map;
    }

    /**
     * Find by filter with map
     * @param nIdCRMUser the user crm id
     * @param locale {@link Locale}
     * @return a map of (id_status_crm, List&lt;Demand&gt;)
     */
    public Map<String, List<Demand>> findByFilterMap( DemandFilter dFilter, Locale locale,
        PaginationFilterSortManager paginationFilterSortManager )
    {
        Map<String, List<Demand>> map = new HashMap<String, List<Demand>>(  );

        for ( DemandStatusCRM statusCRM : DemandStatusCRMService.getService(  ).getAllStatusCRM( locale ) )
        {
            int nIdStatus = statusCRM.getIdStatusCRM(  );
            DemandFilter filterWithSort = new DemandFilter(  );
            filterWithSort.setDateModification( dFilter.getDateModification(  ) );
            filterWithSort.setIdCRMUser( dFilter.getIdCRMUser(  ) );
            filterWithSort.setIdDemandType( dFilter.getIdDemandType(  ) );
            filterWithSort.setIdStatusCRM( nIdStatus );
            filterWithSort.setIsWideSearch( dFilter.getIsWideSearch(  ) );
            filterWithSort.setNotification( dFilter.getNotification(  ) );
            filterWithSort.setOperatorDateModification( dFilter.getOperatorDateModification(  ) );

            DemandSort sort = paginationFilterSortManager.retrieveSort( nIdStatus );
            List<DemandSort> listDemandSort = new ArrayList<DemandSort>(  );

            if ( sort != null )
            {
                listDemandSort.add( sort );
            }

            filterWithSort.setListDemandSort( listDemandSort );

            int nTotalResult = this.countByFilter( filterWithSort );

            List<Demand> listDemands = this.findByFilterWithPagination( filterWithSort,
                    paginationFilterSortManager.retrievePaginationProperties( nIdStatus, nTotalResult ) );

            IPaginator<Demand> paginator = paginationFilterSortManager.createAndStorePaginator( nIdStatus, listDemands,
                    nTotalResult );

            map.put( Integer.toString( nIdStatus ), paginator.getPageItems(  ) );
        }

        return map;
    }

    /**
     * Find by filter with pagination
     * @param dFilter the filter
     * @param paginationProperties the pagination properties
     * @return a list of {@link Demand}
     */
    public List<Demand> findByFilterWithPagination( DemandFilter dFilter, IPaginationProperties paginationProperties )
    {
        return DemandHome.findByFilter( dFilter, paginationProperties );
    }

    /**
     * Count results by filter
     * @param dFilter the filter
     * @return the number of results
     */
    public int countByFilter( DemandFilter dFilter )
    {
        return DemandHome.countByFilter( dFilter );
    }
}
