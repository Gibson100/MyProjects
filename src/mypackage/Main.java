package mypackage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * ADDRESS BOOK CONTACT MANAGEMENT PROJECT
 */

/**
 * @author GIBSON MUMBA
 *
 */

public class Main {

	//using the concept of encapsulation

	protected static String URL="";
	protected static String user="";
	protected static String password="";
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String city;
	private String country;
	private long phoneNo; 
	
	public static int answer;
	


	//setters and getters
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getzipCode() {
		return zipCode;
	}
	public void setzipCode(String postalCode) {
		this.zipCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	
	/**
	 * Starting point of the project
	 * below is the main menu of the project
	 * @return 
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServiceException 
	 */

	
	public static void main (String args[]) throws SQLException, IOException {
		
//		<-------printing the main menu here---------->
		Scanner scan = new Scanner(System.in);
		System.out.println("\t------------------------------------------------------------");
		System.out.println("\tWELCOME TO YOUR ADDRESS-BOOK-CONTACT MANAGEMENT APPLICATION");
		System.out.println("\t------------------------------------------------------------\n\n");

		
		System.out.println("1. Offline mode(with backup feature)\n"
						+ "2. Online mode (No backup feature)\n"
						+ "3. Quit");
		System.out.print("\nEnter your choice : ");
		answer = scan.nextInt();
		if(answer==1) {
			URL = "jdbc:mysql://localhost:3306/phonebook";
			user="root";
		}
		else if(answer==2) {
			URL = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6405582";
			user="sql6405582";
			password="4akPqsxkje";
		}
		else {
			System.exit(0);
		}
		
	
		System.out.println(
				"1. Add a Person\n"
				+ "2. Delete a Person\n"
				+ "3. Edit a Person\n"
				+ "4. View contacts\n"
				+ "5. Restore Deleted contact\n"
				+ "6. Quit Application");
		System.out.println("-------------------------\n");
		
//		<-------x--------menu done------x----->
		
		
		//get users choice
		
		System.out.print("Enter your option : ");
		answer = scan.nextInt();
		
		do {
			
			switch(answer) {
				
			case 1:
				Add add = new Add();
				add.AddContact();
				break;
				
			case 2:
				Delete delete = new Delete();
				break;
				
			case 3:
				Change change = new Change();
				break;
				
			case 4:
				View view = new View();
				break;
				
			case 5:
				Restore restore = new show();
				restore.menu();
				break;
				
			case 6:
				System.out.println("Are you sure you want to quit the program");
				System.err.print("y / n?");
				char ch = scan.next().charAt(0);
				if(ch == 'y' || ch=='Y') {
					System.out.println("GoodBye!!!!");
					System.exit(0);
				}
				else {
					main(null);
				}
			default:
				System.err.println("Invalid choice, please try again!");
			}
		}while(answer<7);
	}
}





