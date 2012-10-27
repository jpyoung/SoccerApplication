package helpers;

import javax.swing.JOptionPane;


public class Prompt {
	
	private String inputEntered = "";
	private boolean didPressCancel = false;
	private boolean isEmpty = false;
	
	public Prompt(String i, boolean b) {
		inputEntered = i;
		didPressCancel = b;
		if(!b){
			checkIfEmpty();
		}
	}
	
	private void checkIfEmpty(){
		if(getInputEntered().length() <= 0){
			isEmpty = true;
			JOptionPane.showMessageDialog(null, "Sorry! You must enter in something");
		}
	}
	
	//getters
	public String getInputEntered() { return inputEntered; }
	public boolean isDidPressCancel() { return didPressCancel; }
	public boolean isEmpty() { return isEmpty; }

	@Override
	public String toString() {
		return "Prompt [inputEntered=" + inputEntered + ", didPressCancel="
				+ didPressCancel + ", isEmpty=" + isEmpty + "]";
	}
	
}