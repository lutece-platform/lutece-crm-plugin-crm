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

import fr.paris.lutece.plugins.crm.business.demand.Demand;
import fr.paris.lutece.plugins.crm.business.notification.Notification;
import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.service.demand.DemandService;
import fr.paris.lutece.plugins.crm.service.notification.NotificationService;
import fr.paris.lutece.plugins.crm.service.user.CRMUserService;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

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
    private CRMUserService _crmUserService;

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
        return SpringContextService.getBean( BEAN_CRM_CRMSERVICE );
    }

    /**
     * Register the demand
     * @param nIdDemandType the id demand type
     * @param strUserGuid the user guid
     * @param strData the data
     * @param strStatusText the status of the demand
     * @param nIdStatusCRM the id status crm
     * @return the newly created id demand
     */
    public int registerDemand( int nIdDemandType, String strUserGuid, String strData, String strStatusText,
        int nIdStatusCRM )
    {
        int nIdDemand = -1;
        CRMUser crmUser = _crmUserService.findByUserGuid( strUserGuid );

        if ( crmUser != null )
        {
            nIdDemand = registerDemand( nIdDemandType, crmUser.getIdCRMUser(  ), strData, strStatusText, nIdStatusCRM );
        }

        return nIdDemand;
    }

    /**
     * Register the demand
     * @param nIdDemandType the id demand type
     * @param nIdCRMUser the ID CRM user
     * @param strData the data
     * @param strStatusText the status of the demand
     * @param nIdStatusCRM the id status crm
     * @return the newly created id demand
     */
    public int registerDemand( int nIdDemandType, int nIdCRMUser, String strData, String strStatusText, int nIdStatusCRM )
    {
      
    	return registerDemand(null,nIdDemandType, nIdCRMUser, strData, strStatusText, nIdStatusCRM);
    }
    
    
    /**
     * Register the demand
     * @param strRemoteId the remoteId
     * @param nIdDemandType the id demand type
     * @param nIdCRMUser the ID CRM user
     * @param strData the data
     * @param strStatusText the status of the demand
     * @param nIdStatusCRM the id status crm
     *  @return the newly created id demand
     */
    public int registerDemand(String strRemoteId, int nIdDemandType, int nIdCRMUser, String strData, String strStatusText, int nIdStatusCRM )
    {
        int nIdDemand = -1;
        CRMUser crmUser = _crmUserService.findByPrimaryKey( nIdCRMUser );

        if ( crmUser != null )
        {
            Demand demand = new Demand(  );
            demand.setIdDemandType( nIdDemandType );
            demand.setIdCRMUser( nIdCRMUser );
            demand.setData( StringUtils.isNotEmpty( strData ) ? strData : StringUtils.EMPTY );
            demand.setStatusText( StringUtils.isNotEmpty( strStatusText ) ? strStatusText : StringUtils.EMPTY );
            demand.setIdStatusCRM( nIdStatusCRM );
            demand.setRemoteId(strRemoteId);
            //Test if the multiple key Remote Id and Id Demand Type do not already exist 
            if(_demandService.findByRemoteKey(strRemoteId, nIdDemandType) == null)
            {
            	nIdDemand = _demandService.create( demand );
            }
        }

        return nIdDemand;
    }

    /**
     * Set the status of the demand
     * @param nIdDemand the id demand
     * @param strData the data
     * @param strStatusText the status of the demand
     * @param nIdStatusCRM the id status crm
     */
    public void setStatus( int nIdDemand, String strData, String strStatusText, int nIdStatusCRM )
    {
        Demand demand = _demandService.findByPrimaryKey( nIdDemand );

        if ( demand != null )
        {
            if ( StringUtils.isNotBlank( strData ) )
            {
                demand.setData( strData );
            }

            if ( nIdStatusCRM != CRMConstants.INVALID_ID_INT )
            {
                demand.setIdStatusCRM( nIdStatusCRM );
            }

            demand.setStatusText( strStatusText );
            _demandService.update( demand );
        }
    }

    /**
     * Delete a demand
     * @param nIdDemand the id demand
     */
    public void deleteDemand( int nIdDemand )
    {
        _demandService.remove( nIdDemand );
    }

    /**
     * Create a notification for a demand
     * @param nIdDemand the id demand
     * @param strObject the object of the notification
     * @param strMessage the message of the notification
     * @param strSender the sender
     */
    public void notify( int nIdDemand, String strObject, String strMessage, String strSender )
    {
        String strNotificationSender = StringUtils.EMPTY;

        if ( StringUtils.isBlank( strSender ) )
        {
            // If the sender is not filled, the email from webmaster.properties is filled instead
            strNotificationSender = AppPropertiesService.getProperty( CRMConstants.PROPERTY_WEBMASTER_EMAIL );
        }
        else
        {
            strNotificationSender = strSender;
        }

        Notification notification = new Notification(  );
        notification.setIdDemand( nIdDemand );
        notification.setObject( strObject );
        notification.setMessage( strMessage );
        notification.setSender( strNotificationSender );
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

    /**
     * Set the crm user service
     * @param crmUserService the crm user service
     */
    public void setCRMUserService( CRMUserService crmUserService )
    {
        _crmUserService = crmUserService;
    }
}
