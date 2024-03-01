// Par Sylvain Lobry, pour le cours "IF05X040 Algorithmique avancee"
// de l'Universite de Paris, 11/2020

package MainApp;

import java.util.LinkedList;
import java.util.ArrayList;

// Classe definissant un graphe pondere.
public class WeightedGraph {
    // Sous-classe pour une arete.
    static class Edge {
        int source;
        int destination;
        double weight;

        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String toString() {
            return "(" + source + "," + destination + "," + weight + ")";
        }
    }

    // Sous-classe pour un sommet.
    static class Vertex {
        double indivTime;
        double timeFromSource;
        double heuristic;
        Vertex prev;
        LinkedList<Edge> adjacencylist;
        int num;

        public Vertex(int num) {
            this.indivTime = Double.POSITIVE_INFINITY;
            this.timeFromSource = Double.POSITIVE_INFINITY;
            this.heuristic = -1;
            this.prev = null;
            this.adjacencylist = new LinkedList<Edge>();
            this.num = num;
        }

        public void addNeighbor(Edge e) {
            this.adjacencylist.addFirst(e);
        }

        public String toString() {
            String content = "";
            content += " {" + num + "," + indivTime + "," + timeFromSource + "," + heuristic + "," + prev + "}";
            return content;
        }
    }

    // Sous-classe pour le graphe.
    static class Graph {
        ArrayList<Vertex> vertexlist;
        int num_v = 0;

        Graph() {
            vertexlist = new ArrayList<Vertex>();
        }

        public void addVertex(double indivTime) {
            Vertex v = new Vertex(num_v);
            v.indivTime = indivTime;
            vertexlist.add(v);
            num_v = num_v + 1;
        }

        public void addEgde(int source, int destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            vertexlist.get(source).addNeighbor(edge);

        }

        public String toString() {
            String content = "";
            for (int i = 1; i < 2; i++) {
                content += "vertex : " + vertexlist.get(i).num +
                        vertexlist.get(i).toString() + " [";
                for (Edge edge : vertexlist.get(i).adjacencylist) {
                    content += edge.toString();
                }
                if (i == 1) {
                    System.out.println("size=" + vertexlist.get(i).adjacencylist.size());
                }
                content += "]\n";
            }
            return content;
        }
    }

    // Test de la classe.
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(10);
        graph.addVertex(10);
        graph.addVertex(10);
        graph.addVertex(10);
        graph.addVertex(10);
        graph.addVertex(10);
        graph.addEgde(0, 1, 4);
        graph.addEgde(0, 2, 3);
        graph.addEgde(1, 3, 2);
        graph.addEgde(1, 2, 5);
        graph.addEgde(2, 3, 7);
        graph.addEgde(3, 4, 2);
        graph.addEgde(4, 0, 4);
        graph.addEgde(4, 1, 4);
        graph.addEgde(4, 5, 6);
        System.out.println(graph.toString());
    }
}
