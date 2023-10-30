/**
 * 
 */

import javax.swing.JLabel;

@SuppressWarnings("serial")

public abstract class Mover extends JLabel {
	
	// create fields for rows and columns
	private int row;
	private int column;
	
	//change in row and column
	private int dRow;
	private int dColumn;
	private int Score = 0;
	
	//to see if ghost is dead or alive
	private boolean isDead;
	

	//getters and setters
	
	//
	public int getdRow() {
		return dRow;
	}
	
	public void setdRow(int dRow) {
		this.dRow = dRow;
		
	}
	
	public int getdColumn() {
		return dColumn;
	}
	
	public void setdColumn(int dColumn) {
		this.dColumn = dColumn;
		
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getDRow() {
		return dRow;
	}

	public void setDRow(int dRow) {
		this.dRow = dRow;
	}

	public int getDColumn() {
		return dColumn;
	}

	public void setDColumn(int dColumn) {
		this.dColumn = dColumn;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	//utility methods 
	
	//move method- allows to move
	public void move() {
		row += dRow;
		column += dColumn;
	}

	//dricetion method
	public void setDirection(int direction) {
		
		//restting the direction change
		dRow = 0;
		dColumn = 0;
		
		//direction 
		if (direction == 0) //LEFT
			dColumn = -1;
		else if (direction == 1) //UP
			dRow = -1;
		else if (direction == 2) //right
			dColumn = 1;
		else if (direction == 3) //DOWN
			dRow = 1;
	}
	//tell them what direction
	public int getDirection() {
		
		if (dRow == 0 && dColumn == -1)		//left
			return 0;
		else if (dRow == -1 && dColumn == 0)	//up
			return 1;
		else if (dRow == 0 && dColumn == 1)	//right
			return 2;
		else 									//down
			return 3;
		
	}
	
	//gets the row
	public int getNextRow() {
		return row + dRow;
	}
	
	//gets the columns
	public int getNextColumn() {
		
		return column + dColumn;
	}
}
