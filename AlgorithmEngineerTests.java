import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * 
 * Tester class for Algorithm Engineer
 * 
 * @author Han Yu Foong
 *
 */
public class AlgorithmEngineerTests {

	public static RBTree<StudentInterface> ST = null;

    @BeforeEach
    public void AEcreate(){
    	ST = new RBTree<StudentInterface>();
    }
	
    /**
     * test to print list contents in order
     */
    
	@Test
	public void test1() {
		StudentInterface s1 = new StudentDW("Han Tu", "100");
		StudentInterface s2 = new StudentDW("Han Yu", "101");
		StudentInterface s3 = new StudentDW("Sarah", "0");
		ST.insert(s1);ST.insert(s2);ST.insert(s3);
//		for (NewNode i: ST.InOrderList()) 
//			System.out.println(i.getWord() + "\t| " + i.getData());

		assertEquals("Sarah\t| 0", (s3.getName() + "\t| " + s3.getScore()));
		assertEquals("Han Tu\t| 100", (s1.getName() + "\t| " + s1.getScore()));
		assertEquals("Han Yu\t| 101", (s2.getName() + "\t| " + s2.getScore()));
	}
	
	/**
	 * test to print top and lowest data values
	 */
	
	@Test
	public void test2() {
		StudentInterface s1 = new StudentDW("Han Tu", "100");
		StudentInterface s2 = new StudentDW("Han Yu", "101");
		StudentInterface s3 = new StudentDW("Sarah", "0");
		ST.insert(s1);ST.insert(s2);ST.insert(s3);
		assertEquals(101, ST.Top(1).get(0).getScore());
		assertEquals(0, ST.Low(3).get(0).getScore());
	}
	
	/**
	 * test for getWord method from NewNode class
	 */
	
	@Test
	public void test3() {
		StudentInterface s1 = new StudentDW("The ", "0");
		StudentInterface s2 = new StudentDW("In-Order ", "1");
		StudentInterface s3 = new StudentDW("List ", "2");
		StudentInterface s4 = new StudentDW("Works", "3");
		ST.insert(s1);ST.insert(s2);ST.insert(s3);ST.insert(s4);
		String temp = "";
		for (StudentInterface i: ST.InOrderList()) 
			temp += i.getName();
		assertEquals("The In-Order List Works", temp);
	}
	
	/**
	 * test for getDatafromNode from RBTree
	 */
	
	@Test
	public void test4() {
		StudentInterface s1 = new StudentDW("Han Tu", "100");
		StudentInterface s2 = new StudentDW("Han Yu", "101");
		StudentInterface s3 = new StudentDW("Sarah", "0");
		ST.insert(s1);ST.insert(s2);ST.insert(s3);
		assertEquals(100, ST.getDatafromNode(s1));
	}
	
	/**
	 * test for NodeAE's getWord and getData methods
	 */
	
	@Test
	public void test5() {
		StudentInterface s1 = new StudentDW("Samsung", "2");
		StudentInterface s2 = new StudentDW("Apple", "1");
		StudentInterface s3 = new StudentDW("Google", "3");
		ST.insert(s1);ST.insert(s2);ST.insert(s3);
//		for (NewNode i: ST.InOrderList()) 
//			System.out.println(i.getWord() + "\t| " + i.getData());
		assertEquals("Apple\t| 1", s2.getName() + "\t| " + s2.getScore());
	}
	
	/**
	 * Integration test for getting a student details from their names
	 */
	
	@Test
	public void Integration1() {
		InfoGrabberBD BD = new InfoGrabberBD();
		FileReaderDW DW = new FileReaderDW();
		ArrayList<StudentInterface> list = new ArrayList<StudentInterface>();
		try {
			list = DW.readInfo("StudentDataset.txt");
			BD.loadInfo("StudentDataset.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(BD.findStudentByName("Aidan").getName());// backend gets student name from database RedBlackTree
		assertEquals("Aidan", BD.findStudentByName("Aidan").getName());
	}
	
	/**
	 * Integration test for getting the student's score
	 */
	
	@Test
	public void Integration2() {
		InfoGrabberBD BD = new InfoGrabberBD();
		FileReaderDW DW = new FileReaderDW();
		ArrayList<StudentInterface> list = new ArrayList<StudentInterface>();
		BD.findGradeByName("Aidan"); // b4 loading
		try {
			list = DW.readInfo("StudentDataset.txt"); // DW read fine
			BD.loadInfo("StudentDataset.txt");// data loaded on backend
//			System.out.println(BD.findGradeByName("Aidan"));// finds grade of student with that name
			assertEquals(80, BD.findGradeByName("Aidan"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Review of Back End Developer's code
	 */
	
	@Test
	public void CodeReviewOfBackendDeveloper() {
		InfoGrabberBD BD = new InfoGrabberBD();
		FileReaderDW DW = new FileReaderDW();
		ArrayList<StudentInterface> list = new ArrayList<StudentInterface>();
		try {
			list = DW.readInfo("StudentDataset.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BD.loadInfo("StudentDataset.txt"); //loading after the method call
//		System.out.println(BD.numStudentByGrade(100));
		assertEquals(1, BD.numStudentByGrade(100)); // returns how many student scored a full 100
	}
	
	/**
	 * Review of Data Wrangler's code
	 */
	
	@Test
	public void CodeReviewOfDataWrangler() {
		InfoGrabberBD BD = new InfoGrabberBD();
		FileReaderDW DW = new FileReaderDW();
		ArrayList<StudentInterface> list = new ArrayList<StudentInterface>();
		try {
			list = DW.readInfo("StudentDataset.txt"); // stores the list that readinfo returns
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(list.toString());// prints the entire list returned from data wrangler
		assertEquals("[Name: Aidan || Score: 80, Name: Will || Score: 85, Name: Rebecca || Score: 65, Name: Faith || Score: 90, Name: Florian || Score: 100, Name: Gary || Score: 95]"
				, list.toString());
	}
	
	/**
	 * test for the correct output
	 */
	public void demotest() {
		SSearchDApp.main(null);
	}
	
	/**
	 * main method to run at least 5 tests
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		AlgorithmEngineerTests AGT = new AlgorithmEngineerTests();
		AGT.test1();
		AGT.test2();
		AGT.test3();
		AGT.test4();
		AGT.test5();
		AGT.Integration1();
		AGT.Integration2();
		AGT.CodeReviewOfBackendDeveloper();
		AGT.CodeReviewOfDataWrangler();
		AGT.demotest(); //not a JUnit Test
	}	
	
}
