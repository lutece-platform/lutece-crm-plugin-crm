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
package fr.paris.lutece.plugins.crm.service.user;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.business.user.CRMUserFilter;
import fr.paris.lutece.plugins.crm.business.user.CRMUserHome;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


/**
 *
 * CRMUserService
 *
 */
public class CRMUserService
{
    private static final String BEAN_CRM_CRMUSERSERVICE = "crm.crmUserService";
    private CRMUserAttributesService _crmUserAttributesService;

    /**
     * Constructor
     */
    protected CRMUserService(  )
    {
    }

    /**
     * Get the instance of CRMUserService
     * @return the instance of CRMUserService
     */
    public static CRMUserService getService(  )
    {
        return SpringContextService.getBean( BEAN_CRM_CRMUSERSERVICE );
    }

    /**
     * Set the crm user attributes service
     * @param crmUserAttributesService the crm user attributes service
     */
    public void setCRMUserAttributesService( CRMUserAttributesService crmUserAttributesService )
    {
        _crmUserAttributesService = crmUserAttributesService;
    }

    /**
     * Find by primary key
     * @param nIdCRMUser the id crm user
     * @return a {@link CRMUser}
     */
    public CRMUser findByPrimaryKey( int nIdCRMUser )
    {
        CRMUser user = CRMUserHome.findByPrimaryKey( nIdCRMUser );
        user.setUserAttributes( _crmUserAttributesService.getAttributes( nIdCRMUser ) );

        return user;
    }

    /**
     * Find from a given user guid
     * @param strUserGuid the user guid
     * @return a {@link CRMUser}
     */
    public CRMUser findByUserGuid( String strUserGuid )
    {
        CRMUser user = CRMUserHome.findByUserGuid( strUserGuid );

        if ( user != null )
        {
            user.setUserAttributes( _crmUserAttributesService.getAttributes( user.getIdCRMUser(  ) ) );
        }

        return user;
    }

    /**
     * Find by primary key.
     *
     * @return a {@link CRMUser}
     */
    public List<CRMUser> findAll(  )
    {
        List<CRMUser> listUsers = CRMUserHome.findAll(  );

        for ( CRMUser user : listUsers )
        {
            user.setUserAttributes( _crmUserAttributesService.getAttributes( user.getIdCRMUser(  ) ) );
        }

        return listUsers;
    }

    /**
     * Find list ids by filter.
     *
     * @param filter the filter
     * @return the list
     */
    public List<Integer> findListIdsByFilter( CRMUserFilter filter )
    {
        return CRMUserHome.findListIdsCRMUserByFilter( filter );
    }

    /**
     * Find by list ids.
     *
     * @param listIdsCRMUser the list ids crm user
     * @return the list
     */
    public List<CRMUser> findByListIds( List<Integer> listIdsCRMUser )
    {
        List<CRMUser> listUsers = new ArrayList<CRMUser>(  );

        if ( ( listIdsCRMUser != null ) && !listIdsCRMUser.isEmpty(  ) )
        {
            listUsers = CRMUserHome.findByListIds( listIdsCRMUser );

            for ( CRMUser user : listUsers )
            {
                user.setUserAttributes( _crmUserAttributesService.getAttributes( user.getIdCRMUser(  ) ) );
            }
        }

        return listUsers;
    }

    /**
     * Create a new {@link CRMUser}
     * @param user the {@link CRMUser}
     * @return the new primary key
     */
    public int create( CRMUser user )
    {
        int nIdCRMUser = -1;

        if ( user != null )
        {
            nIdCRMUser = CRMUserHome.create( user );

            if ( user.getUserAttributes(  ) != null )
            {
                for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
                {
                    _crmUserAttributesService.create( nIdCRMUser, userAttribute.getKey(  ), userAttribute.getValue(  ) );
                }
            }
        }

        return nIdCRMUser;
    }

    /**
     * Update a {@link CRMUser}
     * @param user the {@link CRMUser}
     */
    public void update( CRMUser user )
    {
        if ( user != null )
        {
            CRMUserHome.update( user );
            // Remove its attributes first
            _crmUserAttributesService.remove( user.getIdCRMUser(  ) );

            if ( user.getUserAttributes(  ) != null )
            {
                // Recreate its attributes
                for ( Entry<String, String> userAttribute : user.getUserAttributes(  ).entrySet(  ) )
                {
                    _crmUserAttributesService.create( user.getIdCRMUser(  ), userAttribute.getKey(  ),
                        userAttribute.getValue(  ) );
                }
            }
        }
    }

    /**
     * Remove a CRMUser
     * @param nIdCRMUser the id crm user
     */
    public void remove( int nIdCRMUser )
    {
        CRMUserHome.remove( nIdCRMUser );
        // Remove its attributes
        _crmUserAttributesService.remove( nIdCRMUser );
    }
}
