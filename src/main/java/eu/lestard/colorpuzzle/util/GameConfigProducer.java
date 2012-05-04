package eu.lestard.colorpuzzle.util;

import java.awt.Color;
import java.util.List;

import javax.enterprise.inject.Produces;

import eu.lestard.colorpuzzle.util.qualifier.FieldCountX;
import eu.lestard.colorpuzzle.util.qualifier.FieldCountY;
import eu.lestard.colorpuzzle.util.qualifier.Height;
import eu.lestard.colorpuzzle.util.qualifier.Width;

public class GameConfigProducer {
	
	
	private ColorProfile choosenProfile = ColorProfile.pastel;
	
	@Produces
	@FieldCountX
	public int getFieldCountX(){
		return 11;
	}
	
	@Produces
	@FieldCountY
	public int getFieldCountY(){
		return 11;
	}
	

	@Produces
	@Height
	public int getHeight (){
		return 700;
	}
	
	@Produces
	@Width
	public int getWidth(){
		return 700;
	}
	
	@Produces
	public List<Color> getColors(){
		return new ColorProfileList().getColorListByProfile(choosenProfile);
	}
	
}
