/**********************************************************************
 * Project 3: Chess Game ChessPiece
 * 
 * This class is the base class for all chess pieces. All chess pieces
 * have an owner, a value, and a flag for if it has moved. This class
 * has the fundamental valid moveset of all chess pieces, can return 
 * the row and column of any chess piece and has linear searches that 
 * are used by the Rook, Bishop, and Queen.
 * 
 * @author Randy Nguyen, Sam Ventocilla, Jay Brunsting
 * @version March 27th, 2018.
 *********************************************************************/

package chess;

public abstract class ChessPiece implements IChessPiece {

	/** Player who owns this piece */
	private Player owner;

	/** Value of this piece */
	private int points;

	/** Variable for if the piece has moved */
	private boolean hasMoved;

	/******************************************************************
	 * Constructor for a chess piece. Pieces always start with 
	 * hasMoved as false.
	 *
	 * @param player The player that owns this piece.
	 * @param value  How many points this piece is worth.
	 *****************************************************************/
	protected ChessPiece(Player player, int value) {
		this.owner = player;
		this.points = value;
		this.hasMoved = false;
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
	 * Return the points this piece is worth.
	 *
	 * @return points How many points this piece is worth.
	 *****************************************************************/
	public int getPoints() {
		return points;
	}

	/******************************************************************
	 * Return if this piece has moved.
	 *
	 * @return hasMoved true if this piece has moved, false if not.
	 *****************************************************************/
	public boolean hasMoved() {
		return hasMoved;
	}

	/******************************************************************
	 * Set the status of this piece to has moved.
	 *****************************************************************/
	public void setHasMoved() {
		this.hasMoved = true;
	}

	/******************************************************************
	 * Return the column the piece is in
	 * 
	 * @return c the column the piece is in, -1 if piece isn't found
	 *****************************************************************/
	public int getCol(ChessPiece piece, IChessPiece board[][]) {
		
		// Scan through each square of the board.
		// Return the column value if the specific piece matches.
		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++)
				if(board[r][c] != null)
					if (board[r][c] == piece)
						if (board[r][c].player() == piece.player())
							return c;
		
		// This piece could not be found.
		return -1;
	}

	/******************************************************************
	 * Return the row the piece is in
	 * 
	 * @return r the row the piece is in, -1 if piece isn't found
	 *****************************************************************/
	public int getRow(ChessPiece piece, IChessPiece board[][]) {
		
		// Scan through each square of the board.
		// Return the column value if the specific piece matches.
		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++)
				if(board[r][c] != null)
					if (board[r][c] == piece)
						if (board[r][c].player() == piece.player())
							return r;
		
		// This piece could not be found.
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

		// If the move contains a negative, return false.
		if (move.fromRow < 0 || move.fromColumn < 0) {
			return false;
		}
		
		// Prevents the player to move from an empty square.
		if (board[move.fromRow][move.fromColumn] == null) {
			
			return false;
		}

		// If the move contains a negative, return false.
		if (move.toRow < 0 || move.toColumn < 0) {
			return false;
		}
				
		// Prevents the player from taking their own piece.
		if(board[move.toRow][move.toColumn] != null) 
			if(board[move.toRow][move.toColumn].player() == owner)
				return false;

		// This is a fundamentally valid move.
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
		
		// Check if there are any pieces blocking a Lower Right move.
		if(move.toRow > move.fromRow &&
				move.toColumn > move.fromColumn)
			return isOpenDiagLR(move, board);

		// Check if there are any pieces blocking an Upper Right move.
		if(move.toRow < move.fromRow &&
				move.toColumn > move.fromColumn)
			return isOpenDiagUR(move, board);

		// Check if there are any pieces blocking an Upper Left move.
		if(move.toRow < move.fromRow &&
				move.toColumn < move.fromColumn)
			return isOpenDiagUL(move, board);

		// Check if there are any pieces blocking a Lower Left move.
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
		
		// Check for pieces in between.
		for(int c = move.fromColumn + 1, r = move.fromRow + 1; 
				c < move.toColumn; c++, r++)
			if(board[r][c] != null)
				return false;

		// There are no pieces blocking this path.
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
		
		// Check for pieces in between.
		for (int c = move.fromColumn + 1, r = move.fromRow - 1;
				c < move.toColumn; c++, r--)
			if(board[r][c] != null)
				return false;

		// There are no pieces blocking this path.
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
		
		// Check for pieces in between.
		for (int c = move.fromColumn - 1, r = move.fromRow - 1; 
				c > move.toColumn; r--, c--)
			if(board[r][c] != null)
				return false;

		// There are no pieces blocking this path.
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
		
		// Check for pieces in between.
		for (int c = move.fromColumn - 1, r = move.fromRow + 1; 
				c > move.toColumn; c--, r++)
			if (board[r][c] != null)
				return false;

		// There are no pieces blocking this path.
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
		
		// Check if there are any pieces blocking a Rightward move.
		if(move.toColumn > move.fromColumn)
			return isOpenHoriRight(move, board);

		// Check if there are any pieces blocking a Leftward move.
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

		// Check for pieces in between.
		for(int c = move.fromColumn + 1; c < move.toColumn; c++)
			if(board[move.toRow][c] != null) 
				return false;
		
		// There are no pieces blocking this path.
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

		// Check for pieces in between.
		for(int c = move.fromColumn - 1; c > move.toColumn; c--)
			if(board[move.toRow][c] != null) 
				return false;
		
		// There are no pieces blocking this path.
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
		
		// Check if there are any pieces blocking an Upward move.
		if(move.toRow < move.fromRow)
			return isOpenVertUp(move, board);

		// Check if there are any pieces blocking a Downward move.
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

		// Check for pieces in between.
		for(int r = move.fromRow - 1; r > move.toRow; r--)
			if(board[r][move.toColumn] != null)
				return false;

		// There are no pieces blocking this path.
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
	 *  false if there are pieces between it & its move. 
	 *****************************************************************/
	public boolean isOpenVertDown(Move move, IChessPiece[][] board) {
		
		// Check for pieces in between.
		for(int r = move.fromRow + 1; r < move.toRow; r++)
			if(board[r][move.toColumn] != null)
				return false;

		// There are no pieces blocking this path.
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
	 * @return false if the move isn't perfectly horizontal or 
	 *  vertical or else returns true.
	 *****************************************************************/
	public boolean isHoriOrVert(Move move, IChessPiece[][] board) {
		
		// If the rows or columns are different, move is not straight.
		if(move.toRow != move.fromRow &&
				move.toColumn != move.fromColumn)
			return false;

		// This move is perfectly Horizontal or Vertical.
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
		
		// If the difference of the rows and the columns are the same
		// then this move is perfectly diagonal.
		if (Math.abs(move.toRow - move.fromRow) ==
				Math.abs(move.toColumn - move.fromColumn))
			return true;

		// This move is not perfectly diagonal.
		return false;
	}
}
