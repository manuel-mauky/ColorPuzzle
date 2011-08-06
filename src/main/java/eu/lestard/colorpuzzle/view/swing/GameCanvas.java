package eu.lestard.colorpuzzle.view.swing;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;

import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.view.swing.drawables.FinishBoard;

public class GameCanvas extends Canvas implements Runnable{
	private Grid grid;
	
	private BufferStrategy strategy;
	private Graphics2D g;
	
	
	private final int BORDER_PADDING = 20;
	private final Color BOARD_BACKGROUND_COLOR = new Color(150,150,150);
	private final Color BOARD_BACKGROUND_SHADOW_COLOR = new Color(210,210,210);
	private final Color BOARD_BACKGROUND_BORDER_COLOR = new Color(100,100,100);
	
	private final int BORDER = 10;
	
	private int boardWidth;
	private int boardHeight; 
	
	private boolean isRunning; 
	private final int FRAME_DELAY = 20;
	
	
	private boolean finished = false;
	private long cycleTime;
	
	

	public GameCanvas(Grid grid){

		isRunning = true;
		
		this.grid = grid;
		
		
		setBounds(new Rectangle(WIDTH,HEIGHT));
		setIgnoreRepaint(true);
		
		
	}
	
	@Override
	public void run(){
		
		cycleTime = System.currentTimeMillis();
		
		createBufferStrategy(2);		
		strategy = getBufferStrategy();		
		
		boardWidth = getWidth() - (2 * BORDER);
		boardHeight = getHeight() - (2 * BORDER);
		
		
		while(isRunning){
			
			render();
			
			cycleTime = cycleTime + FRAME_DELAY;
			
			long difference = cycleTime - System.currentTimeMillis();
			
			try{
				Thread.sleep(Math.max(0, difference));
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
		}
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
		
		FinishBoard finishBoard = new FinishBoard(boardWidth, boardHeight);
		
		finishBoard.draw(g);
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

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	
}
