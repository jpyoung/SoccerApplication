import helpers.InputHelper;

import javax.swing.JOptionPane;


public class EditProfileView {

	/***************************************************************************************************/	
	/********************************Start: Edit View ***********************************/
		
	//method used display a list of items in which they can edit. ex password, phone number, name
	public static void editProfileView(int usersIndex) {
		String message = "What would you like to edit?\n1. Password\n2. Phone Number\n3. Name";
		String titleString = "Edit Profile - " + MainApplication.getController().getUc().getUserName(usersIndex);

		String r = InputHelper.promptStringMenuOptionsType2(message, "Edit Profile", titleString, new int[]{1, 2, 3});

		if (r.equals("-n-u-l-l-")) {
			int ti = usersIndex;
			//If they press cancel, they should be taken back to there dashboards. This if structure checks which user class they
			//are and takes them back to their respective dashboards
			if(MainApplication.getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Coach")) {
				MainApplication.coachesDashBoardView(usersIndex);    //its of coach type
			} else if (MainApplication.getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Player")) {
				MainApplication.playersDashBoardView(usersIndex);    //its of player type
			} else if (MainApplication.getController().getUc().getUserObject(ti).getClass().getName().equals("Models.Official")) {
				MainApplication.officialsDashBoardView(usersIndex);  //its of Official type
			}
		} else {
			int editMenuSelection = Integer.parseInt(r);
			if (editMenuSelection == 1) {
				editMenuPasswordOption(usersIndex);  //edit password
			} else if (editMenuSelection == 2) {
				editMenuPhoneNumber(usersIndex); //edit phone number
			} else if (editMenuSelection == 3) {
				editMenuName(usersIndex); 	//edit name
			}
		}

	}
		
	
		@SuppressWarnings("unused")
		public static void editMenuName(int usersIndex) {
			
			String curNameConcat = MainApplication.getController().getUc().getUserObject(usersIndex).getFirstName() + " " + MainApplication.getController().getUc().getUserObject(usersIndex).getLastName();
			String data = "The current name is: " + curNameConcat;
				data += "\nPlease enter in the new name, first and last name seperated by a space: ";
			
			String newName = InputHelper.promptString(data, "newName", "Edit - name");
			System.out.println("Entered name is now : " + newName + " where as before it was " + curNameConcat);
			
			//Need to check and make sure they actually entered a space between the first and last name to delimite and set attribtures
			if (newName == null ) {
				//they pressed the cancel button
				System.out.println("Pressed the cancel button on editMenuName");
			} else if (newName != null) {
				//they entered in something for their name, now we just need to valdiate it for a delimite to set the first and last name attribtues
				String delims = "[ ]+";
				String[] tokens = newName.split(delims);

				if (tokens.length == 2) {
					//the entered name had a space, so the name was set
					MainApplication.getController().getUc().getUserObject(usersIndex).setFirstName(tokens[0]);
					MainApplication.getController().getUc().getUserObject(usersIndex).setLastName(tokens[1]);
					InputHelper.successfulEditDialog("name", "Edit - name");
					editProfileView(usersIndex);
				} else {
					//error they did not enter in a space between the name they enter so 
					//as a result you cant set the first and last name
					InputHelper.unsuccessfulEditDialog("name", "Edit - name");
					editProfileView(usersIndex);
				}
			
			} else {
				System.out.println("They pressed the exit button on the editMenuName method");
			}	
		}
		
		@SuppressWarnings("unused")
		public static void editMenuPhoneNumber(int usersIndex) {
			String data = "Your current phone number is : " + MainApplication.getController().getUc().getUserObject(usersIndex).getPhone();
				data += "\nPlease enter the new phone number (xxx xxx xxxx):";
			
			//need to add validatation to this phone input area.  ex. 703 252 0854
			String newNumber = InputHelper.promptString(data, "phoneNumber", "Edit - phone number");
			
			System.out.println("The new phone number they entered: " + newNumber);
			
			if (newNumber == null) {
				//they pressed the cancel button
				System.out.println("Pressed the cancel button on editMenuPhoneNumber");
				
			} else if (newNumber != null) {
				// place phone number validation here
				System.out.println("entered in something for the new phone number");
				
				int l = newNumber.length();
				System.out.println("Phone number length is : " + l);
				if (l == 12) {
					MainApplication.getController().getUc().getUserObject(usersIndex).setPhone(newNumber);
					InputHelper.successfulEditDialog("phone number", "Edit - phone number");
				} else {
					InputHelper.unsuccessfulEditDialog("phone number", "Edit - phone number");
				}
			} else {
				System.out.println("They pressed the exit button on the editMenuPHoneNumber method");
			}
			
			editProfileView(usersIndex);
		}
		
		
		@SuppressWarnings("unused")
		public static void editMenuPasswordOption(int usersIndex){
			String previousPass = InputHelper.promptString("Please enter your old password:", "old password ", "Edit - password");
			
			if (previousPass == null) {
				//they pressed the cancel button on the prompt for entering previous password
				editProfileView(usersIndex);
			} else if (previousPass != null) {
				//inserted a none null password
				
				//validate their existing password for match
				if (previousPass.equals(MainApplication.getController().getUc().getPassword(usersIndex))){
					String newPassword = InputHelper.promptString("Please enter your new password:", "new password", "Edit - password");
					
					if (newPassword == null) {
						//they pressed the cancel button on the prompt for entering their new password
						//they will be sent back to edit profile view
						editProfileView(usersIndex);
					} else if (newPassword != null) {
						//they entered something for the new password
						
						System.out.println("You changed exist password: " + MainApplication.getController().getUc().getPassword(usersIndex) + " to : " + newPassword);
						//getUc().getPassword(usersIndex) = newPassword;
						MainApplication.getController().getUc().setPassword(usersIndex, newPassword);
						System.out.println("After added new method changed exist password: " + MainApplication.getController().getUc().getPassword(usersIndex) + " to : " + newPassword);
						InputHelper.successfulEditDialog("password", "Successful edit");
						editProfileView(usersIndex);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Sorry! The exiting password you entered does not match our records");
					//redirects them back to the editProfileView
					editProfileView(usersIndex);
				}
			} else {
				System.out.println("They pressed the exit button on the edtiMenuPasswordOption");
				editProfileView(usersIndex);
			}
		}
}
