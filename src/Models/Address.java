package Models;

import java.io.Serializable;


/**
 * @author Jack Young
 *
 */
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	private String street;
	private String state;
	private String zip;
	private String city;
	
	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	public String getZip() { return zip; }
	public void setZip(String zip) { this.zip = zip; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	
	//Inserted this toString method for test.  It was not called for in the project guide though
	@Override
	public String toString() {
		return "Address [street=" + street + ", state=" + state + ", zip="
				+ zip + ", city=" + city + "]";
	}
	
	

}
