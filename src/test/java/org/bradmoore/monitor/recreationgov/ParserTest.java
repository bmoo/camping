package org.bradmoore.monitor.recreationgov;

import static org.hamcrest.Matchers.emptyIterable;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.bradmoore.monitor.Application;
import org.bradmoore.monitor.web.xml.RecreationGovPageParser;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.bradmoore.monitor.domain.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class ParserTest {

	@Autowired
	private RecreationGovPageParser parser;

	@Test
	public void testParseWithGoodHtml() {

		final int expectedSiteCount = 3;

		List<Site> sites = parser
				.parsePageForSites(getGoodHtmlWithSites(expectedSiteCount));

		assertNotNull(sites);
		assertThat(sites, Matchers.<Site> iterableWithSize(expectedSiteCount));
	}

	@Test
	public void testParseWithGoodHtmlNoSites() {

		final int expectedSiteCount = 0;

		List<Site> sites = parser
				.parsePageForSites(getGoodHtmlWithSites(expectedSiteCount));

		assertNotNull(sites);
		assertThat(sites, Matchers.<Site> iterableWithSize(expectedSiteCount));
	}

	@Test
	public void testParseWithGoodHtmlNoAvailability() {

		final int expectedSiteCount = 10;

		List<Site> sites = parser
				.parsePageForSites(getGoodHtmlWithNoAvailableSites(expectedSiteCount));

		assertNotNull(sites);
		assertThat(sites, Matchers.<Site> iterableWithSize(expectedSiteCount));
	}

	@Test
	public void testParseWithNoSitesHtml() {

		List<Site> sites = parser.parsePageForSites(getNoSiteHtml());

		assertNotNull(sites);
		assertThat(sites, emptyIterable());
	}

	private String getGoodHtmlWithSites(int count) {
		String s = "";

		s += "<html><head><title>mkb</title></head><body><table id='calendar'>";

		s += getGoodHeader();
		for (int i = 0; i < count; i++) {
			s += getGoodRowForSiteNumber("000" + i);
		}

		s += "</table></body></html>";

		return s;
	}

	private String getGoodHtmlWithNoAvailableSites(int count) {
		String s = "";

		s += "<html><head><title>mkb</title></head><body><table id='calendar'>";

		s += getGoodHeader();
		for (int i = 0; i < count; i++) {
			s += getNoAvailabilityRowForSiteNumber("000" + i);
		}

		s += "</table></body></html>";

		return s;
	}

	private String getNoSiteHtml() {
		return "<html><head><title>mkb</title></head><body><table id='calendar'><tr><td>HELLO</td></tr></table></body></html>";
	}

	private String getGoodHeader() {
		String s = "";
		s += "<tr>";
		s += "<th ><a href='campsiteSort.do?col=BySiteNumber&amp;dir=asc&amp;page=calendar&amp;contractCode=NRSO&amp;parkId=70925' >Site#</a></th>";
		s += "<th class='calendar' ><div class='date' id='day01date'>19</div><div class='weekday' id='day01week'>Th</div></th>";
		s += "<th class='calendar' ><div class='date' id='day02date'>20</div><div class='weekday' id='day02week'>F</div></th>";
		s += "</tr>";
		return s;
	}

	private String getGoodRowForSiteNumber(String site) {
		return getRowForSiteNumber(site, true);
	}

	private String getNoAvailabilityRowForSiteNumber(String site) {
		return getRowForSiteNumber(site, false);
	}

	private String getRowForSiteNumber(String site, boolean hasAvailability) {

		String s = "";
		s += "<tr>";

		s += "<td class='sn' >";
		s += "	<div id='maplinkicon'><a href='/camping/Upper_Pines/r/campgroundMap.do?camparea=82267466&amp;selectedSiteRb=203794&amp;contractCode=NRSO&amp;parkId=70925' class='sitemarker unavail' id='203794' >Map<img src='/images/type_rv.gif' id='i_203794' width='28' height='28' alt='190, STANDARD NONELECTRIC' title='190, STANDARD NONELECTRIC'></a></div>";
		s += "	<div class='siteListLabel'><a href='/camping/Upper_Pines/r/campsiteDetails.do?siteId=203794&amp;contractCode=NRSO&amp;parkId=70925' >";
		s += site;
		s += "</a></div>";
		s += "</td>";

		if (hasAvailability) {
			s += buildAvailableStatus();
		} else {
			s += buildUnAvailableStatus();
		}
		s += buildUnAvailableStatus();

		s += "</tr>";
		return s;

	}

	private String buildAvailableStatus() {
		return "<td class='status a' >A</td>";
	}

	private String buildUnAvailableStatus() {
		return "<td class='status x' >N</td>";
	}

}
