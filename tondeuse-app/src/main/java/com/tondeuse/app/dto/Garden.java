package com.tondeuse.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.tondeuse.app.dto.interfaces.IGarden;
import com.tondeuse.app.exception.EmptyFileThrowable;
import com.tondeuse.app.exception.GardenException;
import com.tondeuse.app.services.IInstructionsReader;

public class Garden implements IGarden {

	private int length;
	private int width;
	private List<Lawnmower> lawnmowers;

	@Override
	public Garden initialize(String instructionFilePath, IInstructionsReader instructionsReader)
			throws GardenException {
		if (instructionFilePath == null || instructionsReader == null)
			throw new IllegalArgumentException();
		try {
			List<String> instructionsList = instructionsReader.readInstructions(instructionFilePath);

			// Take the first line to determine garden size.
			GardenPoint endingPoint = instructionsReader.getPointFromCoords(instructionsList.get(0));
			this.width = endingPoint.getXCoords();
			this.length = endingPoint.getYCoords();

			// Fill the garden with the lawnmowers.
			lawnmowers = new ArrayList<>();
			for (int i = 1, j = i + 1; i < instructionsList.size() && j < instructionsList.size(); i += 2, j = i + 1) {								
				GardenPosition lawnmowerPosition = instructionsReader.getPositionFromCoords(instructionsList.get(i));
				String lawnmowerItinerary = instructionsReader.getItinerary(instructionsList.get(j));
				Lawnmower lawnmower = new Lawnmower(lawnmowerPosition,lawnmowerItinerary, new int[] {this.width,this.length});
				lawnmowers.add(lawnmower);
			}
			

		} catch (EmptyFileThrowable emptyFileEx) {
			throw new GardenException("Garden initialization failed : Empty file provided.");
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new GardenException("Garden initialization failed.");
		}

		return this;
	}

	@Override
	public int getLength() {
		return this.length;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public List<Lawnmower> getLawnmowers() {
		return this.lawnmowers;
	}
	
	@Override
	public List<GardenPosition> mowGarden() {
		
		List<GardenPosition> finalPositions = new ArrayList<>();
		
		for(Lawnmower lawnmower : this.lawnmowers) {
			finalPositions.add(lawnmower.followItinerary());
		}
		return finalPositions;
	}

}
