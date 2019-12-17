package com.as.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 * Servlet implementation class pinchangeprocess
 */
@WebServlet("/pinchangeprocess")
public class pinchangeprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection con=null;
		 PrintWriter out=response.getWriter();
		
		 HttpSession session= request.getSession();
		 
		 int userid=(int) session.getAttribute("userid");
		 int pass=(int) session.getAttribute("pass");

     int old=Integer.parseInt(request.getParameter("ps1"));
	 int neww=Integer.parseInt(request.getParameter("ps2"));
	 
	 try {
		  con=databaseconn.getConnection();
		  
	      String sql1="SELECT * FROM logindetails where loginid='"+ userid +"' and pin='"+ pass +"' ";
          String sql2="UPDATE logindetails set pin='"+ neww +"' where loginid='"+ userid +"' and pin='"+ pass +"' ";
       
          Statement st1=con.createStatement();
          
          ResultSet rs1=st1.executeQuery(sql1);
      
    if(rs1.next() && pass==old)  
      {   
    	  Statement st2=con.prepareStatement(sql2);
    	  st2.executeUpdate(sql2);
    	  
    	  System.out.println("pinchange"+neww);
    	  
    	  String sql3="UPDATE accdetails set pin='" + neww + "' where loginid='"+ userid +"' and pin='"+ pass +"' ";
    	  Statement st3=con.prepareStatement(sql3);
    	  st3.executeUpdate(sql3);
    	  
    	  response.sendRedirect("/webproject/logoutprocess");
    	  
      }
      else {
    	  
    	    out.println("<script type=\"text/javascript\">");
			out.println("alert('old pin incorrect');");
			out.println("location='/webproject/html/pinchange.html';");
			out.println("</script>");
      }
	 
	 
	     con.close();
	 }
	 catch (SQLException e) {
			e.printStackTrace();

		}
	 
}

}
