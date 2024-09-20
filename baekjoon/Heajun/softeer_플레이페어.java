import java.util.*;
public class softeer_플레이페어 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String key = sc.next();
        char[][] arr = new char[5][5];
        List<Character> list = new ArrayList<>();

        // 알파벳 리스트
        for (int i = 0; i < 26; i++) {
            if ((char) 'A' + i == 'J') {
                continue;
            }
            list.add((char) ('A' + i));
        }
        int r = 0;
        int c = 0;

        // 배열에 삽입
        for (int i = 0; i < key.length(); i++) {
            if (list.contains(key.charAt(i))) {
                arr[r][c] = key.charAt(i);
                list.remove(Character.valueOf(arr[r][c]));
                c++;
                if (c == 5) {
                    c = 0;
                    r++;
                }
            }
        }

        // 나머지 알파벳
        for (int i = 0; i < list.size(); i++) {
            arr[r][c] = list.get(i);
            c++;
            if (c == 5) {
                c = 0;
                r++;
            }
        }


        // 두 글자씩 쌍 지우기
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        int idx = 0;
        while (idx < sb.length() - 1) {
            if (sb.charAt(idx) == sb.charAt(idx + 1)) {
                // XXX 경우
                if (sb.charAt(idx) == 'X') {
                    sb.insert(idx + 1, 'Q');
                } else {
                    sb.insert(idx + 1, 'X');
                }
            }
            idx += 2;
        }

        // String이 홀수 길이일 경우 맨 뒤에 X 추가
        if (sb.length() % 2 == 1) {
            sb.append('X');
        }

        idx = 0;
        while (idx < sb.length() - 1) {
            char c1 = sb.charAt(idx);
            char c2 = sb.charAt(idx + 1);
            int x = 0;
            int y = 0;
            int x2 = 0;
            int y2 = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (c1 == arr[i][j]) {
                        x = i;
                        y = j;
                    }
                    if (c2 == arr[i][j]) {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
            // 같은 행에 존재
            if (x == x2) {
                if (y + 1 >= 5)
                    y = -1;
                if (y2 + 1 >= 5)
                    y2 = -1;
                sb.setCharAt(idx, arr[x][y + 1]);
                sb.setCharAt(idx + 1, arr[x2][y2 + 1]);
                idx += 2;
                continue;
            }

            // 같은 열에 존재할 경우
            if (y == y2) {
                if (x + 1 >= 5)
                    x = -1;
                if (x2 + 1 >= 5)
                    x2 = -1;
                sb.setCharAt(idx, arr[x + 1][y]);
                sb.setCharAt(idx + 1, arr[x2 + 1][y]);
                idx += 2;
                continue;
            }

            // 서로 다른 경우 교환
            sb.setCharAt(idx, arr[x][y2]);
            sb.setCharAt(idx + 1, arr[x2][y]);
            idx += 2;
        }
        System.out.println(sb);
    }

}
