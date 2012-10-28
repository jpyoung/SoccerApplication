package Models;

import java.io.Serializable;

import Waiver.CoachWaiver;
import Waiver.UserForm;
import controller.Controller;

public class Coach extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Team team;
	private UserForm coachWaiver;
	
	public Coach() {
		// TODO Auto-generated constructor stub
		super();
		team = new Team(this);
		
		
		System.out.println("\n\n-----------------------------------");
		System.out.println("Team Info size: " + Controller.getTeam().size());
		System.out.println("\n\n-----------------------------------");
		
		
		Controller.getTeam().add(team);
		
		System.out.println("\n\n-----------------------------------");
		System.out.println("Team Info size: " + Controller.getTeam().size());
		System.out.println("\n\n-----------------------------------");
		
//		this.team = new Team();
//		this.team.setCoach(this);
		
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
