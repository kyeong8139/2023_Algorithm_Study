import java.util.*;
import java.io.*;

public class 교차로_6256 {

    static class Car implements Comparable<Car>{
        int idx;
        int time;
        int roadNum;
    
        Car(int idx, int time, int roadNum) {
            this.idx = idx;
            this.time = time;
            this.roadNum = roadNum;
        }
    
        public int compareTo(Car o) {
            return this.time - o.time;
        }
    }
    
    static int N, K = 4;
    static int[] passTime;
    static Queue<Car>[] road;
    static PriorityQueue<Car> waitingQ;

    public static void main(String args[]) throws IOException {
        init();

        int time = 0;
        while (true) {
            if (isAllRoadFull())
                break;
            addCarToRoad(time);
            passCar(time);
            if (waitingQ.isEmpty() && isRoadEmpty())
                break;

            if (!isRoadEmpty())
                time++;
            else
                time = waitingQ.peek().time;
        }
        printResult();
    }

    static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        passTime = new int[N];
        Arrays.fill(passTime, -1);
        waitingQ = new PriorityQueue<>();
        road = new ArrayDeque[K];
        for (int i = 0; i < K; i++) {
            road[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            int roadNum = st.nextToken().charAt(0) - 'A';
            waitingQ.add(new Car(i, time, roadNum));
        }
    }

    static boolean isRoadEmpty() {
        for (int i = 0; i < K; i++) {
            if (!road[i].isEmpty())
                return false;
        }
        return true;
    }

    static boolean isAllRoadFull() {
        for (int i = 0; i < K; i++) {
            if (road[i].isEmpty())
                return false;
        }
        return true;
    }

    static void addCarToRoad(int time) {
        while (!waitingQ.isEmpty() && waitingQ.peek().time == time) {
            Car car = waitingQ.poll();
            road[car.roadNum].add(car);
        }
    }

    static void passCar(int time) {
        boolean[] isPassed = new boolean[K];
        for (int i = 0; i < K; i++) {
            int next = (i + 3) % 4;
            if (road[next].isEmpty() && !road[i].isEmpty() && !isPassed[next]) {
                Car car = road[i].poll();
                passTime[car.idx] = time;
                isPassed[i] = true;
            }
        }
    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(passTime[i] + "\n");
        }
        System.out.println(sb);
    }
}