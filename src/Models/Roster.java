package Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
public class Roster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<Player> roster = new HashSet<Player>();
	public final int MAX_NUM_OF_PLAYERS = 30;
	public int playerCount = 0;
	
	public void addPlayerName(Player player) {
		System.out.println("Add Player Method called");
		roster.add(player);
	
		
		playerCount++;
	}
	
	//just added
	public void removePlayer(Player player) {
		System.out.println("Remove Player Method called");
		player.setTeam(null);
		roster.remove(player);
		playerCount--;
	}
	
	
	public int getPlayerCount() {
		return playerCount;
	}
	
	
	
	public String toString(){
		String aString = "";
		Iterator<Player> iterator = roster.iterator();
		while(iterator.hasNext()){aString += iterator.next() + "\n";}
		return "Number of player in roster: " + playerCount + "\nMaximum number of player allow: "+ MAX_NUM_OF_PLAYERS
				+ "\nRoster: " + aString;
	}

	
	/**
	 * just created 10-27.  used primarily by the coach to view his or her roster of players
	 * @return the roster
	 */
	public Player[] getPlayersOnRoster() {
		Player[] arr = new Player[roster.size()];
		Iterator<Player> iterator = roster.iterator();
		int counterr = 0;
		while(iterator.hasNext()) {
			arr[counterr] = iterator.next();
			counterr++;
		}
		return arr;
	}

	
	
}
