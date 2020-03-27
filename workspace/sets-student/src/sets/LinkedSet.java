package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
	  // TODO (1)
	  int count=0;
	  for(E e: this) {
		  count++;
	  }
	  return count;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
	  return (size()==0);
  }

  @Override
  public Iterator<E> iterator() {
	  return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
	  // TODO (3)
	  for(E e:this) {
		  if(o.equals(e)) {
			  return true;
		  }
	  }
	  return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
	  // TODO (4)
	  if(this.size()>that.size()) return false;
	  if(head==null) {
		  return true;
	  }
	  for(E e:this) {
		  if(!that.contains(e)) {
			  return false;
		  }
	  }
	  return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
	  // TODO (5)
	  if(this.size() < that.size()) return false;
	  for(E e:that) {
		  if(!this.contains(e)) {
			  return false;
		  }
	  }
	  return true;
  }

  @Override
  public Set<E> adjoin(E e) {
	  // TODO (6)
	  if(this.contains(e)) {
		  LinkedSet<E> temp=new LinkedSet<E>(head);
		  return (Set<E>) temp;

	  }
	  LinkedNode<E> newHead= new LinkedNode<E>(e,head);
	  LinkedSet<E> temp=new LinkedSet<E>(newHead);
	  return (Set<E>) temp;
  }

  @Override
  public Set<E> union(Set<E> that) {
	  // TODO (7)
	  LinkedSet<E> temp = this;
	  for(E e:that) {
		  temp = (LinkedSet<E>) temp.adjoin(e);
	  }
	  return (Set<E>) temp;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
	  // TODO (8)
	  LinkedSet<E> temp = this;
	  for(E e:this) {
		  if(!that.contains(e)) {
			  temp=(LinkedSet<E>) temp.remove(e);
		  }
	  }
	  return temp;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
	  // TODO (9)
	  LinkedSet<E> temp=this;
	  for(E e:this) {
		  if(that.contains(e)) {
			  temp=(LinkedSet<E>)temp.remove(e);
		  }
	  }
	  return temp;
  }

  @Override
  public Set<E> remove(E e) {
	  // TODO (10)
	  LinkedSet<E> temp = null;
	  for(E ee:this) {
		  if(!ee.equals(e) && temp==null) {
			  temp=new LinkedSet<E>(ee);
		  }
		  else if(!ee.equals(e)) {
			  temp=(LinkedSet<E>)temp.adjoin(ee);
		  }
	  }
	  if(temp!=null) return temp;
	  else return new LinkedSet<E>();  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
