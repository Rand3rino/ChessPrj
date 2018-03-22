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
		
		int kingRow = chessPieces[4].getRow(chessPieces[4], board);
		int kingCol = chessPieces[4].getCol(chessPieces[4], board);
		
		if (model.inCheck(AI)) {
			for (int row = -1, col = -1; col < 2; col++)
				
				chessPieces[4].isValidMove(move, board);
				// if (!inCheck(AI))
				// return true;
			
			// Attempt to get out of check.
			// if (!inCheck(AI))
				// return true;
			// First try King, then other pieces to block the check.
			return true;
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