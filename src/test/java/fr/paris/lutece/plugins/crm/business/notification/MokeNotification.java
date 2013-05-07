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
package fr.paris.lutece.plugins.crm.business.notification;

import fr.paris.lutece.util.date.DateUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Locale;


/**
 *
 * MokeNotification
 *
 */
public class MokeNotification extends Notification
{
    private static final String DATE = "01/09/2011";
    private static final int ID_DEMAND = 1;
    private static final boolean IS_READ = false;
    private static final String MESSAGE = "Message";
    private static final String OBJECT = "Object";
    private static final String SENDER = "Sender";
    private static Locale _locale = Locale.getDefault(  );

    /**
     * Constructor
     * @param nIdNotification the id notification
     */
    public MokeNotification( int nIdNotification )
    {
        Date date = DateUtil.formatDateLongYear( DATE, _locale );
        Timestamp dateCreation = new Timestamp( date.getTime(  ) );

        setIdNotification( nIdNotification );
        setIdDemand( ID_DEMAND );
        setIsRead( IS_READ );
        setMessage( MESSAGE + nIdNotification );
        setObject( OBJECT + nIdNotification );
        setSender( SENDER + nIdNotification );
        setDateCreation( dateCreation );
    }
}
