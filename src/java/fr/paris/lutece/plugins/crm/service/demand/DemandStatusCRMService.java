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
package fr.paris.lutece.plugins.crm.service.demand;

import fr.paris.lutece.plugins.crm.business.demand.DemandStatusCRM;
import fr.paris.lutece.plugins.crm.business.demand.DemandStatusCRMHome;
import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;
import java.util.Locale;


/**
 *
 * DemandStatusCRMService
 *
 */
public final class DemandStatusCRMService
{
    private static final String BEAN_CRM_DEMANDSTATUSCRMSERVICE = "crm.demandStatusCRMService";
    private List<DemandStatusCRM> _listStatusCRM;

    /**
     * Private constructor
     */
    private DemandStatusCRMService(  )
    {
    }

    /**
     * Get the instance of {@link DemandStatusCRMService}
     * @return the instance of {@link DemandStatusCRMService}
     */
    public static DemandStatusCRMService getService(  )
    {
        return (DemandStatusCRMService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME,
            BEAN_CRM_DEMANDSTATUSCRMSERVICE );
    }

    /**
     * Get all status CRM, then put the list in session
     * @param locale {@link Locale}
     * @return a list of {@link DemandStatusCRM}
     */
    public List<DemandStatusCRM> getAllStatusCRM( Locale locale )
    {
        if ( _listStatusCRM == null )
        {
            _listStatusCRM = DemandStatusCRMHome.findAll(  );

            for ( DemandStatusCRM statusCRM : _listStatusCRM )
            {
                statusCRM.setLocale( locale );
            }
        }

        return _listStatusCRM;
    }

    /**
     * Get the status crm given an id status
     * @param nIdStatusCRM the id status
     * @param locale the {@link Locale}
     * @return a {@link DemandStatusCRM}
     */
    public DemandStatusCRM getStatusCRM( int nIdStatusCRM, Locale locale )
    {
        DemandStatusCRM statusCRM = DemandStatusCRMHome.find( nIdStatusCRM );
        statusCRM.setLocale( locale );

        return statusCRM;
    }
}
