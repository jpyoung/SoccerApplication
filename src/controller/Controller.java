package controller;

import java.io.Serializable;
import java.util.ArrayList;

import testing.TestDataLoader;
import Models.Team;
import Models.UserCredentials;

/**
 * @author Jack Young
 * @date Oct 28, 2012
 */
public class Controller implements Serializable {

	private static final long serialVersionUID = 1L;
	public UserCredentials uc;
	public ArrayList<Team> team = new ArrayList<Team>();
	
	//Getters
	public ArrayList<Team> getTeam() { return team; }
	public UserCredentials getUc() { return uc; }
	
	public void addATeam(Team t) {
		this.team.add(t);
	}
	
	//used for loading temp data manually into the system
	public void loadInitialData() {
		TestDataLoader td = new TestDataLoader();
		uc = new UserCredentials();
		uc.setUserObject("alex1", "alucas", 0, td.getUser1());
		uc.setUserObject("jyoung", "jpy", 3, td.getUser2());
		uc.setUserObject("dtruong", "dtr", 3, td.getUser3());
		uc.setUserObject("tyoung", "tjy", 2, td.getUser4());
		uc.setUserObject("bVam", "vam", 1, td.getUser5());
		uc.setUserObject("mCook", "cook", 2, td.getUser6());
		uc.setUserObject("sspees", "spes12", 3, td.getUser7());
		uc.setUserObject("cFortier", "fort12", 3, td.getUser8());
		
		//outputting all the users credentials
		getUc().outputAllCredentials();

	}
	
}
