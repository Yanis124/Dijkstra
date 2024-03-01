import matplotlib.pyplot as plt



def read_file(filename):
    list_elements_file=[]
    # Open the file in read mode
    with open(filename, 'r') as file:
    # Iterate over each line in the file
        for line in file:
        # Process the line (e.g., print it)
            list_elements_file.append(line.strip())
    return list_elements_file

def formate_text(text):
    result=text.split(",")
    return result

def formate_list(list_text):
    result=[]
    
    for text in list_text:
        result.append(formate_text(text))
    return result

def get_list_quicksort(list_execution_time):
    result=[]
    for element in list_execution_time:
        if(len(element)>1):
            result.append(float(element[0]))
    return result

def get_list_heapsort(list_execution_time):
    result=[]
    for element in list_execution_time:
        if(len(element)>1):
            result.append(float(element[1]))
    return result

def display_graph_data(list_data_vertical_axis,name_algo,list_data_horizental_axis,title,x_axis_title,y_axis_title):
    
    plt.plot(list_data_horizental_axis, list_data_vertical_axis, marker='o',label=name_algo)
    
    plt.xlabel(x_axis_title)
    plt.ylabel(y_axis_title)
    plt.title(title)
    plt.grid(True)
    plt.legend()
    
    plt.savefig('complexité QuickSort HeapSort')  # sauvgarder le diagramme

def main():
    filename="QuickSort_HeapSort.txt"  

    list_execution_time=read_file(filename)
    list_execution_time_formated=formate_list(list_execution_time)
    list_quicksort=get_list_quicksort(list_execution_time_formated)
    list_heapsort=get_list_heapsort(list_execution_time_formated)

    list_name=["QuickSort","HeapSort"]
    list_execution_sample=[1000,10000,300000,500000,1000000]
    title="temps d'execution QuickSort et la classe Priority queue pour trouver le max/min"
    x_axis_title="nombre d'éléments"
    y_axis_title="temps d'execution"
    
        
    display_graph_data(list_quicksort,list_name[0],list_execution_sample,title,x_axis_title,y_axis_title)
    display_graph_data(list_heapsort,list_name[1],list_execution_sample,title,x_axis_title,y_axis_title)
    
main()

