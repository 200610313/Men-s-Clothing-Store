import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author angelu
 * When shipper has logged in, object of ShipperSession is created.
 * Shipper attributes are inherited that represents the basic information
 * of the current shipper.
 */
public class ShipperSession extends Shipper implements Menuable,ProfileViewable {
	private DBConnect database;
	private Scanner in;
	public ShipperSession(int shipperId ,String company, String shipemail, String shippassword,  DBConnect db, ArrayList<String> cn) {
		super(shipperId, company, shipemail, shippassword);
		// TODO Auto-generated constructor stub
		contactNumber = cn;
		this.database = db;
		this.in = new Scanner(System.in);
		view();
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		int choice;
		while(true) {
			System.out.print("\n[SHIPPER PANEL]\n"
					+ "[0]Ship Completed Orders\n"
					+ "[1]View My Profile\n"
					+ "Choice: ");
			choice = in.nextInt();
			System.out.println("");
			if (choice == 0) {
				ship();
			}
			else if (choice == 1) {
				viewMyProfile();
			}
			promptEnterKey();
		}
	}
	@Override
	public void viewMyProfile() {
		// TODO Auto-generated method stub
		System.out.println(""
				+ "\nShipperID: "+getShipperID()
				+ "\nShipperCompanyName: "+getCompany()
				+ "\nShipperEmail: "+getShipemail()
				+ "\nShipperPassword: "+getShippassword()
				+ "\nContactNumbers: ");
		for (int i = 0; i < getContactNumber().size(); i++) {
			System.out.println("["+(i+1)+"]"+getContactNumber().get(i));
		}
	}

/**
 * Shipper may ship orders where all order items are accomplished by
 * the respective sellers
 */
	private void ship() {
		// TODO Auto-generated method stub

		database.viewOrdersAssignedTo(getShipperID());
	
		System.out.println("Enter `1` to ship, `0` otherwise: ");
		int choice = in.nextInt();
		
		if(choice == 1) {
			System.out.println("ORDER ID: ");
			int orderid = in.nextInt();
			
			//fillout shipdate and receiveddate
			database.shipOrder(orderid);
		}
		

	}
	private void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		in.nextLine();
	}
}
