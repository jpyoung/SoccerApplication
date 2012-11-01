

import helpers.OutputHelpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Models.Coach;
import Models.User;
import controller.Controller;

public class SystemStateController {
	
	
	public static void findCoachesAndAddThereTeams() {
		
		ArrayList<User> u = MainApplication.getController().getUc().getUserObjectArraylist();

		ArrayList<Coach> coaches = new ArrayList<Coach>();
		System.out.println("Coaches Array Size: " + coaches.size());
		for (int i = 0; i < u.size(); i++) {
			System.out.println(u.get(i).getClass().getName());
			if (u.get(i).getClass().getName().equals("Models.Coach")) {
				coaches.add((Coach)u.get(i));
			}
		}
		System.out.println("Coaches Array Size: " + coaches.size());
		System.out.println("Controller Team Size: " + MainApplication.getController().getTeam().size());
		
		//clear existing teams, then will refill the team arraylist var
		MainApplication.getController().getTeam().clear();
		
		
		int hasTeamsCount = 0;
		
		for (int z = 0; z < coaches.size(); z++) {
			if(coaches.get(z).getHasTeam()) {
				MainApplication.getController().addATeam(coaches.get(z).getTeam());
				hasTeamsCount++;
			}
		}
		System.out.println("Has Team Size: " + hasTeamsCount);
		System.out.println("Team Size: " + MainApplication.getController().getTeam().size());
		
	}
	

	public static void loadEverything() {
		
		
		
		
		MainApplication.controller = readIn();
		
		MainApplication.getController().getUc().outputSystemUsersTable();
		
		
		findCoachesAndAddThereTeams();
		
		
	}
	
	public static Controller readIn() {
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("TeamssArr.dat"));
			Controller uu = (Controller) in.readObject();
			in.close();
				System.out.println(OutputHelpers.timeStamp() + "----------------READ IN From DATA File-------------------");
			return uu;
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("================ Fail ===========");
			//MainApplication.getController().loadInitialData();
			return null;
		}
	}
	
	public static void writeOut() {
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TeamssArr.dat"));
			System.out.println("\n\n\n\n\n------asdfasdfasdf-" + MainApplication.getController().getTeam().size());
			out.writeObject(MainApplication.getController());
			out.close();
			
				
				System.out.println(OutputHelpers.timeStamp() + "--------------SAVED STATE TO DATA FILE---------");
				
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void saveUserCreds() {
		
		writeOut();
		
//		ArrayList<String> xU = getController().getUc().getUserNamesArraylist();
//		ArrayList<String> xP = getController().getUc().getPasswordsArraylist();
//		ArrayList<Integer> xID = getController().getUc().getIdArraylist();
//		ArrayList<Integer> xTYPE = getController().getUc().getUserTypeArraylist();
//		
//		CreateUserCredXML cxml = new CreateUserCredXML(xU, xP, xID, xTYPE, "UserCredData.xml");
//		cxml.runExample();
	}
	
}
