import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class srtf {
    private static int att =0, awt =0;
    public static void main(String[] args) {
        ArrayList<String> pn = new ArrayList<>(Arrays.asList("p1","p2","p3","p4")); int n = pn.size();
        ArrayList<Integer> at = new ArrayList<>(Arrays.asList(0,1,2,3));
        ArrayList<Integer> bt = new ArrayList<>(Arrays.asList(8,4,9,5));
        ArrayList<Integer> ct = new ArrayList<>();
        ArrayList<Integer> bt2 = new ArrayList<>(Arrays.asList(8,4,9,5));

        ArrayList<String> temp_pn = new ArrayList<>();
        ArrayList<Integer> temp_at = new ArrayList<>();
        ArrayList<Integer> temp_bt = new ArrayList<>();


        int timeClok = 0;
        Map<String,Integer> ctMap = new HashMap<>();

        bt.set(0, bt.get(0)-1); ++timeClok;


        while(n>1){
            ArrayList<Integer> waitAt = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(at.get(i)<=timeClok){
                    waitAt.add(i);
                }
            }
//            System.out.println(waitAt);

            int min_bt = bt.get(waitAt.get(0));
            int minIdx_bt = waitAt.get(0);
            for (int i=1; i<waitAt.size(); i++){
                if(min_bt > bt.get(waitAt.get(i))){
                    min_bt = bt.get(waitAt.get(i));
                    minIdx_bt = waitAt.get(i);
                }
            }
            bt.set(minIdx_bt, bt.get(minIdx_bt)-1); ++timeClok;


            for(int i=0; i<n; i++){
                if(bt.get(i)==0){
                    ctMap.put(pn.get(i), timeClok);

                    temp_pn.add(pn.get(i)); temp_at.add(at.get(i)); temp_bt.add(bt2.get(i)); ct.add(timeClok);
                    pn.remove(i); at.remove(i); bt.remove(i);bt2.remove(i);  --n;
                    break;
                }
            }
//            System.out.println(ctMap);


        }
        ctMap.put(pn.get(0), timeClok+bt.get(0));
        temp_pn.add(pn.get(0)); temp_at.add(at.get(0)); temp_bt.add(bt2.get(0)); ct.add(timeClok+bt.get(0));


        printGantChart(temp_pn);
        System.out.println();
        ArrayList<Integer> tt = getTAT(ct,temp_at);
        ArrayList<Integer>  wt = getWT(tt,temp_bt);

        System.out.println("average att : "+((double)att/temp_pn.size())+" : awt : "+((double)awt/temp_pn.size()));

        printTable(temp_pn,temp_at,temp_bt,ct,tt,wt);

    }

    private static ArrayList<Integer> getWT(ArrayList<Integer> tt, ArrayList<Integer> tempBt) {
        ArrayList<Integer> wt = new ArrayList<>();
        for (int i=0; i<tt.size(); i++){
            wt.add(i,tt.get(i)-tempBt.get(i));
            awt += wt.get(i);
        }
        return wt;
    }

    private static void printTable(ArrayList<String> tempPn, ArrayList<Integer> tempAt, ArrayList<Integer> tempBt, ArrayList<Integer> ct, ArrayList<Integer> tt, ArrayList<Integer> wt) {
        System.out.println("Table : ");
        System.out.println("PN\tAT\tBT\tCT\tTAT\tWT");


        for (int i=0; i<tempPn.size(); i++){
            System.out.println(tempPn.get(i)+"\t"+tempAt.get(i)+"\t"+tempBt.get(i)+"\t"+ct.get(i)+"\t"+tt.get(i)+"\t"+wt.get(i));
        }

    }



    private static ArrayList<Integer> getTAT(ArrayList<Integer> ct, ArrayList<Integer> tempAt) {

        ArrayList<Integer> tt = new ArrayList<>();
        for (int i=0; i<ct.size(); i++){
            tt.add(i,ct.get(i)-tempAt.get(i));
            att += tt.get(i);
        }
        return tt;
    }

    private static void printGantChart(ArrayList<String> tempPn) {
        System.out.print("Gant chart : ");
        for(int i=0; i<tempPn.size(); i++){
            System.out.print(tempPn.get(i)+" ");
        }

    }



}
