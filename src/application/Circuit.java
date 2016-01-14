package application;

import java.util.ArrayList;

public class Circuit{
	
	
	private double totalVolts = 0;
	private double totalResistance = 0;
	private double current = 0;
	private ArrayList<Component> list = new ArrayList<Component>();
	public Circuit(){
		
	}
	
	public void run(Component firstNode){
		computeGlobals(firstNode);
		computeSpecifics(firstNode);
		//printSpecificComponentData(firstNode);
		printList();
	}
	
	
	public void computeGlobals(Component firstNode){
		
		System.out.println("Currently at..."+firstNode);
		Component traverse = firstNode;
		list.add(firstNode);
		traverse = firstNode.getOutput();
		while(traverse != firstNode){
			System.out.println("Moved to "+traverse+ "of type "+traverse.getClass().getCanonicalName());
			if(hasBranch(traverse))
				handleBranch(traverse);
			
			
			traverse = traverse.getOutput();
			
			collateGlobalData(traverse);
			//traverse = traverse.getOutput();
		}
		System.out.println("Back at... "+traverse);
	}
	
	public boolean hasBranch(Component node){
		list.add(node);
		if(node.getClass().getCanonicalName().equals(new Wire().getClass().getCanonicalName())){
			System.out.println("Handling wire with "+node);
			if(((Wire)node).hasSecondOutput())
				return true;
		}
		return false;
	}
	
	public void handleBranch(Component node){
			System.out.println("Branching required because of "+node);
			generateBranch(((Wire)node).getOutput());
			generateBranch(((Wire)node).getSecondOutput());	
	}
	
	
	public boolean hasBranchEnded(Component node){
		if(node.getClass().getCanonicalName().equals(new Wire().getClass().getCanonicalName())){
			if(((Wire)node).hasSecondInput()){
				System.out.println("Branching has finished! @: "+node);
				return true;
			}
		}
		return false;
	}
	
	public Component generateBranch(Component startingNode){
		System.out.println("Starting branch at "+startingNode);
		double branch_voltage = totalVolts;
		double branch_resistance = 0;
		double branch_current = 0;
		int component_count = 0;
		while(!hasBranchEnded(startingNode)){
			if(hasBranch(startingNode))
				handleBranch(startingNode);
			
			System.out.println("	Added: "+startingNode);
			branch_resistance += (1/startingNode.getResistance());
			System.out.println("totalVolts = :"+totalVolts+"worked out I: "+ (branch_voltage / startingNode.getResistance()) +"and V: "+(branch_current * startingNode.getResistance()));
			startingNode.setCurrent(branch_voltage / startingNode.getResistance()); 
			startingNode.setVoltage(branch_voltage);
			startingNode = startingNode.getOutput();
			component_count++;
		}
		branch_resistance = (1/branch_resistance);
		totalResistance += branch_resistance;
		return startingNode; 	
	}
	
	public void computeSpecifics(Component firstNode){
		Component traverse = firstNode;
		traverse = traverse.getOutput();
		while(traverse != firstNode){
			updateComponetValues(traverse);
			traverse = traverse.getOutput();
		}
	}
	
	public void printSpecificComponentData(Component firstNode){
		printGlobals();
		Component traverse = firstNode;
		traverse = traverse.getOutput();
		while(traverse != firstNode){
			System.out.println("Data for "+traverse+" V: "+traverse.getVoltage()+" R: "+traverse.getResistance()+" I: "+traverse.getCurrent());
			traverse = traverse.getOutput();
		}
	}
	
	public void updateComponetValues(Component node){
		setNodeVoltage(node);
		setNodeCurrent(node);
	}
	
	public void setNodeVoltage(Component node){
		if(node.getResistance()==0){
			node.setVoltage(totalVolts);
		} else {
			node.setVoltage(node.getResistance()*current);
		}
	}
	
	public void setNodeCurrent(Component node){
		node.setCurrent(current);
	}
	
	
	public Component nextNode(Component node){
		System.out.println("Moving to "+node.getOutput());
		return node.getOutput();
	}
	
	
	
	public void collateGlobalData(Component node){	
		updateGlobalVoltage(node);
		updateGlobalCurrent(node);
		updateGlobalResistance(node);
	}
	
	//Update voltages 
	public void updateGlobalVoltage(Component node){
		Battery example = new Battery(0);
		if(node.getClass().equals(example.getClass())){
			System.out.println("Updating Voltage");
			totalVolts += node.getVoltage();
		}
	}
	
	//Update Current
	public void updateGlobalCurrent(Component node){
		current = totalVolts / totalResistance;
	}
	
	//Update Resistance
	public void updateGlobalResistance(Component node){
		totalResistance += node.getResistance();
	}
	
	public void printGlobals(){
		System.out.println("Voltage: " +totalVolts+" Resistance: "+totalResistance+" Current: "+current);;
	}
	
	public void printList(){
		for(Component item : list){
			System.out.println("Data for "+ item +" V: "+item.getVoltage()+" R: "+item.getResistance()+" I: "+item.getCurrent());
		}
	}
}
