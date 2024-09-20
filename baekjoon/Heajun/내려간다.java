import java.util.Scanner;

public class 내려간다 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int map[][] = new int[N+1][3];
		for(int i = 1; i <= N; i++) {
			map[i][0] = sc.nextInt();
			map[i][1] = sc.nextInt();
			map[i][2] = sc.nextInt();
		}
		int maxdp[][] = new int[N+1][3];
		int mindp[][] = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
			maxdp[i][0] = Math.max(maxdp[i-1][0], maxdp[i-1][1]) + map[i][0];
			maxdp[i][1] = Math.max(Math.max(maxdp[i-1][0], maxdp[i-1][1]),maxdp[i-1][2]) + map[i][1];
			maxdp[i][2] = Math.max(maxdp[i-1][1], maxdp[i-1][2]) + map[i][2];
			
			mindp[i][0] = Math.min(mindp[i-1][0], mindp[i-1][1]) + map[i][0];
			mindp[i][1] = Math.min(Math.min(mindp[i-1][0], mindp[i-1][1]),mindp[i-1][2]) + map[i][1];
			mindp[i][2] = Math.min(mindp[i-1][1], mindp[i-1][2]) + map[i][2];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Math.max(Math.max(maxdp[N][1], maxdp[N][0]), maxdp[N][2])).append(" ");
		sb.append(Math.min(Math.min(mindp[N][1], mindp[N][0]), mindp[N][2]));
		System.out.println(sb);
	}
}
