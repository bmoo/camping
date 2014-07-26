package org.bradmoore.monitor.domain;


public enum AvailabilityStatusCode {
	AVAILABLE("A"), WALK_UP("W"), NOT_AVAILABLE("X"), RESERVED("R");

	private String availabilityCode;

	private AvailabilityStatusCode(String code) {
		this.availabilityCode = code;
	}

	public static AvailabilityStatusCode valueOfByCode(String code) {
		final String targetCode = (code == null) ? "" : code.toUpperCase();

		for (AvailabilityStatusCode statusCode : AvailabilityStatusCode
				.values()) {
			if (statusCode.availabilityCode.equals(targetCode)) {
				return statusCode;
			}
		}

		return null;
	}

}
