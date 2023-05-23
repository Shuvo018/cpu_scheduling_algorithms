import java.util.*;

public class SRTF {
    public static void main(String args[]){
//        String[] pn = {"p1", "p2", "p3","p4"};
        ArrayList<String> pn = new ArrayList<>(Arrays.asList("p1", "p2", "p3","p4"));

        ArrayList<Integer> at = new ArrayList<>(Arrays.asList(0,1,2,4));
        ArrayList<Integer> bt = new ArrayList<>(Arrays.asList(5,3,4,1));
        int n = pn.size();


        int ct_cnt = 0;
        Map<String, Integer> ct = new HashMap<>();
        Map<String, Integer> completeTime = new HashMap<>();
        ct.put(pn.get(0),++ct_cnt);
        bt.set(0,bt.get(0)-1);
        int k = 0;
        while(n!=1){
            ArrayList<Integer> stack = new ArrayList<>();
            for(int i=0; i<n; i++){// search arrival for me
                if(ct_cnt>=at.get(i)){
                    stack.add(i);
                }
            }
            System.out.println("stack : "+stack);
            int min = bt.get(stack.get(0)); // get min burst time
            int minIdx = 0;
            for(int i=1; i<stack.size(); i++){

                if(min>bt.get(stack.get(i))){
                    minIdx = i;
                }
            }
            System.out.println("find min "+min+stack.size());
            ct.put(pn.get(minIdx), ++ct_cnt );   bt.set(minIdx, bt.get(minIdx)-1);
//            System.out.println(ct);
            boolean flag = false;
            int del =0;
            for(int i=0; i<n; i++){// print burst time
                System.out.print(bt.get(i)+" ");
                if(bt.get(i)==0){
                    del = i;
                    flag = true;
                    break;
                }
            }
            if(flag){
                completeTime.put(pn.get(del), ct_cnt);
                at.remove(del); bt.remove(del); ct.remove(pn.get(del)); pn.remove(del); n--;
            }
//            System.out.println("complete time : "+completeTime);
//            System.out.println("burst time : "+bt);
//            System.out.println("process no : "+pn);
        }
        completeTime.put(pn.get(0), ct_cnt+bt.get(0));

        System.out.println("complete time* : "+completeTime);


    }
}
