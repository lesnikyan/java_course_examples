/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saxrss;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 *
 * @author Less
 */
public class RssSAXParser {
	
	ArrayList<RssRecord> data;
	SAXParser saxparser;
	RssSAXHandler handler;
	
	RssSAXParser() throws ParserConfigurationException, SAXException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		saxparser = factory.newSAXParser();
		handler = new RssSAXHandler();
	}
	
	public void parse(Path path) throws IOException, SAXException{
		
		
		InputStream instr = Files.newInputStream(path);
		saxparser.parse(instr, handler);
		data = handler.getData();
	}
	
	public ArrayList<RssRecord> getList(){
		return data;
	}
	
}
