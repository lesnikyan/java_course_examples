/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rsshttpclient;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Less
 */
public class RssLoader {
	
	private String uri;
	private String text;
	private String html;
	private String xsl;
	private boolean loaded;
	private final JEditorPane viewer;
	private final TreeMap<String, String> errors;
	private final String errorTpl;
	String charset = "UTF-8";
	String lastError = null;
	
	{
		errorTpl = "<span style=\"color: #aa0000; font-weight: bold;\">%s</span>";
		errors = new  TreeMap<String, String>();
		errors.put("no-xml-loaded", String.format(errorTpl, "Xml content was not load."));
		errors.put("load-problem", String.format(errorTpl, "Some load problem."));
		errors.put("xlst-error", String.format(errorTpl, "Xsl transformation problem."));
	}
	
	RssLoader(JEditorPane viewer, String xsl){
		this.viewer = viewer;
		this.viewer.setContentType("text/html");
		this.xsl = xsl;
	}
	
	
	RssLoader(JEditorPane viewer){
		this.viewer = viewer;
		this.viewer.setContentType("html");
	}
	
	public void setXslSource(String xsl){
		this.xsl = xsl;
	}
	
	public void setCharset(String charsetName){
		this.charset = charsetName;
	}
	
	public void start(String uri){
		p("loader.start: " + uri);
		this.uri = uri;
		loaded = false;
		try {
			load();
		} catch (IOException ex) {
			lastError = "load-problem";
			loaded = false;
		}
		try {
			toHTML();
		} catch (EmptyContentException ex) {
			lastError = "no-xml-loaded";
			loaded = false;
		} catch (TransformerException ex) {
			lastError = "xlst-error";
			loaded = false;
		}
		String content = html;
		if(! loaded){
			content = errors.get(lastError);
		}
		view( content );
	}
	
	private void load() throws UnsupportedEncodingException, IOException{
	//	для запросов с get-параметрами
    //    String name = URLEncoder.encode("Вася", charset);
    //    String getParams = String.format("?name=%s", name);
        
        URL url = new URL(uri);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();
        StringBuilder newText = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
            for (String line; (line = reader.readLine()) != null;) {
                newText.append(line).append("\n");
            }
           // System.out.println(newText);
        }
		text = newText.toString();
		loaded = true;
	}
	
	private void toHTML() throws EmptyContentException, TransformerConfigurationException, TransformerException{
		if(!loaded)
			throw new EmptyContentException();
		Source xmlSrc = new StreamSource(new StringReader(text));
		Source xslSrc = new StreamSource(new StringReader(xsl));
		Transformer trans = TransformerFactory.newInstance().newTransformer(xslSrc);
		trans.setOutputProperty(OutputKeys.METHOD, "xml");
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StringWriter resultTo = new StringWriter();
		Result result = new StreamResult(resultTo);
		trans.transform(xmlSrc, result);
		html = resultTo.toString(); 
		p(html);
	}
	
	private void view(String htmlText){
		viewer.setText(htmlText); //
		viewer.setCaretPosition(0);
	}
	
	private String text1(){
		return "<html>\n" +
"<head>\n" +
//"<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
"<title>news</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1>test</h1>\n" +
"<div>\n" +
"<div class=\"item-title\">Неинформированные массы мешают меньшинству переубедить большинство</div>\n" +
"<div class=\"item-categ\">Биология</div>\n" +
"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Поверхности нормальных и раковых клеток &mdash; фракталы разной размерности</div>\n" +
//"<div class=\"item-categ\">Физика</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Отбор на уровне видов не позволяет растениям утратить самонесовместимость</div>\n" +
//"<div class=\"item-categ\">Эволюция</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Размер геномов в ходе эволюции жизни на Земле увеличивался ускоренными темпами</div>\n" +
//"<div class=\"item-categ\">Генетика</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Бактериальная инфекция приводит к необратимой утрате полового размножения</div>\n" +
//"<div class=\"item-categ\">Эволюция</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Формальные статистические тесты подтверждают происхождение всех живых организмов от единого предка</div>\n" +
//"<div class=\"item-categ\">Эволюция</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Олимпиада по лингвистике пройдет в Москве &lt;nobr&gt;в 40-й раз&lt;/nobr&gt;</div>\n" +
//"<div class=\"item-categ\">Лингвистика</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Правильно выбирая друзей, можно спастись от конкурентов</div>\n" +
//"<div class=\"item-categ\">Экология</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Честные дрожжи и дрожжи-обманщики могут жить дружно</div>\n" +
//"<div class=\"item-categ\">Биохимия</div>\n" +
//"</div>\n" +
//"<div>\n" +
//"<div class=\"item-title\">Предложена теория, объясняющая, чем определяется размер гигантских дюн в пустыне</div>\n" +
//"<div class=\"item-categ\">Физика</div>\n" +
//"</div>\n" +
"</body>\n" +
"</html>";
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}


class EmptyContentException extends Exception {
	
}