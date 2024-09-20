import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        String key = sc.next();

        // 주어진 키를 5 x 5 크기의 표로 변환
        int[][] pos = new int[26][2];
        char[][] board = new char[5][5];
        int rowIdx = 0;
        int colIdx = 0;
        int alphaCnt = 0;

        for (int i = 0; i < key.length(); i++) {
            int alphaIdx = key.charAt(i) - 'A';
            if ((alphaCnt & (1 << alphaIdx)) != 0) continue;

            board[rowIdx][colIdx] = key.charAt(i);
            pos[alphaIdx][0] = rowIdx;
            pos[alphaIdx][1] = colIdx;
            
            alphaCnt |= 1 << alphaIdx;
            rowIdx += (++colIdx / 5);
            colIdx %= 5;

            if (rowIdx == 4 && colIdx == 4) break;
        }
        
        for (int i = 0; i < 26; i++) {
            if (rowIdx == 5 && colIdx == 0) break;
            if ((alphaCnt & (1 << i)) != 0 || 'A' + i == 'J') continue;

            board[rowIdx][colIdx] = (char) ('A' + i);
            pos[i][0] = rowIdx;
            pos[i][1] = colIdx;
            
            alphaCnt |= 1 << i;
            rowIdx += (++colIdx / 5);
            colIdx %= 5;
        }

        int idx = 0; 
        StringBuilder sb = new StringBuilder();
        while (idx < message.length()) {
            char left = message.charAt(idx++);

            char right;
            if (idx == message.length() || (message.charAt(idx) == left && left != 'X')) {
                right = 'X';
                idx--;
            } else if (message.charAt(idx) == left && left == 'X') {
                right = 'Q';
                idx--;
            } else {
                right = message.charAt(idx);
            }

            if (pos[left-'A'][0] == pos[right-'A'][0]) {
                int row = pos[left-'A'][0];
                int col = (pos[left-'A'][1] + 1) % 5;
                sb.append(board[row][col]);

                col = (pos[right-'A'][1] + 1) % 5;
                sb.append(board[row][col]);
            } else if (pos[left-'A'][1] == pos[right-'A'][1]) {
                int row = (pos[left-'A'][0] + 1) % 5;
                int col = pos[left-'A'][1];
                sb.append(board[row][col]);
                
                row = (pos[right-'A'][0] + 1) % 5;
                sb.append(board[row][col]);
            } else {
                int row = pos[left-'A'][0];
                int col = pos[right-'A'][1];
                sb.append(board[row][col]);

                row = pos[right-'A'][0];
                col = pos[left-'A'][1];
                sb.append(board[row][col]);
            }

            idx++;
        }

        System.out.println(sb);
    }
}
