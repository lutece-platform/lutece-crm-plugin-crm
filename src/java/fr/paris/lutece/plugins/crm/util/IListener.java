package fr.paris.lutece.plugins.crm.util;

/**
 * Generic listener for a DTO object
 * @param <K> Type of the DTO object
 */
public interface IListener<K>
{
    /**
     * Notify the listener with an object and the name of the event that occurs
     * @param item The item
     * @param strEventName The name of the event that caused the notification
     */
    void notifyListener( K item, String strEventName );
}
