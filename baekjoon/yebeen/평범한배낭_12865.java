import java.util.Scanner;

public class 평범한배낭_12865 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] resultlist = new int[n+1][k+1];
		
		int result = 0;
		
		for(int i = 1; i<n+1; i++) {
			int w = sc.nextInt();
			int v = sc.nextInt();
			
			for(int j=0; j<k+1; j++) {
				if(j>=w) {
					resultlist[i][j]=Math.max(resultlist[i-1][j], resultlist[i-1][j-w]+v);
				}else
					resultlist[i][j]=resultlist[i-1][j];
				result = Math.max(result, resultlist[i][j]);
				//System.out.print(resultlist[i][j]);
			}
			//System.out.println();
		}
		System.out.println(result);
	}

}
