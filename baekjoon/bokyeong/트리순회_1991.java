import java.util.Scanner;

class Node {
	char data;
	int left;
	int right;
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Node[] tree = new Node[N+1];
		
		for (int i = 1; i <= N; i++) {
			String data = sc.next();
			int idx = data.charAt(0) - 'A' + 1;
			
			tree[idx] = new Node();
			tree[idx].data = data.charAt(0);
			tree[idx].left = sc.next().charAt(0) - 'A' + 1;
			tree[idx].right = sc.next().charAt(0) - 'A' + 1;
		}
		
		preorder(tree, 1);
		System.out.println();
		inorder(tree, 1);
		System.out.println();
		postorder(tree, 1);
	}
	
	public static void preorder(Node[] tree, int idx) {
		if (idx < 1 || idx >= tree.length) {
			return;
		}
		
		System.out.print(tree[idx].data);
		preorder(tree, tree[idx].left);
		preorder(tree, tree[idx].right);
	}
	
	public static void inorder(Node[] tree, int idx) {
		if (idx < 1 || idx >= tree.length) {
			return;
		}
		
		inorder(tree, tree[idx].left);
		System.out.print(tree[idx].data);
		inorder(tree, tree[idx].right);
	}
	
	public static void postorder(Node[] tree, int idx) {
		if (idx < 1 || idx >= tree.length) {
			return;
		}
		
		postorder(tree, tree[idx].left);
		postorder(tree, tree[idx].right);
		System.out.print(tree[idx].data);
	}
}
