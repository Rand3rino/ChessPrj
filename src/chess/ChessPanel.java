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

	private JButton longSide  = new JButton("Long");
	private JButton shortSide = new JButton("Short");
	private JButton blankBtn = new JButton();
	private JButton blankBtn1 = new JButton();
	private JButton blankBtn2 = new JButton();
	private JButton blankBtn3 = new JButton();

	
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

	private int gameType;

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

	private JMenuItem gameItem;
	private JMenuItem quitItem;

	private JLabel showPlayer;

	// Declare other instance variables as needed

	/** ButtonListener for Chess game */
	private ButtonListener listener = new ButtonListener();

	public ChessPanel(JMenuItem pquitItem, JMenuItem pgameItem) {

		quitItem = pquitItem;
		gameItem = pgameItem;

		gameType();

		quitItem.addActionListener(listener);
		gameItem.addActionListener(listener);
	}

	private void gameType() {
		JOptionPane.showMessageDialog(null, "Welcome to Chess");

		String[] options = new String[] {"1v1", "A.I.", "Cancel"};
		gameType = JOptionPane.showOptionDialog(null,"Select One:",
				"Chess", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if(gameType == 2)
			System.exit(1);

		setUpBoard();
	}

	private void setUpBoard() {
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
	}

	/******************************************************************
	 * This method intantiates images and assigns them to the 
	 * ImageIcons of the chess pieces.
	 *****************************************************************/
	private void setImageIcons() {

		// Assign images to the black pieces.
		bRookIcon = new ImageIcon("bRook.png", "bRook");
		bKnightIcon = new ImageIcon("bKnight.png", "bKnight");
		bBishopIcon = new ImageIcon("bBishop.png", "bBishop");
		bQueenIcon = new ImageIcon("bQueen.png", "bQueen");
		bKingIcon = new ImageIcon("bKing.png", "bKing");
		bPawnIcon = new ImageIcon("bPawn.png", "bPawn");

		// Assign images to the white pieces.
		wRookIcon = new ImageIcon("wRook.png", "wRook");
		wKnightIcon = new ImageIcon("wKnight.png", "wKnight");
		wBishopIcon = new ImageIcon("wBishop.png", "wBishop");
		wQueenIcon = new ImageIcon("wQueen.png", "wQueen");
		wKingIcon = new ImageIcon("wKing.png", "wKing");
		wPawnIcon = new ImageIcon("wPawn.png", "wPawn");
	}

	/******************************************************************
	 * Method that creates the board.
	 *****************************************************************/
	private void displayBoard() {

		removeAll();

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

				// Increment alternate 0to change color.
				alternate++;
			}
		}

		blankBtn.setEnabled(false);
		add(blankBtn);

		blankBtn1.setEnabled(false);
		add(blankBtn1);

		showPlayer = new JLabel("White", 0);
		add(showPlayer);

		// Add listeners to both castling buttons and start disabled.
		longSide.addActionListener(listener);
		longSide.setEnabled(false);
		add(longSide);

		shortSide.addActionListener(listener);
		shortSide.setEnabled(false);
		add(shortSide);

		blankBtn2.setEnabled(false);
		add(blankBtn2);

		blankBtn3.setEnabled(false);
		add(blankBtn3);

		repaint();
		revalidate();
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

	private void promotion(Move move) {

		//get the name of the icon
		String desc = ((ImageIcon)board[move.fromRow][move.fromColumn].getIcon()).getDescription();

		//check if its a black pawn
		if (desc.charAt(0) == 'b' && desc.charAt(1) == 'P') {

			//if its a black pawn at row 7 promote to queen
			if(move.toRow == 7)
				board[move.fromRow][move.fromColumn].setIcon(bQueenIcon);
		}

		//check if its a white pawn
		else if (desc.charAt(0) == 'w' && desc.charAt(1) == 'P') {

			//if its a white pawn at row 7 promote to queen
			if(move.toRow == 0)
				board[move.fromRow][move.fromColumn].setIcon(wQueenIcon);
		}
	}

	private void iconCastleLong(Player p) {
		if (p == Player.BLACK) {
			board[0][0].setIcon(null);
			board[0][4].setIcon(null);
			board[0][2].setIcon(bKingIcon);
			board[0][3].setIcon(bRookIcon);
		}
		if (p == Player.WHITE) {
			board[7][0].setIcon(null);
			board[7][4].setIcon(null);
			board[7][2].setIcon(wKingIcon);
			board[7][3].setIcon(wRookIcon);
		}
	}

	private void iconCastleShort(Player p) {
		if (p == Player.BLACK) {
			board[0][7].setIcon(null);
			board[0][4].setIcon(null);
			board[0][6].setIcon(bKingIcon);
			board[0][5].setIcon(bRookIcon);
		}
		if (p == Player.WHITE) {
			board[7][7].setIcon(null);
			board[7][4].setIcon(null);
			board[7][6].setIcon(wKingIcon);
			board[7][5].setIcon(wRookIcon);
		}
	}

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JComponent comp = (JComponent) event.getSource();

			if(comp == gameItem) {
				gameType();
			}
			if(comp == quitItem)
				System.exit(1);

			if(comp == longSide) {
				iconCastleLong(model.currentPlayer());
				model.moveCastleLongSide(model.currentPlayer());
				longSide.setEnabled(false);
				model.changePlayer();
			}

			if(comp == shortSide) {
				iconCastleShort(model.currentPlayer());
				model.moveCastleShortSide(model.currentPlayer());
				shortSide.setEnabled(false);
				model.changePlayer();
			}

			if(gameType == 0) {
				// Complete this.
				for (int row = 0; row < 8; row++) {
					for (int col = 0; col < 8; col++) {
						if (board[row][col] == comp) {
							count++;

							// if the player has selected a piece to move
							// and a location to move to
							if (count % 2 == 0) {

								move = new Move(firstRow, firstCol, row, col);
								if (model.isValidMove(move) && model.stillInCheck(move)) {
									model.move(move);
									promotion(move);
									model.changePlayer();
									board[row][col].setIcon(board[firstRow][firstCol].getIcon());
									board[firstRow][firstCol].setIcon(null);

									//notify player if in check 
									// FIXME: SOME SORT OF ERROR ON 315
									if(model.inCheck(model.currentPlayer()))
										if(model.inCheckMate(model.currentPlayer())) {
											JOptionPane.showMessageDialog(null,"Checkmate:" + model.currentPlayer().toString() + " Loses");
											if (model.isComplete()) {
												JOptionPane.showMessageDialog(null,"Game is over. Application will now close.");
												System.exit(1);
											}
										}
										else
											JOptionPane.showMessageDialog(null,"Check");
										}
										else
									JOptionPane.showMessageDialog(null, 
											"Invalid Move.");
							}

							// store the players first move
							else {
								if(board[row][col].getIcon() == null) {
									JOptionPane.showMessageDialog(null, 
											"Invalid Move.");
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
			
			if(gameType == 1) {
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
									promotion(move);
									board[row][col].setIcon(board[firstRow][firstCol].getIcon());
									board[firstRow][firstCol].setIcon(null);

									//notify player if in check 
									// FIXME: SOME SORT OF ERROR ON 315
									if(model.inCheck(model.currentPlayer()))
										if(model.inCheckMate(model.currentPlayer())) {
											JOptionPane.showMessageDialog(null,"Checkmate:" + model.currentPlayer().toString() + " Loses");
											if (model.isComplete()) {
												JOptionPane.showMessageDialog(null,"Game is over. Application will now close.");
												System.exit(1);
											}
										}
										else
											JOptionPane.showMessageDialog(null,"Check");
								} else
									JOptionPane.showMessageDialog(null, 
											"Invalid Move.");
								
								model.turnComputer();
							}

							// store the players first move
							else {
								if(board[row][col].getIcon() == null) {
									JOptionPane.showMessageDialog(null, 
											"Invalid Move.");
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

			if (model.castleLongSide(model.currentPlayer()))
				longSide.setEnabled(true);
			else 
				longSide.setEnabled(false);

			if (model.castleShortSide(model.currentPlayer()))
				shortSide.setEnabled(true);
			else 
				shortSide.setEnabled(false);

			showPlayer.setText(model.currentPlayer().toString());
		}
	}
}


