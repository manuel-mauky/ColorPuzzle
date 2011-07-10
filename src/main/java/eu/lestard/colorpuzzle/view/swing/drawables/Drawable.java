package eu.lestard.colorpuzzle.view.swing.drawables;

import java.awt.Graphics2D;

public interface Drawable extends Comparable<Drawable>{
	
	void draw(Graphics2D g);
	
	void setZ(int z);
	int getZ();
	
	void setX(int x);
	int getX();
	
	void setY(int y);
	int getY();
	
}
