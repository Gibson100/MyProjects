package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Column;
import com.mysql.cj.xdevapi.ColumnImpl;

public class View extends Add {
	
	ResultSet resultSet;
	static Connection connection;
	static Statement statement;
	
	
	Scanner scan = new Scanner(System.in);
	String pattern = "";
	int opt;

	View() throws SQLException, IOException {
		
		connection = DriverManager.getConnection(URL,user,password);
		statement = connection.createStatement();

		resultSet = statement.executeQuery("Select * from contacts");
		
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");

		
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			
			System.out.println();
		}
		System.out.println("\n"+i+" Contacts fetched from the database successfully");
		
	System.out.println("------------------------------------------------------------------------"
			+ "------------------------------------------------------------------\n");
	
		System.out.println("1. Display Specific Contact \n"
				+ "2. Display names and phone numbers only\n"
				+ "3. Display in Ascending Order by Name\n"
				+ "4. Display in Descending order by name\n"
				+ "5. Search By Pattern\n"
				+ "6. Display in ascending order by city\n"
				+ "7. Display in ascending order by country\n"
				+ "8. Main menu");
		System.out.print("Enter your choice : ");
		opt = scan.nextInt();
		switch(opt) {
		case 1:
			System.out.println("Enter contact id : ");
			opt = scan.nextInt();
			displaySpecificContact(opt);
			break;
			
		case 2:
			displayNamesAndNumbers();
			break;
			
		case 3:
			sortAscending();
			break;
			
		case 4: 
			sortDescending();
			break;
			
		case 5:
			System.out.println("Enter your search here : ");
			pattern = scan.next();
			displayPattern(pattern);
			break;
			
		case 6:
			sortByCity();
			break;
			
		case 7:
			sortByCountry();
			break;
			
		case 8:
			main(null);
			
			default:
				System.out.println("Invalid input");
				new View();
				break;
		}

	connection.close();
	System.in.read();
	main(null);
	}
	
	void displaySpecificContact(int id) throws SQLException, IOException {
		//Display the contact in a nice row
		resultSet = statement.executeQuery("select * from contacts where id='"+id+"'");
		
		
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");
			
		
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			System.out.println();
		}
			System.out.println("successfull!");
			System.in.read();
			System.out.println();
			new View();
}
	
	void displayNamesAndNumbers() throws SQLException, IOException {
		
		System.out.printf("FIRST NAME");
		System.out.printf("%20s","LAST NAME");
		System.out.printf("%20s","COUNTRY CODE");
		System.out.printf("%20s","PHONE NUMBER");
		
		System.out.println("\n-------------------------------------------"
				+ "----------------------");
		ResultSet rs;
		int i=0;
		rs = statement.executeQuery("select FirstName,LastName,CountryCode,PhoneNo from contacts");
		
		while(rs.next()){
		System.out.print(rs.getString("FirstName"));
		System.out.printf("%20s",rs.getString("LastName"));
		System.out.printf("%20s",rs.getString("CountryCode"));
		System.out.printf("%20s",rs.getString("PhoneNo"));
		i++;
		System.out.println("\n");
		}
		System.out.println("\n"+i+" Contacts fetched successfully");
		System.in.read();
		new View();
	}
	
	void sortAscending() throws SQLException, IOException {
		
		resultSet = statement.executeQuery("select * from contacts order by FirstName");
		
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");
			

		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			System.out.println();
		}
		System.out.println("\n"+i+"  Contacts fetched from the database seccessfully");
		System.in.read();
			System.out.println();
			new View();
}
	void sortDescending() throws SQLException, IOException {
		resultSet = statement.executeQuery("select * from contacts order by FirstName desc");
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");
			
		

		
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			
			System.out.println();
		}
		System.out.println("\n"+i+"  Contacts fetched from the database succesfully");

			System.in.read();
			System.out.println();
			new View();
}
	void displayPattern(String pattern) throws SQLException, IOException {
		
			try {
				resultSet = statement.executeQuery("select * from contacts where FirstName or LastName LIKE '%" + pattern + "%' or Address LIKE '%" + pattern + "%' or PhoneNo LIKE '%" + pattern + "%' or ZipCode LIKE '%" + pattern + "%' or Country LIKE '%" + pattern + "%'");
				int i=0;
				System.out.print("ID");
				System.out.printf("%15s","FIRST NAME");
				System.out.printf("%15s","LAST NAME");
				System.out.printf("%20s","ADDRESS");
				System.out.printf("%10s","ZIP CODE");
				System.out.printf("%15s","CITY");
				System.out.printf("%15s","COUNTRY");
				System.out.printf("%15s","COUNTRY CODE");
				System.out.printf("%15s","PHONE NUMBER");
				System.out.printf("%10s","DATE");
				
				System.out.println("\n__________________________________________________________________________________"
						+ "______________________________________________________\n");
			
				while(resultSet.next()){
					System.out.print(resultSet.getInt("ID"));
					System.out.printf("%15s",resultSet.getString("FirstName"));
					System.out.printf("%15s",resultSet.getString("LastName"));
					System.out.printf("%20s",resultSet.getString("Address"));
					System.out.printf("%10s",resultSet.getInt("ZipCode"));
					System.out.printf("%15s",resultSet.getString("City"));
					System.out.printf("%15s",resultSet.getString("Country"));
					System.out.printf("%15s",resultSet.getString("CountryCode"));
					System.out.printf("%15s",resultSet.getLong("PhoneNo"));
					System.out.printf("%15s",resultSet.getDate("Date"));
					i++;
					
					System.out.println();
				}
				System.out.println("\n"+i+"  Contacts fetched from the database successfully");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Pattern  does not exist in the contact list");
			}
			System.in.read();
			new View();
			System.out.println();
			//new View();
		}
	
	void sortByCity() throws SQLException, IOException {
		
		resultSet = statement.executeQuery("select * from contacts order by City");
		
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");
	
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			
			System.out.println();
		}
		System.out.println("\n"+i+"  Contacts fetched from the database successfully");
	 
			System.in.read();
			System.out.println();
			new View();
	}
	void sortByCountry() throws SQLException, IOException {
		
		resultSet = statement.executeQuery("select * from contacts order by Country");
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%20s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%15s","COUNTRY");
		System.out.printf("%15s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "______________________________________________________\n");

		
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%20s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%15s",resultSet.getString("Country"));
			System.out.printf("%15s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%15s",resultSet.getDate("Date"));
			i++;
			
			System.out.println();
		}
		System.out.println("\n"+i+"  Contacts fetched from the database successfully");

			System.in.read();
			System.out.println();
			new View();
	}
}
