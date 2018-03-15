/**********************************************************************
 * Project 3: Chess Game Panel
 * 
 * This class is responsible for presenting the graphical user 
 * interface, responding to user actions, and updating the view.
 * This game is a standard for of chess, where white moves first.
 *********************************************************************/

package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//import project2.ConnectFourPanel.ButtonListener;

public class ChessPanel extends JPanel {

	/** An array of JButtons for the Chess board */
	private JButton[][] board = new JButton[8][8];

	/** DESCRIBE ME */
	private ChessModel model;

	// Declare other instance variables as needed

	/** ButtonListener for Chess game */
	private ButtonListener listener = new ButtonListener();

	public ChessPanel() {
		// Complete this.
		
		// Call the display to set up the Chess board.
		displayBoard();
	}

	/******************************************************************
	 * Method that creates the board.
	 *****************************************************************/
	private void displayBoard() {

		// Create a (9 x 8) dimension grid.
		// The bottom row will be used for control buttons.
		setLayout(new GridLayout(9, 8));


		// Loop for every square on a Chess board.
		for (int row = 0; row < 8; row++) {
			
			// Variable used to alternate color of the board.
			// Start alternate according the the row number.
			int alternate = row;
			
			for (int col = 0; col < 8; col++) {

				// Instantiate a JButton at this location.
				board[row][col] = new JButton();
				
				// If alternate is even, set the square to white.
				if ((alternate % 2) == 0) {
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setOpaque(true);
				}

				// If alternate is odd, set the square to dark gray.
				else {
					board[row][col].setBackground(Color.DARK_GRAY);
					board[row][col].setOpaque(true);
				}
				
				// Add an ActionListener for this square.
				board[row][col].addActionListener(listener);

				// Add the square to the GUI display.
				add(board[row][col]);
				
				// Increment alternate to change color.
				alternate++;
			}
		}
	}

	// Add other helper methods as needed

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			// Complete this.
		}
	}
}
