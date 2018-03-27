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

	/**Variable to hold a temporary piece*/
	private IChessPiece temp;

	/** The player variable */
	private Player player;

	//	/** Static Variable for AI Player */
	//	private static Player HUMAN = Player.WHITE;
	//
	//	/** Static Variable for AI Player */
	//	private static Player AI = Player.BLACK;

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

			// The Black player's pieces begin at chessPieces[0].
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

		if (move.fromRow < 0 || move.fromColumn < 0) 
			return false;
		
		if (pieceAt(move.fromRow, move.fromColumn) != null) {
			//make sure the selected piece is the current player's piece
			if (player == pieceAt(move.fromRow, move.fromColumn).
					player()) {				
				return (pieceAt(move.fromRow, move.fromColumn).
						isValidMove(move, board)); 
			}
		}
		return false;
	}

	public boolean stillInCheck(Move move) {
		temp = board[move.toRow][move.toColumn];
		int row = move.toRow;
		int col = move.toColumn;

		move(move);
		boolean check = (inCheck(player));
		move(new Move(move.toRow, move.toColumn, move.fromRow, move.fromColumn));
		board[row][col] = temp;
		return !check;
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

		// Save the piece that is moving.
		piece = pieceAt(move.fromRow, move.fromColumn);


		// Empty the space that the piece is leaving.
		board[move.fromRow][move.fromColumn] = null;

		// pieceAt(move.toRow, move.toColumn) == really dead.
		board[move.toRow][move.toColumn] = null;

		// Place the piece on the new square.
		board[move.toRow][move.toColumn] = piece;

		// This piece has moved at least once.
		for (int pieceNum = 0; pieceNum < 32; pieceNum++)
			if (chessPieces[pieceNum] == piece)
				chessPieces[pieceNum].setHasMoved();

		// Promote any pawns.
		promotion();
	}

	/******************************************************************
	 * This method determines if a player can castle short-side.
	 * 
	 * @param p The Player attempting to castle.
	 * @return true of the player can castle, false if not.
	 *****************************************************************/
	public boolean castleShortSide(Player p) {


		if (p == Player.BLACK)
			if (chessPieces[4].hasMoved() || chessPieces[7].hasMoved())
				return false;
			else if (board[0][5] != null || board[0][6] != null)
				return false;

		if (p == Player.WHITE)
			if (chessPieces[28].hasMoved() || chessPieces[31].hasMoved())
				return false;
			else if (board[7][5] != null || board[7][6] != null)
				return false;

		return true;
	}

	/******************************************************************
	 * This method determines if a player can castle long-side.
	 * 
	 * @param p The Player attempting to castle.
	 * @return true of the player can castle, false if not.
	 *****************************************************************/
	public boolean castleLongSide(Player p) {

		if (p == Player.BLACK)
			if (chessPieces[4].hasMoved() || chessPieces[0].hasMoved())
				return false;
			else if (board[0][1] != null || board[0][2] != null
					|| board[0][3] != null)
				return false;

		if (p == Player.WHITE)
			if (chessPieces[28].hasMoved() || chessPieces[24].hasMoved())
				return false;
			else if (board[7][1] != null || board[7][2] != null
					|| board[7][3] != null)
				return false;

		return true;
	}

	/******************************************************************
	 * This method performs a short-side castle.
	 * 
	 * @param p The Player castling.
	 *****************************************************************/
	public void moveCastleShortSide(Player p) {

		if (p == Player.BLACK) {
			board[0][4] = null;
			board[0][7] = null;

			chessPieces[4] = new King(Player.BLACK, 10);
			chessPieces[4].setHasMoved();
			board[0][6] = chessPieces[4];

			chessPieces[7] = new Rook(Player.BLACK, 5);
			chessPieces[7].setHasMoved();
			board[0][5] = chessPieces[7];
		}
		if (p == Player.WHITE) {
			board[7][4] = null;
			board[7][7] = null;

			chessPieces[28] = new King(Player.WHITE, 10);
			chessPieces[28].setHasMoved();
			board[7][6] = chessPieces[28];

			chessPieces[31] = new Rook(Player.WHITE, 5);
			chessPieces[31].setHasMoved();
			board[7][5] = chessPieces[31];
		}
	}

	/******************************************************************
	 * This method performs a long-side castle.
	 * 
	 * @param p The Player castling.
	 *****************************************************************/
	public void moveCastleLongSide(Player p) {

		if (p == Player.BLACK) {
			board[0][4] = null;
			board[0][0] = null;

			chessPieces[4] = new King(Player.BLACK, 10);
			chessPieces[4].setHasMoved();
			board[0][2] = chessPieces[4];

			chessPieces[0] = new Rook(Player.BLACK, 5);
			chessPieces[0].setHasMoved();
			board[0][3] = chessPieces[0];
		}
		if (p == Player.WHITE) {
			board[7][4] = null;
			board[7][0] = null;

			chessPieces[28] = new King(Player.WHITE, 10);
			chessPieces[28].setHasMoved();
			board[7][2] = chessPieces[28];

			chessPieces[24] = new Rook(Player.WHITE, 5);
			chessPieces[24].setHasMoved();
			board[7][3] = chessPieces[24];
		}
	}

	/******************************************************************
	 * This method determines if a player's King is in check.
	 * 
	 * @param p The Player being checked.
	 * @return true of the player is in check, false if not.
	 *****************************************************************/
	public boolean inCheck(Player p) {

		int kingRow = -2;
		int kingCol = -2;

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

		changePlayer();

		//go through entire board
		for(int r = 0; r < 8; r++)
			for(int c = 0; c < 8; c++)

				//make sure that board space isn't null
				if(board[r][c] != null)

					//piece player = player
					if(board[r][c].player() == player)
						if(isValidMove(new Move(r, c, kingRow,
								kingCol)))
							//	if(board[r][c].isValidMove(new Move(r, c,
							//kingRow, kingCol), board)) 
						{
							changePlayer();
							return true;
						}
		// Player is not in check
		changePlayer();
		return false;
	}

	/******************************************************************
	 * This method determines if a player's King is in checkmate.
	 * 
	 * @param p The Player being checked.
	 * @return true of the player is in check, false if not.
	 *****************************************************************/
	public boolean inCheckMate(Player p) {

		if (p == Player.BLACK)
			return blackCheckMate(15);
		else 
			return whiteCheckMate(16);

	}

	/******************************************************************
	 * This method determines if the Black King is in checkmate.
	 * 
	 * @param piece Black has pieces in array location 15 to 0.
	 * @return true of the player is in check, false if not.
	 *****************************************************************/
	private boolean blackCheckMate(int piece) {

		// Move all other pieces to get out of check.
		while (piece >= 0) {

			// Continue if this piece is active.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Move every piece to every possible location.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)

						// Continue if this is a valid move.
						if (chessPieces[piece].isValidMove(new Move
								(pieceRow, pieceCol, row, col), board)) {

							if(board[row][col] != null)
								temp = pieceAt(row, col);

							move(new Move(pieceRow, pieceCol, row, col));

							// No longer checked, the move is over.
							if (!inCheck(Player.BLACK)) {
								move(new Move(row, col, pieceRow, pieceCol));
								board[row][col] = temp;
								return false;
							}
							move(new Move(row, col, pieceRow, pieceCol));
							board[row][col] = temp;
						}
				//decrement the move to another piece
				piece--;
			}


		}
		return true;
	}

	/******************************************************************
	 * This method determines if the White King is in checkmate.
	 * 
	 * @param piece White has pieces in array location 16 to 32.
	 * @return true of the player is in check, false if not.
	 *****************************************************************/
	private boolean whiteCheckMate(int piece) {
		// Move all other pieces to get out of check.
		while (piece < 32) {

			// Continue if this piece is active.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Move every piece to every possible location.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++) {
						if(isValidMove(new Move(pieceRow, pieceCol, row, col))) {
							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove(new Move
									(pieceRow, pieceCol, row, col), board)) {

								if(board[row][col] != null)
									temp = pieceAt(row, col);

								move(new Move(pieceRow, pieceCol, row, col));
								// No longer checked, the move is over.
								if (!inCheck(Player.WHITE)) {
									move(new Move(row, col, pieceRow, pieceCol));
									board[row][col] = temp;
									return false;
								}
								move(new Move(row, col, pieceRow, pieceCol));
								board[row][col] = temp;
							}
						}
					}
				piece++;
			}	
		}
		return true;
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
		if (inCheckMate(Player.BLACK) || inCheckMate(Player.WHITE) ) {
			return true;
		}
		else return false;
	}

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

	/******
	 * FIXME
	 *****/
	public void changePlayer() {
		player = player.next();
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
	public Move turnComputer() {
		
		// AI will always be BLACK.
		player = Player.BLACK;
		
		Move moveAI;

		// 1. Check if the AI is in check.
		moveAI = getOutOfCheck();

		// 2. Put the opponent is in check.
		if (moveAI == null)
			moveAI = putInCheck();

		// 3. Move a piece if it is in danger.
		if (moveAI == null)
			moveAI = avoidDanger();

		// 4. Capture and opponent piece.
		if (moveAI == null)
			moveAI = capture();

		// 5. Move towards the opponent King.
		if (moveAI == null)
			moveAI =  moveForward();
		
		return moveAI;

	}

	/******************************************************************
	 * This method is part of the AI feature. Check to see if it is 
	 * in check. If so, get out of check by moving the King or placing 
	 * a piece to block the check.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private Move getOutOfCheck() {

		int piece = 15;

		if (inCheck(Player.BLACK)) {

			// Save the starting location of the Black King.
			int kingRow = chessPieces[4].getRow(chessPieces[4], board);
			int kingCol = chessPieces[4].getCol(chessPieces[4], board);

			// Move the King first. Try each direction.
			for (int row = 0; row <= 7; row++)
				for (int col = 0; col <= 7; col++) {
					move = new Move(kingRow, kingCol, row, col);

					// Continue if this is a valid move.
					if (chessPieces[4].isValidMove(move, board)) {
						move(move);

						// No longer in check, the move is over.
						if (!inCheck(Player.BLACK))
							return move;

						// Still in check, cancel the last move.
						else
							move(new Move (row, col, 
								kingRow, kingCol));
						
					}
				}

			// Move all other pieces to get out of check.
			while (piece >= 0) {

				// Continue if this piece is active.
				if (chessPieces[piece] != null) {

					// Save the starting location of the piece.
					int pieceRow = chessPieces[piece].getRow
							(chessPieces[piece], board);
					int pieceCol = chessPieces[piece].getCol
							(chessPieces[piece], board);

					// Move every piece to every possible location.
					for (int row = 0; row <= 7; row++)
						for (int col = 0; col <= 7; col++) {
							move = new Move(pieceRow, pieceCol,
									row, col);

							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove
									(move, board)) {
								move(move);

								// No longer checked, the move is over.
								if (!inCheck(Player.BLACK))
									return move;

								// Still checked, cancel the last move.
								else
									move(new Move(row, col, 
											pieceRow, pieceCol));
							}
						}
				}

				// Skip the King piece.
				if (piece == 5)
					piece = 3;

				// Decrement to move another piece.
					piece--;
			}
		}
		return null;
	}

	/******************************************************************
	 * This method is part of the AI feature. Attempt to put the 
	 * opponent into check (or checkmate) without losing its piece. 
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private Move putInCheck() {
		
		//FIXME: Does not check that the piece can be lost.
		
		int piece = 15;

		// Move all pieces to check the player.
		while (piece >= 0) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Move every piece to every possible location.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++) {
						move = new Move(pieceRow, pieceCol, 
								row, col);

						// Continue if this is a valid move.
						if (chessPieces[piece].isValidMove
								(move, board)) {

							move(move);

							// No longer checked, the move is over.
							if (!inCheck(Player.WHITE))
								return move;

							// Still checked, cancel the last move.
							else 
								move(new Move(row, col,
									pieceRow, pieceCol));
						}
					}
			}

			// Skip the King piece.
			if (piece == 5)
				piece = 3;

			// Decrement to move another piece.
			else 
				piece--;
		}

		return null;
	}

	/******************************************************************
	 * This method is part of the AI feature. Determine if any of your 
	 * pieces are in danger. If so, move it to safety.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private Move avoidDanger() {

		int piece = 16;

		// Move all pieces to check the player.
		while (piece < 32) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Scan the board for an AI Piece.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)
						if (pieceAt(row, col).player() == Player.BLACK)

							// Move the AI piece out of danger.
							for (row = 0; row <= 7; row++)
								for (col = 0; col <= 7; col++) {
									move = new Move(pieceRow, pieceCol,
											row, col);

									// Continue if move is valid.
									if (chessPieces[piece].isValidMove
											(move, board)) {
										move(move);
										return move;
									}
								}
				// Skip the King piece.
				if (piece == 27)
					piece = 29;

				// Increment to move another piece.
				else
					piece++;
			}
		}
		return null;
	}

	/******************************************************************
	 * This method is part of the AI feature. Take an opponent piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private Move capture() {

		int piece = 15;

		// Move all pieces to check the player.
		while (piece > -1) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {

				// Save the starting location of the piece.
				int pieceRow = chessPieces[piece].getRow
						(chessPieces[piece], board);
				int pieceCol = chessPieces[piece].getCol
						(chessPieces[piece], board);

				// Scan the board for a Human piece.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)
						if (pieceAt(row, col).player() == Player.WHITE) {
							
							// Construct a move to capture this piece.
							move = new Move(pieceRow, pieceCol,
									row, col);

							// Continue if this is a valid move.
							if (chessPieces[piece].isValidMove
									(move, board)) 
								
								if(exchange(chessPieces[piece], move)) {
									move(move);
									return move;
								}
									
						}
			}
			// Skip the King piece.
			if (piece == 5)
				piece = 3;

			// Decrement to move another piece.
			else
				piece--;
		}
		return null;
	}

	/******************************************************************
	 * This method is part of the AI feature. Move a piece (pawns 
	 * first) forward towards the opponent's King. Check to see if 
	 * that piece is in danger of being captured. If so, move a 
	 * different piece.
	 * @return true if the move is complete, false if not.
	 *****************************************************************/
	private Move moveForward() {
		
		//FIXME Right now is moves all pawns beyond the first two rows.
		
		// Move all pawns first.
		for (int pawnNum = 15; pawnNum > 7; pawnNum--)
			
			// Make sure this is an active piece.
			if (chessPieces[pawnNum] != null) {

				// Save the starting location of the piece.
				int pawnRow = chessPieces[pawnNum].getRow
						(chessPieces[pawnNum], board);
				int pawnCol = chessPieces[pawnNum].getCol
						(chessPieces[pawnNum], board);

				// If the pawn is in the starting row, move two spaces.
				if (pawnRow == 1)
					move = new Move(pawnRow, pawnCol,
							pawnRow + 2, pawnCol);
				
				// If the pawn is in the second row, advance one space.
				else if (pawnRow == 2)
					move = new Move(pawnRow, pawnCol,
							pawnRow + 1, pawnCol);
				
				// Continue if this is a valid move.
				if (chessPieces[pawnNum].isValidMove
						(move, board)) {
					move(move);
					return move;
				}
			}
		return null;
	}

	/******************************************************************
	 * This method is part of the AI feature. Determine if a capture
	 * is a favorable move.
	 * @return true if the exchange is favorable, false if not.
	 *****************************************************************/
	private boolean exchange(ChessPiece compPiece, Move move) {
		
		ChessPiece humanPiece = (ChessPiece)pieceAt(move.toRow, 
				move.toColumn);
		
		int piece = 16;

		// Move all pieces to check the player.
		while (piece < 32) {

			// Make sure this is an active piece.
			if (chessPieces[piece] != null) {
				// Scan the board for an AI Piece.
				for (int row = 0; row <= 7; row++)
					for (int col = 0; col <= 7; col++)
						if (pieceAt(row, col) == compPiece) {
							if (compPiece.getPoints() <= humanPiece.getPoints())
								return true;
							else 
								return false;
						}

								
				// Skip the King piece.
				if (piece == 27)
					piece = 29;

				// Increment to move another piece.
				else
					piece++;
			}
		}
		
		// No Human piece can capture the AI piece, continue the move.
		return true;
	}
}
