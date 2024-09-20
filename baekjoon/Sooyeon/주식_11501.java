import java.util.Scanner;
import java.util.Stack;

public class 주식_11501 {
    static long ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //test case 받기

        for(int i=0;i<n;i++) {

            Stack<Integer> st = new Stack<>(); //새로운 stack 생성
            ans = 0; //정답 초기화

            int k = sc.nextInt(); //stack에 넣을 횟수 받기

            for(int j=0;j<k;j++) {
                st.push(sc.nextInt());
            }

            getMoney(st, st.pop()); //getMoney함수에 stack과 가장 마지막값 넣어서 호출
            System.out.println(ans);

        }

    }

    private static void getMoney(Stack<Integer> st, int max) {
        while(!st.empty()) { //stack이 empty가 아닐때만 실행
            int stInt = st.pop(); //stack에서 값을 받아와
        
            if(max < stInt) { //값이 max값 보다 크다면, max값을 바꿔주어 함수 다시 실행
                getMoney(st, stInt);
            } else { //max가 더 크다면 max값으로 주식을 팔고, 같은 함수 다시실행
                ans +=(long) max - stInt;
                getMoney(st, max);
            }
        }
    }
}