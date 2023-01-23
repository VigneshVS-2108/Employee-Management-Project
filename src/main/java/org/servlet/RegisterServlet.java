package org.servlet;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "reg" ,value = "/reg")
public class RegisterServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String name=req.getParameter("nm");
		String address=req.getParameter("ad");
		String gender=req.getParameter("gen");
		double salary=Double.parseDouble(req.getParameter("sal"));
		String dt=req.getParameter("dob");
		
		Connection con=null;
		PreparedStatement pst=null;
		PrintWriter pw=res.getWriter();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employees","root","Python#2121");
			pst=con.prepareStatement("insert into employee(name,address,gender,salary,birthdate) values(?,?,?,?,?)");
			pst.setString(1,name);
			pst.setString(2,address);
			pst.setString(3, gender);
			pst.setDouble(4, salary);
			pst.setString(5, dt);
			pst.execute();
			pw.write("<html><head><link rel='stylesheet' href='demo.css'/></head><body><h1>Employee registered successfully</h1>");
			pw.println("<a href='index.jsp'>Click Here To Go To HomePage</a> ");
			pw.println("<a href='emp'>Click Here To View All The Employees</a> ");
			pw.println("</body></html>");
		}
		catch (SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if(con!=null)con.close();
				if(pst!=null)pst.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

}
