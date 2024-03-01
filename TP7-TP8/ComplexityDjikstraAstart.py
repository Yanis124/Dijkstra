import matplotlib.pyplot as plt



#read the file with data 
def readFile(file_path):
    list_data=[]
    djikstra=False
    A_Start=False
    list_data_Djikstra=[]
    list_data_A_Start=[]
    with open(file_path, 'r') as file:
    # Iterate over each line in the file
        for line in file:
            
            if(line.strip()=="Dijkstra"):
                djikstra=True
           
            elif(line.strip()=="AStart"):
                A_Start=True
                djikstra=False
                
            elif(line.strip()!="Dijkstra" and djikstra):
                
                list_data_Djikstra.append(int(line.strip()))
                
            elif(line.strip()!="AStart" and A_Start):
                
                list_data_A_Start.append(int(line.strip()))
                
    list_data.append(list_data_Djikstra)
    list_data.append(list_data_A_Start)
    return list_data

def display_graph_data(list_data,name_algo,nombre_tries):

    list_tries=[i for i in range(1,nombre_tries+1)]
    
    print(len(list_data))
    print(len(list_tries))
    
    plt.plot(list_tries, list_data, marker='o',label=name_algo)
    
    plt.ylabel('nombre de sommets explorés')
    title="Comparaison entre Dijkstra et A*( le nombre de sommets explorés)"
    plt.title(title)
    plt.grid(True)
    plt.legend()
    
    plt.ylim(0, max(list_data) + 1)
    
    plt.savefig('complexité Djikstra A*')  # sauvgarder le diagramme

    
def main():
    file_path="AlgorithmExecutionTime.txt"
    list_data=readFile(file_path)
    list_name_algo=["Dijkstra","AStart"]
    
    print(list_data)
    
    
    for list_data_algo, name_algo in zip(list_data,list_name_algo):
        display_graph_data(list_data_algo,name_algo,len(list_data_algo))
    
        
main()