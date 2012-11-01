package Models;

import java.io.Serializable;

/**
 * @author Jack Young
 * @date Oct 30, 2012
 */
public class Notification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hasBeenViewed;
	private boolean doesRequireAction;
	private String message;
	private String sender; //it will be team name- coach name
	private int senderUsersIndex = -1;
	
	public Notification() {
		this.hasBeenViewed = false;
		this.doesRequireAction = false;
		this.message = "";
		this.sender = "";
		this.senderUsersIndex = -1;
	}
	
	public Notification(boolean hasBeenViewed, String message, boolean doesRequireAction, String sender, int senderUsersIndex) {
		super();
		this.hasBeenViewed = hasBeenViewed;
		this.message = message;
		this.doesRequireAction = doesRequireAction;
		this.sender = sender;
		this.senderUsersIndex = senderUsersIndex;
	}

	//getters
	public boolean isHasBeenViewed() { return hasBeenViewed; }
	public boolean isDoesRequireAction() { return doesRequireAction; }
	public String getMessage() { return message; }
	public String getSender() { return sender; }
	public int getSenderUsersIndex() { return senderUsersIndex; }
	
	//setters
	public void setHasBeenViewed(boolean hasBeenViewed) { this.hasBeenViewed = hasBeenViewed; }	
	public void setDoesRequireAction(boolean doesRequireAction) { this.doesRequireAction = doesRequireAction; }
	public void setMessage(String message) { this.message = message; }
	public void setSender(String sender) { this.sender = sender; }


	@Override
	public String toString() {
		return "Notification [hasBeenViewed=" + hasBeenViewed
				+ ", doesRequireAction=" + doesRequireAction + ", message="
				+ message + ", sender=" + sender + ", senderUsersIndex="
				+ senderUsersIndex + "]";
	}

}
