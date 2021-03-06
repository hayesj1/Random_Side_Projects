package main.java.com.jhayes;

import main.java.com.jhayes.operations.VectorOperations;
import main.java.com.jhayes.operations.VectorScanner;
import main.java.com.jhayes.vector.Dimensions;
import main.java.com.jhayes.vector.Vector;

import java.util.Scanner;

public class VecMain {

	public static void main(String[] args) {
		Dimensions dim = Dimensions.TWODIM;
		dim.init();
		VectorOperations vecOper = null;
		VectorScanner vecScan = null;
		boolean useComponents = false;
		
		System.out.println("Welcome to Vector Land!");
		String choice = "2D";
		boolean exit = false;
		Scanner keyboard = new Scanner(System.in);
		do {
			System.out.println("Enter dimensional space:\n(\"1D\", \"2D\", or \"3D\", no quotes)");
			choice = keyboard.next();
			switch(choice) {
			case "1d":
			case "1D":
				dim = Dimensions.ONEDIM;
				exit = true;
				break;
			case "2d":
			case "2D":
				dim = Dimensions.TWODIM;
				exit = true;
				break;
			case "3d":
			case "3D":
				dim = Dimensions.THREEDIM;
				exit = true;
				break;
			default:
				System.out.println("Invalid dimensional space; please enter either: \"1D\", \"2D\", or \"3D\", NO quotes");
				break;
			}
		} while(!exit);
		
		System.out.println("Will you enter in components or magnitudes for the vectors?\n(\"true\" for components, anything else for magnitudes");
		choice = keyboard.next();
		useComponents = Boolean.parseBoolean(choice);
		
		vecOper = new VectorOperations(dim);
		vecScan = new VectorScanner(keyboard, dim, useComponents);
		choice = "0";
		double scalar = 0;
		Vector result = null;
		do {
			System.out.println("How may we serve you?");
			System.out.println("1: add the vectors(1 + 2)");
			System.out.println("2: subtract main.java.com.jhayes.vector 2 from main.java.com.jhayes.vector 1(1 - 2)");
			System.out.println("3: subtract main.java.com.jhayes.vector 1 from main.java.com.jhayes.vector 2(2 - 1)");
			System.out.println("4: dot product(1 dot 2)");
			System.out.println("5: cross product (1 x 2)");
			System.out.println("6: cross product (2 x 1)");
			System.out.println("7: scalar product (1 x scalar)");
			System.out.println("8: scalar product (2 x scalar)");
			System.out.println("0: exit");
			choice = keyboard.next();
			if (choice == "0"){
				break;
			}
			switch(choice) {
				case "1":
					result = vecOper.add(vecScan.getVector1(), vecScan.getVector2());
					break;
				case "2":
					result = vecOper.subtract(vecScan.getVector1(), vecScan.getVector2());
					break;
				case "3":
					result = vecOper.subtract(vecScan.getVector2(), vecScan.getVector1());
					break;
				case "4":
					result = vecOper.dotProduct(vecScan.getVector1(), vecScan.getVector2());
					break;
				case "5":
					result = vecOper.crossProduct(vecScan.getVector1(), vecScan.getVector2());
					break;
				case "6":
					result = vecOper.crossProduct(vecScan.getVector2(), vecScan.getVector1());
					break;
				case "7":
					System.out.println("Enter the scalar multiplier:");
					scalar = Double.valueOf(keyboard.next());
					result = vecOper.scalarProduct(vecScan.getVector1(), scalar);
					break;
				case "8":
					System.out.println("Enter the scalar multiplier:");
					scalar = Double.valueOf(keyboard.next());
					result = vecOper.scalarProduct(vecScan.getVector2(), scalar);
					break;
				default:
					System.out.println("you must enter a whole number 0-8");
					continue;
			}
			System.out.println("This result is: " + result);
		} while (true);
		
		System.out.println("Completed...");
		keyboard.close();
	}
}
