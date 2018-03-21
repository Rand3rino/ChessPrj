/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ChessModel implements IChessModel {

	/** The two dimensional board */
	private IChessPiece[][] board;

	/** Variable to hold a single piece */
	private IChessPiece piece;

	/** The player variable */
	private Player player;

	/** Array to hold all chessPieces */
	private ChessPiece[] chessPieces;
	
	private Player playerName;

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
		chessPieces[0] = new Rook(Player.BLACK);
		chessPieces[1] = new Knight(Player.BLACK);
		chessPieces[2] = new Bishop(Player.BLACK);
		chessPieces[3] = new King(Player.BLACK);
		chessPieces[4] = new Queen(Player.BLACK);
		chessPieces[5] = new Bishop(Player.BLACK);
		chessPieces[6] = new Knight(Player.BLACK);
		chessPieces[7] = new Rook(Player.BLACK);

		// Instantiate Black player's front row.
		chessPieces[8] = new Pawn(Player.BLACK);
		chessPieces[9] = new Pawn(Player.BLACK);
		chessPieces[10] = new Pawn(Player.BLACK);
		chessPieces[11] = new Pawn(Player.BLACK);
		chessPieces[12] = new Pawn(Player.BLACK);
		chessPieces[13] = new Pawn(Player.BLACK);
		chessPieces[14] = new Pawn(Player.BLACK);
		chessPieces[15] = new Pawn(Player.BLACK);
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
	 * This method assigns the White player's pieces.
	 *************************************************************/
	private void assignWhitePieces() {

		// Instantiate White player's front row.
		chessPieces[16] = new Pawn(Player.WHITE);
		chessPieces[17] = new Pawn(Player.WHITE);
		chessPieces[18] = new Pawn(Player.WHITE);
		chessPieces[19] = new Pawn(Player.WHITE);
		chessPieces[20] = new Pawn(Player.WHITE);
		chessPieces[21] = new Pawn(Player.WHITE);
		chessPieces[22] = new Pawn(Player.WHITE);
		chessPieces[23] = new Pawn(Player.WHITE);

		// Instantiate White player's back row.
		chessPieces[24] = new Rook(Player.WHITE);
		chessPieces[25] = new Knight(Player.WHITE);
		chessPieces[26] = new Bishop(Player.WHITE);
		chessPieces[27] = new King(Player.WHITE);
		chessPieces[28] = new Queen(Player.WHITE);
		chessPieces[29] = new Bishop(Player.WHITE);
		chessPieces[30] = new Knight(Player.WHITE);
		chessPieces[31] = new Rook(Player.WHITE);
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
		// If move isn't within the board, throw error.
		if (move.toRow < 0 || move.toRow > 9 || 
			move.toColumn < 0 || move.toColumn > 9)
			throw new IndexOutOfBoundsException();
		
		// Prevents the piece being dropped on the same square.
		if (move.fromRow == move.toRow && 
		    move.fromColumn == move.toColumn)
			return false;
		
		// Prevents the player to move from an empty square.
		else if(board[move.fromRow][move.fromColumn] == null)
			return false;
		
		// Prevents the player from taking their own piece.
		else if(board[move.toRow][move.toColumn].
				player() == currentPlayer())
			return false;

		return true;
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

		// If move isn't within the board, throw error.
		if (move.toRow < 0 || move.toRow > 9 || 
			move.toColumn < 0 || move.toColumn > 9)
			throw new IndexOutOfBoundsException();

		// Save the piece that is moving.
		piece = pieceAt(move.fromRow, move.fromColumn);

		// Empty the space that the piece is leaving.
		board[move.fromRow][move.fromColumn] = null;

		// FIXME: Do something if the piece is capturing another?

		// Place the piece on the new square.
		board[move.toRow][move.toColumn] = piece;
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
		
		
		if(this.currentPlayer() == Player.WHITE) {
			//get White king position
			kingRow = chessPieces[27].getRow(chessPieces[27], board);
			kingCol = chessPieces[27].getCol(chessPieces[27], board);
		}
		
		if(this.currentPlayer() == Player.BLACK) {
			//get Black king position
			kingRow = chessPieces[3].getRow(chessPieces[3], board);
			kingCol = chessPieces[3].getCol(chessPieces[3], board); 
		}
		
		
		ArrayList <Move> moves = new ArrayList();
		
		//check other player's pieces to see if it can capture King
		playerName.next();
		
		if(playerName == Player.BLACK) {
			//add each player's move to the array list "moves"
			for(int count = 0; count < 16; count++) {
				int fromRow = chessPieces[count].
						getRow(chessPieces[count], board);
				int fromCol = chessPieces[count].
						getCol(chessPieces[count], board);
				moves.add(new Move(fromRow, fromCol, kingRow, kingCol));
			}
			
			//iterated to check if any move is valid
			for(int i = 0; i < 16; i++) {
				if(chessPieces[i].isValidMove(moves.get(i), board))
					inCheck = true;
			}			
		}
		
		else {
			//add each player's move to the array list "moves"
			for(int count = 16; count < 32; count++) {
				int fromRow = chessPieces[count].
						getRow(chessPieces[count], board);
				int fromCol = chessPieces[count].
						getCol(chessPieces[count], board);
				moves.add(new Move(fromRow,fromCol,kingRow, kingCol));
			}
			
			//iterated to check if any move is valid
			for(int i = 0; i < 16; i++) {
				if(chessPieces[i+16].isValidMove(moves.get(i), board))
					inCheck = true;
			}
		}
		
		if(inCheck) {
			 JOptionPane.showMessageDialog(null, "Check");
			 return true;
	}
		
		
		playerName.next();
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
}