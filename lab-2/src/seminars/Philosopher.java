package seminars;
import java.util.*;

/**
 * A philosopher object models limited behaviours of a philosopher.
 * A philosopher can speak. However, when making a speech, 
 * random "Ah-Hem!" introduced into their speech.
 *  
 * @author Sylvia Wong
 * @version 29-09-2020
 */
public class Philosopher implements Speaker {
	// the name of the philosopher
	private String name;
	
	/**
	 * Constructor
	 * @param name	the name of the philosopher
	 */
	public Philosopher(String name) {
		this.name = name;
	}
	
	/**
	 * Introduce myself. A Philosopher doesn't go "Ah-Hem!" 
	 * while introducing himself/herself.
	 */
	public String introduceOneself() {
		String intro = "";
		// my name is...
		intro += Speaker.nameIntro + name + ". ";
		// thanks
		intro += Speaker.thanks;
		// no "Ah-Hem!" in self-introduction.
		return intro;
	}

	
	@Override
	public String speak(String speech) {
		String[] text = speech.split(" ");
		Random rand = new Random();
		int comp = rand.nextInt(5);
		
		StringBuilder mySpeech = new StringBuilder();
		
		for(int i = 0; i < text.length; i++) {
			int chance = rand.nextInt(5);
			if(chance == comp) {
				mySpeech.append("Ah-Hem! ");
			}
			mySpeech.append(text[i] + " ");
		}
		return mySpeech.toString();
	}


	

	/**
	 * The Main: for quick unit testing
	 * @param args
	 */
	public static void main(String[] args) {
		Philosopher p = new Philosopher("Doctor Loo");
		System.out.println(p.introduceOneself());
		System.out.println(p.speak("In general, polymorphism describes multiple possible states for a single property (it is said to be polymorphic)."));

	}

}
