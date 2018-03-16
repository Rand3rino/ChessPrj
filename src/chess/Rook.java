/**********************************************************************
 * Project 3: Chess Game
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;

public class Rook extends ChessPiece {
	
	/******************************************************************
	 * Constructor for the rook's owner
	 * 
	 * @param player Player's enumerated value
	 *****************************************************************/
	protected Rook(Player player) {
		super(player);
	}

	
	/******************************************************************
	 * Checks to see if the rook is making a valid move
	 * 
	 * @Override
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!(super.isHoriOrVert(move, board)))
				return false;
		
		return super.isOpenHori(move, board);
	}

	/******************************************************************
	 * Checks to see if the rook is making a valid move
	 * 
	 * @Override
	 * @return "Rook" the string that represents the piece's name
	 *****************************************************************/
	public String type() {
		return "Rook";
	}
}
