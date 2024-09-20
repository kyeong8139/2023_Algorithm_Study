import java.io.*;
import java.util.*;

public class softeer_클러스터 {
    static int[] computer;
    static long B;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long answer = 0;
        int N = sc.nextInt();
        B = sc.nextLong();
        computer = new int[N]; //컴퓨터들의 성능을 저장

        for(int i = 0; i < N; i++){
            computer[i] = sc.nextInt();
        }
        Arrays.sort(computer);//크기를 정렬
        long low = computer[0];
        long high = computer[N-1] + (long)Math.sqrt(B);//최대값 + 성능 최대치
        while(low <= high){
            long mid = (low + high)/2;
            if(check(mid)){
                low = mid + 1;
                answer = mid;
            } else{
                high = mid - 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean check(long min){
        long cost = 0;
        for(int c : computer){
            if(c < min){
                cost += Math.pow((min-c), 2); //만약 최소값이 여러개인 경우 얼마가 드는지 확인
                if(cost > B) //원래 최대 가격보다 비싸면 안됨
                    return false;
            }
        }

        return true;
    }
}
