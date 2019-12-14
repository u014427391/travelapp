package com.tsw.servlet;
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class GetProgressServlet extends HttpServlet {
  
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //request.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=UTF-8");
		  request.setCharacterEncoding("UTF-8");
		  HttpSession sesson=request.getSession(); 
		  PrintWriter out=response.getWriter(); 
		  String str=(String)sesson.getAttribute("prog")+"";
		  String res=(String)sesson.getAttribute("result");
		  String err=(String)sesson.getAttribute("error");
		  System.out.println(str+","+res+","+err);
		  out.print(str+","+res+","+err);
		 
		  out.flush();
		  out.close();
	}
 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
    }
 
}
