import helpers.InputHelper;
import helpers.OutputHelpers;
import helpers.Prompt;
import helpers.Prompter;

import java.util.Date;

import javax.swing.JOptionPane;

import testing.PDFWriter;
import Models.Coach;
import Models.Player;
import Models.Team;
import Models.User;
import controller.Controller;

//Jack Young
//Created On: 10/25/2012
public class MainApplication {
	
	
	public static Controller controller = new Controller();
	public static Controller getController() {
		return controller;
	}
	
	
	public static void main(String args[]) {
		System.out.println("MainApplication class - Main Method launch");
		
		//load and output the initial  data
		//getController().loadInitialData();
		
		//Calling the serial load
		//getController().tempLoad();
		
		SystemStateController.loadEverything();
		
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
		//SystemStateController.saveUserCreds();
		
		PDFWriter pdf = new PDFWriter();
		PDFWriter.runPDFwriter(getController());
		
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
	
		
		//Here will go a if tree to decide whether the user that just loged in is a coach
		//player or official.  From which it will call one of the three below methods to carry out that function
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
		
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Save and Logout";
		String titleString = "Dashboard - Official - " + getController().getUc().getUserName(usersIndex);
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3});
		
		int c = Integer.parseInt(option);
		
		if (c == 1) {
			EditProfileView.editProfileView(usersIndex); //go to edit profile
		} else if (c == 2) {
			viewTeamProfiles(usersIndex); //go to view teams profiles
		} else if (c == 3) {
			firstView(); // go to save and log out. this will bring the user back to the first view
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
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call editProfileView() method");
			EditProfileView.editProfileView(usersIndex); //go to edit profile
		} else if (c == 2) {
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call viewTeamProfiles() method");
			viewTeamProfiles(usersIndex); //go to view teams profiles
		} else if (c == 3) {
			System.out.println(timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call manageTeamView() method  in ManageTeamView class");
			ManageTeamView.manageTeamView(usersIndex); //go to manage or create team
		} else if (c == 4) {
			firstView(); // go to save and log out. this will bring the user back to the first view
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
			EditProfileView.editProfileView(usersIndex); //go to edit profile
		} else if (c == 2) {
			viewTeamProfiles(usersIndex); //go to view teams profiles
		} else if (c == 3) {
			//go to invitations and notifications
			
		} else if (c == 4) {
			firstView(); // go to save and log out. this will bring the user back to the first view
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
					coachesDashBoardView(usersIndex);    //its of coach type
				} else if (getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Player")) {
					playersDashBoardView(usersIndex);    //its of player type
				} else if (getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Official")) {
					officialsDashBoardView(usersIndex);  //its of Official type
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
	

	
	/*****/
	//delete this portion 
	public static void output(Player p) {
		System.out.println("----------player -----------");
		System.out.println(p);
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
