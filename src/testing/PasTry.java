package testing;

public class PasTry {
	
	public static void main(String args[]) {
		System.out.println("Hello World");
		
		System.out.println(validateUserName("j@gmail.com"));
	}
	

	public static boolean validateUserName(String userName) {
		int length = userName.length();
		if (length >= 6 && length <= 12) {
			int indexOfAtSymbol = userName.indexOf("@");
			int indexOfDot = userName.lastIndexOf(".");
			if ( indexOfAtSymbol == -1 || indexOfDot == -1 || indexOfAtSymbol == 0) {
				return false;  // does not contain a @ or . or @ symbol is the first char in username
			} else {
				if (indexOfDot < indexOfAtSymbol) {
					return false; //making sure that the last index of the dot is not before the @ symbol
				} else {
					int distanceBetweenAtAndLastDot = indexOfDot - indexOfAtSymbol;
					if (distanceBetweenAtAndLastDot <= 1) {
						return false; // there needs to be atleast one char between @ and .   example jack@g.com
					} else {
						int distBetweenDotAndLastChar = (length - 1) - indexOfDot;
						if (distBetweenDotAndLastChar < 1) {
							return false;  //needs to be atleast 1 char after the last dot.  example jack@g.c
						} else {
							return true;
						}
					}
				}
			}
		} else {
			return false;
		}
	}




}
