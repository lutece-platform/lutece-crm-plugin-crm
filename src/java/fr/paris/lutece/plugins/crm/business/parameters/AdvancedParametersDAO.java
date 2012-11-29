package fr.paris.lutece.plugins.crm.business.parameters;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import org.apache.commons.lang.StringUtils;


public class AdvancedParametersDAO implements IAdvancedParametersDAO
{
    
    //SQL QUERIES
    private static final String SQL_QUERY_SELECT_VALUE_PARAMETER_BY_KEY = "SELECT parameter_value FROM crm_parameter WHERE parameter_key=?";
    private static final String SQL_QUERY_MODIFY_VALUE_PARAMETER_BY_KEY = "UPDATE crm_parameter SET parameter_value=? WHERE parameter_key=?";
    
    /**
     * Get the String value of a parameter
     * @return the string value
     */
    public String getParameterStringValueByKey( String key, Plugin plugin )
    {
        String value = StringUtils.EMPTY;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_VALUE_PARAMETER_BY_KEY, plugin );
        daoUtil.setString( 1, key );
        
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            value = daoUtil.getString( 1 );
        }

        daoUtil.free( );

        return value;
    }
    
    /**
     * Modify the String value of a parameter
     */
    public void modifyParameterStringValueByKey( String key, String value, Plugin plugin )
    {

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_MODIFY_VALUE_PARAMETER_BY_KEY, plugin );
        daoUtil.setString( 1, value );
        daoUtil.setString( 2, key );

        daoUtil.executeUpdate( );

        daoUtil.free( );
    }
}
