/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

public class ChessModel implements IChessModel{

		private IChessPiece[][] board;
		
		private Player player;
		
		private ChessPiece[] chessPieces;
		
		// Declare other instance variables as needed
		
		/**************************************************************
		 * This method instantiates the arraylist of chess pieces.
		 *************************************************************/
		public ChessModel() {
			// FIXME: Complete this method, unsure what's left.
			
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
			return false;
		}
		
		public boolean isValidMove(Move move) {
			// Complete this.
			return false;
		}
		
		public void move (Move move) {
			// Complete this.
		}
		
		public boolean inCheck (Player p) {
			return false;
		}
		
		public Player currentPlayer() {
			// Complete this.
			return Player;
		}
		
		public int numRows() {
			// Complete this.
			return 8;
		}
		
		public int numColumns() {
			// Complete this.
			return 8;
		}
		
//		public IChessPiece pieceAt(Square s) {
//			// Complete this.
//		}
		
		/******************************************************************
		 * Returns whether the game is complete.
		 *
		 * @return {@code true} if complete, {@code false} otherwise.
		 *****************************************************************/
		public boolean isComplete() {
			return false;
		}
		
		// Add other public or helper methods as needed.
}
