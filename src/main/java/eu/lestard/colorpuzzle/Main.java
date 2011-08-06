package eu.lestard.colorpuzzle;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;
import eu.lestard.colorpuzzle.view.swing.SwingGUI;


public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().go();	
	}
	
	public void go(){
		Grid grid = new Grid(Configurator.getHeight(),Configurator.getWidth(),new ColorChooser(Configurator.getColors()));
		
		GameLogic logic = new GameLogic(grid);
		
		new SwingGUI(logic).createGui();
	}
	
}
