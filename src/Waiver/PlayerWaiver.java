package Waiver;

import java.io.Serializable;


public class PlayerWaiver implements UserForm, Serializable {

	private static final long serialVersionUID = 1L;
	private boolean signFlag;
	private String name;
	private String description;
	private String signDate;
	
	public PlayerWaiver() {
		this.signFlag = false;
		this.description = "The waiver you are about to sign will effectively surrender all rights and privileges outside of the scope of this tournament. All officials are responsible for presiding over the game from a neutral point of view, and making on the fly decisions that enforce the rules of the sport.";
		this.name = "n/a";
		this.signDate = "n/a";
	}
	
	public void setSignFlag(boolean waiverFlag) {
		this.signFlag = waiverFlag;	
	}
	
	public boolean getSignFlag() {
		return signFlag;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getSignDate() {
		return signDate;
	}
	
	public String toString() {
		String aString = "Name: " + getName() + ",\nDescription: " + getDescription() + ",\nSign Date: " + getSignDate();
		return aString;
	}
}
