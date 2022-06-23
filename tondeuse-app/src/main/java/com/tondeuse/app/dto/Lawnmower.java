package com.tondeuse.app.dto;

import com.tondeuse.app.constants.Constants;

import java.util.List;

/**
 * The Lawnmower Class.
 */
public class Lawnmower {

	/** The Lawnmower position. */
	private GardenPosition position;

	/** The itinerary. */
	private String itinerary;

	private final int[] areaDimensions = new int[2];

	private final int maxWidth;
	private final int maxLength;

	/**
	 * Instantiates a new lawnmower.
	 *
	 * @param position  the position
	 * @param itinerary the itinerary
	 */
	public Lawnmower(GardenPosition position, String itinerary, int[] areaDimensions) {
		if (position == null || itinerary == null || areaDimensions == null) {
			throw new IllegalArgumentException();
		}

		this.position = position;
		this.itinerary = itinerary;
		this.areaDimensions[0] = Math.abs(areaDimensions[0]);
		this.areaDimensions[1] = Math.abs(areaDimensions[1]);
		this.maxWidth = this.areaDimensions[0] - 1;
		this.maxLength = this.areaDimensions[1] - 1;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public GardenPosition getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(GardenPosition position) {

		if (position.getX() <= this.maxWidth && position.getY() <= this.maxLength) {
			this.position = position;
		}
	}

	/**
	 * Gets the itinerary.
	 *
	 * @return the itinerary
	 */
	public String getItinerary() {
		return itinerary;
	}

	/**
	 * Sets the itinerary.
	 *
	 * @param itinerary the new itinerary
	 */
	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public int[] getAreaDimensions() {
		return areaDimensions;
	}

	public void move(String action) {
		if (action == null) {
			throw new IllegalArgumentException();
		}

		if ("D".equals(action) || "G".equals(action)) {
			this.rotate(action);
		} else if ("A".equals(action)) {
			this.moveForward();
		}

	}
	
	/**
	 * Follow the provided itinerary.
	 *
	 * @return the lawnmower garden position
	 */
	public GardenPosition followItinerary() {
		for(int i=0; i< this.itinerary.length(); i++) {
			this.move(String.valueOf(this.itinerary.charAt(i)));
		}
		this.itinerary = "";
		return this.position;
	}

	/**
	 * Rotate the lawnmower.
	 *
	 * @param direction the direction
	 */
	private void rotate(String direction) {
		List<String> orientations = Constants.getOrientations();
		String currentOrientation = this.position.getOrientation();
		int currentOrientationIndex = orientations.indexOf(currentOrientation);

		if(direction.equals("D")) {
			currentOrientationIndex++;
		}
		else {
			currentOrientationIndex--;
		}


		int nextOrientationIdx = currentOrientationIndex;

		if (currentOrientationIndex >= orientations.size()) {
			nextOrientationIdx = 0;
		} else if (currentOrientationIndex < 0) {
			nextOrientationIdx = orientations.size() - 1;
		}

		this.position.setOrientation(orientations.get(nextOrientationIdx));

	}

	/**
	 * Move the lawnmower forward.
	 */
	private void moveForward() {
		if (this.position.getOrientation().equals("N") && this.position.y < this.maxLength) {
			this.position.y += 1;
			return;
		}
		if (this.position.getOrientation().equals("S") && this.position.y > 0) {
			this.position.y -= 1;
			return;
		}
		
		if (this.position.getOrientation().equals("E") && this.position.x < this.maxWidth) {
			this.position.x += 1;
			return;
		}
		
		if (this.position.getOrientation().equals("W") && this.position.x > 0) {
			this.position.x -= 1;
		}
		
	}

}
