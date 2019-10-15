
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author angelu
 * When customer has logged in, object of CustomerSession is created.
 * Customer attributes are inherited that represents the basic information
 * of the current customer.
 */
public class CustomerSession extends Customer implements Menuable, ProfileViewable {
	private DBConnect database;
	private Scanner in;
	
	public CustomerSession(int cid, String fn, String mi, String ln, long cn, int cem, int cey, String cCity,
			String cState, int CPostalCode, String CCountry, String sCity, String sState, int sPostalCode,
			String sCountry, String jd, String email, String password,DBConnect database, ArrayList<String> connums ) {
		super(cid, fn, mi, ln, cn, cem, cey, cCity, cState, CPostalCode, CCountry, sCity, sState, sPostalCode, sCountry, jd,
				email, password);
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
			System.out.print("\n[CUSTOMER PANEL]\n"
					+ "[0]Add to order\n"
					+ "[1]Pay Order\n"
					+ "[2]Order History\n" 
					+ "[3]View Current Order Details\n"
					+ "[4]View My Profile\n"
					+ "Choice: ");
			choice = in.nextInt();
			System.out.println("");

			if (choice == 0) {
				addtoOrder();
			} else if (choice == 1) {
				payOrder();
			} else if (choice == 2) {
				orderHistory();
			} else if (choice == 3) {
				viewOrderDetails();
			} else if (choice == 4) {
				viewMyProfile();
			}
			promptEnterKey();
		}
	}

	@Override
	public void viewMyProfile() {
		// TODO Auto-generated method stub
		System.out.print("\nCustomerID: "+getCustomerID()+"\n"
				 + "CustEmail: "+getUserEmailAddress()+"\n"
				 + "CustPassword: "+getUserPassword()+"\n"
				 + "ContactFName: "+getfName()+"\n"
				 + "ContactMinit: "+getmInit()+"\n"
				 + "ContactLName: "+getlName()+"\n"
				 + "CardNum: "+getCardNum()+"\n"
				 + "CardExpMo: "+getCardExpMo()+"\n"
				 + "CardExpYr:"+getCardExpYr()+"\n"
				 + "CustCity: "+getA().getCity()+"\n"
				 + "CustState: "+getA().getState()+"\n"
				 + "CustPostalCode: "+getA().getPostalCode()+"\n"
				 + "CustCountry: "+getShippingAddress().getCountry()+"\n"
				 + "CustShipCity: "+getShippingAddress().getCity()+"\n"
				 + "CustShipState: "+getShippingAddress().getState()+"\n"
				 + "CustShipPostalCode: "+getShippingAddress().getPostalCode()+"\n"
				 + "CustShipCountry: "+getShippingAddress().getCountry()+"\n"
				 + "CustJoinDate: "+getJoinDate()+"\n");
		
		System.out.println("Contact Numbers: ");
		for (int i = 0; i < contactNumber.size(); i++) {
			System.out.println("["+(i+1)+"] "+contactNumber.get(i));
		}
	}

	/**
	 * The customer may add one or more items in his order
	 */
	private void addtoOrder() {
		// TODO Auto-generated method stub
		
		// If first time user = havent ordered yet, insert a record on order table
		
		  if ((database.userHasOrdered(getCustomerID())) == false) {//SELECT
		  //MAX(OrderID) AS id FROM `order` if this returns null
		  database.placeNewOrder(getCustomerID()); 
		  }
		 
		
		//First, let user choose a category
		database.viewValidCategories();
		
		System.out.print("CategoryID: ");
		int catID = in.nextInt();
		
		//..list all products available in that category 
		database.viewProductsInCategoryID(catID);
		//then let user choose a product from the category
		System.out.println("\nProductID: ");
		int prodID = in.nextInt();
		//Once chosen, let user choose which variation first: size or color?
		System.out.println("Enter '0' to select size first over color, else, enter '1': ");
		int firstIs = in.nextInt();
		
		int sizeID = 0,colorID = 0,stocksAvailable = 0, quantity = 0;
		
		if (firstIs == 0) {
			database.viewAvailableSizesFor(prodID);
			System.out.println("Size ID: ");
			sizeID = in.nextInt();
			
			database.viewAvailableColorsFor(prodID);
			System.out.println("Color ID: ");
			colorID = in.nextInt();
		}
		
		else if (firstIs == 1) {
			database.viewAvailableColorsFor(prodID);
			System.out.println("Color ID: ");
			colorID = in.nextInt();
			
			database.viewAvailableSizesFor(prodID);
			System.out.println("Size ID: ");
			sizeID = in.nextInt();
		}
			boolean validQuantity = false;
			
			while(validQuantity == false)
			{
				stocksAvailable = database.viewAvailableStockFor(prodID,sizeID,colorID);
				System.out.println("Enter quantity to order: ");
				quantity = in.nextInt();
				if(quantity <= stocksAvailable) {
					break;
				}
			}
			database.decrementStockAt(prodID,sizeID,colorID,quantity);
			/*The details that we need to fill up an order detail*/
			//OrderdetailID - auto increment
			//ProductId
			int supplierID = database.getSupplierIDOf(prodID);
			Double priceAtOrder = database.getPriceOf(prodID);
			//Quantity
			Double subTotal = priceAtOrder*quantity;
			int orderID = database.getLatestOrderIDof(getCustomerID());
			//size
			//color
			//category ID\
			
			String colorLabel = database.getColorLabel(colorID);
			String sizeLabel = database.getSizeLabel(sizeID);
			String productLabel = database.getprodLabel(prodID);
			String supplierLabel = database.getCompanyName(supplierID);
			database.fillNewOrderDetail(prodID,supplierID,priceAtOrder,subTotal,orderID,sizeLabel,colorLabel,catID,quantity,productLabel,supplierLabel);
	}

	/**
	 * A customer pays for an order containing at least one item
	 */
	private void payOrder() {
		double totalOfMostRecentOrder = 0;
		// TODO Auto-generated method stub
		//display current order details + subtotal, then increment
		
		totalOfMostRecentOrder = database.displayTotalOfRecentUnpaidOrder(getCustomerID());
		System.out.print("Pay now?[0=No,1=Yes]: ");
		
		int toPay = in.nextInt();
		if (toPay == 0) {
			return;
		}
		else if (toPay == 1) {
			database.payForRecentUnpaidOrder(getCustomerID(), totalOfMostRecentOrder);
			System.out.println("Successfully paid with credit card!");
			//then increment OrderID
			database.placeNewOrder(getCustomerID()); 
		}
	}

	/**
	 * The customer can view his order history, and all of the items in an order
	 */
	private void orderHistory() {
		// TODO Auto-generated method stub
		database.displayAllPaidOrders(getCustomerID());
		System.out.print("\nView orderdetails?[0=No,1=Yes]: ");
		int toview = in.nextInt();
		if (toview == 1) {
			System.out.println("Enter order number: ");
			int orderNum = in.nextInt();
			database.getDisplayOrderDetailsAt(orderNum);
		}
	}

	/**
	 * The customer can review the items added in the current order
	 */
	private void viewOrderDetails() {
		// TODO Auto-generated method stub
		database.displayOrderDetailsOfRecentOrder(getCustomerID());
		double total = database.displayTotalOfRecentUnpaidOrder(getCustomerID());
	}
	
	private void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		in.nextLine();
	}


}
