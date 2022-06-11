import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyBSTree {

	
	public Node root;											// tạo node root
	
	public MyBSTree() {											// constructor
	}
	
	public boolean isEmpty() {									// kiểm tra cây rỗng
		return root == null;
	}
	
	public Person inputPerson() 								// tạo đối tượng Person mới
	{
		Scanner sc = new Scanner(System.in);					// Khởi tạo Scanner sc
			
		Person newPerson = new Person();						// tạo một đối tượng Person mới
		
		System.out.print("Input new ID: ");						// người dùng nhập ID cho person mới 
		newPerson.setID(sc.next());							
		System.out.print("Input Name: ");						// người dùng nhập tên cho person mới	
		newPerson.setName(sc.next());
		System.out.print("Input birhtdayplace: ");				// người dùng nhập nơi sinh sinh cho person mới
		sc.nextLine();
		newPerson.setBirthplace(sc.nextLine());
		System.out.print("Input Birth of Date: ");				// người dùng nhập gày tháng năm sinh  cho person mới
		newPerson.setDob(sc.next());
		
		return newPerson;										// trả về newPerson vừa tạo
	}
	
	public Node insert(Node root, Person person) 				// thêm Person vào tree
	{
		Node newNode = new Node(person);						// tạo node mới 
		
		if (isEmpty()) {										// nếu tree rỗng thì gán node root của tree là node vừa tạo
			root = newNode;
			return root;
		}
		
		String personId = person.getID();						// khai báo biến personId lấy thông tin ID từ person nhập vào
		
		if(personId.compareTo(root.info.getID()) == 0) {		// so sánh với node root  
			System.out.println("ID da ton tai");				// nếu có ID trùng với node root thì thông báo ID đã tồn tại	
			return root;
		}else if(personId.compareTo(root.info.getID()) < 0) {	// nếu ID nhỏ hơn ID node root thì thêm node mới vào bên trái
			if(root.left == null) {
				root.left = newNode;							// nếu node bên trái của node root == null thì thêm node mới vào bên trái node root 
			}else {
				insert(root.left, person);						// nếu tồn tại node bên trái thì đệ quy với node bên trái
			}
		}else {
			if(root.right == null) {
				root.right = newNode;
			}else {
				insert(root.right, person);
			}
		}
		
		return root;											// trả về node root
	}
	
	//Inorder Traversal
	// Left - Node - Right
	public void inOder(Node currentNode) 						// Duyệt trung thứ tự (In-order Traversal)
	{
		if(currentNode == null) {								//  nếu cây rỗng thì return
			return;
		}
		inOder(currentNode.left);								// duyệt 1 cách đệ quy cây con bên trái
		
		System.out.println(currentNode.info.toString() + " ");	// in node currentNode (node root của cây)
		
		inOder(currentNode.right);								// duyệt 1 cách đệ quy cây con bên phải	
	}	
	
	
	public void bft() 											// Duyệt cây BST theo chiều rộng
	{ 
		Queue<Node> queue = new LinkedList<Node>();				// khởi tạo hàng đợi
        queue.add(root);										// thêm node root vào hàng đợi
        while (!queue.isEmpty()) {								// trong khi hàng đợi không rỗng thì 
 
            Node tempNode = queue.poll();						// lấy phần tử đầu tiên ra khỏi hàng đợi	
            System.out.println(tempNode.info.toString() + " ");	// in thông tin ra cửa sổ console
 
            if (tempNode.left != null) {						// thêm node bên trái vào hàng đợi
                queue.add(tempNode.left);
            }
 
            if (tempNode.right != null) {						// thêm node bên phải vào hàng đợi
                queue.add(tempNode.right);
            }
        }
	}
	
	public void searchByID() 									// Tìm kiếm thông tin của nhân viên theo mã nhân viên trong cây BST
	{
		Scanner sc = new Scanner(System.in);					// tạo Scanner sc
		
		if(isEmpty()) {											// nếu cây rỗng thì thông báo "List is empty"			
			System.out.println("List is empty");
			return;
		}
		
		System.out.print("Input ID to search: ");				// yêu cầu người dùng nhập ID cần tìm
		String pID = sc.next();									
		
		Queue<Node> queue = new LinkedList<Node>();				// khởi tạo hàng đợi queue
        queue.add(root);										// duyệt cây BST theo chiều rộng
        boolean isFound = false;								// tạo biên boolean isFound và khởi tạo giá trị false
        while (!queue.isEmpty()) {
 
            Node tempNode = queue.poll();
            if(pID.compareToIgnoreCase(tempNode.info.getID()) == 0) {	// nếu tìm thấy Node có ID trùng với ID cần tìm thì hiển thị thông tin Node đó
            	System.out.println(tempNode.info.toString());
            	isFound = true;											// gán biên isFound = true
            	break;													// thoát khỏi tìm kiếm
            }
 
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        
        if(!isFound)
        	System.out.println("ID does not exits");					// nếu không tìm thấy ID cần tìm thì thông báo "ID does not exits"
	}
	
	
	public Node findLeftMostNode(Node root) 							// tìm node trái cùng
	{
		if(root == null) return null;
		Node leftModeNode = root;
		while(leftModeNode.left != null) {									
			leftModeNode = leftModeNode.left;
		}
		return leftModeNode;											// trả về node bên trái cùng của node root nhập vào
	}	

	public Node deleteByID(Node root, String id) 						// Xóa đi một hồ sơ nhân viên dựa vào ID (Mã số nhân viên) trong cây BST
	{
		if(root == null) {												// nếu cây rỗng thì trả về null
			return null;
		}
		if(id.compareToIgnoreCase(root.info.getID()) < 0) {				// nếu ID nhập vào nhỏ hơn ID của node root thì tiến hành tìm kiếm ở cây con bên trái
			root.left = deleteByID(root.left, id);
		}else if(id.compareToIgnoreCase(root.info.getID()) > 0) {		// nếu ID nhập vào lớn hơn ID của node root thì tiến hành tìm kiếm ở cây con bên phải
			root.right = deleteByID(root.right, id);
		}else {															
			// TH1: node xoa la node leaf
			if(root.left == null && root.right == null) {				// nếu node xóa là node lá thì trả về null
				return null;
			}
			//TH2 : node xoa co 1 node con ben trai
			if(root.left != null && root.right == null) {				// nếu tồn tại node con bên trái thì trả về node bên trái 
				return root.left;
			}
			//TH2 : node xoa co 1 node con ben phai
			if(root.left == null && root.right != null) {				// nếu tồn tại node con bên phải thì trả về node bên phải 
				return root.right;
			}
			
			//TH3: node xoa co 2 node con
			Node leftMostNode = findLeftMostNode(root.right);			// tìm kiếm node trái cùng của cây con bên phải
			Person tempPer = root.info;
			root.info = leftMostNode.info;								// đổi vị trí node root và node trái cùng của cây con bên phải
			leftMostNode.info = tempPer;
			root.right = deleteByID(root.right, id);					// tiến hành xóa node cần xóa theo cây con bên phải
			
		}
		return root;													// trả về node root
	}
	
	public Node clear(Node root) {										// xóa hết cây
		root = null;
		return root;													// trả về node root = null
	}
	
	public void insertToArrayList(ArrayList<Node> list, Node root) 		// tạo hàm insertToArrayList để thêm các node của cây vào array list
	{
		if(root == null) {
			return;
		}
		insertToArrayList(list, root.left);								// duyệt cây theo thứ tự in-oder
		list.add(root);													// thêm vào arraylist
		insertToArrayList(list, root.right);							// list sẽ chứa các node được sắp xếp 
	}
	
	public Node buildTreeUtil(ArrayList<Node> list, int first, int last) 	// tạo cây 
	{
		
		if(first > last) {													
			return null;
		}
		
		int mid = (first + last) / 2;										// chỉ số mid = chỉ số trung gian của mảng 
		Node root = list.get(mid);											// lấy node chính giữa làm node root
		
		root.left = buildTreeUtil(list, first, mid - 1);					// tạo cây con bên trái
		root.right = buildTreeUtil(list, mid + 1, last);					// tạo cây con bên phải
			
		return root;														// trả về node  root
	}
	
	public Node buildTree(Node root) 										// hàm tạo cây mới
	{
		ArrayList<Node> list = new ArrayList<Node>();						// khởi tạo ArrayList
		insertToArrayList(list, root);										// duyệt cây theo thứ tự in-oder và thêm vào arrayList
		clear(root);														// xóa toàn bộ cây cũ
		int n = list.size();	
		
		return buildTreeUtil(list, 0, n-1);									// tạo lại cây mới	
	}
	
	
	public void preOder(Node currentNode) 									// Duyệt cây theo thứ tự preorder (Duyệt tiền thứ tự) 
	{
		if(currentNode == null) {
			return;
		}
		
		System.out.println(currentNode.info.toString() + " ");				// duyệt node root trước
		
		preOder(currentNode.left);											// duyệt cây con bên trái 
		preOder(currentNode.right);											// duyệt cây con bên phải		
	}
	
	public void postOder(Node currentNode) 									// Duyệt hậu thứ tự (Post-order Traversal)
	{
		if(currentNode == null) {
			return;
		}
		postOder(currentNode.left);											// duyệt cây con bên trái 
		
		postOder(currentNode.right);										// duyệt cây con bên phải	
		
		System.out.println(currentNode.info.toString() + " ");				// duyệt node root sau cùng
	}	
	
	
}
