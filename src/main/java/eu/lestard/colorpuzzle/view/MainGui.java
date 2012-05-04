package eu.lestard.colorpuzzle.view;

import java.awt.BorderLayout;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import eu.lestard.colorpuzzle.view.events.GameExitEvent;
import eu.lestard.colorpuzzle.view.events.GameRestartEvent;
import eu.lestard.colorpuzzle.view.events.RepaintEvent;

@ApplicationScoped
public class MainGui {
	@Inject
	private Logger log;

	private GameCanvas canvas;

	private ColorButtonToolbar colorButtonToolbar;

	private MainMenuBar mainMenuBar;

	private JFrame frame;


	protected MainGui() {
	}

	@Inject
	public MainGui(final GameCanvas canvas,
			final ColorButtonToolbar colorButtonToolbar,
			final MainMenuBar mainMenuBar) {
		this.canvas = canvas;
		this.colorButtonToolbar = colorButtonToolbar;
		this.mainMenuBar = mainMenuBar;
	}

	public void createGui(@Observes final ContainerInitialized event) {
		log.debug("begin creation of swing gui");

		frame = new JFrame("ColorPuzzle");
		JPanel panel = (JPanel) frame.getContentPane();

		panel.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponents(panel);

		frame.setJMenuBar(mainMenuBar);

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		log.info("ColorPuzzle started");

	}

	private void addComponents(final JPanel panel) {
		panel.add(canvas);
		panel.add(colorButtonToolbar, BorderLayout.LINE_END);
	}

	public void restartGameListener(@Observes final GameRestartEvent event) {
		log.info("Restart ColorPuzzle");
		frame.setVisible(false);

		createGui(null);
	}

	public void exitGameListener(@Observes final GameExitEvent event) {
		log.info("Shutdown ColorPuzzle");
		frame.setVisible(false);
		System.exit(0);
	}

}
