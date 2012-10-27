package Waiver;


public interface UserForm {
	
	public void setSignFlag(boolean waiverFlag);
	public boolean getSignFlag();
	public String getName();
	public String getDescription();
	public String getSignDate();
	public String toString();
	
}
