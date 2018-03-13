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
		JFrame frame = new JFrame("Randy's Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}
