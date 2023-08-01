public class StudentBD implements StudentInterface{
    private String name;
    private int score;
    
    public StudentBD(String name, int score) {
      this.name = name;
      this.score = score;
    }
  
    public String getName() {
      return(name);
    }
  
    public int getScore() {
      return(score);
    }
    
    //I don't need to implement this for my testing since the node stuff is for a different person
    public int compareTo(StudentInterface studentB) {
      return(10);
    }
}