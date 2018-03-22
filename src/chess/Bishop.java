/**********************************************************************
 * Project 3: Chess Game
 * 
 * This class handles movement for the Bishop piece.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
 *********************************************************************/

package chess;

public class Bishop extends ChessPiece {

	/******************************************************************
	 * Constructor for the bishop's owner
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
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!(super.isValidMove(move, board)))
			return false;
		
		if (!(super.isPerfDiag(move, board)))
			return false;

		return super.isOpenDiag(move, board);
	}

	/******************************************************************
	 * Checks to see if the bishop is making a valid move.
	 * @return "Bishop" the string that represents the piece's name
	 *****************************************************************/
	@Override
	public String type() {
		return "Bishop";
	}
}
