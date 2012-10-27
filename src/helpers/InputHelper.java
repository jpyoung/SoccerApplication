package helpers;

import javax.swing.JOptionPane;

/**
 * @author Jack Young
 *
 */
public class InputHelper {
	/**************************************************************************************************/
	/*********   The Methods that are below this heading are Helper and Validation Methods  ***********/
	/**  	-they were created to clean up and reduce the redundant code for the methods above.  ******/
	/**************************************************************************************************/
	
	///New helper views dialogs
	public static void successfulEditDialog(String thingEdited, String title) {
		String output = "The " + thingEdited + " was successfully changed.";
		JOptionPane.showMessageDialog(null, output, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void unsuccessfulEditDialog(String thingEdited, String title) {
		String output = "The " + thingEdited + " was not successfully changed.";
		JOptionPane.showMessageDialog(null, output, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void errorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void displayMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/************************* Helper prompter section ************************************************/
	public static String promptString(String displayMessage, String varName, String title){
		String input = "";
			do {
				input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
			} while (!validateInputForEmpty(input, varName));
		return input;
	}
	
	public static String promptStringMenuOptions(String displayMessage, String varName, String title, int[] possibilities){
		String input = "";
			do {
				input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
			} while (!validateNumericInputMenu(input, varName, possibilities));
		return input;
	}
//	public static String promptStringMenuOptions(String displayMessage, String varName, String title, int[] possibilities){
//		String input = "";
//		boolean pressedCancel = false;
//			do {
//				if (!pressedCancel) {
//					input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
//					if (Integer.parseInt(input) == JOptionPane.CANCEL_OPTION) {
//						pressedCancel = true;
//					}
//				} 
//				
//			} while (!validateNumericInputMenu(input, varName, possibilities) && !pressedCancel);
//		return input;
//	}
	
	public static String promptInt(String displayMessage, String varName, int max){
		String input = "";
			do {
				input = JOptionPane.showInputDialog(null, displayMessage, "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
			} while (!validateForIntInput(input, varName, max));
		return input;
	}
	
	public static String promptDouble(String displayMessage, String varName, int max){
		String input = "";
		do {
			input = JOptionPane.showInputDialog(null, displayMessage, "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
		} while (!validateForDoubleInput(input, varName, max));
		return input;
	}
/*************************** Input Help Section: End   ****************************************/
	
/*************************** Validation Section: Start ****************************************/

	public static boolean validateForIntInput(String inputed, String type, int max){
		if(validateInputForEmpty(inputed, type)){
			int convertedValue;
			try {
				convertedValue = Integer.parseInt(inputed);
				if(convertedValue < 0 || convertedValue > max) {
					//the number is a negative number
					//or the number they entered is greater then the given max number allowed to be enter
					JOptionPane.showMessageDialog(null, "Sorry! The " + type + " is not valid. Please enter it again.", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
					return false;
				} else {
					return true;
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Sorry! The " + type + " is not valid. Please enter it again.", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
				return false;
			}
		} else {
			//the inputed value was empty
			return false;
		}
	}
	
	public static boolean validateForDoubleInput(String inputed, String type, int max){
		if(validateInputForEmpty(inputed, type)){
			
			double convertedValue;
			try {
				convertedValue = Double.parseDouble(inputed);
				if(convertedValue < 0 || convertedValue > max) {
					//the number is a negative number
					//or the number they entered is greater then the given max number allowed to be enter
					JOptionPane.showMessageDialog(null, "Sorry! The " + type + " is not valid. Please enter it again.", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
					return false;
				} else {
					return true;
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Sorry! The " + type + " needs to be a number. Please enter it again", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
				return false;
			}
		} else {
			//the inputed value was empty
			return false;
		}
	}
	
	//validate method for list selection
	public static boolean validateNumericInputMenu(String inputed, String type, int[] possibilities) {
		if(validateInputForEmpty(inputed, type)) {
			int convertedValue;
			try {
				convertedValue = Integer.parseInt(inputed);
				boolean isInList = false;
				for(int z = 0; z < possibilities.length; z++) {
					if(convertedValue == possibilities[z]){
						isInList = true;
					}
				}
				if(isInList) { 
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Sorry! No such choice. Please enter it again.", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Sorry! The " + type + " needs to be a number. Please enter it again.", "Take-Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
				return false;
			}
		} else {
			//the inputed value was empty
			return false;
		}
	}
	
	public static boolean validateInputForEmpty(String input, String type){
		/*
		 * This method will validate the input for an empty.  The passed in String input is in fact and empty string false is return.  Else it returns true.  
		 */
		String message = "Sorry! The " + type + " is is not valid. Please enter it again";
		//10-20-2012 just added this input !=null so to avoid the null point exception when user presses the cancel button
		if (input != null) {
		if(input.length() <= 0 ){
			JOptionPane.showMessageDialog(null, message, "Take Home Assignment 5", JOptionPane.QUESTION_MESSAGE);
			return false;
		} else {
			return true;
		}
		} else {
			return true;
		}
	}
/*************************** Validation Section: End   ****************************************/

}
