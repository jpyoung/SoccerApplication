package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
	
	private Roster roster;
	private String name;
	private String homeColor;
	private String awayColor;
	private Coach coach;
	private Player captain;
	@SuppressWarnings("unused")
	private boolean leagueFeesPaid;
	private static ArrayList<String> allTeams = new ArrayList<String>();
	
	//just added saturday 10-27
//	public void updateRoster(){
//		this.roster = new Roster();
//	}
	
	
	//just added - not specified in the uml
	private static int countOfAllTeams = 0;
	public static int getCountOfAllTeams() {
		return countOfAllTeams;
	}
	
	public Team() {
		roster = new Roster();//(DUC) updated 10/27 creating new roster when team is created
		
	}
	
	public Team(Coach c) {
		coach = c;
		roster = new Roster();//(DUC) updated 10/27 creating new roster when team is created
		
	}
	
	public void setName(String name){
		this.name = name;
		Team.allTeams.add(name);
		Team.countOfAllTeams++;
	}
	
	public void setHomeColor(String color){homeColor = color;}
	public void setAwayColor(String color){awayColor = color;}
	public void setCoach(Coach coach){
		this.coach = coach;
//		Team.allTeams.add(name);
//		Team.countOfAllTeams++;
	}
	public void setCaptain(Player captain){this.captain = captain;}
	public String getName(){return name;}
	public String getHomeColor(){return homeColor;}
	public String getAwayColor(){return awayColor;}
	public Roster getRoster(){return roster;}
	public Coach getCoach(){return coach;}
	public Player getCaptain(){return captain;}
	public static String getAllTeams()
	{
		int n = Team.allTeams.size();
		String allTeamsString = "Number of Teams: " + n + "  count: " + Team.getCountOfAllTeams() + "\n";
		for(int i = 0;i<n;i++)
		{
			allTeamsString +=  (i + 1) + ". " + Team.allTeams.get(i) + "\n";
		}
		return allTeamsString;
	}
	public String toString(){return "Team name: " + name + "\nHome Uniform Color: " + homeColor + "\nAway Uniform Color: " +
			awayColor + "\nCoach: " + coach + "\nTeam captain: " + captain;
	}
}
