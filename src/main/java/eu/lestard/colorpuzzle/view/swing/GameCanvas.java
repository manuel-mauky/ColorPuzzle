package eu.lestard.colorpuzzle.view.swing;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;

import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;

public class GameCanvas extends Canvas {
	
	private static final int BORDER_PADDING = 20;
	private static final Color BOARD_BACKGROUND_COLOR = new Color(150,150,150);
	private static final Color BOARD_BACKGROUND_SHADOW_COLOR = new Color(210,210,210);
	private static final Color BOARD_BACKGROUND_BORDER_COLOR = new Color(100,100,100);
	private BufferStrategy strategy;
	private Graphics2D g;
	
	private Grid grid;
	
	private ColorChooser colorChooser;
	
	private static final int BORDER = 10;
	private int boardWidth;
	private int boardHeight; 
	
	
	private boolean finished = false;
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public GameCanvas(Grid grid){

		this.grid = grid;
		
		colorChooser = new ColorChooser(Configurator.getColors());
		
//		grid = new Grid(Configurator.getHeight(),Configurator.getWidth(),colorChooser);
		
		setBounds(new Rectangle(WIDTH,HEIGHT));
		setIgnoreRepaint(true);
	}
	
	public void init(){		
		createBufferStrategy(2);		
		strategy = getBufferStrategy();		
		
		boardWidth = getWidth() - (2 * BORDER);
		boardHeight = getHeight() - (2 * BORDER);
		
		render();
	}
	
	public void render(){		
		g = (Graphics2D) strategy.getDrawGraphics();
		
////		Enable Antialiasing
//		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//		qualityHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);		
//		g.setRenderingHints(qualityHints);
		
		//Set Antialiasing for Text
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		
		drawBoard();	

		drawGrid();
		
		if(finished){
			drawFinish();
		}
	
		g.dispose();
		strategy.show();
		
	}

	private void drawFinish() {
		System.out.println("Finished");
		
		
		
		int finishBackgroundWidth = boardWidth/2;
		int finishBackgroundHeight = boardHeight/2;
		
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
		
		int fieldStartPositionX = BORDER + BORDER_PADDING;
		int fieldStartPositionY = BORDER + BORDER_PADDING;
		
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
				
				
//				
//				if(grid.getPiece(i, j).isSelected()){
//					g.setColor(grid.getPiece(i,j).getColor().darker());
//					g.drawRect(tempX, fieldStartPositionY, fieldWidth-2, fieldHeight-2);
//					
//				}
				
				
				g.setColor(Color.gray);
				tempX = tempX + fieldWidth;				
			}
			fieldStartPositionY = fieldStartPositionY + fieldHeight;
		}
	}

	/**
	 * 
	 */
	private void drawBoard() {
		int shadowOffset = 2;
		
		g.setColor(BOARD_BACKGROUND_COLOR);
		
		int shadowWidth = boardWidth + shadowOffset;
		int shadowHeight = boardHeight + shadowOffset;
		Shape shadowShape = new RoundRectangle2D.Double(BORDER + shadowOffset, BORDER + shadowOffset, shadowWidth, shadowHeight,BORDER,BORDER);
		
		g.fill(shadowShape);
		
		g.setColor(BOARD_BACKGROUND_SHADOW_COLOR);
		
		double radius = 5;
		Shape board = new RoundRectangle2D.Double(BORDER,BORDER,boardWidth,boardHeight,radius ,radius);
		
		g.fill(board);

		g.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(BOARD_BACKGROUND_BORDER_COLOR);
		g.draw(board);
	}

	
	
	
}
