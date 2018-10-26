package com.rakuten.demorest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TestApp {
	public static Connection getPgConnection(String host, int port, String database, String user, String password){
		Connection con =null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres","password");
		}
		catch(Exception e){
			con=null;
			System.err.println(e.getMessage());
		}
		return con;
	}
	public static ArrayList<Customer> searchByCity(String city){
		Connection con = null;
		ArrayList<Customer> searchResult =new ArrayList<Customer>();
		try{
			con =getPgConnection("localhost", 5432,"testdb", "postgres", "password");
			if (con==null){
				throw new Exception("Error! Connection not establish!");
			}
			String sql= "SELECT email, name,gender, date_of_birth,age,city,signup_date FROM customer WHERE city =? and active =?";
			PreparedStatement ps =con.prepareStatement(sql);
			ps.setString(1, city);
			ps.setString(2, "true");
			ResultSet rs = ps.executeQuery();
			System.out.println("error");
			if (rs!=null)
			{
				while(rs.next()){
					String tmpEmail=rs.getString("email");
					String tmpName=rs.getString("name");
					String tmpGender=rs.getString("gender");
					Date tmpDateOfBirth=rs.getDate("date_of_birth");
					int tempAge=rs.getInt("age");
					String tmpCity =rs.getString("city");
					Timestamp tmpSignupDate=rs.getTimestamp("signup_date");
					Customer tmpCustomer= new Customer(tmpEmail, tmpName, tmpGender, tmpDateOfBirth, tempAge, tmpCity, tmpSignupDate);
					searchResult.add(tmpCustomer);
				}
			}
		rs.close();	
		}
		catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if (con!=null){
					con.close();
				}
			}catch (Exception e){
				System.err.println(e.getMessage());
			}
		}
		return searchResult;
	}
public static void main(String[] args){
	ArrayList<Customer> searchResult = searchByCity("Kuala Lumpur");
	for (Customer c : searchResult){
		System.out.println(c);
	}
}
}
