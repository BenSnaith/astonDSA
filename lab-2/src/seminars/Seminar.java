package seminars;

/**
 * A seminar object models a seminar that takes place in a conference. 
 * It keeps track of the seminar title, the current speaker and 
 * the content of their expected speech.  
 * 
 * @author Sylvia Wong
 * @version 29-09-2020
 */
public class Seminar {

	private static final int DOG = 0;
	
	private static final String ENDNOTE = "\n Thank you!\n";
	
	private String title;		// the seminar title
	private String content;		// the expected content of the speech 
	private Speaker current;	// the speaker for this seminar
	
	/**
	 * Constructor for a Seminar object
	 * @param title		the seminar title
	 * @param content	the expected content of the speech
	 * @param speaker	the speaker object that is to give this seminar
	 * @param speakerType	whether the speaker is a dog or a philosopher
	 */
	public Seminar(String title, String content,
					String speaker, int speakerType) {
		this.title = title;
		this.content = content;
		switch(speakerType){		
		case DOG : current = new Dog(speaker);
		           break;
		default : current = new Philosopher(speaker);
		          break;
		}
	}
	
	/*
	 * Begin the seminar with the speaker introducing themselves
	 * and the seminar title.
	 */
	private String begin() { 
		String intro = current.introduceOneself();
		intro += "I am going to speak about " + title + ".\n";
		return intro;
	}
		
	/*
	 * End the speech by uttering the endnote.
	 */
	private String end() {
		return ENDNOTE;
	}
	
	/**
	 * The current speaker gives the complete seminar.
	 * @param speech
	 * @return what was said in the seminar
	 */
	public String proceed() {
		return begin() + current.speak(content) + end();
	}
	
	/**
	 * Change the current speaker.
	 * @param next	the new speaker
	 */
	public void changeSpeaker(Speaker next) {
		current = next;
	}
	
	/**
	 * Main -- for a quick unit testing
	 * @param args
	 */
	public static void main(String[] args) {
		Seminar seminar1 = new Seminar("Ubiquitous Computing", "Ubiquitous computing (ubicomp, or sometimes ubiqcomp) integrates computation into the environment, rather than having computers which are distinct objects. Another term for ubiquitous computing is pervasive computing. Promoters of this idea hope that embedding computation into the environment would enable people to move around and interact with computers more naturally than they currently do. One of the goals of ubiquitous computing is to enable devices to sense changes in their environment and to automatically adapt and act based on these changes based on user needs and preferences. Some simple examples of this type of behavior include GPS-equipped automobiles that give interactive driving directions and RFID store checkout systems. \n Ubiquitous computing encompasses a wide range of research topics, including distributed computing, mobile computing, sensor networks, human-computer interaction, and artificial intelligence.", "Doctor Loo", 1);
		System.out.println(seminar1.proceed());
		Seminar seminar2 = new Seminar("Artificial Intelligence", "Artificial intelligence (also known as machine intelligence and often abbreviated as AI) is intelligence exhibited by any manufactured (i.e. artificial) system. The term is often applied to general purpose computers and also in the field of scientific investigation into the theory and practical application of AI. AI the term is often used in works of science fiction to refer to that which exhibits artificial intelligence as well, as in 'the AI' referring to a singular discrete or distributed mechanism.\n Modern AI research is concerned with producing useful machines to automate human tasks requiring intelligent behavior. Examples include: scheduling resources such as military units, answering questions about products for customers, understanding and transcribing speech, and recognizing faces in CCTV cameras. As such, it has become an engineering discipline, focused on providing solutions to practical problems. AI methods were used to schedule units in the first Gulf War, and the costs saved by this efficiency have repaid the US government's entire investment in AI research since the 1950s. AI systems are now in routine use in many businesses, hospitals and military units around the world, as well as being built into many common home computer software applications and video games.", "Lucky", 0);
		System.out.println(seminar2.proceed());
	}

}
