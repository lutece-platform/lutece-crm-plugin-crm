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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.plugins.crm.util.OperatorEnum;

import org.apache.commons.lang.StringUtils;

import java.util.Date;


/**
 *
 * DemandFilter
 *
 */
public class DemandFilter
{
    private static final int ALL_INT = -1;
    private boolean _bIsWideSearch;
    private String _strUserGuid;
    private int _nIdDemandType;
    private Date _dateModification;
    private int _nIdStatusCRM;
    private OperatorEnum _operatorDateModification;

    /**
     * Constructor
     */
    public DemandFilter(  )
    {
        _bIsWideSearch = false;
        _strUserGuid = StringUtils.EMPTY;
        _nIdDemandType = ALL_INT;
        _nIdStatusCRM = ALL_INT;
        _operatorDateModification = OperatorEnum.EQUAL;
    }

    /**
     * Set true if the filter is applied to a wide search.
     * <br/>
     * In other words, the SQL query will use
     * <ul>
     * <li>SQL <b>OR</b> if it is applied to a wide search</li>
     * <li>SQL <b>AND</b> if it is not applied to a wide search</li>
     * </ul>
     * @param bIsWideSearch true if it a wide search, false otherwise
     */
    public void setIsWideSearch( boolean bIsWideSearch )
    {
        _bIsWideSearch = bIsWideSearch;
    }

    /**
     * Check if the filter is applied to a wide search or not.
     * <br/>
     * In other words, the SQL query will use
     * <ul>
     * <li>SQL <b>OR</b> if it is applied to a wide search</li>
     * <li>SQL <b>AND</b> if it is not applied to a wide search</li>
     * </ul>
     * @return true if it is applied to a wide search
     */
    public boolean getIsWideSearch(  )
    {
        return _bIsWideSearch;
    }

    /**
     * Returns the UserGuid
     * @return The UserGuid
     */
    public String getUserGuid(  )
    {
        return _strUserGuid;
    }

    /**
     * Sets the UserGuid
     * @param strUserGuid The UserGuid
     */
    public void setUserGuid( String strUserGuid )
    {
        _strUserGuid = strUserGuid;
    }

    /**
     * Check if the filter contains the attribute UserGuid
     * @return true if it contains, false otherwise
     */
    public boolean containsUserGuid(  )
    {
        return StringUtils.isNotBlank( _strUserGuid );
    }

    /**
     * Returns the IdDemandType
     * @return The IdDemandType
     */
    public int getIdDemandType(  )
    {
        return _nIdDemandType;
    }

    /**
     * Sets the nIdDemandType
     * @param nIdDemandType The nIdDemandType
     */
    public void setIdDemandType( int nIdDemandType )
    {
        _nIdDemandType = nIdDemandType;
    }

    /**
     * Check if the filter contains the attribute ID demand type
     * @return true if it contains, false otherwise
     */
    public boolean containsIdDemandType(  )
    {
        return _nIdDemandType != ALL_INT;
    }

    /**
     * Set the Modification date
     * @param dateModification the Modification date
     */
    public void setDateModification( Date dateModification )
    {
        _dateModification = dateModification;
    }

    /**
     * Get the Modification date
     * @return the Modification date
     */
    public Date getDateModification(  )
    {
        return _dateModification;
    }

    /**
    * Check if the filter contains the attribute date Modification
    * @return true if it contains, false otherwise
    */
    public boolean containsDateModification(  )
    {
        return _dateModification != null;
    }

    /**
     * Set the IdStatusCRM
     * @param nIdStatusCRM the IdStatusCRM
     */
    public void setIdStatusCRM( int nIdStatusCRM )
    {
        _nIdStatusCRM = nIdStatusCRM;
    }

    /**
     * Get the IdStatusCRM
     * @return the IdStatusCRM
     */
    public int getIdStatusCRM(  )
    {
        return _nIdStatusCRM;
    }

    /**
     * Check if the filter contains the attribute IdStatusCRM
     * @return true if it contains, false otherwise
     */
    public boolean containsIdStatusCRM(  )
    {
        return _nIdStatusCRM != ALL_INT;
    }

    /**
     * Set the operator for the modification date
     * @param operator the operator
     */
    public void setOperatorDateModification( OperatorEnum operator )
    {
        _operatorDateModification = operator;
    }

    /**
     * Get the operator for the modification date
     * @return the operator
     */
    public OperatorEnum getOperatorDateModification(  )
    {
        return _operatorDateModification;
    }
}
