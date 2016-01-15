package application;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class xmlWrite {

	public xmlWrite(ArrayList<Component> components) {
		writeXML(components);
	}
	
	private void writeXML(ArrayList<Component> components) {//, String storageLocation) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElementNS(null, "Components");
			doc.appendChild(mainRootElement);
			
			//append child elements to root element
			for (Component x : components)
				mainRootElement.appendChild(getComponent(doc, x));			
			
			//output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance().newTransformer();;
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
			
			JFileChooser f = new JFileChooser();
	        //f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        f.showSaveDialog(null);
	        
			StreamResult newFile = new StreamResult(f.getSelectedFile());
			transformer.transform(source, newFile);
			
			System.out.println("\nXML DOM Created Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Only edit this
	private Node getComponent(Document doc, Component newComponent) {
		Element component = doc.createElement("Component");
		//component.setAttribute("id", id);
		
		component.appendChild(getComponentElements(doc, component, "Name", newComponent.getClass().getSimpleName()));
		component.appendChild(getComponentElements(doc, component, "Current", Double.toString(newComponent.getCurrent())));
		component.appendChild(getComponentElements(doc, component, "Resistance", Double.toString(newComponent.getResistance())));
		component.appendChild(getComponentElements(doc, component, "Voltage", Double.toString(newComponent.getVoltage())));
		component.appendChild(getComponentElements(doc, component, "positionX", Double.toString(newComponent.getPositionX())));
		component.appendChild(getComponentElements(doc, component, "positionY", Double.toString(newComponent.getPositionY())));
		component.appendChild(getComponentElements(doc, component, "ID", (newComponent.getId())));
		component.appendChild(getComponentElements(doc, component, "inputID", (newComponent.getInput().getId())));
		component.appendChild(getComponentElements(doc, component, "outputID", (newComponent.getOutput().getId())));
		
		return component;
	}
	
	private Node getComponentElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
}
