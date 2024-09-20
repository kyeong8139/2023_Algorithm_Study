import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int[] result = new int[M];
		
		sb = new StringBuilder();
		perm(0, result, 0);
		
		System.out.println(sb);
	}
	
	public static void perm(int depth, int[] result, int selected) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((selected & (1 << i)) != 0) {
				continue;
			}
			result[depth] = arr[i];
			perm(depth + 1, result, selected | (1 << i));
		}
	}
	
}
