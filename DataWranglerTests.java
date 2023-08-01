import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DataWranglerTests {

	@Test
	void test1() {// This tester method ensures that the StudentDW object class is working as
					// intended
		StudentDW s = new StudentDW("Hannah", "65");
		String name = "Hannah";
		int score = 65;
		if (s.getName().equals(name)) {
			assertEquals(score, s.getScore());// Object class instantiates well along with successful getter methods
		}
	}

	@Test
	void test2() {// This method tests the basics of the InfoGrabber class, making sure a list of
					// student is returned and checking that the list is accurate
		FileReaderDW reader = new FileReaderDW();
		ArrayList<StudentInterface> result = new ArrayList<>();
		result.add(new StudentDW("Aidan", "80"));
		result.add(new StudentDW("Will", "85"));
		result.add(new StudentDW("Rebecca", "65"));
		result.add(new StudentDW("Faith", "90"));
		result.add(new StudentDW("Florian", "100"));
		result.add(new StudentDW("Gary", "95"));
		try {
			ArrayList<StudentInterface> students = reader.readInfo("StudentDataset.txt");
			if (students.get(0).getName().equals(result.get(0).getName())) {
				assertEquals(result.get(0).getScore(), students.get(0).getScore());
				return;
			}
			fail("ERROR: First index in student list didn't match expected value");
		} catch (FileNotFoundException e) {
			fail("ERROR: FileReaderDW handled FileNotFoundException that wasn't supposed to happen");
		}
	}

	@Test
	void test3() {// This test scenario specifically loads a file that doesn't exist, should throw
					// a file not found exception
		FileReaderDW reader = new FileReaderDW();
		try {
			reader.readInfo("DNE");// Attempting to read a file that doesn't exist
			fail("Exception wasn't thrown");// If the code gets to this line it means the exception wasn't thrown
		} catch (FileNotFoundException e) {
			assertTrue(true);// Proper exception caught and handled
		} catch (Exception e2) {
			fail("Unhandled exception caught");// Unhandled exception caught
		}
	}

	@Test
	void test4() {// This method tests the scenario where the data within the CSV file is not
					// correct, it should only store the correct ones
		FileReaderDW reader = new FileReaderDW();
		ArrayList<StudentInterface> toC = new ArrayList<>();// Variable to compare to the results
		toC.add(new StudentDW("Alex", "90"));
		toC.add(new StudentDW("Ashton", "70"));
		String toCompare = toC.get(0).toString() + toC.get(1).toString();
		try {
			ArrayList<StudentInterface> students = reader.readInfo("BadStudentDataset.txt");
			String result = students.get(0).toString() + students.get(1).toString();
			if (toCompare.equals(result)) {
				assertTrue(true);
			} else {
				System.out.println("ToC:" + toCompare);
				System.out.println("Result" + result);
				fail("Expected output did not occur");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("ERROR: FileReaderDW handled FileNotFoundException that wasn't supposed to happen");
		}
	}

	@Test
	void test5() {// This test method makes sure the compareTo method in the Student class works
					// as intended
		StudentDW lesser = new StudentDW("Aidan", "70");
		StudentDW greater = new StudentDW("Mauricio", "100");
		StudentDW eq = new StudentDW("Aidan", "70");
		if (greater.compareTo(lesser) == 1) {
			if (lesser.compareTo(greater) == -1) {
				if (lesser.compareTo(eq) == 0) {
					assertTrue(true);
					return;// All cases of compareTo working as intended
				}
			}
		}
		fail("ERROR in compareTo method");// An error occurred in the compareTo method
	}
	
	@Test
	void integrationTest1() {//This method tests the functionality of FileReaderDW and InfoGrabberBD
		InfoGrabberBD backend = new InfoGrabberBD();
		backend.loadInfo("StudentDataset.txt");
		assertEquals(backend.findStudentByName("Rebecca").getName(), "Rebecca");
		//If Rebecca prints, then the code is working as it is intended
	}
	
	@Test
	void integrationTest2() { //This method tests the functionality of the integration with the Backend Developer and the Algorithm Engineer
		InfoGrabberBD bd = new InfoGrabberBD();
		RedBlackTree<StudentInterface> rbt = new RedBlackTree<>();
		bd.loadInfo("StudentDataset.txt");
		rbt.insert(bd.findStudentByName("Aidan"));
		rbt.insert(bd.findStudentByName("Rebecca"));
		rbt.insert(bd.findStudentByName("Gary"));
		rbt.insert(bd.findStudentByName("Florian"));
		String result = "[ Name: Rebecca || Score: 65, Name: Aidan || Score: 80, Name: Gary || Score: 95, Name: Florian || Score: 100 ]";//insert expected answer
		assertEquals(result,rbt.toInOrderString());
	}
	
	@Test
	void codeReviewofBackendDeveloper() {//This code tests the functionality of the BackendDeveloper's code
		InfoGrabberBD bd = new InfoGrabberBD();
		bd.loadInfo("StudentDataset.txt");
		StudentDW answer = new StudentDW("Gary","95");
		StudentDW result = (StudentDW) bd.findStudentByName("Gary");
		assertEquals(answer.toString(),result.toString());
	}
	
	@Test
	void codeReviewofAlgorithmEngineer() {//This code tests the functionality of the AlgorithmEngineer's code
		RedBlackTree<StudentInterface> rbt = new RedBlackTree<>();
		StudentDW toRemove = new StudentDW("Aidan","80");
		rbt.insert(toRemove);
		rbt.insert(new StudentDW("Gary","95"));
		rbt.insert(new StudentDW("Florian","100"));
		rbt.insert(new StudentDW("Ashton","70"));
		try {
		rbt.remove(toRemove);
		assertEquals(3,rbt.size());
		}
		catch(Exception e) {
			fail("Unexpected exception");
		}
	}
	public static void main(String[] args) {
	}
}

