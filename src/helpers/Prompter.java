package helpers;

import javax.swing.JOptionPane;

public class Prompter {
	
	
	public static Prompt question(String message, String title) {
		Prompt prompt;
		
		do {
			prompt = promptMessage(message, "edit-password");
		} while(prompt.isEmpty());
		return prompt;
	}
	
	
	public static Prompt promptMessage(String displayMessage, String title) {
		
		   String input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
		   
		   Prompt p = null;
		   if ( input == null) {
			   p = new Prompt(null, true);
			   System.out.println("You Pressed Cancel");
		   } else {
			   p = new Prompt(input, false);
			   System.out.println("enter: " + input);
		   }
		   System.out.println("sss: " + p);
		   return p;
	}
	
	
	
//	public static void ask(String message, String title){
//		
//		Prompt prompt = question(message, title);
//		
//		
//		if(prompt.isDidPressCancel()){
//			mainA();
//		} else { 
//			if(prompt.getInputEntered().equals("Jack")){
//				JOptionPane.showMessageDialog(null, "Congrats you have update it");
//				mainA();
//			} else {
//				JOptionPane.showMessageDialog(null, "Sorry doesnt match");
//				ask(message, title);
//			}
//			
//		}
//		
//		System.out.println("prompt: " + prompt);
//		
	//}
}
