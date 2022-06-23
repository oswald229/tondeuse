package com.tondeuse.app.exception;


import java.io.Serial;

/**
 * The PointFormatException Class.
 */
public class PointFormatException extends GardenException {

	/** The Constant serialVersionUID. */
	@Serial
	private static final long serialVersionUID = -1741602191538558451L;

	/**
	 * Instantiates a new point format exception.
	 *
	 * @param msg the msg
	 */
	public PointFormatException(String msg){
		super(msg);
	}
	
}
