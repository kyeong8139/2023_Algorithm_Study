import java.io.*;
import java.util.*;

public class softeer_성적평가 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] sort = new int[N];
        int[] sortsum = new int[N];
        int[] sum = new int[N];
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < 3; t++){
            for(int i = 0; i < N; i++){
                int value = sc.nextInt();
                arr[i] = value;
                sum[i] += value;
                sortsum[i] -= value;
                sort[i] = -1 * value;
            }
            Arrays.sort(sort);
            for(int i = 0; i < N; i++){
            	int idx = Arrays.binarySearch(sort,-1*arr[i]);
                while(idx>0 && sort[idx]== sort[idx-1])
                    idx--;
                sb.append(idx+1).append(" ");
            }
            sb.append("\n");
        }
        Arrays.sort(sortsum);
        for(int i = 0; i < N; i++){
            int idx = Arrays.binarySearch(sortsum,-1*sum[i]);
            while(idx>0 && sortsum[idx]== sortsum[idx-1])
                    idx--;
            sb.append(idx+1).append(" ");
        }
        sb.append("\n");
        System.out.println(sb);
    }
}
