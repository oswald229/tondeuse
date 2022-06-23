package com.tondeuse.app;

import java.util.List;

import com.tondeuse.app.dto.Garden;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.dto.interfaces.IGarden;
import com.tondeuse.app.exception.GardenException;
import com.tondeuse.app.services.IInstructionsReader;
import com.tondeuse.app.services.impl.InstructionsReader;
import lombok.extern.slf4j.Slf4j;

/**
 * Tondeuse app.
 *
 */
@Slf4j
public class App {

	public static void main(String[] args) {
		IInstructionsReader instructionsReader = new InstructionsReader();
		String instructionsFilePath = "src/main/resources/instructions.txt";
		IGarden garden = new Garden();
		try {

			garden.initialize(instructionsFilePath, instructionsReader);
			List<GardenPosition> finalPositions = garden.mowGarden();
			finalPositions.forEach(finalPosition -> log.info(finalPosition.toString() + " "));

		} catch (GardenException e) {
			e.printStackTrace();
		}

	}
}
