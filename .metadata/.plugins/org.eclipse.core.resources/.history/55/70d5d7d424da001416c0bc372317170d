package fileIO.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileIO {

	/** The File which gets read or written to */
	protected File file = null;
	
	protected FileIO(String path) { this(Paths.get(path)); }
	protected FileIO(Path path) { file = path.toFile(); }
	
	/**
	 * called to save all changes stored to the file
	 */
	public abstract void saveFile();
	
	/** gets the File associated with the instance
	 * @return @see {@link FileIO#file}
	 */
	public File getFile() { return file; }
}
