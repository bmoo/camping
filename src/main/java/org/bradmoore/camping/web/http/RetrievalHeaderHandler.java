package org.bradmoore.camping.web.http;

import org.apache.http.client.utils.URIBuilder;
import org.bradmoore.camping.domain.entity.CampSiteDefinition;
import org.bradmoore.camping.domain.entity.ReservationRequest;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import org.joda.time.LocalDate;

@Component
public class RetrievalHeaderHandler {
	public static final String URL_PARAM_PARK_ID = "parkId";
	public static final String URL_PARAM_CONTRACT_CODE = "contractCode";
	public static final String URL_PARAM_LENGTH_OF_STAY = "lengthOfStay";

	@SuppressWarnings("SpellCheckingInspection")
	public static final String URL_PARAM_CALENDAR_START = "calarvdate";

	public static final String URL_PARAM_API_KEY = "api_key";

	@Value("${active.api.key}")
	private String apiKey;

	@Value("${active.api.url}")
	private String activeApiUrl;

	@Autowired
	private DateTimeFormatter dateFormat;

	protected URIBuilder createUriRequest(final ReservationRequest reservationRequest) throws URISyntaxException {
		final URIBuilder uriBuilder = new URIBuilder(activeApiUrl);

		final CampSiteDefinition campSiteDefinition = reservationRequest.getCampSiteDefinition();
		final String startDateAsString = this.getStartDateAsString(reservationRequest.getStartDate());
		final String lengthOfStayAsString = reservationRequest.getLengthInDays().toString();

		uriBuilder.addParameter(URL_PARAM_PARK_ID, campSiteDefinition.getParkId());
		uriBuilder.addParameter(URL_PARAM_CONTRACT_CODE, campSiteDefinition.getContractCode());
		uriBuilder.addParameter(URL_PARAM_CALENDAR_START, startDateAsString);
		uriBuilder.addParameter(URL_PARAM_LENGTH_OF_STAY, lengthOfStayAsString);
		uriBuilder.addParameter(URL_PARAM_API_KEY, apiKey);

		return uriBuilder;
	}

	private String getStartDateAsString(java.sql.Date startDate) {

		LocalDate localDate = new LocalDate(startDate);

		return localDate.toString(this.dateFormat);
	}
}