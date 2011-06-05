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


public class Grid {

	private Dimension dimension;
	
	private boolean filled;
	
	private Map<Point,Piece> gridMap;
	private ColorChooser colorChooser;
	
	
	private Canvas canvas;
	
	
	
	
	public Grid(int width, int height){
		dimension = new Dimension(width,height);
		gridMap = new HashMap<Point,Piece>();
	
		
		List<Color> colors = new ArrayList<Color>();
		
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.yellow);
		colors.add(Color.cyan);
		colors.add(Color.magenta);
		
		
		colorChooser = new ColorChooser(colors);
		
		filled = false;
		
	}
	

	public int getWidth(){
		return dimension.width;
	}
	
	public int getHeight(){
		return dimension.height;
	}
	
	public void fill(){
		for(int x=0 ; x<dimension.width ; x++){
			for(int y=0 ; y<dimension.height ; y++){
				
				Point p = new Point(x,y);
				
				Piece t = new Piece();
				t.setColor(colorChooser.getColor());
				
				gridMap.put(p, t);
				
			}
		}
		
		filled = true;
	}
	
	public Piece getPiece(int x, int y){
		
		if(!filled){
			throw new IllegalStateException("The Grid has not been filled yet. Call the fill-method before use getPiece()");
		}
		
		Point p = new Point(x,y);
		
		return gridMap.get(p);		
	}
	
	public int size(){
		return gridMap.size();
	}
	
	
}
