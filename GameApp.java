/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          (p2)
// FILE:             (GameApp.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;


/**
 * This class is responsible for setting up and running the game using the game
 * classes logic.  It handles all user interaction (output and input). 
 *
 * @author Chen Yang
 */

public class GameApp{
    /**
     * Scanner instance for reading input from console
     */
    private static final Scanner STDIN = new Scanner(System.in);
    
    Game game;

    /**
     * Constructor for instantiating game class
     * @param seed: Seed value as processed in command line
     * @param timeToPlay: Total time to play from command line
     */
    public GameApp(int seed, int timeToPlay){
        this.game = new Game(seed, timeToPlay);
        
    }

    /**
     * Main function which takes the command line arguments and instantiate the
     * GameApp class.
     * The main function terminates when the game ends.
     * Use the getIntegerInput function to read inputs from console
     *
     * @param args: Command line arguments <seed> <timeToPlay>
     */
    public static void main(String[] args){

    	int seed = 0; //the seed used to instantiate the GameApp class
    	int time = 0;  //time that will be used to instantiate GameApp class
    	Scanner commandChecker;  //checks to make sure that the args has valid input to instantiate GameApp
    	boolean validArgs = true;  //remains true as long as the arguments are valid
        
        
        //checks the first command line arguments
        
        if (args.length == 2){
        	
        	   commandChecker = new Scanner(args[0]);
    
        	   if (commandChecker.hasNextInt()){
        		   seed = commandChecker.nextInt();

        	   }
        	   else{
        		   validArgs = false;
        	   }
        
        	   commandChecker.close();
        
        	   //checks the second command line arguments
        	   commandChecker = new Scanner(args[1]);
        
        	   if (commandChecker.hasNextInt()){
        		   time = commandChecker.nextInt();

        	   }
        	   else{
        		   validArgs = false;
        	   }
        
        	
        	   commandChecker.close();
        
        	   if (validArgs){
        		   System.out.println("Welcome to the Job Market!");
        		   GameApp gameapp = new GameApp(seed, time);
        		   gameapp.start();
        	   }
        }
    }

    /**
     * This start method handles the game logic.  As long as the game is over, it continues.
     * It prompts the user to select a job to do out of a list and how long they wish to do it.  Then using
     * the game object, it processes this request.  If the task was completed, the total score is displayed,
     * otherwise the user is allowed to reinsert the job back into the list wheree they please.
     */
    private void start(){
    	
    	int task;  //the number task that the user wants to perform
    	int taskTime; //the time the user wants to spend of this task
    	int insertIndex;  //where the user wishes to reinsert a Job 
    	
    	
    	game.createJobs();
    	
    	while (!this.game.isOver()){
    		
    		System.out.println("You have " + this.game.getTimeToPlay() + " left in the game!");
    		this.game.displayActiveJobs();
    		    		
    		System.out.println("");
    		task = getIntegerInput("Select a job to work on: ");
    		
    		taskTime = getIntegerInput("For how long would you like to work on this job?: ");
    		
    		if (task < game.getNumberOfJobs() - 1 && task >= 0){    //makes sure index was valid
    			
    			Job tempJob = game.updateJob(task, taskTime);   //stores the updated time in a temporary job object
    			
    			if (tempJob.isCompleted() == false){  //if the job was not completed
    				insertIndex = getIntegerInput("At what position would you like to insert the job back into the list? ");
    				if (insertIndex >= 0 && insertIndex <= game.getNumberOfJobs()){
    				game.addJob(insertIndex, tempJob);  //if index is valid
    				}
    				else {
    					game.addJob(tempJob);   //if index is invalid
    				}
    			}
    			
    			else{
    				game.createJobs();
    				System.out.println("Job completed! Current Score: " + game.getTotalScore());
    				System.out.println("Total Score: " + game.getTotalScore());
    				game.displayCompletedJobs();
    			}
    			
    		}else{
    			
    			System.out.println("INVALID INDEX");
    		}
    	}
    	System.out.println("Game Over!");
    	System.out.println("Your final score: " + game.getTotalScore());
    	
    }

    /**
     * Displays the prompt and returns the integer entered by the user
     * to the standard input stream.
     *
     * Does not return until the user enters an integer.
     * Does not check the integer value in any way.
     * @param prompt The user prompt to display before waiting for this integer.
     */
    public static int getIntegerInput(String prompt) {
        System.out.print(prompt);
        while ( ! STDIN.hasNextInt() ) {
            System.out.print(STDIN.next()+" is not an int.  Please enter an integer.");
        }
        return STDIN.nextInt();
    }
}