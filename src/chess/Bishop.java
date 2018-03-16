/**********************************************************************
 * Project 3: Chess Game
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;

//bishop class
public class Bishop extends ChessPiece {

	/******************************************************************
	 * Constructor for the bishop's owner
	 * 
	 * @param player Player's enumerated value
	 *****************************************************************/
	protected Bishop(Player player) {
		super(player);
	}

	
	/******************************************************************
	 * Checks to see if the bishop is making a valid move
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!(super.isPerfDiag(move, board)))
			return false;
		
		return super.isOpenDiag(move, board);
	}

	/******************************************************************
	 * Checks to see if the bishop is making a valid move
	 * 
	 * @Override
	 * @return "Bishop" the string that represents the piece's name
	 *****************************************************************/
	public String type() {
		return "Bishop";
	}
}
