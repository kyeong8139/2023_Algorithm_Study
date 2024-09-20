import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2073_수도배관공사 {
	public static class Pipe implements Comparable<Pipe>{
		int length, size;
		public Pipe() {
			// TODO Auto-generated constructor stub
		}
		public Pipe(int length, int size) {
			this.length = length;
			this.size = size;
		}
		@Override
		public int compareTo(Pipe o) {
			if(this.length == o.length) {
				return o.size - this.size;
			} else {
				return this.length - o.length;
			}
		}
		@Override
		public String toString() {
			return "Pipe [length=" + length + ", size=" + size + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st  = new StringTokenizer(str);
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		PriorityQueue<Pipe> pq = new PriorityQueue<>();
		for(int i = 0; i<P; ++i) {
			str = br.readLine();
			st  = new StringTokenizer(str);
			int len = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Pipe p = new Pipe(len, size);
			pq.add(p);
		}
		int[][] dp = new int[P+1][D+1];
		Pipe p1 = pq.poll();
		dp[1][p1.length] = p1.size;
		for(int r = 2 ; r<=P;++r) {
			Pipe p = pq.poll();
			dp[r][p.length] = Math.max(dp[r-1][p.length], p.size);
			for(int c = 0; c<p.length;++c) {
				dp[r][c] = dp[r-1][c];
			}
			for(int c = p.length+1; c<=D; ++c) {
				if(dp[r-1][c-p.length]!=0) {
					dp[r][c] = Math.max(dp[r-1][c], Math.min(dp[r-1][c-p.length], p.size));
				} else {
					dp[r][c] = dp[r-1][c];
				}
			}
		}
		
//		for(int r = 0 ; r<=P;++r) {
//			System.out.println(Arrays.toString(dp[r]));
//		}
		
		System.out.println(dp[P][D]);
	}
}
