import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void showMenu() {														// hàm hiển thị menu

	     System.out.println("Choose one of this options:");
	     System.out.println("Person Tree:");
	     System.out.println("1. Insert a new Person.");
	     System.out.println("2. Inorder traversal");
	     System.out.println("3. Breadth-First Traversal");
	     System.out.println("4. Search by Person ID");
	     System.out.println("5. Delete by Person ID");
	     System.out.println("6. Balancing Binary Search Tree ");
	     System.out.println("7. DFS_Graph");
	     System.out.println("8. Dijkstra");
	     System.out.println("9. BFS_Graph");
	     System.out.println("10. Preorder traversal");
	     System.out.println("11. Postorer traversal");
	     System.out.println("Exit:");
	     System.out.println("0. Exit");

	   }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		MyBSTree myBST = new MyBSTree();								// khởi tạo đối tượng MyBSTree
		Graph myGraph = new Graph();									// khởi tạo đối tượng Graph
		do {
			showMenu();													// hiển thị menu		
			choice = sc.nextInt();										// nhập lựa chọn của người dùng
			
			switch (choice) {											// sử dụng switch-case để thực hiện chương trình
			case 1:
				Person newPerson = myBST.inputPerson();
				myBST.root = myBST.insert(myBST.root, newPerson);
				break;
			case 2:
				myBST.inOder(myBST.root);
				break;
			case 3:
				myBST.bft();
				break;
			case 4:
				myBST.searchByID();
				break;
			case 5:
				System.out.print("Input ID to delete: ");
				String id = sc.next();
				myBST.root = myBST.deleteByID(myBST.root, id);
				break;
			case 6:
				myBST.root = myBST.root = myBST.buildTree(myBST.root);
				break;
			case 7:
				myGraph.dfs('A');
				break;
			case 8:
				myGraph.loadData("MaTran.txt");
				myGraph.displayWeights();
				myGraph.dijkstra(0, 4);
				break;
			case 9:
				myGraph.bfs('A');
				break;
			case 10:
				myBST.preOder(myBST.root);
				break;
			case 11:
				myBST.postOder(myBST.root);
				break;
			default:
				break;
			}
		}while(choice != 0);
	}
}
