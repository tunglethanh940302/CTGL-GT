import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyPerson {

	MyBSTree tree;
	
	public MyPerson() {
		
		tree = new MyBSTree();
	}
	
	public Node insert(Node root, Person person) 
	{
		Node newNode = new Node(person);
		
		if (tree.isEmpty()) {
			root = newNode;
			return root;
		}
		
		String personId = person.getID();
		
		if(personId.compareTo(root.info.getID()) == 0) {
			System.out.println("ID da ton tai");
			return root;
		}else if(personId.compareTo(root.info.getID()) < 0) {
			if(root.left == null) {
				root.left = newNode;
			}else {
				insert(root.left, person);
			}
		}else {
			if(root.right == null) {
				root.right = newNode;
			}else {
				insert(root.right, person);
			}
		}
		
		return root;
	}
	
	public void inOder(Node currentNode) 
	{
		if(currentNode == null) {
			return;
		}
		inOder(currentNode.left);
		
		System.out.println(currentNode.info.toString() + " ");
		
		inOder(currentNode.right);
	}	
		
	
	public void bst() 
	{ 
		Queue<Node> queue = new LinkedList<Node>();
        queue.add(tree.root);
        while (!queue.isEmpty()) {
 
            Node tempNode = queue.poll();
            System.out.println(tempNode.info.toString() + " ");
 
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
	}

	public void searchByID() 
	{
		Scanner sc = new Scanner(System.in);
		
		if(tree.isEmpty()) {
			System.out.println("List is empty");
			return;
		}
		
		System.out.print("Input ID to search: ");
		String pID = sc.next();
		
		Queue<Node> queue = new LinkedList<Node>();
        queue.add(tree.root);
        boolean isFound = false;
        while (!queue.isEmpty()) {
 
            Node tempNode = queue.poll();
            if(pID.compareToIgnoreCase(tempNode.info.getID()) == 0) {
            	System.out.println(tempNode.info.toString());
            	isFound = true;
            	break;
            }
 
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        
        if(!isFound)
        	System.out.println("ID does not exits");
	}
	
	public Node findLeftModeNode(Node root) 
	{
		if(root == null) return null;
		Node leftModeNode = root;
		while(leftModeNode.left != null) {
			leftModeNode = leftModeNode.left;
		}
		return leftModeNode;
	}	

	public Node deleteByID(Node root, String id) 
	{
		if(root == null) {
			return null;
		}
		if(id.compareToIgnoreCase(root.info.getID()) < 0) {
			root.left = deleteByID(root.left, id);
		}else if(id.compareToIgnoreCase(root.info.getID()) > 0) {
			root.right = deleteByID(root.right, id);
		}else {
			// root.val == key >>>xoa root
			// TH1: node xoa la node leaf
			if(root.left == null && root.right == null) {
				return null;
			}
			//TH2 : node xoa co 1 node con ben trai
			if(root.left != null && root.right == null) {
				return root.left;
			}
			//TH2 : node xoa co 1 node con ben phai
			if(root.left == null && root.right != null) {
				return root.right;
			}
			
			//TH3: node xoa co 2 node con
			// tim node trai cung cua cay con ben phai
			Node leftModeNode = findLeftModeNode(root.right);
			root.info.setID(leftModeNode.info.getID());
			root.right = deleteByID(root.right, id);
			
		}
		return root;
	}	
	
	public Node clear(Node root) {
		root = null;
		return root;
	}
	
	public void insertToArrayList(ArrayList<Node> list, Node root) 
	{
		if(root == null) {
			return;
		}
		insertToArrayList(list, root.left);
		list.add(root);
		insertToArrayList(list, root.right);
	}
	
	public Node buildTreeUtil(ArrayList<Node> list, int first, int last) 
	{
		
		if(first > last) {
			return null;
		}
		
		int mid = (first + last) / 2;
		Node root = list.get(mid);
		
		root.left = buildTreeUtil(list, first, mid - 1);
		root.right = buildTreeUtil(list, mid + 1, last);
		
		return root;
	}
	
	public Node buildTree(Node root) 
	{
		ArrayList<Node> list = new ArrayList<Node>();
		insertToArrayList(list, root);
		clear(root);
		int n = list.size();
		
		return buildTreeUtil(list, 0, n-1);
	}	
	
	
	
	
	
	
	
	
	
	
	
}
