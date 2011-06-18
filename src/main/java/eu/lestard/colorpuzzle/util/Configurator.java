package eu.lestard.colorpuzzle.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.lestard.colorpuzzle.core.Shapes;

public class Configurator {
	
	private static Shapes shape;
	private static int width = 13;
	private static int height = 13;
	
	private static List<Color> colors;
	
	private static Map<String,List<Color>> colorProfiles;
	private static String colorProfile = "pastel";
	

	static{
		colorProfiles = new HashMap<String,List<Color>>();
		
		//Default Colors
		colors = new ArrayList<Color>();
		
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.yellow);
		colors.add(Color.cyan);
		colors.add(Color.green);
		colors.add(Color.magenta);

		colorProfiles.put("default", colors);
		
		colors = new ArrayList<Color>();
		
		colors.add(Color.black);
		colors.add(Color.darkGray);
		colors.add(Color.gray);
		colors.add(Color.lightGray);
		colors.add(Color.white);
		
		colorProfiles.put("bw", colors);
		
		colors = new ArrayList<Color>();
		colors.add(new Color(135,206,250));
		colors.add(new Color(155,205,50));
		colors.add(new Color(238,221,130));
		colors.add(new Color(244,164,96));
		colors.add(new Color(255,99,71));
		colors.add(new Color(160,82,45));
		
		colorProfiles.put("pastel", colors);
		
	}
	
	public static String getWinMessage(){
		return "Gewonnen";
	}
	
	
	public static List<Color> getColors() {
		return colorProfiles.get(colorProfile );
	}
	
	public static int getWidth() {
		return width;
	}


	public static int getHeight() {
		return height;
	}


	public static Shapes getShape(){
		return shape;
	}

}
