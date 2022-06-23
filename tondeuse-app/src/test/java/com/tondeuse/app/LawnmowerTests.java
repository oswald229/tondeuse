package com.tondeuse.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.dto.Lawnmower;


class LawnmowerTests {
	
	final String itinerary = "DDAG";
	final String orientation = "N";
	final GardenPosition position = new GardenPosition(0,0,orientation);
	final int[] areaDimension = new int[] {10,10};
	final Lawnmower lawnmower = new Lawnmower(position, itinerary, areaDimension);
	
	
	@BeforeEach
	void beforeEach() {
		lawnmower.setPosition(position);
	}

	
	@Test
	void lawnmowerInstantiation_WithNullArguments_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new Lawnmower(null, null, null));
	}
	
	@Test
	void lawnmowerInstantiation_ShouldSetLawnmowerFields() {
		
		assertAll(				
				()-> assertEquals(itinerary, lawnmower.getItinerary()),
				()-> assertEquals(orientation, lawnmower.getPosition().getOrientation()),
				()-> assertEquals(position.getX(), lawnmower.getPosition().getX()),
				()-> assertEquals(position.getY(), lawnmower.getPosition().getY()),
				()-> assertEquals(areaDimension[0], lawnmower.getAreaDimensions()[0]),
				()-> assertEquals(areaDimension[1], lawnmower.getAreaDimensions()[1])
				);
	}
	
	@Test
	void move_WithNullArgument_ShouldThrowAnIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> lawnmower.move(null));
	}
	
	@Test
	void move_WithUnknownAction_ShouldNotMoveLawnmower() {
		lawnmower.move("F");
		assertEquals(0, lawnmower.getPosition().getX());
		assertEquals(0, lawnmower.getPosition().getY());		
	}
	
	@ParameterizedTest
    @CsvSource({
        "0, 0, E, D",
        "0, 0, W, G",
        "0, 1, N, A"        
    })
	void move_ShouldRotateLawnmowerToExpectedOrientation(int x, int y, String orientation, String action) {
		
		GardenPosition expectedPosition = new GardenPosition(x,y,orientation);
		// lawnmower is at 0,0 facing North
		lawnmower.move(action);
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	}
	
	@Test
	void move_ShouldFullyRotateLawnmowerToExpectedOrientation() {
		
		GardenPosition expectedPosition = new GardenPosition(0,0,"N");
		// lawnmower is at 0,0 facing North
		lawnmower.move("D");
		lawnmower.move("D");
		lawnmower.move("D");
		lawnmower.move("D");
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	}
	

	
	@Test
	void move_ShouldMoveLawnmowerToExpectedHorizontalPosition() {
		GardenPosition expectedPosition = new GardenPosition(1,0,"E");
		// lawnmower is at 0,0 facing North
		lawnmower.move("D");
		lawnmower.move("A");
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	}
	
	@Test
	void move_WhenOutOfArea_ShouldStayInPosition() {
		GardenPosition expectedPosition = new GardenPosition(9,9,"N");
		lawnmower.setPosition(expectedPosition);
		lawnmower.move("A");
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	
	}
	
	
	@Test
	void setPosition_WithOutOfAreaCoords_ShouldDoNothing() {
		GardenPosition expectedPosition = position;
		GardenPosition wrongPosition = new GardenPosition(60,60,"N");
		lawnmower.setPosition(wrongPosition);
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	
	}
	
	@Test
	void followItinerary_ShouldMoveLawnmowerToExpectedPosition() {
		String itinerary = "DAAAAGAAAAAAGG";
		GardenPosition expectedPosition = new GardenPosition(4,6,"S");
		lawnmower.setItinerary(itinerary);
		
		lawnmower.followItinerary();
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
	
		
	}
	
	@Test
	void followItinerary_WithWeirdItineraryShouldMoveLawnmowerToExpectedPosition() {
		String itinerary = "GAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		GardenPosition expectedPosition = new GardenPosition(0,0,"W");
		lawnmower.setItinerary(itinerary);
		
		lawnmower.followItinerary();
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());	
		
	}
	
	@Test
	void followItinerary_WithWeirdItineraryShouldMoveLawnmowerToExpectedPositionBis() {
		String itinerary = "AADADA";
		GardenPosition expectedPosition = new GardenPosition(1,1,"S");
		lawnmower.setItinerary(itinerary);
		
		lawnmower.followItinerary();
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());	
		
	}
	
	@Test
	void followItinerary_WithWeirdItineraryShouldMoveLawnmowerToExpectedPositionTer() {
		String itinerary = "DAAGAGA";
		GardenPosition expectedPosition = new GardenPosition(1,1,"W");
		lawnmower.setItinerary(itinerary);
		
		lawnmower.followItinerary();
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());	
		
	}
	
	@Test
	void followItinerary_ShouldClearLawnmowerItinerary() {
		String itinerary = "DAAGAGA";
		GardenPosition expectedPosition = new GardenPosition(1,1,"W");
		lawnmower.setItinerary(itinerary);
		
		lawnmower.followItinerary();
		
		assertEquals(expectedPosition.getX(), lawnmower.getPosition().getX());
		assertEquals(expectedPosition.getY(), lawnmower.getPosition().getY());	
		assertEquals(expectedPosition.getOrientation(), lawnmower.getPosition().getOrientation());
		assertTrue(lawnmower.getItinerary().isEmpty());
	}
	
	
	@Test
	void followItinerary_ShouldReturnFinalPosition() {
		String itinerary = "AADAA";
		GardenPosition expectedPosition = new GardenPosition(2,2,"E");
		lawnmower.setItinerary(itinerary);
		
		GardenPosition finalPosition = lawnmower.followItinerary();
		assertEquals(expectedPosition, finalPosition);
	}
	
	
	
	
}
