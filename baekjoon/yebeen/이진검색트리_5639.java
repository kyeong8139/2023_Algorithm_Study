import java.util.Scanner;

public class 이진검색트리_5639 {
	public static class Node {
		int id;
		Node par;
		Node left;
		Node right;

		public Node(int id, Node par) {
			this.id = id;
			this.par = par;
		}
	}

	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		sb = new StringBuilder();

		Node root = new Node(sc.nextInt(), null);
		Node ex = root;

		while (sc.hasNextInt()) {
			int tempid = sc.nextInt();
			
			if (tempid < ex.id) {
				Node temp = new Node(tempid, ex);
				ex.left = temp;
				ex = temp;
			} else {
				Node tempex = ex;
				while (tempex.par != null && tempex.par.id < tempid ) {
					tempex = tempex.par;
					if(tempex.par != null && tempex.par.id < tempid)
						continue;
					if(tempex.right==null)
						break;
				}
				while (tempex.right!=null) {
					tempex = tempex.right;
				}
				Node temp = new Node(tempid, tempex);
				tempex.right = temp;
				ex = temp;
			}

		}
		post(root);

		System.out.println(sb);

	}

	public static void post(Node node) {
		
		if (node.left != null)
			post(node.left);
		if (node.right != null)
			post(node.right);
		sb.append(node.id + "\n");
	}

}
