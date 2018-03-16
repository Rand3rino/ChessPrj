/**********************************************************************
 * Project 3: Chess Game Pawn Class
 * 
 * This class controls the movement abilities for the pawn piece.
 * 
 * @Author: Randy Nguyen
 * @version: March 15, 2018
 *********************************************************************/

package chess;

public class Pawn extends ChessPiece {

	/******************************************************************
	 * Constructor for the pawn's owner.
	 * 
	 * @param player This is the owner's enumerated value.
	 *****************************************************************/
	public Pawn(Player player) {
		super(player);
	}

	/******************************************************************
	 * This method assigns the piece title to the chess piece.
	 * 
	 * @param string The title for the pawn piece.
	 *****************************************************************/
	public String type() {
		return "Pawn";
	}

	/******************************************************************
	 * This method determines the valid movement for the pawn piece.
	 * @param move  The move's current and proposed coordinates.
	 * @param board The chess board.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// This is the black pawn.
		if (player() == Player.BLACK) {
			if(moveBlackPawn(move, board))
				return true;
		}

		// This is the white pawn.
		if (player() == Player.WHITE) {
			if(moveWhitePawn(move, board))
				return true;
		}

		// There were no valid moves.
		return false;
	}
	
	/******************************************************************
	 * This method helps the isValidMove for the pawn piece, moving
	 * the black player's pawn. This piece never moves upwards.
	 * @param move  The move's current and proposed coordinates.
	 * @param board The chess board.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	private boolean moveBlackPawn(Move move, IChessPiece[][] board) {
		
		// If this is the pawn's first turn allow a double move.
		if (move.fromRow == 1) {

			if (move.toRow == move.fromRow + 2 && 
				move.toColumn == move.fromColumn)

				// If the path is clear, this move is valid.
				if (board[move.toRow - 1][move.toColumn] == null &&
					board[move.toRow][move.toColumn] == null)
					return true;
		}

		// Move row + 1, column + 0.
		if (move.toRow == move.fromRow + 1 && 
			move.toColumn == move.fromColumn)
			return true;

		// Move row + 1, column - 1.
		if (move.toRow == move.fromRow + 1 && 
			move.toColumn == move.fromColumn - 1)

			// If opponent is in this space, this is a valid move.
			if (board[move.toRow][move.toColumn] != null)
				return true;

		// Move row + 1, column + 1.
		if (move.toRow == move.fromRow + 1 && 
			move.toColumn == move.fromColumn + 1)

			// If opponent is in this space, this is a valid move.
			if (board[move.toRow][move.toColumn] != null)
				return true;
		
		// This move is not valid.
		return false;
	}
	
	/******************************************************************
	 * This method helps the isValidMove for the pawn piece, moving
	 * the white player's pawn. This piece never moves downwards.
	 * @param move  The move's current and proposed coordinates.
	 * @param board The chess board.
	 * @return true if this move is valid, false if not.
	 *****************************************************************/
	private boolean moveWhitePawn(Move move, IChessPiece[][] board) {
		
		// If this is the pawn's first turn allow a double move.
		if (move.fromRow == 6) {

			if (move.toRow == move.fromRow - 2 && 
				move.toColumn == move.fromColumn)

				// If the path is clear, this move is valid.
				if (board[move.toRow - 1][move.toColumn] == null &&
					board[move.toRow][move.toColumn] == null)
					return true;
		}

		// Move row - 1, column + 0.
		if (move.toRow == move.fromRow - 1 && 
			move.toColumn == move.fromColumn)
			return true;

		// Move row - 1, column - 1.
		if (move.toRow == move.fromRow - 1 && 
			move.toColumn == move.fromColumn - 1)

			// If opponent is in this space, this is a valid move.
			if (board[move.toRow][move.toColumn] != null)
				return true;

		// Move row - 1, column + 1.
		if (move.toRow == move.fromRow - 1 && 
			move.toColumn == move.fromColumn + 1)

			// If opponent is in this space, this is a valid move.
			if (board[move.toRow][move.toColumn] != null)
				return true;
		
		// This move is not valid.
		return false;
	}
	
}
