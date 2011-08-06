package eu.lestard.colorpuzzle.core;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import eu.lestard.colorpuzzle.core.listener.FinishListener;
import eu.lestard.colorpuzzle.core.listener.PointCountListener;


public class GameLogic {
	private int counter = 0;
	private Color selectedColor;
	private Grid grid;
	private boolean finished = false;
	private List<Point> checkedPoints = new ArrayList<Point>();
	
	private List<FinishListener> finishListeners;
	private List<PointCountListener> pointCountListeners;
	
	public GameLogic(Grid grid){
		this.grid = grid;
		
		pointCountListeners = new ArrayList<PointCountListener>();
		finishListeners = new ArrayList<FinishListener>();
	}
	
	
	public void addPointCountListener(PointCountListener listener){
		pointCountListeners.add(listener);
	}
	
	public void removePointCountListener(PointCountListener listener){
		pointCountListeners.remove(listener);
	}
	
	public void notifiyPointCountListeners(){
		for(PointCountListener p : pointCountListeners){
			p.update(getCounter());
		}
	}
	
	
	public void addFinishListener(FinishListener listener){
		finishListeners.add(listener);
	}
	public void removeFinishListeners(FinishListener listener){
		finishListeners.remove(listener);
	}
	public void notifyFinishListeners(){
		for(FinishListener f : finishListeners){
			f.update(isFinished());
		}
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
		
		notifiyPointCountListeners();
		
		if(checkedPoints.size() == grid.size()){
			finished  = true;
			notifyFinishListeners();
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
	
	

	public int getCounter() {
		return counter;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public Grid getGrid() {
		return grid;
	}


	public void reset() {
		counter = 0;
		grid.fill();
	}

}
