<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="crmDemandTypePortlet" scope="session" class="fr.paris.lutece.plugins.crm.web.portlet.DemandTypePortletJspBean" />

<%
	crmDemandTypePortlet.init( request,  fr.paris.lutece.plugins.crm.web.demand.DemandTypeJspBean.RIGHT_MANAGE_CRM_DEMAND_TYPES );
%>
<% response.sendRedirect( crmDemandTypePortlet.doCreate( request ) ); %>
