package eu.lestard.colorpuzzle.core;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.core.Piece;



public class GridTest {
	
	@Test
	public void testFill(){
		int height = 5;
		int width = 5;
		
		Grid grid = new Grid(width, height);
		
		grid.fill();
		
		for(int i=0 ; i<width ; i++){
			for(int j=0 ; j<height ; j++){
				
				Piece temp = grid.getPiece(i,j);
				
				assertNotNull(temp);
				assertNotNull(temp.getColor());
				
				
			}
		}
		
		assertNull(grid.getPiece(width, height + 1));
		assertNull(grid.getPiece(width + 1, height));
		
		assertEquals("size", (height*width), grid.size());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetPieceFail(){
		
		Grid grid = new Grid(5,5);
		
		grid.getPiece(1,1);
		
		
		
	}

}
