import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 트리순회_1991 {
	static int N;
	static char[] temp;
	static ArrayList<char[]> tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		tree = new ArrayList<>();

		N = sc.nextInt();
		String trash = sc.nextLine();

		for (int i = 0; i < N; i++) {
			String[] temp1 = sc.nextLine().split(" ");
			temp = new char[3];
			for (int j = 0; j < 3; j++) {
				temp[j] = temp1[j].charAt(0);
			}
			tree.add(temp);
		}

		Collections.sort(tree, (char[] a, char[] b) -> a[0] - b[0]);
//		for(int i = 0; i<N; i++) {
//			System.out.println(Arrays.toString(tree.get(i)));
//		}
//		
		pre(0);
		System.out.println();
		inorder(0);
		System.out.println();
		post(0);

	}

	public static void pre(int idx) {
		System.out.print(tree.get(idx)[0]);
		if (tree.get(idx)[1] != '.') {
			pre(tree.get(idx)[1] - 'A');
		}
		if (tree.get(idx)[2] != '.') {
			pre(tree.get(idx)[2] - 'A');
		}
		return;
	}

	public static void inorder(int idx) {
		if (tree.get(idx)[1] != '.') {
			inorder(tree.get(idx)[1] - 'A');
		}
		System.out.print(tree.get(idx)[0]);
		if (tree.get(idx)[2] != '.') {
			inorder(tree.get(idx)[2] - 'A');
		}
		return;
	}

	public static void post(int idx) {
		if (tree.get(idx)[1] != '.') {
			post(tree.get(idx)[1] - 'A');
		}
		if (tree.get(idx)[2] != '.') {
			post(tree.get(idx)[2] - 'A');
		}
		System.out.print(tree.get(idx)[0]);
		return;
	}

}
