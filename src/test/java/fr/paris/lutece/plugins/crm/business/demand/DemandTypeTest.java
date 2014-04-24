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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.util.date.DateUtil;

import java.util.Date;
import java.util.Locale;


/**
 *
 * DemandTypeTest
 *
 */
public class DemandTypeTest extends LuteceTestCase
{
    private static final String DATE_BEGIN1 = "01/09/2011";
    private static final String DATE_BEGIN2 = "02/10/2011";
    private static final String DATE_END1 = "10/09/2011";
    private static final String DATE_END2 = "11/10/2011";
    private static final int ID_CATEGORY1 = 1;
    private static final int ID_CATEGORY2 = 2;
    private static final String LABEL1 = "Label1";
    private static final String LABEL2 = "Label2";
    private static final int ORDER1 = 1;
    private static final int ORDER2 = 2;
    private static final String ROLE1 = "Role1";
    private static final String ROLE2 = "Role2";
    private static final String URL_CONTACT1 = "UrlContact1";
    private static final String URL_CONTACT2 = "UrlContact2";
    private static final String URL_INFO1 = "UrlInfo1";
    private static final String URL_INFO2 = "UrlInfo2";
    private static final String URL_RESOURCE1 = "UrlResource1";
    private static final String URL_RESOURCE2 = "UrlResource2";
    private static final String WORKGROUP1 = "Workgroup1";
    private static final String WORKGROUP2 = "Workgroup2";
    private static Locale _locale = Locale.getDefault(  );

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.demand.DemandType
     */
    public void testBusiness(  )
    {
        // Initialize an object
        DemandType demandType = new DemandType(  );
        Date dateBegin = DateUtil.formatDateLongYear( DATE_BEGIN1, _locale );
        Date dateEnd = DateUtil.formatDateLongYear( DATE_END1, _locale );
        demandType.setDateBegin( dateBegin );
        demandType.setDateEnd( dateEnd );
        demandType.setIdCategory( ID_CATEGORY1 );
        demandType.setLabel( LABEL1 );
        demandType.setOrder( ORDER1 );
        demandType.setRole( ROLE1 );
        demandType.setUrlContact( URL_CONTACT1 );
        demandType.setUrlInfo( URL_INFO1 );
        demandType.setUrlResource( URL_RESOURCE1 );
        demandType.setWorkgroup( WORKGROUP1 );

        // Test create
        DemandTypeHome.create( demandType );

        DemandType demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getIdDemandType(  ) );
        assertEquals( demandType.getIdDemandType(  ), demandTypeStored.getIdDemandType(  ) );
        assertEquals( demandType.getDateBegin(  ), demandTypeStored.getDateBegin(  ) );
        assertEquals( demandType.getDateEnd(  ), demandTypeStored.getDateEnd(  ) );
        assertEquals( demandType.getIdCategory(  ), demandTypeStored.getIdCategory(  ) );
        assertEquals( demandType.getLabel(  ), demandTypeStored.getLabel(  ) );
        assertEquals( demandType.getOrder(  ), demandTypeStored.getOrder(  ) );
        assertEquals( demandType.getRole(  ), demandTypeStored.getRole(  ) );
        assertEquals( demandType.getUrlContact(  ), demandTypeStored.getUrlContact(  ) );
        assertEquals( demandType.getUrlInfo(  ), demandTypeStored.getUrlInfo(  ) );
        assertEquals( demandType.getUrlResource(  ), demandTypeStored.getUrlResource(  ) );
        assertEquals( demandType.getWorkgroup(  ), demandTypeStored.getWorkgroup(  ) );

        // Test update
        dateBegin = DateUtil.formatDateLongYear( DATE_BEGIN2, _locale );
        dateEnd = DateUtil.formatDateLongYear( DATE_END2, _locale );
        demandType.setDateBegin( dateBegin );
        demandType.setDateEnd( dateEnd );
        demandType.setIdCategory( ID_CATEGORY2 );
        demandType.setLabel( LABEL2 );
        demandType.setOrder( ORDER2 );
        demandType.setRole( ROLE2 );
        demandType.setUrlContact( URL_CONTACT2 );
        demandType.setUrlInfo( URL_INFO2 );
        demandType.setUrlResource( URL_RESOURCE2 );
        demandType.setWorkgroup( WORKGROUP2 );
        DemandTypeHome.update( demandType );
        demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getIdDemandType(  ) );
        assertEquals( demandType.getIdDemandType(  ), demandTypeStored.getIdDemandType(  ) );
        assertEquals( demandType.getDateBegin(  ), demandTypeStored.getDateBegin(  ) );
        assertEquals( demandType.getDateEnd(  ), demandTypeStored.getDateEnd(  ) );
        assertEquals( demandType.getIdCategory(  ), demandTypeStored.getIdCategory(  ) );
        assertEquals( demandType.getLabel(  ), demandTypeStored.getLabel(  ) );
        assertEquals( demandType.getOrder(  ), demandTypeStored.getOrder(  ) );
        assertEquals( demandType.getRole(  ), demandTypeStored.getRole(  ) );
        assertEquals( demandType.getUrlContact(  ), demandTypeStored.getUrlContact(  ) );
        assertEquals( demandType.getUrlInfo(  ), demandTypeStored.getUrlInfo(  ) );
        assertEquals( demandType.getUrlResource(  ), demandTypeStored.getUrlResource(  ) );
        assertEquals( demandType.getWorkgroup(  ), demandTypeStored.getWorkgroup(  ) );

        // Test finders
        DemandTypeFilter dtFilter = new DemandTypeFilter(  );
        DemandTypeHome.findByFilter( dtFilter );
        dtFilter.setDateBegin( dateBegin );
        dtFilter.setDateEnd( dateEnd );
        dtFilter.setIdCategory( ID_CATEGORY2 );
        dtFilter.setLabel( LABEL2 );
        dtFilter.setOrder( ORDER2 );
        dtFilter.setRole( ROLE2 );
        dtFilter.setUrlResource( URL_RESOURCE2 );
        dtFilter.setWorkgroup( WORKGROUP2 );
        DemandTypeHome.findAll(  );
        DemandTypeHome.findDemandTypes(  );
        DemandTypeHome.findMaxOrder(  );
        DemandTypeHome.findByIdCategoryAndDate( ID_CATEGORY2, new Date(  ) );
        DemandTypeHome.findByOrder( ORDER2 );

        // Test remove
        DemandTypeHome.remove( demandType.getIdDemandType(  ) );
        demandTypeStored = DemandTypeHome.findByPrimaryKey( demandType.getIdDemandType(  ) );
        assertNull( demandTypeStored );
    }
}
