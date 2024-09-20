	import java.util.Scanner;
	
	public class _16953 {
		static int max = Integer.MAX_VALUE;
		static long N;
		static long G;
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			N = sc.nextInt();
			G = sc.nextInt();
			dfs(G,0);
			if(max==Integer.MAX_VALUE)
				System.out.println(-1);
			else
				System.out.println(max+1);
		}
		
		public static void dfs(long n, int cnt) {
			if(n == N) {
				max = Math.min(max, cnt);
				return;
			}
			if(n < N) {
				return;
			}
			if(n%2==0)
				dfs(n/2,cnt+1);
			if((n-1)%10==0)
				dfs((n-1)/10,cnt+1);
		}
	}
