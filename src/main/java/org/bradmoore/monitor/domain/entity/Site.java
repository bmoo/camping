package org.bradmoore.monitor.domain.entity;

import javax.persistence.*;

/**
 * Created on 7/20/14.
 */

@Entity
@Table(name = "SITE")
public class Site {

	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "SITE_NUMBER", nullable = false)
	private Integer siteNumber;

	@JoinColumn(name = "SITE_RESERVATION_REQUEST_FK")
	@ManyToOne
	private ReservationRequest reservationRequest;

	@Column(name = "PRIORITY")
	private SitePriority priority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(Integer siteNumber) {
		this.siteNumber = siteNumber;
	}

	public ReservationRequest getReservationRequest() {
		return reservationRequest;
	}

	public void setReservationRequest(ReservationRequest reservationRequest) {
		this.reservationRequest = reservationRequest;
	}

	public SitePriority getPriority() {
		return priority;
	}

	public void setPriority(SitePriority priority) {
		this.priority = priority;
	}
}
