package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="update",value = "/upd")
public class UpdateForm extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id=Integer.parseInt(req.getParameter("id"));
		PrintWriter pw=resp.getWriter();

			pw.println("<html><head><link rel='stylesheet' href='index.css'/></head><body>");
			pw.println("<form action='update?id="+id+"' method='post'>");
			pw.println("<fieldset>");
			pw.println("<legend>Update Employee Details</legend>");
			pw.println("<input type='text' name='nm' placeholder='Enter Employee Name' required/> <br><br>");
			pw.println("<textarea rows='10' cols='30' name='ad' placeholder='Enter Employee Address' required></textarea> <br><br>");
			pw.println("<label for='gen'>Enter Your Gender :-</label>");
			pw.println("<select name='gen'><option>Male</option><option>Female</option>	</select> <br><br>");
			pw.println("<input type='text' name='sal' placeholder='Enter Your Salary' required/> <br><br>");
			pw.println("<label for='gen'>Enter Date Of Birth :-</label>");
			pw.println("<input type='date' name='dob' placeholder='Enter BirthDate' required/> <br><br>");
			pw.println("<input type='submit' value='Update'/>");
			pw.println("</fieldset>");
			pw.println("</form>");
			pw.println("<a href='index.jsp'>Click Here To Go To HomePage</a> ");
			pw.println("<a href='emp'>Click Here To View All The Employees</a> ");
			pw.println("</body></html>");

	}
}
