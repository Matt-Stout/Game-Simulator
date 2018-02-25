/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          (p2)
// FILE:             (Game.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
///////////////////////////// 80 columns wide //////////////////////////////////

/**
 * The class contains most methods to implement the game logic. Each time the
 * job is executed, it determines whether it should update scoreboard as well
 * as deduct time penalty. It also has the functionality to determine whether 
 * the game is over or not.
 * 
 * @author Chen Yang
 */
public class Game {

    /**
     * A list of all jobs currently in the queue.
     */
    private ListADT<Job> list;

    /**
     * Whenever a Job is completed it is added to the scoreboard
     */
    private ScoreboardADT scoreBoard;
    private int timeToPlay;
    private JobSimulator jobSimulator;

    /**
     * Constructor. Initializes all variables.
     * @param seed
     * seed used to seed the random number generator in the Jobsimulator class.
     * @param timeToPlay
     * duration used to determine the length of the game.
     */
    public Game(int seed, int timeToPlay){
    	//initializations
    	this.list = new JobList();
    	this.timeToPlay = timeToPlay;
    	this.jobSimulator = new JobSimulator(seed);
    	this.scoreBoard =  new Scoreboard();
    }

    /**
     * Returns the amount of time currently left in the game.
     * @returns the amount of time left in the game.
     */
    public int getTimeToPlay() {
        return timeToPlay;
    }

    /**
     * Sets the amount of time that the game is to be executed for.
     * Can be used to update the amount of time remaining.
     * @param timeToPlay
     *        the remaining duration of the game
     */
    public void setTimeToPlay(int timeToPlay) {
    	this.timeToPlay = timeToPlay;
    }

    /**
     * States whether or not the game is over yet.
     * @returns true if the amount of time remaining in
     * the game is less than or equal to 0,
     * else returns false
     */
    public boolean isOver(){
        return timeToPlay <= 0;
    }

    /**
     * This method simply invokes the simulateJobs method
     * in the JobSimulator object.
     */
    public void createJobs(){
    	jobSimulator.simulateJobs(list, timeToPlay);
    }

    /**
     * @returns the length of the Joblist.
     */
    public int getNumberOfJobs(){
        return list.size();
    }

    /**
     * Adds a job to a given position in the joblist.
     * Also requires to calculate the time Penalty involved in
     * adding a job back into the list and update the timeToPlay
     * accordingly
     * @param pos
     *      The position that the given job is to be added to in the list.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(int pos, Job item){
        //deduct time penalty
        timeToPlay -= pos;
        //add item to the pos
    	list.add(pos, item);
    }

    /**
     * Adds a job to the joblist.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(Job item){
        //deduct time penalty
        timeToPlay -= list.size();
        //add item to the end of list
    	list.add(item);
    }

    /**
     * Given a valid index and duration,
     * executes the given job for the given duration.
     *
     * This function should remove the job from the list and
     * return it after applying the duration.
     *
     * This function should set duration equal to the
     * amount of time remaining if duration exceeds it prior
     * to executing the job.
     * After executing the job for a given amount of time,
     * check if it is completed or not. If it is, then
     * it must be inserted into the scoreBoard.
     * This method should also calculate the time penalty involved in
     * executing the job and update the timeToPlay value accordingly
     * @param index
     *      The job to be inserted in the list.
     * @param duration
     *      The amount of time the given job is to be worked on for.
     */
    public Job updateJob(int index, int duration){
        //time penalty
        timeToPlay -= index;

        //if timeToPlay is greater than duration
    	if(duration > timeToPlay) {
    		duration = timeToPlay;
    	}
    	
    	//get the job
    	Job currJob = list.remove(index);
    	//execute curr job
        if(currJob.getSteps() + duration <= currJob.getTimeUnits()) {
    	    currJob.setSteps(currJob.getSteps() + duration);
        }
        else {
            currJob.setSteps(currJob.getTimeUnits());
        }
    	//find out whether is completed
    	if(currJob.isCompleted()) {
    		scoreBoard.updateScoreBoard(currJob);
    	}
    	
    	//calc remaining time
    	timeToPlay -= duration;
	
        return currJob;
    }

    /**
     * This method produces the output for the initial Job Listing, IE:
     * "Job Listing
     *  At position: job.toString()
     *  At position: job.toString()
     *  ..."
     *
     */
    public void displayActiveJobs(){
    	System.out.println("Job Listing");
    	//print out each line
    	for(int  i = 0; i < list.size(); i++) {
    		Job curr = list.get(i);
    		//get position
    		String info = "At position: " + i + " ";
    		//get job details
    		info += curr.toString();
    		//print out info
    		System.out.println(info);
    	}
    }

    /**
     * This function simply invokes the displayScoreBoard method in the ScoreBoard class.
     */
    public void displayCompletedJobs(){
    	scoreBoard.displayScoreBoard();
    }

    /**
     * This function simply invokes the getTotalScore method of the ScoreBoard class.
     * @return the value calculated by getTotalScore
     */
    public int getTotalScore(){
        return scoreBoard.getTotalScore();
    }
}
