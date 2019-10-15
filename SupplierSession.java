import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * @author angelu
 * When supplier has logged in, object of SupplierSession is created.
 * Supplier attributes are inherited that represents the basic information
 * of the current supplier.
 */
/*Contains the main UI of the supplier
*/
public class SupplierSession extends Supplier implements Menuable,ProfileViewable {
	private DBConnect database;
	private Scanner in;

	public SupplierSession(int sid, String semail, String up, String c, String fn, String mi, String ln, String ct,
			String city, String state, int postalc, String country, String jd, DBConnect database, ArrayList<String> connums) 
	{
		super(sid, semail, up, c, fn, mi, ln, ct, city, state, postalc, country, jd);
		contactNumber = connums;
		/*Receive the db connector reference*/
		this.database = database;
		/*For user input*/
		in = new Scanner(System.in);
		view();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		int choice;
		while(true) {
			System.out.print("\n[SUPPLIER PANEL]\n"
					+ "[0]View Products\n"
					+ "[1]Add Products\n"
					+ "[2]Add Stock\n" 
					+ "[3]Sales Report\n"
					+ "[4]Pending order details\n"
					+ "[5]View My Profile\n"
					+ "Choice: ");
			choice = in.nextInt();
			System.out.println("");
			if (choice == 0) {
				viewProd();
			}
			else if (choice == 1) {
				addProd();
			}
			else if (choice == 2) {
				addStock();
			}
			else if (choice == 3) {
				salesReport();
			}
			else if (choice == 4) {
				accomplishOrders();
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
		System.out.print("\nSupplierID: "+getSupplierID()+"\n"
				 + "SuppEmail: "+getUserEmailAddress()+"\n"
				 + "SuppPassword: "+getUserPassword()+"\n"
				 + "SuppCompanyName: "+getCompany()+"\n"
				 + "ContactFName: "+getfName()+"\n"
				 + "ContactMinit: "+getmInit()+"\n"
				 + "ContactLName: "+getlName()+"\n"
				 + "ContactTitle: "+getContactTitle()+"\n"
				 + "SuppCity: "+getA().getCity()+"\n"
				 + "SuppState: "+getA().getState()+"\n"
				 + "SuppPostalCode: "+getA().getPostalCode()+"\n"
				 + "SuppCountry: "+getA().getCountry()+"\n"
				 + "SuppJoinDate: "+getJoinDate()+"\n");
		
		System.out.println("Contact Numbers: ");
		for (int i = 0; i < contactNumber.size(); i++) {
			System.out.println("["+(i+1)+"] "+contactNumber.get(i));
		}
	}


	/**
	 * View current supplier's products
	 */
	private void viewProd() {
		// TODO Auto-generated method stub
		System.out.println("\n["+getCompany()+" PRODUCTS]");
		database.viewSupplierProducts(getSupplierID());
	}

	/**
	 * Add products to a category
	 */
	private void addProd() {
		// TODO Auto-generated method stub
		database.viewCategories();
		System.out.print("Category ID: ");
		int cname = in.nextInt();
		System.out.print("\nProduct Name: ");
		in.nextLine();
		String pname = in.nextLine();
		System.out.print("\nProduct Description: ");
		String pdesc = in.nextLine();
		System.out.print("\nQuantity/unit: ");
		int pquantity = in.nextInt();
		System.out.print("\nPrice/unit: ");
		int pprice = in.nextInt();
		database.addProduct(getSupplierID(),cname,pname,pdesc,pquantity,pprice);
	}

	/**
	 * Register a newly-added product, or edit stock
	 */
	private void addStock() {
		// TODO Auto-generated method stub
		/*Show the supplier's products with corresponding stocks*/
		
		System.out.println("[0]Register recently added product/Add product variation\n[1]Edit stock");
		int choice = in.nextInt();
		if (choice == 0) {
			database.viewDistinctSupplierProducts(getSupplierID());
			System.out.print("Select ProductID: ");
			int prodID = in.nextInt();
			database.printColors();
			System.out.print("Select ColorID: ");
			int colorID = in.nextInt();
			database.printSizes();
			System.out.print("Select SizeID: ");
			int sizeID = in.nextInt();
			System.out.print("Stock: ");
			int stocks = in.nextInt();
			System.out.println();
			database.registerStock(prodID,colorID,sizeID,stocks);
			database.viewSupplierProducts(getSupplierID());
		}
		else if (choice == 1) {
			database.displaySupplierStocks(getSupplierID());
			System.out.println("[PRODUCTID][PREVIOUS STOCK][COLORID][SIZEID][NEW STOCK]");
			int pID = in.nextInt();
			int previousstock = in.nextInt();
			int colorID = in.nextInt();
			int sizeID = in.nextInt();
			int newstock = in.nextInt();
			database.updateStockAt(pID,previousstock,colorID,sizeID,newstock);
			System.out.println("UPDATED STOCKS!\n");
			database.displaySupplierStocks(getSupplierID());
		}
	}


	/**
	 * Show all of the items owned by the current seller that has been included in an order
	 */
	private void salesReport() {
		// TODO Auto-generated method stub
			database.displayAllOrderDetailsPaidAndShipped(getSupplierID());	
	}

	/**
	 * Accomplish pending items requested by customers via order
	 */
	private void accomplishOrders() {
		// TODO Auto-generated method stub
			database.displayUnaccomplishedOrderDetails(getSupplierID());
			System.out.print("Enter `1` to accomplish orders, otherwise enter `0` : ");
			int choice = in.nextInt();
			if(choice == 1) {
				System.out.println("[ORDER ID][ORDERDETAIL ID]");
				int orderid = in.nextInt();
				int orderdetailid = in.nextInt();
				
				database.accomplishOrderdetail(orderid, orderdetailid);
			}
	}

	private void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		in.nextLine();
	}
	

	
	
	
}
