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
package fr.paris.lutece.plugins.crm.service.demand;

import fr.paris.lutece.plugins.crm.business.demand.DemandType;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeCategoryRemovalListener;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeFilter;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeHome;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeRoleRemovalListener;
import fr.paris.lutece.plugins.crm.business.demand.DemandTypeWorkgroupRemovalListener;
import fr.paris.lutece.plugins.crm.service.category.CategoryRemovalListenerService;
import fr.paris.lutece.plugins.crm.service.category.CategoryService;
import fr.paris.lutece.plugins.crm.service.listener.CRMDemandTypeRemovalListenerService;
import fr.paris.lutece.plugins.crm.util.OperatorEnum;
import fr.paris.lutece.plugins.crm.util.TargetEnum;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.business.role.RoleHome;
import fr.paris.lutece.portal.service.role.RoleRemovalListenerService;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.workgroup.WorkgroupRemovalListenerService;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * DemandTypeService
 *
 */
public class DemandTypeService
{
    private static final String BEAN_CRM_DEMANDTYPESERVICE = "crm.demandTypeService";

    /**
     * Constructor
     */
    protected DemandTypeService( )
    {
    }

    /**
     * Get the instance of {@link DemandTypeService}
     * 
     * @return the instance of {@link DemandTypeService}
     */
    public static DemandTypeService getService( )
    {
        return SpringContextService.getBean( BEAN_CRM_DEMANDTYPESERVICE );
    }

    /**
     * Initialize the service
     */
    public static void init( )
    {
        CategoryRemovalListenerService.getService( ).registerListener( new DemandTypeCategoryRemovalListener( ) );
        WorkgroupRemovalListenerService.getService( ).registerListener( new DemandTypeWorkgroupRemovalListener( ) );
        RoleRemovalListenerService.getService( ).registerListener( new DemandTypeRoleRemovalListener( ) );
    }

    // CRUD

    /**
     * Create a new demand type
     * 
     * @param demandType
     *            the demand type
     * @return the newly created demand type ID
     */
    public int create( DemandType demandType )
    {
        int nNewPrimaryKey = -1;

        if ( demandType != null )
        {
            // Reorder the list
            doReorderDemandTypesGreaterOrder( demandType.getOrder( ), 0 );
            doReorderDemandTypesLowerOrder( demandType.getOrder( ), 0 );
            // The newly created demand type is placed at the head of the list
            nNewPrimaryKey = DemandTypeHome.create( demandType );
        }

        return nNewPrimaryKey;
    }

    /**
     * Update a demand type
     * 
     * @param demandType
     *            the demand type
     */
    public void update( DemandType demandType )
    {
        if ( demandType != null )
        {
            int nCurrentOrder = demandType.getOrder( );
            DemandType rtOld = findByPrimaryKey( demandType.getIdDemandType( ) );

            if ( rtOld != null )
            {
                int nOldOrder = rtOld.getOrder( );

                if ( nCurrentOrder != nOldOrder )
                {
                    // Reorder the list
                    doReorderDemandTypesGreaterOrder( nCurrentOrder, nOldOrder );
                    doReorderDemandTypesLowerOrder( nCurrentOrder, nOldOrder );
                }

                DemandTypeHome.update( demandType );
            }
        }
    }

    /**
     * Remove a demand type
     * 
     * @param nIdDemandType
     *            the ID demand type
     */
    public void remove( int nIdDemandType )
    {
        // Remove all demands associated to the demand type
        DemandType demandType = findByPrimaryKey( nIdDemandType );
        CRMDemandTypeRemovalListenerService.getService( ).notifyListeners( demandType, CRMConstants.EVENT_CRM_DEMAND_TYPE_REMOVED );
        DemandService.getService( ).removeByIdDemandType( nIdDemandType );
        DemandTypeHome.remove( nIdDemandType );
    }

    // FINDERS

    /**
     * Find a demand type by its primary key
     * 
     * @param nIdDemandType
     *            the id demand type
     * @return a {@link DemandType}
     */
    public DemandType findByPrimaryKey( int nIdDemandType )
    {
        return DemandTypeHome.findByPrimaryKey( nIdDemandType );
    }

    /**
     * Find a demand type by its order
     * 
     * @param nOrder
     *            the order
     * @return a {@link DemandType}
     */
    public DemandType findByOrder( int nOrder )
    {
        return DemandTypeHome.findByOrder( nOrder );
    }

    /**
     * Find all demand types
     * 
     * @return a list of {@link DemandType}
     */
    public List<DemandType> findAll( )
    {
        return DemandTypeHome.findAll( );
    }

    /**
     * Find all demand types as a {@link ReferenceList}
     * 
     * @return a {@link ReferenceList}
     */
    public ReferenceList findDemandTypes( )
    {
        return DemandTypeHome.findDemandTypes( );
    }

    /**
     * Find the max order + 1
     * 
     * @return the max order + 1
     */
    public int findMaxOrder( )
    {
        return DemandTypeHome.findMaxOrder( ) + 1;
    }

    /**
     * Find the demand types given a filter
     * 
     * @param dtFilter
     *            the filter
     * @return a list of {@link DemandType}
     */
    public List<DemandType> findByFilter( DemandTypeFilter dtFilter )
    {
        return DemandTypeHome.findByFilter( dtFilter );
    }

    /**
     * Find the list of demand types for the lutece user and a category
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @param nIdCategory
     *            the category Id Selected
     * @return a list of demand Type
     */
    public List<DemandType> findForLuteceUser( HttpServletRequest request, int nIdCategory )
    {

        DemandTypeFilter dtFilter = new DemandTypeFilter( );
        dtFilter.setIdCategory( nIdCategory );
        List<DemandType> listAuthorizedDemandTypes = new ArrayList<DemandType>( );
        Date dateToday = new Date( );
        for ( DemandType demandType : DemandTypeHome.findByIdCategoryAndDate( nIdCategory, dateToday ) )
        {
            if ( checkRoleForDemandType( demandType, request ) )
            {
                listAuthorizedDemandTypes.add( demandType );
            }
        }
        return listAuthorizedDemandTypes;
    }

    /**
     * Find the list of demand types for the lutece user ordered by id category
     * 
     * @param request
     *            {@link HttpServletRequest}
     * @return a map of (id_category, List&lt;DemandType&gt;)
     */
    public Map<String, List<DemandType>> findForLuteceUser( HttpServletRequest request )
    {
        Map<String, List<DemandType>> map = new HashMap<String, List<DemandType>>( );

        ReferenceList refListCategories = CategoryService.getService( ).getCategories( request.getLocale( ), false, true );
        if ( !CollectionUtils.isEmpty( refListCategories ) )
        {
            for ( ReferenceItem category : refListCategories )
            {
                int nIdCategory = Integer.parseInt( category.getCode( ) );
                map.put( category.getCode( ), findForLuteceUser( request, nIdCategory ) );
            }
        }

        return map;
    }

    /**
     * Find the list of demand types that have not a date end
     * 
     * @return a list of demand types
     */
    public List<DemandType> findNoDateEndDemandTypes( )
    {
        return DemandTypeHome.findNoDateEndDemandTypes( );
    }

    /**
     * Get the list of MyLutece role as a {@link ReferenceList}
     * 
     * @return {@link ReferenceList}
     */
    public ReferenceList getRolesList( )
    {
        return RoleHome.getRolesList( );
    }

    /**
     * Get the list of operators as a {@link ReferenceList}
     * 
     * @return a {@link ReferenceList}
     */
    public ReferenceList getOperatorsList( )
    {
        ReferenceList list = new ReferenceList( );

        for ( OperatorEnum operator : OperatorEnum.values( ) )
        {
            list.addItem( operator.getId( ), operator.toString( ) );
        }

        return list;
    }

    /**
     * Get the list of targets as a {@link ReferenceList}
     * 
     * @return a {@link ReferenceList}
     */
    public ReferenceList getTargetsList( )
    {
        ReferenceList list = new ReferenceList( );

        for ( TargetEnum target : TargetEnum.values( ) )
        {
            list.addItem( target.getId( ), target.toString( ) );
        }

        return list;
    }

    // DO

    /**
     * Reorder all demand types
     */
    public void doReorderDemandTypes( )
    {
        int nOrder = 1;

        for ( DemandType demandType : findAll( ) )
        {
            demandType.setOrder( nOrder++ );
            update( demandType );
        }
    }

    /**
     * Reorder the demand types that are greater or equal to the {@link nCurrentOrder}. <br />
     * Those demand types orders are move up by 1. <br />
     * The {@link nOldOrder} is ignored in the process.
     * 
     * @param nCurrentOrder
     *            the current order
     * @param nOldOrder
     *            the order to ignore
     */
    public void doReorderDemandTypesGreaterOrder( int nCurrentOrder, int nOldOrder )
    {
        int nOrder = nCurrentOrder;
        DemandTypeFilter dtFilter = new DemandTypeFilter( );
        dtFilter.setOrder( nOrder );
        dtFilter.setOperatorOrder( OperatorEnum.GREATER_OR_EQUAL );

        // Move up all demand types that have an order >= nOrder (ie order + 1)
        nOrder++;

        for ( DemandType demandType : findByFilter( dtFilter ) )
        {
            if ( demandType.getOrder( ) != nOldOrder )
            {
                demandType.setOrder( nOrder++ );
                DemandTypeHome.update( demandType );
            }
        }
    }

    /**
     * Reorder the demand types that have a lower order than the {@link nCurrentOrder} . <br  />
     * The {@link nOldOrder} is ignored in the process.
     * 
     * @param nCurrentOrder
     *            the current order
     * @param nOldOrder
     *            the order to ignore
     */
    public void doReorderDemandTypesLowerOrder( int nCurrentOrder, int nOldOrder )
    {
        int nOrder = 1;
        DemandTypeFilter dtFilter = new DemandTypeFilter( );
        dtFilter.setOrder( nCurrentOrder );
        dtFilter.setOperatorOrder( OperatorEnum.LOWER );

        for ( DemandType demandType : findByFilter( dtFilter ) )
        {
            if ( demandType.getOrder( ) != nOldOrder )
            {
                demandType.setOrder( nOrder++ );
                DemandTypeHome.update( demandType );
            }
        }
    }

    // CHECKS

    /**
     * Check if the user has the role in order to view the demand type
     * 
     * @param demandType
     *            the demand type
     * @param request
     *            {@link HttpServletRequest}
     * @return true if the user has the role or the demand type does not require a role, false otherwise
     */
    public boolean checkRoleForDemandType( DemandType demandType, HttpServletRequest request )
    {
        boolean bIsAuthorized = false;

        if ( StringUtils.isBlank( demandType.getRole( ) ) || DemandType.ROLE_NONE.equals( demandType.getRole( ) )
                || SecurityService.getInstance( ).isUserInRole( request, demandType.getRole( ) ) )
        {
            bIsAuthorized = true;
        }

        return bIsAuthorized;
    }

    /**
     * Check if the list of demand types are well ordered
     * 
     * @return true if it is well ordered, false otherwise
     */
    public boolean isWellOrdered( )
    {
        int nOrder = 1;

        for ( DemandType demandType : findAll( ) )
        {
            if ( demandType.getOrder( ) != nOrder )
            {
                return false;
            }

            nOrder++;
        }

        return true;
    }
}
