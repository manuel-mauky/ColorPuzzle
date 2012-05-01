package eu.lestard.colorpuzzle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;
import eu.lestard.colorpuzzle.view.swing.SwingGUI;


public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		new Main().go();
	}

	public void go() {
		log.info("ColorPuzzle started");

		Grid grid = new Grid(Configurator.getHeight(), Configurator.getWidth(),
				new ColorChooser(Configurator.getColors()));

		GameLogic logic = new GameLogic(grid);

		new SwingGUI(logic).createGui();
	}

}
