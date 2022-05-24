/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.crm.service.category;

import fr.paris.lutece.plugins.crm.business.demand.category.Category;
import fr.paris.lutece.plugins.crm.business.demand.category.CategoryHome;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 *
 * CategoryService
 *
 */
public class CategoryService
{

    private static final String BEAN_CRM_CATEGORYSERVICE = "crm.categoryService";

    /**
     * Constructor
     */
    protected CategoryService( )
    {
    }

    /**
     * Get the instance of {@link CategoryService}
     * 
     * @return an instance of {@link CategoryService}
     */
    public static synchronized CategoryService getService( )
    {
        return SpringContextService.getBean( BEAN_CRM_CATEGORYSERVICE );
    }

    /**
     * Load the data of all the category objects and returns them in form of a collection
     * 
     * @return the collection which contains the data of all the category objects
     */
    public Collection<Category> getCategoriesList( )
    {
        return CategoryHome.getCategoriesList( );
    }

    /**
     * Load the data of all the category objects and returns them in form of a ReferenceList
     * 
     * @param locale
     *            {@link Locale}
     * @param bAddAllCategory
     *            true if the must contain all category, false otherwise
     * @param bIsDefaultChoiceTop
     *            true if the default choices are put at the top of the list, false otherwise
     * @return the ReferenceList which contains the data of all the category objects
     */
    public ReferenceList getCategories( Locale locale, boolean bAddAllCategory, boolean bIsDefaultChoiceTop )
    {
        ReferenceList list = new ReferenceList( );

        if ( bIsDefaultChoiceTop )
        {
            list.addItem( CRMConstants.NO_CATEGORY, I18nService.getLocalizedString( CRMConstants.PROPERTY_NO_CATEGORY, locale ) );

            if ( bAddAllCategory )
            {
                list.addItem( CRMConstants.ALL_CATEGORY, I18nService.getLocalizedString( CRMConstants.PROPERTY_ALL_CATEGORY, locale ) );
            }

            list.addAll( CategoryHome.getCategories( ) );
        }
        else
        {
            list = CategoryHome.getCategories( );
            list.addItem( CRMConstants.NO_CATEGORY, I18nService.getLocalizedString( CRMConstants.PROPERTY_NO_CATEGORY, locale ) );

            if ( bAddAllCategory )
            {
                list.addItem( CRMConstants.ALL_CATEGORY, I18nService.getLocalizedString( CRMConstants.PROPERTY_ALL_CATEGORY, locale ) );
            }
        }

        return list;
    }

    /**
     * Returns an instance of a category whose identifier is specified in parameter
     * 
     * @param nIdCategory
     *            The category primary key
     * @return an instance of Category
     */
    public Category findByPrimaryKey( int nIdCategory )
    {
        return CategoryHome.findByPrimaryKey( nIdCategory );
    }

    /**
     * Create an instance of the category class
     * 
     * @param category
     *            The instance of the Category which contains the informations to store
     * @return The instance of category which has been created with its primary key.
     */
    public int createCategory( Category category )
    {
        int nNewPrimaryKey = -1;

        if ( category != null )
        {
            nNewPrimaryKey = CategoryHome.create( category );
        }

        return nNewPrimaryKey;
    }

    /**
     * Remove the category whose identifier is specified in parameter. The category is removed if and only if it is not linked to any widget.
     * 
     * @param nIdCategory
     *            The category Id
     * @param locale
     *            {@link Locale}
     * @return true if the category is linked to a widget, false otherwise
     */
    public String removeCategory( int nIdCategory, Locale locale )
    {
        String strMessage = StringUtils.EMPTY;
        List<String> listErrors = new ArrayList<String>( );

        if ( !CategoryRemovalListenerService.getService( ).checkForRemoval( Integer.toString( nIdCategory ), listErrors, locale ) )
        {
            strMessage = AdminMessageService.getFormattedList( listErrors, locale );
        }
        else
        {
            CategoryHome.remove( nIdCategory );
        }

        return strMessage;
    }

    /**
     * Update of the category which is specified in parameter
     * 
     * @param category
     *            The instance of the Category which contains the data to store
     */
    public void updateCategory( Category category )
    {
        if ( category != null )
        {
            CategoryHome.update( category );
        }
    }

    /**
     * Find the first category (order by the ID category)
     * 
     * @return a {@link Category}
     */
    public Category findFirstCategory( )
    {
        return CategoryHome.findFirstCategory( );
    }
}
