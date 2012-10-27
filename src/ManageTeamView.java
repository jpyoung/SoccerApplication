import helpers.InputHelper;

import javax.swing.JOptionPane;

import Models.Coach;
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
	

}
