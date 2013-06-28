<?xml version="1.0"?>
	<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		<xsl:param  name="i18n-label-demand-types-list" select="'Liste des demandes par catégorie'" />
		<xsl:param name="i18n-label-crm-info" />
		<xsl:param name="i18n-label-crm-contact" />
		<xsl:param name="i18n-label-crm-date-begin" />
		<xsl:param name="i18n-label-crm-date-end" />
		<xsl:output method="html" indent="yes"/>
		<xsl:template match="portlet">
			<div class="portlet -lutece-border-radius append-bottom">
				<xsl:apply-templates select="crm-demand-type-portlet" />
			</div>
		</xsl:template>
		
		<xsl:template match="crm-demand-type-portlet">
			<xsl:apply-templates select="crm-demand-type-portlet-content" />
		</xsl:template>
	
		<xsl:template match="crm-demand-type-portlet-content">
			<div class="portlet">
				<div class="well">
					<div class="row-fluid">
						<xsl:apply-templates select="crm-demand-type-category-list/category"/>
					</div>
				</div>
			</div>
		</xsl:template>
	
		<xsl:template match="category">
			<fieldset>
				<legend>
					<xsl:if test="category-name!=''">
						<xsl:value-of select="category-name" />
					</xsl:if>
				</legend>		
				<ul>
					<xsl:apply-templates select="demand-type-list/demand-type"/>
				</ul>
			</fieldset>
		</xsl:template>
	
		<xsl:template match="demand-type">
			<li>												
				<a href="jsp/site/plugins/crm/DoOpenDemandType.jsp?id_demand_type={demand-type-id}" target="{demand-type-target}">
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
			</li>		
		</xsl:template>
</xsl:stylesheet>