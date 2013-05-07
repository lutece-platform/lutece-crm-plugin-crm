/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.crm.util;


/**
 *
 * TargetEnum
 *
 */
public enum TargetEnum
{SELF( 0, "_self" ),
    BLANK( 1, "_blank" ),
    PARENT( 2, "_parent" ),
    TOP( 3, "_top" );

    private int _nId;
    private String _strTarget;

    /**
     * Constructor
     * @param nId the id of the target
     * @param strTarget the string value of the target
     */
    private TargetEnum( int nId, String strTarget )
    {
        _nId = nId;
        _strTarget = strTarget;
    }

    /**
     * Get the id of the target
     * @return the id of the target
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Get the String value of the target
     * @return the String value of the target
     */
    public String toString(  )
    {
        return _strTarget;
    }

    /**
     * Get the target given an id
     * @param nId the id
     * @return the target
     */
    public static TargetEnum getTarget( int nId )
    {
        if ( ( nId >= 0 ) && ( nId < values(  ).length ) )
        {
            return values(  )[nId];
        }

        return SELF;
    }
}
