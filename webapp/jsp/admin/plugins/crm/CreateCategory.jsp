<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="crmCategory" scope="session" class="fr.paris.lutece.plugins.crm.web.category.CategoryJspBean" />

<%
	crmCategory.init( request, fr.paris.lutece.plugins.crm.web.demand.DemandTypeJspBean.RIGHT_MANAGE_CRM_DEMAND_TYPES );
%>
<%= crmCategory.getCreateCategory( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
