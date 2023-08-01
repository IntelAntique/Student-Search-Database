import java.util.ArrayList;
import java.util.List;

//PLACEHOLDER UNTIL THE DW GIVE DETAILS ON THEIR IMPLEMENTATION
public class filereader {
  public List<NodeAE> readPostsFromFile(String filename) {
    List<NodeAE> rList = new ArrayList<NodeAE>();
    rList.add(new NodeAE("John",5));
    rList.add(new NodeAE("Mike",2));
    rList.add(new NodeAE("Mike",5));
    rList.add(new NodeAE("Guiseppe",8));
    rList.add(new NodeAE("Asterix",9));
    return(rList);
  }
}