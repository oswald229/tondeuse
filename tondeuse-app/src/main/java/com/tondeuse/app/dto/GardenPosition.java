package com.tondeuse.app.dto;

import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * The GardenPosition Class.
 */
@EqualsAndHashCode(callSuper = true)
public class GardenPosition extends GardenPoint {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = 3804168178847576297L;
	
	/** The orientation. */
	private String orientation;
	
	/**
	 * Instantiates a new garden position.
	 *
	 * @param x the x
	 * @param y the y
	 * @param orientation the orientation
	 */
	public GardenPosition(int x, int y, String orientation) {
		super(x, y);
		this.orientation = orientation;
	}

	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Sets the orientation.
	 *
	 * @param orientation the new orientation
	 */
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * To string method.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return String.format("%d %d %s", this.x, this.y, this.orientation);
	}


}
