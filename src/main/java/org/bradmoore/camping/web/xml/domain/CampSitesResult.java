package org.bradmoore.camping.web.xml.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created on 7/27/14.
 */
public class CampSitesResult {
	private String contractCode;
	private Integer count;
	private Integer parkId;

	private List<SiteResult> siteResults;

	public CampSitesResult(String contractCode, Integer count, Integer parkId, List<SiteResult> siteResults) {
		this.contractCode = contractCode;
		this.count = count;
		this.parkId = parkId;
		this.siteResults = siteResults;
	}

	public String getContractCode() {
		return contractCode;
	}

	public Integer getCount() {
		return count;
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
		  "contractCode='" + contractCode + '\'' +
		  ", count=" + count +
		  ", parkId=" + parkId +
		  ", siteResults=" + siteResults +
		  '}';
	}
}
