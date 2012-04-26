package eu.lestard.colorpuzzle.core;


import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Matchers.anyInt;
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


public class GameLogicTest {
	
	
	private ColorChooser colorChooserMock;
	private int x = 5;
	private int y = 5;
	
	
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
	public void testSetColor(){
		Grid grid = new Grid(3,3,colorChooserMock);
		
		
		grid.getPiece(0, 0).setColor(Color.red);
		grid.getPiece(0, 1).setColor(Color.red);
		grid.getPiece(1, 0).setColor(Color.blue);
		
		
		GameLogic logic = new GameLogic(grid);
		
		logic.checkAndSelect();
		
		logic.setColor(Color.green);
		
		assertThat(grid.getPiece(0,0).isSelected()).isTrue();
		assertThat(grid.getPiece(0, 1).isSelected()).isTrue();
		
		
		assertThat(grid.getPiece(0,0).getColor()).isEqualTo(Color.green);
		assertThat(grid.getPiece(0, 1).getColor()).isEqualTo(Color.green);
		assertThat(grid.getPiece(1,0).getColor()).isEqualTo(Color.blue);
		
	}
	
	
	@Test
	public void testCheck(){
		int x = 5;
		int y = 5;

		
		
		//We need a Mock for the Grid
		Grid gridMock = mock(Grid.class);
		
		//Return the value for the height as often it is requested
		when(gridMock.getHeight()).thenReturn(x);
		
		//Same for the width and Size
		when(gridMock.getWidth()).thenReturn(y);
		when(gridMock.size()).thenReturn(x*y);
		
		
		
		//Now we need to configure the mock to return the Pieces we expect
		
		//Our test-Grid looks like this:
		/*
		 * The letter stands for the color (g=green,b=blue,m=magenta,r=red)
		 * capital letters are selected
		 * 
		 * the grid:
		 * 
		 * g b g g m
		 * b r g b r
		 * m r B m m
		 * m g m b r
		 * b r b g b
		 * 
		 */

		
		
		
		//The Piece which is selected from the start
		Piece startPiece = new Piece(Color.blue,true);
		
				
		//We store the other pieces in an Array because later we want to check how they have changed
		//The Colors are Random. All other pieces are not selected
		Piece[][] pieces = new Piece[x][y];
		
		pieces[0][0] = new Piece(Color.green,false);
		pieces[0][1] = new Piece(Color.blue,false);
		pieces[0][2] = new Piece(Color.green,false);
		pieces[0][3] = new Piece(Color.green,false);
		pieces[0][4] = new Piece(Color.magenta,false);
		
		pieces[1][0] = new Piece(Color.blue,false);
		pieces[1][1] = new Piece(Color.red,false);
		pieces[1][2] = new Piece(Color.green,false);
		pieces[1][3] = new Piece(Color.blue,false);
		pieces[1][4] = new Piece(Color.red,false);
		
		pieces[2][0] = new Piece(Color.magenta,false);
		pieces[2][1] = new Piece(Color.red,false);
		//the selected one		
		pieces[2][2] = startPiece;		
		pieces[2][3] = new Piece(Color.magenta,false);		
		pieces[2][4] = new Piece(Color.magenta,false);
		
		pieces[3][0] = new Piece(Color.magenta,false);
		pieces[3][1] = new Piece(Color.green,false);
		pieces[3][2] = new Piece(Color.magenta,false);		
		pieces[3][3] = new Piece(Color.blue,false);
		pieces[3][4] = new Piece(Color.red,false);
		
		pieces[4][0] = new Piece(Color.blue,false);
		pieces[4][1] = new Piece(Color.red,false);
		pieces[4][2] = new Piece(Color.blue,false);
		pieces[4][3] = new Piece(Color.green,false);
		pieces[4][4] = new Piece(Color.blue,false);
		
		//Configure the Mock to return the expected Pieces
		for(int i=0 ; i<pieces.length ; i++){
			for(int j=0 ; j<pieces[0].length ; j++){
				when(gridMock.getPiece(i, j)).thenReturn(pieces[i][j]);
			}
		}
		

		
		//We have to prepare the mock to return null if the piece is not in the grid
		when(gridMock.getPiece(gt(x-1), anyInt())).thenReturn(null);
		
		when(gridMock.getPiece(anyInt(), gt(y-1))).thenReturn(null);
		
		
		//All selected pieces we will store in a List
		// so testing will be easier
		List<Piece> selectedPieces = new ArrayList<Piece>();
		//for now only the startPiece is selected
		selectedPieces.add(startPiece);
		
		
		
		
		GameLogic logic = new GameLogic(gridMock);

		//Call the checkAndSelect method. 
		//Nothing has changed yet so the method should not find anything
		/*
		 * The letter stands for the color (g=green,b=blue,m=magenta,r=red)
		 * capital letters are selected
		 * 
		 * the grid:
		 * 
		 * g b g g m
		 * b r g b r
		 * m r B m m
		 * m g m b r
		 * b r b g b
		 * 
		 */
		
		logic.checkAndSelect();
		
		assertThat(startPiece.getColor()).isEqualTo(Color.blue);
		assertThat(startPiece.isSelected()).isTrue();

		
		//Now the player clicks on a new Color (in our case magenta)
		logic.setColor(Color.magenta);
		
		//Now the Color of the startPiece should be magenta.
		assertThat(startPiece.getColor()).isEqualTo(Color.magenta);
		
		/*
		 * The letter stands for the color (g=green,b=blue,m=magenta,r=red)
		 * capital letters are selected
		 * 
		 * the grid:
		 * 
		 * g b g g m
		 * b r g b r
		 * m r M M M
		 * m g M b r
		 * b r b g b
		 * 
		 */
		
		//Now we check if there are other Pieces in the Grid which should be selected
		logic.checkAndSelect();
		
		//Of cource the startPiece should still be selected 
		assertThat(startPiece.isSelected()).isTrue();
		
		//There are 2 pieces which are direct neighbours of the startpiece and have the same color
		selectedPieces.add(pieces[2][3]);
		selectedPieces.add(pieces[3][2]);
		
		//The piece[2][3] has a neighbour([2][4]) with the same color too. This piece should also be selected
		selectedPieces.add(pieces[2][4]);
		
		
		
		
		
		//Now we check if every piece is marked correctly
		for(int i=0 ; i<pieces.length ; i++){
			for(int j=0 ; j<pieces[0].length ; j++){
				
				//If the piece is in the List for selected pieces ...
				if(selectedPieces.contains(pieces[i][j])){
					//it should be marked as selected
					assertThat(pieces[i][j].isSelected()).isTrue();
				}else{
					//...otherwise it should not be marked as selected
					assertThat(pieces[i][j].isSelected()).isFalse();
				}
			}
		}
		
		
		
		
		
		//The User chooses the next Color. This time he chooses Blue
		//The Grid should look like this:		
		/*
		 * The letter stands for the color (g=green,b=blue,m=magenta,r=red)
		 * capital letters are selected
		 * 
		 * the grid:
		 * 
		 * g b g g m
		 * b r g B r
		 * m r B B B
		 * m g B B r
		 * b r B g b
		 * 
		 */
	
		
		selectedPieces.add(pieces[1][3]);
		selectedPieces.add(pieces[3][3]);
		selectedPieces.add(pieces[4][2]);
		
		logic.setColor(Color.blue);
		
		logic.checkAndSelect();
		
		//Now we check if every piece is marked correctly
		for(int i=0 ; i<pieces.length ; i++){
			for(int j=0 ; j<pieces[0].length ; j++){
				
				//If the piece is in the List for selected pieces ...
				if(selectedPieces.contains(pieces[i][j])){
					//it should be marked as selected
					assertThat(pieces[i][j].isSelected()).as("Piece i:"+i + " j:" + j).isTrue();
				}else{
					//...otherwise it should not be marked as selected
					assertThat(pieces[i][j].isSelected()).as("Piece i:"+i + " j:" + j).isFalse();
				}
			}
		}
	}

}
