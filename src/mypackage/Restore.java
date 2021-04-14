package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.google.gdata.util.ServiceException;

public abstract class Restore extends Main {

	ResultSet resultSet;
	Connection connection;
	Statement statement;
	
	
	Scanner scan = new Scanner(System.in);
	
	public abstract void menu();
	public abstract void showBin();
	public abstract void restoreOne(int id);
	public abstract void restoreAll();
	
}


class show extends Restore {
	
	public void menu() {
		
		if(user=="sql6405582") {
			System.err.print("\nYour can't restore in this mode, please use offline mode for the feature of restoring from bin\n");
			//Main main = new Main();
			try {
				System.in.read();
				Main.main(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
		System.out.println("1. Show Bin\n"
				+ "2. Restore 1\n"
				+ "3. Restore All\n"
				+ "4. Main menu");
		
		answer = scan.nextInt();
		switch(answer) {
		case 1:
			showBin();
			break;
			
		case 2:
			System.out.println("Enter the id of the cantact you want to restore");
			int id = scan.nextInt();
			restoreOne(id);
			break;
			
		case 3:
			restoreAll();
			break;
			
		case 4:
			try {
				main(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			default:
				System.out.println("Invalid input, try again\n");
				menu();
		}
	}

	@Override
	public void showBin() {
	
		try {

			connection = DriverManager.getConnection(URL,user,password);
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select * from backup");
			
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
			System.out.println("\n"+i+" Contacts are in the bin");
		
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		menu();
	
		}
	

	@Override
	public void restoreOne(int id) {
		

		try {
			
			connection = DriverManager.getConnection(URL,user,password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from backup  where id='"+id+"'");
			
			while(resultSet.next()){
				setFirstName((resultSet.getString("FirstName")));
				setLastName((resultSet.getString("LastName")));
				setAddress(resultSet.getString("Address"));
				int zip = resultSet.getInt("ZipCode");
				String convertedToString = ""+zip;
				setzipCode(convertedToString);
				setCity(resultSet.getString("City"));
				setCountry(resultSet.getString("Country"));
				String countryCode = (resultSet.getString("CountryCode"));
				setPhoneNo(resultSet.getLong("PhoneNo"));
				Date date = (resultSet.getDate("Date"));
				
				System.out.println();
			}

			Date date = new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			Connection con;
			con =DriverManager.getConnection(Add.URL,user,password);
			Statement st;
			st = con.createStatement();
			st.execute("insert into contacts (ID,FirstName,LastName,Address,ZipCode,City,Country,PhoneNo,Date) VALUES('"+Add.NULL+"','"+getFirstName()+"','"+
			getLastName()+"','"+getAddress()+"',"+getzipCode()+",'"+getCity()+"','"+getCountry()+"',"+
					getPhoneNo()+",'"+sdf.format(date)+"')");
			
			
			statement.execute("delete from backup where id='"+id+"'");
			System.out.println("successfully restored");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		menu();
		
	}

	@Override
	public void restoreAll() {
		//following agile, this will be added latter on.
		
	}
	
	public void cleanBackup() throws SQLException, ServiceException {
		statement.executeQuery("delete from backup");
		System.out.println("Successfully deleted all backed up contacts\n");
		 try {
			main(null);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
