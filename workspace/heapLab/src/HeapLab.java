class MaxHeap<T extends Comparable<T>> {
    private T[] heap = null;  // array storing heap elements
    private int size = 0;     // number of elements
      
    public void set(int index, T value) {
        // TODO A: set the value of a heap element
        if(index >= size) return;
        int par=(index-1)/2;
    	int cl=(2*index)+1;
    	int cr=cl+1;
    	heap[index]=value;
    	if(heap[index].compareTo(heap[par])>0) bubbleUp(index);
    	else if(index<size/2) {
    		if(heap[index].compareTo(heap[cl])<0 || heap[index].compareTo(heap[cr])<0) bubbleDown(index);
    	}
    	
    	
    }
    
    public void bubbleUp(int index) {
        // TODO B: recursive bubbleUp
    	if(index<=0) return;
        int par=(index-1)/2;
        T elem = heap[index];
        T parelem = heap[par];
        if(elem.compareTo(parelem)>0) {
        	heap[index]=heap[par];
        	heap[par]=elem;
        	bubbleUp(par);
        }
        return;
    }

    
    public void bubbleDown(int index) {
        // TODO C: recursive bubbleDown
    	if(index<0 || index>(size/2)-1)return;
    	int largerChild;
    	int left=(2*index)+1;
    	int right=left+1;
    	T elem=heap[index];
    	if(right<size && heap[right].compareTo(heap[left])>0) {
    		largerChild=right;
    	}
    	else {
    		largerChild=left;
    	}
    	if(elem.compareTo(heap[largerChild])<0) {
    		heap[index]=heap[largerChild];
    		heap[largerChild]=elem;
    		bubbleDown(largerChild);
    	}
    	return;
    	
    }

    // ==========================================
    // The methods below are provided for testing
    // purpose. You do NOT need to modify any of 
    // them. Feel free to add your own tests.
    // ==========================================
    public MaxHeap(int cap) { // constructor
        heap = (T[]) new Comparable[cap];
    }
    
    public void enqueue(T e) {
        // For now we'll just assume the capacity is
        // large enough so no need to expand array.
        heap[size++] = e;
        bubbleUp(size-1);
    }
    
    public T dequeue() {
        if(size==0) return null;
        T root = heap[0];
        heap[0] = heap[--size];
        bubbleDown(0);
        return root;
    }
    
    public void print() {
        for(int i=0;i<size;i++) System.out.print(heap[i]+" ");
        System.out.println();
    }
}

public class HeapLab {
    public static void main(String args[]) {
      MaxHeap<Integer> h = new MaxHeap<Integer>(100);
      System.out.println("===Test bubbleUp===");
      h.enqueue(10);
      h.enqueue(20);
      h.enqueue(30);
      System.out.println("Expected: 30 10 20");
      System.out.print("Actual:   ");h.print();
      h.enqueue(35);
      h.enqueue(25);
      h.enqueue(15);
      System.out.println("Expected: 35 30 20 10 25 15");
      System.out.print("Actual:   ");h.print();
      h.enqueue(50);
      System.out.println("Expected: 50 30 35 10 25 15 20");
      System.out.print("Actual:   ");h.print();
      System.out.println("===Test bubbleDown===");
      h.dequeue();
      System.out.println("Expected: 35 30 20 10 25 15");
      System.out.print("Actual:   ");h.print();
      h.dequeue();
      System.out.println("Expected: 30 25 20 10 15");
      System.out.print("Actual:   ");h.print();
      System.out.println("===Test bubbleUp and Down===");
      h.enqueue(21);
      h.dequeue();
      System.out.println("Expected: 25 20 21 10 15");
      System.out.print("Actual:   ");h.print();   
      h.enqueue(30);
      h.enqueue(35);
      h.enqueue(40);
      h.enqueue(45);
      h.enqueue(50);
      System.out.println("Expected: 50 45 30 35 40 21 25 10 20 15");
      System.out.print("Actual:   ");h.print(); 
      h.set(0, 5);
      System.out.println("Expected: 45 40 30 35 15 21 25 10 20 5");
      System.out.print("Actual:   ");h.print(); 
      h.set(4, 50);
      System.out.println("Expected: 50 45 30 35 40 21 25 10 20 5");
      System.out.print("Actual:   ");h.print(); 
    }
}