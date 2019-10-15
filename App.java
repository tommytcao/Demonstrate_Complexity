package edu.sdsu.cs;

import edu.sdsu.cs.datastructures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * cssc0229 Tommy Cao
 * cssc0210 Ramon Levya
 * CS 310
 **/

public class App {
    private static int size;
    private static int[] unSortedArray;
    private static int[] sortedArrayLowtoHigh;
    private static int[] sortedArrayHightoLow;

    public App(){
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Note that the output will be stored in a file called output.txt");
        System.out.println("Please insert a starting size greater than 20000 and I will begin the test:");
        try {
            size = keyBoard.nextInt();
            if (size < 0 ) {
                System.out.println("This is not a positive number, this program will restart");
                new App();
            }
            if (size < 20000){
                System.out.println("Please choose a greater number to accurately demonstrate complexity behavior for test.");
                new App();
            }
        } catch (InputMismatchException e) {
            System.out.println("Non-Integer number, this program will restart. Please input an integer number");
            new App();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        new App();

        File file = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);

        header();
        initializeUnsortedArray(size);
        runTest();
        header();
        complexityExplanation();
        header();
        runAdditionalTest();
        header();

    }

    private static void header(){
        System.out.println();
        for (int i = 0; i<120;i++){
            System.out.print("-");
        }
        System.out.println("\n");
    }

    private static void complexityExplanation(){
        System.out.println("Heap Priority Queue Worst Case : RANDOM GENERATED ARRAY");
        System.out.println("\t The heap priority queue works worst with an unsorted an array due to sorting the enqueue");
        System.out.println("\t The best case would be enqueuing in sequential order such as increasing or decreasing as it requires little work from the algorithm \n");

        System.out.println("Array Priority Queue Worst Case : SORTED ARRAY FROM HIGH TO LOW (ex. 4,3,2,1...) Complexity : O(nlog(n)");
        System.out.println("\t The array priority queue works worst with an array from high to low because after finding");
        System.out.println("\t where to insert the array, (O(log(n)) behavior), the array has to shift all the contents to the right");
        System.out.println("\t creating O(n) behavior. This makes the algorithm have O(nlog(n)) behavior\n");

        System.out.println("Unordered Priority Queue Worst Case : SORTED ARRAY FROM LOW TO HIGH (ex. 1,2,3,4... )  Complexity: O(n^2)");
        System.out.println("\t The unordered priority queue works worst with an array from low to high because finding");
        System.out.println("\t after finding the index for removal (O(n)) behavior), the array has to shift all the contents to the left");
        System.out.println("\t creating O(n) behavior. This makes the algorithm have O(n^2) behavior\n");
    }

    private static void hpqTimeTest(int timeN, int[] array){
        HeapPriQueue hpq = new HeapPriQueue();
        double startTime;
        double totalTime;

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            hpq.enqueue(array[i]);
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  " + timeN + "N enqueue test took: " + totalTime + " nanoseconds");

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            hpq.poll();
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  "+ timeN +"N poll test took: " + totalTime + " nanoseconds" + "\n");
        hpq.clear();
    }

    private static void apqTimeTest(int timeN, int[] array){
        ArrayPriQueue apq = new ArrayPriQueue();
        double startTime;
        double totalTime;

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            apq.enqueue(array[i]);
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  " + timeN + "N enqueue test took: " + totalTime + " nanoseconds");

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            apq.poll();
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  "+ timeN +"N poll test took: " + totalTime + " nanoseconds" + "\n");
        apq.clear();
    }
    private static void upqTimeTest(int timeN, int[] array){
        UnorderedPriQueue upq = new UnorderedPriQueue();
        double startTime;
        double totalTime;

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            upq.enqueue(array[i]);
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  " + timeN + "N enqueue test took: " + totalTime + " nanoseconds");

        startTime = System.nanoTime();
        for (int i = 0; i < size*timeN; i++) {
            upq.poll();
        }
        totalTime = System.nanoTime() - startTime;
        System.out.println("\t  "+ timeN +"N poll test took: " + totalTime + " nanoseconds" + "\n");
        upq.clear();
    }

    private static void initializeUnsortedArray(int size) {
        Random randNum = new Random(500);
        unSortedArray = new int[size * 4];
        sortedArrayLowtoHigh = new int[size * 4];
        sortedArrayHightoLow = new int[size * 4];
        for (int i = 0; i < size * 4; i++) {
            unSortedArray[i] = randNum.nextInt();
            sortedArrayLowtoHigh[i] = i;
        }
        for (int i = 0; i < size; size--) {
            sortedArrayHightoLow[i] = size;
        }

    }

    private static void runTest() {

        System.out.println("The system will now show you test results of array size:" + size + "\n");
        System.out.println("Heap Priority Queue Test using an random generated Array: \n");
        heapPriQueueTest();

        System.out
                .println("Array Priority Queue Test using Binary Search insertion using a random generated Array: \n");
        arrayPriQueueTest();

        System.out.println(
                "Unordered Priority Queue test using Linear Search insertion using a random generated Array: \n");
        unorderedPriQueueTest();
        header();

        System.out.println("The system will now demonstrate the worst cases for Array and Unordered Priority Queues\n");

        System.out.println("Heap Priority Queue with sequential list of Low to High case\n");
        heapPriQueueLowToHighTest();

        System.out.println("Array Priority Queue with sequential list of Low to High case\n");
        arrayPriQueueLowToHighTest();

        System.out.println("Unordered Priority Queue with sequential list of Low to High case\n");
        unorderedPriorityQueueLowToHighTest();

        header();

        System.out.println("Heap Priority Queue with sequential list of High to Low case\n");
        heapPriQueueHighToLowTest();

        System.out.println("Array Priority Queue with sequential list of High to Low case\n");
        arrayPriQueueHighToLowTest();

        System.out.println("Unordered Priority with sequential list of High to Low case\n");
        unorderedPriorityQueueHightoLowTest();

    }
    private static void runAdditionalTest(){
        testPeek();
    }
    private static void testPeek(){
        HeapPriQueue hpq = new HeapPriQueue();
        ArrayPriQueue apq = new ArrayPriQueue();
        UnorderedPriQueue upq = new UnorderedPriQueue();
        System.out.println("\nSystem will try to peek into a newly instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.println("\tHeap Priority Peek Test: " + hpq.peek());
        System.out.println("\tArray Priority Peek Test: " + apq.peek());
        System.out.println("\tUnordered Priority Peek Test: " + upq.peek());

        System.out.println("\nSystem will try to test the isEmpty() of a newly instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.println("\t Heap Priority isEmpty Test: " + hpq.isEmpty());
        System.out.println("\t Array Priority isEmpty Test: " + apq.isEmpty());
        System.out.println("\t Unordered Priority isEmpty Test: " + upq.isEmpty());

        System.out.println("\nSystem will try to show the size of a newly instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.println("\tHeap Priority size Test: " + hpq.size());
        System.out.println("\tArray Priority size Test: " + apq.size());
        System.out.println("\tUnordered Priority size Test: " + upq.size());

        hpq.enqueue(-2);
        hpq.enqueue(-3);
        hpq.enqueue(5);
        hpq.enqueue(1);
        hpq.enqueue(5);

        apq.enqueue(-2);
        apq.enqueue(-3);
        apq.enqueue(5);
        apq.enqueue(1);
        apq.enqueue(5);

        upq.enqueue(-2);
        upq.enqueue(-3);
        upq.enqueue(5);
        upq.enqueue(1);
        upq.enqueue(5);

        System.out.println("\nSystem will now instantiated all Queues with an array of length 5");
        System.out.println("\tThe array will contain {-2,-3,5,1,5} for testing purposes");

        System.out.println("\nSystem will try to peek into a instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order"
                + "\nThese sort from highest being at the bottom of the queue and lowest being at the top");
        System.out.println("\t Heap Priority Peek Test: " + hpq.peek());
        System.out.println("\t Array Priority Peek Test: " + apq.peek());
        System.out.println("\t Unordered Priority Peek Test: " + upq.peek());

        System.out.println("\nSystem will try to show the size of an instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.println("\t Heap Priority size Test: " + hpq.size());
        System.out.println("\t Array Priority size Test: " + apq.size());
        System.out.println("\t Unordered Priority size Test: " + upq.size());

        System.out.println("\nSystem will try to test the isEmpty() of an instantiated HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.println("\t Heap Priority isEmpty Test: " + hpq.isEmpty());
        System.out.println("\t Array Priority isEmpty Test: " + apq.isEmpty());
        System.out.println("\t Unordered Priority isEmpty Test: " + upq.isEmpty());

        System.out.println("\nSystem will try to test the contents of a HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.print("\t Heap Priority content Test: ");
        for(Object Content: hpq){
            System.out.print(Content+",");
        }

        System.out.print("\n\t Array Priority content Test: ");
        for(Object Content: apq){
            System.out.print(Content+",");
        }

        System.out.print("\n\t Unordered Priority content Test: ");
        for(Object Content: upq){
            System.out.print(Content+",");
        }


        System.out.println("\n\nSystem will try to test the poll() of a HeapPriQueue,ArrayPriQueue,UnorderedPriQueue in this order");
        System.out.print("\t Heap Priority poll Test: ");
        for (int i = 0; i < 5;i++)
            System.out.print(hpq.poll()+",");
        System.out.print("\n\t Array Priority poll Test: ");

        for (int i = 0; i < 5;i++)
            System.out.print(apq.poll()+",");
        System.out.print("\n\t Unordered Priority poll Test: ");

        for (int i = 0; i < 5;i++)
            System.out.print(upq.poll()+",");


    }

    private static void heapPriQueueTest() {
        hpqTimeTest(1,unSortedArray);
        hpqTimeTest(2,unSortedArray);
        hpqTimeTest(4,unSortedArray);

    }

    private static void arrayPriQueueTest() {
        apqTimeTest(1,unSortedArray);
        apqTimeTest(2,unSortedArray);
        apqTimeTest(4,unSortedArray);

        System.out.println("\tPoll demonstrates O(1) behavior while Enqueue demonstrates the average O(nlog(n)) behavior\n");
    }

    private static void unorderedPriQueueTest() {
        upqTimeTest(1,unSortedArray);
        upqTimeTest(2,unSortedArray);
        upqTimeTest(4,unSortedArray);
        System.out.println("\tPoll demonstrates O(n^2) behavior while Enqueue demonstrates O(1) behavior\n");
    }
    private static void heapPriQueueLowToHighTest() {
        hpqTimeTest(1,sortedArrayLowtoHigh);
        hpqTimeTest(2,sortedArrayLowtoHigh);
        hpqTimeTest(4,sortedArrayLowtoHigh);

    }

    private static void arrayPriQueueLowToHighTest(){
        apqTimeTest(1,sortedArrayLowtoHigh);
        apqTimeTest(2,sortedArrayLowtoHigh);
        apqTimeTest(4,sortedArrayLowtoHigh);

    }

    private static void unorderedPriorityQueueLowToHighTest() {
        upqTimeTest(1,sortedArrayLowtoHigh);
        upqTimeTest(2,sortedArrayLowtoHigh);
        upqTimeTest(4,sortedArrayLowtoHigh);

    }
    private static void heapPriQueueHighToLowTest() {
        hpqTimeTest(1,sortedArrayHightoLow);
        hpqTimeTest(2,sortedArrayHightoLow);
        hpqTimeTest(4,sortedArrayHightoLow);
    }

    private static void arrayPriQueueHighToLowTest(){
        apqTimeTest(1,sortedArrayHightoLow);
        apqTimeTest(2,sortedArrayHightoLow);
        apqTimeTest(4,sortedArrayHightoLow);
    }

    private static void unorderedPriorityQueueHightoLowTest() {
        upqTimeTest(1,sortedArrayHightoLow);
        upqTimeTest(2,sortedArrayHightoLow);
        upqTimeTest(4,sortedArrayHightoLow);
    }
}
