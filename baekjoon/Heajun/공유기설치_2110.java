import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_2110 {
    public static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); //집의 개수
        int C = Integer.parseInt(st.nextToken()); //공유기의 개수

        home = new int[N];

        for(int i = 0; i < N; i++){
            home[i] = Integer.parseInt(br.readLine()); //집의 위치를 입력

        }

        Arrays.sort(home);

        int low = 1; // 최소 값
        int high = home[N-1] - home[0] + 1; // 최대값

        while(low < high){
            int mid = (high + low) / 2;

            int cnt = 1;
            int prev = home[0]; // 마지막에 집 위치
            for(int i = 1; i < home.length; i++){
                int curr = home[i];

                if(curr - prev >= mid){
                    cnt++;
                    prev = curr;
                }
            }
            if(cnt < C){ //간격에 설치했던 공유기의 개수가 공유기의 개수보다 작을 경우 간격을 좁힘
                high = mid;
            }else {
                low = mid + 1;
            }
        }


        System.out.println(low - 1);

    }

}
