
package saxrss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class RssSAXHandler extends DefaultHandler  {
	
	private boolean inItem, inEnclosure, inCategory, inTitle, inDescription, inPubdate;
	ArrayList<RssRecord> items;
	RssRecord curItem;
	HashMap<String, String> data;
	HashMap<String, Boolean> inKeyStat;
	String[] itemKeys;
	String [] statKeys;
	ArrayList<String> statList;
	String currentElement;
	
	{
		inItem = inCategory = inTitle = inDescription = inPubdate = false;
		items = new ArrayList<>();
		data = new HashMap<>();
		itemKeys = new String[] {"enclosure", "category", "description", "pubDate", "title"};
		
		inKeyStat = new HashMap<>();
		statKeys = new String[] {"category", "title", "description", "pubDate" };
		statList = new ArrayList<String> (Arrays.asList(statKeys));
		for(String skey: statList){
			inKeyStat.put(skey, false);
		}
	}
	
	private void initEmptyData(){
		String [] keys = {"image", "category", "description", "pubDate", "title"};
		inCategory = inTitle = inDescription = inPubdate = false;
		for(String key: keys){
			data.put(key, "");
		}
	}
	
	@Override
	public void startDocument ()
        throws SAXException
    {
        // no op
    }
	
	@Override
	public void endDocument ()
        throws SAXException
    {
        // no op
    }
		
	@Override
	public void startElement (String uri, String localName,
        String qName, Attributes attributes)
    throws SAXException{
		// "enclosure", "category", "description", "pubDate", "title"
		// inItem, inEnclosure, inCategory, inTitle, inDescription, inPubdate
		
		// check is element item or child of item
		if(!qName.equals("item") && !inItem) return;
		
		// do smth with element or attributes
		switch(qName){
			case "item":{
				initEmptyData();
				curItem = new RssRecord();
				inItem = true;
				break;
			}
			case "enclosure":{
				data.put("image", attributes.getValue("url"));
				break;
			}
		}
		if(statList.contains(qName)){
			inKeyStat.put(qName, true);
			currentElement = qName;
		}
	}
	
	@Override
	public void endElement (String uri, String localName, String qName)
        throws SAXException
    {
        switch(qName){
			case "item":{
				curItem.setData(data);
				items.add(curItem);
				inItem = false;
				return;
			}
		}
		if(!inItem) return;
		if(statList.contains(qName)){
			inKeyStat.put(qName, false);
			currentElement = null;
		}
    }
	
	@Override
	public void characters (char ch[], int start, int length)
        throws SAXException
    {
		if(!inItem || currentElement == null) return;
		
		// do smth with content of element
		String previosContent = data.get(currentElement);
		String content = previosContent + new String(ch, start, length);
		data.put(currentElement, content);
		
//		System.out.println("(" + currentElement + ")[" + start + ": " + length + "] " + content);
    }
	 
	@Override
	public void ignorableWhitespace (char ch[], int start, int length)
        throws SAXException
    {
        // no op
    }
		 
	@Override
	public void processingInstruction (String target, String data)
        throws SAXException
    {
        // no op
    }
	
	public ArrayList<RssRecord> getData(){
		return items;
	}
	
}
