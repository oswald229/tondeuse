package com.tondeuse.app.exception;

/**
 * The IllegalMovementException Class.
 */
public class IllegalMovementException extends GardenException {

	/** The Constant serialVersionUID. */
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
