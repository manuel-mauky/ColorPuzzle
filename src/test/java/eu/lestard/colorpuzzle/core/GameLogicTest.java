package eu.lestard.colorpuzzle.core;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import eu.lestard.colorpuzzle.util.ColorChooser;


public class GameLogicTest {
	
	
	private ColorChooser colorChooserMock;
	private int x = 5;
	private int y = 5;
	
	
	@Before
	public void setUp(){
		//We need to mock the ColorChooser		
		colorChooserMock = EasyMock.createMock(ColorChooser.class);
		
		//The Colors from which the mock can choose
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.magenta);
		
		
		//The mock should return a random color for every call of the getColor method
		//The method is called field in the grid (x * y)
		for(int i=0 ; i < (x*y) ; i++){
			Collections.shuffle(colors);
			EasyMock.expect(colorChooserMock.getColor()).andReturn(colors.get(0));
		}
		
		EasyMock.replay(colorChooserMock);
		
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
		
		
		assertTrue(grid.getPiece(0, 0).isSelected());
		assertTrue(grid.getPiece(0, 1).isSelected());
		
		
		
		
		assertEquals(Color.green,grid.getPiece(0,0).getColor());
		assertEquals(Color.green,grid.getPiece(0,1).getColor());
		assertEquals(Color.blue,grid.getPiece(1,0).getColor());
		
	}
	
	
	@Test
	public void testCheck(){
		int x = 5;
		int y = 5;

		
		
		//We need a Mock for the Grid
		Grid gridMock = EasyMock.createMock(Grid.class);
		
		//Return the value for the height as often it is requested
		EasyMock.expect(gridMock.getHeight()).andReturn(x);
		EasyMock.expectLastCall().anyTimes();
		
		//Same for the width
		EasyMock.expect(gridMock.getWidth()).andReturn(y);
		EasyMock.expectLastCall().anyTimes();
		
		EasyMock.expect(gridMock.size()).andReturn(x*y);
		EasyMock.expectLastCall().anyTimes();
		
		
		
		
		
		
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
				EasyMock.expect(gridMock.getPiece(i,j)).andReturn(pieces[i][j]);
				EasyMock.expectLastCall().anyTimes();
			}
		}
		
		
		//We have to prepare the mock to return null if the piece is not in the grid
		
		EasyMock.expect(gridMock.getPiece(EasyMock.gt(x-1),EasyMock.anyInt())).andReturn(null);
		EasyMock.expectLastCall().anyTimes();
		
		EasyMock.expect(gridMock.getPiece(EasyMock.anyInt(),EasyMock.gt(y-1))).andReturn(null);
		EasyMock.expectLastCall().anyTimes();
		
	
		
		
		//The Mock is ready
		EasyMock.replay(gridMock);
		
		
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
		
		assertEquals(startPiece.getColor(),Color.blue);
		assertTrue(startPiece.isSelected());

		
		//Now the player clicks on a new Color (in our case magenta)
		logic.setColor(Color.magenta);
		
		//Now the Color of the startPiece should be magenta.
		assertEquals(startPiece.getColor(),Color.magenta);
		
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
		
		System.out.println("\n\n\n\n\n\n");
		//Now we check if there are other Pieces in the Grid which should be selected
		logic.checkAndSelect();
		
		//Of cource the startPiece should still be selected 
		assertTrue(startPiece.isSelected());
		
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
					assertTrue(pieces[i][j].isSelected());
				}else{
					//...otherwise it should not be marked as selected
					assertFalse(pieces[i][j].isSelected());
				}
			}
			System.out.println();
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
					assertTrue("Piece: expected true i:" + i +" j:" + j ,pieces[i][j].isSelected());
				}else{
					//...otherwise it should not be marked as selected
					assertFalse("Piece: expected false i:" + i +" j:" + j ,pieces[i][j].isSelected());
				}
			}
			System.out.println();
		}
		
		
	}

}
