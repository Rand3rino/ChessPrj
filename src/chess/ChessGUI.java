/**********************************************************************
 * Project 3: Chess GUI
 * 
 * This class contains the main method that creates and displays the 
 * chess game GUI.
 *********************************************************************/

package chess;

import javax.swing.JFrame;

public class ChessGUI {

	public static void main(String[] args) {
		
		Player hi = null;
		Move checkMove = new Move(1, 1, 1, 1);
		Pawn check1 = new Pawn(hi);
		
		IChessPiece
		IChessPiece board;
		check1.isisValidMove(checkMove, board)
		
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}
