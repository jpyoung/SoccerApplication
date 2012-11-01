package testing;

import Models.Coach;
import Models.Player;

public class Sample {
	
	public static void main(String args[]) {
		System.out.println("hello");
		
	}
	
	
	public static void customCoachOuput(Coach u) {
		String dd = "firstname: " + u.getFirstName() + " " + u.getLastName();
		dd += "\n\tUser { \n";
		dd += "\t\tfirstname:" + u.getFirstName();
		dd += "\n\t\tlastname: " + u.getLastName();
		dd += "\n\t\tphone: " + u.getPhone();


		dd +="\n\t\tCoach { \n";
		dd += "\t\t\tHas Team: " + u.getHasTeam();
		dd += "\n\t\t\tTeam { \n";
		dd += "\t\t\t\tTeam name: " + u.getTeam().getName();
		dd += "\n\t\t\t\tHome Color: " + u.getTeam().getHomeColor();
		dd += "\n\t\t\t\tAway Color: " + u.getTeam().getAwayColor();
		dd += "\n\t\t\t\tCoach Name: " + (u.getTeam().getCoach().getFirstName() == null ? "Null" : u.getTeam().getCoach().getFirstName());
		dd += "\n\t\t\t\tLeague Fees Paid: ";

		dd += "\n\t\t\t\tRoster {\n";
		int pcount = u.getTeam().getRoster().getPlayerCount();
		dd += "\t\t\t\t\tplayer count: " + pcount;
		if(pcount > 0) {
			dd += "\n\t\t\t\t\tList--";
			Player[] ppp = u.getTeam().getRoster().getPlayersOnRoster();
			for (int i = 0; i < ppp.length; i++) {
				dd += "\n\t\t\t\t\t" + ppp[i].getFirstName();
			}
		}

		dd += "\n\t\t\t\t}";
		dd += "\n\t\t\t}";

		dd += "\n\t\tAddress { \n";
		dd += "\t\t\tstate:" + u.getAddress();
		dd += "\n\t\t}";

		dd += "\n\t}";

		System.out.println(dd);
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
		dd += "\t\t\tNotifications has: " + u.getInBox().getHasNotifications() ;
		if (u.getInBox().getHasNotifications()) {
			dd += "\t\t\tList--";
			for (int i = 0; i < u.getInBox().getNotifications().size(); i++) {
				dd += "\n\t\t\t" + u.getInBox().getNotifications().get(i);
			}
		}
		dd += "\n\t\t}";
		
		dd += "\n\t}";
		

		System.out.println(dd);
		
	}
	
	

}
