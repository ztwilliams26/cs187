package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	File rootNode;
	Queue<File> q;
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if(rootNode==null || !rootNode.exists()) throw new FileNotFoundException();
		this.rootNode=rootNode;
		q=new Queue<File>();
		q.enqueue(rootNode);
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return !q.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
		if(hasNext()) {
			File temp=q.dequeue();
			if(temp.isDirectory()) {
				File[] fileArray = temp.listFiles();
				Arrays.sort(fileArray);
				for(File f : fileArray) {
					q.enqueue(f);
				}
			}
			return temp;
		}
        throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
