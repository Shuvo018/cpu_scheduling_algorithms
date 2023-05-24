import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class nonpreemptive_SJF {
    public static void main(String args[]){
        String[] pn = {"p1","p2","p3","p4"};
        int n= pn.length;
        int[]  bt = {6,8,7,3};

        for(int i=0; i<n; i++){
            int minIdx = i;
            for(int j= i+1; j<n; j++){
                if(bt[j] < bt[minIdx]){
                    minIdx = j;
                }
            }
            // swap bt time
            int temp = bt[minIdx];
            bt[minIdx] = bt[i];
            bt[i] = temp;
            // swap pn
            String temp2 = pn[minIdx];
            pn[minIdx] = pn[i];
            pn[i] = temp2;
        }

//        Map<String,Integer> ct = new HashMap<>();
        int[] ct = new int[n];
        int t_cnt=0;
        for (int i=0; i<n; i++){
            t_cnt += bt[i];
            ct[i] = t_cnt;
//            ct.put(pn[i],t_cnt);
        }


        int[] tt = new int[n];
        for(int i=0; i<n; i++){
            tt[i] = ct[i];
        }

        int[] wt = new int[n];
        for (int i=0; i<n; i++){
            wt[i] = tt[i] - bt[i];
        }

        int att = 0, awt =0;
        for (int i=0; i<n; i++){
            System.out.println(pn[i]+" : "+bt[i]+" : "+ct[i]+" : "+ tt[i]+" : "+ wt[i]);
            att+= tt[i];    awt+= wt[i];
        }


        System.out.print("Average : \n att : "+(att/n)+"\n awt : "+(awt/n));

    }
}
