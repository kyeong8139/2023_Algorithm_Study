import java.util.*;
 
public class 숨바꼭질3_13549 {    
 
    static int min = Integer.MAX_VALUE;
    static int n, k;
    static boolean[] visited;
    static int max = 100000;
    static Queue<Node> q = new LinkedList<>();
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        n = scan.nextInt();
        k = scan.nextInt();
        
        visited = new boolean[max + 1]; // 비지티드 배열 처리
        q.offer(new Node(n, 0)); // 초창기 위치 입력
        bfs();
        System.out.println(min);
    }
    
    public static void bfs() {
        while(!q.isEmpty()) {
            Node node = q.poll(); // 큐에서 꺼내서
            visited[node.loc] = true; // 해당 위치 true처리 시켜주고
            if(node.loc == k) {  // 노드가 목표 위치 도달하면 min과 비교후 작다면 min 업데이트
            	min = Math.min(min, node.time);
            }
            if(node.loc * 2 <= max && visited[node.loc * 2] == false) { // 2배 위치 이동
            	q.offer(new Node(node.loc * 2, node.time));
            }
            if(node.loc + 1 <= max && visited[node.loc + 1] == false) { // +1 위치 이동
            	q.offer(new Node(node.loc + 1, node.time + 1));
            }
            if(node.loc - 1 >= 0 && visited[node.loc - 1] == false) { // -1 위치 이동
            	q.offer(new Node(node.loc - 1, node.time + 1));
            }
        }
    }
    
    public static class Node {
        int loc;
        int time;
        
        public Node(int loc, int time) {
            this.loc = loc;
            this.time = time;
        }
    }
}
