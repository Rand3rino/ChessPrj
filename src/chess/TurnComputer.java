/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class TurnComputer{

	/** The two dimensional board */
	private IChessPiece[][] board = new IChessPiece[8][8];

	/** Variable to hold a single piece */
	private IChessPiece piece;
	
	ChessModel model;

	/** Array to hold all chessPieces */
	private ChessPiece[] chessPieces = new ChessPiece[32];

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
	public void turnComputer(ChessModel m) {

		model = m;
		
		// AI will always be BLACK.
		player = AI;

		//instantiate model instance variable
		//model = new ChessModel();
		
		// Variable to skip processes if the turn is complete.
		boolean turnComplete = false;

		// 1. Check if the AI is in check.
		turnComplete = getOutOfCheck();

		// 2. Put the opponent is in check.
		if (!turnComplete) 
			turnComplete = putInCheck();

		// 3. Move a piece if it is in danger.
		if (!turnComplete) 
			turnComplete = avoidDanger();

		// 4. Capture and opponent piece.
		if (!turnComplete) 
			turnComplete = capture();

		// 5. Move towards the opponent King.
		if (!turnComplete) 
			turnComplete = moveForward();

	}

	/******************************************************************
	 * This method is part of the AI feature. Check to see if it is 
	 * in check. If so, get out of check by moving the King or placing 
	 * a piece to block the check.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean getOutOfCheck() {

		int piece = 15;

		if (model.inCheck(AI)) {

			// Save the starting location of the Black King.
			int kingRow = chessPieces[4].getRow(chessPieces[4], board);
			int kingCol = chessPieces[4].getCol(chessPieces[4], board);

			// Move the King first. Try each direction.
			for (int row = 0; row <= 7; row++)
				for (int col = 0; col <= 7; col++) {
					move = new Move(kingRow, kingCol, row, col);

					// Continue if this is a valid move.
					if (chessPieces[4].isValidMove(move, board)) {
						model.move(move);

						// No longer in check, the move is over.
						if (!model.inCheck(AI))
							return true;

						// Still in check, cancel the last move.
						else
							model.move(new Move (row, col, 
								kingRow, kingCol));
						
					}
				}

			// Move all other pieces to get out of check.
			while (piece >= 0) {

				// Continue if this piece is active.
				if (chessPieces[piece] != null) {

					// Save the starting location of the piece.
					int pieceRow = chessPieces[piece].getRow
							(chessPieces[piece], board);
					int pieceCol = chessPieces[piece].getCol
							(chessPieces[piece], board);

					// Move every piece to every possible location.
					for (int row = 0; row <= 7; row++)
						for (int col = 0; col <= 7; col++) {
							move = new Move(pieceRow, pieceCol,
									row, col);

							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove
									(move, board)) {
								model.move(move);

								// No longer checked, the move is over.
								if (!model.inCheck(AI))
									return true;

								// Still checked, cancel the last move.
								else
									model.move(new Move(row, col, 
											pieceRow, pieceCol));
							}
						}
				}

				// Skip the King piece.
				if (piece == 5)
					piece = 3;

				// Decrement to move another piece.
					piece--;
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
		
		//FIXME: Does not check that the piece can be lost.
		
		int piece = 15;

		// Move all pieces to check the player.
		while (piece >= 0) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Move every piece to every possible location.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++) {
						move = new Move(pieceRow, pieceCol, 
								row, col);

						// Continue if this is a valid move.
						if (chessPieces[piece].isValidMove
								(move, board)) {

							model.move(move);

							// No longer checked, the move is over.
							if (!model.inCheck(HUMAN))
								return true;

							// Still checked, cancel the last move.
							else 
								model.move(new Move(row, col,
									pieceRow, pieceCol));
						}
					}
			}

			// Skip the King piece.
			if (piece == 5)
				piece = 3;

			// Decrement to move another piece.
			else 
				piece--;
		}

		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Determine if any of your 
	 * pieces are in danger. If so, move it to safety.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean avoidDanger() {

		int piece = 16;

		// Move all pieces to check the player.
		while (piece < 32) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Scan the board for an AI Piece.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)
						if (model.pieceAt(row, col).player() == AI)

							// Move the AI piece out of danger.
							for (row = 0; row <= 7; row++)
								for (col = 0; col <= 7; col++) {
									move = new Move(pieceRow, pieceCol,
											row, col);

									// Continue if move is valid.
									if (chessPieces[piece].isValidMove
											(move, board)) {
										model.move(move);
										return true;
									}
								}
				// Skip the King piece.
				if (piece == 27)
					piece = 29;

				// Increment to move another piece.
				else
					piece++;
			}
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Take an opponent piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean capture() {

		int piece = 15;

		// Move all pieces to check the player.
		while (piece > -1) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Scan the board for a Human piece.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)
						if (model.pieceAt(row, col).player() == HUMAN) {
							
							// Construct a move to capture this piece.
							move = new Move(pieceRow, pieceCol,
									row, col);

							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove
									(move, board)) {
								model.move(move);
								return true;
							}
						}
			}
			// Skip the King piece.
			if (piece == 5)
				piece = 3;

			// Decrement to move another piece.
			else
				piece--;
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
		
		//FIXME Right now is moves all pawns beyond the first two rows.
		
		// Move all pawns first.
		for (int pawnNum = 15; pawnNum > 7; pawnNum--)
			
			// Make sure this is an active piece.
			if (chessPieces[pawnNum] != null) {

				// Save the starting location of the piece.
				int pawnRow = chessPieces[pawnNum].getRow
						(chessPieces[pawnNum], board);
				int pawnCol = chessPieces[pawnNum].getCol
						(chessPieces[pawnNum], board);

				// If the pawn is in the starting row, move two spaces.
				if (pawnRow == 1)
					move = new Move(pawnRow, pawnCol,
							pawnRow + 2, pawnCol);
				
				// If the pawn is in the second row, advance one space.
				else if (pawnRow == 2)
					move = new Move(pawnRow, pawnCol,
							pawnRow + 1, pawnCol);
				
				// Continue if this is a valid move.
				if (chessPieces[pawnNum].isValidMove
						(move, board)) {
					model.move(move);
					return true;
				}
			}
		return false;
	}
}