package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/update")
public class UpdateServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("nm");
		String address=req.getParameter("ad");
		String gender=req.getParameter("gen");
		double salary=Double.parseDouble(req.getParameter("sal"));
		String dt=req.getParameter("dob");
		Connection con=null;
		PreparedStatement pst=null;
		PrintWriter pw=resp.getWriter();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employees","root","Python#2121");
			pst=con.prepareStatement("update employee set name=?,address=?,gender=?,salary=?,birthdate=? where employeeid=?");
			pst.setString(1,name);
			pst.setString(2,address);
			pst.setString(3, gender);
			pst.setDouble(4, salary);
			pst.setString(5, dt);
			pst.setInt(6, id);
			pst.execute();
			pw.write("<html><head><link rel='stylesheet' href='demo.css'/></head><body><h1>Employee updated successfully</h1>");
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

