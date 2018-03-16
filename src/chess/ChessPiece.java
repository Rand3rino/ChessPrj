/**********************************************************************
 * Project 3: Chess Game Piece Class
 *********************************************************************/

package chess;

public abstract class ChessPiece implements IChessPiece {
	
	private Player owner;
	
	/******************************************************************
	 * Constructor for this piece's owner. 
	 * @param this.owner The player that owns this piece.
	 *****************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}
	
	
	/******************************************************************
	 * Return the player that owns this piece.
	 * @return this.owner The player that owns this piece.
	 *****************************************************************/
	public Player player() {
		return this.owner;
	}

	/******************************************************************
	 * Return the type of this piece ("King", "Queen", "Rook", etc.). 
	 * Note: In this case "type" refers to the game of chess, 
	 * not the type of the Java class.
	 * @return the type of this piece
	 *****************************************************************/
	public abstract String type();
	
	/******************************************************************
	 * This method determines if a movement is valid. This checks if 
	 * the "from" square has a piece and the "to" square is empty or 
	 * contains a piece of the opponent's color.
	 * @return validMove Whether this move is valid.
	 *****************************************************************/
	public boolean isValidMove(Move move) { 
		System.out.println("chessPiece");
		boolean validMove = false;

		return validMove;
		
	}

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
	 * @return validMove True if move is valid, false otherwise.
	 * @throws IndexOutOfBoundsException If either {@code 
	 *  [move.fromRow, move.fromColumn]} or {@code [move.toRow,
	 *  move.toColumn]} don't represent valid locations on the board.
	 * @throws IllegalArgumentException if {@code this} object isn't 
	 *  the piece at location {@code [move.fromRow, move.fromColumn]}.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean validMove = false;
		return validMove;
	}
	
	int column, column1;
	
	//check to see if the rows match up
	if(column != column1) {
		return false;
	}
	
	return true;
}

private boolean checkVertical() {
	int column, column1;
	
	//check to see if columns match up
	//FIXME column 
	if(column != column1) {
		return false;
	}
	
	return true;
}

	//returns true if piece is between spaces
	private boolean pieceInBetween() {
		int toRow, toCol;
		int fromRow, fromCol;
		
		//FIXME delete below
		//Check for horizontal that goes down & right
		if(toRow > fromRow && toCol > fromCol) {
		
			for(int r = fromRow; r <= toRow; r++) {
				for(int c = fromCol; c <= toCol; c++) {
					
					//FIXME make method that checks if there is a piece in column & row
					if(isPiece(row,col))
						return true;
				}
			}
		}
		
		//check for horizontal move
		if(toRow == fromRow) {
			
			//check for pieces inbetween
			for(int c = fromCol; c <= toCol; c++) {
				if(isPiece(row,col)) 
					return true;
			}
		}
		
		//check for vertical move
		if(fromCol == toCol) {
			
			//check for pieces inbetween
			for(int r = fromRow; r <= toRow; r++) {
				if(isPiece(row, col))
					return true;
			}
		}
		
		return false;
	}
}
