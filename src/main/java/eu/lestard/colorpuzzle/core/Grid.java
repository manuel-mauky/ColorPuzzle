package eu.lestard.colorpuzzle.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.lestard.colorpuzzle.util.ColorChooser;

/**
 * The Grid class holds the array with the pieces of the puzzle. 
 * In the constructor you have to specify the number of pieces for width and height.
 * 
 * 
 * The colors of the pieces are choosen by the @see ColorChooser.
 * 
 * 
 * @author manuel mauky <manuel.mauky@googlemail.com>
 *
 */
public class Grid {

	private Dimension dimension;
	
	
	private Map<Point,Piece> gridMap;
	private ColorChooser colorChooser;	
	
	private Canvas canvas;
	
	
	
	/**
	 * 
	 * @throws IllegalArgumentException if the width and/or the height is lower than 1
	 * @throws NullPointerException if the given colorChooser is null
	 * 
	 * @param width
	 * @param height
	 * @param colorChooser
	 */
	public Grid(int width, int height, ColorChooser colorChooser){
		
		if(width < 1){
			throw new IllegalArgumentException("The width has to be at least 1");
		}
		
		if(height < 1){
			throw new IllegalArgumentException("The height has to be at least 1");
		}
		
		if(colorChooser == null){
			throw new NullPointerException("The colorChooser was null");
		}
		
		
		
		dimension = new Dimension(width,height);
		gridMap = new HashMap<Point,Piece>();
		
		this.colorChooser = colorChooser;
		
		fill();
		
	}
	

	public int getWidth(){
		return dimension.width;
	}
	
	public int getHeight(){
		return dimension.height;
	}

	private void fill(){
		for(int x=0 ; x<dimension.width ; x++){
			for(int y=0 ; y<dimension.height ; y++){
				
				Point p = new Point(x,y);
				
				Piece t = new Piece();
				t.setColor(colorChooser.getColor());
				
				gridMap.put(p, t);
				
			}
		}
		
		
		gridMap.get(new Point(0,0)).setSelected(true);
		
	}
	
	/**
	 * 
	 * 
	 * @param x the x coordinate of the piece
	 * @param y the y coordinate of the piece
	 * @return the Piece with the specified coordinates or null if there is no Piece with this coordinates
	 */
	
	public Piece getPiece(int x, int y){
		
		
		Point p = new Point(x,y);
		
		return gridMap.get(p);		
	}
	
	
	/**
	 * 
	 * @return the number of puzzle pieces in the grid
	 */
	public int size(){
		return gridMap.size();
	}
	
	
	
}
