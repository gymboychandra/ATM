package com.as.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class transferprocess
 */
@WebServlet("/transferprocess")
public class transferprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transferprocess() {
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
	  
	  float amount=Float.parseFloat(request.getParameter("tamt"));
      String factype=request.getParameter("factype");
      String tactype=request.getParameter("tactype");
      
      try {
    	  con=databaseconn.getConnection();
    	 
    	  String sql1="SELECT * FROM accdetails where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ factype +"' ";
    	  String sql2="SELECT * FROM accdetails where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ tactype +"' ";
	
    	  Statement st=con.createStatement();
          ResultSet rs1=st.executeQuery(sql1);
	    	
          st=con.createStatement();
	      ResultSet rs2=st.executeQuery(sql2);
	    	
	      con.setAutoCommit(false);
    	  if(rs1.next() && rs2.next())
    	  {
    		  BigDecimal curbal1=(BigDecimal) rs1.getObject("curbalance");
	    		float currentbal1=curbal1.floatValue();
	    		
	    		 BigDecimal curbal2=(BigDecimal) rs2.getObject("curbalance");
		    		float currentbal2=curbal2.floatValue();
		    		
		    		
	    		if(currentbal1>amount)
	    		{
	    			currentbal1-=amount;
	    			String sql3="UPDATE accdetails set curbalance='"+ currentbal1 +"' where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ factype +"' ";
	    			st=con.prepareStatement(sql3);
	    			st.executeUpdate(sql3);
	    			
	    			currentbal2+=amount;
	    			String sql4="UPDATE accdetails set curbalance='"+ currentbal2 +"' where loginid='"+ userid +"' and pin='"+ pass +"' and acctype='"+ tactype +"' ";
	    			st=con.prepareStatement(sql4);
	    			st.executeUpdate(sql4);
	    			
	    			String tr="Debit";
	    			String sql5="insert into transhistory values('"+ userid +"','"+ factype +"','"+ currentdate +"','"+ tr +"','"+ currentbal1 +"')";
	    			st=con.createStatement();
	    			st.executeUpdate(sql5);
	    			
	    			String tr1="Credit";
	    			String sql6="insert into transhistory values('"+ userid +"','"+ tactype +"','"+ currentdate +"','"+ tr1 +"','"+ currentbal2 +"')";
	    			st=con.createStatement();
	    			st.executeUpdate(sql6);
	 
	    			
	    			System.out.println("inside transfer"+currentbal1+" "+amount+" "+currentbal2);
	    			response.sendRedirect("/webproject/html/viewbal.jsp");

	    		}
	    		else{
	    			out.println("<script type=\"text/javascript\">");
					out.println("alert('Insufficient amount');");
					out.println("location='/webproject/html/transfer.html';");
					out.println("</script>");
	    		}
    		 
    		  
    	  }
    	  else 
	    	{
	    		out.println("<script type=\"text/javascript\">");
				out.println("alert('Select Correct Account Type');");
				out.println("location='/webproject/html/transfer.html';");
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
