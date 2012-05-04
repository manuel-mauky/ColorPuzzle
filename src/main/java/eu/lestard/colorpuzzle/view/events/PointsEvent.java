package eu.lestard.colorpuzzle.view.events;

public class PointsEvent {
	private int points;
	
	public PointsEvent(int points){
		this.points = points;
	}
	
	public int getPoints(){
		return points;
	}
}
