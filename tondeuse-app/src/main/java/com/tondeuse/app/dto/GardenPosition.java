package com.tondeuse.app.dto;

public class GardenPosition extends GardenPoint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804168178847576297L;
	
	private String orientation;
	
	public GardenPosition(int x, int y, String orientation) {
		super(x, y);
		this.orientation = orientation;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String toString() {
		return String.format("%d %d %s", this.x, this.y, this.orientation);
	}
	

}
