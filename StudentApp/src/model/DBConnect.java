package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "scott";
	private final String PASSWORD = "1234";

	Connection conn = null;

	public Connection getConnection(){


		try {
			//1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. DB연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		DBConnect connect = new DBConnect();

		Connection conn = connect.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Student";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				System.out.println(rs.getNString("fullname"));
				System.out.println(rs.getNString("email"));
				System.out.println(rs.getNString("password"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
}
