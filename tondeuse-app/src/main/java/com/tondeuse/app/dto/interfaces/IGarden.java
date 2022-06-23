package com.tondeuse.app.dto.interfaces;

import java.util.List;

import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.dto.Lawnmower;
import com.tondeuse.app.exception.GardenException;
import com.tondeuse.app.services.IInstructionsReader;


/**
 * The Garden Interface .
 */
public interface IGarden {

	/**
	 * Initialize garden.
	 *
	 * @param instructionFilePath the instruction file path
	 * @param instructionsReader the instructions reader
	 * @return the garden
	 * @throws GardenException the garden exception
	 */
	IGarden initialize(String instructionFilePath, IInstructionsReader instructionsReader) throws GardenException;

	/**
	 * Gets the garden length.
	 *
	 * @return the length
	 */
	int getLength();

	/**
	 * Gets the garden width.
	 *
	 * @return the width
	 */
	int getWidth();

	/**
	 * Gets the lawnmowers.
	 *
	 * @return the lawnmowers
	 */
	List<Lawnmower> getLawnmowers();

	/**
	 * Mow the garden.
	 *
	 * @return the list of lawnmowers final positions.
	 */
	List<GardenPosition> mowGarden();

}
