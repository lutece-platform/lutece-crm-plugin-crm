<jsp:useBean id="crmApp" scope="session" class="fr.paris.lutece.plugins.crm.web.CRMApp" />

<%
	response.sendRedirect( crmApp.doOpenDemandType( request ) );
%>
