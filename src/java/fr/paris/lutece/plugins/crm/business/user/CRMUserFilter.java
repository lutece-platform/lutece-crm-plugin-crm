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
package fr.paris.lutece.plugins.crm.business.user;

import fr.paris.lutece.plugins.crm.service.user.CRMUserAttributesService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.sql.DAOUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * CRMUserAttributeFilter
 *
 */
public class CRMUserFilter implements Serializable
{
    public static final int MUST_BE_UPDATED_TRUE = 1;
    public static final int MUST_BE_UPDATED_FALSE = 0;
    private static final long serialVersionUID = -4489731073268811381L;
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_OR = " OR ";
    private static final String SQL_PERCENT = "%";
    // SQL FILTERS
    private static final String SQL_FILTER_ID_CRM_USER = " cu.id_crm_user = ? ";
    private static final String SQL_FILTER_USER_GUID = " cu.user_guid LIKE ? ";
    private static final String SQL_FILTER_STATUS_ACTIVE = " cu.status = ? ";
    private static final String SQL_FILTER_MUST_BE_UPDATED = " cu.must_be_updated = ? ";
    private int _nIdCRMUser;
    private String _strUserGuid;
    private Map<String, String> _userInfos;
    private int _nStatus = -1;
    private int _nMustBeUpdated = -1;
    private boolean _bIsWideSearch;

    /**
     * Init.
     *
     * @param request
     *            the request
     */
    public void init( HttpServletRequest request )
    {
        try
        {
            BeanUtils.populate( this, request.getParameterMap( ) );
        }
        catch( IllegalAccessException e )
        {
            AppLogService.error( "Unable to fetch data from request", e );
        }
        catch( InvocationTargetException e )
        {
            AppLogService.error( "Unable to fetch data from request", e );
        }

        // LinkedHashMap to keep the FIFO behavior
        _userInfos = new HashMap<String, String>( );

        for ( String strAttributeKey : CRMUserAttributesService.getService( ).getUserAttributeKeys( ) )
        {
            String strParamValue = request.getParameter( strAttributeKey );

            if ( StringUtils.isNotBlank( strParamValue ) )
            {
                _userInfos.put( strAttributeKey, strParamValue );
            }
        }
    }

    /**
     * Set the id crm user
     * 
     * @param nIdCRMUser
     *            the id crm user
     */
    public void setIdCRMUser( int nIdCRMUser )
    {
        _nIdCRMUser = nIdCRMUser;
    }

    /**
     * Get the id crm user
     * 
     * @return the id crm user
     */
    public int getIdCRMUser( )
    {
        return _nIdCRMUser;
    }

    /**
     * Contains id crm user.
     *
     * @return true, if successful
     */
    public boolean containsIdCRMUser( )
    {
        return _nIdCRMUser > 0;
    }

    /**
     * Set the user guid
     * 
     * @param strUserGuid
     *            the user guid
     */
    public void setUserGuid( String strUserGuid )
    {
        _strUserGuid = strUserGuid;
    }

    /**
     * Get the user guid
     * 
     * @return the user guid
     */
    public String getUserGuid( )
    {
        return _strUserGuid;
    }

    /**
     * Contains user guid.
     *
     * @return true, if successful
     */
    public boolean containsUserGuid( )
    {
        return StringUtils.isNotBlank( _strUserGuid );
    }

    /**
     * Set the user attributes
     * 
     * @param userInfos
     *            the user attributes
     */
    public void setUserAttributes( Map<String, String> userInfos )
    {
        _userInfos = userInfos;
    }

    /**
     * Get the user attributes
     * 
     * @return the user attributes
     */
    public Map<String, String> getUserAttributes( )
    {
        return _userInfos;
    }

    /**
     * Contains user attributes.
     *
     * @return true, if successful
     */
    public boolean containsUserAttributes( )
    {
        return ( _userInfos != null ) && !_userInfos.isEmpty( );
    }

    /**
     * Get the user attribute value
     * 
     * @param strUserAttributeKey
     *            the key
     * @return the user attribute value
     */
    public String getUserAttributeValue( String strUserAttributeKey )
    {
        String strUserInfoValue = StringUtils.EMPTY;

        if ( _userInfos != null )
        {
            strUserInfoValue = _userInfos.get( strUserAttributeKey );
        }

        return StringUtils.isNotBlank( strUserInfoValue ) ? strUserInfoValue : StringUtils.EMPTY;
    }

    /**
     * Set true if the search is wide, false otherwise
     * 
     * @param isWideSearch
     *            true if the search is wide, false otherwise
     */
    public void setWideSearch( boolean isWideSearch )
    {
        _bIsWideSearch = isWideSearch;
    }

    /**
     * Return true if the search is wide, false otherwise
     * 
     * @return true if the search is wide, false otherwise
     */
    public boolean isWideSearch( )
    {
        return _bIsWideSearch;
    }

    /**
     * @return the nStatus
     */
    public int getStatus( )
    {
        return _nStatus;
    }

    /**
     * @param nStatus
     *            the nStatus to set
     */
    public void setStatus( int nStatus )
    {
        _nStatus = nStatus;
    }

    /**
     * Contains status.
     */
    public boolean containsStatus( )
    {
        return _nStatus > -1;
    }

    /**
     * 
     * @return
     */
    public int getMustBeUpdated( )
    {
        return _nMustBeUpdated;
    }

    /**
     * 
     * @param _nMustBeUpdated
     */
    public void setMustBeUpdated( int nMustBeUpdated )
    {
        this._nMustBeUpdated = nMustBeUpdated;
    }

    /**
     * Contains id crm user.
     *
     * @return true, if successful
     */
    public boolean containsMustBeUpdated( )
    {
        return _nMustBeUpdated > 0;
    }

    /**
     * Builds the sql query.
     *
     * @param strSQL
     *            the str sql
     * @return the string
     */
    public String buildSQLQuery( String strSQL )
    {
        StringBuilder sbSQL = new StringBuilder( strSQL );
        int nIndex = 1;
        nIndex = buildSQLQueryForAttribute( sbSQL, nIndex );
        nIndex = buildFilter( sbSQL, containsIdCRMUser( ), SQL_FILTER_ID_CRM_USER, nIndex );
        nIndex = buildFilter( sbSQL, containsUserGuid( ), SQL_FILTER_USER_GUID, nIndex );
        nIndex = buildFilter( sbSQL, containsStatus( ), SQL_FILTER_STATUS_ACTIVE, nIndex );
        buildFilter( sbSQL, containsStatus( ), SQL_FILTER_MUST_BE_UPDATED, nIndex );

        return sbSQL.toString( );
    }

    /**
     * Builds the sql query for attribute.
     *
     * @param sbSQL
     *            the sb sql
     * @param nIndex
     *            the n index
     * @return the int
     */
    public int buildSQLQueryForAttribute( StringBuilder sbSQL, int nIndex )
    {
        int nIndexTmp = nIndex;

        if ( containsUserAttributes( ) )
        {
            for ( int nI = 0; nI < _userInfos.size( ); nI++ )
            {
                sbSQL.append( " INNER JOIN crm_user_attribute AS cua" + nI );
                sbSQL.append( " ON cu.id_crm_user = cua" + nI + ".id_crm_user" );
            }

            for ( int nI = 0; nI < _userInfos.size( ); nI++ )
            {
                // 2 interrogations marks, so nIndexTmp should add 2
                nIndexTmp = addSQLWhereOr( isWideSearch( ), sbSQL, nIndexTmp ) + 1;
                sbSQL.append( " cua" + nI + ".user_attribute_key = ? AND cua" + nI + ".user_attribute_value LIKE ? " );
            }
        }

        return nIndexTmp;
    }

    /**
     * Sets the filter values.
     *
     * @param daoUtil
     *            the new filter values
     */
    public void setFilterValues( DAOUtil daoUtil )
    {
        int nIndex = 1;

        if ( containsUserAttributes( ) )
        {
            for ( Entry<String, String> param : _userInfos.entrySet( ) )
            {
                daoUtil.setString( nIndex++, param.getKey( ) );
                daoUtil.setString( nIndex++, SQL_PERCENT + param.getValue( ) + SQL_PERCENT );
            }
        }

        if ( containsIdCRMUser( ) )
        {
            daoUtil.setInt( nIndex++, getIdCRMUser( ) );
        }

        if ( containsUserGuid( ) )
        {
            daoUtil.setString( nIndex++, SQL_PERCENT + getUserGuid( ) + SQL_PERCENT );
        }

        if ( containsStatus( ) )
        {
            daoUtil.setInt( nIndex++, getStatus( ) );
        }

        if ( containsMustBeUpdated( ) )
        {
            daoUtil.setInt( nIndex++, getMustBeUpdated( ) );
        }

    }

    /**
     * Builds the filter.
     *
     * @param sbSQL
     *            the sb sql
     * @param bAddFilter
     *            the b add filter
     * @param strSQL
     *            the str sql
     * @param nIndex
     *            the n index
     * @return the int
     */
    private int buildFilter( StringBuilder sbSQL, boolean bAddFilter, String strSQL, int nIndex )
    {
        int nIndexTmp = nIndex;

        if ( bAddFilter )
        {
            nIndexTmp = addSQLWhereOr( isWideSearch( ), sbSQL, nIndex );
            sbSQL.append( strSQL );
        }

        return nIndexTmp;
    }

    /**
     * Add a <b>WHERE</b> or a <b>OR</b> depending of the index. <br/>
     * <ul>
     * <li>if <code>nIndex</code> == 1, then we add a <b>WHERE</b></li>
     * <li>if <code>nIndex</code> != 1, then we add a <b>OR</b> or a <b>AND</b> depending of the wide search characteristic</li>
     * </ul>
     * 
     * @param bIsWideSearch
     *            true if it is a wide search, false otherwise
     * @param sbSQL
     *            the SQL query
     * @param nIndex
     *            the index
     * @return the new index
     */
    private int addSQLWhereOr( boolean bIsWideSearch, StringBuilder sbSQL, int nIndex )
    {
        if ( nIndex == 1 )
        {
            sbSQL.append( SQL_WHERE );
        }
        else
        {
            sbSQL.append( bIsWideSearch ? SQL_OR : SQL_AND );
        }

        return nIndex + 1;
    }

}
