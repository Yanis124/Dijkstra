package MainApp;


import MainApp.WeightedGraph.Edge;
import MainApp.WeightedGraph.Graph;
import MainApp.WeightedGraph.Vertex;

import java.util.ArrayList;

//une classe pour initilaiser les arếtes du graphe
public class InitGraph {
    
    // calculer la distance entre deux sommets qui sont alignés diagonalement.
    private static double weightDiagonale(WeightedGraph.Vertex sourceVertex, WeightedGraph.Vertex destinationVertex) {
        double distanceSourceToDestination = Math
                .sqrt(Math.pow(sourceVertex.indivTime, 2) + Math.pow(sourceVertex.indivTime, 2));
        double distanceDestinationToSource = Math
                .sqrt(Math.pow(destinationVertex.indivTime, 2) + Math.pow(destinationVertex.indivTime, 2));

        return (distanceDestinationToSource + distanceSourceToDestination) / 2;
    }

    // ajouter les arếtes alignées diagonalement au graph
    private static void addEdgeDiagonaleGraph(Graph graph, ArrayList<Integer> listDiagonales, int source) {
        for (int i = 0; i < listDiagonales.size(); i++) {
            int dest = listDiagonales.get(i);
            WeightedGraph.Vertex sourceVertex = graph.vertexlist.get(source); // get the source
            WeightedGraph.Vertex destinationVertex = graph.vertexlist.get(dest);

            double weight = weightDiagonale(sourceVertex, destinationVertex);

            graph.addEgde(dest, source, weight); // ajouter l'arête au graphe
        }
    }

    // ajouter les arếtes alignées horizentalement et verticalement au graph
    private static void addEdgeVerticalHorizentalGraph(Graph graph, ArrayList<Integer> listVerticalHorizental,
            int source) {
        for (int i = 0; i < listVerticalHorizental.size(); i++) {
            int dest = listVerticalHorizental.get(i);
            Vertex sourceVertex = graph.vertexlist.get(source);
            Vertex destinationVertex = graph.vertexlist.get(dest);

            double weight = (sourceVertex.indivTime + destinationVertex.indivTime) / 2;

            graph.addEgde(dest, source, weight); // ajouter l'arête au graphe

        }
    }

    //initialiser le graphe
    public static void initGraph(Graph graph,int nlines,int ncols){

		ArrayList<Integer> listDiagonales = new ArrayList<>(); // les sommets qui se trouve a la diagonale de source
		ArrayList<Integer> listVerticalHorizental = new ArrayList<>(); // les sommets qui sont vertical ou
																			// horizental a la source

        for (int line = 0; line < nlines; line++) {
            for (int col = 0; col < ncols; col++) {
                int source = (line * ncols) + col;
                
                // On donne la premiere arete
                listDiagonales.clear();
                listVerticalHorizental.clear();

                if (line > 0 && line < nlines - 1) {

                    if (col > 0 && col < ncols - 1) {

                        // D D D
                        // un carré avec 8 voisins D S D
                        // D D D

                        int dest1 = ((line - 1) * ncols) + col - 1; // le sommet qui se trouve a la diagonal gauche au
                                                                    // dessous de soure
                        int dest2 = ((line - 1) * ncols) + col + 1; // le sommet qui se trouve a la diagonal droite au
                                                                    // dessous de source
                        int dest3 = ((line + 1) * ncols) + col - 1; // le sommet qui se trouve a la diagonal gauche au
                                                                    // dessus de source
                        int dest4 = ((line + 1) * ncols) + col + 1; // le sommet qui se trouve a la diagonal gauche au
                                                                    // dessus de source

                        listDiagonales.add(dest1);
                        listDiagonales.add(dest2);
                        listDiagonales.add(dest3);
                        listDiagonales.add(dest4);
                        

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest5 = ((line - 1) * ncols) + col; // le sommet qui se trouve en haut de source
                        int dest6 = ((line + 1) * ncols) + col;// le sommet qui se trouve en bas de source
                        int dest7 = ((line) * ncols) + col + 1; // le sommet qui se trouve a droite de source
                        int dest8 = ((line) * ncols) + col - 1; // le sommet qui se trouve a gauche de source

                        listVerticalHorizental.add(dest5);
                        listVerticalHorizental.add(dest6);
                        listVerticalHorizental.add(dest7);
                        listVerticalHorizental.add(dest8);

                        

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    } else if (col == 0) {
                        // un carré avec 5 voisins
                        // D D
                        // S D
                        // D D

                        int dest1 = ((line - 1) * ncols) + col + 1;
                        int dest2 = ((line + 1) *ncols)+ col + 1;

                        listDiagonales.add(dest1);
                        listDiagonales.add(dest2);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest3 = (line - 1) * ncols + col;
                        int dest4 = (line) * ncols + col + 1;
                        int dest5 = (line + 1) * ncols + col;

                        listVerticalHorizental.add(dest3);
                        listVerticalHorizental.add(dest4);
                        listVerticalHorizental.add(dest5);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    } else if (col == ncols - 1) {
                        // un carré avec 5 voisins
                        // D D
                        // D S
                        // D D
                        int dest1 = ((line - 1) * ncols) + col - 1;
                        int dest2 = ((line + 1) * ncols) +col-1;

                        listDiagonales.add(dest1);
                        listDiagonales.add(dest2);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest3 = (line - 1) * ncols + col;
                        int dest4 = (line) * ncols + col - 1;
                        int dest5 = (line + 1) * ncols + col;

                        listVerticalHorizental.add(dest3);
                        listVerticalHorizental.add(dest4);
                        listVerticalHorizental.add(dest5);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);
                    }
                    // les cases de la premiére ligne
                }

                else if (line == 0) {

                    if (col > 0 && col < ncols - 1) {
                        // un carré avec 5 voisins
                        // D S D
                        // D D D

                        int dest1 = ((line + 1) * ncols) + (col - 1); // le sommet qui se trouve a la diagonal
                                                                    // gauche au dessous de soure
                        int dest2 = ((line + 1) * ncols) + (col + 1); // le sommet qui se trouve a la diagonal
                                                                    // droite au

                        listDiagonales.add(dest1);
                        listDiagonales.add(dest2);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest3 = (line* ncols) + (col - 1); // le sommet qui se trouve en haut de source
                        int dest4 = ((line + 1) * ncols) + (col);// le sommet qui se trouve en bas de source
                        int dest5 = (line * ncols) + (col + 1); // le sommet qui se trouve a droite de source

                        listVerticalHorizental.add(dest3);
                        listVerticalHorizental.add(dest4);
                        listVerticalHorizental.add(dest5);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);
                    }

                    else if (col == 0) {

                        int dest1 = (line + 1) * ncols + col + 1;

                        listDiagonales.add(dest1);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest2 = (line) * ncols + col + 1;
                        int dest3 = (line + 1) * ncols + col;

                        listVerticalHorizental.add(dest2);
                        listVerticalHorizental.add(dest3);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    }

                    else if (col == ncols - 1) {

                        int dest1 = ((line + 1) * ncols) + col - 1;

                        listDiagonales.add(dest1);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest2 = ((line) * ncols) + col - 1;
                        int dest3 = ((line + 1) * ncols) + col;

                        listVerticalHorizental.add(dest2);
                        listVerticalHorizental.add(dest3);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    }
                }

                else if (line == nlines - 1) {

                    if (col > 0 && col < ncols - 1) {
                        // un carré avec 5 voisins
                        // D D D
                        // D S D

                        int dest1 = ((line - 1) * ncols) + col - 1; // le sommet qui se trouve a la diagonal
                                                                    // gauche au dessous de soure
                        int dest2 = ((line - 1) * ncols) + col + 1; // le sommet qui se trouve a la diagonal
                                                                    // droite au

                        listDiagonales.add(dest1);
                        listDiagonales.add(dest2);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest3 = ((line) * ncols) + col - 1; // le sommet qui se trouve en haut de source
                        int dest4 = ((line - 1) * ncols) + col;// le sommet qui se trouve en bas de source
                        int dest5 = ((line) * ncols) + col + 1; // le sommet qui se trouve a droite de source

                        listVerticalHorizental.add(dest3);
                        listVerticalHorizental.add(dest4);
                        listVerticalHorizental.add(dest5);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);
                    }

                    else if (col == 0) {

                        int dest1 = (line - 1) * ncols + col + 1;

                        listDiagonales.add(dest1);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest2 = (line) * ncols + col + 1;
                        int dest3 = (line - 1) * ncols + col;

                        listVerticalHorizental.add(dest2);
                        listVerticalHorizental.add(dest3);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    }

                    else if (col == ncols - 1) {

                        int dest1 = ((line - 1) * ncols) + col - 1;

                        listDiagonales.add(dest1);

                        addEdgeDiagonaleGraph(graph, listDiagonales, source);

                        int dest2 = ((line * ncols) + col) - 1;
                        int dest3 = ((line - 1) * ncols) + col;

                        listVerticalHorizental.add(dest2);
                        listVerticalHorizental.add(dest3);

                        addEdgeVerticalHorizentalGraph(graph, listVerticalHorizental, source);

                    }
                }
            }
		}
    }

    //calculer l'heuristique d'un sommet
    public static double heuristic(int vertexSource,int vertexDestination,Graph graph){
        //double averageEdgeWeight=averageEdgeWeight(graph); //calculer le poid minimum d'une arête dans le graphe

        double distanceBetweenSourceDestination;

        int lineDestination=vertexSource/100;
        int colDestination=vertexSource%100;
        
        int lineSource=vertexDestination/100;
        int colSource=vertexDestination%100;

        distanceBetweenSourceDestination=Math.sqrt(Math.pow(lineSource-lineDestination,2)+Math.pow(colSource-colDestination,2));

        return distanceBetweenSourceDestination;

    }

    //caluler le poid moyen d'une arếte dans le graphe
    public static double averageEdgeWeight(Graph graph){

        double totalWeightEdge=0;
        int numberEdge=0;
        for(Vertex vertex: graph.vertexlist){
            for(Edge edge : vertex.adjacencylist){
                totalWeightEdge+=edge.weight;
                numberEdge++;
            }
        }
        return totalWeightEdge/numberEdge;
    }

    
}
