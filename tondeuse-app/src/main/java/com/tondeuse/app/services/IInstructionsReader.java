package com.tondeuse.app.services;

import java.util.List;

import com.tondeuse.app.dto.GardenPoint;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.exception.EmptyFileThrowable;
import com.tondeuse.app.exception.IllegalMovementException;
import com.tondeuse.app.exception.PointFormatException;
import com.tondeuse.app.exception.PositionFormatException;


/**
 * The InstructionsReader Interface.
 */
public interface IInstructionsReader {
	
	/**
	 * Read instructions.
	 *
	 * @param filePath the file path
	 * @return the list
	 * @throws Exception the exception
	 * @throws EmptyFileThrowable the empty file throwable
	 */
	List<String> readInstructions(String filePath) throws Exception, EmptyFileThrowable;

	/**
	 * Gets the point from coords.
	 *
	 * @param pointCoords the point coords
	 * @return the point from coords
	 * @throws PointFormatException the point format exception
	 */
	GardenPoint getPointFromCoords(String pointCoords) throws PointFormatException ;

	/**
	 * Gets the position from coords.
	 *
	 * @param positionCoords the position coords
	 * @return the position from coords
	 * @throws PositionFormatException the position format exception
	 */
	GardenPosition getPositionFromCoords(String positionCoords) throws PositionFormatException ;

	/**
	 * Gets the itinerary.
	 *
	 * @param itineraryStr the itinerary str
	 * @return the itinerary
	 * @throws IllegalMovementException the illegal movement exception
	 */
	String getItinerary(String itineraryStr) throws IllegalMovementException;
}
