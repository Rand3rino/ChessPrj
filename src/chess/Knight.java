
/**********************************************************************
 * Project 3: Chess Game  ssss
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;

public class Knight extends ChessPiece{
	
	protected Knight(Player player) {
		super(player);
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) { 
		return false;
	}

	@Override
	public Player player() {
		return null;
	}

	@Override
	public String type() {
		return "Knight";
	}
}
