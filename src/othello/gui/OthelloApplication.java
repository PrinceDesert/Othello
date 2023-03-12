package othello.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.OthelloGame;
import othello.gui.component.OthelloPieceLabel;

public class OthelloApplication {
	
	private OthelloGame model;
	private JFrame frame;
	private JLabel[][] cells;
	
	public OthelloApplication() {
		createModel();
		createView();
		placeComponents();
		createController();
	}

	/*-----------*/
	/* COMMANDES */
	/*-----------*/

	public void display() {
		frame.setPreferredSize(new Dimension(700, 700));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/*--------*/
	/* OUTILS */
	/*--------*/
	
	private void createModel() {
		this.model = OthelloGame.createGame();
	}
	
	private void createView() {
		this.frame = new JFrame("Othello");
		this.frame.setLayout(new BorderLayout());
		this.cells = new JLabel[8][8];
	}

	private void placeComponents() {
		JPanel p = new JPanel(new GridLayout(8, 8, 2, 2));
		p.setBackground(Color.BLACK);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				OthelloPieceLabel label = new OthelloPieceLabel();
				cells[row][col] = label;
				p.add(label);
			}
		}

		this.frame.add(p, BorderLayout.CENTER);
		
		p = new JPanel(new FlowLayout()); {
			JPanel q = new JPanel();
			q.add(new JLabel("Blanc"));
			q.add(new JLabel("x6"));
			p.add(q);
			
			q = new JPanel();
			q.add(new JLabel("Noir"));
			q.add(new JLabel("x6"));
			p.add(q);
		}
		this.frame.add(p, BorderLayout.SOUTH);
		
		/*
		 * 
		 * 
		 * Map<Rank, Podium<E>> podiums = manager.getPodiums();
		 * 
		 * JPanel p = new JPanel(new BorderLayout()); { JPanel q = new JPanel(new
		 * GridLayout(0, 1)); { for (Order o : Order.values()) { q.add(cmds.get(o)); }
		 * q.add(CrazyCircus.this.chkBox); q.add(restart); } p.add(q,
		 * BorderLayout.NORTH); } frame.add(p, BorderLayout.EAST);
		 * 
		 * p = new JPanel(new BorderLayout()); { JPanel q = new JPanel(new GridLayout(1,
		 * 0)); { for (Rank r : Rank.values()) { q.add(podiums.get(r)); if (r ==
		 * Rank.WRK_RIGHT) { q.add(new JLabel("")); } } } p.add(q, BorderLayout.CENTER);
		 * q = new JPanel(new GridLayout(1, 0)); { q.add(createLabel("Départ"));
		 * q.add(new JLabel("")); q.add(createLabel("Objectif")); } p.add(q,
		 * BorderLayout.SOUTH); } frame.add(p, BorderLayout.WEST); frame.add(txtArea,
		 * BorderLayout.SOUTH);
		 */
	}

	private void createController() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * ActionListener al = new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { JButton b = ((JButton)
		 * e.getSource()); Order o = Order.valueOf(b.getName()); if (o != null) { try {
		 * manager.executeOrder(o); } catch (PropertyVetoException pve) {
		 * pve.printStackTrace(); } } } }; for (Order o : Order.values()) {
		 * cmds.get(o).addActionListener(al); }
		 * 
		 * restart.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { manager.reinit(); }
		 * });
		 * 
		 * // Écoute de changement de valeur pour la propriété finished
		 * manager.addPropertyChangeListener(PodiumManager.PROP_FINISHED, new
		 * PropertyChangeListener() {
		 * 
		 * @Override public void propertyChange(PropertyChangeEvent evt) { //
		 * PodiumManager<E> manager = (PodiumManager<E>) evt.getSource(); // début de
		 * partie if (manager.getTimeDelta() == 0) { txtArea.setText(""); // fin de
		 * partie } else if (manager.isFinished()) { String s = "gagné en "; s +=
		 * manager.getShotsNb() + " coup" + (manager.getShotsNb() > 0 ? "s" : ""); s +=
		 * " et " + manager.getTimeDelta() / 1000 + " s."; txtArea.append(s); } } });
		 * 
		 * // Écoute de changement de valeur pour la propriété lastOrder
		 * manager.addPropertyChangeListener(PodiumManager.PROP_LAST_ORDER, new
		 * PropertyChangeListener() {
		 * 
		 * @Override public void propertyChange(PropertyChangeEvent evt) { // affiche
		 * chaque ordre donné (activation d'un bouton) Order lastOrder = (Order)
		 * evt.getNewValue(); txtArea.append(lastOrder.name() + " "); } });
		 * 
		 * manager.addVetoableLastOrderChangeListener(new VetoableChangeListener() {
		 * 
		 * @Override public void vetoableChange(PropertyChangeEvent evt) throws
		 * PropertyVetoException { 
		 * 
		 * Accepté que les valeurs différentes de SO sauf si la checkbox est sélectionné
		 * alors SO est accepté
		 * 
		 * Order order = (Order) evt.getNewValue(); if (!CrazyCircus.this.accept(order))
		 * { throw new PropertyVetoException("Veto : " + evt.toString(), evt); } } });
		 */
	}
	
}
