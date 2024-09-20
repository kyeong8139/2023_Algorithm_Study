import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] mm = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=1;j<=n;j++) {
                mm[i][j] = mm[i-1][j] + mm[i][j-1] - mm[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }
        int[] num = new int[4];
        for(int t=0;t<m;t++) {
            st = new StringTokenizer(bf.readLine());
            for(int i=0;i<4;i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            sb.append((mm[num[2]][num[3]]-mm[num[2]][num[1]-1]-mm[num[0]-1][num[3]]+mm[num[0]-1][num[1]-1])).append("\n");
        }
        System.out.print(sb);
    }
}
