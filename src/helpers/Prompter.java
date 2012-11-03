package helpers;

import javax.swing.JOptionPane;

import Models.UserCredentials;

/**
 * @author Jack Young
 * @date Oct 28, 2012
 */
public class Prompter {

	public static Prompt question(String message, String title) {
		Prompt prompt;
		do {
			prompt = promptMessage(message, title);
		} while (prompt.isEmpty());
		return prompt;
	}
	
	public static Prompt questionR(String message, String title) {
		Prompt prompt;
		do {
			prompt = promptMessage(message, title);
		} while (prompt.isEmpty() || !UserCredentials.validateUserName(prompt.getInputEntered()));
		return prompt;
	}

	public static Prompt promptMessage(String displayMessage, String title) {
		String input = JOptionPane.showInputDialog(null, displayMessage, title, JOptionPane.QUESTION_MESSAGE);
		Prompt p = null;
		if (input == null) {
			p = new Prompt(null, true); //you pressed cancel
		} else {
			p = new Prompt(input, false);
		}
		return p;
	}

}
