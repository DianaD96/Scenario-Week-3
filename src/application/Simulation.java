package application;

import java.util.ArrayList;

public class Simulation {
	
	
	public Simulation(){
		
	}
	
	public void run(){
		Circuit series = simpleSeriesCircuit();
		//series.getCircuitList();
		
		 xmlWrite newWriter = new xmlWrite(series.getCircuitList());		
		 xmlRead newReader = new xmlRead();
		 ArrayList<Component> testList = new ArrayList<Component>();
		 testList = newReader.readXML();
		 connectCircuit(testList);
		 System.out.println("Going to run recreated list!!");
		 series.run(testList.get(0));
	}
	
	public void connectCircuit(ArrayList<Component> raw){
		for(Component x: raw){
			x.setInput(getComponent(raw,x.getInputId()));
			x.setOutput(getComponent(raw,x.getOutputId()));
		}
	}
	
	public Component getComponent(ArrayList<Component> list,String id){
		for(Component x: list){
			if(x.getId().equals(id))
				return x;
		}
		return null;
	}
	
	public Circuit simpleSeriesCircuit(){
		// Define components 
		Battery cell = new Battery(10);
		cell.setPositionX(10); cell.setPositionY(10); cell.setId("1");
		Wire alpha = new Wire();
		alpha.setPositionX(10); alpha.setPositionY(10); alpha.setId("2");
		Wire beta = new Wire();
		beta.setPositionX(10); beta.setPositionY(10); beta.setId("3");
		Bulb light = new Bulb();
		light.setPositionX(10); light.setPositionY(10); light.setId("4");		
		//Define relations (I/O)
		cell.setInput(beta);
		cell.setOutput(alpha);
		alpha.setInput(cell);
		alpha.setOutput(light);
		light.setInput(alpha);
		light.setOutput(beta);
		beta.setInput(light);
		beta.setOutput(cell);
		
		// Trigger Circuit
		
		Circuit series = new Circuit();
		series.run(cell);
		
		return series;
		
	}
	
	public Circuit parallelCircuit(){
		
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
		
		return parellel; 
		
	}
}
