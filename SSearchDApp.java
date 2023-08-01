import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main entry point for starting and running the CHSearch App.
 * 
 * @author AlgorithmEngineer, courtesy of the Han Yu Foong.
 */
public class SSearchDApp {
	public static void main(String[] args) {
		// Use data wrangler's code to load post data
		FileReaderDW DW = new FileReaderDW();
		// Use algorithm engineer's code to store and search for data
		RedBlackTree<StudentInterface> RBT;
		RBT = new RBTree<StudentInterface>();
		// Use the backend developer's code to manage all app specific processing
		InfoGrabberBD BD = new InfoGrabberBD();
		
		// the code that frontend will run from main menu
		ArrayList<StudentInterface> list = new ArrayList<StudentInterface>();
		try {
			list = DW.readInfo("StudentDataset.txt");
			BD.loadInfo("StudentDataset.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(BD.findGradeByName("Florian"));// assume user menu asks for score of specific student
	}
}
