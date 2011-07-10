package eu.lestard.colorpuzzle.view.swing.drawables;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import eu.lestard.colorpuzzle.util.Configurator;

public class FinishBoard implements Drawable {
	
	private int z;
	private int x;

	private int y;
	
	private final int width;
	private final int height;
	

	public FinishBoard(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	
	public void draw(Graphics2D g) {
		
		int finishBackgroundWidth = width/2;
		int finishBackgroundHeight = height/2;
		
		int finishBackgroundX = finishBackgroundWidth/2;
		int finishBackgroundY = finishBackgroundHeight/2;
		
		
		int arch = 20;
		
		int shadowOffset = 5;
		
		Shape background = new RoundRectangle2D.Double(finishBackgroundX,finishBackgroundY,finishBackgroundWidth,finishBackgroundHeight, arch, arch);
		
		Shape shadow = new RoundRectangle2D.Double(finishBackgroundX + shadowOffset,
				finishBackgroundY + shadowOffset,
				finishBackgroundWidth + shadowOffset,
				finishBackgroundHeight + shadowOffset,
				arch, arch);
		
		g.setColor(new Color(0,0,0,120));
		g.fill(shadow);
		
		
		
		
		
		
		g.setColor(Color.white);
		g.fill(background);
		
		g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));		
		
		g.setColor(Color.black);
		g.draw(background);
		
		
		Font finishFont = new Font("DejaVu Sans Mono",Font.PLAIN,40);
		
		Rectangle2D fontRect = g.getFontMetrics(finishFont).getStringBounds(Configurator.getWinMessage(), g);
		
		
		int winMessageX = finishBackgroundX + (int)(finishBackgroundWidth - fontRect.getWidth())/2;
		int winMessageY = finishBackgroundY + (int)((finishBackgroundHeight - fontRect.getHeight())/2 + fontRect.getHeight()/2);
		
		
		g.setFont(finishFont);
		g.drawString(Configurator.getWinMessage(), winMessageX ,winMessageY);
		
		finishFont = new Font("SansSerif", Font.PLAIN,30);
		
		g.setFont(finishFont);

	}

	@Override
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public int getZ() {
		return z;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int compareTo(Drawable object) {
		if(object == null){
			throw new ClassCastException("object is null");
		}
		
		if(this.getZ() > object.getZ()){
			return 1;
		}
		
		if(this.getZ() < object.getZ()){
			return -1;
		}
		
		
		return 0;
	}

}
