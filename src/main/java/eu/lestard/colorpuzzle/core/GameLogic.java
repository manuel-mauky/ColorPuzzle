package eu.lestard.colorpuzzle.core;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class GameLogic {
	private int counter = 0;
	
	private Color selectedColor;
	
	
	public int getCounter() {
		return counter;
	}


	Grid grid;
	private boolean finished = false;
	
	
	public boolean isFinished() {
		return finished;
	}



	public void setFinished(boolean finished) {
		this.finished = finished;
	}



	public GameLogic(Grid grid){
		this.grid = grid;
	}
	
	
	
	public void setColor(Color color){
		Point temp = findFirstSelectedField();
		
		if(color != grid.getPiece(temp.x,temp.y).getColor()){
			
			counter++;
			
			for(int i=0 ; i<grid.getHeight() ; i++){
				for(int j=0 ; j<grid.getWidth() ; j++){
					
					if(grid.getPiece(i, j).isSelected()){
						grid.getPiece(i, j).setColor(color);
					}
				}				
			}			
		}
	}
	
	private Point findFirstSelectedField(){
		for(int i=0 ; i<grid.getHeight() ; i++){
			for(int j=0 ; j<grid.getWidth() ; j++){
				
				if (grid.getPiece(i,j).isSelected()){
					return new Point(i,j);
				}				
			}
		}
		return null;
	}

	public void checkAndSelect() {
		checkedPoints = new ArrayList<Point>();
		
		Point temp = findFirstSelectedField();
	
		if(temp == null){
			return;
		}

		checkNeighbours(temp);
		
		if(checkedPoints.size() == grid.size()){
			finished  = true;
		}
	}

	private void checkNeighbours(Point point) {
		if(point == null){
			return;
		}
		//If the point has already been checked we don't want to do it again
		if(checkedPoints.contains(point)){
			return;
		}
		
		checkedPoints.add(point);
		

		Piece thisPiece = grid.getPiece(point.x, point.y);
		
		Point[] points = new Point[4];
		
				
		points[0] = new Point(point.x + 1, point.y);
		points[1] = new Point(point.x - 1, point.y);
		points[2] = new Point(point.x, point.y - 1);
		points[3] = new Point(point.x, point.y + 1);
		
		for(int i=0 ; i<points.length ; i++){
			Piece temp = grid.getPiece(points[i].x, points[i].y);
			if(temp == null){
				continue;
			}
			
			if(temp.getColor() == thisPiece.getColor()){
				temp.setSelected(true);
				checkNeighbours(points[i]);
			}
		}
		
	}
	
	private List<Point> checkedPoints = new ArrayList<Point>();


}
