package MainApp;

import MainApp.WeightedGraph.Graph;
import MainApp.WeightedGraph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

//Classe pour gerer l'affichage
class Board extends JComponent {
	private static final long serialVersionUID = 1L;
	Graph graph;
	int pixelSize;
	int ncols;
	int nlines;
	HashMap<Integer, String> colors;
	int start;
	int end;
	double max_distance;
	int current;
	LinkedList<Integer> path;
	private static Graphics2D g2d;
	ArrayList<Integer> exploredVertex;

	public Board(Graph graph, int pixelSize, int ncols, int nlines, HashMap<Integer, String> colors,
			int start,
			int end) {
		super();
		this.graph = graph;
		this.pixelSize = pixelSize;
		this.ncols = ncols;
		this.nlines = nlines;
		this.colors = colors;
		this.start = start;
		this.end = end;
		this.max_distance = ncols * nlines;
		this.current = -1;
		this.path = null;
		this.g2d = null;
		this.exploredVertex = null;
	}

	// initiliser la grille
	public void initBoard(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;

		int width = getWidth();
		int height = getHeight();

		int cellWidth = (int) Math.floor((double) width / ncols);
		int cellHeight = (int) Math.floor((double) height / nlines);

		

		// dessiner les lignes de la grille
		for (int i = 0; i < nlines; i++) {
			int y = i * cellHeight;
			g2d.drawLine(0, y, width, y);
		}

		// dessiner les colonnes de la grille
		for (int i = 0; i < ncols; i++) {
			int x = i * cellWidth;
			g2d.drawLine(x, 0, x, height);
		}
	}

	// dessiner la grillle
	protected void paintComponent(Graphics g) {

		initBoard(g); // initialiser la grille
		// Dessiner le chemin dans le graphe

		int width = getWidth();
		int height = getHeight();
		
		int cellWidth = (int) Math.floor((double) width / ncols);
		int cellHeight = (int) Math.floor((double) height / nlines);

		setPreferredSize(new Dimension((ncols * cellWidth) , (nlines * cellHeight)-2*cellHeight ));

		if (this.path != null) {
			for (Integer num : this.path) { // recuperer le numéro du sommet

				int xCell = (num % ncols) * cellWidth;
				int yCell = (num / ncols) * cellHeight;

				g2d.setColor(Color.BLACK);

				g2d.fillRect(xCell, yCell, cellWidth, cellHeight);
			}
		}

		if (this.exploredVertex != null && this.path == null) {
			for (Integer num : this.exploredVertex) { // recuperer le numéro du sommet

				Vertex vertex = graph.vertexlist.get(num); // recuperer le sommet
				double individualTime = vertex.indivTime; // recuperer le temps individuel du sommet
				String color = this.colors.get((int) individualTime); // recuperer la couleur du sommet

				int xCell = (num % ncols) * cellWidth;
				int yCell = (num / ncols) * cellHeight;

				switch (color) {
					case "green":
						g2d.setColor(Color.GREEN);

						break;
					case "gray":
						g2d.setColor(Color.GRAY);
						break;
					case "blue":
						g2d.setColor(Color.BLUE);
						break;
					default:
						g2d.setColor(Color.YELLOW);
						break;
				}

				g2d.fillRect(xCell, yCell, cellWidth, cellHeight);
			}
		}

		if (this.path != null && this.exploredVertex != null) {
			List<Integer> result = this.exploredVertex.stream() // recuperer la list des sommets qui appartiennet au
																// sommet explorer mais pas au chemin
					.filter(num -> !this.path.contains(num))
					.collect(Collectors.toList());

			for (Integer num : result) {

				Vertex vertex = graph.vertexlist.get(num); // recuperer le sommet
				double individualTime = vertex.indivTime; // recuperer le temps individuel du sommet
				String color = this.colors.get((int) individualTime); // recuperer la couleur du sommet

				int xCell = (num % ncols) * cellWidth;
				int yCell = (num / ncols) * cellHeight;

				switch (color) {
					case "green":
						g2d.setColor(Color.GREEN);

						break;
					case "gray":
						g2d.setColor(Color.GRAY);
						break;
					case "blue":
						g2d.setColor(Color.BLUE);
						break;
					default:
						g2d.setColor(Color.YELLOW);
						break;
				}

				g2d.fillRect(xCell, yCell, cellWidth, cellHeight);
			}

		}

	}

	// Mise e jour du graphe (e appeler avant de mettre e jour l'affichage)
	public void update(Graph graph, ArrayList<Integer> exploredVertex) {
		this.graph = graph;
		this.exploredVertex = exploredVertex;
		repaint();
	}

	// Indiquer le chemin (pour affichage)
	public void addPath(Graph graph, LinkedList<Integer> path) {
		this.graph = graph;
		this.path = path;
		this.current = -1;
		repaint();
	}

}
