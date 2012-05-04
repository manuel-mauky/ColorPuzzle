package eu.lestard.colorpuzzle.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eu.lestard.colorpuzzle.view.events.GameExitEvent;
import eu.lestard.colorpuzzle.view.events.GameRestartEvent;
import eu.lestard.colorpuzzle.view.events.PointsEvent;

@Singleton
public class MainMenuBar extends JMenuBar {
	private static final long serialVersionUID = -6740718668407908547L;

	@Inject
	private Event<GameExitEvent> exitEvent;

	@Inject
	private Event<GameRestartEvent> restartEvent;

	private final JLabel movesLabel;

	public MainMenuBar() {
		System.out.println("mainMenubar");
		JMenu menu = new JMenu("Game");

		add(menu);

		JMenuItem menuItemPlayAgain = new JMenuItem("New Game");

		menuItemPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				restartEvent.fire(new GameRestartEvent());
			}
		});

		menu.add(menuItemPlayAgain);

		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				exitEvent.fire(new GameExitEvent());
			}
		});

		menu.addSeparator();
		menu.add(menuItemExit);

		movesLabel = new JLabel("Moves: 0");

		add(movesLabel);
	}

	public void pointsEventListener(@Observes final PointsEvent event) {
		movesLabel.setText("Moves: " + event.getPoints());
	}

	public void restartEventListener(@Observes final GameRestartEvent event) {
		movesLabel.setText("Moves: 0");
	}
}
