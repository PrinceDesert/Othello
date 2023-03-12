package othello.gui.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import othello.model.OthelloBoard;

public class OthelloPieceLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	private static Integer cellLabelNumber = Integer.valueOf(0);
	private Integer cellLabelId = Integer.valueOf(0);

	private static final Color FIRST_GREEN_BACKGROUND_COLOR = new Color(0, 130, 92);
	private static final Color SECOND_GREEN_BACKGROUND_COLOR = new Color(0, 20, 92);

	private Boolean isOccupied;
	private Boolean isPossibleMove;
	
	
	private Color ovalBorderColor;
	
	private void setOvalBorderColor(Color color) {
		this.ovalBorderColor = color;
		this.repaint();
	}
	
	public Color getOvalBorderColor() {
		return this.ovalBorderColor;
	}

	public OthelloPieceLabel() {
		super();
		this.cellLabelId = OthelloPieceLabel.cellLabelNumber++;
		this.isOccupied = false;
		this.isPossibleMove = true;
		this.ovalBorderColor = Color.BLACK;
		createModel();
		createView();
		placeComponents();
		createController();
	}

	private static Integer getCellLabelNumber() {
		return OthelloPieceLabel.cellLabelNumber;
	}

	private Integer getCellLabelId() {
		return this.cellLabelId;
	}

	private static Color getFirstGreenBackgroundColor() {
		return OthelloPieceLabel.FIRST_GREEN_BACKGROUND_COLOR;
	}

	private static Color getSecondGreenBackgroundColor() {
		return OthelloPieceLabel.SECOND_GREEN_BACKGROUND_COLOR;
	}

	private Color getDefaultBackgroundColor() {
		final Integer maxRows = OthelloBoard.getDefaultRows();
		final Integer maxColumns = OthelloBoard.getDefaultRows();
		final Integer row = this.getCellLabelId() / maxRows;
		final Integer column = this.getCellLabelId() % maxColumns;
		if (row % 2 == 0) {
			if (column % 2 == 0) {
				return getFirstGreenBackgroundColor();
			} else {
				return getSecondGreenBackgroundColor();
			}
		} else {
			if (column % 2 == 0) {
				return getSecondGreenBackgroundColor();
			} else {
				return getFirstGreenBackgroundColor();
			}
		}
	}

	private void createModel() {
	}

	private void createView() {
		this.setOpaque(true);
		this.setBackground(this.getDefaultBackgroundColor());
	}

	private void placeComponents() {
	}

	private void createController() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				OthelloPieceLabel label = (OthelloPieceLabel) e.getSource();
				setOvalBorderColor(Color.RED);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				OthelloPieceLabel label = (OthelloPieceLabel) e.getSource();
				
				
				// clickedLabel.setBackground(currentPlayer);
				// currentPlayer = currentPlayer == Color.BLACK ? Color.WHITE : Color.BLACK;

			}

			@Override
			public void mouseExited(MouseEvent e) {
				OthelloPieceLabel label = (OthelloPieceLabel) e.getSource();
				label.setBackground(OthelloPieceLabel.this.getDefaultBackgroundColor());
				
				setOvalBorderColor(Color.BLACK);
			}
		});
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		final int width = getWidth();
		final int height = getHeight();
		// Fond couleur verte
		g2.setColor(getBackground());
		g2.fillRect(0, 0, width, height);
		if (isOccupied || isPossibleMove) {
			// Dessin du cercle de bordure
			g2.setColor(this.getOvalBorderColor());
			g.fillOval(0, 0, width - 1, height - 1);
			// Dimension du cercle central
			final int diameter = Math.min(width, height) - 10;
			final int x = (width - diameter) / 2;
			final int y = (height - diameter) / 2;
			if (isOccupied) {
				final Float xSource = 0F, ySource = 0F, xDest = (float) width, yDest = (float) height;
				final Color colorFrom = Color.WHITE;
				final Color colorTo = Color.GRAY;
				final GradientPaint gradient = new GradientPaint(0, 0, colorFrom, width, height, colorTo);
				g2.setPaint(gradient);
				g.fillOval(x, y, diameter, diameter);
			} else {
				g2.setColor(getBackground());
				g.fillOval(x, y, diameter, diameter);
			}
		}
	}

}
