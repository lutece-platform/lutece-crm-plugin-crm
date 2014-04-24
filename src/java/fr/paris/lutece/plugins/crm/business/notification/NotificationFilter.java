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
package fr.paris.lutece.plugins.crm.business.notification;


/**
 *
 * NotificationFilter
 *
 */
public class NotificationFilter
{
    private static final int ALL_INT = -1;
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private boolean _bIsWideSearch;
    private int _nIdDemand;
    private int _nIsRead;

    /**
     * Constructor
     */
    public NotificationFilter(  )
    {
        _bIsWideSearch = false;
        _nIdDemand = ALL_INT;
        _nIsRead = ALL_INT;
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
     * Returns the IdDemand
     * @return The IdDemand
     */
    public int getIdDemand(  )
    {
        return _nIdDemand;
    }

    /**
     * Sets the IdDemand
     * @param strIdDemand The IdDemand
     */
    public void setIdDemand( int strIdDemand )
    {
        _nIdDemand = strIdDemand;
    }

    /**
     * Check if the filter contains the attribute ID Demand
     * @return true if it contains, false otherwise
     */
    public boolean containsIdDemand(  )
    {
        return _nIdDemand != ALL_INT;
    }

    /**
     * Set the status is_read of the notification
     * @param bIsRead true if the notification is read, false otherwise
     */
    public void setIsRead( boolean bIsRead )
    {
        _nIsRead = bIsRead ? TRUE : FALSE;
    }

    /**
     * Check if the notification is read
     * @return true if it is read, false otherwise
     */
    public boolean isRead(  )
    {
        return _nIsRead == TRUE;
    }

    /**
     * Check if the filter contains the attribute is_read
     * @return true if it contains, false otherwise
     */
    public boolean containsIsRead(  )
    {
        return _nIsRead != ALL_INT;
    }
}
