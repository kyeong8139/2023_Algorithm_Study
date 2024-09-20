import java.util.*;
import java.io.*;

public class 성적평가 {
    static int n;

    public static class Contest {
        int id, score;

        Contest(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(bf.readLine());

        Contest[][] c = new Contest[4][n];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                c[i][j] = new Contest(j, Integer.parseInt(st.nextToken())); // 참가자 번호, 점수
            }
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += c[j][i].score;
            }
            c[3][i] = new Contest(i, sum);
        }

        // 3개의 대회에 대한 등수, 전체 등수 출력
        // 성적 순 내림차순 정렬
        for (Contest[] con : c) {
            Arrays.sort(con, new Comparator<Contest>() {

                @Override
                public int compare(Contest o1, Contest o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o2.score, o1.score);
                }

            });
        }

        int[][] r = new int[4][n];
        for (int i = 0; i < 4; i++) {
            int rank = 1;
            int cnt = 1;
            r[i][c[i][0].id] = rank;
            for (int j = 1; j < n; j++) {
                // 점수가 같으면 rank가 같은
                if (c[i][j - 1].score == c[i][j].score) {
                    r[i][c[i][j].id] = rank;
                    cnt++;
                } else {
                    rank += cnt;
                    r[i][c[i][j].id] = rank;
                    cnt = 1;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }

    }
}