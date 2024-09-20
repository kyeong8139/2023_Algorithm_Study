import java.io.*;
import java.util.*;

public class 성적평가{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> b[1] - a[1]);

        int[] result = new int[n];
        
        for(int tc = 0; tc<3; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i<n; i++){
                int temp = Integer.parseInt(st.nextToken());
                pq.add(new int[] {i, temp});
                result[i]+=temp;
            }

            int[] tempresult = new int[n];
            int l = 1;
            int t = 0;

            while(!pq.isEmpty()){
                int[] temp = pq.poll();
                tempresult[temp[0]]=l;
                if(!pq.isEmpty() && pq.peek()[1]==temp[1]){
                    t++;
                    continue;
                }
                l+=t;
                t=0;
                l++;
            }
            
            for(int i = 0; i<n; i++){
                sb.append(tempresult[i]).append(" ");
            }
            sb.append("\n");
        }

        for(int i = 0; i<n; i++){
            pq.add(new int[] {i, result[i]});
        }

        int[] tempresult = new int[n];
        int l = 1;
        int t = 0;

        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            tempresult[temp[0]]=l;
            if(!pq.isEmpty() && pq.peek()[1]==temp[1]){
                t++;
                continue;
            }
            l+=t;
            t=0;
            l++;
        }
            
        for(int i = 0; i<n; i++){
            sb.append(tempresult[i]).append(" ");
        }

        System.out.print(sb);

        
    }
}