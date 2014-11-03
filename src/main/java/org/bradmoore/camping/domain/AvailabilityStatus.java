package org.bradmoore.camping.domain;


public enum AvailabilityStatus {
	AVAILABLE("A"), WALK_UP("W"), NOT_AVAILABLE("X"), RESERVED("R");

	private String availabilityCode;

	private AvailabilityStatus(String code) {
		this.availabilityCode = code;
	}

	public static AvailabilityStatus valueOfByCode(String code) {
		final String targetCode = (code == null) ? "" : code.toUpperCase();

		for (AvailabilityStatus statusCode : AvailabilityStatus
				.values()) {
			if (statusCode.availabilityCode.equals(targetCode)) {
				return statusCode;
			}
		}

		return null;
	}

}
