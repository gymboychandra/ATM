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
 * Servlet implementation class viewbalprocess
 */
@WebServlet("/viewbalprocess")
public class viewbalprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewbalprocess() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 Connection con=null;
         PrintWriter out=response.getWriter();
         
         HttpSession session=request.getSession();
         int userid= (int) session.getAttribute("userid");
         int pass= (int) session.getAttribute("pass");
         
         String acctyp=request.getParameter("acctype");
         
         try {
        	  con=databaseconn.getConnection();
        	  
        	  String sql="SELECT * FROM accdetails where loginid='" + userid +"' and pin='" + pass + "' and acctype='" + acctyp + "' ";
        	  Statement st=con.createStatement();
        	  ResultSet rs= st.executeQuery(sql);
        	 
        	  if(rs.next()) {
        		  
        		  session.setAttribute("currentbal",rs.getObject("curbalance"));
        		  System.out.println("inside viewbal process"+rs.getObject("curbalance"));
        		  response.sendRedirect("/webproject/html/viewbal.jsp");
        		 
        	  }
        	  else {
        		out.println("<script type=\"text/javascript\">");
  				out.println("alert('Select correct Account Type');");
  				out.println("location='/webproject/html/viewbal.jsp';");
  				out.println("</script>");
        	  }
        	  
        	  con.close();
        	  
        	        	 
         }
         catch(SQLException e){
        	 e.printStackTrace();
        	 
         }
	
	
	
	}

}
