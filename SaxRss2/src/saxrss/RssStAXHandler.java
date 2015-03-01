/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saxrss;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Less
 */
public class RssStAXHandler implements XMLStreamConstants{
	protected boolean inItem;
	protected ArrayList<RssRecord> items;
	protected RssRecord curItem;
	protected HashMap<String, String> data;
	protected HashMap<String, Boolean> inKeyStat;
	protected String[] itemKeys;
	protected String [] statKeys;
	protected ArrayList<String> statList;
	protected String currentElement;
	
	{
		inItem = false;
		items = new ArrayList<>();
		data = new HashMap<>();
		itemKeys = new String[] {"enclosure", "category", "description", "pubDate", "title"};
		
		statKeys = new String[] {"category", "title", "description", "pubDate" };
		statList = new ArrayList<String> (Arrays.asList(statKeys));

	}
	
	RssStAXHandler(){}
	
	private void initEmptyData(){
		String [] keys = {"image", "category", "description", "pubDate", "title"};
		for(String key: keys){
			data.put(key, "");
		}
	}
	
	public void process(InputStream stream){
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try{
			XMLStreamReader xmlReader = factory.createXMLStreamReader(stream);
			int curEvent;
			while(xmlReader.hasNext()){
				curEvent = xmlReader.next();
				switch(curEvent){
					case START_ELEMENT: {
						startElement(xmlReader);
						break;
					}
					case END_ELEMENT: {
						endElement(xmlReader.getLocalName());
						break;
					}
					case CHARACTERS: {
						doText(xmlReader.getText());
						break;
					}
				}
			}
			xmlReader.close();
		}catch(XMLStreamException ex){
		
		}
		
	}
	
	public void startElement(XMLStreamReader element){
		String elementName = element.getLocalName();
		if(! elementName.equals("item") && !inItem) return;
		switch(elementName){
			case "item": {
				initEmptyData();
				curItem = new RssRecord();
				inItem = true;
				break;
			}
			case "enclosure": {
				
				break;
			}
		}
		if(statList.contains(elementName)){
			currentElement = elementName;
		}
	}
	
	public void endElement(String elementName){
		switch(elementName){
			case "item":{
				curItem.setData(data);
				items.add(curItem);
				inItem = false;
				return;
			}
		}
		if(!inItem) return;
		if(statList.contains(elementName)){
			currentElement = null;
		}
	}
	
	public void doText(String text){
		if(!inItem || currentElement == null) return;
		
		String previosContent = data.get(currentElement);
		String content = previosContent + text;
		data.put(currentElement, content);
	}
	
	public ArrayList<RssRecord> getData(){
		return items;
	}
	
}
