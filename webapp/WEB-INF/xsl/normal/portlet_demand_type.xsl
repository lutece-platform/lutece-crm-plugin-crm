<?xml version="1.0"?>
	<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		<xsl:param  name="i18n-label-demand-types-list" select="'Liste des demandes par catégorie'" />
		<xsl:param name="i18n-label-crm-info" />
		<xsl:param name="i18n-label-crm-contact" />
		<xsl:param name="i18n-label-crm-date-begin" />
		<xsl:param name="i18n-label-crm-date-end" />
		<xsl:output method="html" indent="yes"/>
		<xsl:template match="portlet">
			<div class="portlet span4">
				<xsl:apply-templates select="crm-demand-type-portlet" />
			</div>
		</xsl:template>
		
		<xsl:template match="crm-demand-type-portlet">
			<xsl:apply-templates select="crm-demand-type-portlet-content" />
		</xsl:template>
	
		<xsl:template match="crm-demand-type-portlet-content">
			<xsl:apply-templates select="crm-demand-type-category-list/category"/>
		</xsl:template>
	
		<xsl:template match="category">
		<div class="well well-gu">
			<h4>
				<xsl:if test="category-name!=''">
					<xsl:value-of select="category-name" />
					</xsl:if>
			</h4>
			<ul class="unstyled">
				<xsl:apply-templates select="demand-type-list/demand-type"/>
			</ul>
		</div>
		</xsl:template>
	
		<xsl:template match="demand-type">
			<li>												
				<a href="jsp/site/plugins/crm/DoOpenDemandType.jsp?id_demand_type={demand-type-id}" target="{demand-type-target}" class="label-demand">
					<xsl:value-of select="demand-type-label" />
				</a>
				<xsl:if test="demand-type-url-info!=''">
					<br/>
					<em>
						<a href="{demand-type-url-info}">
							<xsl:value-of select="$i18n-label-crm-info" />
						</a>
					</em>
				</xsl:if>
				<xsl:if test="demand-type-url-contact!=''">
					<br />
					<em>
						<a href="{demand-type-url-contact}">
							<xsl:value-of select="$i18n-label-crm-contact" />
						</a>
					</em>
				</xsl:if>
				<xsl:if test="demand-type-date-begin!=''">
					<br />
					<xsl:value-of select="$i18n-label-crm-date-begin" />: <xsl:value-of select="demand-type-date-begin" />
				</xsl:if>
				<xsl:if test="demand-type-date-end!=''">
					<br />
					<xsl:value-of select="$i18n-label-crm-date-end" /> : <xsl:value-of select="demand-type-date-end" />
				</xsl:if>
				<xsl:if test="demand-type-need-authentication='true'">
					<span class="badge badge-warning pull-right">
						<a href="#" class="tooltips" data-toggle="tooltip" title="L'accès à ce téléservice nécessite un compte de connexion ">
							<i class="icon-lock icon-white">&#160;</i>
						</a>
					</span>
				</xsl:if>
		
				<xsl:if test="demand-type-need-validation='true'">
					<span class="badge badge-success pull-right spaced-right">
						<a href="#" class="tooltips" data-toggle="tooltip" data-placement="top" title="L'accès à ce téléservice nécessite la validation de votre compte ">
							<i class="icon-ok icon-white">&#160;</i>
						</a>
					</span>
				</xsl:if>
			</li>		
		</xsl:template>
</xsl:stylesheet>