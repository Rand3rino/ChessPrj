/**********************************************************************
 * Project 3: Chess GUI
 * 
 * This class contains the main method that creates and displays the 
 * chess game GUI.
 *********************************************************************/

package chess;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChessGUI {

	public static void main(String[] args) {
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem gameItem;   
		JMenuItem restartItem;
		
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		gameItem = new JMenuItem("New Game");
		restartItem = new JMenuItem("Restart Game");
		
		fileMenu.add(gameItem);
		fileMenu.add(quitItem);
		fileMenu.add(restartItem);
		menus = new JMenuBar();
		frame.setJMenuBar(menus);
		menus.add(fileMenu);
		
		ChessPanel panel = new ChessPanel(quitItem, gameItem, restartItem);
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}
