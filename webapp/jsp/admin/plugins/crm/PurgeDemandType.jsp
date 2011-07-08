<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="crmDemandType" scope="session" class="fr.paris.lutece.plugins.crm.web.demand.DemandTypeJspBean" />

<%
	crmDemandType.init( request, crmDemandType.RIGHT_MANAGE_CRM_DEMAND_TYPES );
%>
<% response.sendRedirect( crmDemandType.getConfirmPurgeDemandType( request ) ); %>
