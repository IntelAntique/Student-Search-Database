import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InfoGrabberBD {
  private FileReaderDW placeholderReader;
  private RBTree<StudentInterface> database;

  /**
   * Constructor for the InfoGrabber
   */
  public InfoGrabberBD() {
    placeholderReader = new FileReaderDW();
    database = new RBTree<>();
  }

  /**
   * converts a file into usable data in the RBTreeInterface database.
   * @param filename - of type String
   */
  public void loadInfo(String filename){
    ArrayList<StudentInterface> returnList = new ArrayList<StudentInterface>();
    try {
		returnList = placeholderReader.readInfo(filename);
	} catch (FileNotFoundException e) {
		System.out.println("Whoops");
	}
    for (StudentInterface i : returnList) {
        database.insert(i);
      }
    }

    /**
     * finds students based off a given String representing their name
     * @param name - of type String
     */
    public StudentInterface findStudentByName (String name) {
      List<StudentInterface> databaseAsList = database.InOrderList();
      for (StudentInterface i : databaseAsList) {
        if (i.getName().equals(name)) {
          return(i);
        }
      }
      return(null);
    }

    /**
     * returns the grade of the student determined by the given name
     * @param name - of type String
     */
    public int findGradeByName (String name) {
        List<StudentInterface> databaseAsList = database.InOrderList();
        for (StudentInterface i : databaseAsList) {
          if (i.getName().equals(name)) {
            return(i.getScore());
          }
        }
        return(-1);

      }

      /**
       * finds every student with a given grade and returns them in a list
       * @param score - of type int
       */
      public List<StudentInterface> findStudentListByGrade (int score) {
        List<StudentInterface> databaseAsList = database.InOrderList();
        List<StudentInterface> returnList = new ArrayList<StudentInterface>();
        for (StudentInterface i : databaseAsList) {
          if (i.getScore() == score) {
            returnList.add(i);
          }
        }
        return(returnList);

      }

      /**
       * finds the number of students with a given grade
       * @param score - of type int
       */
      public int numStudentByGrade (int score) {
        List<StudentInterface> databaseAsList = database.InOrderList();
        int numStudents = 0;
        for (StudentInterface i : databaseAsList) {
          if (i.getScore() == score) {
            numStudents++;
          }
        }
        return(numStudents);
      }

      /**
       * finds every student with a given grade and returns their names in a list
       * @param score - of type int
       */
      public List<String> findStudentNamesByGrade (int score) {
    	    List<StudentInterface> databaseAsList = database.InOrderList();
    	    List<String> returnList = new ArrayList<String>();
    	    for (StudentInterface i : databaseAsList) {
    	      if (i.getScore() == score) {
    	        returnList.add(i.getName());
    	      }
    	    }
    	    return(returnList);
    	  }
    	}


