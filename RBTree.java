import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Han Yu Foong
 *
 * @param <T extends Comparable<T>>
 */
public class RBTree<T extends Comparable<T>> 
	extends RedBlackTree<T> implements RBTreeInterface<T>{
	
	/**
	 * method to give top x
	 * 
	 * @return a list of Newnodes with the highest data values
	 */
	@Override
	public List<T> Top(int topNum) throws IllegalArgumentException {
		if(topNum < 1 ) { throw new IllegalArgumentException("input can't be less than 1"); }
		else if (topNum > size) { throw new IllegalArgumentException("Input bigger than list"); }
		
		int newSize = size - topNum; // size of selected
		List<T> temp = InOrderList();// copies inorderlist
		for(int i = 0; i < newSize; i++)
		    temp.remove(0);//removes the first in the list, which is the smallest value
		return temp;// left with the highest values in the list
	}
	
	/**
	 * method to give low x
	 * 
	 * @return a list of NewNodes with the lowest data values
	 */
	@Override
	public List<T> Low(int lowNum) throws IllegalArgumentException{
		if(lowNum < 1 ) { throw new IllegalArgumentException("input can't be less than 1"); }
		else if (lowNum > size) { throw new IllegalArgumentException("Input bigger than list"); }
		
		List<T> temp = new ArrayList<T>();
		for(int i = 0; i < lowNum; i++)
		    temp.add(i, InOrderList().get(i));// adds in the value of the least
		return temp;// left with the smallest values in the list
	}

	/**
	 * method to give complete list
	 * 
	 * @return a list of data with words of type List<T>
	 */
	@Override
	public List<T> InOrderList() {// code logic was borrowed from RedBlackTree.java
		List<T> arrList = new ArrayList<T>();
		if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    arrList.add(popped.data);
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
		return arrList;	
	}
	
	/**
	 * method to get a word from NewNode
	 */
	
	public String getWordfromNode(StudentInterface input) {
		return input.getName(); 
	}
	
	/**
	 * method to get data value from NewNode
	 */
	
	public int getDatafromNode(StudentInterface input) {
		return input.getScore(); 
	}
	
	/**
	 * main method for in class testing ground
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		StudentInterface s1 = new StudentDW("Han Tu", "100");
		StudentInterface s2 = new StudentDW("Han Yu", "101");
		StudentInterface s3 = new StudentDW("Sarah", "0");
		RBTree<StudentInterface> ST = new RBTree<StudentInterface>();//NewNode not the same as Node
		ST.insert(s1);ST.insert(s2);ST.insert(s3);
		System.out.println(ST.InOrderList());
		for (StudentInterface i: ST.InOrderList()) {
			System.out.print(i.getName() + "\n");
			System.out.print(i.getScore() + "\n");
		}
		System.out.println("the test method " + ST.getDatafromNode(s1));
		System.out.println(ST.Top(1).get(0).getScore());
		System.out.println(ST.Low(3).get(2).getScore());// top 3 lowest students, gets the score of the last student of the list
	}

}

