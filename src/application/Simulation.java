package application;

public class Simulation {
	
	
	public Simulation(){
		
	}
	
	public void run(){
		simpleSeriesCircuit();
		parallelCircuit();
		
	}

	public static void simpleSeriesCircuit(){
		// Define components 
		Battery cell = new Battery(10);
		Wire alpha = new Wire();
		Wire beta = new Wire();
		Bulb light = new Bulb();
		Resistor resist = new Resistor(10);
		
		//Define relations (I/O)
		cell.setInput(beta);
		cell.setOutput(alpha);
		alpha.setInput(cell);
		alpha.setOutput(resist);
		resist.setInput(alpha);
		resist.setOutput(light);
		light.setInput(resist);
		light.setOutput(beta);
		beta.setInput(light);
		beta.setOutput(cell);
		
		// Trigger Circuit
		
		Circuit series = new Circuit();
		series.run(cell);
		
	}
	
	public static void parallelCircuit(){
		
		// Initialise all components 
		Battery cell = new Battery(10);
		Bulb light = new Bulb();
		Bulb light2 = new Bulb();
		Wire a = new Wire();
		Wire b = new Wire();
		Wire c = new Wire();
		Wire d = new Wire();
		
		//Define relations
		cell.setOutput(a);
		a.setInput(cell);
		a.setOutput(light); 
		a.setSecondOutput(light2); // Branch is made
		
		light.setInput(a); 
		light2.setInput(a);
		
		light.setOutput(b);
		light2.setOutput(b);

		b.setInput(light);
		b.setSecondInput(light2);
		
		b.setOutput(cell);
		cell.setInput(b);
		
		Circuit parellel = new Circuit();
		parellel.run(cell);
		
	}
}
