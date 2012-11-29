package fr.paris.lutece.plugins.crm.business.parameters;

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;


public final class AdvancedParametersHome
{
    private static final String BEAN_CRM_ADVANCEDPARAMETERSDAO = "crm.advancedParametersDAO";
    private static Plugin _plugin = PluginService.getPlugin( CRMPlugin.PLUGIN_NAME );
    private static IAdvancedParametersDAO _dao = SpringContextService.getBean( BEAN_CRM_ADVANCEDPARAMETERSDAO );

    /**
     * Private constructor - this class need not be instantiated
     */
    public AdvancedParametersHome( )
    {
    }

    /**
     * Get the String value of a parameter
     * @return the string value
     */
    public static String getParameterStringValueByKey( String key )
    {
        return _dao.getParameterStringValueByKey( key, _plugin );
    }

    /**
     * Modify the String value of a parameter
     */
    public static void modifyParameterStringValueByKey( String key, String value )
    {
        _dao.modifyParameterStringValueByKey( key, value, _plugin );
    }
}
