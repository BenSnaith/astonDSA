/**
 * 
 */
package raffle;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to model a raffle.
 * To facilitate the lucky draw, a raffle uses two separate boxes to
 * keep its prizes and sold tickets.
 * 
 * @author S H S Wong
 * @version 27-09-2020
 */
public class Raffle {

	private String title;
	private Box<Prize> prizeBox;	
	private Box<Ticket> ticketBox;
	
	public Raffle(String title) { 
		this.title = "Raffle for " + title;
		
		prizeBox = new Box<>();
		ticketBox = new Box<>();		
	}

	/** 
	 * Returns the title (i.e. purpose) of this raffle.
	 * @return
	 */
	public String title() {
		return title;
	}
	
	/**
	 * Add a prize to the prize box.
	 * @param name
	 * @param value
	 */
	public void addPrize(String name, int value) {
		prizeBox.put(new Prize(name, value));
	}
	
	/**
	 * Sell a ticket to the buyer.
	 * @param buyer	the buyer's name.
	 */
	public void sellTicket(String buyer) {
		ticketBox.put(new Ticket(buyer));
	}
	
	/**
	 * Perform the lucky draw.
	 * For each prize in the raffle, 
	 * 	find a winner by drawing a ticket from the box of sold tickets.
	 * If there are more prizes than number of tickets being sold,
	 * 	some prizes will be left behind.
	 *   
	 * @return a mapping between prizes and winning tickets 
	 * 		(each prize is associated with one winning ticket) 
	 */
	public Map<Prize, Ticket> luckyDraw() {
		// Creates a mapping of which prize is won by whom.
		Map<Prize, Ticket> winners = new HashMap<Prize, Ticket>();
		
		for(Prize p : prizeBox) {
			if(!ticketBox.isEmpty()) {
				winners.put(p, ticketBox.draw());
			}
		}
		return winners;
	}

}
