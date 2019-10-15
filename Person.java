import java.util.ArrayList;

public class Person {
	protected String fName;
	protected String mInit;
	protected String lName;
	protected String joinDate;
	protected ArrayList<String> contactNumber;//A person can have > 1 contact numbers
	
	protected ArrayList<String> getContactNumber() {
		return contactNumber;
	}

	protected Address a;//Every person has an address
	
	
	protected String getfName() {
		return fName;
	}
	protected String getmInit() {
		return mInit;
	}
	protected String getlName() {
		return lName;
	}
	protected String getJoinDate() {
		return joinDate;
	}

	protected Address getA() {
		return a;
	}
	
	protected void setContactNumber(ArrayList<String> contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
