/**********************************************************************
 * Project 3: Chess Game
 * 
 * This class handles movement for the Rook piece.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
 *********************************************************************/

package chess;

public class Rook extends ChessPiece {
	
	/******************************************************************
	 * Constructor for the rook's owner
	 * 
	 * @param player Player's enumerated value
	 * @param value  How many points this piece is worth.
	 *****************************************************************/
	protected Rook(Player player, int value) {
		super(player, value);
	}

	
	/******************************************************************
	 * Checks to see if the rook is making a valid move
	 * 
	 * @param move an object describing the move to be made.
	 * @param board the board in which this piece resides.
	 *****************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!(super.isValidMove(move, board)))
			return false;
		if(!(super.isHoriOrVert(move, board)))
				return false;
		
		if(move.toColumn != move.fromColumn)
			return super.isOpenHori(move, board);
		
		else
			return super.isOpenVert(move, board);
	}

	/******************************************************************
	 * Checks to see if the rook is making a valid move
	 * 
	 * @return "Rook" the string that represents the piece's name
	 *****************************************************************/
	@Override
	public String type() {
		return "Rook";
	}
}

