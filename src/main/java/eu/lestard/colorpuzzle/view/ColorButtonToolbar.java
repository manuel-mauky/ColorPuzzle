package eu.lestard.colorpuzzle.view;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JToolBar;

import eu.lestard.colorpuzzle.view.events.ColorButtonPressedEvent;

public class ColorButtonToolbar extends JToolBar{
	private static final long serialVersionUID = -6438616814359398314L;
	
	@Inject
	private Event<ColorButtonPressedEvent> colorButtonPressedEvent;
	
	@Inject
	public ColorButtonToolbar(List<Color> colors) {
		super(JToolBar.VERTICAL);
		setFloatable(false);
		setBackground(Color.white);
		
		for(final Color color : colors){
			this.add(createSetColorButton(color));
		}
	}
	
	/**
	 * creates a JButton for the specified Color
	 * 
	 * @param color
	 * @return the created JButton
	 */
	private JButton createSetColorButton(final Color color) {
		JButton temp = new JButton();
		temp.setBackground(color);
		
		Insets insets = temp.getInsets();
		
		insets.top = 30;
		insets.bottom = 30;
		insets.left = 30;
		insets.right = 30;
		
		temp.setMargin(insets);
		
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				colorButtonPressedEvent.fire(new ColorButtonPressedEvent(color));
			}
		});
		
		return temp;
	}
}
