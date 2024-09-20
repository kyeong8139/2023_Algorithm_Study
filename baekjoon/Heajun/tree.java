import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class tree {
	static class Node {
		int key;
		Node left;
		Node right;

		public Node(int key) {
			this.key = key;
		}
	}

	public static void insert(Node root, int key) {
		if (root.key < key) {
			if (root.right == null)
				root.right = new Node(key);
			else
				insert(root.right, key);
		}
		if (root.key > key) {
			if (root.left == null)
				root.left = new Node(key);
			else
				insert(root.left, key);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int key = Integer.parseInt(br.readLine());
		Node root = new Node(key);
		while (true) {
			String n = br.readLine();
			if (n == null || n.equals("")) {
				break;
			}

			insert(root, Integer.parseInt(n));
		}
		inorder(root);
		System.out.print(sb);
	}

	static StringBuilder sb = new StringBuilder();

	public static void inorder(Node node) {
		if (node == null)
			return;

		inorder(node.left);
		inorder(node.right);
		sb.append(node.key).append("\n");

	}

}
