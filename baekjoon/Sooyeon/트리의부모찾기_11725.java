import java.io.*;
import java.util.*;

public class 트리의부모찾기_11725 {
    /**
    문제
    루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

    입력
    첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

    출력
    첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
     **/


    static ArrayList<Integer>[] arr; //연결된 노드를 담을 ArrayList를 배열로 만들기
    static int num, node[]; //num은 노드의 개수, node[]는 각 정점의 부모노드를 저장하는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(bf.readLine());

        //연결된 노드를 넣을 ArrayList만들기
        arr = new ArrayList[num];
        node = new int[num];
        for(int i=0;i<num;i++) {
            arr[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=1;i<num;i++) {
            st = new StringTokenizer(bf.readLine());
            //두 정점을 받되, index에 넣기 위해 -1씩.
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;

            //두 노드는 연결돼있으므로, 각각의 ArrayList에 인자로 넣어줌
            arr[a].add(b); arr[b].add(a);
        }

        //트리의 루트는 1이므로, 0의 부모노드는 -1라고 두기.
        node[0] = -1;

        //1부터 트리를 dfs로 타고 내려가 부모를 node배열에 저장해주기.
        findParant(0);
        for(int i=1;i<num;i++) {
            sb.append(node[i]+"\n");
        }
        System.out.println(sb.toString());
    }
    private static void findParant(int n) {
        //n과 연결된 모든 노드를 arrayList에서 for문으로 가져오기
        for(int i : arr[n]) {
            //양방향 노드이므로, ArrayList내 값은 자식노드이거나 부모노드.
            //최상위트리에서부터 타고내려오므로, node에 아무런 값이 저장되어 있지 않다면 n의 자식노드.
            if(node[i] == 0) {
                //idx저장을 위해 -1 해줬으므로, node에 저장할 땐 +1 해줌.
                node[i] = n+1;
                //해당 노드의 자식 찾기.
                findParant(i);
            }
        }
    }
}