package com.tondeuse.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tondeuse.app.dto.GardenPoint;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.exception.EmptyFileThrowable;
import com.tondeuse.app.exception.IllegalMovementException;
import com.tondeuse.app.exception.PointFormatException;
import com.tondeuse.app.exception.PositionFormatException;
import com.tondeuse.app.services.IInstructionsReader;
import com.tondeuse.app.services.impl.InstructionsReader;

class InstructionsReaderTests {

	static String testFilePath;
	static String emptyFilePath;
	static String instructionsFilePath;

	@BeforeAll
	static void setUp() {
		testFilePath = "src/test/resources/hello_world.txt";
		emptyFilePath = "src/test/resources/empty_file.txt";
		instructionsFilePath = "src/test/resources/instructions_test.txt";
	}

	IInstructionsReader instructionsReader = new InstructionsReader();

	@Test
	void readInstructions_WithNullArguments_ShouldThrowAnIllegalArgumentException() {

		assertThrows(IllegalArgumentException.class, () -> instructionsReader.readInstructions(null));
	}

	@Test
	void readInstructions_WithInvalidPathString_ShouldThrowAnInvalidPathException() {
		String fakePath = "fake:Path";
		assertThrows(InvalidPathException.class, () -> instructionsReader.readInstructions(fakePath));
	}

	@Test
	void readInstructions_WithNonExistentFile_ShouldThrowAFileNotFoundException() throws Exception {
		String path = RandomStringUtils.random(10, true, false);
		assertThrows(FileNotFoundException.class, () -> instructionsReader.readInstructions(path));
	}
	@Test
	void readInstructions_WithEmptyFile_ShouldThrowAnEmptyFileException() {
		assertThrows(EmptyFileThrowable.class, () -> instructionsReader.readInstructions(emptyFilePath));
	}
	

	@Test
	void readInstructions_ShouldReturnAListOfString() throws Exception, Throwable {
		List<String> list = instructionsReader.readInstructions(testFilePath);
		assertNotNull(list);
		assertTrue(list instanceof List);
	}

	@Test
	void readInstructions_WithHelloWorldFilePath_ShouldHaveHelloWorld() throws Exception, Throwable {
		List<String> list = instructionsReader.readInstructions(testFilePath);
		assertTrue(list.size() > 0);
		assertEquals("Hello World!", list.get(0));
	}

	@Test
	void readInstructions_WithValidFilePath_ShouldHaveFileContent() throws Exception, Throwable {
		List<String> list = instructionsReader.readInstructions(testFilePath);
		assertTrue(list.size() > 0);
		assertEquals("Hello World!", list.get(0));
		assertEquals("Foo", list.get(1));
		assertEquals("Bar", list.get(2));
	}

	@Test
	void getPointFromCoords_WithBadArguments_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPointFromCoords(null));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPointFromCoords(""));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPointFromCoords("    "));
	}

	@Test
	void getPointFromCoords_ShouldReturnAFilledGardenPointObject() {
		String pointCoords = "          304 605        ";
		GardenPoint expectedPoint = new GardenPoint(304, 605);

		try {

			GardenPoint point = instructionsReader.getPointFromCoords(pointCoords);
			assertNotNull(point);
			assertAll(() -> assertEquals(expectedPoint.getXCoord(), point.getXCoord()),
					() -> assertEquals(expectedPoint.getYCoord(), point.getYCoord()));
		} catch (PointFormatException ex) {
			fail();
		}
	}

	@Test
	void getPointFromCoords_WithBadCoordFormat_ShouldThrowAPointFormatException() {
		assertThrows(PointFormatException.class, () -> instructionsReader.getPointFromCoords("5  "));
	}

	@Test
	void getPositionFromCoords_WithBadArguments_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPositionFromCoords(null));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPositionFromCoords(""));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getPositionFromCoords("    "));
	}

	@Test
	void getPositionFromCoords_WithBadCoordFormat_ShouldThrowAPositionFormatException() {

		assertAll(
				() -> assertThrows(PositionFormatException.class,
						() -> instructionsReader.getPositionFromCoords("5   A  2 ")),
				() -> assertThrows(PositionFormatException.class,
						() -> instructionsReader.getPositionFromCoords("5 8 F")),
				() -> assertThrows(PositionFormatException.class,
						() -> instructionsReader.getPositionFromCoords("A5   2 ")));
	}

	@Test
	void getPositionsFromCoords_ShouldReturnAPositionObject() {
		String positionCoords = "8 6 W";
		try {
			GardenPosition position = instructionsReader.getPositionFromCoords(positionCoords);
			assertNotNull(position);
			assertTrue(position instanceof GardenPosition);
		} catch (Exception ex) {
			fail();
		}

	}
	
	@Test
	void getPositionsFromCoords_ShouldReturnAFilledPositionObject() {
		String positionCoords = "8 6 W";
		try {
			GardenPosition position = instructionsReader.getPositionFromCoords(positionCoords);

			assertAll(
					()-> assertEquals(8, position.getXCoord()),
					()-> assertEquals(6, position.getYCoord()),
					()-> assertEquals("W", position.getOrientation())
					);
		} catch (Exception ex) {
			fail();
		}

	}
	
	
	@Test
	void getItinerary_WithBadArguments_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getItinerary(null));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getItinerary(""));
		assertThrows(IllegalArgumentException.class, () -> instructionsReader.getItinerary("    "));
	}
	
	@Test
	void getItinerary_ShouldReturnAString() {
		String expected = "DGAA";
		String itinerary;
		try {
			itinerary = instructionsReader.getItinerary(expected);
			assertFalse(itinerary.isBlank() && itinerary.isEmpty());
		} catch (IllegalMovementException e) {
			fail();
		}
	}
	
	@Test
	void getItinerary_WithMalFormedString_ShouldReturnWellFormattedString() {
		String itineraryParam = "D   A    G    G    ";
		String expected = "DAGG";
		try {
			assertEquals(expected, instructionsReader.getItinerary(itineraryParam));
		} catch (IllegalMovementException e) {
			fail();
		}
	}
	
	@Test
	void getItinerary_WithUnexpectedArgument_ShouldThrowAnIllegalMovementException() {
		String itineraryParam = "ABCDEF";
		assertThrows(IllegalMovementException.class, () -> instructionsReader.getItinerary(itineraryParam));
	}
	
	

}
