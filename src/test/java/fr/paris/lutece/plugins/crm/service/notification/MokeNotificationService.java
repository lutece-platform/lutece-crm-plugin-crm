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
package fr.paris.lutece.plugins.crm.service.notification;

import fr.paris.lutece.plugins.crm.business.notification.MokeNotification;
import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.business.notification.NotificationFilter;
import fr.paris.lutece.plugins.crm.business.notification.NotificationHome;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * MokeNotificationService
 *
 */
public class MokeNotificationService extends NotificationService
{
    private static final int ID_NOTIFICATION1 = 1;
    private static final int ID_NOTIFICATION2 = 2;

    /**
     * {@inheritDoc}
     */
    public Notification findByPrimaryKey( int strIdNotification )
    {
        Notification notification = new MokeNotification( ID_NOTIFICATION1 );
        System.out.println( trace( notification ) );

        return notification;
    }

    /**
     * {@inheritDoc}
     */
    public int create( Notification notification )
    {
        notification.setIdNotification( ID_NOTIFICATION1 );
        notification.setIsRead( false );
        notification.setDateCreation( new Timestamp( new Date( ).getTime( ) ) );

        System.out.println( trace( notification ) );

        return notification.getIdNotification( );
    }

    /**
     * {@inheritDoc}
     */
    public void update( Notification notification )
    {
        if ( notification != null )
        {
            notification.setDateCreation( new Timestamp( new Date( ).getTime( ) ) );
        }

        System.out.println( trace( notification ) );
    }

    /**
     * {@inheritDoc}
     */
    public void remove( int nIdNotification )
    {
        System.out.println( trace( ) );
    }

    /**
     * {@inheritDoc}
     */
    public void removeByIdDemand( int nIdDemand )
    {
        System.out.println( trace( ) );
    }

    /**
     * {@inheritDoc}
     */
    public List<Notification> findAll( )
    {
        List<Notification> listNotifications = new ArrayList<Notification>( );
        listNotifications.add( new MokeNotification( ID_NOTIFICATION1 ) );
        listNotifications.add( new MokeNotification( ID_NOTIFICATION2 ) );

        System.out.println( trace( listNotifications ) );

        return listNotifications;
    }

    /**
     * {@inheritDoc}
     */
    public List<Notification> findByFilter( NotificationFilter nFilter )
    {
        List<Notification> listNotifications = new ArrayList<Notification>( );
        listNotifications.add( new MokeNotification( ID_NOTIFICATION1 ) );
        listNotifications.add( new MokeNotification( ID_NOTIFICATION2 ) );

        System.out.println( trace( listNotifications, nFilter ) );

        return listNotifications;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumberUnreadNotifications( int nIdDemand )
    {
        NotificationFilter nFilter = new NotificationFilter( );
        nFilter.setIdDemand( nIdDemand );
        nFilter.setIsRead( false );

        List<Notification> listNotifications = NotificationHome.findByFilter( nFilter );

        System.out.println( trace( listNotifications ) );

        return listNotifications.size( );
    }

    /**
     * {@inheritDoc}
     */
    public int getNumberNotifications( int nIdDemand )
    {
        NotificationFilter nFilter = new NotificationFilter( );
        nFilter.setIdDemand( nIdDemand );

        List<Notification> listNotifications = NotificationHome.findByFilter( nFilter );

        System.out.println( trace( listNotifications ) );

        return listNotifications.size( );
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
     * @param notification
     *            notification
     * @return trace
     */
    private String trace( Notification notification )
    {
        List<Notification> listNotifications = new ArrayList<Notification>( );
        listNotifications.add( notification );

        return trace( listNotifications, null );
    }

    /**
     * Trace
     * 
     * @param listNotifications
     *            the list of notifications
     * @return trace
     */
    private String trace( List<Notification> listNotifications )
    {
        return trace( listNotifications, null );
    }

    /**
     * Trace
     * 
     * @param listNotifications
     *            the list of notifications
     * @return trace
     */
    private String trace( List<Notification> listNotifications, NotificationFilter nFilter )
    {
        StringBuilder sbTrace = new StringBuilder( );
        sbTrace.append( "\n ---------------------- Notification Service -------------------" );
        sbTrace.append( "\nMethod name : " + Thread.currentThread( ).getStackTrace( ) [2].getMethodName( ) );

        if ( ( listNotifications != null ) && ( listNotifications.size( ) > 0 ) )
        {
            for ( Notification notification : listNotifications )
            {
                sbTrace.append( "\n-- Notification --" );
                sbTrace.append( "\nid_notification : " + notification.getIdNotification( ) );
                sbTrace.append( "\ndate_creation : " + notification.getDateCreation( ) );
                sbTrace.append( "\nid_demand : " + notification.getIdDemand( ) );
                sbTrace.append( "\nis_read : " + notification.isRead( ) );
                sbTrace.append( "\nmessage : " + notification.getMessage( ) );
                sbTrace.append( "\nobject : " + notification.getObject( ) );
                sbTrace.append( "\nsender : " + notification.getSender( ) );
            }
        }

        if ( nFilter != null )
        {
            sbTrace.append( "\n-- Notification Filter --" );
            sbTrace.append( "\nid_demand : " + nFilter.getIdDemand( ) );
            sbTrace.append( "\nis_wide_search : " + nFilter.getIsWideSearch( ) );
        }

        sbTrace.append( "\n --------------------------------------------------------------------" );

        return sbTrace.toString( );
    }
}
