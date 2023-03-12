package othello;

import othello.model.OthelloBoard;
import othello.model.OthelloMove;
import othello.model.OthelloPiece;
import othello.model.OthelloPlayer;

// To do : 

// reprendre le dessin de ça : https://cardgames.io/reversi/
// Implémenter le changelistener ou le prop a voir

/**
 * public List<OthelloMove> getCoupsPossibles(OthelloPlayer player) {
    List<OthelloMove> coups = new ArrayList<>();
    for (int i = 0; i < plateau.length; i++) {
        for (int j = 0; j < plateau[0].length; j++) {
            OthelloMove coup = new OthelloMove(i, j, player.getPiece());
            if (estCoupLegal(coup)) {
                coups.add(coup);
            }
        }
    }
    return coups;
}
*/

/**
 * Resources : http://vision.gel.ulaval.ca/~klein/lothello/othello.htm
 *
 */
public class OthelloGame {

	private static final Integer MIN_NB_PIECE_PER_PLAYER = 2;
	private static final Integer MAX_NB_PIECE_PER_PLAYER = 32;

	private OthelloBoard board;
	private OthelloPlayer firstPlayer;
	private OthelloPlayer secondPlayer;

	private OthelloPiece currentPlayer;

	// OthelloPlayer winner;
	// OthelloPlayer looser;
	// DateTime timeSpend;

	private OthelloGame() {
		this.board = OthelloBoard.createBoard(OthelloBoard.getDefaultRows(), OthelloBoard.getDefaultColumns());
		this.firstPlayer = OthelloPlayer.createPlayer("j1", OthelloPiece.BLACK, OthelloGame.getMinNbPiecePerPlayer());
		this.secondPlayer = OthelloPlayer.createPlayer("j2", OthelloPiece.WHITE, OthelloGame.getMinNbPiecePerPlayer());
		this.currentPlayer = OthelloPiece.BLACK;
	}

	public static OthelloGame createGame() {
		return new OthelloGame();
	}

	public static Integer getMinNbPiecePerPlayer() {
		return OthelloGame.MIN_NB_PIECE_PER_PLAYER;
	}

	public static Integer getMaxNbPiecePerPlayer() {
		return OthelloGame.MAX_NB_PIECE_PER_PLAYER;
	}

	public OthelloBoard getBoard() {
		return board;
	}

	public OthelloPlayer getFirstPlayer() {
		return firstPlayer;
	}

	public OthelloPlayer getSecondPlayer() {
		return secondPlayer;
	}

	public void setBoard(OthelloBoard board) {
		this.board = board;
	}

	public void setFirstPlayer(OthelloPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public void setSecondPlayer(OthelloPlayer secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	/**
	 * Copie en profondeur/Clonage en profondeur pas nécessaire car énumération type
	 * immuable
	 * 
	 * @return la couleur du joueur courant
	 */
	public OthelloPiece getCurrentPlayer() {
		return this.currentPlayer;
	}

	public OthelloPiece getOpponentPlayer() {
		return getCurrentPlayer() == OthelloPiece.BLACK ? OthelloPiece.WHITE : OthelloPiece.BLACK;
	}

	public void setCurrentPlayer(OthelloPiece currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Vérifie si un coup est valide en fonction de la couleur actuelle du joueur.
	 * 
	 * @param rowIndex L'indice de ligne de la case où le joueur souhaite placer sa
	 *                 pièce
	 * @param colIndex L'indice de colonne de la case où le joueur souhaite placer
	 *                 sa pièce
	 * @return true si le coup est valide, false sinon
	 */
	public boolean isValidMove(OthelloMove move) {
		OthelloPiece color = currentPlayer;
		final Integer rowIndex = move.getRow();
		final Integer colIndex = move.getColumn();
		if (rowIndex < 0 || rowIndex >= board.getRows() || colIndex < 0 || colIndex >= board.getColumns()) {
			return false;
		}
		if (board.getBoard()[rowIndex][colIndex].getValue() != OthelloPiece.EMPTY.getValue()) {
			return false;
		}
		final OthelloPiece[][] pieces = board.getBoard();
		final Integer[][] directions = { { -1, -1 }, // diagonal en haut à gauche
				{ -1, 0 }, // vertical vers le haut
				{ -1, 1 }, // diagonal en haut à droite
				{ 0, -1 }, // horizontal vers la gauche
				{ 0, 1 }, // horizontal vers la droite
				{ 1, -1 }, // diagonal en bas à gauche
				{ 1, 0 }, // vertical vers le bas
				{ 1, 1 } // diagonal en bas à droite
		};
		Boolean isValid = false;
		for (Integer[] direction : directions) {
			int i = rowIndex + direction[0];
			int j = colIndex + direction[1];
			boolean foundOpponent = false;
			while (i >= 0 && i < board.getRows() && j >= 0 && j < board.getColumns()
					&& pieces[i][j].getValue() != OthelloPiece.EMPTY.getValue()) {
				if (pieces[i][j].getValue() == color.getValue()) {
					isValid |= foundOpponent;
					break;
				}
				foundOpponent = true;
				i += direction[0];
				j += direction[1];
			}
		}
		return isValid;
	}

	public OthelloBoard validateMove(OthelloMove move) {
		if (isValidMove(move)) {
			board.setBoard(move, currentPlayer);
		}
		return board;
	}

}
