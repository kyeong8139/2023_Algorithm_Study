import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static TreeSet<int[]> set = new TreeSet<>((x, y) -> Arrays.compare(x, y));
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0, new int[M], 0);
		
		for (int[] result : set) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void perm(int depth, int[] result, int select) {
		if (depth == M) {
			int[] temp = new int[M];
			System.arraycopy(result, 0, temp, 0, depth);
			set.add(temp);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((select & (1 << i)) != 0) continue; 
			
			result[depth] = arr[i];
			perm(depth + 1, result, select | 1 << i);
		}
	}
}
