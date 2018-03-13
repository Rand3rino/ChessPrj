/**********************************************************************
 * Project 3: Chess Game Piece
 *********************************************************************/

package chess;

public abstract class ChessPiece implements IChessPiece {
	
	private Player owner;
	
	protected ChessPiece(Player player) {
		this.owner = player;
	}
	
	
	/******************************************************************
	 * Return the player that owns this piece.
	 * Comment comment
	 * @return the player that owns this piece.
	 *****************************************************************/
//	Player player() {
//		return Player;
//	}

	/******************************************************************
	 * Return the type of this piece ("King", "Queen", "Rook", etc.). 
	 * Note: In this case "type" refers to the game of chess, 
	 * not the type of the Java class.
	 *
	 * @return the type of this piece
	 *****************************************************************/
	public abstract String type();

	/******************************************************************
	 * Returns whether the piece at location {@code [move.fromRow, 
	 * move.fromColumn]} is allowed to move to location 
	 * {@code [move.fromRow, move.fromColumn]}.
	 *
	 * Note: Pieces don't store their own location (because doing so
	 * would be redundant). Therefore, the {@code [move.fromRow, move.
	 * fromColumn]} component of {@code move} is necessary. 
	 * {@code this} object must be the piece at location {@code 
	 * [move.fromRow, move.fromColumn]}. (This method makes no sense
	 * otherwise.)
	 *
	 * @param move a {@link chess.Move} object describing the move 
	 *  to be made.
	 * @param board the {@link chess.IChessBoard} in which this 
	 *  piece resides.
	 * @return {@code true} if the proposed move is valid, 
	 *  {@code false} otherwise.
	 * @throws IndexOutOfBoundsException If either {@code 
	 *  [move.fromRow, move.fromColumn]} or {@code [move.toRow,
	 *  move.toColumn]} don't represent valid locations on the board.
	 * @throws IllegalArgumentException if {@code this} object isn't 
	 *  the piece at location {@code [move.fromRow, move.fromColumn]}.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// Complete this.
		return false;
	}
}
