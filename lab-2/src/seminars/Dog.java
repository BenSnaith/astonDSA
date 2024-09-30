package seminars;

/**
 * A Dog object models limited behaviours of a dog.
 * A Dog can speak, so it can introduce itself and even make a speech.
 * 
 * @author Sylvia Wong
 * @version 10-10-2020
 */
/* !!!! Modify the class declaration to define the
 * 		realisation relationship between classes Dog and Speaker.  
 */
public class Dog implements Speaker{

	// the name of this dog object
	private String name;
	
	/**
	 * Constructor for creating a Dog object
	 * @param name	the name of the dog
	 */
	public Dog(String name){
		this.name = name;
	}

	/**
	 * A dog introduces itself
	 * @return the self introduction statement 
	 */
	public String introduceOneself() {
		String intro = "";
		// my name is...
		intro += Speaker.nameIntro + name + ". ";
		// thanks
		intro += Speaker.thanks;
		return speak(intro);
	}

	/**
	 * Speak out an utterance with "Woof!"s in between.
	 * @param speech	the utterance for speaking out
	 * @return the uttered speech with a dog's speaking manner 
	 */
	public String speak(String speech) {
		// split the text string into word tokens
		String[] text = speech.split(" ");
		
		StringBuilder mySpeech = new StringBuilder();
		
		// After every 5 words, append "Woof!"
		for(int i = 0; i < text.length; i++) {
			if((i + 1) % 5 == 0) {
				mySpeech.append("Woof! ");
			}	
			mySpeech.append(text[i] + " ");
		}
		
		return mySpeech.toString();
	}

	/**
	 * The Main -- for a quick unit testing
	 * @param args
	 */
	public static void main(String[] args){
		Dog d = new Dog("Lucky");
		System.out.println(d.introduceOneself());
		System.out.println(d.speak("In general, polymorphism describes multiple possible states for a single property (it is said to be polymorphic)."));
	}
	
}
