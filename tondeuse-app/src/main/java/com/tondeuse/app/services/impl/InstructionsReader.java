package com.tondeuse.app.services.impl;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tondeuse.app.dto.GardenPoint;
import com.tondeuse.app.dto.GardenPosition;
import com.tondeuse.app.exception.EmptyFileThrowable;
import com.tondeuse.app.exception.IllegalMovementException;
import com.tondeuse.app.exception.PointFormatException;
import com.tondeuse.app.exception.PositionFormatException;
import com.tondeuse.app.services.IInstructionsReader;

public class InstructionsReader implements IInstructionsReader {

	private Pattern pointCoordPattern = Pattern.compile("^(\\d*)\\s{1}(\\d*)$");
	private Pattern positionCoordPattern = Pattern.compile("^(\\d*)\\s{1}(\\d*)\\s{1}([NEWS]{1})$");
	private Pattern itineraryPattern = Pattern.compile("^[DGA]*$");
	
	public List<String> readInstructions(String filePath) throws Exception, EmptyFileThrowable {
		if(filePath == null)
			throw new IllegalArgumentException();
		
		Path path = Paths.get(filePath);
		if(!Files.exists(path)) {
			throw new FileNotFoundException();
		}
		try {
			List<String> lines = Files.readAllLines(path);
			if(lines.isEmpty()) {
				throw new EmptyFileThrowable();
			}
			return lines;
		}catch (Exception e) {
			System.err.println("Something went wrong...");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public GardenPoint getPointFromCoords(String pointCoords) throws PointFormatException {
		
		if(pointCoords == null 
				|| pointCoords.isBlank()
				|| pointCoords.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		int[] idxs = parsePointCoords(pointCoords);
		return new GardenPoint(idxs[0],idxs[1]);		
	}

	@Override
	public GardenPosition getPositionFromCoords(String positionCoords) throws PositionFormatException {
		if(positionCoords == null
			|| positionCoords.isBlank()
			|| positionCoords.isEmpty()) {
			throw new IllegalArgumentException();	
		}
		
		String cleanCoords = StringUtils.strip(positionCoords);
		Matcher matches = positionCoordPattern.matcher(cleanCoords);
		
		if(matches.find()) {		
			
			return new GardenPosition(
					Integer.valueOf(matches.group(1)),
					Integer.valueOf(matches.group(2)),
					matches.group(3));
		}
		
		throw new PositionFormatException("Coord string format is not correct.");
	}
	
	private int[] parsePointCoords(String pointCoords) throws PointFormatException {
		String cleanCoords = StringUtils.strip(pointCoords);
		Matcher matches = pointCoordPattern.matcher(cleanCoords);
		int[] pointIdxs = new int[2];
		if(matches.find()) {
			pointIdxs[0] = Integer.valueOf(matches.group(1));
			pointIdxs[1] = Integer.valueOf(matches.group(2));		
			return pointIdxs;
		}
		throw new PointFormatException("Coord string format is not correct.");
	}

	
	@Override
	public String getItinerary(String itineraryStr) throws IllegalMovementException {
		if(itineraryStr == null
			|| itineraryStr.isBlank()
			|| itineraryStr.isEmpty())
			{
				throw new IllegalArgumentException();
			}
		String cleanItineraryParam = StringUtils
				.deleteWhitespace(itineraryStr);
		Matcher matches = itineraryPattern.matcher(cleanItineraryParam);
		if(!matches.find()) {
			throw new IllegalMovementException("Unknown movement in itinerary.");
		}
		return cleanItineraryParam;
		
	}
	
	

}
