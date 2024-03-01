package MainApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import MainApp.WeightedGraph.Graph;

public class FileManager {

    private static final String FILE_OUT = "out.txt";
    private static final String FILE_GRAPH = "graph.txt";

    // write a string to a file
    public static void writeToFile(String content, String filePath) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // writte the path to a file
    public static void writeToFilePath(LinkedList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            writeToFile(String.valueOf(path.get(i)), FILE_OUT);
        }
    }

    // read from file all information relevent to a graph (nlines,
    // ncols,graph,startV,endV,groundColor)
    public static ArrayList<Object> readFromFileGraph() {

        ArrayList<Object> listInformationGraph = new ArrayList<>();

        try {
            // obtenir le fichier qui decrit la carte
            File myObj = new File(FILE_GRAPH);

            Scanner myReader = new Scanner(myObj);
            String data = "";

            // On ignore les deux premieres lignes
            for (int i = 0; i < 3; i++) {
                data = myReader.nextLine();
            }

            // Lecture du nombre de lignes
            int nlines = Integer.parseInt(data.split("=")[1]);
            listInformationGraph.add(nlines);
            // Et du nombre de colonnes
            data = myReader.nextLine();
            int ncols = Integer.parseInt(data.split("=")[1]);
            listInformationGraph.add(ncols);

            // creation du graphe
            Graph graph = new Graph();

            HashMap<String, Integer> groundTypes = new HashMap<String, Integer>(); // {G:1,W:100,B:20,S:30}
            HashMap<Integer, String> groundColor = new HashMap<Integer, String>(); // {1:green,100:gray,20:blue,30:yellow}
            data = myReader.nextLine();
            data = myReader.nextLine();
            // Lire les differents types de cases
            while (!data.equals("==Graph==")) {
                String name = data.split("=")[0];
                int time = Integer.parseInt(data.split("=")[1]);
                data = myReader.nextLine();
                String color = data;
                groundTypes.put(name, time);
                groundColor.put(time, color);
                data = myReader.nextLine();
            }

            // On ajoute les sommets dans le graphe (avec le bon type)
            for (int line = 0; line < nlines; line++) {
                data = myReader.nextLine();
                for (int col = 0; col < ncols; col++) {
                    graph.addVertex(groundTypes.get(String.valueOf(data.charAt(col))));
                }
            }
            listInformationGraph.add(graph);

            data = myReader.nextLine();
            data = myReader.nextLine();
            int startV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols
                    + Integer.parseInt(data.split("=")[1].split(",")[1]);
            listInformationGraph.add(startV);

            data = myReader.nextLine();
            myReader.close();
            int endV = Integer.parseInt(data.split("=")[1].split(",")[0]) * ncols
                    + Integer.parseInt(data.split("=")[1].split(",")[1]);
            listInformationGraph.add(endV);

            listInformationGraph.add(groundColor);
        }

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return listInformationGraph;
    }

}