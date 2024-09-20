import java.util.*;

public class N과M2_15650 {
    static int[] arr;
    static HashSet<String> array = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N-M+1;i++) {
            getNM(i, M, "");
            arr = new int[N];
        }
        ArrayList<String> ans = new ArrayList<>();
        Iterator<String> itr = array.iterator();
        while(itr.hasNext()) {
           ans.add(itr.next());
        }
        Collections.sort(ans);
        for(String x : ans) {
            System.out.println(x);
        }
    }
    private static void getNM(int firstNum, int m, String outPut) {
        if(m == 0) {
            array.add(outPut); return;
        }
        for(int i=firstNum;i<arr.length;i++) {
            if(arr[i] == 0) {
                arr[i] = 1;
                String temp = outPut;
                temp += outPut.equals("") ? Integer.toString(i+1) : " "+Integer.toString(i+1);
                getNM(i, m-1, temp);
                arr[i] = 0;
            }
        }
    }

}
