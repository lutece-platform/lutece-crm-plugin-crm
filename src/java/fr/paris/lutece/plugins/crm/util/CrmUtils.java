package fr.paris.lutece.plugins.crm.util;

import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.xml.XmlUtil;

public class CrmUtils
{

    public static final int CONSTANT_ID_NULL = -1;

    private static final String REGEX_ID = "^[\\d]+$";

    /**
     * convert a string to int
     *
     * @param strParameter
     *            the string parameter to convert
     * @return the conversion
     */
    public static int convertStringToInt( String strParameter )
    {
        int nIdParameter = -1;

        try
        {
            if ( ( strParameter != null ) && strParameter.matches( REGEX_ID ) )
            {
                nIdParameter = Integer.parseInt( strParameter );
            }
        }
        catch( NumberFormatException ne )
        {
            AppLogService.error( ne );
        }

        return nIdParameter;
    }

    /**
     * Add a CDATA type element to XML document buffer add Empty value if strValue is null
     *
     * @param strXmlBuffer
     *            The XML document buffer
     * @param strTag
     *            The tag name of the element to add
     * @param strValue
     *            The value of the element
     */
    public static void addElementHtml( StringBuffer strXmlBuffer, String strTag, String strValue )
    {
        if ( strValue != null )
        {
            XmlUtil.addElementHtml( strXmlBuffer, strTag, strValue );
        }
        else
        {
            XmlUtil.addEmptyElement( strXmlBuffer, strTag, null );
        }
    }

}
