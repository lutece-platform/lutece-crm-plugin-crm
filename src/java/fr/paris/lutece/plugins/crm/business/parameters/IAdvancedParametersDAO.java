package fr.paris.lutece.plugins.crm.business.parameters;

import fr.paris.lutece.portal.service.plugin.Plugin;


public interface IAdvancedParametersDAO
{

    /**
     * Get the String value of a parameter
     * @return the string value
     */
    String getParameterStringValueByKey( String key, Plugin plugin );

    /**
     * Modify the String value of a parameter
     */
    void modifyParameterStringValueByKey( String key, String value, Plugin plugin );
}
