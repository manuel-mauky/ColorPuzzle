package eu.lestard.colorpuzzle.view.swing;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class SettingsDialog extends JDialog{
	
	public SettingsDialog(Frame frame){
		super(frame);
		
		setModal(true);
		setTitle("Settings");
		
		setSize(400,400);
		setLocation(frame.getX() + 30, frame.getY() + 60);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		go();
	}
	
	private void go(){
		JPanel panel = new JPanel();
		add(panel);
		
		
		JButton okButton = new JButton("OK");
		
		panel.add(okButton);
		
		
		JButton cancelButton = new JButton("Cancel");
		
		panel.add(cancelButton);
		
		pack();
		
		
		
		
	}

}
