package com.tondeuse.app.exception;

import java.io.Serial;

/**
 * The IllegalMovementException Class.
 */
public class IllegalMovementException extends GardenException {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = -4804246591719974704L;

	
	/**
	 * Instantiates a new illegal movement exception.
	 *
	 * @param msg the msg
	 */
	public IllegalMovementException(String msg) {
		super(msg);
	}
}
