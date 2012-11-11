
import helpers.OutputHelpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Models.Coach;
import Models.User;
import controller.Controller;

/**
 * @author Jack Young
 * @date Nov 3, 2012
 */
public class SystemStateController {

	public static void loadEverything() {
		MainApplication.controller = readIn();
		//MainApplication.controller = readInDefault();
		MainApplication.getController().getUc().outputSystemUsersTable();
		findCoachesAndAddThereTeams();
	}
	
	public static void findCoachesAndAddThereTeams() {
		ArrayList<User> u = MainApplication.getController().getUc().getUserObjectArraylist();
		ArrayList<Coach> coaches = new ArrayList<Coach>();
		for (int i = 0; i < u.size(); i++) {
			if (u.get(i).getClass().getName().equals("Models.Coach")) {
				coaches.add((Coach) u.get(i));
			}
		}

		// clear existing teams, then will refill the team arraylist var
		MainApplication.getController().getTeam().clear();
		//now re adding the teams from data storage
		for (int z = 0; z < coaches.size(); z++) {
			if (coaches.get(z).getHasTeam()) {
				MainApplication.getController().addATeam(coaches.get(z).getTeam());
			}
		}

	}

	public static Controller readIn() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TeamssArr.dat"));
			Controller uu = (Controller) in.readObject();
			in.close();
			System.out.println(OutputHelpers.timeStamp() + "----------------READ IN From DATA File---------------");
			return uu;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("================ Fail - Data file Synch Error ==========="); //this is expected on the first launch. 
			MainApplication.getController().loadInitialData();
			return MainApplication.getController();
		}
	}
	
	public static Controller readInDefault() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("defaultBaseState.dat"));
			Controller uu = (Controller) in.readObject();
			in.close();
			System.out.println(OutputHelpers.timeStamp() + "----------------READ IN DEFAULT DATA File---------------");
			return uu;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("================ Fail ===========");
			// MainApplication.getController().loadInitialData();
			return null;
		}
	}

	public static void saveUserCreds() {
		writeOut();
		//writeDefault();
	}
	
	public static void writeDefault() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("defaultBaseState.dat"));
			System.out.println("------team size on close-" + MainApplication.getController().getTeam().size());
			out.writeObject(MainApplication.getController());
			out.close();
			System.out.println(OutputHelpers.timeStamp() + "--------------SAVED STATE DEFAULT DATA FILE---------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void writeOut() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TeamssArr.dat"));
			System.out.println("------team size on close-" + MainApplication.getController().getTeam().size());
			out.writeObject(MainApplication.getController());
			out.close();
			System.out.println(OutputHelpers.timeStamp() + "--------------SAVED STATE TO DATA FILE---------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
