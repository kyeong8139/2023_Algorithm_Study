import java.util.Scanner;

public class 계단오르기_2579 {
	
	public static void main(String[] args) {
		
		Scanner ac = new Scanner(System.in);
		
		int[] dp = new int[301];
		
		int a = ac.nextInt(); // 계단 수
		
		int[] value = new int[a+1]; // 계단별 가치
		
		for (int i = 1; i<=a; i++) { // 계단 가치 배열로 입력
			value[i] = ac.nextInt();
		}
		
		for (int j = 1; j<=a; j++) {
			if (j == 1) {
				dp[j] = value[j];
			}			
			else if (j == 2){
				dp[j]= value[j]+value[j-1];
			}
			else if (j>= 3){
				int x = value[j]+value[j-1]+dp[j-3];
				int y = value[j]+dp[j-2];
				dp[j] = Math.max(x,y);
			}
		}
		
		System.out.println(dp[a]);
	}
}