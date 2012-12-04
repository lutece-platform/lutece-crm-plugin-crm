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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.plugins.crm.util.OperatorEnum;

import org.apache.commons.lang.StringUtils;

import java.util.Date;


/**
 *
 * DemandTypeFilter
 *
 */
public class DemandTypeFilter
{
    private static final int ALL_INT = -1;
    private boolean _bIsWideSearch;
    private String _strLabel;
    private String _strUrlResource;
    private int _nIdCategory;
    private Date _dateBegin;
    private Date _dateEnd;
    private String _strWorkgroupKey;
    private String _strRoleKey;
    private int _nOrder;
    private OperatorEnum _operatorOrder;
    private OperatorEnum _operatorDateBegin;
    private OperatorEnum _operatorDateEnd;
    private String _strUrlDelete;

    /**
     * Constructor
     */
    public DemandTypeFilter(  )
    {
        _bIsWideSearch = false;
        _strLabel = StringUtils.EMPTY;
        _strUrlResource = StringUtils.EMPTY;
        _nIdCategory = ALL_INT;
        _strWorkgroupKey = StringUtils.EMPTY;
        _nOrder = ALL_INT;
        _operatorOrder = OperatorEnum.EQUAL;
        _operatorDateBegin = OperatorEnum.EQUAL;
        _operatorDateEnd = OperatorEnum.EQUAL;
        _strUrlDelete = StringUtils.EMPTY;
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
     * Returns the Label
     * @return The Label
     */
    public String getLabel(  )
    {
        return _strLabel;
    }

    /**
     * Sets the Label
     * @param strLabel The Label
     */
    public void setLabel( String strLabel )
    {
        _strLabel = strLabel;
    }

    /**
     * Check if the filter contains the attribute Label
     * @return true if it contains, false otherwise
     */
    public boolean containsLabel(  )
    {
        return StringUtils.isNotBlank( _strLabel );
    }

    /**
     * Returns the UrlResource
     * @return The UrlResource
     */
    public String getUrlResource(  )
    {
        return _strUrlResource;
    }

    /**
     * Sets the UrlResource
     * @param strUrlResource The UrlResource
     */
    public void setUrlResource( String strUrlResource )
    {
        _strUrlResource = strUrlResource;
    }

    /**
     * Check if the filter contains the attribute UrlResource
     * @return true if it contains, false otherwise
     */
    public boolean containsUrlResource(  )
    {
        return StringUtils.isNotBlank( _strUrlResource );
    }

    /**
     * Returns the IdCategory
     * @return The IdCategory
     */
    public int getIdCategory(  )
    {
        return _nIdCategory;
    }

    /**
     * Sets the IdCategory
     * @param nIdCategory The IdCategory
     */
    public void setIdCategory( int nIdCategory )
    {
        _nIdCategory = nIdCategory;
    }

    /**
     * Check if the filter contains the attribute ID category
     * @return true if it contains, false otherwise
     */
    public boolean containsIdCategory(  )
    {
        return _nIdCategory != ALL_INT;
    }

    /**
     * Set the beginning date
     * @param dateBegin the beginning date
     */
    public void setDateBegin( Date dateBegin )
    {
        _dateBegin = dateBegin;
    }

    /**
     * Get the beginning date
     * @return the beginning date
     */
    public Date getDateBegin(  )
    {
        return _dateBegin;
    }

    /**
    * Check if the filter contains the attribute date begin
    * @return true if it contains, false otherwise
    */
    public boolean containsDateBegin(  )
    {
        return _dateBegin != null;
    }

    /**
     * Set the closing date
     * @param dateEnd the closing date
     */
    public void setDateEnd( Date dateEnd )
    {
        _dateEnd = dateEnd;
    }

    /**
     * Get the closing date
     * @return the closing date
     */
    public Date getDateEnd(  )
    {
        return _dateEnd;
    }

    /**
     * Check if the filter contains the attribute date end
     * @return true if it contains, false otherwise
     */
    public boolean containsDateEnd(  )
    {
        return _dateEnd != null;
    }

    /**
     * Set the workgroup
     * @param strWorkgroupKey the workgroup key
     */
    public void setWorkgroup( String strWorkgroupKey )
    {
        _strWorkgroupKey = strWorkgroupKey;
    }

    /**
     * Get the workgroup
     * @return the workgroup key
     */
    public String getWorkgroup(  )
    {
        return _strWorkgroupKey;
    }

    /**
     * Check whether the filter contains or not the workgroup
     * @return true if it contains, false otherwise
     */
    public boolean containsWorkgroup(  )
    {
        return StringUtils.isNotBlank( _strWorkgroupKey );
    }

    /**
     * Set role
     * @param strRoleKey the role key
     */
    public void setRole( String strRoleKey )
    {
        _strRoleKey = strRoleKey;
    }

    /**
     * Get role
     * @return the role key
     */
    public String getRole(  )
    {
        return _strRoleKey;
    }

    /**
     * Check whether the filter contains or not the role
     * @return true if it contains, false otherwise
     */
    public boolean containsRole(  )
    {
        return StringUtils.isNotBlank( _strRoleKey );
    }

    /**
     * Set the order
     * @param nOrder the order
     */
    public void setOrder( int nOrder )
    {
        _nOrder = nOrder;
    }

    /**
     * Get the order
     * @return the order
     */
    public int getOrder(  )
    {
        return _nOrder;
    }

    /**
     * Check if the filter contains the attribute order
     * @return true if it contains, false otherwise
     */
    public boolean containsOrder(  )
    {
        return _nOrder != ALL_INT;
    }

    /**
     * Set the operator for the order
     * @param operator the operator
     */
    public void setOperatorOrder( OperatorEnum operator )
    {
        _operatorOrder = operator;
    }

    /**
     * Get the operator for the order
     * @return the operator for the order
     */
    public OperatorEnum getOperatorOrder(  )
    {
        return _operatorOrder;
    }

    /**
     * Set the operator for the beginning date
     * @param operator the operator
     */
    public void setOperatorDateBegin( OperatorEnum operator )
    {
        _operatorDateBegin = operator;
    }

    /**
     * Get the operator for the beginning date
     * @return the operator
     */
    public OperatorEnum getOperatorDateBegin(  )
    {
        return _operatorDateBegin;
    }

    /**
     * Set the operator for the closing date
     * @param operator the operator
     */
    public void setOperatorDateEnd( OperatorEnum operator )
    {
        _operatorDateEnd = operator;
    }

    /**
     * Get the operator for the closing date
     * @return the operator
     */
    public OperatorEnum getOperatorDateEnd(  )
    {
        return _operatorDateEnd;
    }

    /**
     * Returns the UrlDelete
     * @return The UrlDelete
     */
    public String getUrlDelete(  )
    {
        return _strUrlDelete;
    }

    /**
     * Sets the UrlDelete
     * @param strUrlDelete The UrlDelete
     */
    public void setUrlDelete( String strUrlDelete )
    {
        _strUrlResource = strUrlDelete;
    }

    /**
     * Check if the filter contains the attribute UrlDelete
     * @return true if it contains, false otherwise
     */
    public boolean containsUrlDelete(  )
    {
        return StringUtils.isNotBlank( _strUrlResource );
    }
}
