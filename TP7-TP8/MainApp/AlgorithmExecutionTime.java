package MainApp;

import java.io.File;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

import MainApp.WeightedGraph.Graph;

public class AlgorithmExecutionTime {

    private static final String FILE_EXECUTION_TIME = "AlgorithmExecutionTime.txt";

    // génerer une liste qui contient n couple de sommets aléatoires(startV,endV)
    public static ArrayList<ArrayList<Integer>> selectRandomVertexFromGraph(int numberCoupleVertex, int numberVertex) {
        ArrayList<ArrayList<Integer>> randomVertex = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numberCoupleVertex; i++) {
            int randomVertex1 = (int) (Math.random() * (numberCoupleVertex-1)); // le sommet de départ
            int randomVertex2 = (int) (Math.random() * (numberVertex-1)); // le sommet d'arrivé
            ArrayList<Integer> vertexCouple = new ArrayList<Integer>();
            vertexCouple.add(randomVertex1);
            vertexCouple.add(randomVertex2);
            randomVertex.add(vertexCouple);
            System.out.println("randomVertex1: " + randomVertex1 + " randomVertex2: " + randomVertex2);
        }

        return randomVertex;
    }

    // calculer le temps d'execution de l'algorithme de Dijkstra pour un nombre de
    // couple de sommets aléatoires donné
    public static void calculateExecutionTimeDijkstra(ArrayList<ArrayList<Integer>> randomVertex, Graph graph) {

        FileManager.writeToFile("Dijkstra", FILE_EXECUTION_TIME);

        for (int i = 0; i < randomVertex.size(); i++) {
            System.out.println(randomVertex.size());
            int randomVertex1 = randomVertex.get(i).get(0);
            int randomVertex2 = randomVertex.get(i).get(1);

            
            Graph graph1=graph;
        
            App.Dijkstra(graph1, randomVertex1, randomVertex2, graph.num_v, null);

            

        }
    }

    public static void calculateExecutionTimeAStart(ArrayList<ArrayList<Integer>> randomVertex , Graph graph) {

        FileManager.writeToFile("AStart", FILE_EXECUTION_TIME);

        for (int i = 0; i < randomVertex.size(); i++) {
            int randomVertex1 = randomVertex.get(i).get(0);
            int randomVertex2 = randomVertex.get(i).get(1);

            
            Graph graph1=graph;
        
            App.AStar(graph1, randomVertex1, randomVertex2, graph.num_v, null);

           

        }
    }

    //caclulated execution time of Dijkstra and A* algorithm and write the results to a file
    public static void calculateExecutionTimeDijkstraAStart(int numberCoupleVertex, Graph graph){

         ArrayList<ArrayList<Integer>> randomVertex = selectRandomVertexFromGraph(numberCoupleVertex, graph.num_v);

        calculateExecutionTimeDijkstra(randomVertex, graph);
        calculateExecutionTimeAStart(randomVertex, graph);

    }
}
