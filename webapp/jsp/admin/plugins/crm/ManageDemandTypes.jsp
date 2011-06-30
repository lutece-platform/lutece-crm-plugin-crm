<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="crmDemandType" scope="session" class="fr.paris.lutece.plugins.crm.web.demand.DemandTypeJspBean" />

<%
	crmDemandType.init( request, crmDemandType.RIGHT_MANAGE_CRM_DEMAND_TYPES );
%>
<%= crmDemandType.getManageDemandTypes( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
