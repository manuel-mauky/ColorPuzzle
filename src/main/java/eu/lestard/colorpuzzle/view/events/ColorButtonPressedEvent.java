package eu.lestard.colorpuzzle.view.events;

import java.awt.Color;

public class ColorButtonPressedEvent {

	private Color color;
	
	public ColorButtonPressedEvent(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
}
