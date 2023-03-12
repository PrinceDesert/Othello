package othello.ai;

import othello.ai.OthelloPlayerType;

// https://www.ffothello.org/informatique/algorithmes/

public class OthelloMinimax {
	
	private OthelloMinimax() {}
	
	private static Double minimax(OthelloNode s) {
		if (s.getNodeType() == OthelloNodeType.LEAF) {
			return s.getHeuristic();
		}
		double value;
		if (s.getPlayer() == OthelloPlayerType.MAX) {
			value = Double.NEGATIVE_INFINITY;
			for (OthelloNode children : s.getChildren()) {
				value = Math.max(value, minimax(children));
			}
		} else {
			value = Double.POSITIVE_INFINITY;
			for (OthelloNode children : s.getChildren()) {
				value = Math.min(value, minimax(children));
			}
		}
		return value;
	}
	
}
