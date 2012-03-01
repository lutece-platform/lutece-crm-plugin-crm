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

import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.util.date.DateUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Locale;


/**
 *
 * DemandTest
 *
 */
public class DemandTest extends LuteceTestCase
{
    private static final String DATE1 = "01/09/2011";
    private static final String DATE2 = "10/10/2011";
    private static final String DATA1 = "Data1";
    private static final String DATA2 = "Data2";
    private static final int ID_CRM_USER1 = 1;
    private static final int ID_CRM_USER2 = 2;
    private static final int ID_DEMAND_TYPE1 = 1;
    private static final int ID_DEMAND_TYPE2 = 2;
    private static final int ID_STATUS_CRM1 = 0;
    private static final int ID_STATUS_CRM2 = 1;
    private static final String STATUS_TEXT1 = "StatusText1";
    private static final String STATUS_TEXT2 = "StatusText2";
    private static Locale _locale = Locale.getDefault(  );

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.demand.Demand
     */
    public void testBusiness(  )
    {
        // Initialize an object
        Date date = DateUtil.formatDateLongYear( DATE1, _locale );
        Timestamp dateModification = new Timestamp( date.getTime(  ) );
        Demand demand = new Demand(  );
        demand.setData( DATA1 );
        demand.setIdCRMUser( ID_CRM_USER1 );
        demand.setIdDemandType( ID_DEMAND_TYPE1 );
        demand.setIdStatusCRM( ID_STATUS_CRM1 );
        demand.setStatusText( STATUS_TEXT1 );
        demand.setDateModification( dateModification );

        // Test create
        DemandHome.create( demand );

        Demand demandStored = DemandHome.findByPrimaryKey( demand.getIdDemand(  ) );
        assertEquals( demand.getIdDemand(  ), demandStored.getIdDemand(  ) );
        assertEquals( demand.getData(  ), demandStored.getData(  ) );
        assertEquals( demand.getIdCRMUser(  ), demandStored.getIdCRMUser(  ) );
        assertEquals( demand.getIdDemandType(  ), demandStored.getIdDemandType(  ) );
        assertEquals( demand.getIdStatusCRM(  ), demandStored.getIdStatusCRM(  ) );
        assertEquals( demand.getStatusText(  ), demandStored.getStatusText(  ) );

        // Test update
        date = DateUtil.formatDateLongYear( DATE2, _locale );
        dateModification = new Timestamp( date.getTime(  ) );
        demand.setData( DATA2 );
        demand.setIdCRMUser( ID_CRM_USER2 );
        demand.setIdDemandType( ID_DEMAND_TYPE2 );
        demand.setIdStatusCRM( ID_STATUS_CRM2 );
        demand.setStatusText( STATUS_TEXT2 );
        demand.setDateModification( dateModification );
        DemandHome.update( demand );
        demandStored = DemandHome.findByPrimaryKey( demand.getIdDemand(  ) );
        assertEquals( demand.getIdDemand(  ), demandStored.getIdDemand(  ) );
        assertEquals( demand.getData(  ), demandStored.getData(  ) );
        assertEquals( demand.getIdCRMUser(  ), demandStored.getIdCRMUser(  ) );
        assertEquals( demand.getIdDemandType(  ), demandStored.getIdDemandType(  ) );
        assertEquals( demand.getIdStatusCRM(  ), demandStored.getIdStatusCRM(  ) );
        assertEquals( demand.getStatusText(  ), demandStored.getStatusText(  ) );

        // Test finders
        DemandFilter dFilter = new DemandFilter(  );
        dFilter.setDateModification( dateModification );
        dFilter.setIdCRMUser( ID_CRM_USER2 );
        dFilter.setIdDemandType( ID_DEMAND_TYPE2 );
        dFilter.setIdStatusCRM( ID_STATUS_CRM2 );
        DemandHome.findByFilter( dFilter );
        DemandHome.findAll(  );

        // Test remove
        DemandHome.remove( demand.getIdDemand(  ) );
        demandStored = DemandHome.findByPrimaryKey( demand.getIdDemand(  ) );
        assertNull( demandStored );
    }
}
