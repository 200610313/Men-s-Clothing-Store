import java.util.ArrayList;

public class Shipper {
	int shipperID;
	String company;//Supplier belongs to a company
	String shipemail;
	String shippassword;
	ArrayList<String> contactNumber;//Can have > 1 contact numbers
	
	public Shipper(int shipperID,String company, String shipemail, String shippassword) {
		super();
		this.shipperID = shipperID;
		this.company = company;
		this.shipemail = shipemail;
		this.shippassword = shippassword;
		contactNumber = new ArrayList<String>();
	}
	

	public int getShipperID() {
		return shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getShipemail() {
		return shipemail;
	}

	public void setShipemail(String shipemail) {
		this.shipemail = shipemail;
	}

	public String getShippassword() {
		return shippassword;
	}

	public void setShippassword(String shippassword) {
		this.shippassword = shippassword;
	}

	public ArrayList<String> getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(ArrayList<String> contactNumber) {
		this.contactNumber = contactNumber;
	}


	
	
}
