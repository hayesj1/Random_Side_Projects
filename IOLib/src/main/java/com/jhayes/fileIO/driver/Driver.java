package main.java.com.jhayes.fileIO.driver;

import main.java.com.jhayes.fileIO.FileIO;
import main.java.com.jhayes.fileIO.io.FileIn;
import main.java.com.jhayes.fileIO.io.FileOut;

public class Driver {

	public static void main(String[] args) {
		FileIO fio = FileIO.getInstance();
		FileIn in = fio.getFileIn();
		String line[];
		while ( (line = in.read(" ")) != null) {
			for (int i = 0; i < line.length; i++)
				System.out.println(line[i]);
		}
		FileOut out = fio.getFileOut();
		out.write("Hiya");
		out.saveFile();
	}

}
