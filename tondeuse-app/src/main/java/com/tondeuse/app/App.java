package com.tondeuse.app;

import java.util.List;

import com.tondeuse.app.dto.Garden;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.dto.interfaces.IGarden;
import com.tondeuse.app.exception.GardenException;
import com.tondeuse.app.services.IInstructionsReader;
import com.tondeuse.app.services.impl.InstructionsReader;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		IInstructionsReader instructionsReader = new InstructionsReader();
		String instructionsFilePath = "src/main/resources/instructions.txt";
		IGarden garden = new Garden();
		try {

			garden.initialize(instructionsFilePath, instructionsReader);
			List<GardenPosition> finalPositions = garden.mowGarden();
			finalPositions.stream().forEach(finalPosition -> System.out.print(finalPosition.toString() + " "));

		} catch (GardenException e) {
			e.printStackTrace();
		}

	}
}
