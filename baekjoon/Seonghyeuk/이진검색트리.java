import java.util.Scanner;

public class 이진검색트리 {
	static class Node {
		int data;
		Node par,left, right;

		public Node() {
		}
	}
	static String input1 = "50\r\n"
			+ "30\r\n"
			+ "24\r\n"
			+ "5\r\n"
			+ "28\r\n"
			+ "45\r\n"
			+ "98\r\n"
			+ "52\r\n"
			+ "60";
	
	static String input2 = "1\r\n"
			+ "2\r\n"
			+ "3\r\n"
			+ "4\r\n"
			+ "5\r\n"
			+ "6\r\n"
			+ "7\r\n"
			+ "8\r\n"
			+ "9"; // 오른쪽 편향 트리 1 -> 2 -> 3 ....
	
	static String input3 = "9\r\n"
			+ "8\r\n"
			+ "7\r\n"
			+ "6\r\n"
			+ "5\r\n"
			+ "4\r\n"
			+ "3\r\n"
			+ "2\r\n"
			+ "1"; // 왼쪽 편향 트리 9 -> 8 -> 7 ...
	
	static String input4 = "50\r\n"
			+ "\r\n"
			+ "30\r\n"
			+ "\r\n"
			+ "24\r\n"
			+ "\r\n"
			+ "5\r\n"
			+ "\r\n"
			+ "27\r\n"
			+ "\r\n"
			+ "25\r\n"
			+ "\r\n"
			+ "26\r\n"
			+ "\r\n"
			+ "28\r\n"
			+ "\r\n"
			+ "29\r\n"
			+ "\r\n"
			+ "45\r\n"
			+ "\r\n"
			+ "98\r\n"
			+ "\r\n"
			+ "52\r\n"
			+ "\r\n"
			+ "60\r\n"
			+ "\r\n"
			+ "106\r\n"
			+ "\r\n"
			+ "109\r\n"
			+ "\r\n"
			+ "108\r\n"
			+ "\r\n"
			+ "110";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Node parNode = null;
		Node rootNode = null;
		boolean isroot = true;
		while(sc.hasNext()){
			int nodeData = sc.nextInt(); 
			Node node = new Node();
			node.data = nodeData;
			if(isroot) {
				rootNode = node;
				isroot = false;
			}
			if(parNode!=null) {
				while(true) {
					if(parNode.data>node.data) {// 왼쪽 자식인가?
						if(parNode.left == null) {
							node.par = parNode;
							parNode.left = node;
							break;
						} else {
							parNode = parNode.left;
							continue;
						}
					} else {
						if(parNode.right == null) {
							node.par = parNode;
							parNode.right = node;
							break;
						} else {
							parNode = parNode.right;
							continue;
						}
					}
				}
			}
			parNode = rootNode;
		}
		LRV(rootNode);
	}
	
	public static void LRV(Node node) { // 후위순회
		if(node.left != null) {
			LRV(node.left);
		}
		if(node.right != null) {
			LRV(node.right);
		}
		System.out.println(node.data);
	}
}
