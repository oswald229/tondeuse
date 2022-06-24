package com.tondeuse.app.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Constants {

	private Constants() {}
	
	private static final List<String> orientations 
	= new ArrayList<>(Arrays.asList("N","E","S","W"));
	
	public static List<String> getOrientations() {
		return orientations;
	}

}
