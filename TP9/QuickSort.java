package TP9;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;






public class QuickSort {

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

    private static void generateRandomListPriorityQueue(int size,List<Integer> list,PriorityQueue<Integer> priorityQueue) {
        
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int number=random.nextInt();
            priorityQueue.offer(number);
            list.add(number);
        }
    }

    public static void main(String[] args) {

        int [] listNumberElements = {1000,10000,300000,500000,1000000};
        
        String filePath="TP9/QuickSort_HeapSort.txt";
       
        for(int i=0;i<listNumberElements.length;i++){
            //List<Integer> list = new ArrayList<Integer>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            List<Integer> list = new ArrayList<Integer>();

            generateRandomListPriorityQueue(listNumberElements[i],list,priorityQueue);
            
            //  // Measure the time taken to sort the array using Arrays.sort (Quicksort)
            double startTime = System.nanoTime();
            list.sort((a, b) -> Integer.compare(b, a));
            double endTime = System.nanoTime();

            double durationQuickSort = (endTime - startTime)/1_000_000.0;

            

            //Duration durationQuick = Duration.between(startTimeQuickSort, endTimeQuickSort);
            //long durationQuickSort = durationQuick.toNanos();

            startTime = System.nanoTime();
            
            priorityQueue.poll();
            
            endTime = System.nanoTime();

            double durationHeapSort = (endTime - startTime)/1_000_000;
            
            writeToFile(""+durationQuickSort+","+durationHeapSort, filePath);
            
        }
    }

   

}
