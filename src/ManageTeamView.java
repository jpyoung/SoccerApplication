import helpers.InputHelper;
import helpers.OutputHelpers;

import javax.swing.JOptionPane;

import Models.Coach;
import Models.Player;
import Models.Team;


public class ManageTeamView {
	
	
	
	public static void manageTeamView(int usersIndex) {
		
		//Need if statement
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(usersIndex);
		
		if (c.getTeam() != null) {
			//Team already create: No. THen do this
			manageTeamPrompt(c.getTeam(), usersIndex);
		} else {
			
		}
		
		System.out.println("Does Coach: " + c.getFirstName() + " have a team: " + c.getTeam());
		
	}
	
	
	//create team workflow
	public static void createTeamWorkFlow(int usersIndex){
		JOptionPane.showMessageDialog(null, "Create team workflow will go here");
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
			//exit
			System.out.println("exit");
			MainApplication.coachesDashBoardView(usersIndex);
		} else {
			if ( n == 1) {
				//view roster
				System.out.println("view roster");
				viewRosterMenu(usersIndex);
			} else if ( n == 2) {
				//add a player
				System.out.println("add a player");
			} else if ( n == 3) {
				//remove a player
				System.out.println("remove a player");
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
			} else {
				//pay league fees
				System.out.println("pay league fees");
			}
		}
		
		
	}
	
	//remaining needed methods
	// add a player
	// remove a player
	// choose a captain
	// pay league fees
	// view roster
	
	
/*******************************************************************************************/
	//View Roster [team name ]
	//select player you want to see or click back or ok 
	
	
	//method was designed for the coaches
	public static void viewRosterMenu(int usersIndex) {
		
		
		Coach coach = (Coach)MainApplication.getController().uc.getUserObject(usersIndex);
		
		
		
		System.out.println(coach);
		JOptionPane.showMessageDialog(null, "Your at view roster top");
		
		viewRosterChoices(coach.getTeam());
		
	}
	
	public static void viewRosterChoices(Team t) {
		
		String title = "View Roster [" + t.getName() + "]";
		String m = "Select player you want to see or click 'back':\n";
		
		if (hasRosterBeenSet(t)) {
			//has the roster been initialized 
			
			if (t.getRoster().playerCount == 0) {
				// their are no players on this coaches roster
				InputHelper.errorMessage("Sorry, but you currently do not have any players on your roster", title);
			} else {
				//they do have players on their roster
				Player[] pp = t.getRoster().getPlayersOnRoster();
				int[] tempPoss = new int[pp.length];
				for (int i = 0; i < pp.length; i++) {
					m += (i+1) + ". " + OutputHelpers.givePlayerConcatName(pp[i]) + "  ";
					m += "\n";
					tempPoss[i] = (i + 1);
				}
				String resp = InputHelper.promptStringMenuOptions(m, "player choosen", title, tempPoss);
				System.out.println("You selected this player number: " + resp);
			}
		}
	
		
	}
	
	
	public static void viewTeamDetailedPlayerProfile(Player p){ 
		String title = "Player Profile: [" + OutputHelpers.giveConcatName(p) + "]";
		String m = "Player Name: " + OutputHelpers.giveConcatName(p);
			m += "\nEmail: email address goes here";
			m += "\nPhone: " + p.getPhone();
		//	m += "\nSigned Waiver: " + ()
		
		//Player Profile: []
		//Player Name
		//Email:
		//Phone
		//Signed Waiver
		//Captain
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
		String message = "Current Team Name: " + t.getName();
			message += "\nPlease enter in the new team name";
		
		String resp = InputHelper.promptString(message, "team name", title);
		
		t.setName(resp);
		InputHelper.successfulEditDialog("team name changed ", "Manage Team - Team Name Changed");
		manageTeamPrompt(t, usersIndex);
		
	}
	

	
	public static boolean hasCaptainBeenSet(Team t){
		boolean answer = false;
		if (t.getCaptain() != null) {
			answer = true;
		} 
		return answer;
	}
	
	public static boolean hasRosterBeenSet(Team t) {
		boolean answer = false;
		if ( t.getRoster() != null) {
			answer = true;
		}
		return answer;
	}
	
	public static boolean hasSignedWaiver(Player p) {
		boolean answer = false;
		if(p.getPlayerWaiver() != null) {
			answer = true;
		}
		return answer;
	}
	
}
