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
import java.util.ArrayList;

/**
 * Servlet implementation class miniprocess
 */
@WebServlet("/miniprocess")
public class miniprocess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public miniprocess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public static ArrayList<String> time;
	public static ArrayList<String> balance;
	public static ArrayList<String> action;
	public static ArrayList<String> acctno;
	public static ArrayList<String> acctype;
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		 int userid=(int) session.getAttribute("userid");
	     int pass=(int) session.getAttribute("pass");	 
		
	     String fdate= request.getParameter("fdate");
	     String tdate= request.getParameter("tdate");
	     fdate=fdate+ " 00:00:00";
	     tdate=tdate+ " 23:59:59";
	     
	     time=new ArrayList<>();
	     balance=new ArrayList<>();
	     acctype=new ArrayList<>();
	     acctno=new ArrayList<>();
	     action=new ArrayList<>();
	     
		try {
			con=databaseconn.getConnection();
			Statement st=null;
			
			String sql="SELECT * from transhistory where loginid='"+ userid +"' and tdate between '"+ fdate +"' and '"+ tdate +"' ";
			st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            if(rs.next())
            {
            	while(rs.next())
            	{
            		time.add(rs.getObject("tdate").toString());
            		balance.add(rs.getObject("transamt").toString());
            		action.add(rs.getObject("transtype").toString());
            		acctype.add(rs.getObject("acctype").toString());
            		acctno.add(rs.getObject("loginid").toString());
              	}
            	System.out.println("inside mini");
            	
            	out.println("<!DOCTYPE html>" 
            	        + "<html>" 
            			+ "<head>"
						+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
						+ "<title>MINI STATEMENT</title>"
						+ "</head>" 
						+ "<body>"
						+ "<h1 align='center'>MINI STATEMENT</h1>" 
						+ "<table style='width:90%' border='1'>" + "<tr>"
						+ "<th><font size='3'>Account No</font> </th>"
						+ "<th><font size='3'>Account Type</font> </th>" 
						+ "<th><font size='3'>Time</font> </th>" 
						+ "<th><font size='3'>Transaction Type</font> </th>"
						+ "<th><font size='3'>Available Balance</font> </th>"
						+ "</tr>");

				for (int i = 0; i < action.size(); i++) 
				{
					out.println("<tr>" 
				            + "<th><font size='3'>" + acctno.get(i) + "</font> </th>" 
				            + "<th><font size='3'>" + acctype.get(i) + "</font> </th>"
							+ "<th><font size='3'>" + time.get(i) + "</font> </th>"
							+ "<th><font size='3'>" + action.get(i) + "</font> </th>"
				            + "<th><font size='3'> " + balance.get(i) + "</font> </th>" 
							+ "</tr>");
				}
				out.println("</table>");
            	
				out.println("<form action='Download'><button type='submit'>Download</button></form><br>"
				        +"<form action='logoutprocess'><button>Logout</button></form>" 
						+ "</body>" 
				        + "</html>");
            	
            }
            else {
            	out.println("<script type=\"text/javascript\">");
				out.println("alert('Specify Correct Date');");
				out.println("location='/webproject/html/ministatement.html';");
				out.println("</script>");
            }
			con.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
