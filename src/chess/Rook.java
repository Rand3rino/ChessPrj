/**********************************************************************
 * Project 3: Chess Game
 * 
 * Author: Randy Nguyen
 *********************************************************************/

package chess;
//
public class Rook extends ChessPiece {
	
	public boolean isValidMove() { 
		
		
		return false;
	}
	
	public boolean checkBetweenMove() {
		
	}
	
	private boolean checkHorizontal(){
		int column, column1;
		
		//check to see if the rows match up
		if(column != column1) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkVertical() {
		int column, column1;
		
		//check to see if columns match up
		//FIXME column 
		if(column != column1) {
			return false;
		}
		
		return true;
	}
	
	//returns true if piece is between spaces
	private boolean pieceInBetween() {
		int toRow, toCol;
		int fromRow, fromCol;
		
		//FIXME delete below
		//Check for horizontal that goes down & right
		if(toRow > fromRow && toCol > fromCol) {
		
			for(int r = fromRow; r <= toRow; r++) {
				for(int c = fromCol; c <= toCol; c++) {
					
					//FIXME make method that checks if there is a piece in column & row
					if(isPiece(row,col))
						return true;
				}
			}
		}
		
		//check for horizontal move
		if(toRow == fromRow) {
			
			//check for pieces inbetween
			for(int c = fromCol; c <= toCol; c++) {
				if(isPiece(row,col)) 
					return true;
			}
		}
		
		//check for vertical move
		if(fromCol == toCol) {
			
			//check for pieces inbetween
			for(int r = fromRow; r <= toRow; r++) {
				if(isPiece(row, col))
					return true;
			}
		}
		
		return false;
	}
}
