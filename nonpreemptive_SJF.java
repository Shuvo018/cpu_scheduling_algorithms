public class nonpreemptive_SJF {
    public static void main(String args[]){
        String[] pn = {"p1","p2","p3","p4"};
        int n= pn.length;
        int[]  bt = {6,8,7,3};

        // ascending selection sort for find min burst time
        for(int i=0; i<n; i++){
            int minIdx = i;
            for(int j= i+1; j<n; j++){
                if(bt[j] < bt[minIdx]){
                    minIdx = j;
                }
            }
            // swap burst time
            int temp = bt[minIdx];
            bt[minIdx] = bt[i];
            bt[i] = temp;

            // swap process
            String temp2 = pn[minIdx];
            pn[minIdx] = pn[i];
            pn[i] = temp2;
        }

        int[] ct = new int[n];
        int t_cnt=0; // full process time count
        for (int i=0; i<n; i++){
            t_cnt += bt[i];
            ct[i] = t_cnt;   
        }

        int[] getTT = gettt(ct,n); // Get turnaround time
        int[] getwt = getwt(getTT,bt,n);    // get wating time

        System.out.print("gant chart : ");
        for (int i=0; i<n; i++){
            System.out.print(pn[i]+" ");
        }
        System.out.println();

        System.out.println("Table : ");
        printTable(pn,bt,ct,getTT,getwt,n);


    }
    private static void printTable(String[] pn,int[] bt,int[] ct,int[] getTT,int[] getwt,int n){
        int att = 0, awt =0;
        for (int i=0; i<n; i++){
            System.out.println(pn[i]+" : "+bt[i]+" : "+ct[i]+" : "+ getTT[i]+" : "+ getwt[i]);
            att+= getTT[i];    awt+= getwt[i];
        }
        System.out.print("Average : \n att : "+(att/n)+"\n awt : "+(awt/n));

    }
    private static int[] getwt(int[] getTT, int[] bt, int n) {
        int[] wt = new int[n];

        for (int i=0; i<n; i++){
            wt[i] = getTT[i] - bt[i];
        }
        return wt;
    }

    private static int[] gettt(int[] ct,int n) {
        int[] tt = new int[n];
        for(int i=0; i<n; i++){
            tt[i] = ct[i];
        }
        return tt;
    }
}
