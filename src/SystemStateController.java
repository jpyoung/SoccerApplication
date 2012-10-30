

import helpers.OutputHelpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Models.Coach;
import controller.Controller;

public class SystemStateController {

	public static void loadEverything() {
		MainApplication.controller = readIn();
		
		MainApplication.getController().getUc().outputSystemUsersTable();
		
		
		Coach c = (Coach)MainApplication.getController().getUc().getUserObject(3);
		Coach c2 = (Coach)MainApplication.getController().getUc().getUserObject(5);
//		System.out.println(c2.getTeam().getName());
//		System.out.println("---asd-fadsf-asdf-asdf-asd-f---\n");
//		System.out.println(c.getTeam().getName());
		//Team t = c.getTeam();
		//MainApplication.getController().getTeam().add(c2.getTeam());
		MainApplication.getController().addATeam(c.getTeam());
		MainApplication.getController().addATeam(c2.getTeam());
		
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
