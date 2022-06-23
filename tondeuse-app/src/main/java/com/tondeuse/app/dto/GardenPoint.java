package com.tondeuse.app.dto;

import java.awt.Point;
import java.io.Serial;

/**
 * The Class GardenPoint.
 */
public class GardenPoint extends Point {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = 1738754214388083834L;
	
	/**
	 * Instantiates a new garden point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public GardenPoint(int x, int y) {
		super(x, y);
	}	
	
	/**
	 * Gets the x coords. Horizontal axis.
	 *
	 * @return the x coords
	 */
	public int getXCoords() {
		return (int) super.getX();
	}
	
	/**
	 * Gets the y coords. Vertical axis.
	 *
	 * @return the y coords
	 */
	public int getYCoords() {
		return (int) super.getY();
	}

}
