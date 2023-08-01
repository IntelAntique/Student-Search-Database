import java.util.List;

/**
 * interface for RBTree
 * 
 * @author Han Yu Foong
 *
 * @param <T>
 */
public interface RBTreeInterface<T extends Comparable<T>> extends SortedCollectionInterface<T>{
	//public RBTreeInterface();
    public List<T> Top(int x) throws IllegalArgumentException;
    public List<T> Low(int x) throws IllegalArgumentException;
    public List<T> InOrderList();
    public String getWordfromNode(StudentInterface input);
    public int getDatafromNode(StudentInterface input);
}
