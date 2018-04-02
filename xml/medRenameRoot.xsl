<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" encoding="utf-8" />
    <xsl:template match="/">
        <xsl:apply-templates select="*|comment()|processing-instruction()" />
    </xsl:template>
    <xsl:template match="comment()|processing-instruction()">
        <xsl:copy-of select="." />
    </xsl:template>
    <xsl:template match="*[count(parent::*) = 0]">
        <!--Change root to <med> -->
        <xsl:element name="med" namespace="{namespace-uri()}">
            <xsl:copy-of select="@*|namespace::*" />
            <xsl:apply-templates select="*|comment()|processing-instruction()" />
        </xsl:element>
    </xsl:template>
    <xsl:template match="*[not(count(parent::*) = 0)]">
        <xsl:copy-of select="." />
    </xsl:template>
</xsl:stylesheet>