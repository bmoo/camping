package org.bradmoore.monitor.recreationgov;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import org.bradmoore.monitor.domain.AvailabilityStatusCode;

public class StatusCodeTest {

	@Test
	public void test_a() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.AVAILABLE;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("a");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_A() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.AVAILABLE;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("A");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_x() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.NOT_AVAILABLE;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("x");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_X() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.NOT_AVAILABLE;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("X");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_R() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.RESERVED;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("R");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_r() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.RESERVED;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("r");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_w() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.WALK_UP;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("w");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_W() {
		final AvailabilityStatusCode expected = AvailabilityStatusCode.WALK_UP;
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("W");

		assertNotNull(actual);
		assertEquals(expected, actual);

	}

	@Test
	public void test_null() {
		final AvailabilityStatusCode actual = AvailabilityStatusCode
				.valueOfByCode("b");

		assertNull(actual);

	}

}
