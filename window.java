package graphicU;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;



public class window extends JFrame{
	private JPanel mainPanel = new JPanel();
	private  JPanel difficulty = new JPanel();
	private  JPanel hangMan = new JPanel();
	private  JPanel userGuess  = new JPanel();
	private JTextField guess = new JTextField(1);
	private  JLabel hang = new JLabel();
	private String imgName = "";
	private String actualMysteryWord = "";
	private String displayWord = "";
	private int state = 1;
	private JLabel mysteryWordLabel = new JLabel();
	private JLabel img = new JLabel();
	
	
	public window() {
	 super("Hangman");
	 pack();
	 
	 setLocationRelativeTo(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     getContentPane().setBackground(Color.WHITE);
     
     mainPanel.setLayout(new BorderLayout());
     mainPanel.setPreferredSize(new Dimension(400,900));
     difficulty.setLayout(new FlowLayout());
     
    JLabel logoL = new JLabel(new ImageIcon("icon.png"));
   
    mainPanel.add(logoL, BorderLayout.PAGE_END);
     JLabel diff = new JLabel("Welcome to Hangman | Hover for details and select your difficulty level!");
     diff.setSize(50,100);
     Dimension buttonSize = new Dimension(80,30);
     
     
     JButton easy = new JButton("Easy");
     easy.setPreferredSize(buttonSize);
     easy.setToolTipText("4 letters or less!");
     easy.setBackground(Color.GREEN);
     easy.setForeground(Color.GREEN);
     easy.addActionListener(new listener());
     
     
     JButton medium = new JButton("Medium");
     medium.setToolTipText("4 - 8 letters!");
     medium.setPreferredSize(buttonSize);
     medium.setBackground(Color.orange);
     medium.setForeground(Color.orange);
     medium.addActionListener(new listener());
     
     JButton hard = new JButton("Hard");
     hard.setToolTipText("More than 8 letters!");
     hard.setPreferredSize(buttonSize);
     hard.setBackground(Color.RED);
     hard.setForeground(Color.RED);
     hard.addActionListener(new listener());
     
     difficulty.add(diff);
     difficulty.add(easy);	 
     difficulty.add(medium );
     difficulty.add(hard);
     
     Dimension imgSize = new Dimension(200,500);
    
     hangMan.setLayout(new FlowLayout());

     
     img.setVisible(true);
     img.setPreferredSize(imgSize);
     
     hangMan.setVisible(true);
     hangMan.setSize(300,300);
     hangMan.setPreferredSize(imgSize);
     validate();
     
     userGuess.setLayout(new FlowLayout());
     userGuess.setVisible(false);
     JLabel instructions = new JLabel("Try keep the man alive by guessing all of the letters in the word in less than 11 attempts!");
     instructions.setSize(100,300); 
     

     guess.setSize(20,20);
     guess.addActionListener(new listener());
     guess.setActionCommand("guess");
     
     JButton guessed = new JButton("Click to Guess!");
     guessed.setSize(70,70);
     guessed.addActionListener(new listener());
     userGuess.add(instructions);
     userGuess.add(guess);
     
     userGuess.add(guessed);


     mainPanel.add(difficulty, BorderLayout.NORTH);
    
     mainPanel.add(hangMan, BorderLayout.CENTER);
     add(mainPanel);
     
     
     mainPanel.setBackground(Color.white);
     userGuess.setBackground(Color.WHITE);
     hangMan.setBackground(Color.WHITE);
     difficulty.setBackground(Color.WHITE);
     setSize(2000, 2000);
     setVisible(true);
}

	
	
	private class listener implements ActionListener {
	    public void actionPerformed(ActionEvent event) {
	    	HangMan hangMethods = null;
	    	
			try {
				hangMethods = new HangMan();
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
		
			} catch (IOException e1) {

				e1.printStackTrace();
			}
	    	
	        if(event.getActionCommand()=="Easy" ||event.getActionCommand()=="Medium" || event.getActionCommand()=="Hard" ) {
	        	
	        difficulty.setVisible(false);
	        userGuess.setVisible(true);
	        imgName = "state1.GIF";
	        ImageIcon state1 = new ImageIcon(imgName);
	        img.setIcon(state1);
	        hangMan.add(img);
	        
	        
			try {
				
				actualMysteryWord = hangMethods.mysteryWord(event.getActionCommand());
			} catch (FileNotFoundException e) {

				e.printStackTrace();
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			int length = actualMysteryWord.length();
			String dashes = "---------------------------------------";
			
	        displayWord = dashes.substring(0,length);
	        mysteryWordLabel.setText(displayWord);
	        userGuess.add(mysteryWordLabel);
	        mainPanel.add(userGuess, BorderLayout.NORTH);
	        System.out.println(actualMysteryWord);
	        }
	        
	        else if(event.getActionCommand().equals("Click to Guess!")) { 
	        	
	        	String userGuessT = guess.getText();
	        	
	        	if(hangMethods.anyMatches(userGuessT, actualMysteryWord)) {
	        		displayWord = hangMethods.changeDisplayWord(displayWord, userGuessT, actualMysteryWord);
	        		if(!displayWord.contains("-")) {JOptionPane.showMessageDialog(null, "You win!");System.exit(0);}
	        		userGuess.revalidate();
	        		mysteryWordLabel.setText(displayWord);
	        		guess.setText("");
	        	}
	        
	        	else {state++; if(!displayWord.contains("-")) {JOptionPane.showMessageDialog(null, "You win!"); System.exit(0);}
        	
	        	if(state<=11) {String imgName = "state" +state+".GIF"; ImageIcon newImg = new ImageIcon(imgName); 
	        	img.setIcon(newImg);guess.setText(""); 
	        	if(state == 11){JOptionPane.showMessageDialog(null, "Game Over! The word was: " + actualMysteryWord +". You hung the man!"); System.exit(0);}}
	        	}
	        }	
	        }
	        
	        
	       
	        }
	        
	
	    }
	   
	

