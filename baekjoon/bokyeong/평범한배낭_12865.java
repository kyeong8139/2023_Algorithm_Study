import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static class Item {
		int weight;
		int value;
		
		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Item> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new Item(sc.nextInt(), sc.nextInt()));
		}
		
		int[] dp = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			Item cur = list.get(i);
			if (cur.weight > K || cur.value == 0) {
				continue;
			}
			
			int[] newDp = new int[K+1];
			System.arraycopy(dp, 0, newDp, 0, cur.weight - 1);
			for (int j = 0; cur.weight + j <= K ; j++) {
				newDp[cur.weight + j] = Math.max(dp[cur.weight + j], cur.value + dp[j]);
			}
			
			dp = newDp;
		}
		
		int max = 0;
		for (int i = 1; i <= K; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
