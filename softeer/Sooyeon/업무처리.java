import java.io.*;
import java.util.*;

public class 업무처리 {
    static int H, K, R;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bf.readLine().split(" ");
        H = stoi(inputs[0]);
        K = stoi(inputs[1]);
        R = stoi(inputs[2]);

        int N = 1 << H; // 전체 노드의 수
        Queue<Integer>[] task = new Queue[N];
        for (int i = 0; i < N; ++i) {
            task[i] = new ArrayDeque<>();
        }

        int leaf = 1 << (H - 1); // 말단 직원 중 가장 낮은 번호
        for (int i = leaf; i < N; ++i) {
            inputs = bf.readLine().split(" ");
            for (int j = 0; j < K; ++j) {
                task[i].add(stoi(inputs[j]));
            }
        }

        int result = 0;
        for (int day = 1; day <= R; ++day) {
            int bit = day % 2;

            // 부서장
            if (!task[0].isEmpty()) {
                result += task[0].poll();
            }

            // 직원 (말단 직원 + 일반 직원)
            for (int i = 1; i < leaf; ++i) {
                int parent = i / 2;
                if (task[i].isEmpty()) {
                    continue;
                }
                task[parent].add(task[i].poll());
            }
        }
        System.out.println(result);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
