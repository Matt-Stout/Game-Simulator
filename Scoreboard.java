/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          (p2)
// FILE:             (Scoreboard.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class implements the ScoreboardADT and contains a single ListADT 
 * that contains only jobs that are completed. Jobs can be added at the end of the list
 * including a method for printing out the current status of the Scoreboard object. 
 * this class can display the total score accumulated at the end of the game.
 *
 * @author Ruimin Zhang, Christian Farris, Matthew Stout 
 */
public class Scoreboard implements ScoreboardADT {
	JobList list = null;

	/**
	 * Constructor for the Scoreboard class
	 * 
	 */
	public Scoreboard() {
		//initialization
		list = new JobList();
	}

	/**
	 * Calculates the total points earned for every job in the scoreboard.
	 * 
	 * @return totalScore total points earned for every job in the scoreboard
	 */
	public int getTotalScore() {
		int totalScore = 0;

		//for each
		for(Job job : list) {
			totalScore += job.getPoints();
		}

		return totalScore;
	}

	/**
	 * Add a completed job at the end of the scoreboard
	 * 
	 * @param job
	 *            a completed job that needs to be added to the end of
	 *            scoreBoard
	 */
	public void updateScoreBoard(Job job) {
		list.add(job);
		
	}

	/**
	 * display all the jobs completed, 
	 * showing its name and points earned
	 * 
	 */
	public void displayScoreBoard() {
		//print out title
		System.out.println("The jobs completed: ");

		//list all completed jobs
		for(Job j : list) {
			System.out.println("Job Name: " + j.getJobName());
			System.out.println("Points earned for this job: " + j.getPoints());
			System.out.println("--------------------------------------------");
		}
	}

}
