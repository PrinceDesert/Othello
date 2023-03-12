package othello.model;

import java.io.Serializable;

public class OthelloMove implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer row;
	private Integer column;

	private OthelloMove(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}
	
	private OthelloMove() {
		this(OthelloBoard.getDefaultRows(), OthelloBoard.getDefaultColumns());
	}

	public static OthelloMove createMove(Integer row, Integer column) {
		if (OthelloMove.isInvalidRow(row) || OthelloMove.isInvalidColumn(column)) {
			return null;
		}
		return new OthelloMove(row, column);
	}
	
	private static boolean isInvalidRow(Integer row) {
		return row == null || row < 0 || row >= OthelloBoard.getDefaultRows();
	}

	private static boolean isInvalidColumn(Integer column) {
		return column == null || column < 0 || column >= OthelloBoard.getDefaultColumns();
	}

	public Integer getRow() {
		return this.row;
	}

	public Integer getColumn() {
		return this.column;
	}

	public void setRow(Integer row) {
		this.row = OthelloMove.isInvalidRow(row) ? getRow() : row;
	}

	public void setColumn(Integer column) {
		this.column = OthelloMove.isInvalidColumn(column) ? getColumn() : column;
	}

	@Override
	public String toString() {
		return "OthelloMove [row=" + getRow() + ", column=" + getColumn() + "]";
	}
}
