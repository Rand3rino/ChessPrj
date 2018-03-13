/**********************************************************************
 * Project 3: Chess Game Panel
 * 
 * This class is responsible for presenting the graphical user 
 * interface, responding to user actions, and updating the view.
 * This game is a standard for of chess, where white moves first.
 *********************************************************************/

package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

	private JButton[][] board;

	private ChessModel model;

	// Declare other instance variables as needed

	private ButtonListener buttonListener = new ButtonListener();

	public ChessPanel() {
		// Complete this.
	}

	// Method that updates the board
	private void displayBoard() {
		// Complete this.
	}

	// Add other helper methods as needed

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			// Complete this.
		}
	}
}
