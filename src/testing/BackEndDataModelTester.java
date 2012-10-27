package testing;

import helpers.InputHelper;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Models.Address;
import Models.Player;
import Models.User;
import Models.UserCredentials;

public class BackEndDataModelTester {
	public static InputHelper ih = new InputHelper();

	public static void main(String args[]) {
		
		//playerDashboard();
		//View.firstView();
		
		
		testAddressClass(); //testing the address object
		
			tb();
		
		testUserClass(); //testing the user object
		
			tb();
			
		testUserCredentialsClass(); // testing the UserCredentials Class - where user is null
		
			tb();
			
		testPlayerClass(); //brief test for the Player Class.
		
			tb();
		
		testWithUserForUserCredentialsClass(); // testing the usercredential class - with given user. And username & password ID lookup
		
		
	}
	
	public static void demo() {
		op("--------Demo method------\n");
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> password = new ArrayList<String>();
		
		
		user.add("jyoung");
		user.add("rpy");
		
		password.add("greatness");
		password.add("122686");
		
		
		System.out.println("User array count: " + user.size());
		for (int i = 0; i < user.size(); i++) {
			System.out.println(user.get(i));
		}
		
		System.out.println("password array count: " + password.size());
		for (int i = 0; i < password.size(); i++) {
			System.out.println(password.get(i));
		}
		
		System.out.println("\ntable view style---- ");
		
		for (int i = 0; i < user.size(); i++) {
			System.out.println(user.get(i) + "    " + password.get(i));
		}
		
		
		//checking section
		boolean finder1 = user.contains("jyoung");
		System.out.println("Does user list contain jyoung: " + finder1);
		boolean finder2 = user.contains("Jyoung");
		System.out.println("Does user list contain Jyoung: " + finder2);
		
		int index = -1;
		if (finder2) {
			index = user.indexOf("jyoung");
		}
		System.out.println("The index of jyoung is : " + index);
		
		
	}
	
	
/********************************************************************/
/******************Sample Testing of Model Classes*******************/
/********************************************************************/
	
	//test section 1 - address class
	public static void testAddressClass() {
		Address a = new Address();
		a.setStreet("5209 Rosalie Ridge Drive");
		a.setState("VA");
		a.setZip("20120");
		a.setCity("Centreville");
		System.out.println(a);
	}
	
	//test section 2 - user class
	public static Address createAddress() {
		Address a = new Address();
		a.setStreet("5209 Rosalie Ridge Drive");
		a.setState("VA");
		a.setZip("20120");
		a.setCity("Centreville");
		return a;
	}
	
	public static void testUserClass() {
		User u = new User();
		u.setFirstName("Jack");
		u.setLastName("Young");
		u.setPhone("7039680264");
		u.setAddress(createAddress());
		System.out.println(u);
	}
	
	
	//test section 3 - UserCredentials class
	public static void testUserCredentialsClass() {
		UserCredentials uc = new UserCredentials();
		
		//these have no users associated with these people
		uc.setUserObject("jyoung", "greatness", 1, null);
		uc.setUserObject("cngan", "122686", 2, null);
		uc.setUserObject("mlka", "ccw1", 1, null);
		
		System.out.println("------Test User Credentials Class --------");
		uc.outputAllCredentials();
	}
	
	public static void testWithUserForUserCredentialsClass() {
		
		TestDataLoader td = new TestDataLoader();
		
		UserCredentials uc = new UserCredentials();
		uc.setUserObject("jyoung", "greatness", 1, null);
		uc.setUserObject("cngan", "122686", 2, null);
		uc.setUserObject("mlka", "ccw1", 1, td.getUser1());
		
		System.out.println("------Test With User For User Credentials Class --------");
		uc.outputAllCredentials();
		
		tb();
		System.out.println("simulate add new user");
		uc.setUserObject("alxi", "niccer", 3, td.getUser2());
		uc.outputAllCredentials();
		
		tb();
		System.out.println("simulate find user by password and username new user");
		System.out.println("cngan 122686 - id found: " + uc.getID("cngan", "1226986"));
		
		//sample login
		do {
			
			
			String u = ih.promptString("enter in username", "adsfd", "Login - username");
			String p = ih.promptString("enter in password", "adsfd", "Login - password");
			
			int loginResult = uc.getID(u, p);
			if (loginResult != -1) {
				System.out.println("id found: " + loginResult);
				
				String displayString = "Person Info";
				displayString += "\n\nUser Name: " + uc.getUserName(loginResult);
				displayString += "\nPassword: " + uc.getPassword(loginResult);
				displayString += "\nUser Object: " + uc.getUserObject(loginResult);
				JOptionPane.showMessageDialog(null, displayString);
				
				
			} else {
				System.out.println("No id found: " + loginResult);
			}
			
			
			
		} while (JOptionPane.showConfirmDialog(null, "Try some more?", "Testing", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	
		registerNewUser(uc);
	}
	
	//test section 4 - Player class stuff
	public static void testPlayerClass() {
		TestDataLoader td = new TestDataLoader();
		
		Player player = new Player();
		player.setFirstName("Corey");
		player.setLastName("Fortier");
		player.setAddress(td.createAddress1());
		player.setCaptain(true);
		player.setPhone("3452345243523");
		
		System.out.println("-------Player Test Method Data------");
		System.out.println(player);
		
		viewPlayerProfile(player);
	}
	
	//register section
	public static void registerNewUser(UserCredentials uc) {
		String username = InputHelper.promptString("enter in what you what your username to be", "username", "Register - username");
		String password = InputHelper.promptString("enter in what you want your password to be", "password", "Register - password");
		int userType = dropDownListDialog();
		
	
		uc.setUserObject(username, password, userType, promptForToEnterUserInfo());
		
		
		System.out.println("\n\nNew User List info----------------");
		uc.outputAllCredentials();
		
	}
	
	
	public static int dropDownListDialog() {
		Object[] poss = {"Official", "Coach", "Captain", "Player"};
		String a = (String)JOptionPane.showInputDialog(null, "Choose what you want to register as:", "Register - user type", JOptionPane.PLAIN_MESSAGE, null, poss, "Official");
		
		System.out.println(a);
		int v = -1;
		if(a.equals("Official")) {
			v = 1;
		} else if (a.equals("Coach")){
			v = 2;
		} else if (a.equals("Captain")){
			v = 3;
		} else if (a.equals("Player")) {
			v = 4;
		} else {
			System.out.println("Error, in the dropDownListDialog");
		}
		return v;
	}
	
	public static User promptForToEnterUserInfo() {
		String firstName = InputHelper.promptString("enter in your first name", "firstname", "User-Creator");
		String lastName = InputHelper.promptString("enter in your last name", "lastName", "User-Creator");
		String phone = InputHelper.promptString("enter in your phone number", "phone number", "User Creator");
		String street = InputHelper.promptString("enter in your street", "street", "User Creator");
		String state = InputHelper.promptString("enter in your state", "state", "User Creator");
		String zip = InputHelper.promptString("enter in your zip code", "zip code", "User Creator");
		String city = InputHelper.promptString("enter in your city", "city", "User Creator");
		
		Address nAddress = new Address();
		nAddress.setStreet(street);
		nAddress.setState(state);
		nAddress.setZip(zip);
		nAddress.setCity(city);
		
		User nUser = new User();
		nUser.setFirstName(firstName);
		nUser.setLastName(lastName);
		nUser.setPhone(phone);
		
		nUser.setAddress(nAddress);
	
		return nUser;
	}
	
	
	
	
	//sample player profile view
	public static void viewPlayerProfile(User p) {
		
		String display = "";
			display += "\nPlayer Name: " + p.getFirstName() + " " + p.getLastName();
			
		
		
		JOptionPane.showMessageDialog(null, display);
	}
	
	
	
	/********Player Dashboard*******/
	//below is a crude version of the playerDashboard menu view
	public static void playerDashboard() {
		
		String playerMenuChoices = "\n1. Edit Profile\n2. View Team Profiles\n3. Invitations/Notifications\n4. Save and Logout";	
		
		do {
			String input = InputHelper.promptStringMenuOptions(playerMenuChoices, "menu options", "Dashboard - Player", new int[]{1, 2, 3, 4});
			int choosenOption = Integer.parseInt(input);
			if(choosenOption == 1) {
				//edit profile
				System.out.println("action: edit Profile");
			} else if (choosenOption == 2) {
				//View team profiles
				System.out.println("action: View Team Profiles");
			} else if (choosenOption == 3) {
				//Invitations/Notifications
				System.out.println("action: Invitations/Notifications");
			} else if (choosenOption == 4) {
				//Save and Logout
				System.out.println("action: Save and Logout");
			} else {
				System.out.println("Error, in the Player Dashboard menu choosen option");
			}
			
			
		} while (JOptionPane.showConfirmDialog(null, "More IT Exams?", "Take-Home Assignment 5", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	
	}
	
	
	
	
	//method is just a shorter way of calling system.out.println
	public static void op (String a) {
		System.out.println(a);
	}
	public static void op(int a) {
		System.out.println(a);
	}
	public static void op(double a) {
		System.out.println(a);
	}
	//method for output shorter - and giving it a title
	public static void opt(String a, String title) {
		System.out.println("----------------------");
		System.out.println("Var name: " + title);
		System.out.println("data: " + a);
		System.out.println("----------------------");
	}
	
	public static void tb() {
		System.out.println("--------------------------------");
	}
	
}
