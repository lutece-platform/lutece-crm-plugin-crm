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
package fr.paris.lutece.plugins.crm.service.parameters;

import fr.paris.lutece.plugins.crm.business.parameters.AdvancedParametersHome;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.portal.service.spring.SpringContextService;


public class AdvancedParametersService
{
    //BEAN 
    private static final String BEAN_CRM_ADVANCED_PARAMETERS = "crm.advancedParametersService";

    //CONSTANTS
    private static final String STRING_TRUE = "true";

    /**
     * Constructor
     */
    protected AdvancedParametersService(  )
    {
    }

    /**
     * Get the instance of {@link DemandTypeService}
     * @return the instance of {@link DemandTypeService}
     */
    public static AdvancedParametersService getService(  )
    {
        return SpringContextService.getBean( BEAN_CRM_ADVANCED_PARAMETERS );
    }

    /**
     * Get the String value of a parameter
     * @return the string value
     */
    public String getParameterStringValueByKey( String key )
    {
        return AdvancedParametersHome.getParameterStringValueByKey( key );
    }

    /**
     * Get the boolean value for the parameter displayDraft
     * @return the string value
     */
    public Boolean isParameterValueDisplayDraftTrue( String key )
    {
        boolean ret = false;
        String strValue = AdvancedParametersHome.getParameterStringValueByKey( key );

        if ( ( strValue != null ) && strValue.equals( STRING_TRUE ) )
        {
            ret = true;
        }

        return ret;
    }

    /**
     * Modify the String value of a parameter
     */
    public void modifyParameterStringValueByKey( String key, String value )
    {
        AdvancedParametersHome.modifyParameterStringValueByKey( key, value );
    }
}
