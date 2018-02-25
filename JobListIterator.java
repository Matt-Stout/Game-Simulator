/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          (p2)
// FILE:             (JobListIterator.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Instances of this class are used to iterate through a chain of nodes.  
 * The iterator has direct access to the JobList’s chain of nodes. 
 *
 * @author Christian Farris, Ruimin Zhang
 */
public class JobListIterator implements Iterator<Job>{

	private Listnode<Job> curr;
	
	/**
	 * (This initializes a new node. It has it point to the header node
	 * of the linked list.)
	 * 
	 * @param (Listnode<Job> head) (The header node of the linked list.)
	 */
	public JobListIterator(Listnode<Job> head){
		curr = head;
	}
	
	/**
	 * (Checks if there is another node in the list.)
	 * 
	 * @return (Returns true if node n references points to another node. 
	 * 			if not it returns false.)
	 */
	@Override
	public boolean hasNext() {
		return curr.getNext() != null;
	}

	/**
	 * (This method returns the next Job object in the list and advances n.
	 * If there is not another item in the list it throws a NoSuchElementException
	 * Exception.)
	 * 
	 * @return (Returns the next Job object in the list.)
	 */
	@Override
	public Job next() {
		if(hasNext()){
			Job x = curr.getNext().getData();
			curr = curr.getNext();
			return x;
		}
		else{
			throw new NoSuchElementException();
		}
	}
}
