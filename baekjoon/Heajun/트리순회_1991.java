package river;

import java.util.Arrays;
import java.util.Scanner;

public class 트리순회_1991 {
	static int[][] arr = new int[26][2];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			char A = sc.next().charAt(0);
			char B = sc.next().charAt(0);
			char C = sc.next().charAt(0);
			arr[A -'A'][0] = B - 'A'; //왼쪽 저장
			arr[A -'A'][1] = C - 'A'; //왼쪽 저장

		}
		preorder(0);
		sb.append("\n");
		inorder(0);
		sb.append("\n");
		postorder(0);
		System.out.println(sb);
	}

	public static void preorder(int n) {
		if(n < 0)
			return;
		
		sb.append((char) (n+'A'));
		preorder(arr[n][0]);
		preorder(arr[n][1]);
	}

	public static void inorder(int n) {
		if(n < 0)
			return;
		inorder(arr[n][0]);
		sb.append((char) (n+'A'));
		inorder(arr[n][1]);
	}

	public static void postorder(int n) {
		if(n < 0)
			return;
		postorder(arr[n][0]);
		postorder(arr[n][1]);
		sb.append((char) (n+'A'));
	}
}
