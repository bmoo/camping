package org.bradmoore.camping.web.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.bradmoore.camping.domain.entity.ReservationRequest;
import org.bradmoore.camping.domain.repository.ReservationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveApiDataRetrieval {

	@Autowired
	private ReservationRequestRepository reservationRequestRepository;

	@Autowired
	private RetrievalHeaderHandler retrievalHeaderHandler;

	public List<InputStream> getXmlSiteLists() {
		final List<InputStream> responses = new ArrayList<>();

		final Iterable<ReservationRequest> reservationRequests = reservationRequestRepository.findAll();

		for (ReservationRequest reservationRequest : reservationRequests) {

			RequestConfig globalConfig = RequestConfig.custom()
			  .setCookieSpec(CookieSpecs.BEST_MATCH)
			  .build();
			CloseableHttpClient httpClient = HttpClients.custom()
			  .setDefaultRequestConfig(globalConfig)
			  .build();
			responses.add(getXmlFromReservationRequest(httpClient, reservationRequest, globalConfig));
		}

		return responses;
	}

	protected InputStream getXmlFromReservationRequest(HttpClient httpClient, ReservationRequest reservationRequest,
	  RequestConfig requestConfig) {
		HttpGet httpGet = buildHttpGet(reservationRequest, requestConfig);
		return sendHttpGetAndReturnResponseString(httpClient, httpGet);
	}

	private HttpGet buildHttpGet(ReservationRequest reservationRequest, RequestConfig requestConfig) {
		HttpGet request = null;
		try {
			// build cookie policy
			RequestConfig localConfig = RequestConfig.copy(requestConfig)
			  .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
			  .build();

			URIBuilder uriBuilder = retrievalHeaderHandler.createUriRequest(reservationRequest);
			URI uri = uriBuilder.build();
			request = new HttpGet(uri);
			request.setConfig(localConfig);

			System.out.println("Request URI:   " + uri.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (request != null) {
				request.releaseConnection();
			}
		}
		return request;
	}

	private InputStream sendHttpGetAndReturnResponseString(HttpClient httpClient, HttpGet request) {
		try {
			CookieStore cookieStore = new BasicCookieStore();
			HttpContext httpContext = new BasicHttpContext();
			httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);

			HttpResponse response = httpClient.execute(request);

			checkForGoodStatusCode(response);

			return response.getEntity().getContent();

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void checkForGoodStatusCode(HttpResponse response) {
		final int statusCode = response.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK != statusCode) {
			throw new RuntimeException("Status is not OK: [" + statusCode + "]");
		}
	}
}
