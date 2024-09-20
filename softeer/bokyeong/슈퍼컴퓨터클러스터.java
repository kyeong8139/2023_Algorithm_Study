import java.io.*;
import java.util.*;

public class Main {

    static int answer;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        binarySearch(arr[0], Integer.MAX_VALUE, b);

        System.out.println(answer);
    }

    public static void binarySearch(int min, int max, long price) {
        if (min > max) return;
        
        int mid = min + (max - min) / 2;  // 최저 성능

        long cur = 0L;  // 현재 비용
        boolean hasOverflow = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= mid) {
                break;
            }

            long curPrice = (long) Math.pow(mid - arr[i], 2);

            // 오버플로우 검사
            if (Long.MAX_VALUE - cur < curPrice) {
                hasOverflow = true;
                break;
            }
            
            cur += curPrice;
        }

        if (cur > price || hasOverflow) {
            binarySearch(min, mid - 1, price);
        } else {
            answer = Math.max(mid, answer);
            binarySearch(mid+1, max, price);
        }
    }
}
