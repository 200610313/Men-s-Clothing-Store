public class Address {
	String city;
	String state;
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public String getCountry() {
		return country;
	}
	int postalCode;
	String country;
	public Address(String city, String state, int postalCode, String country) {
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	
}
