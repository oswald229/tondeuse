package com.tondeuse.app.dto;

import java.awt.Point;

/**
 * The Class GardenPoint.
 */
public class GardenPoint extends Point {

	/** The Constant serialVersionUID. */
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
	 * Gets the x coord. Horizontal axis.
	 *
	 * @return the x coord
	 */
	public int getXCoord() {
		return (int) super.getX();
	}
	
	/**
	 * Gets the y coord. Vertical axis.
	 *
	 * @return the y coord
	 */
	public int getYCoord() {
		return (int) super.getY();
	}

}
