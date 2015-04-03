package fileIO.io.out;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import fileIO.io.FileIO;

public class FileOut extends FileIO {

	private BufferedWriter writer = null;
	
	protected FileOut(String path) throws IOException { this(Paths.get(path)); }
	protected FileOut(Path path) throws IOException {
		super(path);
		this.writer = new BufferedWriter( new FileWriter(this.file, true));
	}
	
	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		
	}

	public BufferedWriter getWriter() { return writer; };
}
