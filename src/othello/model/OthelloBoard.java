package othello.model;

import java.io.Serializable;

public class OthelloBoard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_ROWS = 8;
	private static final int DEFAULT_COLUMNS = 8;
	
	private OthelloPiece[][] board;
	private Integer rows;
	private Integer columns;
	
	private OthelloBoard(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		this.board = new OthelloPiece[rows][columns];
		this.initializeBoard();
	}
	
	private OthelloBoard() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}
	
	public static OthelloBoard createBoard(Integer rows, Integer columns) {
		if (rows * columns != getDefaultRows() * getDefaultColumns()) {
			return null;
		}
		return new OthelloBoard(rows, columns);
	}
	
	public static int getDefaultRows() {
		return DEFAULT_ROWS;
	}
	
	public static int getDefaultColumns() {
		return DEFAULT_COLUMNS;
	}
	
	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	/**
	 * Clonage en profondeur pour éviter ça : ob.getBoard()[7][7] = new
	 * OthelloPiece(OthelloColor.WHITE);
	 * 
	 * @return le tableau de pièces
	 */
	public OthelloPiece[][] getBoard() {
		return this.deepCopyBoard(this.board);
	}
	
	public OthelloPiece getPiece(OthelloMove move) {
		return this.deepCopyPiece(this.board[move.getRow()][move.getColumn()]);
	}

	private OthelloPiece[][] deepCopyBoard(OthelloPiece[][] original) {
		final Integer rows = original.length;
		final Integer columns = original[0].length;
		OthelloPiece[][] copy = new OthelloPiece[rows][columns];
		for (Integer i = 0; i < rows; i++) {
			for (Integer j = 0; j < columns; j++) {
				copy[i][j] = OthelloPiece.valueOf(original[i][j].name());
			}
		}
		return copy;
	}
	
	private OthelloPiece deepCopyPiece(OthelloPiece piece) {
		return OthelloPiece.valueOf(piece.name());
	}
	
	public void setBoard(OthelloPiece[][] board) {
		this.board = board;
	}
	
	public void setBoard(OthelloMove move, OthelloPiece piece) throws IllegalArgumentException {
		final Integer row = move.getRow();
		final Integer column = move.getColumn();
		board[row][column] = piece;
	}

	public void setRows(Integer rows) {
		this.rows = getDefaultRows();
	}

	public void setColumns(Integer columns) {
		this.columns = getDefaultColumns();
	}

	private void initializeBoard() {
		for (Integer i = 0; i < this.getRows(); i++) {
			for (Integer j = 0; j < this.getColumns(); j++) {
				this.board[i][j] = OthelloPiece.EMPTY;
			}
		}
		this.board[3][3] = this.board[3][4] = OthelloPiece.WHITE;
		this.board[3][4] = this.board[4][3] = OthelloPiece.BLACK;
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		OthelloBoard clone = (OthelloBoard) super.clone();
		clone.board = deepCopyBoard(this.board); 
		return clone;
	}

	public static void main(String[] args) {
		OthelloBoard ob = new OthelloBoard();
		System.out.println(ob.getBoard()[7][7]);
		ob.getBoard()[7][7] = OthelloPiece.WHITE;
		System.out.println(ob.getBoard()[7][7]);
	}
	
}
