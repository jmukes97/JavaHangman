import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HangMan extends JFrame{
	/* Creates panels used for the UI including empty "buffer" panels just for spaceing
		s is the main panels that everything will sit on
	*/
	JPanel s = new JPanel();
	JPanel empty = new JPanel();
	JPanel empty1 = new JPanel();
	JPanel empty2 = new JPanel();
	JPanel empty3 = new JPanel();
	JPanel empty4 = new JPanel();
	JLabel numOfTries = new JLabel();
	int tries = 10; // number of tries a user has shouldbe be global but cant figure out how not to be 
	JPanel q = new JPanel();
	JPanel w = new JPanel();
	JPanel a = new JPanel();
	JPanel z = new JPanel();
	StartGame init = new StartGame(); //listener for the start button
	String under = "_ _ _ _ _ _ _ "; // helps keep track of the current word probably shouldnt be global so oops
	String[] words = new String[]{ "Allergy","Almanac","Conform","Despite","Garbage","Monarch","Patient","Railcar","Sarcasm","Tadpole", "feature","express","gateway", "housing", "kingdom", "liberal", "justify", "measure", "landing", "predict", "surface", "survive", "subject", "theatre","written", "working", "welfare", "victory" , "veteran"};  // lists of words to guess from again, this probs shouldnt be global
	JLabel MainLabel = new JLabel("<html><div style='font-size:25px'>" + "      Welcome to Hangman     "  +" </div></html>>");
	JLabel Guess = new JLabel("<html><div style='font-size:25px'>" + "XXXXXXX"  +" </div></html>");
	JButton start = new JButton("Start Game");
	int choice; // will eventually hold a random number
	String Challenge; // Will eventually hold the chosen word

//first row in keyboard 
	JButton A = new JButton("A");
	JButton B = new JButton("B");
	JButton C = new JButton("C");
	JButton D = new JButton("D");
	JButton E = new JButton("E");
	JButton F = new JButton("F");
	JButton G = new JButton("G");
	JButton H = new JButton("H");
	JButton I = new JButton("I");
	JButton J = new JButton("J");
//second row
	JButton K = new JButton("K");
	JButton L = new JButton("L");
	JButton M = new JButton("M");
	JButton N = new JButton("N");
	JButton O = new JButton("O");
	JButton P = new JButton("P");
	JButton Q = new JButton("Q");
	JButton R = new JButton("R");
	JButton S = new JButton("S");
	JButton T = new JButton("T");
	JButton U = new JButton("U");
//third row
	JButton V = new JButton("V");
	JButton W = new JButton("W");
	JButton X = new JButton("X");
	JButton Y = new JButton("Y");
	JButton Z = new JButton("Z");
	
	ArrayList Qbuttons =new ArrayList(); // this will hold all the buttons on the keyboard

	public class EndGame implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
    		Guess.setText("<html><div style='font-size:25px'>" + "XXXXXXX"  +" </div></html"); //activates the game changes label to XXXXXX
			start.setText("Start Game");
			under = "_ _ _ _ _ _ _ "; // does this again because this function will run a bunch of times
			start.removeActionListener(start.getActionListeners()[0]); //removes old listener
			start.addActionListener(init); //adds start listener
			tries = 10; //resets number of 
			numOfTries.setText("");//changes label to empty
			for(int i = 0; i < Qbuttons.size(); i++){
				//removes listener on all the buttons
				JButton button = (JButton)Qbuttons.get(i);
				button.removeActionListener(button.getActionListeners()[0]);
			}
		}
	} 
	public class StartGame implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			EndGame finish = new EndGame(); //creates end game listener
			numOfTries.setText("Tries left " + Integer.toString(tries)); //adds label to show the number of tries
    		Guess.setText("<html><div style='font-size:25px'>" + under  + " </div></html"); // changes main label to show ________
			choice = (int)(Math.random()*words.length); //random number from 0 to the ammount of words
			Challenge = words[choice]; // holds the new word to guess
			//System.out.println(Challenge); debugging string
			start.setText("End Game"); //changes button text
			start.removeActionListener(start.getActionListeners()[0]); //changes it into the end button
			start.addActionListener(finish);
			for(int i = 0; i < Qbuttons.size(); i++){ //this loops through all the buttons
				final int t = i; //for some stupid reason java makes you do this idk why tho
				((JButton)Qbuttons.get(t)).addActionListener(new ActionListener() { //adds listener to all the buttons
					public void actionPerformed(ActionEvent e) {
						tries = tries - 1;//decrement the number of tries
						//System.out.println("ran"); debugging
						if(tries <= 0){
							//if they run out of tries
							Guess.setText("<html><div style='font-size:25px'> GAME OVER </div></html"); //sets label to ame over
							numOfTries.setText(""); //removes the ammount of tried text
							}
						else{
							//if they dont run out
							numOfTries.setText("Tries left :" + Integer.toString(tries)); //adds the decremented number to screen
							String test = ((JButton)Qbuttons.get(t)).getText(); //grab the letter they guessed
							String buff = under.replaceAll("\\s+",""); //string stuff to compare the char to the challenge 
							char[] BuffChar = buff.toCharArray(); // make an array of chars cuz i can just loop through them
							for(int i = 0; i < BuffChar.length; i++){
								if(Character.toUpperCase(test.charAt(0)) == Character.toUpperCase(Challenge.charAt(i))){
									BuffChar[i] = test.charAt(0);//adds correct guessed letter to the label 
								}
							}
							under = String.valueOf(BuffChar); //turns it back to a string 
							if(under.indexOf('_') == -1){ //if there are no more _ then they win
								Guess.setText("<html><div style='font-size:25px'> YOU WIN </div></html");
								numOfTries.setText("");
							}else{
								under = under.replace("_", " _ "); //adds spaces so it looks nice when displayed
								Guess.setText("<html><div style='font-size:25px'>" + under  + " </div></html"); 
							}
						}
					}
				});
			}
		}
	} 

	public static void main(String[] args){
		new HangMan();
	}
	public HangMan(){
		//adds everything pretty much
		super("Basic Application Example");
		setSize(500,300);
		setResizable(false);
		empty4.setPreferredSize(new Dimension(70, 25));
		empty4.add(numOfTries);
		empty3.setPreferredSize(new Dimension(70, 15));
		s.add(empty4);		
		s.add(MainLabel);
		s.add(empty3);
		s.add(Guess);
		
		q.add(A);
		q.add(B);
		q.add(C);
		q.add(D);
		q.add(E);
		q.add(F);
		q.add(G);
		q.add(H);
		q.add(I);
		q.add(J);
		Component [] Dbuttons = q.getComponents();
		//hold the first row of buttons
		w.add(K);
		w.add(L);
		w.add(M);
		w.add(N);
		w.add(O);
		w.add(P);
		w.add(Q);
		w.add(R);
		w.add(S);
		w.add(T);
		Component [] Wbuttons = w.getComponents();
		//holds the second row
		a.add(U);
		a.add(V);
		a.add(W);
		a.add(X);
		a.add(Y);
		a.add(Z);
		Component [] Abuttons = a.getComponents();
		//hold the last 
		start.addActionListener(init);
		z.add(start);
		//this adds all the buttons to one global array
		for(int i = 0; i < Dbuttons.length; i++){
			Qbuttons.add(Dbuttons[i]);		
		}
		for(int i = 0; i < Wbuttons.length; i++){
			Qbuttons.add(Wbuttons[i]);		
		}
		for(int i = 0; i < Abuttons.length; i++){
			Qbuttons.add(Abuttons[i]);		
		}
		//Copy all components into Qbuttons 
		
		s.add(q);
		s.add(w);
		s.add(empty);
		s.add(a);
		s.add(empty1);
		empty1.setPreferredSize(new Dimension(100, 15));
		empty.setPreferredSize(new Dimension(75, 15));
		s.add(z);
		empty2.setPreferredSize(new Dimension(370, 15));
		s.add(empty2);
		add(s);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
