/**********************************************************************
 * Project 3: Chess Game
 *********************************************************************/

package chess;

public class ChessModel implements IChessModel{

		private IChessPiece[][] board;
		
		private Player player;
		
		// Declare other instance variables as needed
		
		public ChessModel() {
			// Complete this
		}
		
		public boolean gameOver() {
			return false;
		}
		
		public boolean isValidMove(Move move) {
			System.out.println("chessModel");
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
			return player;
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
