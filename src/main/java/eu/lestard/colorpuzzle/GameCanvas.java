package eu.lestard.colorpuzzle;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;

import eu.lestard.colorpuzzle.core.Grid;

public class GameCanvas extends Canvas {
	
	private BufferStrategy strategy;
	private Graphics2D g;
	
	private Grid grid;
	
	
	public GameCanvas(){
		grid = new Grid(8,8);
		grid.fill();
		
		setBounds(new Rectangle(WIDTH,HEIGHT));
		setIgnoreRepaint(true);
	}
	
	public void init(){
		System.out.println("init");
		
		createBufferStrategy(2);
		
		strategy = getBufferStrategy();
		
		render();
		
	}
	
	public void render(){
		
		g = (Graphics2D) strategy.getDrawGraphics();
		
		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		
		g.setRenderingHints(qualityHints);
		
		
		int border = 10;
		int shadow = 2;
		
		g.setColor(new Color(150,150,150));
		
		
		int boardWidth = getWidth() - (2*border);
		int boardHeight = getHeight() - (2*border);
		
		int shadowWidth = boardWidth + shadow;
		int shadowHeight = boardHeight + shadow;
		Shape shadowShape = new RoundRectangle2D.Double(border + shadow, border + shadow, shadowWidth, shadowHeight,border,border);
		
		g.fill(shadowShape);
		
		
		
		g.setColor(new Color(210,210,210));
		
		double radius = 5;
		Shape board = new RoundRectangle2D.Double(border,border,boardWidth,boardHeight,radius ,radius);
		
		g.fill(board);

		g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(new Color(100,100,100));
		g.draw(board);	

		
		
		//Draw the Grid
		
		int fieldWidth = (int)(boardWidth - 20) / grid.getWidth();
		int fieldHeight = (int)(boardHeight - 20) / grid.getHeight();
		
		int fieldX = border+10;
		int fieldY = border+10;
		
		int diffX = (boardWidth - 20) - fieldWidth * grid.getWidth(); 
		if(diffX != 0){
			fieldX += diffX/2;
		}
		
		int diffY = (boardHeight - 20) - fieldHeight * grid.getHeight();
		if(diffY != 0){
			fieldY += diffY/2;
		}
		
		g.setColor(Color.red);
		
		
		for(int i=0 ; i<grid.getWidth() ; i++){
			int tempX = fieldX;
			for(int j=0 ; j<grid.getHeight() ; j++){
				
				g.setColor(grid.getPiece(i, j).getColor());
				
				
				g.fillRect(tempX, fieldY, fieldWidth, fieldHeight);
				g.setColor(Color.black);
				g.drawRect(tempX, fieldY, fieldWidth, fieldHeight);
				tempX = tempX + fieldWidth;				
			}
			fieldY = fieldY + fieldHeight;
		}
	
		g.dispose();
		strategy.show();
		
	}
	

}
