import java.util.Arrays;
import java.util.Scanner;

public class  N과M{
	static int[] arr;
	static boolean[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[M];
		check = new boolean[N];
		back(N, M, 0);
		System.out.println(sb);

	}

	static StringBuilder sb = new StringBuilder();

	public static void back(int N, int M, int n) {
		if(n==M) {
			for(int j = 0; j < arr.length-1; j++) {
				System.out.print(arr[j] + " ");
			}
			System.out.println(arr[arr.length-1]);
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(!check[i-1]) {
				arr[n] = i;
				check[i-1] = true;
				back(N,M,n+1);
				check[i-1] = false;
			}
		}
		
	}

}
