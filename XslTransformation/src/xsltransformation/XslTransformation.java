/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xsltransformation;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Less
 */
public class XslTransformation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, 
            TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory docfact = DocumentBuilderFactory.newInstance();
        docfact.setNamespaceAware(true);
        DocumentBuilder docbuilder = docfact.newDocumentBuilder();
        Document dataDoc =  docbuilder.parse("./xml/data.xml");
        Document xslDoc = docbuilder.parse("./xml/transform.xsl");
        
        Source dataSource = new DOMSource(dataDoc);
        Source xslSource = new DOMSource(xslDoc);
        
        DOMResult domResult = new DOMResult();
        StreamResult strResult = new StreamResult(System.out);
        StreamResult fileResult = new StreamResult(new FileWriter("./xml/result.html"));
        
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer(xslSource);
        
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        
        transformer.transform(dataSource, strResult);
        transformer.transform(dataSource, fileResult);
        transformer.transform(dataSource, domResult);
        Document resDoc = (Document) domResult.getNode();
        
    }
    
}
