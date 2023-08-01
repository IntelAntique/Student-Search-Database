import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderDW implements FileReaderInterface {

	@Override
	public ArrayList<StudentInterface> readInfo(String filename) throws FileNotFoundException {
		ArrayList<StudentInterface> students = new ArrayList<>();
		Scanner x = new Scanner(new File(filename));
		if (!x.hasNextLine()) {
			throw new FileNotFoundException();
		}
		while (x.hasNextLine()) {
			String info = x.nextLine();
			String[] pieces = info.split(",");
			if (pieces.length == 2) {
				students.add(new StudentDW(pieces[0], pieces[1]));
			} else {
				System.out.println(
						"WARNING: data within the file did not contain 2 pieces of data containing a NAME and SCORE");
			}
		}
		return students;
	}
}

