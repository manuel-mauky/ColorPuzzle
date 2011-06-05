package eu.lestard.colorpuzzle.util;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Grid {

	private Dimension dimension;
	
	Map<Point,Piece> gridMap;
	
	
	public Grid(int width, int height){
		dimension = new Dimension(width,height);
		gridMap = new HashMap<Point,Piece>();
	}
	
	public void fill(){
		
	}
	
	public Piece getPiece(int x, int y){
		Point p = new Point(x,y);
		
		return gridMap.get(p);		
	}
	
	public int size(){
		return gridMap.size();
	}
	
	
}
