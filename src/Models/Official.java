package Models;

import java.io.Serializable;

import Waiver.OfficialWaiver;
import Waiver.UserForm;


public class Official extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserForm officalWaiver;
	
	public Official() {
		// TODO Auto-generated constructor stub
		super();
		this.officalWaiver = new OfficialWaiver();//updated 10/27 : Creating Official object auto create OfficialWaiver() 
	}
	
	public UserForm getOfficalWaiverObject() {
		return officalWaiver;
	}
	
}
