import java.util.ArrayList;
import java.io.FileNotFoundException;

public interface FileReaderInterface {
	public ArrayList<StudentInterface> readInfo(String filename) throws FileNotFoundException;

}

