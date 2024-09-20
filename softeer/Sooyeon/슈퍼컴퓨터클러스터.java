import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class 슈퍼컴퓨터클러스터 {

    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());

        int jump = (int) Math.pow(2, 29);

        // List<Integer> list = new ArrayList<>();
        int[] array = new int[N];
        // Map<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(bf.readLine());

        int goal = 0;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            // list.add(input);
            array[i] = input;
            // map.put(input, map.getOrDefault(input, 0)+1);
        }

        BigInteger cost;
        // PriorityQueue<Integer> pcq = new PriorityQueue<>((i1, i2) -> i1 - i2);

        int answer = 0;

        while (jump > 0) {

            cost = BigInteger.ZERO;

            // pcq.clear();
            // pcq.addAll(list);

            // while (!pcq.isEmpty() && pcq.peek() < goal) {
            // cost = cost.add(new BigInteger(Integer.toString(goal - pcq.poll())).pow(2));

            // if (B.compareTo(cost) < 0) break;
            // }

            for (int i = 0; i < N; i++) {
                int cur = array[i];
                if (cur < goal)
                    cost = cost.add(new BigInteger(Integer.toString(goal - cur)).pow(2));

                if (B.compareTo(cost) < 0) {
                    jump /= 2;
                    goal -= jump;
                    break;
                }
            }

            // for( Integer key : map.keySet() ){
            // if (key < goal) {
            // cost = cost.add(
            // new BigInteger(Integer.toString(goal - key))
            // .pow(2)
            // .multiply(new BigInteger(Integer.toString(map.get(key))))
            // );
            // }
            // if (B.compareTo(cost) < 0) {
            // jump /= 2;
            // goal -= jump;
            // break;
            // }
            // }

            if (B.compareTo(cost) >= 0) {
                answer = goal;
                goal += jump;
            }

        }

        System.out.println(answer);

    }

}