/**
 * 
 */
package raffle;

import java.util.Map;
import java.util.Scanner;

/**
 * A simulator for the running of a raffle. 
 * This simulator uses a text-based user interface (TUI). 
 * It simulates:
 *  - the setting up of a raffle and its prizes,
 * 	- making a number of transaction (i.e. selling raffle tickets),
 *  - the announcement of winners. 
 *  
 * @author S H S Wong
 * @version 27-09-2020
 */
public class TUIRaffle {

	private Raffle raffle;
	private Scanner keyboard;
		
	/**
	 * Constructor
	 * @param raffle
	 */
	public TUIRaffle(Raffle raffle) {
		
		this.raffle = raffle;
		
		// create a Scanner object for obtaining input from keyboard
		keyboard = new Scanner(System.in);
		
		setUpPrizes();
	}
	
	/*
	 * Set up all the prizes for this raffle
	 */
	private void setUpPrizes() {
		
		String more = "yes";

		System.out.println("Enter the details of the prizes for this raffle.");
		
		/*
		 * Prompt the user to enter the details of each prize.
		 * This routine will continue until the user said that 
		 * 	no more prizes is to be entered. 
		 */
		while ((more.trim().toLowerCase()).equals("yes")) {
			
			System.out.println("Enter the details of a prize... ");
			System.out.print("Name of prize: ");
			String name = keyboard.nextLine();
			String prompt = "How much in £ does it worth? ";
			System.out.println(prompt);
			int value = getInt(prompt);
			raffle.addPrize(name, value);
			
			// Ask the user if more prize is to be added.
			System.out.print("More prizes (yes or no)? ");
			more = keyboard.nextLine();
		}
	}

	/**
	 * Sell tickets to a buyer.
	 * The buyer can buy many tickets in a single transaction.
	 */
	public void sellTickets() {
		
		System.out.println("What is your name? ");
		String buyer = keyboard.nextLine();
		
		String prompt = "How many tickets do you want to buy? ";
		System.out.println(prompt);
		int howMany = getInt(prompt);
		
		// Sell multiple tickets...
		while (howMany > 0) {
			raffle.sellTicket(buyer);
			howMany--;
		}
	}
	
	/**
	 * Displays the results to the console.
	 */
	public void results() {
		// Announce the title of the raffle...
		System.out.println(raffle.title() + '\n' + "The winners are... ");
		
		// Get info about who has won what.
		Map<Prize, Ticket> winners = raffle.luckyDraw();
		
		/* !!!! Make use of an enhanced for statement to obtain the 
		 * prizes and their winners one-by-one and output an announcement 
		 * for each prize and its winner. 
		 */
		for(Map.Entry<Prize, Ticket> entry : winners.entrySet()) {
			Prize p = entry.getKey();
			Ticket t = entry.getValue();
			
			System.out.println(p.toString() + ", Won By: " + t.buyer());
		}
		
		
		System.out.println("Many Congratulations!!");
	}

	/*
	 * Get an integer from the specified input medium.
	 * This method will persist until the input is an integer.
	 * 
	 * @param prompt	a message to prompt for the input 
	 */
	private int getInt(String prompt) {
		try {
			return Integer.parseInt(keyboard.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("The value must be a whole number." + prompt);
			return getInt(prompt);
		}
	}
	
	/**
	 * Simulates the running of a raffle
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Set up the raffle
		System.out.println("> Setting up the raffle...");
		TUIRaffle tui = new TUIRaffle(new Raffle("Cancer Research"));
		
		// Simulate selling tickets in 5 transactions
		System.out.println("> Selling tickets...");
		for (int i = 0; i < 5; i++) {
			tui.sellTickets();
		}
		
		// Draw prizes
		System.out.println("> Drawing prizes...");
		tui.results();
	}
}
