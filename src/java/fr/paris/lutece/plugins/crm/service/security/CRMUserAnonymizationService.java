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
package fr.paris.lutece.plugins.crm.service.security;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.service.user.CRMUserService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.service.util.CryptoService;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * CRMUserAnonymizationService
 *
 */
public class CRMUserAnonymizationService implements IAnonymizationService
{
    /** The Constant BEAN_SERVICE. */
    public static final String BEAN_SERVICE = "crm.crmUserAnonymizationService";

    // PROPERTIES
    private static final String PROPERTY_ANONYMIZATION_ENCRYPT_ALGO = "security.anonymization.encryptAlgo";

    // CONSTANTS
    private static final String CONSTANT_DEFAULT_ENCRYPT_ALGO = "SHA-256";

    /**
     * {@inheritDoc}
     */
    @Override
    public void anonymizeUser( int nIdUser, Locale locale )
    {
        CRMUser user = CRMUserService.getService( ).findByPrimaryKey( nIdUser );

        if ( user != null )
        {
            String strEncryptionAlgorithme = AppPropertiesService.getProperty( PROPERTY_ANONYMIZATION_ENCRYPT_ALGO, CONSTANT_DEFAULT_ENCRYPT_ALGO );
            user.setUserGuid( CryptoService.encrypt( user.getUserGuid( ), strEncryptionAlgorithme ) );

            Map<String, String> userInfos = user.getUserAttributes( );

            for ( Entry<String, String> param : userInfos.entrySet( ) )
            {
                userInfos.put( param.getKey( ), CryptoService.encrypt( param.getValue( ), strEncryptionAlgorithme ) );
            }

            user.setUserAttributes( userInfos );
            user.setStatus( CRMUser.STATUS_ANONYMIZED );
            CRMUserService.getService( ).update( user );
        }
    }
}
