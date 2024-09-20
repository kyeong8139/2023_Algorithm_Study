import java.util.Scanner;

public class 연산자끼워넣기_14888 {
	static int[] check = new int[4];
	static int[] num;
	static int[] re_check = new int[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		num = new int[N];
		for(int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		int op_sum = 0;
		for(int i = 0; i < 4; i++) {
			check[i] = sc.nextInt();
			op_sum += check[i];
		}
		dfs(0,op_sum,num[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
	static long max = -1000000000L;
	static long min = 1000000000L;
	public static void dfs(int n, int op_sum,long sum) {
		if(n==op_sum) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i = 0; i < 4;i++) {
			if(check[i]>0) {
				switch(i) {
				case 0:
					sum += num[n+1];
					check[i]--;
					dfs(n+1,op_sum,sum);
					check[i]++;
					sum -= num[n+1];
					break;
				case 1:
					sum -= num[n+1];
					check[i]--;
					dfs(n+1,op_sum,sum);
					check[i]++;
					sum += num[n+1];
					break;
				case 2:
					sum *= num[n+1];
					check[i]--;
					dfs(n+1,op_sum,sum);
					check[i]++;
					sum /= num[n+1];
					break;
				case 3:
					sum /= num[n+1];
					check[i]--;
					dfs(n+1,op_sum,sum);
					check[i]++;
					sum *=num[n+1];
					break;
				}
			}
		}
		
	}
}
