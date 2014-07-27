package org.bradmoore.camping.domain.entity;

import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.List;

/**
 * Created on 7/20/14.
 */
@Entity
@Table(name = "RESERVATION_REQUEST")
public class ReservationRequest {

	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "CAMP_SITE_DEFINITION_FK", nullable = false)
	private CampSiteDefinition campSiteDefinition;

	@Column(name = "START_DATE", nullable = false)
	private LocalDate startDate;

	@Column(name = "LENGTH_OF_STAY", nullable = false)
	private Integer lengthInDays;

	@OneToMany(mappedBy = "reservationRequest")
	private List<Site> sites;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CampSiteDefinition getCampSiteDefinition() {
		return campSiteDefinition;
	}

	public void setCampSiteDefinition(CampSiteDefinition campSiteDefinition) {
		this.campSiteDefinition = campSiteDefinition;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Integer getLengthInDays() {
		return lengthInDays;
	}

	public void setLengthInDays(Integer lengthInDays) {
		this.lengthInDays = lengthInDays;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
}
