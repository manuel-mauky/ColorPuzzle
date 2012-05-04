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

import javax.enterprise.event.Event;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.view.events.FinishEvent;
import eu.lestard.colorpuzzle.view.events.PointsEvent;
import eu.lestard.colorpuzzle.view.events.RepaintEvent;


public class GameLogicTest {
	
	
	private ColorChooser colorChooserMock;
	private int x = 5;
	private int y = 5;
	
	private Event<RepaintEvent> repaintEvent;
	private Event<FinishEvent> finishEvent;
	private Event<PointsEvent> pointsEvent;
	
	private List<Pair> selectedPairs = new ArrayList<Pair>();
	
	
	
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
		
		
		pointsEvent = mock(Event.class);
		finishEvent = mock(Event.class);
		repaintEvent = mock(Event.class);
	}
	

	
	@Ignore
	@Test
	public void testSetColor(){
		Grid grid = new Grid(3,3,colorChooserMock);
		grid.fill();
		
		grid.getPiece(0, 0).setColor(Color.red);
		grid.getPiece(0, 1).setColor(Color.red);
		grid.getPiece(1, 0).setColor(Color.blue);
		
		
		GameLogic logic = new GameLogic(grid, repaintEvent, finishEvent, pointsEvent);
		
		logic.checkAndSelect();
		
		logic.setColor(Color.green);
		
		assertThat(grid.getPiece(0,0).isSelected()).isTrue();
		assertThat(grid.getPiece(0, 1).isSelected()).isTrue();
		
		
		assertThat(grid.getPiece(0,0).getColor()).isEqualTo(Color.green);
		assertThat(grid.getPiece(0, 1).getColor()).isEqualTo(Color.green);
		assertThat(grid.getPiece(1,0).getColor()).isEqualTo(Color.blue);
		
	}
	
	

	@Test
	public void testCheckIntegrationTest(){
		int x = 5;
		int y = 5;
		
		
		colorChooserMock = mock(ColorChooser.class);
		//Our test-Grid looks like this:
		/*
		 * The letter stands for the color (g=green,b=blue,m=magenta,r=red)
		 * capital letters are selected
		 * 
		 * the grid:
		 * 
		 * G b g g m
		 * b r g b r
		 * m r b m m
		 * m g m b r
		 * b r b g b
		 * 
		 */
		
		when(colorChooserMock.getColor()).thenReturn(Color.green)
		.thenReturn(Color.blue)
		.thenReturn(Color.green)
		.thenReturn(Color.green)
		.thenReturn(Color.magenta)
		
		.thenReturn(Color.blue)
		.thenReturn(Color.red)
		.thenReturn(Color.green)
		.thenReturn(Color.blue)
		.thenReturn(Color.red)
		
		.thenReturn(Color.magenta)
		.thenReturn(Color.red)
		.thenReturn(Color.blue)
		.thenReturn(Color.magenta)
		.thenReturn(Color.magenta)
		
		.thenReturn(Color.magenta)
		.thenReturn(Color.green)
		.thenReturn(Color.magenta)
		.thenReturn(Color.blue)
		.thenReturn(Color.red)
		
		.thenReturn(Color.blue)
		.thenReturn(Color.red)
		.thenReturn(Color.blue)
		.thenReturn(Color.green)
		.thenReturn(Color.blue);
		
		
		
		Grid grid = new Grid(x,y, colorChooserMock);
		grid.fill();
		
		GameLogic logic = new GameLogic(grid, repaintEvent, finishEvent, pointsEvent);
		
		logic.checkAndSelect();
		
		
		addPair(0,0);
		verifySelected(grid);
		
		
		/* Now I change set the Color to blue. The Matix shoud be now:
		 *
		 * B B g g m
		 * B r g b r
		 * m r b m m
		 * m g m b r
		 * b r b g b
		 * 
		 */
		
		
		logic.setColor(Color.blue);
		
		addPair(0,1);
		addPair(1,0);
		verifyColor(Color.blue, grid);
		
		logic.checkAndSelect();
		
		
		// Now the three fields should be set as selected
		verifySelected(grid);
		
		/* Now I change set the Color to red. The Matix shoud be now:
		 *
		 * R R g g m
		 * R R g b r
		 * m R b m m
		 * m g m b r
		 * b r b g b
		 * 
		 */
		logic.setColor(Color.red);
		
		addPair(1,1);
		addPair(2,1);
		
		verifyColor(Color.red,grid);
		
		logic.checkAndSelect();
		
		verifySelected(grid);
		
		
		
		
	}
	
	@Ignore
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
				when(gridMock.getColor(i, y)).thenReturn(pieces[i][j].getColor());
			}
		}
		
		when(gridMock.isSelected(2,2)).thenReturn(true);

		
		//We have to prepare the mock to return null if the piece is not in the grid
		when(gridMock.getPiece(gt(x-1), anyInt())).thenReturn(null);
		
		when(gridMock.getPiece(anyInt(), gt(y-1))).thenReturn(null);
		
		
		//All selected pieces we will store in a List
		// so testing will be easier
		List<Piece> selectedPieces = new ArrayList<Piece>();
		//for now only the startPiece is selected
		selectedPieces.add(startPiece);
		
		
		
		
		GameLogic logic = new GameLogic(gridMock, repaintEvent, finishEvent, pointsEvent);
		
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
		
		Mockito.verify(gridMock, Mockito.times(1)).setColor(2, 2, Color.magenta);
		
		
//		
//		//Now the Color of the startPiece should be magenta.
//		assertThat(startPiece.getColor()).isEqualTo(Color.magenta);
		
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
	
	private class Pair{
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		int x;
		int y;
	}
	
	private void addPair(int x, int y){
		this.selectedPairs.add(new Pair(x,y));
	}
	
	/**
	 * Verifies that all selected Pairs have the given color
	 * @param color
	 */
	private void verifyColor(Color color, Grid grid){
		for(Pair p : selectedPairs){
			assertThat(grid.getColor(p.x, p.y)).isEqualTo(color);
		}
	}
	
	/**
	 * Verifies that all selected Pairs are selected
	 * @param grid
	 */
	private void verifySelected(Grid grid){
		for(Pair p : selectedPairs){
			assertThat(grid.isSelected(p.x, p.y)).isTrue();
		}
	}
	

}
