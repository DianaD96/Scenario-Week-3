package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * xmlWrite newWriter = new xmlWrite(componentList);		
 * xmlRead newReader = new xmlRead();
 * ArrayList<Component> testList = new ArrayList<Component>();
 * testList = newReader.readXML();
 * 
 */

public class xmlRead {

	public xmlRead() {		
		
	}
	
	private ArrayList<Component> connections = new ArrayList<Component>();
	
	public ArrayList<Component> readXML() {
		JFileChooser f = new JFileChooser();
        //f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        f.showOpenDialog(null);

//        System.out.println(f.getCurrentDirectory());
//        System.out.println(f.getSelectedFile());
		
		File xmlFile = f.getSelectedFile();
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        ArrayList<Component> compList = new ArrayList<Component>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Component");
            
            //now XML is loaded as Document in memory, lets convert it to Object List            
            for (int i = 0; i < nodeList.getLength(); i++) {
                compList.add(getComponent(nodeList.item(i)));
            }
                        
            for (int i = 0; i < compList.size(); i++) {
            	System.out.println(compList.get(i).getClass().getCanonicalName());
            }
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        return compList;
	}
	
	
	private static Component getComponent(Node node) {
        Component comp = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            if (getTagValue("Name", element).equals("Battery")) {
            	comp = new Battery(Double.parseDouble(getTagValue("Voltage", element)));
            } else if (getTagValue("Name", element).equals("Bulb")) {
            	comp = new Bulb();
            } else if (getTagValue("Name", element).equals("Motor")) {
            	comp = new Motor(Double.parseDouble(getTagValue("Resistance", element)));
            } else if (getTagValue("Name", element).equals("Resistor")) {
            	comp = new Resistor(Double.parseDouble(getTagValue("Resistance", element)));
            } else if (getTagValue("Name", element).equals("Wire")) {
            	comp = new Wire();
            } else {
            	comp = new Component();
            }
          
            comp.setCurrent(Double.parseDouble(getTagValue("Current", element)));
            comp.setResistance(Double.parseDouble(getTagValue("Resistance", element)));
            comp.setVoltage(Double.parseDouble(getTagValue("Voltage", element)));
            comp.setPositionX(Double.parseDouble(getTagValue("positionX", element)));
            comp.setPositionY(Double.parseDouble(getTagValue("positionY", element)));
            comp.setInputId(getTagValue("inputID",element));
            comp.setOutputId(getTagValue("outputID",element));
            comp.setId(getTagValue("ID",element));
        }
        
        return comp;
	}
	

 
	// Ignore
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
