import java.util.ArrayList;
import java.util.Scanner;
/*Contains the main UI of the supplier
*/
/**
 * @author angelu
 * When admin has logged in, object of AdminSession is created.
 * Admin attributes are inherited that represents the basic information
 * of the current admin.
 */
public class AdminSession extends Admin implements Menuable,ProfileViewable {
	private DBConnect database;
	private Scanner in;
	
	public AdminSession(int adminID, String userEmailAddress, String userPassword, DBConnect db) {
		/*Information is saved*/
		super(adminID, userEmailAddress, userPassword);
		/*Receive the db connector reference*/
		this.database = db;
		/*For user input*/
		in = new Scanner(System.in);
		view();
	}
	@Override
	public void view() {
		// TODO Auto-generated method stub
		int choice;
		while(true) {
			System.out.print("\n[ADMIN PANEL]\n"
					+ "[0]Registered Suppliers Report\n"
					+ "[1]Registered Customers Report\n"
					+ "[2]Add Category\n"
					+ "[3]Add Size\n"
					+ "[4]Add Color\n"
					+ "[5]View My Profile\n"
					+ "Choice: ");
			choice = in.nextInt();

			if (choice == 0) {
				suppReport();
			}
			else if (choice == 1) {
				custReport();
			}
			else if (choice == 2) {
				addCategory();
			}
			else if (choice == 3) {
				addSize();
			}
			else if (choice == 4) {
				addColor();
			}
			else if (choice == 5) {
				viewMyProfile();
			}
			promptEnterKey();
		}
	}


	@Override
	public void viewMyProfile() {
		// TODO Auto-generated method stub
		System.out.println("\n[USER DETAILS]\n"
				+ "User: "+getUserEmailAddress()+"\n"
				+ "Password:"+getUserPassword()+"\n");	
	}

	/**
	 * Admin has access to the list of registered suppliers
	 */
	private void suppReport() {
		// TODO Auto-generated method stub
		ArrayList<Supplier> supp = database.getSuppliers();
		for (int i = 0; i < supp.size(); i++) {
			System.out.println("\nSupplierID: "+supp.get(i).getSupplierID()+"\n"
							 + "SuppEmail: "+supp.get(i).getUserEmailAddress()+"\n"
							 + "SuppPassword: "+supp.get(i).getUserPassword()+"\n"
							 + "SuppCompanyName: "+supp.get(i).getCompany()+"\n"
							 + "ContactFName: "+supp.get(i).getfName()+"\n"
							 + "ContactMinit: "+supp.get(i).getmInit()+"\n"
							 + "ContactLName: "+supp.get(i).getlName()+"\n"
							 + "ContactTitle: "+supp.get(i).getContactTitle()+"\n"
							 + "SuppCity: "+supp.get(i).getA().getCity()+"\n"
							 + "SuppState: "+supp.get(i).getA().getState()+"\n"
							 + "SuppPostalCode: "+supp.get(i).getA().getPostalCode()+"\n"
							 + "SuppCountry: "+supp.get(i).getA().getCountry()+"\n"
							 + "SuppJoinDate: "+supp.get(i).getJoinDate());
							System.out.println("Contact Numbers:");
							database.printSupplierContNums(supp.get(i).getSupplierID());
		}
		
	}

	/**
	 * Admin has access to the list of registered customers
	 */
	private void custReport() {
		// TODO Auto-generated method stub
		ArrayList<Customer> cust = database.getCustomers();
		for (int i = 0; i < cust.size(); i++) {
			System.out.println("\nCustID: "+cust.get(i).getCustomerID()+"\n"
							 + "CustFName: "+cust.get(i).getfName()+"\n"
							 + "CustMinit: "+cust.get(i).getmInit()+"\n"
							 + "CustLName: "+cust.get(i).getlName()+"\n"
							 + "CardNum: "+cust.get(i).getCardNum()+"\n"
							 + "CardExpMo: "+cust.get(i).getCardExpMo()+"\n"
							 + "CardExpYr: "+cust.get(i).getCardExpYr()+"\n"
							 + "CustCity: "+cust.get(i).getA().getCity()+"\n"
							 + "CustState: "+cust.get(i).getA().getState()+"\n"
							 + "CustPostalCode: "+cust.get(i).getA().getPostalCode()+"\n"
							 + "CustCountry: "+cust.get(i).getA().getCountry()+"\n"
							 + "CSCity: "+cust.get(i).getShippingAddress().getCity()+"\n"
							 + "CSState: "+cust.get(i).getShippingAddress().getState()+"\n"
							 + "CSPostalCode: "+cust.get(i).getShippingAddress().getPostalCode()+"\n"
							 + "CSCountry: "+cust.get(i).getShippingAddress().getCountry()+"\n"
							 + "JoinDate: "+cust.get(i).getJoinDate()+"\n"
							 + "Email:"+cust.get(i).getUserEmailAddress()+"\n"
							 + "Password:"+cust.get(i).getUserPassword());
			System.out.println("Contact Numbers:");
			database.printCustomerContNums(cust.get(i).getCustomerID());
		}

	}

	/**
	 * Admin may add a product category
	 */
	private void addCategory() {
		// TODO Auto-generated method stub
		System.out.println("\n[CATEGORY - DESCRIPTION]");
		database.printCategories();
		System.out.print("\nCategory Name: ");
		in.nextLine();
		String catName = in.nextLine();
		System.out.print("Category Description: ");
		String catDesc = in.nextLine();
		database.addCategory(catName,catDesc);
		System.out.println("\n[CATEGORY - DESCRIPTION]");
		database.printCategories();
	}

	/**
	 * Admin may add standard sizes for all products
	 */
	private void addSize() {
		// TODO Auto-generated method stub
		System.out.println("\n[SIZES]");
		database.printSizes();
		System.out.print("Size Name: ");
		in.nextLine();
		String newSize = in.nextLine();
		database.addSize(newSize); 
		System.out.println("\n[SIZES]");
		database.printSizes();
	}

	/**
	 * Admin may add standard colors for all products
	 */
	private void addColor() {
		// TODO Auto-generated method stub
		System.out.println("\n[COLORS]");
		database.printColors();
		System.out.print("Color Name: ");
		in.nextLine();
		String newColor = in.nextLine();
		database.addColor(newColor);
		System.out.println("\n[COLORS]");
		database.printColors();
	}
	
	private void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		in.nextLine();
	}

}
