package eu.lestard.colorpuzzle.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import eu.lestard.colorpuzzle.ai.ArtificialIntelligence;
import eu.lestard.colorpuzzle.ai.BogoSolver;
import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.core.listener.PointCountListener;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;

public class SwingGUI {
	private final int HEIGHT = 700;
	private final int WIDTH = 700;
	
	private boolean running = true;
	
	private GameCanvas canvas;
	private GameLogic logic;
	private Grid grid;
	
	private JLabel movesLabel;
	
	private JFrame frame;
	private JPanel panel;
	
	public SwingGUI(GameLogic logic){
		this.logic = logic;
	}
	
	
	public void createGui(){

		frame = new JFrame("ColorPuzzle");
		panel = (JPanel) frame.getContentPane();
		
		panel.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		panel.add(createGameCanvas());
		
		
		
		
		panel.add(createButtonToolbar(),BorderLayout.LINE_END);
		
		
		
		
		
		
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		
		frame.setVisible(true);
		
		
		Thread gameThread = new Thread(canvas);
		gameThread.setPriority(Thread.MIN_PRIORITY);
		gameThread.start();
		
//		initCanvas();
	}


	/**
	 * @return
	 */
	private JToolBar createButtonToolbar() {
		JToolBar buttonToolbar = new JToolBar(JToolBar.VERTICAL);
		
		buttonToolbar.setFloatable(false);
		buttonToolbar.setBackground(Color.white);
		
		
		frame.setJMenuBar(generateMenu());
		for(final Color i : Configurator.getColors()){
			buttonToolbar.add(createSetColorButton(i));
		}
		
		buttonToolbar.addSeparator();
		
		JButton solveButton = new JButton("solve");
		
		solveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Solving");
				ArtificialIntelligence ai = Configurator.getAI();
				ai.solve(logic);
				
			}
			
		});
		
		buttonToolbar.add(solveButton);
		
		
		return buttonToolbar;
	}
	
	
	private GameCanvas createGameCanvas(){
		

		logic.checkAndSelect();
		
		grid = logic.getGrid();
		
		canvas = new GameCanvas(grid);
		canvas.setBackground(Color.white);
		canvas.setSize(WIDTH, HEIGHT);
		
		return canvas;
	}


	/**
	 * creates a JButton for the specified Color
	 * 
	 * @param color
	 * @return the created JButton
	 */
	private JButton createSetColorButton(final Color color) {
		JButton temp = new JButton();
		temp.setBackground(color);
		
		Insets insets = temp.getInsets();
		
		insets.top = 30;
		insets.bottom = 30;
		insets.left = 30;
		insets.right = 30;
		
		temp.setMargin(insets);
		
		temp.addActionListener(new SetColorActionListener(color));
		
		return temp;
	}
	

	private JMenuBar generateMenu() {
		JMenuBar menuBar;
		JMenu menu;
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("Game");
		
		menuBar.add(menu);
		
		JMenuItem menuItemPlayAgain = new JMenuItem("New Game");
		menuItemPlayAgain.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
				
				logic.reset();
				logic.setFinished(false);
				
				createGui();
			}
		});
		menu.add(menuItemPlayAgain);
		
		
		JMenuItem menuItemSettings = new JMenuItem("Settings");
		menuItemSettings.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog settingsDialog = new SettingsDialog(frame);
				settingsDialog.setVisible(true);
			}
		});
		
		menu.add(menuItemSettings);
		
		
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
				System.exit(0);				
			}
		});

		
		menu.addSeparator();
		
		menu.add(menuItemExit);
		
		movesLabel = new JLabel();
		
		logic.addPointCountListener(new PointCountListener() {
			
			@Override
			public void update(int points) {
				if(movesLabel != null){
					movesLabel.setText("moves: " + points); 
				}
			}
		});
		
		logic.notifiyPointCountListeners();
		
		menuBar.add(movesLabel);
		
		
		
		return menuBar;
	}
	
	protected void newGame() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 
	 */
	public void closeWindow() {
		
		if(frame != null){
			frame.setVisible(false);
		}
	}

	/**
	 * ActionListener for the SetColor-Buttons
	 * 
	 * @author manuel mauky
	 *
	 */
	private class SetColorActionListener implements ActionListener{
		private Color color;
		
		public SetColorActionListener(Color color){
			this.color = color;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(logic != null){
				logic.setColor(color);
				
				
				
				logic.checkAndSelect();
				
				if(logic.isFinished()){
					if(canvas != null){
						canvas.setFinished(true);
					}
				}
				if(canvas != null){
					canvas.render();
				}
			}
		}
	}
	
	

}
