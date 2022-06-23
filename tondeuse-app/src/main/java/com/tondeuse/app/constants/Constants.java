package com.tondeuse.app.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Constants Class.
 */
public final class Constants {

	/**
	 * Hide constants default constructor.
	 */
	private Constants() {}
	
	/** The orientations. */
	private static final List<String> orientations 
	= new ArrayList<>(Arrays.asList("N","E","S","W"));
	
	/**
	 * Gets the orientations.
	 *
	 * @return the orientations
	 */
	public static List<String> getOrientations() {
		return orientations;
	}

}
