package com.as.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class loginprocess
 */
@WebServlet("/loginprocess")
public class loginprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Connection con=null;
	PrintWriter out=response.getWriter();
	
      int userid=Integer.parseInt(request.getParameter("userid"));
      int pass=Integer.parseInt(request.getParameter("pass"));
      
      try {
			con = databaseconn.getConnection();
			
			String sql = "SELECT * FROM logindetails where loginid='" + userid + "' and pin='" + pass + "' ";
			Statement a=con.createStatement();
			ResultSet rs = a.executeQuery(sql);
			
			
			if(rs.next()) {
				
				 //cant use post in sendrediredt

				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("pass", pass);
				
				System.out.println("login sucess");
				response.sendRedirect("/webproject/html/menu.html"); 
			
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('card id or PIN incorrect');");
				out.println("location='/webproject/html/login.html';");
				out.println("</script>");
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		}

	
}
