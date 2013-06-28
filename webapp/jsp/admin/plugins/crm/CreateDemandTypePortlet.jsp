<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="crmDemandTypePortlet" scope="session" class="fr.paris.lutece.plugins.crm.web.portlet.DemandTypePortletJspBean" />

<%
	crmDemandTypePortlet.init( request, fr.paris.lutece.plugins.crm.web.demand.DemandTypeJspBean.RIGHT_MANAGE_CRM_DEMAND_TYPES );
%>
<%= crmDemandTypePortlet.getCreate( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
