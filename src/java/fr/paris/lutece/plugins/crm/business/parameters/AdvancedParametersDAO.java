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
package fr.paris.lutece.plugins.crm.business.parameters;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import org.apache.commons.lang.StringUtils;


/**
 * The Class AdvancedParametersDAO.
 */
public class AdvancedParametersDAO implements IAdvancedParametersDAO
{
    //SQL QUERIES
    private static final String SQL_QUERY_SELECT_VALUE_PARAMETER_BY_KEY = "SELECT parameter_value FROM crm_parameter WHERE parameter_key=?";
    private static final String SQL_QUERY_MODIFY_VALUE_PARAMETER_BY_KEY = "UPDATE crm_parameter SET parameter_value=? WHERE parameter_key=?";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterStringValueByKey( String strKey, Plugin plugin )
    {
        String strValue = StringUtils.EMPTY;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_VALUE_PARAMETER_BY_KEY, plugin );
        daoUtil.setString( 1, strKey );

        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            strValue = daoUtil.getString( 1 );
        }

        daoUtil.free(  );

        return strValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyParameterStringValueByKey( String key, String strValue, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_MODIFY_VALUE_PARAMETER_BY_KEY, plugin );
        daoUtil.setString( 1, strValue );
        daoUtil.setString( 2, key );

        daoUtil.executeUpdate(  );

        daoUtil.free(  );
    }
}
