package fileIO.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** <b>WARNING!! <br></b>
 * <b>DO NOT USE THE CLASS FOR IO WITH FILES <br></b>
 * <b>USE FileIO IN PACKAGE fileIO TO READ/WRITE TO FILES</b>
 * <p>
 * This class is the base class for FileIO classes doing the actual IO main.java.com.jhayes.operations
 */
abstract class FileIO {

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
	
	public abstract void close() throws IOException;
}
