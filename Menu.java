import java.util.ArrayList;
import java.util.Scanner;

/*Main menu that every user goes through*/
public class Menu {
	private Scanner in;
	/*Connect database*/
	private DBConnect database;

	/*userID determines who is the user
	 * Admin, Supplier, Customer?*/
	private int userID;
	
	public Menu() {
		database = new DBConnect();
		in = new Scanner(System.in);
	}
	
	public void logInAs() {
		
		while (true) {
			System.out.println("\n[LOG-IN SCREEN]");
			System.out.print("[0]Admin\n[1]Customer\n[2]Supplier\n[3]Shipper\nChoice: ");
			/* Choice determines the type of user */
			int choice = in.nextInt();
			/* Enter login details */
			System.out.print("\nEmail Address: ");
			in.nextLine();
			String email = in.nextLine();
			System.out.print("Password: ");
			String password = in.nextLine();
			
			/*If valid details, go to the respective user session*/
			if (verifyLoginDetails(choice, email, password)== true) {
				/*If valid admin */
				if (choice == 0) {
					int adminID;
					adminID = database.getAdminID(email,password);
					AdminSession a = new AdminSession(adminID, email, password, database);
				}
				/*If valid customer */
				else if (choice == 1) {
					int customerID;
					customerID = database.getCustomerID(email,password);
					/*Retrieve information of the logged in customer*/
					Customer currentCustomer;
					currentCustomer = database.getCustomer(customerID);
					CustomerSession a = new CustomerSession
							(
									currentCustomer.getCustomerID(),
									currentCustomer.getfName(),
									currentCustomer.getmInit(),
									currentCustomer.getlName(),
									currentCustomer.getCardNum(),
									currentCustomer.getCardExpMo(),
									currentCustomer.getCardExpYr(),
									currentCustomer.getA().getCity(),
									currentCustomer.getA().getState(),
									currentCustomer.getA().getPostalCode(),
									currentCustomer.getA().getCountry(),
									currentCustomer.getShippingAddress().getCity(),
									currentCustomer.getShippingAddress().getState(),
									currentCustomer.getShippingAddress().getPostalCode(),
									currentCustomer.getShippingAddress().getCountry(), 
									currentCustomer.getJoinDate(), 
									currentCustomer.getUserEmailAddress(), 
									currentCustomer.getUserPassword(),
									database,currentCustomer.getContactNumber()
							);
					}
				else if (choice == 2) {
					int supplierID;
					supplierID = database.getSupplierID(email, password);
					/*Retrieve information of the logged in supplier*/
					Supplier currentSupplier;
					currentSupplier = database.getSupplier(supplierID);
					SupplierSession a = new SupplierSession
							(
									currentSupplier.getSupplierID(),
									currentSupplier.getUserEmailAddress(),
									currentSupplier.getUserPassword(),
									currentSupplier.getCompany(),
									currentSupplier.getfName(),
									currentSupplier.getmInit(),
									currentSupplier.getlName(),
									currentSupplier.getContactTitle(),
									currentSupplier.getA().getCity(),
									currentSupplier.getA().getState(),
									currentSupplier.getA().getPostalCode(),
									currentSupplier.getA().getCountry(),
									currentSupplier.getJoinDate(),
									database, currentSupplier.getContactNumber()
							);
				}
				else if (choice == 3) {
					int shipperID;
					shipperID = database.getShipperID(email, password);
					/*Retrieve information of the logged in supplier*/
					Shipper currentShipper;
					currentShipper = database.getShipper(shipperID);
					ShipperSession a = new ShipperSession
					(currentShipper.getShipperID(),currentShipper.getCompany(), currentShipper.getShipemail(), currentShipper.getShippassword(), database,currentShipper.getContactNumber());
					
				
				}
				break;
			}
		}
	}

	/*Verify login details*/
	public boolean verifyLoginDetails(int choice, String email, String password) {
		if (choice == 0) {
			return database.verifyAdmin(email, password);
		}
		else if (choice == 1){
			return database.verifyCustomer(email, password);
		}
		else if (choice == 2) {
			return database.verifySupplier(email, password);
		}
		else if (choice == 3) {
			return database.verifyShipper(email, password);
		}
		else System.out.println("Invalid input.");
		return false;
		
	}
}
