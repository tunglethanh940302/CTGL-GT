import java.util.LinkedList;

public class MyStack {

	LinkedList list;
	
	public MyStack() {
		list = null;
	}
	
	public boolean isEmpty() {
		return list == null;
	}
	
	public void push(Node node) {
		list.add(node);
	}
	
	public Node pop() {
		return (Node) list.pollLast();
	}	
	
	
	
	
	
	
}
