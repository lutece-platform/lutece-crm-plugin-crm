package fr.paris.lutece.plugins.crm.service.listener;

import fr.paris.lutece.plugins.crm.business.user.CRMUser;
import fr.paris.lutece.plugins.crm.util.IListener;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.RemovalListenerService;

import java.util.ArrayList;
import java.util.List;


/**
 * Service to manage listeners of modifications on CRMUsers objects
 */
public class CRMUserModificationListenerService extends RemovalListenerService
{
    private static final String BEAN_NAME = "crm.crmUserListenerService";

    private static CRMUserModificationListenerService _instance;
    
    private List<IListener<CRMUser>> _listRegisteredListeners = new ArrayList<IListener<CRMUser>>( );

    /**
     * Private constructor
     */
    private CRMUserModificationListenerService( )
    {
    }

    /**
     * Get the instance of the service
     * @return The instance of the service
     */
    public static CRMUserModificationListenerService getService( )
    {
        if ( _instance == null )
        {
            _instance = SpringContextService.getBean( BEAN_NAME );
        }
        return _instance;
    }

    /**
     * Register a new Removal listener
     * @param listener The listener to register
     */
    public void registerListener( IListener<CRMUser> listener )
    {
        if ( listener != null )
        {
            _listRegisteredListeners.add( listener );
        }
    }

    /**
     * Notify listeners with a CRM user.
     * @param crmUser The user to send to listeners
     * @param strEventName Name of the event that caused the notification
     */
    public void notifyListeners( CRMUser crmUser, String strEventName )
    {
        if ( _listRegisteredListeners != null && _listRegisteredListeners.size( ) > 0 )
        {
            for ( IListener<CRMUser> listener : _listRegisteredListeners )
            {
                listener.notifyListener( crmUser, strEventName );
            }
        }
    }
}
