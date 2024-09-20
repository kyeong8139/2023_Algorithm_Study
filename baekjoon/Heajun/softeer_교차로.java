import java.io.*;
import java.util.*;

public class softeer_교차로 {
    static class Car {
        int n;
        int time;

        Car(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }
    public static void main(String[] args){
        int N;
        int[] arr;
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        Arrays.fill(arr, -1);

        Queue<Car>[] q = new Queue[4];
        for (int i = 0; i < 4; ++i)
            q[i] = new ArrayDeque();

        for (int i = 0; i < N; ++i) {
            int t = sc.nextInt();
            int dir = sc.next().charAt(0) - 'A';
            q[dir].add(new Car(i, t));
        }

        int curr = -1;
        while(true){
            if(q[0].isEmpty() && q[1].isEmpty() && q[2].isEmpty() && q[3].isEmpty())
                break;

            int[] state = new int[4];
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < 4; ++i){
                if(!q[i].isEmpty()){
                    int t = q[i].peek().time;
                    min = Math.min(t, min);
                    if(t <= curr)
                        state[i] = 1;
                }
            }
            int count = 0;
            for(int value : state)
                count += value;

            if(count == 0){
                // 아직 교차로에 진입하지 않음.
                // 가장 빠른 차량의 시간으로
                curr = min;
            } else if(count == 4)
                //  교착상태
                break;
            else{
                for(int i = 0; i < 4; ++i){
                    // 현재 방향에서 진행 가능, 오른쪽은 x
                    if(state[i] != 0 && state[(i+3) % 4] == 0){
                        arr[q[i].poll().n] = curr;
                    }
                }
                curr += 1;
            }
        }

        for (int i = 0; i < N; ++i)
            sb.append(arr[i]).append("\n");

        System.out.println(sb);
    }

}
