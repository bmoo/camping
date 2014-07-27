package org.bradmoore.camping.domain.entity;

import javax.persistence.*;

/**
 * Created on 6/19/14.
 */
@Entity
@Table(name = "CAMP_SITE_DEFINITION")
public class CampSiteDefinition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String displayName;
	private String parkId;
	private String contractCode;

	protected CampSiteDefinition() {}

	public CampSiteDefinition(String displayName, String parkId, String contractCode) {
		this.displayName = displayName;
		this.parkId = parkId;
		this.contractCode = contractCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	@Override
	public String toString() {
		return "CampSiteDefinition{" +
		  "id=" + id +
		  ", displayName='" + displayName + '\'' +
		  ", parkId='" + parkId + '\'' +
		  ", contractCode='" + contractCode + '\'' +
		  '}';
	}
}
