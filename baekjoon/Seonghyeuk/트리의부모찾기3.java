import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리의부모찾기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 0; i < N + 1; ++i) {
            arr[i] = -1;
        }
        arr[1] = 0;
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < N - 1; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer[] edge = new Integer[2];
            edge[0] = Integer.parseInt(st.nextToken());
            edge[1] = Integer.parseInt(st.nextToken());
            list.add(edge);
        }

        // 삭제할 인덱스를 저장하는 리스트
        ArrayList<Integer> toRemove = new ArrayList<>();

        while (!list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                Integer[] edge = list.get(i);
                if (arr[edge[0]] != -1) {
                    arr[edge[1]] = edge[0];
                    toRemove.add(i);
                    continue;
                } else if (arr[edge[1]] != -1) {
                    arr[edge[0]] = edge[1];
                    toRemove.add(i);
                    continue;
                }
            }
            // 저장된 인덱스를 역순으로 제거
            for (int i = toRemove.size() - 1; i >= 0; i--) {
                list.remove(toRemove.get(i).intValue());
            }
            toRemove.clear(); // 리스트 비우기
        }

        for (int i = 2; i < N + 1; ++i) {
            sb.append(arr[i] + "\n");
        }
        System.out.print(sb);
    }
}
