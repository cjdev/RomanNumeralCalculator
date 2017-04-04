
public class Stack<T> {

	private Node<T> top;
	private int size;
	
	public Stack(){
		top = null;
		size = 0;
	}
	
	public void push(T data){
		Node<T> node = new Node<T>(data);
		if(top == null){
			top = node;
			size++;
		}
		else{
			node.setNextNode(top);
			top = node;
			size++;
		}
	}
	
	public T getTop(){
		return top.getData();
	}
	
	public int getSize(){ return size; }
	
	public T pop(){
		if(top != null){
			Node<T> node = top;
			T data = top.getData();
			top = top.getNextNode();
			node = null;
			size--;
			return data;
		}
		return null;
	}
	
	public boolean isEmpty(){
		if(size == 0) return true;
		return false;
	}
	
	private class Node<G>{
		
		private G data;
		private Node<G> next;
		
		public Node(G data){
			this.data = data;
			next = null;
		}
		
		public G getData(){ return data; }
		public void setData(G data){ this.data = data;}
		public Node<G> getNextNode(){ return next; }
		public void setNextNode(Node<G> next){ this.next = next;}
	}
	
}
