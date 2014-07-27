package org.bradmoore.camping.web.xml.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created on 7/27/14.
 */
public class CampSitesResult {
	private LocalDate arrivalDate;
	private String contractCode;
	private Integer lengthOfStay;
	private Integer parkId;

	private List<SiteResult> siteResults;

	public CampSitesResult(LocalDate arrivalDate, String contractCode, Integer lengthOfStay, Integer parkId,
	  List<SiteResult> siteResults) {
		this.arrivalDate = arrivalDate;
		this.contractCode = contractCode;
		this.lengthOfStay = lengthOfStay;
		this.parkId = parkId;
		this.siteResults = siteResults;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public String getContractCode() {
		return contractCode;
	}

	public Integer getLengthOfStay() {
		return lengthOfStay;
	}

	public Integer getParkId() {
		return parkId;
	}

	public List<SiteResult> getSiteResults() {
		return siteResults;
	}

	@Override
	public String toString() {
		return "CampSitesResult{" +
		  "arrivalDate=" + arrivalDate +
		  ", contractCode='" + contractCode + '\'' +
		  ", lengthOfStay=" + lengthOfStay +
		  ", parkId=" + parkId +
		  ", sites=" + siteResults +
		  '}';
	}
}
