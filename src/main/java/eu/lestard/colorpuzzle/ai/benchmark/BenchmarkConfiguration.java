package eu.lestard.colorpuzzle.ai.benchmark;

import java.util.ArrayList;
import java.util.List;

import eu.lestard.colorpuzzle.ai.ArtificialIntelligence;

public class BenchmarkConfiguration {
	private int colorCount;
	private int dimension;
	private int iterations;
	private List<Integer> samples = new ArrayList<Integer>();
	private ArtificialIntelligence ai;
	
	
	public ArtificialIntelligence getAi() {
		return ai;
	}

	public void setAi(ArtificialIntelligence ai) {
		this.ai = ai;
	}

	public BenchmarkConfiguration(int colorCount, int dimension, int iterations, ArtificialIntelligence ai){
		setColorCount(colorCount);
		setDimension(dimension);
		setIterations(iterations);
		setAi(ai);
	}
	
	public double getAverage(){
		
		int sum = 0;
		
		for(int i : samples){
			sum += i;
		}
		
		return sum/samples.size();
	}
	
	public void addSample(int sample){
		samples.add(sample);
	}
	
	public int getColorCount() {
		return colorCount;
	}
	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	public List<Integer> getSamples() {
		return samples;
	}
	
}
