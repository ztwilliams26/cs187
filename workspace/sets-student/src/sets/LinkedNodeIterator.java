package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    // TODO (1) define data variables
	LinkedNode curNode;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      // TODO (2) choose appropriate parameters and do the initialization
	  curNode=head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	if(curNode!=null /* && curNode.getNext()!=null */)return true;
    return false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E next() {
    // TODO (4)
	  if(curNode!=null) {      //Missing last object in list... :)
			E tempData=(E) curNode.getData();
			curNode=curNode.getNext();
			return tempData;
	  }
    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
