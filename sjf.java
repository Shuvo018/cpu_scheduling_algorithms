import java.util.*;

import java.util.stream.Collectors;


import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class sjf {
    public static void main(String args[]){
//*** non preemptive ****//
        String[] procss = {"p1","p2","p3","p4","p5"};
        Integer[] arrival = {4,0,1,6,2};
        Integer[] burst = {5,2,5,7,3};

        int n = procss.length;


        // selection sort
        for(int i=0; i<n; i++){
            int minValIdx = i;
            for(int j= i+1; j<n; j++){
                if(arrival[j]< arrival[minValIdx]){
                    minValIdx = j;
                }
            }
            //for arrival
            int temp = arrival[i];
            arrival[i] = arrival[minValIdx];
            arrival[minValIdx] = temp;

            //for process
            String tempStr = procss[i];
            procss[i] = procss[minValIdx];
            procss[minValIdx] = tempStr;

            // for burst
            temp = burst[i];
            burst[i] = burst[minValIdx];
            burst[minValIdx] = temp;
        }


// print After swap process, arrival, burst
        for(int i=0; i<n; i++){
            System.out.println(procss[i]+" : "+arrival[i]+" : "+burst[i]);
        }

        Integer[] complete = new Integer[n];    int completeIdx = 0;
        complete[completeIdx++] = burst[0];
//        System.out.println("complete : "+complete[0]);

        for(int i=1; i<n; i++){

//            System.out.println("before inner swap arrival i : "+procss[i]+" : "+arrival[i]+" : "+burst[i]);
            ArrayList<Integer> waitForComplete = new ArrayList<Integer>();      int waitForCompleteIdx=0;
            for(int j=i; j<n; j++){//search for execution
                if(arrival[j]<= complete[completeIdx-1]){
                    waitForComplete.add(waitForCompleteIdx++,j);
                }
            }


            int minWaitValue = burst[waitForComplete.get(0)];
            int minWaitIdx = waitForComplete.get(0);

//            System.out.println(procss[waitForComplete.get(0)]+" : "+ burst[waitForComplete.get(0)]);

            for(int k=1; k<waitForComplete.size(); k++){
//                System.out.println(procss[waitForComplete.get(k)]+" : "+ burst[waitForComplete.get(k)]);

                if(burst[waitForComplete.get(k)]<minWaitValue){
                    minWaitIdx = waitForComplete.get(k);
                    minWaitValue = burst[waitForComplete.get(k)];
//                    System.out.println(procss[minWaitIdx]+" : "+minWaitIdx+" : "+burst[minWaitIdx]);
                }
            }

            //for arrival
            int tempForAr =arrival[i];
            arrival[i] = arrival[minWaitIdx];
            arrival[minWaitIdx] = tempForAr;

            //for process
            String tempForPro = procss[i];
            procss[i] = procss[minWaitIdx];
            procss[minWaitIdx] = tempForPro;

            //for burst
            int tempForBur = burst[i];
            burst[i] = burst[minWaitIdx];
            burst[minWaitIdx] = tempForBur;

//            System.out.println("after inner swap arrival i : "+procss[i]+" : "+arrival[i]+" : "+burst[i]);

            complete[completeIdx] = complete[completeIdx-1] + burst[i]; completeIdx++;
//            System.out.println(complete[completeIdx-1]+burst[i]);
//            System.out.println(complete[completeIdx-1]+" : "+completeIdx);

//            System.out.println("after inner swap arrival i : "+procss[i+1]+" : "+arrival[i+1]+" : "+burst[i+1]);



        }

        System.out.print("Gantt chat : ");

        for(int i=0; i<n; i++){
            System.out.print(procss[i]+" ");
        }
        System.out.println();

        System.out.print("complete time : ");
        for(int i=0; i<n; i++){
            System.out.print(complete[i]+" ");
        }
        System.out.println();


        Integer[] turnaroundTime = turnaroundTimeGet(complete,arrival,n);
        Integer[] waitingTime = waitingTimeGet(turnaroundTime,burst,n);


        printGanttChart(procss,arrival,burst,turnaroundTime,waitingTime,n);
//        System.out.println("min : "+minWait);

    }

    private static void printGanttChart(String[] procss, Integer[] arrival, Integer[] burst, Integer[] turnaroundTime, Integer[] waitingTime, int n) {
        System.out.println("Table : ");
        System.out.println("PR\tAT\tBT\tTT\tWT");

        for(int i=0; i<n; i++){
            System.out.println(procss[i]+"\t"+arrival[i]+"\t"+burst[i]+"\t"+turnaroundTime[i]+"\t"+waitingTime[i]);
        }
    }

    private static Integer[] waitingTimeGet(Integer[] turnaroundTime, Integer[] burst, int n) {
        Integer[] waitingTime = new Integer[n];
        for(int i=0; i<n; i++){
            waitingTime[i] = turnaroundTime[i] - burst[i];
        }
        return waitingTime;
    }

    private static Integer[] turnaroundTimeGet(Integer[] complete, Integer[] arrival,int n) {

        Integer[] turnaroundTime = new Integer[n];
        for(int i=0; i<n; i++){
            turnaroundTime[i] = complete[i] - arrival[i];
        }
        System.out.println();

        return turnaroundTime;

    }


}
