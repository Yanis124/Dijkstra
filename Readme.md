# TP7-8 - Dijkstra and A*

## Description
The program aims to find the shortest path between two points in a grid using the Dijkstra and A* algorithms. The goal is to compare the time complexity of these two algorithms.

## Commands
- Navigate to the directory: TP7-TP8
- Compilation command: `javac MainApp/App.java`
- Execution command: `java MainApp/App`
- These commands can be adapted based on your working directory.
- Alternatively, you can execute the program from any directory with the command: `cd ~TP7-TP8/ && find . -name "*.java" \( -name "App.java" -o -name "initGraph.java" -o -name "FileManager.java" -o -name "WeightedGraph.java" -o -name "Board.java" \) -exec javac {} \; && java MainApp/App && cd -`

## Features
- The program provides a visual interface for the user to choose the starting and ending points in a grid of vertices, each vertex being connected by edges with different weights.
- The user can choose to use the A* or Dijkstra algorithm to find the shortest path.
- A window opens to display the explored vertices and the final path found.
- The user can see the number of explored vertices and the length of the found path.
- The found path is written to the "out.txt" file .



