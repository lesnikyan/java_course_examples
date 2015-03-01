/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domparser;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
/**
 *
 * @author Less
 */
public class DomParser {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		parseXml();
	}
	
	public static void parseXml(){
		String fileName = "data-document.xml";
		try {
			// create and load document
			DocumentBuilderFactory docfact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docfact.newDocumentBuilder();
			Document doc = docbuilder.parse(fileName);
			
			XPath xpath = XPathFactory.newInstance().newXPath();
		
			// add element with text
			String itemsPath = "/root/items";
			Node node = (Node) xpath.evaluate(itemsPath, doc.getDocumentElement(), XPathConstants.NODE);
			Element newItem = doc.createElement("item");
			newItem.setAttribute("id", "a");
			newItem.setTextContent("I'll be back, Sarah...");
			node.appendChild(newItem);
			
			// get node list
			String itemPath = "/root/items/item";
			NodeList nodes = (NodeList) xpath.evaluate(itemPath, doc.getDocumentElement(), XPathConstants.NODESET);
			
			// use found nodes
			for(int i=0; i< nodes.getLength(); ++i){
				Element elem = (Element) nodes.item(i);
				p(
					elem.getAttribute("id") + 
					": " + 
					elem.getTextContent()
				);
			}
			
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SAXException ex) {
			Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
		} catch (XPathExpressionException ex) {
			Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}
