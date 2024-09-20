import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj2110_공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);

        int left = 1; // 가능한 최소 간격
        int right = arr[n - 1]; // 입력받은 집들의 최대 간격

        while (left <= right) {
            int mid = (left + right) / 2; // 최소 거리 설정

            int position = 0; // 공유기 설치 위치(처음부터 시작)
            int cnt = 1; // 설치 가능한 공유기 수
            for (int i = 1; i < n; i++) {
                if (arr[i] - arr[position] >= mid) {
                    position = i;
                    cnt++;
                }
            }

            if (cnt < c) { // 설치된 공유기 수가 가지고 있는 공유기의 수보다 적으면
                right = mid - 1; // 작게 이분탐색
                continue;
            }
            
            //설치된 공유기 수가 가지고 있는 공유기 수보다 크다면
            left = mid + 1; // 크게 이분탐색을 실시한다.
            
        }

        System.out.println(left - 1);
    }
}