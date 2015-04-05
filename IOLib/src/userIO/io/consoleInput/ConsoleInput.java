package userIO.io.consoleInput;

import java.util.Scanner;

public class ConsoleInput {

	protected static final Scanner in = new Scanner(System.in);
	
	public static String showBasicInstructions(String typeToEnter) {
		System.out.println("Please enter 1 " + typeToEnter.toLowerCase());
		return in.next();	
	}
	public static String showBasicInstructions(String typeToEnter, int numEntries, String delimeter) {
		String ret = "";
		System.out.println("Please enter " + numEntries + " " + typeToEnter + ", spearated by a \'" + delimeter + "\'");
		for (int i = 0; i <numEntries; i++) {
			ret += (in.next() + " ");
		}
		return ret;
	}
}
