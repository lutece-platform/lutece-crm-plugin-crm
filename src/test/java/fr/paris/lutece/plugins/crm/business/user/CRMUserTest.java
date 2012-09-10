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
package fr.paris.lutece.plugins.crm.business.user;

import fr.paris.lutece.test.LuteceTestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * CRMUserTest
 *
 */
public class CRMUserTest extends LuteceTestCase
{
    private static final String USER_ATTRIBUTE_KEY1 = "UserAttributeKey1";
    private static final String USER_ATTRIBUTE_KEY2 = "UserAttributeKey2";
    private static final String USER_ATTRIBUTE_KEY3 = "UserAttributeKey3";
    private static final String USER_ATTRIBUTE_KEY4 = "UserAttributeKey4";
    private static final String USER_ATTRIBUTE_VALUE1 = "UserAttributeValue1";
    private static final String USER_ATTRIBUTE_VALUE2 = "UserAttributeValue2";
    private static final String USER_ATTRIBUTE_VALUE3 = "UserAttributeValue3";
    private static final String USER_ATTRIBUTE_VALUE4 = "UserAttributeValue4";
    private static final String USER_GUID1 = "UserGuid1";
    private static final String USER_GUID2 = "UserGuid2";

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.user.CRMUser
     */
    public void testBusiness(  )
    {
        // Initialize an object
        CRMUser user = new CRMUser(  );
        user.setUserGuid( USER_GUID1 );

        Map<String, String> userAttributes = new HashMap<String, String>(  );
        userAttributes.put( USER_ATTRIBUTE_KEY1, USER_ATTRIBUTE_VALUE1 );
        userAttributes.put( USER_ATTRIBUTE_KEY2, USER_ATTRIBUTE_VALUE2 );
        user.setUserAttributes( userAttributes );

        // Test create
        CRMUserHome.create( user );

        for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
        {
            CRMUserAttributeHome.create( user.getIdCRMUser(  ), userAttribute.getKey(  ), userAttribute.getValue(  ) );
        }

        CRMUser userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        userStored.setUserAttributes( CRMUserAttributeHome.findByPrimaryKey( user.getIdCRMUser(  ) ) );
        assertEquals( user.getIdCRMUser(  ), userStored.getIdCRMUser(  ) );
        assertEquals( user.getUserGuid(  ), userStored.getUserGuid(  ) );

        for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
        {
            assertEquals( userAttribute.getValue(  ), userStored.getUserAttributeValue( userAttribute.getKey(  ) ) );
        }

        // Test update
        user.setUserGuid( USER_GUID2 );
        userAttributes = new HashMap<String, String>(  );
        userAttributes.put( USER_ATTRIBUTE_KEY3, USER_ATTRIBUTE_VALUE3 );
        userAttributes.put( USER_ATTRIBUTE_KEY4, USER_ATTRIBUTE_VALUE4 );
        user.setUserAttributes( userAttributes );
        CRMUserHome.update( user );
        CRMUserAttributeHome.remove( user.getIdCRMUser(  ) );

        for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
        {
            CRMUserAttributeHome.create( user.getIdCRMUser(  ), userAttribute.getKey(  ), userAttribute.getValue(  ) );
        }

        userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        userStored.setUserAttributes( CRMUserAttributeHome.findByPrimaryKey( user.getIdCRMUser(  ) ) );
        assertEquals( user.getIdCRMUser(  ), userStored.getIdCRMUser(  ) );
        assertEquals( user.getUserGuid(  ), userStored.getUserGuid(  ) );

        for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
        {
            assertEquals( userAttribute.getValue(  ), userStored.getUserAttributeValue( userAttribute.getKey(  ) ) );
        }

        // Test finders
        CRMUserHome.findByUserGuid( USER_GUID2 );
        CRMUserHome.findAll(  );

        CRMUserFilter filter = new CRMUserFilter(  );
        filter.setUserGuid( USER_GUID2 );
        filter.setUserAttributes( userAttributes );

        List<Integer> listIdsCRMUser = CRMUserHome.findListIdsCRMUserByFilter( filter );
        CRMUserHome.findByListIds( listIdsCRMUser );

        // Test remove
        CRMUserAttributeHome.remove( user.getIdCRMUser(  ) );
        CRMUserHome.remove( user.getIdCRMUser(  ) );
        userStored = CRMUserHome.findByPrimaryKey( user.getIdCRMUser(  ) );
        assertNull( userStored );
    }
}
