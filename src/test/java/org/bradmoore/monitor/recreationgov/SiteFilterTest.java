package org.bradmoore.monitor.recreationgov;

import org.bradmoore.monitor.domain.Site;
import org.bradmoore.monitor.support.DateHelper;
import org.bradmoore.monitor.support.RecreationGovSiteFilter;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SiteFilterTest {

	@Test
	public void testSiteRangeAllIn() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 1;
		final Integer endSite = 3;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(1, 3);
		final LocalDate testStartDate = DateHelper.buildDate(2, 3, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 1);

		RecreationGovSiteFilter filter = new RecreationGovSiteFilter(startSite,
				endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);
		assertThat(filteredSites.toArray(), arrayWithSize(3));
	}

	@Test
	public void testSiteRangeHighOut() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 1;
		final Integer endSite = 3;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(1, 4);
		final LocalDate testStartDate = DateHelper.buildDate(2, 3, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 1);

		RecreationGovSiteFilter filter = new RecreationGovSiteFilter(startSite,
				endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);
		assertThat(filteredSites.toArray(), arrayWithSize(3));
	}

	@Test
	public void testSiteRangeLowOut() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 2;
		final Integer endSite = 4;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(1, 4);
		final LocalDate testStartDate = DateHelper.buildDate(2, 3, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 1);

		RecreationGovSiteFilter filter = new RecreationGovSiteFilter(startSite,
				endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);
		assertThat(filteredSites.toArray(), arrayWithSize(3));
	}

	@Test
	public void testSiteRangeAllOut() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 2;
		final Integer endSite = 4;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(5, 40);

		RecreationGovSiteFilter filter = new RecreationGovSiteFilter(startSite,
				endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);
		assertThat(filteredSites, emptyCollectionOf(Site.class));
	}

	@Test
	public void testSiteDateFilterAllIn() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 1;
		final Integer endSite = 1000;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(5, 7);
		final LocalDate testStartDate = DateHelper.buildDate(2, 2, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 3);

		final RecreationGovSiteFilter filter = new RecreationGovSiteFilter(
				startSite, endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);

		assertThat(filteredSites, hasItem(Matchers.<Site> hasProperty("days",
				iterableWithSize(3))));
		assertThat(filteredSites.toArray(),
				hasItemInArray(hasProperty("days", iterableWithSize(3))));
	}

	@Test
	public void testSiteDateFilterAllOUT() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 1;
		final Integer endSite = 1000;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(5, 7);
		final LocalDate testStartDate = DateHelper.buildDate(2, 5, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 3);

		final RecreationGovSiteFilter filter = new RecreationGovSiteFilter(
				startSite, endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);

		assertThat(filteredSites, emptyCollectionOf(Site.class));
	}

	@Test
	public void testSiteDateFilterHighOneOut() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 4, 2014);
		final Integer startSite = 1;
		final Integer endSite = 1000;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(5, 7);
		final LocalDate testStartDate = DateHelper.buildDate(2, 3, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 3);

		final RecreationGovSiteFilter filter = new RecreationGovSiteFilter(
				startSite, endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);

		assertThat(filteredSites, hasItem(Matchers.<Site> hasProperty("days",
				iterableWithSize(2))));
		assertThat(filteredSites.toArray(),
				hasItemInArray(hasProperty("days", iterableWithSize(2))));
	}

	@Test
	public void testSiteDateFilterLowOneOut() {
		final LocalDate startDate = DateHelper.buildDate(2, 3, 2014);
		final LocalDate endDate = DateHelper.buildDate(2, 5, 2014);
		final Integer startSite = 1;
		final Integer endSite = 1000;

		final List<Site> testSites = SiteTestHelper.buildSitesInRange(5, 7);
		final LocalDate testStartDate = DateHelper.buildDate(2, 2, 2014);
		SiteTestHelper.addDaysToSites(testSites, testStartDate, 3);

		final RecreationGovSiteFilter filter = new RecreationGovSiteFilter(
				startSite, endSite, startDate, endDate);

		final List<Site> filteredSites = filter.filterSites(testSites);

		assertNotNull(filteredSites);

		assertThat(filteredSites, hasItem(Matchers.<Site> hasProperty("days",
				iterableWithSize(2))));
		assertThat(filteredSites.toArray(),
				hasItemInArray(hasProperty("days", iterableWithSize(2))));
	}

}
