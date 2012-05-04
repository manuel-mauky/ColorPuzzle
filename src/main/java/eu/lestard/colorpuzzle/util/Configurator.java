package eu.lestard.colorpuzzle.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.lestard.colorpuzzle.ai.ArtificialIntelligence;
import eu.lestard.colorpuzzle.ai.BogoSolver;
import eu.lestard.colorpuzzle.ai.BruteForceSolver;
import eu.lestard.colorpuzzle.core.Shapes;

@Deprecated
public class Configurator {
	
	
	private static int colorCount = 7;
	
	private static Shapes shape;
	private static int width = 11;
	private static int height = 11;
	
	private static List<Color> colors;
	
	private static Map<ColorProfile,List<Color>> colorProfiles;
	private static ColorProfile colorProfile = ColorProfile.pastel;
	
//	private static ArtificialIntelligence ai = new BogoSolver();
	private static ArtificialIntelligence ai = new BruteForceSolver();
	
	
	private static int aiDelay = 200;

	static{
		colorProfiles = new HashMap<ColorProfile,List<Color>>();
		
		//Default Colors
		colors = new ArrayList<Color>();
		
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.yellow);
		colors.add(Color.cyan);
		colors.add(Color.green);
		colors.add(Color.magenta);
		colors.add(Color.orange);

		colorProfiles.put(ColorProfile.neon, colors);
		
		colors = new ArrayList<Color>();
		
		colors.add(new Color(255,255,255));		
		colors.add(new Color(192,192,192));		
		colors.add(new Color(128,128,128));		
		colors.add(new Color(64,64,64));		
		colors.add(new Color(0,0,0));
		colors.add(new Color(224,224,224));		
		colors.add(new Color(160,160,160));		
		
		colorProfiles.put(ColorProfile.blackWhite, colors);
		
		colors = new ArrayList<Color>();
		colors.add(new Color(135,206,250));
		colors.add(new Color(155,205,50));
		colors.add(new Color(238,221,130));
		colors.add(new Color(244,164,96));
		colors.add(new Color(255,99,71));
		colors.add(new Color(160,82,45));
		colors.add(new Color(139,136,120));
		
		
		colorProfiles.put(ColorProfile.pastel, colors);
		
	}
	
	public static String getWinMessage(){
		return "Gewonnen";
	}
	
	public static void setColorProfile(ColorProfile profile){
		Configurator.colorProfile = profile;
	}
	
	public static void setColorsCount(int colorCount){
		Configurator.colorCount = colorCount;
	}
	public static int getColorCount() {
		return colorCount;
	}
	
	public static List<Color> getColors() {
		
		List<Color> list = new ArrayList<Color>();
		
		int counter = 0;
		for(Color c : colorProfiles.get(colorProfile)){
			if(counter < Configurator.colorCount){
				list.add(c);
				counter++;
			}
		}
		
		return list;
	}
	
	public static void setWidth(int width){
		Configurator.width = width;
	}
	
	public static int getWidth() {
		return width;
	}

	public static void setHeight(int height){
		Configurator.height = height;
	}

	public static int getHeight() {
		return height;
	}


	public static Shapes getShape(){
		return shape;
	}
	
	public static void setAI(ArtificialIntelligence ai){
		Configurator.ai = ai;
	}
	
	public static ArtificialIntelligence getAI(){
		return ai;
	}

	public static int getAiDelay() {
		return aiDelay;
	}

	public static void setAiDelay(int aiDelay) {
		Configurator.aiDelay = aiDelay;
	}


}
