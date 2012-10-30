package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class InBox implements Serializable {
	
	private boolean hasNotifications;
	private ArrayList<Notification> notifications = new ArrayList<Notification>();
	
	public InBox() {
		hasNotifications = false;
	}
	
	//getters
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public boolean getHasNotifications() {
		return hasNotifications;
	}
	
	//setters
	public void setHasNotifications(boolean hasNotifications) {
		this.hasNotifications = hasNotifications;
	}
	
	public void addNotification(Notification n) {
		System.out.println("The Arraylist of notes : " + notifications);
		notifications.add(n);
		setHasNotifications(true);
	}
	
	public void removeNotification(Notification n) {
		notifications.remove(n);
		
		if ( getNotifications().size() == 0) {
			setHasNotifications(false);
		}
	}
	
	public String toString() {
		String a = "InBox: \n Does have notifications: " + getHasNotifications();
		
		return a;
	}

}
