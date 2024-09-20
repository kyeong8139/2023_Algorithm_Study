import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj5639_이진검색트리 {

	static class Node {
		int data;
		Node left, right;

		public Node(int data) {
			this.data = data;
		}
	}
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		Node root = new Node(sc.nextInt());
		Node root = new Node(Integer.parseInt(br.readLine()));
//		while (sc.hasNextInt()) {
//			add(root, new Node(sc.nextInt()));
//		}
		
		String input;
		while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            add(root, new Node(Integer.parseInt(input)));
        }
		
		postorder(root);
		
		System.out.println(sb);

	}

	static void add(Node pNode, Node chNode) {
		if(pNode.data > chNode.data) {
			if(pNode.left == null) {
				pNode.left = chNode;
			} else {
				add(pNode.left, chNode);
			}
		} else {
			if(pNode.right == null) {
				pNode.right = chNode;
			} else {
				add(pNode.right, chNode);
			}
		}
	}

	private static void postorder(Node node) {

		if (node.left != null)
			postorder(node.left);

		if (node.right != null)
			postorder(node.right);

		sb.append(node.data).append("\n");
	}

}
