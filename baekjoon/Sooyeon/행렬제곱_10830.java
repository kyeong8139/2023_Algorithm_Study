import java.io.*;
import java.util.*;
 
public class 행렬제곱_10830 {
    static int N, arr[][], ans[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); long B = Long.parseLong(st.nextToken());
		arr = new int[N][N]; ans = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			ans[i][i] = 1;
		}
        
		while(B > 0) {
			if(B % 2 == 1) {
				ans = mult(ans, arr);
			}
			arr = mult(arr, arr);
			B/=2;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] mult(int[][] o1, int[][] o2) {
		
		int[][] returnArr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					returnArr[i][j] += o1[i][k] * o2[k][j];
					returnArr[i][j] %= 1000;
				}
			}
		}
		return returnArr;
	}
}