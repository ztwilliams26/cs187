package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public Queue() {		
            // TODO 1
		size=0;
		
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
		Queue<T> spinQ = (Queue<T>) other.reversed().reversed();
		head=spinQ.head;
		tail=spinQ.tail;
		size=spinQ.size;
		
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
		if(size==0)return true;
        return false;
	}

	@Override
	public int size() {
            // TODO 4
        return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		Node<T> temp = new Node<T>(element);
		if(isEmpty()) {
			head=temp;
			tail=temp;
		}
		else {
			tail.next=temp;
			tail=temp;
		}
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		if(isEmpty()) throw new NoSuchElementException();
		T element=head.data;
		head=head.next;
		size--;
        return element;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if(isEmpty()) throw new NoSuchElementException();
		else return head.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		Queue<T> temp = new Queue<T>();
		if(!isEmpty())recursiveReverse(temp, head);
        return temp;
	}
	
	
	private UnboundedQueueInterface<T> recursiveReverse(Queue<T> q, Node<T> cur){
		if(cur.next!=null) {
			recursiveReverse(q,cur.next);
		}
		q.enqueue(cur.data);
		return q;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

