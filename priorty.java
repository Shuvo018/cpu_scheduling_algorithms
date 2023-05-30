import java.util.*;

public class priorty {
    public static void main(String args[]){

//        String[] pn = {"p0","p1","p2","p3","p4"};
        ArrayList<String> pn = new ArrayList<>(Arrays.asList("p0","p1","p2","p3","p4"));
//        int[] proty = {2,1,3,4,3};
        ArrayList<Integer> proty = new ArrayList<>(Arrays.asList(2,1,3,4,3));

//        int[] at = {3,2,5,1,0};
        ArrayList<Integer> at = new ArrayList<>(Arrays.asList(3,2,5,1,0));

//        int[] bt = {5,4,6,7,5};
        ArrayList<Integer> bt = new ArrayList<>(Arrays.asList(5,4,6,7,5));


        int n = pn.size();
        // selection sort
        boolean flag;
        for(int i=0; i<n; i++){
            int minIdx = i;
            flag = false;
            for(int j=i+1; j<n; j++){
                if(at.get(minIdx) > at.get(j)){
                    minIdx = j; flag = true;
                }
            }
            if(flag){
                // swap
                int temp = at.get(i);
                at.set(i,at.get(minIdx));
                at.set(minIdx,temp);

                String temp2 = pn.get(i);
                pn.set(i, pn.get(minIdx));
                pn.set(minIdx,temp2);

                int temp3 = bt.get(i);
                bt.set(i,bt.get(minIdx));
                bt.set(minIdx,temp3);

                int temp4 = proty.get(i);
                proty.set(i, proty.get(minIdx));
                proty.set(minIdx,temp4);

            }
        }

//        for(int i=0; i<n; i++){
//            System.out.println(pn.get(i)+" : "+proty.get(i)+" : "+ at.get(i)+" : "+bt.get(i));
//        }

        Map<String, Integer> ct = new HashMap<>();
        int timeClock = 0;
        bt.set(0,bt.get(0)-1) ; ++timeClock;

        int k=0;
        while(n != 1){

            List<Integer> waitAT = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(at.get(i) <= timeClock){
                    waitAT.add(i);
                }
            }
            System.out.println(waitAT);
            int HPval = proty.get(waitAT.get(0));
            int HPidx = waitAT.get(0);
            for(int i=1; i<waitAT.size(); i++){
                if(HPval < proty.get(waitAT.get(i))){
                    HPidx = waitAT.get(i);
                }
            }
            System.out.println(pn.get(HPidx)+" : "+bt.get(HPidx));

            bt.set(HPidx, bt.get(HPidx)-1); ++timeClock;

            boolean TF = false; int del = 0;
            for(int i=0; i<n; i++){
                if(bt.get(i) == 0){
                    TF = true;
                    del = i;
                }
            }
            if(TF){
                ct.put(pn.get(del), timeClock);
                pn.remove(del); proty.remove(del); at.remove(del); bt.remove(del); n--;
            }

//            System.out.println(ct);

//            break;

        }
        ct.put(pn.get(0), bt.get(0)+timeClock);
            System.out.println(ct);


    }
}
