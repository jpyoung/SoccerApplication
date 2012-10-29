package helpers;

import java.util.Date;

import Models.Player;
import Models.User;

public class OutputHelpers {
	
	//Dynamically generate possible options for a list selection
	public static int[] generatePossibleOptions(int size) {
		int[] temp = new int[size];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = (i + 1);
		}
		return temp;
	}

	//Method is used for concatenating the first and last name of passed in user, and returns it as a string. 
	public static String giveConcatName(User n) {
		String name = n.getFirstName() + " " + n.getLastName(); 
		return name;
	}
	
	//Method is used for concatenating the first and last name of a passed in player, and returns it as a String.
	//If the player is a captain, it will append a [c] onto the name.  
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
	
	//time stamp area
	public static String timeStamp(){
		Date today = new Date();   
	    String a = "" + today;
	    return a;
	}
	
}
