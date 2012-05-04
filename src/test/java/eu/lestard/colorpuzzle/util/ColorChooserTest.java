package eu.lestard.colorpuzzle.util;


import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.Delta;
import org.junit.Test;

public class ColorChooserTest {
	
	ColorChooser colorChooser;


	@Test
	public void testChooseAColor(){
		
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.green);
		
		colorChooser = new ColorChooser(colors);
		
		assertThat(colors).as("generated Color is valid").contains(colorChooser.getColor());
	
		List<Color> colors2 = new ArrayList<Color>();
		colors2.add(Color.black);
		colors2.add(Color.gray);
		colors2.add(Color.white);
		
		colorChooser = new ColorChooser(colors2);
		
		assertThat(colors2).contains(colorChooser.getColor());
		assertThat(colors).excludes(colorChooser.getColor());
		
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFailNoColors(){
		List<Color> colors = new ArrayList<Color>();
		
		colorChooser = new ColorChooser(colors);	
	}
	
	@Test
	public void testUniformDistribution(){
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.blue);
		colors.add(Color.red);
		colors.add(Color.green);
		colors.add(Color.yellow);
		
		colorChooser = new ColorChooser(colors);
		
		int blueCounter = 0;
		int redCounter = 0;
		int greenCounter = 0;
		int yellowCounter = 0;
		
		
		
		int randomSampleSize = 10000;
		
		for(int i=0 ; i<randomSampleSize ; i++){
			Color tempColor = colorChooser.getColor();
			
			if(tempColor == Color.blue){
				blueCounter++;
			}else if(tempColor == Color.red){
				redCounter++;
			}else if(tempColor == Color.green){
				greenCounter++;
			}else if(tempColor == Color.yellow){
				yellowCounter++;
			}else{
				fail("There should be no other Color");
			}
		}
		
		double expectedValue = randomSampleSize / 4;
		double variance = expectedValue / 10; 

		assertThat((double)blueCounter).isEqualTo(expectedValue, Delta.delta(variance));
		assertThat((double)redCounter).isEqualTo(expectedValue, Delta.delta(variance));
		assertThat((double)greenCounter).isEqualTo(expectedValue, Delta.delta(variance));
		assertThat((double)yellowCounter).isEqualTo(expectedValue, Delta.delta(variance));
	}
	
	
	
}
