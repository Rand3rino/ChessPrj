/**********************************************************************
 * Project 3: Chess Game Panel
 * 
 * This class is responsible for presenting the graphical user 
 * interface, responding to user actions, and updating the view.
 * This game is a standard for of chess, where white moves first.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 20, 2018.
 *********************************************************************/

package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

	/** An array of JButtons for the Chess board */
	private JButton[][] board = new JButton[8][8];

	/** FIXME */
	private ChessModel model;

	/** FIXME */
	private Move move;

	/** FIXME */
	private int firstRow;

	/** FIXME */
	private int firstCol;

	/** Increment to allow player to select two buttons */
	private int count;

	/** FIXME */
	private ImageIcon bRookIcon;
	private ImageIcon bKnightIcon;
	private ImageIcon bBishopIcon;
	private ImageIcon bQueenIcon;
	private ImageIcon bKingIcon;
	private ImageIcon bPawnIcon;

	/** FIXME */
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

		model = new ChessModel();
		move = new Move();
		firstRow = 0;
		firstCol = 0;
		count = 0;

		// Assign .png files to pieces. 
		setImageIcons();
		// Call the display to set up the Chess board.
		displayBoard();
		displayChessPieces();
		//disableInvalidBtn();
	}

	/******************************************************************
	 * This method intantiates images and assigns them to the 
	 * ImageIcons of the chess pieces.
	 *****************************************************************/
	private void setImageIcons() {

		// Assign images to the black pieces.
		bRookIcon = new ImageIcon("bRook.png", "b");
		bKnightIcon = new ImageIcon("bKnight.png", "b");
		bBishopIcon = new ImageIcon("bBishop.png", "b");
		bQueenIcon = new ImageIcon("bQueen.png", "b");
		bKingIcon = new ImageIcon("bKing.png", "b");
		bPawnIcon = new ImageIcon("bPawn.png", "b");

		// Assign images to the white pieces.
		wRookIcon = new ImageIcon("wRook.png", "w");
		wKnightIcon = new ImageIcon("wKnight.png", "w");
		wBishopIcon = new ImageIcon("wBishop.png", "w");
		wQueenIcon = new ImageIcon("wQueen.png", "w");
		wKingIcon = new ImageIcon("wKing.png", "w");
		wPawnIcon = new ImageIcon("wPawn.png", "w");
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

	/******************************************************************
	 * This method puts the chess pieces on their starting locations.
	 *****************************************************************/
	private void displayChessPieces() {

		// call method to set up black chess pieces
		setBlackIcons();

		// call method to set up white chess pieces
		setWhiteIcons();
	}

	/******************************************************************
	 * This method puts the black pieces on their starting locations.
	 *****************************************************************/
	private void setBlackIcons() {

		// place the back row of pieces
		board[0][0].setIcon(bRookIcon);
		board[0][1].setIcon(bKnightIcon);
		board[0][2].setIcon(bBishopIcon);
		board[0][3].setIcon(bQueenIcon);
		board[0][4].setIcon(bKingIcon);
		board[0][5].setIcon(bBishopIcon);
		board[0][6].setIcon(bKnightIcon);
		board[0][7].setIcon(bRookIcon);

		// place the front row of pieces
		board[1][0].setIcon(bPawnIcon);
		board[1][1].setIcon(bPawnIcon);
		board[1][2].setIcon(bPawnIcon);
		board[1][3].setIcon(bPawnIcon);
		board[1][4].setIcon(bPawnIcon);
		board[1][5].setIcon(bPawnIcon);
		board[1][6].setIcon(bPawnIcon);
		board[1][7].setIcon(bPawnIcon);
	}

	/******************************************************************
	 * This method puts the white pieces on their starting locations.
	 *****************************************************************/
	private void setWhiteIcons() {

		// place the back row of pieces
		board[7][0].setIcon(wRookIcon);
		board[7][1].setIcon(wKnightIcon);
		board[7][2].setIcon(wBishopIcon);
		board[7][3].setIcon(wQueenIcon);
		board[7][4].setIcon(wKingIcon);
		board[7][5].setIcon(wBishopIcon);
		board[7][6].setIcon(wKnightIcon);
		board[7][7].setIcon(wRookIcon);

		// place the front row of pieces
		board[6][0].setIcon(wPawnIcon);
		board[6][1].setIcon(wPawnIcon);
		board[6][2].setIcon(wPawnIcon);
		board[6][3].setIcon(wPawnIcon);
		board[6][4].setIcon(wPawnIcon);
		board[6][5].setIcon(wPawnIcon);
		board[6][6].setIcon(wPawnIcon);
		board[6][7].setIcon(wPawnIcon);
	}

	// Add other helper methods as needed

	private void disableInvalidBtn() {

		// call method to enable all buttons
		enableBtn();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {

				//check if button has a piece on it
				if(board[row][col].getIcon() != null) {

					// get the name of the icon on that button
					String desc = ((ImageIcon)board[row][col].getIcon()).getDescription();

					if(board[row][col] == null)
						board[row][col].removeActionListener(listener);

					// if that icon starts with a b its a black piece and
					// if the current player is white disable that button
					if (desc.charAt(0) == 'b' && model.currentPlayer() == Player.WHITE)
						board[row][col].removeActionListener(listener);

					// else if the icon is white and its black turn
					// disable the button
					else if (desc.charAt(0) == 'w' && model.currentPlayer() == 
							Player.BLACK)
						board[row][col].removeActionListener(listener);
				}
			}
		}
	}

	private void enableBtn() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++)
				board[row][col].addActionListener(listener);
		}
	}

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();

			//disableInvalidBtn();

			// Complete this.
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == comp) {
						count++;

						// if the player has selected a piece to move
						// and a location to move to
						if (count % 2 == 0) {
							move = new Move(firstRow, firstCol, row, col);
							if (model.isValidMove(move)) {
								model.move(move);
								board[row][col].setIcon(board[firstRow][firstCol].getIcon());
								board[firstRow][firstCol].setIcon(null);
							} else
								JOptionPane.showMessageDialog(null, 
										"Invalid Move.");
						}

						// store the players first move if not null
						else {
							if(board[row][col].getIcon() == null) {
								JOptionPane.showMessageDialog(null, 
										"Invalid Location.");
								count--;
							}
							else {
								firstRow = row;
								firstCol = col;
							}
						}
					}
				}
			}
		}
	}
}