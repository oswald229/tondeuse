package com.tondeuse.app.services;

import java.util.List;

import com.tondeuse.app.dto.GardenPoint;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.exception.EmptyFileThrowable;
import com.tondeuse.app.exception.IllegalMovementException;
import com.tondeuse.app.exception.PointFormatException;
import com.tondeuse.app.exception.PositionFormatException;

public interface IInstructionsReader {
	
	List<String> readInstructions(String filePath) throws Exception, EmptyFileThrowable;

	GardenPoint getPointFromCoords(String pointCoords) throws PointFormatException ;

	GardenPosition getPositionFromCoords(String positionCoords) throws PositionFormatException ;

	String getItinerary(String itineraryStr) throws IllegalMovementException;
}
