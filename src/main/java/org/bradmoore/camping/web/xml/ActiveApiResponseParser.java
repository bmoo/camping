package org.bradmoore.camping.web.xml;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.bradmoore.camping.web.xml.domain.CampSitesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveApiResponseParser {

	Logger logger = LoggerFactory.getLogger(ActiveApiResponseParser.class);

	public CampSitesResult parseCampSiteApiResponseXmlString(String xmlResponse) {

		DOMParser parser = new DOMParser();

		return null;
	}
}