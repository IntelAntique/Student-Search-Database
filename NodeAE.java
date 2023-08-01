
/**
 * 
 * Node class to store data integers labeled with a data string
 * 
 * @author Han Yu Foong
 *
 */
public class NodeAE implements NewNode{

	private int data;
	private String word;
	
	public NodeAE(String word ,int data) {
		this.data = data;
		this.word = word;
	}
	
	@Override
	public int compareTo(NewNode o) {
		return this.data - o.getData();
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public int getData() {
		return data;
	}
	
}
