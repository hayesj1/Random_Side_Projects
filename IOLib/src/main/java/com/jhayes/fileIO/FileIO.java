package main.java.com.jhayes.fileIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import main.java.com.jhayes.userIO.io.graphicalPopups.Popup;
import main.java.com.jhayes.exception.InvalidArgumentToConstructorException;
import main.java.com.jhayes.fileIO.io.FileIn;
import main.java.com.jhayes.fileIO.io.FileOut;

public final class FileIO {

	private FileIn fileIn = null;
	private FileOut fileOut = null;
	private static FileIO instance = null;

	/**
	 * Constructor for fileIn and fileOut. <code>boolean read</code> and/or <code>boolean write</code> must be true, but not neither
	 * @param theFile the file in question
	 * @param read true if the file should be readable
	 * @param write true if the file should be writable
	 * @throws InvalidArgumentToConstructorException if neither <code>read</code> or <code>write</code> are true
	 * @throws IOException if the file is un-openable for some reason
	 */
	private FileIO(File theFile, boolean read, boolean write) throws InvalidArgumentToConstructorException, IOException {
		if (read && !write) {
			this.fileIn = new FileIn(theFile.toPath());
		} else if (write && !read) {
			this.fileOut = new FileOut(theFile.toPath());
		} else if (write && read) {
			this.fileIn = new FileIn(theFile.toPath());
			this.fileOut = new FileOut(theFile.toPath());
		} else {
			throw new InvalidArgumentToConstructorException();
		}
	}
		public final static FileIO getInstance() {
			if (instance == null) {
				String temp = (String) Popup.getInput("File Path", "Enter the File Path", JOptionPane.QUESTION_MESSAGE);
				boolean read =(Popup.getConfirmChoice("Read?", "Read from the File?", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION ? true : false;
				boolean write = (Popup.getConfirmChoice("Write?", "Write to the File?", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION ? true : false;
				try {
					instance = new FileIO(Paths.get(temp).toFile(), read, write);
				} catch (InvalidArgumentToConstructorException | IOException e) {
					e.printStackTrace();
				}
			}
			return instance;
		}
		public final FileIn getFileIn() { return getInstance().fileIn; }
		public final FileOut getFileOut() { return getInstance().fileOut; }
}
