import java.io.IOException;
import java.util.Scanner;

public class 별찍기11_2448 {
    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int k =1 , cnt = 2;
        while(cnt != N/3) {
            k++;
            cnt *= 2;
        }

        for(int length = 0; length < 3;length++) {
            
        }
    }
}
