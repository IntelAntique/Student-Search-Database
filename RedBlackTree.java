// --== CS400 Spring 2023 File Header Information ==--
// Name: Han Yu Foong
// Email: hfoong@wisc.edu
// Team: CH
// TA: KARAN GROVER
// Lecturer: Florian Heimerl
// Notes to Grader: *dies from implementing*

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {
	
    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
    	public int blackHeight = 1; //nodes are initialized as null and black with a color of 1
        public T data;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { this.data = data; if(data != null) { this.blackHeight = 0; } }
        // if data isn't a null node, then it inserts a new red node
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    
    /**
     * this method takes a reference to a newly added red node as its only parameter and
     * resolve any red-black tree property violations that are introduced by inserting 
     * each new new node into a red-black tree.
     * 
     * 
     * @param K, name for new node
     * 
     * 
     * @author Han Yu Foong
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> K){
    	Node<T> P = K.context[0];
    	if(P != null && P.context[0] != null) {
	    	if(P.blackHeight == 1) {//parent BLACK
	    		System.out.println("does nothing because parent is black");
	    	} else if(P.blackHeight == 0 && K.blackHeight == 0 && P.context[0] != null){// parent & child RED
	    		if(P.isRightChild()) {// checks if it's right child and finds the left also case 2a
	    			if(P.context[0].context[1] == null) { //when parent's sibling is nonexistent
	    				//K=A, P=B, G=C
	    				rotate(P,P.context[0]);
	    				if(K.context[1] != null) K.context[1].blackHeight = 0; // if left child exists, turn it red
	    				if(K.context[2] != null) K.context[2].blackHeight = 0; // if right child exists turn it red
	    			} else { // parent's sibling is alive
	    				if(P.context[0].context[1].blackHeight == 0) { // when red
//	    					if(K.isRightChild()) {
	    						P.context[0].blackHeight = 0;
		    					P.blackHeight = 1;
		    					P.context[0].context[1].blackHeight = 1;
		    					if(P.context[0].context[0] != null) 
		    						if(P.context[0].context[0].blackHeight == 0) 
		    							enforceRBTreePropertiesAfterInsert(P.context[0]);
		    					
//	    					} else { // new node at left of a parent
//		    					rotate(K, P);
//	    						K.context[0].blackHeight = 0;
//		    					K.blackHeight = 1;
//		    					K.context[0].context[1].blackHeight = 1;
//	    					}
	    				} else { // when black (needs a forced example to run)
	    					if(K.isRightChild()) {
	    						rotate(P, P.context[0]);
		    					P.blackHeight = 1;
		    					P.context[1].blackHeight = 0;
	    						
	    					} else {
		    					rotate(K, P);
	    						rotate(K, K.context[0]);
		    					K.blackHeight = 1;
		    					K.context[1].blackHeight = 0;
	    					}
	    				}
	    				// We also need to make sure that any subtrees of P and S (if S is not null) end up in the appropriate place once the restructuring is done.
	    			}
	    		} else {// if parent is LEFT child
	    			
	    			if( P.context[0].context[2] == null ) { //when parent's sibling is null
	    				rotate(P, P.context[0]);
	    				if(K.context[1] != null) K.context[1].blackHeight = 0;
	    				if(K.context[2] != null) K.context[2].blackHeight = 0;	
	    			} else { // when parent's sibling is black or red
	    				if(P.context[0].context[2].blackHeight == 0) { // when red
//	    					if(K.isRightChild()) { 
//	    						rotate(K, P);
//	    						K.context[0].blackHeight = 0;
//		    					K.blackHeight = 1;
//		    					K.context[0].context[2].blackHeight = 1;
//	    					} else {
		    					P.context[0].blackHeight = 0;
		    					P.blackHeight = 1;
		    					P.context[0].context[2].blackHeight = 1;
		    					if(P.context[0].context[0] != null) 
		    						if(P.context[0].context[0].blackHeight == 0) 
		    							enforceRBTreePropertiesAfterInsert(P.context[0]);
//	    					}
	    				} else { // when black (needs a forced example to run)
	    					if(K.isRightChild()) {
	    						rotate(K, P);
	    						rotate(K, K.context[0]);
		    					K.blackHeight = 1;
		    					K.context[2].blackHeight = 0;
	    					} else {
		    					rotate(P, P.context[0]);
		    					P.blackHeight = 1;
		    					P.context[2].blackHeight = 0;
	    					}
	    				}
	    			}
	    		}
	    	}
    	}
    	this.root.blackHeight = 1;// last statement
    }
    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++; enforceRBTreePropertiesAfterInsert(newNode); return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    	//left rotation
    	if(child.context[0] != parent) throw new IllegalArgumentException("Child and Parent Node unrelated");
        if(child.isRightChild()) {// right child
        	Node<T> Lgrandparent = parent.context[0]; // reference the parent node of parent
        	if(this.root == parent) this.root = child; // root node points to child instead of parent
        	parent.context[2] = child.context[1];// parent points to its child's right grandchild
        	if(child.context[1] != null) child.context[1].context[0] = parent; //if left child exists, it's parent node is the parent parameter instead of child 
        	child.context[1] = parent; // child's left child becomes parent 
        	parent.context[0] = child; // parent node's parent is the child
        	child.context[0] = Lgrandparent; // child's parent is now the grandparent node
        	if(Lgrandparent != null) {// if grandparent node exists 
        		if(Lgrandparent.context[2] == parent) {
        			Lgrandparent.context[2] = child;// grandparent node right child node points from parent to child
        		} else {
        			Lgrandparent.context[1] = child;// grandparent node left child node points from parent to child
        		}
        	}
        }
        	//right rotation
        	else {// left child
        	Node<T> Rgrandparent = parent.context[0];// reference the parent node of parent
        	if(this.root == parent) this.root = child; // root node points to child instead of parent
        	parent.context[1] = child.context[2];// parent points to its child's left grandchild
        	if(child.context[2] != null) child.context[2].context[0] = parent;//if right child exists, it's parent node is the parent parameter instead of child 
        	child.context[2] = parent;// child's right child becomes parent 
        	parent.context[0] = child;// parent node's parent is the child
        	child.context[0] = Rgrandparent;// child's parent is now the grandparent node
        	if(Rgrandparent != null) {// if grandparent node exists 
        		if(Rgrandparent.context[2] == parent) {
        			Rgrandparent.context[2] = child;// grandparent node right child node points from parent to child
        		} else {
        			Rgrandparent.context[1] = child;// grandparent node left child node points from parent to child
        		}
        	}
         }
        }
    
    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }  
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            return true;
        } 
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The replacement
     * node may be null to remove the node from the tree.
     * @param nodeToReplace the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a node with two children.
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null; 
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    
    // Implement at least 3 boolean test methods by using the method signatures below,
    // removing the comments around them and adding your testing code to them. You can
    // use your notes from lecture for ideas on concrete examples of rotation to test for.
    // Make sure to include rotations within and at the root of a tree in your test cases.
    // Give each of the methods a meaningful header comment that describes what is being
    // tested and make sure your test have inline comments to help developers read through them.
    // If you are adding additional tests, then name the method similar to the ones given below.
    // Eg: public static boolean test4() {}
    // Do not change the method name or return type of the existing tests.
    // You can run your tests by commenting in the calls to the test methods 
    
    //run in JUnit configuration
    public static RedBlackTree<Integer> t = null;

    @BeforeEach
    public void createRBT(){
    	t = new RedBlackTree<Integer>();
    }
    
    /**
     * test method for red parent as left child with a red right child
     * 
     * @return true if displays order of 3, 1, 4, 2
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test1() {
    	t.insert(3); t.insert(1); t.insert(4); t.insert(2); //3142 -> 3142
    	t.findNodeWithData(1).blackHeight = 1;
    	System.out.println(t.toLevelOrderString()); //changes sub tree
    	assertEquals(t.toLevelOrderString(), "[ 3, 1, 4, 2 ]");
    }
    
    /**
     * test method for (double) rotate operation and after that, recoloring operation
     * 
     * @return true if displays order of 3, 2, 4, 1
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test2() {
    	t.insert(3); t.insert(1); t.insert(4); //a balanced tree 
    	t.root.context[2].blackHeight = 1 ; // sets node with 4 as black, basically parent's sibling node is black
    	t.insert(2); // double red insertion with a black parent's sibling node
    	System.out.println(t.toLevelOrderString());// should have no change because of only color operation
    	assertEquals(t.toLevelOrderString(), "[ 2, 1, 3, 4 ]");
    }
    
    /**
     * test method for basic whole tree rotation
     * 
     * @return true if displays order of 2, 1, 3
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test3() {
    	t.insert(3); t.insert(2); t.insert(1); //321 -> 213 
    	System.out.println(t.toLevelOrderString()); // rotates entire tree
    	assertEquals(t.toLevelOrderString(), "[ 2, 1, 3 ]");
    }
    
    /**
     * test method for gradescope recolor operation only
     * 
     * @return true if displays level order of 23, 7 , 41, 19
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test4() {
    	t.insert(23); t.insert(7); t.insert(41);  t.insert(19);
    	System.out.println(t.toLevelOrderString());
    	assertEquals(t.toLevelOrderString(), "[ 23, 7, 41, 19 ]");
    }
    
    /**
     * test method for other grade recolor operation
     * 
     * @return true if displays order of level order 23, 7, 41 ,37 
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test5() {
    	t.insert(23); t.insert(7); t.insert(41);  t.insert(37); 
    	System.out.println(t.toLevelOrderString());
    	assertEquals(t.toLevelOrderString(), "[ 23, 7, 41, 37 ]");
    }
    
    
    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    
    public static void main(String[] args) {
//        t.test1();
//        t.test2();
//        t.test3();
//        t.test4();
//        t.test5();
        
    }
    // ask how to make sibling parent black for other operation

}
