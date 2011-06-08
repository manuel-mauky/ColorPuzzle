package eu.lestard.colorpuzzle;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;

public class GameCanvas extends Canvas {
	
	private static final int BORDER_PADDING = 20;
	private static final Color BOARD_BACKGROUND_COLOR = new Color(150,150,150);
	private static final Color BOARD_BACKGROUND_SHADOW_COLOR = new Color(210,210,210);
	private static final Color BOARD_BACKGROUND_BORDER_COLOR = new Color(100,100,100);
	private BufferStrategy strategy;
	private Graphics2D g;
	
	private Grid grid;
	
	private ColorChooser colorChooser;
	
	private final int border = 10;
	private int boardWidth;
	private int boardHeight; 
	
	
	public GameCanvas(){
		
		
		
		
		List<Color> colors = new ArrayList<Color>();
		
		colors.add(Color.white);
		colors.add(Color.black);
		colors.add(Color.lightGray);
		colors.add(Color.gray);
		colors.add(Color.darkGray);
		
		colorChooser = new ColorChooser(colors);
		
		
		grid = new Grid(7,7,colorChooser);
		
		setBounds(new Rectangle(WIDTH,HEIGHT));
		setIgnoreRepaint(true);
		
		
	}
	
	public void init(){		
		createBufferStrategy(2);		
		strategy = getBufferStrategy();		

		
		
		boardWidth = getWidth() - (2 * border);
		boardHeight = getHeight() - (2 * border);
		
		render();		
	}
	
	public void render(){		
		g = (Graphics2D) strategy.getDrawGraphics();
		
		
		//Enable Antialiasing
		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);		
		g.setRenderingHints(qualityHints);
		
		
		
		
		
		drawBoard();	

		
		drawGrid();
		
		
	
		g.dispose();
		strategy.show();
		
	}

	/**
	 * 
	 */
	private void drawGrid() {
		
		//calculate the width and height of a single field
		
		int gridWidth = boardWidth - (2 * BORDER_PADDING);
		int gridHeight = boardWidth - (2 * BORDER_PADDING);
		
		
		//The fieldWidth is the width of the grid divided by the number of fields
		int fieldWidth = (int)gridWidth / grid.getWidth();
		//same for the Height
		int fieldHeight = (int)gridHeight / grid.getHeight();
		
		
		
		
		int fieldStartPositionX = border + BORDER_PADDING;
		int fieldStartPositionY = border + BORDER_PADDING;
		
		//Now we need the starting position for the grid.
		if(gridWidth % grid.getWidth() != 0){
			fieldStartPositionX += (gridWidth - fieldWidth * grid.getWidth() ) / 2;
		}
		
		if(gridHeight % grid.getHeight() != 0){
			fieldStartPositionY += (gridHeight - fieldHeight * grid.getHeight() ) / 2;
		}
		
		
		
		
		//Draw a line around the grid
		g.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));		
		g.setColor(Color.black);
		g.drawRect(fieldStartPositionX - 1, fieldStartPositionY - 1, (grid.getWidth() * fieldWidth)+1, (grid.getHeight() * fieldHeight) + 1);
		
		
		
		//draw the fields
		for(int i=0 ; i<grid.getWidth() ; i++){
			int tempX = fieldStartPositionX;
			for(int j=0 ; j<grid.getHeight() ; j++){
				
				g.setColor(grid.getPiece(i, j).getColor());
				
				
				g.fillRect(tempX, fieldStartPositionY, fieldWidth, fieldHeight);
				g.setColor(Color.gray);
//				g.drawRect(tempX, fieldY, fieldWidth, fieldHeight);
				tempX = tempX + fieldWidth;				
			}
			fieldStartPositionY = fieldStartPositionY + fieldHeight;
		}
	}

	/**
	 * 
	 */
	private void drawBoard() {
		int shadow = 2;
		
		g.setColor(BOARD_BACKGROUND_COLOR);
		
		
		int shadowWidth = boardWidth + shadow;
		int shadowHeight = boardHeight + shadow;
		Shape shadowShape = new RoundRectangle2D.Double(border + shadow, border + shadow, shadowWidth, shadowHeight,border,border);
		
		g.fill(shadowShape);
		
		
		
		g.setColor(BOARD_BACKGROUND_SHADOW_COLOR);
		
		double radius = 5;
		Shape board = new RoundRectangle2D.Double(border,border,boardWidth,boardHeight,radius ,radius);
		
		g.fill(board);

		g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(BOARD_BACKGROUND_BORDER_COLOR);
		g.draw(board);
	}
	

}
