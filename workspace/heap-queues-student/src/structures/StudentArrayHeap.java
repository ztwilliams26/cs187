package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
	
	public StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}
	protected int getLeftChildOf(int index) {
		if(index<0)throw new IndexOutOfBoundsException();
		/*if(heap.size()>index*2+1)*/return index*2+1;
		//return -1;
	}
	protected int getRightChildOf(int index) {
		if(index<0)throw new IndexOutOfBoundsException();
		/*if(heap.size()>index*2+2)*/return index*2+2;
		//return -1;
	}
	protected int getParentOf(int index) {
		if(index<1)throw new IndexOutOfBoundsException();
		return (index-1)/2;
	}
	protected void bubbleUp(int index) {
    	if(index<=0) return;
        int par=getParentOf(index);
        Entry<P,V> elem = heap.get(index);
        Entry<P,V> parelem = heap.get(par);
        if(getComparator().compare(elem.getPriority(),parelem.getPriority())>0) {
        	heap.set(index, parelem);
        	heap.set(par, elem);
        	bubbleUp(par);
        }
        return;
	}
	protected void bubbleDown(int index) {
		if(index<0 || index>(heap.size()/2)-1)return;
    	int largerChild;
    	int left=(2*index)+1;
    	int right=left+1;
    	Entry<P,V> elem=heap.get(index);
    	if(right<heap.size() && getComparator().compare(heap.get(right).getPriority(),heap.get(left).getPriority())>0) {
    		largerChild=right;
    	}
    	else {
    		largerChild=left;
    	}
    	if(getComparator().compare(elem.getPriority(),heap.get(largerChild).getPriority())<0) {
    		heap.set(index, heap.get(largerChild));
    		heap.set(largerChild, elem);
    		bubbleDown(largerChild);
    	}
    	return;
		
	}
	public Iterator<Entry<P, V>> iterator(){
		return heap.iterator();
	}
	
}

