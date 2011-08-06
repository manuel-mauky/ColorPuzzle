package eu.lestard.colorpuzzle.ai.benchmark;

import java.util.ArrayList;
import java.util.List;

import eu.lestard.colorpuzzle.ai.ArtificialIntelligence;
import eu.lestard.colorpuzzle.ai.BogoSolver;
import eu.lestard.colorpuzzle.ai.BruteForceSolver;
import eu.lestard.colorpuzzle.core.GameLogic;
import eu.lestard.colorpuzzle.core.Grid;
import eu.lestard.colorpuzzle.util.ColorChooser;
import eu.lestard.colorpuzzle.util.Configurator;
import eu.lestard.colorpuzzle.view.swing.SwingGUI;

public class Benchmark {
	
	
	private SwingGUI gui;
	
	private List<BenchmarkConfiguration> configurations;
	
	public Benchmark(){
		configurations = new ArrayList<BenchmarkConfiguration>();
		
//		configurations.add(new BenchmarkConfiguration(5, 10, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 12, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 14, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 16, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 18, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 20, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 22, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 24, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 26, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 28, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 30, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 32, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 34, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 36, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 38, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 40, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 42, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 44, 10, new BogoSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 46, 10, new BogoSolver()));
//		
		
		configurations.add(new BenchmarkConfiguration(7, 10, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 12, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 14, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 16, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 18, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 20, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 22, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 24, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 26, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 28, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 30, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 32, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 34, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 36, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 38, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 40, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 42, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 44, 10, new BogoSolver()));
		configurations.add(new BenchmarkConfiguration(7, 46, 10, new BogoSolver()));
//		
//		
//		configurations.add(new BenchmarkConfiguration(5, 10, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 12, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 14, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 16, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 18, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 20, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 22, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 24, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 26, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 28, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 30, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 32, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 34, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 36, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 38, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 40, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 42, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 44, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(5, 46, 10, new BruteForceSolver()));
		
		
//		configurations.add(new BenchmarkConfiguration(7, 10, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 12, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 14, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 16, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 18, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 20, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 22, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 24, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 26, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 28, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 30, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 32, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 34, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 36, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 38, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 40, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 42, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 44, 10, new BruteForceSolver()));
//		configurations.add(new BenchmarkConfiguration(7, 46, 10, new BruteForceSolver()));
	}
	
	private void runBenchmarks(){
		for(BenchmarkConfiguration b : configurations){
			
			
			Configurator.setColorsCount(b.getColorCount());
			Configurator.setHeight(b.getDimension());
			Configurator.setWidth(b.getDimension());
			Configurator.setAI(b.getAi());
			
			
			Configurator.setAiDelay(0);
			
			for(int x=0 ; x<b.getIterations() ; x++){
				System.out.print(".");
				Grid grid = new Grid(Configurator.getHeight(),Configurator.getWidth(),new ColorChooser(Configurator.getColors()));
				
				GameLogic logic = new GameLogic(grid);
				
//				logic.addFinishListener(new FinishListener(){
//					
//					@Override
//					public void update(boolean finished) {
//						gui.closeWindow();
//					}
//					
//				});
//				
//				
//				gui = new SwingGUI(logic);
//				
//				gui.createGui();
				
				ArtificialIntelligence ai = Configurator.getAI();
				ai.solve(logic);
				
				
				b.addSample(logic.getCounter());
				
			}
		}
	}
	
	public static void main(String...args){
		new Benchmark().go();
	}
	
	
	private void go(){
		runBenchmarks();
		printResult();
	}
	
	
	private void printResult(){
		for(BenchmarkConfiguration b : configurations){
			
			System.out.println();
			System.out.println("Solver: " + b.getAi().getClass().getSimpleName());
			System.out.println("color: " + b.getColorCount());
			System.out.println("dimension: " + b.getDimension());
			System.out.println("iterations: " + b.getIterations());
			System.out.print("values: ");
			
			for(int i : b.getSamples()){
				System.out.print(i + " ");
			}
			System.out.println();
			
			System.out.println("average: " + b.getAverage());
			
			
			
			
		}
	}
	
}
