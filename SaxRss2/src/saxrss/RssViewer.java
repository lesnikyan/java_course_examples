/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saxrss;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.*;

/**
 *
 * @author Less
 */
public class RssViewer {
	
	JTextPane textPane;
	StyledDocument doc;
	StyleContext context;
	
	RssViewer(JTextPane pane){
		textPane = pane;
		textPane.setEditable(false);
		context = new StyleContext();
		doc = new DefaultStyledDocument(context);
		textPane.setDocument(doc);
	}
	
	public void view(ArrayList<RssRecord> items){
		
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setSpaceAbove(style, 3);
		StyleConstants.setSpaceBelow(style, 3);
		
		SimpleAttributeSet titleAttr = new SimpleAttributeSet(style);
		StyleConstants.setBold(titleAttr, true);
		StyleConstants.setAlignment(titleAttr, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(titleAttr, new Color(0xcc8844));
		
		SimpleAttributeSet dateAttr = new SimpleAttributeSet(style);
		StyleConstants.setFontSize(dateAttr, 10);
		StyleConstants.setBold(dateAttr, true);
		StyleConstants.setAlignment(dateAttr, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(dateAttr, new Color(0xafa0a0));
		
		SimpleAttributeSet catAttr = new SimpleAttributeSet(style);
		StyleConstants.setFontSize(catAttr, 12);
		StyleConstants.setBold(catAttr, true);
		StyleConstants.setAlignment(catAttr, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(catAttr, new Color(0xeeaa80));
		
		// 04 Jul 2014 04:02:00 +0400 "dd MMM yyyy HH:mm:ss Z"
		// short manual about Date, etc. http://www.tutorialspoint.com/java/java_date_time.htm
		SimpleDateFormat dform = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z",  Locale.ENGLISH); 
		SimpleDateFormat viewDform = new SimpleDateFormat("yyyy-MM-dd HH:MM");
		try{
			for(RssRecord item: items){
				String dtime = item.getTime();
				Date date = dform.parse(dtime.substring(0, dtime.length()));
				
				doc.insertString(doc.getLength(), viewDform.format(date) + " ", dateAttr);
				doc.insertString(doc.getLength(), "[" +item.getCategory() + "] ", catAttr);
				doc.insertString(doc.getLength(), item.getTitle() + "\n\n", titleAttr);
				doc.insertString(doc.getLength(), item.getDescription()+ "\n\n", style);
			}
			
			
		} catch (BadLocationException | ParseException ex){
			Logger.getLogger(RssViewer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
}
