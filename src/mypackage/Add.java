package mypackage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Add extends Main {
	
	//protected final static String URL= URL;
	protected static final int NULL = 0;
	
	Date date = new Date(); 
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

			
	void AddContact() throws SQLException, IOException {
		
/*
 * get input from the user and feed it to the setters and getters in main class.		
 */
		
		//object of main class.
		Main main = new Main();
		
		
		System.out.println("-------------------------\n");
		Scanner scan =new Scanner(System.in);
		
		
		
		//setting all the setter methods in main class(assigning values)
		
		System.out.print("Enter First name : ");
		main.setFirstName(scan.nextLine());
		System.out.println();
		System.out.print("Enter Last name : ");
		main.setLastName(scan.nextLine());
		System.out.println();
		System.out.print("Enter Address : ");
		main.setAddress(scan.nextLine());
		System.out.println();
		System.out.print("Enter Postal Code : ");
		main.setzipCode(scan.nextLine());
		System.out.println();
		System.out.print("Enter city name : ");
		main.setCity(scan.nextLine());
		System.out.println();
		System.out.print("Enter Country name : ");
		main.setCountry(scan.nextLine());
		System.out.println();
		System.out.print("Enter Phone Number : ");
		main.setPhoneNo(scan.nextLong());
		System.out.println();
		
		System.out.println("-------------------------\n");
			
		
		
				Connection connection;
				Statement statement;
				connection =DriverManager.getConnection(URL,user,password);
		
				statement = connection.createStatement();
				
			
				//SQL Insert statement here
				statement.execute("INSERT INTO contacts (ID,FirstName,LastName,Address,ZipCode,City,Country,PhoneNo,Date) VALUES('"+NULL+"','"+main.getFirstName()+"','"+
				main.getLastName()+"','"+main.getAddress()+"',"+main.getzipCode()+",'"+main.getCity()+"','"+main.getCountry()+"',"+
						main.getPhoneNo()+",'"+sdf.format(date)+"')");
				
				System.out.println("Success!");
//				<-----------------x---------------SQL statement -----------x----------------->
	
		
		System.out.print("do you want add another contact ");
		System.err.println("y/n ");
		char ch = scan.next().charAt(0);
		if(ch == 'y' || ch == 'Y') {
			new Add();
		}
		else {
			main(null);
		}
	
	
	}

}

