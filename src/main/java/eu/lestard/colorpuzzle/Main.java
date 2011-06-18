package eu.lestard.colorpuzzle;

import eu.lestard.colorpuzzle.view.swing.SwingGUI;


public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().go();	
	}
	
	public void go(){
		new SwingGUI().go();
	}
	
}
