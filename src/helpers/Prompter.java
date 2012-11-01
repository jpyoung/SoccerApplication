package helpers;

import javax.swing.JOptionPane;

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

	public static Prompt promptMessage(String displayMessage, String title) {
		String input = JOptionPane.showInputDialog(null, displayMessage, title,
				JOptionPane.QUESTION_MESSAGE);
		Prompt p = null;
		if (input == null) {
			p = new Prompt(null, true);
			// System.out.println("You Pressed Cancel");
		} else {
			p = new Prompt(input, false);
			// System.out.println("enter: " + input);
		}
		// System.out.println("sss: " + p);
		return p;
	}

}
