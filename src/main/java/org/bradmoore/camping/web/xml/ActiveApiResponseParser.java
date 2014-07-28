package org.bradmoore.camping.web.xml;


import org.bradmoore.camping.web.xml.domain.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveApiResponseParser {

	Logger logger = LoggerFactory.getLogger(ActiveApiResponseParser.class);

	public CampSitesResult parseCampSiteApiResponseXmlString(InputStream inputStream) {

		Document document = parseXmlStringToDomTree(inputStream);

		return getCampSitesResult(document);
	}

	private CampSitesResult getCampSitesResult(Document document) {
		final Node resultSetElement = document.getElementsByTagName("resultset").item(0);

		String contractCode = getNodeAttributeValue(resultSetElement, "contractCode");
		Integer siteCount = getInteger(getNodeAttributeValue(resultSetElement, "count"));
		Integer parkId = getInteger(getNodeAttributeValue(resultSetElement, "parkId"));

		final List<SiteResult> siteResults = getSiteResults(document.getElementsByTagName("result"));

		return new CampSitesResult(contractCode, siteCount, parkId, siteResults);
	}

	private List<SiteResult> getSiteResults(NodeList siteNodeList) {
		List<SiteResult> results = new ArrayList<>();

		for (int i = 0; i < siteNodeList.getLength(); i++) {
			final Node item = siteNodeList.item(i);

			String loop = getNodeAttributeValue(item, "Loop");
			String maxEquipmentLength = getNodeAttributeValue(item, "Maxeqplen");
			String maxPeople = getNodeAttributeValue(item, "Maxpeople");
			String site = getNodeAttributeValue(item, "Site");
			String siteId = getNodeAttributeValue(item, "SiteId");
			String siteReservationType = getNodeAttributeValue(item, "SiteReserveType");
			String siteType = getNodeAttributeValue(item, "SiteType");
			String mapX = getNodeAttributeValue(item, "mapx");
			String mapY = getNodeAttributeValue(item, "mapy");
			String sitePhoto = getNodeAttributeValue(item, "sitePhoto");
			String sitesWithAmps = getNodeAttributeValue(item, "sitesWithAmps");
			String sitesWithPetsAllowed = getNodeAttributeValue(item, "sitesWithPetsAllowed");
			String sitesWithSewerHookup = getNodeAttributeValue(item, "sitesWithSewerHookup");
			String sitesWithWaterHookup = getNodeAttributeValue(item, "sitesWithWaterHookup");
			String sitesWithWaterfront = getNodeAttributeValue(item, "sitesWithWaterfront");

			MapCoordinates mapCoordinates = getMapCoordinates(mapX, mapY);

			SiteResult siteResult = new SiteResult(loop, getInteger(maxEquipmentLength), getInteger(maxPeople),
			  getInteger(site), getInteger(siteId), ReservationType.SITE_SPECIFIC, SiteType.STANDARD_NON_ELECTRIC,
			  mapCoordinates, sitePhoto, getInteger(sitesWithAmps), getBoolean(sitesWithPetsAllowed),
			  getBoolean(sitesWithSewerHookup), getBoolean(sitesWithWaterHookup), null);

			results.add(siteResult);
		}

		return results;
	}

	private MapCoordinates getMapCoordinates(String mapX, String mapY) {
		MapCoordinates mapCoordinates = null;

		Double mapXDouble = getDouble(mapX);
		Double mapYDouble = getDouble(mapY);

		if (mapXDouble != null && mapYDouble != null) {
			mapCoordinates = new MapCoordinates(mapXDouble, mapYDouble);
		}
		return mapCoordinates;
	}

	protected Integer getInteger(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		} else {
			return new Integer(value);
		}
	}

	protected Double getDouble(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		} else {
			return new Double(value);
		}
	}

	protected Boolean getBoolean(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		} else {
			if ("Y".equals(value.trim())) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}

	}

	protected String getNodeAttributeValue(Node node, String attributeName) {
		NamedNodeMap attributes = node.getAttributes();
		for (int y = 0; y < attributes.getLength(); y++ ) {
			Node attr = attributes.item(y);
			if (attr.getNodeName().equalsIgnoreCase(attributeName)) {
				return attr.getNodeValue();
			}
		}
		return "";
	}

	private Document parseXmlStringToDomTree(InputStream inputStream) {
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return db.parse(inputStream);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}