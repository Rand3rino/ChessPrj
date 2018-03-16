
/**********************************************************************
 * Project 3: Chess Game f
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;

public class Knight extends ChessPiece{
	
	protected Knight(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "Knight";
	}
	
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		return false;		
	}
}
