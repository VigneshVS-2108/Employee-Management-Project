package org.servlet;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/emp")
public class DisplayServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		PrintWriter pw= res.getWriter();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "Python#2121");
			st=con.createStatement();
			rs=st.executeQuery("select * from employee");
			pw.println("<html><head><link rel='stylesheet' href='list.css'></head><body><table>");
			pw.println("<tr>");
			pw.println("<th>Employee id</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Address</th>");
			pw.println("<th>Gender</th>");
			pw.println("<th>Salary</th>");
			pw.println("<th>Birth Date</th>");
			pw.println("<th>Update</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getDouble(5)+"</td>");
				pw.println("<td>"+rs.getString(6)+"</td>");
				pw.println("<td><a href='upd?id="+rs.getInt(1)+"' onClick='return func2()'>Update</td>");
				pw.println("<td><a href='del?id="+rs.getInt(1)+"' onClick='return func()'>Delete</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<a href='index.jsp'>Click Here To Go To HomePage</a> ");
			pw.println("<script>function func(){res=confirm('Are you sure you want to delete ?'); return res; }");
			pw.println("function func2(){r=confirm('Are you sure you want to update ?');return r;}");
			pw.println("</script>");
			pw.println("</body></html>");
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
				if(st!=null)st.close();
				if(rs!=null)rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
