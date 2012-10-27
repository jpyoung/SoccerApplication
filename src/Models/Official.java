package Models;

import Waiver.UserForm;


public class Official extends User {

	private UserForm officalWaiver;
	
	public Official() {
		// TODO Auto-generated constructor stub
		super();
		this.officalWaiver = null;
	}
	
	public UserForm getOfficalWaiverObject() {
		return officalWaiver;
	}
	
}
