/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



/**
 * 
 * Detailed info: 
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 *
 * @author less
 */
public class JavaUrl {
    public static void demo() throws UnsupportedEncodingException, MalformedURLException, IOException{
    
        String uri = "http://elementy.ru/rss/news/mathematics";
       // String uri = "http://archives.maillist.ru/64337/rss.xml";
        // http://rssnews.org.ua/science
        
        String charset = "UTF-8";
        
        String name = URLEncoder.encode("Вася", "UTF-8");
        String getParams = String.format("?name=%s", name);
        
        URL url = new URL(uri);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
            StringBuilder text = new StringBuilder();
            for (String line; (line = reader.readLine()) != null;) {
                text.append(line).append("\n");
            }
            System.out.println(text);
        }
        
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        System.out.println("\n******************\n");
        System.out.println("Content-Type: " + httpConnection.getContentType());
        System.out.println("Response code: " + httpConnection.getResponseCode());
    }
}
