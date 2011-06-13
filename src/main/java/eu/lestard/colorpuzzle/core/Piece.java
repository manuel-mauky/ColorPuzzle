package eu.lestard.colorpuzzle.core;

import java.awt.Color;

public class Piece {
	
	private boolean selected;
	
	private Color color;
	
	public Piece(){
	}

	public Piece(Color color, boolean selected) {
		setColor(color);
		setSelected(selected);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

}
