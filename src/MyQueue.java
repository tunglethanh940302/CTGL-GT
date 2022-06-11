import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {

	Queue<Node> list;

	public MyQueue() {
		this.list = new LinkedList<Node>();
	}
	
	
	public boolean isEmpty() {
		return list == null;
	 }
	
	public Node dequeue() {
		
		if(isEmpty()) {
			System.out.println("List is empty");
			return null;
		}
		
		Node newNode = this.list.poll();
		return  newNode;
		
	 }
	
	public void enqueue(Node p) {
		
		this.list.add(p);
		
	}
	
	 public Person front() {

		 return null;
	 }
}
