/**********************************************************************
 * Project 3: Chess Game Pawn Class
 * 
 * This class controls the movement abilities for the pawn piece.
 * 
 * @Author: Randy Nguyen
 *********************************************************************/

package chess;

public class Pawn extends ChessPiece {

	/******************************************************************
	 * Constructor for the pawn's owner.
	 * @param player This is the owner's enumerated value.
	 *****************************************************************/
	public Pawn(Player player) {
		super(player);
	}
	
	
	/******************************************************************
	 * This method assigns the piece title to the chess piece.
	 * @param string The title for the pawn piece.
	 *****************************************************************/
	public String type() {
		return "Pawn";
	}
	
	/******************************************************************
	 * This method determines the valid movement for this chess piece.
	 * @return validMove Whether this move is valid.
	 *****************************************************************/
	public boolean isValidMove() { 
		boolean validMove = false;
		
		// ***THIS IS THE BLACK PAWN***
		
		// If pawn's first turn allow double move
		// if (row == 1) {
		// If row + 1, col + 0 is NULL
		// Move row + 2, col + 0
			// validMove = True
						
		
		// Move row + 1, col + 0
			// validMove = True
						
		// Move row + 1, col - 1
		// If opponent piece is in this space
			// validMove = True
						
		// Move row + 1, col + 1
		// If opponent piece is in this space
			// validMove = True
		
		// ***THIS IS THE WHITE PAWN*** 
		
		// If pawn's first turn allow double move
		// if (row == 6) {
		// If row - 1, col + 0 is NULL
		// Move row - 2, col + 0
			// validMove = True
				
		// Move row - 1, col + 0
			// validMove = True
				
		// Move row - 1, col - 1
		// If opponent piece is in this space
			// validMove = True
				
		// Move row - 1, col + 1
		// If opponent piece is in this space
			// validMove = True
		
		return validMove;
	}
}
