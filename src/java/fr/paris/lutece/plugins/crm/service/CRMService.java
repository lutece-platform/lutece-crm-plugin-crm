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
package fr.paris.lutece.plugins.crm.service;

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.service.demand.DemandService;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import org.apache.commons.lang.StringUtils;


/**
 *
 * CRMService
 *
 */
public final class CRMService
{
    private static final String BEAN_CRM_CRMSERVICE = "crm.crmService";
    private NotificationService _notificationService;
    private DemandService _demandService;

    /**
     * Private constructor
     */
    private CRMService(  )
    {
    }

    /**
     * Get the instance of {@link CRMService}
     * @return the instance
     */
    public static CRMService getService(  )
    {
        return (CRMService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME, BEAN_CRM_CRMSERVICE );
    }

    /**
     * Register the demand
     * @param nIdDemand the id demand
     * @param nIdDemandType the id demand type
     * @param strUserGuid the user guid
     * @param strUrlResource the url resource
     * @param strStatus the status of the demand
     */
    public void registerDemand( int nIdDemand, int nIdDemandType, String strUserGuid, String strUrlResource,
        String strStatus )
    {
        Demand demand = new Demand(  );
        demand.setIdDemand( nIdDemand );
        demand.setIdDemandType( nIdDemandType );
        demand.setUserGuid( strUserGuid );
        demand.setConfigData( StringUtils.isNotEmpty( strUrlResource ) ? strUrlResource : StringUtils.EMPTY );
        demand.setStatusText( StringUtils.isNotEmpty( strStatus ) ? strStatus : StringUtils.EMPTY );

        _demandService.create( demand );
    }

    /**
     * Set the status of the demand
     * @param nIdDemand the id demand
     * @param strStatus the status of the demand
     */
    public void setStatus( int nIdDemand, String strStatus )
    {
        Demand demand = _demandService.findByPrimaryKey( nIdDemand );

        if ( demand != null )
        {
            demand.setStatusText( strStatus );
            _demandService.update( demand );
        }
    }

    /**
     * Create a notification for a demand
     * @param nIdDemand the id demand
     * @param strObject the object of the notification
     * @param strMessage the message of the notification
     */
    public void notify( int nIdDemand, String strObject, String strMessage )
    {
        Notification notification = new Notification(  );
        notification.setIdDemand( nIdDemand );
        notification.setObject( strObject );
        notification.setMessage( strMessage );
        _notificationService.create( notification );
    }

    /**
     * Set the notification service
     * @param notificationService the notification service
     */
    public void setNotificationService( NotificationService notificationService )
    {
        _notificationService = notificationService;
    }

    /**
     * Set the demand service
     * @param demandService the demand service
     */
    public void setDemandService( DemandService demandService )
    {
        _demandService = demandService;
    }
}
