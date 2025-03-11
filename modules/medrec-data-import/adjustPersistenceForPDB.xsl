<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : adjustPersistenceForPDB.xsl
    Created on : March 8, 2014 at 12:42 p.m.
    Description:
        Change the persistence unit properties to
        refer to the specified PDB information.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://java.sun.com/xml/ns/persistence"
                xmlns:p="http://java.sun.com/xml/ns/persistence"
                xmlns:map="http://map.data"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd"
                exclude-result-prefixes="#default p map">

  <!--
      The invoking build.xml will have created an XML
      file containing <property> elements assigning
      the correct values, based on user input,
      to several properties.  These settings will
      replace the settings already in the
      persistence.xml file for the purposes of loading
      the database with the sample MedRec data.
  -->
  <xsl:param name="propConverterFile"/>

  <!--
      The following key creates a map from each property name whose
      value is to be replaced to the <map:property> node
      specifying the name and replacement value.
  -->
  <xsl:key name="propMap" match="map:entries/map:entry" use="@map:property"/>

  <!--
      Process the property converter file.  This will populate
      the key for that document and we'll use it later in this
      transformation.
  -->
  <xsl:variable name="propMapDoc" select="document($propConverterFile)"/>

  <!--
      By default simply copy all XML input to the output.
  -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <!--
      Match each property element in the MedRec persistence unit
      from the descriptor.
  -->
  <xsl:template match="p:persistence/p:persistence-unit[@name='MedRecDataImport']/p:properties/p:property">
    <!-- Record the property name for use in the key lookup -->
    <xsl:variable name="propName" select="./@name"/>

    <!-- Create a variable containing the property value for this prop name from the key -->
    <xsl:variable name="matchedMapValue">
      <xsl:for-each select="$propMapDoc">
        <xsl:value-of select="key('propMap',$propName)/@map:value"/>
      </xsl:for-each>
    </xsl:variable>

    <!-- Use either the mapped replacement property value or, if none was found, the original value -->
    <xsl:variable name="propValueToUse">
      <xsl:choose>
        <xsl:when test="$matchedMapValue!=''">
          <xsl:value-of select="$matchedMapValue"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="./@value"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>

    <!-- Create the output property element using whichever value we choose earlier -->
    <xsl:element name="property">
      <xsl:attribute name="name">
        <xsl:value-of select="$propName"/>
      </xsl:attribute>
      <xsl:attribute name="value">
        <xsl:value-of select="$propValueToUse"/>
      </xsl:attribute>
    </xsl:element>

  </xsl:template>

</xsl:stylesheet>