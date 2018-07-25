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
package fr.paris.lutece.plugins.crm.service;

import fr.paris.lutece.plugins.crm.service.demand.MokeDemandService;
import fr.paris.lutece.plugins.crm.service.notification.MokeNotificationService;
import fr.paris.lutece.plugins.crm.service.user.MokeCRMUserService;
import fr.paris.lutece.test.LuteceTestCase;

/**
 *
 * CRMServiceTest
 *
 */
public class CRMServiceTest extends LuteceTestCase
{
    private static final int ID_DEMAND_TYPE1 = 1;
    private static final String USER_GUID1 = "UserGuid1";
    private static final String DATA1 = "Data1";
    private static final String STATUS_TEXT1 = "StatusText1";
    private static final int ID_STATUS_CRM1 = 1;
    private static final int ID_CRM_USER = 1;
    private static final int ID_DEMAND1 = 1;
    private static final String OBJECT1 = "Object1";
    private static final String MESSAGE1 = "Message1";
    private static final String SENDER1 = "Sender1";

    /**
     * Test of registerDemand method of class fr.paris.lutece.plugins.crm.service.CRMService
     */
    public void testRegisterDemand( )
    {
        System.out.println( "registerDemand" );

        CRMService service = getCRMService( );
        int nIdDemand = service.registerDemand( ID_DEMAND_TYPE1, USER_GUID1, DATA1, STATUS_TEXT1, ID_STATUS_CRM1 );
        assertNotNull( nIdDemand );
        nIdDemand = service.registerDemand( ID_DEMAND_TYPE1, ID_CRM_USER, DATA1, STATUS_TEXT1, ID_STATUS_CRM1 );
        assertNotNull( nIdDemand );
    }

    /**
     * Test of setStatus method of class fr.paris.lutece.plugins.crm.service.CRMService
     */
    public void testSetStatus( )
    {
        System.out.println( "setStatus" );

        CRMService service = getCRMService( );
        service.setStatus( ID_DEMAND1, DATA1, STATUS_TEXT1, ID_STATUS_CRM1 );
    }

    /**
     * Test of deleteDemand method of class fr.paris.lutece.plugins.crm.service.CRMService
     */
    public void testDeleteDemand( )
    {
        System.out.println( "deleteDemand" );

        CRMService service = getCRMService( );
        service.deleteDemand( ID_DEMAND1 );
    }

    /**
     * Test of notify method of class fr.paris.lutece.plugins.crm.service.CRMService
     */
    public void testNotify( )
    {
        System.out.println( "notify" );

        CRMService service = getCRMService( );
        service.notify( ID_DEMAND1, OBJECT1, MESSAGE1, SENDER1 );
    }

    /**
     * Get the crm service
     * 
     * @return the crm service
     */
    private CRMService getCRMService( )
    {
        CRMService service = CRMService.getService( );
        service.setNotificationService( new MokeNotificationService( ) );
        service.setCRMUserService( new MokeCRMUserService( ) );
        service.setDemandService( new MokeDemandService( ) );

        return service;
    }
}
