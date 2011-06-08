package eu.lestard.colorpuzzle;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {

	private static final int HEIGHT = 800;
	private static final int WIDTH = 800;
	
	private boolean running = true;
	
	private GameCanvas canvas;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().go();	
	}
	
	private void go(){
		

		JFrame frame = new JFrame("ColorPuzzle");
		JPanel panel = (JPanel) frame.getContentPane();
		
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		canvas = new GameCanvas();
		canvas.setBackground(Color.white);
		canvas.setSize(WIDTH, HEIGHT);
		
		
		panel.add(canvas);
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
		
		canvas.init();
		canvas.render();
		loop();
	}
	
	private void loop(){
		while(running){
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			canvas.render();
		}
		
	}

}
