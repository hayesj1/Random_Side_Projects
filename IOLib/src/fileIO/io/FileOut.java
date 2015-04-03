package fileIO.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import fileIO.io.FileIO;

public final class FileOut extends FileIO {

	private BufferedWriter writer = null;
	
	protected FileOut(String path) throws IOException { this(Paths.get(path)); }
	public FileOut(Path path) throws IOException {
		super(path);
		this.writer = new BufferedWriter( new FileWriter(this.file, true));
	}
	
	@Override
	public void saveFile() {
		try {
			this.close();
		} catch (IOException e) {
			System.err.println("File Save operation Failed!");
		} finally {
			try {
				if (writer != null)
				this.close();
			} catch (IOException e) { }
		}
	}

	/** Writes a line of data to @see {@link FileIO#file} and adds the line terminator
	 * @param delimiter the delimiter of the tokens in the file
	 * @return an array of Strings holding the data in a given line
	 */
	public void write(String line) {
		try {
			writer.newLine();
			writer.write(line);
		} catch (IOException e) {
			return;
		}
		
	}
	public BufferedWriter getWriter() { return writer; }
	
	@Override
	public void close() throws IOException { writer.close(); }
}
