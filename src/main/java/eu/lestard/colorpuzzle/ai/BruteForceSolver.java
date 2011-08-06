package eu.lestard.colorpuzzle.ai;

import java.awt.Color;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.util.Configurator;

public class BruteForceSolver implements ArtificialIntelligence {

	@Override
	public void solve(GameLogic logic) {
		
		while(!logic.isFinished()){
			
			for(Color c : Configurator.getColors()){
				
				logic.setColor(c);	
				logic.checkAndSelect();
				
				if(logic.isFinished()){
					return;
				}
				
				try {
					Thread.sleep(Configurator.getAiDelay());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}

}
