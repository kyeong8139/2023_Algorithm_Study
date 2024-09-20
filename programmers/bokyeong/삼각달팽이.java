class Solution {
    
    int[][] dirs = {{1, 0}, {0, 1}, {-1, -1}};
    
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        int row = -1;
        int col = 0;
        
        int num = 1;
        int dir = 0;
        int max = ((1 + n) * (n / 2)) + (n % 2 == 0 ? 0 : (n+1) / 2);
        while(num <= max) {
            int nr = row + dirs[dir][0];
            int nc = col + dirs[dir][1];
            
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || arr[nr][nc] != 0) {
                dir = (dir + 1) % 3;
                continue;
            }
             
            row = nr;
            col = nc;
            arr[row][col] = num++;
        }
        
        int[] answer = new int[max];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j]; 
            }
        }
        
        return answer;
    }
}
