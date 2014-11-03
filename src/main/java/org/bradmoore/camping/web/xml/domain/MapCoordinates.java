package org.bradmoore.camping.web.xml.domain;

/**
 * Created on 7/27/14.
 */
public class MapCoordinates {
	private final Double xCoordinate;
	private final Double yCoordinate;

	public MapCoordinates(Double xCoordinate, Double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public Double getyCoordinate() {
		return yCoordinate;
	}

	@Override
	public String toString() {
		return "MapCoordinates{" +
		  "xCoordinate=" + xCoordinate +
		  ", yCoordinate=" + yCoordinate +
		  '}';
	}
}
