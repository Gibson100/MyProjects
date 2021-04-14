package mypackage;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Change extends Main {
	
	private int id;
	
	Scanner scan = new Scanner(System.in);
	Main main = new Main();
	Add add = new Add();
	
	public Change() throws SQLException, IOException {
		
		boolean controller = false;
		
		if  (controller == false) {
			System.out.print("Do you know the id of the contact you want to edit? ");
			System.err.print("[y/n]");
			char ch = scan.next().charAt(0);
			if(ch =='n' || ch=='N') {
				ShowContacts();
			}
			else {
				System.out.print("Enter the ID of the person you want to edit : ");
				id = scan.nextInt();
				
				System.out.println("-------------------------\n");
				System.out.println("1. Edit Name \n"
						+ "2. Edit City \n"
						+ "3. Edit Phone Number \n"
						+ "4. Edit Country \n"
						+ "5. Edit Address\n"
						+ "6. Show all Contacts\n"
						+ "7. Go back");
			}
		}
		System.out.print("Enter your choice : ");
		Main.answer = scan.nextInt();
		do {
			switch(answer) {
			case 1: 
				new Change(id);
				break;
				
			case 2:
				System.out.print("Enter new City : ");
				setCity(scan.next());
				new Change(id,getCity());
				break;
				
			case 3:
				System.out.print("Enter the new Phone Number : ");
				setPhoneNo(scan.nextLong());
				new Change(id,getPhoneNo());
				break;
				
			case 4:
				System.out.print("Enter the new Country : ");
				setCountry(scan.next());
				new Change(getCountry(),id);
				break;
				
			case 5:
				System.out.print("Enter the new Address : ");
				setAddress(scan.next());
				System.out.println("Enter Postal Code : ");
				setzipCode(scan.next());
				ChangeAddress(getAddress(),getzipCode(), id);
				break;
				
			case 6:
				controller = true;
				break;
			case 7:
				main(null);
					
			}
		}while(true);
	}
	
	//change the first name and last name
		public Change(int id) throws SQLException, IOException {
			
			System.out.println("Enter 1 to change first name\n"
					+ "Enter 2 to change last name\n"
					+ "Enter 3 to change both");
			answer = scan.nextInt();
			
			//database query.
			
			Connection connection;
			Statement statement;
			connection =DriverManager.getConnection(URL,user,password);

			statement = connection.createStatement();
			
			switch(answer) {
			case 1:
					System.out.print("Enter new first name : ");
					setFirstName(scan.next());
					statement.executeUpdate("update contacts set FirstName='"+getFirstName()+"' where id='"+id+"'");
					break;
			case 2:
				System.out.print("Enter new last name : ");
				setLastName(scan.next());
				statement.executeUpdate("update contacts set FirstName='"+getLastName()+"' where id='"+id+"'");
				break;
				
			case 3:
				System.out.print("Enter new first name : ");
				setFirstName(scan.next());
				statement.executeUpdate("update contacts set FirstName='"+getFirstName()+"' where id='"+id+"'");
				System.out.print("Enter new last name : ");
				setLastName(scan.next());
				statement.executeUpdate("update table contacts set FirstName='"+getLastName()+"' where id ='"+id+"'");
				break;
				
				default:
					System.out.println("Invalid Input, try again");
					new Change();
		
			}
			System.out.println("Successfull!");
			System.out.println("1. previous menu\n"
					+ "2. main menu");
			int ch = scan.nextInt();
			if(ch == 1)
				new Change();
			else
				main(null);
		}
	
	//contractor to change the city
	public Change(int id, String city) throws SQLException, IOException  {
		
	try {
			
	
		Connection connection;
		Statement statement;
		connection =DriverManager.getConnection(URL,user,password);

		statement = connection.createStatement();
		
			statement.executeUpdate("update contacts set City='"+city+"' where id='"+id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to update the contact");
		}
		
		System.out.println("Successfully Updated");
		System.out.print("do you want to update other detailes ");
		System.err.println("y/n ? ");
		char choice = scan.next().charAt(0);
		if(choice == 'y' || choice == 'Y') {
			new Change();
		}
		else {
			main(null);
		}
		
		
	}
	
	//contractor to change the country
	public Change(String Country, int id) throws SQLException, IOException {
		try {
			
			Connection connection;
			Statement statement;
			connection =DriverManager.getConnection(URL,"root","");

			statement = connection.createStatement();
			
				statement.executeUpdate("update contacts set Country='"+Country+"' where id='"+id+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Failed to update the contact");
			}
			
			System.out.println("Successfully Updated");
			System.out.print("do you want to update other detailes ");
			System.err.println("y/n ? ");
			char choice = scan.next().charAt(0);
			if(choice == 'y' || choice == 'Y') {
				new Change();
			}
			else {
				main(null);
			}
	}
	
	//contractor to change to phone number
	public  Change(int id, Long PhoneNo) throws SQLException, IOException {
		try {
			
			Connection connection;
			Statement statement;
			connection =DriverManager.getConnection(URL,user,password);

			statement = connection.createStatement();
			
				statement.executeUpdate("update contacts set PhoneNo='"+PhoneNo+"' where id='"+id+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Failed to update the contact");
			}
			
			System.out.println("Successfully Updated");
			System.out.print("do you want to update other detailes");
			System.err.println("y/n ? ");
			char choice = scan.next().charAt(0);
			if(choice == 'y' || choice == 'Y') {
				new Change();
			}
			else {
				main(null);
			}
	}
	

	
	//non constructor method to change the address of the contact
	//whenever contact changes address, we have to also update the zip code.
	
	public void ChangeAddress(String address,String zip,int id) throws SQLException, IOException {
		
		try {
		Connection connection;
		Statement statement;
		connection =DriverManager.getConnection(URL,user,password);

		statement = connection.createStatement();
		
			statement.executeUpdate("update contacts set Address ='"+address+"' where id='"+id+"'");
			statement.executeUpdate("update contacts set  ZipCode='"+zip+"' where id='"+id+"'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to update the contact");
		}
		
		System.out.println("Successfully Updated");
		System.out.print("do you want to update other records");
		System.err.println("y/n ? ");
		char choice = scan.next().charAt(0);
		if(choice == 'y' || choice == 'Y') {
			new Change();
		}
		else {
			main(null);
		}
		
	}
	void ShowContacts() throws SQLException, IOException {
		
		Statement statement;
		Connection connection;
		connection = DriverManager.getConnection(URL,user,password);
		statement = connection.createStatement();
		ResultSet resultSet;
		
		resultSet = statement.executeQuery("select * from contacts");
		
		int i=0;
		System.out.print("ID");
		System.out.printf("%15s","FIRST NAME");
		System.out.printf("%15s","LAST NAME");
		System.out.printf("%30s","ADDRESS");
		System.out.printf("%10s","ZIP CODE");
		System.out.printf("%15s","CITY");
		System.out.printf("%20s","COUNTRY");
		System.out.printf("%30s","COUNTRY CODE");
		System.out.printf("%15s","PHONE NUMBER");
		System.out.printf("%10s","DATE");
		
		System.out.println("\n__________________________________________________________________________________"
				+ "____________________________________________________________________________________\n");

		
		while(resultSet.next()){
			System.out.print(resultSet.getInt("ID"));
			System.out.printf("%15s",resultSet.getString("FirstName"));
			System.out.printf("%15s",resultSet.getString("LastName"));
			System.out.printf("%30s",resultSet.getString("Address"));
			System.out.printf("%10s",resultSet.getInt("ZipCode"));
			System.out.printf("%15s",resultSet.getString("City"));
			System.out.printf("%30s",resultSet.getString("Country"));
			System.out.printf("%20s",resultSet.getString("CountryCode"));
			System.out.printf("%15s",resultSet.getLong("PhoneNo"));
			System.out.printf("%10s",resultSet.getDate("Date"));
			i++;
			
			System.out.println();
		}
		
		System.out.println("\n"+i+" contacts fetched successfully");
		
		new Change();
	}
	
}
