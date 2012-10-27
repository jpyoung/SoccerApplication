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
		String mess = "Choose one:\n1. help\n2. More\n3. Ok";
		
		String res = InputHelper.promptStringMenuOptionsType2(mess, "resp", "v2", poss);
		
		System.out.println("Your answer: " + res);
		
		if (res.equals("-n-u-l-l-")) {
			v1();
		} else {
			System.out.println("Lets display the view");
		}
		
	}
	
	

}
