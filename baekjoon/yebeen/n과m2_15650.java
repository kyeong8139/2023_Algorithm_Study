import java.util.Scanner;

public class n과m2_15650 {
	static int N;
	static int M;
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		String temp = "";
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		
		for (int i = 1; i<=N; i++) {
			num[i-1]=i;
		}
		
		find(0, 0, temp);
		
	}
	
	public static void find(int i, int cnt, String result) {
//		System.out.println(" " + result);
		if(cnt == M) {
			System.out.println(result);
			return;
		}
		
		for(int sum = i; sum<N; sum++) {
			String str = num[sum]+" ";
			find(sum+1, cnt+1, result+str);
		}
	}

}
