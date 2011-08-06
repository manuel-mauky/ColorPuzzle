package eu.lestard.colorpuzzle.ai;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.core.Piece;
import eu.lestard.colorpuzzle.util.Configurator;

public class BogoSolver implements ArtificialIntelligence {

	@Override
	public void solve(GameLogic logic) {
		List<Color> colors = Configurator.getColors();

		while(!logic.isFinished()){
			
			Collections.shuffle(colors);
			
			
			logic.setColor(colors.get(0));
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
