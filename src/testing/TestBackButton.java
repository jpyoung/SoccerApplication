package testing;

import helpers.InputHelper;


//sample testing class for list option cancel button functionality
public class TestBackButton {
	
	public static void main(String args[]) {
		System.out.println("hello");
		v1();
	}
		
	public static void v1() {
		InputHelper.displayMessage("Main menu v1", "V1");
		v2();
	}
	
	public static void v2() {
		int[] poss = new int[]{1, 2, 3};
		String mess = "Choose one:\n1. help\n2. More\n3. Generate data";
		
		String res = InputHelper.promptStringMenuOptionsType2(mess, "resp", "v2", poss);
		
		System.out.println("Your answer: " + res);
		
		if (res.equals("-n-u-l-l-")) {
			v1();
		} else {
			System.out.println("Lets display the view");
			int n = Integer.parseInt(res);
			if ( n == 3) {
				quickGenerateData();
			} else {
				
			}
			
		}
		
	}
	
	public static void quickGenerateData() {
		
		String[] email = {"jbelkin@masonlive.gmu.edu", "cjackman@masonlive.gmu.edu", "daamir@masonlive.gmu.edu", "yabalkha@masonlive.gmu.edu", 
				"vabanin@masonlive.gmu.edu", "mabawi@masonlive.gmu.edu", "aabbas4@masonlive.gmu.edu", "cagnew@masonlive.gmu.edu"
				};
		
		String[] passwords = new String[email.length];
		for (int i = 0; i < email.length; i++) {
			//System.out.println(email[i]);
			//System.out.println(substringBeforeLast(email[i], "@"));
			passwords[i] = substringBeforeLast(email[i], "@");
		}
		
		//Output the arrays of passwords and usernames, for testing purposes
//		for (int i = 0; i < passwords.length; i++) {
//			System.out.println(email[i] + "     " + passwords[i]);
//		}
		
		int startingMethodNumber = 9;
		
		for (int i = 0; i < email.length; i++) {
			System.out.println("uc.setUserObject(\"" + email[i] + "\", \"" + passwords[i] + "\", 3, td.getUser" + startingMethodNumber + "());");
		}
		
		System.out.println("\n\n methods to added to data loader");
		methodCreatorUserObject(passwords);
	}
	
	public static void methodCreatorUserObject(String[] p){
		
		int startingMethodNumber = 9;
		
		String sample = "";
		
		for (int i = 0; i < p.length; i++) {
			 sample += "public Player getUser" + startingMethodNumber +"() { Player p = new Player(); p.setAddress(createAddress" + startingMethodNumber +"()); p.setCaptain(false);\r\np.setFirstName(\"pdfirst" + startingMethodNumber + "\"); p.setLastName(\"pdlast" + startingMethodNumber + "\"); p.setPhone(\"222 455 1123\");\r\nreturn p; }";
			sample += "\n\n";
			startingMethodNumber++;
		}
		System.out.println(sample);
		
		System.out.println("\n\n");
		methodCreaterAddressObject(p);
	}
	
	public static void methodCreaterAddressObject(String[] p) {
		
		int startingMethodNumber = 9;
		String sample = "";
		
		for (int i = 0; i < p.length; i++) {
			sample += "public Address createAddress" + startingMethodNumber +"() { Address a = new Address(); a.setStreet(\"" + (i + 1) + ""+ startingMethodNumber + " Happy Moore Crt\");\r\na.setState(\"VA\"); a.setZip(\"20" + startingMethodNumber + "\"); a.setCity(\"Chantilly\");\r\nreturn a; }";
			sample += "\n\n";
			startingMethodNumber++;
		}
		
		System.out.println(sample);
	}
	
	
	 public static String substringBeforeLast(String str, String separator) {
	      if (isEmpty(str) || isEmpty(separator)) {
	          return str;
	      }
	      int pos = str.lastIndexOf(separator);
	      if (pos == -1) {
	          return str;
	      }
	      return str.substring(0, pos);
	  }
	
	 public static boolean isEmpty(String str) {
	      return str == null || str.length() == 0;
	  }
	

}
