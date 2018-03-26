/**********************************************************************
 * Project 3: Chess Game
 * 
 * This class handles movement for the Knight piece.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
 *********************************************************************/

package chess;

public class Knight extends ChessPiece{
	
	/******************************************************************
	 * Constructor that calls the constructor for the parent class of
	 * Knight and passes the player.
	 * @param player the current player
	 * @param value  How many points this piece is worth.
	 *****************************************************************/
	protected Knight(Player player, int value) {
		super(player, value);
	}

	/******************************************************************
	 * Method to check if the move is valid for a knight.
	 * 
	 * @param move object containing the previous and proposed position
	 * 	of the knight
	 * @param board array of chess board with all the pieces
	 * @return true if the move is valid and false if it is not
	 *****************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(!(super.isValidMove(move, board)))
			return false;

		// check if move is 2 spaces to the right or
		// left and 1 up or down
		if (Math.abs(move.fromRow - move.toRow) == 2) {
			if (Math.abs(move.fromColumn - move.toColumn) == 1)
				return true;
		}

		// check if move is 1 spaces to the right or
		// left and 2 up or down
		else if (Math.abs(move.fromRow - move.toRow) == 1) {
			if (Math.abs(move.fromColumn - move.toColumn) == 2)
				return true;
		}

		return false;
	}

	/******************************************************************
	 * Returns what the selected piece is.
	 * @return a string that says "Knight"
	 *****************************************************************/
	@Override
	public String type() {
		return "Knight";
	}
}
