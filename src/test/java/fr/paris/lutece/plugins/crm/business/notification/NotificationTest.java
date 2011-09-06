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
package fr.paris.lutece.plugins.crm.business.notification;

import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.util.date.DateUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Locale;


/**
 *
 * NotificationTest
 *
 */
public class NotificationTest extends LuteceTestCase
{
    private static final String DATE1 = "01/09/2011";
    private static final String DATE2 = "10/10/2011";
    private static final int ID_DEMAND1 = 1;
    private static final boolean IS_READ1 = false;
    private static final boolean IS_READ2 = true;
    private static final String MESSAGE1 = "Message1";
    private static final String MESSAGE2 = "Message2";
    private static final String OBJECT1 = "Object1";
    private static final String OBJECT2 = "Object2";
    private static final String SENDER1 = "Sender1";
    private static final String SENDER2 = "Sender2";
    private static Locale _locale = Locale.getDefault(  );

    /**
     * Test business of class fr.paris.lutece.plugins.crm.business.notification.Notification
     */
    public void testBusiness(  )
    {
        // Initialize an object
        Date date = DateUtil.formatDateLongYear( DATE1, _locale );
        Timestamp dateCreation = new Timestamp( date.getTime(  ) );
        Notification notification = new Notification(  );
        notification.setDateCreation( dateCreation );
        notification.setIdDemand( ID_DEMAND1 );
        notification.setIsRead( IS_READ1 );
        notification.setMessage( MESSAGE1 );
        notification.setObject( OBJECT1 );
        notification.setSender( SENDER1 );

        // Test create
        NotificationHome.create( notification );

        Notification notificationStored = NotificationHome.findByPrimaryKey( notification.getIdNotification(  ) );
        assertEquals( notification.getIdNotification(  ), notificationStored.getIdNotification(  ) );
        assertEquals( notification.getDateCreation(  ), notificationStored.getDateCreation(  ) );
        assertEquals( notification.getIdDemand(  ), notificationStored.getIdDemand(  ) );
        assertEquals( notification.isRead(  ), notificationStored.isRead(  ) );
        assertEquals( notification.getMessage(  ), notificationStored.getMessage(  ) );
        assertEquals( notification.getObject(  ), notificationStored.getObject(  ) );
        assertEquals( notification.getSender(  ), notificationStored.getSender(  ) );

        // Test update
        date = DateUtil.formatDateLongYear( DATE2, _locale );
        dateCreation = new Timestamp( date.getTime(  ) );
        notification.setDateCreation( dateCreation );
        notification.setIsRead( IS_READ2 );
        notification.setMessage( MESSAGE2 );
        notification.setObject( OBJECT2 );
        notification.setSender( SENDER2 );
        NotificationHome.update( notification );
        notificationStored = NotificationHome.findByPrimaryKey( notification.getIdNotification(  ) );
        assertEquals( notification.getIdNotification(  ), notificationStored.getIdNotification(  ) );
        assertEquals( notification.getDateCreation(  ), notificationStored.getDateCreation(  ) );
        assertEquals( notification.getIdDemand(  ), notificationStored.getIdDemand(  ) );
        assertEquals( notification.isRead(  ), notificationStored.isRead(  ) );
        assertEquals( notification.getMessage(  ), notificationStored.getMessage(  ) );
        assertEquals( notification.getObject(  ), notificationStored.getObject(  ) );
        assertEquals( notification.getSender(  ), notificationStored.getSender(  ) );

        // Test finders
        NotificationFilter nFilter = new NotificationFilter(  );
        nFilter.setIdDemand( ID_DEMAND1 );
        nFilter.setIsRead( IS_READ2 );
        NotificationHome.findByFilter( nFilter );
        NotificationHome.findAll(  );

        // Test remove
        NotificationHome.remove( notification.getIdNotification(  ) );
        notificationStored = NotificationHome.findByPrimaryKey( notification.getIdNotification(  ) );
        assertNull( notificationStored );
        NotificationHome.create( notification );
        NotificationHome.removeByIdDemand( notification.getIdDemand(  ) );
        notificationStored = NotificationHome.findByPrimaryKey( notification.getIdNotification(  ) );
        assertNull( notificationStored );
    }
}
