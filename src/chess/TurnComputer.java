/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class TurnComputer {

	/** The two dimensional board */
	private IChessPiece[][] board = new IChessPiece[8][8];

	/** Variable to hold a single piece */
	private IChessPiece piece;

	/** Array to hold all chessPieces */
	private ChessPiece[] chessPieces = new ChessPiece[32];
	
	/** The game engine */
	private ChessModel model;

	/** The player variable */
	private Player player;

	/** Static Variable for AI Player */
	private static Player HUMAN = Player.WHITE;
	
	/** Static Variable for AI Player */
	private static Player AI = Player.BLACK;
	
	private Move move;


	/******************************************************************
	 * This method is the AI feature. The AI will follow these 
	 * priorities:
	 * 		1. Check to see if it is in check. 
	 * 		2. Attempt to put the opponent into check (or checkmate).
	 * 		3. Determine if any of your pieces are in danger. 
	 * 		4. Capture an opponent piece.
	 * 		5. Move a piece (pawns first) forward towards the
	 *  		   opponent's King.
	 *****************************************************************/
	public void turnComputer() {

		// AI will always be BLACK.
		player = AI;

		// Variable to skip processes if the turn is complete.
		boolean turnComplete = false;

		// 1. Check if the AI is in check.
		turnComplete = getOutOfCheck();

		// 2. Put the opponent is in check.
		if (turnComplete) 
			turnComplete = putInCheck();

		// 3. Move a piece if it is in danger.
		if (turnComplete) 
			turnComplete = avoidDanger();

		// 4. Capture and opponent piece.
		if (turnComplete) 
			turnComplete = capture();

		// 5. Move towards the opponent King.
		if (turnComplete) 
			turnComplete = moveForward();

	}

	/******************************************************************
	 * This method is part of the AI feature. Check to see if it is 
	 * in check. If so, get out of check by moving the King or placing 
	 * a piece to block the check.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean getOutOfCheck() {

		int piece = 16;

		if (model.inCheck(AI)) {

			// Save the starting location of the Black King.
			int kingRow = chessPieces[4].getRow(chessPieces[4], board);
			int kingCol = chessPieces[4].getCol(chessPieces[4], board);

			// Move the King first. Try each direction.
			for (int row = -1; row < 2; row++)
				for (int col = -1; col < 2; col++) {
					move = new Move(kingRow, kingCol, kingRow + row,
							kingCol + col);

					// Continue if this is a valid move.
					if (chessPieces[4].isValidMove(move, board)) {
						model.move(move);

						// No longer in check, the move is over.
						if (!model.inCheck(AI))
							return true;

						// Still in check, cancel the last move.
						else {
							move = new Move(kingRow + row, 
									kingCol + col, kingRow, kingCol);
							model.move(move);
						}
					}
				}

			// Move all other pieces to get out of check.
			while (piece > -1) {

				if (chessPieces[piece] != null) {

					// Save the starting location of the piece.
					int pieceRow = chessPieces[piece].getRow
							(chessPieces[piece], board);
					int pieceCol = chessPieces[piece].getCol
							(chessPieces[piece], board);

					// Move every piece to every possible location.
					for (int row = -8; row <= 8; row++)
						for (int col = -8; col <= 8; col++) {
							move = new Move(pieceRow, pieceCol,
									pieceRow + row, pieceCol + col);

							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove
									(move, board)) {

								model.move(move);

								// No longer checked, the move is over.
								if (!model.inCheck(AI))
									return true;

								// Still checked, cancel the last move.
								else {
									move = new Move(pieceRow + row,
										pieceCol + col, pieceRow,
										pieceCol);
									model.move(move);
								}
							}
						}
				}

				// Skip the King piece.
				if (piece == 5)
					piece = 3;

				// Decrement to move another piece.
				else {
					piece--;
				}
			}
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Attempt to put the 
	 * opponent into check (or checkmate) without losing its piece. 
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean putInCheck() {
		// Move all.
		if (model.inCheck(HUMAN)) 
			return true;
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Determine if any of your 
	 * pieces are in danger. If so, move it to safety.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean avoidDanger() {
		if (model.inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.
			return true;
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Take an opponent piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean capture() {
		if (model.inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.	
			return true;
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Move a piece (pawns 
	 * first) forward towards the opponent's King. Check to see if 
	 * that piece is in danger of being captured. If so, move a 
	 * different piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean moveForward() {
		if (model.inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.
			return true;
		}
		return false;
	}
}