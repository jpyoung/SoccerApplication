package testing;

import Models.Coach;
import Models.Player;
import Models.Team;
import Models.User;
import Models.UserCredentials;
import controller.Controller;

//Jack Young
// 10-27-2012

//This class is only for testing purposes.  When project is completed this class shall be deleted.  
public class testStorage {
	
	public static Controller c = new Controller();
	
	public static void main(String args[]) {
		
	
		System.out.println("Hello world");
		
		TestDataLoader td = new TestDataLoader();
	
		
		
		c.uc = new UserCredentials();
		c.getUc().setUserObject("tomyoung", "tjy", 2, td.getUser6());
		c.getUc().setUserObject("jyoung", "greatness", 3, td.getUser2());
		c.getUc().setUserObject("dtruong", "dtr", 3, td.getUser3());
		
		
		Player p = new Player();
		p.setCaptain(false);
		p.setFirstName("Ryan");
		p.setLastName("Young");
		p.setPhone("773 455 2323");
		
		c.getUc().setUserObject("ryoung", "ryc", 3, p);
		
		
		
		c.getUc().outputAllCredentials();
		
		System.out.println("\n       END OF MAIN METHOD \n\n");
		
		om(c);
		
	}
	
	public static void om(Controller c) {
		
		Coach coach = (Coach)c.getUc().getUserObject(0);
		
		System.out.println(coach);
		
		viewTeamDetailss(coach.getTeam());
		
		simulateRosterAddingPlayer(coach.getTeam());
		
		viewTeamDetailss(coach.getTeam());
		
	simulateRosterAddingPlayer2(coach.getTeam());
		
		viewTeamDetailss(coach.getTeam());
		
		
	simulateRosterAddingPlayer3(coach.getTeam());
		
		viewTeamDetailss(coach.getTeam());
	}
	
	
	
	public static void viewTeamDetailss(Team t) {
		//String title = "Team Details ";
		String m = "       Team Overview: Stats and Info\n\n";
		
		m += "Coach:         " + t.getCoach().getFirstName();
		m += "\n";
		m += "Full name:     " + giveConcatName(t.getCoach()); 
		m += "\n";
		m += "         Phone:  " + t.getCoach().getPhone();
		
		m += "\n";
		m += "Team Name:     " + t.getName();
		m += "\n";
		m += "Home Color:    " + t.getHomeColor();
		m += "\n";
		m += "Away Color:    " + t.getAwayColor();
		m += "\n";
		
	
		m += "Is set:       Captain = " + hasCaptainBeenSet(t) + "  Roster = " + hasRosterBeenSet(t);
		m += "\n";
		
		if (hasCaptainBeenSet(t)) {
			m += "Captain:       " + giveConcatName(t.getCaptain());
			m += "\n";
		}
		
		if (hasRosterBeenSet(t)) {
			m += "Roster count:      " + t.getRoster().playerCount;
			m += "\n";
			if ( t.getRoster().playerCount != 0) {
				
				Player[] pp = t.getRoster().getPlayersOnRoster();
				for (int i = 0; i < pp.length; i++) {
					m += "Player #" + (i+1) + ":     " + givePlayerConcatName(pp[i]) + "  ";
					m += "\n";
				}
				
				m += "\n Detailed INFO-----\n";
				for (int i = 0; i < pp.length; i++) {
					m += "               " + pp[i];
					m += "\n";
				}
				
				//checking to see if they have roster players to output
//				for (int i = 0; i < t.getRoster().playerCount; i++) {
////					Player pp = t.getRoster().getPlayersOnRoster().get(i);
//					Player pp = t.getRoster().getPlayersOnRoster()[i];
//					m += "Player #1:     " + giveConcatName(pp);
//					m += "\n           " + pp;
//					//m += "Away Color:    " + t.getAwayColor();
//				}
			}
		}
		
		
		System.out.println(m);
		
		
		
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
	
	
	
	
	
	
//	if (t.getCaptain() == null) {
//		m += "Captain:       Null, Captain has not been set."; 
//	} else {
//		m += "Captain:       " + t.getCaptain().getFirstName();	
//	}
	
	
	public static void simulateRosterAddingPlayer(Team t){
		
		System.out.println("----Simulating Roster Adding a Player-------");
		Player p = (Player)c.getUc().getUserObject(1);
		
		//t.updateRoster();
		t.getRoster().addPlayerName(p);
		p.setTeam(t);
	
		System.out.println(p.toString());
		
		System.out.println("-------------------------------------------");
	
	}
	
	
	public static void simulateRosterAddingPlayer2(Team t){
		
		System.out.println("----Simulating Roster Adding a Player 2-------");
		Player p = (Player)c.getUc().getUserObject(3);
		
		//t.updateRoster();
		t.getRoster().addPlayerName(p);
		p.setTeam(t);
	
		System.out.println(p.toString());
		
		System.out.println("-------------------------------------------");
	
	}
	
	
	public static void simulateRosterAddingPlayer3(Team t){
		
		System.out.println("----Simulating Roster Adding a Player 2-------");
		Player p = (Player)c.getUc().getUserObject(2);
		
		//t.updateRoster();
		t.getRoster().addPlayerName(p);
		p.setTeam(t);
	
		System.out.println(p.toString());
		
		System.out.println("-------------------------------------------");
	
	}
	
	public static void simulatePlayerBeingAddedToRoster() {
		
	}
	
	
	///useful methods consider implementing
	
	//Method is used for concatenating the first and last name of passed in user, and returns it as a string. 
	public static String giveConcatName(User n) {
		String name = n.getFirstName() + " " + n.getLastName(); 
		return name;
	}
	
	//used for display players, esp for if you want c to appear next to the name if they are captain
	public static String givePlayerConcatName(User n) {
		Player p = (Player)n;
		String extraMarkings = "";
			if(p.getCaptain()) {
				extraMarkings = " [c]";
			}
		String name = n.getFirstName() + " " + n.getLastName() + extraMarkings; 
		return name;
	}

}
