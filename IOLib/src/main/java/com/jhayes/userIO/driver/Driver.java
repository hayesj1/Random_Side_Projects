package main.java.com.jhayes.userIO.driver;

import main.java.com.jhayes.userIO.io.consoleInput.ConsoleInput;
import main.java.com.jhayes.userIO.io.graphicalPopups.Popup;

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
