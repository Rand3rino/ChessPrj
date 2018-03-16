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
	
	private Move move;

	private ImageIcon bRookIcon;
	private ImageIcon bKnightIcon;
	private ImageIcon bBishopIcon;
	private ImageIcon bQueenIcon;
	private ImageIcon bKingIcon;
	private ImageIcon bPawnIcon;
	
	private ImageIcon wRookIcon;
	private ImageIcon wKnightIcon;
	private ImageIcon wBishopIcon;
	private ImageIcon wQueenIcon;
	private ImageIcon wKingIcon;
	private ImageIcon wPawnIcon;
	
	// Declare other instance variables as needed

	/** ButtonListener for Chess game */
	private ButtonListener listener = new ButtonListener();

	public ChessPanel() {
		// Complete this.
		
		//model = new ChessModel();
		move = new Move();
		
		bRookIcon = new ImageIcon("bRook.png");
		bKnightIcon = new ImageIcon("bKnight.png");
		bBishopIcon = new ImageIcon("bBishop.png");
		bQueenIcon = new ImageIcon("bQueen.png");
		bKingIcon = new ImageIcon("bKing.png");
		bPawnIcon = new ImageIcon("bPawn.png");
		
		wRookIcon = new ImageIcon("wRook.png");
		wKnightIcon = new ImageIcon("wKnight.png");
		wBishopIcon = new ImageIcon("wBishop.png");
		wQueenIcon = new ImageIcon("wQueen.png");
		wKingIcon = new ImageIcon("wKing.png");
		wPawnIcon = new ImageIcon("wPawn.png");
		// Call the display to set up the Chess board.
		displayBoard();
		displayChessPieces();
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
					board[row][col].setBackground(Color.GRAY);
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
	
	private void displayChessPieces() {
		setBlackIcons();
		setWhiteIcons();
	}

	private void setWhiteIcons() {
		board[7][0].setIcon(wRookIcon);
		board[7][1].setIcon(wKnightIcon);
		board[7][2].setIcon(wBishopIcon);
		board[7][3].setIcon(wQueenIcon);
		board[7][4].setIcon(wKingIcon);
		board[7][5].setIcon(wBishopIcon);
		board[7][6].setIcon(wKnightIcon);
		board[7][7].setIcon(wRookIcon);
		
		board[6][0].setIcon(wPawnIcon);
		board[6][1].setIcon(wPawnIcon);
		board[6][2].setIcon(wPawnIcon);
		board[6][3].setIcon(wPawnIcon);
		board[6][4].setIcon(wPawnIcon);
		board[6][5].setIcon(wPawnIcon);
		board[6][6].setIcon(wPawnIcon);
		board[6][7].setIcon(wPawnIcon);
	}

	private void setBlackIcons() {
		board[0][0].setIcon(bRookIcon);
		board[0][1].setIcon(bKnightIcon);
		board[0][2].setIcon(bBishopIcon);
		board[0][3].setIcon(bQueenIcon);
		board[0][4].setIcon(bKingIcon);
		board[0][5].setIcon(bBishopIcon);
		board[0][6].setIcon(bKnightIcon);
		board[0][7].setIcon(bRookIcon);
		
		board[1][0].setIcon(bPawnIcon);
		board[1][1].setIcon(bPawnIcon);
		board[1][2].setIcon(bPawnIcon);
		board[1][3].setIcon(bPawnIcon);
		board[1][4].setIcon(bPawnIcon);
		board[1][5].setIcon(bPawnIcon);
		board[1][6].setIcon(bPawnIcon);
		board[1][7].setIcon(bPawnIcon);
	}

	// Add other helper methods as needed

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();
			// Complete this.
			for(int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == comp) {

					}
					}
				}
			}
		}
