import java.io.*;
import java.util.*;

public class 업무처리 {
    static int H, K, R;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = in.readLine().split(" ");
        H = stoi(inputs[0]);
        K = stoi(inputs[1]);
        R = stoi(inputs[2]);

        int N = 1 << (H+1); // 전체 노드의 수
        Queue<Integer>[][] task = new Queue[2][N];
        for(int i = 0 ; i < 2; ++i)
            for(int j = 0; j < N; ++j)
                task[i][j] = new ArrayDeque();

        int leaf = 1 << H; // 말단 직원중 가장 낮은 번호
        for(int i = leaf; i < leaf * 2; ++i){
            inputs = in.readLine().split(" ");
            for(int j = 0; j < K; ++j)
                task[0][i].add(stoi(inputs[j]));
        }

        int result = 0;
        for(int day = 1; day <= R; ++day){
            int bit = day % 2;
            // bit : 1, 홀수 일
            // bit : 0, 짝수 일

            // 부서장
            if(!task[bit][1].isEmpty())
                result += task[bit][1].poll();
            
            // 일반 직원
            for(int i = 2; i < leaf; ++i){
                int parent = i / 2;
                if(task[bit][i].isEmpty())
                    continue;
                task[(i+1)%2][parent].add(task[bit][i].poll());
            }

            // 말단 직원
            for(int i = leaf; i < leaf * 2; ++i){
                int parent = i / 2;
                if(task[0][i].isEmpty())
                    continue;
                task[(i+1)%2][parent].add(task[0][i].poll());
            }
        }
        System.out.println(result);
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}