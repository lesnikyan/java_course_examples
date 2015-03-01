
package saxrss;

import java.util.HashMap;
import java.util.Set;


public class RssRecord {
	private String title, description, category, image, time;
	
	public RssRecord(){}
	
	public RssRecord(String title, String description){
		this.title = title;
		this.description = description;
	}
	
	public RssRecord(String title, String description, String image){
		this(title, description);
		this.image = image;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getImage(){
		return image;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getTime(){
		return time;
	}
	
	private void testData(HashMap<String, String> data){
		Set<String> keys = data.keySet();
		StringBuffer sout = new StringBuffer();
		for(String key: keys){
			String val = data.get(key);
			val = val.replaceAll("<[^>]+>", "");
			val = val.substring(0, (Math.min(20, val.length())));
			sout.append(key + ":" + val + "; ");
		}
		System.out.println(sout.toString());
	}
	
	public void setData(HashMap<String, String> data){
		// test
		//testData(data);
		
		if(data.containsKey("title")){
			title = data.get("title");
		}
		if(data.containsKey("description")){
			description = data.get("description").replaceAll("<[^>]+>", "");
			
		}
		if(data.containsKey("image")){
			image = data.get("image");
		}
		if(data.containsKey("category")){
			category = data.get("category");
		}
		if(data.containsKey("pubDate")){
			time = data.get("pubDate");
		}
	}
}

