package eu.lestard.colorpuzzle.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JPanel;

import org.slf4j.Logger;

import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.Configurator;
import eu.lestard.colorpuzzle.util.qualifier.Height;
import eu.lestard.colorpuzzle.util.qualifier.Width;
import eu.lestard.colorpuzzle.view.events.FinishEvent;
import eu.lestard.colorpuzzle.view.events.GameRestartEvent;
import eu.lestard.colorpuzzle.view.events.RepaintEvent;

@Singleton
public class GameCanvas extends JPanel {
	private static final long serialVersionUID = -4821836571618624575L;

	@Inject
	private Logger log;

	private static final int BORDER_PADDING = 20;
	private static final Color BOARD_BACKGROUND_COLOR = new Color(150, 150, 150);
	private static final Color BOARD_BACKGROUND_SHADOW_COLOR = new Color(210, 210, 210);
	private static final Color BOARD_BACKGROUND_BORDER_COLOR = new Color(100, 100, 100);

	private static final int BORDER = 10;

	private int boardWidth;
	private int boardHeight;

	private Grid grid;
	
	private boolean finished = false;

	@Inject
	public GameCanvas(@Width int width, @Height int height, Grid grid) {

		this.grid = grid;

		Dimension dimension = new Dimension(width, height);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);

		setBackground(Color.white);
		setSize(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		log.debug("paint the GameCanvas");
		
		boardWidth = getWidth() - (2 * BORDER);
		boardHeight = getHeight() - (2 * BORDER);
		


		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawBoard(g2d);

		drawGrid(g2d);
		
		if(finished){
			drawFinish(g2d);
		}
		
	}

	private void drawFinish(Graphics2D g2d) {
		int finishBackgroundWidth = boardWidth / 2;
		int finishBackgroundHeight = boardHeight / 2;

		int finishBackgroundX = finishBackgroundWidth / 2;
		int finishBackgroundY = finishBackgroundHeight / 2;

		int arch = 20;

		int shadowOffset = 5;

		Shape background = new RoundRectangle2D.Double(finishBackgroundX,
				finishBackgroundY, finishBackgroundWidth,
				finishBackgroundHeight, arch, arch);

		Shape shadow = new RoundRectangle2D.Double(finishBackgroundX
				+ shadowOffset, finishBackgroundY + shadowOffset,
				finishBackgroundWidth + shadowOffset, finishBackgroundHeight
						+ shadowOffset, arch, arch);

		g2d.setColor(new Color(0, 0, 0, 120));
		g2d.fill(shadow);

		g2d.setColor(Color.white);
		g2d.fill(background);

		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));

		g2d.setColor(Color.black);
		g2d.draw(background);

		Font finishFont = new Font("DejaVu Sans Mono", Font.PLAIN, 40);

		Rectangle2D fontRect = g2d.getFontMetrics(finishFont).getStringBounds(
				Configurator.getWinMessage(), g2d);

		int winMessageX = finishBackgroundX
				+ (int) (finishBackgroundWidth - fontRect.getWidth()) / 2;
		int winMessageY = finishBackgroundY
				+ (int) ((finishBackgroundHeight - fontRect.getHeight()) / 2 + fontRect
						.getHeight() / 2);

		g2d.setFont(finishFont);
		g2d.drawString(Configurator.getWinMessage(), winMessageX, winMessageY);

		finishFont = new Font("SansSerif", Font.PLAIN, 30);

		g2d.setFont(finishFont);
	}

	private void drawGrid(Graphics2D g2d) {
		// calculate the width and height of a single field
		int gridWidth = boardWidth - (2 * BORDER_PADDING);
		int gridHeight = boardWidth - (2 * BORDER_PADDING);

		// The fieldWidth is the width of the grid divided by the number of
		// fields
		int fieldWidth = (int) gridWidth / grid.getWidth();
		// same for the Height
		int fieldHeight = (int) gridHeight / grid.getHeight();

		int fieldStartPositionX = BORDER + BORDER_PADDING;
		int fieldStartPositionY = BORDER + BORDER_PADDING;

		// Now we need the starting position for the grid.
		if (gridWidth % grid.getWidth() != 0) {
			fieldStartPositionX += (gridWidth - fieldWidth * grid.getWidth()) / 2;
		}

		if (gridHeight % grid.getHeight() != 0) {
			fieldStartPositionY += (gridHeight - fieldHeight * grid.getHeight()) / 2;
		}

		// Draw a line around the grid
		g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.black);
		g2d.drawRect(fieldStartPositionX - 1, fieldStartPositionY - 1,
				(grid.getWidth() * fieldWidth) + 1,
				(grid.getHeight() * fieldHeight) + 1);

		// draw the fields
		for (int i = 0; i < grid.getWidth(); i++) {
			int tempX = fieldStartPositionX;
			for (int j = 0; j < grid.getHeight(); j++) {

				g2d.setColor(grid.getColor(i, j));

				g2d.fillRect(tempX, fieldStartPositionY, fieldWidth,
						fieldHeight);

				g2d.setColor(Color.gray);
				tempX = tempX + fieldWidth;
			}
			fieldStartPositionY = fieldStartPositionY + fieldHeight;
		}
	}

	private void drawBoard(Graphics2D g2d) {
		int shadowOffset = 2;

		g2d.setColor(BOARD_BACKGROUND_COLOR);

		int shadowWidth = boardWidth + shadowOffset;
		int shadowHeight = boardHeight + shadowOffset;
		Shape shadowShape = new RoundRectangle2D.Double(BORDER + shadowOffset,
				BORDER + shadowOffset, shadowWidth, shadowHeight, BORDER,
				BORDER);

		g2d.fill(shadowShape);

		g2d.setColor(BOARD_BACKGROUND_SHADOW_COLOR);

		double radius = 5;
		Shape board = new RoundRectangle2D.Double(BORDER, BORDER, boardWidth,
				boardHeight, radius, radius);

		g2d.fill(board);

		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		g2d.setColor(BOARD_BACKGROUND_BORDER_COLOR);
		g2d.draw(board);
	}

	public void repaintListener(@Observes RepaintEvent event) {
		repaint();
	}

	public void finishListener(@Observes FinishEvent enent){
		finished = true;
		repaint();
	}
	
	public void gameRestartListener(@Observes GameRestartEvent event){
		finished = false;
		repaint();
	}

}
