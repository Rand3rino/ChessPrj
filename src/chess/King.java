/**********************************************************************
 * Project 3: Chess Game
 * 
 * This class handles movement for the King piece.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
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
	@Override
	public String type() {
		return "King";
	}
	
	/******************************************************************
	 * This method determines the valid movement for this chess piece.
	 * @param move  The move's current and proposed coordinates.
	 * @param board The chess board.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) { 
		
		if(moveUpRow(move))
			return true;
		
		if(moveSameRow(move))
			return true;
		
		if(moveDownRow(move))
			return true;
		
		return false;
	}

	/******************************************************************
	 * This method helps the isValidMove method, checking movement
	 * to a higher row.
	 * @param move  The move's current and proposed coordinates.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	private boolean moveUpRow(Move move) {
		
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
		
		return false;
	}

	/******************************************************************
	 * This method helps the isValidMove method, checking movement
	 * to the same row.
	 * @param move  The move's current and proposed coordinates.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	private boolean moveSameRow(Move move) {
		
		// Move row + 0, column - 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn - 1)
			return true;
		
		// Move row + 0, column + 1.
		if (move.toRow    == move.fromRow &&
			move.toColumn == move.fromColumn + 1)
			return true;
		
		return false;
	}

	/******************************************************************
	 * This method helps the isValidMove method, checking movement
	 * to a lower row.
	 * @param move  The move's current and proposed coordinates.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	private boolean moveDownRow(Move move) {
		
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
