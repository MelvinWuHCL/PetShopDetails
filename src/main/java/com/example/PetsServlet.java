package com.example;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.DBConnection;





/**
 * Servlet implementation class PetsServlet
 */

@WebServlet("/PetShopServlet")

public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        // TODO Auto-generated method stub
		
		//PrintWriter out = response.getWriter();
        //out.println("Working");
		
        
        try {
        	PrintWriter out = response.getWriter();
            out.println("<html><body>");
                 
            //InputStream in = getServletContext().getResourceAsStream("src/main/resources/config.properties");
            //Properties props = new Properties();
            //props.load(in);
            
            String url = "jdbc:mysql://localhost:3306/sample";
            
            // Used root here
            String userid = "user"; 
            String password = "password"; 
            
            
            
            
            DBConnection conn = new DBConnection(url, userid, password);
        	java.sql.Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rst = stmt.executeQuery("select * from pets");
            		//+ "where id = " + petId);
        	
        	
            out.println("<TABLE><TR><TH>List</TH><TH>of</TH><TH>Pets</TH></TR>");
            out.println("</table>"); // Blank space
            out.println("<TABLE><TR><TH>NAME</TH></TR>");
            out.println("</table>");// Blank space
            
        	
            
            //prints out list of pets with id number
            while (rst.next()) {
            	out.println(rst.getInt("id")+": "+rst.getString("name")+"<Br>");
            } 
            
            
            int id = 1;
            
            String pet = request.getParameter("petId");
            
            int petId = 0;
            
        	boolean hasError = false;
        	/*
        	//below code is breaking for some reason
        	if(pet.isEmpty() ) {
            	out.print("<h1>Must input someting</h1>");
            	hasError = true;
            }else if(!pet.matches("-?(0|[1-9]\\d*)")) {
            	out.println("<h1>Not a number. Please Try again</h1>");
            	hasError = true;
            }else {
            	rst = stmt.executeQuery("select count(*) from pets");

            	petId = Integer.parseInt(pet);
             	 while (rst.next()) {
             		 petId = rst.getInt(1);
             	 }
            	if(0 > petId || petId > id) {
            		hasError = true;
                   	out.println("<h1> Invalid. Please input valid id</h1>");
                   }
            	}
            	
        	if(hasError == false) {
        		rst = stmt.executeQuery("select * from products where id=" + pet);
        	}
            while (rst.next()) {
                out.println("<tr><td>" + rst.getString("name") + "</td>" + "<td>" +
                		rst.getString("color") + "</td><td>" + rst.getBigDecimal("price") + "</td></tr>");
            }
            */
            
            out.println("</table>");
            
            
        
            stmt.close();        
   
        
            out.println("</body></html>");
            conn.closeConnection();
        	
        
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.setContentType("text/html");

		String url = "jdbc:mysql://localhost:3306/sample";
		
		//used root here
        String userid = "user";
        String password = "password";
		
		try {
			PrintWriter out =response.getWriter();
			out.println("<html><body>");
			
			String name = request.getParameter("name");
			
			//InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			//Properties props = new Properties();
			//props.load(in);
			
			
			DBConnection conn = new DBConnection(url, userid, password);
			
			Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rst =stmt.executeQuery("select * from products where id ="+name);
            
            out.println("<TABLE><TR><TH>NAME</TH><TH>  COLOR</TH><TH>  PRICE</TH></TR>");
            out.println("</table>");
            
            while (rst.next()) {
            	out.println(rst.getInt("id")+": "+rst.getString("name")+", "+rst.getString("color")+", $"+rst.getBigDecimal("price")+".00<Br>");
            }
            
            
            stmt.close();
            
            out.println("</body></html>");
            conn.closeConnection();
            }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			} 
		catch (SQLException e) {
			e.printStackTrace();
			}
			
		doGet(request, response);
		 
		
	}

}
