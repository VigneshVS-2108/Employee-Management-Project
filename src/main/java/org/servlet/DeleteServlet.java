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

@WebServlet("/del")
public class DeleteServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id=Integer.parseInt(req.getParameter("id"));
		Connection con=null;
		PreparedStatement pst=null;
		PrintWriter pw=resp.getWriter();
		pw.println("<html><head><link rel='stylesheet' href='demo.css'/></head><body>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employees","root","Python#2121");
			pst=con.prepareStatement("delete from employee where employeeid=?");
			pst.setInt(1, id);
			int row= pst.executeUpdate();
			if(row==1)
				pw.println("<h1>Employee removed successfully</h1>");
			else
				pw.println("<h1>Employee not removed successfully</h1>");
			pw.println("<a href='index.jsp'>Click Here To Go To HomePage</a> ");
			pw.println("<a href='emp'>Click Here To View All The Employees</a> ");
					
		}
		catch(SQLException|ClassNotFoundException e)
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
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			pw.println("</body></html>");
		}
		
	}
}
