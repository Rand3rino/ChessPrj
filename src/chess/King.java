/**********************************************************************
 * Project 3: Chess Game King Class
 * 
 * This class controls the movement abilities for the King piece.
 * 
 * @Author: Randy Nguyen
 * @version: March 15, 2018
 *********************************************************************/

package chess;

public class King extends ChessPiece{

	/******************************************************************
	 * Constructor for the pawn's owner.
	 * @param player This is the owner's enumerated value.
	 *****************************************************************/
	public King(Player player) {
		super(player);
	}
	
	/******************************************************************
	 * This method assigns the piece title to the chess piece.
	 * @param string The title for the King piece.
	 *****************************************************************/
	public String type() {
		return "King";
	}
	
	/******************************************************************
	 * This method determines the valid movement for this chess piece.
	 * @param move  The move's current and proposed coordinates.
	 * @param board The chess board.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) { 
		
		// Move row - 1, column - 1.
		if (move.toRow    == move.fromRow - 1 &&
			move.toColumn == move.fromColumn - 1)
			return true;
		
		// Move row - 1, column + 0.
		if (move.toRow    == move.fromRow - 1 &&
			move.toColumn == move.fromColumn)
			return true;
		
		// Move row - 1, column + 1.
		if (move.toRow    == move.fromRow - 1 &&
			move.toColumn == move.fromColumn + 1)	
			return true;
		
		// Move row + 0, column - 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn - 1)
			return true;
		
		// Move row + 0, column + 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn + 1)
			return true;
		
		// Move row + 1, column - 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn - 1)
			return true;
		
		// Move row + 1, column + 0.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn)
			return true;
		
		// Move row + 1, column + 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn + 1)
			return true;
		
		return false;
	}
}
