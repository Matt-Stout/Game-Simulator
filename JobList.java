/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          (p2)
// FILE:             (JobList.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;

/**
 * (Class implements ListADT as a singly-linked chain of 
 * Listnode<Job> nodes. The linked list uses a header node
 * and does not include a tail reference. ListADT extends
 * Iterable so this class also implements the methods required
 * to implement it. The linked list stores all active jobs.)
 *
 * @author Ruimin Zhang, Christian Farris, Matthew Stout 
 */
public class JobList implements ListADT<Job>{
	
	// Creates header node.
	private Listnode<Job> head;
	// Creates int to store number of items in list
	private int numJobs;
	
	/**
	 * (This is the constructor for JobList. It initializes
	 * field variabls head and numJobs. Head is set to a 
	 * new Listnode initialized with null arguments. numJobs
	 * is initalialy set to 0.)
	 */
	public JobList() {
		head = new Listnode<Job>(null,null);
		numJobs = 0;
	}
	
	/**
	 * (This method creates an iterator for Joblist.)
	 * 
	 * @return (A new iterator for Joblist.)
	 */
	@Override
	public Iterator<Job> iterator() {
		return new JobListIterator(head);
	}

	/**
	 * (This method traverses through the linked list. Upon reaching the lists
	 * end it sets the last node in the list to point to a new node that contains
	 * the Job object named item parameter. Finally numJobs is incremented due to
	 * the new item being added to the list.)
	 *
	 * PRECONDITIONS: (Job item is not equal to null or else an 
	 * 					IllegalArgumentException Exception will be thrown.)
	 * 
	 * POSTCONDITIONS: (The Job object item has been successfully added to
	 * 					the list.)
	 *
	 * @param (Job item) (A Job object named item to be added to the list.)
	 */
	@Override
	public void add(Job item) {
		// Checks to ensure item is not null.
		if(item == null) 
			throw new IllegalArgumentException();
		
		// Creates a new node to store the Job object to be added to the list.
		Listnode <Job> newNode = new Listnode<Job>(item);
		
		// Special Case: If the list is empty it sets the header node to point
		//				 to the new node containing the Job object being added
		if(head.getNext() == null){
			head.setNext(newNode);
		}
		else {
			// Creates a new node to be used to traverse through the list.
			Listnode<Job> n = head;
			// As long as there are more items in the list it will continue to traverse it.
			while(n.getNext() != null)
				// Sets n to the next node in the list.
				n = n.getNext();
			// Sets the last node in the list to point to the new node containing the Job object.
			n.setNext(newNode);
		}
		// Increment numJobs.
		numJobs++;
	}

	/**
	 * (This method traverses through the linked list until it reaches
	 * the position specified through the int parameter pos. Once it 
	 * reaches the specified position it creates a new node. It initializes
	 * its data to that of the Job object named item passed through as a
	 * parameter and it has it point to the item in the position it will be 
	 * set to. Next it sets the node at the index before where it will be to
	 * point to it. Then numJobs is incremented due to the additional item
	 * being added to the list.)
	 *
	 * PRECONDITIONS: (The position is greater than or equal to zero and is less
	 * 					than or equal to numJobs. The Job object passed through
	 * 					is also not null.)
	 * 
	 * POSTCONDITIONS: (Job object has been inserted into the list. The item
	 * 					before it points to it and it points to the item previously
	 * 					in its position.)
	 *
	 * @param (int pos) (The position to insert the Job object item.)
	 * @param (Job item) (A Job object named item to be added to the list.)
	 */
	@Override
	public void add(int pos, Job item) {
		// Checks for bad position.
		if(pos<0 || pos>numJobs){
			throw new IndexOutOfBoundsException();
		}
		// Checks to ensure item is not null.
		if(item == null) 
			throw new IllegalArgumentException();
		
		// Special Case; if the position specified is at the end of the list it uses
		//				 the other method.
		if(pos==numJobs){
			add(item);
			return;
		}
		
		// Creates a new node to be used to traverse the list.
		Listnode<Job> n = head;
		// Traverses through list to the specified position.
		for(int i=0; i<pos; i++){
			// Sets n to point to the next node in the list.
			n=n.getNext();
		}
		// Inserts a new node into the position having it point to the item that was
		// previously in it position and having the item before it's position in the 
		// list point to it.
		n.setNext(new Listnode<Job>(item, n.getNext()));
		
		// Increments numJobs.
		numJobs++;
		
	}
	
	/**
	 * (This method traverses through the list to check if the Job
	 * item passed through the method is already in the list or not.)
	 *
	 * PRECONDITIONS: (Parameter Job object named item is not null.)
	 *
	 * @param (Job item) (A Job object named item to be added to the list.)
	 * @return (If the Job object item is in the list true is
	 * 					returned, if not false is returned.)
	 */
	@Override
	public boolean contains(Job item) {
		
		// Checks to ensure item is not null.
		if(item == null) 
			throw new IllegalArgumentException();
		
		// Creates node to be used to increment through list.
		// Sets node to first node following the header node.
		Listnode<Job> n = head.getNext();
		
		//Increments through the list.
		while(n.getNext() != null){
			// Compares the data of the node referenced by n to data of item.
			// If the data matches it returns true.
			if(n.getNext().getData().equals(item)){
				return true;
			}
			// Sets n to the next node in the list.
			n = n.getNext();
		}
		//If no data of nodes in the list matches item, it returns false.
		return false;
	}
	
	/**
	 * (The method traverses through the list to get the data of a node
	 * at a certain position in the list.)
	 *
	 * PRECONDITIONS: (The int passed through is greater than or equal to
	 * 					zero or is less than or equal to numJobs.)
	 *
	 * @param (int pos) (The position of the node in the list to be returned.)
	 * @return (Returns the data of the node at the specified position.)
	 */
	@Override
	public Job get(int pos) throws IndexOutOfBoundsException{
		// Checks to see if pos is greater than or equal to zero.
		// or if it is less than or equal to numJobs.
		if( pos < 0 || pos > numJobs)
			throw new IndexOutOfBoundsException();
		
		// Creates a new node to be used to traverse the list.
		Listnode<Job> n = head;
		
		// Traverses the list until it reaches index int pos.
		for(int i=0; i<=pos; i++){
			n = n.getNext();
		}
		
		// Returns the data of the node at index int pos.
		return n.getData();
	}

	/**
	 * (This method returns true if numJobs is set to 0, this means there are 
	 * no items currently in the list. It returns false if numJobs is not zero, 
	 * meaning there are items in the list.)
	 *
	 * @return (Returns true if the list is empty. Returns false if it is not.)
	 */
	@Override
	public boolean isEmpty() {
		return numJobs==0;
	}

	/**
	 * (This method creates a new node to be used to traverse through the list.
	 * Then it traverses through the list until it reaches int pos. 
	 * It creates a Job object to store the data from the node at int pos.
	 * Then it sets the node before int pos to point to the node at pos+1. This
	 * effectively removes the node at int pos from the list. Then numJobs is 
	 * decremented due to the item being removed from the list.)
	 *
	 * PRECONDITIONS: (Int pos is greater than or equal to zero and is less
	 * 					than or equal to num Items.)
	 * 
	 * POSTCONDITIONS: (The node at int pos has been removed from the list.)
	 *
	 * @param (int pos) (This int variable specifies the position of the node
	 * 					that will be removed from the list.)
	 * @return (Returns the Job object that was removed from the list.)
	 */
	@Override
	public Job remove(int pos) throws IndexOutOfBoundsException{
		//checks for bad position
		if(pos<0 || pos>=numJobs){
			throw new IndexOutOfBoundsException();
		}
		
		// Creates new node set to point to head.
		// Node will be used to traverse list.
		Listnode<Job> n = head;
		
		// Traverses through list.
		for(int i=0; i<pos; i++){
			// Sets n to point to next node in the list.
			n = n.getNext();
		}
		
		// Has tmp store the data in node at int pos.
		Job tmp = n.getNext().getData();
		// Removes nod at int pos by having node at int pos-1 point to the node that
		// follows pos, pos+1.
		n.setNext(n.getNext().getNext());
		// Increments numJobs.
		numJobs--;
		// Returns Job object that was removed from the list.
		return tmp;
	}

	/**
	 * (Returns the number of jobs in the list, i.e. the size of the list.)
	 * 
	 * @return (Returns the number of jobs in the list.)
	 */
	@Override
	public int size() {
		return numJobs;
	}

}
