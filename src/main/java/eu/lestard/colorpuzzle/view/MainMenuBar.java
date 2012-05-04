package eu.lestard.colorpuzzle.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eu.lestard.colorpuzzle.view.events.GameExitEvent;
import eu.lestard.colorpuzzle.view.events.GameRestartEvent;
import eu.lestard.colorpuzzle.view.events.PointsEvent;

public class MainMenuBar extends JMenuBar{
	private static final long serialVersionUID = -6740718668407908547L;

	@Inject
	private Event<GameExitEvent> exitEvent;
	
	@Inject
	private Event<GameRestartEvent> restartEvent;
	
	private JLabel movesLabel;
	
	public MainMenuBar(){
		JMenu menu = new JMenu("Game");
		
		add(menu);
		
		JMenuItem menuItemPlayAgain = new JMenuItem ("New Game");
		
		menuItemPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restartEvent.fire(new GameRestartEvent());
			}
		});
		
		menu.add(menuItemPlayAgain);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exitEvent.fire(new GameExitEvent());
			}
		});
		
		menu.addSeparator();
		menu.add(menuItemExit);
		
		movesLabel = new JLabel("Moves: 0");
		
		add(movesLabel);
	}
	
	public void pointsEventListener(@Observes PointsEvent event){
		movesLabel.setText("Moves: " + event.getPoints());
	}
}
