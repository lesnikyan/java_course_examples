/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webapp1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * docs:
 * http://www.java2ee.ru/servlets/index.html
 * http://tutorials.jenkov.com/java-servlets/servlet-filters.html
 * http://devcolibri.com/4284
 * 
 * filters:
 * http://www.codejava.net/java-ee/servlet/webfilter-annotation-examples
 * 
 * 
 * @author Less
 */
@WebServlet(name = "servlet1", urlPatterns = {"/servlet1/*", "/KarsnaShapochkaAndSeryjVolk111AdynAdyn/*"})
public class servlet1 extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//	response.addHeader("Content-Type", "text/plain");
		
		//log("Servlet: servlet1");
		try (PrintWriter out = response.getWriter()) {
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet servlet1</title>");			
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/WebApplication1/css/main.css\">");			
			out.println("</head>");
			out.println("<body><div class=\"test-content\">");
			
			// Server info:
			out.println("<h1>Servlet servlet1 at [" + request.getContextPath() + "] context</h1>");
			out.printf("<div>Server info: %s</div>", getServletContext().getServerInfo());
			out.printf("<div>Protocol: %s, port: %s</div>", request.getProtocol(), request.getServerPort());
			out.printf("<div>Method: %s</div>", request.getMethod());
			out.printf("<div>Get[name]: %s, Get[target]: %s </div>", request.getAttribute("name"), request.getParameter("target"));
			
			// Cookies
			// set in response:
			Cookie cook = new Cookie("firstCookie", "Hey, hey! Cookies is the best! ... with coffee.");
			cook.setMaxAge(((int) (new Date()).getTime()) + 600); // 10 min.
			response.addCookie(cook);
		
			// get from request:
			Cookie[] cookies = request.getCookies();
			String firstCookie = "[ no cookie :( ]";
			TreeMap<String, String> cookMap = new TreeMap<>();
			for(Cookie coo: cookies){
				if(coo.getName().equals("firstCookie")){
					firstCookie = coo.getValue();
				}
				cookMap.put(coo.getName(), coo.getValue());
			}
			out.printf("<div>Get[cookie]: %s, </div>", firstCookie);
			
			// Session
			HttpSession session = request.getSession(true);
			
			// get data
			// String
			String personName = "[no person]";
			Object pn = session.getAttribute("personName");
			if(pn != null)
				personName = (String) pn; 
			out.printf("<div>Session person: %s</div>", personName);
			
			// object
			String strSM = "[no superman]";
			Object obSM = session.getAttribute("super");
			if(obSM != null)
				strSM = ((Superman)obSM).name ; 
			out.printf("<div>Session superman: %s</div>", strSM);
			 
			// set data
			session.setAttribute("personName", "Lyolik Pampushko 1-st");
			
			Superman sman = new Superman();
			sman.name = "Petryk P'yatochkin";
			session.setAttribute("super", sman);
			
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex){
			System.out.println(ex);
			System.err.println(Arrays.asList(ex.getStackTrace()));
		}
	}
	
	class Superman {
		String name = "Vasya Pupkin";
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
