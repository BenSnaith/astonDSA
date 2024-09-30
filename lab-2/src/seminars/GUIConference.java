/**
 * 
 */
package seminars;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * A GUI for displaying the conference speeches one by one.
 * 
 * @author Sylvia Wong
 * @version 29-09-2020
 */
public class GUIConference{

	private Conference conference;
	
	private JFrame frame;
	private JTextArea textArea;
	private JButton nextButton;
	private JButton exitButton;
	
	/**
	 * Constructor
	 * @param file	the name of the seminar data file
	 */
	public GUIConference(String file) {
		// create the main GUI window
		makeFrame();
		
		/* 
		 * Try to create a new Conference object using the given filename.
		 * 
		 * There are several kinds of Exceptions that can happen:
		 * IOException, NumberFormatException & BadDataFormatException
		 * 
		 * Each kind of exception carries an appropriate message. However,
		 * we only need one catch clause here because regardless of the 
		 * actual type of exception, we are going to display the message
		 * and exit the application anyway. Hence, we simply reference
		 * those kinds of exceptions as their superclass Exception. 
		 */
		try{
			conference = new Conference(file);
		}
		catch (Exception e){
			showError(e.getMessage());
			System.exit(0);
		}
	}
	
	/*
	 * Create a window with a label, a text area and two buttons. 
	 * This window is the main GUI component for this application.
	 * Anonymous classes are used to create appropriate ActionListener
	 * objects for tracking the user actions and responding to them 
	 * accordingly.
	 */
	private void makeFrame(){
		frame = new JFrame("Conference");
		Container contentPane = frame.getContentPane();		
		// set layout
		contentPane.setLayout(new BorderLayout());
		// create a label
		JLabel label = new JLabel("Current conference in progess...");
		label.setForeground(Color.GRAY);
		contentPane.add(label, BorderLayout.NORTH);		
		
		// create an area for displaying text
		textArea = new JTextArea(30,30);
		// green text
		textArea.setForeground(new Color(112,192,117));
		textArea.setBackground(new Color(255,255,255));
		//textArea.setSize(150,100);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new EtchedBorder());
		// create a scrollable pane
		JScrollPane scrollable = new JScrollPane(textArea);
		contentPane.add(scrollable, BorderLayout.CENTER);
		// create a button
		nextButton = new JButton("Start");
		// nice gray-ish blue background
		nextButton.setBackground(new Color(116,151,200));
		nextButton.setForeground(Color.WHITE);
		nextButton.setSize(30,20);
		/* An action listener for nextButton...
		 * When the button is pressed, its label changes
		 * to "Next" and the text area then display the next
		 * seminar speech, if available. 
		 */		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					textArea.setText(conference.getNextSeminar());
					nextButton.setText("Next");
				}
				catch (ArrayIndexOutOfBoundsException eab){
					showError(eab.getMessage());
				}
			}
		});		
		
		// create a button
		exitButton = new JButton("Exit");
		// nice gray-ish blue background
		exitButton.setBackground(new Color(116,151,200));
		exitButton.setForeground(Color.WHITE);
		exitButton.setSize(30,20);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// create a panel for placing the button
		JPanel panel = new JPanel();
		new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.add(nextButton);
		panel.add(exitButton);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/*
	 * Display a dialogue window with a specified message 
	 */
	private void showError(String message){
		JOptionPane.showMessageDialog(frame, message);
	}

	/**
	 * The main for starting up this application.
	 * This application expect only one command line input argument
	 * which specifies the name of the data file. This data file stores 
	 * seminar records.
	 * @param args	input argument(s)
	 */
	public static void main(String[] args) {
		try {
			new GUIConference(args[0]);
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Missing input argument");
		}
		
	}

}
