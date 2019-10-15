public class Admin {
	protected int adminID;//PK
	protected String userEmailAddress;//Used for logging in
	protected String userPassword;//Used for logging in
	
	public Admin(int adminID, String userEmailAddress, String userPassword) {
		super();
		this.adminID = adminID;
		this.userEmailAddress = userEmailAddress;
		this.userPassword = userPassword;
	}

	protected int getAdminID() {
		return adminID;
	}

	protected String getUserEmailAddress() {
		return userEmailAddress;
	}

	protected String getUserPassword() {
		return userPassword;
	}	
}
