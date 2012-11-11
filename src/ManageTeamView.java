import helpers.InputHelper;
import helpers.OutputHelpers;

import java.util.ArrayList;

import Models.Coach;
import Models.Notification;
import Models.Player;
import Models.Team;
import Models.User;


/**
 * @author Jack Young
 * @date Nov 3, 2012
 */
public class ManageTeamView {
	
	public static void manageTeamView(int usersIndex) {
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);

		if (c.getHasTeam() && c.getTeam() != null) {
			manageTeamPrompt(c.getTeam(), usersIndex); //This coach does have a team
		} else {
			createTeamView(usersIndex); //This coach does not have a team
		}
	}
	
	public static void createTeamView(int usersIndex){
		System.out.println("Create Team View method called");
		String title = "Create Team";
		
		String teamName = InputHelper.promptString("Enter in the Team Name:", "team name", title);
		String homeColor = InputHelper.promptString("Enter in the Teams Home Color:", "home color", title);
		String awayColor = InputHelper.promptString("Enter in the Teams Away Color:", "away color", title);
		
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);
		c.getTeam().setName(teamName);
		c.getTeam().setAwayColor(awayColor);
		c.getTeam().setHomeColor(homeColor);
		c.setHasTeam(true);
		MainApplication.getController().getTeam().add(c.getTeam());
		
		InputHelper.displayMessage(teamName + " team has been created.", "Create Team");
		manageTeamView(usersIndex);
	}
	
	
	//create team workflow
	public static void createTeamWorkFlow(int usersIndex){
		System.out.println(OutputHelpers.timeStamp() + "- SECTION: ManageTeamView.   Method Called: createTeamWorkFlow()");
		MainApplication.coachesDashBoardView(usersIndex);
	}
	
	
	//manage team workflow
	public static void manageTeamPrompt(Team t, int usersIndex) {
		String title = "Manage Team [ " + t.getName() + " ]";
		String[] nOptions = {"View Roster", "Add Player", "Remove Player",
				"Change Team Name", "Change Team Colors", "Choose a Captain", "Pay league fees", "Exit"};
		int[] iOptions = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		
		String dataString = "What would you like to do?\n";
		for (int i = 0; i < iOptions.length; i++) {
			dataString += iOptions[i] + ". " + nOptions[i] + "\n";
		}
		
		String resp = InputHelper.promptStringMenuOptions(dataString, "option selected", title, iOptions);
		
		int n = Integer.parseInt(resp);
		
		if ( n == 8) {
			MainApplication.coachesDashBoardView(usersIndex); //exit, go back to coaches dashboard
		} else {
			if ( n == 1) {
				//view roster
				System.out.println("view roster");
				viewRosterMenu(usersIndex);
			} else if ( n == 2) {
				//add a player
				System.out.println("add a player");
				viewAddPlayerMenu(usersIndex);
			} else if ( n == 3) {
				//remove a player
				System.out.println("remove a player");
				viewRemovePlayerMenu(usersIndex);
			} else if ( n == 4) {
				//change team name
				System.out.println("change team name");
				changeTeamName(t, usersIndex);
			} else if ( n == 5) {
				//change team colors
				System.out.println("change team colors");
				changeTeamColor(t, usersIndex);
			} else if ( n == 6) {
				//choose a captain
				System.out.println("choose a captain");
				viewChooseCaptainMenu(usersIndex);
			} else {
				//pay league fees
				System.out.println("pay league fees");
				viewPayLeagueFeesMenu(usersIndex);
			}
		}
		
		
	}

/****************************Choose a Captain Section***************************************************************/		
	public static void viewChooseCaptainMenu(int usersIndex) {
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);
		
		if (c.getTeam().getRoster().getPlayerCount() == 0) {
			InputHelper.displayMessage("Sorry, you cannot select a captain." +
					"\nYour team must have players on the roster to do so. ", "Team Captain Selection");
			manageTeamView(usersIndex);
		} else {
			Player[] ros = c.getTeam().getRoster().getPlayersOnRoster();
			
			String m = "Select a player to set them as a Captain on the team.";
			for (int i = 0; i < ros.length; i++) {
				m += "\n" + (i + 1) + ". " + OutputHelpers.givePlayerConcatName(ros[i]);
			}
			
			String resp = InputHelper.promptStringMenuOptionsType2(m, "player selection", "Team Captain Selection", OutputHelpers.generatePossibleOptions(ros.length));
			
			if (resp.equals("-n-u-l-l-")) {
				manageTeamView(usersIndex);
			} else { 
				int n = Integer.parseInt(resp) - 1;
				System.out.println("You selected : " + resp + "  and index value of : " + n + "   name: " + ros[n].getFirstName());
				for (int z = 0; z < ros.length; z++) {
					if (n == z) {
						ros[z].setCaptain(true); //setting the new captain
					} else {
						ros[z].setCaptain(false); //unsetting any other captains on the team. 
					}
				}
				String outputM = "You have successfully changed your teams captain.";
				InputHelper.displayMessage(outputM, "Choose a Captain - View");
				manageTeamView(usersIndex);
			}
		}
	}
	
	
	
/****************************Pay League Fees Section***************************************************************/		
	public static void viewPayLeagueFeesMenu(int usersIndex) {
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);
		if (c.getPayLeagueFees()) {
			InputHelper.displayMessage("The " + c.getTeam().getName() + " have already Payed the League Fees.", "Pay League Fees - View");
		} else {
			String m = "The " + c.getTeam().getName() + " have not Payed is League Fees.";
			m += "\nPress accept to pay, or press refuse not to pay.";
			boolean response = MainApplication.invitationDialog(m, "Pay League Fees - View");
			if ( response ) {
				c.setPayLeagueFees(true);
				InputHelper.displayMessage("Thank you! You have successfully payed your teams league fees.", "Pay League Fees - View");
			} else {
				c.setPayLeagueFees(false); //refused to pay league fees
			}
		}
		manageTeamView(usersIndex);
	}
	
	
	
/****************************Add Player Section***************************************************************/	
	public static void viewAddPlayerMenu(int usersIndex) {
		@SuppressWarnings("unused")
		Coach coach = (Coach)MainApplication.getController().uc.getUserObject(usersIndex);
		
		viewAvailablePlayers(usersIndex);
	}
	
	public static void viewAvailablePlayers(int usersIndex) {
		
		String title = "Add Player";
		String m = "This is a list of players who do not have a team.\n";
		m += "Select the player you want to invite to join your roster:";
		
		ArrayList<User> aa = MainApplication.getController().getUc().getUserObjectArraylist();
		ArrayList<Player> tempee = new ArrayList<Player>();
		
		for (int i = 0; i < aa.size(); i++) {
			if(aa.get(i).getClass().getName().equals("Models.Player")){
				tempee.add((Player)aa.get(i));
			}
		}
		
		int counter = 1;
		ArrayList<Player> tempee2 = new ArrayList<Player>(); //this var holds the potential players in which the coach could invite to join his or her roster
		
	
		for (int kk = 0; kk < tempee.size(); kk++) {
			if (tempee.get(kk).getTeam() == null) {
				m +="\n" + counter + ". " + OutputHelpers.giveConcatName(tempee.get(kk));
				tempee2.add(tempee.get(kk));
				counter++;
			}
		}

		String resp = InputHelper.promptStringMenuOptionsType2(m, "select player", title, OutputHelpers.generatePossibleOptions(counter - 1));
		
		if (resp.equals("-n-u-l-l-")) {
			manageTeamView(usersIndex);
		} else {
			int n = Integer.parseInt(resp) - 1;
			String ddMessage = OutputHelpers.giveConcatName(tempee2.get(n)) + " was invited to your team. He/shee will appear in your\n";
				ddMessage += "roster after he/she accepted your invitation.\n";
				ddMessage += "If he/she refuses the invitation, he/she will not appear in your roster.";
			InputHelper.displayMessage(ddMessage, "Invitation Confirmation");
			
			Coach coach = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);

			String messageF = OutputHelpers.giveConcatName(tempee2.get(n)) + ", will you join our team?";
			Notification mm = new Notification(false, messageF, true, "Coach:" + coach.getFirstName(), usersIndex);
			tempee2.get(n).getInBox().addNotification(mm);

			manageTeamView(usersIndex);
		}	
	}
	
/***********Remove a player from the roster**************/	
	public static void viewRemovePlayerMenu(int usersIndex) {
		Coach coach = (Coach)MainApplication.getController().uc.getUserObject(usersIndex);
		choosePlayerToRemove(coach.getTeam(), usersIndex, coach);
	}
	
	public static void choosePlayerToRemove(Team t, int usersIndex, Coach coach) {
		String title = "Remove a player from  [" + t.getName() + "]";
		String m = "Select player you want to remove from your roster.  or click 'back':\n";
		
		if (hasRosterBeenSet(t)) {
			//has the roster been initialized 
			if (t.getRoster().playerCount == 0) {
				// their are no players on this coaches roster
				InputHelper.errorMessage("Sorry, but you currently do not have any players on your roster", title);
				manageTeamView(usersIndex);
			} else {
				//they do have players on their roster
				Player[] pp = t.getRoster().getPlayersOnRoster();
				int[] tempPoss = new int[pp.length];
					for (int i = 0; i < pp.length; i++) {
						m += (i+1) + ". " + OutputHelpers.givePlayerConcatName(pp[i]) + "  ";
						m += "\n";
						tempPoss[i] = (i + 1);
					}
				String resp = InputHelper.promptStringMenuOptionsType2(m, "player choosen", title, tempPoss);
				if (resp.equals("-n-u-l-l-")) {
					manageTeamPrompt(t, usersIndex);
				} else {
					int n = Integer.parseInt(resp) - 1;
					System.out.println("You selected this player number: " + resp + "   index value: " + n);
					
					String messageF = OutputHelpers.giveConcatName(pp[n]) + ", the " + coach.getTeam().getName() + " team has dropped you from their roster.";
					
					Notification mm = new Notification(false, messageF, false, "Coach - " + coach.getFirstName(), usersIndex);
					pp[n].getInBox().addNotification(mm); //adding the notification for being dropper
					coach.getTeam().getRoster().removePlayer(pp[n]); //dropping the relationship
					pp[n].setTeam(null);	//dropping the relationships
					
					InputHelper.displayMessage(OutputHelpers.giveConcatName(pp[n]) + " will be notified that he was cut from your roster.", "Player removed");
					manageTeamPrompt(t, usersIndex);
				}
			}
		}
	}

	
/*****************************View Roster Section**************************************************************/
	
	//method was designed for the coaches
	public static void viewRosterMenu(int usersIndex) {
		Coach coach = (Coach)MainApplication.getController().uc.getUserObject(usersIndex);
		viewRosterChoices(coach.getTeam(), usersIndex);
	}
	
	public static void viewRosterChoices(Team t, int usersIndex) {
		String title = "View Roster [" + t.getName() + "]";
		String m = "Select player you want to see or click 'back':\n";
		
		if (hasRosterBeenSet(t)) {
			//has the roster been initialized 
			if (t.getRoster().playerCount == 0) {
				// their are no players on this coaches roster
				InputHelper.errorMessage("Sorry, but you currently do not have any players on your roster.", title);
				manageTeamView(usersIndex);
			} else {
				//they do have players on their roster
				Player[] pp = t.getRoster().getPlayersOnRoster();
				int[] tempPoss = new int[pp.length];
					for (int i = 0; i < pp.length; i++) {
						m += (i+1) + ". " + OutputHelpers.givePlayerConcatName(pp[i]) + "  ";
						m += "\n";
						tempPoss[i] = (i + 1);
					}
				String resp = InputHelper.promptStringMenuOptionsType2(m, "player choosen", title, tempPoss);
				if (resp.equals("-n-u-l-l-")) {
					manageTeamPrompt(t, usersIndex);
				} else {
					int n = Integer.parseInt(resp) - 1;
					viewTeamDetailedPlayerProfile(pp[n], usersIndex);
				}
			}
		}
	}
	
	
	public static void viewTeamDetailedPlayerProfile(Player p, int usersIndex){ 
		String title = "Player Profile: [" + OutputHelpers.giveConcatName(p) + "]";
		String m = "Player Name: " + OutputHelpers.giveConcatName(p);
			m += "\nEmail: email address goes here";
			m += "\nPhone: " + p.getPhone();
			m += "\nSigned Waiver: " + (hasSignedWaiver(p) ? "Yes" : "NOO");
			m += "\nCaptain: " + (p.getCaptain() ? "Yes" : "No");
		InputHelper.displayMessage(m, title);
		viewRosterChoices(p.getTeam(), usersIndex); //redirects back to menu of players on roster
	}
	
/*******************************************************************************************/	
	
	//method used for changing the team colors. Either home or away
	public static void changeTeamColor(Team t, int usersIndex) {
		String title = "Manage Team [ " + t.getName() + " ]";
		String message = "Select the current team color you would like to update:";
				message += "\n1. Home: " + t.getHomeColor();
				message += "\n2. Away: " + t.getAwayColor();
				message += "\n\n3. Go Back";
		String resp = InputHelper.promptStringMenuOptions(message, "select color option", title, new int[]{1, 2, 3});
		int n = Integer.parseInt(resp);
		
		if (n == 1) {
			//edit home color
			t.setHomeColor(InputHelper.promptString("Enter in what you would like to set the Home color to.", "home color set", title + " - home color change"));
			InputHelper.successfulEditDialog("team home color", "Team Home Color Changed");
			manageTeamPrompt(t, usersIndex);
		} else if (n == 2) {
			//edit away color
			t.setAwayColor(InputHelper.promptString("Enter in what you would like to set the Away color to.", "home color set", title + " - home color change"));
			InputHelper.successfulEditDialog("team away color", "Team Away Color Changed");
			manageTeamPrompt(t, usersIndex);
		} else if (n == 3) {
			//selected go back button
			manageTeamPrompt(t, usersIndex);
		} else {
			System.out.println("Something is wrong in the if tree for the chageTeamColor Method");
		}
	}
	
	
	//method used for changing the team name
	public static void changeTeamName(Team t, int usersIndex) {
		String title = "Manage Team [ " + t.getName() + " ]";
		String message = "Current Team Name: " + t.getName() + "\nPlease enter in the new team name";
		String resp = InputHelper.promptString(message, "team name", title);
		t.setName(resp);
		InputHelper.successfulEditDialog("team name changed ", "Manage Team - Team Name Changed");
		manageTeamPrompt(t, usersIndex);
	}
	

	public static boolean hasCaptainBeenSet(Team t){
		boolean answer = false;
		if (t.getCaptain() != null) { answer = true; } 
		return answer;
	}
	
	public static boolean hasRosterBeenSet(Team t) {
		boolean answer = false;
		if ( t.getRoster() != null) { answer = true; }
		return answer;
	}
	
	public static boolean hasSignedWaiver(Player p) {
		boolean answer = false;
		if (p.getPlayerWaiver() != null) { answer = true; }
		return answer;
	}
	
}
