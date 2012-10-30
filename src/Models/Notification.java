package Models;

import java.io.Serializable;

public class Notification implements Serializable {
	
	private boolean hasBeenViewed;
	private boolean doesRequireAction;
	private String message;
	private String sender; //it will be team name- coach name
	
	public Notification() {
		this.hasBeenViewed = false;
		this.doesRequireAction = false;
		this.message = "";
		this.sender = "";
	}
	
	public Notification(boolean hasBeenViewed, String message, boolean doesRequireAction, String sender) {
		super();
		this.hasBeenViewed = hasBeenViewed;
		this.message = message;
		this.doesRequireAction = doesRequireAction;
		this.sender = sender;
	}

	//getters
	public boolean isHasBeenViewed() { return hasBeenViewed; }
	public boolean isDoesRequireAction() { return doesRequireAction; }
	public String getMessage() { return message; }
	public String getSender() { return sender; }
	
	//setters
	public void setHasBeenViewed(boolean hasBeenViewed) { this.hasBeenViewed = hasBeenViewed; }	
	public void setDoesRequireAction(boolean doesRequireAction) { this.doesRequireAction = doesRequireAction; }
	public void setMessage(String message) { this.message = message; }
	public void setSender(String sender) { this.sender = sender; }

	
	public String toString() {
		return "Notification [hasBeenViewed=" + hasBeenViewed
				+ ", doesRequireAction=" + doesRequireAction + ", message="
				+ message + ", sender=" + sender + "]";
	}

}
