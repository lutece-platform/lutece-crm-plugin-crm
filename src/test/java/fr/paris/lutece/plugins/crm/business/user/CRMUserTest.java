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

import fr.paris.lutece.test.LuteceTestCase;


/**
 *
 * CRMUserTest
 *
 */
public class CRMUserTest extends LuteceTestCase
{
    private static final String EMAIL1 = "Email1";
    private static final String EMAIL2 = "Email2";
    private static final String FIRST_NAME1 = "FirstName1";
    private static final String FIRST_NAME2 = "FirstName2";
    private static final String LAST_NAME1 = "LastName1";
    private static final String LAST_NAME2 = "LastName2";
    private static final String PHONE_NUMBER1 = "PhoneNumber1";
    private static final String PHONE_NUMBER2 = "PhoneNumber2";
    private static final String USER_GUID1 = "UserGuid1";
    private static final String USER_GUID2 = "UserGuid2";

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.user.CRMUser
     */
    public void testBusiness(  )
    {
        // Initialize an object
        CRMUser user = new CRMUser(  );
        user.setEmail( EMAIL1 );
        user.setFirstName( FIRST_NAME1 );
        user.setLastName( LAST_NAME1 );
        user.setPhoneNumber( PHONE_NUMBER1 );
        user.setUserGuid( USER_GUID1 );

        // Test create
        CRMUserHome.create( user );

        CRMUser userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        assertEquals( user.getIdCRMUser(  ), userStored.getIdCRMUser(  ) );
        assertEquals( user.getEmail(  ), userStored.getEmail(  ) );
        assertEquals( user.getFirstName(  ), userStored.getFirstName(  ) );
        assertEquals( user.getLastName(  ), userStored.getLastName(  ) );
        assertEquals( user.getPhoneNumber(  ), userStored.getPhoneNumber(  ) );
        assertEquals( user.getUserGuid(  ), userStored.getUserGuid(  ) );

        // Test update
        user.setEmail( EMAIL2 );
        user.setFirstName( FIRST_NAME2 );
        user.setLastName( LAST_NAME2 );
        user.setPhoneNumber( PHONE_NUMBER2 );
        user.setUserGuid( USER_GUID2 );
        CRMUserHome.update( user );
        userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        assertEquals( user.getIdCRMUser(  ), userStored.getIdCRMUser(  ) );
        assertEquals( user.getEmail(  ), userStored.getEmail(  ) );
        assertEquals( user.getFirstName(  ), userStored.getFirstName(  ) );
        assertEquals( user.getLastName(  ), userStored.getLastName(  ) );
        assertEquals( user.getPhoneNumber(  ), userStored.getPhoneNumber(  ) );
        assertEquals( user.getUserGuid(  ), userStored.getUserGuid(  ) );

        // Test finders
        CRMUserHome.findByUserGuid( USER_GUID2 );

        // Test remove
        CRMUserHome.remove( user.getIdCRMUser(  ) );
        userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        assertNull( userStored );
    }
}
