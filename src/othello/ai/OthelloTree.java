package othello.ai;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import othello.ai.OthelloNode;
import othello.ai.OthelloNodeType;

public class OthelloTree {
	
	private OthelloNode root;
	private int maxNode; // n
	private int bf; // branchingFactor

	public OthelloTree(OthelloNode root, int maxNode, int bf) {
		this.root = root;
		this.maxNode = maxNode;
		this.bf = bf;
	}

	public OthelloNode depthFirstTraversal(OthelloNode node) {
		if (node == null) {
			return null;
		}
		Stack<OthelloNode> stack = new Stack<>();
		Set<OthelloNode> visited = new HashSet<>();
		stack.push(node);
		while (!stack.empty()) {
			OthelloNode current = stack.pop();
			visited.add(current);
			// Traitement du nœud courant
			if (current.nodeType == OthelloNodeType.LEAF) {
				return current; // Retourne le noeud terminal atteint
			}
			for (OthelloNode child : current.children) {
				if (child != null && !visited.contains(child)) {
					stack.push(child);
				}
			}
		}
		return null; // Aucun nœud terminal n'a été trouvé
	}

}