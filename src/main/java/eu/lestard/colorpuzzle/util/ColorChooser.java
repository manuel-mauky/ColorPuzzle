package eu.lestard.colorpuzzle.util;

import java.awt.Color;
import java.util.List;

public class ColorChooser {
	
	private List<Color> colors;

	
	/**
	 * Contructor for the ColorChooser 
	 * 
	 * @throws IllegalArgumentException if the given List is null or <code>size</code> is smaller than 1
	 * 
	 * @param colors List of Colors
	 */
	public ColorChooser(List<Color> colors) {
		if(colors == null){
			throw new IllegalArgumentException("The given Color-List is null");
		}
		
		if(colors.size() < 1){
			throw new IllegalArgumentException("The given Color-List does not contain any colors");
		}
		
		this.colors = colors;
	}

	
	/**
	 * 
	 * @return a random Color choosen from the specified List of colors 
	 */
	public Color getColor() {
		if(colors == null){
			return null;
		}
		
		int colorIndex = (int)(Math.random() * colors.size());
		
		
		return colors.get(colorIndex);
	}

}
