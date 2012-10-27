package Models;

import Waiver.UserForm;


public class Player extends User {
	
	private boolean captain;
	private Team team;
	public UserForm playerWaiver;
	
	public Player() {
		//initialize class vars
		super();
		this.captain = false;
		this.team = null;
		this.playerWaiver = null;
		
	}
	
	//setters
	public void setCaptain(boolean captain) {
		this.captain = captain;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	//getters
	public boolean getCaptain() {
		return captain;
	}
	public Team getTeam() {
		return team;
	}
	public UserForm getPlayerWaiver() {
		return playerWaiver;
	}


	
	
	//added toString method for testing purposes. Remove later on
	@Override
	public String toString() {
		
		String a = "Player [captain=" + captain + ", ";
		
				if (team == null) {
					a += " team= NULL";
				} else {
					a += " team= " + team.getName();
				}
		a += ", playerWaiver=" + playerWaiver + "] and super atts: " + super.toString();
		
		
		return a;
	}

}