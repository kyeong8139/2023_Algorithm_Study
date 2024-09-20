import java.io.*;
import java.util.*;

public class softeer_통근버스 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long answer = 0;
        int[] bus = new int[N];


        for(int i = 0; i < N; i++){
            bus[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++){
            int cnt  =  0;
            for(int j = i + 1; j < N; j++){
                if(bus[i] < bus[j])
                    cnt++;
                else
                    answer += cnt;
            }
        }

        System.out.println(answer);
    }
}
