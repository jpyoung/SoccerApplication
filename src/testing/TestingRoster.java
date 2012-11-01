package testing;

import helpers.InputHelper;
import Models.Coach;
import Models.Notification;
import Models.Player;
import Models.UserCredentials;

public class TestingRoster {
	
	public static void main(String args[]) {
		System.out.println("Hello");
		
		TestDataLoader td = new TestDataLoader();
		
		
		UserCredentials uc = new UserCredentials();
		uc.setUserObject("tomyoung", "tjy", 2, td.getUser6());
		uc.setUserObject("jyoung", "greatness", 3, td.getUser2());
		uc.setUserObject("dtruong", "dtr", 3, td.getUser3());
		
//		Coach coach = (Coach) uc.getUserObject(0);
//		Player jack = (Player) uc.getUserObject(1);
//		Player duc = (Player)uc.getUserObject(2);
//		
//		coach.getTeam().getRoster().addPlayerName(jack);
//		jack.setTeam(coach.getTeam());
//		
		//cc.getTeam().getRoster().addPlayerName(p);
//		p.setTeam(cc.getTeam());
		
		uc.outputAllCredentials();
		
		System.out.println("\n\n----------------------------------\n\n");
		
		v1Menu(uc);
		
//		customOutput(jack);
//		
//		customOutput(duc);
//		
//		customCoachOuput(coach);
	}
	
	public static void v1Menu(UserCredentials uc) {
		v2Menu(uc);
	}
	
	public static void v2Menu(UserCredentials uc) {
		
		Coach coach = (Coach) uc.getUserObject(0);
		Player jack = (Player) uc.getUserObject(1);
		Player duc = (Player)uc.getUserObject(2);
		
		//
//		customOutput(jack);
//		customOutput(duc);
//		customCoachOuput(coach);
		//
		
		String dmessage = "Pick an action:\n1. Output All\n2. Add Jack to Team\n3. Remove Jack from Team\n4. Add Duc to Team\n5.Remove Duc from Team";
		dmessage += "\n6. Jack View Messages\n7. Duc view Messages";
		
		String resp = InputHelper.promptStringMenuOptions(dmessage, "option select", "Pick one", new int[]{1, 2, 3, 4, 5, 6, 7});
		
		if (resp.equals("-n-u-l-l-")) {
			v1Menu(uc);
		} else {
			int r = Integer.parseInt(resp);
			if (r == 1) {
				customOutput(jack);
				customOutput(duc);
				customCoachOuput(coach);
				v2Menu(uc);
			} else if (r == 2) {
				Notification mm = new Notification(false, "Jack, will you join our team.", true, "Coach", 3);
				jack.getInBox().addNotification(mm);
				
				coach.getTeam().getRoster().addPlayerName(jack);
				jack.setTeam(coach.getTeam());
				v2Menu(uc);
			} else if (r == 3) {
				Notification mm = new Notification(false, "Jack, We are dropping you.", false, "Coach", 3);
				jack.getInBox().addNotification(mm);
				
				coach.getTeam().getRoster().removePlayer(jack);
				jack.setTeam(null);
				v2Menu(uc);
				
			} else if (r == 4) {
				Notification mm = new Notification(false, "Duc, will you join our team.", true, "Coach", 3);
				duc.getInBox().addNotification(mm);
				
				coach.getTeam().getRoster().addPlayerName(duc);
				duc.setTeam(coach.getTeam());
				v2Menu(uc);
			} else if (r == 5) {
				
				Notification mm = new Notification(false, "Duc, We are dropping you.", false, "Coach", 3);
				duc.getInBox().addNotification(mm);
				coach.getTeam().getRoster().removePlayer(duc);
				duc.setTeam(null);
				
				v2Menu(uc);
			} else if (r == 6) {
				System.out.println("\n-------------------------Jack InBox--------------------------");
				if(jack.getInBox().getHasNotifications()) {
					if (jack.getInBox().getHasNotifications()) {
						for (int i = 0; i < jack.getInBox().getNotifications().size(); i++) {
							System.out.println(jack.getInBox().getNotifications().get(i));
						}
					}
				} else {
					System.out.println("You have none");
				}
				
				System.out.println("-------------------------Jack InBox--------------------------\n");
				v2Menu(uc);
			} else if (r == 7) {
				System.out.println("\n-------------------------Duc InBox--------------------------");
				if(duc.getInBox().getHasNotifications()) {
					if (duc.getInBox().getHasNotifications()) {
						for (int i = 0; i < duc.getInBox().getNotifications().size(); i++) {
							System.out.println(duc.getInBox().getNotifications().get(i));
						}
						duc.getInBox().removeNotification(duc.getInBox().getNotifications().get(0));
					}
				} else {
					System.out.println("You have none");
				}
				
				System.out.println("-------------------------Duc InBox--------------------------\n");
				v2Menu(uc);
			}
		}
		
		
		
		
	}
	
	
	
	public static void customCoachOuput(Coach u) {
		String dd = "firstname: " + u.getFirstName() + " " + u.getLastName();
		dd += "\n\tUser { \n";
		dd += "\t\tfirstname:" + u.getFirstName();
		dd += "\n\t\tlastname: " + u.getLastName();
		dd += "\n\t\tphone: " + u.getPhone();
		
		
		dd +="\n\t\tCoach { \n";
		dd += "\t\t\tTeam { \n";
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
