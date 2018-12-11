package com.primetgi.org.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set up connection variables
		String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String jdbcURL = "jdbc:sqlserver://user-229;databaseName=user229DB;user=sa;password=admin";

		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			out.println("Connecting to Database: " + jdbcURL);

			Class.forName(jdbcDriver);
			Connection connection = DriverManager.getConnection(jdbcURL);

			out.print("Connection Estabilished Successfully");

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
