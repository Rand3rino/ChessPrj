/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

import javax.swing.JOptionPane;

public class ChessModel implements IChessModel{

		private IChessPiece[][] board;
		
		private Player player;
		
		private ChessPiece[] chessPieces;
		
		// Declare other instance variables as needed
		
		/**************************************************************
		 * Constructor of the chess model.
		 *************************************************************/
		public ChessModel() {
			// FIXME: Complete this method, unsure what's left.
			
			assignBlackPieces();
			assignWhitePieces();
			
		}

		/**************************************************************
		 * This method assigns the Black player's row.
		 *************************************************************/
		private void assignBlackPieces() {
			
			// Instantiate Black player's back row.
			chessPieces[0]  = new Rook(Player.BLACK);
			chessPieces[1]  = new Knight(Player.BLACK);
			chessPieces[2]  = new Bishop(Player.BLACK);
			chessPieces[3]  = new King(Player.BLACK);
			chessPieces[4]  = new Queen(Player.BLACK);
			chessPieces[5]  = new Bishop(Player.BLACK);
			chessPieces[6]  = new Knight(Player.BLACK);
			chessPieces[7]  = new Rook(Player.BLACK);
			
			// Instantiate Black player's front row.
			chessPieces[8]  = new Pawn(Player.BLACK);
			chessPieces[9]  = new Pawn(Player.BLACK);
			chessPieces[10] = new Pawn(Player.BLACK);
			chessPieces[11] = new Pawn(Player.BLACK);
			chessPieces[12] = new Pawn(Player.BLACK);
			chessPieces[13] = new Pawn(Player.BLACK);
			chessPieces[14] = new Pawn(Player.BLACK);
			chessPieces[15] = new Pawn(Player.BLACK);
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
		
		public boolean gameOver() {
			
			// If there is a check, move pieces to avoid check.
			if (inCheck(currentPlayer() ) ) {
				
				// FIXME: MOVE PIECES TO AVOID CHECK
			}
			
			// FIXME: If there is a checkmate. Display game over message.
			if (true) {
				JOptionPane.showMessageDialog(null,
					"Checkmate. Game Over.");
				return true;
			}
			return false;
		}
		
		public boolean isValidMove(Move move) {
			// Complete this.
			return false;
		}
		
		public void move (Move move) {
			// Complete this.
		}
		
		/**************************************************************
		 * This method determines if a player's King is in check.
		 * @param p The current player.
		 * @return true of the player is in check, false if not.
		 *************************************************************/
		public boolean inCheck (Player p) {
			
			// Loop through all pieces of opponent's pieces for check.
			// FIXME: Add a loop.
			
				// FIXME: If there is a check, display a notice.
				if(true) {
					JOptionPane.showMessageDialog(null, "Check");
					return true;
				}
			
			// Player is not in check
			return false;
		}
		
		/**************************************************************
		 * Return the current player.
		 * @return Player The current player.
		 *************************************************************/
		public Player currentPlayer() {
			return player;
		}
		
		/**************************************************************
		 * Return the number of rows.
		 * @return 8 There are 8 rows in a standard chess board.
		 *************************************************************/
		public int numRows() {
			return 8;
		}
		
		/**************************************************************
		 * Return the number of columns.
		 * @return 8 There are 8 columns in a standard chess board.
		 *************************************************************/
		public int numColumns() {
			return 8;
		}
		
//		public IChessPiece pieceAt(Square s) {
//			// Complete this.
//		}
		
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
