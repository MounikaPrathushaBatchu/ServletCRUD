package test;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {
	
	Employee e = new Employee();
	Connection con;
	PreparedStatement pst;

	public Employee getE() {
		return e;
	}
	public void setE(Employee e) {
		this.e = e;
	}
	
	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.230:1521:orcl", "training", "training");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void service(HttpServletRequest request,HttpServletResponse response) {
		try {
			pst = con.prepareStatement("insert into ServletEmp values (?,?,?,?)");
			pst.setInt(1, e.getId());
			pst.setString(2, e.getName());
			pst.setDouble(3, e.getSalary());
			pst.setString(4, e.getAddress());
			pst.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			pw.println("registration successfull");
			RequestDispatcher rd = request.getRequestDispatcher("success");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
