/**********************************************************************
 * Project 3: Chess Game King Class
 * 
 * This class controls the movement abilities for the King piece.
 * 
 * @Author: Randy Nguyen
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
	 * @return validMove Whether this move is valid.
	 *****************************************************************/
	public boolean isValidMove() { 
		boolean validMove = false;
		
		// Move row - 1, col - 1
			// validMove = True
		// Move row - 1, col + 0
			// validMove = True
		// Move row - 1, col + 1
			// validMove = True
		
		// Move row + 0, col - 1
			// validMove = True
		// Move row + 0, col + 1
			// validMove = True
		
		// Move row + 1, col - 1
		    // TvalidMove = True
		// Move row + 1, col + 0
		    // validMove = True
		// Move row + 1, col + 1
			// validMove = True
		
		return validMove;
	}
}
