import helpers.InputHelper;
import helpers.Prompt;
import helpers.Prompter;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Models.Coach;
import Models.Player;
import Models.Team;
import Models.User;
import controller.Controller;

//Jack Young
//Created On: 10/25/2012
public class MainApplication {
	
	public static Controller controller = new Controller();
	public static Controller getController(){
		return controller;
	}
	

	
	public static void main(String args[]) {
		System.out.println("MainApplication class - Main Method launch");
		
		//load and output the initial  data
		getController().loadInitialData();
		
		
		//temp data
		Player p = (Player)getController().getUc().getUserObject(1);
		Coach cc = (Coach)getController().getUc().getUserObject(3);
		cc.getTeam().getRoster().addPlayerName(p);
		p.setTeam(cc.getTeam());
		Player p2 = (Player)getController().getUc().getUserObject(2);
		cc.getTeam().getRoster().addPlayerName(p2);
		p2.setTeam(cc.getTeam());
	
		//end of temp data
		
	
		//make the call for the first view 
		firstView();
		

		
		//output all userCreditential data on close
		System.out.println("--Data on Application Close---");
		getController().getUc().outputAllCredentials();
		
	}
	
/*************************************************************************************************************/
/****************************START:  firstView()**************************************************************/
/*************************************************************************************************************/
	
	//This is the first view the user will see when they run the application
	//Welcome to Soccer Tournament Management - user can login or register
	public static void firstView() {

		System.out.println(timeStamp() + "- SECTION: firstView.   Method Called: firstView()");
		
		Object[] options = {"Login", "Register" };
		int n = JOptionPane.showOptionDialog(null,
				"Welcome to Soccer Tournament Management",
				"Home", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		
		if(n == 1 ) {
			// n == 1, means the user selected the register button
			System.out.println(timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected register button");
			RegisterView.registerView();
		} else if (n == 0 ) { 
			// n == 0, means the user selected the login button
			System.out.println(timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected login button");
			loginView();
		} else {
			// n is equal to -1
			System.out.println(timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected exit red button");
			JOptionPane.showMessageDialog(null, "Good Bye - You clicked the exit button");
		}
	
	}
		
/*************************************************************************************************************/
/****************************END:  firstView()****************************************************************/
/*************************************************************************************************************/
	
	
/*************************************************************************************************************/
/****************************START:  Login In ****************************************************************/
/*************************************************************************************************************/
	public static void loginView() {
		System.out.println(timeStamp() + "- SECTION: Login In.   Method Called: logingView()");
		
		userLoginAction();
		
	}
	
	public static void userLoginAction() {
		System.out.println(timeStamp() + "- SECTION: Login In.   Method Called: userLoginAction()");
		
		Prompt userName;
		Prompt iPassword;
		
//		do {
//			userName = promptMessage("Please enter your email/username", "Login - username");
//		} while (userName.isEmpty());
		userName = Prompter.question("Please enterr your email/username", "Login - username");
		
		//checking to see if they pressed the cancel button on username input
		if (!userName.isDidPressCancel()) {
			do {		
				iPassword = promptMessage("Please enter your password", "Login - password");	
			} while (iPassword.isEmpty());
			
			//checking to see if they pressed the cancel button on the password input 
			if (!iPassword.isDidPressCancel()) {
				System.out.println("\n\nCongrats, You have entered username: " + userName.getInputEntered() + " and password: " + iPassword.getInputEntered());
				//validate their info here then if it checks out send them to this Dashboard
				
				String u = userName.getInputEntered();
				String p = iPassword.getInputEntered();
				
				int loginResult = getController().getUc().getID(u, p);
				if (loginResult != -1) {
					System.out.println("id found: " + loginResult);
					
					dashBoards(loginResult);
					
				} else {
					JOptionPane.showMessageDialog(null, "Sorry! The enter credentials do not match our records");
					System.out.println("No id found: " + loginResult);
					firstView();
				}
				
			
			} else {
				//they pressed the cancel button in the password box, go back to firstview()
				firstView();
			}
			
		} else {
			//they pressed the cancel button in the username input prompt
			//go back to first view
			firstView();
		}			
	}
	
	public static Prompt promptMessage(String displayMessage, String title) {
		String input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
		   Prompt p = null;
		   if ( input == null) {
			   p = new Prompt(null, true);
			   System.out.println("You Pressed Cancel");
		   } else {
			   p = new Prompt(input, false);
			   System.out.println("enter: " + input);
		   }
		   System.out.println("sss: " + p);
		   return p;
	}

/*************************************************************************************************************/
/****************************END:  Login In ******************************************************************/
/*************************************************************************************************************/
	
	
	

	
	
/*************************************************************************************************************/
/****************************START:  Dashboards **************************************************************/
/*************************************************************************************************************/
	public static void dashBoards(int usersIndex) {
		System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()");
	
		String displayString = "Person Info";
		displayString += "\n\nUser Name: " + getController().getUc().getUserName(usersIndex);
		displayString += "\nPassword: " + getController().getUc().getPassword(usersIndex);
		displayString += "\nUser Object: " + getController().getUc().getUserObject(usersIndex);
		JOptionPane.showMessageDialog(null, displayString);
		
		//Here will go a if tree to decide whether the user that just loged in is a coach
		//player or offical.  From which it will call one of the three below methods to carry out that function
		int tempType = getController().getUc().getUserType(usersIndex);
		
		if (tempType == 1) {
			//usertype is offical
			System.out.println("Logged In AS:  Official");
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call officialsDashBoardView() method");
			officialsDashBoardView(usersIndex);
		} else if (tempType == 2) {
			//usertype is coach 
			System.out.println("Logged In AS:  Coach");
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call coachesDashBoardView() method");
			coachesDashBoardView(usersIndex);
		} else if (tempType == 3) {
			//usertype is a player
			System.out.println("Logged In AS:  Player");
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call playersDashBoardView() method");
			playersDashBoardView(usersIndex);
		} else {
			System.out.println("Error, in the dashboard method if three. Unknown usertype");
		}
		
	}
	
	
	//view for the officials dash board view
	public static void officialsDashBoardView(int usersIndex) {
		System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: officialsDashBoardView()");
		//denotations from uses cases
		// can save, can view all teams, can edit user profile
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Save and Logout";
		String titleString = "Dashboard - Official - " + getController().getUc().getUserName(usersIndex);
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3});
		
		int c = Integer.parseInt(option);
		
		if (c == 1) {
			//go to edit profile
			editProfileView(usersIndex);
		} else if (c == 2) {
			//go to view teams profiles
			
		} else if (c == 3) {
			//go to save and log out
			firstView(); // this will bring the user back to the first view
		} else {
			//error
			
		}
		
	}
	
	
	//View for the coaches dash board view
	public static void coachesDashBoardView(int usersIndex) {
		System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()");
		
		//denotations form the uses cases
		// can create team, manage team (edit team, view team info, play league fees), edit user profiel, save
		
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Manage or Create Team\n4. Save and Logout";
		String titleString = "Dashboard - Coach - " + getController().getUc().getUserName(usersIndex);
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3, 4});
	
		int c = Integer.parseInt(option);
		
		if (c == 1) {
			//go to edit profile
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call editProfileView() method");
			editProfileView(usersIndex);
		} else if (c == 2) {
			//go to view teams profiles
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call viewTeamProfiles() method");
			viewTeamProfiles(usersIndex);
		} else if (c == 3) {
			//go to manage or create team
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call manageTeamView() method  in ManageTeamView class");
			ManageTeamView.manageTeamView(usersIndex);
		} else if (c == 4) {
			//go to save and log out
			firstView(); // this will bring the user back to the first view
		} else {
			//error
			
		}
		
	}
	
	//view for the players dashboard view
	public static void playersDashBoardView(int usersIndex) {
		//dentaitons from the uses cases
		// player can edit urse profiel, view all teams, join a team (sign up as captain), and save
		System.out.println("\n\nPlayers Dash Board loaded");
		
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Invitations and Notifications\n4. Save and Logout";
		String titleString = "Dashboard - Player - " + getController().getUc().getUserName(usersIndex);
		
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3, 4});
		
		int c = Integer.parseInt(option);
		
		if (c == 1) {
			//go to edit profile
			editProfileView(usersIndex);
		} else if (c == 2) {
			//go to view teams profiles
			viewTeamProfiles(usersIndex);
		} else if (c == 3) {
			//go to invitations and notifications
			
		} else if (c == 4) {
			//go to save and log out
			firstView(); // this will bring the user back to the first view
		} else {
			//error
			
		}
		
		
	}
/*************************************************************************************************************/
/****************************END:  Dashboards ****************************************************************/
/*************************************************************************************************************/
	


	
/***************************************************************************************************/
/*******************************Start: View Team Profiles ******************************************/
	
	@SuppressWarnings("unused")
	public static void viewTeamProfiles(int usersIndex) {
		String data = "Choose team to view:\n";		
		data += Team.getAllTeams();
		
		int ln = Controller.getTeam().size();
		
		//Taking the number of teams size, then creating a array of ints that corresponds to the option that they can select from
		int[] tempA = new int[ln + 1];
		ArrayList<Integer> tempB = new ArrayList<Integer>();
		for (int y = 0; y < tempA.length; y++) {
			tempA[y] = (y + 1);
			tempB.add(y + 1);
		}
		
		//setting the exit condition number for a dynamically generated list of teams
		int exitNum = tempA[ln];
		data += "\n" + tempA.length + ". Exit";
		
		String option = InputHelper.promptStringMenuOptions(data, "View Team", "View Team", tempA);
		
		if (option == null) {
			//they pressed cancel button
			System.out.println("Pressed the cancel button on editMenuPhoneNumber");
		} else if (option != null) {
			//they didn't pressed the cancel button
			int c = Integer.parseInt(option);
			
			if (exitNum == c) {
				//selected the number that corresponds to the exit condition, which brings the 
				//user back the their respective dashboard.
				int ti = usersIndex;
				
				if(getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Coach")) {
					//its of coach type
					//JOptionPane.showMessageDialog(null, "Its of coach type");
					coachesDashBoardView(usersIndex);
				} else if (getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Player")) {
					//its of player type
					//JOptionPane.showMessageDialog(null, "Its of Player type");
					playersDashBoardView(usersIndex);
				} else if (getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Official")) {
					//its of Official type
					//JOptionPane.showMessageDialog(null, "Its of Official type");
					officialsDashBoardView(usersIndex);
				}
				else {
					JOptionPane.showMessageDialog(null, "Not of coach type");
				}
				
			} else {
				//Calling the method to view a more detailed look at the selected team
				detailedTeamView(Controller.getTeam().get(c - 1), usersIndex);
//				if (tempB.contains(c - 1)) {
//					System.out.println("It contains it ");
//					detailedTeamView(Controller.getTeam().get(c - 1), usersIndex);
//				} else {
//					JOptionPane.showMessageDialog(null, "It does not contain : " + c);
//				}
			}
		} else {
			System.out.println("They pressed the exit button");
		}
	
	}
	
	//need to pass in a para when the backend classes are finished
	// when the roster class is finished this method can be finished. So that it can display the names in the rosters
	// when the user opts to view a detailed listing
	public static void detailedTeamView(Team t, int usersIndex){
		
		System.out.println("Detailed Team View Method was Called");
		
		String dTitle = "View - " + t.getName();
	
		String data = t.getName() + "\n";
		data += "Coach Name: " + t.getCoach().getFirstName() + " " + t.getCoach().getLastName();
		
		data += "\n1. player 1: asdfasd\n2. player 2: aasdfsfaasdf";
		JOptionPane.showMessageDialog(null, data, dTitle, JOptionPane.INFORMATION_MESSAGE);
		
		
		viewTeamProfiles(usersIndex);
		
	}
	
	
/***************************************************************************************************/	
/********************************Start: Edit View ***********************************/
	
	public static void editProfileView(int usersIndex) {
		//"1. Password\n2. Phone Number\n3. Name"
		String message = "What would you like to edit?\n1. Password\n2. Phone Number\n3. Name\n4. Go back to Dashboard";
		
		String titleString = "Edit Profile - " + getController().getUc().getUserName(usersIndex);
		
		String r = InputHelper.promptStringMenuOptions(message, "Edit Profile", titleString, new int[]{1, 2, 3, 4});
		
		//System.exit(0);
		
		System.out.println("Value of R: " + r);
		
		int editMenuSelection = Integer.parseInt(r);
		
		if (editMenuSelection == 1) {
			//edit password
			//-first prompt for them to entered their existing password
			//if correct allow them to enter in a new password
			//then display a successful message dialog
			
			editMenuPasswordOption(usersIndex);
		} else if (editMenuSelection == 2) {
			//edit phone number
			editMenuPhoneNumber(usersIndex);
		} else if (editMenuSelection == 3) {
			//edit name
			editMenuName(usersIndex);
		} else if (editMenuSelection == 4) {
			//Just added, not specified in directions. Go back to dashboard
			playersDashBoardView(usersIndex);
		}
		
		
		
		
	}
	
	/*****/
	//delete this portion 
	public static void output(Player p) {
		System.out.println("----------player -----------");
		System.out.println(p);
	}
	
	/****/
	
	@SuppressWarnings("unused")
	public static void editMenuName(int usersIndex) {
		
		String curNameConcat = getController().getUc().getUserObject(usersIndex).getFirstName() + " " + getController().getUc().getUserObject(usersIndex).getLastName();
		
		String data = "The current name is: " + curNameConcat;
		data += "\nPlease enter in the new name, first and last name seperated by a space: ";
		
		String newName = InputHelper.promptString(data, "newName", "Edit - name");
		System.out.println("Entered name is now : " + newName + " where as before it was " + curNameConcat);
		
		//Need to check and make sure they actually entered a space between the first and last name to delimite and set attribtures
		
		if (newName == null ) {
			//they pressed the cancel button
			System.out.println("Pressed the cancel button on editMenuName");
		} else if (newName != null) {
			//they entered in something for their name, now we just need to valdiate it for a delimite to set the first and last name attribtues
			String delims = "[ ]+";
			String[] tokens = newName.split(delims);
			
//			System.out.println("--------------------");
//			for (int i = 0; i < tokens.length; i++) {
//				System.out.println("#" + i + "  : " + tokens[i]);
//			}
			if (tokens.length == 2) {
				//the entered name had a space, so the name was set
				getController().getUc().getUserObject(usersIndex).setFirstName(tokens[0]);
				getController().getUc().getUserObject(usersIndex).setLastName(tokens[1]);
				InputHelper.successfulEditDialog("name", "Edit - name");
				editProfileView(usersIndex);
			} else {
				//error they did not enter in a space between the name they enter so 
				//as a result you cant set the first and last name
				InputHelper.unsuccessfulEditDialog("name", "Edit - name");
				editProfileView(usersIndex);
			}
			
			
			
		} else {
			System.out.println("They pressed the exit button on the editMenuName method");
		}
		
		
	}
	
	@SuppressWarnings("unused")
	public static void editMenuPhoneNumber(int usersIndex) {
		
		String data = "Your current phone number is : " + getController().getUc().getUserObject(usersIndex).getPhone();
		data += "\nPlease enter the new phone number (xxx xxx xxxx):";
		
		//need to add validatation to this phone input area.  ex. 703 252 0854
		String newNumber = InputHelper.promptString(data, "phoneNumber", "Edit - phone number");
		
		
		
		System.out.println("The new phone number they entered: " + newNumber);
		
		if (newNumber == null) {
			//they pressed the cancel button
			System.out.println("Pressed the cancel button on editMenuPhoneNumber");
			
		} else if (newNumber != null) {
			// place phone number validation here
			System.out.println("entered in something for the new phone number");
			
			int l = newNumber.length();
			System.out.println("Phone number length is : " + l);
			if (l == 12) {
				getController().getUc().getUserObject(usersIndex).setPhone(newNumber);
				InputHelper.successfulEditDialog("phone number", "Edit - phone number");
			} else {
				InputHelper.unsuccessfulEditDialog("phone number", "Edit - phone number");
			}
			
			
		} else {
			System.out.println("They pressed the exit button on the editMenuPHoneNumber method");
		}
		
		
		
		editProfileView(usersIndex);
	}
	
	
	@SuppressWarnings("unused")
	public static void editMenuPasswordOption(int usersIndex){
		String previousPass = InputHelper.promptString("Please enter your old password:", "old password ", "Edit - password");
		
		if (previousPass == null) {
			//they pressed the cancel button on the prompt for entering previous password
			editProfileView(usersIndex);
		} else if (previousPass != null) {
			//inserted a none null password
			
			//validate their existing password for match
			if (previousPass.equals(getController().getUc().getPassword(usersIndex))){
				String newPassword = InputHelper.promptString("Please enter your new password:", "new password", "Edit - password");
				
				if (newPassword == null) {
					//they pressed the cancel button on the prompt for entering their new password
					//they will be sent back to edit profile view
					editProfileView(usersIndex);
				} else if (newPassword != null) {
					//they entered something for the new password
					
					System.out.println("You changed exist password: " + getController().getUc().getPassword(usersIndex) + " to : " + newPassword);
					//getUc().getPassword(usersIndex) = newPassword;
					getController().getUc().setPassword(usersIndex, newPassword);
					System.out.println("After added new method changed exist password: " + getController().getUc().getPassword(usersIndex) + " to : " + newPassword);
					InputHelper.successfulEditDialog("password", "Successful edit");
					editProfileView(usersIndex);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Sorry! The exiting password you entered does not match our records");
				//redirects them back to the editProfileView
				editProfileView(usersIndex);
			}
			
		} else {
			System.out.println("They pressed the exit button on the edtiMenuPasswordOption");
			editProfileView(usersIndex);
		}
		

		
		
	}	
	

	
	
	
	//Other methods not sure where to place yet
	
	public static void invitationDialog(String message, String title) {
		Object[] options = { "Refuse", "Accept"};
		int n = JOptionPane.showOptionDialog(null, message,
				title, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
		
		if (n == -1 ) {
			//they pressed the x button
			System.out.println("Clicked the x button");
		} else {
			//they selected an option
			if (n == 1) {
				//clicked accept
				System.out.println("Clicked Accept");
			} else {
				//clicked refuse
				System.out.println("Clicked Refuse");
			}
		}
		
	}
	
	public static void playerProfileView(User player1) {
		Player player = (Player)player1;
		
		
		@SuppressWarnings("unused")
		String data = "Player Name: " + player.getFirstName() + " " + player.getLastName();
		//data += "\nEmail: " + player.get
		data += "\nPhone: " + player.getPhone();
		//data += "\nSigned Waiver: " + player.getPlayerWaiver().getSignFlag();
		data += "\nCaptain: " + player.getCaptain();
		
	}
	
	//time stamp area
	public static String timeStamp(){
		Date today = new Date();   
	
	    String a = "" + today;
	    return a;
	}

}
