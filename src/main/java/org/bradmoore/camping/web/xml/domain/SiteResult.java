package org.bradmoore.camping.web.xml.domain;

import org.bradmoore.camping.domain.AvailabilityStatus;

/**
 * Created on 7/27/14.
 */
public class SiteResult {
	private final String loop;
	private final Integer maxEquipmentLength;
	private final Integer maxPeople;
	private final Integer site;
	private final Integer siteId;
	private final ReservationType reservationType;
	private final SiteType siteType;
	private final MapCoordinates mapCoordinates;
	private final String sitePhotoUrl;
	private final Integer sitesWithAmps;
	private final Boolean allowsPets;
	private final Boolean hasSewerHookup;
	private final Boolean hasWaterHookup;
	private final Boolean hasWaterFront;

	public SiteResult(String loop, Integer maxEquipmentLength, Integer maxPeople, Integer site, Integer siteId,
	  ReservationType reservationType, SiteType siteType, MapCoordinates mapCoordinates, String sitePhotoUrl,
	  Integer sitesWithAmps, Boolean allowsPets, Boolean hasSewerHookup, Boolean hasWaterHookup,
	  Boolean hasWaterFront) {
		this.loop = loop;
		this.maxEquipmentLength = maxEquipmentLength;
		this.maxPeople = maxPeople;
		this.site = site;
		this.siteId = siteId;
		this.reservationType = reservationType;
		this.siteType = siteType;
		this.mapCoordinates = mapCoordinates;
		this.sitePhotoUrl = sitePhotoUrl;
		this.sitesWithAmps = sitesWithAmps;
		this.allowsPets = allowsPets;
		this.hasSewerHookup = hasSewerHookup;
		this.hasWaterHookup = hasWaterHookup;
		this.hasWaterFront = hasWaterFront;
	}

	public String getLoop() {
		return loop;
	}

	public Integer getMaxEquipmentLength() {
		return maxEquipmentLength;
	}

	public Integer getMaxPeople() {
		return maxPeople;
	}

	public Integer getSite() {
		return site;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public ReservationType getReservationType() {
		return reservationType;
	}

	public SiteType getSiteType() {
		return siteType;
	}

	public MapCoordinates getMapCoordinates() {
		return mapCoordinates;
	}

	public String getSitePhotoUrl() {
		return sitePhotoUrl;
	}

	public Integer getSitesWithAmps() {
		return sitesWithAmps;
	}

	public Boolean getAllowsPets() {
		return allowsPets;
	}

	public Boolean getHasSewerHookup() {
		return hasSewerHookup;
	}

	public Boolean getHasWaterHookup() {
		return hasWaterHookup;
	}

	public Boolean getHasWaterFront() {
		return hasWaterFront;
	}

	@Override
	public String toString() {
		return "SiteResult{" +
		  "loop='" + loop + '\'' +
		  ", maxEquipmentLength=" + maxEquipmentLength +
		  ", maxPeople=" + maxPeople +
		  ", site='" + site + '\'' +
		  ", siteId=" + siteId +
		  ", reservationType=" + reservationType +
		  ", siteType=" + siteType +
		  ", mapCoordinates=" + mapCoordinates +
		  ", sitePhotoUrl='" + sitePhotoUrl + '\'' +
		  ", sitesWithAmps=" + sitesWithAmps +
		  ", allowsPets=" + allowsPets +
		  ", hasSewerHookup=" + hasSewerHookup +
		  ", hasWaterHookup=" + hasWaterHookup +
		  ", hasWaterFront=" + hasWaterFront +
		  '}';
	}
}
