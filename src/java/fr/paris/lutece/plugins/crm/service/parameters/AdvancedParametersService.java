package fr.paris.lutece.plugins.crm.service.parameters;

import fr.paris.lutece.plugins.crm.business.parameters.AdvancedParametersHome;
import fr.paris.lutece.plugins.crm.service.demand.DemandTypeService;
import fr.paris.lutece.portal.service.spring.SpringContextService;


public class AdvancedParametersService
{

    //BEAN 
    private static final String BEAN_CRM_ADVANCED_PARAMETERS = "crm.advancedParametersService";

    //CONSTANTS
    private static final String STRING_TRUE = "true";

    /**
     * Constructor
     */
    protected AdvancedParametersService( )
    {
    }

    /**
     * Get the instance of {@link DemandTypeService}
     * @return the instance of {@link DemandTypeService}
     */
    public static AdvancedParametersService getService( )
    {
        return SpringContextService.getBean( BEAN_CRM_ADVANCED_PARAMETERS );
    }

    /**
     * Get the String value of a parameter
     * @return the string value
     */
    public String getParameterStringValueByKey( String key )
    {
        return AdvancedParametersHome.getParameterStringValueByKey( key );
    }

    /**
     * Get the boolean value for the parameter displayDraft
     * @return the string value
     */

    public Boolean isParameterValueDisplayDraftTrue( String key )
    {
        boolean ret = false;
        String strValue = AdvancedParametersHome.getParameterStringValueByKey( key );
        
        if ( strValue != null && strValue.equals( STRING_TRUE ) )
        {
            ret = true;
        }
        
        return ret;
        
    }

    /**
     * Modify the String value of a parameter
     */
    public void modifyParameterStringValueByKey( String key, String value )
    {
        AdvancedParametersHome.modifyParameterStringValueByKey( key, value );
    }

}
