package fileIO.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import fileIO.io.FileIO;

public final class FileIn extends FileIO {

	private BufferedReader reader = null;
	
	protected FileIn(String path) throws IOException { this(Paths.get(path)); }
	public FileIn(Path path) throws FileNotFoundException {
		super(path);
		this.reader = new BufferedReader(new FileReader(this.file));
	}

	@Override
	public void saveFile() { }
	
	/** Reads a line of data, then splits that line by <code>delimiter</code> and returns the array with that data
	 * @param delimiter the delimiter of the tokens in the file
	 * @return an array of Strings holding the data in a given line
	 */
	public String[] read(String delimiter) {
		String line;
		String[] data = null;
		try {
			line = reader.readLine();
			if (line == null) {
				return null;
			} else {
				data = line.split(delimiter);
				return data;
			}
		} catch (IOException e) {
			return null;
		}
		
	}
	public BufferedReader getReader() { return reader; }
	
	@Override
	public void close() throws IOException { reader.close(); }
}
