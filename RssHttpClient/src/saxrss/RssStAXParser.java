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
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Less
 */
public class RssStAXParser {
	ArrayList<RssRecord> data;
	RssStAXHandler handler;
	
	RssStAXParser(){
		handler = new RssStAXHandler();
	}
	
	public void parse(Path path) throws IOException, XMLStreamException{
		InputStream inputStream = Files.newInputStream(path);
		handler.process(inputStream);
		data = handler.getData();
	}
	
	public ArrayList<RssRecord> getList(){
		return data;
	}
}
