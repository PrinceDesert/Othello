package othello.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class OthelloMainMenu {

	private JFrame frame;

	JLabel labelName;
	JTextField textFieldName;
	JLabel labelDifficulty;
	JComboBox<String> comboBoxDifficulties;
	JButton buttonPlay;
	JButton buttonQuit;

	public OthelloMainMenu() {
		createModel();
		createView();
		placeComponents();
		createController();
	}

	public void display() {
		frame.setPreferredSize(new Dimension(700, 700));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void createModel() {

	}

	private void createView() {
		this.frame = new JFrame("Othello - Accueil");
		this.frame.setLayout(new BorderLayout());
		this.labelName = new JLabel("Nom du joueur :");
		this.textFieldName = new JTextField();
		this.labelDifficulty = new JLabel("Difficulté :");
		String[] difficulties = { "Facile", "Moyenne", "Difficile" };
		this.comboBoxDifficulties = new JComboBox<>(difficulties);
		this.buttonPlay = new JButton("Jouer");
		this.buttonQuit = new JButton("Quitter");
	}

	private void placeComponents() {
		JPanel p = new JPanel(new GridLayout(8, 8, 2, 2)) {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;
			// Constructor
			{
				this.backgroundImage = new ImageIcon("src/othello/gui/music/wallpaper.png").getImage();
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}

			// C'est ici qu'il faut tout ajouté
		};
		this.frame.add(p, BorderLayout.CENTER);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); // Utiliser BoxLayout pour centrer le panel

		int padding = 10;
		int width = 400;

		p.add(Box.createVerticalGlue()); // Ajouter un espace vertical au-dessus du panel

		JPanel innerPanel = new JPanel();
		innerPanel.setPreferredSize(new Dimension(width - (padding * 2), 400 - (padding * 2))); // Définir la taille
																								// intérieure du panel
		innerPanel.setBackground(Color.WHITE); // Définir la couleur de fond du panel intérieur
		innerPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding)); // Ajouter un padding
		// Ajouter un espace vertical sous le panel

		/*
		 * for (int row = 0; row < 8; row++) { for (int col = 0; col < 8; col++) {
		 * JLabel label = new JLabel(); label.setOpaque(true); label.setBackground(new
		 * Color(0, 130, 92)); label.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { JLabel clickedLabel =
		 * (JLabel) e.getSource(); clickedLabel.setBackground(Color.RED); //
		 * clickedLabel.setBackground(currentPlayer); // currentPlayer = currentPlayer
		 * == Color.BLACK ? Color.WHITE : Color.BLACK;
		 * 
		 * } }); cells[row][col] = label; p.add(label); } }
		 */

		JLabel label = new JLabel("Othello");
		label.setFont(new Font("SansSerif", Font.BOLD, 48));
		label.setForeground(Color.WHITE);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		p.add(label);

		this.frame.add(p, BorderLayout.CENTER);

		// .add(innerPanel);

		innerPanel.add(Box.createVerticalGlue());

		innerPanel.add(labelName);
		innerPanel.add(textFieldName);
		innerPanel.add(labelDifficulty);
		innerPanel.add(comboBoxDifficulties);
		innerPanel.add(buttonPlay);
		innerPanel.add(buttonQuit);

		p.add(innerPanel);

		p = new JPanel(new FlowLayout());
		{
			JPanel q = new JPanel();
			q.add(new JButton("Jouer"));
			p.add(q);
		}
		p.setBackground(new Color(42, 110, 43));
		this.frame.add(p, BorderLayout.SOUTH);

	}

	private void createController() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new OthelloApplication().display();
				// System.exit(0);
			}
		});
		buttonQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new OthelloMainMenu().display();
			}
		});
	}
}
