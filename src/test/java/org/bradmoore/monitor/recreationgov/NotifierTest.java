package org.bradmoore.monitor.recreationgov;

import org.bradmoore.monitor.domain.Site;
import org.bradmoore.monitor.integration.campsite.SiteConsumer;
import org.bradmoore.monitor.support.DateHelper;
import org.bradmoore.monitor.support.PriorityDeterminer;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class NotifierTest {

	@Mock
	private MailSender mailSender;

	@Mock
	private PriorityDeterminer priorityDeterminer;

	@InjectMocks
	private SiteConsumer notifier;

	@Test
	public void testSuccess() {

		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final List<Site> sites = SiteTestHelper.buildSitesInRange(1, 5);
		SiteTestHelper.addDaysToSites(sites, startDate, 2);

		doNothing().when(mailSender).send(any(SimpleMailMessage.class));

		notifier.notifyOfAnyAvailableSites(sites);
	}

	@Test
	public void testSuccessAndAssertMessage() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final List<Site> sites = SiteTestHelper.buildSitesInRange(1, 5);
		SiteTestHelper.addDaysToSites(sites, startDate, 2);

		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();

			assertNotNull(args[0]);
			assertTrue(args[0] instanceof SimpleMailMessage);

			return args[0];
		}).when(mailSender).send(any(SimpleMailMessage.class));

		notifier.notifyOfAnyAvailableSites(sites);
	}

	@Test(expected = Exception.class)
	public void testError() {
		final LocalDate startDate = DateHelper.buildDate(2, 2, 2014);
		final List<Site> sites = SiteTestHelper.buildSitesInRange(1, 5);
		SiteTestHelper.addDaysToSites(sites, startDate, 2);

		doThrow(new RuntimeException()).when(mailSender).send(
				any(SimpleMailMessage.class));

		notifier.notifyOfAnyAvailableSites(sites);
	}

}
