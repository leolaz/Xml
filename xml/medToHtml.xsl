<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">

    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    .tg {border-collapse:collapse;border-spacing:0;border-color:#bbb; margin-bottom:10px}
                    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px
                    5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#bbb;color:#594F4F;background-color:#E0FFEB;}
                    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px
                    5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#bbb;color:#493F3F;background-color:#9DE0AD;}
                    .tg .tg-u8fl{font-weight:bold;background-color:#9aff99;text-align:center;vertical-align:top}
                    .tg .tg-yw4l{vertical-align:top}
                    .tg .tg-yw4b{font-weight:bold;vertical-align:top}
                </style>
            </head>

            <body>
                <table class="tg">
                    <tr>
                        <th class="tg-u8fl">Name</th>
                        <th class="tg-u8fl">Pharm</th>
                        <th class="tg-u8fl">Group</th>
                        <th class="tg-u8fl">Analogs</th>
                        <th class="tg-u8fl">Versions</th>
                    </tr>

                    <xsl:for-each select="medicine/medication">
                        <xsl:sort select="group"/>
                        <tr>
                            <td class="tg-yw4l">
                                <xsl:value-of select="name"/>
                            </td>
                            <td class="tg-yw4l">
                                <xsl:value-of select="pharm"/>
                            </td>
                            <td class="tg-yw4l">
                                <xsl:value-of select="group"/>

                            </td>
                            <td class="tg-yw4l">
                                <xsl:for-each select="analogs/analog">
                                    <table>
                                        <tr>
                                            <xsl:value-of select="."/>
                                        </tr>
                                    </table>
                                </xsl:for-each>
                            </td>
                            <td class="tg-yw4b">
                                <xsl:for-each select="versions/form">
                                    <xsl:value-of select="@name"/>
                                    <table class="tg">
                                        <tr>
                                            <th>Номер свід. про реєстр.</th>
                                            <th>Дата видачі</th>
                                            <th>Дата завершення дії</th>
                                            <th>Організація видачі</th>
                                            <th>Тип упаковки</th>
                                            <th>Шт. в упаковці</th>
                                            <th>Ціна</th>
                                            <th>Дозування</th>
                                        </tr>
                                        <xsl:for-each select="version">
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="certificate/@number"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="certificate/dateOfIssue"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="certificate/expDate"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="certificate/organisation"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="package/type"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="package/quantity"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="package/price"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="dosage"/>
                                                </td>
                                            </tr>
                                        </xsl:for-each>
                                    </table>
                                </xsl:for-each>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>