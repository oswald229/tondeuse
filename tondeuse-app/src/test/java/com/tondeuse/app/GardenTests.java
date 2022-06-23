package com.tondeuse.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tondeuse.app.dto.Garden;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.dto.interfaces.IGarden;
import com.tondeuse.app.exception.GardenException;
import com.tondeuse.app.services.IInstructionsReader;
import com.tondeuse.app.services.impl.InstructionsReader;

class GardenTests {

	final IInstructionsReader instructionsReader = new InstructionsReader();

	final IGarden garden = new Garden();

	static String instructionsFilePath;
	static String emptyFilePath;

	@BeforeAll
	static void setUp() {
		instructionsFilePath = "src/test/resources/instructions_test.txt";
		emptyFilePath = "src/test/resources/empty_file.txt";
	}

	@Test
	void initializeGarden_WithNullArguments_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> garden.initialize(null, null));
	}

	@Test
	void initializeGarden_WithEmptyFile_ShouldThrowAGardenException() {
		assertThrows(GardenException.class, () -> garden.initialize(emptyFilePath, instructionsReader));
	}

	@Test
	void initializeGarden_ShouldReturnAGardenObject() throws GardenException {
		IGarden initializedGarden = garden.initialize(instructionsFilePath, instructionsReader);
		assertNotNull(initializedGarden);
		assertTrue(initializedGarden instanceof Garden);
	}

	@Test
	void initializeGarden_ShouldSetTheGardenSize() throws GardenException {

		int expectedLength = 5, expectedWidth = 5;
		garden.initialize(instructionsFilePath, instructionsReader);

		assertAll(() -> assertEquals(expectedLength, garden.getLength()),
				() -> assertEquals(expectedWidth, garden.getWidth()));
	}

	@Test
	void initializeGarden_ShouldSetLawnmowersList() throws GardenException {

		garden.initialize(instructionsFilePath, instructionsReader);

		assertNotNull(garden.getLawnmowers());
	}

	@Test
	void initializeGarden_ShouldSetTheLawnmowers() throws GardenException {
		int amountOfLawnmowers = 2;
		garden.initialize(instructionsFilePath, instructionsReader);
		assertEquals(amountOfLawnmowers, garden.getLawnmowers().size());
	}

	@Test
	void initializeGarden_ShouldReturnAFullyFilledGardenObject() throws GardenException {

		int expectedLength = 5, expectedWidth = 5;
		int amountOfLawnmowers = 2;

		garden.initialize(instructionsFilePath, instructionsReader);

		assertAll(() -> assertEquals(expectedLength, garden.getLength()),
				() -> assertEquals(expectedWidth, garden.getWidth()),
				() -> assertEquals(amountOfLawnmowers, garden.getLawnmowers().size()));
	}

	@Test
	void mowGarden_ShouldReturnLawnmowersFinalPositions() throws GardenException {
		garden.initialize(instructionsFilePath, instructionsReader);
		List<GardenPosition> lawnmowersPositions = garden.mowGarden();
		
		assertNotNull(lawnmowersPositions);
	}
	
	@Test
	void mowGarden_ShouldPrintExpectedLawnmowersFinalPositions() throws GardenException {
		garden.initialize(instructionsFilePath, instructionsReader);
		List<GardenPosition> lawnmowersPositions = garden.mowGarden();
		
		assertEquals("1 3 N", lawnmowersPositions.get(0).toString());
		assertEquals("4 1 E", lawnmowersPositions.get(1).toString());
		
		
	}

}
