import helpers.InputHelper;
import helpers.OutputHelpers;
import helpers.Prompt;
import helpers.Prompter;

import javax.swing.JOptionPane;

import Models.Address;
import Models.Coach;
import Models.Official;
import Models.Player;
import Models.User;
import Models.UserCredentials;


/**
 * @author Jack Young
 * @date Nov 3, 2012
 */
public class RegisterView {
	
/*************************************************************************************************************/
/****************************START:  Register Section ********************************************************/
/*************************************************************************************************************/
	public static void registerView() {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Register Section.   Method Called: registerView()");
		registerNewUser();
	}

	public static void registerNewUser() {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Register Section.   Method Called: registerNewUser()");
		
		Prompt username;
		Prompt password;
		
		boolean ut = true;
		do {
			username = Prompter.question("Please enter your email address:", "Register - username");
			if (username.isDidPressCancel()) {
				ut = false;
			} else {
				if(!UserCredentials.validateUserName(username.getInputEntered())) {
					System.out.println("Invalid username");
					ut = true;
				} else {
					ut = false; //validate username and they did not press cancel
				}
			}
		} while (ut);
		
		if (!username.isDidPressCancel()) {
			
			boolean pt = true;
			do {
				password = Prompter.question("Please enter your password:", "Register - password");
				if (password.isDidPressCancel()) {
					pt = false;
				} else {
					if(!UserCredentials.validatePassword(password.getInputEntered())) {
						System.out.println("Invalid password");
						pt = true;
					} else {
						pt = false; //validate password and they did not press cancel
					}
				}
			} while (pt);
			
			
			if (!password.isDidPressCancel()) {
				
				String u = username.getInputEntered();
				String p = password.getInputEntered();
				int userType = dropDownListDialog();
				
				if (userType == -1) {
					System.out.println("You pressed the cancel");
					MainApplication.firstView(); //you pressed the cancel button on the dropdown list
				} else {
					String[] poss = {" ", "Official", "Coach", "Captain", "Player"};
					String subDataString = poss[userType];
					
					//if they selected captain or player, either way they are still considered to be players
					//thus their usertype is just three. 
					int setLogInUserType = userType;
					if (userType == 4) {
						setLogInUserType = 3;
					} 
					
					MainApplication.getController().getUc().setUserObject(u, p, setLogInUserType, promptForToEnterUserInfo(userType));
					
					String dataString = "Congratulations, your are now registered as a " + subDataString + ".";
					dataString += "\nYou may now login to your dashboard.";
					JOptionPane.showMessageDialog(null, dataString, "Registration Confirmation", JOptionPane.INFORMATION_MESSAGE);
					
					MainApplication.firstView();
				}
				
			} else {
				MainApplication.firstView(); //they pressed the cancel button on the password question.
			}
			
		} else {
			MainApplication.firstView(); //they pressed the cancel button on username question
		}
	
	}
	
	
	
	public static int dropDownListDialog() {
		Object[] poss = {"Official", "Coach", "Captain", "Player"};
		String a = (String)JOptionPane.showInputDialog(null, "Choose what you want to register as:", "Register - user type", JOptionPane.PLAIN_MESSAGE, null, poss, "Official");
		
		int v = -1;
		if (a == null) {
			InputHelper.displayMessage("You Pressed the cancel button, you are no being redirected to the main menu", "Cancel Button Pressed");
			return v;
		} else {
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

	}
	

	public static User promptForToEnterUserInfo(int type) {
		
		String firstName = null;
		do {
			firstName = InputHelper.promptString("Please enter in your first name:", "firstname", "Register - first name");	
			if(firstName == null) {
				InputHelper.displayMessage("Sorry, but you can not press the cancel or exit button. Please try again.", "Error");
			}
		} while (firstName == null);
		
		String lastName = null;
		do {
			lastName = InputHelper.promptString("Please enter in your last name:", "lastName", "Register - last name");
			if(lastName == null) {
				InputHelper.displayMessage("Sorry, but you can not press the cancel or exit button. Please try again.", "Error");
			}
		} while ( lastName == null);
		
		String phone = null;
		do {
			do {
				phone = InputHelper.promptString("Please enter in your phone number:", "phone number", "Register - phone number");
			} while (phone == null);
			if (phone.length() < 12) {
				InputHelper.displayMessage("Phone number must have a length of 12: xxx xxx xxxx", "Phone Input Error");
			}
		} while (phone.length() != 12);
		
		String street = null;
		do {
			street = InputHelper.promptString("Please enter in your street:", "street", "Register - street");
			if(street == null) {
				InputHelper.displayMessage("Sorry, but you can not press the cancel or exit button. Please try again.", "Error");
			}
		} while (street == null);
		
		String city = null;
		do {
			city = InputHelper.promptString("Please enter in your city:", "city", "Register - city");
			if(city == null) {
				InputHelper.displayMessage("Sorry, but you can not press the cancel or exit button. Please try again.", "Error");
			}
		} while (city == null);
		
		String state = null;
		do {
			state = InputHelper.promptString("Please enter in your state:", "state", "Register - state");
			if(state == null) {
				InputHelper.displayMessage("Sorry, but you can not press the cancel or exit button. Please try again.", "Error");
			}
		} while (state == null);
		
		String zip;
		do {
			do {
				zip = InputHelper.promptString("Please enter in your zip code:", "zip code", "Register - zip code");
			} while (zip == null);
			if (zip.length() < 5) {
				InputHelper.displayMessage("Sorry, but the zip code must have a length of 5. Please try again", "Error");
			}
		} while(zip.length() != 5);
		
		Address nAddress = new Address();
		nAddress.setStreet(street);
		nAddress.setState(state);
		nAddress.setZip(zip);
		nAddress.setCity(city);
		
		User nUser = null;
		if (type == 1) {
			nUser = new Official(); //official
		} else if (type == 2) {
			nUser = new Coach(); //coach
		} else if (type == 3) {
			Player p = new Player(); // player captain
			p.setCaptain(true);
			nUser = p;
		} else {
			Player p = new Player(); //player
			p.setCaptain(false);
			nUser = p;
		}
		
		nUser.setFirstName(firstName);
		nUser.setLastName(lastName);
		nUser.setPhone(phone);
		nUser.setAddress(nAddress);
		
		JOptionPane.showMessageDialog(null, "By registering as a user, you have agreed to sign the waiver.\nSign Tournament Waiver agree refuse goes here");
		
		return nUser;
	}

/*************************************************************************************************************/
/****************************END:  Register Section **********************************************************/
/*************************************************************************************************************/
		
}
