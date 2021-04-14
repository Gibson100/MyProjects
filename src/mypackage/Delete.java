package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
	
	public String name;
	public int id;
	int ans;
	Scanner scan = new Scanner(System.in);
	Delete() throws SQLException, IOException{

		do {
			System.out.println("1. Delete by ID \n"
					+ "2. Delete By name \n"
					+ "3. Delete all\n"
					+ "4 main menu");
			System.out.print("Enter your choice : ");
			ans = scan.nextInt();
			
			System.out.println("Enter the ID of the person you want to delete : ");
			id = scan.nextInt();
			
			switch(ans) {
			case 1:
				new Delete(id);
				break;
				
			case 2:
				new Delete(name);
				break;
				
			case 3:
				DeleteAll();
				break;
				
			case 4:
				Main.main(null);
				break;
				
			default:
					System.out.println("Invalid input");
					new Delete();
			}

		}while(ans != 5);
	}
	
	Delete(int id) throws SQLException, IOException {
		Connection connection;
		Statement statement;
		connection =DriverManager.getConnection(Add.URL,Add.user,Add.password);

		statement = connection.createStatement();
		
			statement.execute("delete from contacts where id="+id+"");
			System.out.println("Successful");
			char c = scan.next().charAt(0);
			new Delete();
	}
	
	
	Delete(String name) throws SQLException, IOException {
		Connection connection;
		Statement statement;
		connection =DriverManager.getConnection(Add.URL,Add.user,Add.password);

		statement = connection.createStatement();
		
		System.out.println("1. Delete by First name \n"
				+ "2. Delete by Last name\n"
				+ "3. to Delete by both first and last name");
		ans = scan.nextInt();
		
		switch(ans) {
		case 1: 
			System.out.print("Enter first name : ");
			name = scan.next();
			statement.execute("delete from contacts where FirstName='"+name+"'");
			break;
			
		case 2:
			System.out.print("Enter first name : ");
			name = scan.next();
			statement.execute("delete from contacts where LastName='"+name+"'");
			break;
			
		case 3:
			System.out.print("Enter first name : ");
			name = scan.next();
			statement.execute("delete from contacts where FirstName='"+name+"'");
			System.out.print("Enter first name : ");
			name = scan.next();
			statement.execute("delete from contacts where LastName='"+name+"'");
			break;
			
		case 4:
			Main.main(null);
		
		}
		System.out.println("Successful");
		char c = scan.next().charAt(0);
		new Delete();
	}
	
	void DeleteAll() throws SQLException, IOException {
		
		Connection connection;
		Statement statement;
		connection =DriverManager.getConnection(Add.URL,Add.user,Add.password);

		statement = connection.createStatement();
		statement.execute("delete from contacts");
		
		System.out.println("Successfull");
		char c = scan.next().charAt(0);
		new Delete();
	}
	
}
