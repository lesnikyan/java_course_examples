<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : view.format.xsl.xsl
    Created on : 27 августа 2014 г., 21:45
    Author     : Less
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="html" version="4.0"
encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>news</title>
                <style type="text/css">
                    .item-title{
                        color: #440000;
                        background-color: #eeeee0;
                    }
                    .item-categ{
                        color: #888080;
                    }
                    .item-descr{
                        color: #222222;
                        margin-bottom: 10px;
                    }
                    .item-descr:last-of-type{
                        margin-bottom: 2px;
                    }
                </style>
            </head>
            <body>
                <h1>Новости</h1>
                <xsl:for-each select="/rss/channel/item">
                    <div> 
                        <div class="item-title"><xsl:value-of select="./title" /></div>
                        <div class="item-categ"><xsl:value-of select="./category" /></div>
                        <div class="item-descr"><xsl:value-of select="./description" /></div>
                    </div>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
