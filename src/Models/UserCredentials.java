package Models;

import helpers.InputHelper;

import java.io.Serializable;
import java.util.ArrayList;


public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> id = new ArrayList<Integer>();
	private ArrayList<String> userNames = new ArrayList<String>();
	private ArrayList<String> passwords = new ArrayList<String>();
	private ArrayList<Integer> userType = new ArrayList<Integer>();
	private ArrayList<User> userObject = new ArrayList<User>();
	
	
	public void setID(int id) {
		getIdArraylist().add(id);
	}
	
	public void setUserName(String userName) {
		getUserNamesArraylist().add(userName);
	}
	
	public void setPassword(String password) {
		getPasswordsArraylist().add(password);
	}
	
	public void setUserType(int userType) {
		getUserTypeArraylist().add(userType);
	}
	
	//their proposed method -- bad design - doesnt work
//	public void setUserObject() {
//		
//	}
	
	//my method below
	public void setUserObject(String userName, String password, int userType, User iUser) {
		setID(getIdArraylist().size());
		setUserName(userName);
		setPassword(password);
		setUserType(userType);
		getUserObjectArraylist().add(iUser);
	}
	
	public int getID(String userName, String password) {
		boolean finder1 = getUserNamesArraylist().contains(userName);
		boolean finder2 = getPasswordsArraylist().contains(password);
		int uIndex = -1;
		int pIndex = -2;
		int foundIndex = -1;
		
		if ( finder1 && finder2) {
			System.out.println("the user name and password do exist somewhere");
			uIndex = getUserNamesArraylist().indexOf(userName);
			pIndex = getPasswordsArraylist().indexOf(password);
			
			if ( uIndex == pIndex ) {
				foundIndex = uIndex;
			}
		} 
		//I made it so that it will search the arraylist. First check if user and pass exist in those arraylist
		//then check if those have correspond indexes
		//if so it will return there userID, else if it doesnt find it, it will return -1
		return foundIndex;
	}
	
	/***Start - Methods I added that not described in UML**/
	
	public void setPassword(int userIndex, String newPassword) {
		getPasswordsArraylist().set(userIndex, newPassword);
	}
	
	public ArrayList<String> getUserNamesArraylist() {
		return userNames;
	}
	public ArrayList<String> getPasswordsArraylist() {
		return passwords;
	}

	public ArrayList<Integer> getIdArraylist() {
		return id;
	}
	
	public ArrayList<Integer> getUserTypeArraylist() {
		return userType;
	}
	
	public ArrayList<User> getUserObjectArraylist() {
		return userObject;
	}
	


	/***End - Methods I added that not described in UML**/
	
	public String getUserName(int id) {
		String un = getUserNamesArraylist().get(id);
		return un;
	}
	public String getPassword(int id) {
		String pn = getPasswordsArraylist().get(id);
		return pn;
	}
	public int getUserType(int id) {
		int tt = getUserTypeArraylist().get(id);
		return tt;
	}
	public User getUserObject(int id) {
		return getUserObjectArraylist().get(id);
	}
	
	public static boolean validateUserName(String userName) {
		boolean r = usernameChecker(userName);
		if ( r ) {
			return true;
		} else {
			InputHelper.displayMessage("Invalid username.\nMust have a length between 6 and 12. example format: ja@gmail.com", "Register - Invalid userName");
			return false;
		}
	}
	
	private static boolean usernameChecker(String userName) {
		int length = userName.length();
		if (length >= 6 && length <= 12) {
			int indexOfAtSymbol = userName.indexOf("@");
			int indexOfDot = userName.lastIndexOf(".");
			if ( indexOfAtSymbol == -1 || indexOfDot == -1 || indexOfAtSymbol == 0) {
				return false;  // does not contain a @ or . or @ symbol is the first char in username
			} else {
				if (indexOfDot < indexOfAtSymbol) {
					return false; //making sure that the last index of the dot is not before the @ symbol
				} else {
					int distanceBetweenAtAndLastDot = indexOfDot - indexOfAtSymbol;
					if (distanceBetweenAtAndLastDot <= 1) {
						return false; // there needs to be atleast one char between @ and .   example jack@g.com
					} else {
						int distBetweenDotAndLastChar = (length - 1) - indexOfDot;
						if (distBetweenDotAndLastChar < 1) {
							return false;  //needs to be atleast 1 char after the last dot.  example jack@g.c
						} else {
							return true;
						}
					}
				}
			}
		} else {
			return false;
		}
	}
	
	//The below was described in the diagram, but we redid some design
//	public int validateUserName(String userName) {
//		
//		boolean isError = true;
//		
//		int length = userName.length();
//		boolean isValidLength = false;
//			if(length >= 6 && length <= 12) {
//				isValidLength = true;
//			}
//		
//		if( isValidLength) {
//			int indexOfAtSymbol = userName.indexOf("@");
//			int indexOfDot = userName.indexOf(".");
//			System.out.println("length of : " + length);
//			System.out.println("index of @: " + indexOfAtSymbol);
//			System.out.println("index of .  : " + indexOfDot);
//			if ( indexOfAtSymbol == -1 || indexOfDot == -1) {
//				System.out.println("sorry but you are missing the @ symbol");
//			} else {
//				System.out.println("Valid Email");
//				isError = true;
//			}
//		} else {
//			//sorry but needs to be atleast 6 and 12 in length
//			if(length < 6) {
//				//too short
//				System.out.println("Error: username is to short!");
//			} else if (length > 12) {
//				//to long
//				System.out.println("Error: username is to long!");
//			} else {
//				System.out.println("Error: something went wrong in username validation");
//			}
//		}
//		//string of alphanumeric characters + @ stirng of alphanumer characters + . + alpha characters
//		
//		
//		if(isError) {
//			//no there is no error
//			return 1;
//		} else {
//			//yes there is an error
//			return -1;
//		}
//		
//	}
//	private int validatePassword(String password) {
//		return 1;
//	}
//	public int validateLogin(int i) {
//		return 1;
//	}
//	

	//temp method for testing
	public void outputAllCredentials() {
		System.out.println("\n------------Output - UserTable Credentials-------------------");
		for (int i = 0; i < getUserNamesArraylist().size(); i++) {
			System.out.println("#" + i + "  :  userName: " + getUserNamesArraylist().get(i) + ",    password: "
			+ getPasswordsArraylist().get(i) + "    id: " + getIdArraylist().get(i) + "    userType: "
			+ getUserTypeArraylist().get(i) + "    user: " + getUserObjectArraylist().get(i)
			);
		}
		System.out.println("User Count: " + getUserNamesArraylist().size());
		System.out.println("-------------------------------------------------------------\n");
	}

	
	public void outputSystemUsersTable() {
		System.out.println("\n------------Output - UserTable Credentials-------------------");
		for (int i = 0; i < getUserNamesArraylist().size(); i++) {
			System.out.println("#" + i + "  :  userName: " + getUserNamesArraylist().get(i) + ",    password: "
			+ getPasswordsArraylist().get(i) + "    id: " + getIdArraylist().get(i) + "    userType: "
			+ getUserTypeArraylist().get(i) + "    user: " + (getUserObjectArraylist().get(i) == null ? "NULL" : getUserObjectArraylist().get(i).getClass().getName())
			);
		}
		System.out.println("User Count: " + getUserNamesArraylist().size());
		System.out.println("-------------------------------------------------------------\n");
	}
	
}
