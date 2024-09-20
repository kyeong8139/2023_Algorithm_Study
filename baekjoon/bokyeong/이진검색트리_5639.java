import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		Node root = null;
		if (input != null && !input.equals("")) {
			root = new Node(Integer.parseInt(input));
		}

		Node prev = root;
		input = br.readLine();
		while (input != null && !input.equals("")) {
			int num = Integer.parseInt(input);

			Node next = null;
			if (prev.data > num) {
				prev.left = new Node(num, prev);
				next = prev.left;
			} else {
				Node cur = root;
				while (true) {
					Node temp = null;
					
					if (cur.data < num) {
						temp = cur.right;
						if (temp == null) {
							cur.right = new Node(num, cur);
							next = cur.right;
							break;
						}
					} else {
						temp = cur.left;
						if (temp == null) {
							cur.left = new Node(num, cur);
							next = cur.left;
							break;
						}
					}
					
					cur = temp;
				}
			}
	
			prev = next;
			input = br.readLine();
		}

		postOrder(root);
		System.out.println(sb);
	}

	public static void postOrder(Node node) {
		if (node == null)
			return;

		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.data).append("\n");
	}

	static class Node {
		int data;
		Node parent;
		Node left;
		Node right;

		public Node(int data) {
			super();
			this.data = data;
		}

		public Node(int data, Main.Node parent) {
			this.data = data;
			this.parent = parent;
		}
	}
}
