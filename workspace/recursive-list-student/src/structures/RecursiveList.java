package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

	private int size;
	private LLNode<T> head;
	
	public RecursiveList() {
		// TODO Auto-generated constructor stub
		size=0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<T>)new ListIterator<T>(this);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// TODO Auto-generated method stub
		if(isEmpty())head=new LLNode<T>(elem,null);
		else {
			LLNode<T> temp=new LLNode<T>(elem,head);
			head=temp;
		}
		size++;
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		// TODO Auto-generated method stub
		if(isEmpty())head=new LLNode<T>(elem,null);
		else {
			LLNode<T> cur= findLast(head);
			LLNode<T> temp=new LLNode<T>(elem, null);
			cur.setNext(temp);
		}
		size++;
		return this;
	}
	
	//Returns the last LLNode<T> in the list.
	private LLNode<T> findLast(LLNode<T> cur) {
		if(cur.next()==null) {
			return cur;
		}
		return findLast(cur.next());
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// TODO Auto-generated method stub
		if(isEmpty())head=new LLNode<T>(elem,null);
		else if(index==0) insertFirst(elem);
		else {
			if(index>0 && !(index>size)) {
				LLNode<T> cur= findBeforeIndex(head, index);
				LLNode<T> temp=new LLNode<T>(elem, cur.next());
				cur.setNext(temp);
			}
			
		}
		size++;
		return this;
	}
	
	//Returns the LLNode<T> before the given index, from index 1 and on.
	private LLNode<T> findBeforeIndex(LLNode<T> cur, int index){
		if(index==1) return cur;
		return findBeforeIndex(cur.next(),index-1);
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		T data=head.data();
		if(isEmpty())return null;
		else {
			head=head.next();
		}
		size--;
		return data;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		T data;
		if(isEmpty())return null;
		else if(size==1) {
			data=head.data();
			head=null;
		}
		else {
			LLNode<T> cur= findBeforeIndex(head, size-1);
			data=cur.next().data();
			cur.setNext(null);
		}
		size--;
		return data;
	}

	@Override
	public T removeAt(int i) {
		// TODO
		T data=null;
		if(isEmpty()||i>=size)return null;
		else if(i==0 && size>1) {
			data=head.data();
			head=head.next();
		}
		else if(i==0 && size==1) {
			data=head.data();
			head=null;
		}
		else {
			LLNode<T> cur= findBeforeIndex(head,i);
			if(size>2) {
				data = cur.next().data();
				cur.setNext(cur.next().next());
			}
			else if(size==2) {
				data = cur.next().data();
				cur.setNext(null);
			}
		}
		size--;
		return data;
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		if(isEmpty())return null;
		return head.data();
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		T data;
		if(isEmpty())return null;
		else {
			LLNode<T> cur= findLast(head);
			data=cur.next().data();
			return data;
		}

	}

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if(isEmpty() || i>=size)return null;
		if(i==0)return head.data();
		else {
			LLNode<T> cur= findBeforeIndex(head,i);
			return cur.data();
		}
	}

	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		if(isEmpty())return false;
		else if(size==1) {
			if(head.data()==elem) {
				head=null;
				return true;
			}
		}
		else {
			if(head.data()==elem) {
				head=head.next();
				return true;
			}
			return remove(elem,head);
		}
		return false;
	}
	private boolean remove(T elem, LLNode<T> cur) {
		if(cur.next().data()==elem) {
			cur.setNext(cur.next().next());
			return true;
		}
		return remove(elem,cur.next());
	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return indexOf(elem,head,0);
	}
	private int indexOf(T elem, LLNode<T> cur, int index) {
		if(elem==cur.data())return index;
		if(index>=size)return -1;
		return indexOf(elem, cur.next(),index+1);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)return true;
		return false;
	}
}

class LLNode<T>{
	private T dta;
	private LLNode<T> lnk;
	public LLNode(T element, LLNode<T> link){
		dta=element;
		lnk=link;
	}
	public T data() {
		return dta;
	}
	public LLNode<T> next(){
		return lnk;
	}
	public void setData(T element) {
		dta=element;
	}
	public void setNext(LLNode<T> link) {
		lnk=link;
	}
}

class ListIterator<T> implements Iterator<T>{

	RecursiveList<T> list;
	public ListIterator(RecursiveList<T> input){
		list=input;
	}
	@Override
	public boolean hasNext() {
		if(!list.isEmpty()) return true;
		return false;
	}
	
	@Override
	public T next() {
		return list.removeFirst();
	}
	
	
}
