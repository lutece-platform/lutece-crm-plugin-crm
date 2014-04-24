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
package fr.paris.lutece.plugins.crm.business.demand;

import fr.paris.lutece.plugins.crm.service.CRMPlugin;
import fr.paris.lutece.plugins.crm.service.demand.DemandStatusCRMService;
import fr.paris.lutece.plugins.crm.util.constants.CRMConstants;
import fr.paris.lutece.portal.service.content.XPageAppService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.util.LocalizedDelegatePaginator;
import fr.paris.lutece.util.html.IPaginator;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Manages pagination, filters and sort for demands
 *
 */
public class PaginationFilterSortManager
{
    // PAGINATION
    public static final String SESSION_PAGINATION_CURRENT_PAGE_INDEX = "currentPageIndex";
    public static final String SESSION_PAGINATION_ITEMS_PER_PAGE = "itemsPerPage";
    public static final String SESSION_PAGINATION_PAGINATOR = "paginator";

    // SORT
    public static final String SESSION_SORT_ATTRIBUTE = "sortAttribute";
    public static final String SESSION_SORT_ASC = "sortIsAsc";

    // FILTER
    public static final String SESSION_FILTER_MODIFICATION_DATE = "modificationDate";
    public static final String SESSION_FILTER_STR_MODIFICATION_DATE = "strModificationDate";
    public static final String SESSION_FILTER_DEMAND_TYPE = "demandType";
    public static final String SESSION_FILTER_NOTIFICATION = "notification";
    private HttpServletRequest _request;

    /**
     * Constructor
     * @param request the request
     */
    public PaginationFilterSortManager( HttpServletRequest request )
    {
        _request = request;
    }

    /**
     * Delete stored values for pagination, filter and sort
     */
    public void cleanSession(  )
    {
        HttpSession session = _request.getSession(  );

        int nIdStatus;

        for ( DemandStatusCRM statusCRM : DemandStatusCRMService.getService(  ).getAllStatusCRM( _request.getLocale(  ) ) )
        {
            nIdStatus = statusCRM.getIdStatusCRM(  );

            //PAGINATION
            session.removeAttribute( SESSION_PAGINATION_CURRENT_PAGE_INDEX + nIdStatus );
            session.removeAttribute( SESSION_PAGINATION_ITEMS_PER_PAGE + nIdStatus );
            session.removeAttribute( SESSION_PAGINATION_PAGINATOR + nIdStatus );

            //SORT
            session.removeAttribute( SESSION_SORT_ATTRIBUTE + nIdStatus );
            session.removeAttribute( SESSION_SORT_ASC + nIdStatus );
        }

        //FILTER
        this.cleanSessionFilter(  );
    }

    /**
     * Delete stored values for filter
     */
    public void cleanSessionFilter(  )
    {
        HttpSession session = _request.getSession(  );
        session.removeAttribute( SESSION_FILTER_DEMAND_TYPE );
        session.removeAttribute( SESSION_FILTER_MODIFICATION_DATE );
        session.removeAttribute( SESSION_FILTER_NOTIFICATION );
    }

    /**
     * Store a sort into session
     * @param nIdStatus the id status
     * @param strSortAttribute the sort attribute
     * @param bSortIsAsc the order
     */
    public void storeSort( int nIdStatus, String strSortAttribute, boolean bSortIsAsc )
    {
        HttpSession session = _request.getSession(  );
        session.setAttribute( SESSION_SORT_ATTRIBUTE + nIdStatus, strSortAttribute );
        session.setAttribute( SESSION_SORT_ASC + nIdStatus, bSortIsAsc );
    }

    /**
     * Retrieve a stored sort from session
     * @param nIdStatus the id status
     * @return a DemandSort
     */
    public DemandSort retrieveSort( int nIdStatus )
    {
        HttpSession session = _request.getSession(  );
        String strSortAttribute = (String) ( session.getAttribute( SESSION_SORT_ATTRIBUTE + nIdStatus ) );
        Boolean bSortIsAsc = (Boolean) ( session.getAttribute( SESSION_SORT_ASC + nIdStatus ) );

        DemandSort demandSort = null;

        if ( StringUtils.isNotBlank( strSortAttribute ) )
        {
            demandSort = new DemandSort( strSortAttribute, bSortIsAsc );
        }

        return demandSort;
    }

    /**
     * Retrieve the stored notification filter
     * @return the notification filter
     */
    public String retrieveFilterNotification(  )
    {
        HttpSession session = _request.getSession(  );

        return (String) ( session.getAttribute( SESSION_FILTER_NOTIFICATION ) );
    }

    /**
     * Store a notification filter into session
     * @param strNotification the notification filter
     */
    public void storeFilterNotification( String strNotification )
    {
        HttpSession session = _request.getSession(  );
        session.setAttribute( SESSION_FILTER_NOTIFICATION, strNotification );
    }

    /**
     * Retrieve the stored demand type filter
     * @return the demand type filter
     */
    public Integer retrieveFilterDemandType(  )
    {
        HttpSession session = _request.getSession(  );

        return (Integer) ( session.getAttribute( SESSION_FILTER_DEMAND_TYPE ) );
    }

    /**
     * Store the demand type filter into session
     * @param nIdDemandType the demand type id
     */
    public void storeFilterDemandType( int nIdDemandType )
    {
        HttpSession session = _request.getSession(  );
        session.setAttribute( SESSION_FILTER_DEMAND_TYPE, nIdDemandType );
    }

    /**
     * Retrieve the stored modification date filter
     * @return the modification date filter
     */
    public Date retrieveFilterModificationDate(  )
    {
        HttpSession session = _request.getSession(  );

        return (Date) ( session.getAttribute( SESSION_FILTER_MODIFICATION_DATE ) );
    }

    /**
     * Retrieve the stored modification date filter as string
     * @return the modification date filter as string
     */
    public String retrieveFilterStringModificationDate(  )
    {
        HttpSession session = _request.getSession(  );

        return (String) ( session.getAttribute( SESSION_FILTER_STR_MODIFICATION_DATE ) );
    }

    /**
     * Store the modification date filter into session
     * @param modificationDate the modification date
     */
    public void storeFilterModificationDate( Date modificationDate )
    {
        HttpSession session = _request.getSession(  );
        session.setAttribute( SESSION_FILTER_MODIFICATION_DATE, modificationDate );
    }

    /**
     * Store the modification date filter as string into session
     * @param strModificationDate the modification date as string
     */
    public void storeFilterStringModificationDate( String strModificationDate )
    {
        HttpSession session = _request.getSession(  );
        session.setAttribute( SESSION_FILTER_STR_MODIFICATION_DATE, strModificationDate );
    }

    /**
     * Create a paginator and store it into session
     * @param nIdStatus the id status
     * @param listDemand the result list
     * @param nTotalResult the total number of results
     * @return a paginator
     */
    public IPaginator<Demand> createAndStorePaginator( int nIdStatus, List<Demand> listDemand, int nTotalResult )
    {
        HttpSession session = _request.getSession(  );
        UrlItem url = new UrlItem( AppPathService.getBaseUrl( _request ) + AppPathService.getPortalUrl(  ) );
        url.addParameter( XPageAppService.PARAM_XPAGE_APP, CRMPlugin.PLUGIN_NAME );
        url.addParameter( CRMConstants.PARAMETER_SESSION, Boolean.TRUE.toString(  ) );

        int nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( CRMConstants.PROPERTY_DEMANDS_PER_PAGE, 50 );

        String strCurrentPageIndex = this.retrieveCurrentPageIndex( nIdStatus );

        if ( StringUtils.isBlank( strCurrentPageIndex ) )
        {
            strCurrentPageIndex = "1";
        }

        Integer nItemsPerPage = this.retrieveItemsPerPage( nIdStatus );

        if ( nItemsPerPage == null )
        {
            nItemsPerPage = nDefaultItemsPerPage;
        }

        strCurrentPageIndex = Paginator.getPageIndex( _request, Paginator.PARAMETER_PAGE_INDEX + nIdStatus,
                strCurrentPageIndex );

        nItemsPerPage = Paginator.getItemsPerPage( _request, Paginator.PARAMETER_ITEMS_PER_PAGE + nIdStatus,
                nItemsPerPage, nDefaultItemsPerPage );

        IPaginator<Demand> paginator = new LocalizedDelegatePaginator<Demand>( listDemand, nItemsPerPage,
                url.getUrl(  ), Paginator.PARAMETER_PAGE_INDEX + nIdStatus, strCurrentPageIndex, nTotalResult,
                _request.getLocale(  ) );

        paginator.setItemsPerPageParameterName( Paginator.PARAMETER_ITEMS_PER_PAGE + nIdStatus );

        session.setAttribute( SESSION_PAGINATION_CURRENT_PAGE_INDEX + nIdStatus, strCurrentPageIndex );
        session.setAttribute( SESSION_PAGINATION_ITEMS_PER_PAGE + nIdStatus, nItemsPerPage );
        session.setAttribute( SESSION_PAGINATION_PAGINATOR + nIdStatus, paginator );

        return paginator;
    }

    /**
     * Retrieve a stored paginator
     * @param nIdStatus the id status
     * @return the paginator
     */
    public IPaginator<Demand> retrievePaginator( int nIdStatus )
    {
        HttpSession session = _request.getSession(  );

        return (IPaginator<Demand>) ( session.getAttribute( SESSION_PAGINATION_PAGINATOR + nIdStatus ) );
    }

    /**
     * Retrieve a stored current page index
     * @param nIdStatus the id status
     * @return the current page index
     */
    public String retrieveCurrentPageIndex( int nIdStatus )
    {
        HttpSession session = _request.getSession(  );

        return (String) ( session.getAttribute( SESSION_PAGINATION_CURRENT_PAGE_INDEX + nIdStatus ) );
    }

    /**
     * Retrieve a stored number of items per page
     * @param nIdStatus the id status
     * @return the number of items per page
     */
    public Integer retrieveItemsPerPage( int nIdStatus )
    {
        HttpSession session = _request.getSession(  );

        return (Integer) ( session.getAttribute( SESSION_PAGINATION_ITEMS_PER_PAGE + nIdStatus ) );
    }

    /**
     * Return a bean for pagination in service/dao using parameter in http
     * request
     * @param nIdStatus the id status
     * @param nTotalResult the total number of results
     * @return pagination properties
     */
    public IPaginationProperties retrievePaginationProperties( int nIdStatus, Integer nTotalResult )
    {
        int nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( CRMConstants.PROPERTY_DEMANDS_PER_PAGE, 50 );

        String strCurrentPageIndex = this.retrieveCurrentPageIndex( nIdStatus );

        if ( StringUtils.isBlank( strCurrentPageIndex ) )
        {
            strCurrentPageIndex = "1";
        }

        Integer nItemsPerPage = this.retrieveItemsPerPage( nIdStatus );

        if ( nItemsPerPage == null )
        {
            nItemsPerPage = nDefaultItemsPerPage;
        }

        strCurrentPageIndex = Paginator.getPageIndex( _request, Paginator.PARAMETER_PAGE_INDEX + nIdStatus,
                strCurrentPageIndex );

        int nCurrentPageIndex = 1;

        if ( StringUtils.isNotBlank( strCurrentPageIndex ) )
        {
            nCurrentPageIndex = Integer.valueOf( strCurrentPageIndex );
        }

        nItemsPerPage = Paginator.getItemsPerPage( _request, Paginator.PARAMETER_ITEMS_PER_PAGE + nIdStatus,
                nItemsPerPage, nDefaultItemsPerPage );

        while ( ( ( nCurrentPageIndex - 1 ) * nItemsPerPage ) > nTotalResult )
        {
            nCurrentPageIndex = nCurrentPageIndex - 1;
            strCurrentPageIndex = Integer.toString( nCurrentPageIndex );
        }

        return new PaginationPropertiesImpl( ( nCurrentPageIndex - 1 ) * nItemsPerPage, nItemsPerPage, nItemsPerPage,
            nCurrentPageIndex );
    }
}
