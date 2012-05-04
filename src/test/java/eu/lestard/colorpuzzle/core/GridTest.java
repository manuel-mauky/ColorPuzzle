package eu.lestard.colorpuzzle.core;


import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.lestard.colorpuzzle.util.ColorChooser;



public class GridTest {
	
	ColorChooser colorChooserMock;
	
	@Before
	public void setUp(){
		//We need to mock the ColorChooser		
		colorChooserMock = mock(ColorChooser.class);
		
		//The Colors from which the mock can choose
		final List<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.magenta);
		
		
		//The mock should return a random color for every call of the getColor method
		when(colorChooserMock.getColor()).thenAnswer(new Answer<Color>(){
			@Override
			public Color answer(InvocationOnMock invocation) throws Throwable {
				Collections.shuffle(colors);
				return colors.get(0);
			}
		});
	}
	
	@Test
	public void testFill(){
		int height = 5;
		int width = 5;
		
		Grid grid = new Grid(width, height, colorChooserMock);
		
		grid.fill();
		
		for(int i=0 ; i<width ; i++){
			for(int j=0 ; j<height ; j++){
				
				Piece temp = grid.getPiece(i,j);
				
				assertThat(temp).isNotNull();
				assertThat(temp.getColor()).isNotNull();
				
			}
		}

		assertThat(grid.getPiece(width, height + 1)).isNull();
		assertThat(grid.getPiece(width + 1, height)).isNull();
		assertThat(grid.size()).isEqualTo(height * width);
		
	}	
	
	public void testSetAndGetColor(){
		
		int height = 5;
		int width = 5;
		
		Grid grid = new Grid(width, height, colorChooserMock);
		
		grid.fill();
		
		int x = 2;
		int y = 3;
		Color color = grid.getColor(x, y);
		
		assertThat(color).isEqualTo(grid.getPiece(x, y).getColor());
		
		if(color.equals(Color.red)){
			grid.setColor(x, y, Color.blue);
			
			assertThat(grid.getColor(x, y)).isEqualTo(Color.blue);
		}else{
			grid.setColor(x, y, Color.red);
			assertThat(grid.getColor(x, y)).isEqualTo(Color.red);
		}
	}
	
	public void testIsSelected(){
		int height = 5;
		int width = 5;
		
		Grid grid = new Grid(width, height, colorChooserMock);
		grid.fill();
		
		assertThat(grid.isSelected(0, 0)).isTrue();
		
		int x = 2;
		int y = 3;
		
		assertThat(grid.isSelected(x, y)).isFalse();
		
		grid.getPiece(x, y).setSelected(true);
		assertThat(grid.isSelected(x, y)).isTrue();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail1(){
		
		//The width is 0. This should throw a IllegalArgumentException
		Grid grid = new Grid(0,10,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail2(){
		//Now the width is negative
		Grid grid = new Grid(-20,10,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail3(){
		//Height is 0.
		Grid grid = new Grid(10,0,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail4(){
		//Now height is negative
		Grid grid = new Grid(10,-20,colorChooserMock);
	}
	

}
