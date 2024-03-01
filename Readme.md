## TP7-8 - Dijkstra et A*

**Commandes :**
- se diriger vers le répertoire : TP7-TP8
- Commande de compilation : `javac MainApp/App.java`
- Commande d'exécution : `java MainApp/App`
- Vous pouvez adapter ces commandes en fonction de votre répertoire de travail.
- sinon vous pouvez l'éxecuter depuis n'importe quel répertoire : `cd ~/ALGO/PartieB/TP7-TP8/ && find . -name "*.java" \( -name "App.java" -o -name "initGraph.java" -o -name "FileManager.java" -o -name "WeightedGraph.java" -o -name "Board.java" \) -exec javac {} \; && java MainApp/App && cd -`


**Fonctionnement :**
Le programme permet à l'utilisateur de trouver le chemin le plus court entre deux points dans un graphe qui est representé sous forme de grille. Voici les étapes :
1. Le programme commence par créer le graphe en lisant le fichier "graph.txt" ou se trouve les sommets du graphe 
2. L'utilisateur choisit le sommet de départ et d'arrivée dans une grille contenant des sommets, chaque sommet ayant des arêtes de valeurs différentes. La grille est représentée dans un fichier.
3. L'utilisateur choisit d'utiliser l'algorithme A* ou Dijkstra.
4. Une fenêtre s'ouvre pour afficher les sommets explorés et le chemin final.
5. l'utilisateur voit le nombre de sommets explorés et la longueur du chemin.
6. le chemin trouvé sera écrit dans le fichier "out.txt"




