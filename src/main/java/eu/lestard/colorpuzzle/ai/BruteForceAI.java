package eu.lestard.colorpuzzle.ai;

import java.awt.Color;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.util.Configurator;

public class BruteForceAI implements ArtificialIntelligence {

	@Override
	public void solve(GameLogic logic) {
		
		while(!logic.isFinished()){
			
			for(Color c : Configurator.getColors()){
				System.out.println("trying " + c);
				
				logic.setColor(c);	
				logic.checkAndSelect();
				
				if(logic.isFinished()){
					return;
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		
	}

}
