import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class bj16719_ZOAC {

    static class Node implements Comparable<Node> {
        Node left;
        Node right;
        int idx;
        char data;

        public Node() {};

        public Node(int idx, char data) {
            this.idx = idx;
            this.data = data;
        }

        @Override
        public int compareTo(Node o) {
            if(this.data == o.data) {
                return this.idx - o.idx;
            }
            return this.data - o.data;
        }

        public void add(Node node) {
            if(this.idx > node.idx) {
                if(this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if(this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }
    }

    static int index;
    static char[] ans;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        char[] word = sc.next().toCharArray();
        ans = new char[word.length];
        visited = new boolean[word.length];
        Node[] nodes = new Node[word.length];

        int maxIdx = -1;
        char maxData = 'Z' + 1;
        for(int i = 0; i < word.length; i++) {
            if(maxData > word[i]) {
                maxIdx = i;
                maxData = word[i];
            }

            nodes[i] = new Node(i, word[i]);
        }

        Node root = new Node(maxIdx, maxData);

        Arrays.sort(nodes);

        for(int i = 1; i < word.length; i++) {
            root.add(nodes[i]);
        }

        index = 0;
        dfs(root);

        System.out.println(new String(ans));

        System.out.println(sb);
    }


    public static void dfs(Node node) {
        StringBuilder temp = new StringBuilder();

        if(index == ans.length) return;

        ans[node.idx] = node.data;

        for(int i = 0; i < ans.length; i++) {
            if(ans[i] == '\u0000') continue;
            temp.append(ans[i]);
        }

        sb.append(temp.toString()).append("\n");

        if(node.right != null) {
            dfs(node.right);
        }

        if(node.left != null) {
            dfs(node.left);
        }
    }

}