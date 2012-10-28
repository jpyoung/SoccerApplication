package controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import testing.TestDataLoader;
import Models.Team;
import Models.UserCredentials;

public class Controller implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserCredentials uc;
	public static ArrayList<Team> team = new ArrayList<Team>();
	
	//Getters
	public static ArrayList<Team> getTeam() { return team; }
	
	
	
	public UserCredentials getUc() { return uc; }
	
	public void tempLoad() {
		uc = readIn();
		
	
		//outputting all the users credentials
		
		
		getUc().outputAllCredentials();
		
	}
	
	public UserCredentials readIn() {
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Person.dat"));
			UserCredentials uu = (UserCredentials) in.readObject();
			in.close();
			return uu;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public void loadInitialData() {
			
		TestDataLoader td = new TestDataLoader();
		
		uc = new UserCredentials();
		uc.setUserObject("alex1", "alucas", 0, td.getUser1());
		uc.setUserObject("jyoung", "greatness", 3, td.getUser2());
		uc.setUserObject("dtruong", "dtr", 3, td.getUser3());
		uc.setUserObject("tyoung", "tjy", 2, td.getUser4());
		
		uc.setUserObject("bVam", "vam", 1, td.getUser5());
		uc.setUserObject("mcook", "cook", 2, td.getUser6());
		
		uc.setUserObject("sspees", "spes12", 3, td.getUser7());
		uc.setUserObject("cFortier", "fort12", 3, td.getUser8());
	
		//outputting all the users credentials
		getUc().outputAllCredentials();

	}
	
}
