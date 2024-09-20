public class pro_삼각달팽이 {
    class Solution {
        public int[] solution(int n) {
            int[] answer = new int[n*(n+1) / 2];
            int[][] arr = new int[n][n];
            int x = -1, y = 0, value = 1;

            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {
                    // 아래
                    if(i % 3 == 0) {
                        x++;
                    }
                    // 오른쪽
                    else if(i % 3 == 1) {
                        y++;
                    }
                    // 대각선
                    else if(i % 3 == 2) {
                        x--;
                        y--;
                    }
                    arr[x][y] = value++;
                }
            }

            int index = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    // 0일 경우 반복문 종료
                    if(arr[i][j] == 0) {
                        break;
                    }
                    answer[index++] = arr[i][j];
                }
            }

            return answer;
        }
    }
}
