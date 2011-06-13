package eu.lestard.colorpuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;


public class Main {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 700;
	
	private boolean running = true;
	
	private GameCanvas canvas;
	private GameLogic logic;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().go();	
	}
	
	private void go(){
		
		Grid grid = new Grid(Configurator.getHeight(),Configurator.getWidth(),new ColorChooser(Configurator.getColors()));
		
		logic = new GameLogic(grid);
		logic.checkAndSelect();
		

		JFrame frame = new JFrame("ColorPuzzle");
		JPanel panel = (JPanel) frame.getContentPane();
		
//		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		panel.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		canvas = new GameCanvas(grid);
		canvas.setBackground(Color.white);
		canvas.setSize(WIDTH, HEIGHT);
		
		
		panel.add(canvas);
		
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
		
		
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
					logic.checkAndSelect();
					
					if(logic.isFinished()){
						canvas.setFinished(true);
					}
					canvas.render();
					
					
				}
				
			});
			
			
			
			buttonPanel.add(temp);
			
		}
		
		
		
		panel.add(buttonPanel,BorderLayout.EAST);
		
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
		
		canvas.init();
		canvas.render();
	}
	

	
}
