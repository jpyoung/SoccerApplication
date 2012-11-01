package Models;

import java.io.Serializable;

import Waiver.CoachWaiver;
import Waiver.UserForm;


public class Coach extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Team team;
	private UserForm coachWaiver;
	private boolean payLeagueFees = false;
	private boolean hasTeam = false;
	

	public Coach() {
		super();
		team = new Team(this);
		this.coachWaiver = new CoachWaiver();//(DUC) updated 10/27 : Creating Coach object auto create CoachWaiver()
	}
	
	//getters
	public UserForm getCoachWaiver() { return coachWaiver; }
	public Team getTeam() { return team; }
	public boolean getPayLeagueFees() { return payLeagueFees; }
	
	public boolean getHasTeam() {
		if (getTeam().getName() == null) {
			hasTeam = false;
		} else {
			hasTeam = true;
		}
		return hasTeam;
	}
	
	//setters
	public void setHasTeam(boolean r) { hasTeam = r; }
	public void setPayLeagueFees(boolean l) { this.payLeagueFees = l; }
	
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
