import java.util.Scanner;

public class 행렬_1080 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        int M = sc.nextInt();

        char[][] A = new char[N][M]; //첫번째 행렬
        char[][] B = new char[N][M]; //두번째 행렬
        for(int i = 0; i<N;i++){
            String str = sc.next();
            for(int j = 0; j < M; j++){
                A[i][j] = str.charAt(j); //첫번쨰 행렬 입력
            }
        }
        for(int i = 0; i<N;i++){
            String str = sc.next();
            for(int j = 0; j < M; j++){
                B[i][j] = str.charAt(j); //두번째 행렬 입력
            }
        }
        if(N < 3 && M < 3){ //N과 M이 필터사이즈보다 작을 경우 이미 두 행렬이 같은 경우 0출력 아닌 경우 -1
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(A[i][j]!=B[i][j]){
                        System.out.println(-1);
                        return;
                    }
                }
            }
            System.out.println(0);
            return;
        }
        int answer = 0;
        for(int i = 0; i < N -2; i++){
            for(int j = 0; j < M-2; j++){ //크기에서 필터 크기만큼 뺀 횟수를 순회
                if(A[i][j] == B[i][j]){
                    continue; //같을 경우 다음 단계
                }
                //아닌 경우 횟수 증가
                answer++;
                for(int k = i; k < i + 3; k++){
                    for(int l = j; l < j+3; l++){
                        A[k][l] = A[k][l] == '1' ? '0':'1';
                    }
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] != B[i][j]) {
                    System.out.println(-1); //마지막 검사 같으면 횟수 아니면 -1
                    return;
                }
            }
        }
        System.out.println(answer);

    }
}
