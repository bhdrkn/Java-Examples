<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://v1.helloworld.bahadirakin.com/"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	exclude-result-prefixes="ns0 xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">
		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
			xmlns:v1="http://v1.helloworld.bahadirakin.com/">
			<soapenv:Header />
			<soapenv:Body>
				<sayHello xmlns="http://v2.helloworld.bahadirakin.com/"
					xmlns:tns="http://schemas.xmlsoap.org/soap/envelope/">
					<xsl:attribute name="xsi:schemaLocation"
						namespace="http://www.w3.org/2001/XMLSchema-instance"></xsl:attribute>
					<xsl:for-each select="soapenv:Envelope/soapenv:Body/ns0:sayHello">
						<xsl:variable name="var1_resultof_cast"
							select="string(*[local-name()='request' and namespace-uri()='']/*[local-name()='fullName' and namespace-uri()=''])" />
						<request xmlns="">
							<firstName>
								<xsl:value-of select="substring-before($var1_resultof_cast, ' ')" />
							</firstName>
							<lastName>
								<xsl:value-of select="substring-after($var1_resultof_cast, ' ')" />
							</lastName>
						</request>
					</xsl:for-each>
				</sayHello>
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
</xsl:stylesheet>
