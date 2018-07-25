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
package fr.paris.lutece.plugins.crm.service.demand;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.demand.DemandFilter;
import fr.paris.lutece.plugins.crm.business.demand.MokeDemand;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MokeDemandService extends DemandService
{
    private static final int ID_DEMAND1 = 1;
    private static final int ID_DEMAND2 = 2;
    private static final int ID_DEMAND3 = 3;
    private static final int ID_DEMAND4 = 4;
    private static final String ID_STATUS_CRM1 = "1";
    private static final String ID_STATUS_CRM2 = "2";

    /**
     * Find a demand by its primary key
     * 
     * @param nIdDemand
     *            the id demand
     * @return a {@link Demand}
     */
    public Demand findByPrimaryKey( int nIdDemand )
    {
        Demand demand = new MokeDemand( nIdDemand );
        System.out.println( trace( demand ) );

        return demand;
    }

    /**
     * Create a new demand
     * 
     * @param demand
     *            the demand
     * @return the newly created demand id
     */
    public int create( Demand demand )
    {
        if ( demand != null )
        {
            demand.setDateModification( new Timestamp( new Date( ).getTime( ) ) );
            demand.setIdDemand( ID_DEMAND1 );
        }

        System.out.println( trace( demand ) );

        return demand.getIdDemand( );
    }

    /**
     * Update a demand
     * 
     * @param demand
     *            the demand
     */
    public void update( Demand demand )
    {
        if ( demand != null )
        {
            demand.setDateModification( new Timestamp( new Date( ).getTime( ) ) );
        }

        System.out.println( trace( demand ) );
    }

    /**
     * Remove a demand
     * 
     * @param nIdDemand
     *            the id demand
     */
    public void remove( int nIdDemand )
    {
        System.out.println( trace( ) );
    }

    /**
     * Remove a demand and its resource
     * 
     * @param nIdDemand
     *            the id demand
     */
    public void removeWithItsResource( int nIdDemand )
    {
        System.out.println( trace( ) );
    }

    /**
     * Remove the demands given an id demand type
     * 
     * @param nIdDemandType
     *            the id demand type
     */
    public void removeByIdDemandType( int nIdDemandType )
    {
        System.out.println( trace( ) );
    }

    /**
     * Find all demands
     * 
     * @return a list of {@link Demand}
     */
    public List<Demand> findAll( )
    {
        List<Demand> listDemands = new ArrayList<Demand>( );
        listDemands.add( new MokeDemand( ID_DEMAND1 ) );
        listDemands.add( new MokeDemand( ID_DEMAND2 ) );
        System.out.println( trace( listDemands ) );

        return listDemands;
    }

    /**
     * Find by filter
     * 
     * @param dFilter
     *            the filter
     * @return a list of {@link Demand}
     */
    public List<Demand> findByFilter( DemandFilter dFilter )
    {
        List<Demand> listDemands = new ArrayList<Demand>( );
        listDemands.add( new MokeDemand( ID_DEMAND1 ) );
        listDemands.add( new MokeDemand( ID_DEMAND2 ) );
        System.out.println( trace( listDemands, dFilter ) );

        return listDemands;
    }

    /**
     * Find the demands given an user crm id
     * 
     * @param nIdCRMUser
     *            the user crm id
     * @param locale
     *            {@link Locale}
     * @return a map of (id_status_crm, List&lt;Demand&gt;)
     */
    public Map<String, List<Demand>> findByIdCRMUser( int nIdCRMUser, Locale locale )
    {
        Map<String, List<Demand>> map = new HashMap<String, List<Demand>>( );

        List<Demand> listDemands = new ArrayList<Demand>( );
        listDemands.add( new MokeDemand( ID_DEMAND1 ) );
        listDemands.add( new MokeDemand( ID_DEMAND2 ) );
        map.put( ID_STATUS_CRM1, listDemands );
        System.out.println( trace( listDemands ) );

        listDemands = new ArrayList<Demand>( );
        listDemands.add( new MokeDemand( ID_DEMAND3 ) );
        listDemands.add( new MokeDemand( ID_DEMAND4 ) );
        map.put( ID_STATUS_CRM2, listDemands );
        System.out.println( trace( listDemands ) );

        return map;
    }

    /**
     * Trace
     * 
     * @return trace
     */
    private String trace( )
    {
        return trace( null, null );
    }

    /**
     * Trace
     * 
     * @param demand
     *            demand
     * @return trace
     */
    private String trace( Demand demand )
    {
        List<Demand> listDemands = new ArrayList<Demand>( );
        listDemands.add( demand );

        return trace( listDemands, null );
    }

    /**
     * Trace
     * 
     * @param listDemands
     *            the list of demands
     * @return trace
     */
    private String trace( List<Demand> listDemands )
    {
        return trace( listDemands, null );
    }

    /**
     * Trace
     * 
     * @param listDemands
     *            the list of demands
     * @return trace
     */
    private String trace( List<Demand> listDemands, DemandFilter dFilter )
    {
        StringBuilder sbTrace = new StringBuilder( );
        sbTrace.append( "\n ---------------------- Demand Service -------------------" );
        sbTrace.append( "\nMethod name : " + Thread.currentThread( ).getStackTrace( ) [2].getMethodName( ) );

        if ( ( listDemands != null ) && ( listDemands.size( ) > 0 ) )
        {
            for ( Demand demand : listDemands )
            {
                sbTrace.append( "\n-- Demand --" );
                sbTrace.append( "\nid_demand : " + demand.getIdDemand( ) );
                sbTrace.append( "\ndata : " + demand.getData( ) );
                sbTrace.append( "\nid_crm_user : " + demand.getIdCRMUser( ) );
                sbTrace.append( "\nid_demand_type : " + demand.getIdDemandType( ) );
                sbTrace.append( "\nid_status_crm : " + demand.getIdStatusCRM( ) );
                sbTrace.append( "\nstatus_text : " + demand.getStatusText( ) );
                sbTrace.append( "\ndate_modifiaction : " + demand.getDateModification( ) );
            }
        }

        if ( dFilter != null )
        {
            sbTrace.append( "\n-- Demand Filter --" );
            sbTrace.append( "\nid_crm_user : " + dFilter.getIdCRMUser( ) );
            sbTrace.append( "\nid_demand_type : " + dFilter.getIdDemandType( ) );
            sbTrace.append( "\nid_status_crm : " + dFilter.getIdStatusCRM( ) );
            sbTrace.append( "\ndate_modification : " + dFilter.getDateModification( ) );
            sbTrace.append( "\nis_wide_search : " + dFilter.getIsWideSearch( ) );
            sbTrace.append( "\noperator_date_modification : " + dFilter.getOperatorDateModification( ) );
        }

        sbTrace.append( "\n --------------------------------------------------------------------" );

        return sbTrace.toString( );
    }
}
