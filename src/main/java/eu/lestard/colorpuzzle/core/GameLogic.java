package eu.lestard.colorpuzzle.core;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import eu.lestard.colorpuzzle.view.events.ColorButtonPressedEvent;
import eu.lestard.colorpuzzle.view.events.FinishEvent;
import eu.lestard.colorpuzzle.view.events.GameRestartEvent;
import eu.lestard.colorpuzzle.view.events.PointsEvent;
import eu.lestard.colorpuzzle.view.events.RepaintEvent;


public class GameLogic {
	@Inject
	Logger log;
	
	
	private int counter = 0;
	private Grid grid;
	private List<Point> checkedPoints = new ArrayList<Point>();
	
	private boolean finished = false;
	
	private Event<RepaintEvent> repaintEvent;
	private Event<FinishEvent> finishEvent;
	private Event<PointsEvent> pointsEvent;
	
	
	@Inject
	public GameLogic(Grid grid, Event<RepaintEvent> repaintEvent, Event<FinishEvent> finishEvent, Event<PointsEvent> pointsEvent){
		this.grid = grid;
		this.repaintEvent = repaintEvent;
		this.finishEvent = finishEvent;
		this.pointsEvent = pointsEvent;
	}
	
	
	public void colorChangedListener(@Observes ColorButtonPressedEvent event){
		this.setColor(event.getColor());
		checkAndSelect();
		repaintEvent.fire(new RepaintEvent());
		
	}
	
	
	public void setColor(Color color){
		Point temp = findFirstSelectedField();
		
		if(color != grid.getColor(temp.x,temp.y)){
			counter++;
			for(int i=0 ; i<grid.getHeight() ; i++){
				for(int j=0 ; j<grid.getWidth() ; j++){
					
					if(grid.isSelected(i, j)){
						grid.setColor(i, j, color);
					}
				}				
			}			
		}
	}
	
	private Point findFirstSelectedField(){
		for(int i=0 ; i<grid.getHeight() ; i++){
			for(int j=0 ; j<grid.getWidth() ; j++){
				
				if (grid.isSelected(i,j)){
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
		
		pointsEvent.fire(new PointsEvent(getCounter()));
		
		if(checkedPoints.size() == grid.size()){
			finishEvent.fire(new FinishEvent());
			finished = true;
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
			
			if(temp.getColor().equals(thisPiece.getColor())){
				temp.setSelected(true);
				checkNeighbours(points[i]);
			}
		}
		
	}
	
	

	public int getCounter() {
		return counter;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void gameRestartListener(@Observes GameRestartEvent event){
		counter = 0;
		grid.fill();
		finished = false;
	}
	
	public boolean isFinished(){
		return finished;
	}

}
