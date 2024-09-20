import java.io.*;
import java.util.*;

public class 트리의지름_1967 {
    static class Route {
        int edge, value;

        Route(int edge, int value) {
            this.edge = edge;
            this.value = value;
        }
    }
    static int max;
    static int arr[][];
    static ArrayList<Route> list[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        list = new ArrayList[N];

        for(int i=0;i<N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int startEdge = Integer.parseInt(st.nextToken())-1,
             endEdge = Integer.parseInt(st.nextToken())-1,
             value = Integer.parseInt(st.nextToken());

             //트리의 부모를 index로 하는 list 내부에 자식노드와 가중치를 갖는 Route를 넣어주기.
            list[startEdge].add(new Route(endEdge, value));
        }
        max = 0;
        getLength(0);
        System.out.println(max);
        
    }

    static int getLength(int edge) {
        switch (list[edge].size()) {
            case 0: //리프일경우 가중치가 없음
                return 0;
            case 1: //자식노드가 하나일경우
                Route childNode = list[edge].get(0);
                int childNodeLength = getLength(childNode.edge)+childNode.value;
                // max는 계속해서 갱신
                max = Math.max(childNodeLength, max);
                return childNodeLength;
            default: //두개 이상
                PriorityQueue<Integer> que = new PriorityQueue<>((a, b)->b-a);
                for(Route route : list[edge]){
                    que.add(getLength(route.edge)+route.value);
                }
                int tmp1 = que.poll();
                int tmp2 = que.poll();
                max = Math.max(max, tmp1+tmp2);
                return tmp1;
        }
    }
}