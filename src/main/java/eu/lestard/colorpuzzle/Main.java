package eu.lestard.colorpuzzle;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;
import eu.lestard.colorpuzzle.view.swing.SwingGUI;


public class Main {

	@Inject
	private Logger log;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out
				.println("Wrong Main class. Use org.jboss.weld.environment.se.StartMain instead");
		// new Main().go(null);
	}

	public void go(@Observes final ContainerInitialized event) {
		log.info("ColorPuzzle started");


		Grid grid = new Grid(Configurator.getHeight(), Configurator.getWidth(),
				new ColorChooser(Configurator.getColors()));

		GameLogic logic = new GameLogic(grid);

		new SwingGUI(logic).createGui();
	}

}
