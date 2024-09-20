import java.io.*;
import java.util.*;

public class Main {

    static class Car {
        int idx;
        int time;
        char road;

        public Car (int idx, int time, char road) {
            this.idx = idx;
            this.time = time;
            this.road = road;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        List<Queue<Car>> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new ArrayDeque<>());
        }
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = i;
            int time = Integer.parseInt(st.nextToken());
            char road = st.nextToken().charAt(0);

            list.get(road - 'A').offer(new Car(idx, time, road));
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        int curTime = 0;
        while (true) {
            int minTime = Integer.MAX_VALUE;
            int cnt = 0; 
            
            for (int i = 0; i < 4; i++) {
                Queue<Car> cur = list.get(i);
                if (cur.isEmpty()) continue;

                if (cur.peek().time < minTime) {
                    minTime = cur.peek().time;
                } 
            }

            if (curTime < minTime) {
                curTime = minTime;
            }

            for (int i = 0; i < 4; i++) {
                Queue<Car> cur = list.get(i);
                if (cur.isEmpty()) continue;

                if (cur.peek().time <= curTime) {
                    cnt |= 1 << i;
                }
            }
            
            // 교착상태이거나 도로에 차가 한 대도 없을 경우
            if (cnt == ((1 << 4) - 1) || cnt == 0) break;
            
            for (int i = 0; i < 4; i++) {
                if ((cnt & (1 << i)) == 0) continue;
                
                int next = (4 + (i - 1)) % 4;
                if ((cnt & (1 << next)) == 0) {
                    Car car = list.get(i).poll();
                    answer[car.idx] = curTime;
                } else {
                    list.get(i).peek().time++;
                }
            }
            curTime++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.print(sb);
    }
}
