/**********************************************************************
 * Project 3: Chess Game
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;

public class Queen extends ChessPiece {

	/******************************************************************
	 * Constructor for the queen's owner
	 * 
	 * @param player Player's enumerated value
	 *****************************************************************/
	protected Queen(Player player) {
		super(player);
	}

	
	/******************************************************************
	 * Checks to see if the queen is making a valid move
	 * 
	 * @Override
	 * @param move an object describing the move 
	 *  to be made.
	 * @param board the board in which this 
	 *  piece resides.
	 *****************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) { 
		if(super.isHoriOrVert(move, board) || isPerfDiag(move, board))
			return true;
		
		if(move.toRow == move.fromRow)
			return super.isOpenHori(move, board);
		
		if(move.toColumn == move.fromColumn)
			return super.isOpenVert(move, board);
		
		return super.isOpenDiag(move, board);
	}

	
	/******************************************************************
	 * Checks to see if the queen is making a valid move
	 * 
	 * @Override
	 * @return "Queen the string that represents the piece's name
	 *****************************************************************/
	public String type() {
		return "Queen";
	}
}
