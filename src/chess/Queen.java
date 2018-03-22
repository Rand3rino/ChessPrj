/**********************************************************************
 * Project 3: Chess Game
 * 
 * This class handles movement for the Queen piece.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
 *********************************************************************/

package chess;

public class Queen extends ChessPiece {

	/******************************************************************
	 * Constructor for the queen's owner
	 * @param player Player's enumerated value
	 *****************************************************************/
	protected Queen(Player player) {
		super(player);
	}

	
	/******************************************************************
	 * Checks to see if the queen is making a valid move
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
		
		if (super.isHoriOrVert(move, board) || isPerfDiag(move, board))
			return super.isOpenDiag(move, board);

		if (move.toRow == move.fromRow)
			return super.isOpenHori(move, board);

		if (move.toColumn == move.fromColumn)
			return super.isOpenVert(move, board);

		return super.isOpenDiag(move, board);
	}

	
	/******************************************************************
	 * Checks to see if the queen is making a valid move
	 * 
	 * @return "Queen the string that represents the piece's name
	 *****************************************************************/
	@Override
	public String type() {
		return "Queen";
	}
}
