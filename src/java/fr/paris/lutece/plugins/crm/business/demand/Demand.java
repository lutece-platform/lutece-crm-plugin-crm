/*
 * Copyright (c) 2002-2013, Mairie de Paris
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

import fr.paris.lutece.plugins.crm.service.notification.NotificationService;

import java.sql.Timestamp;


/**
 *
 * Demand
 *
 */
public class Demand
{
    private int _nIdDemand;
    private int _nIdDemandType;
    private int _nIdCRMUser;
    private String _strStatusText;
    private int _nIdStatusCRM;
    private String _strData;
    private Timestamp _dateModification;

    /**
     * Set the ID demand
     * @param nIdDemand the ID demand
     */
    public void setIdDemand( int nIdDemand )
    {
        _nIdDemand = nIdDemand;
    }

    /**
     * Get the ID demand
     * @return the ID demand
     */
    public int getIdDemand(  )
    {
        return _nIdDemand;
    }

    /**
     * Set the status
     * @param strStatusText the status
     */
    public void setStatusText( String strStatusText )
    {
        _strStatusText = strStatusText;
    }

    /**
     * Get the status
     * @return the status
     */
    public String getStatusText(  )
    {
        return _strStatusText;
    }

    /**
     * Set the ID demand type
     * @param nIdDemandType the ID demand type
     */
    public void setIdDemandType( int nIdDemandType )
    {
        _nIdDemandType = nIdDemandType;
    }

    /**
     * Get the ID demand type
     * @return the ID demand type
     */
    public int getIdDemandType(  )
    {
        return _nIdDemandType;
    }

    /**
     * Set the user crm id
     * @param nIdCRMUser the user crm id
     */
    public void setIdCRMUser( int nIdCRMUser )
    {
        _nIdCRMUser = nIdCRMUser;
    }

    /**
     * Get the user user crm id
     * @return the user crm id
     */
    public int getIdCRMUser(  )
    {
        return _nIdCRMUser;
    }

    /**
     * Returns the Date of the last Modification
     * @return The Date of the last Modification
     */
    public Timestamp getDateModification(  )
    {
        return _dateModification;
    }

    /**
     * Sets the Date  of the last Modification
     * @param dateModification The Date of the last Modification
     */
    public void setDateModification( Timestamp dateModification )
    {
        _dateModification = dateModification;
    }

    /**
     * Get the number of unread notifications
     * @return the number of unread notifications
     */
    public int getNumberUnreadNotifications(  )
    {
        return NotificationService.getService(  ).getNumberUnreadNotifications( _nIdDemand );
    }

    /**
     * Get the number of notifications
     * @return the number of notifications
     */
    public int getNumberNotifications(  )
    {
        return NotificationService.getService(  ).getNumberNotifications( _nIdDemand );
    }

    /**
     * Set the data
     * @param strData the data
     */
    public void setData( String strData )
    {
        _strData = strData;
    }

    /**
     * Get the data
     * @return the data
     */
    public String getData(  )
    {
        return _strData;
    }

    /**
     * Set the id status CRM
     * @param nIdStatusCRM the id status crm
     */
    public void setIdStatusCRM( int nIdStatusCRM )
    {
        _nIdStatusCRM = nIdStatusCRM;
    }

    /**
     * Get the id status crm
     * @return the id status crm
     */
    public int getIdStatusCRM(  )
    {
        return _nIdStatusCRM;
    }
}
