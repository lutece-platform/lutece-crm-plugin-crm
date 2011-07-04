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
package fr.paris.lutece.plugins.crm.service.notification;

import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.business.notification.NotificationFilter;
import fr.paris.lutece.plugins.crm.business.notification.NotificationHome;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;


/**
 *
 * NotificationService
 *
 */
public final class NotificationService
{
    private static final String BEAN_CRM_NOTIFICATIONSERVICE = "crm.notificationService";

    /**
     * Private constructor
     */
    private NotificationService(  )
    {
    }

    /**
     * Get the instance of {@link NotificationService}
     * @return the instance of {@link NotificationService}
     */
    public static NotificationService getService(  )
    {
        return (NotificationService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME,
            BEAN_CRM_NOTIFICATIONSERVICE );
    }

    /**
     * Find the notification by its primary key
     * @param strIdNotification the id notification
     * @return a {@link Notification}
     */
    public Notification findByPrimaryKey( int strIdNotification )
    {
        return NotificationHome.findByPrimaryKey( strIdNotification );
    }

    /**
     * Create a notification
     * @param notification the notification
     * @return the newly created notification id
     */
    public int create( Notification notification )
    {
        int nNewPrimaryKey = -1;

        if ( notification != null )
        {
        	notification.setIsRead( false );
            notification.setDateCreation( new Timestamp( new Date(  ).getTime(  ) ) );
            nNewPrimaryKey = NotificationHome.create( notification );
        }

        return nNewPrimaryKey;
    }

    /**
     * Update the notification
     * @param notification the notification
     */
    public void update( Notification notification )
    {
        if ( notification != null )
        {
            NotificationHome.update( notification );
        }
    }

    /**
     * Remove the notification
     * @param nIdNotification the id notification
     */
    public void remove( int nIdNotification )
    {
        NotificationHome.remove( nIdNotification );
    }

    /**
     * Remove the notifications from a given ID demand
     * @param nIdDemand the id demand
     */
    public void removeByIdDemand( int nIdDemand )
    {
        NotificationHome.removeByIdDemand( nIdDemand );
    }

    /**
     * Find all notifications
     * @return a list of {@link Notification}
     */
    public List<Notification> findAll(  )
    {
        return NotificationHome.findAll(  );
    }

    /**
     * Find notifications by a filter
     * @param nFilter the filter
     * @return a list of {@link Notification}
     */
    public List<Notification> findByFilter( NotificationFilter nFilter )
    {
        return NotificationHome.findByFilter( nFilter );
    }

    /**
     * Get the number of unread notifications of a demand
     * @param nIdDemand the id demand
     * @return the number of unread notifications
     */
    public int getNumberUnreadNotifications( int nIdDemand )
    {
        NotificationFilter nFilter = new NotificationFilter(  );
        nFilter.setIdDemand( nIdDemand );
        nFilter.setIsRead( false );

        List<Notification> listNotifications = NotificationHome.findByFilter( nFilter );

        return listNotifications.size(  );
    }

    /**
     * Get the number of notifications of a demand
     * @param nIdDemand the id demand
     * @return the number of notifications
     */
    public int getNumberNotifications( int nIdDemand )
    {
        NotificationFilter nFilter = new NotificationFilter(  );
        nFilter.setIdDemand( nIdDemand );

        List<Notification> listNotifications = NotificationHome.findByFilter( nFilter );

        return listNotifications.size(  );
    }
}
