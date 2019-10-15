import java.util.ArrayList;

public class Customer extends Person {

	protected int customerID;//PK
	protected String userEmailAddress;//Used for logging in
	protected String userPassword;//Used for logging in
	
	protected long cardNum;//9
	protected int cardExpMo;//10
	protected int cardExpYr;//11
	
	protected Address shippingAddress;//Another address for shipping 

	public Customer(int cid, String fn, String mi, String ln, long cn, int cem, int cey, String cCity, String cState, int CPostalCode, String CCountry, String sCity, String sState, int sPostalCode, String sCountry, String jd, String email, String password) {
		customerID = cid;
		fName = fn;
		mInit = mi;
		lName = ln;
		cardNum = cn;
		cardExpMo = cem;
		cardExpYr = cey;
		a = new Address(cCity,cState,CPostalCode,CCountry);
		shippingAddress = new Address(sCity,sState,sPostalCode,sCountry);
		joinDate = jd;
		userEmailAddress = email;
		userPassword = password;
		contactNumber = new ArrayList<String>();
	}	
	protected int getCustomerID() {
		return customerID;
	}

	protected String getUserEmailAddress() {
		return userEmailAddress;
	}

	protected String getUserPassword() {
		return userPassword;
	}

	protected long getCardNum() {
		return cardNum;
	}

	protected int getCardExpMo() {
		return cardExpMo;
	}

	protected int getCardExpYr() {
		return cardExpYr;
	}

	protected Address getShippingAddress() {
		return shippingAddress;
	}
}
