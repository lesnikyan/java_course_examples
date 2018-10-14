/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Less
 */
public class RegExp {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		RegExp app = new RegExp();
		
		app.parseHtml();
		
		app.checkWords();
		
		app.splitTest();
		
		app.replaceText();
	}
	
	public void parseHtml(){
		try {
			String fileName = getClass().getResource("/res/content.html").getFile().substring(1);
			//p(fileName);
			String html = new String(Files.readAllBytes(Paths.get(fileName)));
//			System.out.println(html);
			// Get all content from page
			String titleRegex = "<h1\\s* class=\\\"page-title\\\">(.+)<\\/h1>"; // "(<h1>)"; // 
			
//			String menuRegex = "<div class=\"menu\">\\s+(?:<a\\s+href=\"([^\"]+)\">([^<]+)</a>\\s+)+\\s+<div>";
			String menuRegex = "<a\\s+href=\"([^\"]+)\">([^<]+)</a>";
//			String menuRegex = "<a"; // \\s*[^<>]*>([^<>]+)<\\/a>
			
			String contentRegex = "<div class=\"part\" id=\"(part-\\d+)\">\\s+"
				+ "<div class=\"part-title\">([^<]+)</div>\\s+"
				+ "<div class=\"part-content\">([^<]+)</div>\\s+"
				+ "</div>";
			
			// get title
			Pattern titlePattern = Pattern.compile(titleRegex, Pattern.CASE_INSENSITIVE|Pattern.MULTILINE|Pattern.DOTALL);
			Matcher titleMatcher = titlePattern.matcher(html); // "<h1 class=\"page-title\">Page title of Lorem text.</h1>"
			//p(titleMatcher.find());
			//p("title group count = " + titleMatcher.groupCount());
			if(titleMatcher.find()){
				String title = titleMatcher.group(1);
				pf("Title: %s \n\n", title);
			}
			
			// get menu, using matcher
			Pattern menuPattern = Pattern.compile(menuRegex, Pattern.CASE_INSENSITIVE|Pattern.MULTILINE|Pattern.DOTALL);
			Matcher menuMatcher = menuPattern.matcher(html );
			p("menu:");
			while(menuMatcher.find()){
				int menuGroupCount = menuMatcher.groupCount();
//				pf("menu count = %d \n", menuGroupCount);
				String link = menuMatcher.group(1);
				String text = menuMatcher.group(2);
				pf("page: [%s] | text: '%s'\n", link, text);
			}
			p("");
			
			// get content parts, using MatchResult
			Pattern contentPattern = Pattern.compile(contentRegex, Pattern.CASE_INSENSITIVE|Pattern.DOTALL|Pattern.MULTILINE);
			Matcher contentMatcher = contentPattern.matcher(html);
			List<MatchResult> matchResList = new ArrayList<>();
			while(contentMatcher.find()){
				matchResList.add(contentMatcher.toMatchResult());
			}
			p("parsed content:");
			for(MatchResult mResult : matchResList){
				
				int contGrCount = mResult.groupCount();
				pf("content count = %d \n", contGrCount);
//				p(mResult.group(0));
				pf("[id: %s] \n", mResult.group(1));
				pf("= %s =\n", mResult.group(2));
				p(mResult.group(3));
			}
			
		} catch (IOException ex) {
			Logger.getLogger(RegExp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void checkWords(){
		String[] logins = {"1st_user", "vasya-pupkin", "semen_petrovich", "_usr125", "Cat5", "iAm", "Lena_Perova", "Lyolik17"};
		String[] emails = {"qqwerty", "_iamrobot@mymail.com", "vasya@mail.net", "123@pochta.ec", "pahom-rybak@mail-22.fisher.com",
			"somebody@qqq.www.eee.asdfgh.zzz.it", "@abc.com", "user@luser", "chunga-changa-17@morskoy_babay.net"};
		String[] phones = {"1-095-333-22-45", "123456789", "10-20-30-40-50-60-70-80-90", "25-17-48", "tel-100001", "12-45-pupsik"};
		
		String emailExpr = "^[a-z][a-z0-9\\-\\_\\.]+[\\w]@([\\w\\-]+\\.){1,2}[a-z]{2,10}$";
		String loginExpr = "^[a-zA-Z][a-zA-Z0-9\\_]{3,}$";
		String phoneExpr = "^[1-9][0-9\\-]{9,13}[0-9]$";
		
		// Simple String check
		
		for(String login : logins){
			boolean res = login.matches(loginExpr);
			pf("Login : '%s' is %s\n", login, (res ? "VALID" : "NOT"));
		}
		p("\n");
		
		// Stream filter by regexp Pattern
		p("Valid mails:");
		Pattern emailPattern = Pattern.compile(emailExpr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		List<String> validEmails = Arrays.asList(emails).stream().filter(emailPattern.asPredicate()).collect(Collectors.toList());
		validEmails.forEach(email -> pf("Email : '%s'\n", email));
		p("\n");
		
		// Matcher.matches() - matches full string
		Pattern ph = Pattern.compile(phoneExpr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		for(String phone : phones){
			Matcher phoneMatcher = ph.matcher(phone);
			boolean res = phoneMatcher.matches();
			pf("Phone : '%s' is %s\n", phone, (res ? "VALID" : "NOT"));
		}
		p("\n");
		
		
		// Matcher.lookingAt() - matches part in string
		String[] names = {"Semen Johnson", "21-st Street", "Amadei Mozart", "James Bond", "John Travolta", "Amanda Maria Sanches", 
			"Vasya Pupkin-Khmurobrovchenko", "Matilda Akopjan", "Gogi Agiashvillie"};
		String nameExpr = "Semen|Amadei|Gogi|Akopjan";
		Pattern namePattern = Pattern.compile(nameExpr);
		for(String name: names){
			Matcher nameMatcher = namePattern.matcher(name);
			pf("--- name: %s ---\n", name);
			pf("matches: %b\n", nameMatcher.matches());
			pf("looking: %b\n", nameMatcher.lookingAt());
			pf("String.matches: |expr| : %b, |(?:expr).*| : %b \n", name.matches(nameExpr), name.matches("(?:"+nameExpr+").*"));
		}
		p("\n");
		
	}
	
	public void replaceText(){
		String source = "<h1>{title}</h1>\n<div class=\"content\">\n{text}\n</div >";
		String title = "Lorem ipsum";
		String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. "
			+ "Qui dicta minus molestiae vel beatae natus eveniet ratione temporibus aperiam harum alias officiis "
			+ "assumenda officia quibusdam deleniti eos cupiditate dolore doloribus!";
		String result = source.replaceAll("\\{title\\}", title).replaceAll("\\{text\\}", text);
		p(result);
		p("\n");
		
		// Replace by Matcher
		String msg = "Hi, mr. Bennet. Welkome to our city. Best regards!\n // for mr. Bennet //";
		Pattern tplPattern = Pattern.compile("mr\\. ([A-Z][a-z]+)");
		Matcher tplMatcher = tplPattern.matcher(msg);
		String newMsg = tplMatcher.replaceAll("mister $1 and missis $1");
		pf("new Msg: %s \n", newMsg);
		p("");
	}
	
	public void splitTest(){
		String source = "Lorem, ipsum dolor-sit;amet,consectetur;adipisicing?elit";
		String[] lines = source.split("[\\s\\,\\-;\\?]+");
		int num = 1;
		for(String line: lines){
			pf("%d) %s\n", num++, line);
		}
		p("\n");
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
	public static void pf(String str, Object... args){
		System.out.printf(str, args);
	}
	
}
