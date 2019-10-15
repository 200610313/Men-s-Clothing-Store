import java.util.ArrayList;

public class Supplier extends Person {
	protected int supplierID;//PK
	protected String userEmailAddress;//Used for logging in
	protected String userPassword;//Used for logging in
	protected String company;//Supplier belongs to a company
	protected String contactTitle;
	
	/*Sets own fields, and the fields of the parent class*/
	public Supplier(int sid, String semail, String up, String c, String fn, String mi, String ln, String ct, String city, String state, int postalc, String country, String jd) {
		supplierID = sid;
		userEmailAddress = semail;
		userPassword = up;
		company = c;
		fName = fn;
		mInit = mi;
		lName = ln;
		contactTitle = ct;
		a = new Address(city,state,postalc,country);
		joinDate = jd;
		contactNumber = new ArrayList<String>();
	}
	protected int getSupplierID() {
		return supplierID;
	}
	protected String getUserEmailAddress() {
		return userEmailAddress;
	}
	protected String getUserPassword() {
		return userPassword;
	}
	protected String getCompany() {
		return company;
	}
	protected String getContactTitle() {
		return contactTitle;
	}


	
}
