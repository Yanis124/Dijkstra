package MainApp;
// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avancee"

// de l'Universite de Paris, 11/2020

import MainApp.WeightedGraph.Edge;
import MainApp.WeightedGraph.Graph;
import MainApp.WeightedGraph.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFrame;



// Classe principale. C'est ici que vous devez faire les modifications
public class App {

	private static String FILE_EXECUTION_TIME = "AlgorithmExecutionTime.txt";

	// Initialise l'affichage
	private static void drawBoard(Board board, int nlines, int ncols, int pixelSize) {
	JFrame frame = new JFrame("Grid Example");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.add(board);
	frame.setSize((ncols * pixelSize)+pixelSize , (nlines * pixelSize)-2*pixelSize );
	

	frame.setVisible(true);
}

	// Methode A*
	// graph: le graphe representant la carte
	// start: un entier representant la case de depart
	// (entier unique correspondant e la case obtenue dans le sens de la lecture)
	// end: un entier representant la case d'arrivee
	// (entier unique correspondant e la case obtenue dans le sens de la lecture)
	// ncols: le nombre de colonnes dans la carte
	// numberV: le nombre de cases dans la carte
	// board: l'affichage
	// retourne une liste d'entiers correspondant au chemin.

	public static LinkedList<Integer> AStar(Graph graph, int start, int end, Board board) {



		graph.vertexlist.get(start).timeFromSource = 0;
		int number_tries = 0;

		// mettre tous les noeuds du graphe dans la liste des noeuds e visiter:
		HashSet<Integer> to_visit = new HashSet<Integer>();
		for (int i = 0; i < graph.num_v; i++) {
			to_visit.add(graph.vertexlist.get(i).num);
		}

		

		// calculer l'heursitique pour tous les sommets du graphe
		for (int i = 0; i < graph.num_v; i++) {
			Vertex vertexSource = graph.vertexlist.get(graph.vertexlist.get(i).num);
			vertexSource.heuristic = InitGraph.heuristic(vertexSource.num, end, graph);
		}

		

		to_visit.remove(start); // enlever le sommet de depart des sommet a visité

		double timeFromSource = 0; // initiliser le temps depuis le sommet start a 0

		ArrayList<Vertex> listVisitedVertex = new ArrayList<>(); // la liste des sommts visités
		Vertex min_v = graph.vertexlist.get(start); // le sommet avec la distance minimum

		ArrayList<Vertex> listVisitedVertexTree = new ArrayList<>(); // la liste des sommet visités qui appartiennet a
																		// l'arbre couvrant
		listVisitedVertexTree.add(min_v); // ajouter le sommet de depart au sommet decouvert

		ArrayList<Integer> exploredVertex = new ArrayList<>(); // la liste des numeros des sommets de l'arbre
		exploredVertex.add(min_v.num);

		while (to_visit.contains(end)) {
			for (Edge edge : min_v.adjacencylist) { // parcourir les voisin du sommet recement rajouter

				Vertex neighboVertex = graph.vertexlist.get(edge.destination); // recuperer le sommet

				if (!listVisitedVertexTree.contains(neighboVertex) && !listVisitedVertex.contains(neighboVertex)) { // ajouter
																													// le
																													// sommet
																													// a
																													// la
																													// liste
																													// des
																													// sommet
																													// visités
					listVisitedVertex.add(neighboVertex);

				}

				if (timeFromSource + edge.weight < neighboVertex.timeFromSource) { // mettre a jour la distance vers le
																					// sommet
					neighboVertex.timeFromSource = timeFromSource + edge.weight;
					neighboVertex.prev = min_v;
				}
			}
			// selectionner le sommet avec la distance (timeFromSource + heuristic) est
			// minimale.
			min_v = listVisitedVertex.get(0);

			for (Vertex vertex : listVisitedVertex) {

				if (vertex.timeFromSource + vertex.heuristic < min_v.timeFromSource + min_v.heuristic) {
					min_v = vertex;
				}
			}

			timeFromSource = min_v.timeFromSource; // mettre a jour le temps a la source

			listVisitedVertexTree.add(min_v); // ajouter le sommet avec la distance minimum à l'arbre
			exploredVertex.add(min_v.num); // ajouter le numéro sommet avec la distance minimum à l'arbre
			listVisitedVertex.remove(min_v); // enlever le sommet avec la distance minimum de la liste des sommets
												// visités

			to_visit.remove(min_v.num); // enlever le sommet avec la distance minimum des sommet à visiter
			number_tries += 1; // mettre à jour le nombre d'essais

			// On met e jour l'affichage
			if(board!=null){
				try {
					board.update(graph, exploredVertex);
					Thread.sleep(10);

				} catch (InterruptedException e) {
					System.out.println("stop");
				}
			}
		}

		System.out.println("Done! Using A*:");
		System.out.println("source vertex : " + start);
		System.out.println(" destination vertex : " + end);
		System.out.println(" Number of nodes explored: " + number_tries);
		System.out.println(" Total time of the path:" + graph.vertexlist.get(end).timeFromSource);

		LinkedList<Integer> path = new LinkedList<Integer>();
		path.addFirst(end);

		Vertex currentVertex = graph.vertexlist.get(end); // recuperer le dernier

		// remplir la liste path avec le chemin
		while (currentVertex.prev != null) {
			int numCurrentVertex = currentVertex.prev.num;
			path.addFirst(numCurrentVertex);
			currentVertex = graph.vertexlist.get(numCurrentVertex);
		}

		if(board!=null){
			board.addPath(graph, path); // Afficher le chemin dans la grille
		}

		FileManager.writeToFile(String.valueOf(number_tries), FILE_EXECUTION_TIME);

		return path;
	}

	// Methode Dijkstra
	// graph: le graphe representant la carte
	// start: un entier representant la case de depart
	// (entier unique correspondant e la case obtenue dans le sens de la lecture)
	// end: un entier representant la case d'arrivee
	// (entier unique correspondant e la case obtenue dans le sens de la lecture)
	// numberV: le nombre de cases dans la carte
	// board: l'affichage
	// retourne une liste d'entiers correspondant au chemin.
	public static LinkedList<Integer> Dijkstra(Graph graph, int start, int end, Board board) {

		graph.vertexlist.get(start).timeFromSource = 0; // recupérer le sommet de départ du graphe
		int number_tries = 0; // initialiser le nombre d'essaie à 0

		// mettre tous les noeuds du graphe dans la liste des noeuds a visiter:
		HashSet<Integer> to_visit = new HashSet<Integer>();
		for (int i = 0; i < graph.num_v; i++) {
			to_visit.add(graph.vertexlist.get(i).num);
		}


		to_visit.remove(start); // enlever le sommet de depart des sommet a visité

		double timeFromSource = 0; // initiliser le temps depuis le sommet start a 0

		ArrayList<Vertex> listVisitedVertex = new ArrayList<>(); // la liste des sommts visités
		Vertex min_v = graph.vertexlist.get(start); // le sommet avec la distance minimum

		ArrayList<Vertex> listVisitedVertexTree = new ArrayList<>(); // la liste des sommet visités qui appartiennet a
																		// l'arbre couvrant
		listVisitedVertexTree.add(min_v); // ajouter le sommet de depart au sommet decouvert

		ArrayList<Integer> exploredVertex = new ArrayList<>(); // la liste des numeros des sommets de l'arbre
		exploredVertex.add(min_v.num);

		// tant que le sommet d'arrivé n'a pas été trouvé
		while (to_visit.contains(end)) {

			for (Edge edge : min_v.adjacencylist) { // parcourir les voisin du sommet recement rajouter

				Vertex neighboVertex = graph.vertexlist.get(edge.destination); // recuperer le sommet

				if (!listVisitedVertexTree.contains(neighboVertex) && !listVisitedVertex.contains(neighboVertex)) { // ajouter
																													// le
																													// sommet
																													// a
																													// la
																													// liste
																													// des
																													// sommet
																													// visités
					listVisitedVertex.add(neighboVertex);

				}

				if (timeFromSource + edge.weight < neighboVertex.timeFromSource) { // mettre a jour la distance vers le
																					// sommet
					neighboVertex.timeFromSource = timeFromSource + edge.weight;
					neighboVertex.prev = min_v;
				}
			}

			// selectionner le sommet avec la distance minimum
			min_v = listVisitedVertex.get(0);

			for (Vertex vertex : listVisitedVertex) {

				if (vertex.timeFromSource < min_v.timeFromSource) {
					min_v = vertex;
				}
			}

			timeFromSource = min_v.timeFromSource; // mettre a jour le temps a la source

			listVisitedVertexTree.add(min_v); // ajouter le sommet avec la distance minimum à l'arbre
			exploredVertex.add(min_v.num); // ajouter le numéro sommet avec la distance minimum à l'arbre
			listVisitedVertex.remove(min_v); // enlever le sommet avec la distance minimum de la liste des sommets
												// visités

			to_visit.remove(min_v.num); // enlever le sommet avec la distance minimum des sommet à visiter
			number_tries += 1; // mettre à jour le nombre d'essais

			// On met e jour l'affichage
			if (board != null) {
				try {
					board.update(graph, exploredVertex);
					Thread.sleep(10);

				} catch (InterruptedException e) {
					System.out.println("stop");
				}
			}
		}

		System.out.println("Done! Using Dijkstra : ");
		System.out.println("source vertex : " + start);
		System.out.println(" destination vertex : " + end);
		System.out.println(" Number of nodes explored: " + number_tries);
		System.out.println(" Total time of the path:	" + graph.vertexlist.get(end).timeFromSource);

		LinkedList<Integer> path = new LinkedList<Integer>();
		path.addFirst(end);

		Vertex currentVertex = graph.vertexlist.get(end); // recuperer le dernier

		// remplir la liste path avec le chemin
		while (currentVertex.prev != null) {
			int numCurrentVertex = currentVertex.prev.num;
			path.addFirst(numCurrentVertex);
			currentVertex = graph.vertexlist.get(numCurrentVertex);
		}

		if (board != null) {
			board.addPath(graph, path); // Afficher le chemin dans la grille
		}

		return path;
	}

	// Methode principale
	public static void main(String[] args) {

		ArrayList<Object> listInformationGraph = FileManager.readFromFileGraph(); // la liste des information du graphe
																					// (nline,ncols,graph,startV,endV)
		int nlines = (Integer) listInformationGraph.get(0); // le nombre de ligne
		int ncols = (Integer) listInformationGraph.get(1); // le nombre de colonne
		Graph graph = (Graph) listInformationGraph.get(2); // le graphe
		@SuppressWarnings("unchecked")
		HashMap<Integer, String> groundColor = (HashMap<Integer, String>) listInformationGraph.get(5); // la couleur de
																										// chaque sommet

		InitGraph.initGraph(graph, nlines, ncols); // initialiser le graphe

		Scanner sc=new Scanner(System.in);
		int startV=0;
		int endV=0;
		int choice=0;


		do{
			System.out.println("Veuillez saisir le sommet de départ : la grille contient "+graph.num_v+" sommets");
			try{
				startV=sc.nextInt(); //saisir le sommet de départ
			}
			catch(InputMismatchException e){
				System.out.println("veuillez saisir un nombre");
			}
		}while(startV<0 || startV>graph.num_v);

		do{
			System.out.println("Veuillez saisir le sommet d'arrivé : la grille contient "+graph.num_v+" sommets");
			try{
				endV =sc.nextInt();  //saisir le sommet d'arrivé
			}
			catch(InputMismatchException e){
				System.out.println("veuillez saisir un nombre");
			}
		}while(endV<0 || endV>graph.num_v);

		do{
			System.out.println("Veuillez choisir l'algorithme à utiliser : 1 pour Dijkstra, 2 pour A*");
			try{
				choice =sc.nextInt();  //saisir le choix de l'algorithme
			}
			catch(InputMismatchException e){
				System.out.println("veuillez saisir un nombre");
			}
		}while (choice!=1 && choice!=2);

		int pixelSize = 10;
		Board board = new Board(graph, pixelSize, ncols, nlines, groundColor, startV, endV);
		drawBoard(board, nlines, ncols, pixelSize);
		board.repaint();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("stop");
		}

		if(choice==1){
			App.Dijkstra(graph, startV, endV, board);
		}

		else{
			App.AStar(graph, startV, endV, board);
		}
	}

}
