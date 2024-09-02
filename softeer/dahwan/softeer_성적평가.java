import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static class Node implements Comparable<Node>{
    	int no;
        int idx;
        int score;

        public Node() { }
        
        public Node(int no, int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

		@Override
		public int compareTo(Node o) {
			if(this.score == o.score) {
				return this.idx - o.idx;
			}
			return o.score - this.score;
		}
        
        @Override
        public String toString() {
            return "[" + idx + " " + score + "]";
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] finalScore = new int[N];
        int num = 0;
        
        for(int i=0; i<3; i++) {
            TreeSet<Node> tree = new TreeSet<>();
            int[] ranks = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int score = Integer.parseInt(st.nextToken());
                finalScore[j] += score;
                tree.add(new Node(num++, j, score));
            }
            
            int rank = 1;
            int beforeIndex = 0;
            int beforeScore = 0;
            for(int j=0; j<N; j++){
                Node node = tree.pollFirst();
                if(node.score == beforeScore) {
                    ranks[node.idx] = ranks[beforeIndex];
                } else {
                    ranks[node.idx] = rank;
                    beforeScore = node.score;
                    beforeIndex = node.idx;
                }
                rank++;
            }

            for(int j=0; j<N; j++) {
                sb.append(ranks[j] + " ");
            }

            sb.append("\n");
        }

        TreeSet<Node> tree = new TreeSet<>();

        for(int i=0; i<N; i++) {
            tree.add(new Node(num++, i, finalScore[i]));
        }
        
        int rank = 1;
        int beforeIndex = 0;
        int beforeScore = 0;
        for(int i=0; i<N; i++) {
            Node node = tree.pollFirst();
            if(node.score == beforeScore) {
            	finalScore[node.idx] = finalScore[beforeIndex];
            } else {
                finalScore[node.idx] = rank;
                beforeScore = node.score;
                beforeIndex = node.idx;
            }
            rank++;
        }

        for(int i=0; i<N; i++) {
            sb.append(finalScore[i] + " ");
        }
        
        System.out.println(sb);
    }
}
