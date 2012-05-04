package eu.lestard.colorpuzzle.core;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.qualifier.FieldCountX;
import eu.lestard.colorpuzzle.util.qualifier.FieldCountY;

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
@Singleton
public class Grid {

	private int height;
	private int width;
	
	private Map<Point,Piece> gridMap;
	private ColorChooser colorChooser;	
	
	
	/**
	 * 
	 * @throws IllegalArgumentException if the width and/or the height is lower than 1
	 * @throws NullPointerException if the given colorChooser is null
	 * 
	 * @param width
	 * @param height
	 * @param colorChooser
	 */
	@Inject
	public Grid(@FieldCountX int width, @FieldCountY int height, ColorChooser colorChooser){
		if(width < 1){
			throw new IllegalArgumentException("The width has to be at least 1");
		}
		
		if(height < 1){
			throw new IllegalArgumentException("The height has to be at least 1");
		}

		this.height = height;
		this.width = width;
		
		gridMap = new HashMap<Point,Piece>();
		
		this.colorChooser = colorChooser;
	}
	

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	@PostConstruct
	public void fill(){
		for(int x=0 ; x<width ; x++){
			for(int y=0 ; y<height ; y++){
				
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
	
	public boolean isSelected(int x, int y){
		Point p = new Point(x, y);
		
		Piece piece = gridMap.get(p);
		
		return piece.isSelected();
	}
	
	public Color getColor(int x, int y){
		Point p = new Point(x, y);
		
		Piece piece = gridMap.get(p);
		
		return piece.getColor();
	}
	
	public void setColor(int x, int y, Color color){
		
		Point p = new Point(x,y);
		
		Piece piece = gridMap.get(p);
		
		piece.setColor(color);
	}
	
	/**
	 * 
	 * @return the number of puzzle pieces in the grid
	 */
	public int size(){
		return gridMap.size();
	}


	public List<Piece> getPieces() {
		return new ArrayList<Piece>(gridMap.values());
	}
	
	
	
}
