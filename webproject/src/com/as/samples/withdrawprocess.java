package com.as.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Servlet implementation class withdrawprocess
 */
@WebServlet("/withdrawprocess")
public class withdrawprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public withdrawprocess() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final DateFormat f= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	     Connection con=null;
	     PrintWriter out= response.getWriter();
	     HttpSession session= request.getSession();
	    
	     Date date= new Date();
	    	String currentdate=f.format(date);

	     int userid=(int) session.getAttribute("userid");
	     int pass=(int) session.getAttribute("pass");	     
         float amount=Float.parseFloat(request.getParameter("wamt"));
         String acctyp=request.getParameter("acctype");
         
	    try {
	    	
	    	con=databaseconn.getConnection();
	    	
	    	String sql1="SELECT * FROM accdetails where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ acctyp +"' ";
	    	Statement st=con.createStatement();
             
	    	ResultSet rs=st.executeQuery(sql1);
	    	
	    	con.setAutoCommit(false);
	    	if(rs.next())
	    	{
	    		BigDecimal curbal=(BigDecimal) rs.getObject("curbalance");
	    		float currentbal=curbal.floatValue();
	    		
	    		if(currentbal>amount)
	    		{
	    			currentbal-=amount;
	    			String sql2="UPDATE accdetails set curbalance='"+ currentbal +"' where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ acctyp +"' ";
	    			st=con.prepareStatement(sql2);
	    			st.executeUpdate(sql2);
	    			
	    			String tr="Debit";
	    			String sql3="insert into transhistory values('"+ userid +"','"+ acctyp +"','"+ currentdate +"','"+ tr +"','"+ currentbal +"')";
	    			st=con.createStatement();
	    			st.executeUpdate(sql3);
	    			System.out.println("inside withdraw"+currentbal+" "+amount);
	    			response.sendRedirect("/webproject/html/viewbal.jsp");
	    				    			
	    		}
	    		else
	    		{
	    			out.println("<script type=\"text/javascript\">");
					out.println("alert('Insufficient amount');");
					out.println("location='/webproject/html/withdraw.html';");
					out.println("</script>");
				}
	    	}
	    	else 
	    	{
	    		out.println("<script type=\"text/javascript\">");
				out.println("alert('Select Correct Account Type');");
				out.println("location='/webproject/html/withdraw.html';");
				out.println("</script>");
	    		
	    	}
	    	con.commit();
	    	con.close();  	
	    	
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	     
	
	
	}

}
