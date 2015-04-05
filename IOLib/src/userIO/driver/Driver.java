package userIO.driver;

import userIO.io.consoleInput.ConsoleInput;
import userIO.io.graphicalPopups.Popup;

public class Driver {

	public static void main(String[] args) {
		String choice = ConsoleInput.showBasicInstructions("string");
		System.out.println(choice);
		choice = ConsoleInput.showBasicInstructions("integers", 3, " ");
		System.out.println(choice);
		choice = (String) Popup.getInput("User Input", "Enter Something:");
		System.out.println(choice);
	}

}
