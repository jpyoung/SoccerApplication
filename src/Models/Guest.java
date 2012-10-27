package Models;



public class Guest {
	
	private int userType;
	private boolean loginChoice;
	private boolean signUpChoice;
	
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public void setLoginChoice(boolean loginChoice) {
		this.loginChoice = loginChoice;
	}
	public void setSignUpChoice(boolean signUpChoice) {
		this.signUpChoice = signUpChoice;
	}
	
	public boolean validateUserType(int userType) {
		if(userType == 1 || userType == 2 || userType == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getUserType() {
		return userType;
	}
	public boolean getLoginChoice() {
		return loginChoice;
	}
	public boolean getSignUpChoice() {
		return signUpChoice;
	}
	

}
