package com.tondeuse.app.exception;

import java.io.Serial;

/**
 * The GardenException Class.
 */
public class GardenException extends Exception {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = 6132573700173273923L;

	/**
	 * Instantiates a new garden exception.
	 *
	 * @param msg the msg
	 */
	public GardenException(String msg) {
		super(msg);
	}
}
