package eu.lestard.colorpuzzle.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;

public class SwingGUI {
	private static final int HEIGHT = 700;
	private static final int WIDTH = 700;
	
	private boolean running = true;
	
	private GameCanvas canvas;
	private GameLogic logic;
	private Grid grid;
	
	private JLabel movesLabel;
	
	private JFrame frame;
	private JPanel panel;
	public void go(){
		

		frame = new JFrame("ColorPuzzle");
		panel = (JPanel) frame.getContentPane();
		
		panel.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setUpGame();
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Thread gameThread = new Thread(canvas);
		gameThread.setPriority(Thread.MIN_PRIORITY);
		gameThread.start();
		
		frame.setVisible(true);
		
//		initCanvas();
	}
	
	
	private void setUpGame(){
		grid = new Grid(Configurator.getHeight(),Configurator.getWidth(),new ColorChooser(Configurator.getColors()));
		
		logic = new GameLogic(grid);
		logic.checkAndSelect();
		
		canvas = new GameCanvas(grid);
		canvas.setBackground(Color.white);
		canvas.setSize(WIDTH, HEIGHT);
		

		panel.add(canvas);
		
		JToolBar buttonToolbar = new JToolBar(JToolBar.VERTICAL);
		
		buttonToolbar.setFloatable(false);
		buttonToolbar.setBackground(Color.white);
		
//		buttonPanel.setLayout(new GridLayout(Configurator.getColors().size()+1,1));
//		buttonPanel.setBackground(Color.white);
		
		
		
		frame.setJMenuBar(generateMenu());
		
		
		
		
		
		
		for(final Color i : Configurator.getColors()){
			JButton temp = new JButton();
			temp.setBackground(i);
			
			Insets insets = temp.getInsets();
			
			insets.top = 30;
			insets.bottom = 30;
			insets.left = 30;
			insets.right = 30;
			
			temp.setMargin(insets);
			
			temp.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
					logic.setColor(i);
					
					movesLabel.setText("moves: " + logic.getCounter());
					
					logic.checkAndSelect();
					
					if(logic.isFinished()){
						canvas.setFinished(true);
						
					}
					canvas.render();
				}
			});
			
			
			buttonToolbar.add(temp);
		}
		
		
		
		panel.add(buttonToolbar,BorderLayout.LINE_END);
		
		
		
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
				frame.setVisible(false);
				
				go();
			
			}
			
		});
		menu.add(menuItemPlayAgain);
		
		
		JMenuItem menuItemSettings = new JMenuItem("Settings");
		menuItemSettings.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Not Implemented yet");
			}
			
		});
		
		menu.add(menuItemSettings);
		
		
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				System.exit(0);				
			}
		});
		
		menu.addSeparator();
		
		menu.add(menuItemExit);
		
		
		
		movesLabel = new JLabel("moves: 0");
		
		
		
		menuBar.add(movesLabel);
		
		
		return menuBar;
	}
	
	
	
	

}
