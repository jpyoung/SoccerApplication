package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Jack Young
 * @date Oct 30, 2012
 */
public class InBox implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean hasNotifications;
	private ArrayList<Notification> notifications = new ArrayList<Notification>();
	
	public InBox() {
		hasNotifications = false;
	}
	
	//getters
	public ArrayList<Notification> getNotifications() { return notifications; }
	public boolean getHasNotifications() { return hasNotifications; }
	
	//setters
	public void setHasNotifications(boolean hasNotifications) { this.hasNotifications = hasNotifications; }
	
	public void addNotification(Notification n) {
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
