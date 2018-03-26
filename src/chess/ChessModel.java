/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ChessModel implements IChessModel {

	/** The two dimensional board */
	private IChessPiece[][] board = new IChessPiece[8][8];

	/** Variable to hold a single piece */
	private IChessPiece piece;

	/** The player variable */
	private Player player;

	/** Static Variable for AI Player */
	private static Player HUMAN = Player.WHITE;
	
	/** Static Variable for AI Player */
	private static Player AI = Player.BLACK;
	
	/** Array to hold all chessPieces */
	private ChessPiece[] chessPieces = new ChessPiece[32];
	
	private Move move;

	// Declare other instance variables as needed

	/**************************************************************
	 * Constructor of the chess model. First creating an array of
	 * chess pieces and then assign them to their start positions.
	 *************************************************************/
	public ChessModel() {
		assignBlackPieces();
		assignWhitePieces();
		placeBlackPieces();
		placeWhitePieces();
		player = Player.WHITE;
	}

	/**************************************************************
	 * This method assigns the Black player's row.
	 *************************************************************/
	private void assignBlackPieces() {

		// Instantiate Black player's back row.
		chessPieces[0] = new Rook(Player.BLACK, 5);
		chessPieces[1] = new Knight(Player.BLACK, 3);
		chessPieces[2] = new Bishop(Player.BLACK, 4);
		chessPieces[3] = new Queen(Player.BLACK, 9);
		chessPieces[4] = new King(Player.BLACK, 10);
		chessPieces[5] = new Bishop(Player.BLACK, 4);
		chessPieces[6] = new Knight(Player.BLACK, 3);
		chessPieces[7] = new Rook(Player.BLACK, 5);

		// Instantiate Black player's front row.
		chessPieces[8]  = new Pawn(Player.BLACK, 1);
		chessPieces[9]  = new Pawn(Player.BLACK, 1);
		chessPieces[10] = new Pawn(Player.BLACK, 1);
		chessPieces[11] = new Pawn(Player.BLACK, 1);
		chessPieces[12] = new Pawn(Player.BLACK, 1);
		chessPieces[13] = new Pawn(Player.BLACK, 1);
		chessPieces[14] = new Pawn(Player.BLACK, 1);
		chessPieces[15] = new Pawn(Player.BLACK, 1);
	}
	
	/**************************************************************
	 * This method assigns the White player's pieces.
	 *************************************************************/
	private void assignWhitePieces() {

		// Instantiate White player's front row.
		chessPieces[16] = new Pawn(Player.WHITE, 1);
		chessPieces[17] = new Pawn(Player.WHITE, 1);
		chessPieces[18] = new Pawn(Player.WHITE, 1);
		chessPieces[19] = new Pawn(Player.WHITE, 1);
		chessPieces[20] = new Pawn(Player.WHITE, 1);
		chessPieces[21] = new Pawn(Player.WHITE, 1);
		chessPieces[22] = new Pawn(Player.WHITE, 1);
		chessPieces[23] = new Pawn(Player.WHITE, 1);

		// Instantiate White player's back row.
		chessPieces[24] = new Rook(Player.WHITE, 5);
		chessPieces[25] = new Knight(Player.WHITE, 3);
		chessPieces[26] = new Bishop(Player.WHITE, 4);
		chessPieces[27] = new Queen(Player.WHITE, 9);
		chessPieces[28] = new King(Player.WHITE, 10);
		chessPieces[29] = new Bishop(Player.WHITE, 4);
		chessPieces[30] = new Knight(Player.WHITE, 3);
		chessPieces[31] = new Rook(Player.WHITE, 5);
	}

	/**************************************************************
	 * This method places Black player's pieces on the chess board.
	 *************************************************************/
	private void placeBlackPieces() {

		// The Black player's back line is on row 0.
		int row = 0;

		// Place the back line.
		for (int col = 0; col < 8; col ++)

			// The White player's pieces begin at chessPieces[0].
			board[row][col] = chessPieces[col];

		// The Black player's front line is on row 1.
		row = 1;

		// Place the front line.
		for (int col = 0; col < 8; col ++)

			// The Black player's pawns begin at chessPieces[8].
			board[row][col] = chessPieces[col + 8];

	}

	/**************************************************************
	 * This method places Black player's pieces on the chess board.
	 *************************************************************/
	private void placeWhitePieces() {

		// The White player's front line is on row 6.
		int row = 6;

		// Place the front line.
		for (int col = 0; col < 8; col ++)

			// The White player's pawns begin at chessPieces[16].
			board[row][col] = chessPieces[col + 16];

		// The White Player's back line is on row 7.
		row = 7;

		// Place the back line.
		for (int col = 0; col < 8; col ++)

			// The White player's pieces begin at chessPieces[24].
			board[row][col] = chessPieces[col + 24];
	}

	/******************************************************************
	 * This method determines if the game is over by seeing
	 * if there is a checkmate.
	 *****************************************************************/
	public boolean gameOver() {

		// FIXME: Uncomment at step 10

		// // If there is a check, move pieces to avoid check.
		// if (inCheck(currentPlayer() ) ) {
		//
		// // FIXME: MOVE PIECES TO AVOID CHECK
		// }
		//
		// // Display message if Player cannot avoid check.
		// if (inCheck(currentPlayer() ) ) {
		// JOptionPane.showMessageDialog(null,
		// "Checkmate. Game Over.");
		// return true;
		// }
		return false;
	}

	/******************************************************************
	 * Returns whether the piece at location [move.fromRow, 
	 * move.fromColumn] is allowed to move to location 
	 * [move.toRow, move.toColumn].
	 *
	 * @param move A chess.Move object describing the move to be made.
	 * @return true if the proposed move is valid, false otherwise.
	 * @throws IndexOutOfBoundsException if either [move.fromRow, 
	 * move.fromColumn] or [move.toRow, move.toColumn] don't
	 * represent valid locations on the board.
	 *****************************************************************/
	public boolean isValidMove(Move move) {
		
		if (pieceAt(move.fromRow, move.fromColumn) != null)
			//make sure the selected piece is the current player's piece
			if (player == pieceAt(move.fromRow, move.fromColumn).
			  player())
				return pieceAt(move.fromRow, move.fromColumn).
						isValidMove(move, board);
		return false;
		
//		// If move isn't within the board, throw error.
//		if (move.toRow < 0 || move.toRow > 9 || 
//				move.toColumn < 0 || move.toColumn > 9)
//			throw new IndexOutOfBoundsException();
//
//		// Prevents the piece being dropped on the same square.
//		if (move.fromRow == move.toRow && 
//				move.fromColumn == move.toColumn)
//			return false;
//
//		// Prevents the player to move from an empty square.
//		else if(board[move.fromRow][move.fromColumn] == null)
//			return false;
//
//		// Prevents the player from taking their own piece.
//		else if(board[move.toRow][move.toColumn] != null)
//			if(board[move.toRow][move.toColumn].
//					player() == currentPlayer())
//				return false;
//
//		return true;
	}

	/******************************************************************
	 * Moves the piece from location [move.fromRow, move.fromColumn] 
	 * to location [move.toRow, move.toColumn].
	 *
	 * @param move A chess.Move object describing the move to be made.
	 * @throws IndexOutOfBoundsException if either [move.fromRow, 
	 * move.fromColumn] or [move.toRow, move.toColumn] don't 
	 * represent valid locations on the board.
	 *************************************************************/
	public void move(Move move) {

		// FIXME: Necessary?
		//		// If move isn't within the board, throw error.
		//		if (move.toRow < 0 || move.toRow > 9 || 
		//			move.toColumn < 0 || move.toColumn > 9)
		//			throw new IndexOutOfBoundsException();

		// Save the piece that is moving.
		piece = pieceAt(move.fromRow, move.fromColumn);

		// Empty the space that the piece is leaving.
		board[move.fromRow][move.fromColumn] = null;

		// pieceAt(move.toRow, move.toColumn) == really dead.
		board[move.toRow][move.toColumn] = null;
		board[move.toRow][move.toColumn] = piece;

		// Place the piece on the new square.
		board[move.toRow][move.toColumn] = piece;
		
		// Promote any pawns.
		promotion();
		player = player.next();
	}

	/******************************************************************
	 * This method determines if a player's King is in check.
	 * 
	 * @param p The Player being checked.
	 * @return true of the player is in check, false if not.
	 *****************************************************************/
	public boolean inCheck(Player p) {

		boolean inCheck = false;

		int kingRow = -1;
		int kingCol = -1;

		if (p == Player.WHITE) {
			// get White king position
			kingRow = chessPieces[28].getRow(chessPieces[28], board);
			kingCol = chessPieces[28].getCol(chessPieces[28], board);
		}

		if (p == Player.BLACK) {
			// get Black king position
			kingRow = chessPieces[4].getRow(chessPieces[4], board);
			kingCol = chessPieces[4].getCol(chessPieces[4], board);
		}
		
		player = player.next();
		
		//go through entire board
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				//make sure that board space isn't null
				if(board[r][c] != null) {
					//piece player = player
					if(board[r][c].player() == player) {
						System.out.println("1");
						if(isValidMove(new Move(r, c, kingRow, kingCol))) {
							System.out.println("2");
							if(board[r][c].isValidMove(new Move(r, c, kingRow, kingCol), board)) {
								return true;
							}
						}
					}
				}
			}
		}

//		ArrayList<Move> moves = new ArrayList();
//
//		// check other player's pieces to see if it can capture King
//		player = player.next();
//
//		if (player == Player.BLACK) {
//			// add each player's move to the array list "moves"
//			for (int count = 0; count < 16; count++) {
//				int fromRow = chessPieces[count].getRow(chessPieces[count], board);
//				int fromCol = chessPieces[count].getCol(chessPieces[count], board);
//				moves.add(new Move(fromRow, fromCol, kingRow, kingCol));
//			}
//
//			// iterated to check if any move is valid
//			for (int i = 0; i < 16; i++) {
//				if (chessPieces[i].isValidMove(moves.get(i), board))
//					if(isValidMove(moves.get(i)))
//						inCheck = true;
//			}
//		}
//
//		else {
//			// add each player's move to the array list "moves"
//			for (int count = 16; count < 32; count++) {
//				int fromRow = chessPieces[count].getRow(chessPieces[count], board);
//				int fromCol = chessPieces[count].getCol(chessPieces[count], board);
//				moves.add(new Move(fromRow, fromCol, kingRow, kingCol));
//			}
//
//			// iterated to check if any move is valid
//			for (int i = 0; i < 16; i++) {
//				if (chessPieces[i + 16].isValidMove(moves.get(i), board))
//					if(isValidMove(moves.get(i)))
//						inCheck = true;
//			}
//		}

		if (inCheck) {
			JOptionPane.showMessageDialog(null, "Check");
			return true;
		}

		player = player.next();
		// Player is not in check
		return false;
	}

	/******************************************************************
	 * Return the current player. 
	 * @return Player The current player.
	 *****************************************************************/
	public Player currentPlayer() {
		return player;
	}

	/******************************************************************
	 * Return the number of rows. 
	 * @return 8 There are 8 rows in a standard chess board.
	 *****************************************************************/
	public int numRows() {
		return 8;
	}

	/******************************************************************
	 * Return the number of columns. 
	 * @return 8 There are 8 columns in a standard chess board.
	 *****************************************************************/
	public int numColumns() {
		return 8;
	}

	/******************************************************************
	 * Return the piece occupying the input location
	 * @param row The row number of the coordinate.
	 * @param col The column number of the coordinate.
	 * @return board[][] The IChessPiece in the given location.
	 *****************************************************************/
	public IChessPiece pieceAt(int row, int col) {
		return board[row][col];
	}

	/******************************************************************
	 * Returns whether the game is complete. 
	 * @return true if complete, false if not.
	 *****************************************************************/
	public boolean isComplete() {

		// If the game is over, game is complete.
		if (gameOver())
			return true;

		// FIXME: Stalemate

		// Game is not over.
		return false;
	}

	// Add other public or helper methods as needed.
	
	/******************************************************************
	 * This method will check if a pawn is ready for promotion and 
	 * promote it to a Queen.
	 *****************************************************************/
	private void promotion() {
		
		// Check if a White Pawn will be promoted.
		for (int col = 0; col < 8; col++)
			for (int pawnNumber = 16; pawnNumber < 24; pawnNumber++)
				if (board[0][col] == chessPieces[pawnNumber]) {
					chessPieces[pawnNumber] = new Queen(Player.WHITE, 9);
					board[0][col] = chessPieces[pawnNumber];
				}

		// Check if a Black Pawn will be promoted.
		for (int col = 0; col < 8; col++)
			for (int pawnNumber = 8; pawnNumber < 16; pawnNumber++)
				if (board[7][col] == chessPieces[pawnNumber]) {
					chessPieces[pawnNumber] = new Queen(Player.BLACK, 9);
					board[0][col] = chessPieces[pawnNumber];
				}

	}
	


	/******************************************************************
	 * This method is the AI feature. The AI will follow these 
	 * priorities:
	 * 		1. Check to see if it is in check. 
	 * 		2. Attempt to put the opponent into check (or checkmate).
	 * 		3. Determine if any of your pieces are in danger. 
	 * 		4. Capture an opponent piece.
	 * 		5. Move a piece (pawns first) forward towards the
	 *  		   opponent's King.
	 *****************************************************************/
	public void turnComputer() {

		// AI will always be BLACK.
		player = AI;

		// Variable to skip processes if the turn is complete.
		boolean turnComplete = false;

		// 1. Check if the AI is in check.
		turnComplete = getOutOfCheck();

		// 2. Put the opponent is in check.
		if (turnComplete) 
			turnComplete = putInCheck();

		// 3. Move a piece if it is in danger.
		if (turnComplete) 
			turnComplete = avoidDanger();

		// 4. Capture and opponent piece.
		if (turnComplete) 
			turnComplete = capture();

		// 5. Move towards the opponent King.
		if (turnComplete) 
			turnComplete = moveForward();

	}

	/******************************************************************
	 * This method is part of the AI feature. Check to see if it is 
	 * in check. If so, get out of check by moving the King or placing 
	 * a piece to block the check.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean getOutOfCheck() {
		
		int kingRow = chessPieces[4].getRow(chessPieces[4], board);
		int kingCol = chessPieces[4].getCol(chessPieces[4], board);
		
		if (inCheck(AI)) {
			for (int row = -1, col = -1; col < 2; col++)
				
				chessPieces[4].isValidMove(move, board);
				// if (!inCheck(AI))
				// return true;
			
			// Attempt to get out of check.
			// if (!inCheck(AI))
				// return true;
			// First try King, then other pieces to block the check.
			return true;
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Attempt to put the 
	 * opponent into check (or checkmate) without losing its piece. 
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean putInCheck() {
		// Move all.
		if (inCheck(HUMAN)) 
			return true;
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Determine if any of your 
	 * pieces are in danger. If so, move it to safety.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean avoidDanger() {
		if (inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.
			return true;
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Take an opponent piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean capture() {
		if (inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.	
			return true;
		}
		return false;
	}

	/******************************************************************
	 * This method is part of the AI feature. Move a piece (pawns 
	 * first) forward towards the opponent's King. Check to see if 
	 * that piece is in danger of being captured. If so, move a 
	 * different piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private boolean moveForward() {
		if (inCheck(player)) {
			// Attempt to get out of check.
			// First try King, then other pieces to block the check.
			return true;
		}
		return false;
	}
}