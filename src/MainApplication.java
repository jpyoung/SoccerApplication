import helpers.InputHelper;
import helpers.OutputHelpers;
import helpers.Prompt;
import helpers.Prompter;

import javax.swing.JOptionPane;

import testing.PDFWriter;
import Models.Coach;
import Models.Player;
import Models.Team;
import Models.User;
import controller.Controller;


/**
 * @author Jack Young
 * @date Nov 3, 2012
 */
public class MainApplication {
	
	public static Controller controller = new Controller();
	public static Controller getController() { return controller; } 
	public static boolean showConsoleDetails = false;  //This var is used whats being displayed in the console. 
	public static boolean getShowConsoleDetails() { return showConsoleDetails; }
	public static void setShowConsoleDetails(boolean a){ showConsoleDetails = a; }
	
	public static void main(String args[]) {
		System.out.println(OutputHelpers.timeStamp() + "-----MainApplication class - Main Method launch------");
		
		//load and output the initial  data
		//predefinedDefaultTest();
	
		//used for load data from data file
		SystemStateController.loadEverything();
		
		//getController().getUc().outputAllCredentials();
		Player pppp = (Player)getController().getUc().getUserObject(1);
		customOutput(pppp);
		
		//
		firstView();
		//
		
		//Saving Everything via serial data file
		SystemStateController.saveUserCreds();
		
		//before the application closes, Below I am making the calls to generate the PDF document with information.  UserPdf.pdf
		@SuppressWarnings("unused")
		PDFWriter pdf = new PDFWriter();
		PDFWriter.runPDFwriter(getController());
		
	}

	
/*************************************************************************************************************/
/****************************START:  firstView()**************************************************************/
/*************************************************************************************************************/
	
	//This is the first view the user will see when they run the application
	public static void firstView() {

		System.out.println(OutputHelpers.timeStamp() + "- SECTION: firstView.   Method Called: firstView()");
		
		Object[] options = {"Login", "Register" };
		int n = JOptionPane.showOptionDialog(null, "Welcome to Soccer Tournament Management",
				"Home", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if(n == 1 ) {
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected register button");
			RegisterView.registerView();
		} else if (n == 0 ) { 
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected login button");
			loginView();
		} else {
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: firstView.   Method Called: firstView()   ACTION: selected exit red button");
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
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Login In.   Method Called: logingView()");
		userLoginAction();
	}
	
	public static void userLoginAction() {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Login In.   Method Called: userLoginAction()");
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
				if (getShowConsoleDetails()) { System.out.println("\n\nCongrats, You have entered username: " + userName.getInputEntered() + " and password: " + iPassword.getInputEntered()); }
				
				String u = userName.getInputEntered();
				String p = iPassword.getInputEntered();
				int loginResult = getController().getUc().getID(u, p);
				if (loginResult != -1) {
					dashBoards(loginResult);
				} else {
					JOptionPane.showMessageDialog(null, "Sorry! The enter credentials do not match our records");
					firstView();
				}
			} else {
				firstView(); //they pressed the cancel button in the password box, go back to firstview()
			}
			
		} else {
			firstView(); //they pressed the cancel button in the username input prompt. go back to first view
		}			
	}
	
	
	//figure our on login entry if they pressed cancel and so forth
	public static Prompt promptMessage(String displayMessage, String title) {
		String input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
		   Prompt p = null;
		   if ( input == null) {
			   p = new Prompt(null, true); //pressed cancel
		   } else {
			   p = new Prompt(input, false);
		   }
		   return p;
	}

/*************************************************************************************************************/
/****************************END:  Login In ******************************************************************/
/*************************************************************************************************************/
	
	
	
/*************************************************************************************************************/
/****************************START:  Dashboards **************************************************************/
/*************************************************************************************************************/
	public static void dashBoards(int usersIndex) {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()");
	
		//Once a player has logged in.  This method will decide which dashboard they should be taken to depending
		//on their userType
		int tempType = getController().getUc().getUserType(usersIndex);
		
		if (tempType == 1) {
			//usertype is offical
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call officialsDashBoardView() method");
			officialsDashBoardView(usersIndex);
		} else if (tempType == 2) {
			//usertype is coach 
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call coachesDashBoardView() method");
			coachesDashBoardView(usersIndex);
		} else if (tempType == 3) {
			//usertype is a player
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: dashBoards()   ACTION: call playersDashBoardView() method");
			playersDashBoardView(usersIndex);
		} else {
			System.out.println("Error, in the dashboard method if three. Unknown usertype");
			InputHelper.displayMessage("Error, This user cannot login since they do not have a valid userType.", "Login Error");
			firstView();
		}
		
	}
	
	
	//view for the officials dash board view
	public static void officialsDashBoardView(int usersIndex) {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: officialsDashBoardView()");
		
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
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()");
	
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Manage or Create Team\n4. Save and Logout";
		String titleString = "Dashboard - Coach - " + getController().getUc().getUserName(usersIndex);
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3, 4});
	
		int c = Integer.parseInt(option);
		
		if (c == 1) {
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call editProfileView() method");
			EditProfileView.editProfileView(usersIndex); //go to edit profile
		} else if (c == 2) {
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call viewTeamProfiles() method");
			viewTeamProfiles(usersIndex); //go to view teams profiles
		} else if (c == 3) {
			System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: coachesDashBoardView()   ACTION: call manageTeamView() method  in ManageTeamView class");
			ManageTeamView.manageTeamView(usersIndex); //go to manage or create team
		} else if (c == 4) {
			firstView(); // go to save and log out. this will bring the user back to the first view
		} else {
			//error
			
		}
		
	}

	//view for the players dashboard view
	public static void playersDashBoardView(int usersIndex) {
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: Dashboards.   Method Called: playersDashBoardView");
		String displayString = "1. Edit Profile\n2. View Teams Profiles\n3. Invitations and Notifications\n4. Save and Logout";
		String titleString = "Dashboard - Player - " + getController().getUc().getUserName(usersIndex);
		String option = InputHelper.promptStringMenuOptions(displayString, "options", titleString, new int[]{1, 2, 3, 4});
		int c = Integer.parseInt(option);
		if (c == 1) {
			EditProfileView.editProfileView(usersIndex); //go to edit profile
		} else if (c == 2) {
			viewTeamProfiles(usersIndex); //go to view teams profiles
		} else if (c == 3) {
			playerNotificationView(usersIndex);
		} else if (c == 4) {
			firstView(); // go to save and log out. this will bring the user back to the first view
		} else {
			//error
		}
		
	}
	
	
	public static void playerNotificationView(int usersIndex) {
		
		Player p = (Player)getController().getUc().getUserObject(usersIndex);
		if (p.getInBox() != null) {
			
			if(p.getInBox().getHasNotifications()) {
				
				String message = "You have " + p.getInBox().getNotifications().size() + " new Notifications";
				InputHelper.displayMessage(message, "Your Notifications");
				
				if (p.getInBox().getHasNotifications()) {
					for (int i = 0; i < p.getInBox().getNotifications().size(); i++) {
						//System.out.println(p.getInBox().getNotifications().get(i));
						
						
						if (p.getInBox().getNotifications().get(i).isDoesRequireAction()) {
							//its a notification where they accept or decline an action
							boolean action = invitationDialog(p.getInBox().getNotifications().get(i).toString(), "Invitation Request");
							if(action) {
								//they accept
								System.out.println("They accepttttt");
								Coach coachSender = (Coach)getController().getUc().getUserObject(p.getInBox().getNotifications().get(i).getSenderUsersIndex());
								System.out.println("The send object found: " + coachSender.getFirstName());
								//coachSender.getTeam().getRoster().addPlayerName(p);
								//p.setTeam(coachSender.getTeam());
								
								String messageF = "Congrats, you have just joined the " + coachSender.getTeam().getName() + " team, and were just added to its roster";
								InputHelper.displayMessage(messageF, "Just joined a Team");
								coachSender.getTeam().getRoster().addPlayerName(p);
								p.setTeam(coachSender.getTeam());
							} else {
								System.out.println("You rejected it dude");
								
							}
							p.getInBox().removeNotification(p.getInBox().getNotifications().get(i));
						} else {
							//else its just a message
							InputHelper.displayMessage(p.getInBox().getNotifications().get(i).toString(), "Your Notifications");
							p.getInBox().removeNotification(p.getInBox().getNotifications().get(i));
						}
					}
				}
			} else {
				InputHelper.displayMessage("You have none.", "Notifications");
				System.out.println("You have none");
			}
		} else {
			InputHelper.displayMessage("You have none", "Notifications");
		}
		playersDashBoardView(usersIndex);
	}
	
	
/*************************************************************************************************************/
/****************************END:  Dashboards ****************************************************************/
/*************************************************************************************************************/
	
	
/***************************************************************************************************/
/*******************************Start: View Team Profiles ******************************************/
	
	public static void viewTeamProfiles(int usersIndex) {
		String data = "Choose team to view:  (press cancel to go back)\n";	
		for (int i = 0; i < getController().getTeam().size(); i++) {
			data += "\n" + (i+1) + ". "+ getController().getTeam().get(i).getName();
		}
		
		if (getShowConsoleDetails()) { System.out.println("viewTeamProfiles, The Team Size: " + getController().getTeam().size()); }
		
		int ln = getController().getTeam().size();
		int[] pos = OutputHelpers.generatePossibleOptions(ln);

		String resp = InputHelper.promptStringMenuOptionsType2(data, "View Team", "View Team", pos);
		
		if (resp.equals("-n-u-l-l-")) {
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
			int c = Integer.parseInt(resp);
			//Calling the method to view a more detailed look at the selected team
			detailedTeamView(getController().getTeam().get(c - 1), usersIndex);
		}
	}
	
	//detailed team view method
	public static void detailedTeamView(Team t, int usersIndex){
		System.out.println("Detailed Team View Method was Called");
		String dTitle = "View - " + t.getName();
		if(t.getRoster().playerCount == 0) {
			//though a team has been created, there are no players associated with this team yet. ie zero players
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
	
	//Other methods not sure where to place yet
	public static boolean invitationDialog(String message, String title) {
		Object[] options = { "Refuse", "Accept"};
		int n = JOptionPane.showOptionDialog(null, message,
				title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
		if (n == -1 ) {
			//they pressed the x button
			return false;
		} else {
			//they selected an option
			if (n == 1) {
				//clicked accept
				return true;
			} else {
				//clicked refuse
				return false;
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
	
	
	//This method is used to generate the data for the initial base application
	public static void predefinedDefaultTest(){
		getController().loadInitialData();
		Player p = (Player)getController().getUc().getUserObject(1);
		Coach cc = (Coach)getController().getUc().getUserObject(3);
		cc.getTeam().getRoster().addPlayerName(p);
		p.setTeam(cc.getTeam());
		Player p2 = (Player)getController().getUc().getUserObject(2);
		cc.getTeam().getRoster().addPlayerName(p2);
		p2.setTeam(cc.getTeam());
	}

	
	public static void customOutput(Player u) {
		
		String dd = "firstname: " + u.getFirstName() + " " + u.getLastName();
		
		dd += "\n\tUser { \n";
		dd += "\t\tfirstname:" + u.getFirstName();
		dd += "\n\t\tlastname: " + u.getLastName();
		dd += "\n\t\tphone: " + u.getPhone();
		
		dd +="\n\t\tPlayer { \n";
		dd += "\t\t\tcaptain: " + u.getCaptain();
		dd += "\n\t\t\tteam: " + (u.getTeam() == null ? "null" : u.getTeam().getName());
		
		dd += "\n\t\t\tPlayerWaiver { \n";
		dd += "\t\t\t\tname:" + u.getPlayerWaiver().getName();
		dd += "\n\t\t\t\tDescription:Hello there this is the description";
		dd += "\n\t\t\t\tSign Date: " + u.getPlayerWaiver().getSignDate();
		dd += "\n\t\t\t}";
		dd += "\n\t\t}";
		
	
		dd += "\n\t\tAddress { \n";
		dd += "\t\t\tstate:" + u.getAddress();
		dd += "\n\t\t}";
		
		dd += "\n\t\tInBox {\n";
//		dd += "\t\t\tNotifications has: " + u.getInBox().getHasNotifications() ;
//		if (u.getInBox().getHasNotifications()) {
//			dd += "\t\t\tList--";
//			for (int i = 0; i < u.getInBox().getNotifications().size(); i++) {
//				dd += "\n\t\t\t" + u.getInBox().getNotifications().get(i);
//			}
//		}
		dd += "\n\t\t}";
		
		dd += "\n\t}";
		

		System.out.println(dd);
		
	}
	
	
}
