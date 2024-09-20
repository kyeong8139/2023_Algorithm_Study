import java.util.Scanner;

public class rgb_1149 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] cost = new int[N][3]; // RGB
		int[][] result = new int[N][3];
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<3; j++) {
				cost[i][j]=sc.nextInt();
			}
		}
		
		result[0][0] = cost[0][0];
		result[0][1] = cost[0][1];
		result[0][2] = cost[0][2];
		
		for(int i = 1; i<N; i++) {
			for(int j = 0; j<3; j++) {
				if(j==0) 
					result[i][j] = Math.min(result[i-1][1], result[i-1][2])+cost[i][j];
				else if(j==1) 
					result[i][j] = Math.min(result[i-1][0], result[i-1][2])+cost[i][j];
				else
					result[i][j] = Math.min(result[i-1][0], result[i-1][1])+cost[i][j];
					
			}
		}
		
		System.out.println(Math.min(result[N-1][1], Math.min(result[N-1][2], result[N-1][0])));
		
	}

}
