package eu.lestard.colorpuzzle.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorProfileList {
	
	private Map<ColorProfile, List<Color>> colorProfiles;
	
	public List<Color> getColorListByProfile(ColorProfile profile){
		return colorProfiles.get(profile);
	}
	
	public ColorProfileList(){
		colorProfiles = new HashMap<ColorProfile,List<Color>>();
		List<Color> colors;
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

}
