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
package fr.paris.lutece.plugins.crm.service.listener;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.util.IListener;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.RemovalListenerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to manage listeners of modifications on CRMUsers objects
 */
public class CRMUserModificationListenerService extends RemovalListenerService
{
    private static final String BEAN_NAME = "crm.crmUserListenerService";
    private static CRMUserModificationListenerService _instance;
    private List<IListener<CRMUser>> _listRegisteredListeners = new ArrayList<IListener<CRMUser>>( );

    /**
     * Private constructor
     */
    private CRMUserModificationListenerService( )
    {
    }

    /**
     * Get the instance of the service
     * 
     * @return The instance of the service
     */
    public static CRMUserModificationListenerService getService( )
    {
        if ( _instance == null )
        {
            _instance = SpringContextService.getBean( BEAN_NAME );
        }

        return _instance;
    }

    /**
     * Register a new Removal listener
     * 
     * @param listener
     *            The listener to register
     */
    public void registerListener( IListener<CRMUser> listener )
    {
        if ( listener != null )
        {
            _listRegisteredListeners.add( listener );
        }
    }

    /**
     * Notify listeners with a CRM user.
     * 
     * @param crmUser
     *            The user to send to listeners
     * @param strEventName
     *            Name of the event that caused the notification
     */
    public void notifyListeners( CRMUser crmUser, String strEventName )
    {
        if ( ( _listRegisteredListeners != null ) && ( _listRegisteredListeners.size( ) > 0 ) )
        {
            for ( IListener<CRMUser> listener : _listRegisteredListeners )
            {
                listener.notifyListener( crmUser, strEventName );
            }
        }
    }
}
