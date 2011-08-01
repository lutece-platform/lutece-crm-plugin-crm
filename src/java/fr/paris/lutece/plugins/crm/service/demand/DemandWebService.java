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

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.plugins.crm.service.signrequest.CRMRequestAuthenticatorService;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * DemandWebService
 *
 */
public final class DemandWebService
{
    private static final String BEAN_CRM_DEMANDWEBSERVICE = "crm.demandWebService";

    /**
     * Private constructor
     */
    private DemandWebService(  )
    {
    }

    /**
     * Get an instance of {@link DemandWebService}
     * @return an instance of {@link DemandWebService}
     */
    public static DemandWebService getService(  )
    {
        return (DemandWebService) SpringContextService.getPluginBean( CRMPlugin.PLUGIN_NAME, BEAN_CRM_DEMANDWEBSERVICE );
    }

    /**
     * Remove a demand and its resource
     * @param strUrl the url of the resource
     * @param nIdDemand the id demand
     * @param strData the data
     * @throws HttpAccessException exception when connexion error
     */
    public void sendRemoveDraft( String strUrl, int nIdDemand, String strData )
        throws HttpAccessException
    {
        // List parameters to post
        Map<String, String> params = new HashMap<String, String>(  );
        params.put( CRMConstants.PARAMETER_ACTION, CRMConstants.ACTION_DO_REMOVE_DRAFT );
        params.put( CRMConstants.PARAMETER_ID_DEMAND, Integer.toString( nIdDemand ) );
        params.put( CRMConstants.PARAMETER_DEMAND_DATA, strData );

        // List elements to include to the signature
        List<String> listElements = new ArrayList<String>(  );
        listElements.add( Integer.toString( nIdDemand ) );
        listElements.add( strData );

        HttpAccess httpAccess = new HttpAccess(  );
        httpAccess.doPost( strUrl, params, CRMRequestAuthenticatorService.getRequestAuthenticatorForWS(  ), listElements );
    }
}
