import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class N과M8_15657 {
    static int n, m, arr[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
          N개의 자연수 중에서 M개를 고른 수열
          같은 수를 여러 번 골라도 된다.
          고른 수열은 비내림차순이어야 한다.
        **/

        //입력받은 N개의 수를 오름차순으로 정렬 후 
        Arrays.sort(arr);
        //만든 수열을 저장할 LinkedList 생성
        LinkedList<Integer> ansList = new LinkedList<>();
        dfs(ansList);
        System.out.print(sb.toString());
    }
    static void dfs(LinkedList<Integer> ansList) {
        //LinkedList 안에 m개의 수가 들어가있다면 출력
        if(ansList.size() == m) {
            for(int a : ansList) {
                sb.append(a+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<n;i++) {
            //LinkedList에 아무것도 없는 상태이거나, LinkedList에 마지막으로 담긴 수 보다 대상의 수가 더 클 때
            if(ansList.size()==0 || ansList.getLast() <= arr[i]) {
                //LinkedList에 해당 수를 넣고 dfs
                ansList.add(arr[i]);
                dfs(ansList);
                //dfs 실행 후 해당 수 삭제
                ansList.removeLast();
            }
        }
    }
}
