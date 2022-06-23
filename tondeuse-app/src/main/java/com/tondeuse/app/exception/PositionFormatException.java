package com.tondeuse.app.exception;

import java.io.Serial;

/**
 * The PositionFormatException Class.
 */
public class PositionFormatException extends GardenException {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = 1777877140523112446L;

	
	/**
	 * Instantiates a new position format exception.
	 *
	 * @param msg the msg
	 */
	public PositionFormatException(String msg) {
		super(msg);
	}
}
