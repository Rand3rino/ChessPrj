/**********************************************************************
 * Project 3: Chess Game ChessPiece
 * 
 * This class is the base class for all chess pieces.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 19, 2018.
 *********************************************************************/

package chess;

public abstract class ChessPiece implements IChessPiece {

	/** Player who owns this piece */
	private Player owner;

	/******************************************************************
	 * Assign the player to this piece.
	 *
	 * @param the player that owns this piece.
	 *****************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/******************************************************************
	 * Return the player that owns this piece.
	 *
	 * @return the player that owns this piece.
	 *****************************************************************/
	public Player player() {
		return owner;
	}

	/******************************************************************
	 * Return the column the piece is in
	 * 
	 * @return c the column the piece is in, -1 if piece isn't found
	 *****************************************************************/
	public int getCol(ChessPiece piece, IChessPiece board[][]) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board[r][c].getClass() == piece.getClass())
					if (board[r][c].player() == piece.player())
						return c;
			}
		}
		return -1;
	}

	/******************************************************************
	 * Return the row the piece is in
	 * 
	 * @return r the row the piece is in, -1 if piece isn't found
	 *****************************************************************/
	public int getRow(ChessPiece piece, IChessPiece board[][]) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board[r][c].getClass() == piece.getClass())
					if (board[r][c].player() == piece.player())
						return r;
			}
		}
		return -1;
	}

	/******************************************************************
	 * Subclasses must return the type of this piece ("King", "Queen", 
	 * "Rook", etc.).  Note: In this case "type" refers to the game 
	 * of chess, not the type of the Java class.
	 *****************************************************************/
	public abstract String type();

	/******************************************************************
	 * Returns whether the piece at location [move.fromRow, 
	 * move.fromColumn] is allowed to move to location 
	 * [move.fromRow, move.fromColumn].
	 *
	 * Note: Pieces don't store their own location (because doing so
	 * would be redundant). Therefore, the [move.fromRow, move.
	 * fromColumn] component of move is necessary. 
	 *
	 * @param move A chess.Move object describing the move to be made.
	 * @param board the chess.IChessBoard in which this piece resides.
	 * @return true if the proposed move is valid, false otherwise.
	 * @throws IndexOutOfBoundsException If either 
	 *  [move.fromRow, move.fromColumn] or [move.toRow,
	 *  move.toColumn] don't represent valid locations on the board.
	 * @throws IllegalArgumentException if this object isn't 
	 *  the piece at location [move.fromRow, move.fromColumn].
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// Prevents the piece being dropped on the same square.
		if(move.fromRow == move.toRow && 
				move.fromColumn == move.toColumn)
			return false;

		// Prevents the player to move from an empty square.
		else if(board[move.fromRow][move.fromColumn] == null)
			return false;

		// Prevents the player from taking their own piece.
		if(board[move.toRow][move.toColumn] != null) 
			if(board[move.toRow][move.toColumn].player() == owner)
				return false;

		

		return true;
	}

	/******************************************************************
	 * Returns true if all of the pieces between the spot the piece
	 * wants to move to & the spot it is at are empty of pieces. This
	 * method is used if the move is diagonal.
	 * 
	 * @param move an object describing the move to be made.
	 * @param board the board in which this piece resides.
	 *****************************************************************/
	public boolean isOpenDiag(Move move, IChessPiece[][] board) {
		if(move.toRow > move.fromRow &&
				move.toColumn > move.fromColumn)
			return isOpenDiagLR(move, board);

		if(move.toRow < move.fromRow &&
				move.toColumn > move.fromColumn)
			return isOpenDiagUR(move, board);

		if(move.toRow < move.fromRow &&
				move.toColumn < move.fromColumn)
			return isOpenDiagUL(move, board);

		return isOpenDiagLL(move, board);
	}

	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for the move. This method is used if the move
	 * is to the lower right of the piece diagonally
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	private boolean isOpenDiagLR(Move move, IChessPiece[][] board) {
		for(int c = move.fromColumn + 1, r = move.fromRow + 1; 
				c < move.toColumn; c++, r++) {
			if(board[r][c] != null)
				return false;
		}


		return true;
	}

	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for the move. This method is used if the move
	 * is to the upper right of the piece diagonally
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	private boolean isOpenDiagUR(Move move, IChessPiece[][] board) {
		for(int c = move.fromColumn + 1, r = move.fromRow - 1;
				c < move.toColumn; c++, r--) {
			if(board[r][c] != null)
				return false;
		}


		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for the move. This method is used if the move
	 * is to the upper left of the piece diagonally
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	private boolean isOpenDiagUL(Move move, IChessPiece[][] board) {
		for(int c = move.fromColumn - 1, r = move.fromRow - 1; 
				c > move.toColumn; r--, c--) {
			if(board[r][c] != null)
				return false;
		}


		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for the move. This method is used if the move
	 * is to the lower left of the piece diagonally
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	private boolean isOpenDiagLL(Move move, IChessPiece[][] board) {
		for(int c = move.fromColumn - 1, r = move.fromRow + 1; 
				c > move.toColumn; c--, r++) {
			if(board[r][c] != null)
				return false;
		}


		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a horizontal move
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenHori(Move move, IChessPiece[][] board) {
		if(move.toColumn > move.fromColumn)
			return isOpenHoriRight(move, board);

		return isOpenHoriLeft(move, board);
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a horizontal & right move.
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenHoriRight(Move move, IChessPiece[][] board){

		// check for pieces in between
		for(int c = move.fromColumn + 1; c < move.toColumn; c++) {
			if(board[move.toRow][c] != null) 
				return false;
		}
		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a horizontal & left move.
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenHoriLeft(Move move, IChessPiece[][] board){

		// check for pieces in between
		for(int c = move.fromColumn - 1; c > move.toColumn; c--) {
			if(board[move.toRow][c] != null) 
				return false;
		}
		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a vertical move
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenVert(Move move, IChessPiece[][] board) {
		if(move.toRow < move.fromRow)
			return isOpenVertUp(move, board);

		return isOpenVertDown(move,board);
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a vertically up move
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenVertUp(Move move, IChessPiece[][] board){

		// check for pieces in between
		for(int r = move.fromRow - 1; r > move.toRow; r--) {
			if(board[r][move.toColumn] != null)
				return false;
		}



		return true;
	}


	/******************************************************************
	 * Test for any pieces between the piece's current position & the
	 * position chosen for a vertically down move
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if there are no pieces between it & its move
	 *  false if there are pieces between it & its move 
	 *****************************************************************/
	public boolean isOpenVertDown(Move move, IChessPiece[][] board) {
		// check for pieces in between
		for(int r = move.fromRow + 1; r < move.toRow; r++) {
			if(board[r][move.toColumn] != null)
				return false;
		}

		return true;
	}


	/******************************************************************
	 * Check to make sure that the move is perfectly horizontal or 
	 * vertical from the current piece position
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return false if the move isn't perfectly horizontal or vertical 
	 *  or else returns true
	 *****************************************************************/
	public boolean isHoriOrVert(Move move, IChessPiece[][] board) {
		if(move.toRow != move.fromRow &&
				move.toColumn != move.fromColumn)
			return false;

		return true;
	}

	/******************************************************************
	 * Check to make sure that the move is perfectly diagonal
	 * 
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 * @return true if the move is perfectly diagonal or
	 * else returns false
	 *****************************************************************/
	public boolean isPerfDiag(Move move, IChessPiece[][] board) {
		if(Math.abs(move.toRow - move.fromRow) ==
				Math.abs(move.toColumn-move.fromColumn))
			return true;

		return false;
	}

}
