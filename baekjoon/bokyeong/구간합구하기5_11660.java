import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] array = new int[N+1][N+1];
        int[][] sum = new int[N+ 1][N + 1];
        
        for (int r = 1; r <= N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for (int c = 1; c <= N; c++) {
        		array[r][c] = Integer.parseInt(st.nextToken());
        		sum[r][c] = sum[r-1][c] + sum[r][c-1] - sum[r-1][c-1] + array[r][c];
        	}
        }
        
        for (int i = 0; i < M; i++) {
        	
        	st = new StringTokenizer(br.readLine());
        	
        	int x1 = Integer.parseInt(st.nextToken());
        	int y1 = Integer.parseInt(st.nextToken());
        	int x2 = Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
        	
        	int ans = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
        	System.out.println(ans);
        }
    }
}
