package Models;

import java.io.Serializable;

import Waiver.CoachWaiver;
import Waiver.UserForm;


public class Coach extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Team team;
	private UserForm coachWaiver;
	
	private boolean hasTeam = false;
	public boolean getHasTeam() {
		if (getTeam().getName() == null) {
			hasTeam = false;
		} else {
			hasTeam = true;
		}
		return hasTeam;
	}
	public void setHasTeam(boolean r) { hasTeam = r; }
	
	public Coach() {
		// TODO Auto-generated constructor stub
		super();
		team = new Team(this);
		
		System.out.println("\n\n-----------------------------------");
		//System.out.println("Team Info size: " + Controller.getTeam().size());
		System.out.println("\n\n-----------------------------------");
	
//		this.coachWaiver = null;//Jacks prior to change
		this.coachWaiver = new CoachWaiver();//(DUC) updated 10/27 : Creating Coach object auto create CoachWaiver()
		
	}
	
	public UserForm getCoachWaiver() {
		return coachWaiver;
	}
	public Team getTeam() {
		return team;
	}
	public boolean payLeagueFees() {
		return false;
	}

	@Override
	public String toString() {
		String a = "Coach [ " + super.toString();
		a += "] \n Team [ name:" + team.getName();
		a += "\n home color: " + team.getHomeColor();
		a += "\n away color: " + team.getAwayColor();
		a += "\n coach name: " + team.getCoach().getFirstName();
		a += "]\n";
		return a;
	}
	
}
