package Waiver;

import java.io.Serializable;


public class OfficialWaiver implements UserForm, Serializable {

	private boolean signFlag;
	private String name;
	private String description;
	private String signDate;
	
	public OfficialWaiver() {
		// TODO Auto-generated constructor stub
		
		this.signFlag = false;
		this.description = "The waiver you are about to sign will effectively surrender all rights and privileges outside of the scope of this tournament. All officials are responsible for presiding over the game from a neutral point of view, and making on the fly decisions that enforce the rules of the sport.";
		this.name = "n/a";
		this.signDate = "n/a";
		
		
	}
	
	@Override
	public void setSignFlag(boolean waiverFlag) {
		// TODO Auto-generated method stub
		this.signFlag = waiverFlag;
		
	}
	@Override
	public boolean getSignFlag() {
		// TODO Auto-generated method stub
		return signFlag;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getSignDate() {
		return signDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aString = "Name: " + getName() + ",\nDescription: " + getDescription() + ",\nSign Date: " + getSignDate();
		return aString;
	}
	
}
