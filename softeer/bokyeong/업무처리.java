import java.io.*;
import java.util.*;

public class Main {

    static class Person {
        Queue<Integer> odd = new ArrayDeque<>();
        Queue<Integer> even = new ArrayDeque<>();
    }
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int h = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        Person[] tree = new Person[(int) Math.pow(2, h + 1)];
        // 0번에는 완료된 업무가 들어갈 것임
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Person();
        }

        // 말단 직원에게 업무 배정 -> 홀수번째 날짜부터 교대로 업무를 배정하면 업무 순서가 바뀌지 않음
        int start = (int) Math.pow(2, h); // 말단 직원의 시작 번호
        for (; start < tree.length; start++) {
            st = new StringTokenizer(br.readLine());
            
            boolean isOdd = true;
            while (st.hasMoreTokens()) {
                if (isOdd) {
                    tree[start].odd.offer(Integer.parseInt(st.nextToken()));
                } else {
                    tree[start].even.offer(Integer.parseInt(st.nextToken()));
                }
            }

            isOdd = !isOdd;
        }

        // R일간 업무처리
        for (int i = 1; i <= r; i++) {
            for (int j = 0; j < tree.length; j++) {

                Person parent = tree[j / 2];
                Person cur = tree[j];

                // 짝수일 => 오른쪽 직원이 올린 업무 처리
                if (i%2 == 0 && !cur.even.isEmpty()) {
                    // 상사의 왼쪽 직원일 경우와 오른쪽 직원일 경우를 구분
                    if (j % 2 == 0) {
                        parent.odd.offer(cur.even.poll());
                    } else {
                        parent.even.offer(cur.even.poll());
                    }
                } else if (i % 2 == 1 && !cur.odd.isEmpty()) {
                    if (j % 2 == 0) {
                        parent.odd.offer(cur.odd.poll());
                    } else {
                        parent.even.offer(cur.odd.poll());
                    }
                }
            }
        }

        int answer = 0;
        while (!tree[0].odd.isEmpty()) {
            answer += tree[0].odd.poll();
        }

        while (!tree[0].even.isEmpty()) {
            answer += tree[0].even.poll();
        }

        System.out.println(answer);
    }
}
