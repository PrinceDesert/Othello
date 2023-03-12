package othello.ai;

import java.util.Arrays;

import othello.ai.OthelloNode;
import othello.ai.OthelloPlayerType;
import othello.ai.OthelloNodeType;

public class OthelloNode {
	OthelloPlayerType playerType; // Etiquette
	OthelloNodeType nodeType; // Statut
	Double[] number; // Nombre(1, n)
	Double heuristic; // heuristic
	Integer bf; // branchingFactor
	OthelloNode[] children; // Fils

	public OthelloNode(OthelloPlayerType playerType, OthelloNodeType nodeType, int nbMaxOthelloNode, double heuristic,
			int bf) {
		this.playerType = playerType;
		this.nodeType = nodeType;
		this.heuristic = heuristic;
		this.bf = bf;
		this.children = new OthelloNode[bf];
	}

	public void addChild(OthelloNode parent, OthelloNode child, int index) {
		parent.children[index] = child;
	}

	public void removeChild(OthelloNode parent, int index) {
		OthelloNode[] children = parent.children;
		if (children[index] == null) {
			return;
		}
		int lastIndex = parent.bf - 1;
		if (index == lastIndex) {
			children[index] = null;
			return;
		}
		for (int i = index; i < lastIndex; i++) {
			children[i] = children[i + 1];
		}
		children[lastIndex] = null;
	}

	public OthelloPlayerType getPlayer() {
		return playerType;
	}

	public OthelloNodeType getNodeType() {
		return nodeType;
	}

	public Double[] getNumber() {
		return this.deepCopyNumber(number);
	}

	public Double getHeuristic() {
		return heuristic;
	}

	public OthelloNode[] getChildren() {
		return this.deepCopyChildren(children);
	}

	public void setPlayerType(OthelloPlayerType playerType) {
		this.playerType = playerType;
	}

	public void setNodeType(OthelloNodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setNumber(Double[] number) {
		this.number = number;
	}

	public void setHeuristic(Double heuristic) {
		this.heuristic = heuristic;
	}

	public void setChildren(OthelloNode[] children) {
		this.children = children;
	}

	private Double[] deepCopyNumber(Double[] number) {
		final int n = number.length;
		Double[] copy = new Double[n];
		for (int i = 0; i < n; i++) {
			copy[i] = number[i];
		}
		return copy;
	}

	private OthelloNode[] deepCopyChildren(OthelloNode[] children) {
		final int n = children.length;
		OthelloNode[] copy = new OthelloNode[n];
		for (int i = 0; i < n; i++) {
			copy[i] = new OthelloNode(children[i].playerType, 
					children[i].nodeType, children[i].number.length,
					children[i].heuristic, children[i].bf);
			copy[i].number = Arrays.copyOf(children[i].number, children[i].number.length);
			copy[i].children = deepCopyChildren(children[i].children);
		}
		return copy;
	}

}
