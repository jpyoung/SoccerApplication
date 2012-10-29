import helpers.InputHelper;
import helpers.OutputHelpers;
import helpers.Prompt;
import helpers.Prompter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	//public static Controller controller;
	public static Controller controller = new Controller();
	public static Controller getController(){
		return controller;
	}
	
	
	
	public static void loadEverything() {
		MainApplication.controller = readIn();
		
		getController().getUc().outputAllCredentials();
		
		
		Coach c = (Coach)getController().getUc().getUserObject(3);
		Coach c2 = (Coach)getController().getUc().getUserObject(5);
		System.out.println(c2.getTeam().getName());
		System.out.println("---asd-fadsf-asdf-asdf-asd-f---\n");
		System.out.println(c.getTeam().getName());
		
		//Team t = c.getTeam();
		//getController().getTeam().add(c2.getTeam());
		//getController().addATeam(c.getTeam());
		
		
	}
	
	public static Controller readIn() {
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TeamssArr.dat"));
			Controller uu = (Controller) in.readObject();
			
			in.close();
			
			System.out.println("\n\n----------------READIN-------------------");
			
			
			return uu;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeOut() {
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TeamssArr.dat"));
			
			System.out.println("\n\n\n\n\n------asdfasdfasdf-" + getController().getTeam().size());
			out.writeObject(getController());
			out.close();
			
			System.out.println("--------------------------------------");
			System.out.println("-----SAVED STATE TO DATA FILE---------");
			System.out.println("--------------------------------------");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
//		try
//		{
//			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Person.dat"));
//			out.writeObject(getController().uc);
//			out.close();
//			
//			System.out.println("--------------------------------------");
//			System.out.println("-----SAVED STATE TO DATA FILE---------");
//			System.out.println("--------------------------------------");
//			
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TeamssArr.dat"));
//			out.writeObject(Controller.getTeam());
//			out.close();
//			
//			System.out.println("--------------------------------------");
//			System.out.println("-----SAVED STATE TO DATA FILE---------");
//			System.out.println("--------------------------------------");
//			
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
	}
	

	
	
	public static void saveUserCreds() {
		
		writeOut();
		
//		ArrayList<String> xU = getController().getUc().getUserNamesArraylist();
//		ArrayList<String> xP = getController().getUc().getPasswordsArraylist();
//		ArrayList<Integer> xID = getController().getUc().getIdArraylist();
//		ArrayList<Integer> xTYPE = getController().getUc().getUserTypeArraylist();
//		
//		CreateUserCredXML cxml = new CreateUserCredXML(xU, xP, xID, xTYPE, "UserCredData.xml");
//		cxml.runExample();
	}
	
	public static void main(String args[]) {
		System.out.println("MainApplication class - Main Method launch");
		
		//load and output the initial  data
		//getController().loadInitialData();
		
		//Calling the serial load
		//getController().tempLoad();
		
		loadEverything();
		
		//temp data
		Player p = (Player)getController().getUc().getUserObject(1);
		Coach cc = (Coach)getController().getUc().getUserObject(3);
		cc.getTeam().getRoster().addPlayerName(p);
		p.setTeam(cc.getTeam());
		Player p2 = (Player)getController().getUc().getUserObject(2);
		cc.getTeam().getRoster().addPlayerName(p2);
		p2.setTeam(cc.getTeam());
	
		//end of temp data
		
		
		System.out.println("\n\n--Before Application Close---");
		System.out.println("Team arraylist size: " + getController().getTeam().size());
		
		for (int i = 0; i < getController().getTeam().size(); i++) {
			System.out.println(getController().getTeam().get(i).getName());
		}
		
		getController().getUc().outputAllCredentials();
		//System.out.println("Team arraylist size: " + getController().getTeam().size());
		//make the call for the first view 
		
		
		
		firstView();
		

		
		//output all userCreditential data on close
		System.out.println("--Data on Application Close---");
		getController().getUc().outputAllCredentials();
		
		
		//saving the usercrediential data to xml
		//saveUserCreds();
		
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
		userName = Prompter.question("Please enterr your email/username", "Login - username");
		
		//checking to see if they pressed the cancel button on username input
		if (!userName.isDidPressCancel()) {
			do {		
				iPassword = promptMessage("Please enter your password", "Login - password");	
			} while (iPassword.isEmpty());
			
			//checking to see if they pressed the cancel button on the password input 
			if (!iPassword.isDidPressCancel()) {
				System.out.println("\n\nCongrats, You have entered username: " + userName.getInputEntered() + " and password: " + iPassword.getInputEntered());
				
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
				firstView(); //they pressed the cancel button in the password box, go back to firstview()
			}
			
		} else {
			firstView(); //they pressed the cancel button in the username input prompt. go back to first view
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
			//viewAllTeams(usersIndex);
			viewTeamProfiles(usersIndex);
		} else if (c == 3) {
			//go to save and log out
			firstView(); // this will bring the user back to the first view
		} else {
			//error
		}
		
	}
	
	public static void viewAllTeams(int usersIndex) {
		
		//data += Team.getAllTeams();
		
		int ln = getController().getTeam().size();
		
		InputHelper.displayMessage("There are number of teams " + ln, "view all teams");
		System.out.println("\n\nThere are number of teams: " + ln);
		
		officialsDashBoardView(usersIndex);
		
	}
	
	//temp method
	public static void viewAllPlayersListing(int usersIndex){
		String a = "";
		for (int i = 0; i < getController().getUc().getUserObjectArraylist().size(); i++) {
			a += "#" + (i+1) + "  " + OutputHelpers.giveConcatName(getController().getUc().getUserObject(i));
			a += "\n";
		}
		
		InputHelper.displayMessage(a, "View all people");
		officialsDashBoardView(usersIndex);
	}
	
	
	//View for the coaches dash board view
	public static void coachesDashBoardView(int usersIndex) {
		System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()");
	
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
		System.out.println("\n\n\n\n");
		for (int i = 0; i < getController().getTeam().size(); i++) {
			data += "\n" + (i+1) + ". "+ getController().getTeam().get(i).getName();
		}
		System.out.println("The Size: " + getController().getTeam().size());
		System.out.println("\n\n\n\n");
		
//		String data = "Choose team to view:\n";		
//		data += Team.getAllTeams();
		
		int ln = getController().getTeam().size();
		int[] pos = OutputHelpers.generatePossibleOptions(ln + 1);
//		
//		//Taking the number of teams size, then creating a array of ints that corresponds to the option that they can select from
//		int[] tempA = new int[ln + 1];
//		ArrayList<Integer> tempB = new ArrayList<Integer>();
//		for (int y = 0; y < tempA.length; y++) {
//			tempA[y] = (y + 1);
//			tempB.add(y + 1);
//		}
//		
		//setting the exit condition number for a dynamically generated list of teams
		int exitNum = ln + 1;
		data += "\n" + exitNum + ". Exit";
		
		String option = InputHelper.promptStringMenuOptions(data, "View Team", "View Team", pos);
		
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
				detailedTeamView(getController().getTeam().get(c - 1), usersIndex);
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
		
		if(t.getRoster().playerCount == 0) {
			//thought a team has been created, there are no players associated with this team yet. ie zero players
			InputHelper.displayMessage("This team currently does not have any players.", dTitle);
		} else {
			//the selected team does have players
			
			String data = t.getName() + "\n";
			data += "Coach Name: " + OutputHelpers.giveConcatName(t.getCoach()) + "\n\n";
			data += "--Players List---\n";
			Player[] pp = t.getRoster().getPlayersOnRoster();
			
			for (int i = 0; i < pp.length; i++) {
				data += "   " + (i+1) + ". " + OutputHelpers.givePlayerConcatName(pp[i]) + "\n";
			}
			
			JOptionPane.showMessageDialog(null, data, dTitle, JOptionPane.INFORMATION_MESSAGE);
		}
		
		

		
		viewTeamProfiles(usersIndex);
		
	}
	
	
/***************************************************************************************************/	
/********************************Start: Edit View ***********************************/
	
	public static void editProfileView(int usersIndex) {
		//"1. Password\n2. Phone Number\n3. Name"
		String message = "What would you like to edit?\n1. Password\n2. Phone Number\n3. Name\n4. Go back to Dashboard";
		String titleString = "Edit Profile - " + getController().getUc().getUserName(usersIndex);
		
		String r = InputHelper.promptStringMenuOptions(message, "Edit Profile", titleString, new int[]{1, 2, 3, 4});
		
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

	
	//temp method
//	public static void viewAllPlayersListing(int usersIndex){
//		String a = "";
//		for (int i = 0; i < getController().getUc().getUserObjectArraylist().size(); i++) {
//			a += "#" + (i+1) + "  " + OutputHelpers.giveConcatName(getController().getUc().getUserObject(i));
//			a += "\n";
//		}
//		
//		InputHelper.displayMessage(a, "View all people");
//		officialsDashBoardView(usersIndex);
//	}
	
	
}
